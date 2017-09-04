package okhttp3.internal.a;

import com.tencent.qalsdk.im_open.http;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.util.TimeFormatterUtils;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import okhttp3.internal.b.d;
import okhttp3.internal.b.e;
import okhttp3.r;
import okhttp3.w;
import okhttp3.y;

/* compiled from: CacheStrategy */
public final class c {
    public final w a;
    public final y b;

    /* compiled from: CacheStrategy */
    public static class a {
        final long a;
        final w b;
        final y c;
        private Date d;
        private String e;
        private Date f;
        private String g;
        private Date h;
        private long i;
        private long j;
        private String k;
        private int l = -1;

        public a(long j, w wVar, y yVar) {
            this.a = j;
            this.b = wVar;
            this.c = yVar;
            if (yVar != null) {
                this.i = yVar.l();
                this.j = yVar.m();
                r f = yVar.f();
                int a = f.a();
                for (int i = 0; i < a; i++) {
                    String a2 = f.a(i);
                    String b = f.b(i);
                    if ("Date".equalsIgnoreCase(a2)) {
                        this.d = d.a(b);
                        this.e = b;
                    } else if ("Expires".equalsIgnoreCase(a2)) {
                        this.h = d.a(b);
                    } else if ("Last-Modified".equalsIgnoreCase(a2)) {
                        this.f = d.a(b);
                        this.g = b;
                    } else if ("ETag".equalsIgnoreCase(a2)) {
                        this.k = b;
                    } else if ("Age".equalsIgnoreCase(a2)) {
                        this.l = e.b(b, -1);
                    }
                }
            }
        }

        public c a() {
            c b = b();
            if (b.a == null || !this.b.f().i()) {
                return b;
            }
            return new c(null, null);
        }

        private c b() {
            long j = 0;
            if (this.c == null) {
                return new c(this.b, null);
            }
            if (this.b.g() && this.c.e() == null) {
                return new c(this.b, null);
            }
            if (!c.a(this.c, this.b)) {
                return new c(this.b, null);
            }
            okhttp3.d f = this.b.f();
            if (f.a() || a(this.b)) {
                return new c(this.b, null);
            }
            long toMillis;
            long d = d();
            long c = c();
            if (f.c() != -1) {
                c = Math.min(c, TimeUnit.SECONDS.toMillis((long) f.c()));
            }
            if (f.h() != -1) {
                toMillis = TimeUnit.SECONDS.toMillis((long) f.h());
            } else {
                toMillis = 0;
            }
            okhttp3.d k = this.c.k();
            if (!(k.f() || f.g() == -1)) {
                j = TimeUnit.SECONDS.toMillis((long) f.g());
            }
            if (k.a() || d + toMillis >= r4 + c) {
                String str;
                String str2;
                if (this.k != null) {
                    str = "If-None-Match";
                    str2 = this.k;
                } else if (this.f != null) {
                    str = "If-Modified-Since";
                    str2 = this.g;
                } else if (this.d == null) {
                    return new c(this.b, null);
                } else {
                    str = "If-Modified-Since";
                    str2 = this.e;
                }
                okhttp3.r.a b = this.b.c().b();
                okhttp3.internal.a.a.a(b, str, str2);
                return new c(this.b.e().a(b.a()).b(), this.c);
            }
            okhttp3.y.a h = this.c.h();
            if (toMillis + d >= c) {
                h.a("Warning", "110 HttpURLConnection \"Response is stale\"");
            }
            if (d > TimeFormatterUtils.ONE_DAY && e()) {
                h.a("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
            }
            return new c(null, h.a());
        }

        private long c() {
            okhttp3.d k = this.c.k();
            if (k.c() != -1) {
                return TimeUnit.SECONDS.toMillis((long) k.c());
            }
            long time;
            if (this.h != null) {
                time = this.h.getTime() - (this.d != null ? this.d.getTime() : this.j);
                if (time <= 0) {
                    time = 0;
                }
                return time;
            } else if (this.f == null || this.c.a().a().k() != null) {
                return 0;
            } else {
                time = (this.d != null ? this.d.getTime() : this.i) - this.f.getTime();
                if (time > 0) {
                    return time / 10;
                }
                return 0;
            }
        }

        private long d() {
            long j = 0;
            if (this.d != null) {
                j = Math.max(0, this.j - this.d.getTime());
            }
            if (this.l != -1) {
                j = Math.max(j, TimeUnit.SECONDS.toMillis((long) this.l));
            }
            return (j + (this.j - this.i)) + (this.a - this.j);
        }

        private boolean e() {
            return this.c.k().c() == -1 && this.h == null;
        }

        private static boolean a(w wVar) {
            return (wVar.a("If-Modified-Since") == null && wVar.a("If-None-Match") == null) ? false : true;
        }
    }

    c(w wVar, y yVar) {
        this.a = wVar;
        this.b = yVar;
    }

    public static boolean a(y yVar, w wVar) {
        switch (yVar.b()) {
            case 200:
            case 203:
            case 204:
            case 300:
            case 301:
            case 308:
            case 404:
            case ErrorCode.INFO_CAN_NOT_LOAD_TBS /*405*/:
            case 410:
            case http.Request_URI_Too_Long /*414*/:
            case 501:
                break;
            case 302:
            case 307:
                if (yVar.a("Expires") == null && yVar.k().c() == -1 && !yVar.k().e() && !yVar.k().d()) {
                    return false;
                }
            default:
                return false;
        }
        return (yVar.k().b() || wVar.f().b()) ? false : true;
    }
}
