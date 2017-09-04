package com.qq.reader.common.utils.a;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: SecurityUtil */
public class f {
    private static Key a(String str) throws Exception {
        return new SecretKeySpec(str.getBytes("UTF-8"), "DES");
    }

    public static byte[] a(byte[] bArr, Key key, String str) throws Exception {
        Cipher instance = Cipher.getInstance(str);
        instance.init(1, key);
        return instance.doFinal(bArr);
    }

    public static byte[] a(byte[] bArr) throws Exception {
        Key a = a("2q4qrupe");
        com.qq.reader.common.monitor.f.a("key", b(a.getEncoded()));
        return a(bArr, a, "DES/ECB/PKCS5Padding");
    }

    private static String b(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder("{");
        for (byte append : bArr) {
            stringBuilder.append(append).append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
