package com.tencent.beacon.event;

import android.content.Context;
import android.os.Build;
import android.os.Process;
import com.tencent.beacon.b.b.b;
import com.tencent.beacon.b.b.c;
import com.tencent.beacon.b.b.e;
import com.tencent.beacon.b.b.f;
import com.tencent.beacon.b.b.g;
import com.tencent.beacon.b.d;
import com.tencent.beacon.b.k;
import com.tencent.beacon.e.a;
import com.tencent.beacon.upload.InitHandleListener;
import com.tencent.beacon.upload.UploadHandleListener;
import com.tencent.beacon.upload.h;
import com.tencent.beacon.upload.i;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: ProGuard */
public final class o implements b, f, g {
    private static o e = null;
    public g a = null;
    public final Context b;
    public a c = null;
    public com.tencent.beacon.d.b d = null;
    private j f = null;
    private j g = null;
    private boolean h = true;
    private boolean i = false;
    private h j;
    private boolean k;
    private int l = 0;
    private InitHandleListener m = null;
    private List<e> n = null;
    private Object o = new Object();
    private Object p = new Object();

    static /* synthetic */ void a(o oVar) {
        com.tencent.beacon.b.a.f c = com.tencent.beacon.b.h.c(oVar.b);
        if (c != null) {
            Map hashMap = new HashMap();
            com.tencent.beacon.b.f.a(oVar.b);
            hashMap.put("A33", com.tencent.beacon.b.f.l(oVar.b));
            hashMap.put("A71", c.f);
            hashMap.put("A70", c.g);
            hashMap.put("A72", c.d);
            hashMap.put("A73", c.e);
            if (a("rqd_app_net_consumed", true, 0, 0, hashMap, true, false)) {
                com.tencent.beacon.b.h.a(oVar.b, c);
            }
        }
    }

    public static synchronized o a(Context context, h hVar, UploadHandleListener uploadHandleListener, InitHandleListener initHandleListener) {
        o oVar;
        synchronized (o.class) {
            if (e == null) {
                a.e(" create ua instance ", new Object[0]);
                e = new o(context, hVar, uploadHandleListener, initHandleListener);
            }
            a.e(" return ua instance ", new Object[0]);
            oVar = e;
        }
        return oVar;
    }

    public static synchronized o d() {
        o oVar;
        synchronized (o.class) {
            oVar = e;
        }
        return oVar;
    }

    public static synchronized h a(Context context, boolean z) {
        h a;
        synchronized (o.class) {
            a = i.a(context, z);
        }
        return a;
    }

    public final void a(boolean z) {
        c a = c.a(this.b);
        if (a != null) {
            e.a b = a.e().b(1);
            if (b != null && b.a() != z) {
                b.a(z);
                if (z != h()) {
                    f(z);
                }
            }
        }
    }

    public final void b(boolean z) {
        if (i.a(this.b) != null && z != i.a(this.b).b()) {
            if (z) {
                i.a(this.b).a(true);
                s();
                t();
                return;
            }
            i.a(this.b).a(false);
            Context context = this.b;
            com.tencent.beacon.b.c.a().a(108);
            com.tencent.beacon.b.b.a(context, "HEART_DENGTA", com.tencent.beacon.net.a.d());
            a.a("heartbeat uploaded sucess!", new Object[0]);
        }
    }

    public static void e() {
        o d = d();
        if (d == null || d.b == null) {
            a.d("onAppExited:ua is null", new Object[0]);
        } else {
            i.a(d.b, false);
            Map hashMap = new HashMap();
            com.tencent.beacon.b.f.a(d.b);
            hashMap.put("A33", com.tencent.beacon.b.f.l(d.b));
            a("rqd_appexited", true, 0, 0, hashMap, false, false);
        }
        if (d != null) {
            d.f(false);
        }
    }

    public static void c(boolean z) {
        o d = d();
        if (d != null) {
            j l = d.l();
            if (l != null) {
                l.b(z);
            }
            j m = d.m();
            if (m != null) {
                m.b(z);
            }
        }
    }

    public final void f() {
        a.a(" closeUseInfoEvent start", new Object[0]);
        try {
            this.a.j();
            com.tencent.beacon.b.c.a().a(104);
        } catch (Exception e) {
            a.d(" closeUseInfoEvent function error:" + e.getMessage(), new Object[0]);
        }
    }

    public static boolean a(String str, boolean z, long j, Map<String, String> map) {
        return a(str, z, j, 0, map, true, false);
    }

