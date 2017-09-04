package com.tencent.qalsdk.util;

import android.content.Context;
import android.util.Log;
import com.tencent.qalsdk.sdk.q;

public class ALog {
    private static q helper = new q();

    public static void init(Context context) {
        helper.a(context, "app");
    }

    public static String getFilePath() {
        return helper.d();
    }

    public static void e(String str, String str2) {
        Log.e(str, str2);
        helper.a(str, "[E] " + str2, null);
    }

    public static void w(String str, String str2) {
        Log.w(str, str2);
        helper.a(str, "[W] " + str2, null);
    }

    public static void i(String str, String str2) {
        Log.i(str, str2);
        helper.a(str, "[I] " + str2, null);
    }

    public static void d(String str, String str2) {
        Log.d(str, str2);
    }

    public static String getLogFileName(long j) {
        return helper.a(helper.a(j));
    }
}
