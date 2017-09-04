package com.tencent.android.tpush.stat.a;

import android.util.Base64;

/* compiled from: ProGuard */
public class d {
    static byte[] a() {
        return Base64.decode("MDNhOTc2NTExZTJjYmUzYTdmMjY4MDhmYjdhZjNjMDU=", 0);
    }

    public static byte[] a(byte[] bArr) {
        return a(bArr, a());
    }

    public static byte[] b(byte[] bArr) {
        return b(bArr, a());
    }

    static byte[] a(byte[] bArr, byte[] bArr2) {
        int i = 0;
        int[] iArr = new int[256];
        int[] iArr2 = new int[256];
        int length = bArr2.length;
        if (length < 1 || length > 256) {
            throw new IllegalArgumentException("key must be between 1 and 256 bytes");
        }
        int i2;
        for (i2 = 0; i2 < 256; i2++) {
            iArr[i2] = i2;
            iArr2[i2] = bArr2[i2 % length];
        }
        length = 0;
        for (i2 = 0; i2 < 256; i2++) {
            length = ((length + iArr[i2]) + iArr2[i2]) & 255;
            int i3 = iArr[i2];
            iArr[i2] = iArr[length];
            iArr[length] = i3;
        }
        byte[] bArr3 = new byte[bArr.length];
        i2 = 0;
        length = 0;
        while (i < bArr.length) {
            length = (length + 1) & 255;
            i2 = (i2 + iArr[length]) & 255;
            i3 = iArr[length];
            iArr[length] = iArr[i2];
            iArr[i2] = i3;
            bArr3[i] = (byte) (iArr[(iArr[length] + iArr[i2]) & 255] ^ bArr[i]);
            i++;
        }
        return bArr3;
    }

    static byte[] b(byte[] bArr, byte[] bArr2) {
        return a(bArr, bArr2);
    }
}
