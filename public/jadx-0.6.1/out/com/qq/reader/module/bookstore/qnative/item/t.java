package com.qq.reader.module.bookstore.qnative.item;

import android.os.Bundle;
import android.view.View;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.qq.reader.module.feed.card.view.FeedBookPackView;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

/* compiled from: ListBookBagItem */
public class t extends z {
    private String a;
    private String b;
    private String c;
    private int d;
    private String e;

    public void a(View view, int i, boolean z) {
        ((FeedBookPackView) view).setBookBagItemData(this);
    }

    public void parseData(JSONObject jSONObject) {
        this.a = jSONObject.optString("pid");
        this.b = jSONObject.optString("name");
        this.c = jSONObject.optString(SocialConstants.PARAM_IMG_URL);
        this.d = jSONObject.optInt(ComicStoreExclusiveItemCard.NET_AD_ATTR_COUNT);
        this.e = jSONObject.optString(SocialConstants.PARAM_APP_DESC);
        this.l = new c(null);
        Bundle a = this.l.a();
        a.putInt("function_type", 0);
        a.putString("KEY_JUMP_PAGENAME", "webpage");
        a.putString("com.qq.reader.WebContent", "/packlist.html?pid=" + this.a);
        setStatisic(jSONObject, a);
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public int c() {
        return this.d;
    }

    public String d() {
        return this.e;
    }
}
