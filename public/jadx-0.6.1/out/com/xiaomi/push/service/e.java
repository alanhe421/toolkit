package com.xiaomi.push.service;

import com.xiaomi.push.service.am.c;

final class e implements am$b$a {
    final /* synthetic */ XMPushService a;

    e(XMPushService xMPushService) {
        this.a = xMPushService;
    }

    public void a(c cVar, c cVar2, int i) {
        if (cVar2 == c.c) {
            bg.a(this.a);
            bg.b(this.a);
        } else if (cVar2 == c.a) {
            bg.a(this.a, 70000001, " the push is not connected.");
        }
    }
}
