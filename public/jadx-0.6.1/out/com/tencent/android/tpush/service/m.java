package com.tencent.android.tpush.service;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.net.LocalServerSocket;
import android.os.Handler;
import android.os.Looper;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.g;
import com.tencent.android.tpush.common.p;
import com.tencent.android.tpush.service.a.c;
import com.tencent.android.tpush.service.a.d;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.d.f;
import java.util.List;

/* compiled from: ProGuard */
public class m {
    private static Context a = null;
    private static String b = "";
    private static LocalServerSocket c = null;
    private static volatile boolean e = false;
    private static volatile boolean f = false;
    private Handler d;

    private m() {
        this.d = null;
    }

    public static m a() {
        return o.a;
    }

    public void b() {
        c.a(a, new d(Constants.PUSH_SDK_VERSION, 0));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(android.content.Intent r7) {
        /*
        r6 = this;
        r4 = 1;
        r0 = 0;
        r2 = r6.d;
        if (r2 != 0) goto L_0x000a;
    L_0x0007:
        r6.k();
    L_0x000a:
        monitor-enter(r6);
        r2 = e;	 Catch:{ all -> 0x0053 }
        if (r2 == 0) goto L_0x0074;
    L_0x000f:
        r2 = c;	 Catch:{ all -> 0x0053 }
        if (r2 == 0) goto L_0x0074;
    L_0x0013:
        if (r7 == 0) goto L_0x0045;
    L_0x0015:
        r2 = r7.getAction();	 Catch:{ all -> 0x0053 }
        if (r2 == 0) goto L_0x0045;
    L_0x001b:
        r3 = "com.tencent.android.tpush.action.keepalive";
        r3 = r3.equals(r2);	 Catch:{ all -> 0x0053 }
        if (r3 == 0) goto L_0x0056;
    L_0x0024:
        r2 = r6.d;	 Catch:{ all -> 0x0053 }
        r3 = 2;
        r2 = r2.obtainMessage(r3);	 Catch:{ all -> 0x0053 }
        r3 = "delay_time";
        r4 = 0;
        r4 = r7.getLongExtra(r3, r4);	 Catch:{ all -> 0x0053 }
        r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
        if (r0 != 0) goto L_0x0047;
    L_0x0038:
        r0 = r6.d;	 Catch:{ all -> 0x0053 }
        r1 = 2;
        r0.removeMessages(r1);	 Catch:{ all -> 0x0053 }
        r0 = r6.d;	 Catch:{ all -> 0x0053 }
        r4 = 100;
        r0.sendMessageDelayed(r2, r4);	 Catch:{ all -> 0x0053 }
    L_0x0045:
        monitor-exit(r6);	 Catch:{ all -> 0x0053 }
    L_0x0046:
        return;
    L_0x0047:
        r0 = r6.d;	 Catch:{ all -> 0x0053 }
        r1 = 2;
        r0.removeMessages(r1);	 Catch:{ all -> 0x0053 }
        r0 = r6.d;	 Catch:{ all -> 0x0053 }
        r0.sendMessageDelayed(r2, r4);	 Catch:{ all -> 0x0053 }
        goto L_0x0045;
    L_0x0053:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0053 }
        throw r0;
    L_0x0056:
        r0 = "com.tencent.android.tpush.action.stop_connect";
        r0 = r0.equals(r2);	 Catch:{ all -> 0x0053 }
        if (r0 == 0) goto L_0x0045;
    L_0x005f:
        r0 = r6.d;	 Catch:{ all -> 0x0053 }
        r1 = 3;
        r0 = r0.obtainMessage(r1);	 Catch:{ all -> 0x0053 }
        r1 = r6.d;	 Catch:{ all -> 0x0053 }
        r2 = 3;
        r1.removeMessages(r2);	 Catch:{ all -> 0x0053 }
        r1 = r6.d;	 Catch:{ all -> 0x0053 }
        r2 = 100;
        r1.sendMessageDelayed(r0, r2);	 Catch:{ all -> 0x0053 }
        goto L_0x0045;
    L_0x0074:
        monitor-exit(r6);	 Catch:{ all -> 0x0053 }
        r2 = e();
        r2 = com.tencent.android.tpush.service.cache.CacheManager.getRegisterInfos(r2);
        if (r2 == 0) goto L_0x009b;
    L_0x007f:
        r2 = r2.size();
        if (r2 <= r4) goto L_0x009b;
    L_0x0085:
        r0 = java.lang.Math.random();
        r2 = 4652007308841189376; // 0x408f400000000000 float:0.0 double:1000.0;
        r0 = r0 * r2;
        r0 = (int) r0;
        r0 = r0 + 900;
        r0 = (long) r0;
        r2 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r2 >= 0) goto L_0x009b;
    L_0x0099:
        r0 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
    L_0x009b:
        r2 = r6.d;
        r2 = r2.obtainMessage(r4);
        r3 = r6.d;
        r3.sendMessageDelayed(r2, r0);
        goto L_0x0046;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.service.m.a(android.content.Intent):void");
    }

    public static void a(Context context) {
        a(context, Constants.ACTION_KEEPALIVE, 0);
    }

    public static void a(Context context, long j) {
        a(context, Constants.ACTION_KEEPALIVE, j);
    }

    public static void a(Context context, String str, long j) {
        Object th;
        Intent intent;
        if (context != null) {
            Object intent2;
            try {
                intent2 = new Intent();
                try {
                    intent2.setClass(context, XGPushService.class);
                    intent2.setAction(str);
                    if (j != 0) {
                        intent2.putExtra(Constants.NETWORK_RESTAT_DELAY_TIME, j);
                    }
                    if (p.a(context) <= 0) {
                        context.startService(intent2);
                        return;
                    }
                    a.h("PushServiceManager", "startService failed, libtpnsSecurity.so not found.");
                    context.stopService(intent2);
                } catch (Throwable th2) {
                    th = th2;
                    a.h("PushServiceManager", "startService failed, intent:" + intent2 + ", ex:" + th);
                    try {
                        intent = new Intent();
                        try {
                            intent.setClass(context, XGPushService.class);
                            if (p.a(context) > 0) {
                                a.h("PushServiceManager", "startService failed, libtpnsSecurity.so not found.");
                                context.stopService(intent);
                            }
                            context.startService(intent);
                        } catch (Throwable th3) {
                            th = th3;
                            intent2 = intent;
                            a.h("PushServiceManager", "222 startService failed, intent:" + intent2 + ", ex:" + th);
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        a.h("PushServiceManager", "222 startService failed, intent:" + intent2 + ", ex:" + th);
                    }
                }
            } catch (Throwable th5) {
                th = th5;
                intent2 = null;
                a.h("PushServiceManager", "startService failed, intent:" + intent2 + ", ex:" + th);
                intent = new Intent();
                intent.setClass(context, XGPushService.class);
                if (p.a(context) > 0) {
                    context.startService(intent);
                }
                a.h("PushServiceManager", "startService failed, libtpnsSecurity.so not found.");
                context.stopService(intent);
            }
        }
    }

    public static void b(Context context) {
        a.e("PushServiceManager", "Action -> stop Current Connect");
        a(context, Constants.ACTION_STOP_CONNECT, 0);
    }

    public void c() {
        a.c("PushServiceManager", "@@ serviceExit()");
        p.a();
        if (this.d != null) {
            this.d.removeCallbacksAndMessages(null);
            this.d = null;
        }
        if (g.a().b() != null) {
            g.a().b().removeCallbacksAndMessages(null);
        }
        a.a().b(a);
        d();
        f.q(e());
    }

    public void d() {
        synchronized (this) {
            if (c != null) {
                try {
                    c.close();
                    c = null;
                } catch (Throwable e) {
                    a.c(Constants.ServiceLogTag, ">> Destroy local socket exception", e);
                }
            }
            e = Boolean.valueOf(false).booleanValue();
        }
    }

    public static void c(Context context) {
        if (context != null) {
            a = context;
            b = context.getPackageName();
        }
    }

    public static Context e() {
        return a;
    }

    public static String f() {
        return b;
    }

    private boolean i() {
        try {
            List registerInfos = CacheManager.getRegisterInfos(e());
            if (registerInfos != null && registerInfos.size() >= 2) {
                d dVar = null;
                d a = c.a(a);
                List<RunningServiceInfo> runningServices = ((ActivityManager) a.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningServices(Integer.MAX_VALUE);
                if (runningServices != null && runningServices.size() > 0) {
                    String name = XGPushService.class.getName();
                    for (RunningServiceInfo runningServiceInfo : runningServices) {
                        d a2;
                        if (name.equals(runningServiceInfo.service.getClassName())) {
                            a2 = c.a(a, runningServiceInfo.service.getPackageName());
                            if (dVar != null) {
                                if (a2.a > dVar.a) {
                                }
                            }
                            dVar = a2;
                        }
                        a2 = dVar;
                        dVar = a2;
                    }
                }
                if (dVar != null && dVar.a > a.a) {
                    return false;
                }
            }
        } catch (Throwable e) {
            a.c("PushServiceManager", "isSurvive", e);
        }
        return true;
    }

    private boolean j() {
        boolean i = i();
        synchronized (this) {
            if (i) {
                try {
                    String a = f.a(a, Constants.SETTINGS_SOCKET_NAME, true);
                    if (f.a(a)) {
                        a = f.a();
                        f.a(a, Constants.SETTINGS_SOCKET_NAME, a, true);
                    }
                    p.g(a);
                    c = new LocalServerSocket(a);
                    e = Boolean.valueOf(true).booleanValue();
                    XGWatchdog.getInstance(a).startWatchdog();
                    w.a(a).a();
                } catch (Throwable th) {
                    i = e;
                }
            }
        }
        return i;
    }

    private void k() {
        this.d = new n(this, Looper.getMainLooper());
    }
}
