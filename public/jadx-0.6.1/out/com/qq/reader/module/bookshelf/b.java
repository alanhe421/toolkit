package com.qq.reader.module.bookshelf;

/* compiled from: ActivateShelfShower */
public class b {
    final String a = "ActivateShelfShower";
    private long b = 0;
    private long c = 0;
    private long d = 0;
    private String e = "";
    private String f = "";
    private String g = "";
    private int h = 0;

    public b(long j, long j2, long j3) {
        this.b = j;
        this.c = j2;
        this.d = j3;
    }

    public void a(String str) {
        if (str == null) {
            this.e = "";
        } else {
            this.e = str;
        }
    }

    public void b(String str) {
        if (str == null) {
            this.f = "";
        } else {
            this.f = str;
        }
    }

    public void c(String str) {
        if (str == null) {
            this.g = "";
        } else {
            this.g = str;
        }
    }

    public void a(int i) {
        this.h = i;
    }

    public int a() {
        return this.h;
    }

    public long b() {
        return this.b;
    }

    public long c() {
        return this.c;
    }

    public long d() {
        return this.d;
    }

    public String e() {
        if (this.e == null) {
            return "";
        }
        return this.e;
    }

    public String f() {
        if (this.f == null) {
            return "";
        }
        return this.f;
    }

    public String g() {
        if (this.g == null) {
            return "";
        }
        return this.g;
    }
}
