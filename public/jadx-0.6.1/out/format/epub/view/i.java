package format.epub.view;

import java.util.List;

/* compiled from: ZLTextElementRegion */
public abstract class i {
    public static a a = new a() {
        public boolean a(i iVar) {
            return true;
        }
    };
    public static a b = new a() {
        public boolean a(i iVar) {
            return iVar instanceof o;
        }
    };
    public static a c = new a() {
        public boolean a(i iVar) {
            return (iVar instanceof q) || (iVar instanceof o);
        }
    };
    private final List<h> d;
    private final int e;
    private int f;
    private l g;

    /* compiled from: ZLTextElementRegion */
    public interface a {
        boolean a(i iVar);
    }

    i(List<h> list, int i) {
        this.d = list;
        this.e = i;
        this.f = i + 1;
    }

    void a() {
        this.f++;
        this.g = null;
    }

    private List<h> b() {
        return this.d.subList(this.e, this.f);
    }

    private l c() {
        if (this.g == null) {
            this.g = new l(b());
        }
        return this.g;
    }

    float a(float f, float f2) {
        return c().a(f, f2);
    }
}
