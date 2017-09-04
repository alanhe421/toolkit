package org.jsoup.helper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.regex.Pattern;

/* compiled from: DataUtil */
public final class a {
    private static final Pattern a = Pattern.compile("(?i)\\bcharset=\\s*(?:\"|')?([^\\s,;\"']*)");
    private static final char[] b = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public static ByteBuffer a(InputStream inputStream, int i) throws IOException {
        int i2;
        int i3 = 1;
        int i4 = 60000;
        c.a(i >= 0, "maxSize must be 0 (unlimited) or larger");
        if (i <= 0) {
            i3 = 0;
        }
        if (i3 == 0 || i >= 60000) {
            i2 = 60000;
        } else {
            i2 = i;
        }
        byte[] bArr = new byte[i2];
        if (i3 != 0) {
            i4 = i;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i4);
        while (!Thread.interrupted()) {
            i4 = inputStream.read(bArr);
            if (i4 == -1) {
                break;
            }
            if (i3 != 0) {
                if (i4 > i) {
                    byteArrayOutputStream.write(bArr, 0, i);
                    break;
                }
                i -= i4;
            }
            byteArrayOutputStream.write(bArr, 0, i4);
        }
        return ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
    }
}
