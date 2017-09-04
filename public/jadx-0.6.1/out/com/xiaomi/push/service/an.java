package com.xiaomi.push.service;

import com.xiaomi.push.service.XMPushService.h;

class an extends h {
    final /* synthetic */ XMPushService a;

    an(XMPushService xMPushService, int i) {
        this.a = xMPushService;
        super(i);
    }

    public void a() {
        if (this.a.j != null) {
            this.a.j.h();
            this.a.j = null;
        }
    }

    public String b() {
        return "disconnect for disable push";
    }
}
