package org.apache.commons.compress.archivers.dump;

/* compiled from: DumpArchiveUtil */
class d {
    public static int a(byte[] bArr) {
        int i = 0;
        int i2 = 0;
        while (i < 256) {
            i2 += b(bArr, i * 4);
            i++;
        }
        return 84446 - (i2 - b(bArr, 28));
    }

    public static final boolean b(byte[] bArr) {
        if (b(bArr, 24) == 60012 && b(bArr, 28) == a(bArr)) {
            return true;
        }
        return false;
    }

    public static final long a(byte[] bArr, int i) {
        return (((((((0 + (((long) bArr[i + 7]) << 56)) + ((((long) bArr[i + 6]) << 48) & 71776119061217280L)) + ((((long) bArr[i + 5]) << 40) & 280375465082880L)) + ((((long) bArr[i + 4]) << 32) & 1095216660480L)) + ((((long) bArr[i + 3]) << 24) & 4278190080L)) + ((((long) bArr[i + 2]) << 16) & 16711680)) + ((((long) bArr[i + 1]) << 8) & 65280)) + (((long) bArr[i]) & 255);
    }

    public static final int b(byte[] bArr, int i) {
        return (((bArr[i + 3] << 24) + ((bArr[i + 2] << 16) & 16711680)) + ((bArr[i + 1] << 8) & 65280)) + (bArr[i] & 255);
    }

    public static final int c(byte[] bArr, int i) {
        return (0 + ((bArr[i + 1] << 8) & 65280)) + (bArr[i] & 255);
    }
}
