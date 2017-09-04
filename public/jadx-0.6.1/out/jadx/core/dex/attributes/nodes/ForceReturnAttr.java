package jadx.core.dex.attributes.nodes;

import jadx.core.dex.attributes.AType;
import jadx.core.dex.attributes.IAttribute;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.utils.Utils;

public class ForceReturnAttr implements IAttribute {
    private final InsnNode returnInsn;

    public ForceReturnAttr(InsnNode retInsn) {
        this.returnInsn = retInsn;
    }

    public InsnNode getReturnInsn() {
        return this.returnInsn;
    }

    public AType<ForceReturnAttr> getType() {
        return AType.FORCE_RETURN;
    }

    public String toString() {
        return "FORCE_RETURN " + Utils.listToString(this.returnInsn.getArguments());
    }
}
