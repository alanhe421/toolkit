package com.qq.reader.common.conn.http.b;

import com.pay.http.APPluginErrorCode;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import java.io.IOException;
import okhttp3.s;
import okhttp3.s.a;
import okhttp3.w;
import okhttp3.y;

/* compiled from: OnlineChapterInerceptor */
public class b implements s {
    private static final int[] a = new int[0];
    private int b = 0;
    private boolean c;
    private boolean d;
    private int e = 0;
    private final int f = 5000;

    public b(boolean z, boolean z2) {
        this.d = z2;
        this.c = z;
        if (z2) {
            this.b = 1;
        } else {
            this.b = 2;
        }
    }

    private void a() {
        if (this.d) {
            this.e += 100;
        } else {
            this.e += APPluginErrorCode.ERROR_APP_SYSTEM;
        }
        if (5000 < this.e) {
            this.e = 5000;
        }
    }

    public y a(a aVar) throws IOException {
        y yVar = null;
        c.a("OKHTTP", "----------run OnlineChapterInerceptor");
        w a = aVar.a();
        yVar = aVar.a(a);
        int i = 0;
        while (!yVar.c() && i < this.b) {
            synchronized (a) {
                try {
                    a();
                    c.a("OKHTTP", "Request is not successful - trycount = " + i + "/" + this.b + "|| wait for " + this.e + "|| isForeground =" + this.d + "|| isHttp =" + this.c);
                    a.wait((long) this.e);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                c.a("OKHTTP", "Request is not successful - " + i + " " + a.a().toString());
                int i2 = i + 1;
                yVar = aVar.a(a);
                i = i2;
            } catch (Throwable e2) {
                a(yVar);
                throw new IOException(e2);
            }
        }
        a(yVar);
        return yVar;
    }

    private void a(y yVar) {
        if (yVar != null) {
            try {
                boolean c = yVar.c();
                if (this.c) {
                    i.a("EVENT_ONLINE_WITH_HTTP", c, 0, 0, null, ReaderApplication.getApplicationContext());
                }
                if (!c) {
                    f.d("OKHTTP_RDM", "------OnlineChapterInerceptor------");
                    com.qq.reader.common.conn.a.a.a().a(yVar.a().a().toString());
                }
            } catch (Exception e) {
                c.a("OKHTTP", "doRDM : " + e.toString());
            }
        }
    }
}
