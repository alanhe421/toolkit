package com.qq.reader.module.redpacket.singlebookpacket;

import com.qq.reader.module.bookstore.qnative.c.a;

/* compiled from: RedPacketSingleBookPackage */
public class c {
    private d a;
    private int b;
    private String c;
    private long d;
    private long e;
    private long f;
    private int g;

    public c(int i, a aVar) {
        this.a = null;
        this.b = 0;
        this.c = "";
        this.d = -1;
        this.e = -1;
        this.f = -1;
        this.g = 2;
        this.a = new d();
        this.b = i;
        this.a.a(aVar);
    }

    public int a() {
        return this.g;
    }

    public void a(int i) {
        this.g = i;
    }

    public boolean b() {
        return this.a.a();
    }

    public long c() {
        return this.d;
    }

    public void a(long j) {
        this.d = j;
        this.a.a(this.d);
    }

    public long d() {
        return this.e;
    }

    public void b(long j) {
        this.e = j;
    }

    public long e() {
        return this.f;
    }

    public void c(long j) {
        this.f = j;
    }

    public int f() {
        return this.b;
    }

    public int g() {
        return this.a.d().size();
    }

    public d h() {
        return this.a;
    }
}
