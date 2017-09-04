package jadx.core.dex.instructions;

import jadx.core.dex.nodes.InsnNode;

public final class ConstStringNode extends InsnNode {
    private final String str;

    public ConstStringNode(String str) {
        super(InsnType.CONST_STR, 0);
        this.str = str;
    }

    public String getString() {
        return this.str;
    }

    public InsnNode copy() {
        return copyCommonParams(new ConstStringNode(this.str));
    }

    public boolean isSame(InsnNode obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ConstStringNode) || !super.isSame(obj)) {
            return false;
        }
        return this.str.equals(((ConstStringNode) obj).str);
    }

    public String toString() {
        return super.toString() + " \"" + this.str + "\"";
    }
}
