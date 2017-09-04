package com.qq.reader.common.receiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.t.d;
import com.qq.reader.activity.SplashActivity;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.utils.ao;
import com.tencent.feedback.proguard.R;
import java.util.Random;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

public class AlarmReceiver extends BroadcastReceiver {
    public int[] a = new int[]{R.string.alarm_notice_1, R.string.alarm_notice_2, R.string.alarm_notice_3, R.string.alarm_notice_4, R.string.alarm_notice_5};

    private int a() {
        return this.a[Math.abs(new Random().nextInt()) % 5];
    }

    public void onReceive(Context context, Intent intent) {
        j.a(51, 0);
        intent.putExtra("com.qq.reader.SplashActivity.alarm", true);
        intent.setClass(context, SplashActivity.class);
        intent.setFlags(335544320);
        PendingIntent activity = PendingIntent.getActivity(context, 0, intent, SigType.WLOGIN_PT4Token);
        d y = ao.y(context);
        y.b(context.getText(a()));
        y.a(activity);
        ((NotificationManager) context.getSystemService("notification")).notify(11, y.a());
        ao.m(context);
    }
}
