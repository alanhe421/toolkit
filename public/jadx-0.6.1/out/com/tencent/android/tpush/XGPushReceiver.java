package com.tencent.android.tpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.p;
import com.tencent.android.tpush.horse.g;
import com.tencent.android.tpush.service.m;

/* compiled from: ProGuard */
public class XGPushReceiver extends BroadcastReceiver {
    private static final String a = XGPushReceiver.class.getSimpleName();

    public void onReceive(Context context, Intent intent) {
        if (context != null && intent != null && p.h(context)) {
            String action = intent.getAction();
            if (action != null) {
                m.c(context.getApplicationContext());
                if (XGPushConfig.enableDebug) {
                    a.c(a, "PushReceiver received " + action + " @@ " + context.getPackageName());
                }
                if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
                    g.a().a(intent);
                } else if (Constants.ACTION_INTERNAL_PUSH_MESSAGE.equals(action)) {
                    com.tencent.android.tpush.b.g.a(context).a(intent);
                } else {
                    m.a(context);
                }
            }
        }
    }
}