    public static boolean a(String str, boolean z, long j, long j2, Map<String, String> map, boolean z2, boolean z3) {
        a.e(" onUA: %s,%b,%d,%d,%b,%b", str, Boolean.valueOf(z), Long.valueOf(j), Long.valueOf(j2), Boolean.valueOf(z2), Boolean.valueOf(z3));
        o d = d();
        if (d != null && !d.o()) {
            d.n.add(new e(str, z, j, j2, map, z2));
            return true;
        } else if (!g()) {
            return false;
        } else {
            if (d.a.a(str)) {
                a.c("onUserAction return false, because eventName:[%s] is not allowed in server strategy!", str);
                return false;
            } else if (!z || (z && d.a.b(str))) {
                j m = z2 ? d.m() : d.l();
                if (m != null) {
                    return m.a(b.a(d.b, str, z, j, j2, map, z2, z3));
                }
                return false;
            } else {
                a.c("onUserAction return false, because eventName:[%s] is sampled by svr rate!", str);
                return false;
            }
        }
    }

    public static boolean d(boolean z) {
        o d = d();
        if (d == null) {
            a.c(" ua module not ready!", new Object[0]);
            return false;
        } else if (g()) {
            return d.e(z);
        } else {
            return false;
        }
    }

    public static boolean g() {
        o d = d();
        if (d == null) {
            a.d("isModuleAble:not init ua", new Object[0]);
            return false;
        }
        boolean h = d.h();
        if (h && d.n()) {
            h = d.o();
        }
        if (h) {
            g gVar = d.a;
            if (gVar != null && com.tencent.beacon.b.h.f(d.b).e >= ((long) gVar.f())) {
                a.c(" reach daily consume limited! %d ", Integer.valueOf(gVar.f()));
                return false;
            }
        }
        return h;
    }

    public final boolean h() {
        boolean z;
        synchronized (this.p) {
            z = this.k;
        }
        return z;
    }

