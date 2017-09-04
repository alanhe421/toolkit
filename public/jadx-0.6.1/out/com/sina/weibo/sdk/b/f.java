package com.sina.weibo.sdk.b;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* compiled from: NetworkHelper */
public class f {
    public static boolean a(Context context) {
        if (context == null || context.checkCallingOrSelfPermission("android.permission.INTERNET") == 0) {
            return true;
        }
        return false;
    }

    public static boolean b(Context context) {
        if (context == null) {
            return false;
        }
        NetworkInfo c = c(context);
        if (c != null && 1 == c.getType() && c.isConnected()) {
            return true;
        }
        return false;
    }

    public static NetworkInfo c(Context context) {
        return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
    }
}
