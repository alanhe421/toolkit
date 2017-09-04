package format.epub.view;

import com.qq.reader.readengine.kernel.h;
import format.epub.common.text.model.i;

/* compiled from: ZLTextWordCursor */
public final class z extends h {
    private s a;
    private int b;
    private int c;

    public z(z zVar) {
        a(zVar);
    }

    public void a(z zVar) {
        this.a = zVar.a;
        this.b = zVar.b;
        this.c = zVar.c;
    }

    public z(s sVar) {
        a(sVar);
    }

    public void a(s sVar) {
        this.a = sVar;
        this.b = 0;
        this.c = 0;
    }

    public boolean d() {
        return this.a == null;
    }

    public boolean e() {
        return this.b == 0 && this.c == 0;
    }

    public boolean f() {
        return this.a != null && this.b == this.a.f();
    }

    public boolean g() {
        return !d() && e() && this.a.c();
    }

    public boolean h() {
        return d() || (f() && this.a.d());
    }

    public int a() {
        return this.a != null ? this.a.a : 0;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public g i() {
        return this.a.c(this.b);
    }

    public s j() {
        return this.a;
    }

    public void k() {
        this.b++;
        this.c = 0;
    }

    public boolean l() {
        if (d() || this.a.d()) {
            return false;
        }
        this.a = this.a.h();
        n();
        return true;
    }

    public boolean m() {
        if (d() || this.a.c()) {
            return false;
        }
        this.a = this.a.g();
        n();
        return true;
    }

    public void n() {
        if (!d()) {
            this.b = 0;
            this.c = 0;
        }
    }

    public void o() {
        if (!d()) {
            this.b = this.a.f();
            this.c = 0;
        }
    }

    public void a(int i) {
        if (!d() && i != this.a.a) {
            i iVar = this.a.b;
            this.a = s.a(iVar, Math.max(0, Math.min(i, iVar.b() - 1)));
            n();
        }
    }

    public void a(int i, int i2) {
        if (!d()) {
            if (i == 0 && i2 == 0) {
                this.b = 0;
                this.c = 0;
                return;
            }
            int max = Math.max(0, i);
            int f = this.a.f();
            if (max > f) {
                this.b = f;
                this.c = 0;
                return;
            }
            this.b = max;
            b(i2);
        }
    }

    public void b(int i) {
        int max = Math.max(0, i);
        this.c = 0;
        if (max > 0) {
            g c = this.a.c(this.b);
            if ((c instanceof y) && max <= ((y) c).h) {
                this.c = max;
            }
        }
    }

    public void p() {
        this.a = null;
        this.b = 0;
        this.c = 0;
    }

    public String toString() {
        return super.toString() + " (" + this.a + "," + this.b + "," + this.c + ")";
    }
}
