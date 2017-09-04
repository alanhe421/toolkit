package format.epub.zip;

import java.io.IOException;

/* compiled from: NoCompressionDecompressor */
public class e extends a {
    private final c a;
    private final d b;
    private int c;

    public e(d dVar, c cVar) {
        this.a = cVar;
        this.b = dVar;
    }

    public int a(byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        while (i3 < i2) {
            int a = a();
            if (a == -1) {
                break;
            }
            if (bArr != null) {
                bArr[i + i3] = (byte) a;
            }
            i3++;
        }
        if (i3 > 0) {
            return i3;
        }
        return -1;
    }

    public int a() throws IOException {
        if (this.c >= this.a.h) {
            return -1;
        }
        this.c++;
        int read = this.b.read();
        if (this.a.p) {
            return b.a(this.a.o, (byte) read) & 255;
        }
        return read;
    }

    public int b() throws IOException {
        return this.a.i - this.c;
    }
}
