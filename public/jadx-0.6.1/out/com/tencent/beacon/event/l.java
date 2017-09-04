package com.tencent.beacon.event;

import android.content.Context;
import com.tencent.beacon.b.b;
import com.tencent.beacon.b.c;
import com.tencent.beacon.b.f;
import com.tencent.beacon.upload.h;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: ProGuard */
public final class l implements j {
    private boolean a = false;
    private List<k> b;
    private Context c;
    private Runnable d = new Runnable(this) {
        private /* synthetic */ l a;

        {
            this.a = r1;
        }

        public final void run() {
            try {
                this.a.a();
            } catch (Throwable th) {
                com.tencent.beacon.e.a.a(th);
            }
        }
    };
    private Runnable e = new Runnable(this) {
        private /* synthetic */ l a;

        {
            this.a = r1;
        }

        public final void run() {
            this.a.d();
        }
    };

    /* compiled from: ProGuard */
    static class a extends com.tencent.beacon.upload.a {
        private List<k> f = null;
        private Context g;
        private Long[] h = null;
        private boolean i = false;

        public a(Context context, List<k> list) {
            super(context, 1, 2);
            this.f = list;
            this.g = context;
            this.e = this.f.size();
            if (this.f.size() == 1 && "rqd_heartbeat".equals(((k) this.f.get(0)).d())) {
                this.i = true;
            }
            this.d = com.tencent.beacon.net.a.b(context, 2);
            com.tencent.beacon.e.a.a("real rid:%s", this.d);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final synchronized com.tencent.beacon.c.a.b a() {
            /*
            r3 = this;
            r1 = 0;
            monitor-enter(r3);
            r0 = " TUUD.GetUD start";
            r2 = 0;
            r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x0036 }
            com.tencent.beacon.e.a.b(r0, r2);	 Catch:{ all -> 0x0036 }
            r0 = r3.f;	 Catch:{ all -> 0x0036 }
            if (r0 == 0) goto L_0x0017;
        L_0x000f:
            r0 = r3.f;	 Catch:{ all -> 0x0036 }
            r0 = r0.size();	 Catch:{ all -> 0x0036 }
            if (r0 > 0) goto L_0x001a;
        L_0x0017:
            r0 = r1;
        L_0x0018:
            monitor-exit(r3);
            return r0;
        L_0x001a:
            r0 = r3.g;	 Catch:{ Throwable -> 0x0028 }
            r0 = r3.a;	 Catch:{ Throwable -> 0x0028 }
            r2 = r3.f;	 Catch:{ Throwable -> 0x0028 }
            r0 = com.tencent.beacon.event.m.a(r0, r2);	 Catch:{ Throwable -> 0x0028 }
            if (r0 != 0) goto L_0x0018;
        L_0x0026:
            r0 = r1;
            goto L_0x0018;
        L_0x0028:
            r0 = move-exception;
            com.tencent.beacon.e.a.a(r0);	 Catch:{ all -> 0x0036 }
            r0 = " TUUD.GetUD start error";
            r2 = 0;
            r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x0036 }
            com.tencent.beacon.e.a.d(r0, r2);	 Catch:{ all -> 0x0036 }
            goto L_0x0026;
        L_0x0036:
            r0 = move-exception;
            monitor-exit(r3);
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.beacon.event.l.a.a():com.tencent.beacon.c.a.b");
        }

        public final synchronized void b(boolean z) {
            com.tencent.beacon.e.a.b(" TimeUpUploadDatas.done(), result:%b", Boolean.valueOf(z));
            if (!(this.f == null || z)) {
                com.tencent.beacon.e.a.f(" upload failed, save to db", new Object[0]);
                if (!this.i) {
                    this.h = com.tencent.beacon.net.a.a(this.g, this.f);
                    if (this.h != null) {
                        o.d().d.b(this.h.length);
                    }
                    this.f = null;
                }
            }
            if (z && this.i) {
                Context context = this.g;
                c.a().a(108);
                b.a(context, "HEART_DENGTA", com.tencent.beacon.net.a.d());
                com.tencent.beacon.e.a.a("heartbeat uploaded sucess!", new Object[0]);
            }
            if (z && this.h != null) {
                com.tencent.beacon.net.a.a(this.g, this.h);
            }
            if (z && this.f != null) {
                o.d().d.c(this.f.size());
            }
            if (z && this.h == null && this.f != null) {
                this.f = null;
            }
        }
    }

    public l(Context context) {
        this.c = context;
        this.b = Collections.synchronizedList(new ArrayList(25));
    }

    public final synchronized boolean a(k kVar) {
        boolean z = false;
        synchronized (this) {
            String str = " BF eN:%s   isRT:%b  isCR:%b";
            Object[] objArr = new Object[3];
            objArr[0] = kVar == null ? "null" : kVar.d();
            objArr[1] = Boolean.valueOf(true);
            objArr[2] = kVar == null ? "null" : Boolean.valueOf(kVar.f());
            com.tencent.beacon.e.a.f(str, objArr);
            if (this.c == null || kVar == null) {
                com.tencent.beacon.e.a.c("processUA return false, context is null or bean is null !", new Object[0]);
            } else if (c()) {
                g j = o.d().j();
                int a = j.a();
                long b = (long) (j.b() * 1000);
                if (this.b.size() >= a || kVar.j()) {
                    com.tencent.beacon.e.a.f(" BF mN!", new Object[0]);
                    c.a().a(this.d);
                    c.a().a(103, this.d, b, b);
                }
                this.b.add(kVar);
                if (this.b.size() >= a) {
                    com.tencent.beacon.e.a.c(" err BF 3R! num:" + this.b.size(), new Object[0]);
                }
                if ("rqd_applaunched".equals(kVar.d())) {
                    c.a().a(this.d);
                }
                o.d().d.b();
                com.tencent.beacon.e.a.a("processUA:true!", new Object[0]);
                z = true;
            } else {
                com.tencent.beacon.e.a.c("processUA return false, isEnable is false !", new Object[0]);
            }
        }
        return z;
    }

    private synchronized List<k> b() {
        List<k> list;
        if (this.b == null || this.b.size() <= 0 || !c()) {
            list = null;
        } else {
            list = new ArrayList();
            list.addAll(this.b);
            this.b.clear();
            com.tencent.beacon.e.a.b(" get realEventCnt in Mem:" + list.size(), new Object[0]);
        }
        return list;
    }

    private synchronized boolean c() {
        return this.a;
    }

    public final synchronized void a(boolean z) {
        if (this.a != z) {
            if (z) {
                this.a = z;
                c.a().a(103, this.d, 5000, (long) (o.d().j().b() * 1000));
            } else {
                c.a().a(103);
                b(true);
                this.a = z;
            }
        }
    }

    public final synchronized void b(boolean z) {
        com.tencent.beacon.e.a.e("realtime process flush memory objects to db.", new Object[0]);
        if (z) {
            d();
        } else {
            c.a().a(this.e);
        }
    }

    private void d() {
        List b = b();
        if (b != null && b.size() > 0) {
            com.tencent.beacon.e.a.f(" dsb real events 2 db" + b.size(), new Object[0]);
            Long[] a = com.tencent.beacon.net.a.a(this.c, b);
            if (a != null) {
                o.d().d.b(a.length);
            }
        }
    }

    protected final void a() {
        if (c()) {
            List b = b();
            if (b != null && b.size() > 0) {
                h i = o.d().i();
                g j = o.d().j();
                if (f.o(this.c) && i != null && j.o()) {
                    com.tencent.beacon.e.a.f(" dsu real events 2 up " + b.size(), new Object[0]);
                    i.a(new a(this.c, b));
                    return;
                }
                com.tencent.beacon.e.a.f(" dsu real events 2 db" + b.size(), new Object[0]);
                Long[] a = com.tencent.beacon.net.a.a(this.c, b);
                if (a != null) {
                    o.d().d.b(a.length);
                    return;
                }
                return;
            }
            return;
        }
        com.tencent.beacon.e.a.c(" err su 1R", new Object[0]);
    }
}
