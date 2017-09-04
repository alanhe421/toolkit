package jadx.core.dex.nodes;

public class Edge {
    private final BlockNode source;
    private final BlockNode target;

    public Edge(BlockNode source, BlockNode target) {
        this.source = source;
        this.target = target;
    }

    public BlockNode getSource() {
        return this.source;
    }

    public BlockNode getTarget() {
        return this.target;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Edge edge = (Edge) o;
        if (this.source.equals(edge.source) && this.target.equals(edge.target)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.source.hashCode() + (this.target.hashCode() * 31);
    }

    public String toString() {
        return "Edge: " + this.source + " -> " + this.target;
    }
}
