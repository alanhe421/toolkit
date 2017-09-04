package jadx.core.dex.visitors;

import jadx.core.codegen.TypeGen;
import jadx.core.deobf.NameMapper;
import jadx.core.dex.attributes.AFlag;
import jadx.core.dex.attributes.AType;
import jadx.core.dex.attributes.nodes.FieldReplaceAttr;
import jadx.core.dex.info.ClassInfo;
import jadx.core.dex.info.FieldInfo;
import jadx.core.dex.info.MethodInfo;
import jadx.core.dex.instructions.ArithNode;
import jadx.core.dex.instructions.ConstClassNode;
import jadx.core.dex.instructions.ConstStringNode;
import jadx.core.dex.instructions.FillArrayNode;
import jadx.core.dex.instructions.FilledNewArrayNode;
import jadx.core.dex.instructions.IndexInsnNode;
import jadx.core.dex.instructions.InsnType;
import jadx.core.dex.instructions.InvokeNode;
import jadx.core.dex.instructions.NewArrayNode;
import jadx.core.dex.instructions.SwitchNode;
import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.instructions.args.InsnArg;
import jadx.core.dex.instructions.args.LiteralArg;
import jadx.core.dex.instructions.args.NamedArg;
import jadx.core.dex.instructions.args.RegisterArg;
import jadx.core.dex.instructions.args.SSAVar;
import jadx.core.dex.instructions.mods.ConstructorInsn;
import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.ClassNode;
import jadx.core.dex.nodes.FieldNode;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.dex.trycatch.ExcHandlerAttr;
import jadx.core.dex.trycatch.ExceptionHandler;
import jadx.core.utils.ErrorsCounter;
import jadx.core.utils.InsnUtils;
import jadx.core.utils.InstructionRemover;
import jadx.core.utils.exceptions.JadxRuntimeException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@JadxVisitor(desc = "Modify method instructions", name = "ModVisitor", runBefore = {CodeShrinker.class})
public class ModVisitor extends AbstractVisitor {
    private static final Logger LOG = LoggerFactory.getLogger(ModVisitor.class);

    public void visit(MethodNode mth) {
        if (!mth.isNoCode()) {
            InstructionRemover remover = new InstructionRemover(mth);
            replaceStep(mth, remover);
            removeStep(mth, remover);
            checkArgsNames(mth);
        }
    }

    private static void replaceStep(MethodNode mth, InstructionRemover remover) {
        ClassNode parentClass = mth.getParentClass();
        for (BlockNode block : mth.getBasicBlocks()) {
            remover.setBlock(block);
            int size = block.getInstructions().size();
            for (int i = 0; i < size; i++) {
                InsnNode insn = (InsnNode) block.getInstructions().get(i);
                FieldNode f;
                switch (insn.getType()) {
                    case INVOKE:
                        processInvoke(mth, block, i, remover);
                        break;
                    case CONST:
                    case CONST_STR:
                    case CONST_CLASS:
                        if (insn.getType() == InsnType.CONST_STR) {
                            f = parentClass.getConstField(((ConstStringNode) insn).getString());
                        } else {
                            if (insn.getType() == InsnType.CONST_CLASS) {
                                f = parentClass.getConstField(((ConstClassNode) insn).getClsType());
                            } else {
                                f = parentClass.getConstFieldByLiteralArg((LiteralArg) insn.getArg(0));
                            }
                        }
                        if (f == null) {
                            break;
                        }
                        InsnNode inode = new IndexInsnNode(InsnType.SGET, f.getFieldInfo(), 0);
                        inode.setResult(insn.getResult());
                        replaceInsn(block, i, inode);
                        break;
                    case SWITCH:
                        SwitchNode sn = (SwitchNode) insn;
                        for (int k = 0; k < sn.getCasesCount(); k++) {
                            f = parentClass.getConstField(sn.getKeys()[k]);
                            if (f != null) {
                                sn.getKeys()[k] = f;
                            }
                        }
                        break;
                    case NEW_ARRAY:
                        int next = i + 1;
                        if (next >= size) {
                            break;
                        }
                        InsnNode ni = (InsnNode) block.getInstructions().get(next);
                        if (ni.getType() != InsnType.FILL_ARRAY) {
                            break;
                        }
                        ni.getResult().merge(mth.dex(), insn.getResult());
                        ArgType arrType = ((NewArrayNode) insn).getArrayType();
                        ((FillArrayNode) ni).mergeElementType(mth.dex(), arrType.getArrayElement());
                        remover.add(insn);
                        break;
                    case FILL_ARRAY:
                        replaceInsn(block, i, makeFilledArrayInsn(mth, (FillArrayNode) insn));
                        break;
                    case MOVE_EXCEPTION:
                        processMoveException(mth, block, insn, remover);
                        break;
                    case ARITH:
                        ArithNode arithNode = (ArithNode) insn;
                        if (arithNode.getArgsCount() != 2) {
                            break;
                        }
                        InsnArg litArg = arithNode.getArg(1);
                        if (!litArg.isLiteral()) {
                            break;
                        }
                        f = parentClass.getConstFieldByLiteralArg((LiteralArg) litArg);
                        if (f == null) {
                            break;
                        }
                        insn.replaceArg(litArg, InsnArg.wrapArg(new IndexInsnNode(InsnType.SGET, f.getFieldInfo(), 0)));
                        break;
                    default:
                        break;
                }
            }
            remover.perform();
        }
    }

