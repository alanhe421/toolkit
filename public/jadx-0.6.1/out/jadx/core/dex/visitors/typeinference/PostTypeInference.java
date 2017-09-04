package jadx.core.dex.visitors.typeinference;

import jadx.core.dex.info.MethodInfo;
import jadx.core.dex.instructions.IndexInsnNode;
import jadx.core.dex.instructions.InvokeNode;
import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.instructions.args.InsnArg;
import jadx.core.dex.instructions.args.LiteralArg;
import jadx.core.dex.instructions.args.RegisterArg;
import jadx.core.dex.nodes.DexNode;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.dex.nodes.MethodNode;
import java.util.List;

public class PostTypeInference {
    private PostTypeInference() {
    }

    public static boolean process(MethodNode mth, InsnNode insn) {
        DexNode dex = mth.dex();
        ArgType type;
        boolean change;
        int i;
        switch (insn.getType()) {
            case CONST:
                InsnArg res = insn.getResult();
                LiteralArg litArg = (LiteralArg) insn.getArg(0);
                if (res.getType().isObject()) {
                    long lit = litArg.getLiteral();
                    if (lit != 0) {
                        type = lit == 1 ? ArgType.BOOLEAN : ArgType.INT;
                        litArg.setType(type);
                        res.getSVar().setType(type);
                        return true;
                    }
                }
                return litArg.merge(dex, res);
            case MOVE:
                change = false;
                if (insn.getResult().merge(dex, insn.getArg(0))) {
                    change = true;
                }
                if (insn.getArg(0).merge(dex, insn.getResult())) {
                    return true;
                }
                return change;
            case AGET:
                return fixArrayTypes(dex, insn.getArg(0), insn.getResult());
            case APUT:
                return fixArrayTypes(dex, insn.getArg(0), insn.getArg(2));
            case IF:
                change = false;
                if (insn.getArg(1).merge(dex, insn.getArg(0))) {
                    change = true;
                }
                if (insn.getArg(0).merge(dex, insn.getArg(1))) {
                    return true;
                }
                return change;
            case INVOKE:
                change = false;
                InvokeNode inv = (InvokeNode) insn;
                MethodInfo callMth = inv.getCallMth();
                MethodNode node = mth.dex().resolveMethod(callMth);
                if (node == null || !node.isArgsOverload()) {
                    return false;
                }
                List<ArgType> args = callMth.getArgumentsTypes();
                i = args.size() - 1;
                int j = inv.getArgsCount() - 1;
                while (i >= 0) {
                    ArgType argType = (ArgType) args.get(i);
                    int j2 = j - 1;
                    InsnArg insnArg = inv.getArg(j);
                    if (insnArg.isRegister() && !argType.equals(insnArg.getType())) {
                        insnArg.setType(argType);
                        change = true;
                    }
                    i--;
                    j = j2;
                }
                return change;
            case CHECK_CAST:
                ArgType castType = (ArgType) ((IndexInsnNode) insn).getIndex();
                RegisterArg result = insn.getResult();
                ArgType resultType = result.getType();
                boolean skip = castType.isObject() && resultType.isObject() && castType.getObject().equals(resultType.getObject());
                if (!skip) {
                    result.getSVar().setType(castType);
                }
                return true;
            case PHI:
            case MERGE:
                type = insn.getResult().getType();
                if (!type.isTypeKnown()) {
                    for (InsnArg arg : insn.getArguments()) {
                        if (arg.getType().isTypeKnown()) {
                            type = arg.getType();
                        }
                    }
                }
                boolean changed = false;
                if (updateType(insn.getResult(), type)) {
                    changed = true;
                }
                for (i = 0; i < insn.getArgsCount(); i++) {
                    if (updateType((RegisterArg) insn.getArg(i), type)) {
                        changed = true;
                    }
                }
                return changed;
            default:
                return false;
        }
    }

    private static boolean updateType(RegisterArg arg, ArgType type) {
        ArgType prevType = arg.getType();
        if (prevType != null && prevType.equals(type)) {
            return false;
        }
        arg.setType(type);
        return true;
    }

    private static boolean fixArrayTypes(DexNode dex, InsnArg array, InsnArg elem) {
        boolean change = false;
        if (!elem.getType().isTypeKnown() && elem.merge(dex, array.getType().getArrayElement())) {
            change = true;
        }
        if (array.getType().isTypeKnown() || !array.merge(dex, ArgType.array(elem.getType()))) {
            return change;
        }
        return true;
    }
}
