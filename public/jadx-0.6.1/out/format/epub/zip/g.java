package format.epub.zip;

import java.io.IOException;
import java.io.InputStream;

/* compiled from: ZipInputStream */
class g extends InputStream {
    private final f a;
    private final d b;
    private final a c;
    private boolean d;

    public g(f fVar, c cVar) throws IOException {
        this.a = fVar;
        this.b = fVar.b();
        this.b.d(cVar.m);
        if (cVar.p) {
            cVar.a();
        }
        this.c = a.a(this.b, cVar);
    }

    public int available() throws IOException {
        return this.c.b();
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null) {
            throw new NullPointerException();
        } else if (i < 0 || i > bArr.length || i2 < 0 || i + i2 > bArr.length || i + i2 < 0) {
            throw new IndexOutOfBoundsException();
        } else if (i2 == 0) {
            return 0;
        } else {
            return this.c.a(bArr, i, i2);
        }
    }

    public int read() throws IOException {
        return this.c.a();
    }

    public void close() throws IOException {
        if (!this.d) {
            this.d = true;
            this.a.a(this.b);
            a.a(this.c);
        }
    }

    protected void finalize() throws Throwable {
        try {
            close();
        } finally {
            super.finalize();
        }
    }
}
