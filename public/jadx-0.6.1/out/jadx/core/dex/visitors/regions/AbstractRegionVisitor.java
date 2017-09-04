package jadx.core.dex.visitors.regions;

import jadx.core.dex.nodes.IBlock;
import jadx.core.dex.nodes.IRegion;
import jadx.core.dex.nodes.MethodNode;

public abstract class AbstractRegionVisitor implements IRegionVisitor {
    public boolean enterRegion(MethodNode mth, IRegion region) {
        return true;
    }

    public void processBlock(MethodNode mth, IBlock container) {
    }

    public void leaveRegion(MethodNode mth, IRegion region) {
    }
}
