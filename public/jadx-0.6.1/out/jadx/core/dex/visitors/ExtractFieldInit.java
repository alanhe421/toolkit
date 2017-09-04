package jadx.core.dex.visitors;

import jadx.core.dex.attributes.AFlag;
import jadx.core.dex.attributes.AType;
import jadx.core.dex.info.AccessInfo;
import jadx.core.dex.info.FieldInfo;
import jadx.core.dex.instructions.IndexInsnNode;
import jadx.core.dex.instructions.InsnType;
import jadx.core.dex.instructions.args.InsnArg;
import jadx.core.dex.instructions.args.InsnWrapArg;
import jadx.core.dex.instructions.args.RegisterArg;
import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.ClassNode;
import jadx.core.dex.nodes.FieldNode;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.dex.nodes.parser.FieldInitAttr;
import jadx.core.utils.BlockUtils;
import jadx.core.utils.InstructionRemover;
import jadx.core.utils.exceptions.JadxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JadxVisitor(desc = "Move duplicated field initialization from constructors", name = "ExtractFieldInit", runAfter = {ModVisitor.class}, runBefore = {ClassModifier.class})
public class ExtractFieldInit extends AbstractVisitor {

    private static class InitInfo {
        private final MethodNode constrMth;
        private final List<InsnNode> putInsns;

        private InitInfo(MethodNode constrMth) {
            this.putInsns = new ArrayList();
            this.constrMth = constrMth;
        }

        public MethodNode getConstrMth() {
            return this.constrMth;
        }

        public List<InsnNode> getPutInsns() {
            return this.putInsns;
        }
    }

    public boolean visit(ClassNode cls) throws JadxException {
        if (!cls.isEnum()) {
            for (ClassNode inner : cls.getInnerClasses()) {
                visit(inner);
            }
            checkStaticFieldsInit(cls);
            moveStaticFieldsInit(cls);
            moveCommonFieldsInit(cls);
        }
        return false;
    }

    private static void checkStaticFieldsInit(ClassNode cls) {
        MethodNode clinit = cls.getClassInitMth();
        if (clinit != null && clinit.getAccessFlags().isStatic() && !clinit.isNoCode()) {
            for (BlockNode block : clinit.getBasicBlocks()) {
                for (InsnNode insn : block.getInstructions()) {
                    if (insn.getType() == InsnType.SPUT) {
                        processStaticFieldAssign(cls, (IndexInsnNode) insn);
                    }
                }
            }
        }
    }

    private static void processStaticFieldAssign(ClassNode cls, IndexInsnNode insn) {
        FieldInfo field = (FieldInfo) insn.getIndex();
        if (field.getDeclClass().getFullName().equals(cls.getClassInfo().getFullName())) {
            FieldNode fn = cls.searchField(field);
            if (fn != null && fn.getAccessFlags().isFinal()) {
                fn.remove(AType.FIELD_INIT);
            }
        }
    }

    private static void moveStaticFieldsInit(ClassNode cls) {
        MethodNode classInitMth = cls.getClassInitMth();
        if (classInitMth != null) {
            for (FieldNode field : cls.getFields()) {
                if (!field.contains(AFlag.DONT_GENERATE) && field.getAccessFlags().isStatic()) {
                    List<InsnNode> initInsns = getFieldAssigns(classInitMth, field, InsnType.SPUT);
                    if (initInsns.size() == 1) {
                        InsnNode insn = (InsnNode) initInsns.get(0);
                        if (checkInsn(insn)) {
                            InstructionRemover.remove(classInitMth, insn);
                            addFieldInitAttr(classInitMth, field, insn);
                        }
                    }
                }
            }
        }
    }

