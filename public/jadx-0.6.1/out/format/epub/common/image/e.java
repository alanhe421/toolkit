package format.epub.common.image;

import java.io.InputStream;

/* compiled from: ZLImageProxy */
public abstract class e extends f {
    private g a;

    public abstract g a();

    public e(String str) {
        super(str);
    }

    public e() {
        this("image/auto");
    }

    public String j_() {
        b a = a();
        return a != null ? a.j_() : "image proxy";
    }

    public final InputStream c() {
        return this.a != null ? this.a.c() : null;
    }
}
