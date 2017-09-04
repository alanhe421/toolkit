package jadx.core.dex.nodes;

import com.android.dx.io.instructions.DecodedInstruction;
import com.rits.cloning.Cloner;
import jadx.core.dex.attributes.nodes.LineAttrNode;
import jadx.core.dex.instructions.InsnType;
import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.instructions.args.InsnArg;
import jadx.core.dex.instructions.args.InsnWrapArg;
import jadx.core.dex.instructions.args.LiteralArg;
import jadx.core.dex.instructions.args.NamedArg;
import jadx.core.dex.instructions.args.RegisterArg;
import jadx.core.dex.instructions.args.SSAVar;
import jadx.core.utils.InsnUtils;
import jadx.core.utils.Utils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class InsnNode extends LineAttrNode {
    private static final Cloner INSN_CLONER = new Cloner();
    private final List<InsnArg> arguments;
    protected final InsnType insnType;
    protected int offset = -1;
    private RegisterArg result;

    static {
        INSN_CLONER.dontClone(new Class[]{ArgType.class, SSAVar.class, LiteralArg.class, NamedArg.class});
        INSN_CLONER.dontCloneInstanceOf(new Class[]{RegisterArg.class});
    }

    public InsnNode(InsnType type, int argsCount) {
        this.insnType = type;
        if (argsCount == 0) {
            this.arguments = Collections.emptyList();
        } else {
            this.arguments = new ArrayList(argsCount);
        }
    }

    public static InsnNode wrapArg(InsnArg arg) {
        InsnNode insn = new InsnNode(InsnType.ONE_ARG, 1);
        insn.addArg(arg);
        return insn;
    }

    public void setResult(RegisterArg res) {
        if (res != null) {
            res.setParentInsn(this);
        }
        this.result = res;
    }

    public void addArg(InsnArg arg) {
        arg.setParentInsn(this);
        this.arguments.add(arg);
    }

    public InsnType getType() {
        return this.insnType;
    }

    public RegisterArg getResult() {
        return this.result;
    }

    public Iterable<InsnArg> getArguments() {
        return this.arguments;
    }

    public int getArgsCount() {
        return this.arguments.size();
    }

    public InsnArg getArg(int n) {
        return (InsnArg) this.arguments.get(n);
    }

    public boolean containsArg(RegisterArg arg) {
        for (InsnArg a : this.arguments) {
            if (a == arg || (a.isRegister() && ((RegisterArg) a).getRegNum() == arg.getRegNum())) {
                return true;
            }
        }
        return false;
    }

    public void setArg(int n, InsnArg arg) {
        arg.setParentInsn(this);
        this.arguments.set(n, arg);
    }

    public boolean replaceArg(InsnArg from, InsnArg to) {
        int count = getArgsCount();
        int i = 0;
        while (i < count) {
            InsnArg arg = (InsnArg) this.arguments.get(i);
            if (arg == from) {
                setArg(i, to);
                return true;
            } else if (arg.isInsnWrap() && ((InsnWrapArg) arg).getWrapInsn().replaceArg(from, to)) {
                return true;
            } else {
                i++;
            }
        }
        return false;
    }

    protected boolean removeArg(InsnArg arg) {
        int count = getArgsCount();
        for (int i = 0; i < count; i++) {
            if (arg == this.arguments.get(i)) {
                this.arguments.remove(i);
                if (arg instanceof RegisterArg) {
                    RegisterArg reg = (RegisterArg) arg;
                    reg.getSVar().removeUse(reg);
                }
                return true;
            }
        }
        return false;
    }

    protected void addReg(DecodedInstruction insn, int i, ArgType type) {
        addArg(InsnArg.reg(insn, i, type));
    }

    protected void addReg(int regNum, ArgType type) {
        addArg(InsnArg.reg(regNum, type));
    }

    protected void addLit(long literal, ArgType type) {
        addArg(InsnArg.lit(literal, type));
    }

    protected void addLit(DecodedInstruction insn, ArgType type) {
        addArg(InsnArg.lit(insn, type));
    }

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void getRegisterArgs(Collection<RegisterArg> collection) {
        for (InsnArg arg : getArguments()) {
            if (arg.isRegister()) {
                collection.add((RegisterArg) arg);
            } else if (arg.isInsnWrap()) {
                ((InsnWrapArg) arg).getWrapInsn().getRegisterArgs(collection);
            }
        }
    }

    public boolean isConstInsn() {
        switch (getType()) {
            case CONST:
            case CONST_STR:
            case CONST_CLASS:
                return true;
            default:
                return false;
        }
    }

    public boolean canReorder() {
        switch (getType()) {
            case CONST:
            case CONST_STR:
            case CONST_CLASS:
            case CAST:
            case MOVE:
            case ARITH:
            case NEG:
            case CMP_L:
            case CMP_G:
            case CHECK_CAST:
            case INSTANCE_OF:
            case FILL_ARRAY:
            case FILLED_NEW_ARRAY:
            case NEW_ARRAY:
            case NEW_MULTIDIM_ARRAY:
            case STR_CONCAT:
                return true;
            default:
                return false;
        }
    }

    public boolean canReorderRecursive() {
        if (!canReorder()) {
            return false;
        }
        for (InsnArg arg : getArguments()) {
            if (arg.isInsnWrap() && !((InsnWrapArg) arg).getWrapInsn().canReorderRecursive()) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return InsnUtils.formatOffset(this.offset) + ": " + InsnUtils.insnTypeToString(this.insnType) + (this.result == null ? "" : this.result + " = ") + Utils.listToString(this.arguments);
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public boolean isSame(InsnNode other) {
        if (this == other) {
            return true;
        }
        if (this.insnType != other.insnType || this.arguments.size() != other.arguments.size()) {
            return false;
        }
        int size = this.arguments.size();
        for (int i = 0; i < size; i++) {
            InsnArg arg = (InsnArg) this.arguments.get(i);
            InsnArg otherArg = (InsnArg) other.arguments.get(i);
            if (arg.isInsnWrap()) {
                if (!otherArg.isInsnWrap()) {
                    return false;
                }
                if (!((InsnWrapArg) arg).getWrapInsn().isSame(((InsnWrapArg) otherArg).getWrapInsn())) {
                    return false;
                }
            }
        }
        return true;
    }

    protected <T extends InsnNode> T copyCommonParams(T copy) {
        copy.setResult(this.result);
        if (copy.getArgsCount() == 0) {
            for (InsnArg arg : getArguments()) {
                if (arg.isInsnWrap()) {
                    copy.addArg(InsnArg.wrapArg(((InsnWrapArg) arg).getWrapInsn().copy()));
                } else {
                    copy.addArg(arg);
                }
            }
        }
        copy.copyAttributesFrom(this);
        copy.copyLines(this);
        copy.setOffset(getOffset());
        return copy;
    }

    public InsnNode copy() {
        if (getClass() == InsnNode.class) {
            return copyCommonParams(new InsnNode(this.insnType, getArgsCount()));
        }
        return (InsnNode) INSN_CLONER.deepClone(this);
    }
}
