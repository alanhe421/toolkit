package com.tencent.beacon.e;

import android.util.Log;
import java.util.Locale;

/* compiled from: ProGuard */
public final class a {
    public static boolean a = false;
    public static boolean b = false;

    public static void a(String str, Object... objArr) {
        if ((a ? 1 : null) != null) {
            String str2 = "beacon";
            if (str == null) {
                str = "null";
            } else if (!(objArr == null || objArr.length == 0)) {
                str = String.format(Locale.US, str, objArr);
            }
            Log.i(str2, str);
        }
    }

    public static void b(String str, Object... objArr) {
        if ((a ? 1 : null) != null) {
            String str2 = "beacon";
            if (str == null) {
                str = "null";
            } else if (!(objArr == null || objArr.length == 0)) {
                str = String.format(Locale.US, str, objArr);
            }
            Log.d(str2, str);
        }
    }

    public static void c(String str, Object... objArr) {
        String str2 = "beacon";
        if (str == null) {
            str = "null";
        } else if (!(objArr == null || objArr.length == 0)) {
            str = String.format(Locale.US, str, objArr);
        }
        Log.w(str2, str);
    }

    public static void d(String str, Object... objArr) {
        if (str == null) {
            str = "null";
        } else if (!(objArr == null || objArr.length == 0)) {
            str = String.format(Locale.US, str, objArr);
        }
        Log.e("beacon", str);
    }

    public static void a(Throwable th) {
        if (th != null && (th instanceof Throwable)) {
            if ((a ? 1 : 0) != 0) {
                th.printStackTrace();
            } else {
                d(th.getMessage(), new Object[0]);
            }
        }
    }

    private static void a(String str, String str2, Object... objArr) {
        if ((a ? 1 : null) != null) {
            if (str2 == null) {
                str2 = "null";
            } else if (!(objArr == null || objArr.length == 0)) {
                str2 = String.format(Locale.US, str2, objArr);
            }
            Log.d(str, str2);
        }
    }

    public static void e(String str, Object... objArr) {
        a("beacon_step_api", str, objArr);
    }

    public static void f(String str, Object... objArr) {
        a("beacon_step_buffer", str, objArr);
    }

    public static void g(String str, Object... objArr) {
        a("beacon_step_db", str, objArr);
    }

    public static void h(String str, Object... objArr) {
        a("beacon_step_upload", str, objArr);
    }
}
