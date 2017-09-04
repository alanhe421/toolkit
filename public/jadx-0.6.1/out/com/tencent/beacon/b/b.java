package com.tencent.beacon.b;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.os.Build.VERSION;
import android.os.Process;
import android.util.Base64;
import com.dynamicload.Lib.DLConstants;
import com.tencent.android.tpush.common.Constants;
import com.tencent.beacon.e.a;
import java.util.Date;
import java.util.List;

/* compiled from: ProGuard */
public class b {
    public static int a = 0;
    public static boolean b = false;
    private static String c = null;
    private static String d = null;
    private static Boolean e = null;
    private static String f = "";
    private static byte[] g = new byte[]{(byte) 33, (byte) 94, (byte) 120, (byte) 74, (byte) 111, (byte) 43, (byte) 35};

    @SuppressLint({"NewApi"})
    public static String a(Context context) {
        try {
            if (Integer.parseInt(VERSION.SDK) < 9) {
                a.c("Api level < 9;return null!", new Object[0]);
                return "";
            }
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            a.b("get app_last_updated_time:" + String.valueOf(packageInfo.lastUpdateTime), new Object[0]);
            return String.valueOf(packageInfo.lastUpdateTime);
        } catch (Throwable e) {
            a.a(e);
            a.c("get app_last_updated_time failed!", new Object[0]);
            return "";
        }
    }

    @SuppressLint({"NewApi"})
    public static String b(Context context) {
        try {
            if (Integer.parseInt(VERSION.SDK) < 9) {
                a.c("Api level < 9,return null!", new Object[0]);
                return "";
            }
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            a.b("get app_first_installed_time:" + String.valueOf(packageInfo.firstInstallTime), new Object[0]);
            return String.valueOf(packageInfo.firstInstallTime);
        } catch (Throwable e) {
            a.a(e);
            a.c("get app_first_installed_time failed!!!", new Object[0]);
            return "";
        }
    }

