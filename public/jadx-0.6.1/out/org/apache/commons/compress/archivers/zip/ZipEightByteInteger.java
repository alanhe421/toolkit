package org.apache.commons.compress.archivers.zip;

import java.io.Serializable;
import java.math.BigInteger;

public final class ZipEightByteInteger implements Serializable {
    private static final int BYTE_1 = 1;
    private static final int BYTE_1_MASK = 65280;
    private static final int BYTE_1_SHIFT = 8;
    private static final int BYTE_2 = 2;
    private static final int BYTE_2_MASK = 16711680;
    private static final int BYTE_2_SHIFT = 16;
    private static final int BYTE_3 = 3;
    private static final long BYTE_3_MASK = 4278190080L;
    private static final int BYTE_3_SHIFT = 24;
    private static final int BYTE_4 = 4;
    private static final long BYTE_4_MASK = 1095216660480L;
    private static final int BYTE_4_SHIFT = 32;
    private static final int BYTE_5 = 5;
    private static final long BYTE_5_MASK = 280375465082880L;
    private static final int BYTE_5_SHIFT = 40;
    private static final int BYTE_6 = 6;
    private static final long BYTE_6_MASK = 71776119061217280L;
    private static final int BYTE_6_SHIFT = 48;
    private static final int BYTE_7 = 7;
    private static final long BYTE_7_MASK = 9151314442816847872L;
    private static final int BYTE_7_SHIFT = 56;
    private static final byte LEFTMOST_BIT = Byte.MIN_VALUE;
    private static final int LEFTMOST_BIT_SHIFT = 63;
    public static final ZipEightByteInteger ZERO = new ZipEightByteInteger(0);
    private static final long serialVersionUID = 1;
    private final BigInteger value;

    public ZipEightByteInteger(long j) {
        this(BigInteger.valueOf(j));
    }

    public ZipEightByteInteger(BigInteger bigInteger) {
        this.value = bigInteger;
    }

    public ZipEightByteInteger(byte[] bArr) {
        this(bArr, 0);
    }

    public ZipEightByteInteger(byte[] bArr, int i) {
        this.value = getValue(bArr, i);
    }

    public byte[] getBytes() {
        return getBytes(this.value);
    }

    public long getLongValue() {
        return this.value.longValue();
    }

    public BigInteger getValue() {
        return this.value;
    }

    public static byte[] getBytes(long j) {
        return getBytes(BigInteger.valueOf(j));
    }

    public static byte[] getBytes(BigInteger bigInteger) {
        r0 = new byte[8];
        long longValue = bigInteger.longValue();
        r0[0] = (byte) ((int) (255 & longValue));
        r0[1] = (byte) ((int) ((65280 & longValue) >> 8));
        r0[2] = (byte) ((int) ((16711680 & longValue) >> 16));
        r0[3] = (byte) ((int) ((BYTE_3_MASK & longValue) >> 24));
        r0[4] = (byte) ((int) ((BYTE_4_MASK & longValue) >> 32));
        r0[5] = (byte) ((int) ((BYTE_5_MASK & longValue) >> 40));
        r0[6] = (byte) ((int) ((BYTE_6_MASK & longValue) >> 48));
        r0[7] = (byte) ((int) ((longValue & BYTE_7_MASK) >> 56));
        if (bigInteger.testBit(63)) {
            r0[7] = (byte) (r0[7] | -128);
        }
        return r0;
    }

    public static long getLongValue(byte[] bArr, int i) {
        return getValue(bArr, i).longValue();
    }

    public static BigInteger getValue(byte[] bArr, int i) {
        BigInteger valueOf = BigInteger.valueOf(((((((((((long) bArr[i + 7]) << 56) & BYTE_7_MASK) + ((((long) bArr[i + 6]) << 48) & BYTE_6_MASK)) + ((((long) bArr[i + 5]) << 40) & BYTE_5_MASK)) + ((((long) bArr[i + 4]) << 32) & BYTE_4_MASK)) + ((((long) bArr[i + 3]) << 24) & BYTE_3_MASK)) + ((((long) bArr[i + 2]) << 16) & 16711680)) + ((((long) bArr[i + 1]) << 8) & 65280)) + (((long) bArr[i]) & 255));
        return (bArr[i + 7] & -128) == -128 ? valueOf.setBit(63) : valueOf;
    }

    public static long getLongValue(byte[] bArr) {
        return getLongValue(bArr, 0);
    }

    public static BigInteger getValue(byte[] bArr) {
        return getValue(bArr, 0);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ZipEightByteInteger)) {
            return false;
        }
        return this.value.equals(((ZipEightByteInteger) obj).getValue());
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public String toString() {
        return "ZipEightByteInteger value: " + this.value;
    }
}
