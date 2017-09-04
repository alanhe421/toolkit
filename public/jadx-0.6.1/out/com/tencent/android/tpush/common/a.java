package com.tencent.android.tpush.common;

import android.content.Context;
import android.content.IntentFilter;

/* compiled from: ProGuard */
public class a {
    private static volatile d a = null;

    public static void a(Context context) {
        try {
            if (a == null) {
                synchronized (a.class) {
                    if (a == null) {
                        a = new d();
                        IntentFilter intentFilter = new IntentFilter();
                        intentFilter.addDataScheme("package");
                        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
                        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
                        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
                        context.getApplicationContext().registerReceiver(a, intentFilter);
                    }
                }
            }
        } catch (Throwable e) {
            com.tencent.android.tpush.a.a.c(Constants.LogTag, "AppChangesHandler setupHandler error", e);
        }
    }
}
