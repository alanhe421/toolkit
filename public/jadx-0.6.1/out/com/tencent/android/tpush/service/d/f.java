package com.tencent.android.tpush.service.d;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.common.p;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.service.XGPushService;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import com.tencent.android.tpush.service.m;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.conn.util.InetAddressUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class f {
    private static int a = -1;
    private static String b = null;

    public static String a() {
        try {
            return TpnsSecurity.generateLocalSocketServieName(m.e());
        } catch (Throwable e) {
            a.c(Constants.ServiceLogTag, "getSocketName", e);
            return null;
        }
    }

    public static List a(Context context) {
        if (context != null) {
            try {
                Map hashMap = new HashMap();
                PackageManager packageManager = context.getPackageManager();
                List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(new Intent("android.intent.action"), 32);
                queryIntentActivities.addAll(packageManager.queryIntentActivities(new Intent(""), 32));
                queryIntentActivities.addAll(packageManager.queryBroadcastReceivers(new Intent(Constants.ACTION_SDK_INSTALL), 512));
                for (ResolveInfo resolveInfo : queryIntentActivities) {
                    hashMap.put(resolveInfo.activityInfo.applicationInfo.packageName, resolveInfo);
                }
                return new ArrayList(hashMap.values());
            } catch (Throwable e) {
                a.c(Constants.ServiceLogTag, "getLocalPushAppsInfo", e);
            }
        }
        return null;
    }

    public static void b(Context context) {
        try {
            List<ResolveInfo> a = a(context);
            if (a != null) {
                for (ResolveInfo resolveInfo : a) {
                    String str = resolveInfo.activityInfo.applicationInfo.packageName;
                    if (!(a(str) || context.getPackageName().equals(str))) {
                        List a2 = a(m.e(), str + Constants.RPC_SUFFIX);
                        if (a2 != null || a2.size() > 0) {
                            Intent intent = new Intent();
                            intent.setAction(str + Constants.RPC_SUFFIX);
                            intent.setPackage(str);
                            context.startService(intent);
                        }
                    }
                }
            }
        } catch (Throwable th) {
        }
    }

    public static List a(Context context, String str) {
        List list = null;
        if (context != null) {
            try {
                list = context.getPackageManager().queryIntentServices(new Intent(str), 512);
            } catch (Throwable e) {
                a.c(Constants.ServiceLogTag, "getLocalPushServicesInfo", e);
            }
        } else {
            a.h(Constants.ServiceLogTag, "getLocalPushServicesInfo the context == null");
        }
        return list;
    }

    public static boolean b(Context context, String str) {
        if (p.b(str)) {
            return false;
        }
        if (context != null) {
            try {
                List<ResolveInfo> a = a(context);
                if (a != null) {
                    for (ResolveInfo resolveInfo : a) {
                        if (str.equals(resolveInfo.activityInfo.packageName)) {
                            return true;
                        }
                    }
                }
            } catch (Throwable e) {
                a.c(Constants.ServiceLogTag, "isLocalApp", e);
            }
        }
        return false;
    }

    public static boolean c(Context context, String str) {
        if (p.b(str) || context == null) {
            return false;
        }
        try {
            List a = a(context, str + Constants.RPC_SUFFIX);
            if (a == null || a.size() > 0) {
                return true;
            }
            return false;
        } catch (Throwable e) {
            a.c(Constants.ServiceLogTag, "isPkgHasRemoteService", e);
            return false;
        }
    }

    public static boolean a(Context context, String str, long j) {
        return a(context, str, j, false);
    }

    private static boolean a(Context context, String str, long j, boolean z) {
        boolean z2 = false;
        PackageManager packageManager = context.getPackageManager();
        try {
            packageManager.getPackageInfo(str, 0);
            z2 = true;
        } catch (Throwable e) {
            if (b(context, str) || c(context, str)) {
                return true;
            }
            if (z) {
                try {
                    boolean z3;
                    List<com.tencent.android.tpush.data.a> registerInfo = CacheManager.getRegisterInfo(context);
                    if (registerInfo != null) {
                        for (com.tencent.android.tpush.data.a aVar : registerInfo) {
                            if (aVar.a == j) {
                                try {
                                    packageManager.getPackageInfo(aVar.d, 0);
                                    z3 = true;
                                    break;
                                } catch (Exception e2) {
                                }
                            }
                        }
                    }
                    z3 = false;
                    z2 = z3;
                } catch (Exception e3) {
                    a.c(Constants.ServiceLogTag, "isAppInstalled", e);
                }
            }
        }
        return z2;
    }

    public static boolean d(Context context, String str) {
        if (context != null) {
            List<String> registerInfos = CacheManager.getRegisterInfos(context);
            if (registerInfos != null) {
                for (String equals : registerInfos) {
                    if (equals.equals(str) && !context.getPackageName().equals(str)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean c(Context context) {
        if (context != null) {
            return a(context, Constants.SETTINGS_SERVICE_PACKAGE_NAME, Rijndael.encrypt(context.getPackageName()), false);
        }
        return false;
    }

    public static boolean a(String str) {
        return str == null || str.length() == 0 || str.trim().length() == 0;
    }

    public static boolean b() {
        try {
            return "mounted".equals(Environment.getExternalStorageState());
        } catch (Throwable e) {
            a.c(Constants.ServiceLogTag, "isSDCardMounted", e);
            return false;
        }
    }

    public static int c() {
        return VERSION.SDK_INT;
    }

    public static String d() {
        return Build.MODEL;
    }

    public static String d(Context context) {
        if (context != null) {
            try {
                return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            } catch (Throwable e) {
                a.c("Util", ">>get imei err", e);
            }
        }
        return "";
    }

    public static boolean e(Context context) {
        List registerInfos = CacheManager.getRegisterInfos(context);
        return registerInfos != null && registerInfos.size() > 0;
    }

    public static byte f(Context context) {
        if (context != null) {
            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager == null) {
                    return (byte) 0;
                }
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo == null) {
                    return (byte) -1;
                }
                if (!activeNetworkInfo.isAvailable() || !activeNetworkInfo.isConnected()) {
                    return (byte) -1;
                }
                if (activeNetworkInfo.getType() == 1) {
                    return (byte) 1;
                }
                if (activeNetworkInfo.getType() != 0) {
                    return (byte) 0;
                }
                switch (activeNetworkInfo.getSubtype()) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        return (byte) 2;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 15:
                        return (byte) 3;
                    case 13:
                        return (byte) 4;
                    default:
                        return (byte) 0;
                }
            } catch (Throwable e) {
                a.c(Constants.ServiceLogTag, "getNetworkType", e);
            }
        }
        return (byte) -1;
    }

    public static byte g(Context context) {
        if (context == null) {
            return (byte) 0;
        }
        try {
            byte b;
            String simOperator = ((TelephonyManager) context.getSystemService("phone")).getSimOperator();
            if (simOperator != null) {
                if (simOperator.equals("46000") || simOperator.equals("46002") || simOperator.equals("46007") || simOperator.equals("46020")) {
                    b = (byte) 3;
                    return b;
                } else if (simOperator.equals("46001") || simOperator.equals("46006")) {
                    b = (byte) 2;
                    return b;
                } else if (simOperator.equals("46003") || simOperator.equals("46005")) {
                    b = (byte) 1;
                    return b;
                }
            }
            b = (byte) 0;
            return b;
        } catch (Throwable e) {
            a.c(Constants.ServiceLogTag, "getIsp", e);
            return (byte) 0;
        }
    }

    public static String h(Context context) {
        if (context != null) {
            try {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) {
                    return "" + g(context) + f(context);
                }
                return i(context);
            } catch (Throwable e) {
                a.c(Constants.ServiceLogTag, "getKey", e);
            }
        }
        return "";
    }

    public static String i(Context context) {
        String j = j(context);
        if (j == null || j.equals("0")) {
            return e();
        }
        return j;
    }

    public static String e() {
        try {
            if (NetworkInterface.getNetworkInterfaces() == null) {
                return "0";
            }
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                Iterator it2 = Collections.list(((NetworkInterface) it.next()).getInetAddresses()).iterator();
                while (it2.hasNext()) {
                    InetAddress inetAddress = (InetAddress) it2.next();
                    if (!inetAddress.isLoopbackAddress()) {
                        String hostAddress = inetAddress.getHostAddress();
                        if (InetAddressUtils.isIPv4Address(hostAddress)) {
                            return hostAddress;
                        }
                    }
                }
            }
            return "0";
        } catch (Throwable e) {
            a.c(Constants.ServiceLogTag, "getLocalIpAddress", e);
        }
    }

    public static String j(Context context) {
        try {
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo == null) {
                return "0";
            }
            return connectionInfo.getBSSID();
        } catch (Throwable th) {
            a.c(Constants.ServiceLogTag, "getRouteMac", th);
            return "0";
        }
    }

    public static long b(String str) {
        if (str == null || str.equals("0")) {
            return 0;
        }
        String trim = str.trim();
        long[] jArr = new long[4];
        int indexOf = trim.indexOf(".");
        int indexOf2 = trim.indexOf(".", indexOf + 1);
        int indexOf3 = trim.indexOf(".", indexOf2 + 1);
        try {
            jArr[3] = Long.parseLong(trim.substring(0, indexOf));
            jArr[2] = Long.parseLong(trim.substring(indexOf + 1, indexOf2));
            jArr[1] = Long.parseLong(trim.substring(indexOf2 + 1, indexOf3));
            jArr[0] = Long.parseLong(trim.substring(indexOf3 + 1));
        } catch (Throwable th) {
            jArr[4] = 0;
            jArr[3] = 0;
            jArr[2] = 0;
            jArr[0] = 0;
            a.c(Constants.LogTag, "service Util@@parseIpAddress(" + trim + ")", th);
        }
        return (((jArr[0] << 24) + (jArr[1] << 16)) + (jArr[2] << 8)) + jArr[3];
    }

    public static String a(long j) {
        StringBuffer stringBuffer = new StringBuffer("");
        stringBuffer.append(String.valueOf(255 & j));
        stringBuffer.append(".");
        stringBuffer.append(String.valueOf((65535 & j) >>> 8));
        stringBuffer.append(".");
        stringBuffer.append(String.valueOf((16777215 & j) >>> 16));
        stringBuffer.append(".");
        stringBuffer.append(String.valueOf(j >>> 24));
        return stringBuffer.toString();
    }

    public static String c(String str) {
        if (m.e() != null) {
            try {
                return TpnsSecurity.getEncryptAPKSignature(m.e().createPackageContext(str, 0));
            } catch (Throwable e) {
                a.c(Constants.LogTag, "+++ getAppCert exception.", e);
            }
        }
        return "";
    }

    public static Intent a(int i, String str, int i2) {
        Intent intent = new Intent(Constants.ACTION_FEEDBACK);
        if (!(str == null || str.length() == 0)) {
            intent.setPackage(str);
        }
        intent.putExtra(Constants.FEEDBACK_TAG, i2);
        intent.putExtra(Constants.FEEDBACK_ERROR_CODE, i);
        return intent;
    }

    public static boolean a(Intent intent) {
        try {
            JSONObject jSONObject = new JSONObject(Rijndael.decrypt(intent.getStringExtra(MessageKey.MSG_CONTENT)));
            if (jSONObject.isNull(MessageKey.MSG_ACCEPT_TIME)) {
                return true;
            }
            String string = jSONObject.getString(MessageKey.MSG_ACCEPT_TIME);
            JSONArray jSONArray = new JSONArray(string);
            if (jSONArray.length() == 0) {
                return true;
            }
            Calendar instance = Calendar.getInstance();
            long longExtra = intent.getLongExtra(MessageKey.MSG_SERVER_TIME, 0);
            long longExtra2 = intent.getLongExtra(MessageKey.MSG_TIME_GAP, 0);
            if (!(longExtra == 0 || longExtra2 == 0 || longExtra != 0)) {
                instance.setTimeInMillis(System.currentTimeMillis() - longExtra2);
            }
            int i = (instance.get(11) * 60) + instance.get(12);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject2 = new JSONObject(jSONArray.getString(i2));
                JSONObject jSONObject3 = new JSONObject(jSONObject2.getString(MessageKey.MSG_ACCEPT_TIME_START));
                int intValue = Integer.valueOf(jSONObject3.getString(MessageKey.MSG_ACCEPT_TIME_MIN)).intValue() + (Integer.valueOf(jSONObject3.getString(MessageKey.MSG_ACCEPT_TIME_HOUR)).intValue() * 60);
                JSONObject jSONObject4 = new JSONObject(jSONObject2.getString(MessageKey.MSG_ACCEPT_TIME_END));
                int intValue2 = (Integer.valueOf(jSONObject4.getString(MessageKey.MSG_ACCEPT_TIME_HOUR)).intValue() * 60) + Integer.valueOf(jSONObject4.getString(MessageKey.MSG_ACCEPT_TIME_MIN)).intValue();
                if (intValue <= i && i <= intValue2) {
                    return true;
                }
            }
            a.h("Utils", " discurd the msg due to time not accepted! acceptTime = " + string + " , curTime= " + i);
            return false;
        } catch (Throwable th) {
            a.c(Constants.ServiceLogTag, "checkAcceptTime", th);
            return true;
        }
    }

    public static String a(Context context, long j) {
        String str = "" + com.tencent.android.tpush.common.m.a(context, "tpush_msgId_" + j, "");
        if (str == null || str.trim().length() == 0) {
            str = a(context, "tpush_msgId_" + j, true);
        }
        if (str != null && str.length() > 20480) {
            str = str.substring(0, str.indexOf("@@", 5120));
        }
        return str != null ? str : "";
    }

    private static String d(String str) {
        return com.tencent.android.tpush.encrypt.a.a(str);
    }

    public static boolean a(Context context, String str, String str2, boolean z) {
        if (z) {
            try {
                String str3 = (String) com.tencent.android.tpush.service.cache.a.a(str);
                if (str3 != null && str2 != null && str3.equals(str2)) {
                    return true;
                }
                com.tencent.android.tpush.service.cache.a.a(str, str2);
            } catch (Throwable e) {
                a.c(Constants.ServiceLogTag, "putString", e);
                return false;
            }
        }
        return com.tencent.android.tpush.service.channel.c.f.a(context).a(d(str), str2);
    }

    public static String a(Context context, String str, boolean z) {
        if (!z) {
            return com.tencent.android.tpush.service.channel.c.f.a(context).a(d(str));
        }
        try {
            String str2 = (String) com.tencent.android.tpush.service.cache.a.a(str);
            if (str2 != null) {
                return str2;
            }
            str2 = com.tencent.android.tpush.service.channel.c.f.a(context).a(d(str));
            com.tencent.android.tpush.service.cache.a.a(str, str2);
            return str2;
        } catch (Throwable e) {
            a.c(Constants.ServiceLogTag, "getString", e);
            return "";
        }
    }

    public static boolean a(Context context, String str, float f) {
        try {
            return com.tencent.android.tpush.service.channel.c.f.a(context).a(d(str), f);
        } catch (Throwable e) {
            a.c(Constants.ServiceLogTag, "putFloat", e);
            return false;
        }
    }

    public static float b(Context context, String str, float f) {
        try {
            return com.tencent.android.tpush.service.channel.c.f.a(context).b(d(str), f);
        } catch (Throwable e) {
            a.c(Constants.ServiceLogTag, "getFloat", e);
            return 0.0f;
        }
    }

    public static boolean b(Context context, String str, long j) {
        try {
            com.tencent.android.tpush.service.channel.c.f.a(context).a(d(str), j);
        } catch (Throwable e) {
            a.c(Constants.ServiceLogTag, "putLong", e);
        }
        return false;
    }

    public static long c(Context context, String str, long j) {
        try {
            return com.tencent.android.tpush.service.channel.c.f.a(context).b(d(str), j);
        } catch (Throwable e) {
            a.c(Constants.ServiceLogTag, "getLong", e);
            return 0;
        }
    }

    public static boolean a(Context context, String str, int i) {
        try {
            return com.tencent.android.tpush.service.channel.c.f.a(context).a(d(str), i);
        } catch (Throwable e) {
            a.c(Constants.ServiceLogTag, "putInt", e);
            return false;
        }
    }

    public static int b(Context context, String str, int i) {
        try {
            return com.tencent.android.tpush.service.channel.c.f.a(context).b(d(str), i);
        } catch (Throwable e) {
            a.c(Constants.ServiceLogTag, "getInt", e);
            return 0;
        }
    }

    public static String k(Context context) {
        if (context == null) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
        if (resolveActivity == null || resolveActivity.activityInfo == null || resolveActivity.activityInfo.packageName.equals("android")) {
            return null;
        }
        return resolveActivity.activityInfo.packageName;
    }

    public static int l(Context context) {
        if (a != -1) {
            return a;
        }
        try {
            if (g.a()) {
                a = 1;
            }
        } catch (Throwable th) {
        }
        a = 0;
        return a;
    }

    private static Map r(Context context) {
        Map hashMap = new HashMap();
        for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses()) {
            String[] strArr = runningAppProcessInfo.pkgList;
            for (Object put : strArr) {
                hashMap.put(put, runningAppProcessInfo);
            }
        }
        return hashMap;
    }

    private static boolean e(String str) {
        if (a(str)) {
            return false;
        }
        String toLowerCase = str.toLowerCase();
        if (toLowerCase.contains(".lbe.")) {
            return true;
        }
        if (toLowerCase.contains(".qihoo360.")) {
            return true;
        }
        if (toLowerCase.contains("jinshan.")) {
            return true;
        }
        if (toLowerCase.contains(".qqpimsecure")) {
            return true;
        }
        if (toLowerCase.contains(".phonoalbumshoushou")) {
            return true;
        }
        if (toLowerCase.contains(".netqin.")) {
            return true;
        }
        if (toLowerCase.contains(".kms.")) {
            return true;
        }
        if (toLowerCase.contains(".avg.")) {
            return true;
        }
        if (toLowerCase.contains(".am321.")) {
            return true;
        }
        if (toLowerCase.contains("safe")) {
            return true;
        }
        if (toLowerCase.contains("security")) {
            return true;
        }
        if (toLowerCase.contains("clean")) {
            return true;
        }
        return false;
    }

    public static JSONArray m(Context context) {
        JSONArray jSONArray = new JSONArray();
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                Map r = r(context);
                List<ResolveInfo> a = a(context);
                Map hashMap = new HashMap();
                if (a != null && a.size() > 0) {
                    for (ResolveInfo resolveInfo : a) {
                        if (resolveInfo.activityInfo != null) {
                            hashMap.put(resolveInfo.activityInfo.packageName, Integer.valueOf(1));
                        }
                    }
                }
                for (PackageInfo packageInfo : packageManager.getInstalledPackages(0)) {
                    JSONObject jSONObject = new JSONObject();
                    ApplicationInfo applicationInfo = packageInfo.applicationInfo;
                    if (r.containsKey(applicationInfo.packageName) || hashMap.containsKey(applicationInfo.packageName)) {
                        if ((packageInfo.applicationInfo.flags & 1) != 0) {
                            if (e(applicationInfo.packageName)) {
                                jSONObject.put("s", "1");
                            }
                        }
                        String charSequence = packageManager.getApplicationLabel(packageInfo.applicationInfo).toString();
                        if (charSequence != null) {
                            jSONObject.put("n", charSequence);
                        }
                        if (applicationInfo.packageName != null) {
                            jSONObject.put("p", applicationInfo.packageName);
                        }
                        if (packageInfo.versionName != null) {
                            jSONObject.put("v", packageInfo.versionName);
                        }
                        if (r.containsKey(applicationInfo.packageName)) {
                            jSONObject.put("r", "1");
                        }
                        if (hashMap.containsKey(applicationInfo.packageName)) {
                            jSONObject.put("xg", "1");
                        }
                        jSONArray.put(jSONObject);
                    }
                }
            }
        } catch (Throwable th) {
            a.c(Constants.LogTag, "failed to get app.", th);
        }
        return jSONArray;
    }

    public static String a(String str, int i) {
        int length = str.length();
        if (length < i) {
            for (int i2 = 0; i2 < i - length; i2++) {
                str = str + " ";
            }
        }
        return str;
    }

    public static boolean f() {
        try {
            boolean equals = Environment.getExternalStorageState().equals("mounted");
            if (equals) {
                return equals;
            }
            a.c(Constants.ServiceLogTag, "SDCard is not mounted");
            return equals;
        } catch (Throwable e) {
            a.c(Constants.ServiceLogTag, "SDCard is not mounted", e);
            return false;
        }
    }

    public static boolean n(Context context) {
        try {
            ApplicationInfo o = o(context);
            if (o == null) {
                a.i(Constants.LogTag, "Failed to init due to null ApplicationInfo.");
                return false;
            } else if (o.icon != 0) {
                return true;
            } else {
                a.i(Constants.LogTag, "Failed to get Application icon in AndroidManifest.xml, You App maybe can not show notification, Please add Application icon in AndroidManifest.xml");
                return false;
            }
        } catch (Throwable th) {
            return false;
        }
    }

    public static ApplicationInfo o(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
        } catch (Throwable e) {
            a.d(Constants.LogTag, "Failed to get Application info", e);
            return null;
        }
    }

    public static boolean d(Context context, String str, long j) {
        return a(context, str, j, false);
    }

    public static String p(Context context) {
        if (TextUtils.isEmpty(b)) {
            int myPid = Process.myPid();
            for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses()) {
                if (myPid == runningAppProcessInfo.pid) {
                    b = runningAppProcessInfo.processName;
                    break;
                }
            }
        }
        return b;
    }

    public static void q(Context context) {
        try {
            if (!p(context).endsWith(":xg_service_v2")) {
                return;
            }
            if ("huawei".equalsIgnoreCase(Build.MANUFACTURER)) {
                XGPushService.a().stopSelf();
            } else {
                Process.killProcess(Process.myPid());
            }
        } catch (Throwable th) {
        }
    }
}
