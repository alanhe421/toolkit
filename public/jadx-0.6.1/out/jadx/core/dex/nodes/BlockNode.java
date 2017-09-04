package jadx.core.dex.nodes;

import jadx.core.dex.attributes.AFlag;
import jadx.core.dex.attributes.AType;
import jadx.core.dex.attributes.AttrNode;
import jadx.core.dex.attributes.nodes.IgnoreEdgeAttr;
import jadx.core.dex.attributes.nodes.LoopInfo;
import jadx.core.utils.BlockUtils;
import jadx.core.utils.EmptyBitSet;
import jadx.core.utils.InsnUtils;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BlockNode extends AttrNode implements IBlock {
    private List<BlockNode> cleanSuccessors;
    private BitSet domFrontier;
    private List<BlockNode> dominatesOn = Collections.emptyList();
    private BitSet doms = EmptyBitSet.EMPTY;
    private int id;
    private BlockNode idom;
    private final List<InsnNode> instructions = new ArrayList(2);
    private List<BlockNode> predecessors = new ArrayList(1);
    private final int startOffset;
    private List<BlockNode> successors = new ArrayList(1);

    public BlockNode(int id, int offset) {
        this.id = id;
        this.startOffset = offset;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public List<BlockNode> getPredecessors() {
        return this.predecessors;
    }

    public List<BlockNode> getSuccessors() {
        return this.successors;
    }

    public List<BlockNode> getCleanSuccessors() {
        return this.cleanSuccessors;
    }

    public void updateCleanSuccessors() {
        this.cleanSuccessors = cleanSuccessors(this);
    }

    public void lock() {
        this.cleanSuccessors = lockList(this.cleanSuccessors);
        this.successors = lockList(this.successors);
        this.predecessors = lockList(this.predecessors);
        this.dominatesOn = lockList(this.dominatesOn);
    }

    List<BlockNode> lockList(List<BlockNode> list) {
        if (list.isEmpty()) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(list);
    }

    private static List<BlockNode> cleanSuccessors(BlockNode block) {
        List<BlockNode> sucList = block.getSuccessors();
        if (sucList.isEmpty()) {
            return sucList;
        }
        List<BlockNode> toRemove = new LinkedList();
        for (BlockNode b : sucList) {
            if (BlockUtils.isBlockMustBeCleared(b)) {
                toRemove.add(b);
            }
        }
        if (block.contains(AFlag.LOOP_END)) {
            for (LoopInfo loop : block.getAll(AType.LOOP)) {
                toRemove.add(loop.getStart());
            }
        }
        IgnoreEdgeAttr ignoreEdgeAttr = (IgnoreEdgeAttr) block.get(AType.IGNORE_EDGE);
        if (ignoreEdgeAttr != null) {
            toRemove.addAll(ignoreEdgeAttr.getBlocks());
        }
        if (toRemove.isEmpty()) {
            return sucList;
        }
        List<BlockNode> result = new ArrayList(sucList);
        result.removeAll(toRemove);
        return result;
    }

    public List<InsnNode> getInstructions() {
        return this.instructions;
    }

    public int getStartOffset() {
        return this.startOffset;
    }

    public boolean isDominator(BlockNode block) {
        return this.doms.get(block.getId());
    }

    public BitSet getDoms() {
        return this.doms;
    }

    public void setDoms(BitSet doms) {
        this.doms = doms;
    }

    public BitSet getDomFrontier() {
        return this.domFrontier;
    }

    public void setDomFrontier(BitSet domFrontier) {
        this.domFrontier = domFrontier;
    }

    public BlockNode getIDom() {
        return this.idom;
    }

    public void setIDom(BlockNode idom) {
        this.idom = idom;
    }

    public List<BlockNode> getDominatesOn() {
        return this.dominatesOn;
    }

    public void addDominatesOn(BlockNode block) {
        if (this.dominatesOn.isEmpty()) {
            this.dominatesOn = new LinkedList();
        }
        this.dominatesOn.add(block);
    }

    public boolean isSynthetic() {
        return contains(AFlag.SYNTHETIC);
    }

    public boolean isReturnBlock() {
        return contains(AFlag.RETURN);
    }

    public int hashCode() {
        return this.startOffset;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BlockNode)) {
            return false;
        }
        BlockNode other = (BlockNode) obj;
        if (this.id == other.id && this.startOffset == other.startOffset) {
            return true;
        }
        return false;
    }

    public String baseString() {
        return Integer.toString(this.id);
    }

    public String toString() {
        return "B:" + this.id + ":" + InsnUtils.formatOffset(this.startOffset);
    }
}
