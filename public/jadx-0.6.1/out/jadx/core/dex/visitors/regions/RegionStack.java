package jadx.core.dex.visitors.regions;

import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.IRegion;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.utils.exceptions.JadxOverflowException;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class RegionStack {
    private static final boolean DEBUG = false;
    private static final Logger LOG = LoggerFactory.getLogger(RegionStack.class);
    private static final int REGIONS_STACK_LIMIT = 1000;
    private State curState = new State();
    private final Deque<State> stack = new ArrayDeque();

    private static final class State {
        final Set<BlockNode> exits;
        IRegion region;

        public State() {
            this.exits = new HashSet(4);
        }

        private State(State c) {
            this.exits = new HashSet(c.exits);
        }

        public State copy() {
            return new State(this);
        }

        public String toString() {
            return "Region: " + this.region + ", exits: " + this.exits;
        }
    }

    public RegionStack(MethodNode mth) {
    }

    public void push(IRegion region) {
        this.stack.push(this.curState);
        if (this.stack.size() > REGIONS_STACK_LIMIT) {
            throw new JadxOverflowException("Regions stack size limit reached");
        }
        this.curState = this.curState.copy();
        this.curState.region = region;
    }

    public void pop() {
        this.curState = (State) this.stack.pop();
    }

    public void addExit(BlockNode exit) {
        if (exit != null) {
            this.curState.exits.add(exit);
        }
    }

    public void addExits(Collection<BlockNode> exits) {
        for (BlockNode exit : exits) {
            addExit(exit);
        }
    }

    public void removeExit(BlockNode exit) {
        if (exit != null) {
            this.curState.exits.remove(exit);
        }
    }

    public boolean containsExit(BlockNode exit) {
        return this.curState.exits.contains(exit);
    }

    public IRegion peekRegion() {
        return this.curState.region;
    }

    public int size() {
        return this.stack.size();
    }

    public String toString() {
        return "Region stack size: " + size() + ", last: " + this.curState;
    }
}
