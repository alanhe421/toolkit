package format.epub.view.style;

import android.graphics.Canvas;
import android.graphics.RectF;
import format.epub.common.text.model.h;
import format.epub.view.m;
import format.epub.view.v;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ZLTextDecoratedStyle */
public abstract class b extends v {
    private int A;
    private int B;
    private int C;
    private int D;
    private int E;
    private int F;
    private int G;
    private int H;
    private int I;
    private int J;
    private format.epub.common.c.a.g.b K;
    private float L;
    private float M;
    private List<RectF> N;
    protected final a c;
    protected int d;
    protected h e;
    protected RectF f;
    private String g;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean k;
    private float l;
    private boolean m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;

    protected abstract boolean A();

    protected abstract boolean B();

    protected abstract boolean C();

    protected abstract boolean D();

    protected abstract float E();

    protected abstract int m(h hVar, int i);

    protected abstract int n(h hVar, int i);

    protected abstract int o(h hVar, int i);

    protected abstract int p(h hVar, int i);

    protected abstract int q(h hVar);

    protected abstract int q(h hVar, int i);

    protected abstract int r(h hVar, int i);

    protected abstract int s(h hVar, int i);

    protected abstract int t(h hVar, int i);

    public abstract int u(h hVar, int i);

    public abstract format.epub.common.c.a.g.b v(h hVar, int i);

    protected abstract String z();

    protected b(v vVar, m mVar) {
        if (mVar == null) {
            mVar = vVar != null ? vVar.b : m.c;
        }
        super(vVar, mVar);
        this.m = true;
        this.N = new ArrayList();
        this.c = vVar instanceof a ? (a) vVar : ((b) vVar).c;
    }

    public void a(RectF rectF) {
        if (rectF != null && rectF.left != rectF.right) {
            this.N.add(new RectF(rectF));
        }
    }

    public void a(Canvas canvas, int i, int i2, int i3, int i4, int i5, int i6, float f) {
    }

    public RectF u() {
        this.f = a(this.N);
        if (!(this.f == null || F() == 0.0f)) {
            this.f.left = this.L;
        }
        if (!(this.f == null || G() == 0.0f)) {
            this.f.right = this.M;
        }
        if (this.f != null) {
            return new RectF(this.f.left - ((float) q()), this.f.top - ((float) p()), this.f.right + ((float) r()), this.f.bottom + ((float) w()));
        }
        return null;
    }

    private RectF b(RectF rectF) {
        int m = m() + s();
        int n = n() + t();
        int v = v() + x();
        int w = w() + y();
        RectF rectF2 = new RectF();
        rectF2.left = rectF.left - ((float) m);
        rectF2.right = rectF.right + ((float) n);
        rectF2.top = rectF.top - ((float) v);
        rectF2.bottom = rectF.bottom + ((float) w);
        return rectF2;
    }

    private RectF a(List<RectF> list) {
        RectF rectF;
        float f;
        float f2;
        float f3;
        float f4 = 0.0f;
        if (this.N.size() > 0) {
            rectF = new RectF();
            RectF rectF2 = (RectF) this.N.get(0);
            f = rectF2.left;
            f2 = rectF2.top;
            f3 = rectF2.right;
            f4 = rectF2.bottom;
        } else {
            f2 = 0.0f;
            f = 0.0f;
            rectF = null;
            f3 = 0.0f;
        }
        float f5 = f;
        f = f2;
        f2 = f3;
        f3 = f4;
        for (RectF rectF22 : this.N) {
            float f6 = rectF22.left;
            float f7 = rectF22.top;
            float f8 = rectF22.right;
            f4 = rectF22.bottom;
            f5 = Math.min(f5, f6);
            f = Math.min(f, f7);
            f2 = Math.max(f2, f8);
            f3 = Math.max(f3, f4);
        }
        if (rectF == null) {
            return rectF;
        }
        rectF.left = f5;
        rectF.top = f;
        rectF.right = f2;
        rectF.bottom = f3;
        return b(rectF);
    }

    private void H() {
        this.g = z();
        this.h = A();
        this.i = B();
        this.j = C();
        this.k = D();
        this.l = E();
        this.m = false;
    }

