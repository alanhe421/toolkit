package jadx.core.dex.visitors.blocksmaker.helpers;

import jadx.core.dex.nodes.BlockNode;

public final class BlocksPair {
    private final BlockNode first;
    private final BlockNode second;

    public BlocksPair(BlockNode first, BlockNode second) {
        this.first = first;
        this.second = second;
    }

    public BlockNode getFirst() {
        return this.first;
    }

    public BlockNode getSecond() {
        return this.second;
    }

    public int hashCode() {
        return (this.first.hashCode() * 31) + this.second.hashCode();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BlocksPair)) {
            return false;
        }
        BlocksPair other = (BlocksPair) o;
        if (this.first.equals(other.first) && this.second.equals(other.second)) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "(" + this.first + ", " + this.second + ")";
    }
}
