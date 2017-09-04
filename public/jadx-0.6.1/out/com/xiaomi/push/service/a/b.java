package com.xiaomi.push.service.a;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.SystemClock;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.channel.commonutils.e.a;
import com.xiaomi.push.service.o;
import com.xiaomi.smack.g;

class b implements a$a {
    private PendingIntent a = null;
    private Context b = null;
    private volatile long c = 0;

    public b(Context context) {
        this.b = context;
    }

    private void a(AlarmManager alarmManager, long j, PendingIntent pendingIntent) {
        try {
            AlarmManager.class.getMethod("setExact", new Class[]{Integer.TYPE, Long.TYPE, PendingIntent.class}).invoke(alarmManager, new Object[]{Integer.valueOf(0), Long.valueOf(j), pendingIntent});
        } catch (Throwable e) {
            c.a(e);
        }
    }

    public void a() {
        if (this.a != null) {
            ((AlarmManager) this.b.getSystemService("alarm")).cancel(this.a);
            this.a = null;
            c.c("unregister timer");
        }
        this.c = 0;
    }

    public void a(Intent intent, long j) {
        AlarmManager alarmManager = (AlarmManager) this.b.getSystemService("alarm");
        this.a = PendingIntent.getBroadcast(this.b, 0, intent, 0);
        if (VERSION.SDK_INT >= 23) {
            a.a(alarmManager, "setExactAndAllowWhileIdle", new Object[]{Integer.valueOf(0), Long.valueOf(j), this.a});
        } else if (VERSION.SDK_INT >= 19) {
            a(alarmManager, j, this.a);
        } else {
            alarmManager.set(0, j, this.a);
        }
        c.c("register timer " + j);
    }

    public void a(boolean z) {
        long c = (long) g.c();
        if (z || this.c != 0) {
            if (z) {
                a();
            }
            if (z || this.c == 0) {
                this.c = (c - (SystemClock.elapsedRealtime() % c)) + System.currentTimeMillis();
            } else {
                this.c += c;
                if (this.c < System.currentTimeMillis()) {
                    this.c = c + System.currentTimeMillis();
                }
            }
            Intent intent = new Intent(o.o);
            intent.setPackage(this.b.getPackageName());
            a(intent, this.c);
        }
    }

    public boolean b() {
        return this.c != 0;
    }
}
