package com.qq.reader.common.conn.socket;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

/* compiled from: QRPush */
public final class g {
    public static void a(Context context, ServiceConnection serviceConnection) {
        context.bindService(new Intent(context, PushService.class), serviceConnection, 1);
    }

    public static void b(Context context, ServiceConnection serviceConnection) {
        context.unbindService(serviceConnection);
    }
}
