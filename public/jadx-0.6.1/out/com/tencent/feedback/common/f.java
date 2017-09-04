package com.tencent.feedback.common;

import android.content.Context;
import com.tencent.feedback.proguard.a;
import com.tencent.feedback.proguard.p;
import com.tencent.feedback.upload.UploadHandleListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* compiled from: RQDSRC */
public final class f implements UploadHandleListener {
    private static f d = null;
    private p a;
    private p b;
    private Context c = null;

    private f(Context context) {
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
        }
        this.c = context;
        com.tencent.feedback.upload.f.a(this.c).a((UploadHandleListener) this);
        c();
        if (d() > 0) {
            e();
        }
    }

    public static synchronized f a(Context context) {
        f fVar;
        synchronized (f.class) {
            if (d == null) {
                d = new f(context);
            }
            fVar = d;
        }
        return fVar;
    }

    public static p b(Context context) {
        return a(context).a();
    }

    public static void c(Context context) {
        f a = a(context);
        long time = new Date().getTime();
        p a2 = a.a();
        if (a2 != null && a2.a() >= 0) {
            a.b(a.c, new p[]{a2});
        }
        a.a(new p(0, time, 0, 0, 0, 0, 0));
    }

    public final void onUploadStart(int i) {
    }

    public final void onUploadEnd(int i, int i2, long j, long j2, boolean z, String str) {
        e.a("rqdp{  req:}%d rqdp{  res:}%d rqdp{  send:}%d rqdp{  recv:}%d rqdp{  result:}%b rqdp{  msg:}%s", Integer.valueOf(i), Integer.valueOf(i2), Long.valueOf(j), Long.valueOf(j2), Boolean.valueOf(z), str);
        c();
        d();
        a(j, j2, g.a(this.c));
        e();
        e.b("rqdp{  [total:}%s]rqdp{  \n[today:}%s]", a(), b());
    }

    private synchronized p a() {
        return this.a;
    }

    private synchronized void a(p pVar) {
        this.a = pVar;
    }

    private synchronized p b() {
        d();
        return this.b;
    }

    private synchronized void b(p pVar) {
        this.b = pVar;
    }

    private void c() {
        List<p> a = a.a(this.c);
        if (a != null) {
            for (p pVar : a) {
                if (pVar.a == 0) {
                    a(pVar);
                } else if (pVar.a == 1) {
                    b(pVar);
                }
            }
        }
    }

    private synchronized int d() {
        int i;
        long c = a.c();
        long time = new Date().getTime();
        int i2 = 0;
        if (this.b == null || this.b.b < c) {
            this.b = new p(1, time, 0, 0, 0, 0, 0);
            i2 = 1;
        }
        if (this.a == null) {
            this.a = new p(0, time, 0, 0, 0, 0, 0);
            i = i2 + 1;
        } else {
            i = i2;
        }
        return i;
    }

    private synchronized void a(long j, long j2, boolean z) {
        long time = new Date().getTime();
        long j3 = j + j2;
        long j4 = z ? j3 : 0;
        if (z) {
            j3 = 0;
        }
        if (this.b == null) {
            this.b = new p(1, time, 1, j4, j3, j, j2);
        } else {
            long a = this.b.a();
            this.b = new p(1, this.b.b, 1 + this.b.c, this.b.d + j4, this.b.e + j3, this.b.f + j, this.b.g + j2);
            this.b.a(a);
        }
        if (this.a == null) {
            this.a = new p(0, time, 1, j4, j3, j, j2);
        } else {
            long a2 = this.a.a();
            this.a = new p(0, this.a.b, this.a.c + 1, j4 + this.a.d, j3 + this.a.e, this.a.f + j, this.a.g + j2);
            this.a.a(a2);
        }
    }

    private void e() {
        ArrayList arrayList = new ArrayList();
        p a = a();
        if (a != null) {
            arrayList.add(a);
        }
        a = b();
        if (a != null) {
            arrayList.add(a);
        }
        if (arrayList.size() > 0) {
            a.a(this.c, (p[]) arrayList.toArray(new p[arrayList.size()]));
        }
    }
}
