package com.qq.reader.module.bookstore.qnative.item;

import android.os.Bundle;
import android.text.TextUtils;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

/* compiled from: MoreItem */
public class ac extends s {
    String a;
    String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    c h;
    private String i;
    private int j;
    private String k;
    private String l;
    private int m;

    public ac(String str) {
        this.a = null;
        this.c = "";
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "";
        this.i = "";
        this.j = 0;
        this.k = "";
        this.l = "";
        this.h = null;
        this.a = new String();
        this.b = str;
    }

    public c a() {
        return this.h;
    }

    public boolean a(a aVar) {
        if (this.h == null) {
            return false;
        }
        this.h.a(aVar);
        return true;
    }

    public void parseData(JSONObject jSONObject) {
        this.d = jSONObject.optString("controllerTitle");
        this.a = jSONObject.optString("action");
        this.c = jSONObject.optString("para");
        this.e = jSONObject.optString("name");
        this.f = jSONObject.optString("actionId");
        this.g = jSONObject.optString("actionTag");
        this.i = jSONObject.optString("jumpPageName");
        this.j = jSONObject.optInt("executeType");
        this.k = jSONObject.optString("adids");
        this.l = jSONObject.optString(SocialConstants.PARAM_URL);
        this.m = jSONObject.optInt("catetype");
        this.h = new c(null);
        Bundle a = this.h.a();
        a.putString("KEY_ACTION", this.a);
        a.putString("KEY_ACTIONID", this.f);
        a.putInt("BOOK_INFO_CATEGORY_MORE_CATE_TYPE", this.m);
        if (TextUtils.isEmpty(this.l)) {
            this.l = this.f;
        }
        a.putString("com.qq.reader.WebContent", this.l);
        a.putString("KEY_ACTIONTAG", this.g);
        a.putString("LOCAL_STORE_IN_TITLE", this.d);
        a.putInt("function_type", this.j);
        a.putString("KEY_CARD_ID", this.b);
        a.putString("URL_BUILD_PERE_ADVS", this.k);
        if (this.i.length() > 0) {
            a.putString("KEY_JUMP_PAGENAME", this.i);
        }
        setStatisic(jSONObject, a);
    }
}
