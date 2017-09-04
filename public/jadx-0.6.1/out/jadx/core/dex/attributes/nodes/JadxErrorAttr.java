package jadx.core.dex.attributes.nodes;

import jadx.core.dex.attributes.AType;
import jadx.core.dex.attributes.IAttribute;
import jadx.core.utils.Utils;

public class JadxErrorAttr implements IAttribute {
    private final Throwable cause;

    public JadxErrorAttr(Throwable cause) {
        this.cause = cause;
    }

    public Throwable getCause() {
        return this.cause;
    }

    public AType<JadxErrorAttr> getType() {
        return AType.JADX_ERROR;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("JadxError: ");
        if (this.cause == null) {
            str.append("null");
        } else {
            str.append(this.cause.getClass());
            str.append(":");
            str.append(this.cause.getMessage());
            str.append("\n");
            str.append(Utils.getStackTrace(this.cause));
        }
        return str.toString();
    }
}
