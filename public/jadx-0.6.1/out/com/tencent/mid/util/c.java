package com.tencent.mid.util;

import android.content.Context;
import org.json.JSONObject;

public class c {
    static e a;
    private static f d = Util.getLogger();
    private static JSONObject e = null;
    Integer b = null;
    String c = null;

    public c(Context context) {
        try {
            a(context);
            this.b = j.h(context.getApplicationContext());
            this.c = j.g(context);
        } catch (Throwable th) {
            d.f(th);
        }
    }

    static synchronized e a(Context context) {
        e eVar;
        synchronized (c.class) {
            if (a == null) {
                a = new e(context.getApplicationContext());
            }
            eVar = a;
        }
        return eVar;
    }

    public void a(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            if (a != null) {
                a.a(jSONObject2);
            }
            Util.jsonPut(jSONObject2, "cn", this.c);
            if (this.b != null) {
                jSONObject2.put("tn", this.b);
            }
            jSONObject.put("ev", jSONObject2);
            if (e != null && e.length() > 0) {
                jSONObject.put("eva", e);
            }
        } catch (Throwable th) {
            d.f(th);
        }
    }
}
