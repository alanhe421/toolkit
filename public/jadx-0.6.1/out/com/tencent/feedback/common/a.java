package com.tencent.feedback.common;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Process;
import android.util.Log;
import com.tencent.android.tpush.common.Constants;
import java.io.FileReader;
import java.util.List;

/* compiled from: RQDSRC */
public final class a {
    private static String a = null;
    private static Boolean b = null;
    private static Boolean c = null;
    private static boolean d = false;

    public static String a(Context context) {
        if (context == null) {
            return "";
        }
        try {
            Object obj = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.get("APPKEY_DENGTA");
            if (obj != null) {
                return obj.toString();
            }
        } catch (Throwable th) {
            e.a("rqdp{  no appkey !!}", new Object[0]);
        }
        return "";
    }

    public static String b(Context context) {
        if (context == null) {
            return null;
        }
        return context.getPackageName();
    }

    public static synchronized String c(Context context) {
        String str;
        int i = 0;
        synchronized (a.class) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context == null ? null : context.getPackageName(), 0);
                String str2 = packageInfo.versionName;
                int i2 = packageInfo.versionCode;
                if (str2 == null || str2.trim().length() <= 0) {
                    str = "";
                } else {
                    char[] toCharArray = str2.toCharArray();
                    for (char c : toCharArray) {
                        if (c == '.') {
                            i++;
                        }
                    }
                    if (i < 3) {
                        str = str2 + "." + i2;
                    } else {
                        str = str2;
                    }
                    e.a("rqdp{  version:} %s", str);
                }
            } catch (Throwable th) {
                if (!e.a(th)) {
                    th.printStackTrace();
                }
                str = null;
            }
        }
        return str;
    }

    public static String d(Context context) {
        e.b("rqdp{AppInfo.getUUID() Start}", new Object[0]);
        if (context == null) {
            e.d("context == null", new Object[0]);
            return "";
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null || applicationInfo.metaData == null) {
                e.d("appInfo == null || appInfo.metaData == null", new Object[0]);
                return "";
            }
            Object obj = applicationInfo.metaData.get("com.tencent.rdm.uuid");
            String obj2 = obj != null ? obj.toString() : "";
            Log.d("rqdp{ RDMUUID }:%s", obj2);
            return obj2;
        } catch (Throwable th) {
            if (!e.a(th)) {
                th.printStackTrace();
            }
            e.d(th.toString(), new Object[0]);
            return "";
        }
    }

    public static synchronized boolean e(Context context) {
        boolean z = false;
        synchronized (a.class) {
            e.b("rqdp{  Read Log Permittion! start}", new Object[0]);
            if (context != null) {
                if (b == null) {
                    b = Boolean.valueOf(c(context, "android.permission.READ_LOGS"));
                }
                z = b.booleanValue();
            }
        }
        return z;
    }

    public static synchronized boolean f(Context context) {
        boolean z = false;
        synchronized (a.class) {
            e.b("rqdp{  Read write Permittion! start}", new Object[0]);
            if (context != null) {
                if (c == null) {
                    c = Boolean.valueOf(c(context, "android.permission.WRITE_EXTERNAL_STORAGE"));
                }
                z = c.booleanValue();
            }
        }
        return z;
    }

    public static boolean g(Context context) {
        return b(context, context.getPackageName());
    }

    private static boolean c(Context context, String str) {
        e.b("rqdp{  AppInfo.isContainReadLogPermission() start}", new Object[0]);
        if (context == null || str == null || str.trim().length() <= 0) {
            return false;
        }
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            if (strArr != null) {
                for (Object equals : strArr) {
                    if (str.equals(equals)) {
                        e.b("rqdp{  AppInfo.isContainReadLogPermission() end}", new Object[0]);
                        return true;
                    }
                }
            }
            e.b("rqdp{  AppInfo.isContainReadLogPermission() end}", new Object[0]);
            return false;
        } catch (Throwable th) {
            e.b("rqdp{  AppInfo.isContainReadLogPermission() end}", new Object[0]);
        }
    }

    public static String a(Context context, String str) {
        String str2 = null;
        if (!(context == null || str == null)) {
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 0);
                if (applicationInfo != null) {
                    str2 = applicationInfo.sourceDir;
                }
            } catch (Throwable th) {
                if (!e.a(th)) {
                    th.printStackTrace();
                }
            }
        }
        return str2;
    }

    public static String h(Context context) {
        if (context == null) {
            return null;
        }
        String a = a(context, context.getPackageName());
        if (a != null) {
            return com.tencent.feedback.proguard.a.b(a);
        }
        e.d("rqdp{  No found the apk file on the device,please check it!}", new Object[0]);
        return null;
    }

    public static boolean b(Context context, String str) {
        if (context == null || str == null || str.trim().length() <= 0) {
            return false;
        }
        try {
            List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses();
            if (runningAppProcesses == null || runningAppProcesses.size() == 0) {
                e.b("rqdp{  no running proc}", new Object[0]);
                return false;
            }
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.importance == 100) {
                    for (Object equals : runningAppProcessInfo.pkgList) {
                        if (str.equals(equals)) {
                            e.b("rqdp{  current seen pn:}%s", runningAppProcessInfo.processName);
                            return true;
                        }
                    }
                    continue;
                }
            }
            e.b("rqdp{  current unseen pn:}%s", str);
            return false;
        } catch (Throwable th) {
            if (!e.a(th)) {
                th.printStackTrace();
            }
        }
    }

    public static String a(int i) {
        FileReader fileReader;
        String substring;
        Throwable th;
        int i2 = 0;
        try {
            fileReader = new FileReader("/proc/" + i + "/cmdline");
            try {
                char[] cArr = new char[128];
                fileReader.read(cArr);
                while (i2 < cArr.length && cArr[i2] != '\u0000') {
                    i2++;
                }
                substring = new String(cArr).substring(0, i2);
                try {
                    fileReader.close();
                } catch (Throwable th2) {
                }
            } catch (Throwable th3) {
                th = th3;
                try {
                    if (!e.a(th)) {
                        th.printStackTrace();
                    }
                    substring = String.valueOf(i);
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (Throwable th4) {
                        }
                    }
                    return substring;
                } catch (Throwable th5) {
                    th = th5;
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (Throwable th6) {
                        }
                    }
                    throw th;
                }
            }
        } catch (Throwable th7) {
            th = th7;
            fileReader = null;
            if (fileReader != null) {
                fileReader.close();
            }
            throw th;
        }
        return substring;
    }

    public static String i(Context context) {
        int i = -1;
        try {
            i = Process.myPid();
            return a(i);
        } catch (Throwable th) {
            if (!e.a(th)) {
                th.printStackTrace();
            }
            return i + ":" + th.getClass().getName() + ":" + th.getMessage();
        }
    }
}
