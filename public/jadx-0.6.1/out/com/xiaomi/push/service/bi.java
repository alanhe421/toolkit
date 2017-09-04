package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.push.service.XMPushService.h;
import com.xiaomi.xmpush.thrift.ac;

final class bi extends h {
    final /* synthetic */ XMPushService a;
    final /* synthetic */ ac b;

    bi(int i, XMPushService xMPushService, ac acVar) {
        this.a = xMPushService;
        this.b = acVar;
        super(i);
    }

    public void a() {
        try {
            d.a(this.a, d.a(this.b.j(), this.b.h()));
        } catch (Throwable e) {
            c.a(e);
            this.a.a(10, e);
        }
    }

    public String b() {
        return "send app absent message.";
    }
}
