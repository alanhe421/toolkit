package com.tencent.mid.api;

import android.content.Context;
import com.tencent.mid.a.g;

public class MidService {
    private static boolean a = true;

    public static void enableDebug(boolean z) {
        g.a(z);
    }

    public static String getLocalMidOnly(Context context) {
        return com.tencent.mid.b.g.a(context).f();
    }

    public static String getMid(Context context) {
        return g.b(context);
    }

    public static String getMidRequestHost() {
        return null;
    }

    public static String getMidRequestUrl() {
        return null;
    }

    public static String getNewMid(Context context) {
        return com.tencent.mid.b.g.a(context).b();
    }

    public static boolean isEnableDebug() {
        return g.a();
    }

    public static boolean isEnableReportWifiList() {
        return a;
    }

    public static boolean isMidValid(String str) {
        return g.a(str);
    }

    public static void requestMid(Context context, MidCallback midCallback) {
        if (midCallback == null) {
            throw new IllegalArgumentException("error, callback is null!");
        } else if (context == null) {
            midCallback.onFail(-10000, "content is null!");
        } else {
            g.a(context.getApplicationContext(), midCallback);
        }
    }

    public static void setEnableReportWifiList(boolean z) {
        a = z;
    }

    public static void setMidRequestUrl(String str) {
    }
}
