package com.qq.reader.common.conn.socket;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.qq.reader.common.conn.socket.PushMessageHandleService.a;

public abstract class PushMessageReceiver extends BroadcastReceiver {
    public final void onReceive(Context context, Intent intent) {
        PushMessageHandleService.a(new a(intent, this));
        try {
            context.startService(new Intent(context, PushMessageHandleService.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(Context context, QRPushMessage qRPushMessage) {
    }
}
