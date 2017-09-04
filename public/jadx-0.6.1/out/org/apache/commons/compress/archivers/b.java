package org.apache.commons.compress.archivers;

import java.io.IOException;
import java.io.InputStream;

/* compiled from: ArchiveInputStream */
public abstract class b extends InputStream {
    private final byte[] a = new byte[1];
    private long b = 0;

    public int read() throws IOException {
        if (read(this.a, 0, 1) == -1) {
            return -1;
        }
        return this.a[0] & 255;
    }

    protected void a(int i) {
        a((long) i);
    }

    protected void a(long j) {
        if (j != -1) {
            this.b += j;
        }
    }

    protected void b(long j) {
        this.b -= j;
    }

    public long a() {
        return this.b;
    }
}
