package com.tencent.android.tpush.service.channel.c;

import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: ProGuard */
public class a {
    protected byte[] a;
    protected volatile int b;
    protected volatile int c;
    protected volatile int d;
    protected volatile int e;
    protected volatile boolean f;
    protected boolean g;
    protected InputStream h;
    protected boolean i;
    protected OutputStream j;
    protected boolean k;

    public OutputStream a() {
        return this.j;
    }

    public InputStream b() {
        return this.h;
    }

    public int c() {
        int g;
        synchronized (this) {
            g = g();
        }
        return g;
    }

    public int d() {
        int f;
        synchronized (this) {
            f = f();
        }
        return f;
    }

    private void e() {
        Object obj = new byte[(this.a.length * 2)];
        int h = h();
        int g = g();
        if (this.d <= this.c) {
            System.arraycopy(this.a, this.d, obj, 0, this.c - this.d);
        } else {
            int length = this.a.length - this.d;
            System.arraycopy(this.a, this.d, obj, 0, length);
            System.arraycopy(this.a, 0, obj, length, this.c);
        }
        this.a = obj;
        this.d = 0;
        this.b = h;
        this.c = h + g;
    }

    private int f() {
        if (this.c < this.d) {
            return (this.d - this.c) - 1;
        }
        return (this.a.length - 1) - (this.c - this.d);
    }

    private int g() {
        if (this.b <= this.c) {
            return this.c - this.b;
        }
        return this.a.length - (this.b - this.c);
    }

    private int h() {
        if (this.d <= this.b) {
            return this.b - this.d;
        }
        return this.a.length - (this.d - this.b);
    }

    private void i() {
        if (h() >= this.e) {
            this.d = this.b;
            this.e = 0;
        }
    }

    public a() {
        this(4096, true);
    }

    public a(int i, boolean z) {
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = false;
        this.g = true;
        this.h = new b(this);
        this.i = false;
        this.j = new c(this);
        this.k = false;
        if (i == -1) {
            this.a = new byte[4096];
            this.f = true;
        } else {
            this.a = new byte[i];
            this.f = false;
        }
        this.g = z;
    }
}
