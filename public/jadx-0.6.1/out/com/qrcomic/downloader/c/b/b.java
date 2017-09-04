package com.qrcomic.downloader.c.b;

import com.qrcomic.util.g;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.s;
import okhttp3.t;
import okhttp3.u;
import okhttp3.u.a;
import okhttp3.w;
import okhttp3.y;

/* compiled from: OKHttpHelper */
public class b {
    public static final t a = t.a("application/json; charset=utf-8");
    private static final String b = b.class.getSimpleName();
    private static b c = null;
    private u d = new a().a(5, TimeUnit.SECONDS).b(5, TimeUnit.SECONDS).a(new s(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public y a(s.a aVar) throws IOException {
            w a = aVar.a();
            y a2 = aVar.a(a);
            int i = 0;
            while (!a2.c() && i < 3) {
                if (g.a()) {
                    g.b("OKHTTP", g.d, "Request is not successful - " + i);
                }
                i++;
                a2 = aVar.a(a);
            }
            return a2;
        }
    }).a();

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            if (c == null) {
                c = new b();
            }
            bVar = c;
        }
        return bVar;
    }

    public u a(s sVar) {
        a a = this.d.x().a(5, TimeUnit.SECONDS).b(5, TimeUnit.SECONDS).a(new s(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public y a(s.a aVar) throws IOException {
                w a = aVar.a();
                y a2 = aVar.a(a);
                int i = 0;
                while (!a2.c() && i < 3) {
                    if (g.a()) {
                        g.b("OKHTTP", g.d, "Request is not successful - " + i);
                    }
                    i++;
                    a2 = aVar.a(a);
                }
                return a2;
            }
        });
        if (sVar != null) {
            a.a(sVar);
        }
        return a.a();
    }
}
