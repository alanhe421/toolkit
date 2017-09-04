package com.qq.reader.common.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import java.util.List;

/* compiled from: LauncherUtil */
public class p {
    private static String a = null;

    public static String a(Context context) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
        if (resolveActivity == null || resolveActivity.activityInfo == null) {
            return "";
        }
        if (resolveActivity.activityInfo.packageName.equals("android")) {
            return "";
        }
        return resolveActivity.activityInfo.packageName;
    }

    public static String b(Context context) {
        if (TextUtils.isEmpty(a)) {
            a = a(context, "com.android.launcher.permission.READ_SETTINGS");
        }
        return a;
    }

    public static String a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(8);
            if (installedPackages == null) {
                return "";
            }
            for (PackageInfo packageInfo : installedPackages) {
                ProviderInfo[] providerInfoArr = packageInfo.providers;
                if (providerInfoArr != null) {
                    for (ProviderInfo providerInfo : providerInfoArr) {
                        if (str.equals(providerInfo.readPermission) || str.equals(providerInfo.writePermission)) {
                            return providerInfo.authority;
                        }
                    }
                    continue;
                }
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
