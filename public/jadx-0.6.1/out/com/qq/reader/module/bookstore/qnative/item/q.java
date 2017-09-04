package com.qq.reader.module.bookstore.qnative.item;

import com.tencent.open.SocialConstants;
import org.json.JSONObject;

/* compiled from: HallOfFameAuthorItem */
public class q extends s {
    private int a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;

    public void parseData(JSONObject jSONObject) {
        this.a = jSONObject.optInt("id");
        this.b = jSONObject.optString("label");
        this.c = jSONObject.optString("name");
        this.d = jSONObject.optString(SocialConstants.PARAM_IMG_URL);
        this.e = jSONObject.optString("intro");
        this.f = jSONObject.optString("value");
    }

    public String a() {
        return this.d;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.b;
    }

    public String d() {
        return this.e;
    }
}
