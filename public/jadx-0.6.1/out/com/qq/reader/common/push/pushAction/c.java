package com.qq.reader.common.push.pushAction;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.t.d;
import com.qq.reader.activity.MainActivity;
import com.qq.reader.common.push.a;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.bookshelf.b;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;
import org.json.JSONObject;

/* compiled from: ActivateShelfAction */
public class c extends i {
    public c(Context context) {
        super(context);
    }

    public void a(JSONObject jSONObject) {
        if (a.a.equals(this.b) || a.b.equals(this.b)) {
            if (jSONObject != null) {
                long optLong = jSONObject.optLong("mBookNetId");
                long optLong2 = jSONObject.optLong("mStartTime");
                long optLong3 = jSONObject.optLong("mEndTime");
                String optString = jSONObject.optString("mLinkUrl");
                String optString2 = jSONObject.optString("mImageUrl");
                String optString3 = jSONObject.optString("mContentStr");
                String optString4 = jSONObject.optString("showBookName");
                String optString5 = jSONObject.optString("showMessage");
                if (optString5.length() == 0) {
                    optString5 = optString3;
                }
                b bVar = new b(optLong, optLong2, optLong3);
                bVar.b(optString2);
                bVar.a(optString);
                bVar.c(optString3);
                g.a().a(new ActivateShelfAction$1(this, bVar));
                if (a.a.equals(this.b)) {
                    a(optString4, optString5);
                }
            }
        } else if (a.c.equals(this.b)) {
            Intent intent = new Intent();
            intent.putExtra("fromNotification", 0);
            intent.setClass(a(), MainActivity.class);
            intent.setFlags(335544320);
            a().startActivity(intent);
        }
    }

    public void a(String str, String str2) {
        if (str != null && str.length() != 0 && str2 != null && str2.length() != 0) {
            NotificationManager notificationManager = (NotificationManager) a().getSystemService("notification");
            notificationManager.cancel(29);
            d y = ao.y(a().getApplicationContext());
            Intent intent = new Intent();
            y.a((CharSequence) str);
            y.b((CharSequence) str2);
            intent.putExtra("fromNotification", 0);
            intent.setClass(a(), MainActivity.class);
            intent.setFlags(335544320);
            y.a(PendingIntent.getActivity(a(), 29, intent, SigType.WLOGIN_PT4Token));
            notificationManager.notify(29, y.a());
        }
    }
}
