package jadx.core.dex.visitors;

import jadx.core.dex.attributes.AFlag;
import jadx.core.dex.instructions.ArithNode;
import jadx.core.dex.instructions.ArithOp;
import jadx.core.dex.instructions.InsnType;
import jadx.core.dex.instructions.args.InsnArg;
import jadx.core.dex.instructions.args.InsnWrapArg;
import jadx.core.dex.instructions.args.RegisterArg;
import jadx.core.dex.instructions.mods.ConstructorInsn;
import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.utils.exceptions.JadxException;
import java.util.Iterator;
import java.util.List;

@JadxVisitor(desc = "Prepare instructions for code generation pass", name = "PrepareForCodeGen", runAfter = {CodeShrinker.class, ClassModifier.class})
public class PrepareForCodeGen extends AbstractVisitor {
    public void visit(MethodNode mth) throws JadxException {
        List<BlockNode> blocks = mth.getBasicBlocks();
        if (blocks != null) {
            for (BlockNode block : blocks) {
                removeInstructions(block);
                checkInline(block);
                modifyArith(block);
            }
        }
    }

    private static void removeInstructions(BlockNode block) {
        Iterator<InsnNode> it = block.getInstructions().iterator();
        while (it.hasNext()) {
            InsnNode insn = (InsnNode) it.next();
            switch (insn.getType()) {
                case NOP:
                case MONITOR_ENTER:
                case MONITOR_EXIT:
                case MOVE_EXCEPTION:
                    it.remove();
                    break;
                case CONSTRUCTOR:
                    if (!((ConstructorInsn) insn).isSelf()) {
                        break;
                    }
                    it.remove();
                    break;
                case MOVE:
                    RegisterArg result = insn.getResult();
                    if (result.getSVar().getUseCount() == 0 && result.isNameEquals(insn.getArg(0))) {
                        it.remove();
                        break;
                    }
                default:
                    break;
            }
        }
    }

    private static void checkInline(BlockNode block) {
        List<InsnNode> list = block.getInstructions();
        for (int i = 0; i < list.size(); i++) {
            InsnNode insn = (InsnNode) list.get(i);
            if (insn.getType() == InsnType.MOVE && insn.getArg(0).isInsnWrap()) {
                InsnNode wrapInsn = ((InsnWrapArg) insn.getArg(0)).getWrapInsn();
                wrapInsn.setResult(insn.getResult());
                wrapInsn.copyAttributesFrom(insn);
                list.set(i, wrapInsn);
            }
        }
    }

    private static void removeParenthesis(BlockNode block) {
        for (InsnNode insn : block.getInstructions()) {
            checkInsn(insn);
        }
    }

    private static void checkInsn(InsnNode insn) {
        InsnArg arg;
        if (insn.getType() == InsnType.ARITH) {
            ArithNode arith = (ArithNode) insn;
            ArithOp op = arith.getOp();
            if (op == ArithOp.ADD || op == ArithOp.SUB) {
                for (int i = 0; i < 2; i++) {
                    arg = arith.getArg(i);
                    if (arg.isInsnWrap()) {
                        InsnNode wrapInsn = ((InsnWrapArg) arg).getWrapInsn();
                        wrapInsn.add(AFlag.DONT_WRAP);
                        checkInsn(wrapInsn);
                    }
                }
                return;
            }
            return;
        }
        for (InsnArg arg2 : insn.getArguments()) {
            if (arg2.isInsnWrap()) {
                checkInsn(((InsnWrapArg) arg2).getWrapInsn());
            }
        }
    }

    private static void modifyArith(BlockNode block) {
        for (InsnNode insn : block.getInstructions()) {
            if (insn.getType() == InsnType.ARITH) {
                RegisterArg res = insn.getResult();
                InsnArg arg = insn.getArg(0);
                boolean replace = false;
                if (res.equals(arg)) {
                    replace = true;
                } else if (arg.isRegister()) {
                    replace = res.equalRegisterAndType((RegisterArg) arg);
                }
                if (replace) {
                    insn.add(AFlag.ARITH_ONEARG);
                }
            }
        }
    }
}
