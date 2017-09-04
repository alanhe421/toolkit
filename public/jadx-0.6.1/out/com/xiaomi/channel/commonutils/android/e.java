package com.xiaomi.channel.commonutils.android;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.channel.commonutils.e.a;
import com.xiaomi.channel.commonutils.g.d;

public class e {
    private static String a = null;
    private static String b = "";
    private static String c = null;
    private static String d = null;

    public static String a() {
        return VERSION.SDK_INT > 8 ? Build.SERIAL : null;
    }

    public static String a(Context context) {
        if (c == null) {
            String c = c(context);
            String b = b(context);
            c = "a-" + d.b(c + b + a());
        }
        return c;
    }

    @TargetApi(17)
    public static int b() {
        if (VERSION.SDK_INT < 17) {
            return -1;
        }
        Object a = a.a("android.os.UserHandle", "myUserId", new Object[0]);
        return a != null ? ((Integer) Integer.class.cast(a)).intValue() : -1;
    }

    public static String b(Context context) {
        String str = null;
        try {
            str = Secure.getString(context.getContentResolver(), "android_id");
        } catch (Throwable th) {
            c.a(th);
        }
        return str;
    }

    public static String c(Context context) {
        int i = 10;
        String d = d(context);
        while (d == null) {
            int i2 = i - 1;
            if (i <= 0) {
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            d = d(context);
            i = i2;
        }
        return d;
    }

    public static String d(Context context) {
        if (a != null) {
            return a;
        }
        try {
            String str;
            if (g.a()) {
                str = "miui.telephony.TelephonyManager";
                Object a = a.a("miui.telephony.TelephonyManager", "getDefault", new Object[0]);
                if (a != null) {
                    a = a.a(a, "getMiuiDeviceId", new Object[0]);
                    if (a != null && (a instanceof String)) {
                        str = (String) String.class.cast(a);
                        if (str == null && k(context)) {
                            str = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                        }
                        if (str != null) {
                            return str;
                        }
                        a = str;
                        return str;
                    }
                }
            }
            str = null;
            str = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            if (str != null) {
                return str;
            }
            a = str;
            return str;
        } catch (Throwable th) {
            c.a(th);
            return null;
        }
    }

    public static String e(Context context) {
        int i = 10;
        String g = g(context);
        while (g == null) {
            int i2 = i - 1;
            if (i <= 0) {
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            g = g(context);
            i = i2;
        }
        return g;
    }

    public static String f(Context context) {
        if (VERSION.SDK_INT < 22) {
            return "";
        }
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        if (!k(context)) {
            return "";
        }
        a = d(context);
        if (TextUtils.isEmpty(a)) {
            return "";
        }
        try {
            Object obj = (TelephonyManager) context.getSystemService("phone");
            Integer num = (Integer) a.a(obj, "getPhoneCount", new Object[0]);
            if (num == null || num.intValue() <= 1) {
                return "";
            }
            for (int i = 0; i < num.intValue(); i++) {
                String str = (String) a.a(obj, "getDeviceId", Integer.valueOf(i));
                if (!(TextUtils.isEmpty(str) || TextUtils.equals(a, str))) {
                    b += str + ",";
                }
            }
            b = b.substring(0, b.length() - 1);
            return b;
        } catch (Exception e) {
            c.d(e.toString());
            return "";
        }
    }

    public static String g(Context context) {
        f(context);
        if (TextUtils.isEmpty(b)) {
            return "";
        }
        String str = "";
        for (String a : b.split(",")) {
            str = str + d.a(a) + ",";
        }
        return str.substring(0, str.length() - 1);
    }

    public static synchronized String h(Context context) {
        String str;
        synchronized (e.class) {
            if (d != null) {
                str = d;
            } else {
                str = b(context);
                d = d.b(str + a());
                str = d;
            }
        }
        return str;
    }

    public static String i(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getSimOperatorName();
    }

    public static String j(Context context) {
        try {
            return ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        } catch (Throwable e) {
            c.a(e);
            return null;
        }
    }

    private static boolean k(Context context) {
        String str = "android.permission.READ_PHONE_STATE";
        return context.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", context.getPackageName()) == 0;
    }
}
