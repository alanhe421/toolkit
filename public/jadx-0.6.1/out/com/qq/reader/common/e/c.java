package com.qq.reader.common.e;

import android.os.Build;

/* compiled from: OSUtils */
public class c {
    private static String a = null;

    private static boolean b() {
        try {
            b a = b.a();
            if (a.a("ro.miui.ui.version.code", null) == null && a.a("ro.miui.ui.version.name", null) == null && a.a("ro.miui.internal.storage", null) == null) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean c() {
        try {
            if (Build.class.getMethod("hasSmartBar", new Class[0]) != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static String a() {
        if (a == null) {
            if (b()) {
                a = "miui";
            } else if (c()) {
                a = "meizu";
            } else {
                a = "android";
            }
        }
        return a;
    }
}
