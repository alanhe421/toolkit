package format.epub.common.text.model;

import format.epub.common.c.a.g.a;
import format.epub.common.c.a.g.b;
import format.epub.common.image.ZLImageMap;
import format.epub.common.text.model.a.d;

/* compiled from: ZLTextWritablePlainModel */
public final class p extends l implements o {
    private char[] m;
    private int n;
    private f o;

    public p(String str, String str2, int i, int i2, String str3, String str4, ZLImageMap zLImageMap, int i3) {
        super(str, str2, i, i2, str3, str4, zLImageMap, i3);
    }

    private void f() {
        this.o = this.i.c();
        this.c = this.o.a;
        this.d = this.o.b;
        this.e = this.o.c;
        this.f = this.o.d;
        this.g = this.o.e;
    }

    public void a(byte b) {
        if (this.o == null) {
            f();
        }
        int i = this.h;
        this.h = i + 1;
        int[] iArr = this.c;
        int[] iArr2 = null;
        if (i > 0 && i % this.l == 0) {
            iArr2 = this.f;
            this.i.d();
            f();
            iArr = this.c;
        }
        if (i > 0) {
            if (i % this.l == 0) {
                this.f[0] = iArr2[iArr2.length - 1];
                i %= this.l;
            } else {
                i %= this.l;
                this.f[i] = this.f[i - 1];
            }
        }
        int a = this.i.a();
        iArr[i] = a == 0 ? 0 : a - 1;
        this.d[i] = this.n;
        this.e[i] = 0;
        this.g[i] = b;
        f fVar = this.o;
        fVar.f++;
    }

    private char[] d(int i) {
        char[] cArr = this.m;
        if (cArr != null && i <= cArr.length - this.n) {
            return cArr;
        }
        if (cArr != null) {
            this.i.b();
        }
        cArr = this.i.b(i);
        this.m = cArr;
        this.n = 0;
        return cArr;
    }

    public void a(byte b, boolean z) {
        char[] d = d(2);
        int[] iArr = this.e;
        int i = (this.h - 1) % this.l;
        iArr[i] = iArr[i] + 1;
        int i2 = this.n;
        this.n = i2 + 1;
        d[i2] = '\u0003';
        i2 = (short) b;
        if (z) {
            i2 = (short) (i2 + 256);
        }
        i = this.n;
        this.n = i + 1;
        d[i] = (char) i2;
    }

    public void a(char[] cArr, int i, int i2) {
        Object d = d(i2 + 3);
        int[] iArr = this.e;
        int i3 = (this.h - 1) % this.l;
        iArr[i3] = iArr[i3] + 1;
        int i4 = this.n;
        i3 = i4 + 1;
        d[i4] = '\u0001';
        i4 = i3 + 1;
        d[i3] = (char) (i2 >> 16);
        i3 = i4 + 1;
        d[i4] = (char) i2;
        System.arraycopy(cArr, i, d, i3, i2);
        this.n = i3 + i2;
        int[] iArr2 = this.f;
        i4 = (this.h - 1) % this.l;
        iArr2[i4] = iArr2[i4] + i2;
    }

