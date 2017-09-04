package com.qrcomic.entity;

/* compiled from: ComicHistory */
public class c extends b {
    public boolean A;
    public int B;
    public int C;
    public int D;
    public int E;
    public long F;
    public String G;
    public int H;
    private Long I;
    private String J;
    public String r;
    public String s;
    public String t;
    public String u;
    public String v;
    public long w;
    public long x;
    public int y;
    public boolean z;

    public c(String str, String str2) {
        this.r = str;
        this.s = str2;
    }

    public c(Long l, String str, String str2, String str3, String str4, String str5, String str6, long j, long j2, int i, boolean z, boolean z2, int i2, int i3, int i4, int i5, long j3, String str7, int i6) {
        this.I = l;
        this.J = str;
        this.r = str2;
        this.s = str3;
        this.t = str4;
        this.u = str5;
        this.v = str6;
        this.w = j;
        this.x = j2;
        this.y = i;
        this.z = z;
        this.A = z2;
        this.B = i2;
        this.C = i3;
        this.D = i4;
        this.E = i5;
        this.F = j3;
        this.G = str7;
        this.H = i6;
    }

    public void a(e eVar) {
        this.G = eVar.e;
        if (d.a(this.E)) {
            this.H = eVar.h;
        } else {
            this.H = eVar.g;
        }
    }

    public void a(a aVar) {
        this.t = aVar.b;
        this.E = aVar.m;
        this.u = aVar.l;
        this.v = String.valueOf(aVar.i);
        this.D = aVar.e;
        this.y = a(this.D);
        this.F = aVar.k;
    }

    public int a(int i) {
        if (i == 2 || i == 3) {
            return 1;
        }
        return 4;
    }

    public String a() {
        return this.r;
    }

    public void a(String str) {
        this.r = str;
    }

    public String b() {
        return this.s;
    }

    public void b(String str) {
        this.s = str;
    }

    public String c() {
        return this.t;
    }

    public void c(String str) {
        this.t = str;
    }

    public String d() {
        return this.u;
    }

    public void d(String str) {
        this.u = str;
    }

    public String e() {
        return this.v;
    }

    public void e(String str) {
        this.v = str;
    }

    public long f() {
        return this.w;
    }

    public void a(long j) {
        this.w = j;
    }

    public long g() {
        return this.x;
    }

    public void b(long j) {
        this.x = j;
    }

    public int h() {
        return this.y;
    }

    public void b(int i) {
        this.y = i;
    }

    public boolean i() {
        return this.z;
    }

    public void a(boolean z) {
        this.z = z;
    }

    public boolean j() {
        return this.A;
    }

    public void b(boolean z) {
        this.A = z;
    }

    public int k() {
        return this.B;
    }

    public void c(int i) {
        this.B = i;
    }

    public int l() {
        return this.C;
    }

    public void d(int i) {
        this.C = i;
    }

    public int m() {
        return this.D;
    }

    public void e(int i) {
        this.D = i;
    }

    public int n() {
        return this.E;
    }

    public void f(int i) {
        this.E = i;
    }

    public long o() {
        return this.F;
    }

    public void c(long j) {
        this.F = j;
    }

    public String p() {
        return this.G;
    }

    public void f(String str) {
        this.G = str;
    }

    public int q() {
        return this.H;
    }

    public void g(int i) {
        this.H = i;
    }

    public void a(Long l) {
        this.I = l;
    }

    public String s() {
        return this.J;
    }

    public void g(String str) {
        this.J = str;
    }

    public Long r() {
        return this.I;
    }
}
