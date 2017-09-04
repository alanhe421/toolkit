package jadx.core.dex.instructions;

import jadx.core.dex.instructions.args.InsnArg;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.utils.InsnUtils;
import java.util.Arrays;

public class SwitchNode extends InsnNode {
    private final int def;
    private final Object[] keys;
    private final int[] targets;

    public SwitchNode(InsnArg arg, Object[] keys, int[] targets, int def) {
        super(InsnType.SWITCH, 1);
        this.keys = keys;
        this.targets = targets;
        this.def = def;
        addArg(arg);
    }

    public int getCasesCount() {
        return this.keys.length;
    }

    public Object[] getKeys() {
        return this.keys;
    }

    public int[] getTargets() {
        return this.targets;
    }

    public int getDefaultCaseOffset() {
        return this.def;
    }

    public boolean isSame(InsnNode obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SwitchNode) || !super.isSame(obj)) {
            return false;
        }
        SwitchNode other = (SwitchNode) obj;
        if (this.def == other.def && Arrays.equals(this.keys, other.keys) && Arrays.equals(this.targets, other.targets)) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder targ = new StringBuilder();
        targ.append('[');
        for (int i = 0; i < this.targets.length; i++) {
            targ.append(InsnUtils.formatOffset(this.targets[i]));
            if (i < this.targets.length - 1) {
                targ.append(", ");
            }
        }
        targ.append(']');
        return super.toString() + " k:" + Arrays.toString(this.keys) + " t:" + targ;
    }
}
