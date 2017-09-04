package com.qq.reader.module.bookstore.qnative.item;

import com.tencent.android.tpush.common.MessageKey;
import org.json.JSONObject;

/* compiled from: ReplyNode */
public class af extends s {
    public ag a;
    public String b;
    public int c;
    public long d;
    public int e;
    public String f;
    public int g;
    public String h;
    public String i;
    public String j;

    public void a(JSONObject jSONObject) {
        this.a = new ag(jSONObject.optJSONObject("user"));
        this.b = jSONObject.optString(MessageKey.MSG_CONTENT);
        this.c = jSONObject.optInt("level");
        this.d = jSONObject.optLong("createtime");
        this.e = jSONObject.optInt("top");
        this.f = jSONObject.optString("replyid");
        this.g = jSONObject.optInt("replytype");
        this.j = jSONObject.optString("replynickname");
        this.h = jSONObject.optString("replyuid");
        this.i = jSONObject.optString("replyreplyid");
    }

    public void parseData(JSONObject jSONObject) {
        a(jSONObject);
    }
}
