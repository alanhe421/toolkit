package jadx.core.dex.trycatch;

import jadx.core.dex.attributes.AType;
import jadx.core.dex.attributes.IAttribute;

public class ExcHandlerAttr implements IAttribute {
    private final ExceptionHandler handler;
    private final TryCatchBlock tryBlock;

    public ExcHandlerAttr(TryCatchBlock block, ExceptionHandler handler) {
        this.tryBlock = block;
        this.handler = handler;
    }

    public AType<ExcHandlerAttr> getType() {
        return AType.EXC_HANDLER;
    }

    public TryCatchBlock getTryBlock() {
        return this.tryBlock;
    }

    public ExceptionHandler getHandler() {
        return this.handler;
    }

    public String toString() {
        String str;
        StringBuilder append = new StringBuilder().append("ExcHandler: ");
        if (this.handler.isFinally()) {
            str = " FINALLY";
        } else {
            str = (this.handler.isCatchAll() ? "all" : this.handler.getCatchType()) + " " + this.handler.getArg();
        }
        return append.append(str).toString();
    }
}
