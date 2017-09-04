package format.epub.view;

import format.epub.common.c.a.g.b;
import format.epub.common.text.model.h;
import format.epub.common.utils.k;

/* compiled from: ZLTextStyle */
public abstract class v {
    public final v a;
    public final m b;

    public abstract int a(h hVar);

    public abstract String a();

    public abstract boolean c();

    public abstract boolean d();

    public abstract boolean e();

    public abstract int f(h hVar);

    public abstract boolean f();

    public abstract int g(h hVar);

    public abstract int h(h hVar);

    public abstract int i(h hVar);

    public abstract float j();

    public abstract int j(h hVar);

    public abstract byte k();

    public abstract int k(h hVar);

    public abstract int l();

    public abstract int l(h hVar);

    public abstract int m(h hVar);

    public abstract int n(h hVar);

    public abstract int o(h hVar);

    protected v(v vVar, m mVar) {
        if (vVar == null) {
            vVar = this;
        }
        this.a = vVar;
        this.b = mVar;
    }

    public k b() {
        return null;
    }

    public b g() {
        return null;
    }

    public int h() {
        return 0;
    }

    public int i() {
        return 0;
    }

    public final int b(h hVar) {
        int h;
        int i = 0;
        if (this.a != this) {
            h = this.a.h(hVar);
        } else {
            h = 0;
        }
        if (this.a != this) {
            i = this.a.j(hVar);
        }
        return (h + f(hVar)) + i;
    }

    public final int c(h hVar) {
        int i;
        int i2 = 0;
        if (this.a != this) {
            i = this.a.i(hVar);
        } else {
            i = 0;
        }
        if (this.a != this) {
            i2 = this.a.k(hVar);
        }
        return (i + g(hVar)) + i2;
    }

    public final int d(h hVar) {
        return (f(hVar) + h(hVar)) + j(hVar);
    }

    public final int e(h hVar) {
        return (g(hVar) + i(hVar)) + k(hVar);
    }

    public void a(float f) {
    }

    public void b(float f) {
    }

    public int m() {
        return 0;
    }

    public int n() {
        return 0;
    }

    public int o() {
        return 0;
    }

    public int p() {
        return 0;
    }

    public int q() {
        return 0;
    }

    public int r() {
        return 0;
    }

    public int s() {
        return 0;
    }

    public int t() {
        return 0;
    }

    public boolean a(int i) {
        return false;
    }
}
