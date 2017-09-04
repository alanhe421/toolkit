package com.tencent.android.tpush.service.channel.c;

import com.tencent.android.tpush.service.channel.exception.IORefusedException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: ProGuard */
public class e {
    static final /* synthetic */ boolean a = (!e.class.desiredAssertionStatus());

    public static boolean a(InputStream inputStream, int i) {
        return inputStream.available() >= i;
    }

    public static short a(InputStream inputStream) {
        if (a(inputStream, 1)) {
            byte[] bArr = new byte[1];
            if (inputStream.read(bArr) != -1) {
                return (short) (bArr[0] & 255);
            }
            throw new IOException("the end of stream has been reached!");
        }
        throw new IORefusedException("inputstream cannot read 1 byte");
    }

    public static long b(InputStream inputStream) {
        if (a(inputStream, 4)) {
            byte[] bArr = new byte[4];
            if (inputStream.read(bArr) == -1) {
                throw new IOException("the end of stream has been reached!");
            }
            return (long) (((bArr[0] & 255) << 24) | (((bArr[3] & 255) | ((bArr[2] & 255) << 8)) | ((bArr[1] & 255) << 16)));
        }
        throw new IORefusedException("inputstream cannot read 4 byte");
    }

    public static int c(InputStream inputStream) {
        if (a(inputStream, 4)) {
            byte[] bArr = new byte[4];
            if (inputStream.read(bArr) == -1) {
                throw new IOException("the end of stream has been reached!");
            }
            return ((bArr[0] & 255) << 24) | (((bArr[3] & 255) | ((bArr[2] & 255) << 8)) | ((bArr[1] & 255) << 16));
        }
        throw new IORefusedException("inputstream cannot read 4 byte");
    }

    public static int a(InputStream inputStream, byte[] bArr, int i) {
        if (inputStream.available() == 0 && bArr.length - i > 0) {
            return 0;
        }
        int length = bArr.length - i < inputStream.available() ? bArr.length - i : inputStream.available();
        if (length <= 0) {
            return length;
        }
        length = inputStream.read(bArr, i, length);
        if (length != -1) {
            return length;
        }
        throw new IOException("the end of stream has been reached!");
    }

    public static int a(OutputStream outputStream, int i) {
        if (a || (i >= 0 && ((long) i) <= 255)) {
            outputStream.write((byte) (i & 255));
            return 1;
        }
        throw new AssertionError();
    }

    public static int a(OutputStream outputStream, long j) {
        if (a || (j >= 0 && j <= 4294967295L)) {
            outputStream.write(new byte[]{(byte) ((int) ((j >> 24) & 255)), (byte) ((int) ((j >> 16) & 255)), (byte) ((int) ((j >> 8) & 255)), (byte) ((int) (j & 255))});
            return 4;
        }
        throw new AssertionError();
    }

    public static int b(OutputStream outputStream, int i) {
        outputStream.write(new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)});
        return 4;
    }

    public static int a(OutputStream outputStream, byte[] bArr) {
        int i = 0;
        int i2 = 0;
        while (i < bArr.length) {
            outputStream.write(bArr, i, 1);
            i2++;
            i++;
        }
        return i2;
    }
}
