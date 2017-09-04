package com.tencent.feedback.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.tencent.av.config.ConfigBaseParser;

/* compiled from: RQDSRC */
public final class g {
    private static boolean a = false;
    private static boolean b = false;

    private static NetworkInfo d(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return null;
            }
            return connectivityManager.getActiveNetworkInfo();
        } catch (Throwable th) {
            if (!e.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static boolean a(Context context) {
        NetworkInfo d = d(context);
        if (d == null || d.getType() != 1) {
            return false;
        }
        return true;
    }

    public static boolean b(Context context) {
        NetworkInfo d = d(context);
        if (d == null || !d.isConnected()) {
            return false;
        }
        return true;
    }

    public static String c(Context context) {
        NetworkInfo d = d(context);
        if (d == null) {
            return ConfigBaseParser.DEFAULT_VALUE;
        }
        if (d.getType() == 1) {
            return "wifi";
        }
        String extraInfo = d.getExtraInfo();
        if (extraInfo != null && extraInfo.length() > 64) {
            extraInfo = extraInfo.substring(0, 64);
        }
        return extraInfo;
    }
}
