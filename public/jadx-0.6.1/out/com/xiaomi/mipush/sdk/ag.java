package com.xiaomi.mipush.sdk;

import android.content.Context;

public class ag {
    private static ag a = null;
    private Context b;
    private w c = new w();

    private ag(Context context) {
        this.b = context.getApplicationContext();
        if (this.b == null) {
            this.b = context;
        }
    }

    public static ag a(Context context) {
        if (a == null) {
            synchronized (ag.class) {
                if (a == null) {
                    a = new ag(context);
                }
            }
        }
        return a;
    }

    public synchronized String a() {
        return this.b.getSharedPreferences("mipush_extra", 0).getString("enable_disable_sync_status", "");
    }

    public void a(String str) {
        synchronized (this) {
            if (this.c == null) {
                this.c = new w();
            }
            this.c.a = 0;
            this.c.b = str;
        }
    }

    public void b(String str) {
        synchronized (this) {
            if (this.c == null) {
                this.c = new w();
            }
            this.c.a++;
            this.c.b = str;
        }
    }

    public int c(String str) {
        int i;
        synchronized (this) {
            if (this.c == null || !this.c.b.equals(str)) {
                i = 0;
            } else {
                i = this.c.a;
            }
        }
        return i;
    }

    public void d(String str) {
        synchronized (this) {
            if (this.c != null && this.c.b.equals(str)) {
                this.c = null;
            }
        }
    }

    public boolean e(String str) {
        boolean z;
        synchronized (this) {
            if (this.c == null || !this.c.b.equals(str)) {
                z = false;
            } else {
                z = true;
            }
        }
        return z;
    }

    public synchronized void f(String str) {
        this.b.getSharedPreferences("mipush_extra", 0).edit().putString("enable_disable_sync_status", str).commit();
    }
}
