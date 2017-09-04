package com.qq.reader.common.push.pushAction.a;

import android.content.Context;
import org.json.JSONObject;

/* compiled from: RichMediaCardBuilder */
public class b {
    public static a a(JSONObject jSONObject, Context context) {
        switch (jSONObject.optInt("style")) {
            case 0:
                return new c(context, jSONObject);
            default:
                return null;
        }
    }
}
