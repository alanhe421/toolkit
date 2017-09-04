package org.apache.commons.compress.archivers.b;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: CpioArchiveInputStream */
public class b extends org.apache.commons.compress.archivers.b {
    private boolean a;
    private a b;
    private long c;
    private boolean d;
    private final byte[] e;
    private long f;
    private final InputStream g;
    private final byte[] h;
    private final byte[] i;
    private final byte[] j;
    private final int k;

    public b(InputStream inputStream) {
        this(inputStream, 512);
    }

    public b(InputStream inputStream, int i) {
        this.a = false;
        this.c = 0;
        this.d = false;
        this.e = new byte[4096];
        this.f = 0;
        this.h = new byte[2];
        this.i = new byte[4];
        this.j = new byte[6];
        this.g = inputStream;
        this.k = i;
    }

    public int available() throws IOException {
        b();
        if (this.d) {
            return 0;
        }
        return 1;
    }

    public void close() throws IOException {
        if (!this.a) {
            this.g.close();
            this.a = true;
        }
    }

    private void b() throws IOException {
        if (this.a) {
            throw new IOException("Stream closed");
        }
    }

    private void b(int i) throws IOException {
        if (i > 0) {
            a(this.i, 0, i);
        }
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        b();
        if (i < 0 || i2 < 0 || i > bArr.length - i2) {
            throw new IndexOutOfBoundsException();
        } else if (i2 == 0) {
            return 0;
        } else {
            if (this.b == null || this.d) {
                return -1;
            }
            if (this.c == this.b.b()) {
                b(this.b.d());
                this.d = true;
                if (this.b.c() != (short) 2 || this.f == this.b.a()) {
                    return -1;
                }
                throw new IOException("CRC Error. Occured at byte: " + a());
            }
            int min = (int) Math.min((long) i2, this.b.b() - this.c);
            if (min < 0) {
                return -1;
            }
            int a = a(bArr, i, min);
            if (this.b.c() == (short) 2) {
                while (i3 < a) {
                    this.f += (long) (bArr[i3] & 255);
                    i3++;
                }
            }
            this.c += (long) a;
            return a;
        }
    }

    private final int a(byte[] bArr, int i, int i2) throws IOException {
        if (i2 < 0) {
            throw new IndexOutOfBoundsException();
        }
        int i3 = 0;
        while (i3 < i2) {
            int read = this.g.read(bArr, i + i3, i2 - i3);
            a(read);
            if (read < 0) {
                throw new EOFException();
            }
            i3 += read;
        }
        return i3;
    }

    public long skip(long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("negative skip length");
        }
        b();
        int min = (int) Math.min(j, 2147483647L);
        int i = 0;
        while (i < min) {
            int i2 = min - i;
            if (i2 > this.e.length) {
                i2 = this.e.length;
            }
            i2 = read(this.e, 0, i2);
            if (i2 == -1) {
                this.d = true;
                break;
            }
            i = i2 + i;
        }
        return (long) i;
    }
}