    private static void removeStep(MethodNode mth, InstructionRemover remover) {
        for (BlockNode block : mth.getBasicBlocks()) {
            remover.setBlock(block);
            for (InsnNode insn : block.getInstructions()) {
                switch (insn.getType()) {
                    case NOP:
                    case GOTO:
                    case NEW_INSTANCE:
                        remover.add(insn);
                        break;
                    default:
                        break;
                }
            }
            remover.perform();
        }
    }

    private static void processInvoke(MethodNode mth, BlockNode block, int insnNumber, InstructionRemover remover) {
        ClassNode parentClass = mth.getParentClass();
        InsnNode insn = (InsnNode) block.getInstructions().get(insnNumber);
        InvokeNode inv = (InvokeNode) insn;
        MethodInfo callMth = inv.getCallMth();
        if (callMth.isConstructor()) {
            InsnNode instArgAssignInsn = ((RegisterArg) inv.getArg(0)).getAssignInsn();
            ConstructorInsn co = new ConstructorInsn(mth, inv);
            boolean remove = false;
            if (co.isSuper() && (co.getArgsCount() == 0 || parentClass.isEnum())) {
                remove = true;
            } else if (co.isThis() && co.getArgsCount() == 0) {
                MethodNode defCo = parentClass.searchMethodByName(callMth.getShortId());
                if (defCo == null || defCo.isNoCode()) {
                    remove = true;
                }
            }
            if (parentClass.isAnonymous() && mth.isDefaultConstructor() && co.isSuper()) {
                remove = true;
            }
            if (remove) {
                remover.add(insn);
                return;
            }
            if (co.isNewInstance()) {
                InsnNode newInstInsn = removeAssignChain(instArgAssignInsn, remover, InsnType.NEW_INSTANCE);
                if (newInstInsn != null) {
                    RegisterArg instArg = newInstInsn.getResult();
                    RegisterArg resultArg = co.getResult();
                    if (!resultArg.equals(instArg)) {
                        Iterator i$ = new ArrayList(instArg.getSVar().getUseList()).iterator();
                        while (i$.hasNext()) {
                            InsnArg useArg = (RegisterArg) i$.next();
                            RegisterArg dup = resultArg.duplicate();
                            InsnNode parentInsn = useArg.getParentInsn();
                            parentInsn.replaceArg(useArg, dup);
                            dup.setParentInsn(parentInsn);
                            resultArg.getSVar().use(dup);
                        }
                    }
                }
            }
            ConstructorInsn replace = processConstructor(mth, co);
            if (replace != null) {
                co = replace;
            }
            replaceInsn(block, insnNumber, co);
            processAnonymousConstructor(mth, co);
        }
    }

