package com.tencent.beacon.event;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.tencent.beacon.b.b;
import com.tencent.beacon.b.c;
import com.tencent.beacon.b.d;
import com.tencent.beacon.e.a;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: ProGuard */
public final class f implements Runnable {
    private static f a;
    private final Context b;
    private String c;
    private boolean d = false;
    private String e;
    private AtomicLong f = new AtomicLong(0);
    private AtomicLong g = new AtomicLong(0);
    private AtomicLong h = new AtomicLong(0);
    private String i;
    private boolean j = false;

    private f(Context context) {
        this.b = context;
    }

    public static synchronized f a(Context context) {
        f fVar;
        synchronized (f.class) {
            if (a == null) {
                a = new f(context);
            }
            fVar = a;
        }
        return fVar;
    }

    public final synchronized String a(String str) {
        StringBuilder stringBuilder;
        a();
        b();
        stringBuilder = new StringBuilder(this.c);
        if (str.startsWith("rqd_")) {
            stringBuilder.append("Y_");
        } else {
            stringBuilder.append("N_");
        }
        if (str.equals(this.i)) {
            stringBuilder.append(this.h.addAndGet(1));
        } else if (str.startsWith("rqd_")) {
            stringBuilder.append(this.g.addAndGet(1));
        } else {
            stringBuilder.append(this.f.addAndGet(1));
        }
        if (!this.j) {
            this.j = true;
            c.a().a((Runnable) this);
        }
        a.b("get event sn for [%s] : %s.", str, stringBuilder.toString());
        return stringBuilder.toString();
    }

    private synchronized void a() {
        if (!this.d) {
            o d = o.d();
            if (d.a != null) {
                this.i = d.a.B();
            }
            this.e = b.b(this.b, "on_ua_date", "");
            this.f.set(b.b(this.b, "on_normal_ua_times"));
            this.g.set(b.b(this.b, "on_sdk_ua_times"));
            this.h.set(b.b(this.b, "on_specified_ua_times"));
            a.a("load serial from sp, normalesn: %d, sdkesn: %d, spesn: %d", Long.valueOf(this.f.get()), Long.valueOf(this.g.get()), Long.valueOf(this.h.get()));
            c();
            this.d = true;
        }
    }

    private synchronized void b() {
        if (!com.tencent.beacon.net.a.d().equals(this.e)) {
            this.f.set(0);
            this.g.set(0);
            this.h.set(0);
            this.e = com.tencent.beacon.net.a.d();
            c();
            if (!this.j) {
                this.j = true;
                c.a().a((Runnable) this);
            }
        }
    }

    private synchronized void c() {
        d m = d.m();
        com.tencent.beacon.b.f a = com.tencent.beacon.b.f.a(this.b);
        if (m == null || a == null) {
            this.c = "error_";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(m.j());
            stringBuilder.append("_");
            stringBuilder.append(m.i());
            stringBuilder.append("_");
            stringBuilder.append(com.tencent.beacon.b.f.e(this.b));
            stringBuilder = new StringBuilder(com.tencent.beacon.net.a.h(stringBuilder.toString()));
            stringBuilder.append("_");
            stringBuilder.append(this.e.replace("-", ""));
            stringBuilder.append("_");
            this.c = stringBuilder.toString();
        }
    }

    public final void run() {
        if (this.j) {
            Editor m = b.m(this.b);
            m.putString("on_ua_date", this.e);
            m.putLong("on_normal_ua_times", this.f.get());
            m.putLong("on_sdk_ua_times", this.g.get());
            m.putLong("on_specified_ua_times", this.h.get());
            m.commit();
            this.j = false;
        }
    }
}
