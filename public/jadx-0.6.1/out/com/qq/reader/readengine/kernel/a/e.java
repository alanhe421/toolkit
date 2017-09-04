package com.qq.reader.readengine.kernel.a;

import com.iflytek.cloud.SpeechError;
import com.qq.reader.readengine.kernel.d;
import com.qq.reader.readengine.kernel.g;
import com.qq.reader.readengine.kernel.h;
import format.epub.common.text.model.i;
import format.epub.common.text.model.j.a;
import format.epub.paint.ZLPaintContext;
import format.epub.view.b;
import format.epub.view.c;
import format.epub.view.f;
import format.epub.view.k;
import format.epub.view.p;
import format.epub.view.r;
import format.epub.view.s;
import format.epub.view.t;
import format.epub.view.y;
import format.epub.view.z;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: QEPubPageWrapper */
public class e extends d {
    private static final char[] p = "System developers have used modeling languages for decades to specify, visualize, construct, and document systems. The Unified Modeling Language (UML) is one of those languages. UML makes it possible for team members to collaborate by providing a common language that applies to a multitude of different systems. Essentially, it enables you to communicate solutions in a consistent, tool-supported language.".toCharArray();
    private List<d> e = Collections.synchronizedList(new ArrayList());
    private List<d> f = Collections.synchronizedList(new ArrayList());
    private List<d> g = Collections.synchronizedList(new ArrayList());
    private b h = new b();
    private a i;
    private b j = new b();
    private c k;
    private int l;
    private d m;
    private d n;
    private float o;
    private final char[] q = new char[512];
    private int r = 0;
    private i s = null;
    private float t = -1.0f;
    private int u;

    public void a(com.qq.reader.readengine.fileparse.e eVar) {
        if (eVar != null) {
            t.a();
            this.i = (a) eVar;
        }
    }

    public void a(ZLPaintContext zLPaintContext) {
        this.k = new c(new format.epub.paint.b(zLPaintContext.b(), zLPaintContext.c(), 0, zLPaintContext.i(), zLPaintContext.j(), zLPaintContext.k(), zLPaintContext.l()));
        this.j.a(this.k);
    }

    public void n() {
        this.k.c();
    }

    public void o() {
        this.k.d();
    }

    public synchronized void j() {
        if (this.m != null) {
            t.a();
            this.u = 0;
            this.o = 0.0f;
            this.t = -1.0f;
            this.k.b();
            a(this.m.e());
        }
    }

    public boolean a(h hVar) {
        p();
        if (this.i != null && this.i.c() > 0) {
            c(hVar);
            if (this.f.size() > 0) {
                if (this.c == 1) {
                    this.h.a(this.f);
                }
                w();
                return true;
            }
        }
        return false;
    }

    private void v() {
        this.h.a(this.m);
    }

