package com.sijla.j.a;

import com.etrump.jni.ETConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class c {
    private static final char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String a(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.trim().getBytes());
            str = a(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return str;
    }

    private static String a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            stringBuilder.append(a[(b & ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK) >>> 4]);
            stringBuilder.append(a[b & 15]);
        }
        return stringBuilder.toString();
    }

    public static String b(String str) {
        String str2 = "";
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer("");
            for (int i : digest) {
                int i2;
                if (i2 < 0) {
                    i2 += 256;
                }
                if (i2 < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i2));
            }
            str2 = stringBuffer.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
        }
        return str2;
    }
}
