package com.tencent.mid.util;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.tencent.android.tpush.common.Constants;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.List;
import org.apache.http.conn.util.InetAddressUtils;
import org.json.JSONObject;

public class j {
    private static String a = null;
    private static String b = null;
    private static String c = null;
    private static f d = Util.getLogger();
    private static String e = null;
    private static k f = null;
    private static m g = null;

    public static String a() {
        long b = b() / 1000000;
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return String.valueOf((((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / 1000000) + "/" + String.valueOf(b);
    }

    public static String a(Context context) {
        if (c == null || "" == c) {
            c = b(context);
        }
        return c;
    }

    public static long b() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
    }

    public static String b(Context context) {
        try {
            if (Util.checkPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
                Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaces.hasMoreElements()) {
                    Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            String hostAddress = inetAddress.getHostAddress();
                            if (InetAddressUtils.isIPv4Address(hostAddress)) {
                                return hostAddress;
                            }
                        }
                    }
                }
                return "";
            }
            d.c("Can not get the permission of android.permission.ACCESS_WIFI_STATE");
            return "";
        } catch (Exception e) {
            d.b(e);
        }
    }

    public static DisplayMetrics c(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    private static long d() {
        BufferedReader bufferedReader;
        Throwable th;
        long j = 0;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            try {
                j = (long) (Integer.valueOf(bufferedReader.readLine().split("\\s+")[1]).intValue() * 1024);
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception e) {
                    }
                }
            } catch (IOException e2) {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception e3) {
                    }
                }
                return j;
            } catch (Throwable th2) {
                th = th2;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception e4) {
                    }
                }
                throw th;
            }
        } catch (IOException e5) {
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return j;
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
        return j;
    }

    public static String d(Context context) {
        try {
            if (!Util.checkPermission(context, "android.permission.READ_PHONE_STATE")) {
                d.f("Could not get permission of android.permission.READ_PHONE_STATE");
                return null;
            } else if (!f(context)) {
                return null;
            } else {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                return telephonyManager != null ? telephonyManager.getSimOperator() : null;
            }
        } catch (Throwable th) {
            d.f(th);
            return null;
        }
    }

    public static String e(Context context) {
        String str;
        Object th;
        String str2 = "";
        try {
            str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            if (str != null) {
                return str;
            }
            try {
                return "";
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            str = str2;
            th = th4;
            d.f(th);
            return str;
        }
    }

    public static boolean f(Context context) {
        return context.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", context.getPackageName()) == 0;
    }

    public static String g(Context context) {
        try {
            if (Util.checkPermission(context, "android.permission.INTERNET") && Util.checkPermission(context, "android.permission.ACCESS_NETWORK_STATE")) {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                    String typeName = activeNetworkInfo.getTypeName();
                    String extraInfo = activeNetworkInfo.getExtraInfo();
                    if (typeName != null) {
                        return typeName.equalsIgnoreCase("WIFI") ? "WIFI" : typeName.equalsIgnoreCase("MOBILE") ? extraInfo == null ? "MOBILE" : extraInfo : extraInfo == null ? typeName : extraInfo;
                    }
                }
                return null;
            }
            d.f("can not get the permission of android.permission.ACCESS_WIFI_STATE");
            return null;
        } catch (Throwable th) {
            d.f(th);
        }
    }

    public static Integer h(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                return Integer.valueOf(telephonyManager.getNetworkType());
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public static int i(Context context) {
        try {
            if (n.a()) {
                return 1;
            }
        } catch (Throwable th) {
            d.f(th);
        }
        return 0;
    }

    public static String j(Context context) {
        try {
            if (Util.checkPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                String externalStorageState = Environment.getExternalStorageState();
                if (externalStorageState == null || !externalStorageState.equals("mounted")) {
                    return null;
                }
                externalStorageState = Environment.getExternalStorageDirectory().getPath();
                if (externalStorageState == null) {
                    return null;
                }
                StatFs statFs = new StatFs(externalStorageState);
                long blockCount = (((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize())) / 1000000;
                return String.valueOf((((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks())) / 1000000) + "/" + String.valueOf(blockCount);
            }
            d.c("can not get the permission of android.permission.WRITE_EXTERNAL_STORAGE");
            return null;
        } catch (Throwable th) {
            d.f(th);
            return null;
        }
    }

    public static String k(Context context) {
        return String.valueOf(o(context) / 1000000) + "/" + String.valueOf(d() / 1000000);
    }

    public static synchronized k l(Context context) {
        k kVar;
        synchronized (j.class) {
            if (f == null) {
                f = new k();
            }
            kVar = f;
        }
        return kVar;
    }

    public static JSONObject m(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            l(context);
            int b = k.b();
            if (b > 0) {
                jSONObject.put("fx", b / 1000000);
            }
            l(context);
            b = k.c();
            if (b > 0) {
                jSONObject.put("fn", b / 1000000);
            }
            l(context);
            b = k.a();
            if (b > 0) {
                jSONObject.put("n", b);
            }
            l(context);
            String d = k.d();
            if (d != null && d.length() == 0) {
                l(context);
                jSONObject.put("na", k.d());
            }
        } catch (Throwable th) {
            d.f(th);
        }
        return jSONObject;
    }

    public static String n(Context context) {
        try {
            SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
            if (sensorManager != null) {
                List sensorList = sensorManager.getSensorList(-1);
                if (sensorList != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < sensorList.size(); i++) {
                        stringBuilder.append(((Sensor) sensorList.get(i)).getType());
                        if (i != sensorList.size() - 1) {
                            stringBuilder.append(",");
                        }
                    }
                    return stringBuilder.toString();
                }
            }
        } catch (Throwable th) {
            d.f(th);
        }
        return "";
    }

    private static long o(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME);
        MemoryInfo memoryInfo = new MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }
}
