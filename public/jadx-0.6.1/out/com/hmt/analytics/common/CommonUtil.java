package com.hmt.analytics.common;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.webkit.WebView;
import com.hmt.analytics.dao.HMTParamsInfo;
import com.hmt.analytics.dao.OpenUDID_manager_hmt;
import com.hmt.analytics.objects.LatitudeAndLongitude;
import com.hmt.analytics.objects.SCell;
import com.hmt.analytics.util.SPUtils;
import com.tencent.android.tpush.common.Constants;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

public class CommonUtil {
    public static final String a = CommonUtil.class.getSimpleName();

    public static boolean checkPermissions(Context context, String str) {
        try {
            if (context.getPackageManager().checkPermission(str, context.getPackageName()) == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            printLog(a, e.getMessage());
            return false;
        }
    }

    public static boolean currentNoteworkTypeIsWIFI(Context context) {
        return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo().getType() == 1;
    }

    public static String getMuid(Context context) {
        return (String) SPUtils.get(context, "hmt_agent_online_setting", "muid", "");
    }

    public static boolean isWiFiActive(Context context) {
        if (!checkPermissions(context, "android.permission.ACCESS_WIFI_STATE")) {
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
        if (allNetworkInfo == null) {
            return false;
        }
        int i = 0;
        while (i < allNetworkInfo.length) {
            if (allNetworkInfo[i].getTypeName().equals("WIFI") && allNetworkInfo[i].isConnected()) {
                return true;
            }
            i++;
        }
        return false;
    }

    public static boolean isNetworkAvailable(Context context) {
        if (!checkPermissions(context, "android.permission.INTERNET")) {
            return false;
        }
        if (checkPermissions(context, "android.permission.ACCESS_NETWORK_STATE")) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                return false;
            }
            return true;
        }
        printLog(" lost  permission", "lost----> android.permission.ACCESS_NETWORK_STATE");
        return false;
    }

    public static String getTime() {
        return Long.valueOf(System.currentTimeMillis()).toString();
    }

