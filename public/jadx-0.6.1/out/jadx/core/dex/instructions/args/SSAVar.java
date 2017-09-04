package jadx.core.dex.instructions.args;

import jadx.core.dex.attributes.AttrNode;
import jadx.core.dex.instructions.PhiInsn;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SSAVar extends AttrNode {
    @NotNull
    private RegisterArg assign;
    private int endUseAddr;
    private final int regNum;
    private int startUseAddr;
    private ArgType type;
    private boolean typeImmutable;
    private final List<RegisterArg> useList = new ArrayList(2);
    @Nullable
    private PhiInsn usedInPhi;
    private VarName varName;
    private final int version;

    public SSAVar(int regNum, int v, @NotNull RegisterArg assign) {
        this.regNum = regNum;
        this.version = v;
        this.assign = assign;
        assign.setSVar(this);
        this.startUseAddr = -1;
        this.endUseAddr = -1;
    }

    public int getStartAddr() {
        if (this.startUseAddr == -1) {
            calcUsageAddrRange();
        }
        return this.startUseAddr;
    }

    public int getEndAddr() {
        if (this.endUseAddr == -1) {
            calcUsageAddrRange();
        }
        return this.endUseAddr;
    }

    private void calcUsageAddrRange() {
        int insnAddr;
        int start = Integer.MAX_VALUE;
        int end = Integer.MIN_VALUE;
        if (this.assign.getParentInsn() != null) {
            insnAddr = this.assign.getParentInsn().getOffset();
            if (insnAddr >= 0) {
                start = Math.min(insnAddr, Integer.MAX_VALUE);
                end = Math.max(insnAddr, Integer.MIN_VALUE);
            }
        }
        for (RegisterArg arg : this.useList) {
            if (arg.getParentInsn() != null) {
                insnAddr = arg.getParentInsn().getOffset();
                if (insnAddr >= 0) {
                    start = Math.min(insnAddr, start);
                    end = Math.max(insnAddr, end);
                }
            }
        }
        if (start != Integer.MAX_VALUE && end != Integer.MIN_VALUE) {
            this.startUseAddr = start;
            this.endUseAddr = end;
        }
    }

    public int getRegNum() {
        return this.regNum;
    }

    public int getVersion() {
        return this.version;
    }

    @NotNull
    public RegisterArg getAssign() {
        return this.assign;
    }

    public void setAssign(@NotNull RegisterArg assign) {
        this.assign = assign;
    }

    public List<RegisterArg> getUseList() {
        return this.useList;
    }

    public int getUseCount() {
        return this.useList.size();
    }

    public void use(RegisterArg arg) {
        if (arg.getSVar() != null) {
            arg.getSVar().removeUse(arg);
        }
        arg.setSVar(this);
        this.useList.add(arg);
    }

    public void removeUse(RegisterArg arg) {
        int useListSize = this.useList.size();
        for (int i = 0; i < useListSize; i++) {
            if (this.useList.get(i) == arg) {
                this.useList.remove(i);
                return;
            }
        }
    }

    public void setUsedInPhi(@Nullable PhiInsn usedInPhi) {
        this.usedInPhi = usedInPhi;
    }

    @Nullable
    public PhiInsn getUsedInPhi() {
        return this.usedInPhi;
    }

    public boolean isUsedInPhi() {
        return this.usedInPhi != null;
    }

    public int getVariableUseCount() {
        if (this.usedInPhi == null) {
            return this.useList.size();
        }
        return this.useList.size() + this.usedInPhi.getResult().getSVar().getUseCount();
    }

    public void setType(ArgType type) {
        ArgType acceptedType;
        if (this.typeImmutable) {
            acceptedType = this.type;
        } else {
            acceptedType = type;
            this.type = acceptedType;
        }
        this.assign.type = acceptedType;
        int useListSize = this.useList.size();
        for (int i = 0; i < useListSize; i++) {
            ((RegisterArg) this.useList.get(i)).type = acceptedType;
        }
    }

    public void setTypeImmutable(ArgType type) {
        setType(type);
        this.typeImmutable = true;
    }

    public boolean isTypeImmutable() {
        return this.typeImmutable;
    }

    public void setName(String name) {
        if (name != null) {
            if (this.varName == null) {
                this.varName = new VarName();
            }
            this.varName.setName(name);
        }
    }

    public String getName() {
        if (this.varName == null) {
            return null;
        }
        return this.varName.getName();
    }

    public VarName getVarName() {
        return this.varName;
    }

    public void setVarName(VarName varName) {
        this.varName = varName;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SSAVar)) {
            return false;
        }
        SSAVar ssaVar = (SSAVar) o;
        if (this.regNum == ssaVar.regNum && this.version == ssaVar.version) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.regNum * 31) + this.version;
    }

    public String toString() {
        return "r" + this.regNum + "_" + this.version;
    }
}
