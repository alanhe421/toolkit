package okhttp3.internal.c;

import com.iflytek.speech.VoiceWakeuperAidl;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl;
import okhttp3.internal.b.h;
import okhttp3.internal.b.i;
import okhttp3.internal.b.k;
import okhttp3.r;
import okhttp3.u;
import okhttp3.w;
import okhttp3.y;
import okhttp3.z;
import okio.g;
import okio.j;
import okio.o;
import okio.p;
import okio.q;

/* compiled from: Http1Codec */
public final class a implements okhttp3.internal.b.c {
    final u a;
    final okhttp3.internal.connection.f b;
    final okio.e c;
    final okio.d d;
    int e = 0;

    /* compiled from: Http1Codec */
    private abstract class a implements p {
        protected final g a;
        protected boolean b;
        final /* synthetic */ a c;

        private a(a aVar) {
            this.c = aVar;
            this.a = new g(this.c.c.a());
        }

        public q a() {
            return this.a;
        }

        protected final void a(boolean z) throws IOException {
            if (this.c.e != 6) {
                if (this.c.e != 5) {
                    throw new IllegalStateException("state: " + this.c.e);
                }
                this.c.a(this.a);
                this.c.e = 6;
                if (this.c.b != null) {
                    this.c.b.a(!z, this.c);
                }
            }
        }
    }

    /* compiled from: Http1Codec */
    private final class b implements o {
        final /* synthetic */ a a;
        private final g b = new g(this.a.d.a());
        private boolean c;

        b(a aVar) {
            this.a = aVar;
        }

        public q a() {
            return this.b;
        }

        public void a_(okio.c cVar, long j) throws IOException {
            if (this.c) {
                throw new IllegalStateException("closed");
            } else if (j != 0) {
                this.a.d.j(j);
                this.a.d.b("\r\n");
                this.a.d.a_(cVar, j);
                this.a.d.b("\r\n");
            }
        }

        public synchronized void flush() throws IOException {
            if (!this.c) {
                this.a.d.flush();
            }
        }

        public synchronized void close() throws IOException {
            if (!this.c) {
                this.c = true;
                this.a.d.b("0\r\n\r\n");
                this.a.a(this.b);
                this.a.e = 3;
            }
        }
    }

    /* compiled from: Http1Codec */
    private class c extends a {
        final /* synthetic */ a d;
        private final HttpUrl e;
        private long f = -1;
        private boolean g = true;

        c(a aVar, HttpUrl httpUrl) {
            this.d = aVar;
            super();
            this.e = httpUrl;
        }

        public long a(okio.c cVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (this.b) {
                throw new IllegalStateException("closed");
            } else if (!this.g) {
                return -1;
            } else {
                if (this.f == 0 || this.f == -1) {
                    b();
                    if (!this.g) {
                        return -1;
                    }
                }
                long a = this.d.c.a(cVar, Math.min(j, this.f));
                if (a == -1) {
                    a(false);
                    throw new ProtocolException("unexpected end of stream");
                }
                this.f -= a;
                return a;
            }
        }

        private void b() throws IOException {
            if (this.f != -1) {
                this.d.c.p();
            }
            try {
                this.f = this.d.c.m();
                String trim = this.d.c.p().trim();
                if (this.f < 0 || !(trim.isEmpty() || trim.startsWith(VoiceWakeuperAidl.PARAMS_SEPARATE))) {
                    throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.f + trim + "\"");
                } else if (this.f == 0) {
                    this.g = false;
                    okhttp3.internal.b.e.a(this.d.a.f(), this.e, this.d.d());
                    a(true);
                }
            } catch (NumberFormatException e) {
                throw new ProtocolException(e.getMessage());
            }
        }

