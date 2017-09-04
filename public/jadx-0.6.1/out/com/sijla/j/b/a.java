package com.sijla.j.b;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class a {
    static final IvParameterSpec a = new IvParameterSpec(new byte[]{(byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8});

    public static byte[] a(byte[] bArr, String str) {
        return a(bArr, str.getBytes());
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        Key secretKeySpec = new SecretKeySpec(bArr2, "DES");
        Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
        instance.init(1, secretKeySpec, a);
        return instance.doFinal(bArr);
    }

    public static byte[] a(String str, byte[] bArr) {
        return b(com.sijla.j.b.a.a.a.a(str), bArr);
    }

    public static byte[] b(byte[] bArr, byte[] bArr2) {
        Key secretKeySpec = new SecretKeySpec(bArr2, "DES");
        Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
        instance.init(2, secretKeySpec, a);
        return instance.doFinal(bArr);
    }
}
