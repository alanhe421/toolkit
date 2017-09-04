package com.qq.reader.common.login.b;

import android.content.Context;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.login.c;
import com.qq.reader.module.b.b;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: LoginUser */
public abstract class a {
    public int a = 0;
    public int b = 0;
    public int c = 0;
    public int d = 0;
    public int e = 0;
    public int f = 0;
    public int g = 0;
    public int h = 0;
    public String i = "";
    public int j = 0;
    public String k = "";
    public boolean l = false;
    public boolean m = false;
    public String n;
    public int o = 0;
    public String p = null;

    public abstract String b(Context context);

    public String a(Context context) {
        return com.qq.reader.common.login.define.a.d(context);
    }

    public String a() {
        return com.qq.reader.common.login.define.a.f(ReaderApplication.getApplicationImp());
    }

    public String b() {
        return com.qq.reader.common.login.define.a.g(ReaderApplication.getApplicationImp());
    }

    public String c() {
        return com.qq.reader.common.login.define.a.e(ReaderApplication.getApplicationImp());
    }

    public int d() {
        return com.qq.reader.common.login.define.a.h(ReaderApplication.getApplicationImp());
    }

    public int c(Context context) {
        return com.qq.reader.common.login.define.a.p(context);
    }

    public void a(Context context, int i) {
        com.qq.reader.common.login.define.a.b(context, i);
    }

    public int e() {
        return this.a;
    }

    public void a(int i) {
        this.a = i;
    }

    public int d(Context context) {
        return com.qq.reader.common.login.define.a.u(context);
    }

    public void b(Context context, int i) {
        com.qq.reader.common.login.define.a.e(context, i);
    }

    public int e(Context context) {
        return com.qq.reader.common.login.define.a.w(context);
    }

    public void c(Context context, int i) {
        com.qq.reader.common.login.define.a.g(context, i);
    }

    public int f(Context context) {
        return com.qq.reader.common.login.define.a.v(context);
    }

    public void d(Context context, int i) {
        com.qq.reader.common.login.define.a.f(context, i);
    }

    public int g(Context context) {
        return com.qq.reader.common.login.define.a.s(context);
    }

    public void e(Context context, int i) {
        com.qq.reader.common.login.define.a.c(context, i);
    }

    public int f() {
        return this.g;
    }

    public void b(int i) {
        this.g = i;
    }

    public String h(Context context) {
        return com.qq.reader.common.login.define.a.q(context);
    }

    public void a(Context context, String str) {
        com.qq.reader.common.login.define.a.k(context, str);
    }

    public int i(Context context) {
        return com.qq.reader.common.login.define.a.t(context);
    }

    public void f(Context context, int i) {
        com.qq.reader.common.login.define.a.d(context, i);
    }

    public String g() {
        return this.p;
    }

    public void a(String str) {
        this.p = str;
    }

    public void b(String str) {
        this.k = str;
    }

    public boolean j(Context context) {
        this.l = com.qq.reader.common.login.define.a.o(context);
        return this.l;
    }

    public void a(Context context, boolean z) {
        com.qq.reader.common.login.define.a.b(context, z);
    }

    public int k(Context context) {
        return com.qq.reader.common.login.define.a.z(context);
    }

    public void g(Context context, int i) {
        com.qq.reader.common.login.define.a.h(context, i);
    }

    public String h() {
        return this.n;
    }

    public void c(String str) {
        this.n = str;
    }

    public void a(Context context, long j) {
        com.qq.reader.common.login.define.a.b(context, j);
    }

    public long l(Context context) {
        return com.qq.reader.common.login.define.a.y(context);
    }

    public long m(Context context) {
        return com.qq.reader.common.login.define.a.A(context);
    }

    public void b(Context context, long j) {
        com.qq.reader.common.login.define.a.c(context, j);
    }

    public static void b(Context context, String str) {
        com.qq.reader.common.login.define.a.m(context, str);
    }

    public static String n(Context context) {
        return com.qq.reader.common.login.define.a.B(context);
    }

    public static void a(JSONObject jSONObject) throws JSONException {
        d.c(null, System.currentTimeMillis());
        if (c.b()) {
            a(c.c(), jSONObject);
        }
        if (jSONObject.optBoolean("isLogin")) {
            b.a().a(jSONObject.toString());
        }
    }

    public static a a(a aVar, JSONObject jSONObject) throws JSONException {
        if (aVar == null) {
            return null;
        }
        aVar.a(ReaderApplication.getApplicationImp(), jSONObject.optInt("vipType"));
        aVar.a(ReaderApplication.getApplicationImp(), jSONObject.optInt("vipType") != 0);
        aVar.g(ReaderApplication.getApplicationImp(), jSONObject.optInt("vipStatus"));
        aVar.a(jSONObject.optInt("vipLevel"));
        aVar.b(ReaderApplication.getApplicationImp(), jSONObject.optInt("leftTicket"));
        aVar.c(ReaderApplication.getApplicationImp(), jSONObject.optInt("leftMTicket"));
        aVar.d(ReaderApplication.getApplicationImp(), jSONObject.optInt("combine"));
        if (!jSONObject.isNull("balance")) {
            aVar.e(ReaderApplication.getApplicationImp(), jSONObject.optInt("balance"));
        }
        aVar.b(jSONObject.optInt("norLevel"));
        aVar.c(jSONObject.optString("todayRecmmd"));
        if (!jSONObject.isNull("vipEndTime")) {
            aVar.a(ReaderApplication.getApplicationImp(), jSONObject.optString("vipEndTime"));
        }
        if (!jSONObject.isNull("bookTicket")) {
            aVar.f(ReaderApplication.getApplicationImp(), jSONObject.optInt("bookTicket"));
        }
        if (!jSONObject.isNull("bookTicketEndtime")) {
            aVar.b(jSONObject.optString("bookTicketEndtime"));
        }
        if (!jSONObject.isNull("firstsavemsg")) {
            aVar.a(jSONObject.optString("firstsavemsg"));
        }
        aVar.a(ReaderApplication.getApplicationImp(), jSONObject.optLong("manitorId", 0));
        aVar.b(ReaderApplication.getApplicationImp(), jSONObject.optLong("readTime"));
        b(ReaderApplication.getApplicationImp(), jSONObject.optString("vipComment"));
        return aVar;
    }
}
