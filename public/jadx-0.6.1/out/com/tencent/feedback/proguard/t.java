package com.tencent.feedback.proguard;

import android.content.Context;
import android.util.SparseArray;
import com.tencent.feedback.common.b;
import com.tencent.feedback.upload.d;
import com.tencent.feedback.upload.e;
import java.util.ArrayList;
import java.util.List;

/* compiled from: RQDSRC */
public final class t {
    private static t a = null;
    private w b;
    private boolean c;
    private int d;
    private d e;
    private Runnable f;
    private List<s> g;
    private SparseArray<e> h;
    private List<x> i;

    public static synchronized t a(Context context) {
        t tVar;
        synchronized (t.class) {
            if (a == null && context != null) {
                a = new t(context);
            }
            tVar = a;
        }
        return tVar;
    }

    public static synchronized e a() {
        e f;
        synchronized (t.class) {
            if (a != null) {
                f = a.f();
            } else {
                f = null;
            }
        }
        return f;
    }

    private t(Context context) {
        this.b = null;
        this.c = false;
        this.d = 0;
        this.e = null;
        this.f = null;
        this.g = new ArrayList(5);
        this.h = new SparseArray(5);
        this.i = new ArrayList(5);
        this.b = new w();
        this.e = new r(context);
        this.f = new u(context);
        b.b().a(this.f);
    }

    public final synchronized w b() {
        return this.b;
    }

    private synchronized e f() {
        e eVar;
        if (this.h == null || this.h.size() <= 0) {
            eVar = null;
        } else {
            eVar = (e) this.h.valueAt(0);
        }
        return eVar;
    }

    public final synchronized void a(int i, e eVar) {
        if (this.h != null) {
            if (eVar == null) {
                this.h.remove(i);
            } else {
                this.h.put(i, eVar);
                eVar.a(c());
            }
        }
    }

    public final synchronized d c() {
        return this.e;
    }

    private synchronized boolean g() {
        return this.c;
    }

    protected final synchronized void a(boolean z) {
        this.c = true;
        com.tencent.feedback.common.e.b("rqdp{  isFirst }%b", Boolean.valueOf(true));
    }

    public final synchronized s[] d() {
        s[] sVarArr;
        if (this.g == null || this.g.size() <= 0) {
            sVarArr = null;
        } else {
            sVarArr = (s[]) this.g.toArray(new s[0]);
        }
        return sVarArr;
    }

    private synchronized x[] h() {
        x[] xVarArr;
        if (this.i == null || this.i.size() <= 0) {
            xVarArr = null;
        } else {
            xVarArr = (x[]) this.i.toArray(new x[0]);
        }
        return xVarArr;
    }

    public final synchronized int e() {
        return this.d;
    }

    public final synchronized void a(int i) {
        this.d = i;
        com.tencent.feedback.common.e.b("rqdp{  step }%d", Integer.valueOf(i));
    }

    public final synchronized void a(final s sVar) {
        if (sVar != null) {
            if (this.g == null) {
                this.g = new ArrayList();
            }
            if (!this.g.contains(sVar)) {
                this.g.add(sVar);
                final int e = e();
                if (g()) {
                    com.tencent.feedback.common.e.b("rqdp{  add listener should notify app first run! }%s", sVar.toString());
                    b.b().a(new Runnable(this) {
                        public final void run() {
                            sVar.f();
                        }
                    });
                }
                if (e >= 2) {
                    com.tencent.feedback.common.e.b("rqdp{  add listener should notify app start query!} %s", sVar.toString());
                    b.b().a(new Runnable(this) {
                        public final void run() {
                            sVar.d();
                            if (e >= 3) {
                                com.tencent.feedback.common.e.b("rqdp{  query finished should notify}", new Object[0]);
                                sVar.e();
                            }
                        }
                    });
                }
            }
        }
    }

    public final synchronized void b(s sVar) {
        if (!(this.g == null || sVar == null)) {
            if (this.g.contains(sVar)) {
                this.g.remove(sVar);
            }
        }
    }

    public final synchronized void a(x xVar) {
        if (xVar != null) {
            if (!(this.i == null || this.i.contains(xVar))) {
                this.i.add(xVar);
            }
        }
    }

    public final void a(w wVar) {
        x[] h = h();
        if (h != null) {
            for (x a : h) {
                try {
                    a.a(wVar);
                } catch (Throwable th) {
                    if (!com.tencent.feedback.common.e.a(th)) {
                        th.printStackTrace();
                    }
                    com.tencent.feedback.common.e.d("rqdp{  com strategy changed error }%s", th.toString());
                }
            }
        }
    }
}
