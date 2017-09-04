package com.qrcomic.entity;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.qrcomic.util.j;
import java.util.List;

/* compiled from: ComicSection */
public class f extends j implements Comparable<Object> {
    @SerializedName("cartoonId")
    public String a;
    @SerializedName("id")
    public String b;
    @SerializedName("title")
    public String c;
    @SerializedName("size")
    public long d;
    @SerializedName("pageCount")
    public int e;
    @SerializedName("index")
    public int f;
    @SerializedName("coverUrl")
    public String g;
    @SerializedName("updateTime")
    public long h;
    public byte[] i;
    @SerializedName("shareStatus")
    public int j;
    @SerializedName("definition")
    public String k;
    @SerializedName("payType")
    public int l;
    @SerializedName("specialFree")
    public int m;
    @SerializedName("specialFreeStart")
    public long n;
    @SerializedName("specialFreeEnd")
    public long o;
    public String p;
    @SerializedName("sectionPicList")
    public List<ComicSectionPicInfo> q;
    public i r;
    public boolean s = false;
    public int t = 0;
    public int u = 3;
    public boolean v;
    public boolean w = false;
    private Long x;

    public String a() {
        return j.a(this.a, this.b);
    }

    public f(Long l, String str, String str2, String str3, long j, int i, int i2, String str4, long j2, byte[] bArr, int i3, String str5, int i4, int i5, long j3, long j4) {
        this.x = l;
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = j;
        this.e = i;
        this.f = i2;
        this.g = str4;
        this.h = j2;
        this.i = bArr;
        this.j = i3;
        this.k = str5;
        this.l = i4;
        this.m = i5;
        this.n = j3;
        this.o = j4;
    }

    public boolean b() {
        if (this.m != 1) {
            return false;
        }
        if (this.n == 0 && this.o == 0) {
            return true;
        }
        if (this.n != 0 && this.o == 0) {
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis < this.n || currentTimeMillis > this.o) {
            return false;
        }
        return true;
    }

    public String toString() {
        if (this.r != null) {
            return "第" + this.r.l + "话";
        }
        return "sectionId -> " + this.b + " comicId -> " + this.a + " name -> " + this.c;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof f)) {
            return false;
        }
        if (super.equals(obj)) {
            return true;
        }
        f fVar = (f) obj;
        if (TextUtils.equals(this.a, fVar.a) && TextUtils.equals(this.b, fVar.b)) {
            return true;
        }
        return false;
    }

    public int compareTo(Object obj) {
        if (obj == null || !(obj instanceof f)) {
            return 0;
        }
        f fVar = (f) obj;
        if (this.r == null || fVar.r == null) {
            return 0;
        }
        if (this.r.l < fVar.r.l) {
            return -1;
        }
        if (this.r.l > fVar.r.l) {
            return 1;
        }
        return 0;
    }

    public String c() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public String d() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public String e() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public long f() {
        return this.d;
    }

    public void a(long j) {
        this.d = j;
    }

    public int g() {
        return this.e;
    }

    public void a(int i) {
        this.e = i;
    }

    public int h() {
        return this.f;
    }

    public void b(int i) {
        this.f = i;
    }

    public String i() {
        return this.g;
    }

    public void d(String str) {
        this.g = str;
    }

    public long j() {
        return this.h;
    }

    public void b(long j) {
        this.h = j;
    }

    public byte[] k() {
        return this.i;
    }

    public void a(byte[] bArr) {
        this.i = bArr;
    }

    public int l() {
        return this.j;
    }

    public void c(int i) {
        this.j = i;
    }

    public String m() {
        return this.k;
    }

    public void e(String str) {
        this.k = str;
    }

    public Long n() {
        return this.x;
    }

    public void a(Long l) {
        this.x = l;
    }

    public int o() {
        return this.l;
    }

    public void d(int i) {
        this.l = i;
    }

    public long p() {
        return this.o;
    }

    public void c(long j) {
        this.o = j;
    }

    public long q() {
        return this.n;
    }

    public void d(long j) {
        this.n = j;
    }

    public int r() {
        return this.m;
    }

    public void e(int i) {
        this.m = i;
    }
}
