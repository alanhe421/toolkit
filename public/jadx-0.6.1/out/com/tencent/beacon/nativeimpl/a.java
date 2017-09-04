package com.tencent.beacon.nativeimpl;

import android.app.Activity;
import android.content.Context;

/* compiled from: ProGuard */
public class a {
    private static boolean a = false;

    public static native String a(Context context, int i, Activity activity, String str);

    public static native String b(Context context, int i, Activity activity);

    public static String b(Context context, int i, Activity activity, String str) {
        try {
            if (!a) {
                System.loadLibrary("Beacon");
                com.tencent.beacon.e.a.b("load libBeacon.so success", new Object[0]);
                a = true;
            }
            return a(context, i, activity, str);
        } catch (Throwable th) {
            com.tencent.beacon.e.a.d("libBeacon.so load failed!", new Object[0]);
            com.tencent.beacon.e.a.a(th);
            return "NOLIB";
        }
    }

    public static String a(Context context, int i, Activity activity) {
        try {
            if (!a) {
                System.loadLibrary("Beacon");
                com.tencent.beacon.e.a.b("load libBeacon.so success", new Object[0]);
                a = true;
            }
            return b(context, i, activity);
        } catch (Throwable th) {
            com.tencent.beacon.e.a.d("libBeacon.so load failed!", new Object[0]);
            com.tencent.beacon.e.a.a(th);
            return "NOLIB";
        }
    }
}
