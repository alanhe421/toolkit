package de.innosystec.unrar.unpack;

import com.tencent.openqq.protocol.imsdk.im_common;
import com.tencent.tesla.soload.SoLoadCore;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import de.innosystec.unrar.exception.RarException;
import de.innosystec.unrar.unpack.decode.a;
import de.innosystec.unrar.unpack.decode.b;
import de.innosystec.unrar.unpack.decode.c;
import de.innosystec.unrar.unpack.decode.e;
import de.innosystec.unrar.unpack.decode.f;
import de.innosystec.unrar.unpack.decode.g;
import de.innosystec.unrar.unpack.decode.h;
import java.io.IOException;
import java.util.Arrays;
import tencent.tls.tools.I18nMsg;

/* compiled from: Unpack20 */
public abstract class d extends c {
    public static final int[] ae;
    public static final byte[] af;
    public static final int[] ag;
    public static final int[] ah;
    public static final int[] ai;
    public static final int[] aj = new int[]{2, 2, 3, 4, 5, 6, 6, 6};
    protected g[] S = new g[4];
    protected byte[] T = new byte[I18nMsg.ZH_HK];
    protected int U;
    protected int V;
    protected int W;
    protected int X;
    protected a[] Y = new a[4];
    protected e Z = new e();
    protected de.innosystec.unrar.unpack.decode.d aa = new de.innosystec.unrar.unpack.decode.d();
    protected f ab = new f();
    protected h ac = new h();
    protected b ad = new b();

    static {
        int[] iArr = new int[28];
        iArr[1] = 1;
        iArr[2] = 2;
        iArr[3] = 3;
        iArr[4] = 4;
        iArr[5] = 5;
        iArr[6] = 6;
        iArr[7] = 7;
        iArr[8] = 8;
        iArr[9] = 10;
        iArr[10] = 12;
        iArr[11] = 14;
        iArr[12] = 16;
        iArr[13] = 20;
        iArr[14] = 24;
        iArr[15] = 28;
        iArr[16] = 32;
        iArr[17] = 40;
        iArr[18] = 48;
        iArr[19] = 56;
        iArr[20] = 64;
        iArr[21] = 80;
        iArr[22] = 96;
        iArr[23] = 112;
        iArr[24] = 128;
        iArr[25] = 160;
        iArr[26] = Opcodes.AND_LONG_2ADDR;
        iArr[27] = Opcodes.SHL_INT_LIT8;
        ae = iArr;
        r0 = new byte[28];
        af = r0;
        iArr = new int[48];
        iArr[1] = 1;
        iArr[2] = 2;
        iArr[3] = 3;
        iArr[4] = 4;
        iArr[5] = 6;
        iArr[6] = 8;
        iArr[7] = 12;
        iArr[8] = 16;
        iArr[9] = 24;
        iArr[10] = 32;
        iArr[11] = 48;
        iArr[12] = 64;
        iArr[13] = 96;
        iArr[14] = 128;
        iArr[15] = Opcodes.AND_LONG_2ADDR;
        iArr[16] = 256;
        iArr[17] = 384;
        iArr[18] = 512;
        iArr[19] = Opcodes.FILL_ARRAY_DATA_PAYLOAD;
        iArr[20] = 1024;
        iArr[21] = 1536;
        iArr[22] = 2048;
        iArr[23] = 3072;
        iArr[24] = 4096;
        iArr[25] = 6144;
        iArr[26] = 8192;
        iArr[27] = 12288;
        iArr[28] = 16384;
        iArr[29] = 24576;
        iArr[30] = 32768;
        iArr[31] = 49152;
        iArr[32] = 65536;
        iArr[33] = 98304;
        iArr[34] = SoLoadCore.IF_SO_CONFIG_EXIST;
        iArr[35] = 196608;
        iArr[36] = 262144;
        iArr[37] = 327680;
        iArr[38] = 393216;
        iArr[39] = 458752;
        iArr[40] = 524288;
        iArr[41] = 589824;
        iArr[42] = 655360;
        iArr[43] = 720896;
        iArr[44] = 786432;
        iArr[45] = 851968;
        iArr[46] = 917504;
        iArr[47] = 983040;
        ag = iArr;
        iArr = new int[48];
        ah = iArr;
        iArr = new int[8];
        iArr[1] = 4;
        iArr[2] = 8;
        iArr[3] = 16;
        iArr[4] = 32;
        iArr[5] = 64;
        iArr[6] = 128;
        iArr[7] = Opcodes.AND_LONG_2ADDR;
        ai = iArr;
    }

