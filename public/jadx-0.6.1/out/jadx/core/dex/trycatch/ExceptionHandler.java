package jadx.core.dex.trycatch;

import jadx.core.Consts;
import jadx.core.dex.info.ClassInfo;
import jadx.core.dex.instructions.args.InsnArg;
import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.IContainer;
import jadx.core.utils.InsnUtils;
import java.util.ArrayList;
import java.util.List;

public class ExceptionHandler {
    private InsnArg arg;
    private final List<BlockNode> blocks = new ArrayList();
    private final ClassInfo catchType;
    private final int handleOffset;
    private BlockNode handlerBlock;
    private IContainer handlerRegion;
    private boolean isFinally;
    private TryCatchBlock tryBlock;

    public ExceptionHandler(int addr, ClassInfo type) {
        this.handleOffset = addr;
        this.catchType = type;
    }

    public ClassInfo getCatchType() {
        return this.catchType;
    }

    public boolean isCatchAll() {
        return this.catchType == null || this.catchType.getFullName().equals(Consts.CLASS_THROWABLE);
    }

    public int getHandleOffset() {
        return this.handleOffset;
    }

    public BlockNode getHandlerBlock() {
        return this.handlerBlock;
    }

    public void setHandlerBlock(BlockNode handlerBlock) {
        this.handlerBlock = handlerBlock;
    }

    public List<BlockNode> getBlocks() {
        return this.blocks;
    }

    public void addBlock(BlockNode node) {
        this.blocks.add(node);
    }

    public IContainer getHandlerRegion() {
        return this.handlerRegion;
    }

    public void setHandlerRegion(IContainer handlerRegion) {
        this.handlerRegion = handlerRegion;
    }

    public InsnArg getArg() {
        return this.arg;
    }

    public void setArg(InsnArg arg) {
        this.arg = arg;
    }

    public void setTryBlock(TryCatchBlock tryBlock) {
        this.tryBlock = tryBlock;
    }

    public TryCatchBlock getTryBlock() {
        return this.tryBlock;
    }

    public boolean isFinally() {
        return this.isFinally;
    }

    public void setFinally(boolean isFinally) {
        this.isFinally = isFinally;
    }

    public int hashCode() {
        return (this.catchType == null ? 0 : this.catchType.hashCode() * 31) + this.handleOffset;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ExceptionHandler other = (ExceptionHandler) obj;
        if (this.catchType == null) {
            if (other.catchType != null) {
                return false;
            }
        } else if (!this.catchType.equals(other.catchType)) {
            return false;
        }
        if (this.handleOffset != other.handleOffset) {
            z = false;
        }
        return z;
    }

    public String toString() {
        return (this.catchType == null ? "all" : this.catchType.getShortName()) + " -> " + InsnUtils.formatOffset(this.handleOffset);
    }
}
