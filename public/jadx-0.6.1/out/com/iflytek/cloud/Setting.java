package com.iflytek.cloud;

import android.os.Environment;

public class Setting {
    public static final String a = (Environment.getExternalStorageDirectory().getPath() + "/msc/msc.log");
    private static LOG_LEVEL b = LOG_LEVEL.none;
    private static String c = a;
    private static boolean d = true;
    private static boolean e = false;
    private static boolean f = true;

    public enum LOG_LEVEL {
        all,
        detail,
        normal,
        low,
        none
    }

    public static boolean a() {
        return d;
    }
}
