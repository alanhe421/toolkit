package com.qq.reader.module.bookstore.qnative.item;

import android.os.Bundle;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.c.a;
import org.json.JSONObject;

/* compiled from: CollectCardItem */
public class k extends s {
    private long a;
    private String b;
    private String c;
    private String d;
    private int e;
    private String f;
    private int g = 75;
    private int h = 0;
    private String i = null;
    private int j = 0;
    private String k = "";
    private String l = "";
    private c m = null;

    public void parseData(JSONObject jSONObject) {
        this.a = jSONObject.optLong("bid");
        this.b = jSONObject.optString("title");
        this.c = jSONObject.optString("author");
        this.d = jSONObject.optString("categoryName");
        this.e = jSONObject.optInt("price");
        this.f = jSONObject.optString("intro");
        this.g = jSONObject.optInt("star");
        this.h = jSONObject.optInt("totalWords");
        this.l = jSONObject.optString("lpushname");
        this.m = new c(null);
        JSONObject optJSONObject = jSONObject.optJSONObject("ext");
        if (optJSONObject != null) {
            this.j = optJSONObject.optInt("jzcount");
        }
        Bundle a = this.m.a();
        JSONObject optJSONObject2 = jSONObject.optJSONObject("discount");
        if (optJSONObject2 != null) {
            this.k = optJSONObject2.optString("endTime");
        }
        a.putString("LOCAL_STORE_IN_TITLE", d());
        a.putString("KEY_JUMP_PAGENAME", "DetailPage");
        a.putLong("URL_BUILD_PERE_BOOK_ID", c());
        setStatisic(jSONObject, a);
    }

    public String a() {
        return this.l;
    }

    public void a(a aVar) {
        if (this.m != null) {
            this.m.a(aVar);
        }
    }

    public String b() {
        return ao.g(this.a);
    }

    public long c() {
        return this.a;
    }

    public String d() {
        return this.b;
    }

    public String e() {
        return this.c;
    }

    public String f() {
        return this.f;
    }

    public int g() {
        return this.j;
    }
}
