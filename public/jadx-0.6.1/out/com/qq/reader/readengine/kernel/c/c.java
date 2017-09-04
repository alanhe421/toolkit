package com.qq.reader.readengine.kernel.c;

import android.text.TextPaint;
import com.dynamicload.Lib.DLConstants;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a;
import com.qq.reader.common.utils.ao;
import com.qq.reader.framework.mark.LocalMark;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.framework.mark.UserMark;
import com.qq.reader.module.readpage.ac;
import com.qq.reader.module.readpage.f;
import com.qq.reader.module.readpage.h;
import com.qq.reader.readengine.fileparse.i;
import com.qq.reader.readengine.kernel.d;
import com.qq.reader.readengine.kernel.e;
import com.qq.reader.readengine.kernel.g;
import com.qq.reader.readengine.model.IBook;
import com.tencent.smtt.utils.TbsLog;
import java.io.IOException;
import java.util.List;

/* compiled from: QTxtPageWrapper */
public class c extends d {
    protected f e;
    public com.qq.reader.readengine.fileparse.d f = null;
    private ac g = new ac();
    private b h;
    private b i;
    private h j = new h();
    private float k = 0.0f;
    private Object l = new Object();
    private boolean m = false;
    private i n = new i();
    private List<e> o;
    private int p = 0;

    public boolean i() {
        return (this.f != null && this.f.g()) || this.j.f() != TbsLog.TBSLOG_CODE_SDK_INIT;
    }

    public boolean n() {
        return this.n != null && this.n.b() > 0;
    }

    public void a(com.qq.reader.readengine.fileparse.c cVar) {
        float a = this.e.a();
        int c = this.e.c();
        int b = this.e.b();
        TextPaint textPaint = new TextPaint();
        textPaint.set(this.e.d());
        textPaint.setTextSize(a.d.I(ReaderApplication.getApplicationImp()));
        com.qq.reader.readengine.a.c.a(cVar, c, b, textPaint);
        cVar.a(this.f.t().getEncodingStr());
        cVar.k = (int) (((float) b) / a);
        this.k = (((float) b) % a) / ((float) (cVar.k - 1));
        b.a(this.f.t().getEncodingStr());
    }

    public void b(int i) {
        int i2;
        int b = this.e.b();
        TextPaint textPaint = new TextPaint();
        textPaint.set(this.e.d());
        textPaint.setTextSize(a.d.I(ReaderApplication.getApplicationImp()));
        com.qq.reader.readengine.fileparse.c d = this.f.d();
        com.qq.reader.readengine.fileparse.c b2 = this.f.b();
        if (d != null && d.l) {
            b2 = d;
            Object obj = null;
        } else if (b2 == null || !b2.l) {
            i2 = 1;
            b2 = null;
        } else {
            i2 = 1;
        }
        if (b2 != null) {
            int size = b2.o.size();
            if (b2 != null && size > 0 && this.p >= 0 && this.p < size && b2.m == i) {
                e eVar = (e) b2.o.get(this.p);
                int size2 = b2.o.size();
                int b3 = eVar.b();
                e eVar2 = (e) b2.j().get(b3);
                float f = eVar2.f() + eVar2.e();
                com.qq.reader.readengine.a.c.a(b2, textPaint, b);
                if (obj != null) {
                    i2 = ((e) b2.o.get(this.p)).b();
                    eVar2 = (e) b2.j().get(b3);
                    float e = eVar2.e() + eVar2.f();
                    b = b2.o.size();
                    if (b3 != i2 || f != e) {
                        eVar = (e) b2.o.get(this.p);
                        if (this.i == null || this.i.h() <= eVar.c() || size2 >= b) {
                            d(eVar.b());
                            D();
                        } else {
                            if (this.p == size2 - 1) {
                                this.p = b - 1;
                            }
                            eVar = (e) b2.o.get(this.p);
                            i2 = eVar.a();
                            size = eVar.b();
                            c(i2);
                            d(size);
                            D();
                        }
                    } else if (this.h != null) {
                        a(this.h);
                    }
                    com.qq.reader.common.monitor.debug.c.e("QTxtPageWrapper", "addPageTrailInfoComponent endline-------->" + p());
                }
            }
        }
    }

    public int b(com.qq.reader.readengine.fileparse.c cVar) {
        return cVar.b(this.f.t().getEncodingStr());
    }

