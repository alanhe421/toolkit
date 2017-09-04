package com.tencent.android.tpush.service;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.l;
import com.tencent.android.tpush.service.channel.b;
import com.tencent.android.tpush.service.d.f;

/* compiled from: ProGuard */
class n extends Handler {
    final /* synthetic */ m a;

    n(m mVar, Looper looper) {
        this.a = mVar;
        super(looper);
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message != null) {
            switch (message.what) {
                case 1:
                    if (this.a.j()) {
                        if (!m.f) {
                            if (XGPushConfig.enableDebug) {
                                a.e("PushServiceManager", "Service's first running at " + m.a.getPackageName() + " version : " + Constants.PUSH_SDK_VERSION);
                            }
                            m.f = true;
                            if (!l.a()) {
                                a.h(Constants.ServiceLogTag, "permission check failed, kill service!");
                                this.a.d();
                                f.q(m.e());
                            }
                            f.c(m.a);
                            a.a().a(m.a);
                        }
                        b.a().b();
                        return;
                    }
                    Intent intent = new Intent();
                    intent.setClass(m.a, XGPushService.class);
                    m.a.stopService(intent);
                    return;
                case 2:
                    b.a().b();
                    return;
                case 3:
                    b.a().c();
                    return;
                default:
                    a.h("PushServiceManager", "unknown handler msg = " + message.what);
                    return;
            }
        }
    }
}
