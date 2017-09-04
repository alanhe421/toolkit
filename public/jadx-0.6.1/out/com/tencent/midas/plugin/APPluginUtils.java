package com.tencent.midas.plugin;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import com.dynamicload.Lib.DLConstants;
import com.etrump.jni.ETConverter;
import com.pay.tool.APMidasTools;
import com.tencent.midas.api.APMidasPayAPI;
import com.tencent.midas.comm.APLog;
import com.tencent.midas.comm.APMidasRSATools;
import com.tencent.midas.data.APPluginReportManager;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class APPluginUtils {
    private static final char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static Map<String, File> b = new ConcurrentHashMap();
    private static Map<String, PackageInfo> c = new ConcurrentHashMap();
    private static String d = null;
    private static int e = 0;
    private static ArrayList<String> f = null;
    private static String[] g = null;
    private static Object h = new Object();

    private static int a(Context context, InputStream inputStream) {
        ZipInputStream zipInputStream;
        Exception e;
        Throwable th;
        int i = 0;
        try {
            zipInputStream = new ZipInputStream(inputStream);
            try {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                APLog.i("getAssetsVersionCodeWtihFileName", "zipEntry:" + nextEntry);
                while (nextEntry != null) {
                    String name = nextEntry.getName();
                    APLog.i("getAssetsVersionCodeWtihFileName", "fileName:" + name);
                    if (!nextEntry.isDirectory() && !name.contains("../")) {
                        if (name.startsWith("MidasCore") && name.endsWith(ShareConstants.JAR_SUFFIX)) {
                            i = Integer.parseInt(name.substring(0, name.lastIndexOf(ShareConstants.JAR_SUFFIX)).split("_")[2]);
                            break;
                        }
                        nextEntry = zipInputStream.getNextEntry();
                    } else {
                        nextEntry = zipInputStream.getNextEntry();
                    }
                }
                if (zipInputStream != null) {
                    try {
                        zipInputStream.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (Exception e3) {
                e2 = e3;
            }
        } catch (Exception e4) {
            e2 = e4;
            zipInputStream = null;
            try {
                e2.printStackTrace();
                d = e2.toString();
                if (zipInputStream != null) {
                    try {
                        zipInputStream.close();
                    } catch (Exception e22) {
                        e22.printStackTrace();
                    }
                }
                APLog.i("special data direct", "versionCode:" + i);
                return i;
            } catch (Throwable th2) {
                th = th2;
                if (zipInputStream != null) {
                    try {
                        zipInputStream.close();
                    } catch (Exception e222) {
                        e222.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            zipInputStream = null;
            if (zipInputStream != null) {
                zipInputStream.close();
            }
            throw th;
        }
        APLog.i("special data direct", "versionCode:" + i);
        return i;
    }

    private static int a(Context context, String str) {
        Exception e;
        Throwable th;
        APLog.i("getAssetsVersionCodeWtihFileName", "sUnzipMidasPayFile:" + str);
        int i = 0;
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(str);
            try {
                i = a(context, (InputStream) fileInputStream);
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    d = e.toString();
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    APLog.i("special data direct", "versionCode:" + i);
                    return i;
                } catch (Throwable th2) {
                    th = th2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e4) {
            e = e4;
            fileInputStream = null;
            e.printStackTrace();
            d = e.toString();
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            APLog.i("special data direct", "versionCode:" + i);
            return i;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
        return i;
    }

    static File a(Context context) {
        return context.getDir("midasplugins", 0);
    }

    private static void a(Context context, File file, File file2) {
        if (file != null && file2 != null) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file3 : listFiles) {
                    a(file3.getAbsolutePath(), file2.getAbsolutePath(), file3.getName());
                }
            }
        }
    }

    private static void a(String str, String str2, String str3) {
        Exception e;
        InputStream inputStream;
        Throwable th;
        FileOutputStream fileOutputStream = null;
        FileOutputStream fileOutputStream2 = null;
        InputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(str);
            try {
                File file = new File(str2);
                if (!file.exists()) {
                    file.mkdir();
                }
                File file2 = new File(str2 + str3);
                if (file2.exists()) {
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                            return;
                        }
                    }
                    if (null != null) {
                        fileOutputStream2.close();
                        return;
                    }
                    return;
                }
                FileOutputStream fileOutputStream3 = new FileOutputStream(file2);
                try {
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream3.write(bArr, 0, read);
                    }
                    fileOutputStream3.flush();
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                            return;
                        }
                    }
                    if (fileOutputStream3 != null) {
                        fileOutputStream3.close();
                    }
                } catch (Exception e3) {
                    e = e3;
                    fileOutputStream = fileOutputStream3;
                    inputStream = fileInputStream;
                    try {
                        e.printStackTrace();
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e222) {
                                e222.printStackTrace();
                                return;
                            }
                        }
                        if (fileOutputStream == null) {
                            fileOutputStream.close();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream = inputStream;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                                throw th;
                            }
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream = fileOutputStream3;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            } catch (Exception e5) {
                e = e5;
                inputStream = fileInputStream;
                e.printStackTrace();
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileOutputStream == null) {
                    fileOutputStream.close();
                }
            } catch (Throwable th4) {
                th = th4;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        } catch (Exception e6) {
            e = e6;
            inputStream = null;
            e.printStackTrace();
            if (inputStream != null) {
                inputStream.close();
            }
            if (fileOutputStream == null) {
                fileOutputStream.close();
            }
        } catch (Throwable th5) {
            th = th5;
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    private static void a(HashMap<String, String> hashMap, File file) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getCanonicalPath()));
            APMidasRSATools aPMidasRSATools = new APMidasRSATools();
            Object readLine = bufferedReader.readLine();
            while (readLine != null && !TextUtils.isEmpty(readLine)) {
                String[] split = readLine.split("\\:");
                String str = split[0];
                String deCodeKey = aPMidasRSATools.deCodeKey(split[1]);
                hashMap.put(str.split("\\_")[0], deCodeKey.substring(deCodeKey.length() - 32));
                readLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private static void a(boolean z, String str, String str2, String str3) {
        Object obj = null;
        try {
            obj = Environment.getExternalStorageDirectory().getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!TextUtils.isEmpty(obj)) {
            final String str4 = obj + "/Tencent/MidasPay/";
            final boolean z2 = z;
            final String str5 = str;
            final String str6 = str3;
            final String str7 = str2;
            new Thread(new Runnable() {
                public void run() {
                    synchronized (APPluginUtils.h) {
                        if (z2) {
                            String str = str5;
                            if (str.endsWith(ShareConstants.JAR_SUFFIX)) {
                                str = str.replace(ShareConstants.JAR_SUFFIX, ShareConstants.PATCH_SUFFIX);
                            }
                            long currentTimeMillis = System.currentTimeMillis();
                            APPluginUtils.b(str6, str4, str, str7);
                            APLog.i("Times", "File" + str + "backup times:" + (System.currentTimeMillis() - currentTimeMillis));
                        }
                    }
                }
            }).start();
        }
    }

    private static boolean a(String str, String str2) {
        InputStream fileInputStream;
        Exception e;
        String str3 = "";
        try {
            if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
                return false;
            }
            fileInputStream = new FileInputStream(str);
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    instance.update(bArr, 0, read);
                }
                fileInputStream.close();
                str3 = toHexString(instance.digest());
            } catch (Exception e2) {
                e = e2;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                e.printStackTrace();
                return str3.equalsIgnoreCase(str2);
            }
            if (str3.equalsIgnoreCase(str2)) {
            }
        } catch (Exception e4) {
            e = e4;
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            e.printStackTrace();
            if (str3.equalsIgnoreCase(str2)) {
            }
        }
    }

    private static int b(Context context, InputStream inputStream) {
        Exception e;
        Throwable th;
        ZipInputStream zipInputStream = null;
        g(context);
        if (inputStream == null) {
            return -2;
        }
        ZipInputStream zipInputStream2;
        BufferedOutputStream bufferedOutputStream;
        try {
            zipInputStream2 = new ZipInputStream(inputStream);
            try {
                ZipEntry nextEntry = zipInputStream2.getNextEntry();
                String absolutePath = a(context).getAbsolutePath();
                while (nextEntry != null) {
                    String name = nextEntry.getName();
                    if (nextEntry.isDirectory() || name.contains("../")) {
                        nextEntry = zipInputStream2.getNextEntry();
                    } else {
                        boolean z = true;
                        String str = "";
                        String str2 = "";
                        if (name.endsWith(ShareConstants.JAR_SUFFIX)) {
                            int lastIndexOf = name.lastIndexOf(ShareConstants.JAR_SUFFIX);
                            str2 = lastIndexOf != -1 ? name.substring(0, lastIndexOf) : name;
                            str = str2.split("_")[3];
                            str2 = absolutePath + File.separator + str2 + ShareConstants.PATCH_SUFFIX;
                        } else {
                            str2 = absolutePath + File.separator + name;
                            z = false;
                        }
                        File file = new File(str2);
                        try {
                            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                            try {
                                byte[] bArr = new byte[8192];
                                while (true) {
                                    int read = zipInputStream2.read(bArr);
                                    if (read == -1) {
                                        break;
                                    }
                                    bufferedOutputStream.write(bArr, 0, read);
                                }
                                bufferedOutputStream.flush();
                                a(z, name, str, file.getCanonicalPath());
                                ZipEntry nextEntry2 = zipInputStream2.getNextEntry();
                                if (bufferedOutputStream != null) {
                                    try {
                                        bufferedOutputStream.close();
                                    } catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                                nextEntry = nextEntry2;
                            } catch (Exception e3) {
                                e = e3;
                            }
                        } catch (Exception e4) {
                            e = e4;
                            bufferedOutputStream = null;
                        } catch (Throwable th2) {
                            Throwable th3 = th2;
                            bufferedOutputStream = null;
                        }
                    }
                }
                if (zipInputStream2 != null) {
                    try {
                        zipInputStream2.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                return 0;
            } catch (Exception e6) {
                e = e6;
                zipInputStream = zipInputStream2;
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (Exception e7) {
            e = e7;
            try {
                e.printStackTrace();
                d = e.toString();
                if (zipInputStream == null) {
                    return -1;
                }
                try {
                    zipInputStream.close();
                    return -1;
                } catch (IOException e8) {
                    e8.printStackTrace();
                    return -1;
                }
            } catch (Throwable th5) {
                th = th5;
                zipInputStream2 = zipInputStream;
                if (zipInputStream2 != null) {
                    try {
                        zipInputStream2.close();
                    } catch (IOException e82) {
                        e82.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th6) {
            th = th6;
            zipInputStream2 = null;
            if (zipInputStream2 != null) {
                zipInputStream2.close();
            }
            throw th;
        }
        if (zipInputStream2 != null) {
            return -1;
        }
        try {
            zipInputStream2.close();
            return -1;
        } catch (IOException e822) {
            e822.printStackTrace();
            return -1;
        }
        try {
            e.printStackTrace();
            d = e.toString();
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e8222) {
                    e8222.printStackTrace();
                }
            }
            if (zipInputStream2 != null) {
                return -1;
            }
            zipInputStream2.close();
            return -1;
        } catch (Throwable th7) {
            th3 = th7;
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e22) {
                    e22.printStackTrace();
                }
            }
            throw th3;
        }
    }

    static File b(Context context) {
        return context.getDir("midasemptyRes", 0);
    }

    private static void b(String str, String str2, String str3, String str4) {
        InputStream fileInputStream;
        Exception e;
        FileOutputStream fileOutputStream;
        InputStream inputStream;
        String str5;
        Throwable th;
        File file;
        File file2 = null;
        String str6 = "";
        FileOutputStream fileOutputStream2 = null;
        FileOutputStream fileOutputStream3;
        try {
            MessageDigest instance;
            File file3;
            fileInputStream = new FileInputStream(str);
            try {
                instance = MessageDigest.getInstance("MD5");
                file3 = new File(str2);
                if (!file3.exists()) {
                    file3.mkdir();
                }
                file3 = new File(str2 + str3);
            } catch (Exception e2) {
                e = e2;
                fileOutputStream = null;
                inputStream = fileInputStream;
                str5 = str6;
                try {
                    e.printStackTrace();
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                            if (str5.compareToIgnoreCase(str4) != 0) {
                            }
                        }
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    if (str5.compareToIgnoreCase(str4) != 0) {
                    }
                } catch (Throwable th2) {
                    th = th2;
                    str6 = str5;
                    fileInputStream = inputStream;
                    fileOutputStream3 = fileOutputStream;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                            file2.delete();
                            throw th;
                        }
                    }
                    if (fileOutputStream3 != null) {
                        fileOutputStream3.close();
                    }
                    file2.delete();
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream3 = null;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream3 != null) {
                    fileOutputStream3.close();
                }
                file2.delete();
                throw th;
            }
            try {
                if (file3.exists()) {
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                        }
                    }
                    if (null != null) {
                        fileOutputStream2.close();
                    }
                    if (str6.compareToIgnoreCase(str4) != 0 && file3 != null) {
                        file3.delete();
                        return;
                    }
                    return;
                }
                String toHexString;
                fileOutputStream3 = new FileOutputStream(file3);
                try {
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream3.write(bArr, 0, read);
                        instance.update(bArr, 0, read);
                    }
                    toHexString = toHexString(instance.digest());
                } catch (Exception e5) {
                    e = e5;
                    file2 = file3;
                    fileOutputStream = fileOutputStream3;
                    inputStream = fileInputStream;
                    str5 = str6;
                    e.printStackTrace();
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    if (str5.compareToIgnoreCase(str4) != 0) {
                    }
                } catch (Throwable th4) {
                    th = th4;
                    file2 = file3;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (fileOutputStream3 != null) {
                        fileOutputStream3.close();
                    }
                    file2.delete();
                    throw th;
                }
                try {
                    fileOutputStream3.flush();
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e322) {
                            e322.printStackTrace();
                        }
                    }
                    if (fileOutputStream3 != null) {
                        fileOutputStream3.close();
                    }
                    if (toHexString.compareToIgnoreCase(str4) != 0 && file3 != null) {
                        file3.delete();
                    }
                } catch (Exception e6) {
                    e = e6;
                    file = file3;
                    fileOutputStream = fileOutputStream3;
                    inputStream = fileInputStream;
                    str5 = toHexString;
                    file2 = file;
                    e.printStackTrace();
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    if (str5.compareToIgnoreCase(str4) != 0 && file2 != null) {
                        file2.delete();
                    }
                } catch (Throwable th5) {
                    th = th5;
                    str6 = toHexString;
                    file2 = file3;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (fileOutputStream3 != null) {
                        fileOutputStream3.close();
                    }
                    if (!(str6.compareToIgnoreCase(str4) == 0 || file2 == null)) {
                        file2.delete();
                    }
                    throw th;
                }
            } catch (Exception e7) {
                e = e7;
                inputStream = fileInputStream;
                str5 = str6;
                file = file3;
                fileOutputStream = null;
                file2 = file;
                e.printStackTrace();
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (str5.compareToIgnoreCase(str4) != 0) {
                }
            } catch (Throwable th6) {
                th = th6;
                fileOutputStream3 = null;
                file2 = file3;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream3 != null) {
                    fileOutputStream3.close();
                }
                file2.delete();
                throw th;
            }
        } catch (Exception e8) {
            e = e8;
            fileOutputStream = null;
            inputStream = null;
            str5 = str6;
            e.printStackTrace();
            if (inputStream != null) {
                inputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (str5.compareToIgnoreCase(str4) != 0) {
            }
        } catch (Throwable th7) {
            th = th7;
            fileOutputStream3 = null;
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (fileOutputStream3 != null) {
                fileOutputStream3.close();
            }
            file2.delete();
            throw th;
        }
    }

    private static boolean b() {
        boolean z;
        try {
            Class.forName("com.tencent.theme.SkinEngine").getMethod("getInstances", new Class[0]);
            z = true;
        } catch (Exception e) {
            APLog.w("APPluginContext", " is not has com.tencent.theme.SkinEngine e:" + e.toString());
            z = false;
        }
        if (z) {
            return z;
        }
        try {
            Class.forName("com.tencent.component.theme.SkinEngine").getMethod("getInstances", new Class[0]);
            return true;
        } catch (Exception e2) {
            APLog.w("APPluginContext", " is not has com.tencent.component.theme.SkinEngine e:" + e2.toString());
            return false;
        }
    }

    static File c(Context context) {
        return context.getDir("midaspluginsTemp", 0);
    }

    static File d(Context context) {
        return context.getDir("midaspluginsBKTemp", 0);
    }

    public static void deleteBKPlugin(Context context) {
        APLog.i("APPluginUtils", "deleteUpdatePlugin");
        deleteFiles(d(context));
    }

    public static void deleteDex(Context context) {
        APLog.i("APPluginUtils", "deleteDex");
        deleteFiles(getOptimizedDexPath(context));
    }

    public static void deleteFiles(File file) {
        if (file != null) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    for (File file2 : listFiles) {
                        if (file2 != null && file2.exists()) {
                            file2.delete();
                        }
                    }
                    file.delete();
                    return;
                }
                return;
            }
            file.delete();
        }
    }

    public static void deleteLibs(Context context) {
        APLog.i("APPluginUtils", "deleteLibs");
        deleteFiles(getLibPath(context));
    }

    public static void deletePlugin(Context context) {
        APLog.i("APPluginUtils", "deletePlugin");
        deleteFiles(a(context));
    }

    public static void deleteUpdatePlugin(Context context) {
        APLog.i("APPluginUtils", "deleteUpdatePlugin");
        deleteFiles(c(context));
    }

    private static int e(Context context) {
        int i = 0;
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open("MidasPay.zip");
            i = a(context, inputStream);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            d = e2.toString();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            APLog.i("assets 目录下内核版本号：", "versionCode:" + i);
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e32) {
                    e32.printStackTrace();
                }
            }
        }
        return i;
    }

    public static int extractLibs(String str, String str2) {
        Throwable e;
        InputStream inputStream;
        FileOutputStream fileOutputStream;
        String str3 = Build.CPU_ABI.startsWith("arm64-v8a") ? "arm64-v8a" : Build.CPU_ABI.startsWith("arm") ? DLConstants.CPU_ARMEABI : Build.CPU_ABI.startsWith(DLConstants.CPU_X86) ? DLConstants.CPU_X86 : DLConstants.CPU_ARMEABI;
        APLog.i("APPluginUtils", "extractLibs end to dirToExtract:" + str3 + " extractLibs ABI:" + Build.CPU_ABI);
        if (!str2.endsWith(File.separator)) {
            str2 = str2 + File.separator;
        }
        d = null;
        if (d == null) {
            ZipFile zipFile = new ZipFile(str);
            byte[] bArr = new byte[4096];
            Enumeration entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) entries.nextElement();
                String name = zipEntry.getName();
                if (!(name.endsWith(File.separator) || name.contains("../"))) {
                    if (name.contains(ShareConstants.SO_PATH) || name.endsWith(".so")) {
                        Object obj;
                        APLog.i("APPluginUtils", "fileName:" + name);
                        File file = new File(str2 + name);
                        int lastIndexOf = name.lastIndexOf(File.separator);
                        if (lastIndexOf != -1) {
                            name = name.substring(lastIndexOf + 1);
                        }
                        File file2 = new File(str2 + name);
                        while (file != null) {
                            if (file.getName().equals(str3)) {
                                obj = 1;
                                break;
                            }
                            try {
                                file = file.getParentFile();
                            } catch (Throwable e2) {
                                e2.printStackTrace();
                                d = getExceptionInfo(e2);
                                return -1;
                            }
                        }
                        obj = null;
                        if (obj != null) {
                            file2.getParentFile().mkdirs();
                            try {
                                inputStream = zipFile.getInputStream(zipEntry);
                                try {
                                    fileOutputStream = new FileOutputStream(file2);
                                    while (true) {
                                        try {
                                            int read = inputStream.read(bArr);
                                            if (read <= 0) {
                                                break;
                                            }
                                            fileOutputStream.write(bArr, 0, read);
                                        } catch (IOException e3) {
                                            e2 = e3;
                                        }
                                    }
                                    fileOutputStream.flush();
                                    if (fileOutputStream != null) {
                                        try {
                                            fileOutputStream.close();
                                        } catch (IOException e4) {
                                            e4.printStackTrace();
                                        }
                                    }
                                    if (inputStream != null) {
                                        inputStream.close();
                                    }
                                } catch (IOException e5) {
                                    e2 = e5;
                                    fileOutputStream = null;
                                    try {
                                        APLog.w("extra libs", "extra lbis error:" + e2.toString());
                                        d = getExceptionInfo(e2);
                                        if (fileOutputStream != null) {
                                            try {
                                                fileOutputStream.close();
                                            } catch (IOException e42) {
                                                e42.printStackTrace();
                                            }
                                        }
                                        if (inputStream != null) {
                                            inputStream.close();
                                        }
                                        if (d == null) {
                                            return 0;
                                        }
                                    } catch (Throwable th) {
                                        e2 = th;
                                    }
                                } catch (Throwable th2) {
                                    e2 = th2;
                                    fileOutputStream = null;
                                }
                            } catch (IOException e6) {
                                e2 = e6;
                                inputStream = null;
                                fileOutputStream = null;
                                APLog.w("extra libs", "extra lbis error:" + e2.toString());
                                d = getExceptionInfo(e2);
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                                if (d == null) {
                                    return 0;
                                }
                            } catch (Throwable th3) {
                                e2 = th3;
                                inputStream = null;
                                fileOutputStream = null;
                            }
                            if (d == null) {
                                break;
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
        }
        return 0;
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (IOException e7) {
                e7.printStackTrace();
                throw e2;
            }
        }
        if (inputStream != null) {
            inputStream.close();
        }
        throw e2;
    }

    public static void extractMidasPluginsLibs(Context context) {
        try {
            deleteLibs(context);
            e++;
            String canonicalPath = getLibPath(context).getCanonicalPath();
            long currentTimeMillis = System.currentTimeMillis();
            File a = a(context);
            if (a != null) {
                File[] listFiles = a.listFiles();
                for (File canonicalPath2 : listFiles) {
                    extractLibs(canonicalPath2.getCanonicalPath(), canonicalPath);
                }
            }
            APPluginReportManager.getInstance().insertTimeDataEx(APMidasTools.getCurrentThreadName(Thread.currentThread()), APPluginReportManager.MIDASPLUGIN_TIMENAME_UNZIP_SO, currentTimeMillis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String[] f(Context context) {
        try {
            if (g == null) {
                long currentTimeMillis = System.currentTimeMillis();
                g = context.getAssets().list("");
                APPluginReportManager.getInstance().insertTimeDataEx(APMidasTools.getCurrentThreadName(Thread.currentThread()), APPluginReportManager.MIDASPLUGIN_TIMENAME_GET_FILELIST_FROM_ASSETS, currentTimeMillis);
            }
        } catch (IOException e) {
            APLog.w("APPLuginUtils", "getPluginNameFromAssets e:" + e.getMessage());
        }
        return g;
    }

    private static void g(Context context) {
        if (b()) {
            String[] f = f(context);
            if (f != null) {
                for (String str : f) {
                    APLog.i("APPluginUtils", "copyEmtpyResAPKFromAssets assetFile:" + str);
                    if (str.startsWith("MidasEmptyRes") && str.endsWith(ShareConstants.PATCH_SUFFIX)) {
                        String str2 = b(context).getAbsolutePath() + File.separator + str;
                        APLog.i("APPluginUtils", "copyEmtpyResAPKFromAssets meptyResPath:" + str2);
                        try {
                            InputStream open = context.getAssets().open(str);
                            File file = new File(str2);
                            file.createNewFile();
                            FileOutputStream fileOutputStream = new FileOutputStream(file);
                            byte[] bArr = new byte[1024];
                            while (true) {
                                int read = open.read(bArr);
                                if (read <= 0) {
                                    break;
                                }
                                fileOutputStream.write(bArr, 0, read);
                            }
                            fileOutputStream.flush();
                            fileOutputStream.close();
                            open.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static File getDataZipFile(Context context) {
        String path = APMidasPayAPI.getPath();
        if (!TextUtils.isEmpty(path)) {
            APLog.i("APPluginUtils", "getDataZipFile sPath:" + path);
            File file = new File(path);
            if (file.getName().startsWith("MidasPay") && file.getName().endsWith(".zip")) {
                return file;
            }
        }
        return null;
    }

    public static String getExceptionInfo(Throwable th) {
        while (th.getCause() != null) {
            th = th.getCause();
        }
        Writer stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter, true));
        return stringWriter.getBuffer().toString();
    }

    public static String getInitErrorMsg() {
        return d;
    }

    public static File getInstallPath(Context context, String str) {
        APLog.i("APPluginUtils", "APPluginUtils getInstallPath pluginName:" + str + " sInstallPathMap:" + b);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = (File) b.get(str);
        if (file != null) {
            return file;
        }
        File a = a(context);
        if (a == null) {
            return null;
        }
        File[] listFiles = a.listFiles();
        for (File a2 : listFiles) {
            if (a2.getName().startsWith(str)) {
                b.put(str, a2);
                return a2;
            }
        }
        return file;
    }

    public static String getInstallPathString(Context context, String str) {
        String str2 = "";
        try {
            File installPath = getInstallPath(context, str);
            if (installPath != null) {
                str2 = installPath.getCanonicalPath();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str2;
    }

    public static File getLibPath(Context context) {
        return context.getDir("midaslib_" + e, 0);
    }

    public static String getMD5FromPath(String str) {
        String str2 = "";
        if (!TextUtils.isEmpty(str)) {
            int lastIndexOf = str.lastIndexOf(ShareConstants.PATCH_SUFFIX);
            int lastIndexOf2 = str.lastIndexOf("_");
            if (!(lastIndexOf == -1 || lastIndexOf2 == -1)) {
                try {
                    str2 = str.substring(lastIndexOf2 + 1, lastIndexOf);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return str2;
    }

    public static int getMidasCoreVersionCode(Context context) {
        String canonicalPath;
        String str = "";
        File a = a(context);
        if (a != null) {
            File[] listFiles = a.listFiles();
            int i = 0;
            while (i < listFiles.length) {
                File file = listFiles[i];
                if (file.getName().startsWith("MidasCore")) {
                    try {
                        canonicalPath = file.getCanonicalPath();
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                        canonicalPath = str;
                    }
                } else {
                    i++;
                }
            }
        }
        canonicalPath = str;
        PackageInfo packageInfo = getPackageInfo(context, canonicalPath);
        return packageInfo != null ? packageInfo.versionCode : 0;
    }

    public static String getMidasCoreVersionName(Context context) {
        String canonicalPath;
        String str = "";
        String str2 = "";
        File a = a(context);
        if (a != null) {
            File[] listFiles = a.listFiles();
            int i = 0;
            while (i < listFiles.length) {
                File file = listFiles[i];
                if (file.getName().startsWith("MidasCore")) {
                    try {
                        canonicalPath = file.getCanonicalPath();
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                        canonicalPath = str2;
                    }
                } else {
                    i++;
                }
            }
        }
        canonicalPath = str2;
        PackageInfo packageInfo = getPackageInfo(context, canonicalPath);
        return packageInfo != null ? packageInfo.versionName : str;
    }

    public static ArrayList<String> getMidasEmptyPaht(Context context) {
        if (f == null) {
            f = new ArrayList();
            File b = b(context);
            if (b != null) {
                File[] listFiles = b.listFiles();
                for (File file : listFiles) {
                    if (file.getName().startsWith("MidasEmptyRes") && file.getName().endsWith(ShareConstants.PATCH_SUFFIX)) {
                        try {
                            f.add(file.getCanonicalPath());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return f;
    }

    public static File getOptimizedDexPath(Context context) {
        return context.getDir("midasodex", 0);
    }

    public static PackageInfo getPackageInfo(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        PackageInfo packageInfo = (PackageInfo) c.get(str);
        if (packageInfo != null) {
            return packageInfo;
        }
        packageInfo = packageManager.getPackageArchiveInfo(str, 128);
        if (packageInfo == null) {
            return packageInfo;
        }
        c.put(str, packageInfo);
        return packageInfo;
    }

    private static int h(Context context) {
        g(context);
        Object obj = "MidasPay.zip";
        if (TextUtils.isEmpty(obj)) {
            return -2;
        }
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(obj);
            b(context, inputStream);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return 0;
        } catch (IOException e2) {
            e2.printStackTrace();
            if (inputStream == null) {
                return -1;
            }
            try {
                inputStream.close();
                return -1;
            } catch (IOException e3) {
                e3.printStackTrace();
                return -1;
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e32) {
                    e32.printStackTrace();
                }
            }
        }
    }

    private static int i(Context context) {
        InputStream fileInputStream;
        IOException e;
        Throwable th;
        g(context);
        File dataZipFile = getDataZipFile(context);
        APLog.i("APPluginUtils", "installFromData zipFile:" + dataZipFile);
        if (dataZipFile == null) {
            return -2;
        }
        InputStream inputStream = null;
        try {
            APLog.i("APPluginUtils", "installFromData filePath:" + dataZipFile.getCanonicalPath());
            fileInputStream = new FileInputStream(dataZipFile);
            try {
                b(context, fileInputStream);
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                return 0;
            } catch (IOException e3) {
                e2 = e3;
                try {
                    e2.printStackTrace();
                    if (fileInputStream != null) {
                        return -1;
                    }
                    try {
                        fileInputStream.close();
                        return -1;
                    } catch (IOException e4) {
                        e4.printStackTrace();
                        return -1;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    inputStream = fileInputStream;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e42) {
                            e42.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e5) {
            e2 = e5;
            fileInputStream = null;
            e2.printStackTrace();
            if (fileInputStream != null) {
                return -1;
            }
            fileInputStream.close();
            return -1;
        } catch (Throwable th3) {
            th = th3;
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    public static int installFromLocal(Context context) {
        int installFromLocalByPath;
        deleteBKPlugin(context);
        a(context, c(context), d(context));
        try {
            installFromLocalByPath = installFromLocalByPath(context, c(context));
        } catch (Exception e) {
            installFromLocalByPath = -1;
        }
        if (installFromLocalByPath != 0) {
            unInstallPlugin(context);
            installFromLocalByPath = installFromLocalByPath(context, d(context));
            if (installFromLocalByPath != 0) {
                unInstallPlugin(context);
            }
        } else {
            deleteUpdatePlugin(context);
        }
        APLog.i("APPluginUtils", "installFromLocal state:" + installFromLocalByPath);
        return installFromLocalByPath;
    }

    public static int installFromLocalByPath(Context context, File file) {
        InputStream inputStream;
        BufferedOutputStream bufferedOutputStream;
        Exception exception;
        Exception exception2;
        Throwable th;
        Throwable th2;
        BufferedOutputStream bufferedOutputStream2 = null;
        InputStream inputStream2 = null;
        File a = a(context);
        if (file != null) {
            File[] listFiles = file.listFiles();
            int i = 0;
            inputStream = null;
            bufferedOutputStream = null;
            while (i < listFiles.length) {
                try {
                    File file2 = listFiles[i];
                    String name = file2.getName();
                    APLog.i("APPluginUtils", "installFromLocal src fileName:" + name);
                    if (name.endsWith(ShareConstants.PATCH_SUFFIX) || name.endsWith(".ini")) {
                        String str = name.split("\\_")[0];
                        APLog.i("APPluginUtils", "installFromLocal name:" + str);
                        if (a != null) {
                            File[] listFiles2 = a.listFiles();
                            for (File file3 : listFiles2) {
                                String name2 = file3.getName();
                                APLog.i("APPluginUtils", "installFromLocal destFileName:" + name2);
                                if (name2.startsWith(str)) {
                                    file3.delete();
                                }
                            }
                        }
                        File file4 = new File(a.getCanonicalPath() + File.separator + name);
                        APLog.i("APPluginUtils", "installFromLocal destfileName:" + name);
                        bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(file4));
                        try {
                            inputStream2 = new FileInputStream(file2.getCanonicalPath());
                        } catch (Exception e) {
                            exception = e;
                            inputStream2 = inputStream;
                            exception2 = exception;
                        } catch (Throwable th3) {
                            th = th3;
                            inputStream2 = inputStream;
                            th2 = th;
                        }
                        try {
                            byte[] bArr = new byte[8192];
                            while (true) {
                                int read = inputStream2.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                bufferedOutputStream2.write(bArr, 0, read);
                            }
                            bufferedOutputStream2.flush();
                            inputStream = inputStream2;
                            bufferedOutputStream = bufferedOutputStream2;
                        } catch (Exception e2) {
                            exception2 = e2;
                        }
                    }
                    i++;
                } catch (Exception e3) {
                    exception = e3;
                    bufferedOutputStream2 = bufferedOutputStream;
                    inputStream2 = inputStream;
                    exception2 = exception;
                } catch (Throwable th4) {
                    th = th4;
                    bufferedOutputStream2 = bufferedOutputStream;
                    inputStream2 = inputStream;
                    th2 = th;
                }
            }
        } else {
            inputStream = null;
            bufferedOutputStream = null;
        }
        if (bufferedOutputStream != null) {
            try {
                bufferedOutputStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
                return 0;
            }
        }
        if (inputStream != null) {
            inputStream.close();
        }
        return 0;
        try {
            exception2.printStackTrace();
            d = exception2.toString();
            if (bufferedOutputStream2 != null) {
                try {
                    bufferedOutputStream2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                    return -1;
                }
            }
            if (inputStream2 == null) {
                return -1;
            }
            inputStream2.close();
            return -1;
        } catch (Throwable th5) {
            th2 = th5;
            if (bufferedOutputStream2 != null) {
                try {
                    bufferedOutputStream2.close();
                } catch (IOException e52) {
                    e52.printStackTrace();
                    throw th2;
                }
            }
            if (inputStream2 != null) {
                inputStream2.close();
            }
            throw th2;
        }
    }

    public static int installPlugin(Context context, int i) {
        int i2 = 0;
        try {
            unInstallPlugin(context);
            if (i == 1) {
                i2 = h(context);
            } else if (i == 2) {
                i2 = i(context);
            }
            if (i2 != 0) {
                unInstallPlugin(context);
            }
        } catch (Throwable e) {
            APLog.w("APPluginUtils", "installPlugin Exception:" + e.toString());
            d = getExceptionInfo(e);
        }
        return i2;
    }

    public static int isNeedUpdateFromAssets(Context context) {
        int i;
        int e;
        File dataZipFile;
        Object obj = "";
        try {
            obj = getInstallPath(context, "MidasCore").getCanonicalPath();
        } catch (Exception e2) {
        }
        try {
            i = !TextUtils.isEmpty(obj) ? getPackageInfo(context, obj).versionCode : 0;
            try {
                e = e(context);
            } catch (Exception e3) {
                e = i;
                i = e;
                e = 0;
                APLog.i("APPluginUtils", "isNeedUpdateFromAssets coreVC:" + i + " assetsVC:" + e);
                if (e <= i) {
                    return 1;
                }
                dataZipFile = getDataZipFile(context);
                if (dataZipFile != null) {
                    return 0;
                }
                e = a(context, dataZipFile.getAbsolutePath());
                APLog.i("APPluginUtils", "isNeedUpdateFromAssets dataVC:" + e);
                return e > i ? 0 : 2;
            }
        } catch (Exception e4) {
            e = 0;
            i = e;
            e = 0;
            APLog.i("APPluginUtils", "isNeedUpdateFromAssets coreVC:" + i + " assetsVC:" + e);
            if (e <= i) {
                return 1;
            }
            dataZipFile = getDataZipFile(context);
            if (dataZipFile != null) {
                return 0;
            }
            e = a(context, dataZipFile.getAbsolutePath());
            APLog.i("APPluginUtils", "isNeedUpdateFromAssets dataVC:" + e);
            if (e > i) {
            }
        }
        APLog.i("APPluginUtils", "isNeedUpdateFromAssets coreVC:" + i + " assetsVC:" + e);
        if (e <= i) {
            return 1;
        }
        dataZipFile = getDataZipFile(context);
        if (dataZipFile != null) {
            return 0;
        }
        e = a(context, dataZipFile.getAbsolutePath());
        APLog.i("APPluginUtils", "isNeedUpdateFromAssets dataVC:" + e);
        if (e > i) {
        }
    }

    public static boolean isNeedUpdateFromLocal(Context context) {
        File c = c(context);
        try {
            File file = new File(c.getCanonicalPath() + File.separator + "MidasSign.ini");
            if (!file.exists()) {
                return false;
            }
            HashMap hashMap = new HashMap();
            a(hashMap, file);
            File[] listFiles = c.listFiles();
            int i = 0;
            boolean z = false;
            while (i < listFiles.length) {
                File file2 = listFiles[i];
                String name = file2.getName();
                boolean z2 = name.startsWith("MidasCore") ? true : z;
                if (name.endsWith(ShareConstants.PATCH_SUFFIX)) {
                    z = a(file2.getCanonicalPath(), (String) hashMap.get(name.split("\\_")[0]));
                    APLog.i("APPluginUtils", "isNeedUpdateFromLocal List valid:" + z + " fileName:" + name);
                    if (!z) {
                        return false;
                    }
                }
                i++;
                z = z2;
            }
            APLog.i("APPluginUtils", "isNeedUpdateFromLocal List isMidasCore:" + z);
            return z;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isPluginInstalled(Context context) {
        boolean z;
        boolean z2;
        boolean z3;
        Exception exception;
        try {
            File a = a(context);
            if (a != null) {
                File[] listFiles = a.listFiles();
                int i = 0;
                z = false;
                z2 = false;
                while (i < listFiles.length) {
                    try {
                        File file = listFiles[i];
                        if (file.getName().startsWith("MidasCore")) {
                            z2 = true;
                        } else if (file.getName().startsWith("MidasPay")) {
                            z = true;
                        }
                        i++;
                    } catch (Exception e) {
                        Exception exception2 = e;
                        z3 = z2;
                        z2 = z;
                        exception = exception2;
                    }
                }
            } else {
                z = false;
                z2 = false;
            }
        } catch (Exception e2) {
            exception = e2;
            z2 = false;
            z3 = false;
            APLog.i("isPluginInstalled err", exception.toString());
            z = z2;
            z2 = z3;
            if (!z2) {
            }
        }
        return z2 && z;
    }

    public static boolean isPluginValid(Context context) {
        return isPluginValid(context, a(context));
    }

    public static boolean isPluginValid(Context context, File file) {
        try {
            File file2 = new File(file.getCanonicalPath() + File.separator + "MidasSign.ini");
            if (file2.exists()) {
                int i;
                HashMap hashMap = new HashMap();
                a(hashMap, file2);
                if (file != null) {
                    File[] listFiles = file.listFiles();
                    int i2 = 0;
                    i = 0;
                    while (i2 < listFiles.length) {
                        int i3;
                        File file3 = listFiles[i2];
                        String name = file3.getName();
                        if (name.startsWith("MidasSign")) {
                            i3 = i;
                        } else if (name.startsWith("Midas")) {
                            name = (String) hashMap.get(name.split("\\_")[0]);
                            if (TextUtils.isEmpty(name)) {
                                i3 = i;
                            } else if (a(file3.getCanonicalPath(), name)) {
                                i3 = i + 1;
                            } else {
                                file3.delete();
                                i3 = i;
                            }
                        } else {
                            i3 = i;
                        }
                        i2++;
                        i = i3;
                    }
                } else {
                    i = 0;
                }
                return i == hashMap.size() || i >= 3;
            } else {
                APLog.i("APPluginUtils", "isPluginValid sig file is not found");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void release() {
        b.clear();
        c.clear();
    }

    public static String toHexString(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            stringBuilder.append(a[(bArr[i] & ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK) >>> 4]);
            stringBuilder.append(a[bArr[i] & 15]);
        }
        return stringBuilder.toString();
    }

    public static void unInstallPlugin(Context context) {
        APLog.d("APPluginUtils", "unInstallPlugin");
        deletePlugin(context);
        deleteDex(context);
        deleteLibs(context);
        b.clear();
        c.clear();
        APPluginStatic.release();
        e++;
    }

    public static void updateLibExtendNum() {
        e++;
        APLog.i("APPluginUtils", "updateLibExtendNum libExtend:" + e);
    }
}
