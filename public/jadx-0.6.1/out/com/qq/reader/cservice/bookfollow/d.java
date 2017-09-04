package com.qq.reader.cservice.bookfollow;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

/* compiled from: OrderBookService */
public class d {
    public static void a(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, new Intent(context, OrderBookReceiver.class), 0);
        alarmManager.cancel(broadcast);
        alarmManager.setRepeating(2, SystemClock.elapsedRealtime() + 120000, 1800000, broadcast);
    }
}
