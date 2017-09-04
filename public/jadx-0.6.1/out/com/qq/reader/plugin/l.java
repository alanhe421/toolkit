package com.qq.reader.plugin;

import com.qq.reader.common.c.a;

/* compiled from: PluginData */
public class l {
    protected String a = "";
    protected String b = "";
    private String c = "";
    private String d = "";
    private String e = "";
    private String f = "";
    private String g = "";
    private String h = "";
    private String i = "1";
    private String j = "";
    private String k = "";
    private String l = "";
    private String m = "";
    private String n = "";
    private String o = "";
    private int p = 0;
    private long q = 0;
    private int r = 0;
    private int s = 1;
    private boolean t = false;
    private boolean u = false;

    public l(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13) {
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = str4;
        this.g = str5;
        this.h = str6;
        this.l = str9;
        f(str10);
        this.i = str11;
        this.j = str7;
        this.k = str8;
        this.n = str12;
        this.o = str13;
        StringBuffer stringBuffer = new StringBuffer(a.an);
        stringBuffer.append(this.c);
        if (this.d.length() > 0) {
            stringBuffer.append("_");
            stringBuffer.append(this.d);
        }
        stringBuffer.append("_c");
        stringBuffer.append(".p");
        this.a = stringBuffer.toString();
        stringBuffer = new StringBuffer(a.an);
        stringBuffer.append(this.c);
        if (this.d.length() > 0) {
            stringBuffer.append("_");
            stringBuffer.append(this.d);
        }
        stringBuffer.append("_m");
        stringBuffer.append(".p");
        this.b = stringBuffer.toString();
    }

    public int a() {
        return this.p;
    }

    public void a(int i) {
        this.p = i;
    }

    public void a(String str) {
        this.f = str;
    }

    public void b(String str) {
        this.n = str;
    }

    public void c(String str) {
        this.o = str;
    }

    public String b() {
        return this.n;
    }

    public String c() {
        return this.o;
    }

    public int d() {
        return this.r;
    }

    public l b(int i) {
        this.r = i;
        return this;
    }

    public int e() {
        return this.s;
    }

    public l c(int i) {
        this.s = i;
        return this;
    }

    public l a(long j) {
        this.q = j;
        return this;
    }

    public long f() {
        return this.q;
    }

    public String g() {
        return this.j;
    }

    public String h() {
        return this.k;
    }

    public String i() {
        return this.c;
    }

    public String j() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(a.k);
        stringBuilder.append("_");
        stringBuilder.append(this.c);
        return stringBuilder.toString();
    }

    public String k() {
        return this.d;
    }

    public String l() {
        return this.e;
    }

    public String m() {
        return this.f;
    }

    public String n() {
        return this.g;
    }

    public String o() {
        return this.h;
    }

    public String p() {
        return this.i;
    }

    public boolean q() {
        if (this.i == null || this.i.length() == 0) {
            return true;
        }
        return "1".equals(this.i);
    }

    public void d(String str) {
        this.i = str;
    }

    public String r() {
        return this.a;
    }

    public String s() {
        return this.b;
    }

    public String t() {
        return this.l;
    }

    public boolean u() {
        if ("0".equals(this.l)) {
            return true;
        }
        return false;
    }

    public void e(String str) {
        if ("0".equals(str)) {
            this.l = "0";
        } else if ("1".equals(str)) {
            this.l = "1";
        }
    }

    public void f(String str) {
        if (str != null && str.length() > 0) {
            if (str.endsWith("元宝")) {
                str = str.substring(0, str.length() - 2) + "书币";
            }
            this.m = str;
        }
    }

    public String v() {
        return this.m;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof l)) {
            return false;
        }
        if (k().equals(((l) obj).k()) && i().equals(((l) obj).i())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((k().hashCode() ^ (k().hashCode() >>> 32)) + 31) * 31) + (i().hashCode() ^ (i().hashCode() >>> 32));
    }
}
