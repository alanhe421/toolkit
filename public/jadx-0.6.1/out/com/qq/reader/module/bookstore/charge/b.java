package com.qq.reader.module.bookstore.charge;

import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: MonthlyChargeItem */
public class b {
    private int a;
    private int b;
    private int c;
    private String d;
    private String e;
    private String f;
    private String g;
    private int h;
    private String i;
    private String j;

    public void a(JSONObject jSONObject) {
        this.b = jSONObject.optInt("yuan");
        this.c = jSONObject.optInt("month");
        this.e = jSONObject.optString("monthDesc");
        this.d = jSONObject.optString("intro");
        this.h = jSONObject.optInt("bookCoin");
        JSONArray optJSONArray = jSONObject.optJSONArray("tags");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            int length = optJSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject2 = (JSONObject) optJSONArray.opt(i);
                int optInt = jSONObject2.optInt("type");
                if (optInt == 1) {
                    this.f = jSONObject2.optString("intro");
                } else if (optInt == 2) {
                    this.g = jSONObject2.optString("intro");
                }
            }
        }
        this.i = jSONObject.optString("serviceCode");
        this.j = jSONObject.optString("productId");
    }

    public int a() {
        return this.b;
    }

    public int b() {
        return this.c;
    }

    public String c() {
        return this.d;
    }

    public String d() {
        return this.f;
    }

    public String e() {
        return this.g;
    }

    public String f() {
        return this.e;
    }

    public int g() {
        return this.h;
    }

    public int h() {
        return this.a;
    }

    public void a(int i) {
        this.a = i;
    }

    public String i() {
        return this.i;
    }

    public String j() {
        return this.j;
    }
}
