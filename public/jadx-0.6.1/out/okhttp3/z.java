package okhttp3;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import okhttp3.internal.c;
import okio.e;

/* compiled from: ResponseBody */
public abstract class z implements Closeable {
    public abstract t a();

    public abstract long b();

    public abstract e c();

    public final InputStream d() {
        return c().f();
    }

    public final String e() throws IOException {
        Closeable c = c();
        try {
            String a = c.a(c.a((e) c, f()));
            return a;
        } finally {
            c.a(c);
        }
    }

    private Charset f() {
        t a = a();
        return a != null ? a.a(c.e) : c.e;
    }

    public void close() {
        c.a(c());
    }

    public static z a(t tVar, byte[] bArr) {
        return a(tVar, (long) bArr.length, new okio.c().b(bArr));
    }

    public static z a(final t tVar, final long j, final e eVar) {
        if (eVar != null) {
            return new z() {
                public t a() {
                    return tVar;
                }

                public long b() {
                    return j;
                }

                public e c() {
                    return eVar;
                }
            };
        }
        throw new NullPointerException("source == null");
    }
}
