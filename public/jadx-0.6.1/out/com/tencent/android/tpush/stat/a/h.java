package com.tencent.android.tpush.stat.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import com.tencent.android.tpush.common.p;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class h {
    private static String a = "";
    private static String b = "";
    private static String c = "";

    public static boolean a(Context context, String str) {
        try {
            if (context.getPackageManager().checkPermission(str, context.getPackageName()) == 0) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            Log.e("XgStat", "checkPermission error", th);
            return false;
        }
    }

    public static String a(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                return telephonyManager.getSimOperator();
            }
        } catch (Throwable th) {
            Log.e("XgStat", "", th);
        }
        return null;
    }

    public static String b(Context context) {
        try {
            String externalStorageState = Environment.getExternalStorageState();
            if (externalStorageState != null && externalStorageState.equals("mounted")) {
                externalStorageState = Environment.getExternalStorageDirectory().getPath();
                if (externalStorageState != null) {
                    StatFs statFs = new StatFs(externalStorageState);
                    return String.valueOf((((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks())) / 1000000) + "/" + String.valueOf((((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize())) / 1000000);
                }
            }
        } catch (Throwable th) {
            Log.e("XgStat", "", th);
        }
        return null;
    }

    public static DisplayMetrics c(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static String d(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                String typeName = activeNetworkInfo.getTypeName();
                String extraInfo = activeNetworkInfo.getExtraInfo();
                if (typeName != null) {
                    if (typeName.equalsIgnoreCase("WIFI")) {
                        return "WIFI";
                    }
                    if (typeName.equalsIgnoreCase("MOBILE")) {
                        if (extraInfo == null) {
                            return "MOBILE";
                        }
                        return extraInfo;
                    } else if (extraInfo == null) {
                        return typeName;
                    } else {
                        return extraInfo;
                    }
                }
            }
        } catch (Throwable th) {
            Log.e("XgStat", "", th);
        }
        return null;
    }

    public static String e(Context context) {
        try {
            if (p.b(a)) {
                a = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                if (a != null) {
                    return a;
                }
            }
        } catch (Throwable th) {
            Log.e("XgStat", "get device id error", th);
        }
        return a;
    }

    public static String f(Context context) {
        try {
            b = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        } catch (Throwable th) {
            Log.e("XgStat", "get wifi address error", th);
        }
        return b;
    }

    public static String a(String str) {
        if (str == null) {
            return null;
        }
        if (VERSION.SDK_INT < 8) {
            return str;
        }
        try {
            return new String(d.b(Base64.decode(str.getBytes("UTF-8"), 0)), "UTF-8");
        } catch (Throwable th) {
            Log.e("XgStat", "decode error", th);
            return str;
        }
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        if (VERSION.SDK_INT < 8) {
            return str;
        }
        try {
            return new String(Base64.encode(d.a(str.getBytes("UTF-8")), 0), "UTF-8");
        } catch (Throwable th) {
            Log.e("XgStat", "encode error", th);
            return str;
        }
    }

    public static void a(JSONObject jSONObject, String str, String str2) {
        if (str2 != null) {
            try {
                if (str2.length() > 0) {
                    jSONObject.put(str, str2);
                }
            } catch (Throwable th) {
                Log.e("XgStat", "jsonPut error", th);
            }
        }
    }

    public static WifiInfo g(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
            if (wifiManager != null) {
                return wifiManager.getConnectionInfo();
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public static String h(Context context) {
        try {
            WifiInfo g = g(context);
            if (g != null) {
                return g.getBSSID();
            }
        } catch (Throwable th) {
            Log.e("XgStat", "encode error", th);
        }
        return null;
    }

    public static String i(Context context) {
        try {
            WifiInfo g = g(context);
            if (g != null) {
                return g.getSSID();
            }
        } catch (Throwable th) {
            Log.e("XgStat", "encode error", th);
        }
        return null;
    }

    public static boolean j(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null) {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                    return true;
                }
                Log.w("XgStat", "Network error");
                return false;
            }
        } catch (Throwable th) {
            Log.e("XgStat", "isNetworkAvailable error", th);
        }
        return false;
    }

    public static boolean k(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null) {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getTypeName().equalsIgnoreCase("WIFI")) {
                    return true;
                }
                return false;
            }
        } catch (Throwable th) {
            Log.e("XgStat", "isWifiNet error", th);
        }
        return false;
    }

    public static JSONArray a(Context context, int i) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager != null) {
                List scanResults = wifiManager.getScanResults();
                if (scanResults != null && scanResults.size() > 0) {
                    Collections.sort(scanResults, new i());
                    JSONArray jSONArray = new JSONArray();
                    int i2 = 0;
                    while (i2 < scanResults.size() && i2 < i) {
                        ScanResult scanResult = (ScanResult) scanResults.get(i2);
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("bs", scanResult.BSSID);
                        jSONObject.put("ss", scanResult.SSID);
                        jSONArray.put(jSONObject);
                        i2++;
                    }
                    return jSONArray;
                }
            }
        } catch (Throwable th) {
            Log.e("XgStat", "isWifiNet error", th);
        }
        return null;
    }
}
