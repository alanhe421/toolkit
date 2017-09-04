package com.qq.reader.common.push.pushAction;

import android.content.Context;
import android.content.Intent;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.push.a;
import org.json.JSONObject;

/* compiled from: ActUpdateAction */
public class b extends i {
    public b(Context context) {
        super(context);
    }

    public void a(JSONObject jSONObject) {
        if (a.a.equals(this.b) && jSONObject != null) {
            try {
                d.p(a(), jSONObject.optString("ActUrl"));
                String optString = jSONObject.optString("id");
                long optLong = jSONObject.optLong("startTime");
                long optLong2 = jSONObject.optLong("endTime");
                int optInt = jSONObject.optInt("showTime", 0);
                boolean optBoolean = jSONObject.optBoolean("notifyOnce", false);
                i.a("event_A187", null, ReaderApplication.getApplicationImp());
                com.qq.reader.module.a.a.b().a(new com.qq.reader.module.a.b(optString, optLong, optLong2, optInt, optBoolean));
                Intent intent = new Intent();
                intent.setAction(com.qq.reader.common.c.a.cH);
                a().sendBroadcast(intent);
            } catch (Exception e) {
            }
        }
    }
}
