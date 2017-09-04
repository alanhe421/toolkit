package com.qq.reader.common.conn.http.b;

import com.qq.reader.common.monitor.debug.c;
import java.io.IOException;
import okhttp3.r;
import okhttp3.s;
import okhttp3.w;
import okhttp3.y;

/* compiled from: CmwapPayPageHandleInterceptor */
public class a implements s {
    public y a(okhttp3.s.a aVar) throws IOException {
        c.a("OKHTTP", "----------run CmwapPayPageHandleInterceptor");
        w a = aVar.a();
        y a2 = aVar.a(a);
        int i = 0;
        while (a2.c()) {
            r f = a2.f();
            if (f == null) {
                break;
            }
            String a3 = f.a("content-type");
            if (a3 == null || (a3.indexOf("text/vnd.wap.wml") == -1 && a3.indexOf("application/vnd.wap.wmlc") == -1)) {
                break;
            }
            i++;
            if (i < 2) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a2 = aVar.a(a);
            } else {
                throw new IOException("Failure with wap pay page");
            }
        }
        return a2;
    }
}
