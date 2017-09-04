package jadx.core.dex.regions.conditions;

import jadx.core.dex.instructions.IfNode;
import jadx.core.dex.instructions.IfOp;
import jadx.core.dex.instructions.args.InsnArg;
import jadx.core.dex.instructions.args.LiteralArg;

public final class Compare {
    private final IfNode insn;

    public Compare(IfNode insn) {
        this.insn = insn;
    }

    public IfOp getOp() {
        return this.insn.getOp();
    }

    public InsnArg getA() {
        return this.insn.getArg(0);
    }

    public InsnArg getB() {
        return this.insn.getArg(1);
    }

    public IfNode getInsn() {
        return this.insn;
    }

    public Compare invert() {
        this.insn.invertCondition();
        return this;
    }

    public void normalize() {
        if (getOp() == IfOp.NE && getB().isLiteral() && getB().equals(LiteralArg.FALSE)) {
            this.insn.changeCondition(IfOp.EQ, getA(), LiteralArg.TRUE);
        }
    }

    public String toString() {
        return getA() + " " + getOp().getSymbol() + " " + getB();
    }
}
