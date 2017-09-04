package com.qq.reader.cservice.bookfollow;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.support.v4.app.t.d;
import android.support.v4.app.t.f;
import android.support.v4.app.t.p;
import android.widget.RemoteViews;
import com.qq.reader.activity.SwitchViewActivity;
import com.qq.reader.common.c.a;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.utils.ao;
import com.tencent.feedback.proguard.R;
import java.lang.reflect.Field;
import java.util.List;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

public class FollowBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if ("com.qq.reader.notification".equalsIgnoreCase(intent.getAction())) {
            List f = i.c().f();
            int size = f.size();
            if (size > 0) {
                Notification a;
                NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
                notificationManager.cancel(24);
                Intent intent2 = new Intent();
                String str = "";
                StringBuffer stringBuffer = new StringBuffer();
                d y = ao.y(context.getApplicationContext());
                a aVar;
                if (size == 1) {
                    aVar = (a) f.get(0);
                    CharSequence charSequence = "《" + aVar.a() + "》有更新";
                    y.c(charSequence);
                    stringBuffer.append(aVar.b());
                    CharSequence stringBuffer2 = stringBuffer.toString();
                    y.a(charSequence);
                    y.b(stringBuffer2);
                    intent2.setClass(context, SwitchViewActivity.class);
                    intent2.putExtra("notification_tag", (byte) 23);
                } else {
                    stringBuffer = new StringBuffer();
                    try {
                        stringBuffer.append(context.getResources().getString(R.string.app_name) + "：" + size + "本书有更新");
                    } catch (Exception e) {
                        e.printStackTrace();
                        stringBuffer.append("有书籍更新");
                    }
                    y.c(stringBuffer.toString());
                    p fVar = new f();
                    fVar.a(stringBuffer.toString());
                    y.a(stringBuffer.toString());
                    stringBuffer = new StringBuffer();
                    aVar = (a) f.get(0);
                    stringBuffer.append("《");
                    stringBuffer.append(aVar.a());
                    stringBuffer.append("》 ");
                    stringBuffer.append(aVar.b());
                    y.b(stringBuffer.toString());
                    for (int i = 0; i < f.size(); i++) {
                        if (i >= 5) {
                            fVar.b(" ...");
                            break;
                        }
                        StringBuffer stringBuffer3 = new StringBuffer();
                        aVar = (a) f.get(i);
                        stringBuffer3.append("《");
                        stringBuffer3.append(aVar.a());
                        stringBuffer3.append("》 ");
                        stringBuffer3.append(aVar.b());
                        fVar.b(stringBuffer3.toString());
                    }
                    y.a(fVar);
                    intent2.setClass(context, SwitchViewActivity.class);
                    intent2.putExtra("notification_tag", (byte) 23);
                }
                intent2.setFlags(335544320);
                y.a(PendingIntent.getActivity(context, 24, intent2, SigType.WLOGIN_PT4Token));
                Notification notification = null;
                try {
                    a = y.a();
                } catch (Exception e2) {
                    e2.printStackTrace();
                    a = notification;
                }
                if (a != null) {
                    if (VERSION.SDK_INT > 15) {
                        try {
                            int identifier = context.getResources().getIdentifier("right_icon", "id", "android");
                            Field declaredField;
                            if (size == 1) {
                                declaredField = a.getClass().getDeclaredField("contentView");
                                declaredField.setAccessible(true);
                                ((RemoteViews) declaredField.get(a)).setViewVisibility(identifier, 8);
                            } else {
                                declaredField = a.getClass().getDeclaredField("contentView");
                                declaredField.setAccessible(true);
                                ((RemoteViews) declaredField.get(a)).setViewVisibility(identifier, 8);
                                declaredField = a.getClass().getDeclaredField("bigContentView");
                                declaredField.setAccessible(true);
                                ((RemoteViews) declaredField.get(a)).setViewVisibility(identifier, 8);
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                    a.number = size;
                    try {
                        notificationManager.notify(24, a);
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                } else {
                    return;
                }
            }
            Intent intent3 = new Intent();
            intent3.setAction(a.cx);
            context.sendBroadcast(intent3);
            j.a(75, 0);
        }
    }

    public static void a(Context context) {
        try {
            ((NotificationManager) context.getSystemService("notification")).cancel(24);
        } catch (Exception e) {
            c.e("", e.getMessage());
        }
    }

    public static void b(Context context) {
    }
}
