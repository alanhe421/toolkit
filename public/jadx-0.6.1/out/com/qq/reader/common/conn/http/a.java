package com.qq.reader.common.conn.http;

import java.util.concurrent.TimeUnit;
import okhttp3.s;
import okhttp3.u;

/* compiled from: HTTPConfig */
public class a {
    private static u a;

    static {
        a = null;
        a = new okhttp3.u.a().a(20000, TimeUnit.SECONDS).b(25000, TimeUnit.SECONDS).a(new com.qq.reader.common.conn.http.b.a()).a();
    }

    public static okhttp3.u.a a() {
        return a.x();
    }

    public static u a(s sVar, s sVar2) {
        if (sVar == null && sVar2 == null) {
            return a;
        }
        okhttp3.u.a x = a.x();
        if (sVar != null) {
            x.a(sVar);
        }
        if (sVar2 != null) {
            x.b(sVar2);
        }
        return x.a();
    }
}
