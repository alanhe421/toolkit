package format.epub.options;

/* compiled from: ZLOption */
public abstract class f {
    private final String a;
    private final String b;
    protected boolean c = false;

    protected f(String str, String str2) {
        this.a = str.intern();
        this.b = str2.intern();
    }

    protected final String a(String str) {
        d a = d.a();
        return a != null ? a.a(this.a, this.b, str) : str;
    }

    protected final void b(String str) {
        d a = d.a();
        if (a != null) {
            a.b(this.a, this.b, str);
        }
    }

    protected final void b() {
        d a = d.a();
        if (a != null) {
            a.a(this.a, this.b);
        }
    }
}
