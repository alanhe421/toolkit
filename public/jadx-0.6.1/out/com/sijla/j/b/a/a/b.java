package com.sijla.j.b.a.a;

import java.util.Arrays;

public abstract class b {
    private final int a;
    protected final byte b = (byte) 61;
    protected final int c;
    private final int d;
    private final int e;

    static class a {
        int a;
        long b;
        byte[] c;
        int d;
        int e;
        boolean f;
        int g;
        int h;

        a() {
        }

        public String toString() {
            return String.format("%s[buffer=%s, currentLinePos=%s, eof=%s, ibitWorkArea=%s, lbitWorkArea=%s, modulus=%s, pos=%s, readPos=%s]", new Object[]{getClass().getSimpleName(), Arrays.toString(this.c), Integer.valueOf(this.g), Boolean.valueOf(this.f), Integer.valueOf(this.a), Long.valueOf(this.b), Integer.valueOf(this.h), Integer.valueOf(this.d), Integer.valueOf(this.e)});
        }
    }

    abstract void a(byte[] bArr, int i, int i2, a aVar);

    protected abstract boolean a(byte b);

    abstract void b(byte[] bArr, int i, int i2, a aVar);

    protected b(int i, int i2, int i3, int i4) {
        int i5;
        int i6 = 0;
        this.a = i;
        this.d = i2;
        if (i3 <= 0 || i4 <= 0) {
            i5 = 0;
        } else {
            i5 = 1;
        }
        if (i5 != 0) {
            i6 = (i3 / i2) * i2;
        }
        this.c = i6;
        this.e = i4;
    }

    int a(a aVar) {
        return aVar.c != null ? aVar.d - aVar.e : 0;
    }

    protected int a() {
        return 8192;
    }

    private byte[] b(a aVar) {
        if (aVar.c == null) {
            aVar.c = new byte[a()];
            aVar.d = 0;
            aVar.e = 0;
        } else {
            Object obj = new byte[(aVar.c.length * 2)];
            System.arraycopy(aVar.c, 0, obj, 0, aVar.c.length);
            aVar.c = obj;
        }
        return aVar.c;
    }

    protected byte[] a(int i, a aVar) {
        if (aVar.c == null || aVar.c.length < aVar.d + i) {
            return b(aVar);
        }
        return aVar.c;
    }

    int c(byte[] bArr, int i, int i2, a aVar) {
        if (aVar.c == null) {
            return aVar.f ? -1 : 0;
        } else {
            int min = Math.min(a(aVar), i2);
            System.arraycopy(aVar.c, aVar.e, bArr, i, min);
            aVar.e += min;
            if (aVar.e < aVar.d) {
                return min;
            }
            aVar.c = null;
            return min;
        }
    }

    public byte[] b(String str) {
        return c(c.a(str));
    }

    public byte[] c(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        a aVar = new a();
        b(bArr, 0, bArr.length, aVar);
        b(bArr, 0, -1, aVar);
        bArr = new byte[aVar.d];
        c(bArr, 0, bArr.length, aVar);
        return bArr;
    }

    public byte[] d(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        a aVar = new a();
        a(bArr, 0, bArr.length, aVar);
        a(bArr, 0, -1, aVar);
        bArr = new byte[(aVar.d - aVar.e)];
        c(bArr, 0, bArr.length, aVar);
        return bArr;
    }

    protected boolean e(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        for (byte b : bArr) {
            if ((byte) 61 == b || a(b)) {
                return true;
            }
        }
        return false;
    }

    public long f(byte[] bArr) {
        long length = ((long) (((bArr.length + this.a) - 1) / this.a)) * ((long) this.d);
        if (this.c > 0) {
            return length + ((((((long) this.c) + length) - 1) / ((long) this.c)) * ((long) this.e));
        }
        return length;
    }
}
