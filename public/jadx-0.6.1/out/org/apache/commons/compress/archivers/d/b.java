package org.apache.commons.compress.archivers.d;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.compress.archivers.a;
import org.apache.commons.compress.archivers.zip.p;
import org.apache.commons.compress.archivers.zip.q;

/* compiled from: TarArchiveInputStream */
public class b extends org.apache.commons.compress.archivers.b {
    protected final d a;
    private final byte[] b;
    private final byte[] c;
    private boolean d;
    private long e;
    private long f;
    private byte[] g;
    private a h;
    private final p i;

    public b(InputStream inputStream) {
        this(inputStream, 10240, 512);
    }

    public b(InputStream inputStream, String str) {
        this(inputStream, 10240, 512, str);
    }

    public b(InputStream inputStream, int i, int i2) {
        this(inputStream, i, i2, null);
    }

    public b(InputStream inputStream, int i, int i2, String str) {
        this.b = new byte[8192];
        this.c = new byte[256];
        this.a = new d(inputStream, i, i2);
        this.g = null;
        this.d = false;
        this.i = q.a(str);
    }

    public void close() throws IOException {
        this.a.d();
    }

    public int available() throws IOException {
        if (this.e - this.f > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) (this.e - this.f);
    }

    public long skip(long j) throws IOException {
        long j2 = j;
        while (j2 > 0) {
            int read = read(this.b, 0, (int) (j2 > ((long) this.b.length) ? (long) this.b.length : j2));
            if (read == -1) {
                break;
            }
            j2 -= (long) read;
        }
        return j - j2;
    }

    public synchronized void reset() {
    }