    private static void moveCommonFieldsInit(ClassNode cls) {
        List<MethodNode> constrList = getConstructorsList(cls);
        if (!constrList.isEmpty()) {
            InitInfo info;
            List<InitInfo> infoList = new ArrayList(constrList.size());
            for (MethodNode constrMth : constrList) {
                if (!constrMth.isNoCode() && !constrMth.getBasicBlocks().isEmpty()) {
                    info = new InitInfo(constrMth);
                    infoList.add(info);
                    for (InsnNode insn : ((BlockNode) constrMth.getBasicBlocks().get(0)).getInstructions()) {
                        if (insn.getType() == InsnType.IPUT && checkInsn(insn)) {
                            info.getPutInsns().add(insn);
                        } else if (!info.getPutInsns().isEmpty()) {
                            break;
                        }
                    }
                } else {
                    return;
                }
            }
            InitInfo common = null;
            for (InitInfo info2 : infoList) {
                if (common == null) {
                    common = info2;
                } else if (!compareInsns(common.getPutInsns(), info2.getPutInsns())) {
                    return;
                }
            }
            if (common != null) {
                Set<FieldInfo> fields = new HashSet();
                for (InsnNode insn2 : common.getPutInsns()) {
                    FieldInfo fieldInfo = (FieldInfo) ((IndexInsnNode) insn2).getIndex();
                    if (cls.dex().resolveField(fieldInfo) == null) {
                        return;
                    }
                    if (!fields.add(fieldInfo)) {
                        return;
                    }
                }
                for (InitInfo info22 : infoList) {
                    for (InsnNode putInsn : info22.getPutInsns()) {
                        InstructionRemover.remove(info22.getConstrMth(), putInsn);
                    }
                }
                for (InsnNode insn22 : common.getPutInsns()) {
                    addFieldInitAttr(common.getConstrMth(), cls.dex().resolveField((FieldInfo) ((IndexInsnNode) insn22).getIndex()), insn22);
                }
            }
        }
    }

    private static boolean compareInsns(List<InsnNode> base, List<InsnNode> other) {
        if (base.size() != other.size()) {
            return false;
        }
        int count = base.size();
        for (int i = 0; i < count; i++) {
            if (!((InsnNode) base.get(i)).isSame((InsnNode) other.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkInsn(InsnNode insn) {
        InsnArg arg = insn.getArg(0);
        if (arg.isInsnWrap()) {
            if (!((InsnWrapArg) arg).getWrapInsn().canReorderRecursive() && insn.contains(AType.CATCH_BLOCK)) {
                return false;
            }
            Set<RegisterArg> regs = new HashSet();
            insn.getRegisterArgs(regs);
            if (!regs.isEmpty()) {
                for (RegisterArg reg : regs) {
                    if (!reg.isThis()) {
                        return false;
                    }
                }
            }
            return true;
        } else if (arg.isLiteral() || arg.isThis()) {
            return true;
        } else {
            return false;
        }
    }

    private static List<MethodNode> getConstructorsList(ClassNode cls) {
        List<MethodNode> list = new ArrayList();
        for (MethodNode mth : cls.getMethods()) {
            AccessInfo accFlags = mth.getAccessFlags();
            if (!accFlags.isStatic() && accFlags.isConstructor()) {
                list.add(mth);
                if (BlockUtils.isAllBlocksEmpty(mth.getBasicBlocks())) {
                    return Collections.emptyList();
                }
            }
        }
        return list;
    }

    private static List<InsnNode> getFieldAssigns(MethodNode mth, FieldNode field, InsnType putInsn) {
        if (mth.isNoCode()) {
            return Collections.emptyList();
        }
        List<InsnNode> assignInsns = new ArrayList();
        for (BlockNode block : mth.getBasicBlocks()) {
            for (InsnNode insn : block.getInstructions()) {
                if (insn.getType() == putInsn && ((FieldInfo) ((IndexInsnNode) insn).getIndex()).equals(field.getFieldInfo())) {
                    assignInsns.add(insn);
                }
            }
        }
        return assignInsns;
    }

    private static void addFieldInitAttr(MethodNode classInitMth, FieldNode field, InsnNode insn) {
        field.addAttr(FieldInitAttr.insnValue(classInitMth, InsnNode.wrapArg(insn.getArg(0))));
    }
}
