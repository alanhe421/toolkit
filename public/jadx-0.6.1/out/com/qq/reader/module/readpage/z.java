package com.qq.reader.module.readpage;

import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings.System;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.c.a;
import com.qq.reader.common.utils.ao;

/* compiled from: ScreenOffTimeOutHelper */
public abstract class z {
    public static void a(Handler handler) {
        if (VERSION.SDK_INT >= 23 && handler.hasMessages(1245)) {
            handler.removeMessages(1245);
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.what = 1245;
            obtainMessage.arg1 = (d.ai(ReaderApplication.getApplicationImp()) * 60) * 1000;
            handler.sendMessageDelayed(obtainMessage, (long) obtainMessage.arg1);
        }
    }

    public static void a(int i, Handler handler, Activity activity) {
        if (VERSION.SDK_INT < 23) {
            try {
                System.putInt(activity.getContentResolver(), "screen_off_timeout", i);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        ao.a(activity.getWindow(), true);
        handler.removeMessages(1245);
        Message obtainMessage = handler.obtainMessage();
        obtainMessage.what = 1245;
        obtainMessage.arg1 = i;
        handler.sendMessageDelayed(obtainMessage, (long) i);
    }

    public static void a(Handler handler, Activity activity) {
        handler.removeMessages(1245);
        b(handler, activity);
    }

    public static void b(Handler handler, Activity activity) {
        if (VERSION.SDK_INT < 23) {
            try {
                System.putInt(activity.getContentResolver(), "screen_off_timeout", a.j);
                return;
            } catch (Throwable th) {
                th.printStackTrace();
                return;
            }
        }
        ao.a(activity.getWindow(), false);
        handler.removeMessages(1245);
    }
}
