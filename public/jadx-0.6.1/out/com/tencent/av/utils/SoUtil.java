package com.tencent.av.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.text.TextUtils;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SoUtil {
    private static final String KEY_APK_SIZE = "key_apk_size";
    private static final String KEY_APK_UPDATE_TIME = "key_apk_update_time";
    private static final String KEY_APP_VERSION = "key_app_version";
    private static final String SO_SP = "so_sp";
    private static final String TAG = "SoUtil";
    private static boolean copySoFromAssets = false;
    private static Context ctx = null;
    private static int extractSoError = 0;

    public static void setAppContext(Context context) {
        ctx = context;
    }

    public static Context getAppContext() {
        return ctx;
    }

    public static void releaseAppContext() {
        ctx = null;
    }

    public static void setCopySoInfo(boolean z) {
        copySoFromAssets = z;
    }

    public static boolean getCopySoInfo() {
        return copySoFromAssets;
    }

    public static int extractAVModulesFromAssets() {
        boolean z;
        NameNotFoundException nameNotFoundException;
        String str;
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SO_SP, 0);
        PackageManager packageManager = ctx.getPackageManager();
        String string = sharedPreferences.getString(KEY_APP_VERSION, "");
        long j = sharedPreferences.getLong(KEY_APK_SIZE, -1);
        long j2 = sharedPreferences.getLong(KEY_APK_UPDATE_TIME, -1);
        String str2 = "";
        if (QLog.isColorLevel()) {
            QLog.d(TAG, 0, "local version = " + string + ", lastUpdateApkSize = " + j + ", lastUpdateApkTime = " + j2);
        }
        long j3;
        long j4;
        Object obj;
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ctx.getPackageName(), 0);
            PackageInfo packageInfo = packageManager.getPackageInfo(ctx.getPackageName(), 0);
            File file = new File(applicationInfo.sourceDir);
            if (file == null || !file.exists()) {
                j3 = -1;
                j4 = -1;
            } else {
                long length = file.length();
                j3 = file.lastModified();
                j4 = length;
            }
            if (packageInfo != null) {
                try {
                    obj = packageInfo.versionName + "." + packageInfo.versionCode;
                } catch (NameNotFoundException e) {
                    z = true;
                    String str3 = str2;
                    nameNotFoundException = e;
                    str = str3;
                    nameNotFoundException.printStackTrace();
                    if (extractLibraryFromAssert("hwcodec", z)) {
                    }
                    return extractSoError;
                }
            }
            str = str2;
            if (j4 == j) {
                try {
                    if (string.equals(obj) && j3 == j2) {
                        z = false;
                        if (QLog.isColorLevel()) {
                            QLog.d(TAG, 0, "apk version = " + obj + ", apksize = " + j4 + ", apkTime = " + j3 + ", needupdate = " + z);
                        }
                        if (extractLibraryFromAssert("hwcodec", z) || !extractLibraryFromAssert("traeimp-armeabi-v7a", z) || !extractLibraryFromAssert("qav_graphics", z) || !extractLibraryFromAssert("qavsdk", z) || !extractLibraryFromAssert("stlport_shared", z) || !extractLibraryFromAssert("TcVpxDec", z) || !extractLibraryFromAssert("TcVpxEnc", z) || !extractLibraryFromAssert("xplatform", z) || !extractLibraryFromAssert("UDT", z)) {
                            return extractSoError;
                        }
                        if (!TextUtils.isEmpty(obj)) {
                            sharedPreferences.edit().putString(KEY_APP_VERSION, obj).commit();
                        }
                        if (j4 > 0) {
                            sharedPreferences.edit().putLong(KEY_APK_SIZE, j4).commit();
                        }
                        if (j3 > 0) {
                            sharedPreferences.edit().putLong(KEY_APK_UPDATE_TIME, j3).commit();
                        }
                        return 0;
                    }
                } catch (NameNotFoundException e2) {
                    nameNotFoundException = e2;
                    z = true;
                    nameNotFoundException.printStackTrace();
                    if (extractLibraryFromAssert("hwcodec", z)) {
                    }
                    return extractSoError;
                }
            }
            z = true;
            try {
                if (QLog.isColorLevel()) {
                    QLog.d(TAG, 0, "apk version = " + obj + ", apksize = " + j4 + ", apkTime = " + j3 + ", needupdate = " + z);
                }
            } catch (NameNotFoundException e3) {
                nameNotFoundException = e3;
                nameNotFoundException.printStackTrace();
                if (extractLibraryFromAssert("hwcodec", z)) {
                }
                return extractSoError;
            }
        } catch (NameNotFoundException e4) {
            NameNotFoundException nameNotFoundException2 = e4;
            j3 = -1;
            j4 = -1;
            obj = str2;
            z = true;
            nameNotFoundException = nameNotFoundException2;
            nameNotFoundException.printStackTrace();
            if (extractLibraryFromAssert("hwcodec", z)) {
            }
            return extractSoError;
        }
        if (extractLibraryFromAssert("hwcodec", z)) {
        }
        return extractSoError;
    }

    public static boolean LoadExtractedSo(String str) {
        boolean z;
        if (QLog.isColorLevel()) {
            QLog.i(TAG, 0, "start LoadExtractedSo: " + str);
        }
        try {
            System.load(ctx.getFilesDir().getParent() + "/txav/" + getLibActualName(str));
            z = true;
        } catch (UnsatisfiedLinkError e) {
            File file = new File(ctx.getFilesDir().getParent() + "/txav/" + getLibActualName(str));
            if (file.exists()) {
                file.delete();
            }
            extractLibraryFromAssert(str, true);
            try {
                System.load(ctx.getFilesDir().getParent() + "/txav/" + getLibActualName(str));
                z = true;
            } catch (UnsatisfiedLinkError e2) {
                z = false;
            }
        }
        if (!z) {
            if (QLog.isColorLevel()) {
                QLog.d(TAG, 0, "finally try to use system way to load so: " + str);
            }
            try {
                System.loadLibrary(str);
                z = true;
            } catch (UnsatisfiedLinkError e3) {
            }
        }
        if (!z) {
            QLog.e(TAG, 1, "LoadExtractedSo failed: " + str);
        }
        return z;
    }

    private static long getAvailableInternalMemorySize() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
    }

    private static String getLibActualName(String str) {
        return ShareConstants.SO_PATH + str + ".so";
    }

    private static String getLibPath() {
        return "lib/armeabi/";
    }

    private static String getTxlibPath() {
        File filesDir = ctx.getFilesDir();
        if (filesDir != null) {
            return filesDir.getParent() + "/txav/";
        }
        if (QLog.isColorLevel()) {
            QLog.i(TAG, 0, "getFilesDir is null");
        }
        return "";
    }

    private static synchronized boolean extractLibraryFromAssert(String str, boolean z) {
        boolean z2;
        InputStream inputStream;
        Object obj;
        InputStream inputStream2;
        long j;
        byte[] bArr;
        int read;
        long uptimeMillis;
        synchronized (SoUtil.class) {
            boolean z3 = false;
            String str2 = "";
            Object obj2 = null;
            String txlibPath = getTxlibPath();
            if (TextUtils.isEmpty(txlibPath)) {
                z2 = false;
            } else {
                File file = new File(txlibPath + getLibActualName(str));
                if (z || !file.exists()) {
                    long uptimeMillis2 = SystemClock.uptimeMillis();
                    file.delete();
                    if (!file.exists()) {
                        OutputStream outputStream = null;
                        InputStream inputStream3 = null;
                        InputStream inputStream4 = null;
                        try {
                            File file2 = new File(txlibPath);
                            if (file2.exists() || file2.mkdir()) {
                                try {
                                    if (QLog.isColorLevel()) {
                                        QLog.d(TAG, 0, "extractLibraryFromAssert, getAssets = " + getLibPath() + getLibActualName(str));
                                    }
                                    inputStream3 = ctx.getAssets().open(getLibPath() + getLibActualName(str));
                                    try {
                                        if (getAvailableInternalMemorySize() < ((long) inputStream3.available())) {
                                            obj2 = 1;
                                            extractSoError = 10101;
                                            if (QLog.isColorLevel()) {
                                                QLog.d(TAG, 0, "extractLibraryFromAssert, memory is not enough, available = " + getAvailableInternalMemorySize() + ", so = " + inputStream3.available());
                                            }
                                        }
                                    } catch (FileNotFoundException e) {
                                        inputStream = inputStream3;
                                        obj = null;
                                        inputStream2 = inputStream;
                                        z3 = true;
                                        inputStream = inputStream2;
                                        obj2 = obj;
                                        inputStream3 = inputStream;
                                        if (inputStream3 != null) {
                                            outputStream = new FileOutputStream(txlibPath + getLibActualName(str), true);
                                        }
                                        j = 0;
                                        bArr = new byte[32768];
                                        while (inputStream3 != null) {
                                            read = inputStream3.read(bArr, 0, bArr.length);
                                            if (read != -1) {
                                                break;
                                            }
                                            outputStream.write(bArr, 0, read);
                                            j += (long) read;
                                        }
                                        while (inputStream4 != null) {
                                            read = inputStream4.read(bArr, 0, bArr.length);
                                            if (read != -1) {
                                                break;
                                            }
                                            outputStream.write(bArr, 0, read);
                                            j += (long) read;
                                        }
                                        if (QLog.isColorLevel()) {
                                            QLog.d(TAG, 0, "extractLibraryFromAssert, copy success");
                                        }
                                        if (file.exists()) {
                                            if (j != file.length()) {
                                                z3 = true;
                                                if (QLog.isColorLevel()) {
                                                    QLog.d(TAG, 0, "extractLibraryFromAssert " + str + " success");
                                                }
                                            } else {
                                                file.delete();
                                                str2 = str2 + "copy asset so fail. file size not match";
                                                z3 = false;
                                                if (QLog.isColorLevel()) {
                                                    QLog.d(TAG, 0, str2);
                                                }
                                            }
                                        }
                                        if (outputStream != null) {
                                            try {
                                                outputStream.close();
                                            } catch (IOException e2) {
                                            }
                                        }
                                        if (inputStream3 != null) {
                                            try {
                                                inputStream3.close();
                                            } catch (IOException e3) {
                                            }
                                        }
                                        if (inputStream4 != null) {
                                            try {
                                                inputStream4.close();
                                                z2 = z3;
                                            } catch (IOException e4) {
                                                z2 = z3;
                                            }
                                            uptimeMillis = SystemClock.uptimeMillis() - uptimeMillis2;
                                            if (QLog.isColorLevel()) {
                                                QLog.d(TAG, 0, "extract so " + str + " from assets, spend time: " + uptimeMillis);
                                            }
                                            return z2;
                                        }
                                        z2 = z3;
                                        uptimeMillis = SystemClock.uptimeMillis() - uptimeMillis2;
                                        if (QLog.isColorLevel()) {
                                            QLog.d(TAG, 0, "extract so " + str + " from assets, spend time: " + uptimeMillis);
                                        }
                                        return z2;
                                    }
                                } catch (FileNotFoundException e5) {
                                    obj = null;
                                    inputStream2 = null;
                                    z3 = true;
                                    inputStream = inputStream2;
                                    obj2 = obj;
                                    inputStream3 = inputStream;
                                    if (inputStream3 != null) {
                                        outputStream = new FileOutputStream(txlibPath + getLibActualName(str), true);
                                    }
                                    j = 0;
                                    bArr = new byte[32768];
                                    while (inputStream3 != null) {
                                        read = inputStream3.read(bArr, 0, bArr.length);
                                        if (read != -1) {
                                            break;
                                        }
                                        outputStream.write(bArr, 0, read);
                                        j += (long) read;
                                    }
                                    while (inputStream4 != null) {
                                        read = inputStream4.read(bArr, 0, bArr.length);
                                        if (read != -1) {
                                            break;
                                        }
                                        outputStream.write(bArr, 0, read);
                                        j += (long) read;
                                    }
                                    if (QLog.isColorLevel()) {
                                        QLog.d(TAG, 0, "extractLibraryFromAssert, copy success");
                                    }
                                    if (file.exists()) {
                                        if (j != file.length()) {
                                            file.delete();
                                            str2 = str2 + "copy asset so fail. file size not match";
                                            z3 = false;
                                            if (QLog.isColorLevel()) {
                                                QLog.d(TAG, 0, str2);
                                            }
                                        } else {
                                            z3 = true;
                                            if (QLog.isColorLevel()) {
                                                QLog.d(TAG, 0, "extractLibraryFromAssert " + str + " success");
                                            }
                                        }
                                    }
                                    if (outputStream != null) {
                                        outputStream.close();
                                    }
                                    if (inputStream3 != null) {
                                        inputStream3.close();
                                    }
                                    if (inputStream4 != null) {
                                        inputStream4.close();
                                        z2 = z3;
                                        uptimeMillis = SystemClock.uptimeMillis() - uptimeMillis2;
                                        if (QLog.isColorLevel()) {
                                            QLog.d(TAG, 0, "extract so " + str + " from assets, spend time: " + uptimeMillis);
                                        }
                                        return z2;
                                    }
                                    z2 = z3;
                                    uptimeMillis = SystemClock.uptimeMillis() - uptimeMillis2;
                                    if (QLog.isColorLevel()) {
                                        QLog.d(TAG, 0, "extract so " + str + " from assets, spend time: " + uptimeMillis);
                                    }
                                    return z2;
                                }
                                if (obj2 == null && !r6) {
                                    if (inputStream3 != null) {
                                        outputStream = new FileOutputStream(txlibPath + getLibActualName(str), true);
                                    }
                                    j = 0;
                                    bArr = new byte[32768];
                                    while (inputStream3 != null) {
                                        read = inputStream3.read(bArr, 0, bArr.length);
                                        if (read != -1) {
                                            break;
                                        }
                                        outputStream.write(bArr, 0, read);
                                        j += (long) read;
                                    }
                                    while (inputStream4 != null) {
                                        read = inputStream4.read(bArr, 0, bArr.length);
                                        if (read != -1) {
                                            break;
                                        }
                                        outputStream.write(bArr, 0, read);
                                        j += (long) read;
                                    }
                                    if (QLog.isColorLevel()) {
                                        QLog.d(TAG, 0, "extractLibraryFromAssert, copy success");
                                    }
                                    if (file.exists()) {
                                        if (j != file.length()) {
                                            file.delete();
                                            str2 = str2 + "copy asset so fail. file size not match";
                                            z3 = false;
                                            if (QLog.isColorLevel()) {
                                                QLog.d(TAG, 0, str2);
                                            }
                                        } else {
                                            z3 = true;
                                            if (QLog.isColorLevel()) {
                                                QLog.d(TAG, 0, "extractLibraryFromAssert " + str + " success");
                                            }
                                        }
                                    }
                                }
                            }
                            if (outputStream != null) {
                                outputStream.close();
                            }
                            if (inputStream3 != null) {
                                inputStream3.close();
                            }
                            if (inputStream4 != null) {
                                inputStream4.close();
                                z2 = z3;
                                uptimeMillis = SystemClock.uptimeMillis() - uptimeMillis2;
                                if (QLog.isColorLevel()) {
                                    QLog.d(TAG, 0, "extract so " + str + " from assets, spend time: " + uptimeMillis);
                                }
                            }
                        } catch (Exception e6) {
                            if (QLog.isColorLevel()) {
                                QLog.d(TAG, 0, str2, e6);
                            }
                            if (QLog.isColorLevel()) {
                                QLog.d(TAG, 0, "extractLibraryFromAssert, e = " + e6.toString());
                            }
                            extractSoError = 10102;
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (IOException e7) {
                                }
                            }
                            if (inputStream3 != null) {
                                try {
                                    inputStream3.close();
                                } catch (IOException e8) {
                                }
                            }
                            if (inputStream4 != null) {
                                try {
                                    inputStream4.close();
                                    z2 = z3;
                                } catch (IOException e9) {
                                    z2 = z3;
                                }
                            }
                        } catch (Throwable th) {
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (IOException e10) {
                                }
                            }
                            if (inputStream3 != null) {
                                try {
                                    inputStream3.close();
                                } catch (IOException e11) {
                                }
                            }
                            if (inputStream4 != null) {
                                try {
                                    inputStream4.close();
                                } catch (IOException e12) {
                                }
                            }
                        }
                    }
                    z2 = z3;
                    uptimeMillis = SystemClock.uptimeMillis() - uptimeMillis2;
                    if (QLog.isColorLevel()) {
                        QLog.d(TAG, 0, "extract so " + str + " from assets, spend time: " + uptimeMillis);
                    }
                } else {
                    if (QLog.isColorLevel()) {
                        QLog.d(TAG, 0, "extractLibraryFromAssert, no need update");
                    }
                    z2 = true;
                }
            }
        }
        return z2;
    }
}
