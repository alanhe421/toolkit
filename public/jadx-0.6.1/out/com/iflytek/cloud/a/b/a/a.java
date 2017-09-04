package com.iflytek.cloud.a.b.a;

import android.util.Log;
import com.iflytek.cloud.Setting;

public class a {
    private static String a = "MscSpeechLog";
    private static boolean b = false;

    public static void a(String str) {
        if (Setting.a()) {
            Log.d(a, str);
        }
    }

    public static void a(String str, String str2) {
        if (Setting.a()) {
            Log.d(str, str2);
        }
    }

    public static void b(String str) {
        if (Setting.a()) {
            Log.e(a, str);
        }
    }

    public static void c(String str) {
        if (Setting.a() && b) {
            Log.d(a, str);
        }
    }
}
