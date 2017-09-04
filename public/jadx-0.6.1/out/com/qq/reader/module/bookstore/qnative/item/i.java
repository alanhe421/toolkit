package com.qq.reader.module.bookstore.qnative.item;

import android.os.Bundle;
import com.qq.reader.module.bookstore.qnative.c;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

/* compiled from: CategoryKeyItem */
public class i extends s {
    private long a;
    private long b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private c i;

    public void parseData(JSONObject jSONObject) {
        this.a = jSONObject.optLong("id");
        this.b = jSONObject.optLong("actionTag");
        this.c = jSONObject.optString("title");
        this.d = jSONObject.optString("action");
        this.e = jSONObject.optString("actionId");
        this.f = jSONObject.optString(SocialConstants.PARAM_URL);
        this.g = jSONObject.optString("intro");
        this.h = jSONObject.optString("imageUrl");
        this.i = new c(null);
        Bundle a = this.i.a();
        if ("detail".equals(this.d)) {
            a.putString("KEY_JUMP_PAGENAME", "DetailPage");
            a.putLong("URL_BUILD_PERE_BOOK_ID", Long.valueOf(this.e).longValue());
        } else {
            a.putString("KEY_ACTION", this.d);
            a.putString("KEY_ACTIONTAG", String.valueOf(this.b));
            a.putString("KEY_ACTIONID", this.e);
            a.putSerializable("com.qq.reader.WebContent", this.f);
        }
        a.putString("LOCAL_STORE_IN_TITLE", this.c);
        setStatisic(jSONObject, a);
    }

    public c a() {
        return this.i;
    }

    public String b() {
        return this.c;
    }
}
