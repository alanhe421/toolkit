package com.qq.reader.module.bookstore.qnative.item;

import org.json.JSONObject;

/* compiled from: BookEntryItem */
public class f extends s {
    private int a;
    private String b;
    private String c;
    private String d;

    public int a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public void parseData(JSONObject jSONObject) {
        this.a = jSONObject.optInt("entryId");
        this.b = jSONObject.optString("entryIcon");
        this.c = jSONObject.optString("entryTitle");
        this.d = jSONObject.optString("entryUrl");
    }
}
