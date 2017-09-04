package jadx.core.dex.visitors;

import jadx.core.dex.attributes.AFlag;
import jadx.core.dex.info.FieldInfo;
import jadx.core.dex.instructions.IndexInsnNode;
import jadx.core.dex.instructions.InsnType;
import jadx.core.dex.instructions.InvokeNode;
import jadx.core.dex.instructions.InvokeType;
import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.instructions.args.InsnArg;
import jadx.core.dex.instructions.args.LiteralArg;
import jadx.core.dex.instructions.args.PrimitiveType;
import jadx.core.dex.instructions.args.RegisterArg;
import jadx.core.dex.instructions.args.SSAVar;
import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.DexNode;
import jadx.core.dex.nodes.FieldNode;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.dex.visitors.typeinference.PostTypeInference;
import jadx.core.utils.InstructionRemover;
import jadx.core.utils.exceptions.JadxException;
import java.util.ArrayList;
import java.util.List;

public class ConstInlineVisitor extends AbstractVisitor {
    public void visit(MethodNode mth) throws JadxException {
        if (!mth.isNoCode()) {
            List toRemove = new ArrayList();
            for (BlockNode block : mth.getBasicBlocks()) {
                toRemove.clear();
                for (InsnNode insn : block.getInstructions()) {
                    if (checkInsn(mth, insn)) {
                        toRemove.add(insn);
                    }
                }
                InstructionRemover.removeAll(mth, block, toRemove);
            }
        }
    }

    private static boolean checkInsn(MethodNode mth, InsnNode insn) {
        if (insn.getType() != InsnType.CONST || insn.contains(AFlag.DONT_INLINE)) {
            return false;
        }
        InsnArg arg = insn.getArg(0);
        if (!arg.isLiteral()) {
            return false;
        }
        long lit = ((LiteralArg) arg).getLiteral();
        SSAVar sVar = insn.getResult().getSVar();
        if (lit == 0 && checkObjectInline(sVar)) {
            if (sVar.getUseCount() == 1) {
                InsnNode assignInsn = insn.getResult().getAssignInsn();
                if (assignInsn != null) {
                    assignInsn.add(AFlag.DONT_INLINE);
                }
            }
            return false;
        }
        ArgType resType = insn.getResult().getType();
        if (!arg.getType().isTypeKnown()) {
            arg.merge(mth.dex(), resType);
        }
        return replaceConst(mth, insn, lit);
    }

    private static boolean checkObjectInline(SSAVar sVar) {
        for (InsnArg useArg : sVar.getUseList()) {
            InsnNode insn = useArg.getParentInsn();
            if (insn != null) {
                InsnType insnType = insn.getType();
                if (insnType == InsnType.INVOKE) {
                    InvokeNode inv = (InvokeNode) insn;
                    if (inv.getInvokeType() != InvokeType.STATIC && inv.getArg(0) == useArg) {
                        return true;
                    }
                } else if (insnType == InsnType.ARRAY_LENGTH && insn.getArg(0) == useArg) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean replaceConst(MethodNode mth, InsnNode constInsn, long literal) {
        List<RegisterArg> use = new ArrayList(constInsn.getResult().getSVar().getUseList());
        int replaceCount = 0;
        for (RegisterArg arg : use) {
            InsnNode useInsn = arg.getParentInsn();
            if (!(useInsn == null || useInsn.getType() == InsnType.PHI || useInsn.getType() == InsnType.MERGE)) {
                LiteralArg litArg;
                ArgType argType = arg.getType();
                if (argType.isObject() && literal != 0) {
                    argType = ArgType.NARROW_NUMBERS;
                }
                if (use.size() == 1 || arg.isTypeImmutable()) {
                    litArg = InsnArg.lit(literal, argType);
                } else if (useInsn.getType() != InsnType.MOVE || useInsn.getResult().getType().isTypeKnown()) {
                    litArg = InsnArg.lit(literal, ArgType.UNKNOWN);
                } else {
                    litArg = InsnArg.lit(literal, argType);
                }
                if (useInsn.replaceArg(arg, litArg)) {
                    fixTypes(mth, useInsn, litArg);
                    replaceCount++;
                    if (useInsn.getType() == InsnType.RETURN) {
                        useInsn.setSourceLine(constInsn.getSourceLine());
                    }
                    FieldNode f = null;
                    ArgType litArgType = litArg.getType();
                    if (litArgType.isTypeKnown()) {
                        f = mth.getParentClass().getConstFieldByLiteralArg(litArg);
                    } else if (litArgType.contains(PrimitiveType.INT)) {
                        f = mth.getParentClass().getConstField(Integer.valueOf((int) literal), false);
                    }
                    if (f != null) {
                        litArg.wrapInstruction(new IndexInsnNode(InsnType.SGET, f.getFieldInfo(), 0));
                    }
                }
            }
        }
        return replaceCount == use.size();
    }

    private static void fixTypes(MethodNode mth, InsnNode insn, LiteralArg litArg) {
        DexNode dex = mth.dex();
        PostTypeInference.process(mth, insn);
        InsnArg arg0;
        InsnArg arg1;
        switch (insn.getType()) {
            case CONST:
                insn.getArg(0).merge(dex, insn.getResult());
                return;
            case MOVE:
                insn.getResult().merge(dex, insn.getArg(0));
                insn.getArg(0).merge(dex, insn.getResult());
                return;
            case IPUT:
            case SPUT:
                insn.getArg(0).merge(dex, ((FieldInfo) ((IndexInsnNode) insn).getIndex()).getType());
                return;
            case IF:
                arg0 = insn.getArg(0);
                arg1 = insn.getArg(1);
                if (arg0 == litArg) {
                    arg0.merge(dex, arg1);
                    return;
                } else {
                    arg1.merge(dex, arg0);
                    return;
                }
            case CMP_G:
            case CMP_L:
                arg0 = insn.getArg(0);
                arg1 = insn.getArg(1);
                if (arg0 == litArg) {
                    arg0.merge(dex, arg1);
                    return;
                } else {
                    arg1.merge(dex, arg0);
                    return;
                }
            case RETURN:
                if (insn.getArgsCount() != 0) {
                    insn.getArg(0).merge(dex, mth.getReturnType());
                    return;
                }
                return;
            case INVOKE:
                List<ArgType> types = ((InvokeNode) insn).getCallMth().getArgumentsTypes();
                int count = insn.getArgsCount();
                int k = types.size() == count ? 0 : -1;
                for (int i = 0; i < count; i++) {
                    InsnArg arg = insn.getArg(i);
                    if (!arg.getType().isTypeKnown()) {
                        ArgType type;
                        if (k >= 0) {
                            type = (ArgType) types.get(k);
                        } else {
                            type = mth.getParentClass().getClassInfo().getType();
                        }
                        arg.merge(dex, type);
                    }
                    k++;
                }
                return;
            case ARITH:
                litArg.merge(dex, insn.getResult());
                return;
            case APUT:
            case AGET:
                if (litArg == insn.getArg(1)) {
                    litArg.merge(dex, ArgType.INT);
                    return;
                }
                return;
            case NEW_ARRAY:
                if (litArg == insn.getArg(0)) {
                    litArg.merge(dex, ArgType.INT);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
