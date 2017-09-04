package com.tencent.android.tpush.stat.b;

import android.util.Log;
import com.tencent.android.tpush.stat.a.h;
import com.tencent.mid.api.MidEntity;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
class d {
    String a = null;
    String b = null;
    String c = "0";
    long d = 0;

    d() {
    }

    boolean a() {
        return c.a(this.c);
    }

    static d a(String str) {
        d dVar = new d();
        if (str != null && str.length() > 0) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (!jSONObject.isNull("ui")) {
                    dVar.a = jSONObject.getString("ui");
                }
                if (!jSONObject.isNull("mc")) {
                    dVar.b = jSONObject.getString("mc");
                }
                if (!jSONObject.isNull("mid")) {
                    dVar.c = jSONObject.getString("mid");
                }
                if (!jSONObject.isNull(MidEntity.TAG_TIMESTAMPS)) {
                    dVar.d = jSONObject.getLong(MidEntity.TAG_TIMESTAMPS);
                }
            } catch (Throwable e) {
                Log.w("MID", "", e);
            }
        }
        return dVar;
    }

    public String toString() {
        return b().toString();
    }

    JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            h.a(jSONObject, "ui", this.a);
            h.a(jSONObject, "mc", this.b);
            h.a(jSONObject, "mid", this.c);
            jSONObject.put(MidEntity.TAG_TIMESTAMPS, this.d);
        } catch (JSONException e) {
        }
        return jSONObject;
    }
}
