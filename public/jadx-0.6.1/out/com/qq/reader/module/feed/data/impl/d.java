package com.qq.reader.module.feed.data.impl;

import java.util.Iterator;
import org.json.JSONObject;

/* compiled from: FeedCmd */
public class d {
    private String a;
    private String b;
    private JSONObject c;

    public d(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public JSONObject c() {
        return this.c;
    }

    public String d() {
        if (this.c == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        Iterator keys = this.c.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            String optString = this.c.optString(str);
            if (optString != null) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append("&");
                }
                stringBuilder.append(str);
                stringBuilder.append("=");
                stringBuilder.append(optString);
            }
        }
        return stringBuilder.toString();
    }

    public void a(JSONObject jSONObject) {
        this.c = jSONObject;
    }
}
