package com.dynamicload;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import com.dynamicload.Lib.DLConstants;
import com.dynamicload.Lib.DLException;
import com.dynamicload.Lib.DLPluginManager;
import com.dynamicload.Lib.DLPluginPackage;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import dalvik.system.DexClassLoader;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/* compiled from: DLUtils */
public class c {
    public static void a(String str) {
        Log.i(DLConstants.TAG, str);
    }

    public static void b(String str) {
        Log.d(DLConstants.TAG, str);
    }

    public static void c(String str) {
        Log.w(DLConstants.TAG, str);
    }

    public static void d(String str) {
        Log.e(DLConstants.TAG, str);
    }

    public static String a() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/cpuinfo"), Charset.defaultCharset()));
            String readLine = bufferedReader.readLine();
            bufferedReader.close();
            String[] split = readLine.split(":\\s+", 2);
            if (split.length >= 2) {
                return split[1];
            }
        } catch (FileNotFoundException e) {
            c("getCpuName Exception e= " + e);
        } catch (Exception e2) {
            c("getCpuName Exception e= " + e2);
        }
        return "ARM";
    }

    public static void a(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[8192];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                outputStream.flush();
                outputStream.close();
                inputStream.close();
                return;
            }
        }
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                c("closeStreamSafe Exception e= " + e);
            }
        }
    }

    public static void a(InputStream inputStream, String str, File file, File file2) {
        if (TextUtils.isEmpty(str) || file == null || inputStream == null) {
            throw new DLException(str, DLConstants.LOAD_ERR_FILE_NOT_EXIST);
        }
        a(inputStream, str, file);
        a(str, file, file2);
    }

    private static void a(InputStream inputStream, String str, File file) throws DLException {
        Closeable fileOutputStream;
        Throwable th;
        Object obj;
        Closeable closeable = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                a(inputStream, (OutputStream) fileOutputStream);
                a(fileOutputStream);
            } catch (FileNotFoundException e) {
                try {
                    throw new DLException(str, DLConstants.LOAD_ERR_FILE_NOT_EXIST);
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    closeable = fileOutputStream;
                    th = th3;
                    a(closeable);
                    throw th;
                }
            } catch (IOException e2) {
                IOException iOException = e2;
                closeable = fileOutputStream;
                IOException iOException2 = iOException;
                try {
                    c("copyApkFile Exception e= " + obj);
                    a(closeable);
                } catch (Throwable th4) {
                    th = th4;
                    a(closeable);
                    throw th;
                }
            }
        } catch (FileNotFoundException e3) {
            fileOutputStream = null;
            throw new DLException(str, DLConstants.LOAD_ERR_FILE_NOT_EXIST);
        } catch (IOException e4) {
            obj = e4;
            c("copyApkFile Exception e= " + obj);
            a(closeable);
        }
    }

    private static void a(String str, File file, File file2) throws DLException {
        Throwable th;
        Object e;
        ZipFile zipFile;
        if (file2.mkdirs() || file2.exists()) {
            CharSequence charSequence;
            String a = a();
            String str2 = DLConstants.CPU_ARMEABI;
            if (a.toLowerCase().contains("64")) {
                charSequence = DLConstants.CPU_ARME64;
            } else if (a.toLowerCase().contains("arm")) {
                r1 = DLConstants.CPU_ARMEABI;
            } else if (a.toLowerCase().contains(DLConstants.CPU_X86)) {
                r1 = DLConstants.CPU_X86;
            } else if (a.toLowerCase().contains(DLConstants.CPU_MIPS)) {
                r1 = DLConstants.CPU_MIPS;
            } else {
                r1 = str2;
            }
            ZipFile zipFile2;
            try {
                zipFile2 = new ZipFile(file);
                try {
                    Enumeration entries = zipFile2.entries();
                    while (entries.hasMoreElements()) {
                        ZipEntry zipEntry = (ZipEntry) entries.nextElement();
                        if (!zipEntry.isDirectory()) {
                            String name = zipEntry.getName();
                            if (name.endsWith(".so") && name.contains(charSequence)) {
                                zipEntry.getTime();
                                String substring = name.substring(name.lastIndexOf("/") + 1);
                                a(zipFile2.getInputStream(zipEntry), new FileOutputStream(new File(file2, substring)));
                                a("copyApkLib success name: " + name);
                                if (charSequence == DLConstants.CPU_ARME64) {
                                    try {
                                        System.load(file2.getAbsolutePath() + "/" + substring);
                                    } catch (UnsatisfiedLinkError e2) {
                                        b(str, file, file2);
                                        Log.e("copyApkLib error", e2.getMessage());
                                    } catch (Throwable th2) {
                                        Log.e("copyApkLib error", th2.getMessage());
                                    }
                                } else {
                                    continue;
                                }
                            }
                        }
                    }
                    if (zipFile2 != null) {
                        try {
                            zipFile2.close();
                            return;
                        } catch (Exception e3) {
                            return;
                        }
                    }
                    return;
                } catch (IOException e4) {
                    e = e4;
                    zipFile = zipFile2;
                } catch (Throwable th3) {
                    th2 = th3;
                }
            } catch (IOException e5) {
                e = e5;
                zipFile = null;
                try {
                    c("copyApkLib Exception e= " + e);
                    if (zipFile != null) {
                        try {
                            zipFile.close();
                        } catch (Exception e6) {
                        }
                    }
                    throw new DLException(str, DLConstants.LOAD_ERR_SO_LOAD_FAIL);
                } catch (Throwable th4) {
                    th2 = th4;
                    zipFile2 = zipFile;
                    if (zipFile2 != null) {
                        try {
                            zipFile2.close();
                        } catch (Exception e7) {
                        }
                    }
                    throw th2;
                }
            } catch (Throwable th5) {
                th2 = th5;
                zipFile2 = null;
                if (zipFile2 != null) {
                    zipFile2.close();
                }
                throw th2;
            }
        }
        throw new DLException(str, DLConstants.LOAD_ERR_SO_LOAD_FAIL);
    }

    private static void b(String str, File file, File file2) throws DLException {
        Object e;
        Throwable th;
        if (file2.mkdirs() || file2.exists()) {
            CharSequence charSequence = DLConstants.CPU_ARMEABI;
            ZipFile zipFile;
            try {
                zipFile = new ZipFile(file);
                try {
                    Enumeration entries = zipFile.entries();
                    while (entries.hasMoreElements()) {
                        ZipEntry zipEntry = (ZipEntry) entries.nextElement();
                        if (!zipEntry.isDirectory()) {
                            String name = zipEntry.getName();
                            if (name.endsWith(".so") && name.contains(charSequence)) {
                                String substring = name.substring(name.lastIndexOf("/") + 1);
                                File file3 = new File(file2.getAbsolutePath() + "/" + substring);
                                if (file3.exists() && file3.isFile()) {
                                    file3.delete();
                                }
                                a(zipFile.getInputStream(zipEntry), new FileOutputStream(new File(file2, substring)));
                                a("copyApkLib success name: " + name);
                            }
                        }
                    }
                    if (zipFile != null) {
                        try {
                            zipFile.close();
                            return;
                        } catch (Exception e2) {
                            return;
                        }
                    }
                    return;
                } catch (IOException e3) {
                    e = e3;
                    try {
                        c("copyApkLib Exception e= " + e);
                        if (zipFile != null) {
                            try {
                                zipFile.close();
                            } catch (Exception e4) {
                            }
                        }
                        throw new DLException(str, DLConstants.LOAD_ERR_SO_LOAD_FAIL);
                    } catch (Throwable th2) {
                        th = th2;
                        if (zipFile != null) {
                            try {
                                zipFile.close();
                            } catch (Exception e5) {
                            }
                        }
                        throw th;
                    }
                }
            } catch (IOException e6) {
                e = e6;
                zipFile = null;
                c("copyApkLib Exception e= " + e);
                if (zipFile != null) {
                    zipFile.close();
                }
                throw new DLException(str, DLConstants.LOAD_ERR_SO_LOAD_FAIL);
            } catch (Throwable th3) {
                th = th3;
                zipFile = null;
                if (zipFile != null) {
                    zipFile.close();
                }
                throw th;
            }
        }
        throw new DLException(str, DLConstants.LOAD_ERR_SO_LOAD_FAIL);
    }

    public static PackageInfo a(Context context, String str, File file, File file2, DLPluginPackage dLPluginPackage) throws DLException {
        int i = 0;
        PackageInfo a = a(context, file, (int) Opcodes.SUB_FLOAT_2ADDR);
        if (a == null) {
            throw new DLException(str, 203);
        } else if (dLPluginPackage.apkVersion > a.versionCode) {
            throw new DLException(str, DLConstants.LOAD_ERR_APK_DATE);
        } else {
            if (!a.a) {
                int length;
                HashSet hashSet = new HashSet();
                for (Object add : a.signatures) {
                    hashSet.add(add);
                }
                try {
                    PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
                    HashSet hashSet2 = new HashSet();
                    Signature[] signatureArr = packageInfo.signatures;
                    length = signatureArr.length;
                    while (i < length) {
                        hashSet2.add(signatureArr[i]);
                        i++;
                    }
                    if (!hashSet2.equals(hashSet)) {
                        throw new DLException(file.getName(), DLConstants.LOAD_ERR_SIGNATURES);
                    }
                } catch (NameNotFoundException e) {
                    throw new DLException(str, DLConstants.LOAD_ERR_NAME_NOT_FOUND);
                }
            }
            return a;
        }
    }

    private static PackageInfo a(Context context, File file, int i) {
        if (file == null || !file.exists()) {
            return null;
        }
        PackageInfo packageArchiveInfo;
        PackageManager packageManager = context.getPackageManager();
        if (VERSION.SDK_INT >= 20) {
            packageArchiveInfo = packageManager.getPackageArchiveInfo(file.getAbsolutePath(), i & -65);
            packageArchiveInfo.signatures = c(file);
        } else {
            packageArchiveInfo = packageManager.getPackageArchiveInfo(file.getAbsolutePath(), i);
        }
        if (!(packageArchiveInfo == null || packageArchiveInfo.signatures != null || (i & 64) == 0)) {
            try {
                packageArchiveInfo.signatures = a(file);
            } catch (IOException e) {
                d("Error retrieving signatures of " + file + ".");
            }
        }
        return packageArchiveInfo == null ? packageArchiveInfo : packageArchiveInfo;
    }

    private static boolean a(String str, String str2) {
        return str.regionMatches(true, str.length() - str2.length(), str2, 0, str2.length());
    }

    private static List<String> b(File file) throws IOException {
        List<String> arrayList = new ArrayList(1);
        ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(file)));
        while (true) {
            try {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry == null) {
                    break;
                }
                String name = nextEntry.getName();
                if (a(name, ".SF")) {
                    arrayList.add(name.substring(0, name.length() - 3));
                }
            } finally {
                zipInputStream.close();
            }
        }
        return arrayList;
    }

    private static Signature[] c(File file) {
        ZipFile zipFile = new ZipFile(file);
        List arrayList = new ArrayList();
        for (String str : b(file)) {
            InputStream inputStream;
            int i;
            ZipEntry entry = zipFile.getEntry(str + ".RSA");
            ZipEntry entry2 = zipFile.getEntry(str + ".DSA");
            ZipEntry entry3 = zipFile.getEntry(str + ".EC");
            if (entry != null) {
                inputStream = zipFile.getInputStream(entry);
                i = 54;
            } else if (entry2 != null) {
                inputStream = zipFile.getInputStream(entry2);
                i = 54;
            } else if (entry3 != null) {
                inputStream = zipFile.getInputStream(entry3);
                i = 58;
            } else {
                i = 0;
                inputStream = null;
            }
            if (inputStream != null) {
                try {
                    arrayList.add(new Signature(a(inputStream, i)));
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                } catch (Throwable th) {
                    inputStream.close();
                }
            }
        }
        zipFile.close();
        return (Signature[]) arrayList.toArray(new Signature[arrayList.size()]);
    }

    private static byte[] a(InputStream inputStream, int i) throws IOException {
        inputStream.skip((long) i);
        int read = (inputStream.read() << 8) | inputStream.read();
        byte[] bArr = new byte[read];
        inputStream.read(bArr, 0, read);
        return bArr;
    }

    public static Signature[] a(File file) throws IOException {
        Signature[] signatureArr = null;
        try {
            JarFile jarFile = new JarFile(file);
            Certificate[] a = a(jarFile, jarFile.getJarEntry(ShareConstants.RES_MANIFEST));
            jarFile.close();
            if (a == null || a.length <= 0) {
                d("Package has no certificates!");
                return signatureArr;
            }
            signatureArr = new Signature[a.length];
            for (int i = 0; i < a.length; i++) {
                signatureArr[i] = new Signature(a[i].getEncoded());
            }
            return signatureArr;
        } catch (CertificateEncodingException e) {
            d("Exception reading " + file);
        }
    }

    private static Certificate[] a(JarFile jarFile, JarEntry jarEntry) {
        byte[] bArr = new byte[1024];
        try {
            InputStream inputStream = jarFile.getInputStream(jarEntry);
            do {
            } while (inputStream.read(bArr, 0, bArr.length) != -1);
            inputStream.close();
            return jarEntry.getCertificates();
        } catch (IOException e) {
            d("Exception reading " + jarEntry.getName() + " in " + jarFile.getName());
            return null;
        } catch (RuntimeException e2) {
            d("Exception reading " + jarFile.getName());
            return null;
        }
    }

    public static void a(Context context, Context context2) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method declaredMethod = ((DexClassLoader) context2.getClassLoader()).loadClass("android.content.ContextWrapper").getDeclaredMethod("attachBaseContext", new Class[]{Context.class});
        declaredMethod.setAccessible(true);
        declaredMethod.invoke(context, new Object[]{context2});
    }

    public static ClassLoader a(Intent intent) {
        String dataString = intent.getDataString();
        b("setupIntentExtrasClassLoader uri = " + dataString);
        try {
            if (!TextUtils.isEmpty(dataString) && dataString.startsWith(DLConstants.DLINTENT_DATA_SCHEME)) {
                ClassLoader classLoader = DLPluginManager.getInstance().getPackage(dataString.substring(DLConstants.DLINTENT_DATA_SCHEME.length())).classLoader;
                b("setupIntentExtrasClassLoader 1 loader= " + classLoader + " uri = " + dataString);
                intent.setExtrasClassLoader(classLoader);
                return classLoader;
            }
        } catch (Exception e) {
            d("setupIntentExtrasClassLoader Exception e= " + e + " uri= " + dataString);
            com.qq.reader.common.monitor.debug.c.e(DLConstants.TAG, "setup classLoader for " + String.valueOf(dataString) + " fail");
        }
        return null;
    }
}
