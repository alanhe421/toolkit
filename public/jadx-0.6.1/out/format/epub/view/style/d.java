package format.epub.view.style;

import android.text.TextUtils;
import format.epub.common.c.a.g.b;
import format.epub.common.text.model.h;
import format.epub.view.m;
import format.epub.view.v;

/* compiled from: ZLTextNGStyle */
public class d extends b {
    private final e g;

    public d(v vVar, e eVar, m mVar) {
        super(vVar, mVar);
        this.g = eVar;
    }

    protected String z() {
        String a = this.g.b.a();
        if (TextUtils.isEmpty(a)) {
            return this.a.a();
        }
        return a;
    }

    protected int q(h hVar) {
        return this.g.a(hVar, this.a.a(hVar));
    }

    protected boolean B() {
        switch (this.g.a()) {
            case B3_TRUE:
                return true;
            case B3_FALSE:
                return false;
            default:
                return this.a.c();
        }
    }

    protected boolean A() {
        switch (this.g.b()) {
            case B3_TRUE:
                return true;
            case B3_FALSE:
                return false;
            default:
                return this.a.d();
        }
    }

    protected boolean C() {
        switch (this.g.c()) {
            case B3_TRUE:
                return true;
            case B3_FALSE:
                return false;
            default:
                return this.a.e();
        }
    }

    protected boolean D() {
        switch (this.g.d()) {
            case B3_TRUE:
                return true;
            case B3_FALSE:
                return false;
            default:
                return this.a.f();
        }
    }

    protected int n(h hVar, int i) {
        return this.g.b(hVar, this.a.f(hVar), i);
    }

    protected int o(h hVar, int i) {
        return this.g.c(hVar, this.a.g(hVar), i);
    }

    protected int p(h hVar, int i) {
        return this.g.d(hVar, this.a.h(hVar), i);
    }

    protected int q(h hVar, int i) {
        return this.g.e(hVar, this.a.i(hVar), i);
    }

    protected int t(h hVar, int i) {
        return this.g.f(hVar, this.a.l(hVar), i);
    }

    protected float E() {
        String a = this.g.o.a();
        if (a.matches("[1-9][0-9]*%")) {
            return (float) Integer.valueOf(a.substring(0, a.length() - 1)).intValue();
        }
        return this.a.j();
    }

    protected int m(h hVar, int i) {
        return this.g.a(hVar, this.a.m(hVar), i);
    }

    public byte k() {
        byte e = this.g.e();
        return e != (byte) 0 ? e : this.a.k();
    }

    public String toString() {
        return "ZLTextNGStyle[" + this.g.a + "]";
    }

    public int i(h hVar, int i) {
        return this.g.b(hVar, i);
    }

    public int j(h hVar, int i) {
        return this.g.c(hVar, i);
    }

    public int k(h hVar, int i) {
        return this.g.d(hVar, i);
    }

    public int l(h hVar, int i) {
        return this.g.e(hVar, i);
    }

    protected int r(h hVar, int i) {
        return 0;
    }

    protected int s(h hVar, int i) {
        return 0;
    }

    public int u(h hVar, int i) {
        if (this.a != this) {
            return this.a.l();
        }
        return 0;
    }

    public b v(h hVar, int i) {
        return null;
    }
}
