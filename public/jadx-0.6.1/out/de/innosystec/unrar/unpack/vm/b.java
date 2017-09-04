package de.innosystec.unrar.unpack.vm;

import com.tencent.smtt.sdk.WebView;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import de.innosystec.unrar.a.a;
import java.util.List;
import java.util.Vector;

/* compiled from: RarVM */
public class b extends a {
    private static /* synthetic */ int[] g;
    private static /* synthetic */ int[] h;
    private byte[] a = null;
    private int[] b = new int[8];
    private int c;
    private int d = 25000000;
    private int e;
    private int f;

    static /* synthetic */ int[] c() {
        int[] iArr = g;
        if (iArr == null) {
            iArr = new int[VMCommands.values().length];
            try {
                iArr[VMCommands.VM_ADC.ordinal()] = 38;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[VMCommands.VM_ADD.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[VMCommands.VM_ADDB.ordinal()] = 45;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[VMCommands.VM_ADDD.ordinal()] = 46;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[VMCommands.VM_AND.ordinal()] = 11;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[VMCommands.VM_CALL.ordinal()] = 22;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[VMCommands.VM_CMP.ordinal()] = 2;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[VMCommands.VM_CMPB.ordinal()] = 43;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[VMCommands.VM_CMPD.ordinal()] = 44;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[VMCommands.VM_DEC.ordinal()] = 8;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[VMCommands.VM_DECB.ordinal()] = 51;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr[VMCommands.VM_DECD.ordinal()] = 52;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr[VMCommands.VM_DIV.ordinal()] = 37;
            } catch (NoSuchFieldError e13) {
            }
            try {
                iArr[VMCommands.VM_INC.ordinal()] = 7;
            } catch (NoSuchFieldError e14) {
            }
            try {
                iArr[VMCommands.VM_INCB.ordinal()] = 49;
            } catch (NoSuchFieldError e15) {
            }
            try {
                iArr[VMCommands.VM_INCD.ordinal()] = 50;
            } catch (NoSuchFieldError e16) {
            }
            try {
                iArr[VMCommands.VM_JA.ordinal()] = 18;
            } catch (NoSuchFieldError e17) {
            }
            try {
                iArr[VMCommands.VM_JAE.ordinal()] = 19;
            } catch (NoSuchFieldError e18) {
            }
            try {
                iArr[VMCommands.VM_JB.ordinal()] = 16;
            } catch (NoSuchFieldError e19) {
            }
            try {
                iArr[VMCommands.VM_JBE.ordinal()] = 17;
            } catch (NoSuchFieldError e20) {
            }
            try {
                iArr[VMCommands.VM_JMP.ordinal()] = 9;
            } catch (NoSuchFieldError e21) {
            }
            try {
                iArr[VMCommands.VM_JNS.ordinal()] = 15;
            } catch (NoSuchFieldError e22) {
            }
            try {
                iArr[VMCommands.VM_JNZ.ordinal()] = 6;
            } catch (NoSuchFieldError e23) {
            }
            try {
                iArr[VMCommands.VM_JS.ordinal()] = 14;
            } catch (NoSuchFieldError e24) {
            }
            try {
                iArr[VMCommands.VM_JZ.ordinal()] = 5;
            } catch (NoSuchFieldError e25) {
            }
            try {
                iArr[VMCommands.VM_MOV.ordinal()] = 1;
            } catch (NoSuchFieldError e26) {
            }
            try {
                iArr[VMCommands.VM_MOVB.ordinal()] = 41;
            } catch (NoSuchFieldError e27) {
            }
            try {
                iArr[VMCommands.VM_MOVD.ordinal()] = 42;
            } catch (NoSuchFieldError e28) {
            }
            try {
                iArr[VMCommands.VM_MOVSX.ordinal()] = 34;
            } catch (NoSuchFieldError e29) {
            }
            try {
                iArr[VMCommands.VM_MOVZX.ordinal()] = 33;
            } catch (NoSuchFieldError e30) {
            }
            try {
                iArr[VMCommands.VM_MUL.ordinal()] = 36;
            } catch (NoSuchFieldError e31) {
            }
            try {
                iArr[VMCommands.VM_NEG.ordinal()] = 28;
            } catch (NoSuchFieldError e32) {
            }
            try {
                iArr[VMCommands.VM_NEGB.ordinal()] = 53;
            } catch (NoSuchFieldError e33) {
            }
            try {
                iArr[VMCommands.VM_NEGD.ordinal()] = 54;
            } catch (NoSuchFieldError e34) {
            }
            try {
                iArr[VMCommands.VM_NOT.ordinal()] = 24;
            } catch (NoSuchFieldError e35) {
            }
            try {
                iArr[VMCommands.VM_OR.ordinal()] = 12;
            } catch (NoSuchFieldError e36) {
            }
            try {
                iArr[VMCommands.VM_POP.ordinal()] = 21;
            } catch (NoSuchFieldError e37) {
            }
            try {
                iArr[VMCommands.VM_POPA.ordinal()] = 30;
            } catch (NoSuchFieldError e38) {
            }
            try {
                iArr[VMCommands.VM_POPF.ordinal()] = 32;
            } catch (NoSuchFieldError e39) {
            }
            try {
                iArr[VMCommands.VM_PRINT.ordinal()] = 40;
            } catch (NoSuchFieldError e40) {
            }
            try {
                iArr[VMCommands.VM_PUSH.ordinal()] = 20;
            } catch (NoSuchFieldError e41) {
            }
            try {
                iArr[VMCommands.VM_PUSHA.ordinal()] = 29;
            } catch (NoSuchFieldError e42) {
            }
            try {
                iArr[VMCommands.VM_PUSHF.ordinal()] = 31;
            } catch (NoSuchFieldError e43) {
            }
            try {
                iArr[VMCommands.VM_RET.ordinal()] = 23;
            } catch (NoSuchFieldError e44) {
            }
            try {
                iArr[VMCommands.VM_SAR.ordinal()] = 27;
            } catch (NoSuchFieldError e45) {
            }
            try {
                iArr[VMCommands.VM_SBB.ordinal()] = 39;
            } catch (NoSuchFieldError e46) {
            }
            try {
                iArr[VMCommands.VM_SHL.ordinal()] = 25;
            } catch (NoSuchFieldError e47) {
            }
            try {
                iArr[VMCommands.VM_SHR.ordinal()] = 26;
            } catch (NoSuchFieldError e48) {
            }
            try {
                iArr[VMCommands.VM_STANDARD.ordinal()] = 55;
            } catch (NoSuchFieldError e49) {
            }
            try {
                iArr[VMCommands.VM_SUB.ordinal()] = 4;
            } catch (NoSuchFieldError e50) {
            }
            try {
                iArr[VMCommands.VM_SUBB.ordinal()] = 47;
            } catch (NoSuchFieldError e51) {
            }
            try {
                iArr[VMCommands.VM_SUBD.ordinal()] = 48;
            } catch (NoSuchFieldError e52) {
            }
            try {
                iArr[VMCommands.VM_TEST.ordinal()] = 13;
            } catch (NoSuchFieldError e53) {
            }
            try {
                iArr[VMCommands.VM_XCHG.ordinal()] = 35;
            } catch (NoSuchFieldError e54) {
            }
            try {
                iArr[VMCommands.VM_XOR.ordinal()] = 10;
            } catch (NoSuchFieldError e55) {
            }
            g = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] d() {
        int[] iArr = h;
        if (iArr == null) {
            iArr = new int[VMStandardFilters.values().length];
            try {
                iArr[VMStandardFilters.VMSF_AUDIO.ordinal()] = 6;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[VMStandardFilters.VMSF_DELTA.ordinal()] = 7;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[VMStandardFilters.VMSF_E8.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[VMStandardFilters.VMSF_E8E9.ordinal()] = 3;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[VMStandardFilters.VMSF_ITANIUM.ordinal()] = 4;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[VMStandardFilters.VMSF_NONE.ordinal()] = 1;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[VMStandardFilters.VMSF_RGB.ordinal()] = 5;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[VMStandardFilters.VMSF_UPCASE.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            h = iArr;
        }
        return iArr;
    }

    public void a() {
        if (this.a == null) {
            this.a = new byte[262148];
        }
    }

    private boolean a(byte[] bArr) {
        return this.a == bArr;
    }

    private int a(boolean z, byte[] bArr, int i) {
        if (z) {
            if (a(bArr)) {
                return bArr[i];
            }
            return bArr[i] & 255;
        } else if (a(bArr)) {
            return de.innosystec.unrar.b.b.c(bArr, i);
        } else {
            return de.innosystec.unrar.b.b.a(bArr, i);
        }
    }

    private void a(boolean z, byte[] bArr, int i, int i2) {
        if (z) {
            if (a(bArr)) {
                bArr[i] = (byte) i2;
                return;
            }
            byte b = bArr[i];
            bArr[i] = (byte) (((byte) (i2 & 255)) | 0);
        } else if (a(bArr)) {
            de.innosystec.unrar.b.b.c(bArr, i, i2);
        } else {
            de.innosystec.unrar.b.b.a(bArr, i, i2);
        }
    }

    public void a(Vector<Byte> vector, int i, int i2) {
        vector.set(i + 0, Byte.valueOf((byte) (i2 & 255)));
        vector.set(i + 1, Byte.valueOf((byte) ((i2 >>> 8) & 255)));
        vector.set(i + 2, Byte.valueOf((byte) ((i2 >>> 16) & 255)));
        vector.set(i + 3, Byte.valueOf((byte) ((i2 >>> 24) & 255)));
    }

    private int a(e eVar) {
        if (eVar.c() == VMOpType.VM_OPREGMEM) {
            return de.innosystec.unrar.b.b.c(this.a, (eVar.d() + eVar.a()) & 262143);
        }
        return de.innosystec.unrar.b.b.c(this.a, eVar.d());
    }

    public void a(f fVar) {
        int i;
        int i2;
        List a;
        int i3 = 0;
        for (i = 0; i < fVar.g().length; i++) {
            this.b[i] = fVar.g()[i];
        }
        long min = (long) (Math.min(fVar.f().size(), 8192) & -1);
        if (min != 0) {
            for (i2 = 0; ((long) i2) < min; i2++) {
                this.a[245760 + i2] = ((Byte) fVar.f().get(i2)).byteValue();
            }
        }
        long min2 = -1 & Math.min((long) fVar.h().size(), 8192 - min);
        if (min2 != 0) {
            for (i2 = 0; ((long) i2) < min2; i2++) {
                this.a[(((int) min) + 245760) + i2] = ((Byte) fVar.h().get(i2)).byteValue();
            }
        }
        this.b[7] = 262144;
        this.c = 0;
        if (fVar.a().size() != 0) {
            a = fVar.a();
        } else {
            a = fVar.b();
        }
        if (!a(a, fVar.c())) {
            ((d) a.get(0)).a(VMCommands.VM_RET);
        }
        i2 = a(false, this.a, 245792) & 262143;
        i = a(false, this.a, 245788) & 262143;
        if (i2 + i >= 262144) {
            i = 0;
            i2 = 0;
        }
        fVar.b(i2);
        fVar.c(i);
        fVar.f().clear();
        i = Math.min(a(false, this.a, 245808), 8128);
        if (i != 0) {
            fVar.f().setSize(i + 64);
            while (i3 < i + 64) {
                fVar.f().set(i3, Byte.valueOf(this.a[245760 + i3]));
                i3++;
            }
        }
    }

    public byte[] b() {
        return this.a;
    }

    private boolean a(int i) {
        if (i >= this.e) {
            return true;
        }
        int i2 = this.d - 1;
        this.d = i2;
        if (i2 <= 0) {
            return false;
        }
        this.f = i;
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(java.util.List<de.innosystec.unrar.unpack.vm.d> r13, int r14) {
        /*
        r12 = this;
        r0 = 25000000; // 0x17d7840 float:4.6555036E-38 double:1.2351641E-316;
        r12.d = r0;
        r12.e = r14;
        r0 = 0;
        r12.f = r0;
    L_0x000a:
        r0 = r12.f;
        r0 = r13.get(r0);
        r0 = (de.innosystec.unrar.unpack.vm.d) r0;
        r1 = r0.b();
        r3 = r12.a(r1);
        r1 = r0.c();
        r1 = r12.a(r1);
        r2 = c();
        r4 = r0.d();
        r4 = r4.ordinal();
        r2 = r2[r4];
        switch(r2) {
            case 1: goto L_0x0040;
            case 2: goto L_0x0070;
            case 3: goto L_0x00f5;
            case 4: goto L_0x0197;
            case 5: goto L_0x0216;
            case 6: goto L_0x022d;
            case 7: goto L_0x0244;
            case 8: goto L_0x02a0;
            case 9: goto L_0x02f4;
            case 10: goto L_0x0300;
            case 11: goto L_0x0332;
            case 12: goto L_0x0364;
            case 13: goto L_0x0396;
            case 14: goto L_0x03bf;
            case 15: goto L_0x03d6;
            case 16: goto L_0x03ed;
            case 17: goto L_0x0404;
            case 18: goto L_0x0422;
            case 19: goto L_0x0440;
            case 20: goto L_0x0457;
            case 21: goto L_0x0478;
            case 22: goto L_0x0499;
            case 23: goto L_0x07b7;
            case 24: goto L_0x04c1;
            case 25: goto L_0x04d8;
            case 26: goto L_0x051f;
            case 27: goto L_0x055d;
            case 28: goto L_0x059b;
            case 29: goto L_0x05ea;
            case 30: goto L_0x0614;
            case 31: goto L_0x0634;
            case 32: goto L_0x0650;
            case 33: goto L_0x066d;
            case 34: goto L_0x067c;
            case 35: goto L_0x068c;
            case 36: goto L_0x06b4;
            case 37: goto L_0x06e0;
            case 38: goto L_0x0703;
            case 39: goto L_0x075d;
            case 40: goto L_0x0033;
            case 41: goto L_0x0054;
            case 42: goto L_0x0062;
            case 43: goto L_0x009f;
            case 44: goto L_0x00ca;
            case 45: goto L_0x0157;
            case 46: goto L_0x0177;
            case 47: goto L_0x01d6;
            case 48: goto L_0x01f6;
            case 49: goto L_0x0278;
            case 50: goto L_0x028c;
            case 51: goto L_0x02cc;
            case 52: goto L_0x02e0;
            case 53: goto L_0x05ca;
            case 54: goto L_0x05da;
            case 55: goto L_0x07e0;
            default: goto L_0x0033;
        };
    L_0x0033:
        r0 = r12.f;
        r0 = r0 + 1;
        r12.f = r0;
        r0 = r12.d;
        r0 = r0 + -1;
        r12.d = r0;
        goto L_0x000a;
    L_0x0040:
        r2 = r0.a();
        r4 = r12.a;
        r0 = r0.a();
        r5 = r12.a;
        r0 = r12.a(r0, r5, r1);
        r12.a(r2, r4, r3, r0);
        goto L_0x0033;
    L_0x0054:
        r0 = 1;
        r2 = r12.a;
        r4 = 1;
        r5 = r12.a;
        r1 = r12.a(r4, r5, r1);
        r12.a(r0, r2, r3, r1);
        goto L_0x0033;
    L_0x0062:
        r0 = 0;
        r2 = r12.a;
        r4 = 0;
        r5 = r12.a;
        r1 = r12.a(r4, r5, r1);
        r12.a(r0, r2, r3, r1);
        goto L_0x0033;
    L_0x0070:
        r2 = r0.a();
        r4 = r12.a;
        r2 = r12.a(r2, r4, r3);
        r0 = r0.a();
        r3 = r12.a;
        r0 = r12.a(r0, r3, r1);
        r0 = r2 - r0;
        if (r0 != 0) goto L_0x0091;
    L_0x0088:
        r0 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FZ;
        r0 = r0.getFlag();
        r12.c = r0;
        goto L_0x0033;
    L_0x0091:
        if (r0 <= r2) goto L_0x0097;
    L_0x0093:
        r0 = 1;
    L_0x0094:
        r12.c = r0;
        goto L_0x0033;
    L_0x0097:
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FS;
        r1 = r1.getFlag();
        r0 = r0 & r1;
        goto L_0x0094;
    L_0x009f:
        r0 = 1;
        r2 = r12.a;
        r0 = r12.a(r0, r2, r3);
        r2 = 1;
        r3 = r12.a;
        r1 = r12.a(r2, r3, r1);
        r1 = r0 - r1;
        if (r1 != 0) goto L_0x00bb;
    L_0x00b1:
        r0 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FZ;
        r0 = r0.getFlag();
        r12.c = r0;
        goto L_0x0033;
    L_0x00bb:
        if (r1 <= r0) goto L_0x00c2;
    L_0x00bd:
        r0 = 1;
    L_0x00be:
        r12.c = r0;
        goto L_0x0033;
    L_0x00c2:
        r0 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FS;
        r0 = r0.getFlag();
        r0 = r0 & r1;
        goto L_0x00be;
    L_0x00ca:
        r0 = 0;
        r2 = r12.a;
        r0 = r12.a(r0, r2, r3);
        r2 = 0;
        r3 = r12.a;
        r1 = r12.a(r2, r3, r1);
        r1 = r0 - r1;
        if (r1 != 0) goto L_0x00e6;
    L_0x00dc:
        r0 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FZ;
        r0 = r0.getFlag();
        r12.c = r0;
        goto L_0x0033;
    L_0x00e6:
        if (r1 <= r0) goto L_0x00ed;
    L_0x00e8:
        r0 = 1;
    L_0x00e9:
        r12.c = r0;
        goto L_0x0033;
    L_0x00ed:
        r0 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FS;
        r0 = r0.getFlag();
        r0 = r0 & r1;
        goto L_0x00e9;
    L_0x00f5:
        r2 = r0.a();
        r4 = r12.a;
        r4 = r12.a(r2, r4, r3);
        r6 = (long) r4;
        r2 = r0.a();
        r5 = r12.a;
        r1 = r12.a(r2, r5, r1);
        r8 = (long) r1;
        r6 = r6 + r8;
        r8 = -1;
        r6 = r6 & r8;
        r2 = (int) r6;
        r1 = r0.a();
        if (r1 == 0) goto L_0x013f;
    L_0x0116:
        r2 = r2 & 255;
        if (r2 >= r4) goto L_0x0129;
    L_0x011a:
        r1 = 1;
    L_0x011b:
        r12.c = r1;
        r1 = r2;
    L_0x011e:
        r0 = r0.a();
        r2 = r12.a;
        r12.a(r0, r2, r3, r1);
        goto L_0x0033;
    L_0x0129:
        if (r2 != 0) goto L_0x0132;
    L_0x012b:
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FZ;
        r1 = r1.getFlag();
        goto L_0x011b;
    L_0x0132:
        r1 = r2 & 128;
        if (r1 == 0) goto L_0x013d;
    L_0x0136:
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FS;
        r1 = r1.getFlag();
        goto L_0x011b;
    L_0x013d:
        r1 = 0;
        goto L_0x011b;
    L_0x013f:
        if (r2 >= r4) goto L_0x0146;
    L_0x0141:
        r1 = 1;
    L_0x0142:
        r12.c = r1;
        r1 = r2;
        goto L_0x011e;
    L_0x0146:
        if (r2 != 0) goto L_0x014f;
    L_0x0148:
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FZ;
        r1 = r1.getFlag();
        goto L_0x0142;
    L_0x014f:
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FS;
        r1 = r1.getFlag();
        r1 = r1 & r2;
        goto L_0x0142;
    L_0x0157:
        r0 = 1;
        r2 = r12.a;
        r4 = 1;
        r5 = r12.a;
        r4 = r12.a(r4, r5, r3);
        r4 = (long) r4;
        r6 = -1;
        r8 = 1;
        r9 = r12.a;
        r1 = r12.a(r8, r9, r1);
        r8 = (long) r1;
        r6 = r6 + r8;
        r4 = r4 & r6;
        r6 = -1;
        r4 = r4 & r6;
        r1 = (int) r4;
        r12.a(r0, r2, r3, r1);
        goto L_0x0033;
    L_0x0177:
        r0 = 0;
        r2 = r12.a;
        r4 = 0;
        r5 = r12.a;
        r4 = r12.a(r4, r5, r3);
        r4 = (long) r4;
        r6 = -1;
        r8 = 0;
        r9 = r12.a;
        r1 = r12.a(r8, r9, r1);
        r8 = (long) r1;
        r6 = r6 + r8;
        r4 = r4 & r6;
        r6 = -1;
        r4 = r4 & r6;
        r1 = (int) r4;
        r12.a(r0, r2, r3, r1);
        goto L_0x0033;
    L_0x0197:
        r2 = r0.a();
        r4 = r12.a;
        r2 = r12.a(r2, r4, r3);
        r4 = (long) r2;
        r6 = -1;
        r8 = r0.a();
        r9 = r12.a;
        r1 = r12.a(r8, r9, r1);
        r8 = (long) r1;
        r6 = r6 - r8;
        r4 = r4 & r6;
        r6 = -1;
        r4 = r4 & r6;
        r4 = (int) r4;
        if (r4 != 0) goto L_0x01ca;
    L_0x01b7:
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FZ;
        r1 = r1.getFlag();
    L_0x01bd:
        r12.c = r1;
        r0 = r0.a();
        r1 = r12.a;
        r12.a(r0, r1, r3, r4);
        goto L_0x0033;
    L_0x01ca:
        if (r4 <= r2) goto L_0x01ce;
    L_0x01cc:
        r1 = 1;
        goto L_0x01bd;
    L_0x01ce:
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FS;
        r1 = r1.getFlag();
        r1 = r1 & r4;
        goto L_0x01bd;
    L_0x01d6:
        r0 = 1;
        r2 = r12.a;
        r4 = 1;
        r5 = r12.a;
        r4 = r12.a(r4, r5, r3);
        r4 = (long) r4;
        r6 = -1;
        r8 = 1;
        r9 = r12.a;
        r1 = r12.a(r8, r9, r1);
        r8 = (long) r1;
        r6 = r6 - r8;
        r4 = r4 & r6;
        r6 = -1;
        r4 = r4 & r6;
        r1 = (int) r4;
        r12.a(r0, r2, r3, r1);
        goto L_0x0033;
    L_0x01f6:
        r0 = 0;
        r2 = r12.a;
        r4 = 0;
        r5 = r12.a;
        r4 = r12.a(r4, r5, r3);
        r4 = (long) r4;
        r6 = -1;
        r8 = 0;
        r9 = r12.a;
        r1 = r12.a(r8, r9, r1);
        r8 = (long) r1;
        r6 = r6 - r8;
        r4 = r4 & r6;
        r6 = -1;
        r4 = r4 & r6;
        r1 = (int) r4;
        r12.a(r0, r2, r3, r1);
        goto L_0x0033;
    L_0x0216:
        r0 = r12.c;
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FZ;
        r1 = r1.getFlag();
        r0 = r0 & r1;
        if (r0 == 0) goto L_0x0033;
    L_0x0221:
        r0 = 0;
        r1 = r12.a;
        r0 = r12.a(r0, r1, r3);
        r12.a(r0);
        goto L_0x000a;
    L_0x022d:
        r0 = r12.c;
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FZ;
        r1 = r1.getFlag();
        r0 = r0 & r1;
        if (r0 != 0) goto L_0x0033;
    L_0x0238:
        r0 = 0;
        r1 = r12.a;
        r0 = r12.a(r0, r1, r3);
        r12.a(r0);
        goto L_0x000a;
    L_0x0244:
        r1 = r0.a();
        r2 = r12.a;
        r1 = r12.a(r1, r2, r3);
        r4 = (long) r1;
        r6 = 0;
        r4 = r4 & r6;
        r1 = (int) r4;
        r2 = r0.a();
        if (r2 == 0) goto L_0x025b;
    L_0x0259:
        r1 = r1 & 255;
    L_0x025b:
        r0 = r0.a();
        r2 = r12.a;
        r12.a(r0, r2, r3, r1);
        if (r1 != 0) goto L_0x0270;
    L_0x0266:
        r0 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FZ;
        r0 = r0.getFlag();
    L_0x026c:
        r12.c = r0;
        goto L_0x0033;
    L_0x0270:
        r0 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FS;
        r0 = r0.getFlag();
        r0 = r0 & r1;
        goto L_0x026c;
    L_0x0278:
        r0 = 1;
        r1 = r12.a;
        r2 = 1;
        r4 = r12.a;
        r2 = r12.a(r2, r4, r3);
        r4 = (long) r2;
        r6 = 0;
        r4 = r4 & r6;
        r2 = (int) r4;
        r12.a(r0, r1, r3, r2);
        goto L_0x0033;
    L_0x028c:
        r0 = 0;
        r1 = r12.a;
        r2 = 0;
        r4 = r12.a;
        r2 = r12.a(r2, r4, r3);
        r4 = (long) r2;
        r6 = 0;
        r4 = r4 & r6;
        r2 = (int) r4;
        r12.a(r0, r1, r3, r2);
        goto L_0x0033;
    L_0x02a0:
        r1 = r0.a();
        r2 = r12.a;
        r1 = r12.a(r1, r2, r3);
        r4 = (long) r1;
        r6 = -2;
        r4 = r4 & r6;
        r1 = (int) r4;
        r0 = r0.a();
        r2 = r12.a;
        r12.a(r0, r2, r3, r1);
        if (r1 != 0) goto L_0x02c4;
    L_0x02ba:
        r0 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FZ;
        r0 = r0.getFlag();
    L_0x02c0:
        r12.c = r0;
        goto L_0x0033;
    L_0x02c4:
        r0 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FS;
        r0 = r0.getFlag();
        r0 = r0 & r1;
        goto L_0x02c0;
    L_0x02cc:
        r0 = 1;
        r1 = r12.a;
        r2 = 1;
        r4 = r12.a;
        r2 = r12.a(r2, r4, r3);
        r4 = (long) r2;
        r6 = -2;
        r4 = r4 & r6;
        r2 = (int) r4;
        r12.a(r0, r1, r3, r2);
        goto L_0x0033;
    L_0x02e0:
        r0 = 0;
        r1 = r12.a;
        r2 = 0;
        r4 = r12.a;
        r2 = r12.a(r2, r4, r3);
        r4 = (long) r2;
        r6 = -2;
        r4 = r4 & r6;
        r2 = (int) r4;
        r12.a(r0, r1, r3, r2);
        goto L_0x0033;
    L_0x02f4:
        r0 = 0;
        r1 = r12.a;
        r0 = r12.a(r0, r1, r3);
        r12.a(r0);
        goto L_0x000a;
    L_0x0300:
        r2 = r0.a();
        r4 = r12.a;
        r2 = r12.a(r2, r4, r3);
        r4 = r0.a();
        r5 = r12.a;
        r1 = r12.a(r4, r5, r1);
        r2 = r2 ^ r1;
        if (r2 != 0) goto L_0x032a;
    L_0x0317:
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FZ;
        r1 = r1.getFlag();
    L_0x031d:
        r12.c = r1;
        r0 = r0.a();
        r1 = r12.a;
        r12.a(r0, r1, r3, r2);
        goto L_0x0033;
    L_0x032a:
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FS;
        r1 = r1.getFlag();
        r1 = r1 & r2;
        goto L_0x031d;
    L_0x0332:
        r2 = r0.a();
        r4 = r12.a;
        r2 = r12.a(r2, r4, r3);
        r4 = r0.a();
        r5 = r12.a;
        r1 = r12.a(r4, r5, r1);
        r2 = r2 & r1;
        if (r2 != 0) goto L_0x035c;
    L_0x0349:
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FZ;
        r1 = r1.getFlag();
    L_0x034f:
        r12.c = r1;
        r0 = r0.a();
        r1 = r12.a;
        r12.a(r0, r1, r3, r2);
        goto L_0x0033;
    L_0x035c:
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FS;
        r1 = r1.getFlag();
        r1 = r1 & r2;
        goto L_0x034f;
    L_0x0364:
        r2 = r0.a();
        r4 = r12.a;
        r2 = r12.a(r2, r4, r3);
        r4 = r0.a();
        r5 = r12.a;
        r1 = r12.a(r4, r5, r1);
        r2 = r2 | r1;
        if (r2 != 0) goto L_0x038e;
    L_0x037b:
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FZ;
        r1 = r1.getFlag();
    L_0x0381:
        r12.c = r1;
        r0 = r0.a();
        r1 = r12.a;
        r12.a(r0, r1, r3, r2);
        goto L_0x0033;
    L_0x038e:
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FS;
        r1 = r1.getFlag();
        r1 = r1 & r2;
        goto L_0x0381;
    L_0x0396:
        r2 = r0.a();
        r4 = r12.a;
        r2 = r12.a(r2, r4, r3);
        r0 = r0.a();
        r3 = r12.a;
        r0 = r12.a(r0, r3, r1);
        r0 = r0 & r2;
        if (r0 != 0) goto L_0x03b7;
    L_0x03ad:
        r0 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FZ;
        r0 = r0.getFlag();
    L_0x03b3:
        r12.c = r0;
        goto L_0x0033;
    L_0x03b7:
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FS;
        r1 = r1.getFlag();
        r0 = r0 & r1;
        goto L_0x03b3;
    L_0x03bf:
        r0 = r12.c;
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FS;
        r1 = r1.getFlag();
        r0 = r0 & r1;
        if (r0 == 0) goto L_0x0033;
    L_0x03ca:
        r0 = 0;
        r1 = r12.a;
        r0 = r12.a(r0, r1, r3);
        r12.a(r0);
        goto L_0x000a;
    L_0x03d6:
        r0 = r12.c;
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FS;
        r1 = r1.getFlag();
        r0 = r0 & r1;
        if (r0 != 0) goto L_0x0033;
    L_0x03e1:
        r0 = 0;
        r1 = r12.a;
        r0 = r12.a(r0, r1, r3);
        r12.a(r0);
        goto L_0x000a;
    L_0x03ed:
        r0 = r12.c;
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FC;
        r1 = r1.getFlag();
        r0 = r0 & r1;
        if (r0 == 0) goto L_0x0033;
    L_0x03f8:
        r0 = 0;
        r1 = r12.a;
        r0 = r12.a(r0, r1, r3);
        r12.a(r0);
        goto L_0x000a;
    L_0x0404:
        r0 = r12.c;
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FC;
        r1 = r1.getFlag();
        r2 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FZ;
        r2 = r2.getFlag();
        r1 = r1 | r2;
        r0 = r0 & r1;
        if (r0 == 0) goto L_0x0033;
    L_0x0416:
        r0 = 0;
        r1 = r12.a;
        r0 = r12.a(r0, r1, r3);
        r12.a(r0);
        goto L_0x000a;
    L_0x0422:
        r0 = r12.c;
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FC;
        r1 = r1.getFlag();
        r2 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FZ;
        r2 = r2.getFlag();
        r1 = r1 | r2;
        r0 = r0 & r1;
        if (r0 != 0) goto L_0x0033;
    L_0x0434:
        r0 = 0;
        r1 = r12.a;
        r0 = r12.a(r0, r1, r3);
        r12.a(r0);
        goto L_0x000a;
    L_0x0440:
        r0 = r12.c;
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FC;
        r1 = r1.getFlag();
        r0 = r0 & r1;
        if (r0 != 0) goto L_0x0033;
    L_0x044b:
        r0 = 0;
        r1 = r12.a;
        r0 = r12.a(r0, r1, r3);
        r12.a(r0);
        goto L_0x000a;
    L_0x0457:
        r0 = r12.b;
        r1 = 7;
        r2 = r0[r1];
        r2 = r2 + -4;
        r0[r1] = r2;
        r0 = 0;
        r1 = r12.a;
        r2 = r12.b;
        r4 = 7;
        r2 = r2[r4];
        r4 = 262143; // 0x3ffff float:3.6734E-40 double:1.29516E-318;
        r2 = r2 & r4;
        r4 = 0;
        r5 = r12.a;
        r3 = r12.a(r4, r5, r3);
        r12.a(r0, r1, r2, r3);
        goto L_0x0033;
    L_0x0478:
        r0 = 0;
        r1 = r12.a;
        r2 = 0;
        r4 = r12.a;
        r5 = r12.b;
        r6 = 7;
        r5 = r5[r6];
        r6 = 262143; // 0x3ffff float:3.6734E-40 double:1.29516E-318;
        r5 = r5 & r6;
        r2 = r12.a(r2, r4, r5);
        r12.a(r0, r1, r3, r2);
        r0 = r12.b;
        r1 = 7;
        r2 = r0[r1];
        r2 = r2 + 4;
        r0[r1] = r2;
        goto L_0x0033;
    L_0x0499:
        r0 = r12.b;
        r1 = 7;
        r2 = r0[r1];
        r2 = r2 + -4;
        r0[r1] = r2;
        r0 = 0;
        r1 = r12.a;
        r2 = r12.b;
        r4 = 7;
        r2 = r2[r4];
        r4 = 262143; // 0x3ffff float:3.6734E-40 double:1.29516E-318;
        r2 = r2 & r4;
        r4 = r12.f;
        r4 = r4 + 1;
        r12.a(r0, r1, r2, r4);
        r0 = 0;
        r1 = r12.a;
        r0 = r12.a(r0, r1, r3);
        r12.a(r0);
        goto L_0x000a;
    L_0x04c1:
        r1 = r0.a();
        r2 = r12.a;
        r0 = r0.a();
        r4 = r12.a;
        r0 = r12.a(r0, r4, r3);
        r0 = r0 ^ -1;
        r12.a(r1, r2, r3, r0);
        goto L_0x0033;
    L_0x04d8:
        r2 = r0.a();
        r4 = r12.a;
        r4 = r12.a(r2, r4, r3);
        r2 = r0.a();
        r5 = r12.a;
        r5 = r12.a(r2, r5, r1);
        r6 = r4 << r5;
        if (r6 != 0) goto L_0x0514;
    L_0x04f0:
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FZ;
        r1 = r1.getFlag();
        r2 = r1;
    L_0x04f7:
        r1 = r5 + -1;
        r1 = r4 << r1;
        r4 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r1 = r1 & r4;
        if (r1 == 0) goto L_0x051d;
    L_0x0500:
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FC;
        r1 = r1.getFlag();
    L_0x0506:
        r1 = r1 | r2;
        r12.c = r1;
        r0 = r0.a();
        r1 = r12.a;
        r12.a(r0, r1, r3, r6);
        goto L_0x0033;
    L_0x0514:
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FS;
        r1 = r1.getFlag();
        r1 = r1 & r6;
        r2 = r1;
        goto L_0x04f7;
    L_0x051d:
        r1 = 0;
        goto L_0x0506;
    L_0x051f:
        r2 = r0.a();
        r4 = r12.a;
        r2 = r12.a(r2, r4, r3);
        r4 = r0.a();
        r5 = r12.a;
        r4 = r12.a(r4, r5, r1);
        r5 = r2 >>> r4;
        if (r5 != 0) goto L_0x0555;
    L_0x0537:
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FZ;
        r1 = r1.getFlag();
    L_0x053d:
        r4 = r4 + -1;
        r2 = r2 >>> r4;
        r4 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FC;
        r4 = r4.getFlag();
        r2 = r2 & r4;
        r1 = r1 | r2;
        r12.c = r1;
        r0 = r0.a();
        r1 = r12.a;
        r12.a(r0, r1, r3, r5);
        goto L_0x0033;
    L_0x0555:
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FS;
        r1 = r1.getFlag();
        r1 = r1 & r5;
        goto L_0x053d;
    L_0x055d:
        r2 = r0.a();
        r4 = r12.a;
        r2 = r12.a(r2, r4, r3);
        r4 = r0.a();
        r5 = r12.a;
        r4 = r12.a(r4, r5, r1);
        r5 = r2 >> r4;
        if (r5 != 0) goto L_0x0593;
    L_0x0575:
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FZ;
        r1 = r1.getFlag();
    L_0x057b:
        r4 = r4 + -1;
        r2 = r2 >> r4;
        r4 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FC;
        r4 = r4.getFlag();
        r2 = r2 & r4;
        r1 = r1 | r2;
        r12.c = r1;
        r0 = r0.a();
        r1 = r12.a;
        r12.a(r0, r1, r3, r5);
        goto L_0x0033;
    L_0x0593:
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FS;
        r1 = r1.getFlag();
        r1 = r1 & r5;
        goto L_0x057b;
    L_0x059b:
        r1 = r0.a();
        r2 = r12.a;
        r1 = r12.a(r1, r2, r3);
        r2 = -r1;
        if (r2 != 0) goto L_0x05bb;
    L_0x05a8:
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FZ;
        r1 = r1.getFlag();
    L_0x05ae:
        r12.c = r1;
        r0 = r0.a();
        r1 = r12.a;
        r12.a(r0, r1, r3, r2);
        goto L_0x0033;
    L_0x05bb:
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FC;
        r1 = r1.getFlag();
        r4 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FS;
        r4 = r4.getFlag();
        r4 = r4 & r2;
        r1 = r1 | r4;
        goto L_0x05ae;
    L_0x05ca:
        r0 = 1;
        r1 = r12.a;
        r2 = 1;
        r4 = r12.a;
        r2 = r12.a(r2, r4, r3);
        r2 = -r2;
        r12.a(r0, r1, r3, r2);
        goto L_0x0033;
    L_0x05da:
        r0 = 0;
        r1 = r12.a;
        r2 = 0;
        r4 = r12.a;
        r2 = r12.a(r2, r4, r3);
        r2 = -r2;
        r12.a(r0, r1, r3, r2);
        goto L_0x0033;
    L_0x05ea:
        r1 = 0;
        r0 = r12.b;
        r2 = 7;
        r0 = r0[r2];
        r0 = r0 + -4;
    L_0x05f2:
        r2 = 8;
        if (r1 < r2) goto L_0x0601;
    L_0x05f6:
        r0 = r12.b;
        r1 = 7;
        r2 = r0[r1];
        r2 = r2 + -32;
        r0[r1] = r2;
        goto L_0x0033;
    L_0x0601:
        r2 = 0;
        r3 = r12.a;
        r4 = 262143; // 0x3ffff float:3.6734E-40 double:1.29516E-318;
        r4 = r4 & r0;
        r5 = r12.b;
        r5 = r5[r1];
        r12.a(r2, r3, r4, r5);
        r1 = r1 + 1;
        r0 = r0 + -4;
        goto L_0x05f2;
    L_0x0614:
        r1 = 0;
        r0 = r12.b;
        r2 = 7;
        r0 = r0[r2];
    L_0x061a:
        r2 = 8;
        if (r1 >= r2) goto L_0x0033;
    L_0x061e:
        r2 = r12.b;
        r3 = 7 - r1;
        r4 = 0;
        r5 = r12.a;
        r6 = 262143; // 0x3ffff float:3.6734E-40 double:1.29516E-318;
        r6 = r6 & r0;
        r4 = r12.a(r4, r5, r6);
        r2[r3] = r4;
        r1 = r1 + 1;
        r0 = r0 + 4;
        goto L_0x061a;
    L_0x0634:
        r0 = r12.b;
        r1 = 7;
        r2 = r0[r1];
        r2 = r2 + -4;
        r0[r1] = r2;
        r0 = 0;
        r1 = r12.a;
        r2 = r12.b;
        r3 = 7;
        r2 = r2[r3];
        r3 = 262143; // 0x3ffff float:3.6734E-40 double:1.29516E-318;
        r2 = r2 & r3;
        r3 = r12.c;
        r12.a(r0, r1, r2, r3);
        goto L_0x0033;
    L_0x0650:
        r0 = 0;
        r1 = r12.a;
        r2 = r12.b;
        r3 = 7;
        r2 = r2[r3];
        r3 = 262143; // 0x3ffff float:3.6734E-40 double:1.29516E-318;
        r2 = r2 & r3;
        r0 = r12.a(r0, r1, r2);
        r12.c = r0;
        r0 = r12.b;
        r1 = 7;
        r2 = r0[r1];
        r2 = r2 + 4;
        r0[r1] = r2;
        goto L_0x0033;
    L_0x066d:
        r0 = 0;
        r2 = r12.a;
        r4 = 1;
        r5 = r12.a;
        r1 = r12.a(r4, r5, r1);
        r12.a(r0, r2, r3, r1);
        goto L_0x0033;
    L_0x067c:
        r0 = 0;
        r2 = r12.a;
        r4 = 1;
        r5 = r12.a;
        r1 = r12.a(r4, r5, r1);
        r1 = (byte) r1;
        r12.a(r0, r2, r3, r1);
        goto L_0x0033;
    L_0x068c:
        r2 = r0.a();
        r4 = r12.a;
        r2 = r12.a(r2, r4, r3);
        r4 = r0.a();
        r5 = r12.a;
        r6 = r0.a();
        r7 = r12.a;
        r6 = r12.a(r6, r7, r1);
        r12.a(r4, r5, r3, r6);
        r0 = r0.a();
        r3 = r12.a;
        r12.a(r0, r3, r1, r2);
        goto L_0x0033;
    L_0x06b4:
        r2 = r0.a();
        r4 = r12.a;
        r2 = r12.a(r2, r4, r3);
        r4 = (long) r2;
        r6 = -1;
        r2 = r0.a();
        r8 = r12.a;
        r1 = r12.a(r2, r8, r1);
        r8 = (long) r1;
        r6 = r6 * r8;
        r4 = r4 & r6;
        r6 = -1;
        r4 = r4 & r6;
        r6 = -1;
        r4 = r4 & r6;
        r1 = (int) r4;
        r0 = r0.a();
        r2 = r12.a;
        r12.a(r0, r2, r3, r1);
        goto L_0x0033;
    L_0x06e0:
        r2 = r0.a();
        r4 = r12.a;
        r1 = r12.a(r2, r4, r1);
        if (r1 == 0) goto L_0x0033;
    L_0x06ec:
        r2 = r0.a();
        r4 = r12.a;
        r2 = r12.a(r2, r4, r3);
        r1 = r2 / r1;
        r0 = r0.a();
        r2 = r12.a;
        r12.a(r0, r2, r3, r1);
        goto L_0x0033;
    L_0x0703:
        r2 = r0.a();
        r4 = r12.a;
        r4 = r12.a(r2, r4, r3);
        r2 = r12.c;
        r5 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FC;
        r5 = r5.getFlag();
        r5 = r5 & r2;
        r6 = (long) r4;
        r8 = -1;
        r2 = r0.a();
        r10 = r12.a;
        r1 = r12.a(r2, r10, r1);
        r10 = (long) r1;
        r8 = r8 + r10;
        r6 = r6 & r8;
        r8 = -1;
        r10 = (long) r5;
        r8 = r8 + r10;
        r6 = r6 & r8;
        r8 = -1;
        r6 = r6 & r8;
        r1 = (int) r6;
        r2 = r0.a();
        if (r2 == 0) goto L_0x07f3;
    L_0x0735:
        r1 = r1 & 255;
        r2 = r1;
    L_0x0738:
        if (r2 < r4) goto L_0x073e;
    L_0x073a:
        if (r2 != r4) goto L_0x074c;
    L_0x073c:
        if (r5 == 0) goto L_0x074c;
    L_0x073e:
        r1 = 1;
    L_0x073f:
        r12.c = r1;
        r0 = r0.a();
        r1 = r12.a;
        r12.a(r0, r1, r3, r2);
        goto L_0x0033;
    L_0x074c:
        if (r2 != 0) goto L_0x0755;
    L_0x074e:
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FZ;
        r1 = r1.getFlag();
        goto L_0x073f;
    L_0x0755:
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FS;
        r1 = r1.getFlag();
        r1 = r1 & r2;
        goto L_0x073f;
    L_0x075d:
        r2 = r0.a();
        r4 = r12.a;
        r4 = r12.a(r2, r4, r3);
        r2 = r12.c;
        r5 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FC;
        r5 = r5.getFlag();
        r5 = r5 & r2;
        r6 = (long) r4;
        r8 = -1;
        r2 = r0.a();
        r10 = r12.a;
        r1 = r12.a(r2, r10, r1);
        r10 = (long) r1;
        r8 = r8 - r10;
        r6 = r6 & r8;
        r8 = -1;
        r10 = (long) r5;
        r8 = r8 - r10;
        r6 = r6 & r8;
        r8 = -1;
        r6 = r6 & r8;
        r1 = (int) r6;
        r2 = r0.a();
        if (r2 == 0) goto L_0x07f1;
    L_0x078f:
        r1 = r1 & 255;
        r2 = r1;
    L_0x0792:
        if (r2 > r4) goto L_0x0798;
    L_0x0794:
        if (r2 != r4) goto L_0x07a6;
    L_0x0796:
        if (r5 == 0) goto L_0x07a6;
    L_0x0798:
        r1 = 1;
    L_0x0799:
        r12.c = r1;
        r0 = r0.a();
        r1 = r12.a;
        r12.a(r0, r1, r3, r2);
        goto L_0x0033;
    L_0x07a6:
        if (r2 != 0) goto L_0x07af;
    L_0x07a8:
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FZ;
        r1 = r1.getFlag();
        goto L_0x0799;
    L_0x07af:
        r1 = de.innosystec.unrar.unpack.vm.VMFlags.VM_FS;
        r1 = r1.getFlag();
        r1 = r1 & r2;
        goto L_0x0799;
    L_0x07b7:
        r0 = r12.b;
        r1 = 7;
        r0 = r0[r1];
        r1 = 262144; // 0x40000 float:3.67342E-40 double:1.295163E-318;
        if (r0 < r1) goto L_0x07c2;
    L_0x07c0:
        r0 = 1;
        return r0;
    L_0x07c2:
        r0 = 0;
        r1 = r12.a;
        r2 = r12.b;
        r3 = 7;
        r2 = r2[r3];
        r3 = 262143; // 0x3ffff float:3.6734E-40 double:1.29516E-318;
        r2 = r2 & r3;
        r0 = r12.a(r0, r1, r2);
        r12.a(r0);
        r0 = r12.b;
        r1 = 7;
        r2 = r0[r1];
        r2 = r2 + 4;
        r0[r1] = r2;
        goto L_0x000a;
    L_0x07e0:
        r0 = r0.b();
        r0 = r0.b();
        r0 = de.innosystec.unrar.unpack.vm.VMStandardFilters.findFilter(r0);
        r12.a(r0);
        goto L_0x0033;
    L_0x07f1:
        r2 = r1;
        goto L_0x0792;
    L_0x07f3:
        r2 = r1;
        goto L_0x0738;
        */
        throw new UnsupportedOperationException("Method not decompiled: de.innosystec.unrar.unpack.vm.b.a(java.util.List, int):boolean");
    }

    public void a(byte[] bArr, int i, f fVar) {
        int i2;
        l();
        int min = Math.min(32768, i);
        for (i2 = 0; i2 < min; i2++) {
            byte[] bArr2 = this.am;
            bArr2[i2] = (byte) (bArr2[i2] | bArr[i2]);
        }
        byte b = (byte) 0;
        for (i2 = 1; i2 < i; i2++) {
            b = (byte) (b ^ bArr[i2]);
        }
        d(8);
        fVar.a(0);
        if (b == bArr[0]) {
            d dVar;
            VMStandardFilters a = a(bArr, i);
            if (a != VMStandardFilters.VMSF_NONE) {
                dVar = new d();
                dVar.a(VMCommands.VM_STANDARD);
                dVar.b().b(a.getFilter());
                dVar.b().a(VMOpType.VM_OPNONE);
                dVar.c().a(VMOpType.VM_OPNONE);
                fVar.b().add(dVar);
                fVar.a(fVar.c() + 1);
                i = 0;
            }
            i2 = n();
            d(1);
            if ((i2 & 32768) != 0) {
                long a2 = ((long) a((a) this)) & 0;
                i2 = 0;
                while (this.ak < i && ((long) i2) < a2) {
                    fVar.h().add(Byte.valueOf((byte) (n() >> 8)));
                    d(8);
                    i2++;
                }
            }
            while (this.ak < i) {
                dVar = new d();
                i2 = n();
                if ((i2 & 32768) == 0) {
                    dVar.a(VMCommands.findVMCommand(i2 >> 12));
                    d(4);
                } else {
                    dVar.a(VMCommands.findVMCommand((i2 >> 10) - 24));
                    d(6);
                }
                if ((c.a[dVar.d().getVMCommand()] & 4) != 0) {
                    boolean z;
                    if ((n() >> 15) == 1) {
                        z = true;
                    } else {
                        z = false;
                    }
                    dVar.a(z);
                    d(1);
                } else {
                    dVar.a(false);
                }
                dVar.b().a(VMOpType.VM_OPNONE);
                dVar.c().a(VMOpType.VM_OPNONE);
                i2 = c.a[dVar.d().getVMCommand()] & 3;
                if (i2 > 0) {
                    a(dVar.b(), dVar.a());
                    if (i2 == 2) {
                        a(dVar.c(), dVar.a());
                    } else if (dVar.b().c() == VMOpType.VM_OPINT && (c.a[dVar.d().getVMCommand()] & 24) != 0) {
                        i2 = dVar.b().b();
                        if (i2 >= 256) {
                            i2 -= 256;
                        } else {
                            if (i2 >= Opcodes.FLOAT_TO_LONG) {
                                i2 -= 264;
                            } else if (i2 >= 16) {
                                i2 -= 8;
                            } else if (i2 >= 8) {
                                i2 -= 16;
                            }
                            i2 += fVar.c();
                        }
                        dVar.b().b(i2);
                    }
                }
                fVar.a(fVar.c() + 1);
                fVar.b().add(dVar);
            }
        }
        d dVar2 = new d();
        dVar2.a(VMCommands.VM_RET);
        dVar2.b().a(VMOpType.VM_OPNONE);
        dVar2.c().a(VMOpType.VM_OPNONE);
        fVar.b().add(dVar2);
        fVar.a(fVar.c() + 1);
        if (i != 0) {
            b(fVar);
        }
    }

    private void a(e eVar, boolean z) {
        int n = n();
        if ((32768 & n) != 0) {
            eVar.a(VMOpType.VM_OPREG);
            eVar.b((n >> 12) & 7);
            eVar.c(eVar.b());
            d(4);
        } else if ((49152 & n) == 0) {
            eVar.a(VMOpType.VM_OPINT);
            if (z) {
                eVar.b((n >> 6) & 255);
                d(10);
                return;
            }
            d(2);
            eVar.b(a((a) this));
        } else {
            eVar.a(VMOpType.VM_OPREGMEM);
            if ((n & 8192) == 0) {
                eVar.b((n >> 10) & 7);
                eVar.c(eVar.b());
                eVar.a(0);
                d(6);
                return;
            }
            if ((n & 4096) == 0) {
                eVar.b((n >> 9) & 7);
                eVar.c(eVar.b());
                d(7);
            } else {
                eVar.b(0);
                d(4);
            }
            eVar.a(a((a) this));
        }
    }

    private void b(f fVar) {
        List<d> b = fVar.b();
        for (d dVar : b) {
            VMCommands vMCommands;
            switch (c()[dVar.d().ordinal()]) {
                case 1:
                    if (dVar.a()) {
                        vMCommands = VMCommands.VM_MOVB;
                    } else {
                        vMCommands = VMCommands.VM_MOVD;
                    }
                    dVar.a(vMCommands);
                    break;
                case 2:
                    if (dVar.a()) {
                        vMCommands = VMCommands.VM_CMPB;
                    } else {
                        vMCommands = VMCommands.VM_CMPD;
                    }
                    dVar.a(vMCommands);
                    break;
                default:
                    if ((c.a[dVar.d().getVMCommand()] & 64) == 0) {
                        break;
                    }
                    Object obj;
                    int indexOf = b.indexOf(dVar) + 1;
                    while (indexOf < b.size()) {
                        byte b2 = c.a[((d) b.get(indexOf)).d().getVMCommand()];
                        if ((b2 & 56) != 0) {
                            obj = 1;
                        } else if ((b2 & 64) != 0) {
                            obj = null;
                        } else {
                            indexOf++;
                        }
                        if (obj != null) {
                            break;
                        }
                        switch (c()[dVar.d().ordinal()]) {
                            case 3:
                                if (dVar.a()) {
                                    vMCommands = VMCommands.VM_ADDD;
                                } else {
                                    vMCommands = VMCommands.VM_ADDB;
                                }
                                dVar.a(vMCommands);
                                break;
                            case 4:
                                if (dVar.a()) {
                                    vMCommands = VMCommands.VM_SUBD;
                                } else {
                                    vMCommands = VMCommands.VM_SUBB;
                                }
                                dVar.a(vMCommands);
                                break;
                            case 7:
                                if (dVar.a()) {
                                    vMCommands = VMCommands.VM_INCD;
                                } else {
                                    vMCommands = VMCommands.VM_INCB;
                                }
                                dVar.a(vMCommands);
                                break;
                            case 8:
                                if (dVar.a()) {
                                    vMCommands = VMCommands.VM_DECD;
                                } else {
                                    vMCommands = VMCommands.VM_DECB;
                                }
                                dVar.a(vMCommands);
                                break;
                            case 28:
                                if (dVar.a()) {
                                    vMCommands = VMCommands.VM_NEGD;
                                } else {
                                    vMCommands = VMCommands.VM_NEGB;
                                }
                                dVar.a(vMCommands);
                                break;
                            default:
                                break;
                        }
                    }
                    obj = null;
                    if (obj != null) {
                        switch (c()[dVar.d().ordinal()]) {
                            case 3:
                                if (dVar.a()) {
                                    vMCommands = VMCommands.VM_ADDD;
                                } else {
                                    vMCommands = VMCommands.VM_ADDB;
                                }
                                dVar.a(vMCommands);
                                break;
                            case 4:
                                if (dVar.a()) {
                                    vMCommands = VMCommands.VM_SUBD;
                                } else {
                                    vMCommands = VMCommands.VM_SUBB;
                                }
                                dVar.a(vMCommands);
                                break;
                            case 7:
                                if (dVar.a()) {
                                    vMCommands = VMCommands.VM_INCD;
                                } else {
                                    vMCommands = VMCommands.VM_INCB;
                                }
                                dVar.a(vMCommands);
                                break;
                            case 8:
                                if (dVar.a()) {
                                    vMCommands = VMCommands.VM_DECD;
                                } else {
                                    vMCommands = VMCommands.VM_DECB;
                                }
                                dVar.a(vMCommands);
                                break;
                            case 28:
                                if (dVar.a()) {
                                    vMCommands = VMCommands.VM_NEGD;
                                } else {
                                    vMCommands = VMCommands.VM_NEGB;
                                }
                                dVar.a(vMCommands);
                                break;
                            default:
                                break;
                        }
                    }
            }
        }
    }

    public static int a(a aVar) {
        int n = aVar.n();
        switch (49152 & n) {
            case 0:
                aVar.d(6);
                return (n >> 10) & 15;
            case 16384:
                if ((n & 15360) == 0) {
                    n = ((n >> 2) & 255) | -256;
                    aVar.d(14);
                    return n;
                }
                n = (n >> 6) & 255;
                aVar.d(10);
                return n;
            case 32768:
                aVar.d(2);
                n = aVar.n();
                aVar.d(16);
                return n;
            default:
                aVar.d(2);
                n = aVar.n() << 16;
                aVar.d(16);
                n |= aVar.n();
                aVar.d(16);
                return n;
        }
    }

    private VMStandardFilters a(byte[] bArr, int i) {
        int i2 = 0;
        g[] gVarArr = new g[]{new g(53, -1386780537, VMStandardFilters.VMSF_E8), new g(57, 1020781950, VMStandardFilters.VMSF_E8E9), new g(120, 929663295, VMStandardFilters.VMSF_ITANIUM), new g(29, 235276157, VMStandardFilters.VMSF_DELTA), new g(Opcodes.AND_INT, 472669640, VMStandardFilters.VMSF_RGB), new g(216, -1132075263, VMStandardFilters.VMSF_AUDIO), new g(40, 1186579808, VMStandardFilters.VMSF_UPCASE)};
        int a = a.a(-1, bArr, 0, bArr.length) ^ -1;
        while (i2 < gVarArr.length) {
            if (gVarArr[i2].a() == a && gVarArr[i2].b() == bArr.length) {
                return gVarArr[i2].c();
            }
            i2++;
        }
        return VMStandardFilters.VMSF_NONE;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(de.innosystec.unrar.unpack.vm.VMStandardFilters r28) {
        /*
        r27 = this;
        r2 = d();
        r3 = r28.ordinal();
        r2 = r2[r3];
        switch(r2) {
            case 2: goto L_0x000e;
            case 3: goto L_0x000e;
            case 4: goto L_0x0093;
            case 5: goto L_0x0177;
            case 6: goto L_0x024e;
            case 7: goto L_0x012e;
            case 8: goto L_0x03df;
            default: goto L_0x000d;
        };
    L_0x000d:
        return;
    L_0x000e:
        r0 = r27;
        r2 = r0.b;
        r3 = 4;
        r4 = r2[r3];
        r0 = r27;
        r2 = r0.b;
        r3 = 6;
        r2 = r2[r3];
        r2 = r2 & -1;
        r6 = (long) r2;
        r2 = 245760; // 0x3c000 float:3.44383E-40 double:1.214216E-318;
        if (r4 >= r2) goto L_0x000d;
    L_0x0024:
        r5 = 16777216; // 0x1000000 float:2.3509887E-38 double:8.289046E-317;
        r2 = de.innosystec.unrar.unpack.vm.VMStandardFilters.VMSF_E8E9;
        r0 = r28;
        if (r0 != r2) goto L_0x0074;
    L_0x002c:
        r2 = 233; // 0xe9 float:3.27E-43 double:1.15E-321;
    L_0x002e:
        r8 = (byte) r2;
        r2 = 0;
    L_0x0030:
        r3 = r4 + -4;
        if (r2 >= r3) goto L_0x000d;
    L_0x0034:
        r0 = r27;
        r9 = r0.a;
        r3 = r2 + 1;
        r2 = r9[r2];
        r9 = 232; // 0xe8 float:3.25E-43 double:1.146E-321;
        if (r2 == r9) goto L_0x0042;
    L_0x0040:
        if (r2 != r8) goto L_0x0431;
    L_0x0042:
        r10 = (long) r3;
        r10 = r10 + r6;
        r2 = 0;
        r0 = r27;
        r9 = r0.a;
        r0 = r27;
        r2 = r0.a(r2, r9, r3);
        r12 = (long) r2;
        r14 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r14 = r14 & r12;
        r16 = 0;
        r2 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1));
        if (r2 == 0) goto L_0x0077;
    L_0x005a:
        r10 = r10 + r12;
        r14 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r10 = r10 & r14;
        r14 = 0;
        r2 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1));
        if (r2 != 0) goto L_0x0071;
    L_0x0065:
        r2 = 0;
        r0 = r27;
        r9 = r0.a;
        r10 = (int) r12;
        r10 = r10 + r5;
        r0 = r27;
        r0.a(r2, r9, r3, r10);
    L_0x0071:
        r2 = r3 + 4;
        goto L_0x0030;
    L_0x0074:
        r2 = 232; // 0xe8 float:3.25E-43 double:1.146E-321;
        goto L_0x002e;
    L_0x0077:
        r14 = (long) r5;
        r14 = r12 - r14;
        r16 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r14 = r14 & r16;
        r16 = 0;
        r2 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1));
        if (r2 == 0) goto L_0x0071;
    L_0x0085:
        r2 = 0;
        r0 = r27;
        r9 = r0.a;
        r10 = r12 - r10;
        r10 = (int) r10;
        r0 = r27;
        r0.a(r2, r9, r3, r10);
        goto L_0x0071;
    L_0x0093:
        r0 = r27;
        r2 = r0.b;
        r3 = 4;
        r6 = r2[r3];
        r0 = r27;
        r2 = r0.b;
        r3 = 6;
        r2 = r2[r3];
        r2 = r2 & -1;
        r4 = (long) r2;
        r2 = 245760; // 0x3c000 float:3.44383E-40 double:1.214216E-318;
        if (r6 >= r2) goto L_0x000d;
    L_0x00a9:
        r2 = 0;
        r3 = 16;
        r7 = new byte[r3];
        r3 = 0;
        r8 = 4;
        r7[r3] = r8;
        r3 = 1;
        r8 = 4;
        r7[r3] = r8;
        r3 = 2;
        r8 = 6;
        r7[r3] = r8;
        r3 = 3;
        r8 = 6;
        r7[r3] = r8;
        r3 = 6;
        r8 = 7;
        r7[r3] = r8;
        r3 = 7;
        r8 = 7;
        r7[r3] = r8;
        r3 = 8;
        r8 = 4;
        r7[r3] = r8;
        r3 = 9;
        r8 = 4;
        r7[r3] = r8;
        r3 = 12;
        r8 = 4;
        r7[r3] = r8;
        r3 = 13;
        r8 = 4;
        r7[r3] = r8;
        r3 = 4;
        r4 = r4 >>> r3;
        r3 = r2;
    L_0x00dd:
        r2 = r6 + -21;
        if (r3 >= r2) goto L_0x000d;
    L_0x00e1:
        r0 = r27;
        r2 = r0.a;
        r2 = r2[r3];
        r2 = r2 & 31;
        r2 = r2 + -16;
        if (r2 < 0) goto L_0x00f5;
    L_0x00ed:
        r8 = r7[r2];
        if (r8 == 0) goto L_0x00f5;
    L_0x00f1:
        r2 = 0;
    L_0x00f2:
        r9 = 2;
        if (r2 <= r9) goto L_0x00fc;
    L_0x00f5:
        r2 = r3 + 16;
        r8 = 1;
        r4 = r4 + r8;
        r3 = r2;
        goto L_0x00dd;
    L_0x00fc:
        r9 = 1;
        r9 = r9 << r2;
        r9 = r9 & r8;
        if (r9 == 0) goto L_0x012b;
    L_0x0101:
        r9 = r2 * 41;
        r9 = r9 + 5;
        r10 = r9 + 37;
        r11 = 4;
        r0 = r27;
        r10 = r0.a(r3, r10, r11);
        r11 = 5;
        if (r10 != r11) goto L_0x012b;
    L_0x0111:
        r10 = r9 + 13;
        r11 = 20;
        r0 = r27;
        r10 = r0.a(r3, r10, r11);
        r10 = (long) r10;
        r10 = r10 - r4;
        r10 = (int) r10;
        r11 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r10 = r10 & r11;
        r9 = r9 + 13;
        r11 = 20;
        r0 = r27;
        r0.a(r3, r10, r9, r11);
    L_0x012b:
        r2 = r2 + 1;
        goto L_0x00f2;
    L_0x012e:
        r0 = r27;
        r2 = r0.b;
        r3 = 4;
        r2 = r2[r3];
        r7 = r2 & -1;
        r0 = r27;
        r2 = r0.b;
        r3 = 0;
        r2 = r2[r3];
        r8 = r2 & -1;
        r4 = 0;
        r2 = r7 * 2;
        r9 = r2 & -1;
        r2 = 0;
        r0 = r27;
        r3 = r0.a;
        r5 = 245792; // 0x3c020 float:3.44428E-40 double:1.214374E-318;
        r0 = r27;
        r0.a(r2, r3, r5, r7);
        r2 = 122880; // 0x1e000 float:1.72192E-40 double:6.0711E-319;
        if (r7 >= r2) goto L_0x000d;
    L_0x0157:
        r2 = 0;
        r6 = r2;
    L_0x0159:
        if (r6 >= r8) goto L_0x000d;
    L_0x015b:
        r3 = 0;
        r2 = r7 + r6;
    L_0x015e:
        if (r2 < r9) goto L_0x0164;
    L_0x0160:
        r2 = r6 + 1;
        r6 = r2;
        goto L_0x0159;
    L_0x0164:
        r0 = r27;
        r10 = r0.a;
        r0 = r27;
        r11 = r0.a;
        r5 = r4 + 1;
        r4 = r11[r4];
        r3 = r3 - r4;
        r3 = (byte) r3;
        r10[r2] = r3;
        r2 = r2 + r8;
        r4 = r5;
        goto L_0x015e;
    L_0x0177:
        r0 = r27;
        r2 = r0.b;
        r3 = 4;
        r11 = r2[r3];
        r0 = r27;
        r2 = r0.b;
        r3 = 0;
        r2 = r2[r3];
        r12 = r2 + -3;
        r0 = r27;
        r2 = r0.b;
        r3 = 1;
        r4 = r2[r3];
        r13 = 3;
        r5 = 0;
        r2 = 0;
        r0 = r27;
        r3 = r0.a;
        r6 = 245792; // 0x3c020 float:3.44428E-40 double:1.214374E-318;
        r0 = r27;
        r0.a(r2, r3, r6, r11);
        r2 = 122880; // 0x1e000 float:1.72192E-40 double:6.0711E-319;
        if (r11 >= r2) goto L_0x000d;
    L_0x01a2:
        if (r4 < 0) goto L_0x000d;
    L_0x01a4:
        r6 = 0;
    L_0x01a5:
        if (r6 < r13) goto L_0x01d3;
    L_0x01a7:
        r3 = r11 + -2;
        r2 = r4;
    L_0x01aa:
        if (r2 >= r3) goto L_0x000d;
    L_0x01ac:
        r0 = r27;
        r4 = r0.a;
        r5 = r11 + r2;
        r5 = r5 + 1;
        r4 = r4[r5];
        r0 = r27;
        r5 = r0.a;
        r6 = r11 + r2;
        r7 = r5[r6];
        r7 = r7 + r4;
        r7 = (byte) r7;
        r5[r6] = r7;
        r0 = r27;
        r5 = r0.a;
        r6 = r11 + r2;
        r6 = r6 + 2;
        r7 = r5[r6];
        r4 = r4 + r7;
        r4 = (byte) r4;
        r5[r6] = r4;
        r2 = r2 + 3;
        goto L_0x01aa;
    L_0x01d3:
        r2 = 0;
        r7 = r5;
        r5 = r6;
    L_0x01d7:
        if (r5 < r11) goto L_0x01dd;
    L_0x01d9:
        r6 = r6 + 1;
        r5 = r7;
        goto L_0x01a5;
    L_0x01dd:
        r8 = r5 - r12;
        r9 = 3;
        if (r8 < r9) goto L_0x0223;
    L_0x01e2:
        r8 = r8 + r11;
        r0 = r27;
        r9 = r0.a;
        r9 = r9[r8];
        r9 = r9 & 255;
        r0 = r27;
        r10 = r0.a;
        r8 = r8 + -3;
        r8 = r10[r8];
        r8 = r8 & 255;
        r14 = (long) r9;
        r14 = r14 + r2;
        r0 = (long) r8;
        r16 = r0;
        r14 = r14 - r16;
        r16 = r14 - r2;
        r0 = r16;
        r10 = (int) r0;
        r10 = java.lang.Math.abs(r10);
        r0 = (long) r9;
        r16 = r0;
        r16 = r14 - r16;
        r0 = r16;
        r0 = (int) r0;
        r16 = r0;
        r16 = java.lang.Math.abs(r16);
        r0 = (long) r8;
        r18 = r0;
        r14 = r14 - r18;
        r14 = (int) r14;
        r14 = java.lang.Math.abs(r14);
        r0 = r16;
        if (r10 > r0) goto L_0x0246;
    L_0x0221:
        if (r10 > r14) goto L_0x0246;
    L_0x0223:
        r0 = r27;
        r8 = r0.a;
        r10 = r7 + 1;
        r7 = r8[r7];
        r8 = (long) r7;
        r2 = r2 - r8;
        r8 = 255; // 0xff float:3.57E-43 double:1.26E-321;
        r2 = r2 & r8;
        r8 = 255; // 0xff float:3.57E-43 double:1.26E-321;
        r8 = r8 & r2;
        r0 = r27;
        r2 = r0.a;
        r3 = r11 + r5;
        r14 = 255; // 0xff float:3.57E-43 double:1.26E-321;
        r14 = r14 & r8;
        r7 = (int) r14;
        r7 = (byte) r7;
        r2[r3] = r7;
        r2 = r5 + r13;
        r5 = r2;
        r7 = r10;
        r2 = r8;
        goto L_0x01d7;
    L_0x0246:
        r0 = r16;
        if (r0 > r14) goto L_0x024c;
    L_0x024a:
        r2 = (long) r9;
        goto L_0x0223;
    L_0x024c:
        r2 = (long) r8;
        goto L_0x0223;
    L_0x024e:
        r0 = r27;
        r2 = r0.b;
        r3 = 4;
        r20 = r2[r3];
        r0 = r27;
        r2 = r0.b;
        r3 = 0;
        r21 = r2[r3];
        r13 = 0;
        r2 = 0;
        r0 = r27;
        r3 = r0.a;
        r4 = 245792; // 0x3c020 float:3.44428E-40 double:1.214374E-318;
        r0 = r27;
        r1 = r20;
        r0.a(r2, r3, r4, r1);
        r2 = 122880; // 0x1e000 float:1.72192E-40 double:6.0711E-319;
        r0 = r20;
        if (r0 >= r2) goto L_0x000d;
    L_0x0273:
        r12 = 0;
    L_0x0274:
        r0 = r21;
        if (r12 >= r0) goto L_0x000d;
    L_0x0278:
        r10 = 0;
        r6 = 0;
        r2 = 7;
        r0 = new long[r2];
        r22 = r0;
        r5 = 0;
        r4 = 0;
        r9 = 0;
        r8 = 0;
        r3 = 0;
        r2 = 0;
        r16 = r10;
        r11 = r12;
        r10 = r2;
        r2 = r4;
        r4 = r5;
        r5 = r13;
    L_0x028e:
        r0 = r20;
        if (r11 < r0) goto L_0x0296;
    L_0x0292:
        r12 = r12 + 1;
        r13 = r5;
        goto L_0x0274;
    L_0x0296:
        r13 = (int) r6;
        r13 = r13 - r4;
        r14 = (int) r6;
        r6 = 8;
        r6 = r6 * r16;
        r4 = r9 * r14;
        r0 = (long) r4;
        r18 = r0;
        r6 = r6 + r18;
        r4 = r8 * r13;
        r0 = (long) r4;
        r18 = r0;
        r6 = r6 + r18;
        r4 = r3 * r2;
        r0 = (long) r4;
        r18 = r0;
        r6 = r6 + r18;
        r4 = 3;
        r6 = r6 >>> r4;
        r18 = 255; // 0xff float:3.57E-43 double:1.26E-321;
        r6 = r6 & r18;
        r0 = r27;
        r4 = r0.a;
        r15 = r5 + 1;
        r4 = r4[r5];
        r4 = r4 & 255;
        r4 = (long) r4;
        r6 = r6 - r4;
        r18 = -1;
        r18 = r18 & r6;
        r0 = r27;
        r6 = r0.a;
        r7 = r20 + r11;
        r0 = r18;
        r0 = (int) r0;
        r23 = r0;
        r0 = r23;
        r0 = (byte) r0;
        r23 = r0;
        r6[r7] = r23;
        r6 = r18 - r16;
        r6 = (int) r6;
        r6 = (byte) r6;
        r0 = (long) r6;
        r16 = r0;
        r4 = (int) r4;
        r4 = (byte) r4;
        r4 = r4 << 3;
        r5 = 0;
        r6 = r22[r5];
        r23 = java.lang.Math.abs(r4);
        r0 = r23;
        r0 = (long) r0;
        r24 = r0;
        r6 = r6 + r24;
        r22[r5] = r6;
        r5 = 1;
        r6 = r22[r5];
        r23 = r4 - r14;
        r23 = java.lang.Math.abs(r23);
        r0 = r23;
        r0 = (long) r0;
        r24 = r0;
        r6 = r6 + r24;
        r22[r5] = r6;
        r5 = 2;
        r6 = r22[r5];
        r23 = r4 + r14;
        r23 = java.lang.Math.abs(r23);
        r0 = r23;
        r0 = (long) r0;
        r24 = r0;
        r6 = r6 + r24;
        r22[r5] = r6;
        r5 = 3;
        r6 = r22[r5];
        r23 = r4 - r13;
        r23 = java.lang.Math.abs(r23);
        r0 = r23;
        r0 = (long) r0;
        r24 = r0;
        r6 = r6 + r24;
        r22[r5] = r6;
        r5 = 4;
        r6 = r22[r5];
        r23 = r4 + r13;
        r23 = java.lang.Math.abs(r23);
        r0 = r23;
        r0 = (long) r0;
        r24 = r0;
        r6 = r6 + r24;
        r22[r5] = r6;
        r5 = 5;
        r6 = r22[r5];
        r23 = r4 - r2;
        r23 = java.lang.Math.abs(r23);
        r0 = r23;
        r0 = (long) r0;
        r24 = r0;
        r6 = r6 + r24;
        r22[r5] = r6;
        r5 = 6;
        r6 = r22[r5];
        r2 = r2 + r4;
        r2 = java.lang.Math.abs(r2);
        r0 = (long) r2;
        r24 = r0;
        r6 = r6 + r24;
        r22[r5] = r6;
        r2 = r10 & 31;
        if (r2 != 0) goto L_0x037a;
    L_0x0362:
        r2 = 0;
        r6 = r22[r2];
        r4 = 0;
        r2 = 0;
        r24 = 0;
        r22[r2] = r24;
        r2 = 1;
    L_0x036d:
        r0 = r22;
        r0 = r0.length;
        r23 = r0;
        r0 = r23;
        if (r2 < r0) goto L_0x038f;
    L_0x0376:
        r2 = (int) r4;
        switch(r2) {
            case 1: goto L_0x039f;
            case 2: goto L_0x03a9;
            case 3: goto L_0x03b3;
            case 4: goto L_0x03c0;
            case 5: goto L_0x03cd;
            case 6: goto L_0x03d6;
            default: goto L_0x037a;
        };
    L_0x037a:
        r2 = r3;
        r4 = r9;
        r3 = r8;
    L_0x037d:
        r6 = r11 + r21;
        r5 = r10 + 1;
        r10 = r5;
        r11 = r6;
        r8 = r3;
        r9 = r4;
        r3 = r2;
        r4 = r14;
        r6 = r16;
        r5 = r15;
        r16 = r18;
        r2 = r13;
        goto L_0x028e;
    L_0x038f:
        r24 = r22[r2];
        r23 = (r24 > r6 ? 1 : (r24 == r6 ? 0 : -1));
        if (r23 >= 0) goto L_0x0398;
    L_0x0395:
        r6 = r22[r2];
        r4 = (long) r2;
    L_0x0398:
        r24 = 0;
        r22[r2] = r24;
        r2 = r2 + 1;
        goto L_0x036d;
    L_0x039f:
        r2 = -16;
        if (r9 < r2) goto L_0x037a;
    L_0x03a3:
        r2 = r9 + -1;
        r4 = r2;
        r2 = r3;
        r3 = r8;
        goto L_0x037d;
    L_0x03a9:
        r2 = 16;
        if (r9 >= r2) goto L_0x037a;
    L_0x03ad:
        r2 = r9 + 1;
        r4 = r2;
        r2 = r3;
        r3 = r8;
        goto L_0x037d;
    L_0x03b3:
        r2 = -16;
        if (r8 < r2) goto L_0x037a;
    L_0x03b7:
        r2 = r8 + -1;
        r4 = r9;
        r26 = r2;
        r2 = r3;
        r3 = r26;
        goto L_0x037d;
    L_0x03c0:
        r2 = 16;
        if (r8 >= r2) goto L_0x037a;
    L_0x03c4:
        r2 = r8 + 1;
        r4 = r9;
        r26 = r2;
        r2 = r3;
        r3 = r26;
        goto L_0x037d;
    L_0x03cd:
        r2 = -16;
        if (r3 < r2) goto L_0x037a;
    L_0x03d1:
        r2 = r3 + -1;
        r3 = r8;
        r4 = r9;
        goto L_0x037d;
    L_0x03d6:
        r2 = 16;
        if (r3 >= r2) goto L_0x037a;
    L_0x03da:
        r2 = r3 + 1;
        r3 = r8;
        r4 = r9;
        goto L_0x037d;
    L_0x03df:
        r0 = r27;
        r2 = r0.b;
        r3 = 4;
        r6 = r2[r3];
        r3 = 0;
        r2 = 122880; // 0x1e000 float:1.72192E-40 double:6.0711E-319;
        if (r6 >= r2) goto L_0x000d;
    L_0x03ec:
        r5 = r6;
    L_0x03ed:
        if (r3 < r6) goto L_0x040c;
    L_0x03ef:
        r2 = 0;
        r0 = r27;
        r3 = r0.a;
        r4 = 245788; // 0x3c01c float:3.44422E-40 double:1.214354E-318;
        r5 = r5 - r6;
        r0 = r27;
        r0.a(r2, r3, r4, r5);
        r2 = 0;
        r0 = r27;
        r3 = r0.a;
        r4 = 245792; // 0x3c020 float:3.44428E-40 double:1.214374E-318;
        r0 = r27;
        r0.a(r2, r3, r4, r6);
        goto L_0x000d;
    L_0x040c:
        r0 = r27;
        r2 = r0.a;
        r4 = r3 + 1;
        r2 = r2[r3];
        r3 = 2;
        if (r2 != r3) goto L_0x042f;
    L_0x0417:
        r0 = r27;
        r2 = r0.a;
        r3 = r4 + 1;
        r2 = r2[r4];
        r4 = 2;
        if (r2 == r4) goto L_0x0425;
    L_0x0422:
        r2 = r2 + -32;
        r2 = (byte) r2;
    L_0x0425:
        r0 = r27;
        r7 = r0.a;
        r4 = r5 + 1;
        r7[r5] = r2;
        r5 = r4;
        goto L_0x03ed;
    L_0x042f:
        r3 = r4;
        goto L_0x0425;
    L_0x0431:
        r2 = r3;
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: de.innosystec.unrar.unpack.vm.b.a(de.innosystec.unrar.unpack.vm.VMStandardFilters):void");
    }

    private void a(int i, int i2, int i3, int i4) {
        int i5 = i3 / 8;
        int i6 = i3 & 7;
        int i7 = ((-1 >>> (32 - i4)) << i6) ^ -1;
        int i8 = i2 << i6;
        for (i6 = 0; i6 < 4; i6++) {
            byte[] bArr = this.a;
            int i9 = (i + i5) + i6;
            bArr[i9] = (byte) (bArr[i9] & i7);
            bArr = this.a;
            i9 = (i + i5) + i6;
            bArr[i9] = (byte) (bArr[i9] | i8);
            i7 = (i7 >>> 8) | WebView.NIGHT_MODE_COLOR;
            i8 >>>= 8;
        }
    }

    private int a(int i, int i2, int i3) {
        int i4 = i2 / 8;
        int i5 = i4 + 1;
        int i6 = i5 + 1;
        return (((((this.a[i4 + i] & 255) | ((this.a[i5 + i] & 255) << 8)) | ((this.a[i6 + i] & 255) << 16)) | ((this.a[(i6 + 1) + i] & 255) << 24)) >>> (i2 & 7)) & (-1 >>> (32 - i3));
    }

    public void a(int i, byte[] bArr, int i2, int i3) {
        if (i < 262144) {
            int i4 = 0;
            while (i4 < Math.min(bArr.length - i2, i3) && 262144 - i >= i4) {
                this.a[i + i4] = bArr[i2 + i4];
                i4++;
            }
        }
    }
}
