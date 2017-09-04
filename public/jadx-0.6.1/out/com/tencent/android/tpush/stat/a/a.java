package com.tencent.android.tpush.stat.a;

import android.content.Context;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class a {
    static c a;
    private static f d = e.b();
    private static JSONObject e = new JSONObject();
    Integer b = null;
    String c = null;

    static synchronized c a(Context context, long j) {
        c cVar;
        synchronized (a.class) {
            if (a == null) {
                a = new c(context.getApplicationContext(), j);
            }
            cVar = a;
        }
        return cVar;
    }

    public a(Context context, long j) {
        try {
            a(context, j);
            this.b = e.f(context.getApplicationContext());
            this.c = com.tencent.android.tpush.stat.a.a(context).a();
        } catch (Throwable th) {
            d.b(th);
        }
    }

    public void a(JSONObject jSONObject, Thread thread) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            if (a != null) {
                a.a(jSONObject2, thread);
            }
            h.a(jSONObject2, "cn", this.c);
            if (this.b != null) {
                jSONObject2.put("tn", this.b);
            }
            if (thread == null) {
                jSONObject.put("ev", jSONObject2);
            } else {
                jSONObject.put("errkv", jSONObject2.toString());
            }
            if (e != null && e.length() > 0) {
                jSONObject.put("eva", e);
            }
        } catch (Throwable th) {
            d.b(th);
        }
    }
}
