package com.tencent.upload.utils;

import android.text.TextUtils;

public class SignUtils {
    private static final int hmac_base64_length = 28;
    private static final int hmac_length = 20;

    public static int checkSign(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || str2.length() <= 28) {
            return -1;
        }
        String str3;
        try {
            byte[] decode = Base64.decode(str2, 0);
            str3 = decode.length > 20 ? new String(decode, 20, decode.length - 20) : null;
        } catch (Exception e) {
            str3 = null;
        }
        if (TextUtils.isEmpty(str3)) {
            return -1;
        }
        long longValue;
        try {
            if (!str.equals(parserUrlParam(str3, "a"))) {
                return -2;
            }
        } catch (Exception e2) {
        }
        try {
            longValue = Long.valueOf(parserUrlParam(str3, "e")).longValue();
        } catch (Exception e3) {
            longValue = 0;
        }
        return longValue * 1000 < System.currentTimeMillis() ? 1 : 0;
    }

    private static String parserUrlParam(String str, String str2) {
        String str3 = str2 + "=";
        int indexOf = str.indexOf(str3);
        if (indexOf < 0) {
            return "";
        }
        int length = str3.length() + indexOf;
        int indexOf2 = str.indexOf("&", length);
        if (indexOf2 > length) {
            return str.substring(length, indexOf2);
        }
        indexOf2 = str.indexOf("#", length);
        return indexOf2 > length ? str.substring(length, indexOf2) : str.substring(str3.length() + indexOf);
    }
}
