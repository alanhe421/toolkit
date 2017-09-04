package jadx.api;

public final class CodePosition {
    private final int line;
    private final JavaNode node;
    private final int offset;

    public CodePosition(JavaNode node, int line, int offset) {
        this.node = node;
        this.line = line;
        this.offset = offset;
    }

    public CodePosition(int line, int offset) {
        this.node = null;
        this.line = line;
        this.offset = offset;
    }

    public JavaNode getNode() {
        return this.node;
    }

    public JavaClass getJavaClass() {
        JavaClass parent = this.node.getDeclaringClass();
        if (parent == null && (this.node instanceof JavaClass)) {
            return (JavaClass) this.node;
        }
        return parent;
    }

    public int getLine() {
        return this.line;
    }

    public int getOffset() {
        return this.offset;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CodePosition that = (CodePosition) o;
        if (this.line == that.line && this.offset == that.offset) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.line + (this.offset * 31);
    }

    public String toString() {
        return this.line + ":" + this.offset + (this.node != null ? " " + this.node : "");
    }
}
