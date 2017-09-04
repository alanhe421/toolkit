package com.tencent.android.tpush.service.channel.security;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: ProGuard */
public class d extends FilterInputStream {
    private static final char[] a = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static final int[] b = new int[128];
    private int c;
    private int d;

    static {
        for (int i = 0; i < 64; i++) {
            b[a[i]] = i;
        }
    }

    public d(InputStream inputStream) {
        super(inputStream);
    }

    public int read() {
        int read;
        do {
            read = this.in.read();
            if (read == -1) {
                return -1;
            }
        } while (Character.isWhitespace((char) read));
        this.c++;
        if (read == 61) {
            return -1;
        }
        read = b[read];
        int i = (this.c - 1) % 4;
        if (i == 0) {
            this.d = read & 63;
            return read();
        } else if (i == 1) {
            r0 = ((this.d << 2) + (read >> 4)) & 255;
            this.d = read & 15;
            return r0;
        } else if (i == 2) {
            r0 = ((this.d << 4) + (read >> 2)) & 255;
            this.d = read & 3;
            return r0;
        } else if (i == 3) {
            return ((this.d << 6) + read) & 255;
        } else {
            return -1;
        }
    }

    public int read(byte[] bArr, int i, int i2) {
        if (bArr.length < (i2 + i) - 1) {
            throw new IOException("The input buffer is too small: " + i2 + " bytes requested starting at offset " + i + " while the buffer " + " is only " + bArr.length + " bytes long.");
        }
        int i3 = 0;
        while (i3 < i2) {
            int read = read();
            if (read == -1 && i3 == 0) {
                return -1;
            }
            if (read == -1) {
                break;
            }
            bArr[i + i3] = (byte) read;
            i3++;
        }
        return i3;
    }

    public static byte[] a(String str) {
        byte[] bArr = new byte[0];
        try {
            bArr = str.getBytes("UTF-8");
        } catch (Exception e) {
        }
        d dVar = new d(new ByteArrayInputStream(bArr));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((int) (((double) bArr.length) * 0.67d));
        try {
            bArr = new byte[4096];
            while (true) {
                int read = dVar.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    dVar.close();
                    byteArrayOutputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException e2) {
            return null;
        }
    }
}
