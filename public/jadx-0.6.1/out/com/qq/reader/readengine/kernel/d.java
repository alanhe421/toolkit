package com.qq.reader.readengine.kernel;

import com.qq.reader.readengine.fileparse.e;
import com.qq.reader.readengine.kernel.c.b;
import java.util.ArrayList;
import java.util.List;

/* compiled from: QPageWrapper */
public abstract class d {
    protected a a = new a();
    protected g b = new g();
    protected int c = -1;
    List<a> d = new ArrayList();

    /* compiled from: QPageWrapper */
    public interface a {
        void a(b bVar);
    }

    public abstract int a();

    public abstract int a(float f);

    public abstract int a(g gVar, g gVar2);

    public abstract g a(double d);

    public abstract void a(e eVar);

    public abstract int b();

    public abstract int b(float f);

    public abstract g c();

    public abstract g d();

    public abstract int e();

    public abstract Double f();

    public abstract a g();

    public abstract String h();

    public abstract boolean i();

    public abstract void j();

    public abstract boolean k();

    protected int a(g gVar) {
        g c = c();
        g d = d();
        if (c.b(gVar) <= 0 && d.b(gVar) > 0) {
            return 0;
        }
        if (c.b(gVar) > 0) {
            return -1;
        }
        return 1;
    }

    protected void a(int i) {
        this.c = i;
    }

    public int l() {
        return this.c;
    }

    public void b(int i) {
    }

    public boolean a(a aVar) {
        return this.d.add(aVar);
    }

    protected void a(b bVar) {
        for (a a : this.d) {
            a.a(bVar);
        }
    }

    public boolean m() {
        return false;
    }
}
