package com.sijla.j.a;

import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.PowerManager;
import android.os.Process;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.q.m.QS;
import com.sijla.common.HBS;
import com.sijla.j.b;
import com.sijla.j.f;
import com.sijla.j.j;
import com.tencent.android.tpush.common.Constants;
import com.tencent.av.config.ConfigBaseParser;
import com.tencent.qalsdk.sdk.v;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class a {
    private static int a = 0;

    public static boolean a() {
        String externalStorageState = Environment.getExternalStorageState();
        return externalStorageState.equals("mounted") && !externalStorageState.equals("mounted_ro");
    }

    public static boolean a(Context context) {
        try {
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            if (VERSION.SDK_INT >= 20) {
                return powerManager.isInteractive();
            }
            return powerManager.isScreenOn();
        } catch (Exception e) {
            return true;
        }
    }

    public static boolean b() {
        for (String str : new String[]{"/system/bin/", "/system/xbin/", "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/"}) {
            if (new File(str + "su").exists()) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(Context context, String str) {
        try {
            return a(context.getPackageManager().getPackageInfo(str, 128).applicationInfo);
        } catch (NameNotFoundException e) {
            return true;
        }
    }

    public static boolean a(ApplicationInfo applicationInfo) {
        if ((applicationInfo.flags & 128) == 0 && (applicationInfo.flags & 1) != 0) {
            return false;
        }
        return true;
    }

    public static List<String> c() {
        List<String> arrayList = new ArrayList();
        try {
            List<String> h = b.h("ps -P");
            if (h != null) {
                for (String toLowerCase : h) {
                    String toLowerCase2;
                    CharSequence toLowerCase3 = toLowerCase2.toLowerCase();
                    if (toLowerCase3.contains(":qs") || toLowerCase3.contains(":ajmd")) {
                        toLowerCase2 = Pattern.compile("[' ']+").matcher(toLowerCase3).replaceAll(",").trim();
                        toLowerCase2 = toLowerCase2.substring(toLowerCase2.lastIndexOf(",") + 1).replace(":qs", "").replace(":ajmd", "");
                        if (!arrayList.contains(toLowerCase2)) {
                            arrayList.add(toLowerCase2);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static boolean b(Context context) {
        NetworkInfo B = B(context);
        return B != null && B.isConnected() && B.getState() == State.CONNECTED;
    }

    public static boolean c(Context context) {
        NetworkInfo B = B(context);
        if (B != null && B.isConnected() && B.getType() == 1) {
            return true;
        }
        return false;
    }

    public static boolean d(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null) {
            NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
            if (allNetworkInfo != null) {
                for (NetworkInfo networkInfo : allNetworkInfo) {
                    if (networkInfo.getType() == 1 && networkInfo.isConnected()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static String e(Context context) {
        try {
            if (!c(context)) {
                return r(context);
            }
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo != null) {
                String ssid = connectionInfo.getSSID();
                return b.a(ssid) ? "" : ssid.replace("'", "").replace("\"", "");
            }
            return "";
        } catch (Exception e) {
        }
    }

    private static NetworkInfo B(Context context) {
        return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
    }

    public static String b(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            String trim = packageManager.getPackageInfo(str, 0).applicationInfo.loadLabel(packageManager).toString().trim();
            if (trim.length() > 0) {
                return trim;
            }
            return "";
        } catch (NameNotFoundException e) {
            return "";
        }
    }

    public static String a(String str, Context context) {
        String str2 = "";
        try {
            str2 = context.getPackageManager().getPackageInfo(str, 0).versionName.replace(" ", "");
        } catch (Exception e) {
        }
        if (b.a(str2)) {
            return ConfigBaseParser.DEFAULT_VALUE;
        }
        return str2;
    }

    public static String f(Context context) {
        return a(context.getPackageName(), context);
    }

    public static ActivityManager g(Context context) {
        return (ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME);
    }

    public static String[] h(Context context) {
        String[] strArr = new String[]{"", ""};
        ActivityManager g = g(context);
        try {
            if (VERSION.SDK_INT >= 20) {
                strArr[0] = "";
                strArr[1] = a(context, g);
            } else if (f(context, "android.permission.GET_TASKS")) {
                List runningTasks = g.getRunningTasks(1);
                if (!(runningTasks == null || runningTasks.isEmpty())) {
                    ComponentName componentName = ((RunningTaskInfo) runningTasks.get(0)).topActivity;
                    strArr[0] = componentName.getClassName();
                    strArr[1] = componentName.getPackageName();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strArr;
    }

    public static String a(Context context, ActivityManager activityManager) {
        Field declaredField;
        try {
            declaredField = RunningAppProcessInfo.class.getDeclaredField("processState");
        } catch (Exception e) {
            declaredField = null;
        }
        for (RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
            if (runningAppProcessInfo.importance == 100 && runningAppProcessInfo.importanceReasonCode == 0) {
                Integer valueOf;
                if (declaredField != null) {
                    try {
                        valueOf = Integer.valueOf(declaredField.getInt(runningAppProcessInfo));
                    } catch (Exception e2) {
                        valueOf = null;
                    }
                } else {
                    valueOf = null;
                }
                if (valueOf != null && valueOf.intValue() == 2) {
                    break;
                }
            }
        }
        RunningAppProcessInfo runningAppProcessInfo2 = null;
        if (runningAppProcessInfo2 == null || runningAppProcessInfo2.importanceReasonCode != 0) {
            return "";
        }
        return runningAppProcessInfo2.pkgList[0];
    }

    public static String i(Context context) {
        String deviceId;
        try {
            deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception e) {
            deviceId = null;
        }
        if (deviceId == null) {
            return "emt";
        }
        return deviceId;
    }

    public static String j(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.valueOf(displayMetrics.widthPixels));
        stringBuilder.append("x");
        stringBuilder.append(String.valueOf(displayMetrics.heightPixels));
        return stringBuilder.toString();
    }

    public static String k(Context context) {
        String string = Secure.getString(context.getContentResolver(), "android_id");
        return string == null ? "" : string;
    }

    public static String l(Context context) {
        String line1Number;
        try {
            line1Number = ((TelephonyManager) context.getSystemService("phone")).getLine1Number();
        } catch (Exception e) {
            line1Number = null;
        }
        if (line1Number == null) {
            return "";
        }
        return line1Number;
    }

    public static String m(Context context) {
        String subscriberId;
        try {
            subscriberId = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
        } catch (Exception e) {
            subscriberId = null;
        }
        if (subscriberId == null) {
            return "";
        }
        return subscriberId;
    }

    public static String n(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (5 == telephonyManager.getSimState()) {
                String simOperator = telephonyManager.getSimOperator();
                if (simOperator == null || simOperator.trim().length() <= 0) {
                    return "unknow";
                }
                return simOperator;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "unknow";
    }

    public static String o(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        return defaultDisplay.getHeight() + v.n + defaultDisplay.getWidth();
    }

    public static String d() {
        return VERSION.RELEASE;
    }

    public static String p(Context context) {
        int intValue;
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        StringBuffer stringBuffer = new StringBuffer();
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        try {
            i = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
            intValue = ((Integer) Display.class.getMethod("getRawHeight", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
        } catch (Exception e) {
            i = i;
            intValue = i2;
        }
        try {
            Point point = new Point();
            Display.class.getMethod("getRealSize", new Class[]{Point.class}).invoke(defaultDisplay, new Object[]{point});
            i = point.x;
            intValue = point.y;
        } catch (Exception e2) {
        }
        double d = (double) (((float) i) / displayMetrics.xdpi);
        double d2 = (double) (((float) intValue) / displayMetrics.ydpi);
        d2 *= d2;
        stringBuffer.append(String.format("%.1f", new Object[]{Double.valueOf(Math.sqrt(d2 + (d * d)))}));
        return stringBuffer.toString();
    }

    public static String q(Context context) {
        return s(context);
    }

    public static String r(Context context) {
        int networkType = ((TelephonyManager) context.getSystemService("phone")).getNetworkType();
        String str = "UNKOWN";
        switch (networkType) {
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
                return "1XRTT";
            case 8:
                return "HSDPA";
            case 9:
                return "HSUPA";
            case 10:
                return "HSPA";
            case 11:
                return "IDEN";
            case 12:
                return "EVDO_B";
            case 13:
                return "LTE";
            case 14:
                return "EHRPD";
            case 15:
                return "HSPAP";
            default:
                return networkType + "";
        }
    }

    public static String s(Context context) {
        String C = C(context);
        if (C == null) {
            return "";
        }
        return b(C.toUpperCase(Locale.CHINESE));
    }

    private static String C(Context context) {
        String a = a("wlan0");
        if (a != null) {
            return a;
        }
        a = a("eth0");
        if (a != null) {
            return a;
        }
        try {
            a = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            if (a != null) {
                return a;
            }
        } catch (Exception e) {
        }
        return null;
    }

    private static String a(String str) {
        try {
            String str2 = "/sys/class/net/" + str + "/address";
            StringBuilder stringBuilder = new StringBuilder(1000);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(str2), 1024);
            char[] cArr = new char[1024];
            while (true) {
                int read = bufferedReader.read(cArr);
                if (read != -1) {
                    stringBuilder.append(String.valueOf(cArr, 0, read));
                } else {
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
            }
        } catch (IOException e) {
            return null;
        }
    }

    private static String b(String str) {
        if (str == null) {
            return null;
        }
        CharSequence replaceAll = str.replaceAll("\\s", "");
        if (TextUtils.isEmpty(replaceAll)) {
            return null;
        }
        return replaceAll;
    }

    public static String t(Context context) {
        try {
            String simSerialNumber = ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
            if (simSerialNumber == null || simSerialNumber.trim().length() <= 0) {
                return "";
            }
            return simSerialNumber;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean c(Context context, String str) {
        try {
            ServiceInfo[] serviceInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4).services;
            if (serviceInfoArr == null) {
                return false;
            }
            for (ServiceInfo serviceInfo : serviceInfoArr) {
                if (serviceInfo.name.equals(str)) {
                    return true;
                }
            }
            return false;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String e() {
        String str = "";
        str = "";
        str = "0000000000000000";
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec("cat /proc/cpuinfo").getInputStream()));
            int i = 1;
            while (i < 100) {
                String readLine = lineNumberReader.readLine();
                if (readLine == null) {
                    break;
                } else if (readLine.contains("Serial")) {
                    str = readLine.substring(readLine.indexOf(":") + 1, readLine.length()).trim();
                    break;
                } else {
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String d(Context context, String str) {
        return a(context, str) ? "1" : "0";
    }

    public static List<String> a(Context context, long j) {
        Object obj;
        List<String> arrayList = new ArrayList();
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        switch ((int) j) {
            case 1:
                int i = 1;
                break;
            case 2:
                obj = null;
                break;
            default:
                obj = null;
                break;
        }
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            String str = resolveInfo.activityInfo.packageName;
            if (obj == null) {
                arrayList.add(str);
            } else if (a(context, str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public static boolean e(Context context, String str) {
        try {
            if (context.getPackageManager().getPackageInfo(str, 0) != null) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static List<String> u(Context context) {
        List<String> arrayList = new ArrayList();
        ActivityManager g = g(context);
        PackageManager packageManager = context.getPackageManager();
        try {
            for (RecentTaskInfo recentTaskInfo : g.getRecentTasks(8, 0)) {
                ResolveInfo resolveActivity = packageManager.resolveActivity(recentTaskInfo.baseIntent, 0);
                if (resolveActivity != null) {
                    arrayList.add(resolveActivity.activityInfo.packageName);
                }
            }
            return arrayList;
        } catch (SecurityException e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    public static String v(Context context) {
        int myPid = Process.myPid();
        for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == myPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String f() {
        /*
        r0 = "/proc/meminfo";
        r3 = "";
        r2 = 0;
        r4 = new java.io.FileReader;	 Catch:{ Exception -> 0x0033, all -> 0x0044 }
        r4.<init>(r0);	 Catch:{ Exception -> 0x0033, all -> 0x0044 }
        r1 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x0033, all -> 0x0044 }
        r0 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r1.<init>(r4, r0);	 Catch:{ Exception -> 0x0033, all -> 0x0044 }
        r2 = r1.readLine();	 Catch:{ Exception -> 0x0056, all -> 0x0051 }
        r0 = ":";
        r0 = r2.indexOf(r0);	 Catch:{ Exception -> 0x005b, all -> 0x0051 }
        r0 = r0 + 1;
        r0 = r2.substring(r0);	 Catch:{ Exception -> 0x005b, all -> 0x0051 }
        r0 = r0.trim();	 Catch:{ Exception -> 0x005b, all -> 0x0051 }
        if (r1 == 0) goto L_0x002d;
    L_0x002a:
        r1.close();	 Catch:{ IOException -> 0x002e }
    L_0x002d:
        return r0;
    L_0x002e:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x002d;
    L_0x0033:
        r0 = move-exception;
        r1 = r0;
        r0 = r3;
    L_0x0036:
        r1.printStackTrace();	 Catch:{ all -> 0x0053 }
        if (r2 == 0) goto L_0x002d;
    L_0x003b:
        r2.close();	 Catch:{ IOException -> 0x003f }
        goto L_0x002d;
    L_0x003f:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x002d;
    L_0x0044:
        r0 = move-exception;
        r1 = r2;
    L_0x0046:
        if (r1 == 0) goto L_0x004b;
    L_0x0048:
        r1.close();	 Catch:{ IOException -> 0x004c }
    L_0x004b:
        throw r0;
    L_0x004c:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x004b;
    L_0x0051:
        r0 = move-exception;
        goto L_0x0046;
    L_0x0053:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0046;
    L_0x0056:
        r0 = move-exception;
        r2 = r1;
        r1 = r0;
        r0 = r3;
        goto L_0x0036;
    L_0x005b:
        r0 = move-exception;
        r5 = r0;
        r0 = r2;
        r2 = r1;
        r1 = r5;
        goto L_0x0036;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sijla.j.a.a.f():java.lang.String");
    }

    public static String w(Context context) {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return Formatter.formatFileSize(context, ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize()));
    }

    public static String g() {
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(cls, new Object[]{"ro.serialno", ConfigBaseParser.DEFAULT_VALUE});
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int h() {
        if (a != 0) {
            return a;
        }
        try {
            a = new File("/sys/devices/system/cpu/").listFiles(new a()).length;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (a < 1) {
            a = Runtime.getRuntime().availableProcessors();
        }
        if (a < 1) {
            a = 1;
        }
        return a;
    }

    public static String i() {
        return String.valueOf((System.currentTimeMillis() - SystemClock.elapsedRealtime()) / 1000);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long[] a(int r9) {
        /*
        r0 = -1;
        r2 = 0;
        r6 = "getTrafficInfo";
        r3 = 2;
        r7 = new long[r3];
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "/proc/uid_stat/";
        r3 = r3.append(r4);
        r3 = r3.append(r9);
        r4 = "/tcp_rcv";
        r3 = r3.append(r4);
        r3 = r3.toString();
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "/proc/uid_stat/";
        r4 = r4.append(r5);
        r4 = r4.append(r9);
        r5 = "/tcp_snd";
        r4 = r4.append(r5);
        r8 = r4.toString();
        r5 = new java.io.RandomAccessFile;	 Catch:{ Exception -> 0x008d, all -> 0x00c1 }
        r4 = "r";
        r5.<init>(r3, r4);	 Catch:{ Exception -> 0x008d, all -> 0x00c1 }
        r4 = new java.io.RandomAccessFile;	 Catch:{ Exception -> 0x00f3, all -> 0x00ec }
        r3 = "r";
        r4.<init>(r8, r3);	 Catch:{ Exception -> 0x00f3, all -> 0x00ec }
        r2 = r5.readLine();	 Catch:{ Exception -> 0x00f7, all -> 0x00ef }
        r2 = java.lang.Long.parseLong(r2);	 Catch:{ Exception -> 0x00f7, all -> 0x00ef }
        r8 = r4.readLine();	 Catch:{ Exception -> 0x00fa, all -> 0x00ef }
        r0 = java.lang.Long.parseLong(r8);	 Catch:{ Exception -> 0x00fa, all -> 0x00ef }
        if (r5 == 0) goto L_0x0064;
    L_0x0061:
        r5.close();	 Catch:{ IOException -> 0x0070 }
    L_0x0064:
        if (r4 == 0) goto L_0x0069;
    L_0x0066:
        r4.close();	 Catch:{ IOException -> 0x0070 }
    L_0x0069:
        r4 = 0;
        r7[r4] = r2;
        r2 = 1;
        r7[r2] = r0;
        return r7;
    L_0x0070:
        r4 = move-exception;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r8 = "close randomAccessFile exception: ";
        r5 = r5.append(r8);
        r4 = r4.getMessage();
        r4 = r5.append(r4);
        r4 = r4.toString();
        com.sijla.j.b.a(r6, r4);
        goto L_0x0069;
    L_0x008d:
        r3 = move-exception;
        r4 = r2;
        r5 = r2;
        r2 = r0;
    L_0x0091:
        r2 = android.net.TrafficStats.getUidRxBytes(r9);	 Catch:{ Throwable -> 0x00f1, all -> 0x00ef }
        r0 = android.net.TrafficStats.getUidTxBytes(r9);	 Catch:{ Throwable -> 0x00f1, all -> 0x00ef }
    L_0x0099:
        if (r5 == 0) goto L_0x009e;
    L_0x009b:
        r5.close();	 Catch:{ IOException -> 0x00a4 }
    L_0x009e:
        if (r4 == 0) goto L_0x0069;
    L_0x00a0:
        r4.close();	 Catch:{ IOException -> 0x00a4 }
        goto L_0x0069;
    L_0x00a4:
        r4 = move-exception;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r8 = "close randomAccessFile exception: ";
        r5 = r5.append(r8);
        r4 = r4.getMessage();
        r4 = r5.append(r4);
        r4 = r4.toString();
        com.sijla.j.b.a(r6, r4);
        goto L_0x0069;
    L_0x00c1:
        r0 = move-exception;
        r4 = r2;
        r5 = r2;
    L_0x00c4:
        if (r5 == 0) goto L_0x00c9;
    L_0x00c6:
        r5.close();	 Catch:{ IOException -> 0x00cf }
    L_0x00c9:
        if (r4 == 0) goto L_0x00ce;
    L_0x00cb:
        r4.close();	 Catch:{ IOException -> 0x00cf }
    L_0x00ce:
        throw r0;
    L_0x00cf:
        r1 = move-exception;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "close randomAccessFile exception: ";
        r2 = r2.append(r3);
        r1 = r1.getMessage();
        r1 = r2.append(r1);
        r1 = r1.toString();
        com.sijla.j.b.a(r6, r1);
        goto L_0x00ce;
    L_0x00ec:
        r0 = move-exception;
        r4 = r2;
        goto L_0x00c4;
    L_0x00ef:
        r0 = move-exception;
        goto L_0x00c4;
    L_0x00f1:
        r8 = move-exception;
        goto L_0x0099;
    L_0x00f3:
        r3 = move-exception;
        r4 = r2;
        r2 = r0;
        goto L_0x0091;
    L_0x00f7:
        r2 = move-exception;
        r2 = r0;
        goto L_0x0091;
    L_0x00fa:
        r8 = move-exception;
        goto L_0x0091;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sijla.j.a.a.a(int):long[]");
    }

    public static boolean f(Context context, String str) {
        try {
            if (context.getPackageManager().checkPermission(str, context.getPackageName()) == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void g(Context context, String str) {
        try {
            j.a(context, "usrgid", str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static Class x(Context context) {
        Exception e;
        Class cls = null;
        List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(4);
        if (installedPackages != null) {
            String packageName = context.getPackageName();
            for (PackageInfo packageInfo : installedPackages) {
                Class cls2;
                try {
                    if (packageInfo.packageName.equals(packageName)) {
                        ServiceInfo[] serviceInfoArr = packageInfo.services;
                        if (serviceInfoArr != null) {
                            int length = serviceInfoArr.length;
                            cls2 = cls;
                            int i = 0;
                            while (i < length) {
                                try {
                                    ServiceInfo serviceInfo = serviceInfoArr[i];
                                    if (serviceInfo.name.equals(HBS.class.getName())) {
                                        cls2 = HBS.class;
                                    } else if (serviceInfo.name.equals(QS.class.getName())) {
                                        cls2 = QS.class;
                                    }
                                    i++;
                                } catch (Exception e2) {
                                    e = e2;
                                }
                            }
                            cls = cls2;
                        }
                    }
                    cls2 = cls;
                } catch (Exception e3) {
                    Exception exception = e3;
                    cls2 = cls;
                    e = exception;
                    e.printStackTrace();
                    cls = cls2;
                }
                cls = cls2;
            }
            f.a("GrowthServiceClass:" + cls.getName());
        }
        return cls;
    }

    public static String y(Context context) {
        String str = "";
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                return telephonyManager.getNetworkOperator();
            }
            return str;
        } catch (Exception e) {
            return str;
        }
    }

    public static int z(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getPhoneType();
        } catch (Exception e) {
            return 0;
        }
    }

    public static String A(Context context) {
        String str = "";
        try {
            NetworkInfo B = B(context);
            if (B != null && B.isConnected()) {
                if (B.getType() == 1) {
                    return "WIFI";
                }
                if (B.getType() == 0) {
                    String subtypeName = B.getSubtypeName();
                    switch (B.getSubtype()) {
                        case 1:
                        case 2:
                        case 4:
                        case 7:
                        case 11:
                            return "2G";
                        case 3:
                        case 5:
                        case 6:
                        case 8:
                        case 9:
                        case 10:
                        case 12:
                        case 14:
                        case 15:
                            return "3G";
                        case 13:
                            return "4G";
                        default:
                            if (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) {
                                return "3G";
                            }
                            return subtypeName;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}
