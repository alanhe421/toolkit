package jadx.core.dex.instructions.args;

import jadx.core.dex.instructions.InsnType;
import jadx.core.dex.instructions.PhiInsn;
import jadx.core.dex.nodes.DexNode;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.utils.InsnUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterArg extends InsnArg implements Named {
    private static final Logger LOG = LoggerFactory.getLogger(RegisterArg.class);
    protected final int regNum;
    private SSAVar sVar;

    public RegisterArg(int rn) {
        this.regNum = rn;
    }

    public RegisterArg(int rn, ArgType type) {
        this.type = type;
        this.regNum = rn;
    }

    public int getRegNum() {
        return this.regNum;
    }

    public boolean isRegister() {
        return true;
    }

    public SSAVar getSVar() {
        return this.sVar;
    }

    void setSVar(@NotNull SSAVar sVar) {
        this.sVar = sVar;
    }

    public String getName() {
        if (this.sVar == null) {
            return null;
        }
        return this.sVar.getName();
    }

    public void setName(String name) {
        if (this.sVar != null) {
            this.sVar.setName(name);
        }
    }

    public boolean isNameEquals(InsnArg arg) {
        String n = getName();
        if (n == null || !(arg instanceof Named)) {
            return false;
        }
        return n.equals(((Named) arg).getName());
    }

    public void setType(ArgType type) {
        if (this.sVar != null) {
            this.sVar.setType(type);
        }
    }

    public void mergeDebugInfo(ArgType type, String name) {
        setType(type);
        setName(name);
    }

    public RegisterArg duplicate() {
        return duplicate(getRegNum(), this.sVar);
    }

    public RegisterArg duplicate(int regNum, SSAVar sVar) {
        RegisterArg dup = new RegisterArg(regNum, getType());
        if (sVar != null) {
            dup.setSVar(sVar);
        }
        dup.copyAttributesFrom(this);
        return dup;
    }

    public Object getConstValue(DexNode dex) {
        InsnNode parInsn = getAssignInsn();
        if (parInsn == null) {
            return null;
        }
        return InsnUtils.getConstValueByInsn(dex, parInsn);
    }

    public boolean isThis() {
        if ("this".equals(getName())) {
            return true;
        }
        InsnNode ai = getAssignInsn();
        if (ai == null || ai.getType() != InsnType.MOVE) {
            return false;
        }
        InsnArg arg = ai.getArg(0);
        if (arg != this) {
            return arg.isThis();
        }
        return false;
    }

    public InsnNode getAssignInsn() {
        if (this.sVar == null) {
            return null;
        }
        return this.sVar.getAssign().getParentInsn();
    }

    public InsnNode getPhiAssignInsn() {
        PhiInsn usePhi = this.sVar.getUsedInPhi();
        if (usePhi != null) {
            return usePhi;
        }
        InsnNode parent = this.sVar.getAssign().getParentInsn();
        if (parent == null || parent.getType() != InsnType.PHI) {
            return null;
        }
        return parent;
    }

    public boolean equalRegisterAndType(RegisterArg arg) {
        return this.regNum == arg.regNum && this.type.equals(arg.type);
    }

    public int hashCode() {
        return (this.regNum * 31) + this.type.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof RegisterArg)) {
            return false;
        }
        RegisterArg other = (RegisterArg) obj;
        if (this.regNum != other.regNum) {
            return false;
        }
        if (!this.type.equals(other.type)) {
            return false;
        }
        if (this.sVar == null || this.sVar.equals(other.getSVar())) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(r");
        sb.append(this.regNum);
        if (this.sVar != null) {
            sb.append("_").append(this.sVar.getVersion());
        }
        if (getName() != null) {
            sb.append(" '").append(getName()).append("'");
        }
        sb.append(" ");
        sb.append(this.type);
        sb.append(")");
        return sb.toString();
    }
}
