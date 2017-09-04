package de.innosystec.unrar.unpack;

import com.etrump.jni.ETConverter;
import com.tencent.openqq.protocol.imsdk.im_common;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import de.innosystec.unrar.exception.RarException;
import de.innosystec.unrar.unpack.vm.a;
import java.io.IOException;
import java.util.Arrays;

/* compiled from: Unpack15 */
public abstract class c extends a {
    static int[] O;
    static int[] P;
    static int[] Q;
    static int[] R;
    private static int[] S;
    private static int[] T = new int[]{40960, 49152, 53248, 57344, 59904, 60928, 61440, 61952, 62016, 65535};
    private static int[] U;
    private static int[] V = new int[]{32768, 49152, 57344, 61952, 61952, 61952, 61952, 61952, 65535};
    private static int[] W;
    private static int[] X = new int[]{8192, 49152, 57344, 61440, 61952, 61952, 63456, 65535};
    private static int[] Y;
    private static int[] Z = new int[]{4096, 9216, 32768, 49152, 64000, 65535, 65535, 65535};
    private static int[] a = new int[]{32768, 40960, 49152, 53248, 57344, 59904, 60928, 61440, 61952, 61952, 65535};
    private static int[] aa;
    private static int[] ab = new int[]{2048, 9216, 60928, 65152, 65535, 65535, 65535};
    private static int[] ac;
    private static int[] ad = new int[]{65280, 65535, 65535, 65535, 65535, 65535};
    private static int[] ae;
    protected int A;
    protected int B;
    protected int C;
    protected int D;
    protected int E;
    protected int F;
    protected int G;
    protected int H;
    protected int I;
    protected int J;
    protected int K;
    protected int L;
    protected int M;
    protected int N;
    protected int b;
    protected boolean c;
    protected boolean d;
    protected a e;
    protected boolean f;
    protected int g;
    protected long h;
    protected byte[] i;
    protected int[] j = new int[4];
    protected int k;
    protected int l;
    protected int m;
    protected int[] n = new int[256];
    protected int[] o = new int[256];
    protected int[] p = new int[256];
    protected int[] q = new int[256];
    protected int[] r = new int[256];
    protected int[] s = new int[256];
    protected int[] t = new int[256];
    protected int[] u = new int[256];
    protected int[] v = new int[256];
    protected int[] w = new int[256];
    protected int[] x = new int[256];
    protected int y;
    protected int z;

    protected abstract void a(boolean z);

    static {
        int[] iArr = new int[13];
        iArr[3] = 2;
        iArr[4] = 3;
        iArr[5] = 5;
        iArr[6] = 7;
        iArr[7] = 11;
        iArr[8] = 16;
        iArr[9] = 20;
        iArr[10] = 24;
        iArr[11] = 32;
        iArr[12] = 32;
        S = iArr;
        iArr = new int[13];
        U = iArr;
        iArr = new int[13];
        W = iArr;
        iArr = new int[13];
        Y = iArr;
        iArr = new int[13];
        iArr[6] = 2;
        iArr[7] = 7;
        iArr[8] = 53;
        iArr[9] = Opcodes.INVOKE_SUPER_RANGE;
        iArr[10] = 233;
        aa = iArr;
        iArr = new int[13];
        ac = iArr;
        iArr = new int[13];
        ae = iArr;
        iArr = new int[16];
        iArr[0] = 1;
        iArr[1] = 3;
        iArr[2] = 4;
        iArr[3] = 4;
        iArr[4] = 5;
        iArr[5] = 6;
        iArr[6] = 7;
        iArr[7] = 8;
        iArr[8] = 8;
        iArr[9] = 4;
        iArr[10] = 4;
        iArr[11] = 5;
        iArr[12] = 6;
        iArr[13] = 6;
        iArr[14] = 4;
        O = iArr;
        iArr = new int[15];
        iArr[1] = 160;
        iArr[2] = 208;
        iArr[3] = Opcodes.SHL_INT_LIT8;
        iArr[4] = ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK;
        iArr[5] = 248;
        iArr[6] = 252;
        iArr[7] = 254;
        iArr[8] = 255;
        iArr[9] = Opcodes.AND_LONG_2ADDR;
        iArr[10] = 128;
        iArr[11] = Opcodes.ADD_INT;
        iArr[12] = Opcodes.SHL_INT;
        iArr[13] = Opcodes.SUB_LONG;
        iArr[14] = Opcodes.ADD_INT_2ADDR;
        P = iArr;
        iArr = new int[16];
        iArr[0] = 2;
        iArr[1] = 3;
        iArr[2] = 3;
        iArr[3] = 3;
        iArr[4] = 4;
        iArr[5] = 4;
        iArr[6] = 5;
        iArr[7] = 6;
        iArr[8] = 6;
        iArr[9] = 4;
        iArr[10] = 4;
        iArr[11] = 5;
        iArr[12] = 6;
        iArr[13] = 6;
        iArr[14] = 4;
        Q = iArr;
        iArr = new int[15];
        iArr[1] = 64;
        iArr[2] = 96;
        iArr[3] = 160;
        iArr[4] = 208;
        iArr[5] = Opcodes.SHL_INT_LIT8;
        iArr[6] = ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK;
        iArr[7] = 248;
        iArr[8] = 252;
        iArr[9] = Opcodes.AND_LONG_2ADDR;
        iArr[10] = 128;
        iArr[11] = Opcodes.ADD_INT;
        iArr[12] = Opcodes.SHL_INT;
        iArr[13] = Opcodes.SUB_LONG;
        iArr[14] = Opcodes.ADD_INT_2ADDR;
        R = iArr;
    }

