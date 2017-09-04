package format.epub.zip;

import java.io.IOException;

class Deflator extends a {
    int[] a = new int[3];
    boolean b;
    private d c;
    private int d;
    private int e;
    private final byte[] f = new byte[2048];
    private int g;
    private int h;
    private final byte[] i = new byte[32768];
    private int j;
    private int k;
    private boolean l;
    private volatile int m = -1;

    private native void endInflating(int i);

    private native long inflate(int i, byte[] bArr, int i2, int i3, byte[] bArr2);

    private native int startInflating();

    static {
        System.loadLibrary("epub");
    }

    public Deflator(d dVar, c cVar) throws IOException {
        b(dVar, cVar);
    }

    void b(d dVar, c cVar) throws IOException {
        if (this.m != -1) {
            endInflating(this.m);
            this.m = -1;
        }
        this.b = false;
        this.c = dVar;
        this.d = cVar.h;
        if (this.d <= 0) {
            this.d = Integer.MAX_VALUE;
        }
        this.e = cVar.i;
        if (this.e <= 0) {
            this.e = Integer.MAX_VALUE;
        }
        this.g = 2048;
        this.h = 0;
        this.j = 32768;
        this.k = 0;
        this.m = startInflating();
        if (this.m == -1) {
            throw new ZipException("cannot start inflating");
        }
        if (cVar.p) {
            this.b = true;
            this.a[0] = cVar.o[0];
            this.a[1] = cVar.o[1];
            this.a[2] = cVar.o[2];
        }
        this.l = true;
    }

    public int b() {
        return this.e;
    }

    public int a(byte[] bArr, int i, int i2) throws IOException {
        if (this.e <= 0) {
            return -1;
        }
        int i3;
        if (i2 > this.e) {
            i2 = this.e;
        }
        int i4 = i2;
        while (i4 > 0) {
            if (this.k == 0) {
                c();
            }
            if (this.k == 0) {
                i3 = i2 - i4;
                break;
            }
            i3 = i4 < this.k ? i4 : this.k;
            if (bArr != null) {
                System.arraycopy(this.i, this.j, bArr, i, i3);
            }
            i += i3;
            this.j += i3;
            i4 -= i3;
            this.k -= i3;
        }
        i3 = i2;
        if (i3 > 0) {
            this.e -= i3;
            return i3;
        }
        this.e = 0;
        return i3;
    }

    public int a() throws IOException {
        if (this.e <= 0) {
            return -1;
        }
        if (this.k == 0) {
            c();
        }
        if (this.k == 0) {
            this.e = 0;
            return -1;
        }
        this.e--;
        this.k--;
        byte[] bArr = this.i;
        int i = this.j;
        this.j = i + 1;
        return bArr[i];
    }

    private void c() throws IOException {
        if (this.m != -1) {
            while (this.k == 0) {
                int i;
                if (this.h == 0) {
                    this.g = 0;
                    i = this.d < 2048 ? this.d : 2048;
                    this.h = this.c.read(this.f, 0, i);
                    if (this.h < i) {
                        this.d = 0;
                    } else {
                        this.d -= i;
                    }
                }
                if (this.h > 0) {
                    if (this.b && this.g == 0) {
                        for (i = this.g; i < this.g + this.h; i++) {
                            this.f[i] = b.a(this.a, this.f[i]);
                        }
                    }
                    long inflate = inflate(this.m, this.f, this.g, this.h, this.i);
                    if (inflate <= 0) {
                        StringBuilder append = new StringBuilder().append(this.c.a()).append(":").append(this.g).append(":").append(this.h).append(":").append(this.i.length).append(":");
                        for (i = 0; i < Math.min(10, this.h); i++) {
                            append.append(this.f[this.g + i]).append(",");
                        }
                        throw new ZipException("Cannot inflate zip-compressed block, code = " + inflate + ";extra info = " + append);
                    }
                    i = ((int) (inflate >> 16)) & 65535;
                    if (i > this.h) {
                        throw new ZipException("Invalid inflating result, code = " + inflate + "; buffer length = " + this.h);
                    }
                    int i2 = ((int) inflate) & 65535;
                    this.g += i;
                    this.h -= i;
                    this.j = 0;
                    this.k = i2;
                    if ((4294967296L & inflate) != 0) {
                        endInflating(this.m);
                        this.m = -1;
                        this.l = false;
                        this.c.c(this.h);
                        return;
                    }
                }
                return;
            }
        }
    }
}
