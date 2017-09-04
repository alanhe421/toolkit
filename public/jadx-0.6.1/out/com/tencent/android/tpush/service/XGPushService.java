package com.tencent.android.tpush.service;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.IBinder;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.p;
import com.tencent.android.tpush.service.d.f;

/* compiled from: ProGuard */
public class XGPushService extends Service {
    private static Service a = null;

    public IBinder onBind(Intent intent) {
        return null;
    }

    private void b() {
        boolean z = true;
        if (f.b(getApplicationContext(), "com.tencent.android.tpush.debug," + getApplicationContext().getPackageName(), 0) != 1) {
            z = false;
        }
        XGPushConfig.enableDebug = z;
        if (z) {
            a.a(2);
        } else {
            a.a(3);
        }
    }

    public void onCreate() {
        super.onCreate();
        a = this;
        if (VERSION.SDK_INT < 18) {
            startForeground(-1998, new Notification());
        }
        Context applicationContext = getApplicationContext();
        com.tencent.android.tpush.service.c.a.a(applicationContext);
        m.c(applicationContext);
        b();
        if (XGPushConfig.enableDebug) {
            a.a("XGPushService", "onCreate() : " + getPackageName());
        }
        m.a().b();
    }

    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
    }

    public static Service a() {
        return a;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, 1, i2);
        if (p.a(getApplicationContext()) > 0) {
            f.q(getApplicationContext());
            return 2;
        }
        b();
        f.a(m.e(), "tpush.wifi.bandon", "", true);
        m.a().a(intent);
        return 1;
    }

    public void onDestroy() {
        m.a().c();
        super.onDestroy();
    }
}
