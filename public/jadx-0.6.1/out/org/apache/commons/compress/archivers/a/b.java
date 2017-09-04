package org.apache.commons.compress.archivers.a;

import java.io.IOException;
import java.io.InputStream;

/* compiled from: ArArchiveInputStream */
public class b extends org.apache.commons.compress.archivers.b {
    private static final int l = "#1/".length();
    private final InputStream a;
    private long b = 0;
    private boolean c;
    private a d = null;
    private byte[] e = null;
    private long f = -1;
    private final byte[] g = new byte[16];
    private final byte[] h = new byte[12];
    private final byte[] i = new byte[6];
    private final byte[] j = new byte[8];
    private final byte[] k = new byte[10];

    public b(InputStream inputStream) {
        this.a = inputStream;
        this.c = false;
    }

    public void close() throws IOException {
        if (!this.c) {
            this.c = true;
            this.a.close();
        }
        this.d = null;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.d != null) {
            long a = this.f + this.d.a();
            if (i2 <= 0 || a <= this.b) {
                return -1;
            }
            i2 = (int) Math.min((long) i2, a - this.b);
        }
        int read = this.a.read(bArr, i, i2);
        a(read);
        this.b += (long) (read > 0 ? read : 0);
        return read;
    }
}
