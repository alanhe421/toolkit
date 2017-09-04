package com.qq.reader.common.utils.a;

import com.tencent.connect.common.Constants;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Arrays;

/* compiled from: Rc4Util */
public class e {
    static byte a = Byte.parseByte("-16");
    static byte b = Byte.parseByte(Constants.VIA_REPORT_TYPE_WPA_STATE);
    private static final String c = new String("qidian-tingshu");

    public static byte[] a(byte[] bArr, int i, int i2, String str) {
        if (bArr == null || bArr.length == 0 || i2 <= 0 || i + i2 > bArr.length || str == null || str.isEmpty()) {
            return null;
        }
        return a(a(str), Arrays.copyOfRange(bArr, i, i + i2));
    }

    public static String a(String str, String str2) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update((str + str2 + c).getBytes(Charset.forName("UTF-8")));
            return a(instance.digest());
        } catch (Exception e) {
            return null;
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder(50);
        for (byte b : bArr) {
            stringBuilder.append(Integer.toHexString(((a & b) & 255) >>> 4)).append(Integer.toHexString(b & b));
        }
        return stringBuilder.toString();
    }

    private static byte[] a(short[] sArr, byte[] bArr) {
        int i = 0;
        int length = bArr.length;
        byte[] bArr2 = new byte[bArr.length];
        int i2 = 0;
        int i3 = 0;
        while (i < length) {
            i3 = (i3 + 1) % 256;
            i2 = (i2 + sArr[i3]) % 256;
            short s = sArr[i3];
            sArr[i3] = sArr[i2];
            sArr[i2] = s;
            int i4 = (sArr[i3] + sArr[i2]) % 256;
            bArr2[i] = (byte) (sArr[i4] ^ bArr[i]);
            i++;
        }
        return bArr2;
    }

    private static short[] a(String str) {
        int i = 0;
        short[] sArr = new short[256];
        short[] sArr2 = new short[256];
        for (short s = (short) 0; s < (short) 256; s = (short) (s + 1)) {
            sArr[s] = s;
            sArr2[s] = (short) str.charAt(s % str.length());
        }
        int i2 = 0;
        while (i < 256) {
            i2 = (short) (((i2 + sArr[i]) + sArr2[i]) % 256);
            short s2 = sArr[i];
            sArr[i] = sArr[i2];
            sArr[i2] = s2;
            i = (short) (i + 1);
        }
        return sArr;
    }
}
