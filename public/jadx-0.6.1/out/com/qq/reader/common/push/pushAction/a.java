package com.qq.reader.common.push.pushAction;

import android.content.Context;
import com.qq.reader.common.a.b;
import org.json.JSONObject;

/* compiled from: ABTestAction */
public class a extends i {
    public a(Context context) {
        super(context);
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            b.a(jSONObject.optString("plan_all"));
        }
    }
}
