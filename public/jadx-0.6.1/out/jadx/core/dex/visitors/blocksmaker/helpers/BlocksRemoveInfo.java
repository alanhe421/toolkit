package jadx.core.dex.visitors.blocksmaker.helpers;

import jadx.core.dex.instructions.args.RegisterArg;
import jadx.core.dex.nodes.BlockNode;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.Nullable;

public final class BlocksRemoveInfo {
    private BlocksPair end;
    private int endSplitIndex;
    private final Set<BlocksPair> outs = new HashSet();
    private final Set<BlocksPair> processed = new HashSet();
    private final Map<RegisterArg, RegisterArg> regMap = new HashMap();
    private BlocksPair start;
    private BlockNode startPredecessor;
    private int startSplitIndex;

    public BlocksRemoveInfo(BlocksPair start) {
        this.start = start;
    }

    public Set<BlocksPair> getProcessed() {
        return this.processed;
    }

    public Set<BlocksPair> getOuts() {
        return this.outs;
    }

    public BlocksPair getStart() {
        return this.start;
    }

    public void setStart(BlocksPair start) {
        this.start = start;
    }

    public BlocksPair getEnd() {
        return this.end;
    }

    public void setEnd(BlocksPair end) {
        this.end = end;
    }

    public int getStartSplitIndex() {
        return this.startSplitIndex;
    }

    public void setStartSplitIndex(int startSplitIndex) {
        this.startSplitIndex = startSplitIndex;
    }

    public int getEndSplitIndex() {
        return this.endSplitIndex;
    }

    public void setEndSplitIndex(int endSplitIndex) {
        this.endSplitIndex = endSplitIndex;
    }

    public void setStartPredecessor(BlockNode startPredecessor) {
        this.startPredecessor = startPredecessor;
    }

    public BlockNode getStartPredecessor() {
        return this.startPredecessor;
    }

    public Map<RegisterArg, RegisterArg> getRegMap() {
        return this.regMap;
    }

    @Nullable
    public BlockNode getByFirst(BlockNode first) {
        for (BlocksPair blocksPair : this.processed) {
            if (blocksPair.getFirst() == first) {
                return blocksPair.getSecond();
            }
        }
        return null;
    }

    @Nullable
    public BlockNode getBySecond(BlockNode second) {
        for (BlocksPair blocksPair : this.processed) {
            if (blocksPair.getSecond() == second) {
                return blocksPair.getSecond();
            }
        }
        return null;
    }

    public String toString() {
        return "BRI start: " + this.start + ", end: " + this.end + ", list: " + this.processed + ", outs: " + this.outs + ", regMap: " + this.regMap + ", split: " + this.startSplitIndex;
    }
}
