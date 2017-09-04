package com.sijla.j;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

public class h {
    public static void a(Context context, int i, Class<?> cls) {
        try {
            ((AlarmManager) context.getSystemService("alarm")).setRepeating(3, SystemClock.elapsedRealtime(), (long) (i * 1000), PendingIntent.getService(context, 0, new Intent(context, cls), SigType.WLOGIN_PT4Token));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(Context context, Class<?> cls) {
        try {
            ((AlarmManager) context.getSystemService("alarm")).cancel(PendingIntent.getService(context, 0, new Intent(context, cls), SigType.WLOGIN_PT4Token));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
