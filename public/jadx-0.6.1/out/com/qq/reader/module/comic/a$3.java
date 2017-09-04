package com.qq.reader.module.comic;

import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qrcomic.c.d;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import okhttp3.s;
import okhttp3.u;
import okhttp3.u.a;

/* compiled from: ComicHelper */
class a$3 implements d {
    final /* synthetic */ a a;

    a$3(a aVar) {
        this.a = aVar;
    }

    public u a(s sVar) {
        a a = com.qq.reader.common.conn.http.a.a().a(10, TimeUnit.SECONDS);
        if (sVar != null) {
            a.a(sVar);
        }
        return a.a();
    }

    public HashMap<String, String> a() {
        return new ReaderProtocolJSONTask().getBasicHeader();
    }

    public u b(s sVar) {
        a a = com.qq.reader.common.conn.http.a.a().a(10, TimeUnit.SECONDS);
        com.qq.reader.module.comic.e.d.a(a);
        if (sVar != null) {
            a.a(sVar);
        }
        return a.a();
    }
}
