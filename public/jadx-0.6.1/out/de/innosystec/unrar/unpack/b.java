package de.innosystec.unrar.unpack;

import com.iflytek.speech.VoiceWakeuperAidl;
import com.tencent.upload.log.trace.TracerConfig;
import de.innosystec.unrar.exception.RarException;
import de.innosystec.unrar.unpack.ppm.BlockTypes;
import de.innosystec.unrar.unpack.ppm.k;
import de.innosystec.unrar.unpack.vm.a;
import de.innosystec.unrar.unpack.vm.f;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

/* compiled from: Unpack */
public final class b extends d {
    public static int[] a;
    private boolean aA;
    private int aB;
    private int aC;
    private final de.innosystec.unrar.unpack.ppm.b an = new de.innosystec.unrar.unpack.ppm.b();
    private int ao;
    private de.innosystec.unrar.unpack.vm.b ap = new de.innosystec.unrar.unpack.vm.b();
    private List<e> aq = new ArrayList();
    private List<e> ar = new ArrayList();
    private List<Integer> as = new ArrayList();
    private int at;
    private boolean au;
    private byte[] av = new byte[404];
    private BlockTypes aw;
    private boolean ax;
    private long ay;
    private boolean az;

    static {
        int[] iArr = new int[19];
        iArr[0] = 4;
        iArr[1] = 2;
        iArr[2] = 2;
        iArr[3] = 2;
        iArr[4] = 2;
        iArr[5] = 2;
        iArr[6] = 2;
        iArr[7] = 2;
        iArr[8] = 2;
        iArr[9] = 2;
        iArr[10] = 2;
        iArr[11] = 2;
        iArr[12] = 2;
        iArr[13] = 2;
        iArr[14] = 2;
        iArr[15] = 2;
        iArr[16] = 14;
        iArr[18] = 12;
        a = iArr;
    }

    public b(a aVar) {
        this.e = aVar;
        this.i = null;
        this.ax = false;
        this.c = false;
        this.d = false;
        this.f = false;
    }

    public void a(byte[] bArr) {
        if (bArr == null) {
            this.i = new byte[SigType.WLOGIN_LHSIG];
        } else {
            this.i = bArr;
            this.ax = true;
        }
        this.ak = 0;
        a(false);
    }

    public void a(int i, boolean z) throws IOException, RarException {
        if (this.e.c().q() == (byte) 48) {
            p();
        }
        switch (i) {
            case 15:
                b(z);
                return;
            case 20:
            case 26:
                d(z);
                return;
            case 29:
            case 36:
                f(z);
                return;
            default:
                return;
        }
    }

