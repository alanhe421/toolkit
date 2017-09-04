package de.innosystec.unrar.unpack.ppm;

import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.util.Arrays;

/* compiled from: SubAllocator */
public class k {
    public static final int a = Math.max(c.a, 12);
    static final /* synthetic */ boolean b = (!k.class.desiredAssertionStatus());
    private int c;
    private int[] d = new int[38];
    private int[] e = new int[128];
    private int f;
    private int g;
    private int h;
    private int i;
    private final g[] j = new g[38];
    private int k;
    private int l;
    private int m;
    private int n;
    private byte[] o;
    private int p;
    private int q;
    private g r = null;
    private f s = null;
    private f t = null;
    private f u = null;

    public k() {
        a();
    }

    public void a() {
        this.c = 0;
    }

    private void c(int i, int i2) {
        g gVar = this.r;
        gVar.c(i);
        gVar.a(this.j[i2].a());
        this.j[i2].a(gVar);
    }

    public void b() {
        this.k++;
    }

    private int e(int i) {
        int a = this.j[i].a();
        g gVar = this.r;
        gVar.c(a);
        this.j[i].a(gVar.a());
        return a;
    }

    private int f(int i) {
        return a * i;
    }

    private int d(int i, int i2) {
        return f(i2) + i;
    }

    private void b(int i, int i2, int i3) {
        int i4 = this.d[i2] - this.d[i3];
        int f = f(this.d[i3]) + i;
        int[] iArr = this.d;
        int i5 = this.e[i4 - 1];
        if (iArr[i5] != i4) {
            int i6 = i5 - 1;
            c(f, i6);
            i6 = this.d[i6];
            f += f(i6);
            i4 -= i6;
        }
        c(f, this.e[i4 - 1]);
    }

    public void c() {
        if (this.c != 0) {
            this.c = 0;
            this.o = null;
            this.g = 1;
            this.r = null;
            this.s = null;
            this.t = null;
            this.u = null;
        }
    }

    public int d() {
        return this.c;
    }

    public boolean a(int i) {
        int i2 = i << 20;
        if (this.c != i2) {
            c();
            int i3 = ((i2 / 12) * a) + a;
            int i4 = (i3 + 1) + Opcodes.SHL_INT;
            this.q = i4;
            i4 += 12;
            this.o = new byte[i4];
            this.g = 1;
            this.m = (this.g + i3) - a;
            this.c = i2;
            this.p = this.g + i3;
            if (b || i4 - this.q == 12) {
                i3 = 0;
                i2 = this.p;
                while (i3 < this.j.length) {
                    this.j[i3] = new g(this.o);
                    this.j[i3].c(i2);
                    i3++;
                    i2 += 4;
                }
                this.r = new g(this.o);
                this.s = new f(this.o);
                this.t = new f(this.o);
                this.u = new f(this.o);
            } else {
                throw new AssertionError(new StringBuilder(String.valueOf(i4)).append(" ").append(this.q).append(" ").append(12).toString());
            }
        }
        return true;
    }

    private void k() {
        int i = 0;
        f fVar = this.s;
        fVar.c(this.q);
        f fVar2 = this.t;
        f fVar3 = this.u;
        if (this.h != this.i) {
            this.o[this.h] = (byte) 0;
        }
        fVar.c(fVar);
        fVar.b(fVar);
        while (i < 38) {
            while (this.j[i].a() != 0) {
                fVar2.c(e(i));
                fVar2.a(fVar);
                fVar2.e(65535);
                fVar2.b(this.d[i]);
            }
            i++;
        }
        fVar2.c(fVar.b());
        while (fVar2.e() != fVar.e()) {
            fVar3.c(d(fVar2.e(), fVar2.c()));
            while (fVar3.f() == 65535 && fVar2.c() + fVar3.c() < 65536) {
                fVar3.a();
                fVar2.b(fVar2.c() + fVar3.c());
                fVar3.c(d(fVar2.e(), fVar2.c()));
            }
            fVar2.c(fVar2.b());
        }
        fVar2.c(fVar.b());
        while (fVar2.e() != fVar.e()) {
            fVar2.a();
            int c = fVar2.c();
            while (c > 128) {
                c(fVar2.e(), 37);
                i = c - 128;
                fVar2.c(d(fVar2.e(), 128));
                c = i;
            }
            int[] iArr = this.d;
            i = this.e[c - 1];
            if (iArr[i] != c) {
                i--;
                int i2 = c - this.d[i];
                c(d(fVar2.e(), c - i2), i2 - 1);
            }
            c(fVar2.e(), i);
            fVar2.c(fVar.b());
        }
    }

