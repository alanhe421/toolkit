package com.tencent.beacon.b;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Debug;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.tencent.android.tpush.common.Constants;
import com.tencent.av.config.ConfigBaseParser;
import com.tencent.beacon.e.a;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Locale;

/* compiled from: ProGuard */
public final class f {
    private static f a;
    private Context b;

    private static NetworkInfo q(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return null;
            }
            return connectivityManager.getActiveNetworkInfo();
        } catch (Throwable th) {
            a.a(th);
            return null;
        }
    }

    public static boolean n(Context context) {
        NetworkInfo q = q(context);
        if (q == null || q.getType() != 1) {
            return false;
        }
        return true;
    }

    public static boolean o(Context context) {
        NetworkInfo q = q(context);
        if (q == null || !q.isConnected()) {
            return false;
        }
        return true;
    }

    public static String p(Context context) {
        NetworkInfo q = q(context);
        if (q == null) {
            return ConfigBaseParser.DEFAULT_VALUE;
        }
        if (q.getType() == 1) {
            return "wifi";
        }
        String extraInfo = q.getExtraInfo();
        if (extraInfo != null && extraInfo.length() > 64) {
            extraInfo = extraInfo.substring(0, 64);
        }
        return extraInfo;
    }

    public static synchronized f a(Context context) {
        f fVar;
        synchronized (f.class) {
            if (a == null && context != null) {
                a = new f(context);
            }
            fVar = a;
        }
        return fVar;
    }

    private f(Context context) {
        byte[] bArr = new byte[]{(byte) -10, (byte) 2, (byte) -48, (byte) 82, (byte) -127, (byte) 125, (byte) 26, (byte) -77, (byte) -43, (byte) 39, (byte) -19, (byte) -125, (byte) 73, (byte) -14, (byte) -17, (byte) -124};
        bArr = new byte[]{(byte) -45, (byte) 35, (byte) 22, (byte) 36, (byte) 44, (byte) -85, (byte) -1, (byte) 14};
        bArr = new byte[]{(byte) -5, (byte) -73, (byte) 87, (byte) 18, (byte) -8, (byte) -19, (byte) -44, (byte) -73};
        bArr = new byte[]{(byte) 35, (byte) -115, (byte) -100, (byte) 63, (byte) 78, (byte) -95, (byte) -99, (byte) 114, (byte) -43, (byte) 74, (byte) 27, (byte) -107, (byte) 113, (byte) -36, (byte) 98, (byte) 116, (byte) 6, (byte) -71, (byte) 106, (byte) -109, (byte) 21, (byte) 7, (byte) 55, (byte) 8};
        bArr = new byte[]{(byte) 30, (byte) 94, (byte) 78, (byte) -36, (byte) -2, (byte) -48, (byte) -105, (byte) -119, (byte) -23, (byte) 62, (byte) -100, (byte) 100, (byte) 124, (byte) 88, (byte) -69, (byte) 22, (byte) -84, (byte) -114, (byte) -115, (byte) -56, (byte) 27, (byte) -79, (byte) 95, (byte) -99};
        this.b = context;
    }

    public static String a() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Android ");
        stringBuffer.append(c());
        stringBuffer.append(",level ");
        stringBuffer.append(d());
        return stringBuffer.toString();
    }

    public static String b() {
        try {
            return Build.MODEL;
        } catch (Throwable th) {
            a.d("getDeviceName error", new Object[0]);
            a.a(th);
            return "";
        }
    }

    public static String c() {
        try {
            return VERSION.RELEASE;
        } catch (Throwable th) {
            a.d("getVersion error", new Object[0]);
            a.a(th);
            return "";
        }
    }

    public static String d() {
        try {
            return VERSION.SDK;
        } catch (Throwable th) {
            a.d("getApiLevel error", new Object[0]);
            a.a(th);
            return "";
        }
    }

    public static String b(Context context) {
        String str = "";
        if (context == null) {
            a.d("getImei but context == null!", new Object[0]);
            return str;
        }
        String deviceId;
        try {
            if (b.i(context)) {
                deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                if (deviceId == null) {
                    try {
                        deviceId = "";
                    } catch (Throwable th) {
                        a.d("getImei error!", new Object[0]);
                        return deviceId;
                    }
                }
                deviceId = deviceId.toLowerCase();
            } else {
                deviceId = str;
            }
            a.a("IMEI:" + deviceId, new Object[0]);
            return deviceId;
        } catch (Throwable th2) {
            deviceId = str;
            a.d("getImei error!", new Object[0]);
            return deviceId;
        }
    }

    public static String c(Context context) {
        String str = "";
        if (context == null) {
            a.d("getImsi but context == null!", new Object[0]);
            return str;
        }
        String toLowerCase;
        try {
            if (b.i(context)) {
                str = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
                toLowerCase = str == null ? "" : str.toLowerCase();
            } else {
                toLowerCase = str;
            }
        } catch (Throwable th) {
            toLowerCase = str;
            a.d("getImsi error!", new Object[0]);
        }
        return toLowerCase;
    }

    public static String d(Context context) {
        Throwable th;
        String str = "";
        if (context == null) {
            a.d("getAndroidId but context == null!", new Object[0]);
            return str;
        }
        try {
            String string = Secure.getString(context.getContentResolver(), "android_id");
            if (string != null) {
                return string.toLowerCase();
            }
            try {
                return "";
            } catch (Throwable th2) {
                Throwable th3 = th2;
                str = string;
                th = th3;
                a.d("getAndroidId error!", new Object[0]);
                a.a(th);
                return str;
            }
        } catch (Throwable th4) {
            th = th4;
            a.d("getAndroidId error!", new Object[0]);
            a.a(th);
            return str;
        }
    }

    public static String e(Context context) {
        String str;
        Throwable th;
        String str2 = "";
        if (context == null) {
            a.d("getMacAddress but context == null!", new Object[0]);
            return str2;
        }
        try {
            if (Integer.valueOf(VERSION.SDK).intValue() < 23) {
                str2 = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
                if (str2 == null) {
                    return "";
                }
                return str2.toLowerCase();
            }
            String[] strArr = new String[]{"/sys/class/net/wlan0/address", "/sys/devices/virtual/net/wlan0/address"};
            int length = strArr.length;
            int i = 0;
            str = str2;
            while (i < length) {
                try {
                    str2 = com.tencent.beacon.net.a.e(strArr[i]).toString().trim();
                    if (str2 != null) {
                        if (str2.length() > 0) {
                            return str2.toLowerCase();
                        }
                    }
                    i++;
                    str = str2;
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            return str;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            str = str2;
            th = th4;
        }
        a.a(th);
        return str;
    }

    public static String f(Context context) {
        String str = "";
        if (context == null) {
            a.d("getMacAddress but context == null!", new Object[0]);
            return str;
        }
        String bssid;
        try {
            bssid = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getBSSID();
        } catch (Throwable th) {
            a.a(th);
            a.d("getMacAddress error!", new Object[0]);
            bssid = str;
        }
        return bssid;
    }

    public static String g(Context context) {
        String str = "";
        if (context == null) {
            a.d("getWifiSSID but context == null!", new Object[0]);
            return str;
        }
        String ssid;
        try {
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo.getBSSID() != null) {
                ssid = connectionInfo.getSSID();
                return ssid;
            }
        } catch (Throwable th) {
            a.a(th);
            a.d("getWifiSSID error!", new Object[0]);
        }
        ssid = str;
        return ssid;
    }

    public static DisplayMetrics h(Context context) {
        if (context == null) {
            a.d("getDisplayMetrics but context == null!", new Object[0]);
            return null;
        }
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics;
        } catch (Throwable th) {
            a.d("getDisplayMetrics error!", new Object[0]);
            a.a(th);
            return null;
        }
    }

    public static String e() {
        String str = null;
        try {
            Object obj = Build.class.getField("HARDWARE").get(null);
            if (obj != null) {
                str = obj.toString();
            }
        } catch (Throwable th) {
            a.c("getCpuProductorName error!", new Object[0]);
        }
        return str;
    }

    public static String f() {
        String str = null;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            long blockSize = (long) statFs.getBlockSize();
            str = (((((long) statFs.getBlockCount()) * blockSize) / 1024) / 1024);
        } catch (Throwable th) {
            a.d("getDisplayMetrics error!", new Object[0]);
            a.a(th);
        }
        return str;
    }

    public static long i(Context context) {
        if (context == null) {
            a.d("getFreeMem but context == null!", new Object[0]);
            return -1;
        }
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME);
            MemoryInfo memoryInfo = new MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            return memoryInfo.availMem;
        } catch (Throwable th) {
            a.d("getFreeMem error!", new Object[0]);
            a.a(th);
            return -1;
        }
    }

    public final String g() {
        String str = "";
        if (this.b == null) {
            a.d("getFreeMem but context == null!", new Object[0]);
            return str;
        }
        String stringBuffer;
        try {
            ActivityManager activityManager = (ActivityManager) this.b.getSystemService(Constants.FLAG_ACTIVITY_NAME);
            if (b.a == 0) {
                b.a = Process.myPid();
            }
            Debug.MemoryInfo[] processMemoryInfo = activityManager.getProcessMemoryInfo(new int[]{b.a});
            if (processMemoryInfo != null && processMemoryInfo.length == 1) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append(processMemoryInfo[0].getTotalPrivateDirty()).append(",").append(processMemoryInfo[0].dalvikPrivateDirty).append(",").append(processMemoryInfo[0].nativePrivateDirty).append(",").append(processMemoryInfo[0].otherPrivateDirty).append(",").append(processMemoryInfo[0].getTotalPss()).append(",").append(processMemoryInfo[0].dalvikPss).append(",").append(processMemoryInfo[0].nativePss).append(",").append(processMemoryInfo[0].otherPss);
                stringBuffer = stringBuffer2.toString();
                return stringBuffer;
            }
        } catch (Throwable th) {
            a.d("getFreeMem error!", new Object[0]);
            a.a(th);
        }
        stringBuffer = str;
        return stringBuffer;
    }

    public static String h() {
        long j = 0;
        String str = "0";
        try {
            String[] split;
            long intValue;
            long j2;
            long j3;
            String[] split2;
            float f;
            double d;
            String str2 = "/proc/stat";
            String str3 = "/proc/" + b.a + "/stat";
            String f2 = com.tencent.beacon.net.a.f(str2);
            if (f2 != null) {
                split = f2.split(" ");
                if (split.length >= 11) {
                    intValue = (long) (Integer.valueOf(split[10]).intValue() + (((((((Integer.valueOf(split[2]).intValue() + Integer.valueOf(split[3]).intValue()) + Integer.valueOf(split[4]).intValue()) + Integer.valueOf(split[5]).intValue()) + Integer.valueOf(split[6]).intValue()) + Integer.valueOf(split[7]).intValue()) + Integer.valueOf(split[8]).intValue()) + Integer.valueOf(split[9]).intValue()));
                    a.b("totalCpuTime1:" + intValue, new Object[0]);
                    j2 = intValue;
                    f2 = com.tencent.beacon.net.a.f(str3);
                    if (f2 != null) {
                        split = f2.split(" ");
                        if (split.length >= 18) {
                            intValue = (long) (Integer.valueOf(split[16]).intValue() + ((Integer.valueOf(split[13]).intValue() + Integer.valueOf(split[14]).intValue()) + Integer.valueOf(split[15]).intValue()));
                            a.b("proCpuTime1:" + intValue, new Object[0]);
                            j3 = intValue;
                            Thread.sleep(500);
                            str2 = com.tencent.beacon.net.a.f(str2);
                            if (str2 != null) {
                                split2 = str2.split(" ");
                                if (split2.length >= 10) {
                                    intValue = (long) (Integer.valueOf(split2[10]).intValue() + (((((((Integer.valueOf(split2[2]).intValue() + Integer.valueOf(split2[3]).intValue()) + Integer.valueOf(split2[4]).intValue()) + Integer.valueOf(split2[5]).intValue()) + Integer.valueOf(split2[6]).intValue()) + Integer.valueOf(split2[7]).intValue()) + Integer.valueOf(split2[8]).intValue()) + Integer.valueOf(split2[9]).intValue()));
                                    a.b("totalCpuTime2:" + intValue, new Object[0]);
                                    str2 = com.tencent.beacon.net.a.f(str3);
                                    if (str2 != null) {
                                        split2 = str2.split(" ");
                                        if (split2.length >= 18) {
                                            j = (long) (Integer.valueOf(split2[16]).intValue() + ((Integer.valueOf(split2[13]).intValue() + Integer.valueOf(split2[14]).intValue()) + Integer.valueOf(split2[15]).intValue()));
                                            a.b("proCpuTime2:" + j, new Object[0]);
                                        }
                                    }
                                    f = (float) (intValue - j2);
                                    if (f <= 0.0f) {
                                        return str;
                                    }
                                    d = (double) (((float) (j - j3)) / f);
                                    return String.format("%.2f", new Object[]{Double.valueOf(d)});
                                }
                            }
                            intValue = 0;
                            str2 = com.tencent.beacon.net.a.f(str3);
                            if (str2 != null) {
                                split2 = str2.split(" ");
                                if (split2.length >= 18) {
                                    j = (long) (Integer.valueOf(split2[16]).intValue() + ((Integer.valueOf(split2[13]).intValue() + Integer.valueOf(split2[14]).intValue()) + Integer.valueOf(split2[15]).intValue()));
                                    a.b("proCpuTime2:" + j, new Object[0]);
                                }
                            }
                            f = (float) (intValue - j2);
                            if (f <= 0.0f) {
                                return str;
                            }
                            d = (double) (((float) (j - j3)) / f);
                            return String.format("%.2f", new Object[]{Double.valueOf(d)});
                        }
                    }
                    j3 = 0;
                    Thread.sleep(500);
                    str2 = com.tencent.beacon.net.a.f(str2);
                    if (str2 != null) {
                        split2 = str2.split(" ");
                        if (split2.length >= 10) {
                            intValue = (long) (Integer.valueOf(split2[10]).intValue() + (((((((Integer.valueOf(split2[2]).intValue() + Integer.valueOf(split2[3]).intValue()) + Integer.valueOf(split2[4]).intValue()) + Integer.valueOf(split2[5]).intValue()) + Integer.valueOf(split2[6]).intValue()) + Integer.valueOf(split2[7]).intValue()) + Integer.valueOf(split2[8]).intValue()) + Integer.valueOf(split2[9]).intValue()));
                            a.b("totalCpuTime2:" + intValue, new Object[0]);
                            str2 = com.tencent.beacon.net.a.f(str3);
                            if (str2 != null) {
                                split2 = str2.split(" ");
                                if (split2.length >= 18) {
                                    j = (long) (Integer.valueOf(split2[16]).intValue() + ((Integer.valueOf(split2[13]).intValue() + Integer.valueOf(split2[14]).intValue()) + Integer.valueOf(split2[15]).intValue()));
                                    a.b("proCpuTime2:" + j, new Object[0]);
                                }
                            }
                            f = (float) (intValue - j2);
                            if (f <= 0.0f) {
                                return str;
                            }
                            d = (double) (((float) (j - j3)) / f);
                            return String.format("%.2f", new Object[]{Double.valueOf(d)});
                        }
                    }
                    intValue = 0;
                    str2 = com.tencent.beacon.net.a.f(str3);
                    if (str2 != null) {
                        split2 = str2.split(" ");
                        if (split2.length >= 18) {
                            j = (long) (Integer.valueOf(split2[16]).intValue() + ((Integer.valueOf(split2[13]).intValue() + Integer.valueOf(split2[14]).intValue()) + Integer.valueOf(split2[15]).intValue()));
                            a.b("proCpuTime2:" + j, new Object[0]);
                        }
                    }
                    f = (float) (intValue - j2);
                    if (f <= 0.0f) {
                        return str;
                    }
                    d = (double) (((float) (j - j3)) / f);
                    return String.format("%.2f", new Object[]{Double.valueOf(d)});
                }
            }
            j2 = 0;
            f2 = com.tencent.beacon.net.a.f(str3);
            if (f2 != null) {
                split = f2.split(" ");
                if (split.length >= 18) {
                    intValue = (long) (Integer.valueOf(split[16]).intValue() + ((Integer.valueOf(split[13]).intValue() + Integer.valueOf(split[14]).intValue()) + Integer.valueOf(split[15]).intValue()));
                    a.b("proCpuTime1:" + intValue, new Object[0]);
                    j3 = intValue;
                    Thread.sleep(500);
                    str2 = com.tencent.beacon.net.a.f(str2);
                    if (str2 != null) {
                        split2 = str2.split(" ");
                        if (split2.length >= 10) {
                            intValue = (long) (Integer.valueOf(split2[10]).intValue() + (((((((Integer.valueOf(split2[2]).intValue() + Integer.valueOf(split2[3]).intValue()) + Integer.valueOf(split2[4]).intValue()) + Integer.valueOf(split2[5]).intValue()) + Integer.valueOf(split2[6]).intValue()) + Integer.valueOf(split2[7]).intValue()) + Integer.valueOf(split2[8]).intValue()) + Integer.valueOf(split2[9]).intValue()));
                            a.b("totalCpuTime2:" + intValue, new Object[0]);
                            str2 = com.tencent.beacon.net.a.f(str3);
                            if (str2 != null) {
                                split2 = str2.split(" ");
                                if (split2.length >= 18) {
                                    j = (long) (Integer.valueOf(split2[16]).intValue() + ((Integer.valueOf(split2[13]).intValue() + Integer.valueOf(split2[14]).intValue()) + Integer.valueOf(split2[15]).intValue()));
                                    a.b("proCpuTime2:" + j, new Object[0]);
                                }
                            }
                            f = (float) (intValue - j2);
                            if (f <= 0.0f) {
                                return str;
                            }
                            d = (double) (((float) (j - j3)) / f);
                            return String.format("%.2f", new Object[]{Double.valueOf(d)});
                        }
                    }
                    intValue = 0;
                    str2 = com.tencent.beacon.net.a.f(str3);
                    if (str2 != null) {
                        split2 = str2.split(" ");
                        if (split2.length >= 18) {
                            j = (long) (Integer.valueOf(split2[16]).intValue() + ((Integer.valueOf(split2[13]).intValue() + Integer.valueOf(split2[14]).intValue()) + Integer.valueOf(split2[15]).intValue()));
                            a.b("proCpuTime2:" + j, new Object[0]);
                        }
                    }
                    f = (float) (intValue - j2);
                    if (f <= 0.0f) {
                        return str;
                    }
                    d = (double) (((float) (j - j3)) / f);
                    return String.format("%.2f", new Object[]{Double.valueOf(d)});
                }
            }
            j3 = 0;
            try {
                Thread.sleep(500);
            } catch (Throwable e) {
                a.a(e);
            }
            str2 = com.tencent.beacon.net.a.f(str2);
            if (str2 != null) {
                split2 = str2.split(" ");
                if (split2.length >= 10) {
                    intValue = (long) (Integer.valueOf(split2[10]).intValue() + (((((((Integer.valueOf(split2[2]).intValue() + Integer.valueOf(split2[3]).intValue()) + Integer.valueOf(split2[4]).intValue()) + Integer.valueOf(split2[5]).intValue()) + Integer.valueOf(split2[6]).intValue()) + Integer.valueOf(split2[7]).intValue()) + Integer.valueOf(split2[8]).intValue()) + Integer.valueOf(split2[9]).intValue()));
                    a.b("totalCpuTime2:" + intValue, new Object[0]);
                    str2 = com.tencent.beacon.net.a.f(str3);
                    if (str2 != null) {
                        split2 = str2.split(" ");
                        if (split2.length >= 18) {
                            j = (long) (Integer.valueOf(split2[16]).intValue() + ((Integer.valueOf(split2[13]).intValue() + Integer.valueOf(split2[14]).intValue()) + Integer.valueOf(split2[15]).intValue()));
                            a.b("proCpuTime2:" + j, new Object[0]);
                        }
                    }
                    f = (float) (intValue - j2);
                    if (f <= 0.0f) {
                        return str;
                    }
                    d = (double) (((float) (j - j3)) / f);
                    return String.format("%.2f", new Object[]{Double.valueOf(d)});
                }
            }
            intValue = 0;
            str2 = com.tencent.beacon.net.a.f(str3);
            if (str2 != null) {
                split2 = str2.split(" ");
                if (split2.length >= 18) {
                    j = (long) (Integer.valueOf(split2[16]).intValue() + ((Integer.valueOf(split2[13]).intValue() + Integer.valueOf(split2[14]).intValue()) + Integer.valueOf(split2[15]).intValue()));
                    a.b("proCpuTime2:" + j, new Object[0]);
                }
            }
            f = (float) (intValue - j2);
            if (f <= 0.0f) {
                return str;
            }
            d = (double) (((float) (j - j3)) / f);
            return String.format("%.2f", new Object[]{Double.valueOf(d)});
        } catch (Exception e2) {
            return str;
        }
    }

    public static String i() {
        FileReader fileReader;
        BufferedReader bufferedReader;
        Throwable th;
        Object obj;
        Throwable th2;
        String str = null;
        try {
            fileReader = new FileReader("/proc/meminfo");
            try {
                bufferedReader = new BufferedReader(fileReader, 8192);
            } catch (Throwable th3) {
                obj = str;
                th2 = th3;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
                throw th2;
            }
            try {
                str = (Long.parseLong(bufferedReader.readLine().split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) / 1024);
                try {
                    bufferedReader.close();
                    fileReader.close();
                } catch (Throwable th32) {
                    a.d("getFreeMem error!", new Object[0]);
                    a.a(th32);
                }
            } catch (Throwable th4) {
                th32 = th4;
                a.d("getFreeMem error!", new Object[0]);
                a.a(th32);
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
                return str;
            }
        } catch (Throwable th322) {
            bufferedReader = str;
            fileReader = str;
            th2 = th322;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (fileReader != null) {
                fileReader.close();
            }
            throw th2;
        }
        return str;
    }

    public static long j() {
        Object obj;
        long j = 0;
        if (Environment.getExternalStorageState().equals("mounted")) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            try {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                long blockCount = (long) statFs.getBlockCount();
                j = ((((long) statFs.getBlockSize()) * blockCount) / 1024) / 1024;
            } catch (Throwable th) {
                a.a(th);
            }
        }
        return j;
    }

    public static String k() {
        String str = null;
        try {
            str = Locale.getDefault().getCountry();
        } catch (Throwable th) {
            a.d("getCountry error!", new Object[0]);
            a.a(th);
        }
        return str;
    }

    public static String l() {
        String str = null;
        try {
            str = Locale.getDefault().getLanguage();
        } catch (Throwable th) {
            a.d("getLanguage error!", new Object[0]);
            a.a(th);
        }
        return str;
    }

    public static String j(Context context) {
        Throwable th;
        String str;
        if (context == null) {
            a.d("getSensor but context == null!", new Object[0]);
            return null;
        }
        String str2;
        a.a("getSensor start", new Object[0]);
        String str3 = "X";
        String str4 = "X";
        String str5 = "X";
        String str6 = "X";
        StringBuffer stringBuffer = new StringBuffer();
        if (Integer.parseInt(VERSION.SDK) >= 10) {
            try {
                String str7;
                SensorManager sensorManager;
                Class cls = Class.forName("android.hardware.Camera");
                int intValue = ((Integer) cls.getMethod("getNumberOfCameras", new Class[0]).invoke(cls, new Object[0])).intValue();
                if (intValue == 0) {
                    str7 = "N";
                    str3 = "N";
                } else {
                    int i;
                    Class cls2 = Class.forName("android.hardware.Camera$CameraInfo");
                    Object newInstance = cls2.newInstance();
                    Method method = null;
                    for (Method method2 : cls.getMethods()) {
                        if (method2.getName().equals("getCameraInfo")) {
                            method = method2;
                            break;
                        }
                    }
                    Field field = cls2.getField("facing");
                    Field field2 = cls2.getField("CAMERA_FACING_BACK");
                    Field field3 = cls2.getField("CAMERA_FACING_FRONT");
                    if (method != null) {
                        str2 = str4;
                        str4 = str3;
                        int i2 = 0;
                        while (i2 < intValue) {
                            try {
                                method.invoke(cls, new Object[]{Integer.valueOf(i2), newInstance});
                                i = field.getInt(newInstance);
                                int i3 = field2.getInt(newInstance);
                                int i4 = field3.getInt(newInstance);
                                if (i == i3) {
                                    str4 = "Y";
                                    if (intValue == 1) {
                                        str2 = "N";
                                    }
                                } else if (i == i4) {
                                    str2 = "Y";
                                    if (intValue == 1) {
                                        str4 = "N";
                                    }
                                }
                                i2++;
                            } catch (Throwable th2) {
                                th = th2;
                                str = str5;
                                str5 = str4;
                                str4 = str2;
                                str2 = str;
                            }
                        }
                        str3 = str2;
                        str7 = str4;
                    } else {
                        str7 = str3;
                        str3 = str4;
                    }
                }
                try {
                    sensorManager = (SensorManager) context.getSystemService("sensor");
                    if (sensorManager.getDefaultSensor(9) != null) {
                        str4 = "Y";
                    } else {
                        str4 = "N";
                    }
                } catch (Throwable th3) {
                    str4 = str3;
                    th = th3;
                    str2 = str5;
                    str5 = str7;
                    a.d("getSensor error!", new Object[0]);
                    a.a(th);
                    str = str6;
                    str6 = str4;
                    str4 = str2;
                    str2 = str;
                    stringBuffer.append(str5).append(str6).append(str4).append(str2);
                    return stringBuffer.toString();
                }
                try {
                    if (sensorManager.getDefaultSensor(4) != null) {
                        str2 = "Y";
                        str6 = str3;
                        str5 = str7;
                    } else {
                        str2 = "N";
                        str6 = str3;
                        str5 = str7;
                    }
                } catch (Throwable th32) {
                    str5 = str7;
                    str = str4;
                    str4 = str3;
                    th = th32;
                    str2 = str;
                    a.d("getSensor error!", new Object[0]);
                    a.a(th);
                    str = str6;
                    str6 = str4;
                    str4 = str2;
                    str2 = str;
                    stringBuffer.append(str5).append(str6).append(str4).append(str2);
                    return stringBuffer.toString();
                }
            } catch (Throwable th322) {
                Throwable th4 = th322;
                str2 = str5;
                str5 = str3;
                th = th4;
                a.d("getSensor error!", new Object[0]);
                a.a(th);
                str = str6;
                str6 = str4;
                str4 = str2;
                str2 = str;
                stringBuffer.append(str5).append(str6).append(str4).append(str2);
                return stringBuffer.toString();
            }
        }
        str2 = str6;
        str6 = str4;
        str4 = str5;
        str5 = str3;
        stringBuffer.append(str5).append(str6).append(str4).append(str2);
        return stringBuffer.toString();
    }

    public static String m() {
        String str = null;
        try {
            return Build.BRAND;
        } catch (Throwable th) {
            a.d("getBrand error!", new Object[0]);
            a.a(th);
            return str;
        }
    }

    public static String k(Context context) {
        if (a == null) {
            return ConfigBaseParser.DEFAULT_VALUE;
        }
        f fVar = a;
        return l(context);
    }

    public static String l(Context context) {
        String str = ConfigBaseParser.DEFAULT_VALUE;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return str;
            }
            String str2;
            if (activeNetworkInfo.getType() == 1) {
                str2 = "wifi";
            } else {
                if (activeNetworkInfo.getType() == 0) {
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                    if (telephonyManager != null) {
                        switch (telephonyManager.getNetworkType()) {
                            case 1:
                                str2 = "GPRS";
                                break;
                            case 2:
                                str2 = "EDGE";
                                break;
                            case 3:
                                str2 = "UMTS";
                                break;
                            case 4:
                                str2 = "CDMA";
                                break;
                            case 5:
                                str2 = "EVDO_0";
                                break;
                            case 6:
                                str2 = "EVDO_A";
                                break;
                            case 7:
                                str2 = "1xRTT";
                                break;
                            case 8:
                                str2 = "HSDPA";
                                break;
                            case 9:
                                str2 = "HSUPA";
                                break;
                            case 10:
                                str2 = "HSPA";
                                break;
                            case 11:
                                str2 = "iDen";
                                break;
                            case 12:
                                str2 = "EVDO_B";
                                break;
                            case 13:
                                str2 = "LTE";
                                break;
                            case 14:
                                str2 = "eHRPD";
                                break;
                            case 15:
                                str2 = "HSPA+";
                                break;
                            default:
                                str2 = ConfigBaseParser.DEFAULT_VALUE;
                                break;
                        }
                    }
                }
                str2 = str;
            }
            return str2;
        } catch (Throwable e) {
            a.a(e);
        }
    }

    public static int m(Context context) {
        if (context == null) {
            a.d("getWifiSignalLevel but context == null!", new Object[0]);
            return 0;
        }
        int calculateSignalLevel;
        try {
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo.getBSSID() != null) {
                calculateSignalLevel = WifiManager.calculateSignalLevel(connectionInfo.getRssi(), 5);
                return calculateSignalLevel;
            }
        } catch (Throwable e) {
            a.a(e);
        }
        calculateSignalLevel = 0;
        return calculateSignalLevel;
    }

    public static String n() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/sys/block/mmcblk0/device/type")));
            String readLine = bufferedReader.readLine();
            bufferedReader.close();
            stringBuilder.append(readLine);
        } catch (Exception e) {
        }
        stringBuilder.append(",");
        try {
            bufferedReader = new BufferedReader(new FileReader(new File("/sys/block/mmcblk0/device/name")));
            readLine = bufferedReader.readLine();
            bufferedReader.close();
            stringBuilder.append(readLine);
        } catch (Exception e2) {
        }
        stringBuilder.append(",");
        try {
            bufferedReader = new BufferedReader(new FileReader(new File("/sys/block/mmcblk0/device/cid")));
            readLine = bufferedReader.readLine();
            bufferedReader.close();
            stringBuilder.append(readLine);
        } catch (Exception e3) {
        }
        return stringBuilder.toString();
    }
}
