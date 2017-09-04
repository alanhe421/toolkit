package okio;

import java.io.IOException;

/* compiled from: RealBufferedSink */
final class k implements d {
    public final c a = new c();
    public final o b;
    boolean c;

    k(o oVar) {
        if (oVar == null) {
            throw new NullPointerException("sink == null");
        }
        this.b = oVar;
    }

    public c c() {
        return this.a;
    }

    public void a_(c cVar, long j) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.a_(cVar, j);
        u();
    }

    public d b(String str) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.a(str);
        return u();
    }

    public d c(byte[] bArr) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.b(bArr);
        return u();
    }

    public d c(byte[] bArr, int i, int i2) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.b(bArr, i, i2);
        return u();
    }

    public d i(int i) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.b(i);
        return u();
    }

    public d h(int i) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.c(i);
        return u();
    }

    public d g(int i) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.d(i);
        return u();
    }

    public d k(long j) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.h(j);
        return u();
    }

    public d j(long j) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.i(j);
        return u();
    }

    public d u() throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        long g = this.a.g();
        if (g > 0) {
            this.b.a_(this.a, g);
        }
        return this;
    }

    public void flush() throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        if (this.a.b > 0) {
            this.b.a_(this.a, this.a.b);
        }
        this.b.flush();
    }

    public void close() throws IOException {
        if (!this.c) {
            Throwable th = null;
            try {
                if (this.a.b > 0) {
                    this.b.a_(this.a, this.a.b);
                }
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                this.b.close();
            } catch (Throwable th3) {
                if (th == null) {
                    th = th3;
                }
            }
            this.c = true;
            if (th != null) {
                r.a(th);
            }
        }
    }

    public q a() {
        return this.b.a();
    }

    public String toString() {
        return "buffer(" + this.b + ")";
    }
}