    protected void b(boolean z) throws IOException, RarException {
        if (this.c) {
            this.k = this.l;
        } else {
            a(z);
            c(z);
            c();
            if (z) {
                this.k = this.l;
            } else {
                h();
                this.k = 0;
            }
            this.h--;
        }
        if (this.h >= 0) {
            g();
            this.I = 8;
        }
        while (this.h >= 0) {
            this.k &= 4194303;
            if (this.ak > this.g - 30 && !c()) {
                break;
            }
            if (((this.l - this.k) & 4194303) < im_common.WPA_QZONE && this.l != this.k) {
                i();
                if (this.c) {
                    return;
                }
            }
            if (this.G != 0) {
                f();
            } else {
                int i = this.I - 1;
                this.I = i;
                if (i < 0) {
                    g();
                    this.I = 7;
                }
                if ((this.y & 128) != 0) {
                    this.y <<= 1;
                    if (this.K > this.J) {
                        e();
                    } else {
                        f();
                    }
                } else {
                    this.y <<= 1;
                    i = this.I - 1;
                    this.I = i;
                    if (i < 0) {
                        g();
                        this.I = 7;
                    }
                    if ((this.y & 128) != 0) {
                        this.y <<= 1;
                        if (this.K > this.J) {
                            f();
                        } else {
                            e();
                        }
                    } else {
                        this.y <<= 1;
                        d();
                    }
                }
            }
        }
        i();
    }

    protected boolean c() throws IOException, RarException {
        int i = this.g - this.ak;
        if (i < 0) {
            return false;
        }
        if (this.ak > 16384) {
            if (i > 0) {
                System.arraycopy(this.am, this.ak, this.am, 0, i);
            }
            this.ak = 0;
            this.g = i;
        } else {
            i = this.g;
        }
        i = this.e.a(this.am, i, (32768 - i) & -16);
        if (i > 0) {
            this.g += i;
        }
        this.b = this.g - 30;
        return i != -1;
    }

    private int a(int i) {
        return i == 1 ? this.E + 3 : O[i];
    }

    private int b(int i) {
        return i == 3 ? this.E + 3 : Q[i];
    }