    public static String getAppKey(Context context) {
        if (context == null) {
            return "";
        }
        String str = "";
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                String string = applicationInfo.metaData.getString("HMT_APPKEY");
                if (!TextUtils.isEmpty(string)) {
                    try {
                        return string.toString();
                    } catch (Exception e) {
                        str = string;
                    }
                }
            }
        } catch (Exception e2) {
        }
        if (TextUtils.isEmpty(str)) {
            return (String) SPUtils.get(context, "manual_setting_appkey", (Object) "");
        }
        return str;
    }

    public static String getChannel(Context context) {
        if (HMTConstants.u != null) {
            return HMTConstants.u;
        }
        if (context == null) {
            return "";
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                String string = applicationInfo.metaData.getString("HMT_CHANNEL");
                if (string != null) {
                    return string.toString();
                }
            }
        } catch (Exception e) {
        }
        return "";
    }

    public static String getActivityName(Context context, int i) {
        String packName;
        if (context == null) {
            return "";
        }
        String obj;
        try {
            obj = context.toString();
            obj = obj.substring(0, obj.indexOf("@"));
            packName = HMTParamsInfo.getPackName(context);
            if (packName == null || packName.equals("") || i != 1) {
                return obj;
            }
            return obj.replaceFirst(packName, "");
        } catch (Exception e) {
            printLog("getActivityName", e.getMessage());
            if (checkPermissions(context, "android.permission.GET_TASKS")) {
                List runningTasks;
                try {
                    runningTasks = ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningTasks(1);
                } catch (NullPointerException e2) {
                    printLog("getActivityName", e2.getMessage());
                    runningTasks = null;
                }
                if (runningTasks != null && runningTasks.size() > 0) {
                    ComponentName componentName = ((RunningTaskInfo) runningTasks.get(0)).topActivity;
                    printLog("hmt", componentName.getClassName());
                    obj = componentName.getClassName();
                    packName = HMTParamsInfo.getPackName(context);
                    if (packName == null || packName.equals("") || i != 1) {
                        return obj;
                    }
                    return obj.replaceFirst(packName, "");
                }
            }
            return "";
        }
    }

    public static String getPackageName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getOsVersion(Context context) {
        String str = VERSION.RELEASE;
        if (str == null) {
            return "";
        }
        return str;
    }

    public static String getDeviceID(Context context) {
        if (context == null) {
            return "";
        }
        String str = "";
        String packageName = context.getPackageName();
        str = (String) SPUtils.get(context, HMTConstants.p + packageName, "hmt_device_id", "");
        if (checkDeviceIdValid(str)) {
            return str;
        }
        str = (String) SPUtils.get(context, HMTConstants.q, "hmt_device_id", "");
        if (checkDeviceIdValid(str)) {
            return str;
        }
        Object obj = get_imei(context);
        if (!checkImeiValid(obj)) {
            obj = "";
        }
        String str2 = get_mac(context);
        if (!checkMacValid(str2)) {
            str2 = "";
        }
        obj = new StringBuilder(String.valueOf(obj)).append(str2).toString();
        if (TextUtils.isEmpty(obj)) {
            str = getAndroidId(context);
            if (TextUtils.isEmpty(str) || str.equals("9774d56d682e549c") || str.length() < 15) {
                str = new BigInteger(64, new SecureRandom()).toString(16);
            }
        } else {
            str = MD5Utility.md5Appkey(obj);
        }
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        SPUtils.put(context, HMTConstants.p + packageName, "hmt_device_id", str);
        SPUtils.put(context, HMTConstants.q, "hmt_device_id", str);
        return str;
    }

    public static boolean checkMacValid(String str) {
        if (TextUtils.isEmpty(str) || str.equals("02:00:00:00:00:00") || str.equals("00:00:00:00:00:00") || str.equals("ff:ff:ff:ff:ff:ff")) {
            return false;
        }
        return true;
    }

    public static boolean checkImeiValid(String str) {
        if (TextUtils.isEmpty(str) || str.equals("000000000000000") || str.equals("00000000")) {
            return false;
        }
        return true;
    }

    public static boolean checkDeviceIdValid(String str) {
        if (TextUtils.isEmpty(str) || str.equals("0f607264fc6318a92b9e13c65db7cd3c") || str.equals("3f0fe74b555ff95d563a2cfe3cb9c834") || str.equals("5284047f4ffb4e04824a2fd1d1f0cd62") || str.equals("528c8e6cd4a3c6598999a0e9df15ad32") || str.equals("b21929f60cb26fe36e48926c33f1903c") || str.equals("dd4b21e9ef71e1291183a46b913ae6f2") || str.equals("feef34bbe6f4a1f343ad614c1b25f9b9")) {
            return false;
        }
        return true;
    }

    public static boolean checkPhoneState(Context context) {
        return checkPermissions(context, "android.permission.READ_PHONE_STATE");
    }

    public static String getSdkVersion(Context context) {
        String str = "";
        if (checkPhoneState(context)) {
            return null;
        }
        return VERSION.RELEASE;
    }

    public static String getCurVersion(Context context) {
        try {
            String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            if (str == null || str.length() <= 0) {
                return "";
            }
            return str;
        } catch (Exception e) {
            return "";
        }
    }

    public static int getReportPolicyMode(Context context) {
        int intValue = ((Integer) SPUtils.get(context, "hmt_agent_online_setting", "hmtlocal_report_policy_server", Integer.valueOf(Constants.ERRORCODE_UNKNOWN))).intValue();
        printLog("ReportPolicy", "server=======>" + intValue);
        if (intValue == Constants.ERRORCODE_UNKNOWN) {
            intValue = ((Integer) SPUtils.get(context, "hmt_agent_online_setting", "hmtlocal_report_policy_client", Integer.valueOf(Constants.ERRORCODE_UNKNOWN))).intValue();
        }
        printLog("ReportPolicy", "client=======>" + intValue);
        if (intValue == Constants.ERRORCODE_UNKNOWN) {
            return 0;
        }
        return intValue;
    }

    public static void setReportPolicy(Context context, int i, String str) {
        printLog("reportType", new StringBuilder(String.valueOf(i)).toString());
        if (i == 0 || i == 1) {
            synchronized (HMTConstants.d) {
                SPUtils.put(context, "hmt_agent_online_setting", "hmtlocal_report_policy_" + str, Integer.valueOf(i));
            }
        }
    }

    public static Boolean isUnTracked(String[] strArr, String str) {
        if (strArr != null) {
            for (String equals : strArr) {
                if (equals.equals(str)) {
                    return Boolean.valueOf(true);
                }
            }
        }
        return Boolean.valueOf(false);
    }

    public static void setUnTracked(Context context, String[] strArr, String str) {
        String str2 = "#";
        if (!str.equals("server")) {
            str = "client";
        }
        Object obj = "";
        if (strArr != null && strArr.length > 0) {
            int length = strArr.length;
            int i = 0;
            while (i < length) {
                i++;
                String stringBuilder = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(obj)).append(strArr[i]).toString())).append(str2).toString();
            }
        }
        printLog("unTrackedStr into hmtlocal_untracked_" + str, new StringBuilder(String.valueOf(obj)).toString());
        synchronized (HMTConstants.d) {
            SPUtils.put(context, "hmt_agent_online_setting", "hmtlocal_untracked_" + str, obj);
        }
    }

    public static String[] getUnTracked(Context context) {
        String str = "#";
        String str2 = (String) SPUtils.get(context, "hmt_agent_online_setting", "hmtlocal_untracked_server", "");
        if (TextUtils.isEmpty(str2)) {
            str2 = (String) SPUtils.get(context, "hmt_agent_online_setting", "hmtlocal_untracked_client", "");
        }
        if (str2 == null || str2 == "") {
            return null;
        }
        return str2.split(str);
    }

    public static SCell getCellInfo(Context context) throws Exception {
        SCell sCell = new SCell();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            return null;
        }
        int cid;
        if (!checkPermissions(context, "android.permission.ACCESS_COARSE_LOCATION")) {
            printLog("lost permission", "android.permission.ACCESS_COARSE_LOCATION");
        } else if (telephonyManager.getCellLocation() instanceof GsmCellLocation) {
            GsmCellLocation gsmCellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
            if (gsmCellLocation == null) {
                return null;
            }
            cid = gsmCellLocation.getCid();
            sCell.d = gsmCellLocation.getLac();
            sCell.e = cid;
        } else if (telephonyManager.getCellLocation() instanceof CdmaCellLocation) {
            CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) telephonyManager.getCellLocation();
            if (cdmaCellLocation == null) {
                return null;
            }
            cid = cdmaCellLocation.getBaseStationId();
            sCell.d = cdmaCellLocation.getNetworkId();
            sCell.e = cid;
        }
        String networkOperator = telephonyManager.getNetworkOperator();
        if (!(networkOperator == null || networkOperator.equals(""))) {
            int parseInt = Integer.parseInt(networkOperator.substring(0, 3));
            cid = Integer.parseInt(networkOperator.substring(3));
            sCell.a = parseInt;
            sCell.c = cid;
            sCell.b = Integer.parseInt(networkOperator);
        }
        return sCell;
    }

    public static LatitudeAndLongitude getLatitudeAndLongitude(Context context, boolean z) {
        LatitudeAndLongitude latitudeAndLongitude = new LatitudeAndLongitude();
        latitudeAndLongitude.a = "";
        latitudeAndLongitude.b = "";
        if (z) {
            try {
                LocationManager locationManager = (LocationManager) context.getSystemService("location");
                if (locationManager != null) {
                    for (String str : locationManager.getAllProviders()) {
                        printLog("message", str);
                        if (checkPermissions(context, "android.permission.ACCESS_FINE_LOCATION")) {
                            Location lastKnownLocation = locationManager.getLastKnownLocation(str);
                            if (lastKnownLocation != null) {
                                latitudeAndLongitude.a = new StringBuilder(String.valueOf(lastKnownLocation.getLatitude())).toString();
                                latitudeAndLongitude.b = new StringBuilder(String.valueOf(lastKnownLocation.getLongitude())).toString();
                                break;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                printLog(a, e.getMessage());
            }
        }
        return latitudeAndLongitude;
    }

    public static boolean isHaveGravity(Context context) {
        if (!isDevice()) {
            return false;
        }
        SensorManager sensorManager;
        try {
            sensorManager = (SensorManager) context.getSystemService("sensor");
        } catch (NoSuchFieldError e) {
            printLog("hmt_isHaveGravity", e.getMessage());
            sensorManager = null;
        }
        if (sensorManager == null) {
            return false;
        }
        return true;
    }

    public static boolean isDevice() {
        try {
            if ("sdk".equals(Build.MODEL) || "sdk".equals(Build.PRODUCT) || "generic".equals(Build.DEVICE)) {
                return false;
            }
            return true;
        } catch (NoSuchFieldError e) {
            if ("sdk".equals(Build.MODEL) || "sdk".equals(Build.PRODUCT)) {
                return false;
            }
            return true;
        }
    }

    public static String getNetworkType(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            return "";
        }
        int networkType = telephonyManager.getNetworkType();
        String str = "UNKNOWN";
        if (networkType == 4) {
            str = "CDMA";
        }
        if (networkType == 2) {
            str = "EDGE";
        }
        if (networkType == 5) {
            str = "EVDO_0";
        }
        if (networkType == 6) {
            str = "EVDO_A";
        }
        if (networkType == 1) {
            str = "GPRS";
        }
        if (networkType == 8) {
            str = "HSDPA";
        }
        if (networkType == 10) {
            str = "HSPA";
        }
        if (networkType == 9) {
            str = "HSUPA";
        }
        if (networkType == 3) {
            str = "UMTS";
        }
        if (networkType == 0) {
            str = "UNKNOWN";
        }
        if (networkType == 7) {
            str = "1xRTT";
        }
        if (networkType == 11) {
            str = "iDen";
        }
        if (networkType == 12) {
            str = "EVDO_B";
        }
        if (networkType == 13) {
            str = "LTE";
        }
        if (networkType == 14) {
            str = "eHRPD";
        }
        if (networkType == 15) {
            return "HSPA+";
        }
        return str;
    }

    public static boolean isNetworkTypeWifi(Context context) {
        if (!checkPermissions(context, "android.permission.INTERNET")) {
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getTypeName().equals("WIFI");
    }

    public static String getAppVersion(Context context) {
        String str = "";
        if (context == null) {
            return "";
        }
        try {
            str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            if (str == null || str.length() <= 0) {
                return "";
            }
            return str;
        } catch (Exception e) {
            return str;
        }
    }

    public static void printLog(String str, String str2) {
    }

    public static String getNetworkTypeWIFI2G3G(Context context) {
        return CommonUtillNetwork.getNetwork(context);
    }

    public static String getDeviceName() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        if (str2.startsWith(str)) {
            return capitalize(str2);
        }
        return capitalize(str) + " " + str2;
    }

    private static String capitalize(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        char charAt = str.charAt(0);
        return !Character.isUpperCase(charAt) ? Character.toUpperCase(charAt) + str.substring(1) : str;
    }

    public static boolean isRoot() {
        String[] strArr = new String[]{"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        int i = 0;
        while (i < strArr.length) {
            try {
                File file = new File(strArr[i] + "su");
                if (file != null && file.exists()) {
                    return true;
                }
                i++;
            } catch (Exception e) {
            }
        }
        return false;
    }

    public static String getAndroidId(Context context) {
        String string = Secure.getString(context.getContentResolver(), "android_id");
        if (string == null) {
            return "";
        }
        return string;
    }

    public static String getOpenUdId(Context context) {
        if (!OpenUDID_manager_hmt.isInitialized()) {
            OpenUDID_manager_hmt.sync(context);
        }
        String openUDID = OpenUDID_manager_hmt.getOpenUDID();
        if (openUDID == null) {
            return "";
        }
        return openUDID;
    }

    public static String getUseragent(Context context) {
        PackageManager packageManager;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getApplicationContext().getPackageManager();
            try {
                applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
            } catch (NameNotFoundException e) {
            }
        } catch (NameNotFoundException e2) {
            Object obj = applicationInfo;
        }
        return new StringBuilder(String.valueOf((String) packageManager.getApplicationLabel(applicationInfo))).append("_").append(getAppVersion(context)).toString();
    }

    public static String getV() {
        return HMTConstants.i;
    }

    public static String getSV() {
        return HMTConstants.j;
    }

    public static String getSD() {
        return "";
    }

    public static String getChar() {
        return "";
    }

    public static String getAppCode(Context context) {
        int i = 0;
        if (context == null) {
            return "";
        }
        try {
            i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e) {
        }
        return String.valueOf(i);
    }

    public static String getAaid(Context context) {
        return "";
    }

    public static String getAppName(Context context) {
        String str = "";
        if (context == null) {
            return "";
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            return packageManager.getPackageInfo(context.getPackageName(), 0).applicationInfo.loadLabel(packageManager).toString();
        } catch (Exception e) {
            return str;
        }
    }

    public static boolean isSetWebViewClient(WebView webView) {
        int parseInt = Integer.parseInt(VERSION.SDK);
        if (parseInt <= 15) {
            return isSetWebViewClient15(webView);
        }
        if (parseInt <= 18) {
            return isSetWebViewClient18(webView);
        }
        return isSetWebViewClient19(webView);
    }

    public static boolean isSetWebViewClient19(WebView webView) {
        try {
            Object obj = webView.getClass().getDeclaredField("mProvider").get(webView);
            if (obj != null) {
                obj = obj.getClass().getDeclaredField("mContentsClientAdapter").get(obj);
                if (obj != null) {
                    obj = obj.getClass().getDeclaredField("mWebViewClient").get(obj);
                    if (obj != null && obj.toString().contains("com.android.webview.chromium.WebViewContentsClientAdapter$NullWebViewClient")) {
                        return false;
                    }
                }
            }
        } catch (NoSuchFieldException e) {
            printLog("WebViewException", e.toString());
        } catch (IllegalArgumentException e2) {
            printLog("WebViewException", e2.toString());
        } catch (IllegalAccessException e3) {
            printLog("WebViewException", e3.toString());
        }
        return true;
    }

    public static boolean isSetWebViewClient18(WebView webView) {
        try {
            Object invoke = webView.getClass().getMethod("getWebViewProvider", new Class[0]).invoke(webView, new Object[0]);
            if (invoke != null && invoke.getClass().getMethod("getWebViewClient", new Class[0]).invoke(invoke, new Object[0]) == null) {
                return false;
            }
        } catch (IllegalArgumentException e) {
            printLog("WebViewException", e.toString());
        } catch (IllegalAccessException e2) {
            printLog("WebViewException", e2.toString());
        } catch (NoSuchMethodException e3) {
            printLog("WebViewException", e3.toString());
        } catch (InvocationTargetException e4) {
            printLog("WebViewException", e4.toString());
        }
        return true;
    }

    public static boolean isSetWebViewClient15(WebView webView) {
        try {
            if (Class.forName("android.webkit.WebView").getMethod("getWebViewClient", new Class[0]).invoke(webView, new Object[0]) == null) {
                return false;
            }
        } catch (ClassNotFoundException e) {
            printLog("WebViewException", e.toString());
        } catch (NoSuchMethodException e2) {
            printLog("WebViewException", e2.toString());
        } catch (IllegalArgumentException e3) {
            printLog("WebViewException", e3.toString());
        } catch (IllegalAccessException e4) {
            printLog("WebViewException", e4.toString());
        } catch (InvocationTargetException e5) {
            printLog("WebViewException", e5.toString());
        }
        return true;
    }

    public static void setManualIMEI(Context context, String str) {
        Object obj;
        if (TextUtils.isEmpty(str)) {
            obj = "";
        }
        SPUtils.put(context, "manual_setting_imei", obj);
    }

    public static String getManualIMEI(Context context) {
        return (String) SPUtils.get(context, "manual_setting_imei", (Object) "");
    }

    public static String get_imei(Context context) {
        String manualIMEI = getManualIMEI(context);
        if (!TextUtils.isEmpty(manualIMEI)) {
            return manualIMEI;
        }
        String deviceId;
        if (checkPhoneState(context)) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null) {
                return "";
            }
            try {
                deviceId = telephonyManager.getDeviceId();
            } catch (Exception e) {
                printLog("get_imei lost permission", "Exception");
                deviceId = manualIMEI;
            }
        } else {
            printLog("get_imei lost permission", "android.permission.READ_PHONE_STATE");
            deviceId = manualIMEI;
        }
        if (deviceId == null) {
            return "";
        }
        return deviceId;
    }

    public static String get_mac(Context context) {
        String str = "";
        if (checkPermissions(context, "android.permission.ACCESS_WIFI_STATE")) {
            try {
                WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                if (wifiManager == null) {
                    return "";
                }
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo == null) {
                    return "";
                }
                str = connectionInfo.getMacAddress();
                if (str == null) {
                    return "";
                }
                return str;
            } catch (Exception e) {
                printLog("hmt", e.getMessage());
                return "";
            }
        }
        printLog("get_mac lost permission", "android.permission.ACCESS_WIFI_STATE");
        return str;
    }

    public static int getPhoneType(Context context) {
        if (checkPhoneState(context)) {
            return ((TelephonyManager) context.getSystemService("phone")).getPhoneType();
        }
        printLog("lost permission", "android.permission.READ_PHONE_STATE");
        return 0;
    }

    public static String getImsi(Context context) {
        String str = null;
        if (checkPhoneState(context)) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null) {
                return "";
            }
            str = telephonyManager.getSubscriberId();
        } else {
            printLog("lost permission", "android.permission.READ_PHONE_STATE");
        }
        if (str == null) {
            return "";
        }
        return str;
    }

    public static String getImei(Context context) {
        String str = get_imei(context);
        if (str == null || str.equals("")) {
            return "";
        }
        return MD5Utility.md5Appkey(str);
    }

    public static String getAndroidIdMd5(Context context) {
        String androidId = getAndroidId(context);
        String str = "";
        if (androidId.equals("")) {
            return str;
        }
        return MD5Utility.md5Appkey(androidId);
    }

    public static String getMac(Context context) {
        String str = get_mac(context);
        String str2 = "";
        if (str == null || str.equals("")) {
            return str2;
        }
        return MD5Utility.md5Appkey(str.replace(":", "").toUpperCase());
    }

    public static String getMac1(Context context) {
        String str = get_mac(context);
        String str2 = "";
        if (str == null || str.equals("")) {
            return str2;
        }
        return MD5Utility.md5Appkey(str.toUpperCase());
    }
}
