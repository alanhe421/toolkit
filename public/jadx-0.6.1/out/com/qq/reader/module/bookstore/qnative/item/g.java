package com.qq.reader.module.bookstore.qnative.item;

import android.os.Bundle;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.j;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.c.a;
import org.json.JSONObject;

/* compiled from: BookItem */
public class g extends s {
    private String A = null;
    private int B = 0;
    private long C = 0;
    private String D;
    private String E;
    private int F;
    private double G;
    private c H = null;
    private int I = -1;
    private long a;
    private String b;
    private int c;
    private String d;
    public int e = 0;
    protected String f;
    protected String g;
    private String h;
    private int i;
    private String j;
    private int k = -1;
    private int l;
    private String m;
    private String n;
    private boolean o = false;
    private int p = -1;
    private int q = 0;
    private String r;
    private long s = -1;
    private String t;
    private String u;
    private String v;
    private String w;
    private String x;
    private String y;
    private String z;

    public void parseData(JSONObject jSONObject) {
        this.a = jSONObject.optLong("bid");
        this.b = jSONObject.optString("title");
        this.d = jSONObject.optString("author");
        this.h = jSONObject.optString("categoryName");
        this.i = jSONObject.optInt("price");
        this.j = jSONObject.optString("intro");
        this.k = jSONObject.optInt("star");
        this.e = jSONObject.optInt("totalWords");
        this.B = jSONObject.optInt("jzcount");
        this.r = jSONObject.optString("anchor");
        this.s = jSONObject.optLong("mediaBookId");
        this.z = jSONObject.optString("num");
        this.p = jSONObject.optInt("finished");
        this.q = jSONObject.optInt("lastChapter");
        this.f = jSONObject.optString("originalPrice");
        this.g = jSONObject.optString("discountPrice");
        this.D = jSONObject.optString("catel2name");
        this.E = jSONObject.optString("catel3name");
        this.F = jSONObject.optInt("form");
        this.G = jSONObject.optDouble("score", 0.0d);
        JSONObject optJSONObject = jSONObject.optJSONObject("ext");
        if (optJSONObject != null) {
            this.l = optJSONObject.optInt("recommend");
            this.t = optJSONObject.optString("extleftkey");
            this.u = optJSONObject.optString("extleft");
            this.v = optJSONObject.optString("extrightkey");
            this.w = optJSONObject.optString("extright");
            this.x = optJSONObject.optString("lastChapterName");
            this.y = optJSONObject.optString("unit");
            this.m = optJSONObject.optString("read_percent");
            this.c = optJSONObject.optInt("subCount");
            this.n = optJSONObject.optString("categoryInfoV4SlaveId");
            if (optJSONObject.optInt("userjzinfo") == 1) {
                this.o = true;
            }
        }
        this.H = new c(null);
        Bundle a = this.H.a();
        a.putString("LOCAL_STORE_IN_TITLE", n());
        a.putString("KEY_JUMP_PAGENAME", "DetailPage");
        a.putLong("URL_BUILD_PERE_BOOK_ID", m());
        setStatisic(jSONObject, a);
    }

    public int e() {
        return this.q;
    }

    public int i() {
        return this.p;
    }

    public void a(a aVar) {
        if (this.H != null) {
            Bundle a = this.H.a();
            if (this.I != -1) {
                a.putInt("function_type", this.I);
            }
            a.putLong("frombid", this.C);
            this.H.a(aVar);
        }
    }

    public void b(a aVar) {
        o.e(aVar.getFromActivity(), String.valueOf(this.s), null);
    }

    public void c(a aVar) {
        if (this.H != null) {
            Bundle a = this.H.a();
            int i = a.getInt("function_type");
            if (i != 3) {
                this.I = i;
            }
            a.putInt("function_type", 3);
            this.H.a(aVar);
        }
    }

    public boolean j() {
        return this.o;
    }

    public void a(boolean z) {
        this.o = z;
    }

    public String f() {
        return ao.g(this.a);
    }

    public String k() {
        return this.f;
    }

    public String l() {
        return this.g;
    }

    public long m() {
        return this.a;
    }

    public void a(long j) {
        this.a = j;
    }

    public String n() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public double o() {
        return this.G;
    }

    public int p() {
        return this.c;
    }

    public String q() {
        return this.d;
    }

    public void b(String str) {
        this.d = str;
    }

    public String h() {
        return this.r;
    }

    public String r() {
        return this.h;
    }

    public String s() {
        return this.j;
    }

    public void c(String str) {
        this.j = str;
    }

    public int t() {
        return this.B;
    }

    public String u() {
        return j.a((long) this.B);
    }

    public void a(int i) {
        this.B = i;
    }

    public void b(long j) {
        this.C = j;
    }

    public int v() {
        return this.l;
    }

    public String w() {
        return this.t;
    }

    public String x() {
        return this.u;
    }

    public String y() {
        return this.v;
    }

    public String z() {
        return this.w;
    }

    public String A() {
        return this.x;
    }

    public String B() {
        return this.y;
    }

    public String C() {
        return this.z;
    }

    public String D() {
        return this.m;
    }

    public long d() {
        return this.s;
    }

    public boolean E() {
        return "19200".equals(this.n);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof g)) {
            return false;
        }
        if (this.a == ((g) obj).m()) {
            return true;
        }
        return false;
    }

    public String F() {
        return this.D;
    }

    public void d(String str) {
        this.D = this.D;
    }

    public String G() {
        return this.E;
    }

    public void e(String str) {
        this.E = this.E;
    }

    public int H() {
        return this.F;
    }
}
