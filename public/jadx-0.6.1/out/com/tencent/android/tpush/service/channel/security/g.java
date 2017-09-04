package com.tencent.android.tpush.service.channel.security;

/* compiled from: ProGuard */
public class g {
    static final /* synthetic */ boolean a = (!g.class.desiredAssertionStatus());
    private int[] b = new int[4];

    public g(byte[] bArr) {
        int i = 0;
        if (bArr == null) {
            throw new RuntimeException("Invalid key: Key was null");
        } else if (bArr.length < 16) {
            throw new RuntimeException("Invalid key: Length was less than 16 bytes");
        } else {
            int i2 = 0;
            while (i < 4) {
                int i3 = i2 + 1;
                int i4 = i3 + 1;
                i2 = (bArr[i2] & 255) | ((bArr[i3] & 255) << 8);
                i3 = i4 + 1;
                i4 = ((bArr[i4] & 255) << 16) | i2;
                i2 = i3 + 1;
                this.b[i] = ((bArr[i3] & 255) << 24) | i4;
                i++;
            }
        }
    }

    public byte[] a(byte[] bArr) {
        int[] iArr = new int[((((bArr.length % 8 == 0 ? 0 : 1) + (bArr.length / 8)) * 2) + 1)];
        iArr[0] = bArr.length;
        a(bArr, iArr, 1);
        a(iArr);
        return a(iArr, 0, iArr.length * 4);
    }

    public byte[] b(byte[] bArr) {
        if (!a && bArr.length % 4 != 0) {
            throw new AssertionError();
        } else if (a || (bArr.length / 4) % 2 == 1) {
            int[] iArr = new int[(bArr.length / 4)];
            a(bArr, iArr, 0);
            b(iArr);
            return a(iArr, 1, iArr[0]);
        } else {
            throw new AssertionError();
        }
    }

    void a(int[] iArr) {
        if (a || iArr.length % 2 == 1) {
            for (int i = 1; i < iArr.length; i += 2) {
                int i2 = 32;
                int i3 = iArr[i];
                int i4 = iArr[i + 1];
                int i5 = i3;
                i3 = 0;
                while (true) {
                    int i6 = i2 - 1;
                    if (i2 <= 0) {
                        break;
                    }
                    i2 = i3 - 1640531527;
                    i5 += ((((i4 << 4) + this.b[0]) ^ i4) + ((i4 >>> 5) ^ i2)) + this.b[1];
                    i4 = (((((i5 << 4) + this.b[2]) ^ i5) + ((i5 >>> 5) ^ i2)) + this.b[3]) + i4;
                    i3 = i2;
                    i2 = i6;
                }
                iArr[i] = i5;
                iArr[i + 1] = i4;
            }
            return;
        }
        throw new AssertionError();
    }

    void b(int[] iArr) {
        if (a || iArr.length % 2 == 1) {
            for (int i = 1; i < iArr.length; i += 2) {
                int i2 = 32;
                int i3 = iArr[i];
                int i4 = iArr[i + 1];
                int i5 = -957401312;
                while (true) {
                    int i6 = i2 - 1;
                    if (i2 <= 0) {
                        break;
                    }
                    i4 -= ((((i3 << 4) + this.b[2]) ^ i3) + ((i3 >>> 5) ^ i5)) + this.b[3];
                    i3 -= ((((i4 << 4) + this.b[0]) ^ i4) + ((i4 >>> 5) ^ i5)) + this.b[1];
                    i5 = 1640531527 + i5;
                    i2 = i6;
                }
                iArr[i] = i3;
                iArr[i + 1] = i4;
            }
            return;
        }
        throw new AssertionError();
    }

    void a(byte[] bArr, int[] iArr, int i) {
        if (a || (bArr.length / 4) + i <= iArr.length) {
            iArr[i] = 0;
            int i2 = 24;
            for (byte b : bArr) {
                iArr[i] = iArr[i] | ((b & 255) << i2);
                if (i2 == 0) {
                    i++;
                    if (i < iArr.length) {
                        iArr[i] = 0;
                        i2 = 24;
                    } else {
                        i2 = 24;
                    }
                } else {
                    i2 -= 8;
                }
            }
            return;
        }
        throw new AssertionError();
    }

    byte[] a(int[] iArr, int i, int i2) {
        if (a || i2 <= (iArr.length - i) * 4) {
            byte[] bArr = new byte[i2];
            int i3 = 0;
            int i4 = i;
            for (int i5 = 0; i5 < i2; i5++) {
                bArr[i5] = (byte) ((iArr[i4] >> (24 - (i3 * 8))) & 255);
                i3++;
                if (i3 == 4) {
                    i4++;
                    i3 = 0;
                }
            }
            return bArr;
        }
        throw new AssertionError();
    }
}