    private static void processAnonymousConstructor(MethodNode mth, ConstructorInsn co) {
        MethodInfo callMth = co.getCallMth();
        MethodNode callMthNode = mth.dex().resolveMethod(callMth);
        if (callMthNode != null) {
            ClassNode classNode = callMthNode.getParentClass();
            ClassInfo classInfo = classNode.getClassInfo();
            ClassNode parentClass = mth.getParentClass();
            if (!classInfo.isInner() || !Character.isDigit(classInfo.getShortName().charAt(0)) || !parentClass.getInnerClasses().contains(classNode)) {
                return;
            }
            if (classNode.getAccessFlags().isStatic() || (callMth.getArgsCount() != 0 && ((ArgType) callMth.getArgumentsTypes().get(0)).equals(parentClass.getClassInfo().getType()))) {
                Map<InsnArg, FieldNode> argsMap = getArgsToFieldsMapping(callMthNode, co);
                if (!argsMap.isEmpty()) {
                    classNode.add(AFlag.ANONYMOUS_CLASS);
                    callMthNode.add(AFlag.DONT_GENERATE);
                    for (Entry<InsnArg, FieldNode> entry : argsMap.entrySet()) {
                        FieldNode field = (FieldNode) entry.getValue();
                        if (field != null) {
                            InsnArg arg = (InsnArg) entry.getKey();
                            field.addAttr(new FieldReplaceAttr(arg));
                            field.add(AFlag.DONT_GENERATE);
                            if (arg.isRegister()) {
                                RegisterArg reg = (RegisterArg) arg;
                                SSAVar sVar = reg.getSVar();
                                if (sVar != null) {
                                    sVar.add(AFlag.FINAL);
                                    sVar.add(AFlag.DONT_INLINE);
                                }
                                reg.add(AFlag.SKIP_ARG);
                            }
                        }
                    }
                }
            }
        }
    }

    private static Map<InsnArg, FieldNode> getArgsToFieldsMapping(MethodNode callMthNode, ConstructorInsn co) {
        int startArg = 0;
        Map<InsnArg, FieldNode> map = new LinkedHashMap();
        ClassNode parentClass = callMthNode.getParentClass();
        List<RegisterArg> argList = callMthNode.getArguments(false);
        if (!parentClass.getAccessFlags().isStatic()) {
            startArg = 1;
        }
        int argsCount = argList.size();
        for (int i = startArg; i < argsCount; i++) {
            InsnNode useInsn = getParentInsnSkipMove((RegisterArg) argList.get(i));
            if (useInsn == null) {
                return Collections.emptyMap();
            }
            FieldNode fieldNode = null;
            if (useInsn.getType() == InsnType.IPUT) {
                fieldNode = parentClass.searchField((FieldInfo) ((IndexInsnNode) useInsn).getIndex());
                if (fieldNode == null || !fieldNode.getAccessFlags().isSynthetic()) {
                    return Collections.emptyMap();
                }
            } else if (useInsn.getType() != InsnType.CONSTRUCTOR) {
                return Collections.emptyMap();
            } else {
                if (!((ConstructorInsn) useInsn).isSuper()) {
                    return Collections.emptyMap();
                }
            }
            map.put(co.getArg(i), fieldNode);
        }
        return map;
    }

    private static InsnNode getParentInsnSkipMove(RegisterArg arg) {
        SSAVar sVar = arg.getSVar();
        if (sVar.getUseCount() != 1) {
            return null;
        }
        InsnNode parentInsn = ((RegisterArg) sVar.getUseList().get(0)).getParentInsn();
        if (parentInsn == null) {
            return null;
        }
        if (parentInsn.getType() == InsnType.MOVE) {
            return getParentInsnSkipMove(parentInsn.getResult());
        }
        return parentInsn;
    }

    private static ConstructorInsn processConstructor(MethodNode mth, ConstructorInsn co) {
        boolean passThis = true;
        MethodNode callMth = mth.dex().resolveMethod(co.getCallMth());
        if (callMth == null || !callMth.getAccessFlags().isSynthetic() || !allArgsNull(co)) {
            return null;
        }
        ClassNode classNode = mth.dex().resolveClass(callMth.getParentClass().getClassInfo());
        if (classNode == null) {
            return null;
        }
        if (co.getArgsCount() < 1 || !co.getArg(0).isThis()) {
            passThis = false;
        }
        MethodNode defCtr = classNode.searchMethodByName("<init>(" + (passThis ? TypeGen.signature(co.getArg(0).getType()) : "") + ")V");
        if (defCtr == null) {
            return null;
        }
        ConstructorInsn newInsn = new ConstructorInsn(defCtr.getMethodInfo(), co.getCallType(), co.getInstanceArg());
        newInsn.setResult(co.getResult());
        return newInsn;
    }

