package com.xiaomi.push.service;

import android.annotation.TargetApi;
import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import com.xiaomi.channel.commonutils.b.c;

public class XMJobService extends Service {
    static Service a = null;
    private IBinder b = null;

    @TargetApi(21)
    static class a extends JobService {
        Binder a;
        private Handler b;

        a(Service service) {
            this.a = null;
            this.a = (Binder) com.xiaomi.channel.commonutils.e.a.a((Object) this, "onBind", new Intent());
            com.xiaomi.channel.commonutils.e.a.a((Object) this, "attachBaseContext", service);
        }

        public boolean onStartJob(JobParameters jobParameters) {
            c.a("Job started " + jobParameters.getJobId());
            Intent intent = new Intent(this, XMPushService.class);
            intent.setAction("com.xiaomi.push.timer");
            intent.setPackage(getPackageName());
            startService(intent);
            if (this.b == null) {
                this.b = new a(this);
            }
            this.b.sendMessage(Message.obtain(this.b, 1, jobParameters));
            return true;
        }

        public boolean onStopJob(JobParameters jobParameters) {
            c.a("Job stop " + jobParameters.getJobId());
            return false;
        }
    }

    static Service a() {
        return a;
    }

    public IBinder onBind(Intent intent) {
        return this.b != null ? this.b : new Binder();
    }

    public void onCreate() {
        super.onCreate();
        if (VERSION.SDK_INT >= 21) {
            this.b = new a(this).a;
        }
        a = this;
    }

    public void onDestroy() {
        super.onDestroy();
        a = null;
    }
}
