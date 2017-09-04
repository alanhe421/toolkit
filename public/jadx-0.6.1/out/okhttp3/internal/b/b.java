package okhttp3.internal.b;

import java.io.IOException;
import java.net.ProtocolException;
import okhttp3.internal.c;
import okhttp3.internal.connection.f;
import okhttp3.s;
import okhttp3.s.a;
import okhttp3.w;
import okhttp3.y;
import okio.d;
import okio.j;

/* compiled from: CallServerInterceptor */
public final class b implements s {
    private final boolean a;

    public b(boolean z) {
        this.a = z;
    }

    public y a(a aVar) throws IOException {
        c c = ((g) aVar).c();
        f b = ((g) aVar).b();
        w a = aVar.a();
        long currentTimeMillis = System.currentTimeMillis();
        c.a(a);
        y.a aVar2 = null;
        if (f.c(a.b()) && a.d() != null) {
            if ("100-continue".equalsIgnoreCase(a.a("Expect"))) {
                c.a();
                aVar2 = c.a(true);
            }
            if (aVar2 == null) {
                d a2 = j.a(c.a(a, a.d().b()));
                a.d().a(a2);
                a2.close();
            }
        }
        c.b();
        if (aVar2 == null) {
            aVar2 = c.a(false);
        }
        y a3 = aVar2.a(a).a(b.b().d()).a(currentTimeMillis).b(System.currentTimeMillis()).a();
        int b2 = a3.b();
        if (this.a && b2 == 101) {
            a3 = a3.h().a(c.c).a();
        } else {
            a3 = a3.h().a(c.a(a3)).a();
        }
        if ("close".equalsIgnoreCase(a3.a().a("Connection")) || "close".equalsIgnoreCase(a3.a("Connection"))) {
            b.d();
        }
        if ((b2 != 204 && b2 != 205) || a3.g().b() <= 0) {
            return a3;
        }
        throw new ProtocolException("HTTP " + b2 + " had non-zero Content-Length: " + a3.g().b());
    }
}
