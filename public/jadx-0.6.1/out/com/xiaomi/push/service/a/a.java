package com.xiaomi.push.service.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import android.os.Build.VERSION;
import com.xiaomi.channel.commonutils.android.j;
import com.xiaomi.channel.commonutils.b.c;

public final class a {
    private static a a;

    public static synchronized void a() {
        synchronized (a.class) {
            if (a != null) {
                a.a();
            }
        }
    }

    public static void a(Context context) {
        Object obj = null;
        Context applicationContext = context.getApplicationContext();
        if ("com.xiaomi.xmsf".equals(applicationContext.getPackageName())) {
            a = new b(applicationContext);
            return;
        }
        try {
            PackageInfo packageInfo = applicationContext.getPackageManager().getPackageInfo(applicationContext.getPackageName(), 4);
            if (packageInfo.services != null) {
                for (ServiceInfo serviceInfo : packageInfo.services) {
                    if ("com.xiaomi.push.service.XMJobService".equals(serviceInfo.name) && "android.permission.BIND_JOB_SERVICE".equals(serviceInfo.permission)) {
                        obj = 1;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            c.a("check service err : " + e.getMessage());
        }
        if (obj == null && j.b(applicationContext)) {
            throw new RuntimeException("Should export service: com.xiaomi.push.service.XMJobService with permission android.permission.BIND_JOB_SERVICE in AndroidManifest.xml file");
        }
        if (VERSION.SDK_INT >= 21) {
            a = new b(applicationContext);
        } else {
            a = new b(applicationContext);
        }
    }

    public static synchronized void a(boolean z) {
        synchronized (a.class) {
            if (a == null) {
                c.a("timer is not initialized");
            } else {
                a.a(z);
            }
        }
    }

    public static synchronized boolean b() {
        boolean b;
        synchronized (a.class) {
            b = a == null ? false : a.b();
        }
        return b;
    }
}
