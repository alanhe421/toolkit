package com.tencent.upload.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.tencent.av.config.ConfigBaseParser;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tencent.tls.tools.util.APNName;

public final class d {
    private static Context a;
    private static b b;
    private static Object c = new Object();
    private static List<WeakReference<a>> d = Collections.synchronizedList(new ArrayList());

    public interface a {
    }

    public static class b extends BroadcastReceiver {
        private Context a;
        private String b = "none";
        private int c = 0;

        public b(Context context) {
            this.a = context;
        }

        public final String a() {
            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) this.a.getSystemService("connectivity");
                NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
                return (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) ? "none" : 1 == activeNetworkInfo.getType() ? "wifi" : activeNetworkInfo.getExtraInfo() != null ? activeNetworkInfo.getExtraInfo().toLowerCase() : ConfigBaseParser.DEFAULT_VALUE;
            } catch (Throwable th) {
                return ConfigBaseParser.DEFAULT_VALUE;
            }
        }

        public final void onReceive(Context context, Intent intent) {
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                String a = a();
                int a2 = d.a(a);
                com.tencent.upload.common.a.a.b("NetworkManager", "old apn:" + this.b + "  new apn:" + a + " old isp:" + this.c + " new isp:" + a2);
                if (!a.equals(this.b)) {
                    synchronized (d.c) {
                        for (WeakReference weakReference : d.d) {
                            if (((a) weakReference.get()) != null) {
                                String str = this.b;
                            }
                        }
                    }
                }
                this.b = a;
                this.c = a2;
            }
        }
    }

    public static int a(String str) {
        return TextUtils.isEmpty(str) ? 0 : (str.contains("cmnet") || str.contains(APNName.NAME_CMWAP) || str.contains("cmcc")) ? 1 : (str.contains("uninet") || str.contains(APNName.NAME_UNIWAP) || str.contains("unicom") || str.contains("3gnet") || str.contains(APNName.NAME_3GWAP)) ? 2 : (str.contains(APNName.NAME_CTWAP) || str.contains("ctnet") || str.contains("cmct") || str.contains("#777")) ? 3 : 0;
    }

    public static String a() {
        String str = "none";
        return (b != null && "none".equalsIgnoreCase(str)) ? b.a() : str;
    }

    public static void a(Context context) {
        if (a == null) {
            a = context;
            b = new b(context);
            context.registerReceiver(b, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
    }

    public static String b() {
        try {
            WifiInfo connectionInfo = ((WifiManager) a.getSystemService("wifi")).getConnectionInfo();
            return connectionInfo != null ? connectionInfo.getBSSID() : null;
        } catch (Throwable th) {
            return null;
        }
    }

    public static boolean c() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) a.getSystemService("connectivity");
            NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                return false;
            }
            if (activeNetworkInfo.getType() == 0) {
                return true;
            }
            return false;
        } catch (Throwable th) {
        }
    }

    public static boolean d() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) a.getSystemService("connectivity");
            NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
            return (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) ? false : 1 == activeNetworkInfo.getType();
        } catch (Throwable th) {
            return false;
        }
    }
}
