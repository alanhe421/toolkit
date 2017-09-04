package com.tencent.feedback.common;

import android.util.Log;
import java.util.Locale;

/* compiled from: RQDSRC */
public final class e {
    private static a a = null;

    /* compiled from: RQDSRC */
    public interface a {
        void a(String str) {
            Log.i("eup", str);
        }

        void b(String str) {
            Log.d("eup", str);
        }

        void c(String str) {
            Log.w("eup", str);
        }

        void d(String str) {
            Log.e("eup", str);
        }
    }

    private static synchronized a a() {
        a aVar;
        synchronized (e.class) {
            aVar = a;
        }
        return aVar;
    }

    public static synchronized void a(a aVar) {
        synchronized (e.class) {
            a = aVar;
        }
    }

    private static boolean a(int i, String str, Object... objArr) {
        a a = a();
        if (a == null) {
            return false;
        }
        if (str == null) {
            str = "null";
        } else if (!(objArr == null || objArr.length == 0)) {
            str = String.format(Locale.US, str, objArr);
        }
        switch (i) {
            case 0:
                a.a(str);
                return true;
            case 1:
                a.b(str);
                return true;
            case 2:
                a.c(str);
                return true;
            case 3:
                a.d(str);
                return true;
            default:
                return false;
        }
    }

    private static boolean a(int i, Throwable th) {
        a a = a();
        if (a == null) {
            return false;
        }
        String a2 = com.tencent.feedback.proguard.a.a(th);
        switch (i) {
            case 0:
                a.a(a2);
                return true;
            case 1:
                a.b(a2);
                return true;
            case 2:
                a.c(a2);
                return true;
            case 3:
                a.d(a2);
                return true;
            default:
                return false;
        }
    }

    public static boolean a(String str, Object... objArr) {
        return a(0, str, objArr);
    }

    public static boolean b(String str, Object... objArr) {
        return a(1, str, objArr);
    }

    public static boolean c(String str, Object... objArr) {
        return a(2, str, objArr);
    }

    public static boolean a(Throwable th) {
        return a(2, th);
    }

    public static boolean d(String str, Object... objArr) {
        return a(3, str, objArr);
    }

    public static boolean b(Throwable th) {
        return a(3, th);
    }
}
