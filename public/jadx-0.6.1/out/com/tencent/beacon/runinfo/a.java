package com.tencent.beacon.runinfo;

import java.io.Serializable;

/* compiled from: ProGuard */
public class a implements Serializable {
    private long a;
    private long b = 0;
    private long c = 0;
    private long d = 0;
    private long e = 0;

    public final synchronized long a() {
        return this.d;
    }

    public final synchronized void a(long j) {
        this.d = j;
    }

    public final synchronized long b() {
        return this.e;
    }

    public final synchronized void b(long j) {
        this.e = j;
    }

    public final synchronized long c() {
        return this.b;
    }

    public final synchronized void c(long j) {
        this.b = j;
    }

    public final synchronized long d() {
        return this.c;
    }

    public final synchronized void d(long j) {
        this.c = j;
    }

    public final synchronized long e() {
        return this.a;
    }

    public final synchronized void e(long j) {
        this.a = j;
    }
}
