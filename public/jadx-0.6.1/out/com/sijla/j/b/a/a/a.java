package com.sijla.j.b.a.a;

import com.qq.taf.jce.JceStruct;

public class a extends b {
    static final byte[] a = new byte[]{JceStruct.SIMPLE_LIST, (byte) 10};
    private static final byte[] d = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};
    private static final byte[] e = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 45, (byte) 95};
    private static final byte[] f = new byte[]{(byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 62, (byte) -1, (byte) 62, (byte) -1, (byte) 63, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 58, (byte) 59, (byte) 60, (byte) 61, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, JceStruct.STRUCT_END, JceStruct.ZERO_TAG, JceStruct.SIMPLE_LIST, (byte) 14, (byte) 15, (byte) 16, (byte) 17, (byte) 18, (byte) 19, com.tencent.qalsdk.base.a.z, (byte) 21, (byte) 22, (byte) 23, (byte) 24, (byte) 25, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) 63, (byte) -1, (byte) 26, (byte) 27, (byte) 28, (byte) 29, (byte) 30, (byte) 31, (byte) 32, (byte) 33, (byte) 34, (byte) 35, (byte) 36, (byte) 37, (byte) 38, (byte) 39, (byte) 40, (byte) 41, (byte) 42, (byte) 43, (byte) 44, (byte) 45, (byte) 46, (byte) 47, (byte) 48, (byte) 49, (byte) 50, (byte) 51};
    private final byte[] g;
    private final byte[] h;
    private final byte[] i;
    private final int j;
    private final int k;

    public a() {
        this(0);
    }

    public a(boolean z) {
        this(76, a, z);
    }

    public a(int i) {
        this(i, a);
    }

    public a(int i, byte[] bArr) {
        this(i, bArr, false);
    }

    public a(int i, byte[] bArr, boolean z) {
        int i2;
        if (bArr == null) {
            i2 = 0;
        } else {
            i2 = bArr.length;
        }
        super(3, 4, i, i2);
        this.h = f;
        if (bArr == null) {
            this.k = 4;
            this.i = null;
        } else if (e(bArr)) {
            throw new IllegalArgumentException("lineSeparator must not contain base64 characters: [" + c.a(bArr) + "]");
        } else if (i > 0) {
            this.k = bArr.length + 4;
            this.i = new byte[bArr.length];
            System.arraycopy(bArr, 0, this.i, 0, bArr.length);
        } else {
            this.k = 4;
            this.i = null;
        }
        this.j = this.k - 1;
        this.g = z ? e : d;
    }

    void a(byte[] bArr, int i, int i2, a aVar) {
        if (!aVar.f) {
            int i3;
            int i4;
            if (i2 < 0) {
                aVar.f = true;
                if (aVar.h != 0 || this.c != 0) {
                    Object a = a(this.k, aVar);
                    i3 = aVar.d;
                    switch (aVar.h) {
                        case 0:
                            break;
                        case 1:
                            i4 = aVar.d;
                            aVar.d = i4 + 1;
                            a[i4] = this.g[(aVar.a >> 2) & 63];
                            i4 = aVar.d;
                            aVar.d = i4 + 1;
                            a[i4] = this.g[(aVar.a << 4) & 63];
                            if (this.g == d) {
                                i4 = aVar.d;
                                aVar.d = i4 + 1;
                                a[i4] = 61;
                                i4 = aVar.d;
                                aVar.d = i4 + 1;
                                a[i4] = 61;
                                break;
                            }
                            break;
                        case 2:
                            i4 = aVar.d;
                            aVar.d = i4 + 1;
                            a[i4] = this.g[(aVar.a >> 10) & 63];
                            i4 = aVar.d;
                            aVar.d = i4 + 1;
                            a[i4] = this.g[(aVar.a >> 4) & 63];
                            i4 = aVar.d;
                            aVar.d = i4 + 1;
                            a[i4] = this.g[(aVar.a << 2) & 63];
                            if (this.g == d) {
                                i4 = aVar.d;
                                aVar.d = i4 + 1;
                                a[i4] = 61;
                                break;
                            }
                            break;
                        default:
                            throw new IllegalStateException("Impossible modulus " + aVar.h);
                    }
                    aVar.g = (aVar.d - i3) + aVar.g;
                    if (this.c > 0 && aVar.g > 0) {
                        System.arraycopy(this.i, 0, a, aVar.d, this.i.length);
                        aVar.d += this.i.length;
                        return;
                    }
                    return;
                }
                return;
            }
            i3 = 0;
            while (i3 < i2) {
                Object a2 = a(this.k, aVar);
                aVar.h = (aVar.h + 1) % 3;
                i4 = i + 1;
                int i5 = bArr[i];
                if (i5 < 0) {
                    i5 += 256;
                }
                aVar.a = i5 + (aVar.a << 8);
                if (aVar.h == 0) {
                    i5 = aVar.d;
                    aVar.d = i5 + 1;
                    a2[i5] = this.g[(aVar.a >> 18) & 63];
                    i5 = aVar.d;
                    aVar.d = i5 + 1;
                    a2[i5] = this.g[(aVar.a >> 12) & 63];
                    i5 = aVar.d;
                    aVar.d = i5 + 1;
                    a2[i5] = this.g[(aVar.a >> 6) & 63];
                    i5 = aVar.d;
                    aVar.d = i5 + 1;
                    a2[i5] = this.g[aVar.a & 63];
                    aVar.g += 4;
                    if (this.c > 0 && this.c <= aVar.g) {
                        System.arraycopy(this.i, 0, a2, aVar.d, this.i.length);
                        aVar.d += this.i.length;
                        aVar.g = 0;
                    }
                }
                i3++;
                i = i4;
            }
        }
    }

    void b(byte[] bArr, int i, int i2, a aVar) {
        if (!aVar.f) {
            int i3;
            if (i2 < 0) {
                aVar.f = true;
            }
            int i4 = 0;
            while (i4 < i2) {
                byte[] a = a(this.j, aVar);
                i3 = i + 1;
                byte b = bArr[i];
                if (b == (byte) 61) {
                    aVar.f = true;
                    break;
                }
                if (b >= (byte) 0 && b < f.length) {
                    b = f[b];
                    if (b >= (byte) 0) {
                        aVar.h = (aVar.h + 1) % 4;
                        aVar.a = b + (aVar.a << 6);
                        if (aVar.h == 0) {
                            int i5 = aVar.d;
                            aVar.d = i5 + 1;
                            a[i5] = (byte) ((aVar.a >> 16) & 255);
                            i5 = aVar.d;
                            aVar.d = i5 + 1;
                            a[i5] = (byte) ((aVar.a >> 8) & 255);
                            i5 = aVar.d;
                            aVar.d = i5 + 1;
                            a[i5] = (byte) (aVar.a & 255);
                        }
                    }
                }
                i4++;
                i = i3;
            }
            if (aVar.f && aVar.h != 0) {
                byte[] a2 = a(this.j, aVar);
                switch (aVar.h) {
                    case 1:
                        return;
                    case 2:
                        aVar.a >>= 4;
                        i3 = aVar.d;
                        aVar.d = i3 + 1;
                        a2[i3] = (byte) (aVar.a & 255);
                        return;
                    case 3:
                        aVar.a >>= 2;
                        i3 = aVar.d;
                        aVar.d = i3 + 1;
                        a2[i3] = (byte) ((aVar.a >> 8) & 255);
                        i3 = aVar.d;
                        aVar.d = i3 + 1;
                        a2[i3] = (byte) (aVar.a & 255);
                        return;
                    default:
                        throw new IllegalStateException("Impossible modulus " + aVar.h);
                }
            }
        }
    }

    public static byte[] a(byte[] bArr) {
        return a(bArr, false, true);
    }

    public static String b(byte[] bArr) {
        return c.a(a(bArr, false, true));
    }

    public static byte[] a(byte[] bArr, boolean z, boolean z2) {
        return a(bArr, z, z2, Integer.MAX_VALUE);
    }

    public static byte[] a(byte[] bArr, boolean z, boolean z2, int i) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        a aVar = z ? new a(z2) : new a(0, a, z2);
        long f = aVar.f(bArr);
        if (f <= ((long) i)) {
            return aVar.d(bArr);
        }
        throw new IllegalArgumentException("Input array too big, the output array would be bigger (" + f + ") than the specified maximum size of " + i);
    }

    public static byte[] a(String str) {
        return new a().b(str);
    }

    protected boolean a(byte b) {
        return b >= (byte) 0 && b < this.h.length && this.h[b] != (byte) -1;
    }
}
