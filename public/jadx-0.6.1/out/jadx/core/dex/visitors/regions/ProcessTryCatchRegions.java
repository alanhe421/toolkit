package jadx.core.dex.visitors.regions;

import jadx.core.dex.attributes.AType;
import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.IBranchRegion;
import jadx.core.dex.nodes.IContainer;
import jadx.core.dex.nodes.IRegion;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.dex.regions.AbstractRegion;
import jadx.core.dex.regions.Region;
import jadx.core.dex.regions.TryCatchRegion;
import jadx.core.dex.regions.loops.LoopRegion;
import jadx.core.dex.trycatch.CatchAttr;
import jadx.core.dex.trycatch.ExceptionHandler;
import jadx.core.dex.trycatch.SplitterBlockAttr;
import jadx.core.dex.trycatch.TryCatchBlock;
import jadx.core.utils.BlockUtils;
import jadx.core.utils.ErrorsCounter;
import jadx.core.utils.RegionUtils;
import jadx.core.utils.exceptions.JadxRuntimeException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessTryCatchRegions extends AbstractRegionVisitor {
    private static final Logger LOG = LoggerFactory.getLogger(ProcessTryCatchRegions.class);

    public static void process(MethodNode mth) {
        if (!mth.isNoCode() && !mth.isNoExceptionHandlers()) {
            final Map<BlockNode, TryCatchBlock> tryBlocksMap = new HashMap(2);
            searchTryCatchDominators(mth, tryBlocksMap);
            DepthRegionTraversal.traverseIncludingExcHandlers(mth, new IRegionIterativeVisitor() {
                public boolean visitRegion(MethodNode mth, IRegion region) {
                    return ProcessTryCatchRegions.checkAndWrap(mth, tryBlocksMap, region) && !tryBlocksMap.isEmpty();
                }
            });
        }
    }

    private static void searchTryCatchDominators(MethodNode mth, Map<BlockNode, TryCatchBlock> tryBlocksMap) {
        Set<TryCatchBlock> tryBlocks = new HashSet();
        for (BlockNode block : mth.getBasicBlocks()) {
            CatchAttr c = (CatchAttr) block.get(AType.CATCH_BLOCK);
            if (c != null) {
                tryBlocks.add(c.getTryBlock());
            }
        }
        for (TryCatchBlock tb : tryBlocks) {
            if (tb.getHandlersCount() == 0) {
                LOG.warn("No exception handlers in catch block, method: {}", mth);
            } else {
                BlockNode domBlock;
                BitSet bs = new BitSet(mth.getBasicBlocks().size());
                for (ExceptionHandler excHandler : tb.getHandlers()) {
                    SplitterBlockAttr splitter = (SplitterBlockAttr) excHandler.getHandlerBlock().get(AType.SPLITTER_BLOCK);
                    if (splitter != null) {
                        bs.set(splitter.getBlock().getId());
                    }
                }
                List<BlockNode> domBlocks = BlockUtils.bitSetToBlocks(mth, bs);
                if (domBlocks.size() != 1) {
                    domBlock = BlockUtils.getTopBlock(domBlocks);
                    if (domBlock == null) {
                        throw new JadxRuntimeException("Exception block dominator not found, method:" + mth + ". bs: " + domBlocks);
                    }
                }
                domBlock = (BlockNode) domBlocks.get(0);
                if (((TryCatchBlock) tryBlocksMap.put(domBlock, tb)) != null) {
                    ErrorsCounter.methodError(mth, "Failed to process nested try/catch");
                }
            }
        }
    }

    private static boolean checkAndWrap(MethodNode mth, Map<BlockNode, TryCatchBlock> tryBlocksMap, IRegion region) {
        for (Entry<BlockNode, TryCatchBlock> entry : tryBlocksMap.entrySet()) {
            BlockNode dominator = (BlockNode) entry.getKey();
            if (region.getSubBlocks().contains(dominator)) {
                if (!wrapBlocks(region, (TryCatchBlock) tryBlocksMap.get(dominator), dominator)) {
                    ErrorsCounter.methodError(mth, "Can't wrap try/catch for " + region);
                }
                tryBlocksMap.remove(dominator);
                return true;
            }
        }
        return false;
    }

    private static boolean wrapBlocks(IRegion replaceRegion, TryCatchBlock tb, BlockNode dominator) {
        if (replaceRegion == null) {
            return false;
        }
        if (replaceRegion instanceof LoopRegion) {
            return wrapBlocks(((LoopRegion) replaceRegion).getBody(), tb, dominator);
        }
        if (replaceRegion instanceof IBranchRegion) {
            return wrapBlocks(replaceRegion.getParent(), tb, dominator);
        }
        Region tryRegion = new Region(replaceRegion);
        List<IContainer> subBlocks = replaceRegion.getSubBlocks();
        for (IContainer cont : subBlocks) {
            if (RegionUtils.hasPathThroughBlock(dominator, cont)) {
                if (isHandlerPath(tb, cont)) {
                    break;
                }
                tryRegion.getSubBlocks().add(cont);
            }
        }
        if (tryRegion.getSubBlocks().isEmpty()) {
            return false;
        }
        TryCatchRegion tryCatchRegion = new TryCatchRegion(replaceRegion, tryRegion);
        tryRegion.setParent(tryCatchRegion);
        tryCatchRegion.setTryCatchBlock(tb.getCatchAttr().getTryBlock());
        if (!replaceRegion.replaceSubBlock((IContainer) tryRegion.getSubBlocks().get(0), tryCatchRegion)) {
            return false;
        }
        subBlocks.removeAll(tryRegion.getSubBlocks());
        for (IContainer cont2 : tryRegion.getSubBlocks()) {
            if (cont2 instanceof AbstractRegion) {
                ((AbstractRegion) cont2).setParent(tryRegion);
            }
        }
        return true;
    }

    private static boolean isHandlerPath(TryCatchBlock tb, IContainer cont) {
        for (ExceptionHandler h : tb.getHandlers()) {
            if (RegionUtils.hasPathThroughBlock(h.getHandlerBlock(), cont)) {
                return true;
            }
        }
        return false;
    }
}
