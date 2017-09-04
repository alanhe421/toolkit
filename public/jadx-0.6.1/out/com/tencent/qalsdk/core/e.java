package com.tencent.qalsdk.core;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import com.tencent.qalsdk.service.QalService;
import com.tencent.qalsdk.util.QLog;

/* compiled from: LogManager */
public class e {
    static String a = "MSF.C.LogManager";
    static boolean b = true;
    static int c = 0;
    static boolean d = false;
    static long e = 0;
    static boolean f = false;
    private static final BroadcastReceiver g = new f();

    public static synchronized void a() {
        synchronized (e.class) {
            if (!f) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.SCREEN_ON");
                intentFilter.addAction("android.intent.action.SCREEN_OFF");
                QalService.context.registerReceiver(g, intentFilter);
                if (QLog.isColorLevel()) {
                    QLog.d(a, 2, "LogManager inited.");
                }
                f = true;
            }
        }
    }

    private static void c() {
        Thread gVar = new g();
        gVar.setName("delLogThread");
        gVar.start();
    }
}
