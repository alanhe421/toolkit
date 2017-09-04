package jadx.core.dex.instructions.args;

import jadx.core.dex.attributes.AttrNode;
import jadx.core.dex.nodes.DexNode;

public abstract class Typed extends AttrNode {
    protected ArgType type;

    public ArgType getType() {
        return this.type;
    }

    public void setType(ArgType type) {
        this.type = type;
    }

    public boolean isTypeImmutable() {
        return false;
    }

    public boolean merge(DexNode dex, ArgType newType) {
        ArgType m = ArgType.merge(dex, this.type, newType);
        if (m == null || m.equals(this.type)) {
            return false;
        }
        setType(m);
        return true;
    }

    public boolean merge(DexNode dex, InsnArg arg) {
        return merge(dex, arg.getType());
    }
}
