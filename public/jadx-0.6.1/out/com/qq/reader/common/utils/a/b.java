package com.qq.reader.common.utils.a;

import android.text.TextUtils;
import com.etrump.jni.ETConverter;
import com.tencent.tinker.android.dx.instruction.Opcodes;

/* compiled from: EasyEncrypt */
public class b {
    private static String a;

    public static String a() {
        if (TextUtils.isEmpty(a)) {
            a = b();
        }
        return a;
    }

    private static String b() {
        return a(new char[]{(char) Opcodes.REM_LONG_2ADDR, (char) Opcodes.SHL_INT_2ADDR, (char) 181, (char) 214, (char) Opcodes.XOR_INT_2ADDR, (char) Opcodes.SHL_LONG_2ADDR, (char) 201, (char) Opcodes.SUB_LONG_2ADDR, (char) 181, (char) 214, (char) 210, (char) 238, (char) 218, (char) Opcodes.ADD_FLOAT, (char) Opcodes.REM_DOUBLE, (char) Opcodes.AND_LONG_2ADDR}, Opcodes.OR_INT);
    }

    private static String a(char[] cArr, int i) {
        int i2 = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (i2 < cArr.length) {
            i2++;
            stringBuilder.append((char) (cArr[i2] ^ i));
        }
        return stringBuilder.toString();
    }

    public static byte[] a(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        int i = 0;
        int i2 = 0;
        while (i < length) {
            if (i2 >= "Q9*11q^REaDer%Bs1&#@[".length()) {
                i2 = 0;
            }
            int i3 = i2 + 1;
            byte charAt = (byte) (((byte) "Q9*11q^REaDer%Bs1&#@[".charAt(i2)) ^ bArr[i]);
            bArr2[i] = (byte) (((charAt & ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK) >> 4) | ((charAt & 15) << 4));
            i++;
            i2 = i3;
        }
        return bArr2;
    }

    public static byte[] b(byte[] bArr) {
        int length = bArr.length;
        int length2 = "Q9*11q^REaDer%Bs1&#@[".length();
        byte[] bArr2 = new byte[length];
        int i = 0;
        int i2 = 0;
        while (i < length) {
            if (i2 >= length2) {
                i2 = 0;
            }
            int i3 = i2 + 1;
            byte charAt = (byte) "Q9*11q^REaDer%Bs1&#@[".charAt(i2);
            byte b = bArr[i];
            bArr2[i] = (byte) (charAt ^ ((byte) (((b & ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK) >> 4) | ((b & 15) << 4))));
            i++;
            i2 = i3;
        }
        return bArr2;
    }
}
