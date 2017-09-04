package com.tencent.beacon.b;

import android.content.Context;
import com.tencent.av.config.ConfigBaseParser;
import com.tencent.beacon.e.a;
import com.tencent.beacon.event.UserAction;

/* compiled from: ProGuard */
public final class d {
    private static d n = null;
    private Context a = null;
    private String b = "";
    private String c = "";
    private byte d = (byte) -1;
    private String e = "";
    private String f = "";
    private String g = "";
    private String h = "";
    private String i = "";
    private long j = 0;
    private String k = "";
    private String l = "";
    private String m = "";

    public final String a() {
        return this.b;
    }

    public final String b() {
        return this.c;
    }

    public final synchronized byte c() {
        return this.d;
    }

    private synchronized void n() {
        this.d = (byte) 1;
    }

    private synchronized String o() {
        return this.e;
    }

    private synchronized void e(String str) {
        this.e = str;
    }

    public final synchronized String d() {
        return this.f;
    }

    public final synchronized void a(String str) {
        this.f = str;
    }

    public final synchronized String e() {
        return this.g;
    }

    private synchronized void f(String str) {
        this.g = str;
    }

    public final synchronized String f() {
        return this.h;
    }

    private synchronized void g(String str) {
        this.h = str;
    }

    public final synchronized String g() {
        return this.i;
    }

    public final synchronized void b(String str) {
        this.i = str;
    }

    public final synchronized long h() {
        return this.j;
    }

    public final synchronized void a(long j) {
        this.j = j;
    }

    public final synchronized String i() {
        if ("".equals(this.k)) {
            try {
                this.k = b.a(this.a, "IMEI_DENGTA", "imei_v2", "");
            } catch (Exception e) {
            }
        }
        return this.k;
    }

    private synchronized void h(String str) {
        if (!"".equals(str)) {
            try {
                b.c(this.a, "imei_v2", str);
            } catch (Exception e) {
            }
        }
        this.k = str;
    }

    public final synchronized String j() {
        return this.l;
    }

    public final synchronized String k() {
        return this.m;
    }

    public final synchronized void c(String str) {
        this.m = str;
    }

    public final synchronized void d(String str) {
        this.l = str;
    }

    private d() {
    }

    public final synchronized Context l() {
        return this.a;
    }

    public static synchronized void a(Context context) {
        synchronized (d.class) {
            if (context != null) {
                if (n == null) {
                    n = new d();
                }
                synchronized (n) {
                    a.e("init cominfo", new Object[0]);
                    n.a = context;
                    f.a(context);
                    n.b = f.b();
                    n.c = f.a();
                    n.n();
                    n.e(b.g(context));
                    n.a(b.h(context));
                    n.f("beacon");
                    n.g("2.4.5");
                    n.b(ConfigBaseParser.DEFAULT_VALUE);
                    n.h(f.b(context));
                    String c = b.c(context);
                    if (!"".equals(c)) {
                        n.d(c);
                    } else if (UserAction.getAppkey() == null || "".equals(UserAction.getAppkey())) {
                        n.d(n.o());
                    } else {
                        n.d(UserAction.getAppkey());
                    }
                    n.c(com.tencent.beacon.net.a.b(b.d(context)));
                }
            }
        }
    }

    public static synchronized d m() {
        d dVar;
        synchronized (d.class) {
            dVar = n;
        }
        return dVar;
    }
}
