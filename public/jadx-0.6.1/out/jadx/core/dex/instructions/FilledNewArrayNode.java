package jadx.core.dex.instructions;

import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.nodes.InsnNode;
import org.jetbrains.annotations.NotNull;

public class FilledNewArrayNode extends InsnNode {
    private final ArgType elemType;

    public FilledNewArrayNode(@NotNull ArgType elemType, int size) {
        super(InsnType.FILLED_NEW_ARRAY, size);
        this.elemType = elemType;
    }

    public ArgType getElemType() {
        return this.elemType;
    }

    public ArgType getArrayType() {
        return ArgType.array(this.elemType);
    }

    public boolean isSame(InsnNode obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FilledNewArrayNode) || !super.isSame(obj)) {
            return false;
        }
        if (this.elemType != ((FilledNewArrayNode) obj).elemType) {
            return false;
        }
        return true;
    }

    public String toString() {
        return super.toString() + " elemType: " + this.elemType;
    }
}
