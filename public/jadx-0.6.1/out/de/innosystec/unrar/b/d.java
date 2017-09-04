package de.innosystec.unrar.b;

import java.io.IOException;
import java.io.InputStream;

/* compiled from: ReadOnlyAccessInputStream */
public class d extends InputStream {
    private a a;
    private long b;
    private final long c;
    private final long d;

    public d(a aVar, long j, long j2) throws IOException {
        this.a = aVar;
        this.c = j;
        this.b = j;
        this.d = j2;
        aVar.a(this.b);
    }

    public int read() throws IOException {
        if (this.b == this.d) {
            return -1;
        }
        int read = this.a.read();
        this.b++;
        return read;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        if (this.b == this.d) {
            return -1;
        }
        int read = this.a.read(bArr, i, (int) Math.min((long) i2, this.d - this.b));
        this.b += (long) read;
        return read;
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }
}
