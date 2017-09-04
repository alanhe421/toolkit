package org.apache.commons.compress.archivers.zip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import java.util.zip.ZipException;

/* compiled from: ZipArchiveInputStream */
public class o extends org.apache.commons.compress.archivers.b {
    private static final byte[] r = ZipLong.LFH_SIG.getBytes();
    private static final byte[] s = ZipLong.CFH_SIG.getBytes();
    private static final byte[] t = ZipLong.DD_SIG.getBytes();
    private final p a;
    private final boolean b;
    private final InputStream c;
    private final Inflater d;
    private final CRC32 e;
    private final a f;
    private b g;
    private boolean h;
    private boolean i;
    private ByteArrayInputStream j;
    private boolean k;
    private final byte[] l;
    private final byte[] m;
    private final byte[] n;
    private final byte[] o;
    private final byte[] p;
    private int q;

    /* compiled from: ZipArchiveInputStream */
    private static final class a {
        private final byte[] a;
        private int b;
        private int c;

        private a() {
            this.a = new byte[512];
            this.b = 0;
            this.c = 0;
        }

        static /* synthetic */ int c(a aVar, int i) {
            int i2 = aVar.b + i;
            aVar.b = i2;
            return i2;
        }
    }

    /* compiled from: ZipArchiveInputStream */
    private static final class b {
        private final n a;
        private boolean b;
        private boolean c;
        private long d;
        private long e;

        static /* synthetic */ long a(b bVar, long j) {
            long j2 = bVar.e + j;
            bVar.e = j2;
            return j2;
        }

        static /* synthetic */ long b(b bVar, long j) {
            long j2 = bVar.d + j;
            bVar.d = j2;
            return j2;
        }
    }

    public o(InputStream inputStream) {
        this(inputStream, "UTF8");
    }

    public o(InputStream inputStream, String str) {
        this(inputStream, str, true);
    }

    public o(InputStream inputStream, String str, boolean z) {
        this(inputStream, str, z, false);
    }