    private static InsnNode makeFilledArrayInsn(MethodNode mth, FillArrayNode insn) {
        ArgType insnArrayType = insn.getResult().getType();
        ArgType insnElementType = insnArrayType.getArrayElement();
        ArgType elType = insn.getElementType();
        if (!elType.isTypeKnown() && insnElementType.isPrimitive() && elType.contains(insnElementType.getPrimitiveType())) {
            elType = insnElementType;
        }
        if (!(elType.equals(insnElementType) || insnArrayType.equals(ArgType.OBJECT))) {
            ErrorsCounter.methodError(mth, "Incorrect type for fill-array insn " + InsnUtils.formatOffset(insn.getOffset()) + ", element type: " + elType + ", insn element type: " + insnElementType);
        }
        if (!elType.isTypeKnown()) {
            LOG.warn("Unknown array element type: {} in mth: {}", elType, mth);
            elType = insnElementType.isTypeKnown() ? insnElementType : elType.selectFirst();
            if (elType == null) {
                throw new JadxRuntimeException("Null array element type");
            }
        }
        insn.mergeElementType(mth.dex(), elType);
        elType = insn.getElementType();
        List<LiteralArg> list = insn.getLiteralArgs();
        InsnNode filledArr = new FilledNewArrayNode(elType, list.size());
        filledArr.setResult(insn.getResult());
        for (LiteralArg arg : list) {
            FieldNode f = mth.getParentClass().getConstFieldByLiteralArg(arg);
            if (f != null) {
                filledArr.addArg(InsnArg.wrapArg(new IndexInsnNode(InsnType.SGET, f.getFieldInfo(), 0)));
            } else {
                filledArr.addArg(arg);
            }
        }
        return filledArr;
    }

    private static boolean allArgsNull(InsnNode insn) {
        for (InsnArg insnArg : insn.getArguments()) {
            if (insnArg.isLiteral()) {
                if (((LiteralArg) insnArg).getLiteral() != 0) {
                    return false;
                }
            } else if (!insnArg.isThis()) {
                return false;
            }
        }
        return true;
    }

    private static InsnNode removeAssignChain(InsnNode insn, InstructionRemover remover, InsnType insnType) {
        if (insn == null) {
            return null;
        }
        remover.add(insn);
        InsnType type = insn.getType();
        if (type != insnType) {
            return type == InsnType.MOVE ? removeAssignChain(((RegisterArg) insn.getArg(0)).getAssignInsn(), remover, insnType) : null;
        } else {
            return insn;
        }
    }

    private static void processMoveException(MethodNode mth, BlockNode block, InsnNode insn, InstructionRemover remover) {
        ExcHandlerAttr excHandlerAttr = (ExcHandlerAttr) block.get(AType.EXC_HANDLER);
        if (excHandlerAttr != null) {
            ExceptionHandler excHandler = excHandlerAttr.getHandler();
            RegisterArg resArg = insn.getResult();
            ArgType type = excHandler.isCatchAll() ? ArgType.THROWABLE : excHandler.getCatchType().getType();
            String name = excHandler.isCatchAll() ? "th" : "e";
            if (resArg.getName() == null) {
                resArg.setName(name);
            }
            SSAVar sVar = insn.getResult().getSVar();
            if (sVar.getUseCount() == 0) {
                excHandler.setArg(new NamedArg(name, type));
                remover.add(insn);
            } else if (sVar.isUsedInPhi()) {
                InsnNode moveInsn = new InsnNode(InsnType.MOVE, 1);
                moveInsn.setResult(insn.getResult());
                NamedArg namedArg = new NamedArg(name, type);
                moveInsn.addArg(namedArg);
                excHandler.setArg(namedArg);
                replaceInsn(block, 0, moveInsn);
            }
        }
    }

    private static void replaceInsn(BlockNode block, int i, InsnNode insn) {
        InsnNode prevInsn = (InsnNode) block.getInstructions().get(i);
        insn.copyAttributesFrom(prevInsn);
        insn.setSourceLine(prevInsn.getSourceLine());
        block.getInstructions().set(i, insn);
    }

    private static void checkArgsNames(MethodNode mth) {
        for (RegisterArg arg : mth.getArguments(false)) {
            String name = arg.getName();
            if (name != null && NameMapper.isReserved(name)) {
                arg.getSVar().setName(name + "_");
            }
        }
    }
}
