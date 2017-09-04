package com.tencent.midas.plugin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.tencent.midas.comm.APLog;
import java.io.File;
import java.io.IOException;

public class APPluginProxyBroadcastReceiver extends BroadcastReceiver {
    private IAPPluginBroadcastReceiver a(Context context, Intent intent) {
        if (intent == null) {
            return null;
        }
        String canonicalPath;
        String stringExtra = intent.getStringExtra(APPluginStatic.PARAM_PLUGIN_NAME);
        String stringExtra2 = intent.getStringExtra(APPluginStatic.PARAM_PLUGIN_RECEIVER_CLASS_NAME);
        if (TextUtils.isEmpty(null)) {
            try {
                canonicalPath = APPluginUtils.getInstallPath(context, stringExtra).getCanonicalPath();
            } catch (IOException e) {
                canonicalPath = null;
            }
        } else {
            canonicalPath = null;
        }
        APLog.i("APPLuginProxyBroadcastReciver", "startPluginIfNeccessary Params:" + stringExtra + ", " + stringExtra2);
        if (stringExtra == null || stringExtra.length() <= 0) {
            return null;
        }
        File file = new File(canonicalPath);
        if (!file.exists() || !file.isFile()) {
            return null;
        }
        PackageInfo packageInfo;
        PackageInfo packageInfo2 = (PackageInfo) APPluginStatic.b.get(canonicalPath);
        if (packageInfo2 == null) {
            packageInfo = APApkFileParser.getPackageInfo(context, canonicalPath, 1);
            APPluginStatic.b.put(canonicalPath, packageInfo);
        } else {
            packageInfo = packageInfo2;
        }
        try {
            ClassLoader a = APPluginStatic.a(context, stringExtra, canonicalPath);
            IAPPluginBroadcastReceiver iAPPluginBroadcastReceiver = (IAPPluginBroadcastReceiver) a.loadClass(stringExtra2).newInstance();
            try {
                iAPPluginBroadcastReceiver.IInit(stringExtra, canonicalPath, this, a, packageInfo, false);
                return iAPPluginBroadcastReceiver;
            } catch (Exception e2) {
                return iAPPluginBroadcastReceiver;
            }
        } catch (Exception e3) {
            return null;
        }
    }

    public static void sendBroadcastReceiver(Context context, String str, String str2, Intent intent) {
        intent.putExtra(APPluginStatic.PARAM_PLUGIN_NAME, str);
        intent.putExtra(APPluginStatic.PARAM_PLUGIN_RECEIVER_CLASS_NAME, str2);
        try {
            context.sendBroadcast(intent);
        } catch (Throwable th) {
        }
    }

    public void onReceive(Context context, Intent intent) {
        IAPPluginBroadcastReceiver a = a(context, intent);
        APLog.i("APPLuginProxyBroadcastReciver", "onReceive startPluginIfNeccessary: " + a);
        if (a != null) {
            a.IOnReceive(context, intent);
        }
    }
}
