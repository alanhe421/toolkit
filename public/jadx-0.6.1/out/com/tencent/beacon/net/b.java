package com.tencent.beacon.net;

import java.io.Serializable;

/* compiled from: ProGuard */
public final class b implements Serializable {
    private String a;
    private long b;
    private String c;
    private String d;
    private String e;
    private boolean f;
    private boolean g;

    public final String a() {
        return this.a;
    }

    public final void a(String str) {
        this.a = str;
    }

    public final void a(long j) {
        this.b = j;
    }

    public final String b() {
        return this.c;
    }

    public final void b(String str) {
        this.c = str;
    }

    public final String c() {
        return this.d;
    }

    public final void c(String str) {
        this.d = str;
    }

    public final String d() {
        return this.e;
    }

    public final void d(String str) {
        this.e = str;
    }

    public final boolean e() {
        return this.f;
    }

    public final void a(boolean z) {
        this.f = z;
    }

    public final boolean f() {
        return this.g;
    }

    public final void b(boolean z) {
        this.g = z;
    }

    public final String toString() {
        return "type:" + this.a + "\ntime:" + this.b + "\ndest:" + this.c + "\n";
    }
}
