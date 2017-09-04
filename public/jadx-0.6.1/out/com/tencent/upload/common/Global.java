package com.tencent.upload.common;

import FileCloud.stEnvironment;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.tencent.upload.common.a.a;
import com.tencent.upload.log.b;

public class Global {
    public static final String SDK_VERSION = "1.1.3.332";
    public static String clientIP;
    public static Context context;
    private static String deviceId = "";
    private static stEnvironment env;
    private static int sNetType = 0;

    public static String getDeviceId() {
        if (!TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            String deviceId = telephonyManager != null ? telephonyManager.getDeviceId() : null;
            if (TextUtils.isEmpty(deviceId)) {
                deviceId = getMacAddress();
            }
            deviceId = deviceId;
            a.c("Global", "deviceid:" + deviceId);
            return TextUtils.isEmpty(deviceId) ? "" : deviceId;
        } catch (Throwable th) {
            b.c("Global", "read deviceid error!", th);
            return "no_deviceid";
        }
    }

    public static stEnvironment getEnv() {
        if (env == null || sNetType != getNetType()) {
            stEnvironment FileCloud_stEnvironment = new stEnvironment();
            env = FileCloud_stEnvironment;
            FileCloud_stEnvironment.source = 1;
            env.refer = "android.java";
            env.qua = Build.MODEL;
            env.operators = "";
            env.sdk_version = SDK_VERSION;
            env.os_version = VERSION.SDK_INT;
            FileCloud_stEnvironment = env;
            int netType = getNetType();
            sNetType = netType;
            FileCloud_stEnvironment.net = netType;
        }
        env.device = getDeviceId();
        return env;
    }

    public static String getMacAddress() {
        try {
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo != null) {
                return connectionInfo.getMacAddress();
            }
        } catch (Throwable e) {
            b.c("Global", "read mac error!", e);
        }
        return null;
    }

    public static int getNetType() {
        e.a();
        int c = e.c();
        return 1 == c ? 1 : 3 == c ? 3 : 2 == c ? 2 : 6 == c ? 4 : 0;
    }

    public static void init(Context context) {
        if (context == null) {
            context = context;
            b.a(context);
            com.tencent.upload.a.b.a(context);
            e.a().a(context.getApplicationContext());
            b.a(context, new com.tencent.upload.network.a.b());
            d.a(context);
        }
    }
}
