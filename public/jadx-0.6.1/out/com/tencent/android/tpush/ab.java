package com.tencent.android.tpush;

import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.c.a;
import com.tencent.android.tpush.c.b;
import com.tencent.android.tpush.common.m;
import com.tencent.android.tpush.common.o;
import com.tencent.android.tpush.service.XGWatchdog;
import com.tencent.android.tpush.service.w;
import com.tencent.open.SocialConstants;

/* compiled from: ProGuard */
class ab implements Runnable {
    private Context a;
    private Intent b;
    private XGIOperateCallback c;
    private int d;
    private int e = 0;

    public ab(XGIOperateCallback xGIOperateCallback, Context context, Intent intent, int i, int i2) {
        this.c = xGIOperateCallback;
        this.a = context;
        this.b = intent;
        this.d = i;
        this.e = i2;
    }

    public void run() {
        try {
            XGWatchdog.getInstance(this.a).startWatchdog();
            this.b.removeExtra("storage");
            if (this.d != 1) {
                if (this.d == 0) {
                    switch (this.b.getIntExtra("operation", -1)) {
                        case 100:
                            XGPushManager.b(this.a, this.b, this.c);
                            break;
                        case 101:
                            XGPushManager.a(this.a, this.b, this.c);
                            break;
                        default:
                            break;
                    }
                }
            }
            String stringExtra = this.b.getStringExtra("data");
            switch (this.b.getIntExtra("operation", -1)) {
                case 0:
                    this.c.onSuccess(stringExtra, this.b.getIntExtra("flag", -1));
                    if (this.e == 0) {
                        m.b(this.a, ".firstregister", 0);
                    }
                    if (o.a(this.a).b() && a.d(this.a) && a.b().equals("gcm")) {
                        b.a(this.a);
                        break;
                    }
                case 1:
                    this.c.onFail(stringExtra, this.b.getIntExtra("code", -1), this.b.getStringExtra(SocialConstants.PARAM_SEND_MSG));
                    break;
            }
            XGWatchdog.getInstance(this.a).sendAllLocalXGAppList();
            com.tencent.android.tpush.common.a.a(this.a);
            w.a(this.a).a();
        } catch (Throwable th) {
            com.tencent.android.tpush.a.a.h(XGPushManager.a(), th.toString());
        }
    }
}
