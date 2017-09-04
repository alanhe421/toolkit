package okhttp3.internal.b;

import com.tencent.connect.common.Constants;
import com.tencent.qalsdk.im_open.http;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.HttpRetryException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.HttpUrl;
import okhttp3.aa;
import okhttp3.g;
import okhttp3.i;
import okhttp3.internal.c;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.connection.f;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.s;
import okhttp3.s.a;
import okhttp3.u;
import okhttp3.w;
import okhttp3.x;
import okhttp3.y;

/* compiled from: RetryAndFollowUpInterceptor */
public final class j implements s {
    private final u a;
    private final boolean b;
    private f c;
    private Object d;
    private volatile boolean e;

    public j(u uVar, boolean z) {
        this.a = uVar;
        this.b = z;
    }

    public void a() {
        this.e = true;
        f fVar = this.c;
        if (fVar != null) {
            fVar.e();
        }
    }

    public boolean b() {
        return this.e;
    }

    public void a(Object obj) {
        this.d = obj;
    }

    public y a(a aVar) throws IOException {
        boolean z;
        w a = aVar.a();
        this.c = new f(this.a.o(), a(a.a()), this.d);
        y yVar = null;
        int i = 0;
        w wVar = a;
        while (!this.e) {
            try {
                y a2 = ((g) aVar).a(wVar, this.c, null, null);
                if (yVar != null) {
                    a2 = a2.h().c(yVar.h().a(null).a()).a();
                }
                wVar = a(a2);
                if (wVar == null) {
                    if (!this.b) {
                        this.c.c();
                    }
                    return a2;
                }
                c.a(a2.g());
                int i2 = i + 1;
                if (i2 > 20) {
                    this.c.c();
                    throw new ProtocolException("Too many follow-up requests: " + i2);
                } else if (wVar.d() instanceof l) {
                    this.c.c();
                    throw new HttpRetryException("Cannot retry streamed HTTP body", a2.b());
                } else {
                    if (!a(a2, wVar.a())) {
                        this.c.c();
                        this.c = new f(this.a.o(), a(wVar.a()), this.d);
                    } else if (this.c.a() != null) {
                        throw new IllegalStateException("Closing the body of " + a2 + " didn't close its backing stream. Bad interceptor?");
                    }
                    i = i2;
                    yVar = a2;
                }
            } catch (RouteException e) {
                if (!a(e.getLastConnectException(), false, wVar)) {
                    throw e.getLastConnectException();
                }
            } catch (IOException e2) {
                if (e2 instanceof ConnectionShutdownException) {
                    z = false;
                } else {
                    z = true;
                }
                if (!a(e2, z, wVar)) {
                    throw e2;
                }
            } catch (Throwable th) {
                this.c.a(null);
                this.c.c();
            }
        }
        this.c.c();
        throw new IOException("Canceled");
    }

    private okhttp3.a a(HttpUrl httpUrl) {
        SSLSocketFactory j;
        HostnameVerifier k;
        g gVar = null;
        if (httpUrl.c()) {
            j = this.a.j();
            k = this.a.k();
            gVar = this.a.l();
        } else {
            k = null;
            j = null;
        }
        return new okhttp3.a(httpUrl.f(), httpUrl.g(), this.a.h(), this.a.i(), j, k, gVar, this.a.n(), this.a.d(), this.a.t(), this.a.u(), this.a.e());
    }

    private boolean a(IOException iOException, boolean z, w wVar) {
        this.c.a(iOException);
        if (!this.a.r()) {
            return false;
        }
        if ((!z || !(wVar.d() instanceof l)) && a(iOException, z) && this.c.f()) {
            return true;
        }
        return false;
    }

    private boolean a(IOException iOException, boolean z) {
        boolean z2 = true;
        if (iOException instanceof ProtocolException) {
            return false;
        }
        if (iOException instanceof InterruptedIOException) {
            if (!(iOException instanceof SocketTimeoutException) || z) {
                z2 = false;
            }
            return z2;
        } else if (((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) {
            return false;
        } else {
            return true;
        }
    }

    private w a(y yVar) throws IOException {
        x xVar = null;
        if (yVar == null) {
            throw new IllegalStateException();
        }
        i b = this.c.b();
        aa a = b != null ? b.a() : null;
        int b2 = yVar.b();
        String b3 = yVar.a().b();
        switch (b2) {
            case 300:
            case 301:
            case 302:
            case 303:
                break;
            case 307:
            case 308:
                if (!(b3.equals(Constants.HTTP_GET) || b3.equals("HEAD"))) {
                    return null;
                }
            case 401:
                return this.a.m().a(a, yVar);
            case ErrorCode.INFO_CAN_NOT_LOAD_X5 /*407*/:
                Proxy b4;
                if (a != null) {
                    b4 = a.b();
                } else {
                    b4 = this.a.d();
                }
                if (b4.type() == Type.HTTP) {
                    return this.a.n().a(a, yVar);
                }
                throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
            case http.Request_Timeout /*408*/:
                if (yVar.a().d() instanceof l) {
                    return null;
                }
                return yVar.a();
            default:
                return null;
        }
        if (!this.a.q()) {
            return null;
        }
        String a2 = yVar.a("Location");
        if (a2 == null) {
            return null;
        }
        HttpUrl c = yVar.a().a().c(a2);
        if (c == null) {
            return null;
        }
        if (!c.b().equals(yVar.a().a().b()) && !this.a.p()) {
            return null;
        }
        w.a e = yVar.a().e();
        if (f.c(b3)) {
            boolean d = f.d(b3);
            if (f.e(b3)) {
                e.a(Constants.HTTP_GET, null);
            } else {
                if (d) {
                    xVar = yVar.a().d();
                }
                e.a(b3, xVar);
            }
            if (!d) {
                e.b("Transfer-Encoding");
                e.b("Content-Length");
                e.b("Content-Type");
            }
        }
        if (!a(yVar, c)) {
            e.b("Authorization");
        }
        return e.a(c).b();
    }

    private boolean a(y yVar, HttpUrl httpUrl) {
        HttpUrl a = yVar.a().a();
        return a.f().equals(httpUrl.f()) && a.g() == httpUrl.g() && a.b().equals(httpUrl.b());
    }
}