    public void a(boolean z) {
        int i = 0;
        com.qq.reader.readengine.fileparse.c b = this.f.b();
        if (b != null) {
            int a;
            this.n.a(b);
            v();
            this.o = b.o;
            int size = this.o.size();
            e eVar;
            if (z) {
                if (size > 0) {
                    int d = this.f.d(b);
                    eVar = (e) this.o.get(d);
                    a = eVar.a();
                    i = eVar.b();
                    this.p = d;
                }
                a = 0;
            } else {
                if (this.f.b().d == 0 && this.f.a(this.f.b(), 0)) {
                    a = 1;
                } else {
                    a = 0;
                }
                if (a == 0) {
                    a(b, false);
                }
                if (size > 0) {
                    eVar = (e) this.o.get(this.o.size() - 1);
                    a = eVar.a();
                    i = eVar.b();
                    this.p = this.o.size() - 1;
                }
                a = 0;
            }
            c(a);
            d(i);
            D();
        }
    }

    private void a(com.qq.reader.readengine.fileparse.c cVar, boolean z) {
        float f = 0.0f;
        if (!z) {
            int a;
            e eVar;
            int b = this.e.b();
            List j = cVar.j();
            int size = j.size();
            float b2 = com.qq.reader.readengine.a.a.b(this.e.d());
            List list = cVar.o;
            list.clear();
            e eVar2 = new e();
            eVar2.c(cVar.m);
            float f2 = (float) b;
            eVar2.b(size - 1);
            e eVar3 = eVar2;
            float f3 = f2;
            int i = size;
            while (true) {
                int i2 = i - 1;
                if (i2 < 0) {
                    break;
                }
                f2 = f3 - ((e) j.get(i2)).e();
                if (f2 < 0.0f) {
                    eVar3.a(i2 + 1);
                    float f4 = 0.0f;
                    for (a = eVar3.a(); a <= eVar3.b(); a++) {
                        eVar = (e) j.get(a);
                        eVar.b(f4);
                        f4 += eVar.e();
                    }
                    list.add(0, eVar3);
                    eVar2 = new e();
                    eVar2.c(cVar.m);
                    eVar2.b(i2);
                    eVar3 = eVar2;
                    f3 = ((float) b) - b2;
                    i = i2;
                } else {
                    f3 = f2;
                    i = i2;
                }
            }
            if (f3 >= 0.0f) {
                eVar3.a(0);
                for (a = eVar3.a(); a <= eVar3.b(); a++) {
                    eVar = (e) j.get(a);
                    eVar.b(f);
                    f += eVar.e();
                }
                list.add(0, eVar3);
            }
        }
    }

    public void b(boolean z) {
        if (z) {
            this.n.b(this.f.b());
        } else {
            this.n.c(this.f.b());
        }
    }

    public void a(f fVar) {
        this.e = fVar;
    }

    public void a(com.qq.reader.readengine.fileparse.e eVar) {
        if (eVar != null && (eVar instanceof com.qq.reader.readengine.fileparse.d)) {
            this.f = (com.qq.reader.readengine.fileparse.d) eVar;
        }
    }

    public int o() {
        return this.n.a;
    }

    public int p() {
        return this.n.b;
    }

    public void c(int i) {
        this.n.a = i;
    }

    public void d(int i) {
        this.n.b = i;
    }

    public int a() {
        if (m()) {
            c(false);
            return 0;
        }
        if (!z()) {
            r();
        }
        b q = q();
        int v = v();
        int p = p();
        o();
        if (p >= v - 1 || !this.f.g()) {
            if (!this.f.b(this.f.b(), 0)) {
                this.i = q;
            }
            v = f(true);
        } else {
            v = this.o.size();
            if (v > 0) {
                this.p++;
                this.p = Math.min(this.p, v - 1);
                p = ((e) this.o.get(this.p)).a();
                v = ((e) this.o.get(this.p)).b();
                c(p);
                d(v);
                this.i = q;
            }
            v = 0;
        }
        if (v == 4) {
            this.h = new b();
            return v;
        } else if (v != 1 && v != 0) {
            return v;
        } else {
            C();
            return v;
        }
    }

    private int f(boolean z) {
        if (!this.f.a(this.f.b(), 0)) {
            return h(z);
        }
        if (this.f.b(this.f.b(), 0)) {
            if (this.f.l()) {
                return 5;
            }
            return 3;
        } else if (this.f.m()) {
            return 3;
        } else {
            return 4;
        }
    }

