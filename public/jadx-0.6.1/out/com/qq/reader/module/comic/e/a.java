package com.qq.reader.module.comic.e;

import com.qq.reader.ReaderApplication;
import com.qq.reader.common.db.handle.s;
import com.qq.reader.cservice.cloud.a.e;
import com.qq.reader.cservice.cloud.a.f;
import com.qq.reader.cservice.cloud.g;
import com.qq.reader.framework.a.b;

/* compiled from: ComicCloudProxy */
public class a {
    public static void a(g gVar, com.qq.reader.cservice.cloud.a aVar) {
        if (gVar != null) {
            com.qq.reader.cservice.cloud.a.g eVar = new e(gVar.f(), gVar.h(), gVar.i(), gVar.g(), 3);
            b b = s.a().b(null, gVar.f());
            if (b != null) {
                eVar.d(b.b());
                eVar.c(b.d());
            } else {
                eVar.d(0);
                eVar.c(0);
            }
            com.qq.reader.cservice.cloud.b.a(ReaderApplication.getApplicationContext()).a(eVar, false, aVar);
        }
    }

    public static void b(g gVar, com.qq.reader.cservice.cloud.a aVar) {
        com.qq.reader.cservice.cloud.b.a(ReaderApplication.getApplicationContext()).a(new com.qq.reader.cservice.cloud.a.b(gVar.f(), gVar.h(), gVar.i(), gVar.g(), gVar.t(), 3), false, aVar);
    }

    public static void c(g gVar, com.qq.reader.cservice.cloud.a aVar) {
        com.qq.reader.cservice.cloud.b.a(ReaderApplication.getApplicationContext()).a(new f(gVar.f(), gVar.h(), gVar.i(), gVar.g(), gVar.t(), 3), false, aVar);
    }
}
