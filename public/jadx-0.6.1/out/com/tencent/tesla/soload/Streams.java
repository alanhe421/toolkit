package com.tencent.tesla.soload;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class Streams {
    private Streams() {
    }

    public static int readSingleByte(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[1];
        if (inputStream.read(bArr, 0, 1) != -1) {
            return bArr[0] & 255;
        }
        return -1;
    }

    public static void readFully(InputStream inputStream, byte[] bArr, int i, int i2) throws IOException {
        if (i2 != 0) {
            if (inputStream == null) {
                throw new NullPointerException("in == null");
            } else if (bArr == null) {
                throw new NullPointerException("dst == null");
            } else if ((i | i2) >= 0 && i <= bArr.length && bArr.length - i >= i2) {
                while (i2 > 0) {
                    int read = inputStream.read(bArr, i, i2);
                    if (read < 0) {
                        throw new EOFException();
                    }
                    i += read;
                    i2 -= read;
                }
            }
        }
    }
}
