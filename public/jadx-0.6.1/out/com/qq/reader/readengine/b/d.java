package com.qq.reader.readengine.b;

/* compiled from: SearchMark */
public class d {
    private long a;
    private long b;
    private long c;
    private long d;
    private int e;
    private double f;
    private String g;
    private int h;
    private int i = 0;

    public d(long j, long j2) {
        this.a = j;
        this.b = j2;
    }

    public void a(int i) {
        this.i = i;
    }

    public int a() {
        return this.i;
    }

    public long b() {
        return this.a;
    }

    public long c() {
        return this.b;
    }

    public double d() {
        return this.f;
    }

    public void a(double d) {
        double d2 = 1.0d;
        double d3 = 0.0d;
        if (d <= 1.0d) {
            d2 = d;
        }
        if (d2 >= 0.0d) {
            d3 = d2;
        }
        this.f = d3;
    }

    public long e() {
        return this.c;
    }

    public void a(long j) {
        this.c = j;
    }

    public void b(long j) {
        this.d = j;
    }

    public String f() {
        return this.g;
    }

    public void a(String str) {
        this.g = str;
    }

    public void b(int i) {
        this.e = i;
    }

    public int g() {
        return this.e;
    }

    public void c(int i) {
        this.h = i;
    }

    public int h() {
        return this.h;
    }
}
