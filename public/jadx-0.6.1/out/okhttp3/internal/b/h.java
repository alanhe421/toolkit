package okhttp3.internal.b;

import okhttp3.r;
import okhttp3.t;
import okhttp3.z;
import okio.e;

/* compiled from: RealResponseBody */
public final class h extends z {
    private final r a;
    private final e b;

    public h(r rVar, e eVar) {
        this.a = rVar;
        this.b = eVar;
    }

    public t a() {
        String a = this.a.a("Content-Type");
        return a != null ? t.a(a) : null;
    }

    public long b() {
        return e.a(this.a);
    }

    public e c() {
        return this.b;
    }
}
