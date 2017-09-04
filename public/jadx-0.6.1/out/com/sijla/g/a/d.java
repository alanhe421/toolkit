package com.sijla.g.a;

import android.content.Context;
import android.os.Build.VERSION;
import com.sijla.d.c;
import com.sijla.j.a.a;
import com.sijla.j.b;
import com.sijla.j.f;
import com.sijla.j.g;
import com.sijla.j.i;
import com.tencent.mid.api.MidEntity;
import org.json.JSONArray;
import org.json.JSONObject;

public class d implements b {
    public void a(Context context, final String str) {
        Object obj = 1;
        try {
            if (VERSION.SDK_INT < 20) {
                obj = null;
            }
            if (c.b != null && a(context, str, c.b)) {
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObject2 = new JSONObject();
                jSONObject.put("qid", i.b(context));
                jSONObject.put("did", a.i(context));
                jSONObject.put("appid", str);
                jSONObject.put("appver", a.a(str, context));
                jSONObject.put("appkey", b.p(context));
                String[] m = b.m();
                Object obj2 = m[1];
                jSONObject.put(MidEntity.TAG_TIMESTAMPS, obj2);
                jSONObject.put("time", b.a());
                jSONObject.put("ssid", a.e(context));
                jSONObject.put("locid", b.h(context));
                jSONObject.put("ard5", obj != null ? "1" : "0");
                String str2 = m[0];
                jSONObject2.put("s1", obj2);
                jSONObject2.put("ln", "realsession");
                jSONObject2.put("s2", com.sijla.f.b.a(str2, jSONObject.toString()));
                g.a("http://www.qmlog.cn/n/mlog/", jSONObject2, new g.a(this) {
                    final /* synthetic */ d b;

                    public void a(String str, JSONObject jSONObject) {
                        f.a("RealSessionEvent.handle appid = [" + str + "] success");
                    }

                    public void a(String str) {
                        f.a("report real session error:" + str);
                    }
                }, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static boolean a(Context context, String str, JSONArray jSONArray) {
        String a = a.a(str, context);
        if (jSONArray == null || b.a(str)) {
            return false;
        }
        try {
            if (jSONArray.length() > 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = new JSONObject(jSONArray.getString(i));
                    if (jSONObject.optString("app").equals(str)) {
                        String optString = jSONObject.optString("appver", "");
                        f.a("realsession appid match:" + str);
                        if (b.a(optString)) {
                            return true;
                        }
                        boolean equals = a.equals(optString);
                        f.a("realsession appver match:" + equals);
                        return equals;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
