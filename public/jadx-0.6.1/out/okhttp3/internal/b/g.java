package okhttp3.internal.b;

import java.io.IOException;
import java.util.List;
import okhttp3.HttpUrl;
import okhttp3.i;
import okhttp3.internal.connection.f;
import okhttp3.s;
import okhttp3.s.a;
import okhttp3.w;
import okhttp3.y;

/* compiled from: RealInterceptorChain */
public final class g implements a {
    private final List<s> a;
    private final f b;
    private final c c;
    private final i d;
    private final int e;
    private final w f;
    private int g;

    public g(List<s> list, f fVar, c cVar, i iVar, int i, w wVar) {
        this.a = list;
        this.d = iVar;
        this.b = fVar;
        this.c = cVar;
        this.e = i;
        this.f = wVar;
    }

    public f b() {
        return this.b;
    }

    public c c() {
        return this.c;
    }

    public w a() {
        return this.f;
    }

    public y a(w wVar) throws IOException {
        return a(wVar, this.b, this.c, this.d);
    }

    public y a(w wVar, f fVar, c cVar, i iVar) throws IOException {
        if (this.e >= this.a.size()) {
            throw new AssertionError();
        }
        this.g++;
        if (this.c != null && !a(wVar.a())) {
            throw new IllegalStateException("network interceptor " + this.a.get(this.e - 1) + " must retain the same host and port");
        } else if (this.c == null || this.g <= 1) {
            Object gVar = new g(this.a, fVar, cVar, iVar, this.e + 1, wVar);
            s sVar = (s) this.a.get(this.e);
            y a = sVar.a(gVar);
            if (cVar != null && this.e + 1 < this.a.size() && gVar.g != 1) {
                throw new IllegalStateException("network interceptor " + sVar + " must call proceed() exactly once");
            } else if (a != null) {
                return a;
            } else {
                throw new NullPointerException("interceptor " + sVar + " returned null");
            }
        } else {
            throw new IllegalStateException("network interceptor " + this.a.get(this.e - 1) + " must call proceed() exactly once");
        }
    }

    private boolean a(HttpUrl httpUrl) {
        return httpUrl.f().equals(this.d.a().a().a().f()) && httpUrl.g() == this.d.a().a().a().g();
    }
}
