package jadx.core.dex.visitors.regions;

import jadx.core.dex.nodes.IBlock;
import jadx.core.dex.nodes.IContainer;
import jadx.core.dex.nodes.IRegion;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.dex.trycatch.ExceptionHandler;
import jadx.core.utils.exceptions.JadxOverflowException;

public class DepthRegionTraversal {
    private static final int ITERATIVE_LIMIT = 500;

    private DepthRegionTraversal() {
    }

    public static void traverse(MethodNode mth, IRegionVisitor visitor) {
        traverseInternal(mth, visitor, mth.getRegion());
    }

    public static void traverseIterative(MethodNode mth, IRegionIterativeVisitor visitor) {
        int k = 0;
        while (true) {
            boolean repeat = traverseIterativeStepInternal(mth, visitor, mth.getRegion());
            int k2 = k + 1;
            if (k > ITERATIVE_LIMIT) {
                throw new JadxOverflowException("Iterative traversal limit reached, method: " + mth);
            } else if (repeat) {
                k = k2;
            } else {
                return;
            }
        }
    }

    public static void traverseIncludingExcHandlers(MethodNode mth, IRegionIterativeVisitor visitor) {
        int k = 0;
        while (true) {
            boolean repeat = traverseIterativeStepInternal(mth, visitor, mth.getRegion());
            if (!repeat) {
                for (ExceptionHandler h : mth.getExceptionHandlers()) {
                    repeat = traverseIterativeStepInternal(mth, visitor, h.getHandlerRegion());
                    if (repeat) {
                        break;
                    }
                }
            }
            int k2 = k + 1;
            if (k > ITERATIVE_LIMIT) {
                throw new JadxOverflowException("Iterative traversal limit reached, method: " + mth);
            } else if (repeat) {
                k = k2;
            } else {
                return;
            }
        }
    }

    private static void traverseInternal(MethodNode mth, IRegionVisitor visitor, IContainer container) {
        if (container instanceof IBlock) {
            visitor.processBlock(mth, (IBlock) container);
        } else if (container instanceof IRegion) {
            IRegion region = (IRegion) container;
            if (visitor.enterRegion(mth, region)) {
                for (IContainer subCont : region.getSubBlocks()) {
                    traverseInternal(mth, visitor, subCont);
                }
            }
            visitor.leaveRegion(mth, region);
        }
    }

    private static boolean traverseIterativeStepInternal(MethodNode mth, IRegionIterativeVisitor visitor, IContainer container) {
        if (container instanceof IRegion) {
            IRegion region = (IRegion) container;
            if (visitor.visitRegion(mth, region)) {
                return true;
            }
            for (IContainer subCont : region.getSubBlocks()) {
                if (traverseIterativeStepInternal(mth, visitor, subCont)) {
                    return true;
                }
            }
        }
        return false;
    }
}