    protected void d() {
        this.F = 0;
        int n = n();
        if (this.H == 2) {
            d(1);
            if (n >= 32768) {
                a(this.M, this.N);
                return;
            } else {
                n <<= 1;
                this.H = 0;
            }
        }
        int i = n >>> 8;
        if (this.B < 37) {
            n = 0;
            while (((P[n] ^ i) & ((255 >>> a(n)) ^ -1)) != 0) {
                n++;
            }
            d(a(n));
            i = n;
        } else {
            n = 0;
            while (((R[n] ^ i) & ((255 >> b(n)) ^ -1)) != 0) {
                n++;
            }
            d(b(n));
            i = n;
        }
        int i2;
        int i3;
        int[] iArr;
        if (i < 9) {
            this.H = 0;
            this.B += i;
            this.B -= this.B >> 4;
            n = a(n(), 5, Z, aa) & 255;
            i2 = this.o[n];
            n--;
            if (n != -1) {
                int[] iArr2 = this.s;
                iArr2[i2] = iArr2[i2] - 1;
                i3 = this.o[n];
                int[] iArr3 = this.s;
                iArr3[i3] = iArr3[i3] + 1;
                this.o[n + 1] = i3;
                this.o[n] = i2;
            }
            n = i + 2;
            iArr = this.j;
            i3 = this.m;
            this.m = i3 + 1;
            i2++;
            iArr[i3] = i2;
            this.m &= 3;
            this.N = n;
            this.M = i2;
            a(i2, n);
        } else if (i == 9) {
            this.H++;
            a(this.M, this.N);
        } else if (i == 14) {
            this.H = 0;
            n = a(n(), 3, T, U) + 5;
            i2 = (n() >> 1) | 32768;
            d(15);
            this.N = n;
            this.M = i2;
            a(i2, n);
        } else {
            this.H = 0;
            i2 = this.j[(this.m - (i - 9)) & 3];
            n = a(n(), 2, a, S) + 2;
            if (n == 257 && i == 10) {
                this.E ^= 1;
                return;
            }
            if (i2 > 256) {
                n++;
            }
            if (i2 >= this.L) {
                n++;
            }
            iArr = this.j;
            i3 = this.m;
            this.m = i3 + 1;
            iArr[i3] = i2;
            this.m &= 3;
            this.N = n;
            this.M = i2;
            a(i2, n);
        }
    }

    protected void e() {
        int i;
        int i2;
        int i3;
        int i4 = 0;
        this.F = 0;
        this.K += 16;
        if (this.K > 255) {
            this.K = Opcodes.ADD_INT;
            this.J >>>= 1;
        }
        int i5 = this.C;
        int n = n();
        if (this.C >= 122) {
            n = a(n, 3, T, U);
        } else if (this.C >= 64) {
            n = a(n, 2, a, S);
        } else if (n < 256) {
            d(16);
        } else {
            while (((n << i4) & 32768) == 0) {
                i4++;
            }
            d(i4 + 1);
            n = i4;
        }
        this.C += n;
        this.C -= this.C >>> 5;
        i4 = n();
        if (this.A > 10495) {
            i4 = a(i4, 5, Z, aa);
        } else if (this.A > 1791) {
            i4 = a(i4, 5, X, Y);
        } else {
            i4 = a(i4, 4, V, W);
        }
        this.A += i4;
        this.A -= this.A >> 8;
        while (true) {
            i = this.p[i4 & 255];
            int[] iArr = this.w;
            i2 = i + 1;
            i &= 255;
            i3 = iArr[i];
            iArr[i] = i3 + 1;
            if ((i2 & 255) != 0) {
                break;
            }
            a(this.p, this.w);
        }
        this.p[i4] = this.p[i3];
        this.p[i3] = i2;
        i4 = ((65280 & i2) | (n() >>> 8)) >>> 1;
        d(7);
        i = this.D;
        if (!(n == 1 || n == 4)) {
            if (n == 0 && i4 <= this.L) {
                this.D++;
                this.D -= this.D >> 8;
            } else if (this.D > 0) {
                this.D--;
            }
        }
        n += 3;
        if (i4 >= this.L) {
            n++;
        }
        if (i4 <= 256) {
            n += 8;
        }
        if (i > Opcodes.ADD_INT_2ADDR || (this.z >= 10752 && i5 < 64)) {
            this.L = 32512;
        } else {
            this.L = 8193;
        }
        int[] iArr2 = this.j;
        i = this.m;
        this.m = i + 1;
        iArr2[i] = i4;
        this.m &= 3;
        this.N = n;
        this.M = i4;
        a(i4, n);
    }

