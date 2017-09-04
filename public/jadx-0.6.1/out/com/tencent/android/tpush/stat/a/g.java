package com.tencent.android.tpush.stat.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;

/* compiled from: ProGuard */
public class g {
    private static SharedPreferences a = null;

    static synchronized SharedPreferences a(Context context) {
        SharedPreferences sharedPreferences;
        synchronized (g.class) {
            if (VERSION.SDK_INT >= 11) {
                a = context.getSharedPreferences(".tpush_mta", 4);
            } else {
                a = context.getSharedPreferences(".tpush_mta", 0);
            }
            if (a == null) {
                a = PreferenceManager.getDefaultSharedPreferences(context);
            }
            sharedPreferences = a;
        }
        return sharedPreferences;
    }

    public static long a(Context context, String str, long j) {
        return a(context).getLong(e.a(context, "tpush_" + str), j);
    }

    public static void b(Context context, String str, long j) {
        String a = e.a(context, "tpush_" + str);
        Editor edit = a(context).edit();
        edit.putLong(a, j);
        edit.commit();
    }

    public static int a(Context context, String str, int i) {
        return a(context).getInt(e.a(context, "tpush_" + str), i);
    }

    public static void b(Context context, String str, int i) {
        String a = e.a(context, "tpush_" + str);
        Editor edit = a(context).edit();
        edit.putInt(a, i);
        edit.commit();
    }
}
