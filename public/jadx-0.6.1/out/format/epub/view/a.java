package format.epub.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.Log;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.readpage.j;
import com.qq.reader.module.readpage.n;
import com.qq.reader.readengine.kernel.PageIndex;
import com.qq.reader.readengine.kernel.a.d;
import com.qq.reader.readengine.kernel.a.e;
import com.tencent.feedback.proguard.R;
import com.tencent.smtt.sdk.WebView;
import format.epub.a.b;
import format.epub.common.text.model.h;
import format.epub.common.utils.ZLStyleNodeList;
import format.epub.common.utils.k;
import format.epub.options.g;
import format.epub.paint.ZLPaintContext;
import format.epub.paint.ZLPaintContext.ScalingType;
import format.epub.view.style.f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* compiled from: PagePaintContextForEpub */
public class a extends j {
    private static final char[] G = new char[]{' '};
    private Paint A = new Paint();
    private Paint B = new Paint();
    private List<r> C = new ArrayList();
    private Drawable D;
    private TextPaint E;
    private char[] F = new char[20];
    private i H;
    private boolean I = true;
    protected ZLPaintContext q;
    b r;
    format.epub.a.a s;
    private v t;
    private float u = -1.0f;
    private float v = -1.0f;
    private volatile d w;
    private final HashMap<r, r> x = new HashMap();
    private h y;
    private e z;

    public a(Context context, com.qq.reader.readengine.kernel.b bVar) {
        super(context, bVar);
        this.z = (e) bVar.b();
        if (com.qq.reader.common.c.b.n == 1) {
            this.A.setStyle(Style.STROKE);
            this.A.setColor(-65536);
        }
        if (com.qq.reader.common.c.b.o == 1) {
            this.B.setColor(-16776961);
        }
        this.D = context.getResources().getDrawable(R.drawable.new_btn_normal);
        this.E = new TextPaint();
        this.E.setAntiAlias(true);
        this.E.setColor(WebView.NIGHT_MODE_COLOR);
        this.E.setTextAlign(Align.CENTER);
    }

    public void q() {
        g gVar = f.a().c().n;
        if (com.qq.reader.appconfig.a.d.B.equals(ao.b)) {
            gVar.c(f.a().c().n.c());
        } else {
            gVar.c(ao.b);
        }
        if (this.q != null) {
            this.q.s();
        }
        A();
        this.z.n();
    }

    public void v() {
        if (this.q != null) {
            this.q.t();
        }
        this.z.o();
    }

    public void a(b bVar, format.epub.a.a aVar) {
        this.r = bVar;
        this.s = aVar;
    }

    private final float C() {
        if (this.u == -1.0f) {
            v vVar = this.t;
            this.u = ((float) vVar.m(E())) + ((this.q.o() * vVar.j()) / 100.0f);
        }
        return this.u;
    }

    private final float D() {
        if (this.v == -1.0f) {
            v vVar = this.t;
            this.v = ((float) vVar.m(E())) + ((this.q.p() * vVar.j()) / 100.0f);
        }
        return this.v;
    }

    public boolean t() {
        if (this.z.r() != null) {
            return this.z.r().a();
        }
        return false;
    }

    public int p() {
        return 0;
    }

    public void a(int i, int i2) {
        int i3;
        if (this.b != i2) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        if (!(this.a == 0 || this.a == i)) {
            i3 = 1;
        }
        super.a(i, i2);
        if (i3 != 0) {
            this.q = new format.epub.paint.b(c(), b(), 0, this.a, this.b, j.j(), j.i());
            g gVar = f.a().c().n;
            if (com.qq.reader.appconfig.a.d.B.equals(ao.b)) {
                gVar.c(f.a().c().n.c());
            } else {
                gVar.c(ao.b);
            }
            s();
            this.z.a(this.q);
            this.z.j();
        }
    }

    public void r() {
        this.z.q();
    }

