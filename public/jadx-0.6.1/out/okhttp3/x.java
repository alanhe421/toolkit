package okhttp3;

import java.io.IOException;
import java.nio.charset.Charset;
import okhttp3.internal.c;
import okio.d;

/* compiled from: RequestBody */
public abstract class x {
    public abstract t a();

    public abstract void a(d dVar) throws IOException;

    public long b() throws IOException {
        return -1;
    }

    public static x a(t tVar, String str) {
        Charset charset = c.e;
        if (tVar != null) {
            charset = tVar.a();
            if (charset == null) {
                charset = c.e;
                tVar = t.a(tVar + "; charset=utf-8");
            }
        }
        return a(tVar, str.getBytes(charset));
    }

    public static x a(t tVar, byte[] bArr) {
        return a(tVar, bArr, 0, bArr.length);
    }

    public static x a(final t tVar, final byte[] bArr, final int i, final int i2) {
        if (bArr == null) {
            throw new NullPointerException("content == null");
        }
        c.a((long) bArr.length, (long) i, (long) i2);
        return new x() {
            public t a() {
                return tVar;
            }

            public long b() {
                return (long) i2;
            }

            public void a(d dVar) throws IOException {
                dVar.c(bArr, i, i2);
            }
        };
    }
}
