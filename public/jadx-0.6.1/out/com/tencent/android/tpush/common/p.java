package com.tencent.android.tpush.common;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import com.tencent.android.tpush.XGPush4Msdk;
import com.tencent.android.tpush.XGPushActivity;
import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.XGPushReceiver;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.rpc.XGRemoteService;
import com.tencent.android.tpush.service.XGPushService;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import com.tencent.android.tpush.service.d.f;
import com.tencent.android.tpush.service.m;
import com.tencent.android.tpush.service.v;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: ProGuard */
public class p {
    private static AtomicBoolean a = new AtomicBoolean(false);
    private static boolean b = false;

    public static String a(String str) {
        if (str == null) {
            return "0";
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                int i = b & 255;
                if (i < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i));
            }
            return stringBuffer.toString();
        } catch (Throwable th) {
            return "0";
        }
    }

    public static int a(Context context) {
        if (a.get()) {
            return 0;
        }
        try {
            if (XGPushManager.getContext() == null) {
                XGPushManager.setContext(context);
            }
            if (m.e() == null) {
                m.c(context);
            }
            if (!h(context)) {
                a.i("Util", "XG is disable");
                return Constants.CODE_SERVICE_DISABLED;
            } else if (!TpnsSecurity.checkTpnsSecurityLibSo(context)) {
                a.i("Util", "can not load library from so file");
                return 10004;
            } else if (!l.a()) {
                return 10003;
            } else {
                if (b(context)) {
                    f.n(context);
                    a.set(true);
                    return 0;
                }
                a.i("Util", "The service rpc.XGRemoteService is unfined, Please add it in AndroidManifest.xml");
                return -10005;
            }
        } catch (Throwable th) {
            a.c("Util", "Util -> initGlobal", th);
            return -1;
        }
    }

    public static boolean b(Context context) {
        try {
            List a = f.a(context, context.getPackageName() + Constants.RPC_SUFFIX);
            if (a != null && a.size() > 0) {
                return true;
            }
        } catch (Throwable th) {
            a.c("Util", "Util -> isAIDLConfiged", th);
        }
        return false;
    }

    public static boolean b(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String c(Context context) {
        if (context != null) {
            return Rijndael.decrypt(f.a(context, Constants.SETTINGS_SERVICE_PACKAGE_NAME, false));
        }
        return "";
    }

    public static int d(Context context) {
        if (context != null) {
            try {
                List<RunningServiceInfo> runningServices = ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningServices(Integer.MAX_VALUE);
                if (runningServices != null && runningServices.size() > 0) {
                    String name = XGPushService.class.getName();
                    for (RunningServiceInfo runningServiceInfo : runningServices) {
                        String className = runningServiceInfo.service.getClassName();
                        if (!name.equals(className)) {
                            if ("com.tencent.android.tpush.service.XGPushService".equals(className)) {
                            }
                        }
                        return runningServiceInfo.pid != 0 ? 1 : 2;
                    }
                }
            } catch (Throwable th) {
                a.c(Constants.ServiceLogTag, "getServiceStatus", th);
            }
        }
        return 0;
    }

    public static void e(Context context) {
        if (context != null) {
            try {
                m.c(context.getApplicationContext());
                List a = f.a(context);
                if (a == null || o.a(context).a() || a.size() < 1 || (a.size() < 2 && ((ResolveInfo) a.get(0)).activityInfo.packageName.equals(context.getPackageName()))) {
                    m.a(context);
                    a.a(Constants.ServiceLogTag, "Action -> start Local Service()");
                    g.a().a(new q(context), 1500);
                }
                context.sendBroadcast(new Intent(Constants.ACTION_SDK_INSTALL));
                g.a().a(new q(context), 1500);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static boolean a(Context context, BroadcastReceiver broadcastReceiver) {
        try {
            context.unregisterReceiver(broadcastReceiver);
            return true;
        } catch (Throwable e) {
            a.c("Util", "safeUnregisterReceiver error", e);
            return false;
        }
    }

    public static String f(Context context) {
        String str;
        Throwable th;
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
            a.c("Util", "get app version error", th);
            return str;
        }
    }

    private static void a(Context context, String str) {
        if (context != null && str != null && str.trim().length() != 0) {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                ComponentName componentName = new ComponentName(context.getPackageName(), str);
                if (packageManager.getComponentEnabledSetting(componentName) != 1) {
                    packageManager.setComponentEnabledSetting(componentName, 1, 1);
                }
            }
        }
    }

    public static void g(Context context) {
        if (context != null && !b) {
            try {
                a(context, XGPushService.class.getName());
                a(context, XGPushActivity.class.getName());
                a(context, XGRemoteService.class.getName());
                for (ActivityInfo activityInfo : context.getPackageManager().getPackageInfo(context.getPackageName(), 2).receivers) {
                    String str = activityInfo.name;
                    Class loadClass = context.getClassLoader().loadClass(str);
                    if (XGPushBaseReceiver.class.isAssignableFrom(loadClass) || loadClass.getName().equals(XGPushReceiver.class.getName())) {
                        a(context, str);
                    }
                }
            } catch (Throwable e) {
                a.c("Util", "enableComponents", e);
            }
            b = true;
        }
    }

    public static String a(long j) {
        try {
            return new SimpleDateFormat("yyyyMMdd").format(Long.valueOf(j));
        } catch (Throwable e) {
            a.c("Util", "getDateString", e);
            return "20141111";
        }
    }

    public static boolean h(Context context) {
        if (context == null) {
            return true;
        }
        XGPushManager.enableService = f.b(context, context.getPackageName() + XGPushManager.ENABLE_SERVICE_SUFFIX, XGPushManager.enableService);
        if (XGPushManager.enableService == -1) {
            XGPushManager.enableService = f.b(context, context.getPackageName() + XGPushManager.ENABLE_SERVICE_SUFFIX, 2);
        }
        if (XGPushManager.enableService == 2 && TpnsSecurity.checkTpnsSecurityLibSo(context)) {
            String str = com.tencent.android.tpush.service.a.a.a(context).x;
            if (!b(str)) {
                String[] split = Rijndael.decrypt(str).split(",");
                Map hashMap = new HashMap();
                for (String valueOf : split) {
                    try {
                        hashMap.put(Long.valueOf(valueOf), Long.valueOf(0));
                    } catch (NumberFormatException e) {
                    }
                }
                if (hashMap.size() > 0) {
                    if (XGPushConfig.getAccessId(context) > 0 && hashMap.containsKey(Long.valueOf(XGPushConfig.getAccessId(context)))) {
                        XGPushManager.enableService(context, false);
                        return false;
                    } else if (XGPush4Msdk.getQQAccessId(context) > 0 && hashMap.containsKey(Long.valueOf(XGPush4Msdk.getQQAccessId(context)))) {
                        XGPushManager.enableService(context, false);
                        return false;
                    }
                }
            }
        }
        if (XGPushManager.enableService != 0) {
            return true;
        }
        return false;
    }

    public static boolean i(Context context) {
        try {
            return ((PowerManager) context.getSystemService("power")).isScreenOn();
        } catch (Throwable e) {
            a.c(Constants.LogTag, "Util -> isScreenOn", e);
            return false;
        }
    }

    public static int j(Context context) {
        int i = -1;
        try {
            Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            int intExtra = registerReceiver.getIntExtra("status", -1);
            Object obj = (intExtra == 2 || intExtra == 5) ? 1 : null;
            if (obj != null) {
                i = registerReceiver.getIntExtra("plugged", -1);
            }
        } catch (Throwable e) {
            a.c(Constants.LogTag, "Util -> getChangedStatus", e);
        }
        return i;
    }

    public static void k(Context context) {
        if (context == null) {
            a.h("Util", "Util -> getWakeCpu error null context");
            return;
        }
        try {
            v.a().a(((PowerManager) context.getSystemService("power")).newWakeLock(1, "TPUSH"));
            if (!v.a().b().isHeld()) {
                v.a().b().acquire();
            }
            a.c("Util", "get Wake Cpu ");
        } catch (Throwable th) {
            a.c("Util", "get Wake cpu", th);
        }
    }

    public static void a() {
        try {
            WakeLock b = v.a().b();
            if (b != null) {
                if (b.isHeld()) {
                    b.release();
                }
                a.c("Util", "stop WakeLock CPU");
            }
        } catch (Throwable th) {
            a.c("Util", "stopWakeLock", th);
        }
    }

    public static boolean a(File file, File file2) {
        BufferedOutputStream bufferedOutputStream;
        Object e;
        Throwable th;
        FileInputStream fileInputStream = null;
        boolean z = false;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
            } catch (IOException e2) {
                e = e2;
                bufferedOutputStream = null;
                fileInputStream = fileInputStream2;
                try {
                    a.h("Util", "copyFile IOException: " + e);
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e3) {
                        }
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    return z;
                } catch (Throwable th2) {
                    th = th2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e4) {
                            throw th;
                        }
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedOutputStream = null;
                fileInputStream = fileInputStream2;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                throw th;
            }
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = fileInputStream2.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    bufferedOutputStream.write(bArr, 0, read);
                }
                bufferedOutputStream.flush();
                z = true;
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e5) {
                    }
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
            } catch (IOException e6) {
                e = e6;
                fileInputStream = fileInputStream2;
                a.h("Util", "copyFile IOException: " + e);
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                return z;
            } catch (Throwable th4) {
                th = th4;
                fileInputStream = fileInputStream2;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                throw th;
            }
        } catch (IOException e7) {
            e = e7;
            bufferedOutputStream = null;
            a.h("Util", "copyFile IOException: " + e);
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
            return z;
        } catch (Throwable th5) {
            th = th5;
            bufferedOutputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
            throw th;
        }
        return z;
    }
}
