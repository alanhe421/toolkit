package com.tencent.android.tpush.common;

import android.content.Context;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.c.a;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class n {
    private static n a = null;
    private Context b = null;
    private String c = null;
    private String d = null;

    private n(Context context) {
        this.b = context.getApplicationContext();
        this.c = p.f(context);
        this.d = String.valueOf(Constants.PUSH_SDK_VERSION);
    }

    public static synchronized n a(Context context) {
        n nVar;
        synchronized (n.class) {
            if (a == null) {
                a = new n(context);
            }
            nVar = a;
        }
        return nVar;
    }

    public String a() {
        Object obj = null;
        JSONObject jSONObject = new JSONObject();
        try {
            int i;
            e.a(jSONObject, "appVer", this.c);
            e.a(jSONObject, "appSdkVer", this.d);
            e.a(jSONObject, "ch", XGPushConfig.getInstallChannel(this.b));
            e.a(jSONObject, "gs", XGPushConfig.getGameServer(this.b));
            if (o.a(this.b).b() && a.d(this.b)) {
                String b = a.b();
                Object c = a.c(this.b);
                com.tencent.android.tpush.a.a.e(Constants.OTHER_PUSH_TAG, "Reservert info: other push token is : " + c + "  other push type: " + b);
                if (!(p.b(b) || p.b((String) c))) {
                    e.a(jSONObject, b, c);
                    obj = 1;
                }
            }
            if (obj == null) {
                com.tencent.android.tpush.a.a.e(Constants.OTHER_PUSH_TAG, "Reservert info: use normal xg token register");
                e.a(jSONObject, "gcm", (Object) "");
                e.a(jSONObject, "miid", (Object) "");
            }
            int a = m.a(this.b, ".firstregister", 1);
            int a2 = m.a(this.b, ".usertype", 0);
            long a3 = m.a(this.b, ".installtime", 0);
            long currentTimeMillis = System.currentTimeMillis();
            if (a3 == 0) {
                m.b(this.b, ".installtime", currentTimeMillis);
                i = a2;
            } else {
                if (a2 == 0 && a != 1) {
                    if (!p.a(a3).equals(p.a(System.currentTimeMillis()))) {
                        m.b(this.b, ".usertype", 1);
                        currentTimeMillis = a3;
                        i = 1;
                    }
                }
                currentTimeMillis = a3;
                i = a2;
            }
            jSONObject.put("ut", i);
            if (a == 1) {
                jSONObject.put("freg", 1);
            }
            jSONObject.put("it", (int) (currentTimeMillis / 1000));
            if (p.b(this.b)) {
                jSONObject.put("aidl", 1);
            }
        } catch (Throwable e) {
            com.tencent.android.tpush.a.a.c("RegisterReservedInfo", "toSting", e);
        }
        return jSONObject.toString();
    }
}
