package com.xiaomi.push.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.xmpush.thrift.ac;
import java.util.Map;

public class z {
    public static Runnable a;

    private static String a(Context context, String str) {
        return context.getSharedPreferences("typed_shield_pref", 0).getString(str + "_title", str);
    }

    public static String a(ac acVar) {
        Map s = acVar.m().s();
        return s == null ? null : (String) s.get("__typed_shield_type");
    }

    @TargetApi(19)
    static void a(Context context, ac acVar, Notification notification) {
        if (VERSION.SDK_INT >= 19) {
            String a = a(acVar);
            if (!TextUtils.isEmpty(a) && "com.xiaomi.xmsf".equals(f.a(acVar))) {
                Bundle bundle = notification.extras;
                if (bundle == null) {
                    bundle = new Bundle();
                }
                bundle.putString("miui.category", a);
                bundle.putString("miui.substName", a(context, a));
                notification.extras = bundle;
            }
        }
    }

    public static boolean a(Context context, ac acVar) {
        if (!"com.xiaomi.xmsf".equals(f.a(acVar))) {
            return false;
        }
        String a = a(acVar);
        if (TextUtils.isEmpty(a)) {
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("typed_shield_pref", 0);
        if (!(sharedPreferences.contains(a + "_shield") || a == null)) {
            a.run();
        }
        return sharedPreferences.getBoolean(a + "_shield", true);
    }
}
