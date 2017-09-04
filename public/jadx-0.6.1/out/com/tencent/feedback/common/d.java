package com.tencent.feedback.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.tencent.av.config.ConfigBaseParser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Locale;

/* compiled from: RQDSRC */
public final class d {
    private static d a;

    public static synchronized d a(Context context) {
        d dVar;
        synchronized (d.class) {
            if (a == null && context != null) {
                if (context != null) {
                    Context applicationContext = context.getApplicationContext();
                    if (applicationContext != null) {
                        context = applicationContext;
                    }
                }
                a = new d(context);
            }
            dVar = a;
        }
        return dVar;
    }

    private d(Context context) {
        if (context == null || context.getApplicationContext() != null) {
        }
    }

    public static String a() {
        try {
            return Build.MODEL;
        } catch (Throwable th) {
            e.d("rqdp{  getDeviceName error}", new Object[0]);
            if (!e.a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    public static String b() {
        try {
            return VERSION.RELEASE;
        } catch (Throwable th) {
            e.d("rqdp{  getVersion error}", new Object[0]);
            if (!e.a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    public static String c() {
        try {
            return VERSION.SDK;
        } catch (Throwable th) {
            e.d("rqdp{  getApiLevel error}", new Object[0]);
            if (!e.a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    public static String b(Context context) {
        String str;
        Throwable th;
        String str2 = "fail";
        if (context == null) {
            e.d("rqdp{  getImei but context == null!}", new Object[0]);
            return str2;
        }
        try {
            str2 = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            if (str2 == null) {
                str = "null";
            } else {
                str = str2.toLowerCase();
            }
            try {
                e.a("rqdp{  IMEI:}" + str, new Object[0]);
                return str;
            } catch (Throwable th2) {
                th = th2;
                e.d("rqdp{  getImei error!}", new Object[0]);
                if (!e.a(th)) {
                    return str;
                }
                th.printStackTrace();
                return str;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            str = str2;
            th = th4;
            e.d("rqdp{  getImei error!}", new Object[0]);
            if (!e.a(th)) {
                return str;
            }
            th.printStackTrace();
            return str;
        }
    }

    public static String c(Context context) {
        String str = "fail";
        if (context == null) {
            e.d("rqdp{  getImsi but context == null!}", new Object[0]);
            return str;
        }
        try {
            str = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
            if (str == null) {
                return "null";
            }
            return str.toLowerCase();
        } catch (Throwable th) {
            Throwable th2 = th;
            String str2 = str;
            Throwable th3 = th2;
            e.d("rqdp{  getImsi error!}", new Object[0]);
            if (e.a(th3)) {
                return str2;
            }
            th3.printStackTrace();
            return str2;
        }
    }

    public static String d(Context context) {
        Throwable th;
        String str = "fail";
        if (context == null) {
            e.d("rqdp{  getAndroidId but context == null!}", new Object[0]);
            return str;
        }
        try {
            String string = Secure.getString(context.getContentResolver(), "android_id");
            if (string != null) {
                return string.toLowerCase();
            }
            try {
                return "null";
            } catch (Throwable th2) {
                Throwable th3 = th2;
                str = string;
                th = th3;
                e.d("rqdp{  getAndroidId error!}", new Object[0]);
                if (!e.a(th)) {
                    return str;
                }
                th.printStackTrace();
                return str;
            }
        } catch (Throwable th4) {
            th = th4;
            e.d("rqdp{  getAndroidId error!}", new Object[0]);
            if (!e.a(th)) {
                return str;
            }
            th.printStackTrace();
            return str;
        }
    }

    public static String e(Context context) {
        String str = "fail";
        if (context == null) {
            e.d("rqdp{  getMacAddress but context == null!}", new Object[0]);
            return str;
        }
        try {
            str = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            if (str == null) {
                return "null";
            }
            return str.toLowerCase();
        } catch (Throwable th) {
            Throwable th2 = th;
            String str2 = str;
            Throwable th3 = th2;
            if (!e.a(th3)) {
                th3.printStackTrace();
            }
            e.d("rqdp{  getMacAddress error!}", new Object[0]);
            return str2;
        }
    }

    public static String d() {
        String str = null;
        try {
            str = Build.CPU_ABI;
        } catch (Throwable th) {
            e.c("rqdp{  ge cuabi fa!}", new Object[0]);
            if (!e.a(th)) {
                th.printStackTrace();
            }
        }
        if (str == null || str.trim().length() == 0) {
            str = System.getProperty("os.arch");
        }
        return str == null ? "fail" : str;
    }

    public static long e() {
        long j = -1;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            e.d("rqdp{  getDisplayMetrics error!}", new Object[0]);
            if (e.a(th)) {
                return j;
            }
            th.printStackTrace();
            return j;
        }
    }

    public static long f() {
        long j = -1;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            e.d("rqdp{  getDisplayMetrics error!}", new Object[0]);
            if (e.a(th)) {
                return j;
            }
            th.printStackTrace();
            return j;
        }
    }

    public static long g() {
        FileReader fileReader;
        Throwable th;
        FileReader fileReader2;
        BufferedReader bufferedReader = null;
        BufferedReader bufferedReader2;
        try {
            fileReader = new FileReader("/proc/meminfo");
            try {
                bufferedReader2 = new BufferedReader(fileReader, 2048);
            } catch (Throwable th2) {
                th = th2;
                bufferedReader2 = null;
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
                throw th;
            }
            try {
                long parseLong = Long.parseLong(bufferedReader2.readLine().split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) * 1000;
                try {
                    bufferedReader2.close();
                } catch (Throwable e) {
                    if (!e.a(e)) {
                        e.printStackTrace();
                    }
                }
                try {
                    fileReader.close();
                    return parseLong;
                } catch (Throwable e2) {
                    if (e.a(e2)) {
                        return parseLong;
                    }
                    e2.printStackTrace();
                    return parseLong;
                }
            } catch (Throwable th3) {
                th = th3;
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader2 = null;
            fileReader = null;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            if (fileReader != null) {
                fileReader.close();
            }
            throw th;
        }
    }

    public static long h() {
        FileReader fileReader;
        BufferedReader bufferedReader;
        Throwable th;
        FileReader fileReader2;
        BufferedReader bufferedReader2 = null;
        try {
            fileReader = new FileReader("/proc/meminfo");
            try {
                bufferedReader = new BufferedReader(fileReader, 2048);
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
                throw th;
            }
            try {
                bufferedReader.readLine();
                long parseLong = Long.parseLong(bufferedReader.readLine().split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) * 1000;
                try {
                    bufferedReader.close();
                } catch (Throwable e) {
                    if (!e.a(e)) {
                        e.printStackTrace();
                    }
                }
                try {
                    fileReader.close();
                    return parseLong;
                } catch (Throwable e2) {
                    if (e.a(e2)) {
                        return parseLong;
                    }
                    e2.printStackTrace();
                    return parseLong;
                }
            } catch (Throwable th3) {
                th = th3;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
            fileReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (fileReader != null) {
                fileReader.close();
            }
            throw th;
        }
    }

    public final long i() {
        if ((Environment.getExternalStorageState().equals("mounted") ? 1 : 0) == 0) {
            return 0;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            e.d("rqdp{  get total sd error %s}", th.toString());
            if (!e.a(th)) {
                th.printStackTrace();
            }
            return -2;
        }
    }

    public final long j() {
        if ((Environment.getExternalStorageState().equals("mounted") ? 1 : 0) == 0) {
            return 0;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            e.d("rqdp{  get free sd error %s}", th.toString());
            if (!e.a(th)) {
                th.printStackTrace();
            }
            return -2;
        }
    }

    public static String k() {
        String str = "fail";
        try {
            str = Locale.getDefault().getCountry();
        } catch (Throwable th) {
            e.d("rqdp{  getCountry error!}", new Object[0]);
            if (!e.a(th)) {
                th.printStackTrace();
            }
        }
        return str;
    }

    public static String l() {
        String str = "fail";
        try {
            return Build.BRAND;
        } catch (Throwable th) {
            e.d("rqdp{  getBrand error!}", new Object[0]);
            if (e.a(th)) {
                return str;
            }
            th.printStackTrace();
            return str;
        }
    }

    public static String f(Context context) {
        String str = ConfigBaseParser.DEFAULT_VALUE;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return str;
            }
            if (activeNetworkInfo.getType() == 1) {
                return "wifi";
            }
            String str2;
            if (activeNetworkInfo.getType() == 0) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    switch (telephonyManager.getNetworkType()) {
                        case 1:
                            return "GPRS";
                        case 2:
                            return "EDGE";
                        case 3:
                            return "UMTS";
                        case 4:
                            return "CDMA";
                        case 5:
                            return "EVDO_0";
                        case 6:
                            return "EVDO_A";
                        case 7:
                            return "1xRTT";
                        case 8:
                            return "HSDPA";
                        case 9:
                            return "HSUPA";
                        case 10:
                            return "HSPA";
                        case 11:
                            return "iDen";
                        case 12:
                            return "EVDO_B";
                        case 13:
                            return "LTE";
                        case 14:
                            return "eHRPD";
                        case 15:
                            return "HSPA+";
                        default:
                            str2 = ConfigBaseParser.DEFAULT_VALUE;
                            break;
                    }
                    return str2;
                }
            }
            str2 = str;
            return str2;
        } catch (Throwable e) {
            if (e.a(e)) {
                return str;
            }
            e.printStackTrace();
            return str;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long m() {
        /*
        r1 = 0;
        r0 = new java.lang.StringBuilder;
        r2 = "/proc/";
        r0.<init>(r2);
        r2 = android.os.Process.myPid();
        r0 = r0.append(r2);
        r2 = "/maps";
        r0 = r0.append(r2);
        r2 = r0.toString();
        r0 = java.lang.Thread.currentThread();	 Catch:{ Throwable -> 0x0102, all -> 0x0139 }
        r3 = "main";
        r0 = r0.getName();	 Catch:{ Throwable -> 0x0102, all -> 0x0139 }
        r0 = r3.equals(r0);	 Catch:{ Throwable -> 0x0102, all -> 0x0139 }
        if (r0 == 0) goto L_0x00ae;
    L_0x002d:
        r0 = "[stack]";
    L_0x0030:
        r3 = "stack:%s";
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ Throwable -> 0x0102, all -> 0x0139 }
        r5 = 0;
        r4[r5] = r0;	 Catch:{ Throwable -> 0x0102, all -> 0x0139 }
        com.tencent.feedback.common.e.b(r3, r4);	 Catch:{ Throwable -> 0x0102, all -> 0x0139 }
        r3 = new java.io.FileReader;	 Catch:{ Throwable -> 0x0102, all -> 0x0139 }
        r3.<init>(r2);	 Catch:{ Throwable -> 0x0102, all -> 0x0139 }
        r2 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x0164, all -> 0x015c }
        r4 = 2048; // 0x800 float:2.87E-42 double:1.0118E-320;
        r2.<init>(r3, r4);	 Catch:{ Throwable -> 0x0164, all -> 0x015c }
    L_0x0048:
        r1 = r2.readLine();	 Catch:{ Throwable -> 0x0167, all -> 0x015e }
        if (r1 == 0) goto L_0x00e3;
    L_0x004e:
        r4 = r1.contains(r0);	 Catch:{ Throwable -> 0x0167, all -> 0x015e }
        if (r4 == 0) goto L_0x0048;
    L_0x0054:
        r4 = "\\s+";
        r1 = r1.split(r4);	 Catch:{ Throwable -> 0x0167, all -> 0x015e }
        r4 = r1.length;	 Catch:{ Throwable -> 0x0167, all -> 0x015e }
        if (r4 <= 0) goto L_0x0048;
    L_0x005e:
        r4 = 0;
        r4 = r1[r4];	 Catch:{ Throwable -> 0x0167, all -> 0x015e }
        r5 = "-";
        r4 = r4.indexOf(r5);	 Catch:{ Throwable -> 0x0167, all -> 0x015e }
        if (r4 <= 0) goto L_0x0048;
    L_0x006a:
        r5 = 0;
        r5 = r1[r5];	 Catch:{ Throwable -> 0x0167, all -> 0x015e }
        r5 = r5.length();	 Catch:{ Throwable -> 0x0167, all -> 0x015e }
        if (r5 <= r4) goto L_0x0048;
    L_0x0073:
        r0 = 0;
        r0 = r1[r0];	 Catch:{ Throwable -> 0x0167, all -> 0x015e }
        r5 = 0;
        r0 = r0.substring(r5, r4);	 Catch:{ Throwable -> 0x0167, all -> 0x015e }
        r5 = 16;
        r6 = java.lang.Long.parseLong(r0, r5);	 Catch:{ Throwable -> 0x0167, all -> 0x015e }
        r0 = 0;
        r0 = r1[r0];	 Catch:{ Throwable -> 0x0167, all -> 0x015e }
        r1 = r4 + 1;
        r0 = r0.substring(r1);	 Catch:{ Throwable -> 0x0167, all -> 0x015e }
        r1 = 16;
        r0 = java.lang.Long.parseLong(r0, r1);	 Catch:{ Throwable -> 0x0167, all -> 0x015e }
        r0 = r0 - r6;
        r4 = "st:%d";
        r5 = 1;
        r5 = new java.lang.Object[r5];	 Catch:{ Throwable -> 0x0167, all -> 0x015e }
        r6 = 0;
        r7 = java.lang.Long.valueOf(r0);	 Catch:{ Throwable -> 0x0167, all -> 0x015e }
        r5[r6] = r7;	 Catch:{ Throwable -> 0x0167, all -> 0x015e }
        com.tencent.feedback.common.e.b(r4, r5);	 Catch:{ Throwable -> 0x0167, all -> 0x015e }
        r4 = 0;
        r4 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r4 <= 0) goto L_0x00cb;
    L_0x00a7:
        r2.close();	 Catch:{ IOException -> 0x00cd }
    L_0x00aa:
        r3.close();	 Catch:{ IOException -> 0x00d8 }
    L_0x00ad:
        return r0;
    L_0x00ae:
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0102, all -> 0x0139 }
        r3 = "[stack:";
        r0.<init>(r3);	 Catch:{ Throwable -> 0x0102, all -> 0x0139 }
        r3 = android.os.Process.myTid();	 Catch:{ Throwable -> 0x0102, all -> 0x0139 }
        r0 = r0.append(r3);	 Catch:{ Throwable -> 0x0102, all -> 0x0139 }
        r3 = "]";
        r0 = r0.append(r3);	 Catch:{ Throwable -> 0x0102, all -> 0x0139 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x0102, all -> 0x0139 }
        goto L_0x0030;
    L_0x00cb:
        r0 = -r0;
        goto L_0x00a7;
    L_0x00cd:
        r2 = move-exception;
        r4 = com.tencent.feedback.common.e.a(r2);
        if (r4 != 0) goto L_0x00aa;
    L_0x00d4:
        r2.printStackTrace();
        goto L_0x00aa;
    L_0x00d8:
        r2 = move-exception;
        r3 = com.tencent.feedback.common.e.a(r2);
        if (r3 != 0) goto L_0x00ad;
    L_0x00df:
        r2.printStackTrace();
        goto L_0x00ad;
    L_0x00e3:
        r2.close();	 Catch:{ IOException -> 0x00ec }
    L_0x00e6:
        r3.close();	 Catch:{ IOException -> 0x00f7 }
    L_0x00e9:
        r0 = -1;
        goto L_0x00ad;
    L_0x00ec:
        r0 = move-exception;
        r1 = com.tencent.feedback.common.e.a(r0);
        if (r1 != 0) goto L_0x00e6;
    L_0x00f3:
        r0.printStackTrace();
        goto L_0x00e6;
    L_0x00f7:
        r0 = move-exception;
        r1 = com.tencent.feedback.common.e.a(r0);
        if (r1 != 0) goto L_0x00e9;
    L_0x00fe:
        r0.printStackTrace();
        goto L_0x00e9;
    L_0x0102:
        r0 = move-exception;
        r2 = r1;
    L_0x0104:
        r3 = "rqdp{  getFreeMem error!}";
        r4 = 0;
        r4 = new java.lang.Object[r4];	 Catch:{ all -> 0x0161 }
        com.tencent.feedback.common.e.d(r3, r4);	 Catch:{ all -> 0x0161 }
        r3 = com.tencent.feedback.common.e.a(r0);	 Catch:{ all -> 0x0161 }
        if (r3 != 0) goto L_0x0116;
    L_0x0113:
        r0.printStackTrace();	 Catch:{ all -> 0x0161 }
    L_0x0116:
        if (r1 == 0) goto L_0x011b;
    L_0x0118:
        r1.close();	 Catch:{ IOException -> 0x0123 }
    L_0x011b:
        if (r2 == 0) goto L_0x0120;
    L_0x011d:
        r2.close();	 Catch:{ IOException -> 0x012e }
    L_0x0120:
        r0 = -2;
        goto L_0x00ad;
    L_0x0123:
        r0 = move-exception;
        r1 = com.tencent.feedback.common.e.a(r0);
        if (r1 != 0) goto L_0x011b;
    L_0x012a:
        r0.printStackTrace();
        goto L_0x011b;
    L_0x012e:
        r0 = move-exception;
        r1 = com.tencent.feedback.common.e.a(r0);
        if (r1 != 0) goto L_0x0120;
    L_0x0135:
        r0.printStackTrace();
        goto L_0x0120;
    L_0x0139:
        r0 = move-exception;
        r3 = r1;
    L_0x013b:
        if (r1 == 0) goto L_0x0140;
    L_0x013d:
        r1.close();	 Catch:{ IOException -> 0x0146 }
    L_0x0140:
        if (r3 == 0) goto L_0x0145;
    L_0x0142:
        r3.close();	 Catch:{ IOException -> 0x0151 }
    L_0x0145:
        throw r0;
    L_0x0146:
        r1 = move-exception;
        r2 = com.tencent.feedback.common.e.a(r1);
        if (r2 != 0) goto L_0x0140;
    L_0x014d:
        r1.printStackTrace();
        goto L_0x0140;
    L_0x0151:
        r1 = move-exception;
        r2 = com.tencent.feedback.common.e.a(r1);
        if (r2 != 0) goto L_0x0145;
    L_0x0158:
        r1.printStackTrace();
        goto L_0x0145;
    L_0x015c:
        r0 = move-exception;
        goto L_0x013b;
    L_0x015e:
        r0 = move-exception;
        r1 = r2;
        goto L_0x013b;
    L_0x0161:
        r0 = move-exception;
        r3 = r2;
        goto L_0x013b;
    L_0x0164:
        r0 = move-exception;
        r2 = r3;
        goto L_0x0104;
    L_0x0167:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
        goto L_0x0104;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.feedback.common.d.m():long");
    }

    public static long n() {
        BufferedReader bufferedReader;
        Throwable e;
        FileReader fileReader;
        BufferedReader bufferedReader2 = null;
        FileReader fileReader2;
        try {
            CharSequence charSequence = "[heap]";
            fileReader2 = new FileReader("/proc/" + Process.myPid() + "/maps");
            try {
                bufferedReader = new BufferedReader(fileReader2, 2048);
                long j = 0;
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            try {
                                break;
                            } catch (Throwable e2) {
                                if (!e.a(e2)) {
                                    e2.printStackTrace();
                                }
                            }
                        } else if (readLine.contains(charSequence)) {
                            long parseLong;
                            String[] split = readLine.split("\\s+");
                            if (split.length > 0) {
                                int indexOf = split[0].indexOf("-");
                                if (indexOf > 0 && split[0].length() > indexOf) {
                                    parseLong = Long.parseLong(split[0].substring(indexOf + 1), 16) - Long.parseLong(split[0].substring(0, indexOf), 16);
                                    e.b("hp:%d", Long.valueOf(parseLong));
                                    if (parseLong <= 0) {
                                        parseLong = -parseLong;
                                    }
                                    parseLong += j;
                                    j = parseLong;
                                }
                            }
                            parseLong = j;
                            j = parseLong;
                        }
                    } catch (Throwable th) {
                        e2 = th;
                    }
                }
                bufferedReader.close();
                try {
                    fileReader2.close();
                    return j;
                } catch (Throwable e22) {
                    if (e.a(e22)) {
                        return j;
                    }
                    e22.printStackTrace();
                    return j;
                }
            } catch (Throwable th2) {
                e22 = th2;
                bufferedReader = null;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader2 != null) {
                    fileReader2.close();
                }
                throw e22;
            }
        } catch (Throwable th3) {
            e22 = th3;
            bufferedReader = null;
            fileReader2 = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (fileReader2 != null) {
                fileReader2.close();
            }
            throw e22;
        }
    }
}
