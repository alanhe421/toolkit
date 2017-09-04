package com.sijla.common;

import android.content.Context;
import com.sijla.j.a.a;
import com.sijla.j.b;
import com.sijla.j.f;
import com.sijla.j.g;
import com.sijla.j.i;
import com.sijla.j.j;
import com.tencent.mid.api.MidEntity;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

public class d {
    private static boolean a(Context context) {
        String str = (String) j.b(context, "qdd", "");
        return str == null || !str.equals(b.c());
    }

    public static void a(final Context context, JSONObject jSONObject) {
        String optString = jSONObject.optString("qtdau", "");
        if (!b.a(optString) && a(context) && a.b(context)) {
            JSONObject b = b(context);
            if (b != null) {
                g.a(optString, b, new g.a() {
                    public void a(String str, JSONObject jSONObject) {
                        if (jSONObject != null) {
                            f.a("Qtdau report(" + str + ")response = " + jSONObject);
                            if ("ok".equals(jSONObject.optString("status")) || "ok".equals(jSONObject.optString(SocialConstants.PARAM_SEND_MSG))) {
                                j.a(context, "qdd", b.c());
                                f.a("Qtdau report success");
                            }
                        }
                    }

                    public void a(String str) {
                        f.a("Qtdau report onError:" + str);
                    }
                }, true);
            }
        }
    }

    private static JSONObject b(Context context) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("time", b.a());
            jSONObject.put("qid", i.b(context));
            jSONObject.put("did", a.i(context));
            String packageName = context.getPackageName();
            jSONObject.put("appid", packageName);
            jSONObject.put("appver", a.a(packageName, context));
            jSONObject.put("uid3", b.a());
            jSONObject.put("channel", b.m(context));
            jSONObject.put("firstchannel", b.n(context));
            String[] m = b.m();
            String str = m[1];
            f.a("Qtdau Report ts = " + str);
            jSONObject.put(MidEntity.TAG_TIMESTAMPS, str);
            String str2 = m[0];
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("s1", str);
            jSONObject2.put("s2", com.sijla.f.b.a(str2, jSONObject.toString()));
            jSONObject2.put("ln", "qtdau");
            return jSONObject2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