    public static synchronized String c(Context context) {
        String str;
        synchronized (b.class) {
            if (context == null) {
                str = "";
            } else {
                try {
                    Object obj = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.get("APPKEY_DENGTA");
                    if (obj != null) {
                        str = obj.toString().trim();
                    }
                } catch (Throwable th) {
                    a.d("no appkey !! ", new Object[0]);
                }
                str = "";
            }
        }
        return str;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized java.lang.String d(android.content.Context r9) {
        /*
        r3 = com.tencent.beacon.b.b.class;
        monitor-enter(r3);
        if (r9 != 0) goto L_0x000a;
    L_0x0005:
        r0 = "";
    L_0x0008:
        monitor-exit(r3);
        return r0;
    L_0x000a:
        r2 = "";
        r0 = 0;
        r4 = r9.getAssets();	 Catch:{ all -> 0x00a7 }
        r1 = "key_channelpath";
        r5 = "";
        r6 = "DENGTA_META";
        r7 = 0;
        r6 = r9.getSharedPreferences(r6, r7);	 Catch:{ Exception -> 0x00d8, all -> 0x0109 }
        r1 = r6.getString(r1, r5);	 Catch:{ Exception -> 0x00d8, all -> 0x0109 }
        r5 = "";
        r5 = r1.equals(r5);	 Catch:{ Exception -> 0x00d8, all -> 0x0109 }
        if (r5 == 0) goto L_0x0046;
    L_0x002d:
        r1 = "channel.ini";
        r5 = "key_channelpath";
        r6 = "DENGTA_META";
        r7 = 0;
        r6 = r9.getSharedPreferences(r6, r7);	 Catch:{ Exception -> 0x00d8, all -> 0x0109 }
        r6 = r6.edit();	 Catch:{ Exception -> 0x00d8, all -> 0x0109 }
        r5 = r6.putString(r5, r1);	 Catch:{ Exception -> 0x00d8, all -> 0x0109 }
        r5.commit();	 Catch:{ Exception -> 0x00d8, all -> 0x0109 }
    L_0x0046:
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00d8, all -> 0x0109 }
        r6 = "channel path!! ";
        r5.<init>(r6);	 Catch:{ Exception -> 0x00d8, all -> 0x0109 }
        r5 = r5.append(r1);	 Catch:{ Exception -> 0x00d8, all -> 0x0109 }
        r5 = r5.toString();	 Catch:{ Exception -> 0x00d8, all -> 0x0109 }
        r6 = 0;
        r6 = new java.lang.Object[r6];	 Catch:{ Exception -> 0x00d8, all -> 0x0109 }
        com.tencent.beacon.e.a.a(r5, r6);	 Catch:{ Exception -> 0x00d8, all -> 0x0109 }
        r5 = "";
        r5 = r1.equals(r5);	 Catch:{ Exception -> 0x00d8, all -> 0x0109 }
        if (r5 != 0) goto L_0x0137;
    L_0x0065:
        r1 = r4.open(r1);	 Catch:{ Exception -> 0x00d8, all -> 0x0109 }
        r0 = new java.util.Properties;	 Catch:{ Exception -> 0x012b, all -> 0x0124 }
        r0.<init>();	 Catch:{ Exception -> 0x012b, all -> 0x0124 }
        r0.load(r1);	 Catch:{ Exception -> 0x012b, all -> 0x0124 }
        r4 = "CHANNEL";
        r5 = "";
        r0 = r0.getProperty(r4, r5);	 Catch:{ Exception -> 0x012b, all -> 0x0124 }
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x012f, all -> 0x0124 }
        r4 = "channel !! ";
        r2.<init>(r4);	 Catch:{ Exception -> 0x012f, all -> 0x0124 }
        r2 = r2.append(r0);	 Catch:{ Exception -> 0x012f, all -> 0x0124 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x012f, all -> 0x0124 }
        r4 = 0;
        r4 = new java.lang.Object[r4];	 Catch:{ Exception -> 0x012f, all -> 0x0124 }
        com.tencent.beacon.e.a.a(r2, r4);	 Catch:{ Exception -> 0x012f, all -> 0x0124 }
        r2 = "";
        r2 = r2.equals(r0);	 Catch:{ Exception -> 0x012f, all -> 0x0124 }
        if (r2 != 0) goto L_0x00aa;
    L_0x009a:
        if (r1 == 0) goto L_0x0008;
    L_0x009c:
        r1.close();	 Catch:{ IOException -> 0x00a1 }
        goto L_0x0008;
    L_0x00a1:
        r1 = move-exception;
        com.tencent.beacon.e.a.a(r1);	 Catch:{ all -> 0x00a7 }
        goto L_0x0008;
    L_0x00a7:
        r0 = move-exception;
        monitor-exit(r3);
        throw r0;
    L_0x00aa:
        r8 = r1;
        r1 = r0;
        r0 = r8;
    L_0x00ad:
        if (r0 == 0) goto L_0x0134;
    L_0x00af:
        r0.close();	 Catch:{ IOException -> 0x00d2 }
        r0 = r1;
    L_0x00b3:
        r1 = r9.getPackageManager();	 Catch:{ Throwable -> 0x0118 }
        r2 = r9.getPackageName();	 Catch:{ Throwable -> 0x0118 }
        r4 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r1 = r1.getApplicationInfo(r2, r4);	 Catch:{ Throwable -> 0x0118 }
        r1 = r1.metaData;	 Catch:{ Throwable -> 0x0118 }
        r2 = "CHANNEL_DENGTA";
        r1 = r1.get(r2);	 Catch:{ Throwable -> 0x0118 }
        if (r1 == 0) goto L_0x0008;
    L_0x00cc:
        r0 = r1.toString();	 Catch:{ Throwable -> 0x0118 }
        goto L_0x0008;
    L_0x00d2:
        r0 = move-exception;
        com.tencent.beacon.e.a.a(r0);	 Catch:{ all -> 0x00a7 }
        r0 = r1;
        goto L_0x00b3;
    L_0x00d8:
        r1 = move-exception;
        r1 = r2;
    L_0x00da:
        r2 = "key_channelpath";
        r4 = "";
        r5 = "DENGTA_META";
        r6 = 0;
        r5 = r9.getSharedPreferences(r5, r6);	 Catch:{ all -> 0x0126 }
        r5 = r5.edit();	 Catch:{ all -> 0x0126 }
        r2 = r5.putString(r2, r4);	 Catch:{ all -> 0x0126 }
        r2.commit();	 Catch:{ all -> 0x0126 }
        r2 = "get app channel fail!";
        r4 = 0;
        r4 = new java.lang.Object[r4];	 Catch:{ all -> 0x0126 }
        com.tencent.beacon.e.a.c(r2, r4);	 Catch:{ all -> 0x0126 }
        if (r0 == 0) goto L_0x0134;
    L_0x00fe:
        r0.close();	 Catch:{ IOException -> 0x0103 }
        r0 = r1;
        goto L_0x00b3;
    L_0x0103:
        r0 = move-exception;
        com.tencent.beacon.e.a.a(r0);	 Catch:{ all -> 0x00a7 }
        r0 = r1;
        goto L_0x00b3;
    L_0x0109:
        r1 = move-exception;
        r8 = r1;
        r1 = r0;
        r0 = r8;
    L_0x010d:
        if (r1 == 0) goto L_0x0112;
    L_0x010f:
        r1.close();	 Catch:{ IOException -> 0x0113 }
    L_0x0112:
        throw r0;	 Catch:{ all -> 0x00a7 }
    L_0x0113:
        r1 = move-exception;
        com.tencent.beacon.e.a.a(r1);	 Catch:{ all -> 0x00a7 }
        goto L_0x0112;
    L_0x0118:
        r1 = move-exception;
        r1 = "no channel !!";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x00a7 }
        com.tencent.beacon.e.a.a(r1, r2);	 Catch:{ all -> 0x00a7 }
        goto L_0x0008;
    L_0x0124:
        r0 = move-exception;
        goto L_0x010d;
    L_0x0126:
        r1 = move-exception;
        r8 = r1;
        r1 = r0;
        r0 = r8;
        goto L_0x010d;
    L_0x012b:
        r0 = move-exception;
        r0 = r1;
        r1 = r2;
        goto L_0x00da;
    L_0x012f:
        r2 = move-exception;
        r8 = r1;
        r1 = r0;
        r0 = r8;
        goto L_0x00da;
    L_0x0134:
        r0 = r1;
        goto L_0x00b3;
    L_0x0137:
        r1 = r2;
        goto L_0x00ad;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.beacon.b.b.d(android.content.Context):java.lang.String");
    }

    public static synchronized boolean e(Context context) {
        boolean z = false;
        synchronized (b.class) {
            if (context == null) {
                a.d("context == null return null", new Object[0]);
            } else {
                try {
                    String string = context.getSharedPreferences("DENGTA_META", 4).getString("APPKEY_DENGTA", null);
                    String c = c(context);
                    if (string == null || !string.equals(c)) {
                        z = true;
                        Editor edit = context.getSharedPreferences("DENGTA_META", 0).edit();
                        edit.putString("APPKEY_DENGTA", c);
                        edit.commit();
                    }
                } catch (Throwable e) {
                    a.b("updateLocalAPPKEY fail!", new Object[0]);
                    a.a(e);
                }
            }
        }
        return z;
    }

    public static boolean f(Context context) {
        boolean z;
        Throwable e;
        if (context == null) {
            a.d("context == null return null", new Object[0]);
            return false;
        }
        try {
            String string = context.getSharedPreferences("DENGTA_META", 0).getString("APPVER_DENGTA", null);
            String h = h(context);
            if (string != null && string.equals(h)) {
                return false;
            }
            z = true;
            try {
                Editor edit = context.getSharedPreferences("DENGTA_META", 0).edit();
                edit.putString("APPVER_DENGTA", h);
                edit.commit();
                return true;
            } catch (Exception e2) {
                e = e2;
                a.b("updateLocalAPPKEY fail!", new Object[0]);
                a.a(e);
                return z;
            }
        } catch (Throwable e3) {
            e = e3;
            z = false;
            a.b("updateLocalAPPKEY fail!", new Object[0]);
            a.a(e);
            return z;
        }
    }

    public static String g(Context context) {
        if (context == null) {
            return null;
        }
        return context.getPackageName();
    }

    public static synchronized String h(Context context) {
        String str;
        int i = 0;
        synchronized (b.class) {
            if (c != null) {
                str = c;
            } else {
                try {
                    PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context == null ? null : context.getPackageName(), 0);
                    String str2 = packageInfo.versionName;
                    int i2 = packageInfo.versionCode;
                    if (str2 == null || str2.trim().length() <= 0) {
                        str = i2;
                    } else {
                        str2 = str2.trim().replace('\n', ' ').replace('\r', ' ').replace(DLConstants.DEPENDENCY_PACKAGE_DIV, "%7C");
                        char[] toCharArray = str2.toCharArray();
                        int i3 = 0;
                        while (i < toCharArray.length) {
                            if (toCharArray[i] == '.') {
                                i3++;
                            }
                            i++;
                        }
                        if (i3 < 3) {
                            a.a("add versionCode: %s", Integer.valueOf(i2));
                            str = str2 + "." + i2;
                        } else {
                            str = str2;
                        }
                        a.a("version: %s", str);
                    }
                } catch (Throwable e) {
                    a.a(e);
                    a.d(e.toString(), new Object[0]);
                    str = "";
                }
            }
        }
        return str;
    }

    public static void a(String str) {
        synchronized (b.class) {
            c = str;
        }
        d m = d.m();
        if (m != null) {
            m.a(str);
        }
    }

    public static synchronized boolean i(Context context) {
        boolean booleanValue;
        synchronized (b.class) {
            a.b("Read phone state permission check! start ", new Object[0]);
            if (e == null) {
                e = Boolean.valueOf(f(context, "android.permission.READ_PHONE_STATE"));
            }
            booleanValue = e.booleanValue();
        }
        return booleanValue;
    }

    public static boolean j(Context context) {
        return g(context, context.getPackageName());
    }

    public static String a() {
        if (!"".equals(f)) {
            return f;
        }
        try {
            if (a == 0) {
                a = Process.myPid();
            }
            f += a + "_";
            f += new Date().getTime();
        } catch (Exception e) {
        }
        return f;
    }

    public static int k(Context context) {
        try {
            if (a == 0) {
                a = Process.myPid();
            }
            for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == a) {
                    return runningAppProcessInfo.importance;
                }
            }
        } catch (Exception e) {
        }
        return 0;
    }

    private static boolean f(Context context, String str) {
        boolean z = true;
        if (context == null || str == null) {
            a.d("context or permission is null.", new Object[0]);
            return false;
        }
        boolean z2 = context.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
        if (z2) {
            return z2;
        }
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            if (strArr != null) {
                for (Object equals : strArr) {
                    if (str.equals(equals)) {
                        break;
                    }
                }
            }
            z = z2;
            a.b("AppInfo.isContainReadLogPermission() end", new Object[0]);
            return z;
        } catch (Throwable th) {
            a.b("AppInfo.isContainReadLogPermission() end", new Object[0]);
        }
    }

    public static String l(Context context) {
        if (d != null) {
            return d;
        }
        try {
            if (a == 0) {
                a = Process.myPid();
            }
            for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == a) {
                    String str = runningAppProcessInfo.processName;
                    d = str;
                    return str;
                }
            }
        } catch (Throwable th) {
            a.a(th);
        }
        return "";
    }

    private static boolean g(Context context, String str) {
        if (context == null || str == null || str.trim().length() <= 0) {
            return false;
        }
        try {
            List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses();
            if (runningAppProcesses == null || runningAppProcesses.size() == 0) {
                a.b("no running proc", new Object[0]);
                return false;
            }
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.importance == 100) {
                    for (Object equals : runningAppProcessInfo.pkgList) {
                        if (str.equals(equals)) {
                            return true;
                        }
                    }
                    continue;
                }
            }
            return false;
        } catch (Throwable th) {
            a.a(th);
            a.d("Failed to judge }[%s]", th.getLocalizedMessage());
        }
    }

    public static Editor m(Context context) {
        return context.getSharedPreferences("DENGTA_META", 0).edit();
    }

    public static boolean a(Context context, String str, String str2) {
        return context.getSharedPreferences("DENGTA_META", 0).edit().putString(str, str2).commit();
    }

    public static boolean a(Context context, String str, int i) {
        return context.getSharedPreferences("DENGTA_META", 0).edit().putInt(str, i).commit();
    }

    public static boolean a(Context context, String str, long j) {
        return context.getSharedPreferences("DENGTA_META", 0).edit().putLong(str, j).commit();
    }

    public static String b(Context context, String str, String str2) {
        return context.getSharedPreferences("DENGTA_META", 0).getString(str, str2);
    }

    public static int a(Context context, String str) {
        return context.getSharedPreferences("DENGTA_META", 0).getInt(str, 0);
    }

    public static long b(Context context, String str) {
        return context.getSharedPreferences("DENGTA_META", 0).getLong(str, 0);
    }

    public static void c(Context context, String str) {
        context.getSharedPreferences("DENGTA_META", 0).edit().putString("key_channelpath", str).commit();
    }

    public static void d(Context context, String str) {
        context.getSharedPreferences("DENGTA_META", 0).edit().putString("querytimes", str).commit();
    }

    public static void e(Context context, String str) {
        context.getSharedPreferences("DENGTA_META", 0).edit().putString("initsdkdate", str).commit();
    }

    public static String a(Context context, String str, String str2, String str3) {
        int i = 0;
        SharedPreferences sharedPreferences = context.getSharedPreferences("DENGTA_META", 0);
        String string = sharedPreferences.getString(str, "");
        if (string == null || string.trim().equals("")) {
            string = sharedPreferences.getString(str2, "");
            if (string == null || string.trim().equals("")) {
                return str3;
            }
            byte[] decode = Base64.decode(string, 2);
            int i2 = 0;
            while (i < decode.length) {
                decode[i] = (byte) (decode[i] ^ g[i2]);
                i2 = (i2 + 1) % g.length;
                i++;
            }
            return new String(decode);
        }
        sharedPreferences.edit().remove(str).putString(str2, b(string)).commit();
        return string;
    }

    public static boolean c(Context context, String str, String str2) {
        return context.getSharedPreferences("DENGTA_META", 0).edit().putString(str, b(str2)).commit();
    }

    private static String b(String str) {
        int i = 0;
        byte[] bytes = str.getBytes();
        int i2 = 0;
        while (i < bytes.length) {
            bytes[i] = (byte) (bytes[i] ^ g[i2]);
            i2 = (i2 + 1) % g.length;
            i++;
        }
        return Base64.encodeToString(bytes, 2);
    }
}
