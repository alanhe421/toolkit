package com.tencent.imsdk;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;

public class LogManager {
    static boolean isInited = false;
    static boolean isScreenOn = true;
    private static final BroadcastReceiver mReceiver = new bl();
    static int screenOffCount = 0;
    static String tag = "MSF.C.LogManager";

    private static void delExpiresLog() {
        Thread bmVar = new bm();
        bmVar.setName("imsdkDelLogThread");
        bmVar.start();
    }

    public static synchronized void init() {
        synchronized (LogManager.class) {
            if (!isInited) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.SCREEN_ON");
                intentFilter.addAction("android.intent.action.SCREEN_OFF");
                IMMsfCoreProxy.get().getContext().registerReceiver(mReceiver, intentFilter);
                QLog.i(tag, 1, "LogManager inited.");
                isInited = true;
            }
        }
    }
}
