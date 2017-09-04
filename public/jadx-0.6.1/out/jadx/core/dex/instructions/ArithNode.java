package jadx.core.dex.instructions;

import com.android.dx.io.instructions.DecodedInstruction;
import jadx.core.dex.attributes.AFlag;
import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.instructions.args.InsnArg;
import jadx.core.dex.instructions.args.RegisterArg;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.utils.InsnUtils;

public class ArithNode extends InsnNode {
    private final ArithOp op;

    public ArithNode(DecodedInstruction insn, ArithOp op, ArgType type, boolean literal) {
        super(InsnType.ARITH, 2);
        this.op = op;
        setResult(InsnArg.reg(insn, 0, type));
        int rc = insn.getRegisterCount();
        if (literal) {
            if (rc == 1) {
                addReg(insn, 0, type);
                addLit(insn, type);
            } else if (rc == 2) {
                addReg(insn, 1, type);
                addLit(insn, type);
            }
        } else if (rc == 2) {
            addReg(insn, 0, type);
            addReg(insn, 1, type);
        } else if (rc == 3) {
            addReg(insn, 1, type);
            addReg(insn, 2, type);
        }
    }

    public ArithNode(ArithOp op, RegisterArg res, InsnArg a, InsnArg b) {
        super(InsnType.ARITH, 2);
        this.op = op;
        setResult(res);
        addArg(a);
        addArg(b);
    }

    public ArithNode(ArithOp op, RegisterArg res, InsnArg a) {
        this(op, res, (InsnArg) res, a);
        add(AFlag.ARITH_ONEARG);
    }

    public ArithOp getOp() {
        return this.op;
    }

    public boolean isSame(InsnNode obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ArithNode) || !super.isSame(obj)) {
            return false;
        }
        if (this.op != ((ArithNode) obj).op) {
            return false;
        }
        return true;
    }

    public String toString() {
        return InsnUtils.formatOffset(this.offset) + ": " + InsnUtils.insnTypeToString(this.insnType) + getResult() + " = " + getArg(0) + " " + this.op.getSymbol() + " " + getArg(1);
    }
}
