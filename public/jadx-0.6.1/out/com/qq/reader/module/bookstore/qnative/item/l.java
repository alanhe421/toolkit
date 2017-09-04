package com.qq.reader.module.bookstore.qnative.item;

import android.text.Html;
import com.qq.reader.common.c.a;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.module.bookstore.qnative.page.impl.ao;
import com.tencent.android.tpush.common.MessageKey;
import org.json.JSONObject;

/* compiled from: CommentAndReplyItem */
public class l extends s {
    public ag a;
    public String b;
    public String c;
    public long d;
    public long e;
    public String f;
    public String g;
    public int h = 0;
    public long i = 0;
    public boolean j = false;
    public int k;
    public int l;
    public int m;
    public int n;
    public String o = "";
    public int p;
    public String q;
    protected float r;
    public int s = -1;
    public String t = "";
    private int u = 0;
    private int v;
    private int w;
    private String x;

    public String a() {
        if (this.u == 0 || this.u >= ao.B) {
            return "";
        }
        return this.u + "楼";
    }

    public void parseData(JSONObject jSONObject) {
        this.a = new ag(jSONObject.optJSONObject("user"));
        this.b = jSONObject.optString(MessageKey.MSG_CONTENT);
        if (a.D) {
            this.b = this.b.replace("<br/>", "\n\r");
        }
        try {
            this.b = Html.fromHtml(this.b).toString();
            this.b = com.qq.reader.common.utils.ao.w(this.b);
        } catch (Exception e) {
        }
        this.n = jSONObject.optInt("agreestatus", -1);
        this.d = jSONObject.optLong("createtime");
        this.e = jSONObject.optLong("lastreplytime");
        this.f = jSONObject.optString("replyid");
        this.g = jSONObject.optString("commentid");
        this.h = jSONObject.optInt("top");
        this.i = jSONObject.optLong("bid");
        this.c = jSONObject.optString("title");
        this.m = jSONObject.optInt("agree");
        this.u = jSONObject.optInt("index");
        this.l = jSONObject.optInt("replycount");
        this.s = jSONObject.optInt("exvoteoptionid", -1);
        this.t = jSONObject.optString("shortTime");
        this.p = jSONObject.optInt("replytype");
        this.q = jSONObject.optString("replynickname");
        this.v = jSONObject.optInt("better");
        this.w = jSONObject.optInt("authortag");
        this.o = jSONObject.optString("platformname");
        if (jSONObject.has("reward")) {
            this.k = jSONObject.optInt("reward");
            if (this.k > 0) {
                this.j = true;
            } else {
                this.j = false;
            }
        } else {
            this.j = false;
            this.k = 0;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("scoreInfo");
        if (optJSONObject != null) {
            try {
                this.r = Float.valueOf(optJSONObject.optString("score")).floatValue();
            } catch (Exception e2) {
                c.e("DetailPageBookItem", e2.getMessage());
            }
            this.x = optJSONObject.optString("intro");
            return;
        }
        this.r = -1.0f;
    }

    public ag b() {
        return this.a;
    }

    public boolean c() {
        return this.h == 1;
    }

    public boolean d() {
        return this.v == 1;
    }

    public boolean e() {
        return this.w == 1;
    }

    public String f() {
        return this.x;
    }

    public float g() {
        return this.r;
    }

    public int h() {
        return this.u;
    }
}
