package com.tencent.android.tpush.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/* compiled from: ProGuard */
public class XGDaemonService extends Service {
    public int onStartCommand(Intent intent, int i, int i2) {
        return 2;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
}
