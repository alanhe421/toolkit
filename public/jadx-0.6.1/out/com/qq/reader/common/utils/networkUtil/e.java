package com.qq.reader.common.utils.networkUtil;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.e.a;

/* compiled from: NetWorkUtil */
public class e {
    public static boolean a() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) ReaderApplication.getApplicationImp().getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) {
            return false;
        }
        return true;
    }

    public static boolean a(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean z = activeNetworkInfo != null && activeNetworkInfo.isAvailable();
        return z;
    }

    public static String b() {
        int b = a.b();
        StringBuilder stringBuilder = new StringBuilder("Net TYPE : ");
        switch (b) {
            case 0:
                stringBuilder.append("UNKNOWN");
                break;
            case 1:
                stringBuilder.append("WIFI");
                break;
            case 2:
                stringBuilder.append("2G");
                break;
            case 3:
                stringBuilder.append("3G");
                break;
            case 4:
                stringBuilder.append("4G");
                break;
        }
        return stringBuilder.toString();
    }
}
