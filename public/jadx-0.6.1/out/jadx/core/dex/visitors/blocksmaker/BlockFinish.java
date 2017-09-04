package jadx.core.dex.visitors.blocksmaker;

import jadx.core.dex.attributes.AType;
import jadx.core.dex.instructions.IfNode;
import jadx.core.dex.instructions.InsnType;
import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.dex.trycatch.ExcHandlerAttr;
import jadx.core.dex.trycatch.SplitterBlockAttr;
import jadx.core.dex.visitors.AbstractVisitor;
import jadx.core.utils.BlockUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlockFinish extends AbstractVisitor {
    private static final Logger LOG = LoggerFactory.getLogger(BlockFinish.class);

    public void visit(MethodNode mth) {
        if (!mth.isNoCode()) {
            for (BlockNode block : mth.getBasicBlocks()) {
                block.updateCleanSuccessors();
                initBlocksInIfNodes(block);
                fixSplitterBlock(block);
            }
            mth.finishBasicBlocks();
        }
    }

    private static void initBlocksInIfNodes(BlockNode block) {
        List<InsnNode> instructions = block.getInstructions();
        if (instructions.size() == 1) {
            InsnNode insn = (InsnNode) instructions.get(0);
            if (insn.getType() == InsnType.IF) {
                ((IfNode) insn).initBlocks(block);
            }
        }
    }

    private static void fixSplitterBlock(BlockNode block) {
        ExcHandlerAttr excHandlerAttr = (ExcHandlerAttr) block.get(AType.EXC_HANDLER);
        if (excHandlerAttr != null) {
            BlockNode handlerBlock = excHandlerAttr.getHandler().getHandlerBlock();
            if (handlerBlock.getPredecessors().size() >= 2) {
                BlockNode pred;
                SplitterBlockAttr splitterAttr;
                Map<BlockNode, SplitterBlockAttr> splitters = new HashMap();
                for (BlockNode pred2 : handlerBlock.getPredecessors()) {
                    pred2 = BlockUtils.skipSyntheticPredecessor(pred2);
                    splitterAttr = (SplitterBlockAttr) pred2.get(AType.SPLITTER_BLOCK);
                    if (splitterAttr != null && pred2 == splitterAttr.getBlock()) {
                        splitters.put(pred2, splitterAttr);
                    }
                }
                if (splitters.size() >= 2) {
                    BlockNode topSplitter = BlockUtils.getTopBlock(splitters.keySet());
                    if (topSplitter == null) {
                        LOG.warn("Unknown top splitter block from list: {}", splitters);
                        return;
                    }
                    for (Entry<BlockNode, SplitterBlockAttr> entry : splitters.entrySet()) {
                        pred2 = (BlockNode) entry.getKey();
                        splitterAttr = (SplitterBlockAttr) entry.getValue();
                        if (pred2 == topSplitter) {
                            block.addAttr(splitterAttr);
                        } else {
                            pred2.remove(AType.SPLITTER_BLOCK);
                            for (BlockNode s : pred2.getCleanSuccessors()) {
                                s.remove(AType.SPLITTER_BLOCK);
                            }
                        }
                    }
                }
            }
        }
    }
}
