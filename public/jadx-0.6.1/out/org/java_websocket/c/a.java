package org.java_websocket.c;

import com.etrump.jni.ETConverter;
import com.qq.taf.jce.JceStruct;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPOutputStream;
import tencent.tls.platform.TLSErrInfo;

/* compiled from: Base64 */
public class a {
    static final /* synthetic */ boolean a;
    private static final byte[] b = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};
    private static final byte[] c;
    private static final byte[] d = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 45, (byte) 95};
    private static final byte[] e;
    private static final byte[] f = new byte[]{(byte) 45, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 95, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122};
    private static final byte[] g;

    /* compiled from: Base64 */
    public static class a extends FilterOutputStream {
        private boolean a;
        private int b;
        private byte[] c;
        private int d;
        private int e;
        private boolean f;
        private byte[] g;
        private boolean h;
        private int i;
        private byte[] j;

        public a(OutputStream outputStream, int i) {
            int i2;
            boolean z = true;
            super(outputStream);
            this.f = (i & 8) != 0;
            if ((i & 1) == 0) {
                z = false;
            }
            this.a = z;
            if (this.a) {
                i2 = 3;
            } else {
                i2 = 4;
            }
            this.d = i2;
            this.c = new byte[this.d];
            this.b = 0;
            this.e = 0;
            this.h = false;
            this.g = new byte[4];
            this.i = i;
            this.j = a.c(i);
        }

        public void write(int i) throws IOException {
            if (this.h) {
                this.out.write(i);
            } else if (this.a) {
                r0 = this.c;
                r1 = this.b;
                this.b = r1 + 1;
                r0[r1] = (byte) i;
                if (this.b >= this.d) {
                    this.out.write(a.b(this.g, this.c, this.d, this.i));
                    this.e += 4;
                    if (this.f && this.e >= 76) {
                        this.out.write(10);
                        this.e = 0;
                    }
                    this.b = 0;
                }
            } else if (this.j[i & Opcodes.NEG_FLOAT] > (byte) -5) {
                r0 = this.c;
                r1 = this.b;
                this.b = r1 + 1;
                r0[r1] = (byte) i;
                if (this.b >= this.d) {
                    this.out.write(this.g, 0, a.b(this.c, 0, this.g, 0, this.i));
                    this.b = 0;
                }
            } else if (this.j[i & Opcodes.NEG_FLOAT] != (byte) -5) {
                throw new IOException("Invalid character in Base64 data.");
            }
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            if (this.h) {
                this.out.write(bArr, i, i2);
                return;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                write(bArr[i + i3]);
            }
        }

        public void a() throws IOException {
            if (this.b <= 0) {
                return;
            }
            if (this.a) {
                this.out.write(a.b(this.g, this.c, this.b, this.i));
                this.b = 0;
                return;
            }
            throw new IOException("Base64 input not properly padded.");
        }

        public void close() throws IOException {
            a();
            super.close();
            this.c = null;
            this.out = null;
        }
    }

    static {
        boolean z;
        if (a.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        a = z;
        byte[] bArr = new byte[256];
        bArr[0] = (byte) -9;
        bArr[1] = (byte) -9;
        bArr[2] = (byte) -9;
        bArr[3] = (byte) -9;
        bArr[4] = (byte) -9;
        bArr[5] = (byte) -9;
        bArr[6] = (byte) -9;
        bArr[7] = (byte) -9;
        bArr[8] = (byte) -9;
        bArr[9] = (byte) -5;
        bArr[10] = (byte) -5;
        bArr[11] = (byte) -9;
        bArr[12] = (byte) -9;
        bArr[13] = (byte) -5;
        bArr[14] = (byte) -9;
        bArr[15] = (byte) -9;
        bArr[16] = (byte) -9;
        bArr[17] = (byte) -9;
        bArr[18] = (byte) -9;
        bArr[19] = (byte) -9;
        bArr[20] = (byte) -9;
        bArr[21] = (byte) -9;
        bArr[22] = (byte) -9;
        bArr[23] = (byte) -9;
        bArr[24] = (byte) -9;
        bArr[25] = (byte) -9;
        bArr[26] = (byte) -9;
        bArr[27] = (byte) -9;
        bArr[28] = (byte) -9;
        bArr[29] = (byte) -9;
        bArr[30] = (byte) -9;
        bArr[31] = (byte) -9;
        bArr[32] = (byte) -5;
        bArr[33] = (byte) -9;
        bArr[34] = (byte) -9;
        bArr[35] = (byte) -9;
        bArr[36] = (byte) -9;
        bArr[37] = (byte) -9;
        bArr[38] = (byte) -9;
        bArr[39] = (byte) -9;
        bArr[40] = (byte) -9;
        bArr[41] = (byte) -9;
        bArr[42] = (byte) -9;
        bArr[43] = (byte) 62;
        bArr[44] = (byte) -9;
        bArr[45] = (byte) -9;
        bArr[46] = (byte) -9;
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
        bArr[58] = (byte) -9;
        bArr[59] = (byte) -9;
        bArr[60] = (byte) -9;
        bArr[61] = (byte) -1;
        bArr[62] = (byte) -9;
        bArr[63] = (byte) -9;
        bArr[64] = (byte) -9;
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
        bArr[91] = (byte) -9;
        bArr[92] = (byte) -9;
        bArr[93] = (byte) -9;
        bArr[94] = (byte) -9;
        bArr[95] = (byte) -9;
        bArr[96] = (byte) -9;
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
        bArr[Opcodes.NEG_INT] = (byte) -9;
        bArr[Opcodes.NOT_INT] = (byte) -9;
        bArr[Opcodes.NEG_LONG] = (byte) -9;
        bArr[Opcodes.NOT_LONG] = (byte) -9;
        bArr[Opcodes.NEG_FLOAT] = (byte) -9;
        bArr[128] = (byte) -9;
        bArr[Opcodes.INT_TO_LONG] = (byte) -9;
        bArr[Opcodes.INT_TO_FLOAT] = (byte) -9;
        bArr[Opcodes.INT_TO_DOUBLE] = (byte) -9;
        bArr[Opcodes.LONG_TO_INT] = (byte) -9;
        bArr[Opcodes.LONG_TO_FLOAT] = (byte) -9;
        bArr[Opcodes.LONG_TO_DOUBLE] = (byte) -9;
        bArr[Opcodes.FLOAT_TO_INT] = (byte) -9;
        bArr[Opcodes.FLOAT_TO_LONG] = (byte) -9;
        bArr[Opcodes.FLOAT_TO_DOUBLE] = (byte) -9;
        bArr[Opcodes.DOUBLE_TO_INT] = (byte) -9;
        bArr[Opcodes.DOUBLE_TO_LONG] = (byte) -9;
        bArr[Opcodes.DOUBLE_TO_FLOAT] = (byte) -9;
        bArr[Opcodes.INT_TO_BYTE] = (byte) -9;
        bArr[Opcodes.INT_TO_CHAR] = (byte) -9;
        bArr[Opcodes.INT_TO_SHORT] = (byte) -9;
        bArr[Opcodes.ADD_INT] = (byte) -9;
        bArr[Opcodes.SUB_INT] = (byte) -9;
        bArr[Opcodes.MUL_INT] = (byte) -9;
        bArr[Opcodes.DIV_INT] = (byte) -9;
        bArr[Opcodes.REM_INT] = (byte) -9;
        bArr[Opcodes.AND_INT] = (byte) -9;
        bArr[Opcodes.OR_INT] = (byte) -9;
        bArr[Opcodes.XOR_INT] = (byte) -9;
        bArr[Opcodes.SHL_INT] = (byte) -9;
        bArr[Opcodes.SHR_INT] = (byte) -9;
        bArr[Opcodes.USHR_INT] = (byte) -9;
        bArr[Opcodes.ADD_LONG] = (byte) -9;
        bArr[Opcodes.SUB_LONG] = (byte) -9;
        bArr[Opcodes.MUL_LONG] = (byte) -9;
        bArr[Opcodes.DIV_LONG] = (byte) -9;
        bArr[Opcodes.REM_LONG] = (byte) -9;
        bArr[160] = (byte) -9;
        bArr[Opcodes.OR_LONG] = (byte) -9;
        bArr[Opcodes.XOR_LONG] = (byte) -9;
        bArr[Opcodes.SHL_LONG] = (byte) -9;
        bArr[Opcodes.SHR_LONG] = (byte) -9;
        bArr[Opcodes.USHR_LONG] = (byte) -9;
        bArr[Opcodes.ADD_FLOAT] = (byte) -9;
        bArr[Opcodes.SUB_FLOAT] = (byte) -9;
        bArr[Opcodes.MUL_FLOAT] = (byte) -9;
        bArr[Opcodes.DIV_FLOAT] = (byte) -9;
        bArr[Opcodes.REM_FLOAT] = (byte) -9;
        bArr[Opcodes.ADD_DOUBLE] = (byte) -9;
        bArr[Opcodes.SUB_DOUBLE] = (byte) -9;
        bArr[Opcodes.MUL_DOUBLE] = (byte) -9;
        bArr[Opcodes.DIV_DOUBLE] = (byte) -9;
        bArr[Opcodes.REM_DOUBLE] = (byte) -9;
        bArr[Opcodes.ADD_INT_2ADDR] = (byte) -9;
        bArr[Opcodes.SUB_INT_2ADDR] = (byte) -9;
        bArr[Opcodes.MUL_INT_2ADDR] = (byte) -9;
        bArr[Opcodes.DIV_INT_2ADDR] = (byte) -9;
        bArr[180] = (byte) -9;
        bArr[Opcodes.AND_INT_2ADDR] = (byte) -9;
        bArr[Opcodes.OR_INT_2ADDR] = (byte) -9;
        bArr[Opcodes.XOR_INT_2ADDR] = (byte) -9;
        bArr[Opcodes.SHL_INT_2ADDR] = (byte) -9;
        bArr[Opcodes.SHR_INT_2ADDR] = (byte) -9;
        bArr[Opcodes.USHR_INT_2ADDR] = (byte) -9;
        bArr[Opcodes.ADD_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.SUB_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.MUL_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.DIV_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.REM_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.AND_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.OR_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.XOR_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.SHL_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.SHR_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.USHR_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.ADD_FLOAT_2ADDR] = (byte) -9;
        bArr[Opcodes.SUB_FLOAT_2ADDR] = (byte) -9;
        bArr[200] = (byte) -9;
        bArr[201] = (byte) -9;
        bArr[202] = (byte) -9;
        bArr[203] = (byte) -9;
        bArr[204] = (byte) -9;
        bArr[205] = (byte) -9;
        bArr[206] = (byte) -9;
        bArr[207] = (byte) -9;
        bArr[208] = (byte) -9;
        bArr[209] = (byte) -9;
        bArr[210] = (byte) -9;
        bArr[211] = (byte) -9;
        bArr[212] = (byte) -9;
        bArr[213] = (byte) -9;
        bArr[214] = (byte) -9;
        bArr[215] = (byte) -9;
        bArr[216] = (byte) -9;
        bArr[217] = (byte) -9;
        bArr[218] = (byte) -9;
        bArr[219] = (byte) -9;
        bArr[220] = (byte) -9;
        bArr[221] = (byte) -9;
        bArr[Opcodes.OR_INT_LIT8] = (byte) -9;
        bArr[Opcodes.XOR_INT_LIT8] = (byte) -9;
        bArr[Opcodes.SHL_INT_LIT8] = (byte) -9;
        bArr[Opcodes.SHR_INT_LIT8] = (byte) -9;
        bArr[Opcodes.USHR_INT_LIT8] = (byte) -9;
        bArr[227] = (byte) -9;
        bArr[228] = (byte) -9;
        bArr[TLSErrInfo.LOGIN_NO_ACCOUNT] = (byte) -9;
        bArr[230] = (byte) -9;
        bArr[231] = (byte) -9;
        bArr[232] = (byte) -9;
        bArr[233] = (byte) -9;
        bArr[234] = (byte) -9;
        bArr[235] = (byte) -9;
        bArr[236] = (byte) -9;
        bArr[237] = (byte) -9;
        bArr[238] = (byte) -9;
        bArr[239] = (byte) -9;
        bArr[ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK] = (byte) -9;
        bArr[241] = (byte) -9;
        bArr[242] = (byte) -9;
        bArr[243] = (byte) -9;
        bArr[244] = (byte) -9;
        bArr[245] = (byte) -9;
        bArr[246] = (byte) -9;
        bArr[247] = (byte) -9;
        bArr[248] = (byte) -9;
        bArr[249] = (byte) -9;
        bArr[250] = (byte) -9;
        bArr[251] = (byte) -9;
        bArr[252] = (byte) -9;
        bArr[253] = (byte) -9;
        bArr[254] = (byte) -9;
        bArr[255] = (byte) -9;
        c = bArr;
        bArr = new byte[256];
        bArr[0] = (byte) -9;
        bArr[1] = (byte) -9;
        bArr[2] = (byte) -9;
        bArr[3] = (byte) -9;
        bArr[4] = (byte) -9;
        bArr[5] = (byte) -9;
        bArr[6] = (byte) -9;
        bArr[7] = (byte) -9;
        bArr[8] = (byte) -9;
        bArr[9] = (byte) -5;
        bArr[10] = (byte) -5;
        bArr[11] = (byte) -9;
        bArr[12] = (byte) -9;
        bArr[13] = (byte) -5;
        bArr[14] = (byte) -9;
        bArr[15] = (byte) -9;
        bArr[16] = (byte) -9;
        bArr[17] = (byte) -9;
        bArr[18] = (byte) -9;
        bArr[19] = (byte) -9;
        bArr[20] = (byte) -9;
        bArr[21] = (byte) -9;
        bArr[22] = (byte) -9;
        bArr[23] = (byte) -9;
        bArr[24] = (byte) -9;
        bArr[25] = (byte) -9;
        bArr[26] = (byte) -9;
        bArr[27] = (byte) -9;
        bArr[28] = (byte) -9;
        bArr[29] = (byte) -9;
        bArr[30] = (byte) -9;
        bArr[31] = (byte) -9;
        bArr[32] = (byte) -5;
        bArr[33] = (byte) -9;
        bArr[34] = (byte) -9;
        bArr[35] = (byte) -9;
        bArr[36] = (byte) -9;
        bArr[37] = (byte) -9;
        bArr[38] = (byte) -9;
        bArr[39] = (byte) -9;
        bArr[40] = (byte) -9;
        bArr[41] = (byte) -9;
        bArr[42] = (byte) -9;
        bArr[43] = (byte) -9;
        bArr[44] = (byte) -9;
        bArr[45] = (byte) 62;
        bArr[46] = (byte) -9;
        bArr[47] = (byte) -9;
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
        bArr[58] = (byte) -9;
        bArr[59] = (byte) -9;
        bArr[60] = (byte) -9;
        bArr[61] = (byte) -1;
        bArr[62] = (byte) -9;
        bArr[63] = (byte) -9;
        bArr[64] = (byte) -9;
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
        bArr[91] = (byte) -9;
        bArr[92] = (byte) -9;
        bArr[93] = (byte) -9;
        bArr[94] = (byte) -9;
        bArr[95] = (byte) 63;
        bArr[96] = (byte) -9;
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
        bArr[Opcodes.NEG_INT] = (byte) -9;
        bArr[Opcodes.NOT_INT] = (byte) -9;
        bArr[Opcodes.NEG_LONG] = (byte) -9;
        bArr[Opcodes.NOT_LONG] = (byte) -9;
        bArr[Opcodes.NEG_FLOAT] = (byte) -9;
        bArr[128] = (byte) -9;
        bArr[Opcodes.INT_TO_LONG] = (byte) -9;
        bArr[Opcodes.INT_TO_FLOAT] = (byte) -9;
        bArr[Opcodes.INT_TO_DOUBLE] = (byte) -9;
        bArr[Opcodes.LONG_TO_INT] = (byte) -9;
        bArr[Opcodes.LONG_TO_FLOAT] = (byte) -9;
        bArr[Opcodes.LONG_TO_DOUBLE] = (byte) -9;
        bArr[Opcodes.FLOAT_TO_INT] = (byte) -9;
        bArr[Opcodes.FLOAT_TO_LONG] = (byte) -9;
        bArr[Opcodes.FLOAT_TO_DOUBLE] = (byte) -9;
        bArr[Opcodes.DOUBLE_TO_INT] = (byte) -9;
        bArr[Opcodes.DOUBLE_TO_LONG] = (byte) -9;
        bArr[Opcodes.DOUBLE_TO_FLOAT] = (byte) -9;
        bArr[Opcodes.INT_TO_BYTE] = (byte) -9;
        bArr[Opcodes.INT_TO_CHAR] = (byte) -9;
        bArr[Opcodes.INT_TO_SHORT] = (byte) -9;
        bArr[Opcodes.ADD_INT] = (byte) -9;
        bArr[Opcodes.SUB_INT] = (byte) -9;
        bArr[Opcodes.MUL_INT] = (byte) -9;
        bArr[Opcodes.DIV_INT] = (byte) -9;
        bArr[Opcodes.REM_INT] = (byte) -9;
        bArr[Opcodes.AND_INT] = (byte) -9;
        bArr[Opcodes.OR_INT] = (byte) -9;
        bArr[Opcodes.XOR_INT] = (byte) -9;
        bArr[Opcodes.SHL_INT] = (byte) -9;
        bArr[Opcodes.SHR_INT] = (byte) -9;
        bArr[Opcodes.USHR_INT] = (byte) -9;
        bArr[Opcodes.ADD_LONG] = (byte) -9;
        bArr[Opcodes.SUB_LONG] = (byte) -9;
        bArr[Opcodes.MUL_LONG] = (byte) -9;
        bArr[Opcodes.DIV_LONG] = (byte) -9;
        bArr[Opcodes.REM_LONG] = (byte) -9;
        bArr[160] = (byte) -9;
        bArr[Opcodes.OR_LONG] = (byte) -9;
        bArr[Opcodes.XOR_LONG] = (byte) -9;
        bArr[Opcodes.SHL_LONG] = (byte) -9;
        bArr[Opcodes.SHR_LONG] = (byte) -9;
        bArr[Opcodes.USHR_LONG] = (byte) -9;
        bArr[Opcodes.ADD_FLOAT] = (byte) -9;
        bArr[Opcodes.SUB_FLOAT] = (byte) -9;
        bArr[Opcodes.MUL_FLOAT] = (byte) -9;
        bArr[Opcodes.DIV_FLOAT] = (byte) -9;
        bArr[Opcodes.REM_FLOAT] = (byte) -9;
        bArr[Opcodes.ADD_DOUBLE] = (byte) -9;
        bArr[Opcodes.SUB_DOUBLE] = (byte) -9;
        bArr[Opcodes.MUL_DOUBLE] = (byte) -9;
        bArr[Opcodes.DIV_DOUBLE] = (byte) -9;
        bArr[Opcodes.REM_DOUBLE] = (byte) -9;
        bArr[Opcodes.ADD_INT_2ADDR] = (byte) -9;
        bArr[Opcodes.SUB_INT_2ADDR] = (byte) -9;
        bArr[Opcodes.MUL_INT_2ADDR] = (byte) -9;
        bArr[Opcodes.DIV_INT_2ADDR] = (byte) -9;
        bArr[180] = (byte) -9;
        bArr[Opcodes.AND_INT_2ADDR] = (byte) -9;
        bArr[Opcodes.OR_INT_2ADDR] = (byte) -9;
        bArr[Opcodes.XOR_INT_2ADDR] = (byte) -9;
        bArr[Opcodes.SHL_INT_2ADDR] = (byte) -9;
        bArr[Opcodes.SHR_INT_2ADDR] = (byte) -9;
        bArr[Opcodes.USHR_INT_2ADDR] = (byte) -9;
        bArr[Opcodes.ADD_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.SUB_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.MUL_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.DIV_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.REM_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.AND_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.OR_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.XOR_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.SHL_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.SHR_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.USHR_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.ADD_FLOAT_2ADDR] = (byte) -9;
        bArr[Opcodes.SUB_FLOAT_2ADDR] = (byte) -9;
        bArr[200] = (byte) -9;
        bArr[201] = (byte) -9;
        bArr[202] = (byte) -9;
        bArr[203] = (byte) -9;
        bArr[204] = (byte) -9;
        bArr[205] = (byte) -9;
        bArr[206] = (byte) -9;
        bArr[207] = (byte) -9;
        bArr[208] = (byte) -9;
        bArr[209] = (byte) -9;
        bArr[210] = (byte) -9;
        bArr[211] = (byte) -9;
        bArr[212] = (byte) -9;
        bArr[213] = (byte) -9;
        bArr[214] = (byte) -9;
        bArr[215] = (byte) -9;
        bArr[216] = (byte) -9;
        bArr[217] = (byte) -9;
        bArr[218] = (byte) -9;
        bArr[219] = (byte) -9;
        bArr[220] = (byte) -9;
        bArr[221] = (byte) -9;
        bArr[Opcodes.OR_INT_LIT8] = (byte) -9;
        bArr[Opcodes.XOR_INT_LIT8] = (byte) -9;
        bArr[Opcodes.SHL_INT_LIT8] = (byte) -9;
        bArr[Opcodes.SHR_INT_LIT8] = (byte) -9;
        bArr[Opcodes.USHR_INT_LIT8] = (byte) -9;
        bArr[227] = (byte) -9;
        bArr[228] = (byte) -9;
        bArr[TLSErrInfo.LOGIN_NO_ACCOUNT] = (byte) -9;
        bArr[230] = (byte) -9;
        bArr[231] = (byte) -9;
        bArr[232] = (byte) -9;
        bArr[233] = (byte) -9;
        bArr[234] = (byte) -9;
        bArr[235] = (byte) -9;
        bArr[236] = (byte) -9;
        bArr[237] = (byte) -9;
        bArr[238] = (byte) -9;
        bArr[239] = (byte) -9;
        bArr[ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK] = (byte) -9;
        bArr[241] = (byte) -9;
        bArr[242] = (byte) -9;
        bArr[243] = (byte) -9;
        bArr[244] = (byte) -9;
        bArr[245] = (byte) -9;
        bArr[246] = (byte) -9;
        bArr[247] = (byte) -9;
        bArr[248] = (byte) -9;
        bArr[249] = (byte) -9;
        bArr[250] = (byte) -9;
        bArr[251] = (byte) -9;
        bArr[252] = (byte) -9;
        bArr[253] = (byte) -9;
        bArr[254] = (byte) -9;
        bArr[255] = (byte) -9;
        e = bArr;
        bArr = new byte[257];
        bArr[0] = (byte) -9;
        bArr[1] = (byte) -9;
        bArr[2] = (byte) -9;
        bArr[3] = (byte) -9;
        bArr[4] = (byte) -9;
        bArr[5] = (byte) -9;
        bArr[6] = (byte) -9;
        bArr[7] = (byte) -9;
        bArr[8] = (byte) -9;
        bArr[9] = (byte) -5;
        bArr[10] = (byte) -5;
        bArr[11] = (byte) -9;
        bArr[12] = (byte) -9;
        bArr[13] = (byte) -5;
        bArr[14] = (byte) -9;
        bArr[15] = (byte) -9;
        bArr[16] = (byte) -9;
        bArr[17] = (byte) -9;
        bArr[18] = (byte) -9;
        bArr[19] = (byte) -9;
        bArr[20] = (byte) -9;
        bArr[21] = (byte) -9;
        bArr[22] = (byte) -9;
        bArr[23] = (byte) -9;
        bArr[24] = (byte) -9;
        bArr[25] = (byte) -9;
        bArr[26] = (byte) -9;
        bArr[27] = (byte) -9;
        bArr[28] = (byte) -9;
        bArr[29] = (byte) -9;
        bArr[30] = (byte) -9;
        bArr[31] = (byte) -9;
        bArr[32] = (byte) -5;
        bArr[33] = (byte) -9;
        bArr[34] = (byte) -9;
        bArr[35] = (byte) -9;
        bArr[36] = (byte) -9;
        bArr[37] = (byte) -9;
        bArr[38] = (byte) -9;
        bArr[39] = (byte) -9;
        bArr[40] = (byte) -9;
        bArr[41] = (byte) -9;
        bArr[42] = (byte) -9;
        bArr[43] = (byte) -9;
        bArr[44] = (byte) -9;
        bArr[46] = (byte) -9;
        bArr[47] = (byte) -9;
        bArr[48] = (byte) 1;
        bArr[49] = (byte) 2;
        bArr[50] = (byte) 3;
        bArr[51] = (byte) 4;
        bArr[52] = (byte) 5;
        bArr[53] = (byte) 6;
        bArr[54] = (byte) 7;
        bArr[55] = (byte) 8;
        bArr[56] = (byte) 9;
        bArr[57] = (byte) 10;
        bArr[58] = (byte) -9;
        bArr[59] = (byte) -9;
        bArr[60] = (byte) -9;
        bArr[61] = (byte) -1;
        bArr[62] = (byte) -9;
        bArr[63] = (byte) -9;
        bArr[64] = (byte) -9;
        bArr[65] = JceStruct.STRUCT_END;
        bArr[66] = JceStruct.ZERO_TAG;
        bArr[67] = JceStruct.SIMPLE_LIST;
        bArr[68] = (byte) 14;
        bArr[69] = (byte) 15;
        bArr[70] = (byte) 16;
        bArr[71] = (byte) 17;
        bArr[72] = (byte) 18;
        bArr[73] = (byte) 19;
        bArr[74] = com.tencent.qalsdk.base.a.z;
        bArr[75] = (byte) 21;
        bArr[76] = (byte) 22;
        bArr[77] = (byte) 23;
        bArr[78] = (byte) 24;
        bArr[79] = (byte) 25;
        bArr[80] = (byte) 26;
        bArr[81] = (byte) 27;
        bArr[82] = (byte) 28;
        bArr[83] = (byte) 29;
        bArr[84] = (byte) 30;
        bArr[85] = (byte) 31;
        bArr[86] = (byte) 32;
        bArr[87] = (byte) 33;
        bArr[88] = (byte) 34;
        bArr[89] = (byte) 35;
        bArr[90] = (byte) 36;
        bArr[91] = (byte) -9;
        bArr[92] = (byte) -9;
        bArr[93] = (byte) -9;
        bArr[94] = (byte) -9;
        bArr[95] = (byte) 37;
        bArr[96] = (byte) -9;
        bArr[97] = (byte) 38;
        bArr[98] = (byte) 39;
        bArr[99] = (byte) 40;
        bArr[100] = (byte) 41;
        bArr[101] = (byte) 42;
        bArr[102] = (byte) 43;
        bArr[103] = (byte) 44;
        bArr[104] = (byte) 45;
        bArr[105] = (byte) 46;
        bArr[106] = (byte) 47;
        bArr[107] = (byte) 48;
        bArr[108] = (byte) 49;
        bArr[109] = (byte) 50;
        bArr[110] = (byte) 51;
        bArr[111] = (byte) 52;
        bArr[112] = (byte) 53;
        bArr[113] = (byte) 54;
        bArr[114] = (byte) 55;
        bArr[com.tencent.qalsdk.base.a.cd] = (byte) 56;
        bArr[116] = (byte) 57;
        bArr[Opcodes.INVOKE_SUPER_RANGE] = (byte) 58;
        bArr[118] = (byte) 59;
        bArr[Opcodes.INVOKE_STATIC_RANGE] = (byte) 60;
        bArr[120] = (byte) 61;
        bArr[121] = (byte) 62;
        bArr[122] = (byte) 63;
        bArr[Opcodes.NEG_INT] = (byte) -9;
        bArr[Opcodes.NOT_INT] = (byte) -9;
        bArr[Opcodes.NEG_LONG] = (byte) -9;
        bArr[Opcodes.NOT_LONG] = (byte) -9;
        bArr[Opcodes.NEG_FLOAT] = (byte) -9;
        bArr[128] = (byte) -9;
        bArr[Opcodes.INT_TO_LONG] = (byte) -9;
        bArr[Opcodes.INT_TO_FLOAT] = (byte) -9;
        bArr[Opcodes.INT_TO_DOUBLE] = (byte) -9;
        bArr[Opcodes.LONG_TO_INT] = (byte) -9;
        bArr[Opcodes.LONG_TO_FLOAT] = (byte) -9;
        bArr[Opcodes.LONG_TO_DOUBLE] = (byte) -9;
        bArr[Opcodes.FLOAT_TO_INT] = (byte) -9;
        bArr[Opcodes.FLOAT_TO_LONG] = (byte) -9;
        bArr[Opcodes.FLOAT_TO_DOUBLE] = (byte) -9;
        bArr[Opcodes.DOUBLE_TO_INT] = (byte) -9;
        bArr[Opcodes.DOUBLE_TO_LONG] = (byte) -9;
        bArr[Opcodes.DOUBLE_TO_FLOAT] = (byte) -9;
        bArr[Opcodes.INT_TO_BYTE] = (byte) -9;
        bArr[Opcodes.INT_TO_CHAR] = (byte) -9;
        bArr[Opcodes.INT_TO_SHORT] = (byte) -9;
        bArr[Opcodes.ADD_INT] = (byte) -9;
        bArr[Opcodes.SUB_INT] = (byte) -9;
        bArr[Opcodes.MUL_INT] = (byte) -9;
        bArr[Opcodes.DIV_INT] = (byte) -9;
        bArr[Opcodes.REM_INT] = (byte) -9;
        bArr[Opcodes.AND_INT] = (byte) -9;
        bArr[Opcodes.OR_INT] = (byte) -9;
        bArr[Opcodes.XOR_INT] = (byte) -9;
        bArr[Opcodes.SHL_INT] = (byte) -9;
        bArr[Opcodes.SHR_INT] = (byte) -9;
        bArr[Opcodes.USHR_INT] = (byte) -9;
        bArr[Opcodes.ADD_LONG] = (byte) -9;
        bArr[Opcodes.SUB_LONG] = (byte) -9;
        bArr[Opcodes.MUL_LONG] = (byte) -9;
        bArr[Opcodes.DIV_LONG] = (byte) -9;
        bArr[Opcodes.REM_LONG] = (byte) -9;
        bArr[160] = (byte) -9;
        bArr[Opcodes.OR_LONG] = (byte) -9;
        bArr[Opcodes.XOR_LONG] = (byte) -9;
        bArr[Opcodes.SHL_LONG] = (byte) -9;
        bArr[Opcodes.SHR_LONG] = (byte) -9;
        bArr[Opcodes.USHR_LONG] = (byte) -9;
        bArr[Opcodes.ADD_FLOAT] = (byte) -9;
        bArr[Opcodes.SUB_FLOAT] = (byte) -9;
        bArr[Opcodes.MUL_FLOAT] = (byte) -9;
        bArr[Opcodes.DIV_FLOAT] = (byte) -9;
        bArr[Opcodes.REM_FLOAT] = (byte) -9;
        bArr[Opcodes.ADD_DOUBLE] = (byte) -9;
        bArr[Opcodes.SUB_DOUBLE] = (byte) -9;
        bArr[Opcodes.MUL_DOUBLE] = (byte) -9;
        bArr[Opcodes.DIV_DOUBLE] = (byte) -9;
        bArr[Opcodes.REM_DOUBLE] = (byte) -9;
        bArr[Opcodes.ADD_INT_2ADDR] = (byte) -9;
        bArr[Opcodes.SUB_INT_2ADDR] = (byte) -9;
        bArr[Opcodes.MUL_INT_2ADDR] = (byte) -9;
        bArr[Opcodes.DIV_INT_2ADDR] = (byte) -9;
        bArr[180] = (byte) -9;
        bArr[Opcodes.AND_INT_2ADDR] = (byte) -9;
        bArr[Opcodes.OR_INT_2ADDR] = (byte) -9;
        bArr[Opcodes.XOR_INT_2ADDR] = (byte) -9;
        bArr[Opcodes.SHL_INT_2ADDR] = (byte) -9;
        bArr[Opcodes.SHR_INT_2ADDR] = (byte) -9;
        bArr[Opcodes.USHR_INT_2ADDR] = (byte) -9;
        bArr[Opcodes.ADD_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.SUB_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.MUL_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.DIV_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.REM_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.AND_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.OR_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.XOR_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.SHL_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.SHR_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.USHR_LONG_2ADDR] = (byte) -9;
        bArr[Opcodes.ADD_FLOAT_2ADDR] = (byte) -9;
        bArr[Opcodes.SUB_FLOAT_2ADDR] = (byte) -9;
        bArr[200] = (byte) -9;
        bArr[201] = (byte) -9;
        bArr[202] = (byte) -9;
        bArr[203] = (byte) -9;
        bArr[204] = (byte) -9;
        bArr[205] = (byte) -9;
        bArr[206] = (byte) -9;
        bArr[207] = (byte) -9;
        bArr[208] = (byte) -9;
        bArr[209] = (byte) -9;
        bArr[210] = (byte) -9;
        bArr[211] = (byte) -9;
        bArr[212] = (byte) -9;
        bArr[213] = (byte) -9;
        bArr[214] = (byte) -9;
        bArr[215] = (byte) -9;
        bArr[216] = (byte) -9;
        bArr[217] = (byte) -9;
        bArr[218] = (byte) -9;
        bArr[219] = (byte) -9;
        bArr[220] = (byte) -9;
        bArr[221] = (byte) -9;
        bArr[Opcodes.OR_INT_LIT8] = (byte) -9;
        bArr[Opcodes.XOR_INT_LIT8] = (byte) -9;
        bArr[Opcodes.SHL_INT_LIT8] = (byte) -9;
        bArr[Opcodes.SHR_INT_LIT8] = (byte) -9;
        bArr[Opcodes.USHR_INT_LIT8] = (byte) -9;
        bArr[227] = (byte) -9;
        bArr[228] = (byte) -9;
        bArr[TLSErrInfo.LOGIN_NO_ACCOUNT] = (byte) -9;
        bArr[230] = (byte) -9;
        bArr[231] = (byte) -9;
        bArr[232] = (byte) -9;
        bArr[233] = (byte) -9;
        bArr[234] = (byte) -9;
        bArr[235] = (byte) -9;
        bArr[236] = (byte) -9;
        bArr[237] = (byte) -9;
        bArr[238] = (byte) -9;
        bArr[239] = (byte) -9;
        bArr[ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK] = (byte) -9;
        bArr[241] = (byte) -9;
        bArr[242] = (byte) -9;
        bArr[243] = (byte) -9;
        bArr[244] = (byte) -9;
        bArr[245] = (byte) -9;
        bArr[246] = (byte) -9;
        bArr[247] = (byte) -9;
        bArr[248] = (byte) -9;
        bArr[249] = (byte) -9;
        bArr[250] = (byte) -9;
        bArr[251] = (byte) -9;
        bArr[252] = (byte) -9;
        bArr[253] = (byte) -9;
        bArr[254] = (byte) -9;
        bArr[255] = (byte) -9;
        bArr[256] = (byte) -9;
        g = bArr;
    }

    private static final byte[] b(int i) {
        if ((i & 16) == 16) {
            return d;
        }
        if ((i & 32) == 32) {
            return f;
        }
        return b;
    }

    private static final byte[] c(int i) {
        if ((i & 16) == 16) {
            return e;
        }
        if ((i & 32) == 32) {
            return g;
        }
        return c;
    }

    private a() {
    }

    private static byte[] b(byte[] bArr, byte[] bArr2, int i, int i2) {
        a(bArr2, 0, i, bArr, 0, i2);
        return bArr;
    }

    private static byte[] a(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        int i5;
        int i6 = 0;
        byte[] b = b(i4);
        int i7 = i2 > 0 ? (bArr[i] << 24) >>> 8 : 0;
        if (i2 > 1) {
            i5 = (bArr[i + 1] << 24) >>> 16;
        } else {
            i5 = 0;
        }
        i5 |= i7;
        if (i2 > 2) {
            i6 = (bArr[i + 2] << 24) >>> 24;
        }
        i6 |= i5;
        switch (i2) {
            case 1:
                bArr2[i3] = b[i6 >>> 18];
                bArr2[i3 + 1] = b[(i6 >>> 12) & 63];
                bArr2[i3 + 2] = (byte) 61;
                bArr2[i3 + 3] = (byte) 61;
                break;
            case 2:
                bArr2[i3] = b[i6 >>> 18];
                bArr2[i3 + 1] = b[(i6 >>> 12) & 63];
                bArr2[i3 + 2] = b[(i6 >>> 6) & 63];
                bArr2[i3 + 3] = (byte) 61;
                break;
            case 3:
                bArr2[i3] = b[i6 >>> 18];
                bArr2[i3 + 1] = b[(i6 >>> 12) & 63];
                bArr2[i3 + 2] = b[(i6 >>> 6) & 63];
                bArr2[i3 + 3] = b[i6 & 63];
                break;
        }
        return bArr2;
    }

    public static String a(byte[] bArr) {
        String str = null;
        try {
            str = a(bArr, 0, bArr.length, 0);
        } catch (IOException e) {
            if (!a) {
                throw new AssertionError(e.getMessage());
            }
        }
        if (a || str != null) {
            return str;
        }
        throw new AssertionError();
    }

    public static String a(byte[] bArr, int i, int i2, int i3) throws IOException {
        byte[] b = b(bArr, i, i2, i3);
        try {
            return new String(b, "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            return new String(b);
        }
    }

    public static byte[] b(byte[] bArr, int i, int i2, int i3) throws IOException {
        IOException e;
        OutputStream outputStream;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream;
        GZIPOutputStream gZIPOutputStream = null;
        if (bArr == null) {
            throw new NullPointerException("Cannot serialize a null array.");
        } else if (i < 0) {
            throw new IllegalArgumentException("Cannot have negative offset: " + i);
        } else if (i2 < 0) {
            throw new IllegalArgumentException("Cannot have length offset: " + i2);
        } else if (i + i2 > bArr.length) {
            throw new IllegalArgumentException(String.format("Cannot have offset of %d and length of %d with array of length %d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(bArr.length)}));
        } else if ((i3 & 2) != 0) {
            a aVar;
            try {
                OutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                try {
                    aVar = new a(byteArrayOutputStream2, i3 | 1);
                    try {
                        GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(aVar);
                        try {
                            gZIPOutputStream2.write(bArr, i, i2);
                            gZIPOutputStream2.close();
                            try {
                                gZIPOutputStream2.close();
                            } catch (Exception e2) {
                            }
                            try {
                                aVar.close();
                            } catch (Exception e3) {
                            }
                            try {
                                byteArrayOutputStream2.close();
                            } catch (Exception e4) {
                            }
                            return byteArrayOutputStream2.toByteArray();
                        } catch (IOException e5) {
                            e = e5;
                            gZIPOutputStream = gZIPOutputStream2;
                            outputStream = byteArrayOutputStream2;
                            try {
                                throw e;
                            } catch (Throwable th2) {
                                th = th2;
                                byteArrayOutputStream = outputStream;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            gZIPOutputStream = gZIPOutputStream2;
                            try {
                                gZIPOutputStream.close();
                            } catch (Exception e6) {
                            }
                            try {
                                aVar.close();
                            } catch (Exception e7) {
                            }
                            try {
                                byteArrayOutputStream.close();
                            } catch (Exception e8) {
                            }
                            throw th;
                        }
                    } catch (IOException e9) {
                        e = e9;
                        outputStream = byteArrayOutputStream2;
                        throw e;
                    } catch (Throwable th4) {
                        th = th4;
                        gZIPOutputStream.close();
                        aVar.close();
                        byteArrayOutputStream.close();
                        throw th;
                    }
                } catch (IOException e10) {
                    e = e10;
                    aVar = null;
                    outputStream = byteArrayOutputStream2;
                    throw e;
                } catch (Throwable th5) {
                    th = th5;
                    aVar = null;
                    gZIPOutputStream.close();
                    aVar.close();
                    byteArrayOutputStream.close();
                    throw th;
                }
            } catch (IOException e11) {
                e = e11;
                aVar = null;
                outputStream = null;
                throw e;
            } catch (Throwable th6) {
                th = th6;
                aVar = null;
                byteArrayOutputStream = null;
                gZIPOutputStream.close();
                aVar.close();
                byteArrayOutputStream.close();
                throw th;
            }
        } else {
            int i4;
            int i5 = (i3 & 8) != 0 ? 1 : 0;
            int i6 = (i2 / 3) * 4;
            if (i2 % 3 > 0) {
                i4 = 4;
            } else {
                i4 = 0;
            }
            i4 += i6;
            if (i5 != 0) {
                i4 += i4 / 76;
            }
            byte[] bArr2 = new byte[i4];
            int i7 = i2 - 2;
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            while (i10 < i7) {
                a(bArr, i10 + i, 3, bArr2, i9, i3);
                i4 = i8 + 4;
                if (i5 != 0 && i4 >= 76) {
                    bArr2[i9 + 4] = (byte) 10;
                    i9++;
                    i4 = 0;
                }
                i9 += 4;
                i8 = i4;
                i10 += 3;
            }
            if (i10 < i2) {
                a(bArr, i10 + i, i2 - i10, bArr2, i9, i3);
                i9 += 4;
            }
            if (i9 > bArr2.length - 1) {
                return bArr2;
            }
            Object obj = new byte[i9];
            System.arraycopy(bArr2, 0, obj, 0, i9);
            return obj;
        }
    }

    private static int b(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        if (bArr == null) {
            throw new NullPointerException("Source array was null.");
        } else if (bArr2 == null) {
            throw new NullPointerException("Destination array was null.");
        } else if (i < 0 || i + 3 >= bArr.length) {
            throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and still process four bytes.", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i)}));
        } else if (i2 < 0 || i2 + 2 >= bArr2.length) {
            throw new IllegalArgumentException(String.format("Destination array with length %d cannot have offset of %d and still store three bytes.", new Object[]{Integer.valueOf(bArr2.length), Integer.valueOf(i2)}));
        } else {
            byte[] c = c(i3);
            if (bArr[i + 2] == (byte) 61) {
                bArr2[i2] = (byte) ((((c[bArr[i]] & 255) << 18) | ((c[bArr[i + 1]] & 255) << 12)) >>> 16);
                return 1;
            } else if (bArr[i + 3] == (byte) 61) {
                r0 = (((c[bArr[i]] & 255) << 18) | ((c[bArr[i + 1]] & 255) << 12)) | ((c[bArr[i + 2]] & 255) << 6);
                bArr2[i2] = (byte) (r0 >>> 16);
                bArr2[i2 + 1] = (byte) (r0 >>> 8);
                return 2;
            } else {
                r0 = ((((c[bArr[i]] & 255) << 18) | ((c[bArr[i + 1]] & 255) << 12)) | ((c[bArr[i + 2]] & 255) << 6)) | (c[bArr[i + 3]] & 255);
                bArr2[i2] = (byte) (r0 >> 16);
                bArr2[i2 + 1] = (byte) (r0 >> 8);
                bArr2[i2 + 2] = (byte) r0;
                return 3;
            }
        }
    }
}
