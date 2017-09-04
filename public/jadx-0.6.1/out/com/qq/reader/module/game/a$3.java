package com.qq.reader.module.game;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/* compiled from: GameDataHelper */
class a$3 extends BroadcastReceiver {
    final /* synthetic */ a a;

    a$3(a aVar) {
        this.a = aVar;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Object dataString;
        if ("android.intent.action.PACKAGE_ADDED".equals(action)) {
            dataString = intent.getDataString();
            if (!TextUtils.isEmpty(dataString)) {
                a.a(this.a, dataString.replace("package:", ""), 1);
            }
        } else if ("android.intent.action.PACKAGE_REMOVED".equals(action)) {
            dataString = intent.getDataString();
            if (!TextUtils.isEmpty(dataString)) {
                a.a(this.a, dataString.replace("package:", ""), -1);
            }
        }
    }
}
