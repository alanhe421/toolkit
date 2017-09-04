package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.push.service.XMPushService.h;
import com.xiaomi.xmpush.thrift.ac;

final class bm extends h {
    final /* synthetic */ XMPushService a;
    final /* synthetic */ ac b;
    final /* synthetic */ String c;

    bm(int i, XMPushService xMPushService, ac acVar, String str) {
        this.a = xMPushService;
        this.b = acVar;
        this.c = str;
        super(i);
    }

    public void a() {
        try {
            ac a = bh.a(this.a, this.b);
            a.m().a("absent_target_package", this.c);
            d.a(this.a, a);
        } catch (Throwable e) {
            c.a(e);
            this.a.a(10, e);
        }
    }

    public String b() {
        return "send app absent ack message for message.";
    }
}
