package jadx.core.dex.regions;

import jadx.core.dex.nodes.IContainer;
import jadx.core.dex.nodes.IRegion;
import jadx.core.dex.nodes.InsnNode;
import java.util.LinkedList;
import java.util.List;

public final class SynchronizedRegion extends AbstractRegion {
    private final InsnNode enterInsn;
    private final List<InsnNode> exitInsns = new LinkedList();
    private final Region region;

    public SynchronizedRegion(IRegion parent, InsnNode insn) {
        super(parent);
        this.enterInsn = insn;
        this.region = new Region(this);
    }

    public InsnNode getEnterInsn() {
        return this.enterInsn;
    }

    public List<InsnNode> getExitInsns() {
        return this.exitInsns;
    }

    public Region getRegion() {
        return this.region;
    }

    public List<IContainer> getSubBlocks() {
        return this.region.getSubBlocks();
    }

    public String baseString() {
        return Integer.toHexString(this.enterInsn.getOffset());
    }

    public String toString() {
        return "Synchronized:" + this.region;
    }
}
