package com.xiaomi.push.service;

import android.content.SharedPreferences;
import com.xiaomi.channel.commonutils.android.j;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.channel.commonutils.c.i.b;
import com.xiaomi.smack.d.e;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class v {
    private static String a;
    private static v e = new v();
    private List<a> b = new ArrayList();
    private com.xiaomi.push.b.a.a c;
    private b d;

    public static abstract class a {
        public void a(com.xiaomi.push.b.a.a aVar) {
        }

        public void a(com.xiaomi.push.b.b.b bVar) {
        }
    }

    private v() {
    }

    public static v a() {
        return e;
    }

    public static synchronized String e() {
        String str;
        synchronized (v.class) {
            if (a == null) {
                SharedPreferences sharedPreferences = j.a().getSharedPreferences("XMPushServiceConfig", 0);
                a = sharedPreferences.getString("DeviceUUID", null);
                if (a == null) {
                    a = j.b();
                    if (a != null) {
                        sharedPreferences.edit().putString("DeviceUUID", a).commit();
                    }
                }
            }
            str = a;
        }
        return str;
    }

    private void f() {
        if (this.c == null) {
            h();
        }
    }

    private void g() {
        if (this.d == null) {
            this.d = new w(this);
            e.a(this.d);
        }
    }

    private void h() {
        InputStream bufferedInputStream;
        Exception e;
        Throwable th;
        try {
            bufferedInputStream = new BufferedInputStream(j.a().openFileInput("XMCloudCfg"));
            try {
                this.c = com.xiaomi.push.b.a.a.c(com.google.protobuf.micro.b.a(bufferedInputStream));
                bufferedInputStream.close();
                com.xiaomi.channel.commonutils.a.a.a(bufferedInputStream);
            } catch (Exception e2) {
                e = e2;
                try {
                    c.a("load config failure: " + e.getMessage());
                    com.xiaomi.channel.commonutils.a.a.a(bufferedInputStream);
                    if (this.c != null) {
                        this.c = new com.xiaomi.push.b.a.a();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    com.xiaomi.channel.commonutils.a.a.a(bufferedInputStream);
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            bufferedInputStream = null;
            c.a("load config failure: " + e.getMessage());
            com.xiaomi.channel.commonutils.a.a.a(bufferedInputStream);
            if (this.c != null) {
                this.c = new com.xiaomi.push.b.a.a();
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedInputStream = null;
            com.xiaomi.channel.commonutils.a.a.a(bufferedInputStream);
            throw th;
        }
        if (this.c != null) {
            this.c = new com.xiaomi.push.b.a.a();
        }
    }

    private void i() {
        try {
            if (this.c != null) {
                OutputStream bufferedOutputStream = new BufferedOutputStream(j.a().openFileOutput("XMCloudCfg", 0));
                com.google.protobuf.micro.c a = com.google.protobuf.micro.c.a(bufferedOutputStream);
                this.c.a(a);
                a.a();
                bufferedOutputStream.close();
            }
        } catch (Exception e) {
            c.a("save config failure: " + e.getMessage());
        }
    }

    void a(com.xiaomi.push.b.b.b bVar) {
        if (bVar.i() && bVar.h() > c()) {
            g();
        }
        synchronized (this) {
        }
        for (a a : (a[]) this.b.toArray(new a[this.b.size()])) {
            a.a(bVar);
        }
    }

    public synchronized void a(a aVar) {
        this.b.add(aVar);
    }

    synchronized void b() {
        this.b.clear();
    }

    int c() {
        f();
        return this.c != null ? this.c.d() : 0;
    }

    public com.xiaomi.push.b.a.a d() {
        f();
        return this.c;
    }
}
