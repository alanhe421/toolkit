package com.qq.reader.common.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.qq.reader.common.monitor.debug.c;

public abstract class BaseBroadcastReceiver extends BroadcastReceiver {
    public abstract void a(Context context, Intent intent);

    public void onReceive(Context context, Intent intent) {
        try {
            a(context, intent);
        } catch (Throwable th) {
            c.e("BaseBroadcastReceiver", th.getMessage());
        }
    }
}
