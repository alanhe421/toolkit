package jadx.core.dex.visitors.regions;

import jadx.core.dex.attributes.AFlag;
import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.nodes.IBlock;
import jadx.core.dex.nodes.IContainer;
import jadx.core.dex.nodes.IRegion;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.dex.regions.Region;
import jadx.core.dex.regions.conditions.IfCondition.Mode;
import jadx.core.dex.regions.conditions.IfRegion;
import jadx.core.dex.visitors.AbstractVisitor;
import jadx.core.utils.RegionUtils;
import java.util.List;

public class IfRegionVisitor extends AbstractVisitor implements IRegionVisitor, IRegionIterativeVisitor {
    private static final TernaryVisitor TERNARY_VISITOR = new TernaryVisitor();

    private static class TernaryVisitor implements IRegionIterativeVisitor {
        private TernaryVisitor() {
        }

        public boolean visitRegion(MethodNode mth, IRegion region) {
            return (region instanceof IfRegion) && TernaryMod.makeTernaryInsn(mth, (IfRegion) region);
        }
    }

    public void visit(MethodNode mth) {
        DepthRegionTraversal.traverseIterative(mth, TERNARY_VISITOR);
        DepthRegionTraversal.traverse(mth, this);
        DepthRegionTraversal.traverseIterative(mth, this);
    }

    public boolean enterRegion(MethodNode mth, IRegion region) {
        if (region instanceof IfRegion) {
            processIfRegion(mth, (IfRegion) region);
        }
        return true;
    }

    public boolean visitRegion(MethodNode mth, IRegion region) {
        if (region instanceof IfRegion) {
            return removeRedundantElseBlock(mth, (IfRegion) region);
        }
        return false;
    }

    public void processBlock(MethodNode mth, IBlock container) {
    }

    public void leaveRegion(MethodNode mth, IRegion region) {
    }

    private static void processIfRegion(MethodNode mth, IfRegion ifRegion) {
        simplifyIfCondition(ifRegion);
        moveReturnToThenBlock(mth, ifRegion);
        moveBreakToThenBlock(ifRegion);
        markElseIfChains(ifRegion);
    }

    private static void simplifyIfCondition(IfRegion ifRegion) {
        if (ifRegion.simplifyCondition() && ifRegion.getCondition().getMode() == Mode.NOT) {
            invertIfRegion(ifRegion);
        }
        IContainer elseRegion = ifRegion.getElseRegion();
        if (elseRegion != null && !RegionUtils.isEmpty(elseRegion)) {
            boolean thenIsEmpty = RegionUtils.isEmpty(ifRegion.getThenRegion());
            if (thenIsEmpty || hasSimpleReturnBlock(ifRegion.getThenRegion())) {
                invertIfRegion(ifRegion);
            }
            if (!thenIsEmpty && isIfRegion(ifRegion.getThenRegion()) && !isIfRegion(elseRegion)) {
                invertIfRegion(ifRegion);
            }
        }
    }

    private static boolean isIfRegion(IContainer container) {
        if (container instanceof IfRegion) {
            return true;
        }
        if (container instanceof IRegion) {
            List<IContainer> subBlocks = ((IRegion) container).getSubBlocks();
            if (subBlocks.size() == 1 && (subBlocks.get(0) instanceof IfRegion)) {
                return true;
            }
        }
        return false;
    }

    private static void moveReturnToThenBlock(MethodNode mth, IfRegion ifRegion) {
        if (!mth.getReturnType().equals(ArgType.VOID) && hasSimpleReturnBlock(ifRegion.getElseRegion())) {
            invertIfRegion(ifRegion);
        }
    }

    private static void moveBreakToThenBlock(IfRegion ifRegion) {
        if (ifRegion.getElseRegion() != null && RegionUtils.hasBreakInsn(ifRegion.getElseRegion())) {
            invertIfRegion(ifRegion);
        }
    }

    private static void markElseIfChains(IfRegion ifRegion) {
        if (!hasSimpleReturnBlock(ifRegion.getThenRegion())) {
            IContainer elsRegion = ifRegion.getElseRegion();
            if (elsRegion instanceof Region) {
                List<IContainer> subBlocks = ((Region) elsRegion).getSubBlocks();
                if (subBlocks.size() == 1 && (subBlocks.get(0) instanceof IfRegion)) {
                    ((IContainer) subBlocks.get(0)).add(AFlag.ELSE_IF_CHAIN);
                    elsRegion.add(AFlag.ELSE_IF_CHAIN);
                }
            }
        }
    }

    private static boolean removeRedundantElseBlock(MethodNode mth, IfRegion ifRegion) {
        if (ifRegion.getElseRegion() == null || ifRegion.contains(AFlag.ELSE_IF_CHAIN) || ifRegion.getElseRegion().contains(AFlag.ELSE_IF_CHAIN) || !hasBranchTerminator(ifRegion.getThenRegion())) {
            return false;
        }
        if (mth.getReturnType() == ArgType.VOID && RegionUtils.insnsCount(ifRegion.getThenRegion()) == 2 && RegionUtils.insnsCount(ifRegion.getElseRegion()) == 2) {
            return false;
        }
        IRegion parent = ifRegion.getParent();
        Region newRegion = new Region(parent);
        if (!parent.replaceSubBlock(ifRegion, newRegion)) {
            return false;
        }
        newRegion.add(ifRegion);
        newRegion.add(ifRegion.getElseRegion());
        ifRegion.setElseRegion(null);
        return true;
    }

    private static boolean hasBranchTerminator(IContainer region) {
        return RegionUtils.hasExitBlock(region) || RegionUtils.hasBreakInsn(region);
    }

    private static void invertIfRegion(IfRegion ifRegion) {
        if (ifRegion.getElseRegion() != null) {
            ifRegion.invert();
        }
    }

    private static boolean hasSimpleReturnBlock(IContainer region) {
        if (region == null) {
            return false;
        }
        if (region.contains(AFlag.RETURN)) {
            return true;
        }
        if (!(region instanceof IRegion)) {
            return false;
        }
        List<IContainer> subBlocks = ((IRegion) region).getSubBlocks();
        boolean z = subBlocks.size() == 1 && ((IContainer) subBlocks.get(0)).contains(AFlag.RETURN);
        return z;
    }
}
