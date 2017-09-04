package jadx.core.dex.instructions;

import com.android.dx.io.instructions.DecodedInstruction;
import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.instructions.args.InsnArg;
import jadx.core.dex.instructions.args.PrimitiveType;
import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.utils.BlockUtils;
import jadx.core.utils.InsnUtils;

public class IfNode extends GotoNode {
    private static final ArgType ARG_TYPE = ArgType.unknown(PrimitiveType.INT, PrimitiveType.OBJECT, PrimitiveType.ARRAY, PrimitiveType.BOOLEAN, PrimitiveType.BYTE, PrimitiveType.SHORT, PrimitiveType.CHAR);
    private BlockNode elseBlock;
    protected IfOp op;
    private BlockNode thenBlock;

    public IfNode(DecodedInstruction insn, IfOp op) {
        this(op, insn.getTarget(), InsnArg.reg(insn, 0, ARG_TYPE), insn.getRegisterCount() == 1 ? InsnArg.lit(0, ARG_TYPE) : InsnArg.reg(insn, 1, ARG_TYPE));
    }

    public IfNode(IfOp op, int targetOffset, InsnArg arg1, InsnArg arg2) {
        super(InsnType.IF, targetOffset, 2);
        this.op = op;
        addArg(arg1);
        addArg(arg2);
    }

    public IfOp getOp() {
        return this.op;
    }

    public void invertCondition() {
        this.op = this.op.invert();
        BlockNode tmp = this.thenBlock;
        this.thenBlock = this.elseBlock;
        this.elseBlock = tmp;
        this.target = this.thenBlock.getStartOffset();
    }

    public void changeCondition(IfOp op, InsnArg arg1, InsnArg arg2) {
        this.op = op;
        setArg(0, arg1);
        setArg(1, arg2);
    }

    public void initBlocks(BlockNode curBlock) {
        this.thenBlock = BlockUtils.getBlockByOffset(this.target, curBlock.getSuccessors());
        if (curBlock.getSuccessors().size() == 1) {
            this.elseBlock = this.thenBlock;
        } else {
            this.elseBlock = BlockUtils.selectOther(this.thenBlock, curBlock.getSuccessors());
        }
    }

    public BlockNode getThenBlock() {
        return this.thenBlock;
    }

    public BlockNode getElseBlock() {
        return this.elseBlock;
    }

    public boolean isSame(InsnNode obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IfNode) || !super.isSame(obj)) {
            return false;
        }
        if (this.op != ((IfNode) obj).op) {
            return false;
        }
        return true;
    }

    public String toString() {
        return InsnUtils.formatOffset(this.offset) + ": " + InsnUtils.insnTypeToString(this.insnType) + getArg(0) + " " + this.op.getSymbol() + " " + getArg(1) + "  -> " + (this.thenBlock != null ? this.thenBlock : InsnUtils.formatOffset(this.target));
    }
}
