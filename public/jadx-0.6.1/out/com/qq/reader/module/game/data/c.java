package com.qq.reader.module.game.data;

import com.qq.reader.common.c.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import org.json.JSONObject;

/* compiled from: GameData */
public class c {
    private String a;
    private String b;
    private int c;
    private String d;
    private String e;
    private String f;
    private long g;
    private int h;
    private int i;
    private String j;
    private String k;
    private int l;
    private String m;

    public int a() {
        return this.i;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.e;
    }

    public int d() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }

    public String e() {
        return this.a;
    }

    public String f() {
        return this.d;
    }

    public static c a(JSONObject jSONObject) {
        c cVar = new c();
        cVar.b(jSONObject);
        return cVar;
    }

    public boolean g() {
        return this.h == 1;
    }

    public boolean h() {
        return this.h == 2;
    }

    public String i() {
        return a.bj + this.e + ShareConstants.PATCH_SUFFIX;
    }

    public String j() {
        return this.f;
    }

    public long k() {
        return (long) this.d.hashCode();
    }

    public long l() {
        return this.g;
    }

    public String m() {
        return this.j;
    }

    public String n() {
        return this.k;
    }

    public int o() {
        return this.l;
    }

    public String p() {
        return this.m;
    }

    public void b(JSONObject jSONObject) {
        this.e = jSONObject.optString("gameName");
        this.g = jSONObject.optLong("gameId");
        this.a = jSONObject.optString("gameIcon");
        this.d = jSONObject.optString(Constants.FLAG_PACKAGE_NAME);
        this.f = jSONObject.optString("qurl");
        this.h = jSONObject.optInt("gameType");
        this.i = jSONObject.optInt("gameTag");
        this.j = jSONObject.optString("sSlogan");
        this.k = jSONObject.optString("lSlogan");
        this.l = jSONObject.optInt("hasCoupon");
        this.b = jSONObject.optString("gameUrl");
        this.m = jSONObject.optString("loginInfo");
    }
}
