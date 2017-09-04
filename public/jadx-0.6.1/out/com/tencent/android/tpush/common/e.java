package com.tencent.android.tpush.common;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import com.tencent.android.tpush.a.a;
import java.io.Closeable;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class e {
    public static boolean a(JSONObject jSONObject, String str, Object obj) {
        if (obj != null) {
            try {
                jSONObject.put(str, obj);
                return true;
            } catch (JSONException e) {
            }
        }
        return false;
    }

    public static Object b(JSONObject jSONObject, String str, Object obj) {
        try {
            if (jSONObject.has(str)) {
                obj = jSONObject.get(str);
            }
        } catch (JSONException e) {
        }
        return obj;
    }

    public static Object a(Context context, String str, Object obj) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null) {
                return obj;
            }
            Object obj2 = applicationInfo.metaData.get(str);
            if (obj2 != null) {
                return obj2;
            }
            return obj;
        } catch (Throwable th) {
            a.c(Constants.LogTag, "", th);
            return obj;
        }
    }

    public static JSONArray a(Context context, int i) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager != null) {
                List scanResults = wifiManager.getScanResults();
                if (scanResults != null && scanResults.size() > 0) {
                    Collections.sort(scanResults, new f());
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
            a.c(Constants.LogTag, "getMetaData", th);
        }
        return null;
    }

    public static boolean a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
                return true;
            } catch (Exception e) {
            }
        }
        return false;
    }
}
