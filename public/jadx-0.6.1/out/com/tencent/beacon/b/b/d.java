package com.tencent.beacon.b.b;

import android.content.Context;
import com.tencent.beacon.b.b;
import com.tencent.beacon.b.j;
import com.tencent.beacon.event.o;
import com.tencent.beacon.net.a;
import com.tencent.beacon.upload.c;
import com.tencent.beacon.upload.g;
import com.tencent.beacon.upload.h;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;

/* compiled from: ProGuard */
public final class d implements Runnable {
    private static long b = 0;
    Context a = null;

    public d(Context context) {
        this.a = context;
    }

    private static synchronized long a() {
        long j;
        synchronized (d.class) {
            j = b;
        }
        return j;
    }

    public static synchronized void a(long j) {
        synchronized (d.class) {
            b = j;
        }
    }

    public final void run() {
        b[] i;
        int i2;
        int i3;
        long a;
        h c = a.c(this.a);
        if (!(c == null || c.b() != 101 || c.c() == null)) {
            try {
                g f = c.a(this.a).f();
                if (f != null) {
                    f.a(101, c.c(), false);
                    com.tencent.beacon.e.a.e("common strategy setted by local db", new Object[0]);
                }
            } catch (Throwable th) {
                com.tencent.beacon.e.a.a(th);
            }
        }
        c a2 = c.a(this.a);
        if (a2.j() == 0) {
            com.tencent.beacon.e.a.e("stepCheckApp", new Object[0]);
            a2.a(1);
            com.tencent.beacon.e.a.e("isAppFirstRun : %b", Boolean.valueOf(b.f(this.a)));
            if (b.f(this.a)) {
                com.tencent.beacon.e.a.e("clear all ao: %d", Integer.valueOf(com.tencent.beacon.b.a.a.a(this.a, null, Long.MAX_VALUE)));
                synchronized (a2) {
                    com.tencent.beacon.e.a.e("appfirstRun", new Object[0]);
                    i = a2.i();
                    a2.h();
                }
                if (i != null) {
                    com.tencent.beacon.e.a.e("notify listener first run", new Object[0]);
                    for (b c2 : i) {
                        c2.c();
                    }
                }
            } else {
                a = a();
                if (a > 0) {
                    com.tencent.beacon.e.a.e("sleep: %d", Long.valueOf(a));
                    try {
                        Thread.sleep(a);
                    } catch (Throwable th2) {
                        com.tencent.beacon.e.a.a(th2);
                    }
                }
            }
        }
        synchronized (a2) {
            com.tencent.beacon.e.a.e("stepStartQuery", new Object[0]);
            i = a2.i();
            a2.a(2);
        }
        if (i != null) {
            com.tencent.beacon.e.a.e("notify listener query start", new Object[0]);
            for (b c22 : i) {
                c22.a();
            }
        }
        if (!e.a().j()) {
            i2 = 0;
            h c3 = c.c();
            while (c3 == null) {
                i3 = i2 + 1;
                if (i3 >= 5) {
                    break;
                }
                try {
                    Thread.sleep(200);
                } catch (Throwable th22) {
                    com.tencent.beacon.e.a.a(th22);
                }
                c3 = c.c();
                i2 = i3;
            }
            if (c3 != null) {
                Object obj;
                String b = b.b(this.a, "initsdkdate", "");
                if (!a.d().equals(b)) {
                    b.e(this.a, a.d());
                }
                boolean a3 = a(this.a, b);
                Context context = this.a;
                com.tencent.beacon.event.g gVar = o.d().a;
                if (gVar.D()) {
                    long currentTimeMillis = System.currentTimeMillis();
                    long j = ((currentTimeMillis / BuglyBroadcastRecevier.UPLOADLIMITED) + 480) % 1440;
                    if (j >= 0 && j <= 30 && currentTimeMillis - b.b(context, "last_success_strategy_query_time") <= 90000000) {
                        obj = 1;
                        if (!a3) {
                        }
                        com.tencent.beacon.e.a.c("startQuery: query times or query success times arrive max, return!", new Object[0]);
                        c.a(this.a).b();
                    } else if (!a.d().equals(b)) {
                        b.a(context, "today_success_strategy_query_times", 0);
                    } else if (b.a(context, "today_success_strategy_query_times") >= gVar.C()) {
                        obj = 1;
                        if (a3 || r0 != null) {
                            com.tencent.beacon.e.a.c("startQuery: query times or query success times arrive max, return!", new Object[0]);
                            c.a(this.a).b();
                        } else {
                            c3.a(new c(this.a));
                            try {
                                obj = e.a().g() ? 1 : null;
                                final com.tencent.beacon.e.b bVar = new com.tencent.beacon.e.b(this.a);
                                if (obj != null && bVar.b()) {
                                    obj = null;
                                }
                                if ("".equals(j.a(this.a).b())) {
                                    obj = 1;
                                }
                                if (!(obj == null || e.a().k())) {
                                    com.tencent.beacon.b.c.a().a(new Runnable(this) {
                                        private /* synthetic */ d c;

                                        public final void run() {
                                            c3.a(new com.tencent.beacon.upload.d(this.c.a));
                                            bVar.c();
                                        }
                                    });
                                }
                            } catch (Exception e) {
                                com.tencent.beacon.e.a.c("save GEN_QIMEI flag failed! ", new Object[0]);
                            }
                        }
                    }
                }
                obj = null;
                if (a3) {
                }
                com.tencent.beacon.e.a.c("startQuery: query times or query success times arrive max, return!", new Object[0]);
                c.a(this.a).b();
            } else {
                com.tencent.beacon.e.a.h("no uphandler ,no upload!", new Object[0]);
            }
        }
        com.tencent.beacon.e.a.h("common query end!", new Object[0]);
        synchronized (a2) {
            i = a2.i();
            a2.a(3);
        }
        if (i != null) {
            com.tencent.beacon.e.a.e("notify listener query end", new Object[0]);
            for (b b2 : i) {
                b2.b();
            }
        }
        e e2 = c.a(this.a).e();
        if (e2 == null) {
            com.tencent.beacon.e.a.d("magic should never null ? comStrategy", new Object[0]);
            return;
        }
        a = (long) (e2.c() * 60000);
        if (a > 0) {
            com.tencent.beacon.b.c.a().a(this, a);
            com.tencent.beacon.e.a.h("next time: %d", Long.valueOf(a));
            c.a(this.a).a(4);
            return;
        }
        com.tencent.beacon.e.a.h("stop loop query", new Object[0]);
    }

    private static boolean a(Context context, String str) {
        boolean z;
        try {
            int parseInt = Integer.parseInt(b.b(context, "querytimes", "0"));
            if (!a.d().equals(str)) {
                parseInt = 0;
            }
            if (parseInt <= e.a().i()) {
                b.d(context, (parseInt + 1));
                return false;
            }
            z = true;
            try {
                com.tencent.beacon.e.a.e(" sdk init max times", new Object[0]);
                return true;
            } catch (Exception e) {
                com.tencent.beacon.e.a.c(" set init times failed! ", new Object[0]);
                return z;
            }
        } catch (Exception e2) {
            z = false;
            com.tencent.beacon.e.a.c(" set init times failed! ", new Object[0]);
            return z;
        }
    }
}