    public int b() {
        if (m()) {
            return 3;
        }
        if (!z()) {
            r();
        }
        b q = q();
        int o = o();
        p();
        if (o <= 0 || !this.f.g()) {
            o = g(true);
        } else if (this.o.size() > 0) {
            this.p--;
            this.p = Math.max(this.p, 0);
            int a = ((e) this.o.get(this.p)).a();
            o = ((e) this.o.get(this.p)).b();
            c(a);
            d(o);
            o = 0;
        } else {
            o = 3;
        }
        if (!(o == 3 && this.f.b(this.f.b()))) {
            this.i = q;
        }
        if (o == 4) {
            this.h = new b();
        } else if (o == 1 || o == 0) {
            C();
        }
        return o;
    }

    private int g(boolean z) {
        if (!this.f.a(this.f.b())) {
            return i(z);
        }
        if (!m() && this.f.b(this.f.b())) {
            c(true);
            return 0;
        } else if (this.f.b(this.f.b()) || this.f.n()) {
            return 3;
        } else {
            return 4;
        }
    }

    private void C() {
        this.h = D();
    }

    private b D() {
        if (this.f == null) {
            return null;
        }
        this.h = new b();
        this.h.b = this.k;
        this.n.b(this.h);
        if (z() && this.o != null) {
            int size = this.o.size();
            if (this.p >= 0 && this.p < size) {
                e eVar = (e) this.o.get(this.p);
                if (eVar != null) {
                    this.h.c(eVar.c());
                }
            }
        }
        if (z() && this.n.b == v() - 1 && this.f.a(this.f.b(), 0)) {
            this.h.b(true);
        }
        a(this.h);
        return this.h;
    }

    public b q() {
        if (this.h == null) {
            this.h = D();
        }
        return this.h;
    }

    public void r() {
        this.i = null;
        this.n.a();
    }

    public b s() {
        return this.i;
    }

    private synchronized int h(boolean z) {
        int i = 1;
        synchronized (this) {
            if (this.f != null) {
                boolean i2;
                synchronized (this.l) {
                    i2 = this.f.i();
                }
                if (i2) {
                    a(this.f, 0, true);
                    if (z) {
                        a(true);
                    } else {
                        b(true);
                    }
                    this.g.a(this.f, true, this.e.d().getTextSize());
                    t();
                } else {
                    i = 2;
                }
            } else {
                i = 2;
            }
        }
        return i;
    }

    private int i(boolean z) {
        if (this.f == null) {
            return 2;
        }
        try {
            if (!this.f.j()) {
                return 2;
            }
            a(this.f, 0, false);
            if (z) {
                a(false);
            } else {
                b(false);
            }
            this.g.a(this.f, false, this.e.d().getTextSize());
            return 1;
        } catch (IOException e) {
            com.qq.reader.common.monitor.f.a("BookTxtOperator", e.toString());
            return 2;
        }
    }

    protected void t() {
        if (!this.f.a(this.f.b(), 0)) {
            Thread thread = new Thread(new Runnable(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void run() {
                    synchronized (this.a.l) {
                        this.a.f.k();
                        if (this.a.f.d() != null) {
                            this.a.a(this.a.f, 1, true);
                        }
                    }
                }
            });
            thread.setPriority(1);
            thread.start();
        }
    }

    public boolean c(com.qq.reader.readengine.fileparse.c cVar) {
        return cVar.h();
    }

    public void a(com.qq.reader.readengine.fileparse.e eVar, int i, boolean z) {
        com.qq.reader.readengine.fileparse.c b;
        com.qq.reader.readengine.fileparse.d dVar = (com.qq.reader.readengine.fileparse.d) eVar;
        if (i == 0) {
            b = dVar.b();
        } else {
            b = dVar.d();
        }
        if (b != null) {
            b.a(dVar, z, i);
        } else {
            com.qq.reader.common.monitor.f.b("YT", "formatBuff buff== null , type = " + i);
        }
        dVar.q();
        if (b != null && !b.l) {
            a(b);
            boolean a = dVar.a(b, i);
            if (a || dVar.c(b)) {
                b.l = true;
                if (z && !a) {
                    b(b);
                }
            } else if (z) {
                if (dVar.i()) {
                    a(dVar, 0, z);
                }
            } else if (c(b)) {
                b.l = true;
            } else {
                i(true);
            }
        }
    }

