package jadx.core.dex.regions.conditions;

import jadx.core.dex.nodes.BlockNode;
import java.util.HashSet;
import java.util.Set;

public final class IfInfo {
    private final IfCondition condition;
    private final BlockNode elseBlock;
    @Deprecated
    private BlockNode ifBlock;
    private final Set<BlockNode> mergedBlocks;
    private BlockNode outBlock;
    private final Set<BlockNode> skipBlocks;
    private final BlockNode thenBlock;

    public IfInfo(IfCondition condition, BlockNode thenBlock, BlockNode elseBlock) {
        this(condition, thenBlock, elseBlock, new HashSet(), new HashSet());
    }

    public IfInfo(IfInfo info, BlockNode thenBlock, BlockNode elseBlock) {
        this(info.getCondition(), thenBlock, elseBlock, info.getMergedBlocks(), info.getSkipBlocks());
    }

    private IfInfo(IfCondition condition, BlockNode thenBlock, BlockNode elseBlock, Set<BlockNode> mergedBlocks, Set<BlockNode> skipBlocks) {
        this.condition = condition;
        this.thenBlock = thenBlock;
        this.elseBlock = elseBlock;
        this.mergedBlocks = mergedBlocks;
        this.skipBlocks = skipBlocks;
    }

    public static IfInfo invert(IfInfo info) {
        IfInfo tmpIf = new IfInfo(IfCondition.invert(info.getCondition()), info.getElseBlock(), info.getThenBlock(), info.getMergedBlocks(), info.getSkipBlocks());
        tmpIf.setIfBlock(info.getIfBlock());
        return tmpIf;
    }

    public void merge(IfInfo... arr) {
        for (IfInfo info : arr) {
            this.mergedBlocks.addAll(info.getMergedBlocks());
            this.skipBlocks.addAll(info.getSkipBlocks());
        }
    }

    public IfCondition getCondition() {
        return this.condition;
    }

    public Set<BlockNode> getMergedBlocks() {
        return this.mergedBlocks;
    }

    public Set<BlockNode> getSkipBlocks() {
        return this.skipBlocks;
    }

    public BlockNode getThenBlock() {
        return this.thenBlock;
    }

    public BlockNode getElseBlock() {
        return this.elseBlock;
    }

    public BlockNode getOutBlock() {
        return this.outBlock;
    }

    public void setOutBlock(BlockNode outBlock) {
        this.outBlock = outBlock;
    }

    public BlockNode getIfBlock() {
        return this.ifBlock;
    }

    public void setIfBlock(BlockNode ifBlock) {
        this.ifBlock = ifBlock;
    }

    public String toString() {
        return "IfInfo: then: " + this.thenBlock + ", else: " + this.elseBlock;
    }
}
