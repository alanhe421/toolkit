package com.tencent.feedback.common;

import android.content.Context;
import com.tencent.feedback.proguard.s;
import com.tencent.feedback.proguard.t;
import com.tencent.feedback.proguard.w;
import com.tencent.feedback.proguard.w.a;
import com.tencent.feedback.proguard.x;
import com.tencent.feedback.proguard.z;
import com.tencent.feedback.upload.UploadHandleListener;
import com.tencent.feedback.upload.d;
import com.tencent.feedback.upload.e;

/* compiled from: RQDSRC */
public class i implements s, x {
    protected final Context a;
    private int b;
    private int c;
    private int d;
    private boolean e;
    private boolean f;
    private e g;
    private d h;
    private int i = 0;

    public i(Context context, String str, int i, int i2, int i3, e eVar, d dVar, UploadHandleListener uploadHandleListener) {
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
        }
        this.a = context;
        c a = c.a(this.a);
        if (str != null && str.trim().length() > 0) {
            a.a(str);
        }
        this.b = 3;
        this.c = 530;
        this.d = 510;
        this.g = eVar;
        this.h = dVar;
        if (eVar != null) {
            eVar.a(dVar);
            eVar.a(uploadHandleListener);
        }
        t a2 = t.a(this.a);
        a2.a((s) this);
        a2.a((x) this);
        a2.a(3, eVar);
    }

    public final void a(boolean z) {
        boolean z2 = true;
        t a = t.a(this.a);
        if (a != null) {
            a c = a.b().c(this.b);
            if (c != null && c.b() != z) {
                e.a("rqdp{  mid:}%d rqdp{  change user switch} %b", Integer.valueOf(this.b), Boolean.valueOf(z));
                c.a(z);
                if (!(c.b() && c.c())) {
                    z2 = false;
                }
                if (z2 != a()) {
                    b(z2);
                }
            }
        }
    }

    public final synchronized boolean a() {
        return this.e;
    }

    public synchronized void b(boolean z) {
        this.e = z;
    }

    public final synchronized boolean b() {
        return this.f;
    }

    private synchronized void c(boolean z) {
        this.f = true;
    }

    public final synchronized e c() {
        return this.g;
    }

    private synchronized d l() {
        return this.h;
    }

    public final void d() {
        e.b("rqdp{  com query start }%s", getClass().toString());
        a(k() + 1);
    }

    public void e() {
        e.b("rqdp{  com query end }%s", getClass().toString());
        if (!b()) {
            e.b("rqdp{  step after query}", new Object[0]);
            c(true);
        }
        try {
            w b = t.a(this.a).b();
            a c = b.c(this.b);
            if (!a() || c == null) {
                e.b("rqdp{  disable}", new Object[0]);
                return;
            }
            boolean z;
            e.b("rqdp{  isable}", new Object[0]);
            boolean d = c.d();
            boolean g = b.g();
            if (d) {
                if (g) {
                    d = true;
                } else if (!i()) {
                    z a = com.tencent.feedback.proguard.a.a(this.a, this.d);
                    if (a != null) {
                        d l = l();
                        if (l == null) {
                            e.c("rqdp{  imposiable eup reshandler not ready}", new Object[0]);
                        } else {
                            l.a(this.d, a.c(), false);
                        }
                    }
                    if (!i()) {
                        d = true;
                    }
                }
                e.b("rqdp{  needDetail} %b rqdp{  allQ:}%b rqdp{  result:}%b", Boolean.valueOf(c.d()), Boolean.valueOf(b.g()), Boolean.valueOf(d));
                if (g() <= 0) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    e.b("rqdp{  asyn up module} %d", Integer.valueOf(this.b));
                    b.b().a(new Runnable(this) {
                        private /* synthetic */ i a;

                        {
                            this.a = r1;
                        }

                        public final void run() {
                            this.a.h();
                        }
                    });
                } else if (d) {
                    e.b("rqdp{  asyn query module }%d", Integer.valueOf(this.b));
                    b.b().a(new Runnable(this) {
                        private /* synthetic */ i a;

                        {
                            this.a = r1;
                        }

                        public final void run() {
                            this.a.j();
                        }
                    });
                }
            }
            d = false;
            e.b("rqdp{  needDetail} %b rqdp{  allQ:}%b rqdp{  result:}%b", Boolean.valueOf(c.d()), Boolean.valueOf(b.g()), Boolean.valueOf(d));
            if (g() <= 0) {
                z = false;
            } else {
                z = true;
            }
            if (z) {
                e.b("rqdp{  asyn up module} %d", Integer.valueOf(this.b));
                b.b().a(/* anonymous class already generated */);
            } else if (d) {
                e.b("rqdp{  asyn query module }%d", Integer.valueOf(this.b));
                b.b().a(/* anonymous class already generated */);
            }
        } catch (Throwable th) {
            if (!e.a(th)) {
                th.printStackTrace();
            }
            e.d("rqdp{  common query end error} %s", th.toString());
        }
    }

    public void f() {
        e.a("rqdp{  app first start} %s", getClass().toString());
    }

    public int g() {
        if (a()) {
            return 0;
        }
        return -1;
    }

    public boolean h() {
        if (a()) {
            return true;
        }
        return false;
    }

    public boolean i() {
        return true;
    }

    public final boolean j() {
        if (!a()) {
            return false;
        }
        e c = c();
        if (c == null) {
            return false;
        }
        c.a(new com.tencent.feedback.upload.a(this.a, this.b, this.c));
        return true;
    }

    public final void a(w wVar) {
        e.b("rqdp{  com strateyg changed }%s", getClass().toString());
        if (wVar != null) {
            a c = wVar.c(this.b);
            if (c != null) {
                boolean z = c.c() && c.b();
                if (a() != z) {
                    e.a("rqdp{  module} %d rqdp{  able changed }%b", Integer.valueOf(this.b), Boolean.valueOf(z));
                    b(z);
                }
            }
        }
    }

    public final synchronized int k() {
        return this.i;
    }

    private synchronized void a(int i) {
        this.i = i;
    }
}
