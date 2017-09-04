package com.yuewen.ywlogin.b;

import android.os.Handler;
import com.yuewen.ywlogin.a.j;
import org.json.JSONObject;

public class c {
    public static void a(j jVar, Handler handler, b bVar) {
        if (jVar.a()) {
            JSONObject b = jVar.b();
            if (b != null) {
                int optInt = b.optInt("code");
                String optString = b.optString("message");
                if (optInt != 0) {
                    a(optInt, optString, handler, bVar);
                    return;
                }
                JSONObject optJSONObject = b.optJSONObject("data");
                if (optJSONObject == null) {
                    return;
                }
                if (optJSONObject.has("nextAction")) {
                    a(optJSONObject.optInt("nextAction"), b, handler, bVar);
                } else {
                    c(b, handler, bVar);
                }
            }
        }
    }

    public static void a(int i, String str, Handler handler, b bVar) {
        if (handler != null) {
            handler.post(new f(bVar, i, str));
        }
    }

    public static void a(int i, JSONObject jSONObject, Handler handler, b bVar) {
        switch (i) {
            case 0:
                c(jSONObject, handler, bVar);
                return;
            case 8:
                b(jSONObject, handler, bVar);
                return;
            default:
                return;
        }
    }

    private static void b(JSONObject jSONObject, Handler handler, b bVar) {
        handler.post(new g(bVar, jSONObject));
    }

    private static void c(JSONObject jSONObject, Handler handler, b bVar) {
        if (jSONObject != null) {
            i.a().a(jSONObject.optJSONObject("data"));
        }
        handler.post(new h(bVar, jSONObject));
    }

    public static void a(long j, String str, j jVar, Handler handler, b bVar) {
        if (jVar.a()) {
            JSONObject b = jVar.b();
            if (b != null && b.optInt("code") == 0) {
                JSONObject optJSONObject = b.optJSONObject("data");
                if (optJSONObject != null) {
                    int optInt = optJSONObject.optInt("nextAction");
                    String optString = optJSONObject.optString("ywKey");
                    if (optInt == 0) {
                        i.a().a("LastAutoLoginTime", Long.valueOf(System.currentTimeMillis()));
                        if (str == null || !str.equals(optString)) {
                            a(b, handler, bVar);
                            i.a().a(optJSONObject);
                        }
                    }
                }
            }
        }
    }

    public static void a(JSONObject jSONObject, Handler handler, b bVar) {
        if (handler != null) {
            handler.post(new e(bVar, jSONObject));
        }
    }
}
