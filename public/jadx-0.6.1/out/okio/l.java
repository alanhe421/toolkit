package okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/* compiled from: RealBufferedSource */
final class l implements e {
    public final c a = new c();
    public final p b;
    boolean c;

    l(p pVar) {
        if (pVar == null) {
            throw new NullPointerException("source == null");
        }
        this.b = pVar;
    }

    public c c() {
        return this.a;
    }

    public long a(c cVar, long j) throws IOException {
        if (cVar == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.c) {
            throw new IllegalStateException("closed");
        } else if (this.a.b == 0 && this.b.a(this.a, 8192) == -1) {
            return -1;
        } else {
            return this.a.a(cVar, Math.min(j, this.a.b));
        }
    }

    public boolean e() throws IOException {
        if (!this.c) {
            return this.a.e() && this.b.a(this.a, 8192) == -1;
        } else {
            throw new IllegalStateException("closed");
        }
    }

    public void a(long j) throws IOException {
        if (!b(j)) {
            throw new EOFException();
        }
    }

    public boolean b(long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.c) {
            throw new IllegalStateException("closed");
        } else {
            while (this.a.b < j) {
                if (this.b.a(this.a, 8192) == -1) {
                    return false;
                }
            }
            return true;
        }
    }

    public byte h() throws IOException {
        a(1);
        return this.a.h();
    }

    public ByteString c(long j) throws IOException {
        a(j);
        return this.a.c(j);
    }

    public byte[] f(long j) throws IOException {
        a(j);
        return this.a.f(j);
    }

    public String a(Charset charset) throws IOException {
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        this.a.a(this.b);
        return this.a.a(charset);
    }

    public String p() throws IOException {
        long a = a((byte) 10);
        if (a != -1) {
            return this.a.e(a);
        }
        c cVar = new c();
        this.a.a(cVar, 0, Math.min(32, this.a.b()));
        throw new EOFException("\\n not found: size=" + this.a.b() + " content=" + cVar.n().hex() + "…");
    }

    public short i() throws IOException {
        a(2);
        return this.a.i();
    }

    public short k() throws IOException {
        a(2);
        return this.a.k();
    }

    public int j() throws IOException {
        a(4);
        return this.a.j();
    }

    public int l() throws IOException {
        a(4);
        return this.a.l();
    }

    public long m() throws IOException {
        a(1);
        for (int i = 0; b((long) (i + 1)); i++) {
            byte b = this.a.b((long) i);
            if ((b < (byte) 48 || b > (byte) 57) && ((b < (byte) 97 || b > (byte) 102) && (b < (byte) 65 || b > (byte) 70))) {
                if (i == 0) {
                    throw new NumberFormatException(String.format("Expected leading [0-9a-fA-F] character but was %#x", new Object[]{Byte.valueOf(b)}));
                }
                return this.a.m();
            }
        }
        return this.a.m();
    }

    public void g(long j) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        while (j > 0) {
            if (this.a.b == 0 && this.b.a(this.a, 8192) == -1) {
                throw new EOFException();
            }
            long min = Math.min(j, this.a.b());
            this.a.g(min);
            j -= min;
        }
    }

    public long a(byte b) throws IOException {
        return a(b, 0);
    }

    public long a(byte b, long j) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            long a = this.a.a(b, j);
            if (a != -1) {
                return a;
            }
            a = this.a.b;
            if (this.b.a(this.a, 8192) == -1) {
                return -1;
            }
            j = Math.max(j, a);
        }
    }

    public boolean a(long j, ByteString byteString) throws IOException {
        return a(j, byteString, 0, byteString.size());
    }

    public boolean a(long j, ByteString byteString, int i, int i2) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        } else if (j < 0 || i < 0 || i2 < 0 || byteString.size() - i < i2) {
            return false;
        } else {
            int i3 = 0;
            while (i3 < i2) {
                long j2 = ((long) i3) + j;
                if (!b(1 + j2) || this.a.b(j2) != byteString.getByte(i + i3)) {
                    return false;
                }
                i3++;
            }
            return true;
        }
    }

    public InputStream f() {
        return new InputStream(this) {
            final /* synthetic */ l a;

            {
                this.a = r1;
            }

            public int read() throws IOException {
                if (this.a.c) {
                    throw new IOException("closed");
                } else if (this.a.a.b == 0 && this.a.b.a(this.a.a, 8192) == -1) {
                    return -1;
                } else {
                    return this.a.a.h() & 255;
                }
            }

            public int read(byte[] bArr, int i, int i2) throws IOException {
                if (this.a.c) {
                    throw new IOException("closed");
                }
                r.a((long) bArr.length, (long) i, (long) i2);
                if (this.a.a.b == 0 && this.a.b.a(this.a.a, 8192) == -1) {
                    return -1;
                }
                return this.a.a.a(bArr, i, i2);
            }

            public int available() throws IOException {
                if (!this.a.c) {
                    return (int) Math.min(this.a.a.b, 2147483647L);
                }
                throw new IOException("closed");
            }

            public void close() throws IOException {
                this.a.close();
            }

            public String toString() {
                return this.a + ".inputStream()";
            }
        };
    }

    public void close() throws IOException {
        if (!this.c) {
            this.c = true;
            this.b.close();
            this.a.r();
        }
    }

    public q a() {
        return this.b.a();
    }

    public String toString() {
        return "buffer(" + this.b + ")";
    }
}
