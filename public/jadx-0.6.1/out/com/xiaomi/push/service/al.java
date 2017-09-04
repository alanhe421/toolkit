package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.c.a;
import com.xiaomi.push.service.XMPushService.h;
import com.xiaomi.smack.b;

class al extends h {
    final /* synthetic */ int a;
    final /* synthetic */ byte[] b;
    final /* synthetic */ String c;
    final /* synthetic */ XMPushService d;

    al(XMPushService xMPushService, int i, int i2, byte[] bArr, String str) {
        this.d = xMPushService;
        this.a = i2;
        this.b = bArr;
        this.c = str;
        super(i);
    }

    public void a() {
        bd.b(this.d);
        am.a().a("5");
        a.a(this.a);
        this.d.d.b(b.b());
        this.d.a(this.b, this.c);
    }

    public String b() {
        return "clear account cache.";
    }
}
