package com.tencent.mm.sdk.b;

import android.os.Build;
import android.os.Build.VERSION;
import android.os.Looper;
import android.os.Process;

public final class b {
    public static e h;
    private static a i;
    private static a j;
    private static final String k;
    private static int level = 6;

    static {
        a cVar = new c();
        i = cVar;
        j = cVar;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("VERSION.RELEASE:[" + VERSION.RELEASE);
        stringBuilder.append("] VERSION.CODENAME:[" + VERSION.CODENAME);
        stringBuilder.append("] VERSION.INCREMENTAL:[" + VERSION.INCREMENTAL);
        stringBuilder.append("] BOARD:[" + Build.BOARD);
        stringBuilder.append("] DEVICE:[" + Build.DEVICE);
        stringBuilder.append("] DISPLAY:[" + Build.DISPLAY);
        stringBuilder.append("] FINGERPRINT:[" + Build.FINGERPRINT);
        stringBuilder.append("] HOST:[" + Build.HOST);
        stringBuilder.append("] MANUFACTURER:[" + Build.MANUFACTURER);
        stringBuilder.append("] MODEL:[" + Build.MODEL);
        stringBuilder.append("] PRODUCT:[" + Build.PRODUCT);
        stringBuilder.append("] TAGS:[" + Build.TAGS);
        stringBuilder.append("] TYPE:[" + Build.TYPE);
        stringBuilder.append("] USER:[" + Build.USER + "]");
        k = stringBuilder.toString();
    }

    public static void a(String str, String str2) {
        a(str, str2, null);
    }

    public static void a(String str, String str2, Object... objArr) {
        if (j != null && j.b() <= 4) {
            String format = objArr == null ? str2 : String.format(str2, objArr);
            if (format == null) {
                format = "";
            }
            String b = b(str);
            a aVar = j;
            Process.myPid();
            Thread.currentThread().getId();
            Looper.getMainLooper().getThread().getId();
            aVar.f(b, format);
        }
    }

    private static String b(String str) {
        return h != null ? h.b(str) : str;
    }

    public static void b(String str, String str2) {
        if (j != null && j.b() <= 2) {
            if (str2 == null) {
                str2 = "";
            }
            String b = b(str);
            a aVar = j;
            Process.myPid();
            Thread.currentThread().getId();
            Looper.getMainLooper().getThread().getId();
            aVar.d(b, str2);
        }
    }

    public static void c(String str, String str2) {
        if (j != null && j.b() <= 1) {
            if (str2 == null) {
                str2 = "";
            }
            String b = b(str);
            a aVar = j;
            Process.myPid();
            Thread.currentThread().getId();
            Looper.getMainLooper().getThread().getId();
            aVar.e(b, str2);
        }
    }
}
