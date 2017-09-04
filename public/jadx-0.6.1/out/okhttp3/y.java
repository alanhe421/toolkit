package okhttp3;

import java.io.Closeable;

/* compiled from: Response */
public final class y implements Closeable {
    final w a;
    final Protocol b;
    final int c;
    final String d;
    final q e;
    final r f;
    final z g;
    final y h;
    final y i;
    final y j;
    final long k;
    final long l;
    private volatile d m;

    /* compiled from: Response */
    public static class a {
        w a;
        Protocol b;
        int c;
        String d;
        q e;
        okhttp3.r.a f;
        z g;
        y h;
        y i;
        y j;
        long k;
        long l;

        public a() {
            this.c = -1;
            this.f = new okhttp3.r.a();
        }

        a(y yVar) {
            this.c = -1;
            this.a = yVar.a;
            this.b = yVar.b;
            this.c = yVar.c;
            this.d = yVar.d;
            this.e = yVar.e;
            this.f = yVar.f.b();
            this.g = yVar.g;
            this.h = yVar.h;
            this.i = yVar.i;
            this.j = yVar.j;
            this.k = yVar.k;
            this.l = yVar.l;
        }

        public a a(w wVar) {
            this.a = wVar;
            return this;
        }

        public a a(Protocol protocol) {
            this.b = protocol;
            return this;
        }

        public a a(int i) {
            this.c = i;
            return this;
        }

        public a a(String str) {
            this.d = str;
            return this;
        }

        public a a(q qVar) {
            this.e = qVar;
            return this;
        }

        public a a(String str, String str2) {
            this.f.a(str, str2);
            return this;
        }

        public a a(r rVar) {
            this.f = rVar.b();
            return this;
        }

        public a a(z zVar) {
            this.g = zVar;
            return this;
        }

        public a a(y yVar) {
            if (yVar != null) {
                a("networkResponse", yVar);
            }
            this.h = yVar;
            return this;
        }

        public a b(y yVar) {
            if (yVar != null) {
                a("cacheResponse", yVar);
            }
            this.i = yVar;
            return this;
        }

        private void a(String str, y yVar) {
            if (yVar.g != null) {
                throw new IllegalArgumentException(str + ".body != null");
            } else if (yVar.h != null) {
                throw new IllegalArgumentException(str + ".networkResponse != null");
            } else if (yVar.i != null) {
                throw new IllegalArgumentException(str + ".cacheResponse != null");
            } else if (yVar.j != null) {
                throw new IllegalArgumentException(str + ".priorResponse != null");
            }
        }

        public a c(y yVar) {
            if (yVar != null) {
                d(yVar);
            }
            this.j = yVar;
            return this;
        }

        private void d(y yVar) {
            if (yVar.g != null) {
                throw new IllegalArgumentException("priorResponse.body != null");
            }
        }

        public a a(long j) {
            this.k = j;
            return this;
        }

        public a b(long j) {
            this.l = j;
            return this;
        }

        public y a() {
            if (this.a == null) {
                throw new IllegalStateException("request == null");
            } else if (this.b == null) {
                throw new IllegalStateException("protocol == null");
            } else if (this.c >= 0) {
                return new y(this);
            } else {
                throw new IllegalStateException("code < 0: " + this.c);
            }
        }
    }

    y(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f.a();
        this.g = aVar.g;
        this.h = aVar.h;
        this.i = aVar.i;
        this.j = aVar.j;
        this.k = aVar.k;
        this.l = aVar.l;
    }

    public w a() {
        return this.a;
    }

    public int b() {
        return this.c;
    }

    public boolean c() {
        return this.c >= 200 && this.c < 300;
    }

    public String d() {
        return this.d;
    }

    public q e() {
        return this.e;
    }

    public String a(String str) {
        return a(str, null);
    }

    public String a(String str, String str2) {
        String a = this.f.a(str);
        return a != null ? a : str2;
    }

    public r f() {
        return this.f;
    }

    public z g() {
        return this.g;
    }

    public a h() {
        return new a(this);
    }

    public y i() {
        return this.h;
    }

    public y j() {
        return this.i;
    }

    public d k() {
        d dVar = this.m;
        if (dVar != null) {
            return dVar;
        }
        dVar = d.a(this.f);
        this.m = dVar;
        return dVar;
    }

    public long l() {
        return this.k;
    }

    public long m() {
        return this.l;
    }

    public void close() {
        this.g.close();
    }

    public String toString() {
        return "Response{protocol=" + this.b + ", code=" + this.c + ", message=" + this.d + ", url=" + this.a.a() + '}';
    }
}
