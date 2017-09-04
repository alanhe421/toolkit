package okhttp3.internal.b;

import java.io.IOException;
import java.util.List;
import okhttp3.internal.c;
import okhttp3.internal.d;
import okhttp3.l;
import okhttp3.m;
import okhttp3.r;
import okhttp3.s;
import okhttp3.t;
import okhttp3.w;
import okhttp3.x;
import okhttp3.y;
import okio.h;
import okio.j;
import okio.p;

/* compiled from: BridgeInterceptor */
public final class a implements s {
    private final m a;

    public a(m mVar) {
        this.a = mVar;
    }

    public y a(okhttp3.s.a aVar) throws IOException {
        boolean z = false;
        w a = aVar.a();
        okhttp3.w.a e = a.e();
        x d = a.d();
        if (d != null) {
            t a2 = d.a();
            if (a2 != null) {
                e.a("Content-Type", a2.toString());
            }
            long b = d.b();
            if (b != -1) {
                e.a("Content-Length", Long.toString(b));
                e.b("Transfer-Encoding");
            } else {
                e.a("Transfer-Encoding", "chunked");
                e.b("Content-Length");
            }
        }
        if (a.a("Host") == null) {
            e.a("Host", c.a(a.a(), false));
        }
        if (a.a("Connection") == null) {
            e.a("Connection", "Keep-Alive");
        }
        if (a.a("Accept-Encoding") == null && a.a("Range") == null) {
            z = true;
            e.a("Accept-Encoding", "gzip");
        }
        List a3 = this.a.a(a.a());
        if (!a3.isEmpty()) {
            e.a("Cookie", a(a3));
        }
        if (a.a("User-Agent") == null) {
            e.a("User-Agent", d.a());
        }
        y a4 = aVar.a(e.b());
        e.a(this.a, a.a(), a4.f());
        okhttp3.y.a a5 = a4.h().a(a);
        if (z && "gzip".equalsIgnoreCase(a4.a("Content-Encoding")) && e.b(a4)) {
            p hVar = new h(a4.g().c());
            r a6 = a4.f().b().b("Content-Encoding").b("Content-Length").a();
            a5.a(a6);
            a5.a(new h(a6, j.a(hVar)));
        }
        return a5.a();
    }

    private String a(List<l> list) {
        StringBuilder stringBuilder = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                stringBuilder.append("; ");
            }
            l lVar = (l) list.get(i);
            stringBuilder.append(lVar.a()).append('=').append(lVar.b());
        }
        return stringBuilder.toString();
    }
}
