package format.epub.view.style;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import format.epub.b.b;
import format.epub.common.c.a.g;
import format.epub.common.c.a.g.a;
import format.epub.common.text.model.n;
import format.epub.common.utils.h;
import format.epub.common.utils.k;
import format.epub.options.ZLBoolean3;
import format.epub.paint.ZLPaintContext.ScalingType;
import format.epub.view.v;

/* compiled from: ZLTextExplicitlyDecoratedStyle */
public class c extends b {
    private final n g;
    private Paint h = new Paint();
    private Path i = new Path();
    private Paint j = new Paint();
    private Paint k = new Paint();
    private Paint l = new Paint();
    private Paint m = new Paint();
    private Path n = new Path();
    private Path o = new Path();
    private Path p = new Path();
    private Path q = new Path();
    private DashPathEffect r = new DashPathEffect(new float[]{10.0f, 10.0f}, 0.0f);
    private v s;

    public String z() {
        if (this.g.a(13)) {
            return this.g.h();
        }
        return this.a.a();
    }

    public k b() {
        if (this.g.a(15)) {
            try {
                return new k(h.a(this.g.i()));
            } catch (Exception e) {
            }
        }
        return this.a.b();
    }

    public c(v vVar, n nVar) {
        super(vVar, vVar.b);
        this.g = nVar;
        this.j.setStyle(Style.STROKE);
        this.j.setAntiAlias(true);
        this.k.setStyle(Style.STROKE);
        this.k.setAntiAlias(true);
        this.l.setStyle(Style.STROKE);
        this.l.setAntiAlias(true);
        this.m.setStyle(Style.STROKE);
        this.m.setAntiAlias(true);
        this.h.setAntiAlias(true);
    }

    public n H() {
        return this.g;
    }

    public int I() {
        return this.g.a;
    }

    public boolean J() {
        return this.g.p() == 1;
    }

