package com.yuewen.ywlogin;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class e {
    public static String a(String str) {
        try {
            Key generateSecret = SecretKeyFactory.getInstance("desede").generateSecret(new DESedeKeySpec("bMyzJ1D7Kl7zt9mwjegtJGMoF53msSfP".getBytes()));
            Cipher instance = Cipher.getInstance("desede/CBC/PKCS5Padding");
            instance.init(1, generateSecret, new IvParameterSpec("W9F1bXrz".getBytes()));
            return c.a(instance.doFinal(str.getBytes("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
