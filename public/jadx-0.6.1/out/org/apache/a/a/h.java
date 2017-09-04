package org.apache.a.a;

/* compiled from: ZipLong */
public final class h implements Cloneable {
    private long a;

    public long a() {
        return this.a;
    }

    public static byte[] a(long j) {
        return new byte[]{(byte) ((int) (255 & j)), (byte) ((int) ((65280 & j) >> 8)), (byte) ((int) ((16711680 & j) >> 16)), (byte) ((int) ((4278190080L & j) >> 24))};
    }

    public static long a(byte[] bArr, int i) {
        return (((((long) (bArr[i + 3] << 24)) & 4278190080L) + ((long) ((bArr[i + 2] << 16) & 16711680))) + ((long) ((bArr[i + 1] << 8) & 65280))) + ((long) (bArr[i] & 255));
    }

    public static long a(byte[] bArr) {
        return a(bArr, 0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof h) && this.a == ((h) obj).a()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (int) this.a;
    }
}
