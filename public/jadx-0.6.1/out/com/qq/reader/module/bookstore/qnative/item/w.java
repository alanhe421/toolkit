package com.qq.reader.module.bookstore.qnative.item;

import android.os.Bundle;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

/* compiled from: ListBookItem_2 */
public class w extends s {
    private long a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private int l;
    private String m;
    private c n = null;
    private int o = -1;

    public void parseData(JSONObject jSONObject) {
        this.a = jSONObject.optLong("id");
        this.b = jSONObject.optString("title");
        this.c = jSONObject.optString("intro");
        this.d = jSONObject.optString("imageUrl");
        this.e = jSONObject.optString("action");
        this.f = jSONObject.optString("favorCnt");
        this.g = jSONObject.optString(ComicStoreExclusiveItemCard.NET_AD_BOOK_INTRO);
        this.h = jSONObject.optString("label");
        this.i = jSONObject.optString("color");
        this.j = jSONObject.optString("bookAuth");
        this.k = jSONObject.optString("bookCate");
        this.l = jSONObject.optInt("readCnt");
        this.m = jSONObject.optString(SocialConstants.PARAM_URL);
        this.n = new c(null);
        Bundle a = this.n.a();
        a.putString("KEY_JUMP_PAGENAME", "DetailPage");
        a.putString("LOCAL_STORE_IN_TITLE", g());
        a.putString("KEY_ACTION", this.e);
        a.putString("com.qq.reader.WebContent", this.m);
        a.putLong("URL_BUILD_PERE_BOOK_ID", f());
        setStatisic(jSONObject, a);
    }

    public String a() {
        return this.h;
    }

    public String b() {
        return "#" + this.i;
    }

    public int c() {
        return this.l;
    }

    public void a(a aVar) {
        if (this.n != null) {
            if (this.o != -1) {
                this.n.a().putInt("function_type", this.o);
            }
            this.n.a(aVar);
        }
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return ao.g(this.a);
    }

    public long f() {
        return this.a;
    }

    public String g() {
        return this.b;
    }

    public String h() {
        return this.c;
    }

    public String i() {
        return this.e;
    }

    public String j() {
        return this.j;
    }

    public String k() {
        return this.k;
    }

    public String l() {
        return this.g;
    }
}
