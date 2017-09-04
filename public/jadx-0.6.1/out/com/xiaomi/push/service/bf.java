package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.push.service.XMPushService.h;
import com.xiaomi.push.service.am.b;
import java.util.Collection;

public class bf extends h {
    private XMPushService a;
    private byte[] b;
    private String c;
    private String d;
    private String e;

    public bf(XMPushService xMPushService, String str, String str2, String str3, byte[] bArr) {
        super(9);
        this.a = xMPushService;
        this.c = str;
        this.b = bArr;
        this.d = str2;
        this.e = str3;
    }

    public void a() {
        bc a;
        Collection c;
        b bVar;
        bc a2 = bd.a(this.a);
        if (a2 == null) {
            try {
                a = bd.a(this.a, this.c, this.d, this.e);
            } catch (Throwable e) {
                c.a(e);
                a = a2;
            } catch (Throwable e2) {
                c.a(e2);
            }
            if (a != null) {
                c.d("no account for mipush");
                bg.a(this.a, 70000002, "no account.");
            }
            c = am.a().c("5");
            if (c.isEmpty()) {
                bVar = (b) c.iterator().next();
            } else {
                bVar = a.a(this.a);
                d.a(this.a, bVar);
                am.a().a(bVar);
            }
            if (this.a.f()) {
                this.a.a(true);
                return;
            }
            try {
                if (bVar.m == am.c.binded) {
                    d.a(this.a, this.c, this.b);
                    return;
                } else if (bVar.m == am.c.unbind) {
                    XMPushService xMPushService = this.a;
                    XMPushService xMPushService2 = this.a;
                    xMPushService2.getClass();
                    xMPushService.a(new a(xMPushService2, bVar));
                    return;
                } else {
                    return;
                }
            } catch (Exception e3) {
                c.a((Throwable) e3);
                this.a.a(10, e3);
                return;
            }
        }
        a = a2;
        if (a != null) {
            c = am.a().c("5");
            if (c.isEmpty()) {
                bVar = (b) c.iterator().next();
            } else {
                bVar = a.a(this.a);
                d.a(this.a, bVar);
                am.a().a(bVar);
            }
            if (this.a.f()) {
                this.a.a(true);
                return;
            } else if (bVar.m == am.c.binded) {
                d.a(this.a, this.c, this.b);
                return;
            } else if (bVar.m == am.c.unbind) {
                XMPushService xMPushService3 = this.a;
                XMPushService xMPushService22 = this.a;
                xMPushService22.getClass();
                xMPushService3.a(new a(xMPushService22, bVar));
                return;
            } else {
                return;
            }
        }
        c.d("no account for mipush");
        bg.a(this.a, 70000002, "no account.");
    }

    public String b() {
        return "register app";
    }
}
