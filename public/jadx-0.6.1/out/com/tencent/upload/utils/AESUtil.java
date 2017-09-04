package com.tencent.upload.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import com.tencent.upload.a.b;
import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
    private static final String HEX = "0123456789ABCDEF";
    private static final int JELLY_BEAN_4_2 = 17;

    private static void appendHex(StringBuffer stringBuffer, byte b) {
        stringBuffer.append(HEX.charAt((b >> 4) & 15)).append(HEX.charAt(b & 15));
    }

    public static String decrypt(String str, String str2) {
        return new String(decrypt(getRawKey(str.getBytes()), toByte(str2)));
    }

    private static byte[] decrypt(byte[] bArr, byte[] bArr2) {
        Key secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher instance = Cipher.getInstance("AES");
        instance.init(2, secretKeySpec);
        return instance.doFinal(bArr2);
    }

    public static String encrypt(String str, String str2) {
        return toHex(encrypt(getRawKey(str.getBytes()), str2.getBytes()));
    }

    private static byte[] encrypt(byte[] bArr, byte[] bArr2) {
        Key secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher instance = Cipher.getInstance("AES");
        instance.init(1, secretKeySpec);
        return instance.doFinal(bArr2);
    }

    public static String fromHex(String str) {
        return new String(toByte(str));
    }

    private static String getDeviceMixInfo() {
        String str = Build.MANUFACTURER;
        return Build.DEVICE.toLowerCase() + str.hashCode();
    }

    public static byte[] getDeviceSecurity() {
        byte[] bArr = new byte[]{(byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5};
        String str = getMacAddress() + getDeviceMixInfo();
        return str != null ? str.getBytes() : bArr;
    }

    private static String getMacAddress() {
        Context a = b.a();
        if (a != null) {
            WifiManager wifiManager = (WifiManager) a.getSystemService("wifi");
            if (wifiManager != null) {
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo != null) {
                    return connectionInfo.getMacAddress();
                }
            }
        }
        return "";
    }

    private static byte[] getRawKey(byte[] bArr) {
        KeyGenerator instance = KeyGenerator.getInstance("AES");
        SecureRandom instance2 = VERSION.SDK_INT >= 17 ? SecureRandom.getInstance("SHA1PRNG", "Crypto") : SecureRandom.getInstance("SHA1PRNG");
        instance2.setSeed(bArr);
        instance.init(256, instance2);
        return instance.generateKey().getEncoded();
    }

    public static byte[] toByte(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = Integer.valueOf(str.substring(i * 2, (i * 2) + 2), 16).byteValue();
        }
        return bArr;
    }

    public static String toHex(String str) {
        return toHex(str.getBytes());
    }

    public static String toHex(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte appendHex : bArr) {
            appendHex(stringBuffer, appendHex);
        }
        return stringBuffer.toString();
    }
}
