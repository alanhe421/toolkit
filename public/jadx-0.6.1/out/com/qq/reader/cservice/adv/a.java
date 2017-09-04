package com.qq.reader.cservice.adv;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import com.qq.reader.module.bookstore.qnative.c;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Advertisement */
public class a {
    private long a;
    private String b;
    private long c;
    private long d;
    private int e = 1;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private boolean m;
    private String n;
    private String o = "";
    private a p;
    private int q;
    private c r;

    /* compiled from: Advertisement */
    public static class a {
        int a;
        long b;
        String c;
        String d;
        String e;
        long f;
        boolean g = false;
        int h;
        int i;
        String j;
        int k = 7;

        public a a(String str) {
            boolean z = false;
            JSONObject jSONObject = new JSONObject(str);
            try {
                this.d = jSONObject.getString("action");
                this.e = jSONObject.getString("actionTag");
            } catch (JSONException e) {
            }
            try {
                this.f = Long.valueOf(jSONObject.getString("actionId")).longValue();
            } catch (NumberFormatException e2) {
            } catch (JSONException e3) {
            }
            try {
                this.a = Integer.valueOf(jSONObject.getString("uitype")).intValue();
            } catch (NumberFormatException e4) {
            } catch (JSONException e5) {
            }
            try {
                this.c = jSONObject.optString("data_desc");
                try {
                    this.b = Long.valueOf(jSONObject.optString("data")).longValue();
                } catch (Exception e6) {
                }
                if (jSONObject.optInt("forceLogin", 0) > 0) {
                    z = true;
                }
                this.g = z;
                this.i = jSONObject.optInt("showPosition");
                this.h = jSONObject.optInt("show_limit", 0);
                this.j = jSONObject.optString("label");
                this.k = jSONObject.optInt("chapterLength", 7);
            } catch (JSONException e7) {
            }
            return this;
        }
    }

    public a(long j, String str) {
        this.a = j;
        this.b = str;
    }

    public void a(boolean z) {
        this.m = z;
    }

    public boolean a() {
        return this.m;
    }

    public String b() {
        return this.f;
    }

    public a a(String str) {
        this.f = str;
        return this;
    }

    public a b(String str) {
        this.i = str;
        i(str);
        return this;
    }

    public String c() {
        return this.k;
    }

    public a c(String str) {
        this.k = str;
        return this;
    }

    public long d() {
        return this.a;
    }

    public String e() {
        return this.g;
    }

    public a d(String str) {
        this.g = str;
        return this;
    }

    public String f() {
        return this.b;
    }

    public String g() {
        return this.l;
    }

    public a e(String str) {
        this.l = str;
        return this;
    }

    public String h() {
        return this.j;
    }

    public a f(String str) {
        if (str != null && str.length() > 0) {
            this.j = str;
        }
        return this;
    }

    public String i() {
        return this.h;
    }

    public a g(String str) {
        this.h = str;
        return this;
    }

    public long j() {
        return this.c;
    }

    public a a(long j) {
        if (j < 0) {
            this.c = 0;
        } else {
            this.c = j;
        }
        return this;
    }

    public long k() {
        return this.d;
    }

    public a b(long j) {
        if (j < 0) {
            this.d = 0;
        } else {
            this.d = j;
        }
        return this;
    }

    public static void a(Context context, String str) {
        Editor edit = context.getSharedPreferences("adv", 0).edit();
        edit.putString(str, new SimpleDateFormat("yyyyMMdd").format(new Date()));
        edit.commit();
    }

    public static boolean b(Context context, String str) {
        return context.getSharedPreferences("adv", 0).getString(str, "").equals(new SimpleDateFormat("yyyyMMdd").format(new Date()));
    }

    public static void c(Context context, String str) {
        Editor edit = context.getSharedPreferences("adv", 0).edit();
        edit.putLong(str, System.currentTimeMillis());
        edit.commit();
    }

    public static long d(Context context, String str) {
        return context.getSharedPreferences("adv", 0).getLong(str, 0);
    }

    public static void a(Context context, String str, boolean z) {
        Editor edit = context.getSharedPreferences("adv", 0).edit();
        edit.putBoolean(str, z);
        edit.commit();
    }

    public static boolean e(Context context, String str) {
        return context.getSharedPreferences("adv", 0).getBoolean(str, false);
    }

    public static void a(Context context) {
        Editor edit = context.getSharedPreferences("adv", 0).edit();
        edit.remove("ADV_SHAKE_DATE");
        edit.remove("ADV_SHAKE_FLAG");
        edit.remove("ADV_CLICK_FLAG");
        edit.commit();
    }

    public int l() {
        return this.e;
    }

    public a a(int i) {
        this.e = i;
        return this;
    }

    public String m() {
        if (this.o != null) {
            return this.o;
        }
        return "";
    }

    public void h(String str) {
        this.o = str;
        if (this.p == null) {
            this.p = new a();
        }
        this.p.a(str);
    }

    public boolean equals(Object obj) {
        try {
            return this.a == ((a) obj).a;
        } catch (Exception e) {
            return false;
        }
    }

    public synchronized void b(Context context) {
        com.qq.reader.common.imageloader.c.a(context).c(this.l);
    }

    public int n() {
        return this.p.a;
    }

    public long o() {
        return this.p.b;
    }

    public boolean p() {
        if (this.p != null) {
            return this.p.g;
        }
        return false;
    }

    public int q() {
        if (r() == 80) {
            return 1;
        }
        if (y() == 13) {
            return 2;
        }
        return 0;
    }

    public int r() {
        if (this.p == null) {
            return 80;
        }
        switch (this.p.i) {
            case 1:
                return 17;
            default:
                return 80;
        }
    }

    public String s() {
        return this.p.c;
    }

    public int t() {
        return this.p.h;
    }

    public String u() {
        return this.p.j;
    }

    public int v() {
        return this.p.k;
    }

    public c w() {
        if (this.r == null) {
            this.r = new c(null);
            Bundle a = this.r.a();
            a.putString("KEY_ACTION", this.p.d);
            a.putString("KEY_ACTIONTAG", this.p.e);
            a.putString("KEY_ACTIONID", this.p.f + "");
            a.putLong("URL_BUILD_PERE_BOOK_ID", this.p.f);
            a.putString("com.qq.reader.WebContent", h());
            a.putString("KEY_JUMP_PAGENAME", c.a(this.p.d));
            a.putBoolean("newactivitywithresult", true);
        }
        return this.r;
    }

    public String x() {
        return this.n;
    }

    public void i(String str) {
        this.n = str;
    }

    public a b(int i) {
        this.q = i;
        return this;
    }

    public int y() {
        return this.q;
    }
}
