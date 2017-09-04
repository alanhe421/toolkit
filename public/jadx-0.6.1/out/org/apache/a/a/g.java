package org.apache.a.a;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipException;

/* compiled from: ZipFile */
public class g {
    private Hashtable a;
    private Hashtable b;
    private String c;
    private RandomAccessFile d;

    /* compiled from: ZipFile */
    static class AnonymousClass1 {
    }

    /* compiled from: ZipFile */
    private class a extends InputStream {
        private long a;
        private long b;
        private boolean c = false;
        private final g d;

        a(g gVar, long j, long j2) {
            this.d = gVar;
            this.a = j2;
            this.b = j;
        }

        public int read() throws IOException {
            long j = this.a;
            this.a = j - 1;
            if (j > 0) {
                int read;
                synchronized (g.a(this.d)) {
                    RandomAccessFile a = g.a(this.d);
                    j = this.b;
                    this.b = j + 1;
                    a.seek(j);
                    read = g.a(this.d).read();
                }
                return read;
            } else if (!this.c) {
                return -1;
            } else {
                this.c = false;
                return 0;
            }
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            if (this.a <= 0) {
                if (!this.c) {
                    return -1;
                }
                this.c = false;
                bArr[i] = (byte) 0;
                return 1;
            } else if (i2 <= 0) {
                return 0;
            } else {
                int read;
                if (((long) i2) > this.a) {
                    i2 = (int) this.a;
                }
                synchronized (g.a(this.d)) {
                    g.a(this.d).seek(this.b);
                    read = g.a(this.d).read(bArr, i, i2);
                }
                if (read <= 0) {
                    return read;
                }
                this.b += (long) read;
                this.a -= (long) read;
                return read;
            }
        }

        void a() {
            this.c = true;
        }
    }

    /* compiled from: ZipFile */
    private static final class b {
        private long a;
        private long b;

        private b() {
            this.a = -1;
            this.b = -1;
        }

        b(AnonymousClass1 anonymousClass1) {
            this();
        }

        static long a(b bVar) {
            return bVar.b;
        }

        static long a(b bVar, long j) {
            bVar.a = j;
            return j;
        }

        static long b(b bVar) {
            return bVar.a;
        }

        static long b(b bVar, long j) {
            bVar.b = j;
            return j;
        }
    }

    static RandomAccessFile a(g gVar) {
        return gVar.d;
    }

    public g(String str, String str2) throws IOException {
        this(new File(str), str2);
    }

    public g(File file, String str) throws IOException {
        this.a = new Hashtable(509);
        this.b = new Hashtable(509);
        this.c = null;
        this.c = str;
        this.d = new RandomAccessFile(file, "r");
        try {
            c();
            e();
        } catch (IOException e) {
            try {
                this.d.close();
            } catch (IOException e2) {
            }
            throw e;
        }
    }

    public String a() {
        return this.c;
    }

    public Enumeration b() {
        return this.a.keys();
    }

    public e a(String str) {
        return (e) this.b.get(str);
    }

    public InputStream a(e eVar) throws IOException, ZipException {
        b bVar = (b) this.a.get(eVar);
        if (bVar == null) {
            return null;
        }
        InputStream aVar = new a(this, b.a(bVar), eVar.getCompressedSize());
        switch (eVar.getMethod()) {
            case 0:
                return aVar;
            case 8:
                aVar.a();
                return new InflaterInputStream(aVar, new Inflater(true));
            default:
                throw new ZipException(new StringBuffer().append("Found unsupported compression method ").append(eVar.getMethod()).toString());
        }
    }

    private void c() throws IOException {
        d();
        byte[] bArr = new byte[42];
        byte[] bArr2 = new byte[4];
        this.d.readFully(bArr2);
        long a = h.a(i.e);
        for (long a2 = h.a(bArr2); a2 == a; a2 = h.a(bArr2)) {
            this.d.readFully(bArr);
            e eVar = new e();
            eVar.b((j.a(bArr, 0) >> 8) & 15);
            eVar.setMethod(j.a(bArr, 6));
            eVar.setTime(a(h.a(bArr, 8)));
            eVar.setCrc(h.a(bArr, 12));
            eVar.setCompressedSize(h.a(bArr, 16));
            eVar.setSize(h.a(bArr, 20));
            int a3 = j.a(bArr, 24);
            int a4 = j.a(bArr, 26);
            int a5 = j.a(bArr, 28);
            eVar.a(j.a(bArr, 32));
            eVar.a(h.a(bArr, 34));
            byte[] bArr3 = new byte[a3];
            this.d.readFully(bArr3);
            eVar.a(a(bArr3));
            b bVar = new b(null);
            b.a(bVar, h.a(bArr, 38));
            this.a.put(eVar, bVar);
            this.b.put(eVar.getName(), eVar);
            this.d.skipBytes(a4);
            bArr3 = new byte[a5];
            this.d.readFully(bArr3);
            eVar.setComment(a(bArr3));
            this.d.readFully(bArr2);
        }
    }

    private void d() throws IOException {
        int i = 1;
        long length = this.d.length() - 22;
        if (length >= 0) {
            this.d.seek(length);
            byte[] bArr = i.f;
            byte read = this.d.read();
            while (read != (byte) -1) {
                if (read == bArr[0] && this.d.read() == bArr[1] && this.d.read() == bArr[2] && this.d.read() == bArr[3]) {
                    break;
                }
                long j = length - 1;
                this.d.seek(j);
                length = j;
                read = this.d.read();
            }
        }
        i = 0;
        if (i == 0) {
            throw new ZipException("archive is not a ZIP archive");
        }
        this.d.seek(length + 16);
        byte[] bArr2 = new byte[4];
        this.d.readFully(bArr2);
        this.d.seek(h.a(bArr2));
    }

    private void e() throws IOException {
        Enumeration b = b();
        while (b.hasMoreElements()) {
            e eVar = (e) b.nextElement();
            b bVar = (b) this.a.get(eVar);
            long b2 = b.b(bVar);
            this.d.seek(b2 + 26);
            byte[] bArr = new byte[2];
            this.d.readFully(bArr);
            int a = j.a(bArr);
            this.d.readFully(bArr);
            int a2 = j.a(bArr);
            this.d.skipBytes(a);
            byte[] bArr2 = new byte[a2];
            this.d.readFully(bArr2);
            eVar.setExtra(bArr2);
            b.b(bVar, ((((b2 + 26) + 2) + 2) + ((long) a)) + ((long) a2));
        }
    }

    private static long a(long j) {
        Calendar instance = Calendar.getInstance();
        instance.set(1, ((int) ((j >> 25) & 127)) + 1980);
        instance.set(2, ((int) ((j >> 21) & 15)) - 1);
        instance.set(5, ((int) (j >> 16)) & 31);
        instance.set(11, ((int) (j >> 11)) & 31);
        instance.set(12, ((int) (j >> 5)) & 63);
        instance.set(13, ((int) (j << 1)) & 62);
        return instance.getTime().getTime();
    }

    protected String a(byte[] bArr) throws ZipException {
        if (this.c == null) {
            return new String(bArr);
        }
        try {
            return new String(bArr, this.c);
        } catch (UnsupportedEncodingException e) {
            throw new ZipException(e.getMessage());
        }
    }
}
