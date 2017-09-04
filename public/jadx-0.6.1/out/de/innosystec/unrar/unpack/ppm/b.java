package de.innosystec.unrar.unpack.ppm;

import com.tencent.qalsdk.base.a;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import de.innosystec.unrar.exception.RarException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

/* compiled from: ModelPPM */
public class b {
    private static int[] w = new int[]{15581, 7999, 22975, 18675, 25761, 23228, 26162, 24657};
    private final i A = new i(null);
    private final j B = new j();
    private final j C = new j();
    private final c D = new c(null);
    private final c E = new c(null);
    private final c F = new c(null);
    private final c G = new c(null);
    private final int[] H = new int[64];
    private h[][] a = ((h[][]) Array.newInstance(h.class, new int[]{25, 16}));
    private h b;
    private c c = null;
    private c d = null;
    private c e = null;
    private i f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int[] m = new int[256];
    private int[] n = new int[256];
    private int[] o = new int[256];
    private int[] p = new int[256];
    private int q;
    private int r;
    private int s;
    private int[][] t = ((int[][]) Array.newInstance(Integer.TYPE, new int[]{128, 64}));
    private e u = new e();
    private k v = new k();
    private final i x = new i(null);
    private final i y = new i(null);
    private final i z = new i(null);

    public k a() {
        return this.v;
    }

    private void t() {
        int i = 12;
        Arrays.fill(this.m, 0);
        this.v.i();
        if (this.j < 12) {
            i = this.j;
        }
        this.l = (-i) - 1;
        i = this.v.e();
        this.c.c(i);
        this.e.c(i);
        this.c.b(0);
        this.i = this.j;
        this.c.a(256);
        this.c.a().a(this.c.b() + 1);
        i = this.v.b(128);
        this.f.c(i);
        this.c.a().a_(i);
        i iVar = new i(this.v.j());
        int b = this.c.a().b();
        this.k = this.l;
        this.r = 0;
        for (i = 0; i < 256; i++) {
            iVar.c((i * 6) + b);
            iVar.a(i);
            iVar.b(1);
            iVar.e(0);
        }
        for (b = 0; b < 128; b++) {
            int i2;
            for (i2 = 0; i2 < 8; i2++) {
                for (i = 0; i < 64; i += 8) {
                    this.t[b][i2 + i] = 16384 - (w[i2] / (b + 2));
                }
            }
        }
        for (i2 = 0; i2 < 25; i2++) {
            for (i = 0; i < 16; i++) {
                this.a[i2][i].a((i2 * 5) + 10);
            }
        }
    }

    private void i(int i) {
        int i2;
        int i3 = 1;
        this.q = 1;
        this.j = i;
        t();
        this.o[0] = 0;
        this.o[1] = 2;
        for (i2 = 0; i2 < 9; i2++) {
            this.o[i2 + 2] = 4;
        }
        for (i2 = 0; i2 < 245; i2++) {
            this.o[i2 + 11] = 6;
        }
        i2 = 0;
        while (i2 < 3) {
            this.n[i2] = i2;
            i2++;
        }
        int i4 = 1;
        for (int i5 = i2; i5 < 256; i5++) {
            this.n[i5] = i2;
            i4--;
            if (i4 == 0) {
                i3++;
                i2++;
                i4 = i3;
            }
        }
        for (i3 = 0; i3 < 64; i3++) {
            this.p[i3] = 0;
        }
        for (i3 = 0; i3 < Opcodes.AND_LONG_2ADDR; i3++) {
            this.p[i3 + 64] = 8;
        }
        this.b.b(7);
    }

    private void u() {
        this.q = 1;
        Arrays.fill(this.m, 0);
    }

