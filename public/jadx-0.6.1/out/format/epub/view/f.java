package format.epub.view;

/* compiled from: ZLTextControlElement */
public class f extends g {
    private static f[] h;
    private static f[] i;
    public final byte a;
    public final boolean b;

    private static void b() {
        h = new f[256];
        i = new f[256];
    }

    public static void a() {
        h = null;
        i = null;
    }

    static f a(byte b, boolean z) {
        if (h == null || i == null) {
            b();
        }
        f[] fVarArr = z ? h : i;
        f fVar = fVarArr[b & 255];
        if (fVar != null) {
            return fVar;
        }
        fVar = new f(b, z);
        fVarArr[b & 255] = fVar;
        return fVar;
    }

    protected f(byte b, boolean z) {
        this.a = b;
        this.b = z;
    }
}
