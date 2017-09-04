package com.tencent.midas.plugin;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import java.util.HashSet;

public class APApkFileParser {
    public static Drawable getAPKIcon(Context context, String str) {
        try {
            PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 1);
            if (packageArchiveInfo == null || packageArchiveInfo.applicationInfo == null) {
                return null;
            }
            ApplicationInfo applicationInfo = packageArchiveInfo.applicationInfo;
            Class cls = Class.forName("android.content.res.AssetManager");
            AssetManager assetManager = (AssetManager) cls.getConstructor((Class[]) null).newInstance((Object[]) null);
            cls.getDeclaredMethod("addAssetPath", new Class[]{String.class}).invoke(assetManager, new Object[]{str});
            DisplayMetrics displayMetrics = new DisplayMetrics();
            displayMetrics.setToDefaults();
            Resources resources = new Resources(assetManager, displayMetrics, context.getResources().getConfiguration());
            if (applicationInfo.icon != 0) {
                return resources.getDrawable(applicationInfo.icon);
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static PackageInfo getPackageInfo(Context context, String str, int i) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageArchiveInfo(str, i);
        } catch (Exception e) {
        }
        return packageInfo;
    }

    public static boolean isApkFileBroken(Context context, String str) {
        PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 64);
        return packageArchiveInfo == null || packageArchiveInfo.applicationInfo == null;
    }

    public static boolean isSignaturesSame(Signature[] signatureArr, Signature[] signatureArr2) {
        if (signatureArr == null || signatureArr2 == null) {
            return true;
        }
        HashSet hashSet = new HashSet();
        for (Object add : signatureArr) {
            hashSet.add(add);
        }
        HashSet hashSet2 = new HashSet();
        for (Object add2 : signatureArr2) {
            hashSet2.add(add2);
        }
        return hashSet.equals(hashSet2);
    }
}
