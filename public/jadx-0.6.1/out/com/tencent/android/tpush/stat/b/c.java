package com.tencent.android.tpush.stat.b;

import android.content.Context;
import android.util.Log;
import com.tencent.android.tpush.stat.a.h;

/* compiled from: ProGuard */
public class c {
    private static String a = null;

    public static String a(Context context) {
        if (!a(a)) {
            synchronized (c.class) {
                if (!a(a)) {
                    a = h.a(context).a().c;
                }
            }
        }
        return a;
    }

    public static void a(Context context, String str) {
        Log.d("MID", "updateLocalMid:" + str);
        d dVar = new d();
        dVar.a = h.e(context);
        dVar.b = h.f(context);
        dVar.c = str;
        dVar.d = System.currentTimeMillis();
        h.a(context).a(dVar);
    }

    public static boolean a(String str) {
        if (str == null || str.trim().length() != 40) {
            return false;
        }
        return true;
    }
}
