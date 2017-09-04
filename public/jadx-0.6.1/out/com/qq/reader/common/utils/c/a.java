package com.qq.reader.common.utils.c;

import com.tencent.tinker.android.dx.instruction.Opcodes;

/* compiled from: CharsetDecoder */
public class a {
    private static String a = "Charset_decoder";
    private static a b = null;
    private static c c = null;

    public a() {
        if (c == null) {
            c = new c();
        }
        if (b == null) {
            b = this;
        }
    }

    public int a(byte[] bArr) throws Exception {
        if ((bArr[0] & 255) == 255 && (bArr[1] & 255) == 254) {
            return 1;
        }
        if ((bArr[0] & 255) == 254 && (bArr[1] & 255) == 255) {
            return 8;
        }
        if ((bArr[0] & 255) == 239 && (bArr[1] & 255) == Opcodes.ADD_LONG_2ADDR && (bArr[2] & 255) == Opcodes.REM_LONG_2ADDR) {
            return 10;
        }
        switch (c.a(bArr)) {
            case 0:
            case 8:
                return 12;
            case 1:
                return 14;
            case 6:
                return 4;
            case 7:
                return 1;
            default:
                return 0;
        }
    }

    public static void a() {
        if (c == null) {
            c = new c();
        }
    }
}
