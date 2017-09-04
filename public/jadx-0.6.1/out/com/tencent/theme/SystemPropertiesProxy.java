package com.tencent.theme;

import android.content.Context;

public class SystemPropertiesProxy {
    private SystemPropertiesProxy() {
    }

    public static String get(Context context, String str) throws IllegalArgumentException {
        String str2 = "";
        try {
            Class loadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            return (String) loadClass.getMethod("get", new Class[]{String.class}).invoke(loadClass, new Object[]{new String(str)});
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e2) {
            return "";
        }
    }
}
