package jadx.core.dex.visitors;

import jadx.core.dex.attributes.AType;
import jadx.core.dex.info.ClassInfo;
import jadx.core.dex.info.FieldInfo;
import jadx.core.dex.instructions.IndexInsnNode;
import jadx.core.dex.instructions.InvokeNode;
import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.instructions.args.InsnArg;
import jadx.core.dex.instructions.args.InsnWrapArg;
import jadx.core.dex.instructions.args.RegisterArg;
import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.ClassNode;
import jadx.core.dex.nodes.DexNode;
import jadx.core.dex.nodes.FieldNode;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.utils.exceptions.JadxException;
import java.util.Set;

public class DependencyCollector extends AbstractVisitor {
    public boolean visit(ClassNode cls) throws JadxException {
        DexNode dex = cls.dex();
        Set<ClassNode> depList = cls.getDependencies();
        processClass(cls, dex, depList);
        for (ClassNode inner : cls.getInnerClasses()) {
            processClass(inner, dex, depList);
        }
        depList.remove(cls);
        return false;
    }

    private static void processClass(ClassNode cls, DexNode dex, Set<ClassNode> depList) {
        addDep(dex, (Set) depList, cls.getSuperClass());
        for (ArgType iType : cls.getInterfaces()) {
            addDep(dex, (Set) depList, iType);
        }
        for (FieldNode fieldNode : cls.getFields()) {
            addDep(dex, (Set) depList, fieldNode.getType());
        }
        for (MethodNode methodNode : cls.getMethods()) {
            if (!(methodNode.isNoCode() || methodNode.contains(AType.JADX_ERROR))) {
                processMethod(dex, depList, methodNode);
            }
        }
    }

    private static void processMethod(DexNode dex, Set<ClassNode> depList, MethodNode methodNode) {
        addDep(dex, (Set) depList, methodNode.getParentClass());
        addDep(dex, (Set) depList, methodNode.getReturnType());
        for (ArgType arg : methodNode.getMethodInfo().getArgumentsTypes()) {
            addDep(dex, (Set) depList, arg);
        }
        for (BlockNode block : methodNode.getBasicBlocks()) {
            for (InsnNode insnNode : block.getInstructions()) {
                processInsn(dex, depList, insnNode);
            }
        }
    }

    private static void processInsn(DexNode dex, Set<ClassNode> depList, InsnNode insnNode) {
        RegisterArg result = insnNode.getResult();
        if (result != null) {
            addDep(dex, (Set) depList, result.getType());
        }
        for (InsnArg arg : insnNode.getArguments()) {
            if (arg.isInsnWrap()) {
                processInsn(dex, depList, ((InsnWrapArg) arg).getWrapInsn());
            } else {
                addDep(dex, (Set) depList, arg.getType());
            }
        }
        processCustomInsn(dex, depList, insnNode);
    }

    private static void processCustomInsn(DexNode dex, Set<ClassNode> depList, InsnNode insn) {
        if (insn instanceof IndexInsnNode) {
            Object index = ((IndexInsnNode) insn).getIndex();
            if (index instanceof FieldInfo) {
                addDep(dex, (Set) depList, ((FieldInfo) index).getDeclClass());
            } else if (index instanceof ArgType) {
                addDep(dex, (Set) depList, (ArgType) index);
            }
        } else if (insn instanceof InvokeNode) {
            addDep(dex, (Set) depList, ((InvokeNode) insn).getCallMth().getDeclClass());
        }
    }

    private static void addDep(DexNode dex, Set<ClassNode> depList, ArgType type) {
        if (type == null) {
            return;
        }
        if (type.isObject()) {
            addDep(dex, (Set) depList, ClassInfo.fromName(dex, type.getObject()));
            ArgType[] genericTypes = type.getGenericTypes();
            if (type.isGeneric() && genericTypes != null) {
                for (ArgType argType : genericTypes) {
                    addDep(dex, (Set) depList, argType);
                }
            }
        } else if (type.isArray()) {
            addDep(dex, (Set) depList, type.getArrayRootElement());
        }
    }

    private static void addDep(DexNode dex, Set<ClassNode> depList, ClassInfo clsInfo) {
        if (clsInfo != null) {
            addDep(dex, (Set) depList, dex.resolveClass(clsInfo));
        }
    }

    private static void addDep(DexNode dex, Set<ClassNode> depList, ClassNode clsNode) {
        if (clsNode != null) {
            depList.add(clsNode.getTopParentClass());
        }
    }
}