    private void p() throws IOException, RarException {
        byte[] bArr = new byte[65536];
        while (true) {
            int a = this.e.a(bArr, 0, (int) Math.min((long) bArr.length, this.h));
            if (a != 0 && a != -1) {
                if (((long) a) >= this.h) {
                    a = (int) this.h;
                }
                this.e.b(bArr, 0, a);
                if (this.h >= 0) {
                    this.h -= (long) a;
                }
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void f(boolean r12) throws java.io.IOException, de.innosystec.unrar.exception.RarException {
        /*
        r11 = this;
        r0 = 60;
        r5 = new int[r0];
        r0 = 60;
        r6 = new byte[r0];
        r0 = 1;
        r0 = r5[r0];
        if (r0 != 0) goto L_0x0016;
    L_0x000d:
        r3 = 0;
        r2 = 0;
        r1 = 0;
        r0 = 0;
    L_0x0011:
        r4 = a;
        r4 = r4.length;
        if (r0 < r4) goto L_0x0027;
    L_0x0016:
        r0 = 1;
        r11.az = r0;
        r0 = r11.c;
        if (r0 != 0) goto L_0x004c;
    L_0x001d:
        r11.a(r12);
        r0 = r11.c();
        if (r0 != 0) goto L_0x0040;
    L_0x0026:
        return;
    L_0x0027:
        r4 = a;
        r7 = r4[r0];
        r4 = 0;
    L_0x002c:
        if (r4 < r7) goto L_0x0033;
    L_0x002e:
        r0 = r0 + 1;
        r2 = r2 + 1;
        goto L_0x0011;
    L_0x0033:
        r5[r1] = r3;
        r8 = (byte) r2;
        r6[r1] = r8;
        r4 = r4 + 1;
        r1 = r1 + 1;
        r8 = 1;
        r8 = r8 << r2;
        r3 = r3 + r8;
        goto L_0x002c;
    L_0x0040:
        if (r12 == 0) goto L_0x0046;
    L_0x0042:
        r0 = r11.au;
        if (r0 != 0) goto L_0x004c;
    L_0x0046:
        r0 = r11.t();
        if (r0 == 0) goto L_0x0026;
    L_0x004c:
        r0 = r11.aA;
        if (r0 != 0) goto L_0x0026;
    L_0x0050:
        r0 = r11.k;
        r1 = 4194303; // 0x3fffff float:5.87747E-39 double:2.072261E-317;
        r0 = r0 & r1;
        r11.k = r0;
        r0 = r11.ak;
        r1 = r11.b;
        if (r0 <= r1) goto L_0x0068;
    L_0x005e:
        r0 = r11.c();
        if (r0 != 0) goto L_0x0068;
    L_0x0064:
        r11.q();
        goto L_0x0026;
    L_0x0068:
        r0 = r11.l;
        r1 = r11.k;
        r0 = r0 - r1;
        r1 = 4194303; // 0x3fffff float:5.87747E-39 double:2.072261E-317;
        r0 = r0 & r1;
        r1 = 260; // 0x104 float:3.64E-43 double:1.285E-321;
        if (r0 >= r1) goto L_0x008e;
    L_0x0075:
        r0 = r11.l;
        r1 = r11.k;
        if (r0 == r1) goto L_0x008e;
    L_0x007b:
        r11.q();
        r0 = r11.ay;
        r2 = r11.h;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 > 0) goto L_0x0026;
    L_0x0086:
        r0 = r11.c;
        if (r0 == 0) goto L_0x008e;
    L_0x008a:
        r0 = 0;
        r11.az = r0;
        goto L_0x0026;
    L_0x008e:
        r0 = r11.aw;
        r1 = de.innosystec.unrar.unpack.ppm.BlockTypes.BLOCK_PPM;
        if (r0 != r1) goto L_0x011a;
    L_0x0094:
        r0 = r11.an;
        r0 = r0.b();
        r1 = -1;
        if (r0 != r1) goto L_0x00a1;
    L_0x009d:
        r0 = 1;
        r11.aA = r0;
        goto L_0x0064;
    L_0x00a1:
        r1 = r11.ao;
        if (r0 != r1) goto L_0x010d;
    L_0x00a5:
        r1 = r11.an;
        r1 = r1.b();
        if (r1 != 0) goto L_0x00b4;
    L_0x00ad:
        r0 = r11.t();
        if (r0 != 0) goto L_0x0050;
    L_0x00b3:
        goto L_0x0064;
    L_0x00b4:
        r2 = 2;
        if (r1 == r2) goto L_0x0064;
    L_0x00b7:
        r2 = -1;
        if (r1 == r2) goto L_0x0064;
    L_0x00ba:
        r2 = 3;
        if (r1 != r2) goto L_0x00c4;
    L_0x00bd:
        r0 = r11.v();
        if (r0 != 0) goto L_0x0050;
    L_0x00c3:
        goto L_0x0064;
    L_0x00c4:
        r2 = 4;
        if (r1 != r2) goto L_0x00f9;
    L_0x00c7:
        r3 = 0;
        r2 = 0;
        r1 = 0;
        r0 = 0;
        r10 = r0;
        r0 = r1;
        r1 = r2;
        r2 = r3;
        r3 = r10;
    L_0x00d0:
        r4 = 4;
        if (r3 >= r4) goto L_0x00d5;
    L_0x00d3:
        if (r0 == 0) goto L_0x00e0;
    L_0x00d5:
        if (r0 != 0) goto L_0x0064;
    L_0x00d7:
        r0 = r1 + 32;
        r1 = r2 + 2;
        r11.e(r0, r1);
        goto L_0x0050;
    L_0x00e0:
        r4 = r11.an;
        r4 = r4.b();
        r7 = -1;
        if (r4 != r7) goto L_0x00ed;
    L_0x00e9:
        r0 = 1;
    L_0x00ea:
        r3 = r3 + 1;
        goto L_0x00d0;
    L_0x00ed:
        r7 = 3;
        if (r3 != r7) goto L_0x00f3;
    L_0x00f0:
        r1 = r4 & 255;
        goto L_0x00ea;
    L_0x00f3:
        r2 = r2 << 8;
        r4 = r4 & 255;
        r2 = r2 + r4;
        goto L_0x00ea;
    L_0x00f9:
        r2 = 5;
        if (r1 != r2) goto L_0x010d;
    L_0x00fc:
        r0 = r11.an;
        r0 = r0.b();
        r1 = -1;
        if (r0 == r1) goto L_0x0064;
    L_0x0105:
        r0 = r0 + 4;
        r1 = 1;
        r11.e(r0, r1);
        goto L_0x0050;
    L_0x010d:
        r1 = r11.i;
        r2 = r11.k;
        r3 = r2 + 1;
        r11.k = r3;
        r0 = (byte) r0;
        r1[r2] = r0;
        goto L_0x0050;
    L_0x011a:
        r0 = r11.Z;
        r0 = r11.a(r0);
        r1 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
        if (r0 >= r1) goto L_0x0131;
    L_0x0124:
        r1 = r11.i;
        r2 = r11.k;
        r3 = r2 + 1;
        r11.k = r3;
        r0 = (byte) r0;
        r1[r2] = r0;
        goto L_0x0050;
    L_0x0131:
        r1 = 271; // 0x10f float:3.8E-43 double:1.34E-321;
        if (r0 < r1) goto L_0x01bc;
    L_0x0135:
        r1 = ae;
        r2 = r0 + -271;
        r0 = r1[r2];
        r0 = r0 + 3;
        r1 = af;
        r1 = r1[r2];
        if (r1 <= 0) goto L_0x014e;
    L_0x0143:
        r2 = r11.m();
        r3 = 16 - r1;
        r2 = r2 >>> r3;
        r0 = r0 + r2;
        r11.c(r1);
    L_0x014e:
        r1 = r11.aa;
        r2 = r11.a(r1);
        r1 = r5[r2];
        r1 = r1 + 1;
        r3 = r6[r2];
        if (r3 <= 0) goto L_0x017f;
    L_0x015c:
        r4 = 9;
        if (r2 <= r4) goto L_0x01b0;
    L_0x0160:
        r2 = 4;
        if (r3 <= r2) goto L_0x0172;
    L_0x0163:
        r2 = r11.m();
        r4 = 20 - r3;
        r2 = r2 >>> r4;
        r2 = r2 << 4;
        r1 = r1 + r2;
        r2 = r3 + -4;
        r11.c(r2);
    L_0x0172:
        r2 = r11.aC;
        if (r2 <= 0) goto L_0x019a;
    L_0x0176:
        r2 = r11.aC;
        r2 = r2 + -1;
        r11.aC = r2;
        r2 = r11.aB;
        r1 = r1 + r2;
    L_0x017f:
        r2 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        if (r1 < r2) goto L_0x018f;
    L_0x0183:
        r0 = r0 + 1;
        r2 = (long) r1;
        r8 = 262144; // 0x40000 float:3.67342E-40 double:1.295163E-318;
        r2 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1));
        if (r2 < 0) goto L_0x018f;
    L_0x018d:
        r0 = r0 + 1;
    L_0x018f:
        r11.f(r1);
        r11.d(r0, r1);
        r11.e(r0, r1);
        goto L_0x0050;
    L_0x019a:
        r2 = r11.ab;
        r2 = r11.a(r2);
        r3 = 16;
        if (r2 != r3) goto L_0x01ac;
    L_0x01a4:
        r2 = 15;
        r11.aC = r2;
        r2 = r11.aB;
        r1 = r1 + r2;
        goto L_0x017f;
    L_0x01ac:
        r1 = r1 + r2;
        r11.aB = r2;
        goto L_0x017f;
    L_0x01b0:
        r2 = r11.m();
        r4 = 16 - r3;
        r2 = r2 >>> r4;
        r1 = r1 + r2;
        r11.c(r3);
        goto L_0x017f;
    L_0x01bc:
        r1 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
        if (r0 != r1) goto L_0x01c8;
    L_0x01c0:
        r0 = r11.s();
        if (r0 != 0) goto L_0x0050;
    L_0x01c6:
        goto L_0x0064;
    L_0x01c8:
        r1 = 257; // 0x101 float:3.6E-43 double:1.27E-321;
        if (r0 != r1) goto L_0x01d4;
    L_0x01cc:
        r0 = r11.u();
        if (r0 != 0) goto L_0x0050;
    L_0x01d2:
        goto L_0x0064;
    L_0x01d4:
        r1 = 258; // 0x102 float:3.62E-43 double:1.275E-321;
        if (r0 != r1) goto L_0x01e5;
    L_0x01d8:
        r0 = r11.N;
        if (r0 == 0) goto L_0x0050;
    L_0x01dc:
        r0 = r11.N;
        r1 = r11.M;
        r11.e(r0, r1);
        goto L_0x0050;
    L_0x01e5:
        r1 = 263; // 0x107 float:3.69E-43 double:1.3E-321;
        if (r0 >= r1) goto L_0x0228;
    L_0x01e9:
        r0 = r0 + -259;
        r1 = r11.j;
        r1 = r1[r0];
    L_0x01ef:
        if (r0 > 0) goto L_0x021b;
    L_0x01f1:
        r0 = r11.j;
        r2 = 0;
        r0[r2] = r1;
        r0 = r11.ac;
        r2 = r11.a(r0);
        r0 = ae;
        r0 = r0[r2];
        r0 = r0 + 2;
        r3 = af;
        r2 = r3[r2];
        if (r2 <= 0) goto L_0x0213;
    L_0x0208:
        r3 = r11.m();
        r4 = 16 - r2;
        r3 = r3 >>> r4;
        r0 = r0 + r3;
        r11.c(r2);
    L_0x0213:
        r11.d(r0, r1);
        r11.e(r0, r1);
        goto L_0x0050;
    L_0x021b:
        r2 = r11.j;
        r3 = r11.j;
        r4 = r0 + -1;
        r3 = r3[r4];
        r2[r0] = r3;
        r0 = r0 + -1;
        goto L_0x01ef;
    L_0x0228:
        r1 = 272; // 0x110 float:3.81E-43 double:1.344E-321;
        if (r0 >= r1) goto L_0x0050;
    L_0x022c:
        r1 = ai;
        r2 = r0 + -263;
        r0 = r1[r2];
        r0 = r0 + 1;
        r1 = aj;
        r1 = r1[r2];
        if (r1 <= 0) goto L_0x0245;
    L_0x023a:
        r2 = r11.m();
        r3 = 16 - r1;
        r2 = r2 >>> r3;
        r0 = r0 + r2;
        r11.c(r1);
    L_0x0245:
        r11.f(r0);
        r1 = 2;
        r11.d(r1, r0);
        r1 = 2;
        r11.e(r1, r0);
        goto L_0x0050;
        */
        throw new UnsupportedOperationException("Method not decompiled: de.innosystec.unrar.unpack.b.f(boolean):void");
    }

    private void q() throws IOException {
        int i = this.l;
        int i2 = 0;
        int i3 = i;
        i = (this.k - i) & 4194303;
        while (i2 < this.ar.size()) {
            int i4;
            e eVar = (e) this.ar.get(i2);
            if (eVar == null) {
                i4 = i2;
                i2 = i3;
            } else if (eVar.d()) {
                eVar.a(false);
                i4 = i2;
                i2 = i3;
            } else {
                int b = eVar.b();
                i4 = eVar.a();
                if (((b - i3) & 4194303) < i) {
                    if (i3 != b) {
                        c(i3, b);
                        i = (this.k - b) & 4194303;
                        i3 = b;
                    }
                    if (i4 <= i) {
                        int i5 = (b + i4) & 4194303;
                        if (b < i5 || i5 == 0) {
                            this.ap.a(0, this.i, b, i4);
                        } else {
                            i = SigType.WLOGIN_LHSIG - b;
                            this.ap.a(0, this.i, b, i);
                            this.ap.a(i, this.i, 0, i5);
                        }
                        f f = ((e) this.aq.get(eVar.e())).f();
                        f f2 = eVar.f();
                        if (f.f().size() > 64) {
                            f2.f().setSize(f.f().size());
                            for (i = 0; i < f.f().size() - 64; i++) {
                                f2.f().set(i + 64, (Byte) f.f().get(i + 64));
                            }
                        }
                        a(f2);
                        if (f2.f().size() > 64) {
                            if (f.f().size() < f2.f().size()) {
                                f.f().setSize(f2.f().size());
                            }
                            for (i = 0; i < f2.f().size() - 64; i++) {
                                f.f().set(i + 64, (Byte) f2.f().get(i + 64));
                            }
                        } else {
                            f.f().clear();
                        }
                        int d = f2.d();
                        i = f2.e();
                        byte[] bArr = new byte[i];
                        for (i3 = 0; i3 < i; i3++) {
                            bArr[i3] = this.ap.b()[d + i3];
                        }
                        this.ar.set(i2, null);
                        i3 = i;
                        i4 = i2;
                        byte[] bArr2 = bArr;
                        while (i4 + 1 < this.ar.size()) {
                            e eVar2 = (e) this.ar.get(i4 + 1);
                            if (eVar2 == null || eVar2.b() != b || eVar2.a() != i3 || eVar2.d()) {
                                break;
                            }
                            this.ap.a(0, bArr2, 0, i3);
                            f f3 = ((e) this.aq.get(eVar2.e())).f();
                            f f4 = eVar2.f();
                            if (f3.f().size() > 64) {
                                f4.f().setSize(f3.f().size());
                                for (i = 0; i < f3.f().size() - 64; i++) {
                                    f4.f().set(i + 64, (Byte) f3.f().get(i + 64));
                                }
                            }
                            a(f4);
                            if (f4.f().size() > 64) {
                                if (f3.f().size() < f4.f().size()) {
                                    f3.f().setSize(f4.f().size());
                                }
                                for (i = 0; i < f4.f().size() - 64; i++) {
                                    f3.f().set(i + 64, (Byte) f4.f().get(i + 64));
                                }
                            } else {
                                f3.f().clear();
                            }
                            int d2 = f4.d();
                            i2 = f4.e();
                            byte[] bArr3 = new byte[i2];
                            for (i3 = 0; i3 < i2; i3++) {
                                bArr3[i3] = ((Byte) f4.f().get(d2 + i3)).byteValue();
                            }
                            int i6 = i4 + 1;
                            this.ar.set(i6, null);
                            i3 = i2;
                            i4 = i6;
                            bArr2 = bArr3;
                        }
                        this.e.b(bArr2, 0, i3);
                        this.f = true;
                        this.ay += (long) i3;
                        i = (this.k - i5) & 4194303;
                        i2 = i5;
                    } else {
                        while (i2 < this.ar.size()) {
                            eVar = (e) this.ar.get(i2);
                            if (eVar != null && eVar.d()) {
                                eVar.a(false);
                            }
                            i2++;
                        }
                        this.l = i3;
                        return;
                    }
                }
                i4 = i2;
                i2 = i3;
            }
            i3 = i2;
            i2 = i4 + 1;
        }
        c(i3, this.k);
        this.l = this.k;
    }

    private void c(int i, int i2) throws IOException {
        if (i2 != i) {
            this.f = true;
        }
        if (i2 < i) {
            a(this.i, i, (-i) & 4194303);
            a(this.i, 0, i2);
            this.d = true;
            return;
        }
        a(this.i, i, i2 - i);
    }

    private void a(byte[] bArr, int i, int i2) throws IOException {
        if (this.ay < this.h) {
            int i3;
            long j = this.h - this.ay;
            if (((long) i2) > j) {
                i3 = (int) j;
            } else {
                i3 = i2;
            }
            this.e.b(bArr, i, i3);
            this.ay += (long) i2;
        }
    }

    private void f(int i) {
        this.j[3] = this.j[2];
        this.j[2] = this.j[1];
        this.j[1] = this.j[0];
        this.j[0] = i;
    }

    private void d(int i, int i2) {
        this.M = i2;
        this.N = i;
    }

    private void e(int i, int i2) {
        int i3 = this.k - i2;
        if (i3 < 0 || i3 >= 4194044 || this.k >= 4194044) {
            while (true) {
                int i4 = i - 1;
                if (i != 0) {
                    int i5 = i3 + 1;
                    this.i[this.k] = this.i[i3 & 4194303];
                    this.k = (this.k + 1) & 4194303;
                    i3 = i5;
                    i = i4;
                } else {
                    return;
                }
            }
        }
        byte[] bArr = this.i;
        int i6 = this.k;
        this.k = i6 + 1;
        i5 = i3 + 1;
        bArr[i6] = this.i[i3];
        i3 = i5;
        while (true) {
            i--;
            if (i > 0) {
                bArr = this.i;
                i6 = this.k;
                this.k = i6 + 1;
                i5 = i3 + 1;
                bArr[i6] = this.i[i3];
                i3 = i5;
            } else {
                return;
            }
        }
    }

    protected void a(boolean z) {
        if (!z) {
            this.au = false;
            Arrays.fill(this.j, 0);
            this.m = 0;
            this.M = 0;
            this.N = 0;
            Arrays.fill(this.av, (byte) 0);
            this.k = 0;
            this.l = 0;
            this.ao = 2;
            r();
        }
        l();
        this.aA = false;
        this.ay = 0;
        this.g = 0;
        this.b = 0;
        e(z);
    }

    private void r() {
        this.as.clear();
        this.at = 0;
        this.aq.clear();
        this.ar.clear();
    }

    private boolean s() throws IOException, RarException {
        boolean z;
        boolean z2;
        boolean z3;
        int m = m();
        if ((32768 & m) != 0) {
            c(1);
            z = false;
            z2 = true;
        } else {
            if ((m & 16384) != 0) {
                z3 = true;
            } else {
                z3 = false;
            }
            c(2);
            z = true;
            z2 = z3;
        }
        if (z2) {
            z3 = false;
        } else {
            z3 = true;
        }
        this.au = z3;
        if (z || (z2 && !t())) {
            return false;
        }
        return true;
    }

    private boolean t() throws IOException, RarException {
        byte[] bArr = new byte[20];
        byte[] bArr2 = new byte[404];
        if (this.ak > this.g - 25 && !c()) {
            return false;
        }
        d((8 - this.al) & 7);
        long n = (long) (n() & -1);
        if ((TracerConfig.MEMORY_SIZE & n) != 0) {
            this.aw = BlockTypes.BLOCK_PPM;
            return this.an.a(this, this.ao);
        }
        int n2;
        int i;
        this.aw = BlockTypes.BLOCK_LZ;
        this.aB = 0;
        this.aC = 0;
        if ((n & 16384) == 0) {
            Arrays.fill(this.av, (byte) 0);
        }
        d(2);
        int i2 = 0;
        while (i2 < 20) {
            n2 = (n() >>> 12) & 255;
            d(4);
            if (n2 == 15) {
                n2 = (n() >>> 12) & 255;
                d(4);
                if (n2 == 0) {
                    bArr[i2] = (byte) 15;
                } else {
                    i = i2;
                    i2 = n2 + 2;
                    while (true) {
                        n2 = i2 - 1;
                        if (i2 <= 0 || i >= bArr.length) {
                            i2 = i - 1;
                        } else {
                            i2 = i + 1;
                            bArr[i] = (byte) 0;
                            i = i2;
                            i2 = n2;
                        }
                    }
                    i2 = i - 1;
                }
            } else {
                bArr[i2] = (byte) n2;
            }
            i2++;
        }
        a(bArr, 0, this.ad, 20);
        n2 = 0;
        while (n2 < 404) {
            if (this.ak > this.g - 5 && !c()) {
                return false;
            }
            i2 = a(this.ad);
            if (i2 < 16) {
                bArr2[n2] = (byte) ((i2 + this.av[n2]) & 15);
                n2++;
            } else {
                if (i2 >= 18) {
                    if (i2 == 18) {
                        i2 = (n() >>> 13) + 3;
                        d(3);
                        i = n2;
                    } else {
                        i2 = (n() >>> 9) + 11;
                        d(7);
                        i = n2;
                    }
                    while (true) {
                        n2 = i2 - 1;
                        if (i2 <= 0 || i >= 404) {
                            break;
                        }
                        i2 = i + 1;
                        bArr2[i] = (byte) 0;
                        i = i2;
                        i2 = n2;
                    }
                } else {
                    if (i2 == 16) {
                        i2 = (n() >>> 13) + 3;
                        d(3);
                        i = n2;
                    } else {
                        i2 = (n() >>> 9) + 11;
                        d(7);
                        i = n2;
                    }
                    while (true) {
                        n2 = i2 - 1;
                        if (i2 <= 0) {
                            break;
                        } else if (i >= 404) {
                            break;
                        } else {
                            bArr2[i] = bArr2[i - 1];
                            i++;
                            i2 = n2;
                        }
                    }
                    n2 = i;
                }
                n2 = i;
            }
        }
        this.au = true;
        if (this.ak > this.g) {
            return false;
        }
        a(bArr2, 0, this.Z, 299);
        a(bArr2, 299, this.aa, 60);
        a(bArr2, 359, this.ab, 17);
        a(bArr2, 376, this.ac, 28);
        for (i2 = 0; i2 < this.av.length; i2++) {
            this.av[i2] = bArr2[i2];
        }
        return true;
    }

    private boolean u() throws IOException, RarException {
        int m = m() >> 8;
        c(8);
        int i = (m & 7) + 1;
        if (i == 7) {
            i = (m() >> 8) + 7;
            c(8);
        } else if (i == 8) {
            i = m();
            c(16);
        }
        List arrayList = new ArrayList();
        int i2 = 0;
        while (i2 < i) {
            if (this.ak >= this.g - 1 && !c() && i2 < i - 1) {
                return false;
            }
            arrayList.add(Byte.valueOf((byte) (m() >> 8)));
            c(8);
            i2++;
        }
        return a(m, arrayList, i);
    }

    private boolean v() throws IOException, RarException {
        int b = this.an.b();
        if (b == -1) {
            return false;
        }
        int b2;
        int i = (b & 7) + 1;
        if (i == 7) {
            i = this.an.b();
            if (i == -1) {
                return false;
            }
            i += 7;
        } else if (i == 8) {
            i = this.an.b();
            if (i == -1) {
                return false;
            }
            b2 = this.an.b();
            if (b2 == -1) {
                return false;
            }
            i = (i * 256) + b2;
        }
        List arrayList = new ArrayList();
        for (b2 = 0; b2 < i; b2++) {
            int b3 = this.an.b();
            if (b3 == -1) {
                return false;
            }
            arrayList.add(Byte.valueOf((byte) b3));
        }
        return a(b, arrayList, i);
    }

    private boolean a(int i, List<Byte> list, int i2) {
        int i3;
        int a;
        a aVar = new a();
        aVar.l();
        for (i3 = 0; i3 < Math.min(32768, list.size()); i3++) {
            aVar.o()[i3] = ((Byte) list.get(i3)).byteValue();
        }
        this.ap.a();
        if ((i & 128) != 0) {
            a = de.innosystec.unrar.unpack.vm.b.a(aVar);
            if (a == 0) {
                r();
                i3 = a;
            } else {
                i3 = a - 1;
            }
        } else {
            i3 = this.at;
        }
        if (i3 > this.aq.size() || i3 > this.as.size()) {
            return false;
        }
        Object obj;
        e eVar;
        int i4;
        this.at = i3;
        if (i3 == this.aq.size()) {
            obj = 1;
        } else {
            obj = null;
        }
        e eVar2 = new e();
        e eVar3;
        if (obj == null) {
            eVar3 = (e) this.aq.get(i3);
            eVar2.d(i3);
            eVar3.c(eVar3.c() + 1);
            eVar = eVar3;
        } else if (i3 > 1024) {
            return false;
        } else {
            eVar3 = new e();
            this.aq.add(eVar3);
            eVar2.d(this.aq.size() - 1);
            this.as.add(Integer.valueOf(0));
            eVar3.c(0);
            eVar = eVar3;
        }
        this.ar.add(eVar2);
        eVar2.c(eVar.c());
        a = de.innosystec.unrar.unpack.vm.b.a(aVar);
        if ((i & 64) != 0) {
            i4 = a + VoiceWakeuperAidl.RES_SPECIFIED;
        } else {
            i4 = a;
        }
        eVar2.b((this.k + i4) & 4194303);
        if ((i & 32) != 0) {
            eVar2.a(de.innosystec.unrar.unpack.vm.b.a(aVar));
        } else {
            if (i3 < this.as.size()) {
                a = ((Integer) this.as.get(i3)).intValue();
            } else {
                a = 0;
            }
            eVar2.a(a);
        }
        boolean z = this.l != this.k && ((this.l - this.k) & 4194303) <= i4;
        eVar2.a(z);
        this.as.set(i3, Integer.valueOf(eVar2.a()));
        Arrays.fill(eVar2.f().g(), 0);
        eVar2.f().g()[3] = 245760;
        eVar2.f().g()[4] = eVar2.a();
        eVar2.f().g()[5] = eVar2.c();
        if ((i & 16) != 0) {
            i3 = aVar.n() >>> 9;
            aVar.d(7);
            for (a = 0; a < 7; a++) {
                if (((1 << a) & i3) != 0) {
                    eVar2.f().g()[a] = de.innosystec.unrar.unpack.vm.b.a(aVar);
                }
            }
        }
        if (obj != null) {
            i3 = de.innosystec.unrar.unpack.vm.b.a(aVar);
            if (i3 >= 65536 || i3 == 0) {
                return false;
            }
            byte[] bArr = new byte[i3];
            for (a = 0; a < i3; a++) {
                if (aVar.e(3)) {
                    return false;
                }
                bArr[a] = (byte) (aVar.n() >> 8);
                aVar.d(8);
            }
            this.ap.a(bArr, i3, eVar.f());
        }
        eVar2.f().a(eVar.f().b());
        eVar2.f().a(eVar.f().c());
        a = eVar.f().h().size();
        if (a > 0 && a < 8192) {
            eVar2.f().a(eVar.f().h());
        }
        if (eVar2.f().f().size() < 64) {
            eVar2.f().f().clear();
            eVar2.f().f().setSize(64);
        }
        Vector f = eVar2.f().f();
        for (a = 0; a < 7; a++) {
            this.ap.a(f, a * 4, eVar2.f().g()[a]);
        }
        this.ap.a(f, 28, eVar2.a());
        this.ap.a(f, 32, 0);
        this.ap.a(f, 36, 0);
        this.ap.a(f, 40, 0);
        this.ap.a(f, 44, eVar2.c());
        for (a = 0; a < 16; a++) {
            f.set(a + 48, Byte.valueOf((byte) 0));
        }
        if ((i & 8) != 0) {
            if (aVar.e(3)) {
                return false;
            }
            i3 = de.innosystec.unrar.unpack.vm.b.a(aVar);
            if (i3 > 8128) {
                return false;
            }
            a = eVar2.f().f().size();
            if (a < i3 + 64) {
                eVar2.f().f().setSize((i3 + 64) - a);
            }
            Vector f2 = eVar2.f().f();
            for (a = 0; a < i3; a++) {
                if (aVar.e(3)) {
                    return false;
                }
                f2.set(64 + a, Byte.valueOf((byte) (aVar.n() >>> 8)));
                aVar.d(8);
            }
        }
        return true;
    }

    private void a(f fVar) {
        if (fVar.f().size() > 0) {
            fVar.g()[6] = (int) this.ay;
            this.ap.a(fVar.f(), 36, (int) this.ay);
            this.ap.a(fVar.f(), 40, (int) (this.ay >>> 32));
            this.ap.a(fVar);
        }
    }

    public void a(long j) {
        this.h = j;
        this.az = false;
    }

    public int a() throws IOException, RarException {
        if (this.ak > 32738) {
            c();
        }
        byte[] bArr = this.am;
        int i = this.ak;
        this.ak = i + 1;
        return bArr[i] & 255;
    }

    public void a(int i) {
        this.ao = i;
    }

    public void b() {
        if (this.an != null) {
            k a = this.an.a();
            if (a != null) {
                a.c();
            }
        }
    }
}
