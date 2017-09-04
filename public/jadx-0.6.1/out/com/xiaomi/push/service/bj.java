package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.push.service.XMPushService.h;
import com.xiaomi.xmpush.thrift.ac;

final class bj extends h {
    final /* synthetic */ XMPushService a;
    final /* synthetic */ ac b;

    bj(int i, XMPushService xMPushService, ac acVar) {
        this.a = xMPushService;
        this.b = acVar;
        super(i);
    }

    public void a() {
        try {
            d.a(this.a, bh.a(this.a, this.b));
        } catch (Throwable e) {
            c.a(e);
            this.a.a(10, e);
        }
    }

    public String b() {
        return "send ack message for message.";
    }
}