    public void a(Canvas canvas, int i, int i2, int i3, int i4, int i5, int i6, float f) {
        if (this.f != null) {
            Object obj;
            Object obj2;
            Bitmap a;
            byte f2;
            this.i.reset();
            this.n.reset();
            this.o.reset();
            this.p.reset();
            this.q.reset();
            if (this.g.a(22)) {
                this.h.setColor(h.a(new k(h.a(this.g.q()))));
                this.h.setStyle(Style.FILL);
                obj = 1;
            } else {
                obj = null;
            }
            if (this.g.a(24)) {
                obj2 = 1;
                a = ((b) this.g.t()).a(i, i2, ScalingType.FILLSCREEN);
            } else {
                a = null;
                obj2 = null;
            }
            float f3 = this.f.top;
            float f4 = this.f.bottom;
            this.f.top = f3 + f;
            this.f.bottom = f4 + f;
            if (I() == 2) {
                this.f.left = 0.0f;
                this.f.top = 0.0f;
                this.f.bottom = (float) i2;
                this.f.right = (float) i;
            } else {
                if (this.f.bottom > ((float) (i2 - i5))) {
                    this.f.bottom = (float) (i2 - i5);
                }
                if (this.f.top < ((float) i3)) {
                    this.f.top = (float) i3;
                }
            }
            float f5 = this.f.left;
            float f6 = this.f.top;
            float f7 = this.f.right;
            float f8 = this.f.bottom;
            a aVar = this.g.f[0];
            a aVar2 = this.g.f[1];
            a aVar3 = this.g.f[2];
            a aVar4 = this.g.f[3];
            int c = this.g.c(18, this.e, this.d);
            this.n.moveTo(((float) c) + f5, f6);
            this.q.moveTo(f5, ((float) c) + f6);
            this.i.moveTo(((float) c) + f5, f6);
            if (c > 0) {
                this.n.addArc(new RectF(f5, f6, ((float) (c * 2)) + f5, ((float) (c * 2)) + f6), 225.0f, 45.0f);
            }
            int c2 = this.g.c(19, this.e, this.d);
            this.n.lineTo(f7 - ((float) c2), f6);
            this.i.lineTo(f7 - ((float) c2), f6);
            this.o.moveTo(f7, ((float) c2) + f6);
            if (c2 > 0) {
                this.n.addArc(new RectF(f7 - ((float) (c2 * 2)), f6, f7, ((float) (c2 * 2)) + f6), 270.0f, 45.0f);
                this.o.addArc(new RectF(f7 - ((float) (c2 * 2)), f6, f7, ((float) (c2 * 2)) + f6), 315.0f, 45.0f);
                this.i.arcTo(new RectF(f7 - ((float) (c2 * 2)), f6, f7, ((float) (c2 * 2)) + f6), 270.0f, 90.0f);
            }
            c2 = this.g.c(20, this.e, this.d);
            this.o.lineTo(f7, f8 - ((float) c2));
            this.i.lineTo(f7, f8 - ((float) c2));
            this.p.moveTo(f7 - ((float) c2), f8);
            if (c2 > 0) {
                this.o.addArc(new RectF(f7 - ((float) (c2 * 2)), f8 - ((float) (c2 * 2)), f7, f8), 0.0f, 45.0f);
                this.p.addArc(new RectF(f7 - ((float) (c2 * 2)), f8 - ((float) (c2 * 2)), f7, f8), 45.0f, 45.0f);
                this.i.arcTo(new RectF(f7 - ((float) (c2 * 2)), f8 - ((float) (c2 * 2)), f7, f8), 0.0f, 90.0f);
            }
            int c3 = this.g.c(21, this.e, this.d);
            this.p.lineTo(((float) c3) + f5, f8);
            this.i.lineTo(((float) c3) + f5, f8);
            this.q.lineTo(f5, f8 - ((float) c3));
            if (c > 0) {
                this.q.addArc(new RectF(f5, f6, ((float) (c * 2)) + f5, ((float) (c * 2)) + f6), 180.0f, 45.0f);
            }
            if (c3 > 0) {
                this.p.addArc(new RectF(f5, f8 - ((float) (c3 * 2)), ((float) (c3 * 2)) + f5, f8), 90.0f, 45.0f);
                this.q.addArc(new RectF(f5, f8 - ((float) (c3 * 2)), ((float) (c3 * 2)) + f5, f8), 135.0f, 45.0f);
                this.i.arcTo(new RectF(f5, f8 - ((float) (c3 * 2)), ((float) (c3 * 2)) + f5, f8), 90.0f, 90.0f);
            }
            this.i.lineTo(f5, ((float) c) + f6);
            if (c > 0) {
                this.i.arcTo(new RectF(f5, f6, ((float) (c * 2)) + f5, ((float) (c * 2)) + f6), 180.0f, 90.0f);
            }
            if (obj != null) {
                canvas.drawPath(this.i, this.h);
            }
            if (!(obj2 == null || a == null)) {
                Rect a2 = format.epub.paint.a.a(a, i, i2, 0, i2, ScalingType.FILLSCREEN);
                a2.top = (i2 - a2.bottom) / 2;
                a2.bottom += (i2 - a2.bottom) / 2;
                canvas.drawBitmap(a, null, a2, this.h);
            }
            int x = x();
            if (x > 0) {
                this.j.setStrokeWidth((float) x);
                f2 = this.g.f(18);
                if (f2 != format.epub.common.text.model.c.a) {
                    if (f2 == format.epub.common.text.model.c.d) {
                        this.j.setPathEffect(this.r);
                    } else {
                        this.j.setPathEffect(null);
                    }
                    this.j.setColor(h.a(new k(h.a(aVar.f))));
                    canvas.drawPath(this.n, this.j);
                }
            }
            x = t();
            if (x > 0) {
                this.k.setStrokeWidth((float) x);
                f2 = this.g.f(19);
                if (f2 != format.epub.common.text.model.c.a) {
                    if (f2 == format.epub.common.text.model.c.d) {
                        this.k.setPathEffect(this.r);
                    } else {
                        this.k.setPathEffect(null);
                    }
                    this.k.setColor(h.a(new k(h.a(aVar2.f))));
                    canvas.drawPath(this.o, this.k);
                }
            }
            x = y();
            if (x > 0) {
                this.l.setStrokeWidth((float) x);
                f2 = this.g.f(20);
                if (f2 != format.epub.common.text.model.c.a) {
                    if (f2 == format.epub.common.text.model.c.d) {
                        this.l.setPathEffect(this.r);
                    } else {
                        this.l.setPathEffect(null);
                    }
                    this.l.setColor(h.a(new k(h.a(aVar3.f))));
                    canvas.drawPath(this.p, this.l);
                }
            }
            x = s();
            if (x > 0) {
                this.m.setStrokeWidth((float) x);
                f2 = this.g.f(21);
                if (f2 != format.epub.common.text.model.c.a) {
                    if (f2 == format.epub.common.text.model.c.d) {
                        this.m.setPathEffect(this.r);
                    } else {
                        this.m.setPathEffect(null);
                    }
                    this.m.setColor(h.a(new k(h.a(aVar4.f))));
                    canvas.drawPath(this.q, this.m);
                }
            }
            this.f.top = f3;
            this.f.bottom = f4;
        }
    }

