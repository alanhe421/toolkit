package org.apache.a.a;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import java.util.zip.ZipException;

/* compiled from: ZipOutputStream */
public class i extends FilterOutputStream {
    protected static final byte[] c = h.a(67324752);
    protected static final byte[] d = h.a(134695760);
    protected static final byte[] e = h.a(33639248);
    protected static final byte[] f = h.a(101010256);
    private static final byte[] p = new byte[]{(byte) 0, (byte) 0};
    private static final byte[] q = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0};
    private static final byte[] u = h.a(8448);
    protected Deflater a;
    protected byte[] b;
    private e g;
    private String h;
    private Vector i;
    private CRC32 j;
    private long k;
    private long l;
    private long m;
    private long n;
    private long o;
    private Hashtable r;
    private String s;
    private RandomAccessFile t;

    public void a() throws IOException {
        b();
        this.n = this.k;
        int size = this.i.size();
        for (int i = 0; i < size; i++) {
            b((e) this.i.elementAt(i));
        }
        this.o = this.k - this.n;
        d();
        this.r.clear();
        this.i.removeAllElements();
    }

    public void b() throws IOException {
        if (this.g != null) {
            long value = this.j.getValue();
            this.j.reset();
            if (this.g.getMethod() == 8) {
                this.a.finish();
                while (!this.a.finished()) {
                    c();
                }
                this.g.setSize(a(this.a.getTotalIn()));
                this.g.setCompressedSize(a(this.a.getTotalOut()));
                this.g.setCrc(value);
                this.a.reset();
                this.k += this.g.getCompressedSize();
            } else if (this.t != null) {
                long j = this.k - this.l;
                this.g.setSize(j);
                this.g.setCompressedSize(j);
                this.g.setCrc(value);
            } else if (this.g.getCrc() != value) {
                throw new ZipException(new StringBuffer().append("bad CRC checksum for entry ").append(this.g.getName()).append(": ").append(Long.toHexString(this.g.getCrc())).append(" instead of ").append(Long.toHexString(value)).toString());
            } else if (this.g.getSize() != this.k - this.l) {
                throw new ZipException(new StringBuffer().append("bad size for entry ").append(this.g.getName()).append(": ").append(this.g.getSize()).append(" instead of ").append(this.k - this.l).toString());
            }
            if (this.t != null) {
                value = this.t.getFilePointer();
                this.t.seek(this.m);
                a(h.a(this.g.getCrc()));
                a(h.a(this.g.getCompressedSize()));
                a(h.a(this.g.getSize()));
                this.t.seek(value);
            }
            a(this.g);
            this.g = null;
        }
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (this.g.getMethod() != 8) {
            a(bArr, i, i2);
            this.k += (long) i2;
        } else if (i2 > 0 && !this.a.finished()) {
            this.a.setInput(bArr, i, i2);
            while (!this.a.needsInput()) {
                c();
            }
        }
        this.j.update(bArr, i, i2);
    }

    public void write(int i) throws IOException {
        write(new byte[]{(byte) (i & 255)}, 0, 1);
    }

    public void close() throws IOException {
        a();
        if (this.t != null) {
            this.t.close();
        }
        if (this.out != null) {
            this.out.close();
        }
    }

    public void flush() throws IOException {
        if (this.out != null) {
            this.out.flush();
        }
    }

    protected final void c() throws IOException {
        int deflate = this.a.deflate(this.b, 0, this.b.length);
        if (deflate > 0) {
            a(this.b, 0, deflate);
        }
    }

    protected void a(e eVar) throws IOException {
        if (eVar.getMethod() == 8 && this.t == null) {
            a(d);
            a(h.a(this.g.getCrc()));
            a(h.a(this.g.getCompressedSize()));
            a(h.a(this.g.getSize()));
            this.k += 16;
        }
    }

    protected void b(e eVar) throws IOException {
        a(e);
        this.k += 4;
        a(j.a((eVar.c() << 8) | 20));
        this.k += 2;
        if (eVar.getMethod() == 8 && this.t == null) {
            a(j.a(20));
            a(j.a(8));
        } else {
            a(j.a(10));
            a(p);
        }
        this.k += 4;
        a(j.a(eVar.getMethod()));
        this.k += 2;
        a(a(eVar.getTime()));
        this.k += 4;
        a(h.a(eVar.getCrc()));
        a(h.a(eVar.getCompressedSize()));
        a(h.a(eVar.getSize()));
        this.k += 12;
        byte[] a = a(eVar.getName());
        a(j.a(a.length));
        this.k += 2;
        byte[] f = eVar.f();
        a(j.a(f.length));
        this.k += 2;
        String comment = eVar.getComment();
        if (comment == null) {
            comment = "";
        }
        byte[] a2 = a(comment);
        a(j.a(a2.length));
        this.k += 2;
        a(p);
        this.k += 2;
        a(j.a(eVar.a()));
        this.k += 2;
        a(h.a(eVar.b()));
        this.k += 4;
        a((byte[]) this.r.get(eVar));
        this.k += 4;
        a(a);
        this.k = ((long) a.length) + this.k;
        a(f);
        this.k += (long) f.length;
        a(a2);
        this.k += (long) a2.length;
    }

    protected void d() throws IOException {
        a(f);
        a(p);
        a(p);
        byte[] a = j.a(this.i.size());
        a(a);
        a(a);
        a(h.a(this.o));
        a(h.a(this.n));
        a = a(this.h);
        a(j.a(a.length));
        a(a);
    }

    protected static byte[] a(long j) {
        Date date = new Date(j);
        int year = date.getYear() + 1900;
        if (year < 1980) {
            return u;
        }
        return h.a((long) ((date.getSeconds() >> 1) | ((((((year - 1980) << 25) | ((date.getMonth() + 1) << 21)) | (date.getDate() << 16)) | (date.getHours() << 11)) | (date.getMinutes() << 5))));
    }

    protected byte[] a(String str) throws ZipException {
        if (this.s == null) {
            return str.getBytes();
        }
        try {
            return str.getBytes(this.s);
        } catch (UnsupportedEncodingException e) {
            throw new ZipException(e.getMessage());
        }
    }

    protected final void a(byte[] bArr) throws IOException {
        a(bArr, 0, bArr.length);
    }

    protected final void a(byte[] bArr, int i, int i2) throws IOException {
        if (this.t != null) {
            this.t.write(bArr, i, i2);
        } else {
            this.out.write(bArr, i, i2);
        }
    }

    protected static long a(int i) {
        if (i < 0) {
            return 4294967296L + ((long) i);
        }
        return (long) i;
    }
}
