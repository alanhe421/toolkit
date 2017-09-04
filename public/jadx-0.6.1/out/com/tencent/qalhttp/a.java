package com.tencent.qalhttp;

import android.content.Context;
import com.tencent.connect.common.Constants;
import com.tencent.qalsdk.util.QLog;
import qalsdk.c;

/* compiled from: HttpCacheHelper */
class a {
    private static final String e = "HttpCacheHelper";
    private static c f;
    public boolean a = false;
    public String b = null;
    public String c = null;
    public long d = 0;
    private boolean g = false;
    private qalsdk.a.a h = null;

    a() {
    }

    public static void a(Context context) {
        f = c.b();
        f.a(context);
        QLog.d(e, 4, "cache init ok");
    }

    public static void a(long j) {
        f.a(j);
    }

    public QALHttpResponse a(int i, String str) {
        if (i != 1) {
            return null;
        }
        String str2 = Constants.HTTP_GET + str;
        long currentTimeMillis = System.currentTimeMillis();
        qalsdk.a.a a = f.a(str2);
        this.d = System.currentTimeMillis() - currentTimeMillis;
        QLog.d(e, 4, "cache get costTime:" + this.d);
        if (a == null) {
            return null;
        }
        this.g = true;
        this.h = a;
        if (!a.a()) {
            QLog.d(e, 4, str2 + " hit cache,not expired");
            f.b(str2);
            return a(a);
        } else if (a.b()) {
            this.a = true;
            this.b = a.k;
            this.c = a.j;
            QLog.d(e, 4, str2 + " hit stale cache,need update");
            f.b(str2);
            return a(a);
        } else {
            QLog.d(e, 4, str2 + " hit cache,expired");
            this.b = a.k;
            this.c = a.j;
            return null;
        }
    }

    private QALHttpResponse a(qalsdk.a.a aVar) {
        QALHttpResponse qALHttpResponse = new QALHttpResponse();
        qALHttpResponse.setStatus(aVar.a);
        qALHttpResponse.setContentType(aVar.b);
        qALHttpResponse.setLocation(aVar.c);
        qALHttpResponse.setDate(aVar.e);
        qALHttpResponse.setServer(aVar.f);
        qALHttpResponse.setVia(aVar.g);
        qALHttpResponse.setXCache(aVar.h);
        qALHttpResponse.setXCacheLookup(aVar.i);
        qALHttpResponse.setAge(aVar.o);
        qALHttpResponse.setLastModified(aVar.j);
        qALHttpResponse.setEtag(aVar.k);
        qALHttpResponse.setCacheControl(aVar.l);
        qALHttpResponse.setExpires(aVar.m);
        qALHttpResponse.setPragma(aVar.n);
        qALHttpResponse.setSetCookie(aVar.d);
        qALHttpResponse.setOtherHeaders(aVar.r);
        qALHttpResponse.setBody(aVar.s);
        return qALHttpResponse;
    }

    private qalsdk.a.a a(QALHttpResponse qALHttpResponse) {
        qalsdk.a.a aVar = new qalsdk.a.a();
        aVar.a = qALHttpResponse.getStatus();
        aVar.b = qALHttpResponse.getContentType();
        aVar.c = qALHttpResponse.getLocation();
        aVar.e = qALHttpResponse.getDate();
        aVar.f = qALHttpResponse.getServer();
        aVar.g = qALHttpResponse.getVia();
        aVar.h = qALHttpResponse.getXCache();
        aVar.i = qALHttpResponse.getXCacheLookup();
        aVar.o = qALHttpResponse.getAge();
        aVar.j = qALHttpResponse.getLastModified();
        aVar.k = qALHttpResponse.getEtag();
        aVar.l = qALHttpResponse.getCacheControl();
        aVar.m = qALHttpResponse.getExpires();
        aVar.n = qALHttpResponse.getPragma();
        aVar.d = qALHttpResponse.getSetCookie();
        aVar.p = qALHttpResponse.responsePrivate.cache_max_age.get();
        aVar.q = qALHttpResponse.responsePrivate.cache_max_stale_age.get();
        aVar.r = qALHttpResponse.getOtherHeaders();
        aVar.s = qALHttpResponse.getBody();
        return aVar;
    }

    public void a(int i, String str, QALHttpResponse qALHttpResponse) {
        if (i == 1) {
            String str2 = Constants.HTTP_GET + str;
            if (qALHttpResponse.getStatus() == 200) {
                if (qALHttpResponse.responsePrivate.cache_max_age.get() > 0 || qALHttpResponse.responsePrivate.cache_max_stale_age.get() > 0 || !((qALHttpResponse.getLastModified() == null || qALHttpResponse.getLastModified().length() == 0) && (qALHttpResponse.getEtag() == null || qALHttpResponse.getLastModified().length() == 0))) {
                    QLog.d(e, 4, str2 + " 200|write to cache");
                    long currentTimeMillis = System.currentTimeMillis();
                    f.a(str2, a(qALHttpResponse));
                    QLog.d(e, 4, "cache put costTime:" + (System.currentTimeMillis() - currentTimeMillis));
                } else if (this.g) {
                    QLog.d(e, 4, str2 + " 200|remove cache");
                    f.c(str2);
                }
            } else if (qALHttpResponse.getStatus() == 304) {
                QLog.d(e, 4, str2 + "304 |wirte to cache");
                f.a(str2, a(qALHttpResponse));
            } else if (qALHttpResponse.getStatus() == 404 && this.g) {
                QLog.d(e, 4, str2 + " 404 |remove cache");
                f.c(str2);
            }
        }
    }

    public QALHttpResponse a(String str) {
        if (this.h == null) {
            QLog.e(e, 1, "304,but no cache");
            return null;
        }
        f.b(Constants.HTTP_GET + str);
        return a(this.h);
    }
}
