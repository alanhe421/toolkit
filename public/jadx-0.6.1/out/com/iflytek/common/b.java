package com.iflytek.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.iflytek.common.c.c;

final class b extends BroadcastReceiver {
    final /* synthetic */ LaunchService a;

    b(LaunchService launchService) {
        this.a = launchService;
    }

    public final void onReceive(Context context, Intent intent) {
        c.a(context, "alarm onReceive");
        com.iflytek.common.a.c.a(context);
        this.a.b();
    }
}
