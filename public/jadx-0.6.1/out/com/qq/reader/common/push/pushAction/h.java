package com.qq.reader.common.push.pushAction;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.t;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.push.a;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.qmessage.MessageActivity;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;
import org.json.JSONObject;

/* compiled from: MessageAction */
public class h extends i {
    public h(Context context) {
        super(context);
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        if (a.a.equals(this.b) || a.b.equals(this.b)) {
            jSONObject.optLong("time");
            String optString = jSONObject.optString("showmessage");
            int optInt = jSONObject.optInt("type");
            d.x(a(), optInt);
            Intent intent = new Intent();
            intent.setAction(com.qq.reader.common.c.a.cF);
            intent.putExtra("TYPE", optInt);
            a().sendBroadcast(intent);
            if (a.a.equals(this.b)) {
                a(optString);
            }
            i.a("event_C160", null, ReaderApplication.getApplicationImp());
            if (optInt == 2) {
                d.a(ReaderApplication.getApplicationImp(), System.currentTimeMillis());
            }
        } else if (a.c.equals(this.b)) {
            Intent intent2 = new Intent();
            intent2.putExtra("fromNotification", true);
            intent2.setClass(a(), MessageActivity.class);
            intent2.setFlags(335544320);
            a().startActivity(intent2);
        }
    }

    public void a(String str) {
        if (str != null && str.length() != 0) {
            NotificationManager notificationManager = (NotificationManager) a().getSystemService("notification");
            notificationManager.cancel(27);
            Intent intent = new Intent();
            t.d y = ao.y(a().getApplicationContext());
            y.a((CharSequence) "收到1条新消息");
            y.b((CharSequence) str);
            intent.putExtra("fromNotification", true);
            intent.setClass(a(), MessageActivity.class);
            intent.setFlags(335544320);
            y.a(PendingIntent.getActivity(a(), 27, intent, SigType.WLOGIN_PT4Token));
            notificationManager.notify(27, y.a());
        }
    }
}
