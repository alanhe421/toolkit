package com.qq.reader.module.bookstore.qnative.item;

import org.json.JSONObject;

/* compiled from: HallOfFameTabItem */
public class r extends s {
    private String a;
    private String b;

    public void parseData(JSONObject jSONObject) {
        this.a = jSONObject.optString("id");
        this.b = jSONObject.optString("title");
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }
}
