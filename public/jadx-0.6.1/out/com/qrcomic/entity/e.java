package com.qrcomic.entity;

import java.util.List;

/* compiled from: ComicReadProgress */
public class e extends j {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public int g;
    public int h;
    public int i;
    public boolean j;
    public byte[] k;
    public long l = 0;
    public int m;
    public List<ComicSectionReaded> n;
    private Long o;

    public e(String str, String str2, String str3, Long l, String str4, String str5, String str6, int i, int i2, int i3, boolean z, byte[] bArr, long j, int i4) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.o = l;
        this.d = str4;
        this.e = str5;
        this.f = str6;
        this.g = i;
        this.h = i2;
        this.i = i3;
        this.j = z;
        this.k = bArr;
        this.l = j;
        this.m = i4;
    }

    public String a() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public String b() {
        return this.c;
    }

    public void b(String str) {
        this.c = str;
    }

    public String c() {
        return this.d;
    }

    public void c(String str) {
        this.d = str;
    }

    public String d() {
        return this.a;
    }

    public void d(String str) {
        this.a = str;
    }

    public String e() {
        return this.e;
    }

    public void e(String str) {
        this.e = str;
    }

    public String f() {
        return this.f;
    }

    public void f(String str) {
        this.f = str;
    }

    public int g() {
        return this.g;
    }

    public void a(int i) {
        this.g = i;
    }

    public int h() {
        return this.h;
    }

    public void b(int i) {
        this.h = i;
    }

    public int i() {
        return this.i;
    }

    public void c(int i) {
        this.i = i;
    }

    public boolean j() {
        return this.j;
    }

    public void a(boolean z) {
        this.j = z;
    }

    public byte[] k() {
        return this.k;
    }

    public void a(byte[] bArr) {
        this.k = bArr;
    }

    public long l() {
        return this.l;
    }

    public void a(long j) {
        this.l = j;
    }

    public int m() {
        return this.m;
    }

    public void d(int i) {
        this.m = i;
    }

    public void a(Long l) {
        this.o = l;
    }

    public Long n() {
        return this.o;
    }
}