    private v K() {
        if (this.g.a == (short) 0) {
            return this.a.a;
        }
        v vVar = this.a;
        int i = 0;
        while (vVar != vVar.a) {
            int i2;
            if (!(vVar instanceof c)) {
                i2 = i + 1;
                if (i2 > 1) {
                    return vVar;
                }
            } else if (((c) vVar).g.a != this.g.a) {
                return vVar;
            } else {
                i2 = i;
            }
            vVar = vVar.a;
            i = i2;
        }
        return vVar;
    }

    private v L() {
        if (this.s == null) {
            this.s = K();
        }
        return this.s;
    }

    protected int q(format.epub.common.text.model.h hVar) {
        if ((this.g instanceof format.epub.common.text.model.a.a) && !this.c.e.a()) {
            return this.a.a(hVar);
        }
        int a = L().a(hVar);
        if (this.g.a(14)) {
            if (this.g.d((byte) 32) == ZLBoolean3.B3_TRUE) {
                return a;
            }
            if (this.g.d(Byte.MIN_VALUE) == ZLBoolean3.B3_TRUE) {
                return (a * 120) / 100;
            }
            if (this.g.d((byte) 64) == ZLBoolean3.B3_TRUE) {
                return (a * 100) / 120;
            }
        }
        if (this.g.a(9)) {
            return this.g.a(9, hVar, a);
        }
        return this.a.a(hVar);
    }

    protected boolean B() {
        switch (this.g.d((byte) 1)) {
            case B3_TRUE:
                return true;
            case B3_FALSE:
                return false;
            default:
                return this.a.c();
        }
    }

    protected boolean A() {
        switch (this.g.d((byte) 2)) {
            case B3_TRUE:
                return true;
            case B3_FALSE:
                return false;
            default:
                return this.a.d();
        }
    }

    protected boolean C() {
        switch (this.g.d((byte) 4)) {
            case B3_TRUE:
                return true;
            case B3_FALSE:
                return false;
            default:
                return this.a.e();
        }
    }

    protected boolean D() {
        switch (this.g.d((byte) 8)) {
            case B3_TRUE:
                return true;
            case B3_FALSE:
                return false;
            default:
                return this.a.f();
        }
    }

    public int n(format.epub.common.text.model.h hVar, int i) {
        if ((this.g instanceof format.epub.common.text.model.a.a) && !this.c.d.a()) {
            return this.a.f(hVar);
        }
        if (this.g.a(2)) {
            return L().f(hVar) + this.g.a(2, hVar, i);
        }
        return this.a.f(hVar);
    }

    public int o(format.epub.common.text.model.h hVar, int i) {
        if ((this.g instanceof format.epub.common.text.model.a.a) && !this.c.d.a()) {
            return this.a.g(hVar);
        }
        if (this.g.a(3)) {
            return L().g(hVar) + this.g.a(3, hVar, i);
        }
        return this.a.g(hVar);
    }

    public int p(format.epub.common.text.model.h hVar, int i) {
        if ((this.g instanceof format.epub.common.text.model.a.a) && !this.c.d.a()) {
            return this.a.h(hVar);
        }
        if (this.g.a(0)) {
            return L().h(hVar) + this.g.a(0, hVar, i);
        }
        return this.a.h(hVar);
    }

    public int q(format.epub.common.text.model.h hVar, int i) {
        if ((this.g instanceof format.epub.common.text.model.a.a) && !this.c.d.a()) {
            return this.a.i(hVar);
        }
        if (this.g.a(1)) {
            return L().i(hVar) + this.g.a(1, hVar, i);
        }
        return this.a.i(hVar);
    }

    protected int t(format.epub.common.text.model.h hVar, int i) {
        if ((this.g instanceof format.epub.common.text.model.a.a) && !this.c.d.a()) {
            return this.a.l(hVar);
        }
        if (this.g.a(4)) {
            return this.g.a(4, hVar, i);
        }
        return this.a.l(hVar);
    }

    protected float E() {
        return this.a.j();
    }

    protected int m(format.epub.common.text.model.h hVar, int i) {
        if (this.g.a(10)) {
            return this.g.a(10, hVar, i);
        }
        if (!this.g.a(16)) {
            return this.a.m(hVar);
        }
        switch (this.g.j()) {
            case 0:
                return n.a(new n.a((short) -50, (byte) 2), hVar, i, 10);
            case 1:
                return n.a(new n.a((short) 50, (byte) 2), hVar, i, 10);
            default:
                return this.a.m(hVar);
        }
    }

