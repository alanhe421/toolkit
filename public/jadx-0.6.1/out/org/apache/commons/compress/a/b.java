package org.apache.commons.compress.a;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: IOUtils */
public final class b {
    public static long a(InputStream inputStream, OutputStream outputStream) throws IOException {
        return a(inputStream, outputStream, 8024);
    }

    public static long a(InputStream inputStream, OutputStream outputStream, int i) throws IOException {
        byte[] bArr = new byte[i];
        long j = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (-1 == read) {
                return j;
            }
            outputStream.write(bArr, 0, read);
            j += (long) read;
        }
    }
}