    private void a(Canvas canvas) {
        int n = n();
        int o = o();
        int dimensionPixelSize = n - (this.i.getResources().getDimensionPixelSize(R.dimen.paypage_btn_margin_horizonal) * 2);
        int dimensionPixelSize2 = this.i.getResources().getDimensionPixelSize(R.dimen.paypage_btn_height);
        o = (o / 2) + this.i.getResources().getDimensionPixelSize(R.dimen.paypage_btn_margin_top);
        this.E.setTextSize((float) ao.a(17.0f));
        FontMetrics fontMetrics = this.E.getFontMetrics();
        int ceil = (((dimensionPixelSize2 - ((dimensionPixelSize2 - ((int) Math.ceil((double) (fontMetrics.descent - fontMetrics.ascent)))) / 2)) - ((int) fontMetrics.descent)) + o) + 1;
        this.D.setBounds((n - dimensionPixelSize) / 2, o, (dimensionPixelSize + n) / 2, dimensionPixelSize2 + o);
        com.qq.reader.module.readpage.h.b c = this.d.o().c();
        switch (c.p()) {
            case 1001:
                this.D.draw(canvas);
                dimensionPixelSize = this.E.getColor();
                this.E.setColor(-1);
                canvas.drawText(c.n(), (float) (n / 2), (float) ceil, this.E);
                this.E.setColor(dimensionPixelSize);
                return;
            default:
                return;
        }
    }

    protected void a(Canvas canvas, PageIndex pageIndex, ZLTextElementAreaArrayList zLTextElementAreaArrayList, ZLRectNoteArrayList zLRectNoteArrayList, n nVar, com.qq.reader.module.readpage.d dVar, int i) {
        if (i == 2 || !this.d.o().d()) {
            switch (pageIndex) {
                case current:
                    if (i != 1) {
                        this.w = this.z.r();
                        break;
                    }
                    this.w = this.z.s();
                    if (this.w == null || !this.w.b()) {
                        this.w = this.z.r();
                        break;
                    }
                default:
                    this.w = this.z.r();
                    break;
            }
            System.currentTimeMillis();
            if (this.w != null && this.w.b()) {
                ZLStyleNodeList l;
                int k;
                int i2;
                int l2;
                int j;
                int i3;
                e eVar;
                e eVar2;
                this.C.clear();
                this.C.addAll(this.w.g());
                float k2 = ((float) j.k()) - this.w.k();
                for (r i4 : this.C) {
                    if (i4.i()) {
                        k2 = 0.0f;
                        l = this.w.l();
                        k = j.k();
                        i2 = j.i();
                        l2 = l();
                        j = j.j();
                        i3 = 0;
                        while (i3 < l.size()) {
                            eVar = (e) l.get(i3);
                            while (true) {
                                ((format.epub.view.style.b) eVar.b).a(canvas, n(), o(), k, i2, l2, j, k2);
                                eVar2 = eVar.f;
                                if (eVar2 != null) {
                                    i3++;
                                } else {
                                    eVar = eVar2;
                                }
                            }
                        }
                        for (r rVar : this.C) {
                            canvas.save();
                            if (rVar.A) {
                                canvas.clipRect(0, 0, (n() - j.i()) - rVar.u.e(E()), o());
                                canvas.translate((float) dVar.a(), 0.0f);
                            }
                            a(this.w, rVar, k2, canvas, zLTextElementAreaArrayList);
                            canvas.restore();
                        }
                        zLRectNoteArrayList.clear();
                        this.l.j();
                        this.l.a(zLTextElementAreaArrayList, zLRectNoteArrayList);
                        this.l.b(canvas);
                        return;
                    }
                }
                l = this.w.l();
                k = j.k();
                i2 = j.i();
                l2 = l();
                j = j.j();
                i3 = 0;
                while (i3 < l.size()) {
                    eVar = (e) l.get(i3);
                    while (true) {
                        ((format.epub.view.style.b) eVar.b).a(canvas, n(), o(), k, i2, l2, j, k2);
                        eVar2 = eVar.f;
                        if (eVar2 != null) {
                            eVar = eVar2;
                        } else {
                            i3++;
                        }
                    }
                }
                for (r rVar2 : this.C) {
                    canvas.save();
                    if (rVar2.A) {
                        canvas.clipRect(0, 0, (n() - j.i()) - rVar2.u.e(E()), o());
                        canvas.translate((float) dVar.a(), 0.0f);
                    }
                    a(this.w, rVar2, k2, canvas, zLTextElementAreaArrayList);
                    canvas.restore();
                }
                zLRectNoteArrayList.clear();
                this.l.j();
                this.l.a(zLTextElementAreaArrayList, zLRectNoteArrayList);
                this.l.b(canvas);
                return;
            }
            return;
        }
        zLTextElementAreaArrayList.clear();
        a(canvas);
    }

