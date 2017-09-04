package jadx.core.dex.trycatch;

import jadx.core.dex.attributes.AFlag;
import jadx.core.dex.attributes.AType;
import jadx.core.dex.info.ClassInfo;
import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.utils.BlockUtils;
import jadx.core.utils.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TryCatchBlock {
    private final CatchAttr attr = new CatchAttr(this);
    private final List<ExceptionHandler> handlers = new LinkedList();
    private final List<InsnNode> insns = new ArrayList();

    public Iterable<ExceptionHandler> getHandlers() {
        return this.handlers;
    }

    public int getHandlersCount() {
        return this.handlers.size();
    }

    public boolean containsAllHandlers(TryCatchBlock tb) {
        return this.handlers.containsAll(tb.handlers);
    }

    public ExceptionHandler addHandler(MethodNode mth, int addr, ClassInfo type) {
        ExceptionHandler handler = mth.addExceptionHandler(new ExceptionHandler(addr, type));
        this.handlers.add(handler);
        handler.setTryBlock(this);
        return handler;
    }

    public void removeHandler(MethodNode mth, ExceptionHandler handler) {
        Iterator<ExceptionHandler> it = this.handlers.iterator();
        while (it.hasNext()) {
            ExceptionHandler h = (ExceptionHandler) it.next();
            if (h == handler) {
                unbindHandler(h);
                it.remove();
                break;
            }
        }
        if (this.handlers.isEmpty()) {
            removeWholeBlock(mth);
        }
    }

    private void unbindHandler(ExceptionHandler handler) {
        for (BlockNode block : handler.getBlocks()) {
            BlockUtils.skipPredSyntheticPaths(block);
            block.add(AFlag.SKIP);
            ExcHandlerAttr excHandlerAttr = (ExcHandlerAttr) block.get(AType.EXC_HANDLER);
            if (excHandlerAttr != null && excHandlerAttr.getHandler().equals(handler)) {
                block.remove(AType.EXC_HANDLER);
            }
            SplitterBlockAttr splitter = (SplitterBlockAttr) handler.getHandlerBlock().get(AType.SPLITTER_BLOCK);
            if (splitter != null) {
                splitter.getBlock().remove(AType.SPLITTER_BLOCK);
            }
        }
    }

    private void removeWholeBlock(MethodNode mth) {
        Iterator<ExceptionHandler> it = this.handlers.iterator();
        while (it.hasNext()) {
            unbindHandler((ExceptionHandler) it.next());
            it.remove();
        }
        for (InsnNode insn : this.insns) {
            insn.removeAttr(this.attr);
        }
        this.insns.clear();
        if (mth.getBasicBlocks() != null) {
            for (BlockNode block : mth.getBasicBlocks()) {
                block.removeAttr(this.attr);
            }
        }
    }

    public void addInsn(InsnNode insn) {
        this.insns.add(insn);
        insn.addAttr(this.attr);
    }

    public void removeInsn(MethodNode mth, InsnNode insn) {
        this.insns.remove(insn);
        insn.remove(AType.CATCH_BLOCK);
        if (this.insns.isEmpty()) {
            removeWholeBlock(mth);
        }
    }

    public void removeBlock(MethodNode mth, BlockNode block) {
        for (InsnNode insn : block.getInstructions()) {
            this.insns.remove(insn);
            insn.remove(AType.CATCH_BLOCK);
        }
        if (this.insns.isEmpty()) {
            removeWholeBlock(mth);
        }
    }

    public Iterable<InsnNode> getInsns() {
        return this.insns;
    }

    public CatchAttr getCatchAttr() {
        return this.attr;
    }

    public boolean merge(MethodNode mth, TryCatchBlock tryBlock) {
        if (tryBlock == this) {
            return false;
        }
        for (InsnNode insn : tryBlock.getInsns()) {
            addInsn(insn);
        }
        this.handlers.addAll(tryBlock.handlers);
        for (ExceptionHandler eh : this.handlers) {
            eh.setTryBlock(this);
        }
        tryBlock.handlers.clear();
        tryBlock.removeWholeBlock(mth);
        return true;
    }

    public int hashCode() {
        return this.handlers.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.handlers.equals(((TryCatchBlock) obj).handlers);
    }

    public String toString() {
        return "Catch:{ " + Utils.listToString(this.handlers) + " }";
    }
}
