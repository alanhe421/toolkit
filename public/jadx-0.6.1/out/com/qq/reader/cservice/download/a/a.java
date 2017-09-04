package com.qq.reader.cservice.download.a;

import android.content.Context;
import com.qq.reader.ReaderApplication;
import java.util.Vector;

/* compiled from: DownloadManager */
public abstract class a {
    private final Object a = new Object();
    protected Vector<b> b = new Vector();
    protected final Vector<b> c = new Vector();
    protected final Vector<b> d = new Vector();
    protected final Object e = new Object();
    protected Context f = ReaderApplication.getApplicationImp();
    protected int g = 0;
    protected int h = 0;

    protected abstract void a(b bVar);

    protected abstract void a(b bVar, int i);

    protected abstract void a(b bVar, boolean z);

    protected abstract void b();

    public abstract void b(b bVar);

    protected abstract void c();

    protected abstract void c(b bVar);

    protected abstract void d(b bVar);

    protected abstract void e(b bVar);

    public a(boolean z) {
    }

    public void d() {
        for (int i = 0; i < this.d.size(); i++) {
            ((b) this.d.elementAt(i)).o();
            ((b) this.d.elementAt(i)).p();
        }
        this.b.clear();
        this.c.clear();
        this.d.clear();
    }

    public void f(b bVar) {
        synchronized (this.a) {
            int indexOf = this.b.indexOf(bVar);
        }
        if (indexOf >= 0) {
            synchronized (this.a) {
                a(bVar, indexOf);
            }
        }
    }

    public Vector<b> g() {
        Vector<b> vector;
        synchronized (this.e) {
            vector = this.b;
        }
        return vector;
    }

    public void b(b bVar, boolean z) {
        synchronized (this.e) {
            if (bVar != null) {
                if (a(this.d, bVar) == null) {
                    b a;
                    if (this.d.size() < 1) {
                        a = a(this.c, bVar);
                        if (a != null) {
                            a.a(false);
                            this.c.remove(a);
                            bVar = a;
                        }
                        h(bVar);
                    } else if (z) {
                        a = (b) this.d.elementAt(0);
                        f(a, false);
                        b a2 = a(this.c, bVar);
                        if (a2 != null) {
                            a2.a(false);
                            this.c.remove(a2);
                            bVar = a2;
                        }
                        h(bVar);
                        if (a != null) {
                            a.a(true);
                            this.c.add(a);
                        }
                    } else if (a(this.c, bVar) == null) {
                        bVar.a(true);
                        this.c.add(bVar);
                    }
                    b(bVar);
                }
            }
        }
    }

    public void c(b bVar, boolean z) {
        synchronized (this.e) {
            if (bVar != null) {
                b a = a(this.d, bVar);
                if (a != null) {
                    f(a, z);
                    a();
                } else {
                    a = a(this.c, bVar);
                    if (a != null) {
                        a.a(false);
                        this.c.remove(a);
                    }
                }
                b(a);
            }
        }
    }

    public b d(b bVar, boolean z) {
        b a;
        synchronized (this.e) {
            if (bVar != null) {
                a = a(this.b, bVar);
                if (a != null) {
                } else {
                    this.b.add(bVar);
                    synchronized (this.a) {
                        a(bVar, this.b.size() - 1);
                    }
                    if (z) {
                        if (this.d.size() < 1) {
                            h(bVar);
                        } else {
                            bVar.a(true);
                            this.c.add(bVar);
                        }
                    }
                    c(bVar);
                }
            }
            a = null;
        }
        return a;
    }

    public void h() {
        int i = 0;
        synchronized (this.e) {
            if (this.d.size() == 0) {
                return;
            }
            for (int i2 = 0; i2 < this.d.size(); i2++) {
                b bVar = (b) this.d.elementAt(i2);
                if (bVar.j() == 10) {
                    bVar.p();
                    bVar.a(true);
                    this.c.add(0, bVar);
                }
            }
            while (i < this.c.size()) {
                ((b) this.c.elementAt(i)).a(false);
                i++;
            }
            this.d.clear();
            b(null);
        }
    }

    public void i() {
        for (int i = 0; i < this.b.size(); i++) {
            b bVar = (b) this.b.elementAt(i);
            if (bVar.j() != 40) {
                bVar.a(true);
                if (!this.c.contains(bVar)) {
                    this.c.add(bVar);
                }
            }
        }
        a();
        b(null);
    }

    public void e(b bVar, boolean z) {
        boolean z2 = true;
        synchronized (this.e) {
            b a = a(this.d, bVar);
            if (a != null) {
                if (a.j() != 40) {
                    z2 = false;
                }
                if (z2) {
                    this.h++;
                    this.g++;
                    a(a);
                    this.d.remove(a);
                } else {
                    f(a, true);
                }
                a(a, z2);
                if (z) {
                    j();
                } else {
                    a();
                }
                if (this.d.size() == 0 && this.c.size() == 0) {
                    c();
                } else {
                    b(a);
                }
            }
        }
    }

    protected void j() {
        while (this.c.size() > 0) {
            b bVar = (b) this.c.remove(this.c.size() - 1);
            bVar.a(false);
            bVar.r();
        }
    }

    public void g(b bVar) {
        b(bVar);
    }

    private void h(b bVar) {
        e(bVar);
        this.d.add(bVar);
        bVar.n();
    }

    private void f(b bVar, boolean z) {
        bVar.p();
        this.d.remove(bVar);
        if (z) {
            d(bVar);
        }
    }

    private void a() {
        if (this.d.size() < 1 && this.c.size() > 0) {
            b bVar = (b) this.c.remove(0);
            bVar.a(false);
            h(bVar);
        }
    }

    private b a(Vector<b> vector, b bVar) {
        int b = b((Vector) vector, bVar);
        if (b < 0 || b >= vector.size()) {
            return null;
        }
        return (b) vector.elementAt(b);
    }

    private int b(Vector<b> vector, b bVar) {
        if (!(bVar == null || vector == null)) {
            for (int i = 0; i < vector.size(); i++) {
                if (bVar.equals(vector.elementAt(i))) {
                    return i;
                }
            }
        }
        return -1;
    }
}
