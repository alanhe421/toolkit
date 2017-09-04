package jadx.core.utils;

import jadx.core.dex.attributes.AType;
import jadx.core.dex.instructions.InsnType;
import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.IBlock;
import jadx.core.dex.nodes.IBranchRegion;
import jadx.core.dex.nodes.IContainer;
import jadx.core.dex.nodes.IRegion;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.dex.trycatch.CatchAttr;
import jadx.core.dex.trycatch.ExceptionHandler;
import jadx.core.dex.trycatch.TryCatchBlock;
import jadx.core.utils.exceptions.JadxRuntimeException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class RegionUtils {
    private RegionUtils() {
    }

    public static boolean hasExitEdge(IContainer container) {
        boolean z = true;
        if (container instanceof IBlock) {
            InsnNode lastInsn = BlockUtils.getLastInsn((IBlock) container);
            if (lastInsn == null) {
                return false;
            }
            InsnType type = lastInsn.getType();
            boolean z2 = type == InsnType.RETURN || type == InsnType.CONTINUE || type == InsnType.BREAK || type == InsnType.THROW;
            return z2;
        } else if (container instanceof IBranchRegion) {
            for (IContainer br : ((IBranchRegion) container).getBranches()) {
                if (br == null) {
                    return false;
                }
                if (!hasExitEdge(br)) {
                    return false;
                }
            }
            return true;
        } else if (container instanceof IRegion) {
            List<IContainer> blocks = ((IRegion) container).getSubBlocks();
            if (blocks.isEmpty() || !hasExitEdge((IContainer) blocks.get(blocks.size() - 1))) {
                z = false;
            }
            return z;
        } else {
            throw new JadxRuntimeException(unknownContainerType(container));
        }
    }

    public static InsnNode getLastInsn(IContainer container) {
        if (container instanceof IBlock) {
            List<InsnNode> insnList = ((IBlock) container).getInstructions();
            if (insnList.isEmpty()) {
                return null;
            }
            return (InsnNode) insnList.get(insnList.size() - 1);
        } else if (container instanceof IBranchRegion) {
            return null;
        } else {
            if (container instanceof IRegion) {
                List<IContainer> blocks = ((IRegion) container).getSubBlocks();
                if (blocks.isEmpty()) {
                    return null;
                }
                return getLastInsn((IContainer) blocks.get(blocks.size() - 1));
            }
            throw new JadxRuntimeException(unknownContainerType(container));
        }
    }

    public static IBlock getLastBlock(IContainer container) {
        if (container instanceof IBlock) {
            return (IBlock) container;
        }
        if (container instanceof IBranchRegion) {
            return null;
        }
        if (container instanceof IRegion) {
            List<IContainer> blocks = ((IRegion) container).getSubBlocks();
            if (blocks.isEmpty()) {
                return null;
            }
            return getLastBlock((IContainer) blocks.get(blocks.size() - 1));
        }
        throw new JadxRuntimeException(unknownContainerType(container));
    }

    public static boolean hasExitBlock(IContainer container) {
        if (container instanceof BlockNode) {
            return ((BlockNode) container).getSuccessors().isEmpty();
        }
        if (container instanceof IBlock) {
            return true;
        }
        if (container instanceof IRegion) {
            List<IContainer> blocks = ((IRegion) container).getSubBlocks();
            boolean z = !blocks.isEmpty() && hasExitBlock((IContainer) blocks.get(blocks.size() - 1));
            return z;
        }
        throw new JadxRuntimeException(unknownContainerType(container));
    }

    public static boolean hasBreakInsn(IContainer container) {
        if (container instanceof IBlock) {
            return BlockUtils.checkLastInsnType((IBlock) container, InsnType.BREAK);
        }
        if (container instanceof IRegion) {
            List<IContainer> blocks = ((IRegion) container).getSubBlocks();
            return !blocks.isEmpty() && hasBreakInsn((IContainer) blocks.get(blocks.size() - 1));
        } else {
            throw new JadxRuntimeException("Unknown container type: " + container);
        }
    }

    public static int insnsCount(IContainer container) {
        if (container instanceof IBlock) {
            return ((IBlock) container).getInstructions().size();
        }
        if (container instanceof IRegion) {
            int count = 0;
            for (IContainer block : ((IRegion) container).getSubBlocks()) {
                count += insnsCount(block);
            }
            return count;
        }
        throw new JadxRuntimeException(unknownContainerType(container));
    }

    public static boolean isEmpty(IContainer container) {
        return !notEmpty(container);
    }

    public static boolean notEmpty(IContainer container) {
        if (container instanceof IBlock) {
            if (((IBlock) container).getInstructions().isEmpty()) {
                return false;
            }
            return true;
        } else if (container instanceof IRegion) {
            for (IContainer block : ((IRegion) container).getSubBlocks()) {
                if (notEmpty(block)) {
                    return true;
                }
            }
            return false;
        } else {
            throw new JadxRuntimeException(unknownContainerType(container));
        }
    }

    public static void getAllRegionBlocks(IContainer container, Set<IBlock> blocks) {
        if (container instanceof IBlock) {
            blocks.add((IBlock) container);
        } else if (container instanceof IRegion) {
            for (IContainer block : ((IRegion) container).getSubBlocks()) {
                getAllRegionBlocks(block, blocks);
            }
        } else {
            throw new JadxRuntimeException(unknownContainerType(container));
        }
    }

    public static boolean isRegionContainsBlock(IContainer container, BlockNode block) {
        if (container instanceof IBlock) {
            if (container == block) {
                return true;
            }
            return false;
        } else if (container instanceof IRegion) {
            for (IContainer b : ((IRegion) container).getSubBlocks()) {
                if (isRegionContainsBlock(b, block)) {
                    return true;
                }
            }
            return false;
        } else {
            throw new JadxRuntimeException(unknownContainerType(container));
        }
    }

    public static List<IContainer> getExcHandlersForRegion(IContainer region) {
        CatchAttr cb = (CatchAttr) region.get(AType.CATCH_BLOCK);
        if (cb == null) {
            return Collections.emptyList();
        }
        TryCatchBlock tb = cb.getTryBlock();
        List<IContainer> arrayList = new ArrayList(tb.getHandlersCount());
        for (ExceptionHandler eh : tb.getHandlers()) {
            arrayList.add(eh.getHandlerRegion());
        }
        return arrayList;
    }

    private static boolean isRegionContainsExcHandlerRegion(IContainer container, IRegion region) {
        if (container == region) {
            return true;
        }
        if (container instanceof IRegion) {
            for (IContainer b : ((IRegion) container).getSubBlocks()) {
                CatchAttr cb = (CatchAttr) b.get(AType.CATCH_BLOCK);
                if (cb != null && (b instanceof IRegion)) {
                    for (ExceptionHandler eh : cb.getTryBlock().getHandlers()) {
                        if (isRegionContainsRegion(eh.getHandlerRegion(), region)) {
                            return true;
                        }
                    }
                }
                if (isRegionContainsRegion(b, region)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isRegionContainsRegion(IContainer container, IRegion region) {
        if (container == region) {
            return true;
        }
        if (region == null) {
            return false;
        }
        IContainer parent = region.getParent();
        while (container != parent) {
            if (parent == null) {
                return region.contains(AType.EXC_HANDLER) ? isRegionContainsExcHandlerRegion(container, region) : false;
            } else {
                IContainer region2 = parent;
                parent = region2.getParent();
            }
        }
        return true;
    }

    public static IContainer getBlockContainer(IContainer container, BlockNode block) {
        if (container instanceof IBlock) {
            return container == block ? container : null;
        } else {
            if (container instanceof IRegion) {
                IRegion region = (IRegion) container;
                for (IContainer c : region.getSubBlocks()) {
                    IContainer res = getBlockContainer(c, block);
                    if (res != null) {
                        if (!(res instanceof IBlock)) {
                            IContainer region2 = res;
                        }
                        return region;
                    }
                }
                return null;
            }
            throw new JadxRuntimeException(unknownContainerType(container));
        }
    }

    public static boolean isDominatedBy(BlockNode dom, IContainer cont) {
        if (dom == cont) {
            return true;
        }
        if (cont instanceof BlockNode) {
            return ((BlockNode) cont).isDominator(dom);
        }
        if (cont instanceof IBlock) {
            return false;
        }
        if (cont instanceof IRegion) {
            for (IContainer c : ((IRegion) cont).getSubBlocks()) {
                if (!isDominatedBy(dom, c)) {
                    return false;
                }
            }
            return true;
        }
        throw new JadxRuntimeException(unknownContainerType(cont));
    }

    public static boolean hasPathThroughBlock(BlockNode block, IContainer cont) {
        if (block == cont) {
            return true;
        }
        if (cont instanceof BlockNode) {
            return BlockUtils.isPathExists(block, (BlockNode) cont);
        }
        if (cont instanceof IBlock) {
            return false;
        }
        if (cont instanceof IRegion) {
            for (IContainer c : ((IRegion) cont).getSubBlocks()) {
                if (!hasPathThroughBlock(block, c)) {
                    return false;
                }
            }
            return true;
        }
        throw new JadxRuntimeException(unknownContainerType(cont));
    }

    protected static String unknownContainerType(IContainer container) {
        if (container == null) {
            return "Null container variable";
        }
        return "Unknown container type: " + container.getClass();
    }
}
