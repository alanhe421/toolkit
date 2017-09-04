package com.qrcomic.util;

import android.util.Log;
import com.qrcomic.a.f;

/* compiled from: QRLog */
public class g {
    public static boolean a = f.b;
    public static String b = "DEBUG";
    public static String c = "BATE";
    public static String d = "CLR";
    public static String e = "ONLINE";
    public static String f = d;

    public static boolean a() {
        return f.equals(d) && a;
    }

    public static void a(String str, String str2, String str3) {
        Log.i(str, "level : " + str2 + "   msg : " + str3);
    }

    public static void b(String str, String str2, String str3) {
        Log.d(str, "level : " + str2 + "   msg : " + str3);
    }

    public static void c(String str, String str2, String str3) {
        Log.e(str, "level : " + str2 + "   msg : " + str3);
    }

    public static void a(String str, String str2, String str3, String str4) {
        Log.d(str, "level : " + str2 + " error : " + str3 + " ex : " + str4);
    }

    public static void a(String str, String str2, String str3, long j, String str4, long j2) {
        Log.d(str, "level : " + str2 + " error : " + str3 + " ex : " + str4 + " msgCount : " + j + " totalCost : " + j2);
    }

    public static void a(String str, String str2, String str3, boolean z, String str4, boolean z2) {
        Log.d(str, "level : " + str2 + " error : " + str3 + " ex : " + str4 + " idleHandlerAttached : " + z + " hookReqeusted : " + z2);
    }

    public static void a(String str, String str2, String str3, int i, String str4, boolean z) {
        Log.d(str, "level : " + str2 + " error : " + str3 + " ex : " + str4 + " idleHandlerAttached : " + z + " what : " + i);
    }

    public static boolean b() {
        return a;
    }

    public static void a(Exception exception) {
        if (a()) {
            exception.printStackTrace();
        }
    }
}
