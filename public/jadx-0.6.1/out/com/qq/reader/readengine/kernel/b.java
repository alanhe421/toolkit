package com.qq.reader.readengine.kernel;

import android.os.Handler;
import android.os.Message;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.readpage.h;
import com.qq.reader.module.readpage.j;
import com.qq.reader.plugin.tts.c;
import com.qq.reader.plugin.tts.m;
import com.qq.reader.plugin.tts.model.d;
import com.qq.reader.readengine.a.a;
import com.qq.reader.readengine.fileparse.e;
import java.io.IOException;

/* compiled from: QBookCore */
public abstract class b {
    protected j a;
    protected d b;
    protected m c;
    protected Handler d;
    protected e e;

    public abstract Mark a(int i);

    public abstract void a(boolean z, boolean z2, boolean z3);

    public abstract boolean a(Mark mark);

    public abstract boolean a(g gVar, boolean z, boolean z2, boolean z3) throws IOException;

    public abstract long m();

    public abstract h o();

    public boolean a() {
        return this.b.i();
    }

    public d b() {
        return this.b;
    }

    public void a(j jVar) {
        this.a = jVar;
    }

    public j c() {
        return this.a;
    }

    public void a(e eVar) {
        this.e = eVar;
        this.b.a(eVar);
        this.c.a(eVar);
    }

    public e d() {
        return this.e;
    }

    public int e() {
        int a = this.b.a();
        if (a == 1) {
            r();
        }
        return a;
    }

    public int f() {
        return this.b.b();
    }

    public int a(float f) {
        return this.b.a(f);
    }

    public int b(float f) {
        return this.b.b(f);
    }

    public Double g() {
        return this.b.f();
    }

    public g h() {
        return this.b.c();
    }

    public String i() {
        a aVar = null;
        try {
            aVar = this.b.g();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (aVar == null) {
            return "";
        }
        int a = aVar.a();
        if (a <= 0) {
            a = 1;
        }
        int b = aVar.b();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(a);
        stringBuffer.append("/");
        stringBuffer.append(b);
        return stringBuffer.toString();
    }

    public g a(double d) {
        return this.b.a(d);
    }

    public boolean c(float f) {
        if (!a()) {
            return false;
        }
        c().a(f);
        this.b.j();
        return true;
    }

    public boolean j() {
        if (!a()) {
            return false;
        }
        c().q();
        this.b.j();
        return true;
    }

    public boolean k() {
        if (!a()) {
            return false;
        }
        this.a.v();
        this.b.j();
        return true;
    }

    public int l() {
        return this.b.l();
    }

    public void b(int i) {
        int l = this.b.l();
        if (i == 2) {
            a.a(true);
        } else {
            a.a(false);
        }
        if (l != i) {
            this.b.a(i);
            if (l != -1) {
                this.b.j();
            }
            if (this.d != null) {
                Message obtain = Message.obtain();
                obtain.what = 1236;
                obtain.arg1 = i;
                this.d.sendMessage(obtain);
            }
        }
    }

    public void a(Handler handler) {
        this.d = handler;
    }

    public boolean n() {
        return this.b.k();
    }

    public void p() {
    }

    public boolean q() {
        return this.c.a();
    }

    public boolean r() {
        return this.c.b();
    }

    public void c(int i) {
        this.c.a(i);
    }

    public void a(d dVar) {
        int a = this.b.a(dVar.d(), dVar.e());
        if (a <= 0) {
            dVar.a(false);
            return;
        }
        dVar.b(a);
        dVar.a(true);
    }

    public c s() {
        return this.c.c();
    }

    public void t() {
        this.c.a(this);
    }

    public void u() {
        this.c.d();
    }
}
