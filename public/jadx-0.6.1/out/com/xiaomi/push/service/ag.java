package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.smack.b;
import com.xiaomi.smack.e;
import java.util.Map;

class ag extends b {
    final /* synthetic */ XMPushService a;

    ag(XMPushService xMPushService, Map map, int i, String str, e eVar) {
        this.a = xMPushService;
        super(map, i, str, eVar);
    }

    public byte[] a() {
        try {
            com.xiaomi.push.b.b.b bVar = new com.xiaomi.push.b.b.b();
            bVar.a(v.a().c());
            return bVar.c();
        } catch (Exception e) {
            c.a("getOBBString err: " + e.toString());
            return null;
        }
    }
}