    private o(Context context, h hVar, UploadHandleListener uploadHandleListener, InitHandleListener initHandleListener) {
        if (context == null) {
            a.c(" the context is null! init UserActionRecord failed!", new Object[0]);
            this.b = null;
            return;
        }
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            this.b = applicationContext;
        } else {
            this.b = context;
        }
        if (d.m() == null) {
            d.a(this.b);
        }
        if (this.n == null) {
            this.n = Collections.synchronizedList(new ArrayList(5));
        }
        this.j = hVar;
        if (hVar != null) {
            hVar.a(uploadHandleListener);
        }
        c a = c.a(this.b);
        a.a((b) this);
        a.a((f) this);
        a.a((g) this);
        a.a(0, hVar);
        a.a(1, hVar);
        this.a = new g();
        this.f = new b(context);
        this.g = new l(context);
        this.h = true;
        this.m = initHandleListener;
        this.d = new com.tencent.beacon.d.b(this.b);
        new com.tencent.beacon.b.e().a(this.b);
    }

    private synchronized j l() {
        return this.f;
    }

    private synchronized j m() {
        return this.g;
    }

    private synchronized boolean n() {
        return this.h;
    }

    private synchronized boolean o() {
        return this.i;
    }

    private synchronized void p() {
        this.i = true;
        if (c.a(this.b).a() && this.m != null) {
            this.m.onStrategyQuerySuccess();
        }
    }

    public final boolean e(boolean z) {
        synchronized (this.o) {
            if ((h() ? com.tencent.beacon.net.a.e(this.b) : -1) > 0) {
                try {
                    if (this.j != null) {
                        com.tencent.beacon.upload.a cVar = new c(this.b);
                        cVar.a(z);
                        this.j.a(cVar);
                    }
                    return true;
                } catch (Throwable th) {
                    a.c(" up common error: %s", th.toString());
                    a.a(th);
                    return false;
                }
            }
        }
    }

    public final void c() {
        Context context = this.b;
        a.a(" RecordDAO.deleteRecords() start", new Object[0]);
        int a = com.tencent.beacon.b.a.a.a(context, new int[]{1, 2, 3, 4}, Long.MAX_VALUE);
        a.e(" ua first clean :%d", Integer.valueOf(a));
        a.e(" ua remove strategy :%d", Integer.valueOf(com.tencent.beacon.net.a.d(this.b)));
    }

    private void f(boolean z) {
        synchronized (this.p) {
            if (z != h()) {
                j l = l();
                if (l != null) {
                    l.a(z);
                }
                l = m();
                if (l != null) {
                    l.a(z);
                }
                this.k = z;
            }
        }
    }

    public final h i() {
        return this.j;
    }

    public final g j() {
        return this.a;
    }

    public final void b() {
        p();
        this.d.a = this.a.x();
        try {
            e e = c.a(this.b).e();
            if (e != null) {
                e.a b = e.b(1);
                if (!(b == null || this.a == null)) {
                    Set d = b.d();
                    if (d != null && d.size() > 0) {
                        this.a.a(d);
                    }
                    d = b.g();
                    if (d != null && d.size() > 0) {
                        this.a.b(d);
                    }
                }
                if (!h() || b == null) {
                    a.b("event module is disable", new Object[0]);
                } else {
                    if ((h() ? com.tencent.beacon.net.a.e(this.b) : -1) > 0) {
                        a.e(" asyn up module %d", Integer.valueOf(1));
                        com.tencent.beacon.b.c.a().a(new Runnable(this) {
                            private /* synthetic */ o a;

                            {
                                this.a = r1;
                            }

                            public final void run() {
                                this.a.e(true);
                            }
                        });
                    }
                }
            }
        } catch (Throwable th) {
            a.a(th);
            a.d(" common query end error %s", th.toString());
        }
        q();
        if (k() < 2) {
            if (this.m != null) {
                this.m.onInitEnd();
            }
            if (i.a(this.b).a()) {
                if (com.tencent.beacon.b.i.a(this.b).a("sig_1")) {
                    a.e(" get lock %s do singleton!", "sig_1");
                    com.tencent.beacon.net.a.a("com.tencent.beacon.runinfo.AppRunInfoTask", "startAppRunMonitor", new Class[]{Context.class, Object.class}, new Object[]{this.b, this.a});
                    if (i.a(this.b).b()) {
                        s();
                        t();
                        if (this.a.E()) {
                            com.tencent.beacon.b.a.f b2 = com.tencent.beacon.b.h.b(this.b);
                            if (b2 != null) {
                                long j = b2.d + b2.e;
                                long j2 = b2.e;
                                Map hashMap = new HashMap();
                                com.tencent.beacon.b.f.a(this.b);
                                hashMap.put("A33", com.tencent.beacon.b.f.l(this.b));
                                hashMap.put("A64", j);
                                hashMap.put("A65", j2);
                                if (a("rqd_sdk_net_consumed", true, 0, 0, hashMap, true, false)) {
                                    UserAction.clearSDKTotalConsume(this.b);
                                }
                            }
                        }
                        try {
                            if (!com.tencent.beacon.net.a.d().equals(com.tencent.beacon.b.b.b(this.b, "rqd_model", ""))) {
                                com.tencent.beacon.b.c.a().a(new Runnable(this) {
                                    private /* synthetic */ o a;

                                    {
                                        this.a = r1;
                                    }

                                    public final void run() {
                                        if (d.m() == null) {
                                            a.c(" model even common info == null?,return", new Object[0]);
                                            return;
                                        }
                                        n a = n.a(this.a.b);
                                        if (a == null) {
                                            a.c(" UADeviceInfo == null?,return", new Object[0]);
                                            return;
                                        }
                                        com.tencent.beacon.b.f.a(this.a.b);
                                        Map hashMap = new HashMap();
                                        hashMap.put("A9", a.k());
                                        hashMap.put("A10", a.b());
                                        hashMap.put("A11", a.h());
                                        hashMap.put("A12", a.i());
                                        hashMap.put("A13", a.j());
                                        hashMap.put("A14", a.f());
                                        hashMap.put("A15", a.g());
                                        hashMap.put("A16", a.d());
                                        hashMap.put("A17", a.c());
                                        hashMap.put("A18", a.e());
                                        hashMap.put("A20", com.tencent.beacon.b.f.f(this.a.b));
                                        hashMap.put("A22", n.b(this.a.b));
                                        hashMap.put("A30", com.tencent.beacon.b.f.j() + "m");
                                        hashMap.put("A33", com.tencent.beacon.b.f.l(this.a.b));
                                        hashMap.put("A52", a.l());
                                        hashMap.put("A53", a.m());
                                        hashMap.put("A54", a.n());
                                        hashMap.put("A55", a.o());
                                        hashMap.put("A56", a.p());
                                        hashMap.put("A57", a.q());
                                        hashMap.put("A58", a.r());
                                        String str = "A59";
                                        StringBuilder stringBuilder = new StringBuilder();
                                        String str2 = "0";
                                        long i = com.tencent.beacon.b.f.i(this.a.b);
                                        if (i > 0) {
                                            str2 = ((i / 1024) / 1024);
                                        }
                                        hashMap.put(str, stringBuilder.append(str2).append("m").toString());
                                        hashMap.put("A69", com.tencent.beacon.b.f.g(this.a.b));
                                        hashMap.put("A82", Build.FINGERPRINT);
                                        o.a("rqd_model", true, 0, hashMap);
                                    }
                                }, 50000);
                                try {
                                    com.tencent.beacon.b.b.a(this.b, "rqd_model", com.tencent.beacon.net.a.d());
                                } catch (Throwable th2) {
                                    a.c(" save modelEvent upload flag failed! ", new Object[0]);
                                    a.a(th2);
                                }
                            }
                        } catch (Exception e2) {
                            a.c(" save modelEvent upload flag failed! ", new Object[0]);
                        }
                        if (this.a.t() || this.a.u()) {
                            this.c = new a(this.b, this.a.t(), this.a.u(), this.a.v(), this.a.w());
                            if (this.a.u()) {
                                new k(this.b).a();
                            }
                        }
                        if (this.a.p() && r()) {
                            com.tencent.beacon.b.c.a().a(new Runnable(this) {
                                private /* synthetic */ o a;

                                {
                                    this.a = r1;
                                }

                                public final void run() {
                                    o.a(this.a);
                                }
                            }, 50000);
                            int q = this.a.q();
                            if (q > 0) {
                                com.tencent.beacon.b.c.a().a(107, new Runnable(this) {
                                    private /* synthetic */ o a;

                                    {
                                        this.a = r1;
                                    }

                                    public final void run() {
                                        com.tencent.beacon.b.h.c(this.a.b);
                                    }
                                }, 0, (long) (q * 1000));
                            }
                        }
                        if (this.a.n()) {
                            new com.tencent.beacon.net.a(this.b).a();
                        }
                    }
                }
            }
        }
    }

    private synchronized void q() {
        if (this.n != null && this.n.size() > 0) {
            for (e eVar : this.n) {
                a(eVar.a, eVar.b, eVar.c, eVar.d, eVar.e, eVar.f, false);
            }
            this.n.clear();
            this.n = null;
        }
    }

    private static boolean r() {
        try {
            int myUid = Process.myUid();
            Class cls = Class.forName("android.net.TrafficStats");
            if (cls != null) {
                long longValue = ((Long) cls.getMethod("getUidRxBytes", new Class[]{Integer.TYPE}).invoke(null, new Object[]{Integer.valueOf(myUid)})).longValue();
                long longValue2 = ((Long) cls.getMethod("getUidTxBytes", new Class[]{Integer.TYPE}).invoke(null, new Object[]{Integer.valueOf(myUid)})).longValue();
                if (!(longValue == -1 || longValue2 == -1)) {
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    private void s() {
        try {
            if (h() || this.a.m()) {
                c a = c.a(this.b);
                if (a != null) {
                    e.a b = a.e().b(1);
                    if (b != null) {
                        String b2 = b.b();
                        if (b2 != null && !"".equals(b2.trim())) {
                            new i(this.b).a(false);
                        }
                    }
                }
            }
        } catch (Exception e) {
            a.c(" startHeart failed! " + e.getMessage(), new Object[0]);
        }
    }

    private void t() {
        if (com.tencent.beacon.b.f.a(this.b) == null) {
            a.c(" DeviceInfo == null?,return", new Object[0]);
            return;
        }
        Map hashMap = new HashMap();
        hashMap.put("A33", com.tencent.beacon.b.f.l(this.b));
        hashMap.put("A63", "Y");
        if (com.tencent.beacon.b.b.e(this.b)) {
            hashMap.put("A21", "Y");
        } else {
            hashMap.put("A21", "N");
        }
        if (c.a(this.b).g()) {
            hashMap.put("A45", "Y");
        } else {
            hashMap.put("A45", "N");
        }
        if (com.tencent.beacon.b.b.j(this.b)) {
            hashMap.put("A66", "F");
        } else {
            hashMap.put("A66", "B");
        }
        hashMap.put("A68", com.tencent.beacon.b.b.k(this.b));
        hashMap.put("A85", com.tencent.beacon.b.b.b ? "Y" : "N");
        a("rqd_applaunched", true, 0, 0, hashMap, true, false);
        try {
            if (this.a.l()) {
                int k = this.a.k();
                com.tencent.beacon.b.c.a().a(106, new h(this.b, k), (long) (k * 1000), (long) (k * 1000));
            }
        } catch (Exception e) {
            a.c(" startAutoLaunchEvent failed! ", new Object[0]);
            a.b(" startAutoLaunchEvent failed! " + e.getMessage(), new Object[0]);
        }
    }

    public final void a(e eVar) {
        if (eVar != null) {
            e.a b = eVar.b(1);
            if (b != null) {
                boolean a = b.a();
                if (h() != a) {
                    a.f("UAR onCommonStrategyChange setUsable:%b ", Boolean.valueOf(a));
                    f(a);
                }
            }
        }
    }

    public final void a() {
        a(k() + 1);
    }

    public final synchronized int k() {
        return this.l;
    }

    private synchronized void a(int i) {
        this.l = i;
    }

    public final void a(Map<String, String> map) {
        if (map != null && map.size() > 0 && this.a != null) {
            this.a.a((Map) map);
        }
    }
}
