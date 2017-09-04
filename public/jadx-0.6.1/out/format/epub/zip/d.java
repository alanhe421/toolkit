package format.epub.zip;

import com.qq.reader.common.utils.c.b;
import format.epub.zip.f.a;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.ZipException;

/* compiled from: MyBufferedInputStream */
final class d extends InputStream {
    int a;
    int b;
    private final a c;
    private InputStream d;
    private final byte[] e;
    private int f;

    public d(a aVar, int i) throws IOException {
        this.c = aVar;
        this.d = aVar.a();
        this.e = new byte[i];
        this.a = 0;
        this.b = 0;
    }

    public d(a aVar) throws IOException {
        this(aVar, 1024);
    }

    public int available() throws IOException {
        return this.d.available() + this.a;
    }

    int a() {
        return this.f;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = i2 < this.a ? i2 : this.a;
        if (i3 > 0) {
            System.arraycopy(this.e, this.b, bArr, i, i3);
            i2 -= i3;
            this.a -= i3;
            this.b += i3;
            i += i3;
        }
        if (i2 > 0) {
            int read = this.d.read(bArr, i, i2);
            if (read >= 0) {
                i3 += read;
            }
        }
        this.f += i3;
        if (i3 > 0) {
            return i3;
        }
        return -1;
    }

    public int read() throws IOException {
        this.f++;
        if (this.a <= 0) {
            this.b = 0;
            this.a = this.d.read(this.e);
            if (this.a <= 0) {
                return -1;
            }
        }
        this.a--;
        byte[] bArr = this.e;
        int i = this.b;
        this.b = i + 1;
        return bArr[i] & 255;
    }

    int b() throws IOException {
        int read = read();
        int read2 = read();
        if (read2 >= 0) {
            return read + (read2 << 8);
        }
        throw new IOException("unexpected end of file at position " + a());
    }

    int c() throws IOException {
        int read = read();
        int read2 = read();
        int read3 = read();
        int read4 = read();
        if (read4 < 0) {
            throw new IOException("unexpected end of file at position " + a());
        }
        return read + ((read2 << 8) + ((read3 << 16) + (read4 << 24)));
    }

    String a(int i) throws IOException {
        byte[] bArr = new byte[i];
        read(bArr, 0, i);
        return a(bArr);
    }

    protected String a(byte[] bArr) throws ZipException {
        String a;
        int a2 = b.a(bArr);
        if (a2 != 0) {
            a = b.a(a2);
        } else {
            a = null;
        }
        if (a == null) {
            return new String(bArr);
        }
        try {
            return new String(bArr, a);
        } catch (UnsupportedEncodingException e) {
            throw new ZipException(e.getMessage());
        }
    }

    public void b(int i) throws IOException {
        this.f += i;
        if (this.a >= i) {
            this.a -= i;
            this.b += i;
            return;
        }
        int i2 = i - this.a;
        this.a = 0;
        if (i2 > this.d.available()) {
            throw new IOException("Not enough bytes to read");
        }
        i2 = (int) (((long) i2) - this.d.skip((long) i2));
        while (i2 > 0) {
            int read = this.d.read(this.e, 0, Math.min(i2, this.e.length));
            if (read <= 0) {
                throw new IOException("Not enough bytes to read");
            }
            i2 -= read;
        }
    }

    public void c(int i) throws IOException {
        if (i > 0) {
            this.d.close();
            this.d = this.c.a();
            this.a = 0;
            this.b = 0;
            int i2 = this.f - i;
            this.f = 0;
            b(i2);
        }
    }

    public void d(int i) throws IOException {
        if (this.f < i) {
            b(i - this.f);
        } else {
            c(this.f - i);
        }
    }

    public void close() throws IOException {
        this.d.close();
        this.a = 0;
    }
}
