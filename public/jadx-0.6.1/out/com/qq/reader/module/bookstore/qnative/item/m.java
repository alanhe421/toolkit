package com.qq.reader.module.bookstore.qnative.item;

import org.json.JSONObject;

/* compiled from: CopyrightInfoItem */
public class m extends s {
    private final String a = "form";
    private final String b = "publishtime";
    private final String c = "publisher";
    private final String d = "isbn";
    private final String e = "publishPrice";
    private final String f = "bookfrom";
    private final String g = "translator";
    private final String h = "book";
    private final String i = "copyrightinfo";
    private int j;
    private String k;
    private String l;
    private String m;
    private int n;
    private String o;
    private String p;
    private String q;

    public void parseData(JSONObject jSONObject) {
        if (jSONObject != null) {
            JSONObject optJSONObject = jSONObject.optJSONObject("book");
            this.j = optJSONObject.optInt("form");
            this.p = optJSONObject.optString("translator");
            this.k = optJSONObject.optString("publishtime");
            this.l = optJSONObject.optString("publisher");
            this.m = optJSONObject.optString("isbn");
            this.n = optJSONObject.optInt("publishPrice");
            this.o = optJSONObject.optString("bookfrom");
            this.q = optJSONObject.optString("copyrightinfo");
        }
    }

    public int a() {
        return this.j;
    }

    public String b() {
        return this.k;
    }

    public String c() {
        return this.l;
    }

    public int d() {
        return this.n;
    }

    public String e() {
        return this.q;
    }

    public String f() {
        return this.p;
    }
}
