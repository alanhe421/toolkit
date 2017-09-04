package format.epub.common.utils;

import java.io.IOException;
import java.io.InputStream;

/* compiled from: ZLInputStreamWithOffset */
public class l extends InputStream {
    private final InputStream a;
    private int b = 0;

    public l(InputStream inputStream) {
        this.a = inputStream;
    }

    public int available() throws IOException {
        return this.a.available();
    }

    public long skip(long j) throws IOException {
        long skip = this.a.skip(j);
        if (skip > 0) {
            this.b += (int) skip;
        }
        while (skip < j && read() != -1) {
            skip++;
        }
        return skip;
    }

    public int read() throws IOException {
        int read = this.a.read();
        if (read != -1) {
            this.b++;
        }
        return read;
    }

    public void close() throws IOException {
        this.b = 0;
        this.a.close();
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.a.read(bArr, i, i2);
        if (read > 0) {
            this.b += read;
        }
        return read;
    }

    public int read(byte[] bArr) throws IOException {
        int read = this.a.read(bArr);
        if (read > 0) {
            this.b += read;
        }
        return read;
    }

    public void reset() throws IOException {
        this.b = 0;
        this.a.reset();
    }

    public int a() {
        return this.b;
    }
}
