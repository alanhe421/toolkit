package com.qq.reader.liveshow.utils;

import android.util.Log;
import com.qq.reader.liveshow.model.c;
import com.tencent.imsdk.QLogImpl;
import java.util.Calendar;

public class SxbLog {
    public static String a = "clogs.host.createRoom";
    public static String b = "clogs.viewer.enterRoom";
    public static String c = "clogs.viewer.quitRoom";
    public static String d = "clogs.host.quitRoom";
    public static String e = "clogs.viewer.upShow";
    public static String f = "clogs.viewer.unShow";
    public static String g = "clogs.host.kick";
    private static boolean h = e.b;
    private static SxbLogLevel i = SxbLogLevel.OFF;

    public enum SxbLogLevel {
        OFF,
        ERROR,
        WARN,
        DEBUG,
        INFO
    }

    public static void a(SxbLogLevel sxbLogLevel) {
        i = sxbLogLevel;
        d("Log", "change log level: " + sxbLogLevel);
    }

    public static void a(boolean z) {
        h = z;
    }

    public static void a(String str, String str2) {
        if (h) {
            Log.v(str, str2);
        }
        if (i.ordinal() >= SxbLogLevel.INFO.ordinal()) {
            o.a("I", str, str2, null);
        }
    }

    public static void b(String str, String str2) {
        a(str, str2);
    }

    public static void c(String str, String str2) {
        if (h) {
            Log.d(str, str2);
        }
        if (i.ordinal() >= SxbLogLevel.DEBUG.ordinal()) {
            o.a(QLogImpl.TAG_REPORTLEVEL_DEVELOPER, str, str2, null);
        }
    }

    public static void d(String str, String str2) {
        if (h) {
            Log.w(str, str2);
        }
        if (i.ordinal() >= SxbLogLevel.WARN.ordinal()) {
            o.a(QLogImpl.TAG_REPORTLEVEL_COLORUSER, str, str2, null);
        }
    }

    public static void e(String str, String str2) {
        if (h) {
            Log.e(str, str2);
        }
        if (i.ordinal() >= SxbLogLevel.ERROR.ordinal()) {
            o.a(QLogImpl.TAG_REPORTLEVEL_USER, str, str2, null);
        }
    }

    public static String a() {
        long currentTimeMillis = System.currentTimeMillis();
        Log.v("Test", String.valueOf(currentTimeMillis));
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(currentTimeMillis);
        return instance.get(12) + ":" + instance.get(13) + ":" + instance.get(14);
    }

    public static void a(String str, String str2, String str3, String str4) {
        if (c.a().h() == 1) {
            c(str, LogConstants.b + LogConstants.a + c.a().b() + LogConstants.a + str2 + LogConstants.a + str3 + LogConstants.a + str4);
        } else {
            c(str, LogConstants.c + LogConstants.a + c.a().b() + LogConstants.a + str2 + LogConstants.a + str3 + LogConstants.a + str4);
        }
    }

    public static void b(String str, String str2, String str3, String str4) {
        if (c.a().h() == 1) {
            c(str, LogConstants.g + LogConstants.a + c.a().b() + LogConstants.a + str2 + LogConstants.a + str3 + LogConstants.a + str4);
        } else {
            c(str, LogConstants.d + LogConstants.a + c.a().b() + LogConstants.a + str2 + LogConstants.a + str3 + LogConstants.a + str4);
        }
    }

    public static void c(String str, String str2, String str3, String str4) {
        c(str, LogConstants.f + LogConstants.a + c.a().b() + LogConstants.a + str2 + LogConstants.a + str3 + LogConstants.a + str4);
    }
}
