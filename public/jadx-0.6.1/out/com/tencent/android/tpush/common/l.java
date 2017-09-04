package com.tencent.android.tpush.common;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.service.m;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ProGuard */
public class l {
    private static final String[] a = new String[]{"android.permission.INTERNET", "android.permission.ACCESS_WIFI_STATE", "android.permission.ACCESS_NETWORK_STATE"};
    private static Map b = new HashMap(8);

    private static Context b() {
        return XGPushManager.getContext() != null ? XGPushManager.getContext() : m.e();
    }

    public static boolean a(String str) {
        Throwable th;
        boolean z;
        try {
            if (b.containsKey(str)) {
                return ((Boolean) b.get(str)).booleanValue();
            }
            Context b = b();
            if (b.getPackageManager().checkPermission(str, b.getPackageName()) == 0) {
                z = true;
            } else {
                z = false;
            }
            try {
                b.put(str, Boolean.valueOf(z));
                return z;
            } catch (Throwable th2) {
                th = th2;
                Log.e("XgStat", "checkPermission error", th);
                return z;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            z = false;
            th = th4;
            Log.e("XgStat", "checkPermission error", th);
            return z;
        }
    }

    public static boolean a() {
        Context b = b();
        if (b == null) {
            throw new IllegalArgumentException("The context parameter can not be null!");
        }
        try {
            PackageManager packageManager = b.getPackageManager();
            if (packageManager != null) {
                String[] strArr = packageManager.getPackageInfo(b.getPackageName(), 4096).requestedPermissions;
                if (strArr == null) {
                    return false;
                }
                String[] strArr2 = a;
                int length = strArr2.length;
                int i = 0;
                while (i < length) {
                    String str = strArr2[i];
                    boolean a = a(strArr, str);
                    b.put(str, Boolean.valueOf(a));
                    if (a) {
                        i++;
                    } else {
                        a.i(Constants.LogTag, "The required permission of <" + str + "> does not found!");
                        return false;
                    }
                }
            }
            return true;
        } catch (Throwable e) {
            a.c(Constants.LogTag, "check required permissins exception.", e);
            return false;
        }
    }

    private static boolean a(String[] strArr, String str) {
        for (Object equals : strArr) {
            if (str.equals(equals)) {
                return true;
            }
        }
        return false;
    }
}
