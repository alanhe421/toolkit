package jadx.core.dex.regions.loops;

import jadx.core.dex.nodes.InsnNode;

public final class ForLoop extends LoopType {
    private final InsnNode incrInsn;
    private final InsnNode initInsn;

    public ForLoop(InsnNode initInsn, InsnNode incrInsn) {
        this.initInsn = initInsn;
        this.incrInsn = incrInsn;
    }

    public InsnNode getInitInsn() {
        return this.initInsn;
    }

    public InsnNode getIncrInsn() {
        return this.incrInsn;
    }
}
