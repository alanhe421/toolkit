package com.tencent.tesla.soload;

import java.nio.ByteOrder;

public class Memory {
    private Memory() {
    }

    public static int peekInt(byte[] bArr, int i, ByteOrder byteOrder) {
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            int i2 = i + 1;
            int i3 = i2 + 1;
            return ((((bArr[i2] & 255) << 16) | ((bArr[i] & 255) << 24)) | ((bArr[i3] & 255) << 8)) | ((bArr[i3 + 1] & 255) << 0);
        }
        i2 = i + 1;
        i3 = i2 + 1;
        return ((((bArr[i2] & 255) << 8) | ((bArr[i] & 255) << 0)) | ((bArr[i3] & 255) << 16)) | ((bArr[i3 + 1] & 255) << 24);
    }

    public static short peekShort(byte[] bArr, int i, ByteOrder byteOrder) {
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            return (short) ((bArr[i] << 8) | (bArr[i + 1] & 255));
        }
        return (short) ((bArr[i + 1] << 8) | (bArr[i] & 255));
    }
}
