package com.tencent.smtt.utils;

import java.security.MessageDigest;

public class l {
    public static String a(String str) {
        String str2 = null;
        if (str != null) {
            try {
                byte[] bytes = str.getBytes();
                MessageDigest instance = MessageDigest.getInstance("MD5");
                instance.update(bytes);
                str2 = b.a(instance.digest());
            } catch (Exception e) {
            }
        }
        return str2;
    }
}
