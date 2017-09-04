package com.qq.reader.module.question.data;

import com.tencent.android.tpush.common.MessageKey;
import com.tencent.mm.performance.WxPerformanceHandle;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

/* compiled from: AuthorData */
public class a {
    private long a;
    private String b;
    private String c;
    private String d;
    private String e;
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private int i;
    private int j;
    private int k;
    private int l;
    private String m;
    private boolean n;

    public String a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public int c() {
        return this.f;
    }

    public long d() {
        return this.a;
    }

    public String e() {
        return this.e;
    }

    public void a(String str) {
        this.e = str;
    }

    public void a(int i) {
        this.i = i;
    }

    public int f() {
        return this.i;
    }

    public int g() {
        return this.j;
    }

    public void b(int i) {
        this.j += i;
        this.l += i;
    }

    public int h() {
        return this.k;
    }

    public int i() {
        return this.l;
    }

    public int j() {
        return this.g;
    }

    public int k() {
        return this.h;
    }

    public boolean l() {
        return this.n;
    }

    public void a(JSONObject jSONObject) {
        this.a = jSONObject.optLong("authorId");
        this.b = jSONObject.optString("authorName");
        this.c = jSONObject.optString(MessageKey.MSG_ICON);
        this.d = jSONObject.optString(MessageKey.MSG_CONTENT);
        this.e = jSONObject.optString(SocialConstants.PARAM_APP_DESC);
        this.f = jSONObject.optInt(WxPerformanceHandle.MESSAGE_TAG);
        this.g = jSONObject.optInt("answerCount");
        this.h = jSONObject.optInt("listenCount");
        this.k = jSONObject.optInt("listenIncome");
        this.j = jSONObject.optInt("answerIncome");
        this.l = jSONObject.optInt("income");
        this.d = jSONObject.optString(MessageKey.MSG_CONTENT);
        this.m = jSONObject.optString("wording");
        this.i = jSONObject.optInt("questionPrice");
        this.n = jSONObject.optBoolean("isBindingWX", false);
    }
}
