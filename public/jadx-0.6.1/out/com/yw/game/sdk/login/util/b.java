package com.yw.game.sdk.login.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: Md5 */
public class b {
    public static String a(String... strArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String append : strArr) {
            stringBuilder.append(append);
        }
        return a(stringBuilder.toString());
    }

    public static String a(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuilder stringBuilder = new StringBuilder("");
            for (int i : digest) {
                int i2;
                if (i2 < 0) {
                    i2 += 256;
                }
                if (i2 < 16) {
                    stringBuilder.append("0");
                }
                stringBuilder.append(Integer.toHexString(i2));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