    public void j() {
        if (this.f != null && this.f.a(c())) {
            a(this.f, 0, true);
            r();
            a(true);
            d(true);
        }
    }

    public int u() {
        if (this.f.b() == null) {
            return 0;
        }
        int i = this.f.b().k;
        if (z()) {
            return i;
        }
        return i + 1;
    }

    public int v() {
        return this.n.b();
    }

    public Mark e(int i) {
        try {
            if (this.f == null || 3 == i) {
                return null;
            }
            double doubleValue;
            Mark userMark;
            g c = c();
            String str = "";
            if (w() != 0) {
                doubleValue = f().doubleValue();
            } else {
                doubleValue = 0.0d;
            }
            for (int i2 = 0; i2 < 3; i2++) {
                d a = this.n.a(this.n.a + i2);
                if (a != null) {
                    str = str + a.g();
                }
            }
            if (doubleValue > 1.0d) {
                doubleValue = 1.0d;
            }
            String a2 = ao.a(doubleValue);
            if (i == 0 || i == 7) {
                userMark = new UserMark(this.f.t().getBookNetId(), this.f.t().getBookPath(), y(), c.f(), c.g(), c.e(), i, System.currentTimeMillis(), a2, str);
            } else {
                userMark = new LocalMark(y(), this.f.t().getBookPath(), w(), i, true);
            }
            boolean m = m();
            userMark.setDescriptionStr(str).setPercentStr(a2).setAuthor(this.f.t().getAuthor()).setEncoding(this.f.t().getEncoding()).setBookId(this.f.t().getBookNetId()).setTurePageBytes(this.g.e).setTurePageCurIndex(F()).setTurePageFont(this.g.f);
            if (m && 4 != i) {
                userMark.setStarPointStr(Mark.HEADPAGE_FLAG);
                return userMark;
            } else if (this.f.t().getReadType() == 1) {
                userMark.setStartPoint(c.g());
                return userMark;
            } else {
                userMark.setStartPoint(c.e());
                return userMark;
            }
        } catch (Exception e) {
            com.qq.reader.common.monitor.f.b("YT", "bulidBookmark Exception : " + e.toString());
            return null;
        }
    }

    public Double f() {
        g c = c();
        if (c == null) {
            return Double.valueOf(0.0d);
        }
        return Double.valueOf(com.qq.reader.readengine.fileparse.d.a(c, this.f.o(), x()));
    }

    public long w() {
        if (this.f == null) {
            return 0;
        }
        return this.f.a(-1);
    }

    public long x() {
        if (this.f == null) {
            return 0;
        }
        return this.f.v();
    }

    public boolean a(g gVar, boolean z) throws IOException {
        return this.f.a(gVar, z);
    }

    public g a(double d) {
        return this.f.a(d);
    }

    public String y() {
        if (this.f == null) {
            return "";
        }
        return this.f.t().getBookName();
    }

    public boolean m() {
        return this.m;
    }

    protected boolean z() {
        return l() == 2;
    }

    public void c(boolean z) {
        this.m = z;
        if (z) {
            B().b(1008);
        } else if (B().f() == 1008) {
            B().b(B().g());
        }
    }

    private g E() {
        if (this.f == null) {
            return null;
        }
        g c = this.n.c();
        this.f.b(c);
        return c;
    }

    public void d(boolean z) {
        IBook t = this.f.t();
        if (t != null) {
            switch (t.mTurePageCmd) {
                case 100:
                    this.g.b = t.mCurBufferPageIndex;
                    this.g.e = t.mTruePageBytes;
                    this.g.f = t.mTruePageFont;
                    break;
                case 101:
                    this.g.a();
                    break;
                case 102:
                    this.g.b();
                    break;
            }
            if (t.mTurePageCmd != DLConstants.LOAD_ERR_SIGNATURES) {
                this.g.a(this.f, z, this.e.d().getTextSize());
            }
            t.mTurePageCmd = DLConstants.LOAD_ERR_SIGNATURES;
        }
    }

    private long F() {
        g E = E();
        if (this.g.e <= 0.0f) {
            return (long) E.f();
        }
        return this.g.b + ((long) (o() / this.f.b().k));
    }

    public boolean k() {
        return q().b();
    }

