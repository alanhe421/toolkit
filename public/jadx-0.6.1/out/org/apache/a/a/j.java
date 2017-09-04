package org.apache.a.a;

/* compiled from: ZipShort */
public final class j implements Cloneable {
    private int a;

    public j(int i) {
        this.a = i;
    }

    public j(byte[] bArr, int i) {
        this.a = a(bArr, i);
    }

    public byte[] a() {
        return new byte[]{(byte) (this.a & 255), (byte) ((this.a & 65280) >> 8)};
    }

    public int b() {
        return this.a;
    }

    public static byte[] a(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((65280 & i) >> 8)};
    }

    public static int a(byte[] bArr, int i) {
        return ((bArr[i + 1] << 8) & 65280) + (bArr[i] & 255);
    }

    public static int a(byte[] bArr) {
        return a(bArr, 0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof j) && this.a == ((j) obj).b()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.a;
    }
}
