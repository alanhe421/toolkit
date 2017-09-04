package com.qq.reader.common.push.pushAction;

import android.content.Context;
import com.qq.reader.common.conn.a.c;
import org.json.JSONObject;

/* compiled from: IPInterceptAction */
public class g extends i {
    public g(Context context) {
        super(context);
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            String optString = jSONObject.optString("online");
            if (optString != null) {
                c.a().a(optString);
            }
        }
    }
}