    public int b(b bVar) {
        if (z()) {
            return 2;
        }
        if ((this.f.b(this.f.b(), 0) && p() >= v() - 1) || q().f() >= u()) {
            return 2;
        }
        while (q().f() < u()) {
            if (!this.n.a(bVar)) {
                return f(false);
            }
        }
        return 2;
    }

    public int a(float f) {
        int i = 3;
        b q = q();
        if (q != null && q.b()) {
            float f2 = q.c;
            float a = this.e.a();
            if (!this.f.b(this.f.b(), 0) || p() < v() - 1 || f2 != 0.0f) {
                this.n.a(true, this.f);
                i = b(q);
                if (i == 2) {
                    i = 0;
                    while (f - f2 >= a) {
                        if (!this.n.a(true, q())) {
                            i = f(false);
                            if (i != 1) {
                                break;
                            }
                            this.n.a(true, q());
                            f2 = f - (f2 + a);
                        } else {
                            f2 = f - (f2 + a);
                        }
                        f = f2;
                        f2 = 0.0f;
                    }
                    q.c = f2 - f;
                }
            }
        }
        return i;
    }

    public int b(float f) {
        int i = 3;
        b q = q();
        if (q != null && q.b()) {
            float f2 = q.c;
            float a = this.e.a();
            if (!this.f.b(this.f.b()) || o() > 0 || f2 != 0.0f) {
                this.n.a(false, this.f);
                i = 0;
                while (f2 + f >= 0.0f) {
                    float f3;
                    if (!this.n.a(false, q())) {
                        i = g(false);
                        if (i != 1) {
                            q.c = 0.0f;
                            break;
                        }
                        this.n.a(false, q());
                        if (q().f() < u()) {
                            this.n.a(q());
                        }
                        f3 = f + f2;
                        f2 = -(a - 1.0f);
                    } else {
                        if (q().f() < u()) {
                            this.n.a(q());
                        }
                        f3 = f + f2;
                        f2 = -(a - 1.0f);
                    }
                    f = f3;
                }
                q.c = f2 + f;
            }
        }
        return i;
    }

    public g c() {
        this.b = E();
        return this.b;
    }

    public g d() {
        if (this.f == null) {
            return null;
        }
        g d = this.n.d();
        this.f.b(d);
        return d;
    }

    public com.qq.reader.readengine.kernel.a g() {
        if (this.f == null) {
            return null;
        }
        int f;
        int o;
        g E = E();
        if (this.g.e <= 0.0f) {
            f = E.f();
            o = this.f.o();
        } else if (this.f.t().getReadType() == 0) {
            if (this.f.b().k > 0) {
                f = (((int) this.g.b) + (o() / this.f.b().k)) + 1;
            } else {
                f = ((int) this.g.b) + 1;
            }
            o = (int) this.g.a;
        } else {
            float e = ((float) E.e()) / this.g.e;
            if (e == ((float) ((int) e))) {
                f = ((int) e) + 1;
            } else {
                f = (int) Math.ceil((double) e);
            }
            o = (int) Math.ceil((double) (((float) w()) / this.g.e));
            if (f > o) {
                o = f;
            }
            this.g.a = (long) o;
        }
        this.a.a(f, o);
        return this.a;
    }

    public String h() {
        return null;
    }

    public boolean A() {
        return B().d();
    }

    public h B() {
        return this.j;
    }

    public String b(g gVar, g gVar2) {
        try {
            return this.n.a(gVar, gVar2);
        } catch (Exception e) {
            com.qq.reader.common.monitor.f.b("getSelectText", e.toString());
            return "";
        }
    }

    public void e(boolean z) {
        boolean z2 = z() && this.f.b(this.f.b()) && o() == 0 && z;
        c(z2);
    }

    public int a(g gVar, g gVar2) {
        int a = a(gVar);
        int a2 = a(gVar2);
        if (a <= 0 && a2 == 0) {
            return 0;
        }
        if (a2 < 0) {
            return -1;
        }
        if (a > 0) {
            return 1;
        }
        long e;
        long e2;
        long e3;
        g d = d();
        if (gVar.a() == 0) {
            e = gVar.e();
            e2 = gVar2.e();
            e3 = d.e();
        } else {
            e = gVar.g();
            e2 = gVar2.g();
            e3 = d.g();
        }
        return (int) Math.ceil((double) ((((float) (e3 - e)) * 100.0f) / ((float) (e2 - e))));
    }

    public int e() {
        return 0;
    }
}
