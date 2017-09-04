package com.qq.reader.common.login;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import com.qq.reader.common.monitor.debug.c;
import com.tencent.util.TimeFormatterUtils;

/* compiled from: LoginService */
public class d {
    public static long a = TimeFormatterUtils.ONE_DAY;

    public static void a(Context context, int i, boolean z) {
        switch (i) {
            case 1:
                a = 10800000;
                break;
            case 2:
            case 3:
            case 10:
            case 50:
                a = 3600000;
                break;
            default:
                try {
                    a = TimeFormatterUtils.ONE_DAY;
                    break;
                } catch (Exception e) {
                    c.a("LoginService", e.toString(), true);
                    return;
                }
        }
        if (z) {
            Intent intent = new Intent(context, LoginReceiver.class);
            intent.putExtra("type", i);
            context.sendBroadcast(intent);
        }
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        Intent intent2 = new Intent(context, LoginReceiver.class);
        intent2.putExtra("type", i);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent2, 0);
        alarmManager.cancel(broadcast);
        alarmManager.setRepeating(2, SystemClock.elapsedRealtime() + a, a, broadcast);
    }

    public static void a(Context context) {
        try {
            ((AlarmManager) context.getSystemService("alarm")).cancel(PendingIntent.getBroadcast(context, 0, new Intent(context, LoginReceiver.class), 0));
        } catch (Exception e) {
            c.a("LoginService", e.toString(), true);
        }
    }
}
