package com.xiaomi.push.service;

import com.xiaomi.push.service.XMPushService.h;

class ac extends h {
    final /* synthetic */ XMPushService a;

    ac(XMPushService xMPushService, int i) {
        this.a = xMPushService;
        super(i);
    }

    public void a() {
        if (this.a.j != null) {
            this.a.j.b(15, null);
            this.a.j = null;
        }
    }

    public String b() {
        return "disconnect for service destroy.";
    }
}
