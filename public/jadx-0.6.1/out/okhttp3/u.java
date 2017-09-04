package okhttp3;

import com.tencent.android.tpush.common.Constants;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.internal.a.e;
import okhttp3.internal.c;
import okhttp3.internal.connection.f;
import okhttp3.internal.f.b;
import okhttp3.internal.f.d;

/* compiled from: OkHttpClient */
public class u implements Cloneable, okhttp3.e.a {
    static final List<Protocol> a = c.a(Protocol.HTTP_2, Protocol.HTTP_1_1);
    static final List<k> b = c.a(k.a, k.b, k.c);
    final int A;
    final int B;
    final n c;
    final Proxy d;
    final List<Protocol> e;
    final List<k> f;
    final List<s> g;
    final List<s> h;
    final ProxySelector i;
    final m j;
    final c k;
    final e l;
    final SocketFactory m;
    final SSLSocketFactory n;
    final b o;
    final HostnameVerifier p;
    final g q;
    final b r;
    final b s;
    final j t;
    final o u;
    final boolean v;
    final boolean w;
    final boolean x;
    final int y;
    final int z;

    /* compiled from: OkHttpClient */
    public static final class a {
        n a;
        Proxy b;
        List<Protocol> c;
        List<k> d;
        final List<s> e;
        final List<s> f;
        ProxySelector g;
        m h;
        c i;
        e j;
        SocketFactory k;
        SSLSocketFactory l;
        b m;
        HostnameVerifier n;
        g o;
        b p;
        b q;
        j r;
        o s;
        boolean t;
        boolean u;
        boolean v;
        int w;
        int x;
        int y;
        int z;

        public a() {
            this.e = new ArrayList();
            this.f = new ArrayList();
            this.a = new n();
            this.c = u.a;
            this.d = u.b;
            this.g = ProxySelector.getDefault();
            this.h = m.a;
            this.k = SocketFactory.getDefault();
            this.n = d.a;
            this.o = g.a;
            this.p = b.a;
            this.q = b.a;
            this.r = new j();
            this.s = o.a;
            this.t = true;
            this.u = true;
            this.v = true;
            this.w = Constants.ERRORCODE_UNKNOWN;
            this.x = Constants.ERRORCODE_UNKNOWN;
            this.y = Constants.ERRORCODE_UNKNOWN;
            this.z = 0;
        }

        a(u uVar) {
            this.e = new ArrayList();
            this.f = new ArrayList();
            this.a = uVar.c;
            this.b = uVar.d;
            this.c = uVar.e;
            this.d = uVar.f;
            this.e.addAll(uVar.g);
            this.f.addAll(uVar.h);
            this.g = uVar.i;
            this.h = uVar.j;
            this.j = uVar.l;
            this.i = uVar.k;
            this.k = uVar.m;
            this.l = uVar.n;
            this.m = uVar.o;
            this.n = uVar.p;
            this.o = uVar.q;
            this.p = uVar.r;
            this.q = uVar.s;
            this.r = uVar.t;
            this.s = uVar.u;
            this.t = uVar.v;
            this.u = uVar.w;
            this.v = uVar.x;
            this.w = uVar.y;
            this.x = uVar.z;
            this.y = uVar.A;
            this.z = uVar.B;
        }

        public a a(long j, TimeUnit timeUnit) {
            this.w = a("timeout", j, timeUnit);
            return this;
        }

        public a b(long j, TimeUnit timeUnit) {
            this.x = a("timeout", j, timeUnit);
            return this;
        }

        private static int a(String str, long j, TimeUnit timeUnit) {
            if (j < 0) {
                throw new IllegalArgumentException(str + " < 0");
            } else if (timeUnit == null) {
                throw new NullPointerException("unit == null");
            } else {
                long toMillis = timeUnit.toMillis(j);
                if (toMillis > 2147483647L) {
                    throw new IllegalArgumentException(str + " too large.");
                } else if (toMillis != 0 || j <= 0) {
                    return (int) toMillis;
                } else {
                    throw new IllegalArgumentException(str + " too small.");
                }
            }
        }

        public a a(SSLSocketFactory sSLSocketFactory, X509TrustManager x509TrustManager) {
            if (sSLSocketFactory == null) {
                throw new NullPointerException("sslSocketFactory == null");
            } else if (x509TrustManager == null) {
                throw new NullPointerException("trustManager == null");
            } else {
                this.l = sSLSocketFactory;
                this.m = b.a(x509TrustManager);
                return this;
            }
        }

        public a a(HostnameVerifier hostnameVerifier) {
            if (hostnameVerifier == null) {
                throw new NullPointerException("hostnameVerifier == null");
            }
            this.n = hostnameVerifier;
            return this;
        }

