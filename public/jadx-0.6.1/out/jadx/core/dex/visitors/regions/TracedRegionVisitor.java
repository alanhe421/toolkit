package jadx.core.dex.visitors.regions;

import jadx.core.dex.nodes.IBlock;
import jadx.core.dex.nodes.IRegion;
import jadx.core.dex.nodes.MethodNode;
import java.util.ArrayDeque;
import java.util.Deque;

public abstract class TracedRegionVisitor implements IRegionVisitor {
    protected final Deque<IRegion> regionStack = new ArrayDeque();

    public abstract void processBlockTraced(MethodNode methodNode, IBlock iBlock, IRegion iRegion);

    public boolean enterRegion(MethodNode mth, IRegion region) {
        this.regionStack.push(region);
        return true;
    }

    public void processBlock(MethodNode mth, IBlock container) {
        processBlockTraced(mth, container, (IRegion) this.regionStack.peek());
    }

    public void leaveRegion(MethodNode mth, IRegion region) {
        this.regionStack.pop();
    }
}
