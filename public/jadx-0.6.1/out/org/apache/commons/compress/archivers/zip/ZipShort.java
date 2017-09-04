package org.apache.commons.compress.archivers.zip;

import java.io.Serializable;

public final class ZipShort implements Serializable, Cloneable {
    private static final int BYTE_1_MASK = 65280;
    private static final int BYTE_1_SHIFT = 8;
    private static final long serialVersionUID = 1;
    private final int value;

    public ZipShort(int i) {
        this.value = i;
    }

    public ZipShort(byte[] bArr) {
        this(bArr, 0);
    }

    public ZipShort(byte[] bArr, int i) {
        this.value = getValue(bArr, i);
    }

    public byte[] getBytes() {
        return new byte[]{(byte) (this.value & 255), (byte) ((this.value & BYTE_1_MASK) >> 8)};
    }

    public int getValue() {
        return this.value;
    }

    public static byte[] getBytes(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((BYTE_1_MASK & i) >> 8)};
    }

    public static int getValue(byte[] bArr, int i) {
        return ((bArr[i + 1] << 8) & BYTE_1_MASK) + (bArr[i] & 255);
    }

    public static int getValue(byte[] bArr) {
        return getValue(bArr, 0);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof ZipShort) && this.value == ((ZipShort) obj).getValue()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.value;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        return "ZipShort value: " + this.value;
    }
}
