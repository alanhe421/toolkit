package com.qq.reader.common.utils.a;

import java.security.MessageDigest;

/* compiled from: MD5Coding */
public class d {
    public static byte[] a(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr);
            return instance.digest();
        } catch (Exception e) {
            return null;
        }
    }

    public static String b(byte[] bArr) {
        return c.a(a(bArr));
    }
}
