package okhttp3.internal.a;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Protocol;
import okhttp3.internal.b.e;
import okhttp3.internal.b.f;
import okhttp3.internal.b.h;
import okhttp3.internal.c;
import okhttp3.r;
import okhttp3.s;
import okhttp3.w;
import okhttp3.y;
import okio.d;
import okio.j;
import okio.o;
import okio.p;
import okio.q;

/* compiled from: CacheInterceptor */
public final class a implements s {
    final e a;

    public a(e eVar) {
        this.a = eVar;
    }

    public y a(okhttp3.s.a aVar) throws IOException {
        y yVar = null;
        y a = this.a != null ? this.a.a(aVar.a()) : yVar;
        c a2 = new okhttp3.internal.a.c.a(System.currentTimeMillis(), aVar.a(), a).a();
        w wVar = a2.a;
        y yVar2 = a2.b;
        if (this.a != null) {
            this.a.a(a2);
        }
        if (a != null && yVar2 == null) {
            c.a(a.g());
        }
        if (wVar == null && yVar2 == null) {
            return new okhttp3.y.a().a(aVar.a()).a(Protocol.HTTP_1_1).a(504).a("Unsatisfiable Request (only-if-cached)").a(c.c).a(-1).b(System.currentTimeMillis()).a();
        }
        if (wVar == null) {
            return yVar2.h().b(a(yVar2)).a();
        }
        try {
            yVar = aVar.a(wVar);
            if (yVar2 != null) {
                if (yVar.b() == 304) {
                    a = yVar2.h().a(a(yVar2.f(), yVar.f())).a(yVar.l()).b(yVar.m()).b(a(yVar2)).a(a(yVar)).a();
                    yVar.g().close();
                    this.a.a();
                    this.a.a(yVar2, a);
                    return a;
                }
                c.a(yVar2.g());
            }
            a = yVar.h().b(a(yVar2)).a(a(yVar)).a();
            if (e.b(a)) {
                return a(a(a, yVar.a(), this.a), a);
            }
            return a;
        } finally {
            if (yVar == null && a != null) {
                c.a(a.g());
            }
        }
    }

    private static y a(y yVar) {
        if (yVar == null || yVar.g() == null) {
            return yVar;
        }
        return yVar.h().a(null).a();
    }

    private b a(y yVar, w wVar, e eVar) throws IOException {
        if (eVar == null) {
            return null;
        }
        if (c.a(yVar, wVar)) {
            return eVar.a(yVar);
        }
        if (!f.a(wVar.b())) {
            return null;
        }
        try {
            eVar.b(wVar);
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    private y a(final b bVar, y yVar) throws IOException {
        if (bVar == null) {
            return yVar;
        }
        o a = bVar.a();
        if (a == null) {
            return yVar;
        }
        final okio.e c = yVar.g().c();
        final d a2 = j.a(a);
        return yVar.h().a(new h(yVar.f(), j.a(new p(this) {
            boolean a;
            final /* synthetic */ a e;

            public long a(okio.c cVar, long j) throws IOException {
                try {
                    long a = c.a(cVar, j);
                    if (a == -1) {
                        if (!this.a) {
                            this.a = true;
                            a2.close();
                        }
                        return -1;
                    }
                    cVar.a(a2.c(), cVar.b() - a, a);
                    a2.u();
                    return a;
                } catch (IOException e) {
                    if (!this.a) {
                        this.a = true;
                        bVar.b();
                    }
                    throw e;
                }
            }

            public q a() {
                return c.a();
            }

            public void close() throws IOException {
                if (!(this.a || c.a((p) this, 100, TimeUnit.MILLISECONDS))) {
                    this.a = true;
                    bVar.b();
                }
                c.close();
            }
        }))).a();
    }

    private static r a(r rVar, r rVar2) {
        int i;
        int i2 = 0;
        okhttp3.r.a aVar = new okhttp3.r.a();
        int a = rVar.a();
        for (i = 0; i < a; i++) {
            String a2 = rVar.a(i);
            String b = rVar.b(i);
            if (!("Warning".equalsIgnoreCase(a2) && b.startsWith("1")) && (!a(a2) || rVar2.a(a2) == null)) {
                okhttp3.internal.a.a.a(aVar, a2, b);
            }
        }
        i = rVar2.a();
        while (i2 < i) {
            String a3 = rVar2.a(i2);
            if (!"Content-Length".equalsIgnoreCase(a3) && a(a3)) {
                okhttp3.internal.a.a.a(aVar, a3, rVar2.b(i2));
            }
            i2++;
        }
        return aVar.a();
    }

    static boolean a(String str) {
        if ("Connection".equalsIgnoreCase(str) || "Keep-Alive".equalsIgnoreCase(str) || "Proxy-Authenticate".equalsIgnoreCase(str) || "Proxy-Authorization".equalsIgnoreCase(str) || "TE".equalsIgnoreCase(str) || "Trailers".equalsIgnoreCase(str) || "Transfer-Encoding".equalsIgnoreCase(str) || "Upgrade".equalsIgnoreCase(str)) {
            return false;
        }
        return true;
    }
}
