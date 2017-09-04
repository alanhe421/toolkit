package com.tencent.android.tpush.stat;

import com.tencent.android.tpush.XGPush4Msdk;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.common.p;
import java.lang.Thread.UncaughtExceptionHandler;

/* compiled from: ProGuard */
class r implements UncaughtExceptionHandler {
    r() {
    }

    public void uncaughtException(Thread thread, Throwable th) {
        p.a();
        if (c.c() && h.f != null) {
            long accessId = XGPushConfig.getAccessId(h.f);
            if (accessId <= 0) {
                accessId = XGPush4Msdk.getQQAccessId(h.f);
            }
            Object obj = 1;
            if (!(h.g == null || th.toString().contains(h.g))) {
                obj = null;
            }
            if (obj != null) {
                if (h.e(h.f) != null) {
                    h.b.post(new s(this, accessId, th, thread));
                }
                h.d.g("has caught the following uncaught exception:");
                h.d.a(th);
            }
            if (h.e != null) {
                h.d.h("Call the original uncaught exception handler.");
                if (!(h.e instanceof r)) {
                    h.e.uncaughtException(thread, th);
                }
            }
        }
    }
}
