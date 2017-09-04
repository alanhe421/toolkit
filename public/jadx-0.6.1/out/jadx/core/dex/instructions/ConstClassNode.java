package jadx.core.dex.instructions;

import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.nodes.InsnNode;

public final class ConstClassNode extends InsnNode {
    private final ArgType clsType;

    public ConstClassNode(ArgType clsType) {
        super(InsnType.CONST_CLASS, 0);
        this.clsType = clsType;
    }

    public ArgType getClsType() {
        return this.clsType;
    }

    public InsnNode copy() {
        return copyCommonParams(new ConstClassNode(this.clsType));
    }

    public boolean isSame(InsnNode obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ConstClassNode) || !super.isSame(obj)) {
            return false;
        }
        return this.clsType.equals(((ConstClassNode) obj).clsType);
    }

    public String toString() {
        return super.toString() + " " + this.clsType;
    }
}
