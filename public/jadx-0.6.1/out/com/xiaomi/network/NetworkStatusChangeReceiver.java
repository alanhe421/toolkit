package com.xiaomi.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.channel.commonutils.d.d;

public class NetworkStatusChangeReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (d.f(context)) {
            try {
                context.startService(new Intent(context, HostRefreshService.class));
            } catch (Throwable e) {
                c.a(e);
            }
        }
    }
}
