package com.qq.reader.module.bookstore.qnative.item;

import com.tencent.android.tpush.common.MessageKey;
import org.json.JSONObject;

/* compiled from: BookClubSlidItem */
public class e extends s {
    public a[] a;
    public int b = 0;

    /* compiled from: BookClubSlidItem */
    public static class a {
        public String a;
        public String b;
        public int c;
        public long d;
        public long e;
        public int f;
        public long g;
        public int h;
        public int i;
        public int j;
        public int k;
        public String l;
        public String m;
        public int n;
        public int o;
        public int p;
        public ag q;
        public int r;
        public int s;
        public int t;
        int u;

        public a(int i, String str, JSONObject jSONObject) {
            this.u = i;
            this.a = str;
            this.b = jSONObject.optString("commentid");
            this.c = jSONObject.optInt("uid");
            this.d = jSONObject.optLong("createtime");
            this.e = jSONObject.optLong("lastreplytime");
            this.f = jSONObject.optInt("reward");
            this.g = jSONObject.optLong("timeStamp");
            this.h = jSONObject.optInt("status");
            this.i = jSONObject.optInt("disageree");
            this.j = jSONObject.optInt("type");
            this.k = jSONObject.optInt("replycount");
            this.l = jSONObject.optString(MessageKey.MSG_CONTENT);
            this.m = jSONObject.optString("title");
            this.n = jSONObject.optInt("red");
            this.o = jSONObject.optInt("better");
            this.p = jSONObject.optInt("subtype");
            if (jSONObject.optJSONObject("user") != null) {
                this.q = new ag(jSONObject.optJSONObject("user"));
            }
            this.r = jSONObject.optInt("bid");
            this.s = jSONObject.optInt("agree");
            this.t = jSONObject.optInt("top");
        }
    }

    public void parseData(JSONObject jSONObject) {
        this.b = jSONObject.optInt("type");
        switch (this.b) {
            case 1:
                this.a = new a[2];
                if (jSONObject.optJSONObject("new") != null) {
                    this.a[0] = new a(this.b, "new", jSONObject.optJSONObject("new"));
                }
                if (jSONObject.optJSONObject("max") != null) {
                    this.a[1] = new a(this.b, "max", jSONObject.optJSONObject("max"));
                    return;
                }
                return;
            case 2:
                this.a = new a[1];
                if (jSONObject.optJSONObject("top") != null) {
                    this.a[0] = new a(this.b, "top", jSONObject.optJSONObject("top"));
                    return;
                }
                return;
            default:
                return;
        }
    }
}
