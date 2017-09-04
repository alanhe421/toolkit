package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.d.d;
import com.xiaomi.push.service.XMPushService.h;

class ah extends h {
    final /* synthetic */ XMPushService a;

    ah(XMPushService xMPushService, int i) {
        this.a = xMPushService;
        super(i);
    }

    public void a() {
        d.a(this.a);
        if (d.d(this.a)) {
            this.a.a(true);
        }
    }

    public String b() {
        return "prepare the mi push account.";
    }
}
