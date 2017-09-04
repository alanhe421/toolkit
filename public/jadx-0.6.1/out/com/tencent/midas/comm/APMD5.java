package com.tencent.midas.comm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class APMD5 {
    public static String parseByte2HexStr(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() == 1) {
                toHexString = '0' + toHexString;
            }
            stringBuffer.append(toHexString.toUpperCase());
        }
        return stringBuffer.toString();
    }

    public static String toMd5(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(bArr);
            return parseByte2HexStr(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
