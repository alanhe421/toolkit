package jadx.core.dex.instructions;

import jadx.core.dex.nodes.InsnNode;
import jadx.core.utils.InsnUtils;

public class IndexInsnNode extends InsnNode {
    private final Object index;

    public IndexInsnNode(InsnType type, Object index, int argCount) {
        super(type, argCount);
        this.index = index;
    }

    public Object getIndex() {
        return this.index;
    }

    public IndexInsnNode copy() {
        return (IndexInsnNode) copyCommonParams(new IndexInsnNode(this.insnType, this.index, getArgsCount()));
    }

    public boolean isSame(InsnNode obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IndexInsnNode) || !super.isSame(obj)) {
            return false;
        }
        IndexInsnNode other = (IndexInsnNode) obj;
        if (this.index != null) {
            return this.index.equals(other.index);
        }
        if (other.index != null) {
            return false;
        }
        return true;
    }

    public String toString() {
        return super.toString() + " " + InsnUtils.indexToString(this.index);
    }
}
