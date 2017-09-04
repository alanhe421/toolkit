package jadx.core.dex.visitors.regions;

import jadx.core.dex.attributes.AFlag;
import jadx.core.dex.attributes.AType;
import jadx.core.dex.attributes.nodes.EdgeInsnAttr;
import jadx.core.dex.attributes.nodes.LoopInfo;
import jadx.core.dex.attributes.nodes.LoopLabelAttr;
import jadx.core.dex.instructions.IfNode;
import jadx.core.dex.instructions.InsnType;
import jadx.core.dex.instructions.SwitchNode;
import jadx.core.dex.instructions.args.InsnArg;
import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.Edge;
import jadx.core.dex.nodes.IBlock;
import jadx.core.dex.nodes.IContainer;
import jadx.core.dex.nodes.IRegion;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.dex.regions.Region;
import jadx.core.dex.regions.SwitchRegion;
import jadx.core.dex.regions.SynchronizedRegion;
import jadx.core.dex.regions.conditions.IfInfo;
import jadx.core.dex.regions.conditions.IfRegion;
import jadx.core.dex.regions.loops.LoopRegion;
import jadx.core.dex.trycatch.ExcHandlerAttr;
import jadx.core.dex.trycatch.ExceptionHandler;
import jadx.core.dex.trycatch.SplitterBlockAttr;
import jadx.core.dex.trycatch.TryCatchBlock;
import jadx.core.utils.BlockUtils;
import jadx.core.utils.ErrorsCounter;
import jadx.core.utils.InstructionRemover;
import jadx.core.utils.RegionUtils;
import jadx.core.utils.exceptions.JadxOverflowException;
import jadx.core.utils.exceptions.JadxRuntimeException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegionMaker {
    private static final Logger LOG = LoggerFactory.getLogger(RegionMaker.class);
    private static final int REGIONS_LIMIT = 1000000;
    private final MethodNode mth;
    private BitSet processedBlocks;
    private int regionsCount;

    public RegionMaker(MethodNode mth) {
        this.mth = mth;
    }

    public Region makeRegion(BlockNode startBlock, RegionStack stack) {
        this.regionsCount++;
        if (this.regionsCount > REGIONS_LIMIT) {
            throw new JadxOverflowException("Regions count limit reached");
        }
        Region r = new Region(stack.peekRegion());
        BlockNode next = startBlock;
        while (next != null) {
            next = traverse(r, next, stack);
        }
        return r;
    }

    private BlockNode traverse(IRegion r, BlockNode block, RegionStack stack) {
        BlockNode next = null;
        boolean processed = false;
        List<LoopInfo> loops = block.getAll(AType.LOOP);
        int loopCount = loops.size();
        if (loopCount != 0 && block.contains(AFlag.LOOP_START)) {
            if (loopCount != 1) {
                for (LoopInfo loop : loops) {
                    if (loop.getStart() == block) {
                        next = processLoop(r, loop, stack);
                        processed = true;
                        break;
                    }
                }
            }
            next = processLoop(r, (LoopInfo) loops.get(0), stack);
            processed = true;
        }
        if (!processed && block.getInstructions().size() == 1) {
            InsnNode insn = (InsnNode) block.getInstructions().get(0);
            switch (insn.getType()) {
                case IF:
                    next = processIf(r, block, (IfNode) insn, stack);
                    processed = true;
                    break;
                case SWITCH:
                    next = processSwitch(r, block, (SwitchNode) insn, stack);
                    processed = true;
                    break;
                case MONITOR_ENTER:
                    next = processMonitorEnter(r, block, insn, stack);
                    processed = true;
                    break;
            }
        }
        if (!processed) {
            r.getSubBlocks().add(block);
            next = BlockUtils.getNextBlock(block);
        }
        return (next == null || stack.containsExit(block) || stack.containsExit(next)) ? null : next;
    }

    private BlockNode processLoop(IRegion curRegion, LoopInfo loop, RegionStack stack) {
        BlockNode loopStart = loop.getStart();
        Set<BlockNode> exitBlocksSet = loop.getExitNodes();
        List<BlockNode> exitBlocks = new ArrayList(exitBlocksSet.size());
        BlockNode nextStart = BlockUtils.getNextBlock(loopStart);
        if (nextStart != null && exitBlocksSet.remove(nextStart)) {
            exitBlocks.add(nextStart);
        }
        if (exitBlocksSet.remove(loopStart)) {
            exitBlocks.add(loopStart);
        }
        if (exitBlocksSet.remove(loop.getEnd())) {
            exitBlocks.add(loop.getEnd());
        }
        exitBlocks.addAll(exitBlocksSet);
        LoopRegion loopRegion = makeLoopRegion(curRegion, loop, exitBlocks);
        if (loopRegion == null) {
            BlockNode exit = makeEndlessLoop(curRegion, stack, loop, loopStart);
            insertContinue(loop);
            return exit;
        }
        BlockNode out;
        curRegion.getSubBlocks().add(loopRegion);
        IRegion outerRegion = stack.peekRegion();
        stack.push(loopRegion);
        IfInfo condInfo = IfMakerHelper.searchNestedIf(IfMakerHelper.makeIfInfo(loopRegion.getHeader()));
        IfMakerHelper.confirmMerge(condInfo);
        if (!loop.getLoopBlocks().contains(condInfo.getThenBlock())) {
            condInfo = IfInfo.invert(condInfo);
        }
        loopRegion.setCondition(condInfo.getCondition());
        exitBlocks.removeAll(condInfo.getMergedBlocks());
        if (!exitBlocks.isEmpty()) {
            BlockNode loopExit = condInfo.getElseBlock();
            if (loopExit != null) {
                for (Edge exitEdge : loop.getExitEdges()) {
                    if (exitBlocks.contains(exitEdge.getSource())) {
                        insertBreak(stack, loopExit, exitEdge);
                    }
                }
            }
        }
        if (loopRegion.isConditionAtEnd()) {
            BlockNode thenBlock = condInfo.getThenBlock();
            if (thenBlock == loopStart) {
                out = condInfo.getElseBlock();
            } else {
                out = thenBlock;
            }
            loopStart.remove(AType.LOOP);
            loop.getEnd().add(AFlag.SKIP);
            stack.addExit(loop.getEnd());
            loopRegion.setBody(makeRegion(loopStart, stack));
            loopStart.addAttr(AType.LOOP, loop);
            loop.getEnd().remove(AFlag.SKIP);
        } else {
            out = condInfo.getElseBlock();
            if (outerRegion != null && out.contains(AFlag.LOOP_START) && !out.getAll(AType.LOOP).contains(loop) && RegionUtils.isRegionContainsBlock(outerRegion, out)) {
                out = null;
            }
            stack.addExit(out);
            Region body = makeRegion(condInfo.getThenBlock(), stack);
            BlockNode conditionBlock = condInfo.getIfBlock();
            if (loopStart != conditionBlock) {
                Set<BlockNode> blocks = BlockUtils.getAllPathsBlocks(loopStart, conditionBlock);
                blocks.remove(conditionBlock);
                for (BlockNode block : blocks) {
                    if (!(!block.getInstructions().isEmpty() || block.contains(AFlag.SKIP) || RegionUtils.isRegionContainsBlock(body, block))) {
                        body.add(block);
                    }
                }
            }
            loopRegion.setBody(body);
        }
        stack.pop();
        insertContinue(loop);
        return out;
    }

    private LoopRegion makeLoopRegion(IRegion curRegion, LoopInfo loop, List<BlockNode> exitBlocks) {
        for (BlockNode block : exitBlocks) {
            if (!block.contains(AType.EXC_HANDLER) && block.getInstructions().size() == 1 && ((InsnNode) block.getInstructions().get(0)).getType() == InsnType.IF) {
                List<LoopInfo> loops = block.getAll(AType.LOOP);
                if (loops.isEmpty() || loops.get(0) == loop) {
                    boolean found;
                    LoopRegion loopRegion = new LoopRegion(curRegion, loop, block, block == loop.getEnd());
                    if (block == loop.getStart() || block == loop.getEnd() || BlockUtils.isEmptySimplePath(loop.getStart(), block)) {
                        found = true;
                    } else if (block.getPredecessors().contains(loop.getStart())) {
                        loopRegion.setPreCondition(loop.getStart());
                        found = loopRegion.checkPreCondition();
                    } else {
                        found = false;
                    }
                    if (found && this.mth.getAllLoopsForBlock(block).size() >= 2) {
                        boolean allOuter = true;
                        for (BlockNode outerBlock : block.getCleanSuccessors()) {
                            List<LoopInfo> outLoopList = this.mth.getAllLoopsForBlock(outerBlock);
                            outLoopList.remove(loop);
                            if (!outLoopList.isEmpty()) {
                                allOuter = false;
                                break;
                            }
                        }
                        if (allOuter) {
                            found = false;
                        }
                    }
                    if (found) {
                        return loopRegion;
                    }
                }
            }
        }
        return null;
    }

    private BlockNode makeEndlessLoop(IRegion curRegion, RegionStack stack, LoopInfo loop, BlockNode loopStart) {
        LoopRegion loopRegion = new LoopRegion(curRegion, loop, null, false);
        curRegion.getSubBlocks().add(loopRegion);
        loopStart.remove(AType.LOOP);
        stack.push(loopRegion);
        BlockNode loopExit = null;
        for (Edge exitEdge : loop.getExitEdges()) {
            BlockNode exit = exitEdge.getTarget();
            if (insertBreak(stack, exit, exitEdge)) {
                BlockNode nextBlock = BlockUtils.getNextBlock(exit);
                if (nextBlock != null) {
                    stack.addExit(nextBlock);
                    loopExit = nextBlock;
                }
            }
        }
        Region body = makeRegion(loopStart, stack);
        BlockNode loopEnd = loop.getEnd();
        if (!(RegionUtils.isRegionContainsBlock(body, loopEnd) || loopEnd.contains(AType.EXC_HANDLER) || inExceptionHandlerBlocks(loopEnd))) {
            body.getSubBlocks().add(loopEnd);
        }
        loopRegion.setBody(body);
        if (loopExit == null) {
            BlockNode next = BlockUtils.getNextBlock(loopEnd);
            if (RegionUtils.isRegionContainsBlock(body, next)) {
                loopExit = null;
            } else {
                loopExit = next;
            }
        }
        stack.pop();
        loopStart.addAttr(AType.LOOP, loop);
        return loopExit;
    }

    private boolean inExceptionHandlerBlocks(BlockNode loopEnd) {
        if (this.mth.getExceptionHandlersCount() == 0) {
            return false;
        }
        for (ExceptionHandler eh : this.mth.getExceptionHandlers()) {
            if (eh.getBlocks().contains(loopEnd)) {
                return true;
            }
        }
        return false;
    }

    private boolean canInsertBreak(BlockNode exit) {
        if (exit.contains(AFlag.RETURN) || BlockUtils.checkLastInsnType(exit, InsnType.BREAK)) {
            return false;
        }
        List<BlockNode> simplePath = BlockUtils.buildSimplePath(exit);
        if (!simplePath.isEmpty()) {
            BlockNode lastBlock = (BlockNode) simplePath.get(simplePath.size() - 1);
            if (lastBlock.contains(AFlag.RETURN) || lastBlock.getSuccessors().isEmpty()) {
                return false;
            }
        }
        for (BlockNode block : BlockUtils.getAllPathsBlocks(this.mth.getEnterBlock(), exit)) {
            if (BlockUtils.checkLastInsnType(block, InsnType.SWITCH)) {
                return false;
            }
        }
        return true;
    }

    private boolean insertBreak(RegionStack stack, BlockNode loopExit, Edge exitEdge) {
        BlockNode exit = exitEdge.getTarget();
        BlockNode insertBlock = null;
        boolean confirm = false;
        if (loopExit == exit) {
            BlockNode source = exitEdge.getSource();
            if (source.contains(AType.CATCH_BLOCK) && source.getSuccessors().size() == 2) {
                BlockNode other = BlockUtils.selectOther(loopExit, source.getSuccessors());
                if (other != null && BlockUtils.skipSyntheticSuccessor(other).contains(AType.EXC_HANDLER)) {
                    insertBlock = source;
                    confirm = true;
                }
            }
        }
        if (!confirm) {
            while (exit != null) {
                if (insertBlock == null || !BlockUtils.isPathExists(loopExit, exit)) {
                    insertBlock = exit;
                    List<BlockNode> cs = exit.getCleanSuccessors();
                    exit = cs.size() == 1 ? (BlockNode) cs.get(0) : null;
                } else if (!canInsertBreak(insertBlock)) {
                    return false;
                } else {
                    confirm = true;
                }
            }
        }
        if (!confirm) {
            return false;
        }
        InsnNode breakInsn = new InsnNode(InsnType.BREAK, 0);
        EdgeInsnAttr.addEdgeInsn(insertBlock, (BlockNode) insertBlock.getSuccessors().get(0), breakInsn);
        stack.addExit(exit);
        addBreakLabel(exitEdge, exit, breakInsn);
        return true;
    }

    private void addBreakLabel(Edge exitEdge, BlockNode exit, InsnNode breakInsn) {
        BlockNode outBlock = BlockUtils.getNextBlock(exitEdge.getTarget());
        if (outBlock != null && this.mth.getAllLoopsForBlock(outBlock).isEmpty()) {
            List<LoopInfo> inLoops = this.mth.getAllLoopsForBlock(exitEdge.getSource());
            if (inLoops.size() >= 2) {
                LoopInfo parentLoop = null;
                for (LoopInfo loop : inLoops) {
                    if (loop.getParentLoop() == null) {
                        parentLoop = loop;
                        break;
                    }
                }
                if (parentLoop != null && parentLoop.getEnd() != exit && !parentLoop.getExitNodes().contains(exit)) {
                    LoopLabelAttr labelAttr = new LoopLabelAttr(parentLoop);
                    breakInsn.addAttr(labelAttr);
                    parentLoop.getStart().addAttr(labelAttr);
                }
            }
        }
    }

    private static void insertContinue(LoopInfo loop) {
        BlockNode loopEnd = loop.getEnd();
        List<BlockNode> predecessors = loopEnd.getPredecessors();
        if (predecessors.size() > 1) {
            Set<BlockNode> loopExitNodes = loop.getExitNodes();
            for (BlockNode pred : predecessors) {
                if (canInsertContinue(pred, predecessors, loopEnd, loopExitNodes)) {
                    pred.getInstructions().add(new InsnNode(InsnType.CONTINUE, 0));
                }
            }
        }
    }

    private static boolean canInsertContinue(BlockNode pred, List<BlockNode> predecessors, BlockNode loopEnd, Set<BlockNode> loopExitNodes) {
        if (!pred.contains(AFlag.SYNTHETIC) || BlockUtils.checkLastInsnType(pred, InsnType.CONTINUE)) {
            return false;
        }
        List<BlockNode> preds = pred.getPredecessors();
        if (preds.isEmpty()) {
            return false;
        }
        BlockNode codePred = (BlockNode) preds.get(0);
        if (codePred.contains(AFlag.SKIP) || loopEnd.isDominator(codePred) || loopExitNodes.contains(codePred) || isDominatedOnBlocks(codePred, predecessors)) {
            return false;
        }
        for (BlockNode exit : loopExitNodes) {
            if (BlockUtils.isPathExists(codePred, exit)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDominatedOnBlocks(BlockNode dom, List<BlockNode> blocks) {
        for (BlockNode node : blocks) {
            if (!node.isDominator(dom)) {
                return false;
            }
        }
        return true;
    }

    private BlockNode processMonitorEnter(IRegion curRegion, BlockNode block, InsnNode insn, RegionStack stack) {
        SynchronizedRegion synchRegion = new SynchronizedRegion(curRegion, insn);
        synchRegion.getSubBlocks().add(block);
        curRegion.getSubBlocks().add(synchRegion);
        Set<BlockNode> exits = new HashSet();
        Set<BlockNode> cacheSet = new HashSet();
        traverseMonitorExits(synchRegion, insn.getArg(0), block, exits, cacheSet);
        for (InsnNode exitInsn : synchRegion.getExitInsns()) {
            BlockNode insnBlock = BlockUtils.getBlockByInsn(this.mth, exitInsn);
            if (insnBlock != null) {
                insnBlock.add(AFlag.SKIP);
            }
            exitInsn.add(AFlag.SKIP);
            InstructionRemover.unbindInsn(this.mth, exitInsn);
        }
        BlockNode body = BlockUtils.getNextBlock(block);
        if (body == null) {
            ErrorsCounter.methodError(this.mth, "Unexpected end of synchronized block");
            return null;
        }
        BlockNode exit = null;
        if (exits.size() == 1) {
            exit = BlockUtils.getNextBlock((BlockNode) exits.iterator().next());
        } else if (exits.size() > 1) {
            cacheSet.clear();
            exit = traverseMonitorExitsCross(body, exits, cacheSet);
        }
        stack.push(synchRegion);
        if (exit != null) {
            stack.addExit(exit);
        } else {
            for (BlockNode exitBlock : exits) {
                List<BlockNode> list = BlockUtils.buildSimplePath(exitBlock);
                if (list.isEmpty() || !((BlockNode) list.get(list.size() - 1)).getSuccessors().isEmpty()) {
                    stack.addExit(exitBlock);
                }
            }
        }
        synchRegion.getSubBlocks().add(makeRegion(body, stack));
        stack.pop();
        return exit;
    }

    private static void traverseMonitorExits(SynchronizedRegion region, InsnArg arg, BlockNode block, Set<BlockNode> exits, Set<BlockNode> visited) {
        visited.add(block);
        for (InsnNode insn : block.getInstructions()) {
            if (insn.getType() == InsnType.MONITOR_EXIT && insn.getArg(0).equals(arg)) {
                exits.add(block);
                region.getExitInsns().add(insn);
                return;
            }
        }
        for (BlockNode node : block.getSuccessors()) {
            if (!visited.contains(node)) {
                traverseMonitorExits(region, arg, node, exits, visited);
            }
        }
    }

    private static BlockNode traverseMonitorExitsCross(BlockNode block, Set<BlockNode> exits, Set<BlockNode> visited) {
        visited.add(block);
        for (BlockNode node : block.getCleanSuccessors()) {
            boolean cross = true;
            for (BlockNode exitBlock : exits) {
                if (!BlockUtils.isPathExists(exitBlock, node)) {
                    cross = false;
                    break;
                }
            }
            if (cross) {
                return node;
            }
            if (!visited.contains(node)) {
                BlockNode res = traverseMonitorExitsCross(node, exits, visited);
                if (res != null) {
                    return res;
                }
            }
        }
        return null;
    }

    private BlockNode processIf(IRegion currentRegion, BlockNode block, IfNode ifnode, RegionStack stack) {
        if (block.contains(AFlag.SKIP)) {
            return ifnode.getThenBlock();
        }
        IfInfo currentIf = IfMakerHelper.makeIfInfo(block);
        IfInfo mergedIf = IfMakerHelper.mergeNestedIfNodes(currentIf);
        if (mergedIf != null) {
            currentIf = mergedIf;
        } else {
            currentIf = IfInfo.invert(currentIf);
        }
        IfInfo modifiedIf = IfMakerHelper.restructureIf(this.mth, block, currentIf);
        if (modifiedIf != null) {
            currentIf = modifiedIf;
        } else if (currentIf.getMergedBlocks().size() <= 1) {
            return null;
        } else {
            currentIf = IfMakerHelper.restructureIf(this.mth, block, IfMakerHelper.makeIfInfo(block));
            if (currentIf == null) {
                return null;
            }
        }
        IfMakerHelper.confirmMerge(currentIf);
        IfRegion ifRegion = new IfRegion(currentRegion, block);
        ifRegion.setCondition(currentIf.getCondition());
        currentRegion.getSubBlocks().add(ifRegion);
        BlockNode outBlock = currentIf.getOutBlock();
        stack.push(ifRegion);
        stack.addExit(outBlock);
        ifRegion.setThenRegion(makeRegion(currentIf.getThenBlock(), stack));
        BlockNode elseBlock = currentIf.getElseBlock();
        if (elseBlock == null || stack.containsExit(elseBlock)) {
            ifRegion.setElseRegion(null);
        } else {
            ifRegion.setElseRegion(makeRegion(elseBlock, stack));
        }
        if (ifRegion.getElseRegion() == null && outBlock != null) {
            List<EdgeInsnAttr> edgeInsnAttrs = outBlock.getAll(AType.EDGE_INSN);
            if (!edgeInsnAttrs.isEmpty()) {
                Region elseRegion = new Region(ifRegion);
                for (EdgeInsnAttr edgeInsnAttr : edgeInsnAttrs) {
                    if (edgeInsnAttr.getEnd().equals(outBlock)) {
                        addEdgeInsn(currentIf, elseRegion, edgeInsnAttr);
                    }
                }
                ifRegion.setElseRegion(elseRegion);
            }
        }
        stack.pop();
        return outBlock;
    }

    private void addEdgeInsn(IfInfo ifInfo, Region region, EdgeInsnAttr edgeInsnAttr) {
        BlockNode start = edgeInsnAttr.getStart();
        if (!start.contains(AFlag.SKIP)) {
            boolean fromThisIf = false;
            for (BlockNode ifBlock : ifInfo.getMergedBlocks()) {
                if (ifBlock.getSuccessors().contains(start)) {
                    fromThisIf = true;
                    break;
                }
            }
            if (fromThisIf) {
                region.add(start);
            }
        }
    }

    private BlockNode processSwitch(IRegion currentRegion, BlockNode block, SwitchNode insn, RegionStack stack) {
        int i;
        SwitchRegion switchRegion = new SwitchRegion(currentRegion, block);
        currentRegion.getSubBlocks().add(switchRegion);
        int len = insn.getTargets().length;
        Map<Integer, List<Object>> casesMap = new LinkedHashMap(len);
        for (i = 0; i < len; i++) {
            Object key = insn.getKeys()[i];
            int targ = insn.getTargets()[i];
            List<Object> keys = (List) casesMap.get(Integer.valueOf(targ));
            if (keys == null) {
                List<Object> arrayList = new ArrayList(2);
                casesMap.put(Integer.valueOf(targ), arrayList);
            }
            keys.add(key);
        }
        Map<BlockNode, List<Object>> blocksMap = new LinkedHashMap(len);
        for (Entry<Integer, List<Object>> entry : casesMap.entrySet()) {
            BlockNode c = BlockUtils.getBlockByOffset(((Integer) entry.getKey()).intValue(), block.getSuccessors());
            if (c == null) {
                throw new JadxRuntimeException("Switch block not found by offset: " + entry.getKey());
            }
            blocksMap.put(c, entry.getValue());
        }
        BlockNode defCase = BlockUtils.getBlockByOffset(insn.getDefaultCaseOffset(), block.getSuccessors());
        if (defCase != null) {
            blocksMap.remove(defCase);
        }
        LoopInfo loop = this.mth.getLoopForBlock(block);
        Map<BlockNode, BlockNode> fallThroughCases = new LinkedHashMap();
        List<BlockNode> basicBlocks = this.mth.getBasicBlocks();
        BitSet bitSet = new BitSet(basicBlocks.size());
        bitSet.or(block.getDomFrontier());
        for (BlockNode s : block.getCleanSuccessors()) {
            BitSet df = s.getDomFrontier();
            if (df.cardinality() > 1) {
                if (df.cardinality() > 2) {
                    LOG.debug("Unexpected case pattern, block: {}, mth: {}", s, this.mth);
                } else {
                    BlockNode first = (BlockNode) basicBlocks.get(df.nextSetBit(0));
                    BlockNode second = (BlockNode) basicBlocks.get(df.nextSetBit(first.getId() + 1));
                    BitSet df2;
                    if (second.getDomFrontier().get(first.getId())) {
                        fallThroughCases.put(s, second);
                        df2 = new BitSet(df.size());
                        df2.set(first.getId());
                        df = df2;
                    } else if (first.getDomFrontier().get(second.getId())) {
                        fallThroughCases.put(s, first);
                        df2 = new BitSet(df.size());
                        df2.set(second.getId());
                        df = df2;
                    }
                }
            }
            bitSet.or(df);
        }
        bitSet.clear(block.getId());
        if (loop != null) {
            bitSet.clear(loop.getStart().getId());
        }
        stack.push(switchRegion);
        stack.addExits(BlockUtils.bitSetToBlocks(this.mth, bitSet));
        if (!fallThroughCases.isEmpty() && isBadCasesOrder(blocksMap, fallThroughCases)) {
            LOG.debug("Fixing incorrect switch cases order, method: {}", this.mth);
            blocksMap = reOrderSwitchCases(blocksMap, fallThroughCases);
            if (isBadCasesOrder(blocksMap, fallThroughCases)) {
                LOG.error("Can't fix incorrect switch cases order, method: {}", this.mth);
                this.mth.add(AFlag.INCONSISTENT_CODE);
            }
        }
        if (bitSet.cardinality() > 1) {
            BlockUtils.cleanBitSet(this.mth, bitSet);
        }
        if (bitSet.cardinality() > 1) {
            for (i = bitSet.nextSetBit(0); i >= 0; i = bitSet.nextSetBit(i + 1)) {
                BlockNode b = (BlockNode) basicBlocks.get(i);
                bitSet.andNot(b.getDomFrontier());
                if (b.contains(AFlag.LOOP_START)) {
                    bitSet.clear(b.getId());
                } else {
                    for (BlockNode s2 : b.getCleanSuccessors()) {
                        bitSet.clear(s2.getId());
                    }
                }
            }
        }
        if (loop != null && bitSet.cardinality() > 1) {
            bitSet.clear(loop.getEnd().getId());
        }
        if (bitSet.cardinality() == 0) {
            for (BlockNode maybeOut : block.getSuccessors()) {
                boolean allReached = true;
                for (BlockNode s22 : block.getSuccessors()) {
                    if (!BlockUtils.isPathExists(s22, maybeOut)) {
                        allReached = false;
                        continue;
                        break;
                    }
                }
                if (allReached) {
                    bitSet.set(maybeOut.getId());
                    break;
                }
            }
        }
        BlockNode out = null;
        if (bitSet.cardinality() == 1) {
            out = (BlockNode) basicBlocks.get(bitSet.nextSetBit(0));
            stack.addExit(out);
        } else if (loop == null && bitSet.cardinality() > 1) {
            LOG.warn("Can't detect out node for switch block: {} in {}", block, this.mth);
        }
        if (loop != null) {
            BlockNode end = loop.getEnd();
            if (!(out == end || out == null)) {
                insertContinueInSwitch(block, out, end);
            }
        }
        if (!stack.containsExit(defCase)) {
            switchRegion.setDefaultCase(makeRegion(defCase, stack));
        }
        for (Entry<BlockNode, List<Object>> entry2 : blocksMap.entrySet()) {
            BlockNode caseBlock = (BlockNode) entry2.getKey();
            if (stack.containsExit(caseBlock)) {
                switchRegion.addCase((List) entry2.getValue(), new Region(stack.peekRegion()));
            } else {
                BlockNode next = (BlockNode) fallThroughCases.get(caseBlock);
                stack.addExit(next);
                Region caseRegion = makeRegion(caseBlock, stack);
                stack.removeExit(next);
                if (next != null) {
                    next.add(AFlag.FALL_THROUGH);
                    caseRegion.add(AFlag.FALL_THROUGH);
                }
                switchRegion.addCase((List) entry2.getValue(), caseRegion);
            }
        }
        stack.pop();
        return out;
    }

    private boolean isBadCasesOrder(Map<BlockNode, List<Object>> blocksMap, Map<BlockNode, BlockNode> fallThroughCases) {
        BlockNode nextCaseBlock = null;
        for (BlockNode caseBlock : blocksMap.keySet()) {
            if (nextCaseBlock != null && !caseBlock.equals(nextCaseBlock)) {
                return true;
            }
            nextCaseBlock = (BlockNode) fallThroughCases.get(caseBlock);
        }
        if (nextCaseBlock == null) {
            return false;
        }
        return true;
    }

    private Map<BlockNode, List<Object>> reOrderSwitchCases(Map<BlockNode, List<Object>> blocksMap, final Map<BlockNode, BlockNode> fallThroughCases) {
        List<BlockNode> list = new ArrayList(blocksMap.size());
        list.addAll(blocksMap.keySet());
        Collections.sort(list, new Comparator<BlockNode>() {
            public int compare(BlockNode a, BlockNode b) {
                BlockNode nextA = (BlockNode) fallThroughCases.get(a);
                if (nextA != null) {
                    if (b.equals(nextA)) {
                        return -1;
                    }
                } else if (a.equals(fallThroughCases.get(b))) {
                    return 1;
                }
                return 0;
            }
        });
        Map<BlockNode, List<Object>> newBlocksMap = new LinkedHashMap(blocksMap.size());
        for (BlockNode key : list) {
            newBlocksMap.put(key, blocksMap.get(key));
        }
        return newBlocksMap;
    }

    private static void insertContinueInSwitch(BlockNode block, BlockNode out, BlockNode end) {
        int endId = end.getId();
        for (BlockNode s : block.getCleanSuccessors()) {
            if (s.getDomFrontier().get(endId) && s != out) {
                List<BlockNode> list = BlockUtils.collectBlocksDominatedBy(s, s);
                for (BlockNode p : end.getPredecessors()) {
                    if (list.contains(p)) {
                        if (p.isSynthetic()) {
                            p.getInstructions().add(new InsnNode(InsnType.CONTINUE, 0));
                        }
                    }
                }
            }
        }
    }

    public IRegion processTryCatchBlocks(MethodNode mth) {
        Set<TryCatchBlock> tcs = new HashSet();
        for (ExceptionHandler handler : mth.getExceptionHandlers()) {
            tcs.add(handler.getTryBlock());
        }
        for (TryCatchBlock tc : tcs) {
            List<BlockNode> blocks = new ArrayList(tc.getHandlersCount());
            Set<BlockNode> splitters = new HashSet();
            for (ExceptionHandler handler2 : tc.getHandlers()) {
                BlockNode handlerBlock = handler2.getHandlerBlock();
                if (handlerBlock != null) {
                    blocks.add(handlerBlock);
                    splitters.addAll(handlerBlock.getPredecessors());
                } else {
                    LOG.debug(ErrorsCounter.formatErrorMsg(mth, "No exception handler block: " + handler2));
                }
            }
            Set<BlockNode> exits = new HashSet();
            for (BlockNode splitter : splitters) {
                for (BlockNode handler3 : blocks) {
                    List<BlockNode> s = splitter.getSuccessors();
                    if (s.isEmpty()) {
                        LOG.debug(ErrorsCounter.formatErrorMsg(mth, "No successors for splitter: " + splitter));
                    } else {
                        BlockNode ss = (BlockNode) s.get(0);
                        BlockNode cross = BlockUtils.getPathCross(mth, ss, handler3);
                        if (!(cross == null || cross == ss || cross == handler3)) {
                            exits.add(cross);
                        }
                    }
                }
            }
            for (ExceptionHandler handler22 : tc.getHandlers()) {
                processExcHandler(handler22, exits);
            }
        }
        return processHandlersOutBlocks(mth, tcs);
    }

    protected IRegion processHandlersOutBlocks(MethodNode mth, Set<TryCatchBlock> tcs) {
        Set<IBlock> allRegionBlocks = new HashSet();
        RegionUtils.getAllRegionBlocks(mth.getRegion(), allRegionBlocks);
        Set<IBlock> succBlocks = new HashSet();
        for (TryCatchBlock tc : tcs) {
            for (ExceptionHandler handler : tc.getHandlers()) {
                IContainer region = handler.getHandlerRegion();
                if (region != null) {
                    IBlock lastBlock = RegionUtils.getLastBlock(region);
                    if (lastBlock instanceof BlockNode) {
                        succBlocks.addAll(((BlockNode) lastBlock).getSuccessors());
                    }
                    RegionUtils.getAllRegionBlocks(region, allRegionBlocks);
                }
            }
        }
        succBlocks.removeAll(allRegionBlocks);
        if (succBlocks.isEmpty()) {
            return null;
        }
        IRegion excOutRegion = new Region(mth.getRegion());
        for (IBlock block : succBlocks) {
            if (block instanceof BlockNode) {
                excOutRegion.add(makeRegion((BlockNode) block, new RegionStack(mth)));
            }
        }
        return excOutRegion;
    }

    private void processExcHandler(ExceptionHandler handler, Set<BlockNode> exits) {
        BlockNode start = handler.getHandlerBlock();
        if (start != null) {
            BlockNode dom;
            RegionStack stack = new RegionStack(this.mth);
            if (handler.isFinally()) {
                SplitterBlockAttr splitterAttr = (SplitterBlockAttr) start.get(AType.SPLITTER_BLOCK);
                if (splitterAttr != null) {
                    dom = splitterAttr.getBlock();
                } else {
                    return;
                }
            }
            dom = start;
            stack.addExits(exits);
            List<BlockNode> handlerExits = BlockUtils.bitSetToBlocks(this.mth, dom.getDomFrontier());
            boolean inLoop = this.mth.getLoopForBlock(start) != null;
            for (BlockNode exit : handlerExits) {
                if ((!inLoop || BlockUtils.isPathExists(start, exit)) && RegionUtils.isRegionContainsBlock(this.mth.getRegion(), exit)) {
                    stack.addExit(exit);
                }
            }
            handler.setHandlerRegion(makeRegion(start, stack));
            ExcHandlerAttr excHandlerAttr = (ExcHandlerAttr) start.get(AType.EXC_HANDLER);
            if (excHandlerAttr == null) {
                LOG.warn("Missing exception handler attribute for start block");
            } else {
                handler.getHandlerRegion().addAttr(excHandlerAttr);
            }
        }
    }

    static boolean isEqualPaths(BlockNode b1, BlockNode b2) {
        if (b1 == b2) {
            return true;
        }
        if (b1 == null || b2 == null) {
            return false;
        }
        if (isEqualReturnBlocks(b1, b2) || isSyntheticPath(b1, b2)) {
            return true;
        }
        return false;
    }

    private static boolean isSyntheticPath(BlockNode b1, BlockNode b2) {
        BlockNode n1 = BlockUtils.skipSyntheticSuccessor(b1);
        BlockNode n2 = BlockUtils.skipSyntheticSuccessor(b2);
        return !(n1 == b1 && n2 == b2) && isEqualPaths(n1, n2);
    }

    public static boolean isEqualReturnBlocks(BlockNode b1, BlockNode b2) {
        if (!b1.isReturnBlock() || !b2.isReturnBlock()) {
            return false;
        }
        List<InsnNode> b1Insns = b1.getInstructions();
        List<InsnNode> b2Insns = b2.getInstructions();
        if (b1Insns.size() != 1 || b2Insns.size() != 1) {
            return false;
        }
        InsnNode i1 = (InsnNode) b1Insns.get(0);
        InsnNode i2 = (InsnNode) b2Insns.get(0);
        if (i1.getArgsCount() != i2.getArgsCount()) {
            return false;
        }
        if (i1.getArgsCount() == 0 || i1.getArg(0).equals(i2.getArg(0))) {
            return true;
        }
        return false;
    }
}
