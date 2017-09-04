package jadx.core.dex.instructions;

import jadx.core.dex.nodes.InsnNode;
import jadx.core.utils.InsnUtils;

public class GotoNode extends InsnNode {
    protected int target;

    public GotoNode(int target) {
        this(InsnType.GOTO, target, 0);
    }

    protected GotoNode(InsnType type, int target, int argsCount) {
        super(type, argsCount);
        this.target = target;
    }

    public int getTarget() {
        return this.target;
    }

    public String toString() {
        return super.toString() + "-> " + InsnUtils.formatOffset(this.target);
    }
}
