package com.qrcomic.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/* compiled from: Comic */
public class a extends j {
    @SerializedName("count")
    public int A;
    @SerializedName("direction")
    public int B;
    @SerializedName("pageType")
    public int C;
    @SerializedName("detailMode")
    public int D;
    public boolean E;
    @SerializedName("checkLevel")
    public int F;
    @SerializedName("specialFree")
    public int G;
    @SerializedName("specialFreeStart")
    public long H;
    @SerializedName("specialFreeEnd")
    public long I;
    @SerializedName("commentCount")
    public long J;
    private Long K;
    @SerializedName("id")
    public String a;
    @SerializedName("title")
    public String b;
    @SerializedName("chapterCount")
    public int c;
    @Expose(deserialize = false, serialize = false)
    @Deprecated
    public long d;
    @SerializedName("status")
    public int e;
    @SerializedName("payType")
    public int f;
    @SerializedName("buyType")
    public int g;
    public byte[] h;
    public int i;
    @SerializedName("isChapterSplit")
    public boolean j = false;
    @SerializedName("updateTime")
    public long k;
    @SerializedName("coverUrl")
    public String l;
    @SerializedName("type")
    public int m;
    public List<Chapter> n;
    @SerializedName("sectionIdList")
    public List<String> o;
    @SerializedName("sectionList")
    public List<f> p;
    @SerializedName("author")
    public String q;
    @SerializedName("discount")
    public int r;
    @SerializedName("disStart")
    public long s;
    @SerializedName("disEnd")
    public long t;
    @SerializedName("monthly")
    public int u;
    @SerializedName("monthlyStart")
    public long v;
    @SerializedName("monthlyEnd")
    public long w;
    @Expose(deserialize = false, serialize = false)
    public String x;
    @Expose(deserialize = false, serialize = false)
    public int y;
    @SerializedName("begin")
    public String z;

    public List<String> a() {
        if (this.j && this.o == null) {
            this.o = new ArrayList(this.i);
            for (Chapter chapter : this.n) {
                this.o.addAll(chapter.sectionIdList);
            }
        }
        return this.o;
    }

    public a(Long l, String str, String str2, int i, long j, int i2, int i3, int i4, byte[] bArr, int i5, boolean z, long j2, String str3, int i6, String str4, int i7, long j3, long j4, int i8, long j5, long j6, String str5, int i9, int i10, int i11, long j7, long j8, long j9) {
        this.K = l;
        this.a = str;
        this.b = str2;
        this.c = i;
        this.d = j;
        this.e = i2;
        this.f = i3;
        this.g = i4;
        this.h = bArr;
        this.i = i5;
        this.j = z;
        this.k = j2;
        this.l = str3;
        this.m = i6;
        this.q = str4;
        this.r = i7;
        this.s = j3;
        this.t = j4;
        this.u = i8;
        this.v = j5;
        this.w = j6;
        this.x = str5;
        this.y = i9;
        this.F = i10;
        this.G = i11;
        this.H = j7;
        this.I = j8;
        this.J = j9;
    }

    public boolean b() {
        if (this.r != 0) {
            return false;
        }
        if (this.s == 0 && this.t == 0) {
            return true;
        }
        if (this.s != 0 && this.t == 0) {
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis < this.s || currentTimeMillis > this.t) {
            return false;
        }
        return true;
    }

    public boolean c() {
        if (this.u != 3 || this.F <= 8) {
            return false;
        }
        if (this.v == 0 && this.w == 0) {
            return true;
        }
        if (this.v != 0 && this.w == 0) {
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis < this.v || currentTimeMillis > this.w) {
            return false;
        }
        return true;
    }

    public boolean d() {
        if (this.G != 1) {
            return false;
        }
        if (this.H == 0 && this.I == 0) {
            return true;
        }
        if (this.H != 0 && this.I == 0) {
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis < this.H || currentTimeMillis > this.I) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "{ comicId = " + this.a + " , comicName = " + this.b + " , pagetype = " + this.C + " , direction = " + this.B + " , count = " + this.A + " , count = " + this.A + " }";
    }

    public String e() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public String f() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public int g() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }

    public long h() {
        return this.d;
    }

    public void a(long j) {
        this.d = j;
    }

    public int i() {
        return this.e;
    }

    public void b(int i) {
        this.e = i;
    }

    public byte[] j() {
        return this.h;
    }

    public void a(byte[] bArr) {
        this.h = bArr;
    }

    public int k() {
        return this.i;
    }

    public void c(int i) {
        this.i = i;
    }

    public boolean l() {
        return this.j;
    }

    public void a(boolean z) {
        this.j = z;
    }

    public long m() {
        return this.k;
    }

    public void b(long j) {
        this.k = j;
    }

    public String n() {
        return this.l;
    }

    public void c(String str) {
        this.l = str;
    }

    public int o() {
        return this.m;
    }

    public void d(int i) {
        this.m = i;
    }

    public void a(Long l) {
        this.K = l;
    }

    public Long p() {
        return this.K;
    }

    public int q() {
        return this.f;
    }

    public void e(int i) {
        this.f = i;
    }

    public int r() {
        return this.g;
    }

    public void f(int i) {
        this.g = i;
    }

    public String s() {
        return this.q;
    }

    public void d(String str) {
        this.q = str;
    }

    public long t() {
        return this.t;
    }

    public void c(long j) {
        this.t = j;
    }

    public long u() {
        return this.s;
    }

    public void d(long j) {
        this.s = j;
    }

    public int v() {
        return this.r;
    }

    public void g(int i) {
        this.r = i;
    }

    public int w() {
        return this.y;
    }

    public void h(int i) {
        this.y = i;
    }

    public String x() {
        return this.x;
    }

    public void e(String str) {
        this.x = str;
    }

    public long y() {
        return this.w;
    }

    public void e(long j) {
        this.w = j;
    }

    public long z() {
        return this.v;
    }

    public void f(long j) {
        this.v = j;
    }

    public int A() {
        return this.u;
    }

    public void i(int i) {
        this.u = i;
    }

    public int B() {
        return this.F;
    }

    public void j(int i) {
        this.F = i;
    }

    public int C() {
        return this.G;
    }

    public void k(int i) {
        this.G = i;
    }

    public long D() {
        return this.I;
    }

    public void g(long j) {
        this.I = j;
    }

    public long E() {
        return this.H;
    }

    public void h(long j) {
        this.H = j;
    }

    public long F() {
        return this.J;
    }

    public void i(long j) {
        this.J = j;
    }
}
