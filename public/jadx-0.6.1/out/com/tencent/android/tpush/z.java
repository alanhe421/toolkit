package com.tencent.android.tpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.g;
import com.tencent.android.tpush.common.p;

/* compiled from: ProGuard */
final class z extends BroadcastReceiver {
    final /* synthetic */ XGIOperateCallback a;

    z(XGIOperateCallback xGIOperateCallback) {
        this.a = xGIOperateCallback;
    }

    public void onReceive(Context context, Intent intent) {
        if (XGPushConfig.enableDebug) {
            a.e(XGPushManager.a, "Register call back to " + context.getPackageName());
        }
        try {
            g.a().a(new ab(this.a, context, intent, 1, 0));
        } catch (Exception e) {
        }
        p.a(context, (BroadcastReceiver) this);
    }
}
