package com.tencent.qalsdk.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.tencent.qalsdk.base.a;
import com.tencent.qalsdk.util.QLog;

/* compiled from: LogManager */
final class f extends BroadcastReceiver {
    f() {
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (!TextUtils.isEmpty(action)) {
            QLog.d(e.a, 1, "onReceive broadcastreceiver.action = " + action);
            if (action.equals("android.intent.action.SCREEN_OFF")) {
                e.b = false;
                o.z = -1;
                o.B.set(false);
                e.c++;
                if (e.c == a.aq) {
                    e.c();
                }
            } else if (action.equals("android.intent.action.SCREEN_ON")) {
                e.b = true;
            }
        } else if (QLog.isColorLevel()) {
            QLog.d(e.a, 2, "onReceive broadcastreceiver.action null");
        }
    }
}
