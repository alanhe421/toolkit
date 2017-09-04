package jadx.core.dex.trycatch;

import jadx.core.dex.attributes.AType;
import jadx.core.dex.attributes.IAttribute;
import jadx.core.dex.nodes.BlockNode;

public class SplitterBlockAttr implements IAttribute {
    private final BlockNode block;

    public SplitterBlockAttr(BlockNode block) {
        this.block = block;
    }

    public BlockNode getBlock() {
        return this.block;
    }

    public AType<SplitterBlockAttr> getType() {
        return AType.SPLITTER_BLOCK;
    }

    public String toString() {
        return "Splitter: " + this.block;
    }
}
