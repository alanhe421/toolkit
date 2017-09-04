package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.push.service.XMPushService.h;
import com.xiaomi.xmpush.thrift.ac;

final class bl extends h {
    final /* synthetic */ XMPushService a;
    final /* synthetic */ ac b;

    bl(int i, XMPushService xMPushService, ac acVar) {
        this.a = xMPushService;
        this.b = acVar;
        super(i);
    }

    public void a() {
        try {
            ac a = bh.a(this.a, this.b);
            a.m().a("miui_message_unrecognized", "1");
            d.a(this.a, a);
        } catch (Throwable e) {
            c.a(e);
            this.a.a(10, e);
        }
    }

    public String b() {
        return "send ack message for unrecognized new miui message.";
    }
}
