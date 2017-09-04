package com.tencent.android.tpush.common;

import java.lang.reflect.Method;

/* compiled from: ProGuard */
public class j {
    public static String a(String str) {
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            Method declaredMethod = cls.getDeclaredMethod("get", new Class[]{String.class});
            declaredMethod.setAccessible(true);
            return (String) declaredMethod.invoke(cls, new Object[]{str});
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean a() {
        try {
            return (p.b(a("ro.miui.ui.version.code")) && p.b(a(a("ro.miui.ui.version.name"))) && p.b(a(a("ro.miui.internal.storage")))) ? false : true;
        } catch (Throwable th) {
            return false;
        }
    }
}
