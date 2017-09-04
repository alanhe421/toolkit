package com.tencent;

import android.util.Base64;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Map;
import java.util.Random;
import java.util.zip.GZIPOutputStream;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class IMFunc {
    private static final int MAX_SIZE = 10240;
    private static final char[] digits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final StringBuilder strBuilder = new StringBuilder(MAX_SIZE);

    public interface RequestListener {
        void onFail(String str);

        void onSuccess(byte[] bArr);
    }

    public static String appSignature(int i, String str, String str2, long j, String str3, String str4) throws Exception {
        if (str == null || str2 == null || str3 == null || str4 == null) {
            return WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
        }
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        String str5 = "a=" + i + "&k=" + str + "&e=" + (currentTimeMillis + j) + "&t=" + currentTimeMillis + "&r=" + Math.abs(new Random().nextInt()) + "&f=" + str3 + "&b=" + str4;
        Object hmacSHA1 = getHmacSHA1(str5.getBytes(), str2);
        Object obj = new byte[(hmacSHA1.length + str5.getBytes().length)];
        System.arraycopy(hmacSHA1, 0, obj, 0, hmacSHA1.length);
        System.arraycopy(str5.getBytes(), 0, obj, hmacSHA1.length, str5.getBytes().length);
        return base64Encode(obj);
    }

    public static String base64Encode(byte[] bArr) {
        return Base64.encodeToString(bArr, 2);
    }

    public static synchronized String byte2hex(byte[] bArr) {
        String str;
        synchronized (IMFunc.class) {
            strBuilder.delete(0, MAX_SIZE);
            if (bArr == null || bArr.length == 0) {
                str = null;
            } else {
                for (byte b : bArr) {
                    strBuilder.append(digits[((byte) (b >>> 4)) & 15]).append(digits[b & 15]);
                }
                str = strBuilder.toString();
            }
        }
        return str;
    }

    public static String calcSHA(byte[] bArr) throws Exception {
        MessageDigest instance = MessageDigest.getInstance("SHA-1");
        instance.update(bArr);
        return byte2hex(instance.digest()).replace(" ", "");
    }

    public static String getExceptionInfo(Throwable th) {
        if (th == null) {
            return "";
        }
        String th2 = th.toString();
        StackTraceElement[] stackTrace = th.getStackTrace();
        if (stackTrace == null) {
            return th2;
        }
        int length = stackTrace.length;
        int i = 0;
        while (i < length) {
            i++;
            th2 = th2 + "\n\tat " + stackTrace[i].toString();
        }
        return th2;
    }

    public static byte[] getHmacSHA1(byte[] bArr, String str) throws Exception {
        Mac instance = Mac.getInstance("HmacSHA1");
        instance.init(new SecretKeySpec(str.getBytes(), "HmacSHA1"));
        byte[] bArr2 = null;
        try {
            bArr2 = instance.doFinal(bArr);
        } catch (IllegalStateException e) {
        }
        return bArr2;
    }

    public static byte[] getParamBytes(String str, String str2, String str3, String str4) {
        return ("--" + str + "\r\nContent-Disposition: form-data; name=\"" + str2 + "\"; filename=\"" + str3 + "\"\r\n\r\n" + str4 + "\r\n").getBytes();
    }

    public static byte[] getParamBytes(String str, String str2, String str3, byte[] bArr) {
        Object bytes = ("--" + str + "\r\nContent-Disposition: form-data; name=\"" + str2 + "\"; filename=\"" + str3 + "\"\r\n\r\n").getBytes();
        Object obj = new byte[((bytes.length + bArr.length) + 2)];
        System.arraycopy(bytes, 0, obj, 0, bytes.length);
        System.arraycopy(bArr, 0, obj, bytes.length, bArr.length);
        System.arraycopy("\r\n".getBytes(), 0, obj, bytes.length + bArr.length, 2);
        return obj;
    }

    public static byte[] gzCompress(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return null;
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gZIPOutputStream.write(str.getBytes());
        gZIPOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    public static boolean httpPostReq(String str, byte[] bArr, Map<String, String> map, RequestListener requestListener) {
        try {
            new Thread(new bg((HttpURLConnection) new URL(str).openConnection(), bArr, map, requestListener)).start();
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            if (requestListener != null) {
                requestListener.onFail(getExceptionInfo(th));
            }
            return false;
        }
    }
}
