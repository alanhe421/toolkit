package com.tencent.mid.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class a {
    public static final List<Integer> a = new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(2), Integer.valueOf(4), Integer.valueOf(8), Integer.valueOf(16), Integer.valueOf(32), Integer.valueOf(64), Integer.valueOf(128), Integer.valueOf(256), Integer.valueOf(512), Integer.valueOf(1024), Integer.valueOf(2048)}));
    private SecretKey b = null;
    private IvParameterSpec c = null;

    private int a(int i, int i2) {
        if (i < i2) {
            i = i2;
        }
        if (a.contains(Integer.valueOf(i))) {
            return i;
        }
        for (Integer num : a) {
            if (num.intValue() > i) {
                return num.intValue();
            }
        }
        return i;
    }

    public static SecretKey a() {
        try {
            KeyGenerator instance = KeyGenerator.getInstance("AES");
            instance.init(128);
            return new SecretKeySpec(instance.generateKey().getEncoded(), "AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private byte[] a(String str, int i) {
        Object bytes = str.getBytes();
        int length = bytes.length;
        if (length >= i) {
            return bytes;
        }
        Object obj = new byte[i];
        Arrays.fill(obj, (byte) 0);
        System.arraycopy(bytes, 0, obj, 0, length);
        return obj;
    }

    public static IvParameterSpec d() {
        byte[] bArr = new byte[16];
        new SecureRandom().nextBytes(bArr);
        return new IvParameterSpec(bArr);
    }

    public void a(String str, String str2) {
        int a = a(str.length(), str2.length());
        byte[] a2 = a(str, a);
        byte[] a3 = a(str2, a);
        this.b = new SecretKeySpec(a2, "AES");
        this.c = new IvParameterSpec(a3);
    }

    public byte[] a(byte[] bArr) {
        if (this.b == null) {
            throw new Exception("密钥为空, 请设置");
        }
        try {
            Cipher instance = Cipher.getInstance("AES/CFB/NoPadding");
            instance.init(1, this.b, this.c);
            return instance.doFinal(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] b() {
        return this.b != null ? this.b.getEncoded() : null;
    }

    public byte[] b(byte[] bArr) {
        if (this.b == null) {
            throw new Exception("密钥为空, 请设置");
        }
        try {
            Cipher instance = Cipher.getInstance("AES/CFB/NoPadding");
            instance.init(2, this.b, this.c);
            return instance.doFinal(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] c() {
        return this.c != null ? this.c.getIV() : null;
    }

    public void e() {
        this.b = a();
        this.c = d();
    }
}
