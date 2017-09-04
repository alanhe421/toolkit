package jadx.core.dex.visitors;

import jadx.core.dex.attributes.AFlag;
import jadx.core.dex.instructions.InsnType;
import jadx.core.dex.instructions.args.InsnArg;
import jadx.core.dex.instructions.args.InsnWrapArg;
import jadx.core.dex.instructions.args.RegisterArg;
import jadx.core.dex.instructions.args.SSAVar;
import jadx.core.dex.instructions.mods.ConstructorInsn;
import jadx.core.dex.instructions.mods.TernaryInsn;
import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.utils.BlockUtils;
import jadx.core.utils.EmptyBitSet;
import jadx.core.utils.InsnList;
import jadx.core.utils.exceptions.JadxRuntimeException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public class CodeShrinker extends AbstractVisitor {

    private static final class ArgsInfo {
        private final List<RegisterArg> args;
        private final List<ArgsInfo> argsList;
        private int inlineBorder;
        private ArgsInfo inlinedInsn;
        private final InsnNode insn;
        private final int pos;

        public ArgsInfo(InsnNode insn, List<ArgsInfo> argsList, int pos) {
            this.insn = insn;
            this.argsList = argsList;
            this.pos = pos;
            this.inlineBorder = pos;
            this.args = getArgs(insn);
        }

        public static List<RegisterArg> getArgs(InsnNode insn) {
            List<RegisterArg> args = new LinkedList();
            addArgs(insn, args);
            return args;
        }

        private static void addArgs(InsnNode insn, List<RegisterArg> args) {
            if (insn.getType() == InsnType.CONSTRUCTOR) {
                args.add(((ConstructorInsn) insn).getInstanceArg());
            } else if (insn.getType() == InsnType.TERNARY) {
                args.addAll(((TernaryInsn) insn).getCondition().getRegisterArgs());
            }
            for (InsnArg arg : insn.getArguments()) {
                if (arg.isRegister()) {
                    args.add((RegisterArg) arg);
                }
            }
            for (InsnArg arg2 : insn.getArguments()) {
                if (arg2.isInsnWrap()) {
                    addArgs(((InsnWrapArg) arg2).getWrapInsn(), args);
                }
            }
        }

        public InsnNode getInsn() {
            return this.insn;
        }

        private List<RegisterArg> getArgs() {
            return this.args;
        }

        public WrapInfo checkInline(int assignPos, RegisterArg arg) {
            if (!arg.isThis() && (assignPos >= this.inlineBorder || !canMove(assignPos, this.inlineBorder))) {
                return null;
            }
            this.inlineBorder = assignPos;
            return inline(assignPos, arg);
        }

        private boolean canMove(int from, int to) {
            ArgsInfo startInfo = (ArgsInfo) this.argsList.get(from);
            List<RegisterArg> movedArgs = startInfo.getArgs();
            int start = from + 1;
            if (start == to) {
                return true;
            }
            if (start > to) {
                throw new JadxRuntimeException("Invalid inline insn positions: " + start + " - " + to);
            }
            BitSet movedSet;
            if (!movedArgs.isEmpty()) {
                movedSet = new BitSet();
                for (RegisterArg arg : movedArgs) {
                    movedSet.set(arg.getRegNum());
                }
            } else if (startInfo.insn.isConstInsn()) {
                return true;
            } else {
                movedSet = EmptyBitSet.EMPTY;
            }
            for (int i = start; i < to; i++) {
                ArgsInfo argsInfo = (ArgsInfo) this.argsList.get(i);
                if (argsInfo.getInlinedInsn() != this) {
                    InsnNode curInsn = argsInfo.insn;
                    if (!curInsn.canReorder() || usedArgAssign(curInsn, movedSet)) {
                        return false;
                    }
                }
            }
            return true;
        }

        private static boolean usedArgAssign(InsnNode insn, BitSet args) {
            RegisterArg result = insn.getResult();
            return result != null && args.get(result.getRegNum());
        }

        public WrapInfo inline(int assignInsnPos, RegisterArg arg) {
            ArgsInfo argsInfo = (ArgsInfo) this.argsList.get(assignInsnPos);
            argsInfo.inlinedInsn = this;
            return new WrapInfo(argsInfo.insn, arg);
        }

        public ArgsInfo getInlinedInsn() {
            if (this.inlinedInsn != null) {
                ArgsInfo parent = this.inlinedInsn.getInlinedInsn();
                if (parent != null) {
                    this.inlinedInsn = parent;
                }
            }
            return this.inlinedInsn;
        }

        public String toString() {
            return "ArgsInfo: |" + this.inlineBorder + " ->" + (this.inlinedInsn == null ? "-" : Integer.valueOf(this.inlinedInsn.pos)) + " " + this.args + " : " + this.insn;
        }
    }

    private static final class WrapInfo {
        private final RegisterArg arg;
        private final InsnNode insn;

        public WrapInfo(InsnNode assignInsn, RegisterArg arg) {
            this.insn = assignInsn;
            this.arg = arg;
        }

        private InsnNode getInsn() {
            return this.insn;
        }

        private RegisterArg getArg() {
            return this.arg;
        }

        public String toString() {
            return "WrapInfo: " + this.arg + " -> " + this.insn;
        }
    }

    public void visit(MethodNode mth) {
        shrinkMethod(mth);
    }

    public static void shrinkMethod(MethodNode mth) {
        if (!mth.isNoCode() && !mth.contains(AFlag.DONT_SHRINK)) {
            for (BlockNode block : mth.getBasicBlocks()) {
                shrinkBlock(mth, block);
                simplifyMoveInsns(block);
            }
        }
    }

    private static void shrinkBlock(MethodNode mth, BlockNode block) {
        if (!block.getInstructions().isEmpty()) {
            WrapInfo wrapInfo;
            InsnList insnList = new InsnList(block.getInstructions());
            int insnCount = insnList.size();
            List<ArgsInfo> argsList = new ArrayList(insnCount);
            for (int i = 0; i < insnCount; i++) {
                argsList.add(new ArgsInfo(insnList.get(i), argsList, i));
            }
            List<WrapInfo> wrapList = new ArrayList();
            for (ArgsInfo argsInfo : argsList) {
                List<RegisterArg> args = argsInfo.getArgs();
                if (!args.isEmpty()) {
                    ListIterator<RegisterArg> it = args.listIterator(args.size());
                    while (it.hasPrevious()) {
                        RegisterArg arg = (RegisterArg) it.previous();
                        SSAVar sVar = arg.getSVar();
                        if (sVar != null && ((sVar.getVariableUseCount() == 1 || arg.isThis()) && !sVar.contains(AFlag.DONT_INLINE))) {
                            InsnNode assignInsn = sVar.getAssign().getParentInsn();
                            if (!(assignInsn == null || assignInsn.contains(AFlag.DONT_INLINE))) {
                                int assignPos = insnList.getIndex(assignInsn);
                                if (assignPos != -1) {
                                    wrapInfo = argsInfo.checkInline(assignPos, arg);
                                    if (wrapInfo != null) {
                                        wrapList.add(wrapInfo);
                                    }
                                } else {
                                    BlockNode assignBlock = BlockUtils.getBlockByInsn(mth, assignInsn);
                                    if (!(assignBlock == null || assignInsn == arg.getParentInsn() || !canMoveBetweenBlocks(assignInsn, assignBlock, block, argsInfo.getInsn()))) {
                                        inline(arg, assignInsn, assignBlock);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!wrapList.isEmpty()) {
                for (WrapInfo wrapInfo2 : wrapList) {
                    inline(wrapInfo2.getArg(), wrapInfo2.getInsn(), block);
                }
            }
        }
    }

    private static boolean inline(RegisterArg arg, InsnNode insn, BlockNode block) {
        InsnNode parentInsn = arg.getParentInsn();
        if (parentInsn != null && parentInsn.getType() == InsnType.RETURN) {
            parentInsn.setSourceLine(insn.getSourceLine());
        }
        boolean replaced = arg.wrapInstruction(insn) != null;
        if (replaced) {
            InsnList.remove(block, insn);
        }
        return replaced;
    }

    private static boolean canMoveBetweenBlocks(InsnNode assignInsn, BlockNode assignBlock, BlockNode useBlock, InsnNode useInsn) {
        if (!BlockUtils.isPathExists(assignBlock, useBlock)) {
            return false;
        }
        List<RegisterArg> argsList = ArgsInfo.getArgs(assignInsn);
        BitSet args = new BitSet();
        for (RegisterArg arg : argsList) {
            args.set(arg.getRegNum());
        }
        boolean startCheck = false;
        for (InsnNode insn : assignBlock.getInstructions()) {
            if (startCheck && (!insn.canReorder() || ArgsInfo.usedArgAssign(insn, args))) {
                return false;
            }
            if (insn == assignInsn) {
                startCheck = true;
            }
        }
        Set<BlockNode> pathsBlocks = BlockUtils.getAllPathsBlocks(assignBlock, useBlock);
        pathsBlocks.remove(assignBlock);
        pathsBlocks.remove(useBlock);
        for (BlockNode block : pathsBlocks) {
            for (InsnNode insn2 : block.getInstructions()) {
                if (!insn2.canReorder()) {
                    return false;
                }
                if (ArgsInfo.usedArgAssign(insn2, args)) {
                    return false;
                }
            }
        }
        for (InsnNode insn22 : useBlock.getInstructions()) {
            if (insn22 == useInsn) {
                return true;
            }
            if (!insn22.canReorder()) {
                return false;
            }
            if (ArgsInfo.usedArgAssign(insn22, args)) {
                return false;
            }
        }
        throw new JadxRuntimeException("Can't process instruction move : " + assignBlock);
    }

    private static void simplifyMoveInsns(BlockNode block) {
        List<InsnNode> insns = block.getInstructions();
        int size = insns.size();
        for (int i = 0; i < size; i++) {
            InsnNode insn = (InsnNode) insns.get(i);
            if (insn.getType() == InsnType.MOVE) {
                InsnArg arg = insn.getArg(0);
                if (arg.isInsnWrap()) {
                    InsnNode wrapInsn = ((InsnWrapArg) arg).getWrapInsn();
                    wrapInsn.setResult(insn.getResult());
                    wrapInsn.copyAttributesFrom(insn);
                    wrapInsn.setOffset(insn.getOffset());
                    wrapInsn.remove(AFlag.WRAPPED);
                    block.getInstructions().set(i, wrapInsn);
                }
            }
        }
    }
}
