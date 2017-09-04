package com.iflytek.common;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.Process;
import com.iflytek.common.a.d;
import com.iflytek.common.c.c;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

public class LaunchService extends Service {
    private d a;
    private long b = 0;
    private BroadcastReceiver c = new b(this);

    private void a() {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(getPackageName() + "_LAUNCH_ALARM_LAUNCH");
            registerReceiver(this.c, intentFilter);
        } catch (Throwable e) {
            c.b("LaunchService", "", e);
        }
    }

    private synchronized void b() {
        long c = this.a.c();
        long currentTimeMillis = c + System.currentTimeMillis();
        long j = currentTimeMillis - this.b;
        if (j <= 0 || j >= c) {
            try {
                AlarmManager alarmManager = (AlarmManager) getSystemService("alarm");
                PendingIntent broadcast = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(getPackageName() + "_LAUNCH_ALARM_LAUNCH"), SigType.WLOGIN_PT4Token);
                if (c > 0) {
                    this.b = currentTimeMillis;
                    alarmManager.cancel(broadcast);
                    alarmManager.set(0, this.b, broadcast);
                    c.a((Context) this, "reg next alarm: " + c.a(this.b));
                } else {
                    c.a((Context) this, "not reg alarm,periodrun = 0.");
                }
            } catch (Throwable e) {
                c.b("LaunchService", "", e);
            }
        } else {
            c.a((Context) this, "reg next alarm too short:" + j);
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        c.a((Context) this, "LaunchService onCreate pid=" + Process.myTid());
        this.a = d.a(getApplicationContext());
        a();
        com.iflytek.common.a.c.a(this);
        b();
    }

    public void onDestroy() {
        super.onDestroy();
        c.a((Context) this, "LaunchService onDestroy");
        try {
            unregisterReceiver(this.c);
        } catch (Throwable e) {
            c.b("LaunchService", "", e);
        }
    }

    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
        c.a((Context) this, "LaunchService onStart lastalarm=" + this.b + " periodrun=" + this.a.c());
        if (this.b == 0 && this.a.c() > 0) {
            b();
        }
    }
}
