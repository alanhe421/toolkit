package com.tencent.mid.a;

import android.content.Context;
import org.json.JSONObject;

public abstract class e {
    protected Context a = null;

    protected e(Context context) {
        this.a = context.getApplicationContext();
    }

    protected abstract int a();

    public JSONObject a(JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        jSONObject.put("et", a());
        b(jSONObject);
        return jSONObject;
    }

    protected abstract void b(JSONObject jSONObject);
}
