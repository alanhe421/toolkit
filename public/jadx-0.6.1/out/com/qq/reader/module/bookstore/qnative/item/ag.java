package com.qq.reader.module.bookstore.qnative.item;

import android.text.TextUtils;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubReplyCard;
import com.tencent.android.tpush.common.MessageKey;
import org.json.JSONObject;

/* compiled from: UserNode */
public class ag extends s {
    public String a;
    public String b;
    public int c;
    public String d;
    public String e;
    public int f;
    public String g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public String n;
    public int o;

    public ag(JSONObject jSONObject) {
        a(jSONObject);
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.g = jSONObject.optString("uid");
            this.a = jSONObject.optString("nickname");
            this.b = jSONObject.optString(MessageKey.MSG_ICON);
            this.c = jSONObject.optInt("userlevel", 0);
            this.d = jSONObject.optString("title");
            this.f = jSONObject.optInt("fanslevel", -1);
            this.e = jSONObject.optString("fanslevelname");
            this.h = jSONObject.optInt("isauthor");
            this.i = jSONObject.optInt("admin");
            this.k = jSONObject.optInt("istopuser");
            this.l = jSONObject.optInt("miyuezhuan", 0);
            this.m = jSONObject.optInt("isManito", 0);
            this.n = jSONObject.optString("centerAuthorId");
            this.o = jSONObject.optInt(BookClubReplyCard.REPLY_USER_BLACK, -1);
            this.j = jSONObject.optInt("vipStatus", 0);
            if (!TextUtils.isEmpty(this.a)) {
                this.a = this.a.replaceAll("\r|\n", "");
            }
        }
    }

    public void parseData(JSONObject jSONObject) {
        a(jSONObject);
    }
}