    private void a(d dVar) {
        try {
            this.j.a(dVar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private synchronized void a(h hVar, List<d> list, boolean z) {
        try {
            d dVar = new d();
            z zVar = new z(s.a(this.i.a(), hVar.a()));
            zVar.a(hVar.b(), hVar.c());
            if (z) {
                dVar.a(zVar);
            } else {
                dVar.b(zVar);
            }
            d dVar2 = dVar;
            int i = 0;
            while (i < 5) {
                d dVar3;
                a(dVar2);
                if (z) {
                    list.add(dVar2);
                } else {
                    list.add(0, dVar2);
                }
                if (!z) {
                    if (dVar2.d()) {
                        break;
                    }
                    dVar3 = new d();
                    dVar3.b(dVar2.b);
                } else if (dVar2.c()) {
                    break;
                } else {
                    dVar3 = new d();
                    dVar3.a(dVar2.c);
                }
                i++;
                dVar2 = dVar3;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void w() {
        if (this.c == 2) {
            this.m = (d) this.f.get(this.l);
            return;
        }
        if (this.m == null) {
            this.m = new d();
        }
        this.m.d.clear();
        this.m.l = 0.0f;
        this.m.m = 0.0f;
        int i = this.k.i();
        float f = 0.0f;
        for (int i2 = 0; i2 < this.h.b(); i2++) {
            r c = this.h.c(i2);
            this.m.d.add(c);
            if (c.d()) {
                f += c.c();
            }
            if (f >= ((float) i)) {
                this.m.l = f - ((float) i);
                break;
            }
            this.h.a(0);
        }
        int size = this.m.d.size();
        if (size > 0) {
            this.m.b.a(((r) this.m.d.get(0)).e());
            this.m.c.a(((r) this.m.d.get(size - 1)).f());
        }
        this.m.h = 1;
    }

    protected void a(int i) {
        super.a(i);
        this.j.a(this.c);
    }

    public void p() {
        this.l = 0;
        this.e.clear();
        this.f.clear();
        this.g.clear();
        this.h.a();
        this.m = null;
        this.n = null;
    }

    public void q() {
        this.n = null;
    }

    public d r() {
        return this.m;
    }

    public d s() {
        if (this.c == 1) {
            return this.m;
        }
        return this.n;
    }

    public int a(float f) {
        r d = this.h.d();
        if (d != null && d.h() && this.m.l == 0.0f) {
            return 3;
        }
        int i;
        float f2 = f - this.m.l;
        while (f2 > 0.0f) {
            if (d.h()) {
                f2 = f - f2;
                f2 = 0.0f;
                break;
            }
            if (!this.h.a(0)) {
                c(d.f());
                this.h.b(this.f);
            }
            d = this.h.d();
            f2 -= d.b();
        }
        this.m.l = Math.abs(f2);
        float i2 = ((float) this.k.i()) - (d.b() - this.m.l);
        int i3 = this.h.b;
        f2 = 0.0f;
        for (int i4 = this.h.b - 1; i4 >= 0; i4--) {
            d = this.h.c(i4);
            if (d.d()) {
                f2 += d.b();
                if (f2 >= i2) {
                    this.m.k = f2 - i2;
                    i = i4;
                    break;
                }
            }
            i3 = i4;
        }
        i = i3;
        this.h.a = i;
        v();
        return 6;
    }

    public int b(float f) {
        r c = this.h.c();
        if (c != null && c.e().g() && this.m.k == 0.0f) {
            return 3;
        }
        float f2 = f - this.m.k;
        while (f2 > 0.0f) {
            if (c.e().g()) {
                f2 = f - f2;
                f2 = 0.0f;
                break;
            }
            if (!this.h.b(1)) {
                d(c.e());
                this.h.c(this.f);
            }
            c = this.h.c();
            f2 -= c.b();
        }
        this.m.k = Math.abs(f2);
        float i = ((float) this.k.i()) - (c.b() - this.m.k);
        int i2 = this.h.a;
        f2 = 0.0f;
        int i3 = this.h.a + 1;
        while (i3 < this.h.b()) {
            c = this.h.c(i3);
            if (c.d()) {
                f2 += c.b();
                if (f2 >= i) {
                    this.m.l = f2 - i;
                    break;
                }
            }
            i2 = i3;
            i3++;
        }
        i3 = i2;
        this.h.b = i3;
        v();
        return 6;
    }

    private void b(final h hVar) {
        Thread thread = new Thread(new Runnable(this) {
            final /* synthetic */ e b;

            public void run() {
                this.b.a(hVar, this.b.g, true);
            }
        });
        thread.setPriority(1);
        thread.start();
    }

    private synchronized void c(h hVar) {
        int i;
        d dVar;
        this.e.clear();
        this.e.addAll(this.f);
        this.f.clear();
        int size = this.g.size();
        if (size <= 0 || ((d) this.g.get(0)).e().equals(hVar)) {
            i = size;
        } else {
            this.g.clear();
            i = 0;
        }
        if (this.g == null || i <= 0) {
            a(hVar, this.f, true);
            i = this.f.size();
            dVar = i > 0 ? (d) this.f.get(i - 1) : null;
        } else {
            this.f.addAll(this.g);
            this.g.clear();
            dVar = (d) this.f.get(i - 1);
        }
        if (!(dVar == null || dVar.c())) {
            h zVar = new z();
            zVar.a(dVar.c);
            b(zVar);
        }
    }

    private synchronized void d(h hVar) {
        this.g.clear();
        this.g.addAll(this.f);
        this.f.clear();
        int size = this.e.size();
        int i;
        if (size <= 0 || ((d) this.e.get(size - 1)).f().equals(hVar)) {
            i = size;
        } else {
            this.e.clear();
            i = 0;
        }
        if (this.e == null || r0 <= 0) {
            a(hVar, this.f, false);
        } else {
            this.f.addAll(this.e);
            this.e.clear();
        }
    }

    public int a() {
        if (this.m == null || this.m.c()) {
            return 3;
        }
        this.n = this.m;
        if (this.l < this.f.size() - 1) {
            this.l++;
            this.m = (d) this.f.get(this.l);
            return 0;
        }
        this.l = 0;
        c(new k(this.m.c));
        if (this.f.size() <= 0) {
            return 3;
        }
        this.m = (d) this.f.get(this.l);
        return 1;
    }

    public int b() {
        if (this.m == null || this.m.d() || this.f.size() <= 0) {
            return 3;
        }
        if (this.l > 0) {
            this.l--;
            this.m = (d) this.f.get(this.l);
            return 0;
        }
        this.l = 0;
        d(new k(this.m.b));
        int size = this.f.size();
        if (size <= 0) {
            return 3;
        }
        this.l = size - 1;
        this.m = (d) this.f.get(this.l);
        return 0;
    }

    private int a(z zVar) {
        s j = zVar.j();
        if (j == null) {
            return -1;
        }
        int i = j.a;
        int b = this.i.a().b(i - 1);
        int f = j.f();
        if (f > 0) {
            return b + (((this.i.a().b(i) - b) * zVar.b()) / f);
        }
        return b;
    }

    private synchronized float x() {
        float f;
        if (this.o > 0.0f) {
            f = this.o;
        } else {
            int b = this.i.a().b();
            this.o = this.k.a(b, this.i.a().b(b - 1), y());
            f = this.o;
        }
        return f;
    }

    private synchronized int c(int i) {
        int i2 = 1;
        synchronized (this) {
            if (!(this.i == null || this.i.c() == 0)) {
                float x = 1.0f / x();
                i2 = Math.max((int) (((((float) i) * x) + 1.0f) - (x * 0.5f)), 1);
            }
        }
        return i2;
    }

    private final float y() {
        if (this.s != this.i.a()) {
            this.s = this.i.a();
            this.r = 0;
            this.t = -1.0f;
            int b = this.i.a().b(this.i.c() - 1);
            if (b > this.q.length) {
                b = this.i.a().c((b - this.q.length) / 2);
            } else {
                b = 0;
            }
            while (b < this.i.c() && this.r < this.q.length) {
                int i = b + 1;
                a a = this.i.a().a(b).a();
                while (a.l() && this.r < this.q.length) {
                    try {
                        a.m();
                        if (a.a() == (byte) 1) {
                            b = Math.min(a.d(), this.q.length - this.r);
                            System.arraycopy(a.b(), a.c(), this.q, this.r, b);
                            this.r = b + this.r;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                b = i;
            }
            if (this.r == 0) {
                this.r = Math.min(this.q.length, p.length);
                System.arraycopy(p, 0, this.q, 0, this.r);
            }
        }
        if (this.t < 0.0f) {
            this.t = this.k.a(this.q, this.r);
        }
        return this.t;
    }

    protected final synchronized int t() {
        int i;
        if (this.i == null || this.i.c() == 0) {
            i = 1;
        } else {
            i = this.i.a().b(this.i.c() - 1);
        }
        return i;
    }

    private final synchronized int a(boolean z) {
        int i = 1;
        synchronized (this) {
            if (this.i == null || this.i.c() == 0) {
                i = 0;
            } else {
                try {
                    d dVar = this.m;
                    if (z) {
                        i = Math.max(0, a(dVar.b));
                    } else {
                        int a = a(dVar.c);
                        if (a == -1) {
                            a = this.i.a().b(this.i.c() - 1) - 1;
                        }
                        i = Math.max(1, a);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return i;
    }

    public com.qq.reader.readengine.kernel.a g() {
        int i = 1;
        int i2 = 3;
        if (this.m == null) {
            return null;
        }
        int c = c(a(false));
        if (this.u <= 0) {
            this.u = c(t());
        }
        if (this.u > 3) {
            this.a.a(c, this.u);
            return this.a;
        }
        z zVar = this.m.b;
        if (zVar == null || zVar.d()) {
            return new com.qq.reader.readengine.kernel.a(c, this.u);
        }
        if (zVar.g()) {
            i2 = 1;
        } else {
            d dVar = new d();
            dVar.c.a(zVar);
            dVar.h = 3;
            zVar = dVar.b;
            if (zVar == null || zVar.d()) {
                a(dVar);
                zVar = dVar.b;
            }
            if (zVar == null || zVar.d()) {
                i2 = c;
            } else if (zVar.g()) {
                i2 = 2;
            }
        }
        this.u = i2;
        z zVar2 = this.m.c;
        if (zVar2 == null || zVar2.d()) {
            return new com.qq.reader.readengine.kernel.a(i2, this.u);
        }
        if (!zVar2.h()) {
            d dVar2 = new d();
            dVar2.b.a(zVar2);
            dVar2.h = 2;
            zVar2 = this.n != null ? this.n.c : null;
            if (zVar2 == null || zVar2.d()) {
                a(dVar2);
                zVar2 = dVar2.c;
            }
            if (zVar2 != null) {
                int i3 = this.u;
                if (!zVar2.h()) {
                    i = 2;
                }
                this.u = i3 + i;
            }
        }
        this.a.a(i2, this.u);
        return this.a;
    }

    public Double f() {
        double d = 0.0d;
        if (this.m == null) {
            return Double.valueOf(d);
        }
        z e = this.m.e();
        int a = e.a();
        try {
            d = ((double) (e.b() + this.i.a().b(a - 1))) / ((double) this.i.a().b(this.i.c() - 1));
        } catch (Exception e2) {
        }
        if (d > 1.0d) {
            return Double.valueOf(1.0d);
        }
        return Double.valueOf(d);
    }

    public g c() {
        z e = this.m.e();
        int a = e.a();
        long c = ((long) e.c()) & 255;
        this.b.a(c | ((((long) e.b()) << 8) | (((long) a) << 32)));
        return this.b;
    }

    public g d() {
        g gVar = new g();
        z f = this.m.f();
        int a = f.a();
        gVar.a(((((long) f.b()) << 8) | (((long) a) << 32)) | (((long) f.c()) & 255));
        return gVar;
    }

    public String h() {
        z zVar = new z();
        zVar.a(this.m.b);
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence stringBuilder2 = new StringBuilder();
        CharSequence stringBuilder3 = new StringBuilder();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i5 < 20 && i4 < 3) {
            int i6 = i4;
            i4 = i3;
            i3 = i2;
            i2 = i;
            while (zVar.f()) {
                if (zVar.l() && (stringBuilder.length() <= 0 || !zVar.j().e())) {
                    if (stringBuilder3.length() > 0) {
                        stringBuilder2.append(stringBuilder3);
                        stringBuilder3.delete(0, stringBuilder3.length());
                    }
                    if (stringBuilder2.length() > 0) {
                        if (i2 != 0) {
                            stringBuilder.append("\n");
                        }
                        stringBuilder.append(stringBuilder2);
                        stringBuilder2.delete(0, stringBuilder2.length());
                        i3 = i6 + 1;
                        i = i5;
                    } else {
                        i = i4;
                        i3 = i6;
                    }
                    if (stringBuilder.length() > 0) {
                        i2 = 1;
                        i4 = i;
                        i6 = i3;
                        i3 = 0;
                    } else {
                        i4 = i;
                        i6 = i3;
                        i3 = 0;
                    }
                }
                if (i4 < 4) {
                    if (stringBuilder2.length() == 0) {
                        stringBuilder2.append(stringBuilder3);
                    }
                    if (i2 != 0) {
                        stringBuilder.append("\n");
                    }
                    stringBuilder.append(stringBuilder2);
                }
                return stringBuilder.toString();
            }
            format.epub.view.g i7 = zVar.i();
            if (i7 instanceof y) {
                y yVar = (y) i7;
                if (i3 != 0) {
                    stringBuilder3.append(" ");
                }
                stringBuilder3.append(yVar.a, yVar.b, yVar.h);
                i3 = i5 + 1;
                switch (yVar.a[(yVar.h + yVar.b) - 1]) {
                    case '!':
                    case '.':
                    case SpeechError.TIP_ERROR_IVP_NO_ENOUGH_AUDIO /*63*/:
                        i6++;
                        if (i2 != 0) {
                            stringBuilder.append("\n");
                            i2 = 0;
                        }
                        stringBuilder2.append(stringBuilder3);
                        stringBuilder3.delete(0, stringBuilder3.length());
                        stringBuilder.append(stringBuilder2);
                        stringBuilder2.delete(0, stringBuilder2.length());
                        i4 = i3;
                        i5 = i3;
                        i3 = 1;
                        break;
                    case ')':
                    case ',':
                    case ':':
                    case ';':
                        stringBuilder2.append(stringBuilder3);
                        stringBuilder3.delete(0, stringBuilder3.length());
                        i5 = i3;
                        i3 = 1;
                        break;
                    default:
                        i5 = i3;
                        i3 = 1;
                        break;
                }
            }
            zVar.k();
            i = i2;
            i2 = i3;
            i3 = i4;
            i4 = i6;
        }
        i2 = i;
        i4 = i3;
        if (i4 < 4) {
            if (stringBuilder2.length() == 0) {
                stringBuilder2.append(stringBuilder3);
            }
            if (i2 != 0) {
                stringBuilder.append("\n");
            }
            stringBuilder.append(stringBuilder2);
        }
        return stringBuilder.toString();
    }

    public boolean k() {
        if (this.m == null || this.m.h == 0) {
            return false;
        }
        return true;
    }

    public boolean i() {
        if (this.i == null || this.f.size() <= 0 || this.m == null || (this.m.b.d() && this.m.c.d())) {
            return false;
        }
        return true;
    }

    public long u() {
        return this.i.b();
    }

    public g a(double d) {
        int i = 0;
        long u = (long) (((double) u()) * d);
        if (d != 0.0d) {
            if (d == 1.0d) {
                i = this.j.a(new z(s.a(this.i.a(), this.i.c() - 1)), 0, (float) this.k.i()).a();
            } else {
                i = a(this.i.a(), this.i.a().c((int) u));
            }
        }
        long j = ((long) i) << 32;
        g gVar = new g();
        gVar.a(j);
        return gVar;
    }

    private int a(i iVar, int i) {
        int i2 = 0;
        int i3 = i;
        while (i3 > 0 && r3 == 0) {
            int i4;
            s a = s.a(iVar, i3);
            for (i4 = 0; i4 < a.f(); i4++) {
                format.epub.view.g c = a.c(i4);
                if ((c instanceof y) || (c instanceof p)) {
                    i2 = 1;
                    break;
                }
            }
            if (i2 == 0) {
                i4 = i3 - 1;
            } else {
                i4 = i3;
                while (i4 > 0 && s.a(iVar, i4 - 1).f() == 1 && (a.c(0) instanceof f) && a.i().b() != (byte) 4) {
                    i4--;
                }
            }
            i3 = i4;
        }
        return Math.max(i3, 0);
    }

    public int a(long j, long j2) {
        i a = this.i.a();
        int i = (int) ((j2 >> 32) & -1);
        int b = a.b(i - 1) - a.b(((int) ((j >> 32) & -1)) - 1);
        int i2 = 0;
        s a2 = s.a(a, i);
        if (!(a2 == null || a2.b())) {
            i2 = a2.a((int) ((j2 >> 8) & 16777215));
        }
        return i2 + b;
    }

    public long a(int i, int i2) {
        i a = this.i.a();
        int b = a.b(i - 1) + i2;
        int c = a.c(b);
        int b2 = s.a(a, c).b(b - a.b(c - 1));
        if (b2 == -1) {
            c++;
            b2 = 0;
        }
        return ((((long) b2) << 8) | (((long) c) << 32)) | 0;
    }

    public String b(g gVar, g gVar2) {
        f fVar = new f();
        fVar.a(this.i);
        fVar.a(new k((int) ((gVar.e() >> 32) & -1), (int) ((gVar.e() >> 8) & 16777215), (int) (gVar.e() & 255)), new k((int) ((gVar2.e() >> 32) & -1), (int) ((gVar2.e() >> 8) & 16777215), (int) (gVar2.e() & 255)));
        return fVar.c();
    }

    public int a(g gVar, g gVar2) {
        int a = a(gVar);
        int a2 = a(gVar2);
        if (a2 == 0) {
            return 0;
        }
        if (a2 < 0) {
            return -1;
        }
        if (a > 0) {
            return 1;
        }
        g d = d();
        a2 = gVar.b();
        if (a2 != d.b()) {
            return 100;
        }
        return (int) Math.ceil((double) ((((float) (d.c() - gVar.c())) * 100.0f) / ((float) (s.a(this.i.a(), a2).f() - gVar.c()))));
    }

    public int e() {
        return ((d) this.f.get(this.f.size() - 1)).f().a();
    }
}
