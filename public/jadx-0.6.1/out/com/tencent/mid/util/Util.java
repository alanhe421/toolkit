package com.tencent.mid.util;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import com.tencent.mid.api.MidConstants;
import com.tencent.mid.api.MidEntity;
import com.tencent.mid.api.MidProvider;
import com.tencent.mid.api.MidService;
import com.tencent.mid.b.g;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.http.HttpHost;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tencent.tls.tools.util.APNName;

public class Util {
    private static f a = null;
    private static Random b = null;
    public static int errorCount = 0;

    private static synchronized Random a() {
        Random random;
        synchronized (Util.class) {
            if (b == null) {
                b = new Random();
            }
            random = b;
        }
        return random;
    }

    private static JSONObject a(MidEntity midEntity) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("mid", midEntity.getMid());
        jSONObject.put(MidEntity.TAG_TIMESTAMPS, midEntity.getTimestamps() / 1000);
        return jSONObject;
    }

    public static String bytesToStr(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(b + "");
        }
        return stringBuffer.toString();
    }

    public static boolean checkPermission(Context context, String str) {
        try {
            return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
        } catch (Throwable th) {
            Log.e("MID", "checkPermission error", th);
            return false;
        }
    }

    public static String decode(String str) {
        if (str == null) {
            return null;
        }
        if (VERSION.SDK_INT < 8) {
            return str;
        }
        try {
            return new String(g.b(Base64.decode(str.getBytes("UTF-8"), 0)), "UTF-8").trim().replace("\t", "").replace("\n", "").replace("\r", "");
        } catch (Throwable th) {
            Log.e("MID", "decode error", th);
            return str;
        }
    }

    public static byte[] deocdeGZipContent(byte[] bArr) {
        InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
        byte[] bArr2 = new byte[4096];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length * 2);
        while (true) {
            int read = gZIPInputStream.read(bArr2);
            if (read != -1) {
                byteArrayOutputStream.write(bArr2, 0, read);
            } else {
                bArr2 = byteArrayOutputStream.toByteArray();
                byteArrayInputStream.close();
                gZIPInputStream.close();
                byteArrayOutputStream.close();
                return bArr2;
            }
        }
    }

    public static String encode(String str) {
        if (str == null) {
            return null;
        }
        if (VERSION.SDK_INT < 8) {
            return str;
        }
        try {
            return new String(Base64.encode(g.a(str.getBytes("UTF-8")), 0), "UTF-8").trim().replace("\t", "").replace("\n", "").replace("\r", "");
        } catch (Throwable th) {
            Log.e("MID", "encode error", th);
            return str;
        }
    }

    public static boolean equal(MidEntity midEntity, MidEntity midEntity2) {
        return (midEntity == null || midEntity2 == null) ? midEntity == null && midEntity2 == null : midEntity.compairTo(midEntity2) == 0;
    }

    public static String getDateString(int i) {
        try {
            Calendar instance = Calendar.getInstance();
            instance.roll(6, i);
            return new SimpleDateFormat("yyyyMMdd").format(instance.getTime());
        } catch (Throwable th) {
            return "00";
        }
    }

    public static byte[] getHMAC(String str, String str2) {
        try {
            Key secretKeySpec = new SecretKeySpec(str.getBytes(), "hmacmd5");
            Mac instance = Mac.getInstance("HmacSHA1");
            instance.init(secretKeySpec);
            instance.update(str2.getBytes());
            return instance.doFinal();
        } catch (Exception e) {
            a.b(e);
            return null;
        }
    }

    public static String getHttpAddr(Context context) {
        return "http://" + b.a(context).c();
    }

    public static HttpHost getHttpProxy() {
        return Proxy.getDefaultHost() != null ? new HttpHost(Proxy.getDefaultHost(), Proxy.getDefaultPort()) : null;
    }

    public static HttpHost getHttpProxy(Context context) {
        if (context == null) {
            return null;
        }
        try {
            if (context.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", context.getPackageName()) != 0) {
                return null;
            }
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            if (activeNetworkInfo.getTypeName() != null && activeNetworkInfo.getTypeName().equalsIgnoreCase("WIFI")) {
                return null;
            }
            String extraInfo = activeNetworkInfo.getExtraInfo();
            if (extraInfo == null) {
                return null;
            }
            if (extraInfo.equals(APNName.NAME_CMWAP) || extraInfo.equals(APNName.NAME_3GWAP) || extraInfo.equals(APNName.NAME_UNIWAP)) {
                return new HttpHost("10.0.0.172", 80);
            }
            if (extraInfo.equals(APNName.NAME_CTWAP)) {
                return new HttpHost("10.0.0.200", 80);
            }
            return null;
        } catch (Throwable th) {
            a.f(th);
        }
    }

    public static String getImei(Context context) {
        String str = "";
        try {
            String deviceId;
            if (checkPermission(context, "android.permission.READ_PHONE_STATE")) {
                deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                if (deviceId != null) {
                    return deviceId;
                }
                return deviceId != null ? "" : deviceId;
            } else {
                a.d("Could not get permission of android.permission.READ_PHONE_STATE");
                deviceId = str;
                if (deviceId != null) {
                }
            }
        } catch (Throwable th) {
            a.d("get device id error:" + th.toString());
        }
    }

    public static String getImsi(Context context) {
        String str = "";
        try {
            String str2;
            if (checkPermission(context, "android.permission.READ_PHONE_STATE")) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                str = telephonyManager.getSubscriberId();
                if (telephonyManager != null) {
                    return str;
                }
                str2 = str;
                if (str2 == null) {
                    str2 = "";
                }
                return str2;
            }
            a.d("Could not get permission of android.permission.READ_PHONE_STATE");
            str2 = str;
            if (str2 == null) {
                str2 = "";
            }
            return str2;
        } catch (Throwable th) {
            a.d("get subscriber id error:" + th.toString());
        }
    }

    public static Map<String, ProviderInfo> getLocalXGAppList(Context context) {
        Map<String, ProviderInfo> hashMap = new HashMap();
        for (ProviderInfo providerInfo : context.getPackageManager().queryContentProviders(null, 0, 0)) {
            if (providerInfo.name.equals("com.tencent.android.tpush.XGPushProvider") && providerInfo.authority.equals(getProviderAuth(providerInfo.packageName))) {
                hashMap.put(providerInfo.packageName, providerInfo);
                Log.d("MID.XG", providerInfo.authority + "," + providerInfo.packageName + "," + providerInfo.name);
            }
        }
        return hashMap;
    }

    public static synchronized f getLogger() {
        f fVar;
        synchronized (Util.class) {
            if (a == null) {
                a = new f("MID");
            }
            fVar = a;
        }
        return fVar;
    }

    public static Map<String, MidEntity> getMidsByApps(Context context, int i) {
        Map<String, MidEntity> hashMap = new HashMap(4);
        Map queryMatchContentProviders = queryMatchContentProviders(context);
        Log.i("MID", ">>>   queryMatchContentProviders size:" + (queryMatchContentProviders != null ? queryMatchContentProviders.size() : 0));
        MidEntity midEntity = null;
        if (i == 2) {
            midEntity = g.a(context).h();
        } else if (i == 3) {
            midEntity = g.a(context).c();
        }
        if (isMidValid(midEntity)) {
            hashMap.put(context.getPackageName(), midEntity);
        }
        if (queryMatchContentProviders == null || queryMatchContentProviders.size() == 0) {
            return hashMap;
        }
        for (String str : queryMatchContentProviders.keySet()) {
            try {
                String str2 = getPackageAuth(str) + "/" + i;
                String type = context.getContentResolver().getType(Uri.parse(str2));
                Log.d("MID", ">>>   mid cmd:" + str2 + ", return:" + type);
                if (!isEmpty(type)) {
                    MidEntity parse = MidEntity.parse(type);
                    if (parse != null && parse.isMidValid()) {
                        hashMap.put(str, parse);
                    }
                }
            } catch (Throwable th) {
                a.f(th);
            }
        }
        Log.d("MID", ">>>   appPrivateMidMap size:" + hashMap.size() + ",content:");
        for (Entry entry : hashMap.entrySet()) {
            Log.w("MID", ">>>   pkg:" + ((String) entry.getKey()) + ",midEntity:" + ((MidEntity) entry.getValue()).toString());
        }
        return hashMap;
    }

    public static MidEntity getNewerMidEntity(MidEntity midEntity, MidEntity midEntity2) {
        return (midEntity == null || midEntity2 == null) ? midEntity == null ? midEntity2 != null ? midEntity2 : null : midEntity : midEntity.compairTo(midEntity2) >= 0 ? midEntity : midEntity2;
    }

    public static String getPackageAuth(String str) {
        return "content://" + getPackageAuthName(str);
    }

    public static String getPackageAuthName(String str) {
        return str + MidConstants.PROVIDER_AUTH_SUFFIX;
    }

    public static String getProviderAuth(String str) {
        return str + ".AUTH_XGPUSH";
    }

    public static int getRandInt() {
        return a().nextInt(Integer.MAX_VALUE);
    }

    public static JSONArray getSensors(Context context) {
        try {
            SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
            if (sensorManager != null) {
                List sensorList = sensorManager.getSensorList(-1);
                if (sensorList != null && sensorList.size() > 0) {
                    JSONArray jSONArray = new JSONArray();
                    for (int i = 0; i < sensorList.size(); i++) {
                        Sensor sensor = (Sensor) sensorList.get(i);
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("name", sensor.getName());
                        jSONObject.put("vendor", sensor.getVendor());
                        jSONArray.put(jSONObject);
                    }
                    return jSONArray;
                }
            }
        } catch (Throwable th) {
            a.d(th.toString());
        }
        return null;
    }

    public static String getToken(Context context, String str) {
        String str2;
        String type = context.getContentResolver().getType(Uri.parse("content://" + str + ".AUTH_XGPUSH" + "/" + "tokenByMid"));
        if (type != null) {
            try {
                str2 = new String(Base64.decode(type.getBytes(), 0), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Log.i("MID.XG", "get token from pkg:" + str + ", token:" + str2);
            return (str2 == null && str2.trim().length() == 40) ? str2 : null;
        }
        str2 = type;
        Log.i("MID.XG", "get token from pkg:" + str + ", token:" + str2);
        if (str2 == null) {
        }
    }

    public static String getWiFiBBSID(Context context) {
        try {
            WifiInfo wifiInfo = getWifiInfo(context);
            if (wifiInfo != null) {
                return wifiInfo.getBSSID();
            }
        } catch (Throwable th) {
            a.d(th.toString());
        }
        return null;
    }

    public static String getWiFiSSID(Context context) {
        try {
            WifiInfo wifiInfo = getWifiInfo(context);
            if (wifiInfo != null) {
                return wifiInfo.getSSID();
            }
        } catch (Throwable th) {
            a.d(th.toString());
        }
        return null;
    }

    public static WifiInfo getWifiInfo(Context context) {
        if (checkPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
            if (wifiManager != null) {
                return wifiManager.getConnectionInfo();
            }
        }
        return null;
    }

    public static String getWifiMacAddress(Context context) {
        String str = "";
        if (checkPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
            try {
                WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                if (wifiManager == null) {
                    return "";
                }
                str = wifiManager.getConnectionInfo().getMacAddress();
            } catch (Exception e) {
                a.d("get wifi address error" + e);
                return "";
            }
        }
        a.d("Could not get permission of android.permission.ACCESS_WIFI_STATE");
        return str == null ? "" : str;
    }

    public static JSONArray getWifiTopN(Context context, int i) {
        try {
            if (!MidService.isEnableReportWifiList()) {
                return null;
            }
            if (checkPermission(context, "android.permission.INTERNET") && checkPermission(context, "android.permission.ACCESS_NETWORK_STATE")) {
                WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                if (wifiManager != null) {
                    List scanResults = wifiManager.getScanResults();
                    if (scanResults != null && scanResults.size() > 0) {
                        Collections.sort(scanResults, new o());
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
                return null;
            }
            a.d("can not get the permisson of android.permission.INTERNET");
            return null;
        } catch (Throwable th) {
            a.d(th.toString());
        }
    }

    public static void insertMid2OldProvider(Context context, String str, String str2) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("mid", str2);
            context.getContentResolver().insert(Uri.parse(getPackageAuth(str) + "/" + 11), contentValues);
        } catch (Throwable th) {
        }
    }

    public static void insertMid2Provider(Context context, String str, String str2) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("mid", str2);
            context.getContentResolver().insert(Uri.parse(getPackageAuth(str) + "/" + 10), contentValues);
        } catch (Throwable th) {
        }
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isMidValid(MidEntity midEntity) {
        return midEntity != null && isMidValid(midEntity.getMid());
    }

    public static boolean isMidValid(String str) {
        return str != null && str.trim().length() >= 40;
    }

    public static boolean isNetworkAvailable(Context context) {
        try {
            if (!checkPermission(context, "android.permission.INTERNET")) {
                return false;
            }
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null) {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null) {
                    if (activeNetworkInfo.isConnectedOrConnecting()) {
                        return true;
                    }
                    Log.w("MID", "Network error is not exist");
                    return false;
                }
            }
            errorCount++;
            if (errorCount <= 5) {
                return true;
            }
            if (errorCount < 10) {
                return false;
            }
            if (errorCount >= 10) {
                errorCount = 0;
            }
            return false;
        } catch (Throwable th) {
            Log.e("MID", "isNetworkAvailable error", th);
        }
    }

    public static boolean isStringValid(String str) {
        return (str == null || str.trim().length() == 0) ? false : true;
    }

    public static boolean isWifiNet(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return (activeNetworkInfo == null || activeNetworkInfo.getTypeName() == null || !activeNetworkInfo.getTypeName().equalsIgnoreCase("WIFI")) ? false : true;
    }

    public static void jsonPut(JSONObject jSONObject, String str, String str2) {
        if (isStringValid(str2)) {
            jSONObject.put(str, str2);
        }
    }

    public static String md5(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes("UTF-8"));
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte append : digest) {
                stringBuffer.append(append);
            }
            str = stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        return str;
    }

    public static Map<String, Integer> queryAllToken(Context context) {
        Map localXGAppList = getLocalXGAppList(context);
        Map<String, Integer> hashMap = new HashMap();
        if (localXGAppList == null || localXGAppList.size() == 0) {
            return hashMap;
        }
        for (String token : localXGAppList.keySet()) {
            String token2 = getToken(context, token);
            if (isMidValid(token2)) {
                Integer num = (Integer) hashMap.get(token2);
                if (num == null) {
                    hashMap.put(token2, Integer.valueOf(1));
                } else {
                    hashMap.put(token2, Integer.valueOf(num.intValue() + 1));
                }
            }
        }
        return hashMap;
    }

    public static Map<String, ProviderInfo> queryMatchContentProviders(Context context) {
        Map<String, ProviderInfo> hashMap = new HashMap();
        for (ProviderInfo providerInfo : context.getPackageManager().queryContentProviders(null, 0, 0)) {
            if (providerInfo.name.equals(MidProvider.class.getName()) && providerInfo.authority.equals(getPackageAuthName(providerInfo.packageName))) {
                hashMap.put(providerInfo.packageName, providerInfo);
            }
        }
        return hashMap;
    }

    public static JSONArray queryMids(Context context, int i) {
        MidEntity midEntity;
        JSONObject a;
        a.h("queryMids, midType=" + i);
        JSONArray jSONArray = new JSONArray();
        Map midsByApps = getMidsByApps(context, i == 2 ? 3 : 2);
        if (midsByApps != null && midsByApps.size() > 0) {
            for (Entry entry : midsByApps.entrySet()) {
                String str = (String) entry.getKey();
                midEntity = (MidEntity) entry.getValue();
                if (midEntity != null && midEntity.isMidValid()) {
                    try {
                        a = a(midEntity);
                        a.put("loc", "priv");
                        if (str.equals(context.getPackageName())) {
                            a.put("app", 1);
                        }
                        a.put("pkg", str);
                        jSONArray.put(a);
                    } catch (Exception e) {
                    }
                }
            }
        }
        midEntity = i == 2 ? g.a(context).d() : g.a(context).i();
        a.b("settingEntity:" + midEntity);
        if (midEntity != null && midEntity.isMidValid()) {
            try {
                a = a(midEntity);
                a.put("loc", "pub");
                a.put("lc", "set");
                jSONArray.put(a);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        midEntity = i == 2 ? g.a(context).e() : g.a(context).j();
        a.b("sdCardEntity:" + midEntity);
        if (midEntity != null && midEntity.isMidValid()) {
            try {
                a = a(midEntity);
                a.put("loc", "pub");
                a.put("lc", "sd");
                jSONArray.put(a);
            } catch (JSONException e22) {
                e22.printStackTrace();
            }
        }
        return jSONArray;
    }

    public static String selectMaxCountXgAppToken(Context context) {
        String str = null;
        Map queryAllToken = queryAllToken(context);
        if (queryAllToken != null && queryAllToken.size() > 0) {
            int i = 0;
            for (Entry entry : queryAllToken.entrySet()) {
                int intValue;
                String str2;
                if (((Integer) entry.getValue()).intValue() > i) {
                    intValue = ((Integer) entry.getValue()).intValue();
                    str2 = (String) entry.getKey();
                } else {
                    str2 = str;
                    intValue = i;
                }
                str = str2;
                i = intValue;
            }
        }
        return str;
    }

    public static byte[] strToBytes(String str) {
        byte[] bArr = new byte[str.length()];
        for (int i = 0; i < str.length(); i++) {
            bArr[i] = (byte) str.charAt(i);
        }
        return bArr;
    }

    public static void updateIfLocalInvalid(Context context, String str) {
        if (isMidValid(str)) {
            MidEntity midEntity = new MidEntity();
            midEntity.setImei(getImei(context));
            midEntity.setMac(getWifiMacAddress(context));
            midEntity.setMid(str);
            midEntity.setTimestamps(System.currentTimeMillis());
            g.a(context).f(midEntity);
        }
    }
}