    public void p(h hVar) {
        this.e = hVar;
        this.d = q(hVar);
        this.p = m(hVar, this.d);
        this.q = n(hVar, this.d);
        this.r = o(hVar, this.d);
        this.s = p(hVar, this.d);
        this.t = q(hVar, this.d);
        this.I = r(hVar, this.d);
        this.H = s(hVar, this.d);
        this.u = t(hVar, this.d);
        this.z = a(hVar, this.d);
        this.A = b(hVar, this.d);
        this.B = c(hVar, this.d);
        this.C = d(hVar, this.d);
        this.v = l(hVar, this.d);
        this.w = j(hVar, this.d);
        this.x = i(hVar, this.d);
        this.y = k(hVar, this.d);
        this.G = e(hVar, this.d);
        this.E = f(hVar, this.d);
        this.D = g(hVar, this.d);
        this.F = h(hVar, this.d);
        this.J = u(hVar, this.d);
        this.K = v(hVar, this.d);
        this.o = (this.y + this.C) + this.F;
        this.n = (this.x + this.B) + this.D;
    }

    public int a(h hVar, int i) {
        return 0;
    }

    public int b(h hVar, int i) {
        return 0;
    }

    public int c(h hVar, int i) {
        return 0;
    }

    public int d(h hVar, int i) {
        return 0;
    }

    public int e(h hVar, int i) {
        return 0;
    }

    public int f(h hVar, int i) {
        return 0;
    }

    public int g(h hVar, int i) {
        return 0;
    }

    public int h(h hVar, int i) {
        return 0;
    }

    public int i(h hVar, int i) {
        return 0;
    }

    public int j(h hVar, int i) {
        return 0;
    }

    public int k(h hVar, int i) {
        return 0;
    }

    public int l(h hVar, int i) {
        return 0;
    }

    public int m() {
        return this.z;
    }

    public int n() {
        return this.A;
    }

    public int v() {
        return this.B;
    }

    public int w() {
        return this.C;
    }

    public int q() {
        return this.v;
    }

    public int r() {
        return this.w;
    }

    public int p() {
        return this.x;
    }

    public int o() {
        return this.y;
    }

    public int s() {
        return this.G;
    }

    public int t() {
        return this.E;
    }

    public int x() {
        return this.D;
    }

    public int y() {
        return this.F;
    }

    public int l() {
        return this.J;
    }

    public format.epub.common.c.a.g.b g() {
        return this.K;
    }

    public final String a() {
        if (this.m) {
            H();
        }
        return this.g;
    }

    public final int a(h hVar) {
        if (!hVar.equals(this.e)) {
            p(hVar);
        }
        return this.d;
    }

    public final int n(h hVar) {
        if (!hVar.equals(this.e)) {
            p(hVar);
        }
        return this.n;
    }

    public final int o(h hVar) {
        if (!hVar.equals(this.e)) {
            p(hVar);
        }
        return this.o;
    }

    public final boolean d() {
        if (this.m) {
            H();
        }
        return this.h;
    }

    public final boolean c() {
        if (this.m) {
            H();
        }
        return this.i;
    }

    public final boolean e() {
        if (this.m) {
            H();
        }
        return this.j;
    }

    public final boolean f() {
        if (this.m) {
            H();
        }
        return this.k;
    }

    public final int m(h hVar) {
        if (!hVar.equals(this.e)) {
            p(hVar);
        }
        return this.p;
    }

    public final int f(h hVar) {
        if (!hVar.equals(this.e)) {
            p(hVar);
        }
        return this.q;
    }

    public final int g(h hVar) {
        if (!hVar.equals(this.e)) {
            p(hVar);
        }
        return this.r;
    }

    public final int h(h hVar) {
        if (!hVar.equals(this.e)) {
            p(hVar);
        }
        return this.s;
    }

    public final int i(h hVar) {
        if (!hVar.equals(this.e)) {
            p(hVar);
        }
        return this.t;
    }

    public int j(h hVar) {
        if (!hVar.equals(this.e)) {
            p(hVar);
        }
        return this.I;
    }

    public int k(h hVar) {
        if (!hVar.equals(this.e)) {
            p(hVar);
        }
        return this.H;
    }

    public final int l(h hVar) {
        if (!hVar.equals(this.e)) {
            p(hVar);
        }
        return this.u;
    }

    public final float j() {
        if (this.m) {
            H();
        }
        return this.l;
    }

    public float F() {
        return this.L;
    }

    public void a(float f) {
        this.L = f;
    }

    public float G() {
        return this.M;
    }

    public void b(float f) {
        this.M = f;
    }
}
