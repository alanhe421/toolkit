package com.tencent.feedback.proguard;

import android.content.Context;
import com.tencent.feedback.common.a;
import com.tencent.feedback.common.b;
import com.tencent.feedback.common.c;
import com.tencent.feedback.common.e;
import com.tencent.feedback.common.g;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import com.tencent.feedback.upload.d;
import com.tencent.qalsdk.im_open.http;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* compiled from: RQDSRC */
public final class u implements Runnable {
    private static long a = 0;
    private Context b = null;

    public u(Context context) {
        this.b = context;
    }

    private static synchronized long a() {
        long j;
        synchronized (u.class) {
            j = a;
        }
        return j;
    }

    public static synchronized void a(long j) {
        synchronized (u.class) {
            a = j;
        }
    }

    private long b() {
        z a = a.a(this.b, 510);
        if (!(a == null || a.b() != 510 || a.c() == null)) {
            try {
                d c = t.a(this.b).c();
                if (c != null) {
                    c.a(510, a.c(), false);
                    e.b("rqdp{  common strategy setted by history}", new Object[0]);
                }
                return a.d();
            } catch (Throwable th) {
                if (!e.a(th)) {
                    th.printStackTrace();
                }
            }
        }
        return -1;
    }

    private void a(long j, long j2) {
        com.tencent.feedback.upload.e a = t.a();
        y a2 = y.a(this.b, b.b(), a);
        boolean b = a2.b();
        if (!b) {
            a2.a((long) BuglyBroadcastRecevier.UPLOADLIMITED);
        }
        if (new Date().getTime() < j + j2) {
            e.a("lastUpdate:%d ,return not query", Long.valueOf(j));
            return;
        }
        int i = 0;
        while (a == null) {
            i++;
            if (i >= 5) {
                break;
            }
            e.b("rqdp{  wait uphandler:} %d", Integer.valueOf(200));
            try {
                Thread.sleep(200);
            } catch (Throwable e) {
                if (!e.a(e)) {
                    e.printStackTrace();
                }
            }
            a = t.a();
        }
        if (a == null || !g.b(this.b)) {
            e.a("rqdp{  no uphandler or offline ,not query!!}", new Object[0]);
            return;
        }
        if (!b && a2.b(c.a(this.b).E()) <= 0) {
            e.b("rqdp{  wait lanch record:} %d", Integer.valueOf(http.Internal_Server_Error));
            try {
                Thread.sleep(500);
            } catch (Throwable e2) {
                if (!e.a(e2)) {
                    e2.printStackTrace();
                }
            }
        }
        S a3 = A.a(this.b, a2, (byte) 2);
        if (a3 != null) {
            e.c("create uPkg fail!", new Object[0]);
        }
        a.a(new v(this.b, 1111, 540, a3));
    }

    private void c() {
        e.a("rqdp{  AppFirstRun } %s", c.a(this.b).k());
        e.a("rqdp{  clear ao count} %d", Integer.valueOf(l.a(this.b, null, -1, Long.MAX_VALUE, -1, -1)));
        t a = t.a(this.b);
        synchronized (a) {
            s[] d = a.d();
            t.a(this.b).a(true);
        }
        if (d != null) {
            for (s f : d) {
                f.f();
            }
        }
    }

    public final void run() {
        long b = b();
        t a = t.a(this.b);
        w b2 = a.b();
        long c = b2 == null ? -1 : (long) ((b2.c() * 3600) * 1000);
        if (a.e() == 0) {
            boolean z;
            e.a("rqdp{  onlaunch}", new Object[0]);
            a.a(1);
            String a2 = a.a(this.b, c.a(this.b).b());
            if (a2 == null || a2.trim().length() == 0) {
                e.d("not found apk %s", a2);
                z = false;
            } else {
                File file = new File(a2);
                if (file.exists() && file.canRead()) {
                    long lastModified = file.lastModified();
                    long length = file.length();
                    String B = c.a(this.b).B();
                    List a3 = l.a(this.b, a2, 0, 10);
                    o oVar = null;
                    if (a3 != null && a3.size() > 0) {
                        oVar = (o) a3.get(0);
                    }
                    c a4 = c.a(this.b);
                    List arrayList;
                    o oVar2;
                    if (oVar == null) {
                        arrayList = new ArrayList();
                        oVar2 = new o();
                        oVar2.a(0);
                        oVar2.a(a2);
                        oVar2.b(a4.l());
                        oVar2.c(B);
                        oVar2.b(lastModified);
                        oVar2.c(length);
                        arrayList.add(oVar2);
                        l.c(this.b, arrayList);
                        e.a("new app %s ", oVar2.d());
                        z = true;
                    } else if (a4.m()) {
                        if (a4.l().equals(oVar.d())) {
                            z = false;
                        } else {
                            l.d(this.b, a3);
                            arrayList = new ArrayList();
                            oVar2 = new o();
                            oVar2.a(0);
                            oVar2.a(a2);
                            oVar2.b(a4.l());
                            oVar2.c(B);
                            oVar2.b(lastModified);
                            oVar2.c(length);
                            arrayList.add(oVar2);
                            l.c(this.b, arrayList);
                            e.a("new app %s ", oVar2.d());
                            z = true;
                        }
                    } else if (oVar.d() != null && B.equals(oVar.f()) && lastModified == oVar.b() && length == oVar.c()) {
                        c.a(this.b).d(oVar.d());
                        z = false;
                    } else {
                        String l = c.a(this.b).l();
                        if (l == null) {
                            e.d("rqdp{  apkid get error!return false}", new Object[0]);
                            z = false;
                        } else {
                            z = l.equals(oVar.d());
                            l.d(this.b, a3);
                            a3 = new ArrayList();
                            o oVar3 = new o();
                            oVar3.a(0);
                            oVar3.a(a2);
                            oVar3.b(l);
                            oVar3.c(B);
                            oVar3.b(lastModified);
                            oVar3.c(length);
                            a3.add(oVar3);
                            l.c(this.b, a3);
                            e.a("is new :%b %s ", Boolean.valueOf(z), oVar3.toString());
                        }
                    }
                } else {
                    e.d("apk not exist or read %s", a2);
                    z = false;
                }
            }
            if (z) {
                c();
            } else {
                long a5 = a();
                if (a5 > 0) {
                    e.b("rqdp{  delay:} %d", Long.valueOf(a5));
                    try {
                        Thread.sleep(a5);
                    } catch (Throwable e) {
                        if (!e.a(e)) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        e.b("rqdp{  on Query Start}", new Object[0]);
        synchronized (a) {
            s[] d = a.d();
            a.a(2);
        }
        if (d != null) {
            e.b("rqdp{  notify Query Start}", new Object[0]);
            for (s d2 : d) {
                d2.d();
            }
        }
        a(b, c);
        e.b("rqdp{  on query end!}", new Object[0]);
        synchronized (a) {
            d = a.d();
            a.a(3);
        }
        if (d != null) {
            e.b("rqdp{  notify Query end}", new Object[0]);
            for (s e2 : d) {
                e2.e();
            }
        }
        if (c <= 0) {
            e.c("periodLimit %d return", Long.valueOf(c));
        } else if (c > 0) {
            b.b().a(this, c);
            e.b("rqdp{  next time} %d", Long.valueOf(c));
            t.a(this.b).a(4);
        } else {
            e.b("rqdp{  stop loop query}", new Object[0]);
        }
    }
}
