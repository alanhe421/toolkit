package com.qq.reader.module.bookstore.qnative.item;

import android.os.Bundle;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: AdvItem */
public class b extends s {
    String a = "unknow";
    c b = null;
    private long c;
    private String d;
    private String e;
    private List<g> f;
    private String g;
    private long h;
    private String i;
    private String j;
    private int k = 0;
    private String l = "";

    public void parseData(JSONObject jSONObject) {
        int i = 0;
        this.c = jSONObject.optLong("id");
        this.d = jSONObject.optString("title");
        this.e = jSONObject.optString("intro");
        this.g = jSONObject.optString(SocialConstants.PARAM_URL);
        this.h = jSONObject.optLong("actionId");
        this.i = jSONObject.optString("actionTag");
        this.j = jSONObject.optString("imageUrl");
        this.a = jSONObject.optString("action");
        JSONObject optJSONObject = jSONObject.optJSONObject(s.STATPARAM_KEY);
        if (optJSONObject != null) {
            this.k = optJSONObject.optInt(s.ORIGIN, 0);
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("bookList");
        if (optJSONArray != null) {
            if (this.f == null) {
                this.f = new ArrayList();
            }
            int length = optJSONArray.length();
            while (i < length) {
                try {
                    g gVar = new g();
                    gVar.parseData(optJSONArray.getJSONObject(i));
                    this.f.add(gVar);
                    i++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        this.b = new c(null);
        Bundle a = this.b.a();
        a.putString("LOCAL_STORE_IN_TITLE", this.d);
        a.putString("KEY_ACTION", this.a);
        a.putString("KEY_JUMP_PAGENAME", c.a(this.a));
        a.putString("KEY_ACTIONID", String.valueOf(this.h));
        a.putString("com.qq.reader.WebContent", this.g);
        if ("categoryV3".equalsIgnoreCase(this.a)) {
            this.i = "-1,-1,6";
        }
        a.putString("KEY_ACTIONTAG", this.i);
        a.putLong("URL_BUILD_PERE_BOOK_ID", this.h);
        setStatisic(jSONObject, a);
    }

    public long a() {
        return this.c;
    }

    public int b() {
        return this.k;
    }

    public String c() {
        return this.d;
    }

    public String d() {
        return this.e;
    }

    public String e() {
        return this.g;
    }

    public String f() {
        return this.j;
    }

    public List<g> g() {
        return this.f;
    }

    public boolean a(a aVar) {
        if (this.b == null) {
            return false;
        }
        this.b.a(aVar);
        return true;
    }
}