    protected void d(boolean z) throws IOException, RarException {
        if (this.c) {
            this.k = this.l;
        } else {
            a(z);
            if (!c()) {
                return;
            }
            if (z || j()) {
                this.h--;
            } else {
                return;
            }
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
            int a;
            byte[] bArr;
            int i;
            if (this.U != 0) {
                a = a(this.S[this.W]);
                if (a != 256) {
                    bArr = this.i;
                    i = this.k;
                    this.k = i + 1;
                    bArr[i] = b(a);
                    a = this.W + 1;
                    this.W = a;
                    if (a == this.V) {
                        this.W = 0;
                    }
                    this.h--;
                } else if (!j()) {
                    break;
                }
            } else {
                a = a(this.Z);
                if (a < 256) {
                    bArr = this.i;
                    i = this.k;
                    this.k = i + 1;
                    bArr[i] = (byte) a;
                    this.h--;
                } else if (a > 269) {
                    i = a - 270;
                    a = ae[i] + 3;
                    byte b = af[i];
                    if (b > (byte) 0) {
                        a += m() >>> (16 - b);
                        c(b);
                    }
                    i = a(this.aa);
                    r1 = ag[i] + 1;
                    i = ah[i];
                    if (i > 0) {
                        r1 += m() >>> (16 - i);
                        c(i);
                    }
                    if (r1 >= 8192) {
                        a++;
                        if (((long) r1) >= 262144) {
                            a++;
                        }
                    }
                    b(a, r1);
                } else if (a == 269) {
                    if (!j()) {
                        break;
                    }
                } else if (a == 256) {
                    b(this.N, this.M);
                } else if (a < 261) {
                    r1 = this.j[(this.m - (a - 256)) & 3];
                    i = a(this.ac);
                    a = ae[i] + 2;
                    byte b2 = af[i];
                    if (b2 > (byte) 0) {
                        a += m() >>> (16 - b2);
                        c(b2);
                    }
                    if (r1 >= 257) {
                        a++;
                        if (r1 >= 8192) {
                            a++;
                            if (r1 >= 262144) {
                                a++;
                            }
                        }
                    }
                    b(a, r1);
                } else if (a < im_common.WPA_QZONE) {
                    i = a - 261;
                    a = ai[i] + 1;
                    r1 = aj[i];
                    if (r1 > 0) {
                        a += m() >>> (16 - r1);
                        c(r1);
                    }
                    b(2, a);
                }
            }
        }
        k();
        i();
    }

    protected void b(int i, int i2) {
        int[] iArr = this.j;
        int i3 = this.m;
        this.m = i3 + 1;
        iArr[i3 & 3] = i2;
        this.M = i2;
        this.N = i;
        this.h -= (long) i;
        int i4 = this.k - i2;
        int i5;
        if (i4 >= 4194004 || this.k >= 4194004) {
            while (true) {
                i5 = i - 1;
                if (i != 0) {
                    i3 = i4 + 1;
                    this.i[this.k] = this.i[i4 & 4194303];
                    this.k = (this.k + 1) & 4194303;
                    i4 = i3;
                    i = i5;
                } else {
                    return;
                }
            }
        }
        byte[] bArr = this.i;
        i5 = this.k;
        this.k = i5 + 1;
        int i6 = i4 + 1;
        bArr[i5] = this.i[i4];
        bArr = this.i;
        i5 = this.k;
        this.k = i5 + 1;
        i4 = i6 + 1;
        bArr[i5] = this.i[i6];
        while (i > 2) {
            i--;
            byte[] bArr2 = this.i;
            int i7 = this.k;
            this.k = i7 + 1;
            i3 = i4 + 1;
            bArr2[i7] = this.i[i4];
            i4 = i3;
        }
    }

