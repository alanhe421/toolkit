package com.sijla.f;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class b {
    private static byte[] a = new byte[]{(byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8};

    public static String a(String str, String str2) {
        AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(a);
        Key secretKeySpec = new SecretKeySpec(str.getBytes(), "DES");
        Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
        instance.init(1, secretKeySpec, ivParameterSpec);
        return a.a(instance.doFinal(str2.getBytes()));
    }

    public static String b(String str, String str2) {
        AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(a);
        Key secretKeySpec = new SecretKeySpec(str.getBytes(), "DES");
        try {
            Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
            instance.init(2, secretKeySpec, ivParameterSpec);
            return new String(instance.doFinal(a.a(str2)));
        } catch (Exception e) {
            return "";
        }
    }
}
