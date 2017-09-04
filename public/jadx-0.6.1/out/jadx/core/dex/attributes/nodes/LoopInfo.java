package jadx.core.dex.attributes.nodes;

import jadx.core.dex.attributes.AType;
import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.Edge;
import jadx.core.utils.BlockUtils;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class LoopInfo {
    private final BlockNode end;
    private int id;
    private final Set<BlockNode> loopBlocks;
    private LoopInfo parentLoop;
    private final BlockNode start;

    public LoopInfo(BlockNode start, BlockNode end) {
        this.start = start;
        this.end = end;
        this.loopBlocks = Collections.unmodifiableSet(BlockUtils.getAllPathsBlocks(start, end));
    }

    public BlockNode getStart() {
        return this.start;
    }

    public BlockNode getEnd() {
        return this.end;
    }

    public Set<BlockNode> getLoopBlocks() {
        return this.loopBlocks;
    }

    public Set<BlockNode> getExitNodes() {
        Set<BlockNode> nodes = new HashSet();
        Set<BlockNode> blocks = getLoopBlocks();
        for (BlockNode block : blocks) {
            for (BlockNode s : block.getSuccessors()) {
                if (!(blocks.contains(s) || s.contains(AType.EXC_HANDLER))) {
                    nodes.add(block);
                }
            }
        }
        return nodes;
    }

    public List<Edge> getExitEdges() {
        List<Edge> edges = new LinkedList();
        Set<BlockNode> blocks = getLoopBlocks();
        for (BlockNode block : blocks) {
            for (BlockNode s : block.getSuccessors()) {
                if (!(blocks.contains(s) || s.contains(AType.EXC_HANDLER))) {
                    edges.add(new Edge(block, s));
                }
            }
        }
        return edges;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LoopInfo getParentLoop() {
        return this.parentLoop;
    }

    public void setParentLoop(LoopInfo parentLoop) {
        this.parentLoop = parentLoop;
    }

    public String toString() {
        return "LOOP:" + this.id + ": " + this.start + "->" + this.end;
    }
}
