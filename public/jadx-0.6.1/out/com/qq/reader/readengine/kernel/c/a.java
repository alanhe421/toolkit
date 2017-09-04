package com.qq.reader.readengine.kernel.c;

import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.readpage.f;
import com.qq.reader.module.readpage.h;
import com.qq.reader.module.readpage.j;
import com.qq.reader.plugin.tts.l;
import com.qq.reader.readengine.fileparse.e;
import com.qq.reader.readengine.kernel.b;
import com.qq.reader.readengine.kernel.g;
import java.io.IOException;

/* compiled from: QBookCoreTxt */
public class a extends b {
    c f;

    public a() {
        this.f = new c();
        this.b = this.f;
        this.c = new l(this.b);
    }

    public void a(e eVar, int i, boolean z) {
        this.f.a(eVar, i, z);
    }

    public void a(boolean z, boolean z2) {
        if (z2 || !this.f.n() || this.f.z()) {
            this.f.a(z);
            this.f.e(z);
            return;
        }
        this.f.b(z);
    }

    public void a(j jVar) {
        this.a = jVar;
        this.f.a((f) jVar);
    }

    public int f() {
        return this.f.b();
    }

    protected void v() {
        this.f.t();
    }

    public boolean n() {
        return this.f.k();
    }

    public Mark a(int i) {
        return this.f.e(i);
    }

    public Double g() {
        return this.f.f();
    }

    public long m() {
        return this.f.w();
    }

    public boolean a(g gVar, boolean z, boolean z2, boolean z3) throws IOException {
        boolean a = this.f.a(gVar, z);
        if (!z3) {
            a(z2, true, true);
        }
        return a;
    }

    public void a(boolean z, boolean z2, boolean z3) {
        a(this.e, 0, true);
        a(z2, z3);
        if (z) {
            v();
        }
        this.f.d(true);
    }

    public boolean a(Mark mark) {
        return false;
    }

    public h o() {
        return this.f.B();
    }

    public final String i() {
        if (this.e != null && this.e.t() != null && this.e.t().getReadType() == 1) {
            return "";
        }
        if (this.e instanceof com.qq.reader.readengine.fileparse.h) {
            return "";
        }
        return super.i();
    }

    public void p() {
        this.f.c(false);
    }
}
