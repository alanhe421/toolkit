package com.qrcomic.a;

import android.content.Context;
import android.os.Environment;

/* compiled from: QRComicConstants */
public class f {
    public static boolean a = false;
    public static boolean b = false;
    private static String c = "QQReader";

    public static String a(Context context) {
        return "mounted".equals(Environment.getExternalStorageState()) ? Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + c + "/" : context.getCacheDir().getAbsolutePath() + "/";
    }

    public static String b(Context context) {
        return a(context) + "comic/";
    }

    public static String c(Context context) {
        return b(context) + "diskcache/";
    }
}