        public a a(b bVar) {
            if (bVar == null) {
                throw new NullPointerException("authenticator == null");
            }
            this.q = bVar;
            return this;
        }

        public a a(s sVar) {
            this.e.add(sVar);
            return this;
        }

        public a b(s sVar) {
            this.f.add(sVar);
            return this;
        }

        public u a() {
            return new u(this);
        }
    }

    static {
        okhttp3.internal.a.a = new okhttp3.internal.a() {
            public void a(okhttp3.r.a aVar, String str) {
                aVar.a(str);
            }

            public void a(okhttp3.r.a aVar, String str, String str2) {
                aVar.b(str, str2);
            }

            public boolean a(j jVar, okhttp3.internal.connection.c cVar) {
                return jVar.b(cVar);
            }

            public okhttp3.internal.connection.c a(j jVar, a aVar, f fVar) {
                return jVar.a(aVar, fVar);
            }

            public Socket b(j jVar, a aVar, f fVar) {
                return jVar.b(aVar, fVar);
            }

            public void b(j jVar, okhttp3.internal.connection.c cVar) {
                jVar.a(cVar);
            }

            public okhttp3.internal.connection.d a(j jVar) {
                return jVar.a;
            }

            public int a(okhttp3.y.a aVar) {
                return aVar.c;
            }

            public void a(k kVar, SSLSocket sSLSocket, boolean z) {
                kVar.a(sSLSocket, z);
            }
        };
    }

    public u() {
        this(new a());
    }

    u(a aVar) {
        this.c = aVar.a;
        this.d = aVar.b;
        this.e = aVar.c;
        this.f = aVar.d;
        this.g = c.a(aVar.e);
        this.h = c.a(aVar.f);
        this.i = aVar.g;
        this.j = aVar.h;
        this.k = aVar.i;
        this.l = aVar.j;
        this.m = aVar.k;
        Object obj = null;
        for (k kVar : this.f) {
            Object obj2;
            if (obj != null || kVar.a()) {
                obj2 = 1;
            } else {
                obj2 = null;
            }
            obj = obj2;
        }
        if (aVar.l != null || obj == null) {
            this.n = aVar.l;
            this.o = aVar.m;
        } else {
            X509TrustManager y = y();
            this.n = a(y);
            this.o = b.a(y);
        }
        this.p = aVar.n;
        this.q = aVar.o.a(this.o);
        this.r = aVar.p;
        this.s = aVar.q;
        this.t = aVar.r;
        this.u = aVar.s;
        this.v = aVar.t;
        this.w = aVar.u;
        this.x = aVar.v;
        this.y = aVar.w;
        this.z = aVar.x;
        this.A = aVar.y;
        this.B = aVar.z;
    }

    private X509TrustManager y() {
        try {
            TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance.init((KeyStore) null);
            TrustManager[] trustManagers = instance.getTrustManagers();
            if (trustManagers.length == 1 && (trustManagers[0] instanceof X509TrustManager)) {
                return (X509TrustManager) trustManagers[0];
            }
            throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
        } catch (GeneralSecurityException e) {
            throw new AssertionError();
        }
    }

    private SSLSocketFactory a(X509TrustManager x509TrustManager) {
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, new TrustManager[]{x509TrustManager}, null);
            return instance.getSocketFactory();
        } catch (GeneralSecurityException e) {
            throw new AssertionError();
        }
    }

    public int a() {
        return this.y;
    }

    public int b() {
        return this.z;
    }

    public int c() {
        return this.A;
    }

    public Proxy d() {
        return this.d;
    }

    public ProxySelector e() {
        return this.i;
    }

    public m f() {
        return this.j;
    }

    e g() {
        return this.k != null ? this.k.a : this.l;
    }

    public o h() {
        return this.u;
    }

    public SocketFactory i() {
        return this.m;
    }

    public SSLSocketFactory j() {
        return this.n;
    }

    public HostnameVerifier k() {
        return this.p;
    }

    public g l() {
        return this.q;
    }

    public b m() {
        return this.s;
    }

    public b n() {
        return this.r;
    }

    public j o() {
        return this.t;
    }

    public boolean p() {
        return this.v;
    }

    public boolean q() {
        return this.w;
    }

    public boolean r() {
        return this.x;
    }

    public n s() {
        return this.c;
    }

    public List<Protocol> t() {
        return this.e;
    }

    public List<k> u() {
        return this.f;
    }

    public List<s> v() {
        return this.g;
    }

    public List<s> w() {
        return this.h;
    }

    public e a(w wVar) {
        return new v(this, wVar, false);
    }

    public a x() {
        return new a(this);
    }
}