    public a b() throws IOException {
        if (this.d) {
            return null;
        }
        if (this.h != null) {
            long j = this.e - this.f;
            while (j > 0) {
                long skip = skip(j);
                if (skip <= 0) {
                    throw new RuntimeException("failed to skip current tar entry");
                }
                j -= skip;
            }
            this.g = null;
        }
        byte[] d = d();
        if (this.d) {
            this.h = null;
            return null;
        }
        try {
            this.h = new a(d, this.i);
            this.f = 0;
            this.e = this.h.b();
            if (this.h.e()) {
                int read;
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    read = read(this.c);
                    if (read < 0) {
                        break;
                    }
                    byteArrayOutputStream.write(this.c, 0, read);
                }
                c();
                if (this.h == null) {
                    return null;
                }
                byte[] bArr;
                Object toByteArray = byteArrayOutputStream.toByteArray();
                read = toByteArray.length;
                while (read > 0 && toByteArray[read - 1] == (byte) 0) {
                    read--;
                }
                if (read != toByteArray.length) {
                    bArr = new byte[read];
                    System.arraycopy(toByteArray, 0, bArr, 0, read);
                } else {
                    Object obj = toByteArray;
                }
                this.h.a(this.i.a(bArr));
            }
            if (this.h.f()) {
                e();
            }
            if (this.h.d()) {
                f();
            }
            this.e = this.h.b();
            return this.h;
        } catch (Throwable e) {
            IOException iOException = new IOException("Error detected parsing the header");
            iOException.initCause(e);
            throw iOException;
        }
    }

    private byte[] d() throws IOException {
        if (this.d) {
            return null;
        }
        byte[] b = this.a.b();
        if (b == null) {
            this.d = true;
        } else if (this.a.a(b)) {
            this.d = true;
            this.a.e();
        }
        if (this.d) {
            return null;
        }
        return b;
    }

    private void e() throws IOException {
        Map a = a((InputStream) this);
        c();
        a(a);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    java.util.Map<java.lang.String, java.lang.String> a(java.io.InputStream r11) throws java.io.IOException {
        /*
        r10 = this;
        r1 = 0;
        r9 = -1;
        r4 = new java.util.HashMap;
        r4.<init>();
    L_0x0007:
        r0 = r1;
        r2 = r1;
    L_0x0009:
        r3 = r11.read();
        if (r3 == r9) goto L_0x007e;
    L_0x000f:
        r0 = r0 + 1;
        r5 = 32;
        if (r3 != r5) goto L_0x0078;
    L_0x0015:
        r5 = new java.io.ByteArrayOutputStream;
        r5.<init>();
    L_0x001a:
        r3 = r11.read();
        if (r3 == r9) goto L_0x007e;
    L_0x0020:
        r0 = r0 + 1;
        r6 = 61;
        if (r3 != r6) goto L_0x0073;
    L_0x0026:
        r6 = "UTF-8";
        r5 = r5.toString(r6);
        r6 = r2 - r0;
        r6 = new byte[r6];
        r7 = r11.read(r6);
        r8 = r2 - r0;
        if (r7 == r8) goto L_0x0060;
    L_0x0039:
        r1 = new java.io.IOException;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "Failed to read Paxheader. Expected ";
        r3 = r3.append(r4);
        r0 = r2 - r0;
        r0 = r3.append(r0);
        r2 = " bytes, read ";
        r0 = r0.append(r2);
        r0 = r0.append(r7);
        r0 = r0.toString();
        r1.<init>(r0);
        throw r1;
    L_0x0060:
        r7 = new java.lang.String;
        r0 = r2 - r0;
        r0 = r0 + -1;
        r2 = "UTF-8";
        r7.<init>(r6, r1, r0, r2);
        r4.put(r5, r7);
        r0 = r3;
    L_0x0070:
        if (r0 != r9) goto L_0x0007;
    L_0x0072:
        return r4;
    L_0x0073:
        r3 = (byte) r3;
        r5.write(r3);
        goto L_0x001a;
    L_0x0078:
        r2 = r2 * 10;
        r3 = r3 + -48;
        r2 = r2 + r3;
        goto L_0x0009;
    L_0x007e:
        r0 = r3;
        goto L_0x0070;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.d.b.a(java.io.InputStream):java.util.Map<java.lang.String, java.lang.String>");
    }

    private void a(Map<String, String> map) {
        for (Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            if ("path".equals(str)) {
                this.h.a(str2);
            } else if ("linkpath".equals(str)) {
                this.h.b(str2);
            } else if ("gid".equals(str)) {
                this.h.b(Integer.parseInt(str2));
            } else if ("gname".equals(str)) {
                this.h.d(str2);
            } else if ("uid".equals(str)) {
                this.h.a(Integer.parseInt(str2));
            } else if ("uname".equals(str)) {
                this.h.c(str2);
            } else if ("size".equals(str)) {
                this.h.b(Long.parseLong(str2));
            } else if ("mtime".equals(str)) {
                this.h.a((long) (Double.parseDouble(str2) * 1000.0d));
            } else if ("SCHILY.devminor".equals(str)) {
                this.h.d(Integer.parseInt(str2));
            } else if ("SCHILY.devmajor".equals(str)) {
                this.h.c(Integer.parseInt(str2));
            }
        }
    }

    private void f() throws IOException {
        if (this.h.c()) {
            byte[] d;
            do {
                d = d();
                if (this.d) {
                    this.h = null;
                    return;
                }
            } while (new c(d).a());
        }
    }

    public a c() throws IOException {
        return b();
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.f >= this.e) {
            return -1;
        }
        int length;
        int length2;
        int i3;
        if (((long) i2) + this.f > this.e) {
            i2 = (int) (this.e - this.f);
        }
        if (this.g != null) {
            length = i2 > this.g.length ? this.g.length : i2;
            System.arraycopy(this.g, 0, bArr, i, length);
            if (length >= this.g.length) {
                this.g = null;
            } else {
                length2 = this.g.length - length;
                Object obj = new byte[length2];
                System.arraycopy(this.g, length, obj, 0, length2);
                this.g = obj;
            }
            i3 = 0 + length;
            length2 = i2 - length;
            i += length;
        } else {
            i3 = 0;
            length2 = i2;
        }
        while (length2 > 0) {
            Object b = this.a.b();
            if (b == null) {
                throw new IOException("unexpected EOF with " + length2 + " bytes unread. Occured at byte: " + a());
            }
            a(b.length);
            length = b.length;
            if (length > length2) {
                System.arraycopy(b, 0, bArr, i, length2);
                this.g = new byte[(length - length2)];
                System.arraycopy(b, length2, this.g, 0, length - length2);
                length = length2;
            } else {
                System.arraycopy(b, 0, bArr, i, length);
            }
            i3 += length;
            length2 -= length;
            i += length;
        }
        this.f += (long) i3;
        return i3;
    }
}
