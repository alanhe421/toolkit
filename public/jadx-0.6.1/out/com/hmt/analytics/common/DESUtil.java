package com.hmt.analytics.common;

import java.security.Key;
import java.util.Locale;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DESUtil {
    public static String encode(String str, String str2) {
        if (str2 == null) {
            return null;
        }
        try {
            Key generateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(str.getBytes()));
            Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
            instance.init(1, generateSecret, new IvParameterSpec(HMTConstants.m.getBytes()));
            return byte2String(instance.doFinal(str2.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            return str2;
        }
    }

    public static String decode(String str, String str2) {
        if (str2 == null) {
            return null;
        }
        try {
            Key generateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(str.getBytes()));
            Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
            instance.init(2, generateSecret, new IvParameterSpec(HMTConstants.m.getBytes()));
            return new String(instance.doFinal(byte2hex(str2.getBytes())));
        } catch (Exception e) {
            e.printStackTrace();
            return str2;
        }
    }

    private static String byte2String(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        while (bArr != null && i < bArr.length) {
            String toHexString = Integer.toHexString(bArr[i] & 255);
            if (toHexString.length() == 1) {
                stringBuilder.append('0');
            }
            stringBuilder.append(toHexString);
            i++;
        }
        return stringBuilder.toString().toUpperCase(Locale.CHINA);
    }

    private static byte[] byte2hex(byte[] bArr) {
        if (bArr.length % 2 != 0) {
            throw new IllegalArgumentException();
        }
        byte[] bArr2 = new byte[(bArr.length / 2)];
        for (int i = 0; i < bArr.length; i += 2) {
            bArr2[i / 2] = (byte) Integer.parseInt(new String(bArr, i, 2), 16);
        }
        return bArr2;
    }
}
