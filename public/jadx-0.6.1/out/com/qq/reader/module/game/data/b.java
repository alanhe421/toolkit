package com.qq.reader.module.game.data;

import org.json.JSONObject;

/* compiled from: GameCoupon */
public class b {
    private String a;
    private int b;
    private String c = "";
    private int d = -1;
    private String e = "";
    private String f;
    private long g = -1;
    private boolean h = false;
    private String i = "";

    public String a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.e;
    }

    public String e() {
        switch (this.d) {
            case 0:
                return "已过期";
            case 1:
                return "今日有效";
            default:
                return this.d + "日内有效";
        }
    }

    public String f() {
        return this.f;
    }

    public String g() {
        return this.i;
    }

    public boolean h() {
        return this.d > 0 && !this.h;
    }

    public void a(JSONObject jSONObject) throws Exception {
        boolean z = true;
        this.a = jSONObject.optString("sno");
        this.b = jSONObject.optInt("igifttype");
        this.f = jSONObject.optString("amount");
        this.c = jSONObject.optString("giftname");
        this.e = jSONObject.optString("gameids");
        this.d = jSONObject.optInt("expiryday");
        this.g = jSONObject.optLong("tstartdatetime");
        if (jSONObject.optInt("istatus") != 1) {
            z = false;
        }
        this.h = z;
        this.i = jSONObject.optString("unit");
    }
}
