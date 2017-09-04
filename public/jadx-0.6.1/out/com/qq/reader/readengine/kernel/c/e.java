package com.qq.reader.readengine.kernel.c;

/* compiled from: VisualPage */
public class e {
    private int a;
    private int b;
    private boolean c;
    private int d;
    private long e;

    public int a() {
        return this.a;
    }

    public void a(int i) {
        this.a = i;
    }

    public int b() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public int c() {
        return this.d;
    }

    public void c(int i) {
        this.d = i;
    }

    public boolean d(int i) {
        if (i < this.a || i > this.b) {
            return false;
        }
        return true;
    }

    public long d() {
        return this.e;
    }

    public void a(long j) {
        this.e = j;
    }
}
