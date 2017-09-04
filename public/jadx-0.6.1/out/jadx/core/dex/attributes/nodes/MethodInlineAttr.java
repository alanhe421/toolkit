package jadx.core.dex.attributes.nodes;

import jadx.core.dex.attributes.AType;
import jadx.core.dex.attributes.IAttribute;
import jadx.core.dex.nodes.InsnNode;

public class MethodInlineAttr implements IAttribute {
    private final InsnNode insn;

    public MethodInlineAttr(InsnNode insn) {
        this.insn = insn;
    }

    public InsnNode getInsn() {
        return this.insn;
    }

    public AType<MethodInlineAttr> getType() {
        return AType.METHOD_INLINE;
    }

    public String toString() {
        return "INLINE: " + this.insn;
    }
}
