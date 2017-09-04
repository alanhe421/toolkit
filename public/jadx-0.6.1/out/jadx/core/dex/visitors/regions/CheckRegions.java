package jadx.core.dex.visitors.regions;

import jadx.core.dex.attributes.AFlag;
import jadx.core.dex.attributes.AType;
import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.IBlock;
import jadx.core.dex.nodes.IRegion;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.dex.regions.loops.LoopRegion;
import jadx.core.dex.visitors.AbstractVisitor;
import jadx.core.utils.ErrorsCounter;
import jadx.core.utils.exceptions.JadxException;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckRegions extends AbstractVisitor {
    private static final Logger LOG = LoggerFactory.getLogger(CheckRegions.class);

    public void visit(MethodNode mth) throws JadxException {
        if (!mth.isNoCode() && !mth.getBasicBlocks().isEmpty() && !mth.contains(AType.JADX_ERROR)) {
            final Set<BlockNode> blocksInRegions = new HashSet();
            DepthRegionTraversal.traverse(mth, new AbstractRegionVisitor() {
                public void processBlock(MethodNode mth, IBlock container) {
                    if (container instanceof BlockNode) {
                        BlockNode block = (BlockNode) container;
                        if (!blocksInRegions.add(block) && !block.contains(AFlag.RETURN) && !block.contains(AFlag.SKIP) && !block.contains(AFlag.SYNTHETIC) && !block.getInstructions().isEmpty()) {
                            CheckRegions.LOG.debug(" Duplicated block: {} in {}", block, mth);
                        }
                    }
                }
            });
            if (mth.getBasicBlocks().size() != blocksInRegions.size()) {
                for (BlockNode block : mth.getBasicBlocks()) {
                    if (!(blocksInRegions.contains(block) || block.getInstructions().isEmpty() || block.contains(AFlag.SKIP))) {
                        mth.add(AFlag.INCONSISTENT_CODE);
                        LOG.debug(" Missing block: {} in {}", block, mth);
                    }
                }
            }
            DepthRegionTraversal.traverse(mth, new AbstractRegionVisitor() {
                public boolean enterRegion(MethodNode mth, IRegion region) {
                    if (region instanceof LoopRegion) {
                        BlockNode loopHeader = ((LoopRegion) region).getHeader();
                        if (!(loopHeader == null || loopHeader.getInstructions().size() == 1)) {
                            ErrorsCounter.methodError(mth, "Incorrect condition in loop: " + loopHeader);
                        }
                    }
                    return true;
                }
            });
        }
    }
}
