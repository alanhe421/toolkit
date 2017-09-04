package com.sijla.j;

import android.util.Log;
import java.util.Locale;

public class f {
    private static boolean a = false;
    private static boolean b = false;
    private static boolean c = false;
    private static boolean d = false;
    private static boolean e = false;

    private static String a(StackTraceElement stackTraceElement) {
        String className = stackTraceElement.getClassName();
        className = className.substring(className.lastIndexOf(".") + 1);
        return String.format(Locale.getDefault(), "%s.%s(L:%d)", new Object[]{className, stackTraceElement.getMethodName(), Integer.valueOf(stackTraceElement.getLineNumber())});
    }

    public static void a(String str) {
        if (a) {
            Log.d(a(a()), "qlog " + str);
        }
    }

    public static void b(String str) {
        if (b) {
            Log.e(a(a()), "qlog " + str);
        }
    }

    public static void c(String str) {
        if (c) {
            Log.i(a(a()), "qlog " + str);
        }
    }

    public static void d(String str) {
        if (e) {
            Log.w(a(a()), str);
        }
    }

    private static StackTraceElement a() {
        return Thread.currentThread().getStackTrace()[4];
    }
}
