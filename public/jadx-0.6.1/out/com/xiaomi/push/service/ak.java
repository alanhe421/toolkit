package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.push.service.module.b;
import java.util.Iterator;

class ak implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ aj d;

    ak(aj ajVar, Context context, String str, String str2) {
        this.d = ajVar;
        this.a = context;
        this.b = str;
        this.c = str2;
    }

    public void run() {
        Iterator it = at.a(this.a).c(this.b).iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            if (XMPushService.a(bVar.e(), this.c)) {
                if (bVar.a() >= System.currentTimeMillis()) {
                    byte[] d = bVar.d();
                    if (d == null) {
                        c.a("Geo canBeShownMessage content null");
                    } else {
                        Intent a = bh.a(d, System.currentTimeMillis());
                        if (a == null) {
                            c.a("Geo canBeShownMessage intent null");
                        } else {
                            bh.a(this.d.a, null, d, a, true);
                            if (at.a(this.d.a).a(bVar.b()) == 0) {
                                c.a("show some exit geofence message. then remove this message failed. message_id:" + bVar.b());
                            }
                        }
                    }
                } else if (at.a(this.a).a(bVar.b()) == 0) {
                    c.a("XMPushService remove some geofence message failed. message_id:" + bVar.b());
                }
            }
        }
    }
}