    public void a(n nVar, int i) {
        if (nVar.e() != (byte) 0) {
            System.out.println();
        }
        int i2 = 7;
        for (int i3 = 0; i3 < 12; i3++) {
            if (nVar.a(i3)) {
                i2 += 2;
            }
        }
        if (nVar.a(12) || nVar.a(16)) {
            i2++;
        }
        if (nVar.a(13)) {
            i2 += nVar.h().toCharArray().length + 1;
        }
        if (nVar.a(14)) {
            i2++;
        }
        if (nVar.a(15)) {
            i2 += nVar.i().toCharArray().length + 1;
        }
        if (nVar.a(22)) {
            i2 += nVar.q().toCharArray().length + 1;
        }
        if (nVar.a(24)) {
            i2 += nVar.r().toCharArray().length + 1;
        }
        if (nVar.a(23)) {
            i2++;
        }
        if (nVar.a(26)) {
            i2++;
        }
        if (nVar.a(18)) {
            i2 += nVar.s()[0].f.toCharArray().length + 6;
        }
        if (nVar.a(19)) {
            i2 += nVar.s()[1].f.toCharArray().length + 6;
        }
        if (nVar.a(20)) {
            i2 += nVar.s()[2].f.toCharArray().length + 6;
        }
        if (nVar.a(21)) {
            i2 += nVar.s()[3].f.toCharArray().length + 6;
        }
        if (nVar.a(25)) {
            i2 += nVar.o().g.toCharArray().length + 7;
        }
        Object d = d(i2);
        int[] iArr = this.e;
        int i4 = (this.h - 1) % this.l;
        iArr[i4] = iArr[i4] + 1;
        i2 = this.n;
        this.n = i2 + 1;
        d[i2] = (char) (((i & 255) << 8) | nVar.k());
        i2 = this.n;
        this.n = i2 + 1;
        d[i2] = (char) ((nVar.b >> 24) & 255);
        i2 = this.n;
        this.n = i2 + 1;
        d[i2] = (char) ((nVar.b >> 16) & 255);
        i2 = this.n;
        this.n = i2 + 1;
        d[i2] = (char) ((nVar.b >> 8) & 255);
        i2 = this.n;
        this.n = i2 + 1;
        d[i2] = (char) (nVar.b & 255);
        i2 = this.n;
        this.n = i2 + 1;
        d[i2] = (char) nVar.p();
        i2 = this.n;
        this.n = i2 + 1;
        d[i2] = (char) nVar.v();
        for (i2 = 0; i2 < 12; i2++) {
            if (nVar.a(i2)) {
                i4 = this.n;
                this.n = i4 + 1;
                d[i4] = (char) nVar.c[i2].a;
                i4 = this.n;
                this.n = i4 + 1;
                d[i4] = (char) nVar.c[i2].b;
            }
        }
        if (nVar.a(12) || nVar.a(16)) {
            i2 = this.n;
            this.n = i2 + 1;
            d[i2] = (char) (((nVar.j() & 255) << 8) | nVar.g());
        }
        if (nVar.a(13)) {
            i2 = nVar.h().toCharArray().length;
            i4 = this.n;
            this.n = i4 + 1;
            d[i4] = (char) i2;
            System.arraycopy(nVar.h().toCharArray(), 0, d, this.n, i2);
            this.n = i2 + this.n;
        }
        if (nVar.a(14)) {
            i2 = this.n;
            this.n = i2 + 1;
            d[i2] = (char) (((nVar.e & 255) << 8) | nVar.d);
        }
        if (nVar.a(15)) {
            i2 = nVar.i().toCharArray().length;
            i4 = this.n;
            this.n = i4 + 1;
            d[i4] = (char) i2;
            System.arraycopy(nVar.i().toCharArray(), 0, d, this.n, i2);
            this.n = i2 + this.n;
        }
        if (nVar.a(22)) {
            i2 = nVar.q().toCharArray().length;
            i4 = this.n;
            this.n = i4 + 1;
            d[i4] = (char) i2;
            System.arraycopy(nVar.q().toCharArray(), 0, d, this.n, i2);
            this.n = i2 + this.n;
        }
        if (nVar.a(24)) {
            i2 = nVar.r().toCharArray().length;
            i4 = this.n;
            this.n = i4 + 1;
            d[i4] = (char) i2;
            System.arraycopy(nVar.r().toCharArray(), 0, d, this.n, i2);
            this.n = i2 + this.n;
        }
        if (nVar.a(23)) {
            i2 = this.n;
            this.n = i2 + 1;
            d[i2] = (char) nVar.e();
        }
        if (nVar.a(26)) {
            i2 = this.n;
            this.n = i2 + 1;
            d[i2] = (char) nVar.f();
        }
        if (nVar.a(18)) {
            a aVar = nVar.s()[0];
            i4 = aVar.f.toCharArray().length;
            int i5 = this.n;
            this.n = i5 + 1;
            d[i5] = (char) aVar.a;
            i5 = this.n;
            this.n = i5 + 1;
            d[i5] = (char) aVar.b;
            i5 = this.n;
            this.n = i5 + 1;
            d[i5] = (char) aVar.c;
            i5 = this.n;
            this.n = i5 + 1;
            d[i5] = (char) aVar.d;
            i5 = this.n;
            this.n = i5 + 1;
            d[i5] = (char) aVar.e;
            i5 = this.n;
            this.n = i5 + 1;
            d[i5] = (char) i4;
            System.arraycopy(aVar.f.toCharArray(), 0, d, this.n, i4);
            this.n += i4;
        }
        if (nVar.a(19)) {
            aVar = nVar.s()[1];
            i4 = aVar.f.toCharArray().length;
            i5 = this.n;
            this.n = i5 + 1;
            d[i5] = (char) aVar.a;
            i5 = this.n;
            this.n = i5 + 1;
            d[i5] = (char) aVar.b;
            i5 = this.n;
            this.n = i5 + 1;
            d[i5] = (char) aVar.c;
            i5 = this.n;
            this.n = i5 + 1;
            d[i5] = (char) aVar.d;
            i5 = this.n;
            this.n = i5 + 1;
            d[i5] = (char) aVar.e;
            i5 = this.n;
            this.n = i5 + 1;
            d[i5] = (char) i4;
            System.arraycopy(aVar.f.toCharArray(), 0, d, this.n, i4);
            this.n += i4;
        }
        if (nVar.a(20)) {
            aVar = nVar.s()[2];
            i4 = aVar.f.toCharArray().length;
            i5 = this.n;
            this.n = i5 + 1;
            d[i5] = (char) aVar.a;
            i5 = this.n;
            this.n = i5 + 1;
            d[i5] = (char) aVar.b;
            i5 = this.n;
            this.n = i5 + 1;
            d[i5] = (char) aVar.c;
            i5 = this.n;
            this.n = i5 + 1;
            d[i5] = (char) aVar.d;
            i5 = this.n;
            this.n = i5 + 1;
            d[i5] = (char) aVar.e;
            i5 = this.n;
            this.n = i5 + 1;
            d[i5] = (char) i4;
            System.arraycopy(aVar.f.toCharArray(), 0, d, this.n, i4);
            this.n += i4;
        }
        if (nVar.a(21)) {
            aVar = nVar.s()[3];
            i4 = aVar.f.toCharArray().length;
            i5 = this.n;
            this.n = i5 + 1;
            d[i5] = (char) aVar.a;
            i5 = this.n;
            this.n = i5 + 1;
            d[i5] = (char) aVar.b;
            i5 = this.n;
            this.n = i5 + 1;
            d[i5] = (char) aVar.c;
            i5 = this.n;
            this.n = i5 + 1;
            d[i5] = (char) aVar.d;
            i5 = this.n;
            this.n = i5 + 1;
            d[i5] = (char) aVar.e;
            i5 = this.n;
            this.n = i5 + 1;
            d[i5] = (char) i4;
            System.arraycopy(aVar.f.toCharArray(), 0, d, this.n, i4);
            this.n += i4;
        }
        if (nVar.a(25)) {
            b o = nVar.o();
            if (o != null) {
                i4 = o.g.toCharArray().length;
                i5 = this.n;
                this.n = i5 + 1;
                d[i5] = (char) o.a;
                i5 = this.n;
                this.n = i5 + 1;
                d[i5] = (char) o.b;
                i5 = this.n;
                this.n = i5 + 1;
                d[i5] = (char) o.c;
                i5 = this.n;
                this.n = i5 + 1;
                d[i5] = (char) o.d;
                i5 = this.n;
                this.n = i5 + 1;
                d[i5] = (char) o.e;
                i5 = this.n;
                this.n = i5 + 1;
                d[i5] = (char) o.f;
                i5 = this.n;
                this.n = i5 + 1;
                d[i5] = (char) i4;
                System.arraycopy(o.g.toCharArray(), 0, d, this.n, i4);
                this.n += i4;
            }
        }
    }

