package org.apache.a.a;

import java.util.zip.CRC32;
import java.util.zip.ZipException;

/* compiled from: AsiExtraField */
public class a implements Cloneable, f {
    private static final j a = new j(30062);
    private int b = 0;
    private int c = 0;
    private int d = 0;
    private String e = "";
    private boolean f = false;
    private CRC32 g = new CRC32();

    public j a() {
        return a;
    }

    public j b() {
        return new j(h().getBytes().length + 14);
    }

    public j c() {
        return b();
    }

    public byte[] d() {
        Object obj = new byte[(b().b() - 4)];
        System.arraycopy(j.a(j()), 0, obj, 0, 2);
        Object bytes = h().getBytes();
        System.arraycopy(h.a((long) bytes.length), 0, obj, 2, 4);
        System.arraycopy(j.a(f()), 0, obj, 6, 2);
        System.arraycopy(j.a(g()), 0, obj, 8, 2);
        System.arraycopy(bytes, 0, obj, 10, bytes.length);
        this.g.reset();
        this.g.update(obj);
        bytes = new byte[(obj.length + 4)];
        System.arraycopy(h.a(this.g.getValue()), 0, bytes, 0, 4);
        System.arraycopy(obj, 0, bytes, 4, obj.length);
        return bytes;
    }

    public byte[] e() {
        return d();
    }

    public int f() {
        return this.c;
    }

    public int g() {
        return this.d;
    }

    public String h() {
        return this.e;
    }

    public boolean i() {
        return h().length() != 0;
    }

    public void a(int i) {
        this.b = b(i);
    }

    public int j() {
        return this.b;
    }

    public void a(boolean z) {
        this.f = z;
        this.b = b(this.b);
    }

    public boolean k() {
        return this.f && !i();
    }

    public void a(byte[] bArr, int i, int i2) throws ZipException {
        boolean z = false;
        long a = h.a(bArr, i);
        Object obj = new byte[(i2 - 4)];
        System.arraycopy(bArr, i + 4, obj, 0, i2 - 4);
        this.g.reset();
        this.g.update(obj);
        long value = this.g.getValue();
        if (a != value) {
            throw new ZipException(new StringBuffer().append("bad CRC checksum ").append(Long.toHexString(a)).append(" instead of ").append(Long.toHexString(value)).toString());
        }
        int a2 = j.a(obj, 0);
        Object obj2 = new byte[((int) h.a(obj, 2))];
        this.c = j.a(obj, 6);
        this.d = j.a(obj, 8);
        if (obj2.length == 0) {
            this.e = "";
        } else {
            System.arraycopy(obj, 10, obj2, 0, obj2.length);
            this.e = new String(obj2);
        }
        if ((a2 & 16384) != 0) {
            z = true;
        }
        a(z);
        a(a2);
    }

    protected int b(int i) {
        int i2 = 32768;
        if (i()) {
            i2 = 40960;
        } else if (k()) {
            i2 = 16384;
        }
        return i2 | (i & 4095);
    }
}
