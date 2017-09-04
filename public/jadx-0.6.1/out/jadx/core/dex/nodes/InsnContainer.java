package jadx.core.dex.nodes;

import jadx.core.dex.attributes.AttrNode;
import java.util.List;

public class InsnContainer extends AttrNode implements IBlock {
    private final List<InsnNode> insns;

    public InsnContainer(List<InsnNode> insns) {
        this.insns = insns;
    }

    public List<InsnNode> getInstructions() {
        return this.insns;
    }

    public String baseString() {
        return Integer.toString(this.insns.size());
    }

    public String toString() {
        return "InsnContainer:" + this.insns.size();
    }
}
