package com.xiaomi.push.service;

import com.xiaomi.push.service.XMPushService.h;
import com.xiaomi.xmpush.thrift.ac;

final class c extends h {
    final /* synthetic */ XMPushService a;
    final /* synthetic */ ac b;
    final /* synthetic */ boolean c;
    final /* synthetic */ boolean d;
    final /* synthetic */ boolean e;

    c(int i, XMPushService xMPushService, ac acVar, boolean z, boolean z2, boolean z3) {
        this.a = xMPushService;
        this.b = acVar;
        this.c = z;
        this.d = z2;
        this.e = z3;
        super(i);
    }

    public void a() {
        try {
            d.a(this.a, bh.a(this.a, this.b, this.c, this.d, this.e));
        } catch (Throwable e) {
            com.xiaomi.channel.commonutils.b.c.a(e);
            this.a.a(10, e);
        }
    }

    public String b() {
        return "send wrong message ack for message.";
    }
}
