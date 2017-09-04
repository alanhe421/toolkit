package com.qq.reader.module.game.activity;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import com.qq.reader.common.monitor.debug.c;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.QbSdk.WebviewInitType;

public class H5GameProcessService extends Service {
    a a = null;

    class a extends BroadcastReceiver {
        final /* synthetic */ H5GameProcessService a;

        a(H5GameProcessService h5GameProcessService) {
            this.a = h5GameProcessService;
        }

        public void onReceive(Context context, Intent intent) {
            c.e("testservice", "stopService");
            this.a.stopSelf();
        }
    }

    public void onCreate() {
        this.a = new a(this);
        registerReceiver(this.a, new IntentFilter("com.qq.reader.module.game.stop.H5GameProcessService"));
        QbSdk.initX5Environment(getApplicationContext(), WebviewInitType.FIRSTUSE_AND_PRELOAD, null);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null) {
            c.e("testservice", "onStartCommand");
        }
        return 2;
    }

    public IBinder onBind(Intent intent) {
        c.e("testservice", "onBind");
        return null;
    }

    public boolean onUnbind(Intent intent) {
        try {
            if (this.a != null) {
                unregisterReceiver(this.a);
            }
        } catch (Exception e) {
            c.e("H5GameProcessService", e.toString());
        }
        return super.onUnbind(intent);
    }

    public void onDestroy() {
        try {
            if (this.a != null) {
                unregisterReceiver(this.a);
            }
        } catch (Exception e) {
            c.e("H5GameProcessService", e.toString());
        }
        super.onDestroy();
    }
}
