package org.apaches.commons.codec.a;

/* compiled from: BaseNCodec */
public abstract class b {
    private final int a;
    @Deprecated
    protected final byte b;
    protected final byte c;
    protected final int d;
    private final int e;
    private final int f;

    abstract void a(byte[] bArr, int i, int i2, a aVar);

    protected abstract boolean a(byte b);

    protected b(int i, int i2, int i3, int i4) {
        this(i, i2, i3, i4, (byte) 61);
    }

    protected b(int i, int i2, int i3, int i4, byte b) {
        int i5 = 0;
        this.b = (byte) 61;
        this.a = i;
        this.e = i2;
        int i6 = (i3 <= 0 || i4 <= 0) ? 0 : 1;
        if (i6 != 0) {
            i5 = (i3 / i2) * i2;
        }
        this.d = i5;
        this.f = i4;
        this.c = b;
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

    int b(byte[] bArr, int i, int i2, a aVar) {
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

    public byte[] b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        a aVar = new a();
        a(bArr, 0, bArr.length, aVar);
        a(bArr, 0, -1, aVar);
        bArr = new byte[(aVar.d - aVar.e)];
        b(bArr, 0, bArr.length, aVar);
        return bArr;
    }

    protected boolean c(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        for (byte b : bArr) {
            if (this.c == b || a(b)) {
                return true;
            }
        }
        return false;
    }

    public long d(byte[] bArr) {
        long length = ((long) (((bArr.length + this.a) - 1) / this.a)) * ((long) this.e);
        if (this.d > 0) {
            return length + ((((((long) this.d) + length) - 1) / ((long) this.d)) * ((long) this.f));
        }
        return length;
    }
}
