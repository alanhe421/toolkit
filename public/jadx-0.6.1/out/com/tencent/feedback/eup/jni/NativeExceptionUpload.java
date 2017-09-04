package com.tencent.feedback.eup.jni;

import com.tencent.feedback.common.e;
import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: RQDSRC */
public class NativeExceptionUpload {
    public static final int ANDROID_LOG_DEBUG = 3;
    public static final int ANDROID_LOG_ERROR = 6;
    public static final int ANDROID_LOG_INFO = 4;
    public static final int ANDROID_LOG_WARN = 5;
    public static final int JAR_JNI_VERSION = 1;
    private static AtomicBoolean a = new AtomicBoolean(false);
    private static NativeExceptionHandler b = null;

    protected static native void doNativeCrashForTest();

    protected static native void enableHandler(boolean z);

    protected static native boolean registNativeExceptionHandler(String str, String str2, int i);

    protected static native String registNativeExceptionHandler2(String str, String str2, int i, int i2);

    protected static native void setLogMode(int i);

    public static synchronized boolean loadRQDNativeLib(File file) {
        boolean z = true;
        synchronized (NativeExceptionUpload.class) {
            if (file != null) {
                if (file.exists() && file.canRead()) {
                    try {
                        e.b("load %s", file.getAbsolutePath());
                        System.load(file.getAbsolutePath());
                        a.set(true);
                    } catch (Throwable th) {
                        if (!e.a(th)) {
                            th.printStackTrace();
                        }
                        e.d("rqdp{  load library fail! see detail ,will turn off native eup function!}", new Object[0]);
                        z = false;
                    }
                }
            }
            z = false;
        }
        return z;
    }

    public static synchronized boolean loadRQDNativeLib(List<File> list) {
        boolean loadRQDNativeLib;
        synchronized (NativeExceptionUpload.class) {
            if (list != null) {
                for (File file : list) {
                    if (file.exists() && file.isFile() && file.getName().equals("libNativeRQD.so")) {
                        break;
                    }
                }
            }
            File file2 = null;
            if (file2 != null) {
                loadRQDNativeLib = loadRQDNativeLib(file2);
            } else {
                loadRQDNativeLib = false;
            }
        }
        return loadRQDNativeLib;
    }

    public static synchronized boolean loadRQDNativeLib() {
        boolean z = true;
        synchronized (NativeExceptionUpload.class) {
            try {
                System.loadLibrary("NativeRQD");
                a.set(true);
            } catch (Throwable th) {
                if (!e.a(th)) {
                    th.printStackTrace();
                }
                e.d("rqdp{  load library fail! see detail ,will turn off native eup function!}", new Object[0]);
                z = false;
            }
        }
        return z;
    }

    public static synchronized NativeExceptionHandler getmHandler() {
        NativeExceptionHandler nativeExceptionHandler;
        synchronized (NativeExceptionUpload.class) {
            nativeExceptionHandler = b;
        }
        return nativeExceptionHandler;
    }

    public static synchronized void setmHandler(NativeExceptionHandler nativeExceptionHandler) {
        synchronized (NativeExceptionUpload.class) {
            b = nativeExceptionHandler;
        }
    }

    public static boolean registEUP(String str, String str2, int i) {
        boolean z = false;
        if (!a.get()) {
            e.c("rqdp{  nreg disable!}", new Object[z]);
            return z;
        } else if (str == null || str.trim().length() <= 0) {
            e.c("rqdp{  nreg param!}", new Object[z]);
            return z;
        } else {
            try {
                String registNativeExceptionHandler2 = registNativeExceptionHandler2(str, str2, i, 1);
                e.a("jarV:%d nativeV:%s", Integer.valueOf(1), registNativeExceptionHandler2);
                return true;
            } catch (Throwable th) {
                e.c("regist fail:%s , try old regist", th.getMessage());
                if (!e.a(th)) {
                    th.printStackTrace();
                }
                if (e.a(th)) {
                    return z;
                }
                th.printStackTrace();
                return z;
            }
        }
    }

    public static void enableNativeEUP(boolean z) {
        if (a.get()) {
            try {
                enableHandler(z);
                return;
            } catch (Throwable th) {
                if (!e.a(th)) {
                    th.printStackTrace();
                    return;
                }
                return;
            }
        }
        e.c("rqdp{  n enable disable!!}", new Object[0]);
    }

    public static void testNativeCrash() {
        if (a.get()) {
            doNativeCrashForTest();
        } else {
            e.c("rqdp{  n testNC disable!!}", new Object[0]);
        }
    }

    public static void setNativeLogMode(int i) {
        if (a.get()) {
            try {
                setLogMode(i);
                return;
            } catch (Throwable th) {
                if (!e.a(th)) {
                    th.printStackTrace();
                    return;
                }
                return;
            }
        }
        e.c("rqdp{  n sNL disable!!}", new Object[0]);
    }
}
