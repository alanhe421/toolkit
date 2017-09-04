package okhttp3;

import com.tencent.connect.common.Constants;
import okhttp3.internal.b.f;

/* compiled from: Request */
public final class w {
    final HttpUrl a;
    final String b;
    final r c;
    final x d;
    final Object e;
    private volatile d f;

    /* compiled from: Request */
    public static class a {
        HttpUrl a;
        String b;
        okhttp3.r.a c;
        x d;
        Object e;

        public a() {
            this.b = Constants.HTTP_GET;
            this.c = new okhttp3.r.a();
        }

        a(w wVar) {
            this.a = wVar.a;
            this.b = wVar.b;
            this.d = wVar.d;
            this.e = wVar.e;
            this.c = wVar.c.b();
        }

        public a a(HttpUrl httpUrl) {
            if (httpUrl == null) {
                throw new NullPointerException("url == null");
            }
            this.a = httpUrl;
            return this;
        }

        public a a(String str) {
            if (str == null) {
                throw new NullPointerException("url == null");
            }
            if (str.regionMatches(true, 0, "ws:", 0, 3)) {
                str = "http:" + str.substring(3);
            } else {
                if (str.regionMatches(true, 0, "wss:", 0, 4)) {
                    str = "https:" + str.substring(4);
                }
            }
            HttpUrl e = HttpUrl.e(str);
            if (e != null) {
                return a(e);
            }
            throw new IllegalArgumentException("unexpected url: " + str);
        }

        public a a(String str, String str2) {
            this.c.c(str, str2);
            return this;
        }

        public a b(String str, String str2) {
            this.c.a(str, str2);
            return this;
        }

        public a b(String str) {
            this.c.b(str);
            return this;
        }

        public a a(r rVar) {
            this.c = rVar.b();
            return this;
        }

        public a a() {
            return a(Constants.HTTP_GET, null);
        }

        public a a(x xVar) {
            return a(Constants.HTTP_POST, xVar);
        }

        public a a(String str, x xVar) {
            if (str == null) {
                throw new NullPointerException("method == null");
            } else if (str.length() == 0) {
                throw new IllegalArgumentException("method.length() == 0");
            } else if (xVar != null && !f.c(str)) {
                throw new IllegalArgumentException("method " + str + " must not have a request body.");
            } else if (xVar == null && f.b(str)) {
                throw new IllegalArgumentException("method " + str + " must have a request body.");
            } else {
                this.b = str;
                this.d = xVar;
                return this;
            }
        }

        public w b() {
            if (this.a != null) {
                return new w(this);
            }
            throw new IllegalStateException("url == null");
        }
    }

    w(a aVar) {
        Object obj;
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c.a();
        this.d = aVar.d;
        if (aVar.e != null) {
            obj = aVar.e;
        } else {
            w wVar = this;
        }
        this.e = obj;
    }

    public HttpUrl a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public r c() {
        return this.c;
    }

    public String a(String str) {
        return this.c.a(str);
    }

    public x d() {
        return this.d;
    }

    public a e() {
        return new a(this);
    }

    public d f() {
        d dVar = this.f;
        if (dVar != null) {
            return dVar;
        }
        dVar = d.a(this.c);
        this.f = dVar;
        return dVar;
    }

    public boolean g() {
        return this.a.c();
    }

    public String toString() {
        return "Request{method=" + this.b + ", url=" + this.a + ", tag=" + (this.e != this ? this.e : null) + '}';
    }
}
