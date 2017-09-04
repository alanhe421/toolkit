package com.qq.reader.common.monitor;

import android.util.Log;
import com.qq.reader.appconfig.b;

/* compiled from: Log */
public class f {
    private static boolean a = b.f;
    private static boolean b = a;
    private static long c = 0;

    public static void a(String str, String str2) {
        if (b && str2 != null) {
            Log.e(str, str2);
        }
    }

    public static void a(String str, String str2, Exception exception) {
        if (a) {
            Log.e(str, str2, exception);
        }
    }

    public static void b(String str, String str2) {
        if (a && str2 != null) {
            Log.i(str, str2);
        }
    }

    public static void c(String str, String str2) {
        if (a && str2 != null) {
            Log.w(str, str2);
        }
    }

    public static void d(String str, String str2) {
        if (a && str2 != null) {
            Log.d(str, str2);
        }
    }

    public static void e(String str, String str2) {
        if (a && str2 != null) {
            Log.v(str, str2);
        }
    }
}
