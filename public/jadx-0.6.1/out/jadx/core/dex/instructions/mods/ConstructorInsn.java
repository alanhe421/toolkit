package jadx.core.dex.instructions.mods;

import jadx.core.dex.info.ClassInfo;
import jadx.core.dex.info.MethodInfo;
import jadx.core.dex.instructions.InsnType;
import jadx.core.dex.instructions.InvokeNode;
import jadx.core.dex.instructions.args.RegisterArg;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.dex.nodes.MethodNode;

public class ConstructorInsn extends InsnNode {
    private final MethodInfo callMth;
    private final CallType callType;
    private final RegisterArg instanceArg;

    private enum CallType {
        CONSTRUCTOR,
        SUPER,
        THIS,
        SELF
    }

    public ConstructorInsn(MethodNode mth, InvokeNode invoke) {
        super(InsnType.CONSTRUCTOR, invoke.getArgsCount() - 1);
        this.callMth = invoke.getCallMth();
        ClassInfo classType = this.callMth.getDeclClass();
        this.instanceArg = (RegisterArg) invoke.getArg(0);
        if (!this.instanceArg.isThis()) {
            this.callType = CallType.CONSTRUCTOR;
            setResult(this.instanceArg);
            this.instanceArg.getSVar().setAssign(this.instanceArg);
        } else if (!classType.equals(mth.getParentClass().getClassInfo())) {
            this.callType = CallType.SUPER;
        } else if (this.callMth.getShortId().equals(mth.getMethodInfo().getShortId())) {
            this.callType = CallType.SELF;
        } else {
            this.callType = CallType.THIS;
        }
        this.instanceArg.getSVar().removeUse(this.instanceArg);
        for (int i = 1; i < invoke.getArgsCount(); i++) {
            addArg(invoke.getArg(i));
        }
        this.offset = invoke.getOffset();
        setSourceLine(invoke.getSourceLine());
    }

    public ConstructorInsn(MethodInfo callMth, CallType callType, RegisterArg instanceArg) {
        super(InsnType.CONSTRUCTOR, callMth.getArgsCount());
        this.callMth = callMth;
        this.callType = callType;
        this.instanceArg = instanceArg;
    }

    public MethodInfo getCallMth() {
        return this.callMth;
    }

    public RegisterArg getInstanceArg() {
        return this.instanceArg;
    }

    public ClassInfo getClassType() {
        return this.callMth.getDeclClass();
    }

    public CallType getCallType() {
        return this.callType;
    }

    public boolean isNewInstance() {
        return this.callType == CallType.CONSTRUCTOR;
    }

    public boolean isSuper() {
        return this.callType == CallType.SUPER;
    }

    public boolean isThis() {
        return this.callType == CallType.THIS;
    }

    public boolean isSelf() {
        return this.callType == CallType.SELF;
    }

    public boolean isSame(InsnNode obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ConstructorInsn) || !super.isSame(obj)) {
            return false;
        }
        ConstructorInsn other = (ConstructorInsn) obj;
        if (this.callMth.equals(other.callMth) && this.callType == other.callType) {
            return true;
        }
        return false;
    }

    public String toString() {
        return super.toString() + " " + this.callMth + " " + this.callType;
    }
}
