package com.tencent.beacon.b.b;

import android.content.Context;
import android.util.SparseArray;
import com.tencent.beacon.e.a;
import com.tencent.beacon.upload.g;
import com.tencent.beacon.upload.h;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: ProGuard */
public final class c {
    private static c c = null;
    protected e a = null;
    protected Context b = null;
    private boolean d = false;
    private int e = 0;
    private g f = null;
    private Runnable g = null;
    private List<b> h = new ArrayList(5);
    private SparseArray<h> i = new SparseArray(5);
    private List<f> j = new ArrayList(5);
    private SparseArray<g> k = new SparseArray(2);
    private boolean l = false;
    private Runnable m = new Runnable(this) {
        private /* synthetic */ c a;

        {
            this.a = r1;
        }

        public final void run() {
            if (this.a.b != null && this.a.a != null) {
                this.a.a.a(this.a.b);
            }
        }
    };

    /* compiled from: ProGuard */
    class AnonymousClass2 implements Runnable {
        private /* synthetic */ b a;

        AnonymousClass2(b bVar) {
            this.a = bVar;
        }

        public final void run() {
            this.a.c();
        }
    }

    /* compiled from: ProGuard */
    class AnonymousClass3 implements Runnable {
        private /* synthetic */ b a;
        private /* synthetic */ int b;

        AnonymousClass3(b bVar, int i) {
            this.a = bVar;
            this.b = i;
        }

        public final void run() {
            this.a.a();
            if (this.b >= 3) {
                a.e("query finished should notify", new Object[0]);
                this.a.b();
            }
        }
    }

    public static synchronized c a(Context context) {
        c cVar;
        synchronized (c.class) {
            if (c == null && context != null) {
                c = new c(context);
            }
            cVar = c;
        }
        return cVar;
    }

    public final synchronized boolean a() {
        return this.l;
    }

    public final synchronized void b() {
        this.l = true;
    }

    public static synchronized h c() {
        h k;
        synchronized (c.class) {
            if (c != null) {
                k = c.k();
            } else {
                k = null;
            }
        }
        return k;
    }

    private c(Context context) {
        this.b = context;
        this.a = e.a();
        com.tencent.beacon.b.c.a().a(this.m);
        this.f = new a(context);
        this.g = new d(context);
        com.tencent.beacon.b.c.a().a(this.g);
    }

    public final synchronized Runnable d() {
        return this.g;
    }

    public final synchronized e e() {
        return this.a;
    }

    private synchronized h k() {
        h hVar;
        if (this.i == null || this.i.size() <= 0) {
            hVar = null;
        } else {
            hVar = (h) this.i.get(0);
        }
        return hVar;
    }

    public final synchronized void a(int i, h hVar) {
        if (this.i != null) {
            if (hVar == null) {
                this.i.remove(i);
            } else {
                this.i.put(i, hVar);
                hVar.a(101, f());
            }
        }
    }

    public final synchronized g f() {
        return this.f;
    }

    public final synchronized boolean g() {
        return this.d;
    }

    protected final synchronized void h() {
        this.d = true;
        a.f("isFirst }%b", Boolean.valueOf(true));
    }

    public final synchronized b[] i() {
        b[] bVarArr;
        if (this.h == null || this.h.size() <= 0) {
            bVarArr = null;
        } else {
            bVarArr = (b[]) this.h.toArray(new b[0]);
        }
        return bVarArr;
    }

    private synchronized f[] l() {
        f[] fVarArr;
        if (this.j == null || this.j.size() <= 0) {
            fVarArr = null;
        } else {
            fVarArr = (f[]) this.j.toArray(new f[0]);
        }
        return fVarArr;
    }

    public final synchronized int j() {
        return this.e;
    }

    public final synchronized void a(int i) {
        this.e = i;
        a.f("step:%d", Integer.valueOf(i));
    }

    public final synchronized void a(b bVar) {
        if (bVar != null) {
            if (this.h == null) {
                this.h = new ArrayList();
            }
            if (!this.h.contains(bVar)) {
                this.h.add(bVar);
                int j = j();
                if (g()) {
                    a.e("add listener should notify app first run! %s", bVar.toString());
                    com.tencent.beacon.b.c.a().a(new AnonymousClass2(bVar));
                }
                if (j >= 2) {
                    a.e("add listener should notify app start query! %s", bVar.toString());
                    com.tencent.beacon.b.c.a().a(new AnonymousClass3(bVar, j));
                }
            }
        }
    }

    public final synchronized void a(f fVar) {
        if (fVar != null) {
            if (!(this.j == null || this.j.contains(fVar))) {
                this.j.add(fVar);
            }
        }
    }

    public final synchronized void a(g gVar) {
        if (gVar != null) {
            if (this.k != null) {
                this.k.put(1, gVar);
            }
        }
    }

    private synchronized SparseArray<g> m() {
        return this.k;
    }

    public final void a(e eVar) {
        f[] l = l();
        if (l != null) {
            for (f a : l) {
                try {
                    a.a(eVar);
                } catch (Throwable th) {
                    a.a(th);
                    a.d("com strategy changed error %s", th.toString());
                }
            }
        }
    }

    public final void a(int i, Map<String, String> map) {
        SparseArray m = m();
        if (m != null) {
            g gVar = (g) m.get(i);
            if (gVar != null) {
                gVar.a(map);
            }
        }
    }
}
