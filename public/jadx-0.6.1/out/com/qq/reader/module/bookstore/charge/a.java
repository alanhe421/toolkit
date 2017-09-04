package com.qq.reader.module.bookstore.charge;

import org.json.JSONObject;

/* compiled from: ChargeItem */
public class a {
    private boolean a;
    private int b;
    private double c;
    private String d;
    private int e;
    private String f;
    private String g;
    private String h;

    public void a(JSONObject jSONObject) {
        boolean z = true;
        if (jSONObject.optInt("isShow") != 1) {
            z = false;
        }
        this.a = z;
        this.b = jSONObject.optInt("money");
        this.c = jSONObject.optDouble("money");
        this.d = this.c > ((double) this.b) ? String.valueOf(this.c) : String.valueOf(this.b);
        this.e = jSONObject.optInt("number");
        this.f = jSONObject.optString("privilegeInfo");
        this.g = jSONObject.optString("recommendInfo");
        this.h = jSONObject.optString("productId");
    }

    public boolean a() {
        return this.a;
    }

    public String b() {
        return this.d;
    }

    public int c() {
        return this.e;
    }

    public String d() {
        return this.f;
    }

    public String e() {
        return this.g;
    }

    public String f() {
        return this.h;
    }
}