    public boolean a(de.innosystec.unrar.unpack.b bVar, int i) throws IOException, RarException {
        int a;
        int a2 = bVar.a() & 255;
        boolean z = (a2 & 32) != 0;
        if (z) {
            a = bVar.a();
        } else if (this.v.d() == 0) {
            return false;
        } else {
            a = 0;
        }
        if ((a2 & 64) != 0) {
            bVar.a(bVar.a());
        }
        this.u.a(bVar);
        if (z) {
            int i2;
            int i3 = (a2 & 31) + 1;
            if (i3 > 16) {
                i2 = ((i3 - 16) * 3) + 16;
            } else {
                i2 = i3;
            }
            if (i2 == 1) {
                this.v.c();
                return false;
            }
            this.v.a(a + 1);
            this.c = new c(r());
            this.d = new c(r());
            this.e = new c(r());
            this.f = new i(r());
            this.b = new h();
            for (a = 0; a < 25; a++) {
                for (i3 = 0; i3 < 16; i3++) {
                    this.a[a][i3] = new h();
                }
            }
            i(i2);
        }
        if (this.c.e() != 0) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int b() throws java.io.IOException, de.innosystec.unrar.exception.RarException {
        /*
        r3 = this;
        r0 = -1;
        r1 = r3.c;
        r1 = r1.e();
        r2 = r3.v;
        r2 = r2.h();
        if (r1 <= r2) goto L_0x001d;
    L_0x000f:
        r1 = r3.c;
        r1 = r1.e();
        r2 = r3.v;
        r2 = r2.g();
        if (r1 <= r2) goto L_0x001e;
    L_0x001d:
        return r0;
    L_0x001e:
        r1 = r3.c;
        r1 = r1.b();
        r2 = 1;
        if (r1 == r2) goto L_0x008e;
    L_0x0027:
        r1 = r3.c;
        r1 = r1.a();
        r1 = r1.b();
        r2 = r3.v;
        r2 = r2.h();
        if (r1 <= r2) goto L_0x001d;
    L_0x0039:
        r1 = r3.c;
        r1 = r1.a();
        r1 = r1.b();
        r2 = r3.v;
        r2 = r2.g();
        if (r1 > r2) goto L_0x001d;
    L_0x004b:
        r1 = r3.c;
        r1 = r1.d(r3);
        if (r1 == 0) goto L_0x001d;
    L_0x0053:
        r1 = r3.u;
        r1.c();
    L_0x0058:
        r1 = r3.f;
        r1 = r1.e();
        if (r1 == 0) goto L_0x0094;
    L_0x0060:
        r0 = r3.f;
        r0 = r0.a();
        r1 = r3.i;
        if (r1 != 0) goto L_0x00df;
    L_0x006a:
        r1 = r3.f;
        r1 = r1.c();
        r2 = r3.v;
        r2 = r2.h();
        if (r1 <= r2) goto L_0x00df;
    L_0x0078:
        r1 = r3.f;
        r1 = r1.c();
        r2 = r3.c;
        r2.c(r1);
        r2 = r3.e;
        r2.c(r1);
    L_0x0088:
        r1 = r3.u;
        r1.d();
        goto L_0x001d;
    L_0x008e:
        r1 = r3.c;
        r1.b(r3);
        goto L_0x0053;
    L_0x0094:
        r1 = r3.u;
        r1.d();
    L_0x0099:
        r1 = r3.i;
        r1 = r1 + 1;
        r3.i = r1;
        r1 = r3.c;
        r2 = r3.c;
        r2 = r2.d();
        r1.c(r2);
        r1 = r3.c;
        r1 = r1.e();
        r2 = r3.v;
        r2 = r2.h();
        if (r1 <= r2) goto L_0x001d;
    L_0x00b8:
        r1 = r3.c;
        r1 = r1.e();
        r2 = r3.v;
        r2 = r2.g();
        if (r1 > r2) goto L_0x001d;
    L_0x00c6:
        r1 = r3.c;
        r1 = r1.b();
        r2 = r3.g;
        if (r1 == r2) goto L_0x0099;
    L_0x00d0:
        r1 = r3.c;
        r1 = r1.c(r3);
        if (r1 == 0) goto L_0x001d;
    L_0x00d8:
        r1 = r3.u;
        r1.c();
        goto L_0x0058;
    L_0x00df:
        r3.w();
        r1 = r3.q;
        if (r1 != 0) goto L_0x0088;
    L_0x00e6:
        r3.u();
        goto L_0x0088;
        */
        throw new UnsupportedOperationException("Method not decompiled: de.innosystec.unrar.unpack.ppm.b.b():int");
    }

    public h[][] c() {
        return this.a;
    }

    public h d() {
        return this.b;
    }

    public int e() {
        return this.l;
    }

    public void a(int i) {
        this.q = i & 255;
    }

    public int f() {
        return this.q;
    }

    public void b(int i) {
        a(f() + i);
    }

    public int[] g() {
        return this.m;
    }

    public int h() {
        return this.g;
    }

    public void c(int i) {
        this.g = i;
    }

    public void d(int i) {
        this.r = i & 255;
    }

    public void e(int i) {
        this.h = i;
    }

    public void f(int i) {
        this.k = i;
    }

    public int i() {
        return this.k;
    }

    public void g(int i) {
        f(i() + i);
    }

    public int j() {
        return this.r;
    }

    public int k() {
        return this.s;
    }

    public void h(int i) {
        this.s = i & 255;
    }

    public int[][] l() {
        return this.t;
    }

    public e m() {
        return this.u;
    }

    public int[] n() {
        return this.p;
    }

    public int[] o() {
        return this.o;
    }

    public int[] p() {
        return this.n;
    }

    public i q() {
        return this.f;
    }

    public byte[] r() {
        return this.v.j();
    }

    public int s() {
        return this.i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int a(boolean r12, de.innosystec.unrar.unpack.ppm.i r13) {
        /*
        r11 = this;
        r0 = 1;
        r1 = 0;
        r4 = r11.C;
        r2 = r11.x;
        r3 = r11.r();
        r5 = r2.a(r3);
        r2 = r11.D;
        r3 = r11.r();
        r6 = r2.a(r3);
        r2 = r11.c;
        r2 = r2.e();
        r6.c(r2);
        r2 = r11.E;
        r3 = r11.r();
        r7 = r2.a(r3);
        r2 = r11.f;
        r2 = r2.c();
        r7.c(r2);
        r2 = r11.y;
        r3 = r11.r();
        r8 = r2.a(r3);
        if (r12 != 0) goto L_0x0183;
    L_0x0040:
        r2 = r11.H;
        r3 = r11.f;
        r3 = r3.e();
        r2[r1] = r3;
        r2 = r6.d();
        if (r2 != 0) goto L_0x017f;
    L_0x0050:
        r3 = r0;
        r2 = r0;
    L_0x0052:
        if (r3 != 0) goto L_0x00af;
    L_0x0054:
        r3 = r13.e();
        if (r3 == 0) goto L_0x017c;
    L_0x005a:
        r3 = r13.e();
        r8.c(r3);
        r3 = r6.d();
        r6.c(r3);
        r3 = r0;
    L_0x0069:
        if (r3 != 0) goto L_0x009e;
    L_0x006b:
        r3 = r6.d();
        r6.c(r3);
        r3 = r6.b();
        if (r3 == r0) goto L_0x00b6;
    L_0x0078:
        r3 = r6.a();
        r3 = r3.b();
        r8.c(r3);
        r3 = r8.a();
        r9 = r11.f;
        r9 = r9.a();
        if (r3 == r9) goto L_0x009e;
    L_0x008f:
        r8.f();
        r3 = r8.a();
        r9 = r11.f;
        r9 = r9.a();
        if (r3 != r9) goto L_0x008f;
    L_0x009e:
        r3 = r8.c();
        r9 = r7.e();
        if (r3 == r9) goto L_0x00c2;
    L_0x00a8:
        r3 = r8.c();
        r6.c(r3);
    L_0x00af:
        if (r2 != 0) goto L_0x00d4;
    L_0x00b1:
        r1 = r6.e();
    L_0x00b5:
        return r1;
    L_0x00b6:
        r3 = r6.c();
        r3 = r3.e();
        r8.c(r3);
        goto L_0x009e;
    L_0x00c2:
        r9 = r11.H;
        r3 = r2 + 1;
        r10 = r8.e();
        r9[r2] = r10;
        r2 = r6.d();
        if (r2 != 0) goto L_0x0178;
    L_0x00d2:
        r2 = r3;
        goto L_0x00af;
    L_0x00d4:
        r3 = r11.r();
        r9 = r7.e();
        r3 = r3[r9];
        r4.a(r3);
        r3 = r7.e();
        r3 = r3 + 1;
        r4.d(r3);
        r3 = r6.b();
        if (r3 == r0) goto L_0x016b;
    L_0x00f0:
        r3 = r6.e();
        r7 = r11.v;
        r7 = r7.h();
        if (r3 <= r7) goto L_0x00b5;
    L_0x00fc:
        r3 = r6.a();
        r3 = r3.b();
        r8.c(r3);
        r3 = r8.a();
        r7 = r4.a();
        if (r3 == r7) goto L_0x011e;
    L_0x0111:
        r8.f();
        r3 = r8.a();
        r7 = r4.a();
        if (r3 != r7) goto L_0x0111;
    L_0x011e:
        r3 = r8.b();
        r3 = r3 + -1;
        r7 = r6.a();
        r7 = r7.a();
        r8 = r6.b();
        r7 = r7 - r8;
        r7 = r7 - r3;
        r8 = r3 * 2;
        if (r8 > r7) goto L_0x0160;
    L_0x0136:
        r3 = r3 * 5;
        if (r3 <= r7) goto L_0x015e;
    L_0x013a:
        r0 = r0 + 1;
        r4.b(r0);
        r0 = r2;
    L_0x0140:
        r2 = r11.H;
        r0 = r0 + -1;
        r2 = r2[r0];
        r5.c(r2);
        r2 = r6.a(r11, r5, r4);
        r6.c(r2);
        r2 = r6.e();
        if (r2 == 0) goto L_0x00b5;
    L_0x0156:
        if (r0 != 0) goto L_0x0140;
    L_0x0158:
        r1 = r6.e();
        goto L_0x00b5;
    L_0x015e:
        r0 = r1;
        goto L_0x013a;
    L_0x0160:
        r0 = r3 * 2;
        r3 = r7 * 3;
        r0 = r0 + r3;
        r0 = r0 + -1;
        r3 = r7 * 2;
        r0 = r0 / r3;
        goto L_0x013a;
    L_0x016b:
        r0 = r6.c();
        r0 = r0.b();
        r4.b(r0);
        r0 = r2;
        goto L_0x0140;
    L_0x0178:
        r2 = r3;
        r3 = r1;
        goto L_0x0069;
    L_0x017c:
        r3 = r1;
        goto L_0x0069;
    L_0x017f:
        r3 = r1;
        r2 = r0;
        goto L_0x0052;
    L_0x0183:
        r3 = r1;
        r2 = r1;
        goto L_0x0052;
        */
        throw new UnsupportedOperationException("Method not decompiled: de.innosystec.unrar.unpack.ppm.b.a(boolean, de.innosystec.unrar.unpack.ppm.i):int");
    }

    private void v() {
        t();
        this.q = 0;
    }

    private void w() {
        j jVar = this.B;
        jVar.a(this.f);
        i a = this.z.a(r());
        i a2 = this.A.a(r());
        c a3 = this.F.a(r());
        c a4 = this.G.a(r());
        a3.c(this.c.d());
        if (jVar.b() < 31 && a3.e() != 0) {
            if (a3.b() != 1) {
                a.c(a3.a().b());
                if (a.a() != jVar.a()) {
                    do {
                        a.f();
                    } while (a.a() != jVar.a());
                    a2.c(a.e() - 6);
                    if (a.b() >= a2.b()) {
                        i.a(a, a2);
                        a.d();
                    }
                }
                if (a.b() < a.cd) {
                    a.d(2);
                    a3.a().b(2);
                }
            } else {
                a.c(a3.c().e());
                if (a.b() < 32) {
                    a.d(1);
                }
            }
        }
        if (this.i == 0) {
            this.f.e(a(true, a));
            this.c.c(this.f.c());
            this.e.c(this.f.c());
            if (this.c.e() == 0) {
                v();
                return;
            }
            return;
        }
        this.v.j()[this.v.h()] = (byte) jVar.a();
        this.v.b();
        a4.c(this.v.h());
        if (this.v.h() >= this.v.f()) {
            v();
            return;
        }
        int i;
        if (jVar.c() != 0) {
            if (jVar.c() <= this.v.h()) {
                jVar.d(a(false, a));
                if (jVar.c() == 0) {
                    v();
                    return;
                }
            }
            i = this.i - 1;
            this.i = i;
            if (i == 0) {
                a4.c(jVar.c());
                if (this.e.e() != this.c.e()) {
                    this.v.d(1);
                }
            }
        } else {
            this.f.e(a4.e());
            jVar.a(this.c);
        }
        int b = this.c.b();
        int a5 = (this.c.a().a() - b) - (jVar.b() - 1);
        a3.c(this.e.e());
        while (a3.e() != this.c.e()) {
            int i2;
            int i3;
            int b2 = a3.b();
            if (b2 != 1) {
                if ((b2 & 1) == 0) {
                    a3.a().a_(this.v.a(a3.a().b(), b2 >>> 1));
                    if (a3.a().b() == 0) {
                        v();
                        return;
                    }
                }
                i2 = b2 * 2 < b ? 1 : 0;
                if (b2 * 4 <= b) {
                    i = 1;
                } else {
                    i = 0;
                }
                if (a3.a().a() <= b2 * 8) {
                    i3 = 1;
                } else {
                    i3 = 0;
                }
                a3.a().b(((i & i3) * 2) + i2);
            } else {
                a.c(this.v.b(1));
                if (a.e() == 0) {
                    v();
                    return;
                }
                a.a(a3.c());
                a3.a().a(a);
                if (a.b() < 30) {
                    a.d(a.b());
                } else {
                    a.b(120);
                }
                a a6 = a3.a();
                i2 = this.h + a.b();
                if (b > 3) {
                    i = 1;
                } else {
                    i = 0;
                }
                a6.a(i + i2);
            }
            i3 = (a3.a().a() + 6) * (jVar.b() * 2);
            i2 = a5 + a3.a().a();
            if (i3 < i2 * 6) {
                if (i3 > i2) {
                    i = 1;
                } else {
                    i = 0;
                }
                i = (i3 >= i2 * 4 ? 1 : 0) + (i + 1);
                a3.a().b(3);
            } else {
                if (i3 >= i2 * 9) {
                    i = 1;
                } else {
                    i = 0;
                }
                int i4 = (i + 4) + (i3 >= i2 * 12 ? 1 : 0);
                if (i3 >= i2 * 15) {
                    i = 1;
                } else {
                    i = 0;
                }
                i += i4;
                a3.a().b(i);
            }
            a.c(a3.a().b() + (b2 * 6));
            a.a(a4);
            a.a(jVar.a());
            a.b(i);
            a3.a(b2 + 1);
            a3.c(a3.d());
        }
        i = jVar.c();
        this.e.c(i);
        this.c.c(i);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ModelPPM[");
        stringBuilder.append("\n  numMasked=");
        stringBuilder.append(this.g);
        stringBuilder.append("\n  initEsc=");
        stringBuilder.append(this.h);
        stringBuilder.append("\n  orderFall=");
        stringBuilder.append(this.i);
        stringBuilder.append("\n  maxOrder=");
        stringBuilder.append(this.j);
        stringBuilder.append("\n  runLength=");
        stringBuilder.append(this.k);
        stringBuilder.append("\n  initRL=");
        stringBuilder.append(this.l);
        stringBuilder.append("\n  escCount=");
        stringBuilder.append(this.q);
        stringBuilder.append("\n  prevSuccess=");
        stringBuilder.append(this.r);
        stringBuilder.append("\n  foundState=");
        stringBuilder.append(this.f);
        stringBuilder.append("\n  coder=");
        stringBuilder.append(this.u);
        stringBuilder.append("\n  subAlloc=");
        stringBuilder.append(this.v);
        stringBuilder.append("\n]");
        return stringBuilder.toString();
    }
}
