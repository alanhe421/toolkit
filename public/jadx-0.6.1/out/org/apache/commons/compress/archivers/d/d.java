package org.apache.commons.compress.archivers.d;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

/* compiled from: TarBuffer */
class d {
    private InputStream a;
    private OutputStream b;
    private final int c;
    private final int d;
    private final int e;
    private final byte[] f;
    private int g;
    private int h;

    public d(InputStream inputStream, int i, int i2) {
        this(inputStream, null, i, i2);
    }

    private d(InputStream inputStream, OutputStream outputStream, int i, int i2) {
        this.a = inputStream;
        this.b = outputStream;
        this.c = i;
        this.d = i2;
        this.e = this.c / this.d;
        this.f = new byte[this.c];
        if (this.a != null) {
            this.g = -1;
            this.h = this.e;
            return;
        }
        this.g = 0;
        this.h = 0;
    }

    public int a() {
        return this.d;
    }

    public boolean a(byte[] bArr) {
        int a = a();
        for (int i = 0; i < a; i++) {
            if (bArr[i] != (byte) 0) {
                return false;
            }
        }
        return true;
    }

    public byte[] b() throws IOException {
        if (this.a == null) {
            if (this.b == null) {
                throw new IOException("input buffer is closed");
            }
            throw new IOException("reading from an output buffer");
        } else if (this.h >= this.e && !f()) {
            return null;
        } else {
            byte[] bArr = new byte[this.d];
            System.arraycopy(this.f, this.h * this.d, bArr, 0, this.d);
            this.h++;
            return bArr;
        }
    }

    private boolean f() throws IOException {
        if (this.a == null) {
            throw new IOException("reading from an output buffer");
        }
        this.h = 0;
        int i = this.c;
        int i2 = 0;
        while (i > 0) {
            long read = (long) this.a.read(this.f, i2, i);
            if (read != -1) {
                i2 = (int) (((long) i2) + read);
                i = (int) (((long) i) - read);
                if (read != ((long) this.c)) {
                }
            } else if (i2 == 0) {
                return false;
            } else {
                Arrays.fill(this.f, i2, i + i2, (byte) 0);
                this.g++;
                return true;
            }
        }
        this.g++;
        return true;
    }

    private void g() throws IOException {
        if (this.b == null) {
            throw new IOException("writing to an input buffer");
        }
        this.b.write(this.f, 0, this.c);
        this.b.flush();
        this.h = 0;
        this.g++;
        Arrays.fill(this.f, (byte) 0);
    }

    void c() throws IOException {
        if (this.b == null) {
            throw new IOException("writing to an input buffer");
        } else if (this.h > 0) {
            g();
        }
    }

    public void d() throws IOException {
        if (this.b != null) {
            c();
            if (this.b != System.out && this.b != System.err) {
                this.b.close();
                this.b = null;
            }
        } else if (this.a != null) {
            if (this.a != System.in) {
                this.a.close();
            }
            this.a = null;
        }
    }

    void e() throws IOException {
        boolean markSupported = this.a.markSupported();
        if (markSupported) {
            this.a.mark(this.d);
        }
        try {
            if ((!a(b()) ? 1 : null) == null) {
            }
        } finally {
            if (markSupported) {
                this.a.reset();
            }
        }
    }
}
