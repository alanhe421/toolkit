package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.channel.commonutils.c.g.a;
import com.xiaomi.push.service.module.b;
import java.util.Iterator;

public class aq extends a {
    private XMPushService a;

    public aq(XMPushService xMPushService) {
        this.a = xMPushService;
    }

    public int a() {
        return 15;
    }

    public void run() {
        Iterator it = at.a(this.a).a().iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            if (bVar.a() < System.currentTimeMillis()) {
                if (at.a(this.a).a(bVar.b()) == 0) {
                    c.a("GeofenceDbCleaner delete a geofence message failed message_id:" + bVar.b());
                }
                bh.a(this.a, bh.a(bVar.d()), false, false, true);
            }
        }
    }
}
