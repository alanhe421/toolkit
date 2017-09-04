package com.qq.reader.module.game.data;

import com.tencent.open.SocialConstants;
import org.json.JSONObject;

/* compiled from: GameAdvsData */
public class a {
    String a;
    String b;
    String c;
    int d;

    public a(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.a = jSONObject.optString(SocialConstants.PARAM_IMG_URL);
            this.b = jSONObject.optString("qurl");
            this.c = jSONObject.optString("txt");
            this.d = jSONObject.optInt("id");
        }
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.a;
    }

    public int c() {
        return this.d;
    }
}
