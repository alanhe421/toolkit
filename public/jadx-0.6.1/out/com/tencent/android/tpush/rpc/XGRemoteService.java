package com.tencent.android.tpush.rpc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.tencent.android.tpush.service.m;

/* compiled from: ProGuard */
public class XGRemoteService extends Service {
    private b a = new h();

    public void onCreate() {
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        m.a(getApplication(), "pullup", 1000);
        return super.onStartCommand(intent, i, i2);
    }

    public IBinder onBind(Intent intent) {
        m.c(getApplicationContext());
        return this.a;
    }
}
