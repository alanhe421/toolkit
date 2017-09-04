package com.qq.reader.module.bookstore.qnative.item;

import android.text.TextUtils;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import org.json.JSONObject;

/* compiled from: BookClubBookInfoItem */
public class d extends s {
    private long a;
    private String b = null;
    private String c = null;
    private String d = null;
    private double e = 0.0d;
    private double f = 0.0d;
    private int g = 0;
    private String h;
    private int i;

    public void parseData(JSONObject jSONObject) {
        this.a = jSONObject.optLong("bid");
        this.b = jSONObject.optString("title");
        this.c = jSONObject.optString("author");
        this.d = jSONObject.optString(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE);
        this.e = jSONObject.optDouble("score", 0.0d);
        this.f = jSONObject.optDouble("commentcount", 0.0d);
        this.g = jSONObject.optInt("scorerank");
        this.h = jSONObject.optString("topuser");
        if (!TextUtils.isEmpty(this.h)) {
            this.h = this.h.replace("[", "").replace("]", "").replace("\"", "").replace(",", "、");
        }
    }

    public String a() {
        if (this.i == 9) {
            return ao.h(this.a);
        }
        return ao.g(this.a);
    }

    public long b() {
        return this.a;
    }

    public String c() {
        return this.b;
    }

    public String d() {
        return this.c;
    }

    public String e() {
        return this.h;
    }

    public int f() {
        return (int) this.e;
    }

    public int g() {
        return (int) this.f;
    }

    public void h() {
        this.f += 1.0d;
    }

    public void i() {
        this.f -= 1.0d;
    }

    public int j() {
        return this.g;
    }

    public void a(int i) {
        this.i = i;
    }
}
