package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.network.e;
import com.xiaomi.network.f;
import com.xiaomi.network.f.b;
import com.xiaomi.push.service.v.a;
import java.util.ArrayList;
import java.util.Iterator;

public class p extends a implements f.a {
    private XMPushService a;
    private long b;

    p(XMPushService xMPushService) {
        this.a = xMPushService;
    }

    public static void a(XMPushService xMPushService) {
        f.a pVar = new p(xMPushService);
        v.a().a((a) pVar);
        synchronized (f.class) {
            f.a(pVar);
            f.a(xMPushService, null, new a(), "0", "push", "2.2");
        }
    }

    public f a(Context context, e eVar, b bVar, String str) {
        return new b(context, eVar, bVar, str);
    }

    public void a(com.xiaomi.push.b.a.a aVar) {
    }

    public void a(com.xiaomi.push.b.b.b bVar) {
        if (bVar.e() && bVar.d() && System.currentTimeMillis() - this.b > 3600000) {
            c.a("fetch bucket :" + bVar.d());
            this.b = System.currentTimeMillis();
            f a = f.a();
            a.d();
            a.e();
            com.xiaomi.smack.a h = this.a.h();
            if (h != null) {
                com.xiaomi.network.b b = a.b(h.c().e());
                if (b != null) {
                    boolean z;
                    ArrayList d = b.d();
                    Iterator it = d.iterator();
                    while (it.hasNext()) {
                        if (((String) it.next()).equals(h.d())) {
                            z = false;
                            break;
                        }
                    }
                    z = true;
                    if (z && !d.isEmpty()) {
                        c.a("bucket changed, force reconnect");
                        this.a.a(0, null);
                        this.a.a(false);
                    }
                }
            }
        }
    }
}
