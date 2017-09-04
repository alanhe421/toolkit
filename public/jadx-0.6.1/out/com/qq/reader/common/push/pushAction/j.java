package com.qq.reader.common.push.pushAction;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.t;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.TypeContext;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.push.a;
import com.qq.reader.common.utils.ao;
import com.tencent.feedback.proguard.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;
import org.json.JSONObject;

/* compiled from: QURLAction */
public class j extends i {
    SimpleDateFormat c = new SimpleDateFormat("yyyyMMdd");

    public j(Context context) {
        super(context);
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            jSONObject.optLong("time");
            String optString = jSONObject.optString("message");
            String optString2 = jSONObject.optString("title");
            String optString3 = jSONObject.optString("qurl");
            if (jSONObject.optInt("personalize") == 1) {
                Object d = d.d(a());
                String format = this.c.format(new Date());
                if (TextUtils.isEmpty(d) || !d.equals(format)) {
                    d.a(a(), format);
                } else {
                    return;
                }
            }
            if (a.a.equals(this.b)) {
                a(optString2, optString, optString3);
            } else if (a.c.equals(this.b)) {
                Intent intent = new Intent();
                intent.setClass(a(), TypeContext.class);
                intent.setFlags(335544320);
                intent.setData(Uri.parse(optString3));
                a().startActivity(intent);
            }
        }
    }

    public void a(String str, String str2, String str3) {
        int hashCode = str3.hashCode();
        if ((str3 != null && str3.startsWith("uniteqqreader://")) || (str2 != null && str2.length() != 0)) {
            NotificationManager notificationManager = (NotificationManager) a().getSystemService("notification");
            Intent intent = new Intent();
            t.d y = ao.y(a().getApplicationContext());
            if (str == null || str.length() == 0) {
                y.a(ReaderApplication.getApplicationImp().getString(R.string.app_name));
            } else {
                y.a((CharSequence) str);
            }
            y.b((CharSequence) str2);
            intent.setClass(a(), TypeContext.class);
            intent.setFlags(335544320);
            intent.setData(Uri.parse(str3));
            y.a(PendingIntent.getActivity(a(), hashCode, intent, SigType.WLOGIN_PT4Token));
            notificationManager.notify(hashCode, y.a());
        }
    }
}
