package com.qq.reader.appconfig;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;

/* compiled from: Version */
public class f {
    public static String a = null;
    public static String b = null;

    public static String a(Context context) {
        if (b == null) {
            try {
                b = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            } catch (NameNotFoundException e) {
                e.printStackTrace();
                b = "qqreader_6.5.3.0888_android";
            } catch (Exception e2) {
                e2.printStackTrace();
                b = "qqreader_6.5.3.0888_android";
            }
        }
        return b;
    }
}
