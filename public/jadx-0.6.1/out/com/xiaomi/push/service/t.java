package com.xiaomi.push.service;

import com.tencent.android.tpush.common.Constants;
import com.tencent.qalsdk.im_open.http;
import com.tencent.upload.impl.TaskManager;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.d.f;
import com.xiaomi.push.service.XMPushService.d;
import qalsdk.o;

class t {
    private static int e = o.c;
    private XMPushService a;
    private int b;
    private long c;
    private int d = 0;

    public t(XMPushService xMPushService) {
        this.a = xMPushService;
        this.b = http.Internal_Server_Error;
        this.c = 0;
    }

    private int b() {
        if (this.d > 8) {
            return o.c;
        }
        if (this.d > 4) {
            return 60000;
        }
        if (this.d > 1) {
            return Constants.ERRORCODE_UNKNOWN;
        }
        if (this.c == 0) {
            return 0;
        }
        if (System.currentTimeMillis() - this.c >= TaskManager.IDLE_PROTECT_TIME) {
            this.b = http.Internal_Server_Error;
            return 0;
        } else if (this.b >= e) {
            return this.b;
        } else {
            int i = this.b;
            this.b = (int) (((double) this.b) * 1.5d);
            return i;
        }
    }

    public void a() {
        this.c = System.currentTimeMillis();
        this.a.a(1);
        this.d = 0;
    }

    public void a(boolean z) {
        if (!this.a.b()) {
            c.c("should not reconnect as no client or network.");
        } else if (z) {
            if (!this.a.b(1)) {
                this.d++;
            }
            this.a.a(1);
            XMPushService xMPushService = this.a;
            XMPushService xMPushService2 = this.a;
            xMPushService2.getClass();
            xMPushService.a(new d(xMPushService2));
        } else if (!this.a.b(1)) {
            int b = b();
            if (!this.a.b(1)) {
                this.d++;
            }
            c.a("schedule reconnect in " + b + "ms");
            XMPushService xMPushService3 = this.a;
            XMPushService xMPushService4 = this.a;
            xMPushService4.getClass();
            xMPushService3.a(new d(xMPushService4), (long) b);
            if (this.d == 2 && f.a().c()) {
                h.b();
            }
            if (this.d == 3) {
                h.a();
            }
        }
    }
}
