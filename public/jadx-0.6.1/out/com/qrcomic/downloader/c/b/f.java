package com.qrcomic.downloader.c.b;

import com.qrcomic.manager.b;
import java.util.Map;
import okhttp3.r;
import okhttp3.u;
import okhttp3.w.a;

/* compiled from: RequestUtil */
public class f {
    public static void a(a aVar, Map<String, String> map) {
        aVar.a(r.a((Map) map));
    }

    public static void a(a aVar) {
        try {
            a(aVar, b.a().b().f().e().a());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static u a(d dVar) {
        return b.a().b().f().e().a(dVar);
    }
}
