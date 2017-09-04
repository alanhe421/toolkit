package com.qq.reader.cservice.download.book;

/* compiled from: ObtainDownloadUrlResult */
public class g {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private int h;
    private long i;

    public g(String str) {
        this.a = str;
    }

    public int a() {
        return this.h;
    }

    public void a(int i) {
        this.h = i;
    }

    public long b() {
        return this.i;
    }

    public void a(long j) {
        this.i = j;
    }

    public void a(String str) {
        this.b = str;
    }

    public String c() {
        return this.c;
    }

    public void b(String str) {
        this.c = str;
    }

    public String d() {
        return this.g;
    }

    public void c(String str) {
        this.g = str;
    }

    public void d(String str) {
        this.d = str + "&origin=" + this.e;
    }

    public String e() {
        return this.a;
    }

    public void e(String str) {
        if (str == null) {
            this.e = "";
        } else {
            this.e = str;
        }
    }

    public String f() {
        return this.f;
    }

    public void f(String str) {
        this.f = str;
    }
}
