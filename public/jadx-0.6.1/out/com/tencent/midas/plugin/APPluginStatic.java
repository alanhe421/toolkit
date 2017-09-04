package com.tencent.midas.plugin;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.pay.tool.APMidasTools;
import com.tencent.android.tpush.common.Constants;
import com.tencent.midas.comm.APLog;
import com.tencent.midas.data.APPluginReportManager;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public abstract class APPluginStatic {
    public static final String PARAM_CLASS_STATISTICS_UPLOADER = "clsUploader";
    public static final String PARAM_CLEAR_TOP = "cleartop";
    public static final String PARAM_LAUNCH_ACTIVITY = "pluginsdk_launchActivity";
    public static final String PARAM_PLUGIN_INTERNAL_ACTIVITIES_ONLY = "PARAM_PLUGIN_INTERNAL_ACTIVITIES_ONLY";
    public static final String PARAM_PLUGIN_LOCATION = "pluginsdk_pluginLocation";
    public static final String PARAM_PLUGIN_NAME = "pluginsdk_pluginName";
    public static final String PARAM_PLUGIN_PATH = "pluginsdk_pluginpath";
    public static final String PARAM_PLUGIN_RECEIVER_CLASS_NAME = "pluginsdk_launchReceiver";
    public static final int USER_QQ_RESOURCES_NO = -1;
    public static final int USER_QQ_RESOURCES_YES = 1;
    static final HashMap<String, DexClassLoader> a = new HashMap();
    static final ConcurrentHashMap<String, PackageInfo> b = new ConcurrentHashMap();
    private static ArrayList<WeakReference<IAPPluginActivity>> c = new ArrayList();
    private static DexClassLoader d = null;

    static synchronized DexClassLoader a(Context context, String str, String str2) {
        DexClassLoader dexClassLoader;
        synchronized (APPluginStatic.class) {
            String mD5FromPath = APPluginUtils.getMD5FromPath(str2);
            String str3 = str + "_" + mD5FromPath;
            dexClassLoader = (DexClassLoader) a.get(str3);
            APLog.d("APPluginStatic", "getOrCreateClassLoader apkFilePath: " + str2);
            APLog.d("APPluginStatic", "getOrCreateClassLoader MD5: " + mD5FromPath);
            APLog.d("APPluginStatic", "getOrCreateClassLoader key: " + str3);
            APLog.d("APPluginStatic", "getOrCreateClassLoader dexClassLoader: " + dexClassLoader);
            if (dexClassLoader == null) {
                mD5FromPath = APPluginUtils.getOptimizedDexPath(context).getCanonicalPath();
                long currentTimeMillis = System.currentTimeMillis();
                APPluginUtils.extractLibs(str2, APPluginUtils.getLibPath(context).getCanonicalPath());
                APPluginReportManager.getInstance().insertTimeDataEx(APMidasTools.getCurrentThreadName(Thread.currentThread()), APPluginReportManager.MIDASPLUGIN_TIMENAME_UNZIP_SO, currentTimeMillis);
                String canonicalPath = APPluginUtils.getLibPath(context).getCanonicalPath();
                dexClassLoader = d != null ? new DexClassLoader(str2, mD5FromPath, canonicalPath, d) : new DexClassLoader(str2, mD5FromPath, canonicalPath, context.getClassLoader());
                APLog.d("APPluginStatic", "getOrCreateClassLoader new DexClassLoader cache: " + mD5FromPath + " libDir: " + canonicalPath);
                a.put(str3, dexClassLoader);
            }
            APLog.d("APPluginStatic", "getOrCreateClassLoader midasClassLoader: " + dexClassLoader.hashCode());
        }
        return dexClassLoader;
    }

    static synchronized ClassLoader a(String str, String str2) {
        DexClassLoader dexClassLoader;
        synchronized (APPluginStatic.class) {
            dexClassLoader = (DexClassLoader) a.get(str + "_" + str2);
            APLog.d("APPluginStatic", "getClassLoader pluginName: " + str);
            APLog.d("APPluginStatic", "getClassLoader midasClassLoader: " + dexClassLoader);
        }
        return dexClassLoader;
    }

    static void a() {
        synchronized (c) {
            int i = 0;
            while (i < c.size()) {
                int i2;
                if (((WeakReference) c.get(i)).get() == null) {
                    c.remove(i);
                    i2 = i - 1;
                } else {
                    i2 = i;
                }
                i = i2 + 1;
            }
        }
    }

    static void a(IAPPluginActivity iAPPluginActivity) {
        a();
        synchronized (c) {
            c.add(new WeakReference(iAPPluginActivity));
        }
    }

    static void b(IAPPluginActivity iAPPluginActivity) {
        a();
        c(iAPPluginActivity);
    }

    public static String byteArrayToHex(byte[] bArr) {
        int i = 0;
        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] cArr2 = new char[(bArr.length * 2)];
        int length = bArr.length;
        int i2 = 0;
        while (i < length) {
            byte b = bArr[i];
            int i3 = i2 + 1;
            cArr2[i2] = cArr[(b >>> 4) & 15];
            i2 = i3 + 1;
            cArr2[i3] = cArr[b & 15];
            i++;
        }
        return new String(cArr2);
    }

    private static boolean c(IAPPluginActivity iAPPluginActivity) {
        synchronized (c) {
            for (int i = 0; i < c.size(); i++) {
                if (((WeakReference) c.get(i)).get() == iAPPluginActivity) {
                    c.remove(i);
                    return true;
                }
            }
            return false;
        }
    }

    public static String encodeFile(String str) {
        FileInputStream fileInputStream;
        String str2 = "";
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            byte[] bArr;
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                fileInputStream = new FileInputStream(file);
                byte[] bArr2 = new byte[1024];
                bArr = null;
                while (true) {
                    int read = fileInputStream.read(bArr2);
                    if (read == -1) {
                        break;
                    }
                    instance.update(bArr2, 0, read);
                }
                bArr = instance.digest();
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e2) {
                try {
                    fileInputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            } catch (Exception e4) {
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (IOException e32) {
                    e32.printStackTrace();
                }
            }
            if (bArr != null) {
                str2 = byteArrayToHex(bArr);
            }
        }
        return str2;
    }

    public static synchronized ClassLoader getOrCreateClassLoader(Context context, String str) {
        ClassLoader a;
        synchronized (APPluginStatic.class) {
            a = a(context, str, APPluginUtils.getInstallPath(context, str).getCanonicalPath());
            APLog.d("APPluginStatic", "getClassLoader getOrCreateClassLoader midasClassLoader: " + a.hashCode());
        }
        return a;
    }

    public static List<Boolean> isProcessesExist(Context context, List<String> list) {
        if (list == null) {
            return null;
        }
        List<Boolean> arrayList = new ArrayList();
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            for (int i = 0; i < list.size(); i++) {
                arrayList.add(Boolean.FALSE);
            }
            return arrayList;
        }
        for (String str : list) {
            boolean z;
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (str.equalsIgnoreCase(runningAppProcessInfo.processName)) {
                    z = true;
                    break;
                }
            }
            z = false;
            arrayList.add(Boolean.valueOf(z));
        }
        return arrayList;
    }

    public static synchronized void preCreateClassLoaderByPath(Context context) {
        synchronized (APPluginStatic.class) {
            Object obj = "";
            Object obj2 = "";
            File a = APPluginUtils.a(context);
            if (a != null) {
                File[] listFiles = a.listFiles();
                for (File file : listFiles) {
                    if (file.getName().startsWith("MidasPay")) {
                        obj = "MidasPay";
                        obj2 = file.getCanonicalPath();
                        break;
                    }
                }
            }
            if (!(TextUtils.isEmpty(obj) || TextUtils.isEmpty(obj2))) {
                a(context, obj, obj2);
            }
        }
    }

    public static void release() {
        a.clear();
        b.clear();
        APLog.d("APPluginStatic", "release sInstances size: " + c.size());
    }

    public static void removeAll() {
        a();
        synchronized (c) {
            int i = 0;
            while (i < c.size()) {
                int i2;
                IAPPluginActivity iAPPluginActivity = (IAPPluginActivity) ((WeakReference) c.get(i)).get();
                if (iAPPluginActivity != null) {
                    iAPPluginActivity.IFinish();
                    c.remove(i);
                    i2 = i - 1;
                } else {
                    i2 = i;
                }
                i = i2 + 1;
            }
        }
    }

    public static void setParentClassLoader(DexClassLoader dexClassLoader) {
        d = dexClassLoader;
    }
}
