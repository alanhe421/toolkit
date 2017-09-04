package com.qq.reader.module.bookstore.qnative.item;

import android.os.Bundle;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

/* compiled from: AdvGuideItem */
public class a extends s {
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
    private c l;

    public void parseData(JSONObject jSONObject) {
        this.a = jSONObject.optLong("id");
        this.b = jSONObject.optString("title");
        this.f = jSONObject.optString("actionTag");
        this.g = jSONObject.optString("imageUrl");
        this.e = jSONObject.optString("actionId");
        this.c = jSONObject.optString("intro");
        this.d = jSONObject.optString(SocialConstants.PARAM_URL);
        this.h = jSONObject.optString("action");
        if (this.h.equalsIgnoreCase("detail")) {
            this.k = jSONObject.optString(ComicStoreExclusiveItemCard.NET_AD_BOOK_INTRO);
            this.i = s.countTransform((long) jSONObject.optInt("favorCnt"));
        } else if (this.h.equalsIgnoreCase("topic") || this.h.equalsIgnoreCase("webpage")) {
            this.j = s.countTransform((long) jSONObject.optInt("readCnt"));
        }
        this.l = new c(null);
        Bundle a = this.l.a();
        a.putString("LOCAL_STORE_IN_TITLE", this.b);
        a.putString("KEY_ACTION", this.h);
        a.putString("KEY_ACTIONID", String.valueOf(this.e));
        a.putString("com.qq.reader.WebContent", this.d);
        a.putString("KEY_ACTIONTAG", this.f);
        a.putLong("URL_BUILD_PERE_BOOK_ID", Long.valueOf(this.e).longValue());
        setStatisic(jSONObject, a);
    }

    public c a() {
        return this.l;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.g;
    }

    public String d() {
        return this.h;
    }

    public String e() {
        return this.i;
    }

    public String f() {
        return this.j;
    }
}
