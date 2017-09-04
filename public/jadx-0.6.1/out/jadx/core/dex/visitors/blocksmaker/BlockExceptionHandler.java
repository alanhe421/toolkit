package jadx.core.dex.visitors.blocksmaker;

import jadx.core.dex.attributes.AFlag;
import jadx.core.dex.attributes.AType;
import jadx.core.dex.instructions.InsnType;
import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.instructions.args.InsnArg;
import jadx.core.dex.instructions.args.RegisterArg;
import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.dex.trycatch.CatchAttr;
import jadx.core.dex.trycatch.ExcHandlerAttr;
import jadx.core.dex.trycatch.ExceptionHandler;
import jadx.core.dex.trycatch.TryCatchBlock;
import jadx.core.dex.visitors.AbstractVisitor;
import jadx.core.utils.BlockUtils;
import jadx.core.utils.InstructionRemover;

public class BlockExceptionHandler extends AbstractVisitor {
    public void visit(MethodNode mth) {
        if (!mth.isNoCode()) {
            for (BlockNode block : mth.getBasicBlocks()) {
                markExceptionHandlers(block);
            }
            for (BlockNode block2 : mth.getBasicBlocks()) {
                block2.updateCleanSuccessors();
            }
            for (BlockNode block22 : mth.getBasicBlocks()) {
                processExceptionHandlers(mth, block22);
            }
            for (BlockNode block222 : mth.getBasicBlocks()) {
                processTryCatchBlocks(mth, block222);
            }
        }
    }

    private static void markExceptionHandlers(BlockNode block) {
        if (!block.getInstructions().isEmpty()) {
            InsnNode me = (InsnNode) block.getInstructions().get(0);
            ExcHandlerAttr handlerAttr = (ExcHandlerAttr) me.get(AType.EXC_HANDLER);
            if (handlerAttr != null && me.getType() == InsnType.MOVE_EXCEPTION) {
                ExceptionHandler excHandler = handlerAttr.getHandler();
                block.addAttr(handlerAttr);
                RegisterArg resArg = InsnArg.reg(me.getResult().getRegNum(), excHandler.isCatchAll() ? ArgType.THROWABLE : excHandler.getCatchType().getType());
                me.setResult(resArg);
                me.add(AFlag.DONT_INLINE);
                excHandler.setArg(resArg);
            }
        }
    }

    private static void processExceptionHandlers(MethodNode mth, BlockNode block) {
        ExcHandlerAttr handlerAttr = (ExcHandlerAttr) block.get(AType.EXC_HANDLER);
        if (handlerAttr != null) {
            ExceptionHandler excHandler = handlerAttr.getHandler();
            excHandler.addBlock(block);
            for (BlockNode node : BlockUtils.collectBlocksDominatedBy(block, block)) {
                excHandler.addBlock(node);
            }
            for (BlockNode excBlock : excHandler.getBlocks()) {
                InstructionRemover remover = new InstructionRemover(mth, excBlock);
                for (InsnNode insn : excBlock.getInstructions()) {
                    if (insn.getType() == InsnType.MONITOR_ENTER) {
                        break;
                    } else if (insn.getType() == InsnType.MONITOR_EXIT) {
                        remover.add(insn);
                    }
                }
                remover.perform();
                for (InsnNode insn2 : excBlock.getInstructions()) {
                    CatchAttr catchAttr = (CatchAttr) insn2.get(AType.CATCH_BLOCK);
                    if (catchAttr != null && (insn2.getType() == InsnType.THROW || onlyAllHandler(catchAttr.getTryBlock()))) {
                        handlerAttr.getTryBlock().merge(mth, catchAttr.getTryBlock());
                    }
                }
            }
        }
    }

    private static boolean onlyAllHandler(TryCatchBlock tryBlock) {
        if (tryBlock.getHandlersCount() == 1) {
            ExceptionHandler eh = (ExceptionHandler) tryBlock.getHandlers().iterator().next();
            if (eh.isCatchAll() || eh.isFinally()) {
                return true;
            }
        }
        return false;
    }

    private static void processTryCatchBlocks(MethodNode mth, BlockNode block) {
        CatchAttr commonCatchAttr = null;
        for (InsnNode insn : block.getInstructions()) {
            CatchAttr catchAttr = (CatchAttr) insn.get(AType.CATCH_BLOCK);
            if (catchAttr != null) {
                if (commonCatchAttr == null) {
                    commonCatchAttr = catchAttr;
                } else if (commonCatchAttr != catchAttr) {
                    commonCatchAttr = null;
                    break;
                }
            }
        }
        if (commonCatchAttr != null) {
            block.addAttr(commonCatchAttr);
            for (ExceptionHandler handler : commonCatchAttr.getTryBlock().getHandlers()) {
                connectHandler(mth, handler);
            }
        }
    }

    private static void connectHandler(MethodNode mth, ExceptionHandler handler) {
        int addr = handler.getHandleOffset();
        for (BlockNode block : mth.getBasicBlocks()) {
            ExcHandlerAttr bh = (ExcHandlerAttr) block.get(AType.EXC_HANDLER);
            if (bh != null && bh.getHandler().getHandleOffset() == addr) {
                handler.setHandlerBlock(block);
                return;
            }
        }
    }
}
