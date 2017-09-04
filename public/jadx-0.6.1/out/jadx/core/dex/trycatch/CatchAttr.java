package jadx.core.dex.trycatch;

import jadx.core.dex.attributes.AType;
import jadx.core.dex.attributes.IAttribute;

public class CatchAttr implements IAttribute {
    private final TryCatchBlock tryBlock;

    public CatchAttr(TryCatchBlock block) {
        this.tryBlock = block;
    }

    public AType<CatchAttr> getType() {
        return AType.CATCH_BLOCK;
    }

    public TryCatchBlock getTryBlock() {
        return this.tryBlock;
    }

    public String toString() {
        return this.tryBlock.toString();
    }
}