    protected void f() {
        int a;
        int n = n();
        if (this.z > 30207) {
            a = a(n, 8, ad, ae);
        } else if (this.z > 24063) {
            a = a(n, 6, ab, ac);
        } else if (this.z > 13823) {
            a = a(n, 5, Z, aa);
        } else if (this.z > 3583) {
            a = a(n, 5, X, Y);
        } else {
            a = a(n, 4, V, W);
        }
        a &= 255;
        if (this.G != 0) {
            if (a == 0 && n > 4095) {
                a = 256;
            }
            a--;
            if (a == -1) {
                a = n();
                d(1);
                if ((32768 & a) != 0) {
                    this.G = 0;
                    this.F = 0;
                    return;
                }
                a = (a & 16384) != 0 ? 4 : 3;
                d(1);
                int a2 = (a(n(), 5, Z, aa) << 5) | (n() >>> 11);
                d(5);
                a(a2, a);
                return;
            }
        }
        a2 = this.F;
        this.F = a2 + 1;
        if (a2 >= 16 && this.I == 0) {
            this.G = 1;
        }
        this.z += a;
        this.z -= this.z >>> 8;
        this.J += 16;
        if (this.J > 255) {
            this.J = Opcodes.ADD_INT;
            this.K >>>= 1;
        }
        byte[] bArr = this.i;
        n = this.k;
        this.k = n + 1;
        bArr[n] = (byte) (this.n[a] >>> 8);
        this.h--;
        while (true) {
            a2 = this.n[a];
            int[] iArr = this.v;
            int i = a2 + 1;
            a2 &= 255;
            int i2 = iArr[a2];
            iArr[a2] = i2 + 1;
            if ((i & 255) > Opcodes.OR_LONG) {
                a(this.n, this.v);
            } else {
                this.n[a] = this.n[i2];
                this.n[i2] = i;
                return;
            }
        }
    }

    protected void g() {
        int a = a(n(), 5, Z, aa);
        while (true) {
            int i = this.q[a];
            this.y = i >>> 8;
            int[] iArr = this.x;
            int i2 = i + 1;
            i &= 255;
            int i3 = iArr[i];
            iArr[i] = i3 + 1;
            if ((i2 & 255) != 0) {
                this.q[a] = this.q[i3];
                this.q[i3] = i2;
                return;
            }
            a(this.q, this.x);
        }
    }

    protected void c(boolean z) {
        if (!z) {
            this.E = 0;
            this.F = 0;
            this.D = 0;
            this.C = 0;
            this.B = 0;
            this.A = 0;
            this.z = 13568;
            this.L = 8193;
            this.K = 128;
            this.J = 128;
        }
        this.I = 0;
        this.y = 0;
        this.G = 0;
        this.H = 0;
        this.g = 0;
    }

    protected void h() {
        for (int i = 0; i < 256; i++) {
            int[] iArr = this.r;
            int[] iArr2 = this.s;
            this.t[i] = i;
            iArr2[i] = i;
            iArr[i] = i;
            this.u[i] = ((i ^ -1) + 1) & 255;
            iArr = this.n;
            int i2 = i << 8;
            this.p[i] = i2;
            iArr[i] = i2;
            this.o[i] = i;
            this.q[i] = (((i ^ -1) + 1) & 255) << 8;
        }
        Arrays.fill(this.v, 0);
        Arrays.fill(this.w, 0);
        Arrays.fill(this.x, 0);
        a(this.p, this.w);
    }

    protected void a(int[] iArr, int[] iArr2) {
        int i = 0;
        for (int i2 = 7; i2 >= 0; i2--) {
            int i3 = 0;
            while (i3 < 32) {
                iArr[i] = (iArr[i] & -256) | i2;
                i3++;
                i++;
            }
        }
        Arrays.fill(iArr2, 0);
        for (i = 6; i >= 0; i--) {
            iArr2[i] = (7 - i) * 32;
        }
    }

    protected void a(int i, int i2) {
        this.h -= (long) i2;
        while (true) {
            int i3 = i2 - 1;
            if (i2 != 0) {
                this.i[this.k] = this.i[(this.k - i) & 4194303];
                this.k = (this.k + 1) & 4194303;
                i2 = i3;
            } else {
                return;
            }
        }
    }

    protected int a(int i, int i2, int[] iArr, int[] iArr2) {
        int i3 = 0;
        int i4 = i & 65520;
        int i5 = 0;
        while (iArr[i5] <= i4) {
            i2++;
            i5++;
        }
        d(i2);
        if (i5 != 0) {
            i3 = iArr[i5 - 1];
        }
        return ((i4 - i3) >>> (16 - i2)) + iArr2[i2];
    }

    protected void i() throws IOException {
        if (this.k != this.l) {
            this.f = true;
        }
        if (this.k < this.l) {
            this.e.b(this.i, this.l, (-this.l) & 4194303);
            this.e.b(this.i, 0, this.k);
            this.d = true;
        } else {
            this.e.b(this.i, this.l, this.k - this.l);
        }
        this.l = this.k;
    }
}
