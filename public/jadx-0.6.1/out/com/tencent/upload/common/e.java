package com.tencent.upload.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.tencent.av.config.ConfigBaseParser;
import java.util.ArrayList;
import java.util.List;

public final class e extends BroadcastReceiver {
    private static e a = null;
    private static int c = 0;
    private Context b = null;
    private String d = "none";
    private List<a> e = new ArrayList();

    public interface a {
        void onNetworkConnect(boolean z);
    }

    public static e a() {
        if (a == null) {
            a = new e();
        }
        return a;
    }

    private void a(boolean z) {
        synchronized (this.e) {
            a[] aVarArr = new a[this.e.size()];
            this.e.toArray(aVarArr);
        }
        if (aVarArr != null) {
            for (a onNetworkConnect : aVarArr) {
                onNetworkConnect.onNetworkConnect(z);
            }
        }
    }

    public static boolean b(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
            if (allNetworkInfo != null) {
                int length = allNetworkInfo.length;
                int i = 0;
                while (i < length) {
                    NetworkInfo networkInfo = allNetworkInfo[i];
                    if (networkInfo == null || !networkInfo.isConnectedOrConnecting()) {
                        i++;
                    } else {
                        switch (networkInfo.getType()) {
                            case 0:
                                switch (networkInfo.getSubtype()) {
                                    case 1:
                                    case 2:
                                    case 4:
                                    case 7:
                                    case 11:
                                        c = 3;
                                        break;
                                    case 3:
                                    case 5:
                                    case 6:
                                    case 8:
                                    case 9:
                                    case 10:
                                    case 12:
                                    case 14:
                                    case 15:
                                        c = 2;
                                        break;
                                    case 13:
                                        c = 6;
                                        break;
                                    default:
                                        break;
                                }
                            case 1:
                                c = 1;
                                break;
                            default:
                                c = 0;
                                break;
                        }
                        i = c;
                        return true;
                    }
                }
            }
            return false;
        } catch (SecurityException e) {
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    public static int c() {
        return c;
    }

    private static boolean c(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } catch (Throwable th) {
            return true;
        }
    }

    private String d() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.b.getSystemService("connectivity");
            NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
            return (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) ? "none" : 1 == activeNetworkInfo.getType() ? "wifi" : activeNetworkInfo.getExtraInfo() != null ? activeNetworkInfo.getExtraInfo().toLowerCase() : ConfigBaseParser.DEFAULT_VALUE;
        } catch (Throwable th) {
            return ConfigBaseParser.DEFAULT_VALUE;
        }
    }

    public final void a(Context context) {
        this.b = context;
        context.registerReceiver(this, new IntentFilter(new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")));
        b(context);
    }

    public final void a(a aVar) {
        if (aVar != null) {
            synchronized (this.e) {
                if (!this.e.contains(aVar)) {
                    this.e.add(aVar);
                }
            }
        }
    }

    public final String b() {
        return this.d;
    }

    public final void onReceive(Context context, Intent intent) {
        String d = d();
        com.tencent.upload.common.a.a.d("NetworkState", "NetworkStateReceiver ====== " + intent.getAction() + " apn:" + this.d + " -> " + d + " Available:" + c(context));
        this.d = d;
        if (intent.getAction() != null && intent.getAction().compareTo("android.net.conn.CONNECTIVITY_CHANGE") == 0) {
            a(b(context));
        }
    }
}
