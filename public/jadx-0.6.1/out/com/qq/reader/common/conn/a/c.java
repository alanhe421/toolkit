package com.qq.reader.common.conn.a;

import android.content.Context;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.conn.http.b;
import com.qq.reader.common.monitor.f;

/* compiled from: OnlineTgwIpProvider */
public final class c {
    private static c a = null;
    private b b;
    private Context c;

    public static synchronized c a() {
        c cVar;
        synchronized (c.class) {
            if (a == null) {
                a = new c();
            }
            cVar = a;
        }
        return cVar;
    }

    private c() {
        this.b = null;
        this.c = null;
        this.c = ReaderApplication.getApplicationImp().getApplicationContext();
        this.b = new b(b.b(this.c), "", "dwn.reader.qq.com");
    }

    public synchronized void a(String str) {
        if (str != null) {
            if (str.length() == 0) {
                this.b = new b(b.b(this.c), "", "dwn.reader.qq.com");
            } else {
                this.b = new b(b.b(this.c), "", str);
            }
        }
    }

    public String b() {
        if ("dwn.reader.qq.com".equals(this.b.a())) {
            return "https://dwn.reader.qq.com";
        }
        return "http://" + this.b.a();
    }

    public synchronized b c() {
        if (!(this.b.c() || this.b.e())) {
            this.b = new b(b.b(this.c), "", "dwn.reader.qq.com");
        }
        return this.b;
    }

    public synchronized void a(b bVar) {
        if (bVar != null) {
            try {
                if (bVar.c()) {
                    com.qq.reader.common.monitor.debug.c.a("OKHTTP", "change https -> http");
                    this.b = new b(b.b(this.c), "", "dwn.3g.qq.com");
                } else if (bVar.d()) {
                    com.qq.reader.common.monitor.debug.c.a("OKHTTP", "change http -> https");
                    this.b = new b(b.b(this.c), "", "dwn.reader.qq.com");
                } else {
                    com.qq.reader.common.monitor.debug.c.a("OKHTTP", "change push ip -> https");
                    this.b = new b(b.b(this.c), "", "dwn.reader.qq.com");
                }
            } catch (Throwable th) {
                f.a("OnlineIpProvider", "usingIpFailed error : " + th.toString());
            }
        }
    }
}
