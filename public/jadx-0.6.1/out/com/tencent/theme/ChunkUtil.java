package com.tencent.theme;

import java.io.IOException;
import java.nio.ByteBuffer;

class ChunkUtil {
    ChunkUtil() {
    }

    static final void readCheckType(ByteBuffer byteBuffer, int i) throws IOException {
        int i2 = byteBuffer.getInt();
        if (i2 != i) {
            throw new IOException("Expected chunk of type 0x" + Integer.toHexString(i) + ", read 0x" + Integer.toHexString(i2) + ".");
        }
    }

    static final void skip(ByteBuffer byteBuffer, int i) {
        byteBuffer.position(byteBuffer.position() + i);
    }

    static final int[] readIntArray(ByteBuffer byteBuffer, int i) throws IOException {
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = byteBuffer.getInt();
        }
        return iArr;
    }

    static final byte[] readByteArray(ByteBuffer byteBuffer, int i) {
        byte[] bArr = new byte[i];
        byteBuffer.get(bArr);
        return bArr;
    }
}
