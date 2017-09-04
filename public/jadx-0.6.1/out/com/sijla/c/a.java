package com.sijla.c;

public class a<E> {
    static final /* synthetic */ boolean a = (!a.class.desiredAssertionStatus());
    private transient E[] b;
    private transient int c;
    private transient int d;

    private void a(int i) {
        int i2 = 8;
        if (i >= 8) {
            i2 = (i >>> 1) | i;
            i2 |= i2 >>> 2;
            i2 |= i2 >>> 4;
            i2 |= i2 >>> 8;
            i2 = (i2 | (i2 >>> 16)) + 1;
            if (i2 < 0) {
                i2 >>>= 1;
            }
        }
        this.b = new Object[i2];
    }

    private void d() {
        if (a || this.c == this.d) {
            int i = this.c;
            int length = this.b.length;
            int i2 = length - i;
            int i3 = length << 1;
            if (i3 < 0) {
                throw new IllegalStateException("Sorry, deque too big");
            }
            Object obj = new Object[i3];
            System.arraycopy(this.b, i, obj, 0, i2);
            System.arraycopy(this.b, 0, obj, i2, i);
            this.b = (Object[]) obj;
            this.c = 0;
            this.d = length;
            return;
        }
        throw new AssertionError();
    }

    public a() {
        this.b = new Object[16];
    }

    public a(int i) {
        a(i);
    }

    public void a(E e) {
        if (e == null) {
            throw new NullPointerException("e == null");
        }
        this.b[this.d] = e;
        int length = (this.d + 1) & (this.b.length - 1);
        this.d = length;
        if (length == this.c) {
            d();
        }
    }

    public boolean b(E e) {
        a((Object) e);
        return true;
    }

    public E a() {
        int i = this.c;
        E e = this.b[i];
        if (e == null) {
            return null;
        }
        this.b[i] = null;
        this.c = (i + 1) & (this.b.length - 1);
        return e;
    }

    public E b() {
        int length = (this.b.length - 1) & (this.d - 1);
        E e = this.b[length];
        if (e == null) {
            return null;
        }
        this.b[length] = null;
        this.d = length;
        return e;
    }

    public int c() {
        return (this.d - this.c) & (this.b.length - 1);
    }
}
