package com.xiaomi.push.service;

import com.xiaomi.c.b;
import com.xiaomi.smack.f;
import com.xiaomi.smack.packet.d;

class ab implements f {
    final /* synthetic */ XMPushService a;

    ab(XMPushService xMPushService) {
        this.a = xMPushService;
    }

    public void a(b bVar) {
        this.a.a(new c(this.a, bVar));
    }

    public void b(d dVar) {
        this.a.a(new j(this.a, dVar));
    }
}
