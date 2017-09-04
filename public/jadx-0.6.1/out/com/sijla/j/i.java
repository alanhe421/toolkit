package com.sijla.j;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.sijla.j.a.a;
import com.sijla.j.a.c;
import com.sijla.j.a.d;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class i {
    private static final String a = i.class.getSimpleName();

    public static String a(Context context) {
        return d(context);
    }

    public static String b(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("arch", 0);
        Editor edit = sharedPreferences.edit();
        String string = sharedPreferences.getString("hbuid", "");
        try {
            if (!b.a(string)) {
                return string;
            }
            string = f(context);
            if (b.a(string)) {
                string = c(context);
                if (a.a()) {
                    List arrayList = new ArrayList();
                    arrayList.add(string);
                    arrayList.add(context.getPackageName());
                    arrayList.add(b.g() + "");
                    b.a(arrayList);
                }
            }
            if (b.a(string)) {
                return string;
            }
            edit.putString("hbuid", string).commit();
            return string;
        } catch (Exception e) {
            return c(context);
        }
    }

    private static String f(Context context) {
        String str = "";
        if (!a.a()) {
            return str;
        }
        String str2 = str;
        for (String str3 : b.f()) {
            try {
                str2 = b.a(d.b(new FileInputStream(new File(str3)))).split("\t")[0];
                f.a("get uid:" + str2 + " from:" + str3);
                if (!TextUtils.isEmpty(str2)) {
                    return str2;
                }
                str3 = str2;
                str2 = str3;
            } catch (Exception e) {
                str3 = str2;
            }
        }
        return str2;
    }

    public static String c(Context context) {
        try {
            MessageDigest instance;
            String str = ((TelephonyManager) context.getSystemService("phone")).getDeviceId() + ("35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10) + (Build.DEVICE.length() % 10) + (Build.DISPLAY.length() % 10) + (Build.HOST.length() % 10) + (Build.ID.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10) + (Build.TAGS.length() % 10) + (Build.TYPE.length() % 10) + (Build.USER.length() % 10)) + Secure.getString(context.getContentResolver(), "android_id") + ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            try {
                instance = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                instance = null;
            }
            instance.update(str.getBytes(), 0, str.length());
            byte[] digest = instance.digest();
            String str2 = "";
            for (byte b : digest) {
                int i = b & 255;
                if (i <= 15) {
                    str2 = str2 + "0";
                }
                str2 = str2 + Integer.toHexString(i);
            }
            return str2.toUpperCase();
        } catch (Exception e2) {
            e2.printStackTrace();
            return c.a(System.currentTimeMillis() + "").toUpperCase();
        }
    }

    public static String d(Context context) {
        try {
            String i = a.i(context);
            String str = a.h() + a.e() + Build.BRAND + Build.CPU_ABI + Build.DEVICE + Build.MANUFACTURER + Build.MODEL + Build.PRODUCT;
            String string = Secure.getString(context.getContentResolver(), "android_id");
            String macAddress = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo().getMacAddress();
            return c.a(i + str + string + macAddress + a.g()).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            return c.a("").toUpperCase();
        }
    }

    public static String e(Context context) {
        try {
            String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            String str = a.h() + a.e() + Build.BRAND + Build.CPU_ABI + Build.DEVICE + Build.MANUFACTURER + Build.MODEL + Build.PRODUCT;
            String string = Secure.getString(context.getContentResolver(), "android_id");
            return c.a(deviceId + str + string + a.g()).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            return c.a("").toUpperCase();
        }
    }
}
