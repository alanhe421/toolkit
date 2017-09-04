package jadx.core.dex.instructions.mods;

import jadx.core.dex.instructions.InsnType;
import jadx.core.dex.instructions.args.InsnArg;
import jadx.core.dex.instructions.args.LiteralArg;
import jadx.core.dex.instructions.args.RegisterArg;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.dex.regions.conditions.IfCondition;
import jadx.core.dex.regions.conditions.IfCondition.Mode;
import jadx.core.utils.InsnUtils;
import jadx.core.utils.Utils;
import java.util.Collection;

public final class TernaryInsn extends InsnNode {
    private IfCondition condition;

    public TernaryInsn(IfCondition condition, RegisterArg result) {
        this(condition, result, LiteralArg.TRUE, LiteralArg.FALSE);
    }

    public TernaryInsn(IfCondition condition, RegisterArg result, InsnArg th, InsnArg els) {
        super(InsnType.TERNARY, 2);
        setResult(result);
        if (th.equals(LiteralArg.FALSE) && els.equals(LiteralArg.TRUE)) {
            this.condition = IfCondition.invert(condition);
            addArg(els);
            addArg(th);
            return;
        }
        this.condition = condition;
        addArg(th);
        addArg(els);
    }

    public IfCondition getCondition() {
        return this.condition;
    }

    public void simplifyCondition() {
        this.condition = IfCondition.simplify(this.condition);
        if (this.condition.getMode() == Mode.NOT) {
            invert();
        }
    }

    private void invert() {
        this.condition = IfCondition.invert(this.condition);
        InsnArg tmp = getArg(0);
        setArg(0, getArg(1));
        setArg(1, tmp);
    }

    public void getRegisterArgs(Collection<RegisterArg> list) {
        super.getRegisterArgs(list);
        list.addAll(this.condition.getRegisterArgs());
    }

    public boolean isSame(InsnNode obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TernaryInsn) || !super.isSame(obj)) {
            return false;
        }
        return this.condition.equals(((TernaryInsn) obj).condition);
    }

    public String toString() {
        return InsnUtils.formatOffset(this.offset) + ": TERNARY" + getResult() + " = " + Utils.listToString(getArguments());
    }
}