    private int g(int i) {
        int i2;
        if (this.f == 0) {
            this.f = 255;
            k();
            if (this.j[i].a() != 0) {
                return e(i);
            }
        }
        int i3 = i;
        while (true) {
            i2 = i3 + 1;
            if (i2 == 38) {
                break;
            } else if (this.j[i2].a() != 0) {
                i3 = e(i2);
                b(i3, i2, i);
                return i3;
            } else {
                i3 = i2;
            }
        }
        this.f--;
        i3 = f(this.d[i]);
        i2 = this.d[i] * 12;
        if (this.n - this.k <= i2) {
            return 0;
        }
        this.n -= i2;
        this.l -= i3;
        return this.l;
    }

    public int b(int i) {
        int i2 = this.e[i - 1];
        if (this.j[i2].a() != 0) {
            return e(i2);
        }
        int i3 = this.h;
        this.h += f(this.d[i2]);
        if (this.h <= this.i) {
            return i3;
        }
        this.h -= f(this.d[i2]);
        return g(i2);
    }

    public int e() {
        if (this.i != this.h) {
            int i = this.i - a;
            this.i = i;
            return i;
        } else if (this.j[0].a() != 0) {
            return e(0);
        } else {
            return g(0);
        }
    }

    public int a(int i, int i2) {
        int i3 = this.e[i2 - 1];
        if (i3 == this.e[(i2 - 1) + 1]) {
            return i;
        }
        int b = b(i2 + 1);
        if (b != 0) {
            System.arraycopy(this.o, i, this.o, b, f(i2));
            c(i, i3);
        }
        return b;
    }

    public int a(int i, int i2, int i3) {
        int i4 = this.e[i2 - 1];
        int i5 = this.e[i3 - 1];
        if (i4 == i5) {
            return i;
        }
        if (this.j[i5].a() != 0) {
            i5 = e(i5);
            System.arraycopy(this.o, i, this.o, i5, f(i3));
            c(i, i4);
            return i5;
        }
        b(i, i4, i5);
        return i;
    }

    public void b(int i, int i2) {
        c(i, this.e[i2 - 1]);
    }

    public int f() {
        return this.n;
    }

    public int g() {
        return this.m;
    }

    public int h() {
        return this.k;
    }

    public void c(int i) {
        this.k = i;
    }

    public void d(int i) {
        c(h() - i);
    }

    public void i() {
        Arrays.fill(this.o, this.p, this.p + l(), (byte) 0);
        this.k = this.g;
        int i = (((this.c / 8) / 12) * 7) * 12;
        int i2 = (i / 12) * a;
        i = this.c - i;
        int i3 = ((i / 12) * a) + (i % 12);
        this.i = this.g + this.c;
        i3 += this.g;
        this.l = i3;
        this.h = i3;
        this.n = i + this.g;
        this.i = this.h + i2;
        i = 1;
        i2 = 0;
        while (i2 < 4) {
            this.d[i2] = i & 255;
            i2++;
            i++;
        }
        i++;
        while (i2 < 8) {
            this.d[i2] = i & 255;
            i2++;
            i += 2;
        }
        i++;
        while (i2 < 12) {
            this.d[i2] = i & 255;
            i2++;
            i += 3;
        }
        i++;
        while (i2 < 38) {
            this.d[i2] = i & 255;
            i2++;
            i += 4;
        }
        this.f = 0;
        i3 = 0;
        for (i2 = 0; i2 < 128; i2++) {
            if (this.d[i3] < i2 + 1) {
                i = 1;
            } else {
                i = 0;
            }
            i3 += i;
            this.e[i2] = i3 & 255;
        }
    }

    private int l() {
        return this.j.length * 4;
    }

    public byte[] j() {
        return this.o;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SubAllocator[");
        stringBuilder.append("\n  subAllocatorSize=");
        stringBuilder.append(this.c);
        stringBuilder.append("\n  glueCount=");
        stringBuilder.append(this.f);
        stringBuilder.append("\n  heapStart=");
        stringBuilder.append(this.g);
        stringBuilder.append("\n  loUnit=");
        stringBuilder.append(this.h);
        stringBuilder.append("\n  hiUnit=");
        stringBuilder.append(this.i);
        stringBuilder.append("\n  pText=");
        stringBuilder.append(this.k);
        stringBuilder.append("\n  unitsStart=");
        stringBuilder.append(this.l);
        stringBuilder.append("\n]");
        return stringBuilder.toString();
    }
}
