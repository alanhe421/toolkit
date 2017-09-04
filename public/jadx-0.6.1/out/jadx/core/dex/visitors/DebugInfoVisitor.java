package jadx.core.dex.visitors;

import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.instructions.args.RegisterArg;
import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.dex.nodes.parser.DebugInfoParser;
import jadx.core.utils.BlockUtils;
import jadx.core.utils.exceptions.JadxException;

public class DebugInfoVisitor extends AbstractVisitor {
    public void visit(MethodNode mth) throws JadxException {
        int debugOffset = mth.getDebugInfoOffset();
        if (debugOffset > 0) {
            InsnNode[] insnArr = mth.getInstructions();
            new DebugInfoParser(mth, debugOffset, insnArr).process();
            if (insnArr.length != 0) {
                InsnNode[] arr$ = insnArr;
                int len$ = arr$.length;
                int i$ = 0;
                while (i$ < len$) {
                    InsnNode insn = arr$[i$];
                    if (insn != null) {
                        int line = insn.getSourceLine();
                        if (line != 0) {
                            mth.setSourceLine(line - 1);
                        }
                    } else {
                        i$++;
                    }
                }
            }
            if (!mth.getReturnType().equals(ArgType.VOID)) {
                for (BlockNode exit : mth.getExitBlocks()) {
                    InsnNode ret = BlockUtils.getLastInsn(exit);
                    if (ret != null) {
                        InsnNode oldRet = insnArr[ret.getOffset()];
                        if (oldRet != ret) {
                            RegisterArg oldArg = (RegisterArg) oldRet.getArg(0);
                            ((RegisterArg) ret.getArg(0)).mergeDebugInfo(oldArg.getType(), oldArg.getName());
                            ret.setSourceLine(oldRet.getSourceLine());
                        }
                    }
                }
            }
        }
        mth.unloadInsnArr();
    }
}
