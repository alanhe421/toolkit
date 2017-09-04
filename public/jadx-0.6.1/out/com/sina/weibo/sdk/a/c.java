package com.sina.weibo.sdk.a;

import com.tencent.open.SocialConstants;
import org.json.JSONObject;

/* compiled from: AppInvokeCmd */
class c extends e {
    private String a;
    private String b;
    private String c;

    public c(JSONObject jSONObject) {
        super(jSONObject);
    }

    public void a(JSONObject jSONObject) {
        super.a(jSONObject);
        this.a = jSONObject.optString("package");
        this.b = jSONObject.optString("scheme");
        this.c = jSONObject.optString(SocialConstants.PARAM_URL);
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }
}
