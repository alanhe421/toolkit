package com.qq.reader.readengine.model;

import com.qq.reader.framework.a.a;
import com.qq.reader.readengine.kernel.g;

/* compiled from: Note */
public class b {
    private long a;
    private String b;
    g c;
    g d;
    private String e;
    private String f;
    private String g;
    private String h;
    private long i;
    private int j;
    private long k;
    private long l;
    private int m;
    private long n;
    private int o;
    private long p;
    private int q;
    private boolean r;
    private a s;
    private String t;

    public b() {
        this.b = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.j = 1;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.c = new g();
        this.d = new g();
        this.r = false;
    }

    public void a(boolean z) {
        this.r = z;
    }

    public b(long j, long j2, long j3, int i, long j4, int i2, long j5, int i3) {
        this.b = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.j = 1;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.c = new g();
        this.d = new g();
        this.r = false;
        this.a = -1;
        this.b = "";
        this.e = String.valueOf(j);
        this.f = String.valueOf(j2);
        this.m = i;
        this.n = j4;
        this.o = i2;
        this.p = j5;
        this.l = j3;
        this.h = "";
        this.g = "";
        this.i = 0;
        this.q = i3;
    }

    public b(long j, long j2, String str, String str2, String str3, String str4, String str5, long j3, int i, long j4, int i2, long j5, long j6, int i3) {
        this.b = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.j = 1;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.c = new g();
        this.d = new g();
        this.r = false;
        this.a = j;
        this.k = j2;
        if (str == null) {
            str = "";
        }
        this.b = str;
        if (str2 == null) {
            str2 = "";
        }
        this.e = str2;
        if (str3 == null) {
            str3 = "";
        }
        this.f = str3;
        if (str4 == null) {
            str4 = "";
        }
        this.h = str4;
        if (str5 == null) {
            str5 = "";
        }
        this.g = str5;
        this.i = j3;
        this.m = i;
        this.n = j4;
        this.o = i2;
        this.p = j5;
        this.l = j6;
        this.q = i3;
    }

    public void b(long j) {
        this.i = j;
    }

    public String c() {
        return this.b;
    }

    public String d() {
        return this.h;
    }

    public String e() {
        if (this.g == null) {
            return "";
        }
        return this.g;
    }

    public void a(String str) {
        this.g = str;
    }

    public long f() {
        return this.a;
    }

    public void c(long j) {
        this.a = j;
    }

    public String g() {
        return this.e;
    }

    public String h() {
        return this.f;
    }

    public void b(String str) {
        this.e = str;
    }

    public void c(String str) {
        this.f = str;
    }

    public long i() {
        return this.i;
    }

    public int j() {
        return this.j;
    }

    public long k() {
        return this.k;
    }

    public void d(long j) {
        this.k = j;
    }

    public void d(String str) {
        this.b = str;
    }

    public a l() {
        return this.s;
    }

    public void a(a aVar) {
        this.s = aVar;
    }

    public int m() {
        return this.m;
    }

    public long n() {
        return this.n;
    }

    public int o() {
        return this.o;
    }

    public long p() {
        return this.p;
    }

    public void a(int i) {
        this.m = i;
    }

    public void e(long j) {
        this.n = j;
    }

    public void b(int i) {
        this.o = i;
    }

    public void f(long j) {
        this.p = j;
    }

    public long q() {
        return this.l;
    }

    public int r() {
        return this.q;
    }

    public g s() {
        if (this.q == 1) {
            if (this.m <= 0 || !this.r) {
                this.c.a(Long.valueOf(this.e).longValue());
            } else {
                this.c.a(this.m, this.n);
            }
        } else if (this.q == 2) {
            this.c.a(Long.valueOf(this.e).longValue());
        }
        return this.c;
    }

    public g t() {
        if (this.q == 1) {
            if (this.o <= 0 || !this.r) {
                this.d.a(Long.valueOf(this.f).longValue());
            } else {
                this.d.a(this.o, this.p);
            }
        } else if (this.q == 2) {
            this.d.a(Long.valueOf(this.f).longValue());
        }
        return this.d;
    }

    public String u() {
        return this.t;
    }

    public void e(String str) {
        this.t = str;
    }
}
