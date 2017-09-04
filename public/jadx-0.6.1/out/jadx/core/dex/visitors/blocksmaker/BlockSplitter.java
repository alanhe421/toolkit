package jadx.core.dex.visitors.blocksmaker;

import jadx.core.dex.attributes.AFlag;
import jadx.core.dex.attributes.AType;
import jadx.core.dex.attributes.nodes.JumpInfo;
import jadx.core.dex.instructions.IfNode;
import jadx.core.dex.instructions.InsnType;
import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.dex.trycatch.CatchAttr;
import jadx.core.dex.trycatch.ExceptionHandler;
import jadx.core.dex.trycatch.SplitterBlockAttr;
import jadx.core.dex.visitors.AbstractVisitor;
import jadx.core.utils.exceptions.JadxRuntimeException;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class BlockSplitter extends AbstractVisitor {
    private static final Set<InsnType> SEPARATE_INSNS = EnumSet.of(InsnType.RETURN, new InsnType[]{InsnType.IF, InsnType.SWITCH, InsnType.MONITOR_ENTER, InsnType.MONITOR_EXIT, InsnType.THROW});

    public void visit(MethodNode mth) {
        if (!mth.isNoCode()) {
            mth.checkInstructions();
            mth.initBasicBlocks();
            splitBasicBlocks(mth);
            removeInsns(mth);
        }
    }

    private static void splitBasicBlocks(MethodNode mth) {
        InsnNode prevInsn = null;
        Map<Integer, BlockNode> blocksMap = new HashMap();
        BlockNode curBlock = startNewBlock(mth, 0);
        mth.setEnterBlock(curBlock);
        for (InsnNode insn : mth.getInstructions()) {
            if (insn != null) {
                BlockNode block;
                boolean startNew = false;
                if (prevInsn != null) {
                    InsnType type = prevInsn.getType();
                    if (type == InsnType.GOTO || type == InsnType.THROW || SEPARATE_INSNS.contains(type)) {
                        if (type == InsnType.RETURN || type == InsnType.THROW) {
                            mth.addExitBlock(curBlock);
                        }
                        block = startNewBlock(mth, insn.getOffset());
                        if (type == InsnType.MONITOR_ENTER || type == InsnType.MONITOR_EXIT) {
                            connect(curBlock, block);
                        }
                        curBlock = block;
                        startNew = true;
                    } else {
                        if (isSplitByJump(prevInsn, insn) || SEPARATE_INSNS.contains(insn.getType()) || isDoWhile(blocksMap, curBlock, insn) || prevInsn.contains(AFlag.TRY_LEAVE) || prevInsn.getType() == InsnType.MOVE_EXCEPTION) {
                            startNew = true;
                        } else {
                            startNew = false;
                        }
                        if (startNew) {
                            block = startNewBlock(mth, insn.getOffset());
                            connect(curBlock, block);
                            curBlock = block;
                        }
                    }
                }
                if (insn.contains(AFlag.TRY_ENTER)) {
                    if (!(insn.getOffset() == 0 || startNew)) {
                        block = startNewBlock(mth, insn.getOffset());
                        connect(curBlock, block);
                        curBlock = block;
                    }
                    blocksMap.put(Integer.valueOf(insn.getOffset()), curBlock);
                    block = startNewBlock(mth, -1);
                    curBlock.add(AFlag.SYNTHETIC);
                    SplitterBlockAttr splitter = new SplitterBlockAttr(curBlock);
                    block.addAttr(splitter);
                    curBlock.addAttr(splitter);
                    connect(curBlock, block);
                    curBlock = block;
                } else {
                    blocksMap.put(Integer.valueOf(insn.getOffset()), curBlock);
                }
                curBlock.getInstructions().add(insn);
                prevInsn = insn;
            }
        }
        setupConnections(mth, blocksMap);
    }

    static BlockNode startNewBlock(MethodNode mth, int offset) {
        BlockNode block = new BlockNode(mth.getBasicBlocks().size(), offset);
        mth.getBasicBlocks().add(block);
        return block;
    }

    static void connect(BlockNode from, BlockNode to) {
        if (!from.getSuccessors().contains(to)) {
            from.getSuccessors().add(to);
        }
        if (!to.getPredecessors().contains(from)) {
            to.getPredecessors().add(from);
        }
    }

    static void removeConnection(BlockNode from, BlockNode to) {
        from.getSuccessors().remove(to);
        to.getPredecessors().remove(from);
    }

    static BlockNode insertBlockBetween(MethodNode mth, BlockNode source, BlockNode target) {
        BlockNode newBlock = startNewBlock(mth, target.getStartOffset());
        newBlock.add(AFlag.SYNTHETIC);
        removeConnection(source, target);
        connect(source, newBlock);
        connect(newBlock, target);
        return newBlock;
    }

    private static void setupConnections(MethodNode mth, Map<Integer, BlockNode> blocksMap) {
        for (BlockNode block : mth.getBasicBlocks()) {
            for (InsnNode insn : block.getInstructions()) {
                for (JumpInfo jump : insn.getAll(AType.JUMP)) {
                    connect(getBlock(jump.getSrc(), blocksMap), getBlock(jump.getDest(), blocksMap));
                }
                connectExceptionHandlers(blocksMap, block, insn);
            }
        }
    }

    private static void connectExceptionHandlers(Map<Integer, BlockNode> blocksMap, BlockNode block, InsnNode insn) {
        CatchAttr catches = (CatchAttr) insn.get(AType.CATCH_BLOCK);
        SplitterBlockAttr spl = (SplitterBlockAttr) block.get(AType.SPLITTER_BLOCK);
        if (catches != null && spl != null) {
            BlockNode splitterBlock = spl.getBlock();
            boolean tryEnd = insn.contains(AFlag.TRY_LEAVE);
            for (ExceptionHandler h : catches.getTryBlock().getHandlers()) {
                BlockNode handlerBlock = getBlock(h.getHandleOffset(), blocksMap);
                if (splitterBlock != handlerBlock) {
                    if (!handlerBlock.contains(AType.SPLITTER_BLOCK)) {
                        handlerBlock.addAttr(spl);
                    }
                    connect(splitterBlock, handlerBlock);
                }
                if (tryEnd) {
                    connect(block, handlerBlock);
                }
            }
        }
    }

    private static boolean isSplitByJump(InsnNode prevInsn, InsnNode currentInsn) {
        for (JumpInfo jump : prevInsn.getAll(AType.JUMP)) {
            if (jump.getSrc() == prevInsn.getOffset()) {
                return true;
            }
        }
        for (JumpInfo jump2 : currentInsn.getAll(AType.JUMP)) {
            if (jump2.getDest() == currentInsn.getOffset()) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDoWhile(Map<Integer, BlockNode> blocksMap, BlockNode curBlock, InsnNode insn) {
        if (insn.getType() == InsnType.IF && ((BlockNode) blocksMap.get(Integer.valueOf(((IfNode) insn).getTarget()))) == curBlock) {
            return true;
        }
        return false;
    }

    private static BlockNode getBlock(int offset, Map<Integer, BlockNode> blocksMap) {
        BlockNode block = (BlockNode) blocksMap.get(Integer.valueOf(offset));
        if (block != null) {
            return block;
        }
        throw new JadxRuntimeException("Missing block: " + offset);
    }

    private static void removeInsns(MethodNode mth) {
        for (BlockNode block : mth.getBasicBlocks()) {
            Iterator<InsnNode> it = block.getInstructions().iterator();
            while (it.hasNext()) {
                InsnType insnType = ((InsnNode) it.next()).getType();
                if (insnType == InsnType.GOTO || insnType == InsnType.NOP) {
                    it.remove();
                }
            }
        }
    }
}