    public boolean a(int i) {
        if (this.g.a(i)) {
            return true;
        }
        return false;
    }

    public byte k() {
        if (!(this.g instanceof format.epub.common.text.model.a.a) || this.c.c.a()) {
            return this.g.a(12) ? this.g.g() : this.a.k();
        } else {
            return this.a.k();
        }
    }

    public int a(format.epub.common.text.model.h hVar, int i) {
        if (this.g.a(0)) {
            return this.g.a(0, hVar, i);
        }
        return 0;
    }

    public int b(format.epub.common.text.model.h hVar, int i) {
        if (this.g.a(1)) {
            return this.g.a(1, hVar, i);
        }
        return 0;
    }

    public int c(format.epub.common.text.model.h hVar, int i) {
        if (this.g.a(7)) {
            return this.g.a(7, hVar, i);
        }
        return 0;
    }

    public int d(format.epub.common.text.model.h hVar, int i) {
        if (this.g.a(8)) {
            return this.g.a(8, hVar, i);
        }
        return 0;
    }

    public int e(format.epub.common.text.model.h hVar, int i) {
        if (this.g.a(21)) {
            return this.g.b(21, hVar, i);
        }
        return 0;
    }

    public int f(format.epub.common.text.model.h hVar, int i) {
        if (this.g.a(19)) {
            return this.g.b(19, hVar, i);
        }
        return 0;
    }

    public int g(format.epub.common.text.model.h hVar, int i) {
        if (this.g.a(18)) {
            return this.g.b(18, hVar, i);
        }
        return 0;
    }

    public int h(format.epub.common.text.model.h hVar, int i) {
        if (this.g.a(20)) {
            return this.g.b(20, hVar, i);
        }
        return 0;
    }

    public int i(format.epub.common.text.model.h hVar, int i) {
        if (this.g.a(5)) {
            return this.g.a(5, hVar, i);
        }
        return 0;
    }

    public int j(format.epub.common.text.model.h hVar, int i) {
        if (this.g.a(3)) {
            return this.g.a(3, hVar, i);
        }
        return 0;
    }

    public int k(format.epub.common.text.model.h hVar, int i) {
        if (this.g.a(6)) {
            return this.g.a(6, hVar, i);
        }
        return 0;
    }

    public int l(format.epub.common.text.model.h hVar, int i) {
        if (this.g.a(2)) {
            return this.g.a(2, hVar, i);
        }
        return 0;
    }

    protected int r(format.epub.common.text.model.h hVar, int i) {
        if (this.g.a(21)) {
            return L().j(hVar) + this.g.b(21, hVar, i);
        }
        return this.a.j(hVar);
    }

    protected int s(format.epub.common.text.model.h hVar, int i) {
        if (this.g.a(19)) {
            return L().k(hVar) + this.g.b(19, hVar, i);
        }
        return this.a.k(hVar);
    }

    public int u(format.epub.common.text.model.h hVar, int i) {
        int v = this.g.v();
        if (v == 54 && this.g.e() == (byte) 0) {
            return 0;
        }
        if (this.g.a(11)) {
            int l = L().l();
            v = hVar.b;
            if (l != 0) {
                hVar.b = l;
            }
            l = this.g.a(11, hVar, i);
            hVar.b = v;
            return l;
        } else if (v != 53 || this.g.e() == (byte) 0) {
            return L().l();
        } else {
            Log.e("style", "textKind : " + v);
            return 0;
        }
    }

    public final int h() {
        if (this.g.e() != (byte) 0) {
            return (q() + s()) + m();
        }
        return ((L().q() + L().s()) + L().m()) + ((q() + s()) + m());
    }

    public final int i() {
        if (this.g.e() != (byte) 0) {
            return (r() + t()) + n();
        }
        return ((L().r() + L().t()) + L().n()) + ((r() + t()) + n());
    }

    public float F() {
        if (this.g.v() == 53) {
            return 0.0f;
        }
        return super.F();
    }

    public float G() {
        if (this.g.v() == 53) {
            return 0.0f;
        }
        return super.G();
    }

    public g.b v(format.epub.common.text.model.h hVar, int i) {
        if (!this.g.a(25)) {
            return null;
        }
        g.b o = this.g.o();
        if (o == null) {
            return o;
        }
        o.h = n.a(new n.a(o.a, o.b), hVar, i, 9);
        o.i = n.a(new n.a(o.c, o.d), hVar, i, 9);
        o.j = n.a(new n.a(o.e, o.f), hVar, i, 9);
        o.k = h.a(new k(h.a(o.g)));
        return o;
    }
}
