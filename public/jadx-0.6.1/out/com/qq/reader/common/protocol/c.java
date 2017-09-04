package com.qq.reader.common.protocol;

import android.content.Context;
import org.json.JSONObject;

/* compiled from: Update */
public class c {
    private String a = "";
    private String b = "";
    private String c = "";
    private int d = 0;

    public String a() {
        return this.a;
    }

    public c a(String str) {
        this.a = str;
        return this;
    }

    public String b() {
        return this.b;
    }

    public c b(String str) {
        this.b = str;
        return this;
    }

    public String c() {
        return this.c;
    }

    public c c(String str) {
        this.c = str;
        return this;
    }

    public int d() {
        return this.d;
    }

    public c a(int i) {
        this.d = i;
        return this;
    }

    public static c a(Context context, String str) throws Exception {
        c cVar = new c();
        JSONObject jSONObject = new JSONObject(str);
        if (jSONObject.getInt("code") != 0) {
            return null;
        }
        int i = jSONObject.getInt("update_code");
        if (i == 0) {
            return null;
        }
        cVar.a(jSONObject.getString("update_url")).a(i).b(jSONObject.getString("update_intro")).c(jSONObject.getString("update_version"));
        return cVar;
    }

    public static boolean a(Context context) {
        return com.qq.reader.appconfig.a.c.a != 0;
    }
}
