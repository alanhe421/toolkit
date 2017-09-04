package org.apaches.commons.codec.a;

import com.qq.taf.jce.JceStruct;
import com.tencent.tinker.android.dx.instruction.Opcodes;

/* compiled from: Base64 */
public class a extends b {
    static final byte[] a = new byte[]{JceStruct.SIMPLE_LIST, (byte) 10};
    private static final byte[] e = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};
    private static final byte[] f = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 45, (byte) 95};
    private static final byte[] g;
    private final byte[] h;
    private final byte[] i;
    private final byte[] j;
    private final int k;
    private final int l;

    static {
        byte[] bArr = new byte[Opcodes.NEG_INT];
        bArr[0] = (byte) -1;
        bArr[1] = (byte) -1;
        bArr[2] = (byte) -1;
        bArr[3] = (byte) -1;
        bArr[4] = (byte) -1;
        bArr[5] = (byte) -1;
        bArr[6] = (byte) -1;
        bArr[7] = (byte) -1;
        bArr[8] = (byte) -1;
        bArr[9] = (byte) -1;
        bArr[10] = (byte) -1;
        bArr[11] = (byte) -1;
        bArr[12] = (byte) -1;
        bArr[13] = (byte) -1;
        bArr[14] = (byte) -1;
        bArr[15] = (byte) -1;
        bArr[16] = (byte) -1;
        bArr[17] = (byte) -1;
        bArr[18] = (byte) -1;
        bArr[19] = (byte) -1;
        bArr[20] = (byte) -1;
        bArr[21] = (byte) -1;
        bArr[22] = (byte) -1;
        bArr[23] = (byte) -1;
        bArr[24] = (byte) -1;
        bArr[25] = (byte) -1;
        bArr[26] = (byte) -1;
        bArr[27] = (byte) -1;
        bArr[28] = (byte) -1;
        bArr[29] = (byte) -1;
        bArr[30] = (byte) -1;
        bArr[31] = (byte) -1;
        bArr[32] = (byte) -1;
        bArr[33] = (byte) -1;
        bArr[34] = (byte) -1;
        bArr[35] = (byte) -1;
        bArr[36] = (byte) -1;
        bArr[37] = (byte) -1;
        bArr[38] = (byte) -1;
        bArr[39] = (byte) -1;
        bArr[40] = (byte) -1;
        bArr[41] = (byte) -1;
        bArr[42] = (byte) -1;
        bArr[43] = (byte) 62;
        bArr[44] = (byte) -1;
        bArr[45] = (byte) 62;
        bArr[46] = (byte) -1;
        bArr[47] = (byte) 63;
        bArr[48] = (byte) 52;
        bArr[49] = (byte) 53;
        bArr[50] = (byte) 54;
        bArr[51] = (byte) 55;
        bArr[52] = (byte) 56;
        bArr[53] = (byte) 57;
        bArr[54] = (byte) 58;
        bArr[55] = (byte) 59;
        bArr[56] = (byte) 60;
        bArr[57] = (byte) 61;
        bArr[58] = (byte) -1;
        bArr[59] = (byte) -1;
        bArr[60] = (byte) -1;
        bArr[61] = (byte) -1;
        bArr[62] = (byte) -1;
        bArr[63] = (byte) -1;
        bArr[64] = (byte) -1;
        bArr[66] = (byte) 1;
        bArr[67] = (byte) 2;
        bArr[68] = (byte) 3;
        bArr[69] = (byte) 4;
        bArr[70] = (byte) 5;
        bArr[71] = (byte) 6;
        bArr[72] = (byte) 7;
        bArr[73] = (byte) 8;
        bArr[74] = (byte) 9;
        bArr[75] = (byte) 10;
        bArr[76] = JceStruct.STRUCT_END;
        bArr[77] = JceStruct.ZERO_TAG;
        bArr[78] = JceStruct.SIMPLE_LIST;
        bArr[79] = (byte) 14;
        bArr[80] = (byte) 15;
        bArr[81] = (byte) 16;
        bArr[82] = (byte) 17;
        bArr[83] = (byte) 18;
        bArr[84] = (byte) 19;
        bArr[85] = com.tencent.qalsdk.base.a.z;
        bArr[86] = (byte) 21;
        bArr[87] = (byte) 22;
        bArr[88] = (byte) 23;
        bArr[89] = (byte) 24;
        bArr[90] = (byte) 25;
        bArr[91] = (byte) -1;
        bArr[92] = (byte) -1;
        bArr[93] = (byte) -1;
        bArr[94] = (byte) -1;
        bArr[95] = (byte) 63;
        bArr[96] = (byte) -1;
        bArr[97] = (byte) 26;
        bArr[98] = (byte) 27;
        bArr[99] = (byte) 28;
        bArr[100] = (byte) 29;
        bArr[101] = (byte) 30;
        bArr[102] = (byte) 31;
        bArr[103] = (byte) 32;
        bArr[104] = (byte) 33;
        bArr[105] = (byte) 34;
        bArr[106] = (byte) 35;
        bArr[107] = (byte) 36;
        bArr[108] = (byte) 37;
        bArr[109] = (byte) 38;
        bArr[110] = (byte) 39;
        bArr[111] = (byte) 40;
        bArr[112] = (byte) 41;
        bArr[113] = (byte) 42;
        bArr[114] = (byte) 43;
        bArr[com.tencent.qalsdk.base.a.cd] = (byte) 44;
        bArr[116] = (byte) 45;
        bArr[Opcodes.INVOKE_SUPER_RANGE] = (byte) 46;
        bArr[118] = (byte) 47;
        bArr[Opcodes.INVOKE_STATIC_RANGE] = (byte) 48;
        bArr[120] = (byte) 49;
        bArr[121] = (byte) 50;
        bArr[122] = (byte) 51;
        g = bArr;
    }

    public a() {
        this(0);
    }

    public a(boolean z) {
        this(76, a, z);
    }

    public a(int i) {
        this(i, a);
    }

    public a(int i, byte[] bArr) {
        this(i, bArr, false);
    }

    public a(int i, byte[] bArr, boolean z) {
        int i2;
        if (bArr == null) {
            i2 = 0;
        } else {
            i2 = bArr.length;
        }
        super(3, 4, i, i2);
        this.i = g;
        if (bArr == null) {
            this.l = 4;
            this.j = null;
        } else if (c(bArr)) {
            throw new IllegalArgumentException("lineSeparator must not contain base64 characters: [" + c.a(bArr) + "]");
        } else if (i > 0) {
            this.l = bArr.length + 4;
            this.j = new byte[bArr.length];
            System.arraycopy(bArr, 0, this.j, 0, bArr.length);
        } else {
            this.l = 4;
            this.j = null;
        }
        this.k = this.l - 1;
        this.h = z ? f : e;
    }

    void a(byte[] bArr, int i, int i2, b$a org_apaches_commons_codec_a_b_a) {
        if (!org_apaches_commons_codec_a_b_a.f) {
            int i3;
            int i4;
            if (i2 < 0) {
                org_apaches_commons_codec_a_b_a.f = true;
                if (org_apaches_commons_codec_a_b_a.h != 0 || this.d != 0) {
                    Object a = a(this.l, org_apaches_commons_codec_a_b_a);
                    i3 = org_apaches_commons_codec_a_b_a.d;
                    switch (org_apaches_commons_codec_a_b_a.h) {
                        case 0:
                            break;
                        case 1:
                            i4 = org_apaches_commons_codec_a_b_a.d;
                            org_apaches_commons_codec_a_b_a.d = i4 + 1;
                            a[i4] = this.h[(org_apaches_commons_codec_a_b_a.a >> 2) & 63];
                            i4 = org_apaches_commons_codec_a_b_a.d;
                            org_apaches_commons_codec_a_b_a.d = i4 + 1;
                            a[i4] = this.h[(org_apaches_commons_codec_a_b_a.a << 4) & 63];
                            if (this.h == e) {
                                i4 = org_apaches_commons_codec_a_b_a.d;
                                org_apaches_commons_codec_a_b_a.d = i4 + 1;
                                a[i4] = this.c;
                                i4 = org_apaches_commons_codec_a_b_a.d;
                                org_apaches_commons_codec_a_b_a.d = i4 + 1;
                                a[i4] = this.c;
                                break;
                            }
                            break;
                        case 2:
                            i4 = org_apaches_commons_codec_a_b_a.d;
                            org_apaches_commons_codec_a_b_a.d = i4 + 1;
                            a[i4] = this.h[(org_apaches_commons_codec_a_b_a.a >> 10) & 63];
                            i4 = org_apaches_commons_codec_a_b_a.d;
                            org_apaches_commons_codec_a_b_a.d = i4 + 1;
                            a[i4] = this.h[(org_apaches_commons_codec_a_b_a.a >> 4) & 63];
                            i4 = org_apaches_commons_codec_a_b_a.d;
                            org_apaches_commons_codec_a_b_a.d = i4 + 1;
                            a[i4] = this.h[(org_apaches_commons_codec_a_b_a.a << 2) & 63];
                            if (this.h == e) {
                                i4 = org_apaches_commons_codec_a_b_a.d;
                                org_apaches_commons_codec_a_b_a.d = i4 + 1;
                                a[i4] = this.c;
                                break;
                            }
                            break;
                        default:
                            throw new IllegalStateException("Impossible modulus " + org_apaches_commons_codec_a_b_a.h);
                    }
                    org_apaches_commons_codec_a_b_a.g = (org_apaches_commons_codec_a_b_a.d - i3) + org_apaches_commons_codec_a_b_a.g;
                    if (this.d > 0 && org_apaches_commons_codec_a_b_a.g > 0) {
                        System.arraycopy(this.j, 0, a, org_apaches_commons_codec_a_b_a.d, this.j.length);
                        org_apaches_commons_codec_a_b_a.d += this.j.length;
                        return;
                    }
                    return;
                }
                return;
            }
            i3 = 0;
            while (i3 < i2) {
                Object a2 = a(this.l, org_apaches_commons_codec_a_b_a);
                org_apaches_commons_codec_a_b_a.h = (org_apaches_commons_codec_a_b_a.h + 1) % 3;
                i4 = i + 1;
                int i5 = bArr[i];
                if (i5 < 0) {
                    i5 += 256;
                }
                org_apaches_commons_codec_a_b_a.a = i5 + (org_apaches_commons_codec_a_b_a.a << 8);
                if (org_apaches_commons_codec_a_b_a.h == 0) {
                    i5 = org_apaches_commons_codec_a_b_a.d;
                    org_apaches_commons_codec_a_b_a.d = i5 + 1;
                    a2[i5] = this.h[(org_apaches_commons_codec_a_b_a.a >> 18) & 63];
                    i5 = org_apaches_commons_codec_a_b_a.d;
                    org_apaches_commons_codec_a_b_a.d = i5 + 1;
                    a2[i5] = this.h[(org_apaches_commons_codec_a_b_a.a >> 12) & 63];
                    i5 = org_apaches_commons_codec_a_b_a.d;
                    org_apaches_commons_codec_a_b_a.d = i5 + 1;
                    a2[i5] = this.h[(org_apaches_commons_codec_a_b_a.a >> 6) & 63];
                    i5 = org_apaches_commons_codec_a_b_a.d;
                    org_apaches_commons_codec_a_b_a.d = i5 + 1;
                    a2[i5] = this.h[org_apaches_commons_codec_a_b_a.a & 63];
                    org_apaches_commons_codec_a_b_a.g += 4;
                    if (this.d > 0 && this.d <= org_apaches_commons_codec_a_b_a.g) {
                        System.arraycopy(this.j, 0, a2, org_apaches_commons_codec_a_b_a.d, this.j.length);
                        org_apaches_commons_codec_a_b_a.d += this.j.length;
                        org_apaches_commons_codec_a_b_a.g = 0;
                    }
                }
                i3++;
                i = i4;
            }
        }
    }

    public static byte[] a(byte[] bArr) {
        return a(bArr, false, true);
    }

    public static byte[] a(byte[] bArr, boolean z, boolean z2) {
        return a(bArr, z, z2, Integer.MAX_VALUE);
    }

    public static byte[] a(byte[] bArr, boolean z, boolean z2, int i) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        a aVar = z ? new a(z2) : new a(0, a, z2);
        long d = aVar.d(bArr);
        if (d <= ((long) i)) {
            return aVar.b(bArr);
        }
        throw new IllegalArgumentException("Input array too big, the output array would be bigger (" + d + ") than the specified maximum size of " + i);
    }

    protected boolean a(byte b) {
        return b >= (byte) 0 && b < this.i.length && this.i[b] != (byte) -1;
    }
}
