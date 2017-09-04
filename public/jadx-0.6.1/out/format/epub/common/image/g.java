package format.epub.common.image;

import java.io.InputStream;

/* compiled from: ZLSingleImage */
public abstract class g implements b {
    private final String a;

    public abstract InputStream c();

    public g(String str) {
        this.a = str;
    }

    public final String d() {
        return this.a;
    }
}
