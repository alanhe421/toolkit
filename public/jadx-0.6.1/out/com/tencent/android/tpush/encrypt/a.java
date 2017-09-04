package com.tencent.android.tpush.encrypt;

import com.tencent.android.tpush.common.Constants;
import java.security.MessageDigest;

/* compiled from: ProGuard */
public class a {
    public static String a(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            return a(instance.digest());
        } catch (Throwable e) {
            com.tencent.android.tpush.a.a.c(Constants.LogTag, "md5 encrypt:" + str, e);
            return "";
        } catch (Throwable e2) {
            com.tencent.android.tpush.a.a.c(Constants.LogTag, "md5 encrypt:" + str, e2);
            return "";
        }
    }

    public static String a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            stringBuilder.append(Integer.toHexString(b & 255));
        }
        return stringBuilder.toString();
    }
}
