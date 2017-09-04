package com.tencent.util;

import android.os.Build.VERSION;

public final class VersionUtils {
    private VersionUtils() {
    }

    public static boolean isECLAIR_MR1() {
        return VERSION.SDK_INT >= 7;
    }

    public static boolean isrFroyo() {
        return VERSION.SDK_INT >= 8;
    }

    public static boolean isGingerBread() {
        return VERSION.SDK_INT >= 9;
    }

    public static boolean isIceScreamSandwich() {
        return VERSION.SDK_INT >= 14;
    }

    public static boolean isHoneycomb() {
        return VERSION.SDK_INT >= 11;
    }

    public static boolean isHoneycombMR2() {
        return VERSION.SDK_INT >= 13;
    }

    public static boolean isJellyBean() {
        return false;
    }

    public static boolean hasKitKat() {
        return VERSION.SDK_INT >= 19;
    }

    public static boolean isKitKat() {
        return VERSION.SDK_INT == 19;
    }
}
