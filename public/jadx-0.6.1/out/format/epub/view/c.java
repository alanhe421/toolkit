package format.epub.view;

import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.c.a;
import com.qq.reader.common.utils.ao;
import format.epub.common.text.model.h;
import format.epub.paint.ZLPaintContext;
import format.epub.paint.ZLPaintContext.ScalingType;
import format.epub.view.style.e;
import format.epub.view.style.f;

/* compiled from: QEPubRenderKit */
public class c {
    protected ZLPaintContext a;
    private v b;
    private h c;
    private float d = -1.0f;
    private float e = -1.0f;
    private char[] f = new char[20];

    public c(ZLPaintContext zLPaintContext) {
        this.a = zLPaintContext;
    }

    public float a() {
        return (this.a.o() * f.a().c().j()) / 100.0f;
    }

    public void b() {
        this.c = null;
        this.d = -1.0f;
        this.e = -1.0f;
    }

    public void c() {
        if (this.a != null) {
            this.a.s();
        }
    }

    public void d() {
        if (this.a != null) {
            this.a.t();
        }
    }

    public ZLPaintContext e() {
        return this.a;
    }

    public h f() {
        if (this.c == null) {
            this.c = new h(a.bY, (a.bU - this.a.k()) - this.a.l(), a.bT, f.a().c().u());
        }
        return this.c;
    }

    final void g() {
        a(f.a().c());
    }

    public void a(v vVar) {
        if (this.b != vVar) {
            this.b = vVar;
            this.d = -1.0f;
            this.e = -1.0f;
        }
        this.a.b(vVar.a(), vVar.a(f()), vVar.c(), vVar.d(), vVar.e(), vVar.f(), vVar.g());
    }

    final v h() {
        return this.b;
    }

    boolean a(g gVar) {
        return gVar == g.f || gVar == g.g || (gVar instanceof x) || (gVar instanceof f);
    }

    void b(g gVar) {
        if (gVar == g.f || gVar == g.g) {
            k();
        } else if (gVar instanceof x) {
            a((x) gVar);
        } else if (gVar instanceof f) {
            a((f) gVar);
        }
    }

    void a(s sVar, int i, int i2) {
        while (i != i2) {
            b(sVar.c(i));
            i++;
        }
    }

    private void a(x xVar) {
        a(new format.epub.view.style.c(this.b, xVar.a));
    }

    private void k() {
        a(this.b.a);
    }

    void a(f fVar) {
        if (fVar.b) {
            e a = f.a().a(fVar.a);
            if (d.B.equals(ao.b)) {
                a.b.c(f.a().c().n.c());
            } else {
                a.b.c(ao.b);
            }
            m mVar = fVar instanceof n ? ((n) fVar).h : null;
            if (a != null) {
                a(new format.epub.view.style.d(this.b, a, mVar));
                return;
            }
            return;
        }
        a(this.b.a);
    }

    public final float a(g gVar, int i) {
        if (gVar instanceof y) {
            return a((y) gVar, i);
        }
        if (gVar instanceof p) {
            return (float) this.a.a((p) gVar, f(), a((p) gVar), h());
        }
        if (gVar == g.e) {
            return (float) this.b.l(f());
        }
        if (gVar instanceof j) {
            return this.a.n() * ((float) ((j) gVar).a);
        }
        return 0.0f;
    }

    public static ScalingType a(p pVar) {
        ScalingType scalingType = ScalingType.FitMaximum;
        if (pVar.j) {
            return ScalingType.FULLSCREEN;
        }
        if (pVar.n != null || pVar.o != null || (pVar.b() && pVar.c())) {
            return ScalingType.SCALEWIDTH;
        }
        if (pVar.i) {
            return ScalingType.FitMaximum;
        }
        return ScalingType.IntegerCoefficient;
    }

    final float c(g gVar) {
        if (gVar instanceof y) {
            return l();
        }
        if (gVar instanceof p) {
            return (float) this.a.a((p) gVar, f(), a((p) gVar));
        }
        return 0.0f;
    }

    final float d(g gVar) {
        if (gVar instanceof y) {
            return m();
        }
        if (gVar instanceof p) {
            return (float) this.a.a((p) gVar, f(), a((p) gVar));
        }
        return 0.0f;
    }

    final float a(y yVar, int i) {
        return i == 0 ? yVar.a(this.a) : this.a.a(yVar.a, yVar.b + i, yVar.h - i);
    }

    final float a(y yVar, int i, int i2, boolean z) {
        if (i2 == -1) {
            if (i == 0) {
                return yVar.a(this.a);
            }
            i2 = yVar.h - i;
        }
        if (!z) {
            return this.a.a(yVar.a, yVar.b + i, i2);
        }
        char[] cArr = this.f;
        if (i2 + 1 > cArr.length) {
            cArr = new char[(i2 + 1)];
            this.f = cArr;
        }
        System.arraycopy(yVar.a, yVar.b + i, cArr, 0, i2);
        cArr[i2] = '-';
        return this.a.a(cArr, 0, i2 + 1);
    }

    private final float l() {
        if (this.d == -1.0f) {
            try {
                v vVar = this.b;
                this.d = ((float) vVar.m(f())) + ((this.a.o() * vVar.j()) / 100.0f);
            } catch (Exception e) {
                if (this.a != null) {
                    this.d = this.a.o();
                }
            }
        }
        return this.d;
    }

    private final float m() {
        if (this.e == -1.0f) {
            v vVar = this.b;
            this.e = ((float) vVar.m(f())) + ((this.a.p() * vVar.j()) / 100.0f);
        }
        return this.e;
    }

    final float e(g gVar) {
        return ((gVar instanceof y) || (gVar instanceof p)) ? this.a.q() : 0.0f;
    }

    final float f(g gVar) {
        return ((gVar instanceof y) || (gVar instanceof p)) ? this.a.r() : 0.0f;
    }

    public int i() {
        return this.a.c();
    }

    public int j() {
        return this.a.b();
    }

    public final float a(char[] cArr, int i) {
        return this.a.a(cArr, 0, i) / ((float) i);
    }

    public synchronized float a(int i, int i2, float f) {
        int j;
        int i3;
        float f2;
        a(f.a().c());
        j = j();
        i3 = i();
        f2 = ((float) i2) / ((float) i);
        return Math.min((((float) j) - (((((float) j) * 0.5f) + a(g.e, 0)) / f2)) / f, 1.2f * f2) * (((float) ((int) (((float) i3) - (((float) (h().n(f()) + h().o(f()))) / f2)))) / (l() + this.a.q()));
    }
}
