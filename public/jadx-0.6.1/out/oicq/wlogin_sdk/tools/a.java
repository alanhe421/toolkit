package oicq.wlogin_sdk.tools;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/* compiled from: CryptorImpl */
class a {
    private byte[] a;
    private byte[] b;
    private byte[] c;
    private int d;
    private int e;
    private int f;
    private int g;
    private byte[] h;
    private boolean i = true;
    private int j;
    private Random k = new Random();

    a() {
    }

    private static long a(byte[] bArr, int i, int i2) {
        int i3;
        long j = 0;
        if (i2 > 4) {
            i3 = i + 4;
        } else {
            i3 = i + i2;
        }
        while (i < i3) {
            j = (j << 8) | ((long) (bArr[i] & 255));
            i++;
        }
        return 4294967295L & j;
    }

    protected byte[] a(byte[] bArr, int i, int i2, byte[] bArr2) {
        this.e = 0;
        this.d = 0;
        this.h = bArr2;
        byte[] bArr3 = new byte[(i + 8)];
        if (i2 % 8 != 0 || i2 < 16) {
            return null;
        }
        this.b = a(bArr, i);
        this.f = this.b[0] & 7;
        int i3 = (i2 - this.f) - 10;
        if (i3 < 0) {
            return null;
        }
        int i4;
        for (i4 = i; i4 < bArr3.length; i4++) {
            bArr3[i4] = (byte) 0;
        }
        this.c = new byte[i3];
        this.e = 0;
        this.d = 8;
        this.j = 8;
        this.f++;
        this.g = 1;
        byte[] bArr4 = bArr3;
        while (this.g <= 2) {
            if (this.f < 8) {
                this.f++;
                this.g++;
            }
            if (this.f == 8) {
                if (!b(bArr, i, i2)) {
                    return null;
                }
                bArr4 = bArr;
            }
        }
        int i5 = i3;
        byte[] bArr5 = bArr4;
        i4 = 0;
        byte[] bArr6 = bArr5;
        while (i5 != 0) {
            if (this.f < 8) {
                this.c[i4] = (byte) (bArr6[(this.e + i) + this.f] ^ this.b[this.f]);
                i4++;
                i5--;
                this.f++;
            }
            if (this.f == 8) {
                this.e = this.d - 8;
                if (!b(bArr, i, i2)) {
                    return null;
                }
                bArr6 = bArr;
            }
        }
        this.g = 1;
        bArr4 = bArr6;
        while (this.g < 8) {
            if (this.f < 8) {
                if ((bArr4[(this.e + i) + this.f] ^ this.b[this.f]) != 0) {
                    return null;
                }
                this.f++;
            }
            if (this.f == 8) {
                this.e = this.d;
                if (!b(bArr, i, i2)) {
                    return null;
                }
                bArr4 = bArr;
            }
            this.g++;
        }
        return this.c;
    }

    protected byte[] a(byte[] bArr, byte[] bArr2) {
        return a(bArr, 0, bArr.length, bArr2);
    }

    private byte[] b(byte[] bArr, int i, int i2, byte[] bArr2) {
        int i3;
        int i4;
        this.a = new byte[8];
        this.b = new byte[8];
        this.f = 1;
        this.g = 0;
        this.e = 0;
        this.d = 0;
        this.h = bArr2;
        this.i = true;
        this.f = (i2 + 10) % 8;
        if (this.f != 0) {
            this.f = 8 - this.f;
        }
        this.c = new byte[((this.f + i2) + 10)];
        this.a[0] = (byte) ((b() & 248) | this.f);
        for (i3 = 1; i3 <= this.f; i3++) {
            this.a[i3] = (byte) (b() & 255);
        }
        this.f++;
        for (i3 = 0; i3 < 8; i3++) {
            this.b[i3] = (byte) 0;
        }
        this.g = 1;
        while (this.g <= 2) {
            if (this.f < 8) {
                byte[] bArr3 = this.a;
                i4 = this.f;
                this.f = i4 + 1;
                bArr3[i4] = (byte) (b() & 255);
                this.g++;
            }
            if (this.f == 8) {
                a();
            }
        }
        i4 = i;
        int i5 = i2;
        while (i5 > 0) {
            if (this.f < 8) {
                byte[] bArr4 = this.a;
                int i6 = this.f;
                this.f = i6 + 1;
                i3 = i4 + 1;
                bArr4[i6] = bArr[i4];
                i4 = i5 - 1;
            } else {
                i3 = i4;
                i4 = i5;
            }
            if (this.f == 8) {
                a();
                i5 = i4;
                i4 = i3;
            } else {
                i5 = i4;
                i4 = i3;
            }
        }
        this.g = 1;
        while (this.g <= 7) {
            if (this.f < 8) {
                bArr3 = this.a;
                int i7 = this.f;
                this.f = i7 + 1;
                bArr3[i7] = (byte) 0;
                this.g++;
            }
            if (this.f == 8) {
                a();
            }
        }
        return this.c;
    }

