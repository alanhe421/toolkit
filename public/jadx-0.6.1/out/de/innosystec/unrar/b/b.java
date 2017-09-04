package de.innosystec.unrar.b;

/* compiled from: Raw */
public class b {
    public static final int a(byte[] bArr, int i) {
        return ((((((0 | (bArr[i] & 255)) << 8) | (bArr[i + 1] & 255)) << 8) | (bArr[i + 2] & 255)) << 8) | (bArr[i + 3] & 255);
    }

    public static final short b(byte[] bArr, int i) {
        return (short) (((short) (((short) (0 + (bArr[i + 1] & 255))) << 8)) + (bArr[i] & 255));
    }

    public static final int c(byte[] bArr, int i) {
        return ((((bArr[i + 3] & 255) << 24) | ((bArr[i + 2] & 255) << 16)) | ((bArr[i + 1] & 255) << 8)) | (bArr[i] & 255);
    }

    public static final void a(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) ((i2 >>> 24) & 255);
        bArr[i + 1] = (byte) ((i2 >>> 16) & 255);
        bArr[i + 2] = (byte) ((i2 >>> 8) & 255);
        bArr[i + 3] = (byte) (i2 & 255);
    }

    public static final void a(byte[] bArr, int i, short s) {
        bArr[i + 1] = (byte) (s >>> 8);
        bArr[i] = (byte) (s & 255);
    }

    public static final void b(byte[] bArr, int i, int i2) {
        int i3 = ((bArr[i] & 255) + (i2 & 255)) >>> 8;
        bArr[i] = (byte) (bArr[i] + (i2 & 255));
        if (i3 > 0 || (65280 & i2) != 0) {
            int i4 = i + 1;
            bArr[i4] = (byte) ((i3 + ((i2 >>> 8) & 255)) + bArr[i4]);
        }
    }

    public static final void c(byte[] bArr, int i, int i2) {
        bArr[i + 3] = (byte) (i2 >>> 24);
        bArr[i + 2] = (byte) (i2 >>> 16);
        bArr[i + 1] = (byte) (i2 >>> 8);
        bArr[i] = (byte) (i2 & 255);
    }
}
