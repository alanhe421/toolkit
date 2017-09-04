package com.qq.reader.module.bookstore.qnative.item;

import android.os.Bundle;
import com.qq.reader.module.bookstore.qnative.c;
import org.json.JSONObject;

/* compiled from: CategoryGridItem */
public class h extends s {
    private boolean a;
    private int b;
    private String c;
    private int d;
    private String e;
    private boolean f;
    private c g;

    public void parseData(JSONObject jSONObject) {
        this.a = jSONObject.optBoolean("hasTopic");
        this.b = jSONObject.optInt("actionTag");
        this.c = jSONObject.optString("title");
        this.d = jSONObject.optInt("actionId");
        this.e = jSONObject.optString("ext");
        this.f = jSONObject.optBoolean("recommend");
        this.g = new c(null);
        Bundle a = this.g.a();
        a.putString("KEY_ACTIONTAG", "-1,-1,6");
        a.putString("KEY_ACTIONID", String.valueOf(this.d));
        a.putString("LOCAL_STORE_IN_TITLE", this.c);
        a.putBoolean("LOCAL_STORE_INTERNAL_CATEGORY", this.b == 0);
        a.putString("KEY_JUMP_PAGENAME", "classify");
        setStatisic(jSONObject, a);
    }

    public c a() {
        return this.g;
    }

    public boolean b() {
        return this.f;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.e;
    }
}