    protected void a(byte[] bArr, int i, c cVar, int i2) {
        int i3;
        int[] iArr = new int[16];
        int[] iArr2 = new int[16];
        Arrays.fill(iArr, 0);
        Arrays.fill(cVar.b(), 0);
        for (i3 = 0; i3 < i2; i3++) {
            int i4 = bArr[i + i3] & 15;
            iArr[i4] = iArr[i4] + 1;
        }
        iArr[0] = 0;
        iArr2[0] = 0;
        cVar.c()[0] = 0;
        cVar.a()[0] = 0;
        long j = 0;
        int i5 = 1;
        while (i5 < 16) {
            long j2 = 2 * (j + ((long) iArr[i5]));
            j = j2 << (15 - i5);
            if (j > 65535) {
                j = 65535;
            }
            cVar.a()[i5] = (int) j;
            i4 = cVar.c()[i5 - 1] + iArr[i5 - 1];
            cVar.c()[i5] = i4;
            iArr2[i5] = i4;
            i5++;
            j = j2;
        }
        for (i3 = 0; i3 < i2; i3++) {
            if (bArr[i + i3] != (byte) 0) {
                int[] b = cVar.b();
                int i6 = bArr[i + i3] & 15;
                int i7 = iArr2[i6];
                iArr2[i6] = i7 + 1;
                b[i7] = i3;
            }
        }
        cVar.a(i2);
    }

    protected int a(c cVar) {
        int i = 1;
        long m = (long) (m() & 65534);
        int[] a = cVar.a();
        if (m < ((long) a[8])) {
            if (m < ((long) a[4])) {
                if (m < ((long) a[2])) {
                    if (m >= ((long) a[1])) {
                        i = 2;
                    }
                } else if (m < ((long) a[3])) {
                    i = 3;
                } else {
                    i = 4;
                }
            } else if (m < ((long) a[6])) {
                if (m < ((long) a[5])) {
                    i = 5;
                } else {
                    i = 6;
                }
            } else if (m < ((long) a[7])) {
                i = 7;
            } else {
                i = 8;
            }
        } else if (m < ((long) a[12])) {
            if (m < ((long) a[10])) {
                if (m < ((long) a[9])) {
                    i = 9;
                } else {
                    i = 10;
                }
            } else if (m < ((long) a[11])) {
                i = 11;
            } else {
                i = 12;
            }
        } else if (m >= ((long) a[14])) {
            i = 15;
        } else if (m < ((long) a[13])) {
            i = 13;
        } else {
            i = 14;
        }
        c(i);
        i = ((((int) m) - a[i - 1]) >>> (16 - i)) + cVar.c()[i];
        if (i >= cVar.d()) {
            i = 0;
        }
        return cVar.b()[i];
    }

    protected boolean j() throws IOException, RarException {
        int i = 0;
        byte[] bArr = new byte[19];
        byte[] bArr2 = new byte[I18nMsg.ZH_HK];
        if (this.ak > this.g - 25 && !c()) {
            return false;
        }
        int i2;
        int m = m();
        this.U = 32768 & m;
        if ((m & 16384) == 0) {
            Arrays.fill(this.T, (byte) 0);
        }
        c(2);
        if (this.U != 0) {
            this.V = ((m >>> 12) & 3) + 1;
            if (this.W >= this.V) {
                this.W = 0;
            }
            c(2);
            m = this.V * 257;
        } else {
            m = 374;
        }
        for (i2 = 0; i2 < 19; i2++) {
            bArr[i2] = (byte) (m() >>> 12);
            c(4);
        }
        a(bArr, 0, this.ad, 19);
        i2 = 0;
        while (i2 < m) {
            if (this.ak <= this.g - 5 || c()) {
                int a = a(this.ad);
                if (a >= 16) {
                    int i3;
                    if (a != 16) {
                        if (a == 17) {
                            a = (m() >>> 13) + 3;
                            c(3);
                        } else {
                            a = (m() >>> 9) + 11;
                            c(7);
                        }
                        while (true) {
                            i3 = a - 1;
                            if (a <= 0 || i2 >= m) {
                                break;
                            }
                            a = i2 + 1;
                            bArr2[i2] = (byte) 0;
                            i2 = a;
                            a = i3;
                        }
                    } else {
                        a = (m() >>> 14) + 3;
                        c(2);
                        while (true) {
                            i3 = a - 1;
                            if (a <= 0 || i2 >= m) {
                                break;
                            }
                            bArr2[i2] = bArr2[i2 - 1];
                            i2++;
                            a = i3;
                        }
                    }
                } else {
                    bArr2[i2] = (byte) ((a + this.T[i2]) & 15);
                    i2++;
                }
            } else {
                return false;
            }
        }
        if (this.ak > this.g) {
            return true;
        }
        if (this.U != 0) {
            for (m = 0; m < this.V; m++) {
                a(bArr2, m * 257, this.S[m], 257);
            }
        } else {
            a(bArr2, 0, this.Z, 298);
            a(bArr2, 298, this.aa, 48);
            a(bArr2, 346, this.ac, 28);
        }
        while (i < this.T.length) {
            this.T[i] = bArr2[i];
            i++;
        }
        return true;
    }

