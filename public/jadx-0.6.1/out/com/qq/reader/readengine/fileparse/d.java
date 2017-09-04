package com.qq.reader.readengine.fileparse;

import com.qq.reader.common.monitor.f;
import com.qq.reader.plugin.tts.model.a;
import com.qq.reader.readengine.kernel.g;
import java.io.IOException;
import java.io.RandomAccessFile;

/* compiled from: IReaderInput */
public abstract class d extends e {
    protected RandomAccessFile a = null;
    c b = null;
    c c = null;
    c d = null;
    protected volatile c e = null;

    public abstract long a(long j);

    public abstract g a(double d);

    public abstract boolean a(c cVar, int i);

    public abstract boolean a(g gVar);

    public abstract boolean a(g gVar, boolean z) throws IOException;

    public abstract void b(g gVar);

    public abstract boolean b(c cVar);

    public abstract boolean b(c cVar, int i);

    public abstract a c(g gVar);

    public abstract boolean c(c cVar);

    public abstract boolean i();

    public abstract boolean j() throws IOException;

    public abstract void k();

    public abstract boolean m();

    public abstract boolean n();

    public abstract g p();

    public abstract boolean q();

    public abstract a r();

    public void a() {
        this.b = null;
        this.c = null;
        this.d = null;
    }

    public c b() {
        return this.b;
    }

    public c c() {
        return this.c;
    }

    public c d() {
        return this.d;
    }

    public boolean a(boolean z, c cVar) {
        if (z) {
            this.b = cVar;
            if (this.d != null && this.d.equals(cVar)) {
                this.d = null;
                f.a("shiftBuff", "next shift");
                return true;
            }
        }
        this.b = cVar;
        if (this.c != null && this.c.equals(cVar)) {
            this.c = null;
            f.a("shiftBuff", "last shift");
            return true;
        }
        return false;
    }

    public void e() {
    }

    public boolean f() {
        return false;
    }

    public boolean g() {
        return this.b != null;
    }

    public boolean h() throws IOException {
        return false;
    }

    public boolean a(c cVar) {
        return cVar.h();
    }

    public boolean l() {
        return false;
    }

    public boolean a(int i) {
        return true;
    }

    public int o() {
        return 1;
    }

    public static final double a(g gVar, int i, long j) {
        if (gVar == null) {
            return 0.0d;
        }
        double f;
        if (i != 1) {
            f = ((double) (gVar.f() - 1)) / ((double) i);
            if (j != 0) {
                f += ((double) gVar.g()) / ((double) (((long) i) * j));
            }
        } else if (j == 0) {
            return 0.0d;
        } else {
            f = ((double) gVar.e()) / ((double) j);
        }
        if (f > 1.0d) {
            return 1.0d;
        }
        return f;
    }

    public int d(c cVar) {
        return 0;
    }
}