    public o(InputStream inputStream, String str, boolean z, boolean z2) {
        this.d = new Inflater(true);
        this.e = new CRC32();
        this.f = new a();
        this.g = null;
        this.h = false;
        this.i = false;
        this.j = null;
        this.k = false;
        this.l = new byte[30];
        this.m = new byte[1024];
        this.n = new byte[2];
        this.o = new byte[4];
        this.p = new byte[16];
        this.q = 0;
        this.a = q.a(str);
        this.b = z;
        this.c = new PushbackInputStream(inputStream, this.f.a.length);
        this.k = z2;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.h) {
            throw new IOException("The stream is closed");
        } else if (this.d.finished() || this.g == null) {
            return -1;
        } else {
            if (i > bArr.length || i2 < 0 || i < 0 || bArr.length - i < i2) {
                throw new ArrayIndexOutOfBoundsException();
            }
            s.a(this.g.a);
            if (!a(this.g.a)) {
                throw new UnsupportedZipFeatureException(org.apache.commons.compress.archivers.zip.UnsupportedZipFeatureException.a.c, this.g.a);
            } else if (this.g.a.getMethod() == 0) {
                return a(bArr, i, i2);
            } else {
                return b(bArr, i, i2);
            }
        }
    }

    private int a(byte[] bArr, int i, int i2) throws IOException {
        if (this.g.b) {
            if (this.j == null) {
                d();
            }
            return this.j.read(bArr, i, i2);
        }
        long size = this.g.a.getSize();
        if (this.g.d >= size) {
            return -1;
        }
        if (this.f.b >= this.f.c) {
            this.f.b = 0;
            if (this.f.c = this.c.read(this.f.a) == -1) {
                return -1;
            }
            a(this.f.c);
            b.a(this.g, (long) this.f.c);
        }
        int min = Math.min(this.f.c - this.f.b, i2);
        if (size - this.g.d < ((long) min)) {
            min = (int) (size - this.g.d);
        }
        System.arraycopy(this.f.a, this.f.b, bArr, i, min);
        a.c(this.f, min);
        b.b(this.g, (long) min);
        this.e.update(bArr, i, min);
        return min;
    }

    private int b(byte[] bArr, int i, int i2) throws IOException {
        int c = c(bArr, i, i2);
        if (c == 0) {
            if (this.d.finished()) {
                return -1;
            }
            if (this.d.needsDictionary()) {
                throw new ZipException("This archive needs a preset dictionary which is not supported by Commons Compress.");
            } else if (this.f.c == -1) {
                throw new IOException("Truncated ZIP file");
            }
        }
        this.e.update(bArr, i, c);
        return c;
    }

    private int c(byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        do {
            if (this.d.needsInput()) {
                b();
                if (this.f.c <= 0) {
                    break;
                }
                b.a(this.g, (long) this.f.c);
            }
            try {
                i3 = this.d.inflate(bArr, i, i2);
                if (i3 != 0) {
                    break;
                }
            } catch (DataFormatException e) {
                throw new ZipException(e.getMessage());
            }
        } while (this.d.needsInput());
        return i3;
    }

    public void close() throws IOException {
        if (!this.h) {
            this.h = true;
            this.c.close();
            this.d.end();
        }
    }

    public long skip(long j) throws IOException {
        long j2 = 0;
        if (j >= 0) {
            while (j2 < j) {
                long j3 = j - j2;
                byte[] bArr = this.m;
                if (((long) this.m.length) <= j3) {
                    j3 = (long) this.m.length;
                }
                int read = read(bArr, 0, (int) j3);
                if (read == -1) {
                    return j2;
                }
                j2 += (long) read;
            }
            return j2;
        }
        throw new IllegalArgumentException();
    }

    private void b() throws IOException {
        if (this.h) {
            throw new IOException("The stream is closed");
        } else if (this.f.c = this.c.read(this.f.a) > 0) {
            a(this.f.c);
            this.d.setInput(this.f.a, 0, this.f.c);
        }
    }

    private void a(byte[] bArr) throws IOException {
        int i = 0;
        while (i != bArr.length) {
            int read = this.c.read(bArr, i, bArr.length - i);
            i += read;
            if (read == -1) {
                throw new EOFException();
            }
            a(read);
        }
    }

    private void c() throws IOException {
        a(this.o);
        ZipLong zipLong = new ZipLong(this.o);
        if (ZipLong.DD_SIG.equals(zipLong)) {
            a(this.o);
            zipLong = new ZipLong(this.o);
        }
        this.g.a.setCrc(zipLong.getValue());
        a(this.p);
        zipLong = new ZipLong(this.p, 8);
        if (zipLong.equals(ZipLong.CFH_SIG) || zipLong.equals(ZipLong.LFH_SIG)) {
            d(this.p, 8, 8);
            this.g.a.setCompressedSize(ZipLong.getValue(this.p));
            this.g.a.setSize(ZipLong.getValue(this.p, 4));
            return;
        }
        this.g.a.setCompressedSize(ZipEightByteInteger.getLongValue(this.p));
        this.g.a.setSize(ZipEightByteInteger.getLongValue(this.p, 8));
    }

    private boolean a(n nVar) {
        return this.k || !nVar.g().a() || nVar.getMethod() == 8;
    }

    private void d() throws IOException {
        int i = 0;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = this.g.c ? 20 : 12;
        boolean z = false;
        while (!z) {
            int read = this.c.read(this.f.a, i, 512 - i);
            if (read <= 0) {
                throw new IOException("Truncated ZIP file");
            } else if (read + i < 4) {
                i += read;
            } else {
                z = a(byteArrayOutputStream, i, read, i2);
                if (!z) {
                    i = b(byteArrayOutputStream, i, read, i2);
                }
            }
        }
        this.j = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    private boolean a(ByteArrayOutputStream byteArrayOutputStream, int i, int i2, int i3) throws IOException {
        int i4 = 0;
        int i5 = 0;
        boolean z = false;
        while (!z && i4 < i2 - 4) {
            if (this.f.a[i4] == r[0] && this.f.a[i4 + 1] == r[1]) {
                if ((this.f.a[i4 + 2] == r[2] && this.f.a[i4 + 3] == r[3]) || (this.f.a[i4] == s[2] && this.f.a[i4 + 3] == s[3])) {
                    i5 = ((i + i2) - i4) - i3;
                    z = true;
                } else if (this.f.a[i4 + 2] == t[2] && this.f.a[i4 + 3] == t[3]) {
                    i5 = (i + i2) - i4;
                    z = true;
                }
                if (z) {
                    d(this.f.a, (i + i2) - i5, i5);
                    byteArrayOutputStream.write(this.f.a, 0, i4);
                    c();
                }
            }
            i4++;
        }
        return z;
    }

    private int b(ByteArrayOutputStream byteArrayOutputStream, int i, int i2, int i3) {
        int i4 = ((i + i2) - i3) - 3;
        if (i4 <= 0) {
            return i + i2;
        }
        byteArrayOutputStream.write(this.f.a, 0, i4);
        System.arraycopy(this.f.a, i4, this.f.a, 0, i3 + 3);
        return i3 + 3;
    }

    private void d(byte[] bArr, int i, int i2) throws IOException {
        ((PushbackInputStream) this.c).unread(bArr, i, i2);
        b((long) i2);
    }
}
