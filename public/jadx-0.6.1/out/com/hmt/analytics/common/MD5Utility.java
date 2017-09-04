package com.hmt.analytics.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utility {
    public static String md5Appkey(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                int i = b & 255;
                if (i < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            CommonUtil.printLog("MD5Utility", "getMD5 error");
            e.printStackTrace();
            return "";
        }
    }
}
