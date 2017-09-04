package com.tencent.android.tpush.common;

import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.service.XGWatchdog;

/* compiled from: ProGuard */
class c implements Runnable {
    private Context a = null;
    private Intent b = null;

    public c(Context context, Intent intent) {
        this.a = context;
        this.b = intent;
    }

    public void run() {
        String action = this.b.getAction();
        if (action != null) {
            if ("android.intent.action.PACKAGE_ADDED".equals(action) || "android.intent.action.PACKAGE_REPLACED".equals(action) || "android.intent.action.PACKAGE_REMOVED".equals(action)) {
                XGWatchdog.getInstance(this.a).sendAllLocalXGAppList();
            }
        }
    }
}
