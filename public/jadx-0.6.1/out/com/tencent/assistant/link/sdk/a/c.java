package com.tencent.assistant.link.sdk.a;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/* compiled from: ProGuard */
public class c {
    private byte[] a;
    private byte[] b;
    private int c;
    private int d;
    private int e;
    private int f;
    private byte[] g;
    private boolean h = true;
    private int i;
    private Random j = new Random();

    public static long a(byte[] bArr, int i, int i2) {
        int i3;
        long j = 0;
        if (i2 > 8) {
            i3 = i + 8;
        } else {
            i3 = i + i2;
        }
        while (i < i3) {
            j = (j << 8) | ((long) (bArr[i] & 255));
            i++;
        }
        return (4294967295L & j) | (j >>> 32);
    }

    public byte[] a(byte[] bArr, int i, int i2, byte[] bArr2) {
        this.d = 0;
        this.c = 0;
        this.g = bArr2;
        byte[] bArr3 = new byte[(i + 8)];
        if (i2 % 8 != 0 || i2 < 16) {
            return null;
        }
        this.a = a(bArr, i);
        if (this.a == null) {
            return null;
        }
        this.e = this.a[0] & 7;
        int i3 = (i2 - this.e) - 10;
        if (i3 < 0) {
            return null;
        }
        int i4;
        for (i4 = i; i4 < bArr3.length; i4++) {
            bArr3[i4] = (byte) 0;
        }
        this.b = new byte[i3];
        this.d = 0;
        this.c = 8;
        this.i = 8;
        this.e++;
        this.f = 1;
        byte[] bArr4 = bArr3;
        while (this.f <= 2) {
            if (this.e < 8) {
                this.e++;
                this.f++;
            }
            if (this.e == 8) {
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
            if (this.e < 8) {
                this.b[i4] = (byte) (bArr6[(this.d + i) + this.e] ^ this.a[this.e]);
                i4++;
                i5--;
                this.e++;
            }
            if (this.e == 8) {
                this.d = this.c - 8;
                if (!b(bArr, i, i2)) {
                    return null;
                }
                bArr6 = bArr;
            }
        }
        this.f = 1;
        bArr4 = bArr6;
        while (this.f < 8) {
            if (this.e < 8) {
                if ((bArr4[(this.d + i) + this.e] ^ this.a[this.e]) != 0) {
                    return null;
                }
                this.e++;
            }
            if (this.e == 8) {
                this.d = this.c;
                if (!b(bArr, i, i2)) {
                    return null;
                }
                bArr4 = bArr;
            }
            this.f++;
        }
        return this.b;
    }

    public byte[] a(byte[] bArr, byte[] bArr2) {
        return a(bArr, 0, bArr.length, bArr2);
    }

    private byte[] a(byte[] bArr, int i) {
        int i2 = 16;
        try {
            long a = a(bArr, i, 4);
            long a2 = a(bArr, i + 4, 4);
            long a3 = a(this.g, 0, 4);
            long a4 = a(this.g, 4, 4);
            long a5 = a(this.g, 8, 4);
            long a6 = a(this.g, 12, 4);
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

    private byte[] a(byte[] bArr) {
        return a(bArr, 0);
    }

    private boolean b(byte[] bArr, int i, int i2) {
        this.e = 0;
        while (this.e < 8) {
            if (this.i + this.e >= i2) {
                return true;
            }
            byte[] bArr2 = this.a;
            int i3 = this.e;
            bArr2[i3] = (byte) (bArr2[i3] ^ bArr[(this.c + i) + this.e]);
            this.e++;
        }
        this.a = a(this.a);
        if (this.a == null) {
            return false;
        }
        this.i += 8;
        this.c += 8;
        this.e = 0;
        return true;
    }
}
