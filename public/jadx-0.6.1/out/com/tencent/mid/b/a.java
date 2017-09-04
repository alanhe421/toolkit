package com.tencent.mid.b;

import com.tencent.mid.api.MidEntity;
import com.tencent.mid.util.Util;
import com.tencent.mid.util.f;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    public static String a = MidEntity.TAG_TIMESTAMPS;
    public static String b = "times";
    public static String c = "mfreq";
    public static String d = "mdays";
    private static f i = Util.getLogger();
    private long e = 0;
    private int f = 1;
    private int g = 1024;
    private int h = 3;

    public a(String str) {
        if (Util.isStringValid(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (!jSONObject.isNull(a)) {
                    this.e = jSONObject.getLong(a);
                }
                if (!jSONObject.isNull(c)) {
                    this.g = jSONObject.getInt(c);
                }
                if (!jSONObject.isNull(b)) {
                    this.f = jSONObject.getInt(b);
                }
                if (!jSONObject.isNull(d)) {
                    this.h = jSONObject.getInt(d);
                }
            } catch (JSONException e) {
                i.d(e.toString());
            }
        }
    }

    public long a() {
        return this.e;
    }

    public void a(int i) {
        this.h = i;
    }

    public void a(long j) {
        this.e = j;
    }

    public int b() {
        return this.f;
    }

    public void b(int i) {
        this.f = i;
    }

    public void c(int i) {
        this.g = i;
    }

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(a, this.e);
            jSONObject.put(b, this.f);
            jSONObject.put(c, this.g);
            jSONObject.put(d, this.h);
        } catch (JSONException e) {
            i.d(e.toString());
        }
        return jSONObject.toString();
    }
}
