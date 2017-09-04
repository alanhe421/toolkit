package jadx.core.dex.instructions.args;

import jadx.core.dex.nodes.InsnNode;
import jadx.core.utils.exceptions.JadxRuntimeException;
import org.jetbrains.annotations.NotNull;

public final class InsnWrapArg extends InsnArg {
    private final InsnNode wrappedInsn;

    public InsnWrapArg(@NotNull InsnNode insn) {
        RegisterArg result = insn.getResult();
        this.type = result != null ? result.getType() : ArgType.VOID;
        this.wrappedInsn = insn;
    }

    public InsnNode getWrapInsn() {
        return this.wrappedInsn;
    }

    public void setParentInsn(InsnNode parentInsn) {
        if (parentInsn == this.wrappedInsn) {
            throw new JadxRuntimeException("Can't wrap instruction info itself: " + parentInsn);
        }
        this.parentInsn = parentInsn;
    }

    public boolean isInsnWrap() {
        return true;
    }

    public int hashCode() {
        return this.wrappedInsn.hashCode();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InsnWrapArg)) {
            return false;
        }
        InsnWrapArg that = (InsnWrapArg) o;
        InsnNode thisInsn = this.wrappedInsn;
        InsnNode thatInsn = that.wrappedInsn;
        if (!thisInsn.isSame(thatInsn)) {
            return false;
        }
        int count = thisInsn.getArgsCount();
        for (int i = 0; i < count; i++) {
            if (!thisInsn.getArg(i).equals(thatInsn.getArg(i))) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return "(wrap: " + this.type + "\n  " + this.wrappedInsn + ")";
    }
}