    protected void y() {
        this.y = null;
    }

    private h E() {
        if (this.y == null) {
            this.y = new h(com.qq.reader.common.c.a.bY, (com.qq.reader.common.c.a.bU - j.j()) - j.i(), com.qq.reader.common.c.a.bT, f.a().c().u());
        }
        return this.y;
    }

    public void a(v vVar) {
        if (this.t != vVar) {
            this.t = vVar;
            this.u = -1.0f;
            this.v = -1.0f;
        }
        this.q.b(vVar.a(), vVar.a(E()), vVar.c(), vVar.d(), vVar.e(), vVar.f(), vVar.g());
    }

    final v z() {
        return this.t;
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

    final float a(g gVar) {
        if (gVar instanceof y) {
            return C();
        }
        if (gVar instanceof p) {
            return (float) this.q.a((p) gVar, E(), a((p) gVar));
        }
        return 0.0f;
    }

    final float b(g gVar) {
        return ((gVar instanceof y) || (gVar instanceof p)) ? this.q.q() : 0.0f;
    }

    final float c(g gVar) {
        return ((gVar instanceof y) || (gVar instanceof p) || gVar == g.c) ? this.q.r() : 0.0f;
    }

    final void a(float f, float f2, y yVar, int i, int i2, boolean z, Canvas canvas) {
        ZLPaintContext zLPaintContext = this.q;
        if (this.t.b() != null) {
            zLPaintContext.a(this.t.b());
        } else {
            zLPaintContext.a(a(this.t.b));
        }
        if (i == 0 && i2 == -1) {
            a(f, f2, yVar.a, yVar.b, yVar.h, yVar.b(), 0, canvas);
            return;
        }
        int i3;
        if (i2 == -1) {
            i3 = yVar.h - i;
        } else {
            i3 = i2;
        }
        if (z) {
            Object obj = this.F;
            if (i3 + 1 > obj.length) {
                obj = new char[(i3 + 1)];
                this.F = obj;
            }
            System.arraycopy(yVar.a, yVar.b + i, obj, 0, i3);
            obj[i3] = '-';
            a(f, f2, obj, 0, i3 + 1, yVar.b(), i, canvas);
            return;
        }
        a(f, f2, yVar.a, yVar.b + i, i3, yVar.b(), i, canvas);
    }

    private final void a(float f, float f2, char[] cArr, int i, int i2, a aVar, int i3, Canvas canvas) {
        ZLPaintContext zLPaintContext = this.q;
        if (aVar == null) {
            zLPaintContext.a(f, f2, cArr, i, i2, canvas);
            return;
        }
        int i4 = 0;
        float f3 = f;
        while (aVar != null && i4 < i2) {
            int i5;
            int i6;
            float f4;
            int i7;
            int i8 = aVar.a - i3;
            int i9 = aVar.b;
            if (i8 < i4) {
                i5 = i9 + (i8 - i4);
                i6 = i4;
            } else {
                i5 = i9;
                i6 = i8;
            }
            if (i5 <= 0) {
                f4 = f3;
                i7 = i4;
            } else {
                if (i6 > i4) {
                    int min = Math.min(i6, i2);
                    zLPaintContext.a(f3, f2, cArr, i + i4, min - i4, canvas);
                    f3 += zLPaintContext.a(cArr, i + i4, min - i4);
                }
                if (i6 < i2) {
                    zLPaintContext.b(F());
                    int min2 = Math.min(i6 + i5, i2);
                    float a = f3 + zLPaintContext.a(cArr, i + i6, min2 - i6);
                    zLPaintContext.a(f3, f2 - zLPaintContext.o(), a - 1.0f, zLPaintContext.q() + f2, canvas);
                    zLPaintContext.a(f3, f2, cArr, i + i6, min2 - i6, canvas);
                    f3 = a;
                }
                f4 = f3;
                i7 = i6 + i5;
            }
            aVar = aVar.a();
            i4 = i7;
            f3 = f4;
        }
        if (i4 < i2) {
            zLPaintContext.a(f3, f2, cArr, i + i4, i2 - i4, canvas);
        }
    }

    private k a(m mVar) {
        format.epub.common.utils.b a = format.epub.common.utils.b.a("defaultLight");
        switch (mVar.a) {
            case (byte) 1:
                return a.f.a();
            case (byte) 2:
                return a.f.a();
            default:
                return a.e.a();
        }
    }

    private k F() {
        return null;
    }

    public void b(int i) {
        format.epub.common.utils.b.a("defaultLight").e.a(new k(i));
    }

    public void b(float f) {
        super.b(f);
        f.a().c().o.a((int) f);
    }

    public void a(float f) {
        super.a(f);
        f.a().c().o.a((int) f);
        y();
        A();
    }

    public TextPaint d() {
        return null;
    }

    public void A() {
        t.a();
        this.x.clear();
        this.u = -1.0f;
        this.v = -1.0f;
    }

    private void a(d dVar, r rVar, float f, Canvas canvas, ZLTextElementAreaArrayList zLTextElementAreaArrayList) {
        if (com.qq.reader.common.c.b.o == 1) {
            canvas.drawLine((float) j.j(), 0.0f, (float) j.j(), 1920.0f, this.B);
            canvas.drawLine((float) (n() - j.i()), 0.0f, (float) (n() - j.i()), 1920.0f, this.B);
            canvas.drawLine((float) (j.j() + rVar.k), 0.0f, (float) (j.j() + rVar.k), 1920.0f, this.B);
            canvas.drawLine((float) ((n() - j.i()) - rVar.l), 0.0f, (float) ((n() - j.i()) - rVar.l), 1920.0f, this.B);
        }
        List g = rVar.g();
        int i = 0;
        int size = g.size();
        if (size == 0) {
            Log.i("OOM", "drawTextLine page.TextElementMap.size() == 0");
            return;
        }
        s sVar = rVar.a;
        ZLPaintContext zLPaintContext = this.q;
        int i2 = rVar.g;
        int i3 = rVar.f;
        int i4 = rVar.e;
        int i5 = i3;
        while (i4 != i2 && i < size) {
            int i6;
            g c = sVar.c(i4);
            h hVar = (h) g.get(i);
            hVar.c = hVar.e + f;
            hVar.d = hVar.f + f;
            if (com.qq.reader.common.c.b.n == 1) {
                canvas.drawRect(hVar.a, hVar.c, hVar.b, hVar.d, this.A);
            }
            if (com.qq.reader.common.c.b.o == 1) {
                canvas.drawLine(0.0f, hVar.c, 1920.0f, hVar.c, this.B);
                canvas.drawLine(0.0f, hVar.d, 1920.0f, hVar.d, this.B);
            }
            zLTextElementAreaArrayList.add(hVar);
            if (c == hVar.o) {
                int i7 = i + 1;
                if (hVar.m) {
                    a(hVar.n);
                }
                float f2 = hVar.a;
                float c2 = c(c);
                float b = b(c);
                float m = ((hVar.d - (2.0f * b)) - ((float) z().m(E()))) - rVar.v;
                if (c instanceof y) {
                    if (a(c) >= rVar.n - ((float) rVar.q)) {
                        c2 = hVar.c - c2;
                        if (com.qq.reader.common.c.a.bZ == 4.0f) {
                            hVar.d = (3.0f * b) + c2;
                        } else {
                            hVar.d = (2.0f * b) + c2;
                        }
                    } else {
                        hVar.c = (hVar.d - rVar.p) - D();
                        c2 = hVar.c - c2;
                        hVar.d = (2.0f * b) + c2;
                    }
                    a(f2, c2, (y) c, i5, -1, false, canvas);
                } else if (c instanceof p) {
                    if (((p) c).j) {
                        if (this.d.l() == 2) {
                            hVar.c = hVar.e - this.w.k();
                            hVar.d = hVar.f - this.w.k();
                        }
                        c2 = hVar.d;
                    } else if (((p) c).d()) {
                        c2 = hVar.d;
                    } else if (((p) c).i) {
                        c2 = m + (2.0f * b);
                    } else if (rVar.z) {
                        float f3;
                        if (a(c) < rVar.n) {
                            f3 = ((hVar.c - c2) + b) - ((float) ((p) c).f());
                        } else {
                            f3 = (hVar.d - rVar.v) - ((float) ((p) c).f());
                        }
                        c2 = f3;
                    } else {
                        c2 = hVar.d - rVar.v;
                    }
                    if (((p) c).j && this.z.l() == 2) {
                        zLPaintContext.a(new k(WebView.NIGHT_MODE_COLOR), canvas);
                    }
                    zLPaintContext.a(f2, c2, (p) c, E(), a((p) c), canvas);
                } else if (c == g.c) {
                    float n = zLPaintContext.n();
                    m = hVar.c - c2;
                    for (i6 = 0; ((float) i6) < hVar.b - hVar.a; i6 = (int) (((float) i6) + n)) {
                        zLPaintContext.a(f2 + ((float) i6), m, G, 0, 1, canvas);
                    }
                }
                if ((c instanceof p) && ((p) c).a()) {
                    i6 = ao.a(10.0f);
                    hVar.g = hVar.a - ((float) i6);
                    hVar.h = hVar.b + ((float) i6);
                    hVar.i = hVar.c - ((float) i6);
                    hVar.j = ((float) i6) + hVar.d;
                    i6 = i7;
                } else {
                    hVar.g = hVar.a;
                    hVar.h = hVar.b;
                    hVar.i = hVar.c;
                    hVar.j = hVar.d;
                    i6 = i7;
                }
            } else {
                i6 = i;
            }
            i4++;
            i5 = 0;
            i = i6;
        }
        if (i != size) {
            i6 = i + 1;
            h hVar2 = (h) g.get(i);
            if (hVar2.m) {
                a(hVar2.n);
            }
            int i8 = rVar.h;
            y yVar = (y) sVar.c(rVar.g);
            a(hVar2.a, ((hVar2.d - rVar.v) - zLPaintContext.q()) - ((float) z().m(E())), yVar, 0, i8, hVar2.l, canvas);
        }
    }

    public boolean b(int i, int i2) {
        i a = a((float) i, (float) i2, 10.0f, i.b);
        if (a == null) {
            return false;
        }
        a(a);
        if (this.r != null) {
            this.r.a(this.i, this, this.s);
        }
        return true;
    }

    protected i a(float f, float f2, float f3, format.epub.view.i.a aVar) {
        i iVar = null;
        float f4 = f3 + 1.0f;
        if (this.k != null) {
            Iterator it = this.k.b().ElementRegions.iterator();
            while (it.hasNext()) {
                i iVar2;
                float f5;
                i iVar3 = (i) it.next();
                if (aVar.a(iVar3)) {
                    float a = iVar3.a(f, f2);
                    if (a < f4) {
                        float f6 = a;
                        iVar2 = iVar3;
                        f5 = f6;
                        f4 = f5;
                        iVar = iVar2;
                    }
                }
                f5 = f4;
                iVar2 = iVar;
                f4 = f5;
                iVar = iVar2;
            }
        }
        return iVar;
    }

    protected void a(i iVar) {
        if (iVar == null || !iVar.equals(this.H)) {
            this.I = true;
        }
        this.H = iVar;
    }

    private i G() {
        if (this.k == null) {
            return null;
        }
        ArrayList arrayList = this.k.b().ElementRegions;
        int indexOf = arrayList.indexOf(this.H);
        if (indexOf == -1) {
            return null;
        }
        return (i) arrayList.get(indexOf);
    }

    public i B() {
        return G();
    }

    public boolean s() {
        int L = com.qq.reader.appconfig.a.d.L(this.i);
        boolean P = com.qq.reader.appconfig.a.d.P(this.i);
        if (this.q.a()) {
            if (L == 3 || L == 5 || L == 6 || P) {
                this.q.a(false);
                return true;
            }
        } else if (!(L == 3 || P || L == 5 || L == 6)) {
            this.q.a(true);
            return true;
        }
        return false;
    }

    public String a(com.qq.reader.readengine.kernel.g gVar, com.qq.reader.readengine.kernel.g gVar2) {
        return this.z.b(gVar, gVar2);
    }
}
