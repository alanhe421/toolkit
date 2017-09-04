package com.tencent.upload.log;

import android.content.Context;
import android.util.Log;
import java.util.Date;

public class b {
    private static a a;
    private static Context b;
    private static Context c;
    private static com.tencent.upload.network.a.b d;

    public static String a(Date date) {
        if (a == null || date == null) {
            return null;
        }
        a.a();
        return a.a(date);
    }

    public static void a() {
        if (a != null) {
            a.a();
        }
    }

    public static void a(Context context) {
        b = context.getApplicationContext();
        a = new c(b);
    }

    public static void a(Context context, com.tencent.upload.network.a.b bVar) {
        if (context == null || bVar == null) {
            throw new RuntimeException("upload组件init初始化参数错误！");
        }
        c = context;
        d = bVar;
    }

    public static void a(String str, String str2, Throwable th) {
        if (a != null) {
            a.a(str, str2, null);
        } else {
            Log.d(str, str2, null);
        }
    }

    public static Context b() {
        return c;
    }

    public static void b(String str, String str2, Throwable th) {
        if (a != null) {
            a.b(str, str2, null);
        } else {
            Log.i(str, str2, null);
        }
    }

    public static com.tencent.upload.network.a.b c() {
        return d;
    }

    public static void c(String str, String str2, Throwable th) {
        if (a != null) {
            a.c(str, str2, th);
        } else {
            Log.w(str, str2, th);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (a != null) {
            a.d(str, str2, null);
        } else {
            Log.e(str, str2, null);
        }
    }
}