    protected void e(boolean z) {
        if (!z) {
            this.W = 0;
            this.X = 0;
            this.V = 1;
            Arrays.fill(this.Y, new a());
            Arrays.fill(this.T, (byte) 0);
        }
    }

    protected void k() throws IOException, RarException {
        if (this.g < this.ak + 5) {
            return;
        }
        if (this.U != 0) {
            if (a(this.S[this.W]) == 256) {
                j();
            }
        } else if (a(this.Z) == 269) {
            j();
        }
    }

    protected byte b(int i) {
        int i2 = 1;
        a aVar = this.Y[this.W];
        aVar.a(aVar.a() + 1);
        aVar.e(aVar.d());
        aVar.d(aVar.c());
        aVar.c(aVar.m() - aVar.b());
        aVar.b(aVar.m());
        int l = ((((((aVar.l() * 8) + (aVar.g() * aVar.b())) + ((aVar.h() * aVar.c()) + (aVar.i() * aVar.d()))) + ((aVar.j() * aVar.e()) + (aVar.k() * this.X))) >>> 3) & 255) - i;
        int i3 = ((byte) i) << 3;
        int[] f = aVar.f();
        f[0] = f[0] + Math.abs(i3);
        f = aVar.f();
        f[1] = f[1] + Math.abs(i3 - aVar.b());
        f = aVar.f();
        f[2] = f[2] + Math.abs(aVar.b() + i3);
        f = aVar.f();
        f[3] = f[3] + Math.abs(i3 - aVar.c());
        f = aVar.f();
        f[4] = f[4] + Math.abs(aVar.c() + i3);
        f = aVar.f();
        f[5] = f[5] + Math.abs(i3 - aVar.d());
        f = aVar.f();
        f[6] = f[6] + Math.abs(aVar.d() + i3);
        f = aVar.f();
        f[7] = f[7] + Math.abs(i3 - aVar.e());
        f = aVar.f();
        f[8] = f[8] + Math.abs(aVar.e() + i3);
        f = aVar.f();
        f[9] = f[9] + Math.abs(i3 - this.X);
        f = aVar.f();
        f[10] = Math.abs(i3 + this.X) + f[10];
        aVar.l((byte) (l - aVar.l()));
        this.X = aVar.m();
        aVar.k(l);
        if ((aVar.a() & 31) == 0) {
            i3 = aVar.f()[0];
            aVar.f()[0] = 0;
            int i4 = i3;
            i3 = 0;
            while (i2 < aVar.f().length) {
                if (aVar.f()[i2] < i4) {
                    i4 = aVar.f()[i2];
                    i3 = i2;
                }
                aVar.f()[i2] = 0;
                i2++;
            }
            switch (i3) {
                case 1:
                    if (aVar.g() >= -16) {
                        aVar.f(aVar.g() - 1);
                        break;
                    }
                    break;
                case 2:
                    if (aVar.g() < 16) {
                        aVar.f(aVar.g() + 1);
                        break;
                    }
                    break;
                case 3:
                    if (aVar.h() >= -16) {
                        aVar.g(aVar.h() - 1);
                        break;
                    }
                    break;
                case 4:
                    if (aVar.h() < 16) {
                        aVar.g(aVar.h() + 1);
                        break;
                    }
                    break;
                case 5:
                    if (aVar.i() >= -16) {
                        aVar.h(aVar.i() - 1);
                        break;
                    }
                    break;
                case 6:
                    if (aVar.i() < 16) {
                        aVar.h(aVar.i() + 1);
                        break;
                    }
                    break;
                case 7:
                    if (aVar.j() >= -16) {
                        aVar.i(aVar.j() - 1);
                        break;
                    }
                    break;
                case 8:
                    if (aVar.j() < 16) {
                        aVar.i(aVar.j() + 1);
                        break;
                    }
                    break;
                case 9:
                    if (aVar.k() >= -16) {
                        aVar.j(aVar.k() - 1);
                        break;
                    }
                    break;
                case 10:
                    if (aVar.k() < 16) {
                        aVar.j(aVar.k() + 1);
                        break;
                    }
                    break;
            }
        }
        return (byte) l;
    }
}
