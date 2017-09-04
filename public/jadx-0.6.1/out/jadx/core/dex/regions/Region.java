package jadx.core.dex.regions;

import jadx.core.dex.nodes.IContainer;
import jadx.core.dex.nodes.IRegion;
import java.util.ArrayList;
import java.util.List;

public final class Region extends AbstractRegion {
    private final List<IContainer> blocks = new ArrayList(1);

    public Region(IRegion parent) {
        super(parent);
    }

    public List<IContainer> getSubBlocks() {
        return this.blocks;
    }

    public void add(IContainer region) {
        if (region instanceof AbstractRegion) {
            ((AbstractRegion) region).setParent(this);
        }
        this.blocks.add(region);
    }

    public boolean replaceSubBlock(IContainer oldBlock, IContainer newBlock) {
        int i = this.blocks.indexOf(oldBlock);
        if (i == -1) {
            return false;
        }
        this.blocks.set(i, newBlock);
        return true;
    }

    public String baseString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.blocks.size());
        for (IContainer cont : this.blocks) {
            sb.append(cont.baseString());
        }
        return sb.toString();
    }

    public String toString() {
        return "R:" + baseString();
    }
}