        public void close() throws IOException {
            if (!this.b) {
                if (this.g && !okhttp3.internal.c.a((p) this, 100, TimeUnit.MILLISECONDS)) {
                    a(false);
                }
                this.b = true;
            }
        }
    }

    /* compiled from: Http1Codec */
    private final class d implements o {
        final /* synthetic */ a a;
        private final g b = new g(this.a.d.a());
        private boolean c;
        private long d;

        d(a aVar, long j) {
            this.a = aVar;
            this.d = j;
        }

        public q a() {
            return this.b;
        }

        public void a_(okio.c cVar, long j) throws IOException {
            if (this.c) {
                throw new IllegalStateException("closed");
            }
            okhttp3.internal.c.a(cVar.b(), 0, j);
            if (j > this.d) {
                throw new ProtocolException("expected " + this.d + " bytes but received " + j);
            }
            this.a.d.a_(cVar, j);
            this.d -= j;
        }

        public void flush() throws IOException {
            if (!this.c) {
                this.a.d.flush();
            }
        }

        public void close() throws IOException {
            if (!this.c) {
                this.c = true;
                if (this.d > 0) {
                    throw new ProtocolException("unexpected end of stream");
                }
                this.a.a(this.b);
                this.a.e = 3;
            }
        }
    }

    /* compiled from: Http1Codec */
    private class e extends a {
        final /* synthetic */ a d;
        private long e;

        public e(a aVar, long j) throws IOException {
            this.d = aVar;
            super();
            this.e = j;
            if (this.e == 0) {
                a(true);
            }
        }

        public long a(okio.c cVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (this.b) {
                throw new IllegalStateException("closed");
            } else if (this.e == 0) {
                return -1;
            } else {
                long a = this.d.c.a(cVar, Math.min(this.e, j));
                if (a == -1) {
                    a(false);
                    throw new ProtocolException("unexpected end of stream");
                }
                this.e -= a;
                if (this.e == 0) {
                    a(true);
                }
                return a;
            }
        }

        public void close() throws IOException {
            if (!this.b) {
                if (!(this.e == 0 || okhttp3.internal.c.a((p) this, 100, TimeUnit.MILLISECONDS))) {
                    a(false);
                }
                this.b = true;
            }
        }
    }

    /* compiled from: Http1Codec */
    private class f extends a {
        final /* synthetic */ a d;
        private boolean e;

        f(a aVar) {
            this.d = aVar;
            super();
        }

        public long a(okio.c cVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (this.b) {
                throw new IllegalStateException("closed");
            } else if (this.e) {
                return -1;
            } else {
                long a = this.d.c.a(cVar, j);
                if (a != -1) {
                    return a;
                }
                this.e = true;
                a(true);
                return -1;
            }
        }

        public void close() throws IOException {
            if (!this.b) {
                if (!this.e) {
                    a(false);
                }
                this.b = true;
            }
        }
    }

    public a(u uVar, okhttp3.internal.connection.f fVar, okio.e eVar, okio.d dVar) {
        this.a = uVar;
        this.b = fVar;
        this.c = eVar;
        this.d = dVar;
    }

    public o a(w wVar, long j) {
        if ("chunked".equalsIgnoreCase(wVar.a("Transfer-Encoding"))) {
            return e();
        }
        if (j != -1) {
            return a(j);
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }

    public void c() {
        okhttp3.internal.connection.c b = this.b.b();
        if (b != null) {
            b.b();
        }
    }

    public void a(w wVar) throws IOException {
        a(wVar.c(), i.a(wVar, this.b.b().a().b().type()));
    }

    public z a(y yVar) throws IOException {
        return new h(yVar.f(), j.a(b(yVar)));
    }

    private p b(y yVar) throws IOException {
        if (!okhttp3.internal.b.e.b(yVar)) {
            return b(0);
        }
        if ("chunked".equalsIgnoreCase(yVar.a("Transfer-Encoding"))) {
            return a(yVar.a().a());
        }
        long a = okhttp3.internal.b.e.a(yVar);
        if (a != -1) {
            return b(a);
        }
        return f();
    }

    public void a() throws IOException {
        this.d.flush();
    }

    public void b() throws IOException {
        this.d.flush();
    }

    public void a(r rVar, String str) throws IOException {
        if (this.e != 0) {
            throw new IllegalStateException("state: " + this.e);
        }
        this.d.b(str).b("\r\n");
        int a = rVar.a();
        for (int i = 0; i < a; i++) {
            this.d.b(rVar.a(i)).b(": ").b(rVar.b(i)).b("\r\n");
        }
        this.d.b("\r\n");
        this.e = 1;
    }

    public okhttp3.y.a a(boolean z) throws IOException {
        if (this.e == 1 || this.e == 3) {
            try {
                k a = k.a(this.c.p());
                okhttp3.y.a a2 = new okhttp3.y.a().a(a.a).a(a.b).a(a.c).a(d());
                if (z && a.b == 100) {
                    return null;
                }
                this.e = 4;
                return a2;
            } catch (Throwable e) {
                IOException iOException = new IOException("unexpected end of stream on " + this.b);
                iOException.initCause(e);
                throw iOException;
            }
        }
        throw new IllegalStateException("state: " + this.e);
    }

    public r d() throws IOException {
        okhttp3.r.a aVar = new okhttp3.r.a();
        while (true) {
            String p = this.c.p();
            if (p.length() == 0) {
                return aVar.a();
            }
            okhttp3.internal.a.a.a(aVar, p);
        }
    }

    public o e() {
        if (this.e != 1) {
            throw new IllegalStateException("state: " + this.e);
        }
        this.e = 2;
        return new b(this);
    }

    public o a(long j) {
        if (this.e != 1) {
            throw new IllegalStateException("state: " + this.e);
        }
        this.e = 2;
        return new d(this, j);
    }

    public p b(long j) throws IOException {
        if (this.e != 4) {
            throw new IllegalStateException("state: " + this.e);
        }
        this.e = 5;
        return new e(this, j);
    }

    public p a(HttpUrl httpUrl) throws IOException {
        if (this.e != 4) {
            throw new IllegalStateException("state: " + this.e);
        }
        this.e = 5;
        return new c(this, httpUrl);
    }

    public p f() throws IOException {
        if (this.e != 4) {
            throw new IllegalStateException("state: " + this.e);
        } else if (this.b == null) {
            throw new IllegalStateException("streamAllocation == null");
        } else {
            this.e = 5;
            this.b.d();
            return new f(this);
        }
    }

    void a(g gVar) {
        q a = gVar.a();
        gVar.a(q.b);
        a.o_();
        a.n_();
    }
}
