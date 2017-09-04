package com.tencent.android.tpush.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;

/* compiled from: ProGuard */
public class m {
    private static SharedPreferences a = null;

    static synchronized SharedPreferences a(Context context) {
        SharedPreferences sharedPreferences;
        synchronized (m.class) {
            if (a == null) {
                if (VERSION.SDK_INT >= 11) {
                    a = context.getSharedPreferences(".tpns.xml", 4);
                } else {
                    a = context.getSharedPreferences(".tpns.xml", 0);
                }
            }
            sharedPreferences = a;
        }
        return sharedPreferences;
    }

    public static boolean a(Context context, String str) {
        return a(context).contains(str);
    }

    public static long a(Context context, String str, long j) {
        return a(context).getLong(str, j);
    }

    public static void b(Context context, String str, long j) {
        Editor edit = a(context).edit();
        edit.putLong(str, j);
        edit.commit();
    }

    public static int a(Context context, String str, int i) {
        return a(context).getInt(str, i);
    }

    public static void b(Context context, String str, int i) {
        Editor edit = a(context).edit();
        edit.putInt(str, i);
        edit.commit();
    }

    public static String a(Context context, String str, String str2) {
        if (a(context).contains(str)) {
            return a(context).getString(str, str2);
        }
        return str2;
    }

    public static void b(Context context, String str, String str2) {
        Editor edit = a(context).edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public static void b(Context context, String str) {
        if (a(context) != null) {
            Editor edit = a(context).edit();
            edit.remove(str);
            edit.commit();
        }
    }
}
