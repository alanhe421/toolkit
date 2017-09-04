package com.tencent.beacon.event;

import android.content.Context;
import com.tencent.beacon.b.b;
import com.tencent.beacon.b.f;
import com.tencent.beacon.e.a;
import com.tencent.beacon.upload.i;

/* compiled from: ProGuard */
public final class d {
    private static d a = null;
    private String b = "";
    private String c = "";
    private String d = "";
    private String e = "";
    private String f = "";

    public static synchronized d a(Context context) {
        d dVar;
        synchronized (d.class) {
            if (a == null) {
                a = new d(context);
            }
            dVar = a;
        }
        return dVar;
    }

    public final String a() {
        return this.f;
    }

    public final synchronized String b() {
        return this.b;
    }

    public final synchronized String c() {
        return this.c;
    }

    public final synchronized String d() {
        return this.d;
    }

    public final synchronized String e() {
        return this.e;
    }

    private d(Context context) {
        if (context == null) {
            a.d(" DetailUserInfo context == null? pls check!", new Object[0]);
        }
        a.b(" start to create DetailUserInfo", new Object[0]);
        long currentTimeMillis = System.currentTimeMillis();
        f.a(context);
        this.b = f.b(context);
        a.b(" imei:" + this.b, new Object[0]);
        if (!"".equals(this.b)) {
            try {
                if ("".equals(b.a(context, "IMEI_DENGTA", "imei_v2", ""))) {
                    b.c(context, "imei_v2", this.b);
                }
            } catch (Exception e) {
            }
        }
        this.c = f.e(context);
        this.d = f.c(context);
        this.e = f.d(context);
        if (i.a(context).a()) {
            this.f = f.n();
        } else {
            this.f = "";
        }
        currentTimeMillis = System.currentTimeMillis() - currentTimeMillis;
        a.b(" detail create cost: %d  values:\n %s", Long.valueOf(currentTimeMillis), toString());
    }
}