    protected byte[] b(byte[] bArr, byte[] bArr2) {
        return b(bArr, 0, bArr.length, bArr2);
    }

    private byte[] a(byte[] bArr) {
        int i = 16;
        try {
            long a = a(bArr, 0, 4);
            long a2 = a(bArr, 4, 4);
            long a3 = a(this.h, 0, 4);
            long a4 = a(this.h, 4, 4);
            long a5 = a(this.h, 8, 4);
            long a6 = a(this.h, 12, 4);
            long j = 0;
            long j2 = -1640531527 & 4294967295L;
            while (true) {
                int i2 = i - 1;
                if (i > 0) {
                    j = (j + j2) & 4294967295L;
                    a = (a + ((((a2 << 4) + a3) ^ (a2 + j)) ^ ((a2 >>> 5) + a4))) & 4294967295L;
                    a2 = (a2 + ((((a << 4) + a5) ^ (a + j)) ^ ((a >>> 5) + a6))) & 4294967295L;
                    i = i2;
                } else {
                    OutputStream byteArrayOutputStream = new ByteArrayOutputStream(8);
                    DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                    dataOutputStream.writeInt((int) a);
                    dataOutputStream.writeInt((int) a2);
                    dataOutputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException e) {
            return null;
        }
    }

    private byte[] a(byte[] bArr, int i) {
        int i2 = 16;
        try {
            long a = a(bArr, i, 4);
            long a2 = a(bArr, i + 4, 4);
            long a3 = a(this.h, 0, 4);
            long a4 = a(this.h, 4, 4);
            long a5 = a(this.h, 8, 4);
            long a6 = a(this.h, 12, 4);
            long j = -478700656 & 4294967295L;
            long j2 = -1640531527 & 4294967295L;
            while (true) {
                int i3 = i2 - 1;
                if (i2 > 0) {
                    a2 = (a2 - ((((a << 4) + a5) ^ (a + j)) ^ ((a >>> 5) + a6))) & 4294967295L;
                    a = (a - ((((a2 << 4) + a3) ^ (a2 + j)) ^ ((a2 >>> 5) + a4))) & 4294967295L;
                    j = (j - j2) & 4294967295L;
                    i2 = i3;
                } else {
                    OutputStream byteArrayOutputStream = new ByteArrayOutputStream(8);
                    DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                    dataOutputStream.writeInt((int) a);
                    dataOutputStream.writeInt((int) a2);
                    dataOutputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException e) {
            return null;
        }
    }

    private byte[] b(byte[] bArr) {
        return a(bArr, 0);
    }

    private void a() {
        this.f = 0;
        while (this.f < 8) {
            byte[] bArr;
            int i;
            if (this.i) {
                bArr = this.a;
                i = this.f;
                bArr[i] = (byte) (bArr[i] ^ this.b[this.f]);
            } else {
                bArr = this.a;
                i = this.f;
                bArr[i] = (byte) (bArr[i] ^ this.c[this.e + this.f]);
            }
            this.f++;
        }
        System.arraycopy(a(this.a), 0, this.c, this.d, 8);
        this.f = 0;
        while (this.f < 8) {
            bArr = this.c;
            i = this.d + this.f;
            bArr[i] = (byte) (bArr[i] ^ this.b[this.f]);
            this.f++;
        }
        System.arraycopy(this.a, 0, this.b, 0, 8);
        this.e = this.d;
        this.d += 8;
        this.f = 0;
        this.i = false;
    }

    private boolean b(byte[] bArr, int i, int i2) {
        this.f = 0;
        while (this.f < 8) {
            if (this.j + this.f >= i2) {
                return true;
            }
            byte[] bArr2 = this.b;
            int i3 = this.f;
            bArr2[i3] = (byte) (bArr2[i3] ^ bArr[(this.d + i) + this.f]);
            this.f++;
        }
        this.b = b(this.b);
        if (this.b == null) {
            return false;
        }
        this.j += 8;
        this.d += 8;
        this.f = 0;
        return true;
    }

    private int b() {
        return this.k.nextInt();
    }
}
