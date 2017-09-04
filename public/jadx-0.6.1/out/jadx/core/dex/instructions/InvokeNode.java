package jadx.core.dex.instructions;

import com.android.dx.io.instructions.DecodedInstruction;
import jadx.core.dex.info.MethodInfo;
import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.instructions.args.InsnArg;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.utils.InsnUtils;
import jadx.core.utils.Utils;

public class InvokeNode extends InsnNode {
    private final MethodInfo mth;
    private final InvokeType type;

    public InvokeNode(MethodInfo mth, DecodedInstruction insn, InvokeType type, boolean isRange, int resReg) {
        int i;
        int k;
        InsnType insnType = InsnType.INVOKE;
        int argsCount = mth.getArgsCount();
        if (type != InvokeType.STATIC) {
            i = 1;
        } else {
            i = 0;
        }
        super(insnType, i + argsCount);
        this.mth = mth;
        this.type = type;
        if (resReg >= 0) {
            setResult(InsnArg.reg(resReg, mth.getReturnType()));
        }
        if (isRange) {
            k = insn.getA();
        } else {
            k = 0;
        }
        if (type != InvokeType.STATIC) {
            addReg(isRange ? k : InsnUtils.getArg(insn, k), mth.getDeclClass().getType());
            k++;
        }
        for (ArgType arg : mth.getArgumentsTypes()) {
            addReg(isRange ? k : InsnUtils.getArg(insn, k), arg);
            k += arg.getRegCount();
        }
    }

    private InvokeNode(MethodInfo mth, InvokeType invokeType, int argsCount) {
        super(InsnType.INVOKE, argsCount);
        this.mth = mth;
        this.type = invokeType;
    }

    public InvokeType getInvokeType() {
        return this.type;
    }

    public MethodInfo getCallMth() {
        return this.mth;
    }

    public InsnNode copy() {
        return copyCommonParams(new InvokeNode(this.mth, this.type, getArgsCount()));
    }

    public boolean isSame(InsnNode obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof InvokeNode) || !super.isSame(obj)) {
            return false;
        }
        InvokeNode other = (InvokeNode) obj;
        if (this.type == other.type && this.mth.equals(other.mth)) {
            return true;
        }
        return false;
    }

    public String toString() {
        return InsnUtils.formatOffset(this.offset) + ": " + InsnUtils.insnTypeToString(this.insnType) + (getResult() == null ? "" : getResult() + " = ") + Utils.listToString(getArguments()) + " " + this.mth + " type: " + this.type;
    }
}
