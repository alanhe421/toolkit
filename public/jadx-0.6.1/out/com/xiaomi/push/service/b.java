package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.push.service.XMPushService.h;
import com.xiaomi.xmpush.thrift.ac;

final class b extends h {
    final /* synthetic */ XMPushService a;
    final /* synthetic */ ac b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;

    b(int i, XMPushService xMPushService, ac acVar, String str, String str2) {
        this.a = xMPushService;
        this.b = acVar;
        this.c = str;
        this.d = str2;
        super(i);
    }

    public void a() {
        try {
            ac a = bh.a(this.a, this.b);
            a.h.a("error", this.c);
            a.h.a("reason", this.d);
            d.a(this.a, a);
        } catch (Throwable e) {
            c.a(e);
            this.a.a(10, e);
        }
    }

    public String b() {
        return "send wrong message ack for message.";
    }
}
