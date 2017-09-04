package format.epub.view;

/* compiled from: ZLTextFixedHSpaceElement */
public class j extends g {
    private static final g[] b = new g[20];
    public final short a;

    public static g a(short s) {
        if (s >= (short) 20) {
            return new j(s);
        }
        g gVar = b[s];
        if (gVar != null) {
            return gVar;
        }
        gVar = new j(s);
        b[s] = gVar;
        return gVar;
    }

    private j(short s) {
        this.a = s;
    }
}