    public void a(boolean z) {
        char[] d = d(1);
        int[] iArr = this.e;
        int i = (this.h - 1) % this.l;
        iArr[i] = iArr[i] + 1;
        if (z) {
            int i2 = this.n;
            this.n = i2 + 1;
            d[i2] = '\r';
            return;
        }
        i2 = this.n;
        this.n = i2 + 1;
        d[i2] = '\u0007';
    }

    public void a(d dVar) {
    }

    public void a(byte b, byte b2, String str) {
        short length = (short) str.length();
        char[] d = d(length + 3);
        int[] iArr = this.e;
        int i = (this.h - 1) % this.l;
        iArr[i] = iArr[i] + 1;
        int i2 = this.n;
        i = i2 + 1;
        d[i2] = '\u0003';
        i2 = i + 1;
        d[i] = (char) (((b2 << 9) + 256) + b);
        i = i2 + 1;
        d[i2] = (char) length;
        str.getChars(0, length, d, i);
        this.n = length + i;
    }

    public void a(String str, short s) {
        int length = str.length();
        char[] d = d(length + 3);
        int[] iArr = this.e;
        int i = (this.h - 1) % this.l;
        iArr[i] = iArr[i] + 1;
        int i2 = this.n;
        i = i2 + 1;
        d[i2] = '\u0002';
        i2 = i + 1;
        d[i] = (char) s;
        i = i2 + 1;
        d[i2] = (char) length;
        str.getChars(0, length, d, i);
        this.n = length + i;
    }

    public void a(short s) {
        char[] d = d(2);
        int[] iArr = this.e;
        int i = (this.h - 1) % this.l;
        iArr[i] = iArr[i] + 1;
        int i2 = this.n;
        this.n = i2 + 1;
        d[i2] = '\u0005';
        i2 = this.n;
        this.n = i2 + 1;
        d[i2] = (char) s;
    }

    public void e() {
    }
}
