package com.tencent.android.tpush.stat;

import android.content.Context;
import android.content.IntentFilter;
import com.tencent.android.tpush.stat.a.e;
import com.tencent.android.tpush.stat.a.f;
import com.tencent.android.tpush.stat.a.h;
import org.apache.http.HttpHost;

/* compiled from: ProGuard */
public class a {
    private static volatile a d = null;
    private volatile int a = 2;
    private volatile String b = "";
    private volatile HttpHost c = null;
    private Context e = null;
    private f f = null;

    public String a() {
        return this.b;
    }

    private a(Context context) {
        this.e = context.getApplicationContext();
        f.a(context);
        this.f = e.b();
        f();
        d();
    }

    public boolean b() {
        return this.a == 1;
    }

    public boolean c() {
        return this.a != 0;
    }

    public static a a(Context context) {
        if (d == null) {
            synchronized (a.class) {
                if (d == null) {
                    d = new a(context);
                }
            }
        }
        return d;
    }

    private void f() {
        this.a = 0;
        this.c = null;
        this.b = null;
    }

    void d() {
        if (h.j(this.e)) {
            this.b = e.e(this.e);
            if (c.b()) {
                this.f.b("NETWORK name:" + this.b);
            }
            if (e.b(this.b)) {
                if ("WIFI".equalsIgnoreCase(this.b)) {
                    this.a = 1;
                } else {
                    this.a = 2;
                }
                this.c = e.a(this.e);
                return;
            }
            return;
        }
        if (c.b()) {
            this.f.b((Object) "NETWORK TYPE: network is close.");
        }
        f();
    }

    public void e() {
        this.e.getApplicationContext().registerReceiver(new b(this), new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }
}
