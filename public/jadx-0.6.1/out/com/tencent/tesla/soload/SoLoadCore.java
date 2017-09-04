package com.tencent.tesla.soload;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import com.dynamicload.Lib.DLConstants;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import dalvik.system.BaseDexClassLoader;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class SoLoadCore {
    public static final int API_BELOW_14 = 128;
    public static final int API_EUQAL_AND_ABOVE_14 = 256;
    public static final String APP_ROOT = "/data/data/com.tencent.mobileqq/files";
    private static final String CONFIGPATH = "soconfig.cfg";
    public static final int CONFIG_SO_PATH_IN_LIB = 32768;
    public static final int CONFIG_SO_PATH_IN_TXLIB = 16384;
    public static final int IF_CONFIG_SO_CRCCHECK_SUCCESS = 8192;
    public static final int IF_GENERATE_CACHE_SUCCESS = 2097152;
    public static final int IF_GET_AVAILIABLE_CRCVALUE = 1048576;
    public static final int IF_READ_CONFIGFILE_SUCCESS = 65536;
    public static final int IF_RELEASE_BY_CLASSLOADER_SUCCESS = 64;
    public static final int IF_RELEASE_BY_ZIP_CHECK_SUCCESS = 8;
    public static final int IF_RELEASE_SOFILE_CHECK_SUCCESS = 32;
    public static final int IF_SOFILE_EXIST_IN_LIBPATH = 2048;
    public static final int IF_SOFILE_IN_LIB_CHECK_SUCCESS = 1024;
    public static final int IF_SO_CONFIG_EXIST = 131072;
    public static final int IF_TRY_LOADSO_INLIBPATH_SUCCESS = 512;
    public static final int IF_TRY_LOAD_CONFIG_SO_SUCCESS = 4096;
    public static final int IF_TRY_LOAD_LIBRARY_SUCCESS = 524288;
    public static final int IF_TRY_LOAD_RELEASESO_SUCCESS = 16;
    public static final int IF_TRY_LOAD_SO_BY_ZIP_SUCCESS = 4;
    public static final int LOAD_SO_SUCCESS_SAVE_AND_RETURN = 2;
    public static final String PATH_LIB = "/lib/";
    public static final String PATH_TX_LIB = "/txlib/";
    public static final int TRY_LOAD_LIBRARY_SUCCESS = 262144;
    private static HashMap<String, Long> assestCrcConfigs = null;

    public static int loadSo(Context context, String str) {
        String libActualName = getLibActualName(str);
        int i = 2097152;
        long j = -1;
        initAssestCrcConfigs(context);
        if (!(assestCrcConfigs == null || assestCrcConfigs.isEmpty())) {
            Long l = (Long) assestCrcConfigs.get(getPlatformString() + "/" + libActualName);
            if (l == null || l.longValue() <= 1) {
                l = (Long) assestCrcConfigs.get("armeabi/" + libActualName);
                if (l == null || l.longValue() <= 1) {
                    i = 3145728;
                } else {
                    j = l.longValue();
                    i = 3145728;
                }
            } else {
                j = l.longValue();
                i = 3145728;
            }
        }
        if (j < 0) {
            i |= 524288;
            try {
                System.loadLibrary(str);
                return i | 262144;
            } catch (UnsatisfiedLinkError e) {
                return i;
            }
        }
        long cRC32Value;
        String str2 = context.getFilesDir() + "/" + "soconfigs";
        new File(str2).mkdirs();
        i |= IF_SO_CONFIG_EXIST;
        File file = new File(new StringBuilder(String.valueOf(str2)).append("/").append(str).append(".cfg").toString());
        if (file.exists()) {
            ConfigStruct readConfig = readConfig(file);
            i |= 65536;
            if (readConfig != null) {
                if (readConfig.mSopath.contains(PATH_TX_LIB)) {
                    i |= 16384;
                } else {
                    i |= 32768;
                }
                File file2 = new File(readConfig.mSopath);
                i |= 8192;
                if (readConfig.mCrcvalue == j && file2.exists()) {
                    i |= 4096;
                    try {
                        System.load(file2.getAbsolutePath());
                        return i | 2;
                    } catch (UnsatisfiedLinkError e2) {
                    }
                }
            }
        }
        String str3 = getAppWorkPath(context) + PATH_TX_LIB;
        File file3 = new File(new StringBuilder(String.valueOf(getAppWorkPath(context) + PATH_LIB)).append(libActualName).toString());
        i |= 2048;
        if (file3.exists()) {
            i |= 1024;
            cRC32Value = getCRC32Value(file3);
            if (cRC32Value == j) {
                i |= 512;
                if (loadAndSave(file3, cRC32Value, file)) {
                    return i | 2;
                }
            }
        }
        String osClassLoader = getOsClassLoader();
        File file4 = null;
        int i2;
        if (osClassLoader.equals("equalAndAbove14")) {
            i2 = i | 256;
            File soOrDexByBaseDexClassLoader = getSoOrDexByBaseDexClassLoader(context, "lib/" + getPlatformString() + "/" + libActualName, new StringBuilder(String.valueOf(str3)).append(libActualName).toString());
            i = i2;
            file4 = soOrDexByBaseDexClassLoader;
        } else if (osClassLoader.equals("below14")) {
            i2 = i | 128;
            i = i2;
            file4 = getSoOrDexByPathClassLoader(context, libActualName, str3);
        }
        i |= 64;
        if (file4 != null) {
            cRC32Value = getCRC32Value(file4);
            i |= 32;
            if (cRC32Value == j) {
                i |= 16;
                if (loadAndSave(file4, cRC32Value, file)) {
                    return i | 2;
                }
            } else if (file4.exists()) {
                file4.delete();
            }
        }
        file4 = releaseFromApk(getApkPath(context), libActualName, str3);
        long cRC32Value2 = getCRC32Value(file4);
        i |= 8;
        if (cRC32Value2 != j) {
            return i;
        }
        i |= 4;
        if (loadAndSave(file4, cRC32Value2, file)) {
            return i | 2;
        }
        return i;
    }

    public static File releaseSoAnyway(Context context, String str, String str2) {
        File soOrDexByBaseDexClassLoader;
        if (hasBaseDexClassloader()) {
            String str3 = "lib/" + getPlatformString() + "/" + str;
            if (!str2.endsWith(File.separator)) {
                str2 = new StringBuilder(String.valueOf(str2)).append(File.separator).toString();
            }
            soOrDexByBaseDexClassLoader = getSoOrDexByBaseDexClassLoader(context, str3, new StringBuilder(String.valueOf(str2)).append(str).toString());
        } else {
            soOrDexByBaseDexClassLoader = getSoOrDexByPathClassLoader(context, str, str2);
        }
        if (soOrDexByBaseDexClassLoader == null) {
            return releaseFromApk(getApkPath(context), str, str2);
        }
        return soOrDexByBaseDexClassLoader;
    }

    private static boolean loadAndSave(File file, long j, File file2) {
        boolean z;
        try {
            System.load(file.getAbsolutePath());
            z = true;
        } catch (UnsatisfiedLinkError e) {
            z = false;
        }
        if (z) {
            writeConfig(new ConfigStruct(j, file.getAbsolutePath()), file2);
        }
        return z;
    }

    private static synchronized void initAssestCrcConfigs(Context context) {
        synchronized (SoLoadCore.class) {
            if (assestCrcConfigs == null) {
                InputStream inputStream = null;
                try {
                    assestCrcConfigs = new HashMap();
                    String str = "";
                    inputStream = context.getResources().getAssets().open(CONFIGPATH);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        int indexOf = readLine.indexOf(":");
                        String substring = readLine.substring(0, indexOf);
                        long parseLong = Long.parseLong(readLine.substring(indexOf + 1));
                        if (parseLong >= 1) {
                            assestCrcConfigs.put(substring, Long.valueOf(parseLong));
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                } catch (Throwable th) {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static String getLibActualName(String str) {
        return new StringBuilder(ShareConstants.SO_PATH).append(str).append(".so").toString();
    }

    private static boolean writeConfig(ConfigStruct configStruct, File file) {
        Throwable th;
        BufferedWriter bufferedWriter = null;
        boolean z = false;
        BufferedWriter bufferedWriter2;
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            try {
                bufferedWriter2.write("crcvalue=" + configStruct.mCrcvalue);
                bufferedWriter2.newLine();
                bufferedWriter2.write("path=" + configStruct.mSopath);
                z = true;
                if (bufferedWriter2 != null) {
                    try {
                        bufferedWriter2.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e2) {
                bufferedWriter = bufferedWriter2;
                if (bufferedWriter != null) {
                    try {
                        bufferedWriter.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                return z;
            } catch (Throwable th2) {
                th = th2;
                if (bufferedWriter2 != null) {
                    try {
                        bufferedWriter2.close();
                    } catch (IOException e32) {
                        e32.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Exception e4) {
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            return z;
        } catch (Throwable th3) {
            th = th3;
            bufferedWriter2 = null;
            if (bufferedWriter2 != null) {
                bufferedWriter2.close();
            }
            throw th;
        }
        return z;
    }

    private static ConfigStruct readConfig(File file) {
        Throwable th;
        ConfigStruct configStruct = null;
        BufferedReader bufferedReader;
        try {
            String str = "";
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            try {
                String str2 = "";
                str2 = bufferedReader.readLine();
                if (str2 == null || !str2.contains("crc")) {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return configStruct;
                }
                long parseLong = Long.parseLong(str2.substring(str2.indexOf("=") + 1));
                str2 = bufferedReader.readLine();
                if (str2 == null || !str2.contains("path")) {
                    str2 = str;
                } else {
                    str2 = str2.substring(str2.indexOf("=") + 1);
                }
                if (!(parseLong == -1 || str2 == "")) {
                    configStruct = new ConfigStruct(parseLong, str2);
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                return configStruct;
            } catch (Exception e3) {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e22) {
                        e22.printStackTrace();
                    }
                }
                return configStruct;
            } catch (Throwable th2) {
                th = th2;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e222) {
                        e222.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Exception e4) {
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return configStruct;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            bufferedReader = null;
            th = th4;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
    }

    public static long getCRC32Value(InputStream inputStream) throws Exception {
        byte[] bArr = new byte[8192];
        CRC32 crc32 = new CRC32();
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return crc32.getValue();
            }
            crc32.update(bArr, 0, read);
        }
    }

    public static long getCRC32Value(File file) {
        FileInputStream fileInputStream;
        Exception e;
        Throwable th;
        long j = -100;
        if (file != null && file.exists()) {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    j = getCRC32Value((InputStream) fileInputStream);
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
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e22) {
                                e22.printStackTrace();
                            }
                        }
                        return j;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (Exception e5) {
                e = e5;
                fileInputStream = null;
                e.printStackTrace();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return j;
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        }
        return j;
    }

    public static File releaseFromApk(String str, String str2, String str3) {
        MyZipFile myZipFile;
        if (!str3.endsWith(File.separator)) {
            str3 = new StringBuilder(String.valueOf(str3)).append(File.separator).toString();
        }
        new File(str3).mkdirs();
        CharSequence charSequence = "lib/" + getPlatformString() + "/";
        try {
            myZipFile = new MyZipFile(new File(str), "lib/" + getPlatformString() + "/" + str2);
        } catch (IOException e) {
            e.printStackTrace();
            myZipFile = null;
        }
        if (myZipFile == null) {
            return null;
        }
        MyZipEntry desEntry = myZipFile.getDesEntry();
        if (desEntry == null || !desEntry.getName().contains(charSequence) || !desEntry.getName().endsWith(".so")) {
            return null;
        }
        String name = desEntry.getName();
        int lastIndexOf = name.lastIndexOf(47);
        if (lastIndexOf != -1) {
            name = name.substring(lastIndexOf + 1);
        }
        File file = new File(new StringBuilder(String.valueOf(str3)).append(name).toString());
        if (file.exists()) {
            file.delete();
        }
        byte[] bArr = new byte[4096];
        try {
            InputStream inputStream = myZipFile.getInputStream(desEntry);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            while (true) {
                int read = inputStream.read(bArr);
                if (read <= 0) {
                    return file;
                }
                fileOutputStream.write(bArr, 0, read);
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            return file;
        }
    }

    public static File getSoOrDexByPathClassLoader(Context context, String str, String str2) {
        File file = new File(new StringBuilder(String.valueOf(str2)).append(str).toString());
        ClassLoader classLoader = context.getClassLoader();
        try {
            Field declaredField = classLoader.getClass().getDeclaredField("mZips");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(classLoader);
            if (!obj.getClass().isArray()) {
                return file;
            }
            int length = Array.getLength(obj);
            ZipEntry zipEntry = null;
            ZipFile zipFile = null;
            for (int i = 0; i < length; i++) {
                zipFile = (ZipFile) Array.get(obj, i);
                zipEntry = zipFile.getEntry(str);
                if (zipEntry != null) {
                    break;
                }
            }
            if (zipEntry == null || zipFile == null) {
                return null;
            }
            copyZipEntry2File(zipEntry, zipFile, file);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static File getSoOrDexByBaseDexClassLoader(Context context, String str, String str2) {
        File file = new File(str2);
        if (file.exists()) {
            file.delete();
        }
        BaseDexClassLoader baseDexClassLoader = (BaseDexClassLoader) context.getClassLoader();
        Class superclass = context.getClassLoader().getClass().getSuperclass();
        try {
            file.createNewFile();
            Field declaredField = superclass.getDeclaredField("pathList");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(baseDexClassLoader);
            Field declaredField2 = declaredField.get(baseDexClassLoader).getClass().getDeclaredField("dexElements");
            declaredField2.setAccessible(true);
            Object obj2 = declaredField2.get(obj);
            if (obj2.getClass().isArray()) {
                int length = Array.getLength(obj2);
                if (length > 0) {
                    ZipEntry zipEntry = null;
                    ZipFile zipFile = null;
                    for (int i = 0; i < length; i++) {
                        Object obj3 = Array.get(obj2, i);
                        declaredField = obj3.getClass().getDeclaredField("zipFile");
                        declaredField.setAccessible(true);
                        zipFile = (ZipFile) declaredField.get(obj3);
                        zipEntry = zipFile.getEntry(str);
                        if (zipEntry != null) {
                            break;
                        }
                    }
                    if (zipEntry == null || zipFile == null) {
                        return null;
                    }
                    copyZipEntry2File(zipEntry, zipFile, file);
                    return file;
                }
            }
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean hasBaseDexClassloader() {
        try {
            Class.forName("dalvik.system.BaseDexClassLoader");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    private static void copyZipEntry2File(ZipEntry zipEntry, ZipFile zipFile, File file) {
        InputStream inputStream;
        BufferedInputStream bufferedInputStream;
        IOException e;
        InputStream inputStream2;
        Throwable th;
        BufferedOutputStream bufferedOutputStream = null;
        InputStream inputStream3 = null;
        BufferedOutputStream bufferedOutputStream2 = null;
        BufferedInputStream bufferedInputStream2 = null;
        if (zipEntry == null || zipFile == null) {
            if (null != null) {
                try {
                    bufferedInputStream2.close();
                } catch (IOException e2) {
                }
            }
            if (null != null) {
                inputStream3.close();
            }
            if (null != null) {
                bufferedOutputStream2.close();
            }
            try {
                zipFile.close();
                return;
            } catch (IOException e3) {
                return;
            }
        }
        try {
            inputStream = zipFile.getInputStream(zipEntry);
            try {
                bufferedInputStream = new BufferedInputStream(inputStream);
            } catch (IOException e4) {
                e = e4;
                bufferedInputStream = null;
                inputStream2 = inputStream;
                try {
                    e.printStackTrace();
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e5) {
                            zipFile.close();
                        }
                    }
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    try {
                        zipFile.close();
                    } catch (IOException e6) {
                        return;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    inputStream = inputStream2;
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e7) {
                            zipFile.close();
                            throw th;
                        }
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    try {
                        zipFile.close();
                    } catch (IOException e8) {
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedInputStream = null;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                zipFile.close();
                throw th;
            }
            try {
                byte[] bArr = new byte[8192];
                BufferedOutputStream bufferedOutputStream3 = new BufferedOutputStream(new FileOutputStream(file));
                while (true) {
                    try {
                        int read = bufferedInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        bufferedOutputStream3.write(bArr, 0, read);
                    } catch (IOException e9) {
                        e = e9;
                        bufferedOutputStream = bufferedOutputStream3;
                        inputStream2 = inputStream;
                    } catch (Throwable th4) {
                        th = th4;
                        bufferedOutputStream = bufferedOutputStream3;
                    }
                }
                if (bufferedOutputStream3 != null) {
                    bufferedOutputStream3.close();
                }
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e10) {
                    }
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bufferedOutputStream3 != null) {
                    bufferedOutputStream3.close();
                }
                try {
                    zipFile.close();
                } catch (IOException e11) {
                }
            } catch (IOException e12) {
                e = e12;
                inputStream2 = inputStream;
                e.printStackTrace();
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                zipFile.close();
            } catch (Throwable th5) {
                th = th5;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                zipFile.close();
                throw th;
            }
        } catch (IOException e13) {
            e = e13;
            bufferedInputStream = null;
            inputStream2 = null;
            e.printStackTrace();
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            if (inputStream2 != null) {
                inputStream2.close();
            }
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
            zipFile.close();
        } catch (Throwable th6) {
            th = th6;
            bufferedInputStream = null;
            inputStream = null;
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
            zipFile.close();
            throw th;
        }
    }

    private static synchronized String getPlatformString() {
        String str;
        synchronized (SoLoadCore.class) {
            str = Build.CPU_ABI;
            if (str == null || !str.contains(DLConstants.CPU_X86)) {
                if (str != null) {
                    if (str.contains("mip")) {
                        str = DLConstants.CPU_MIPS;
                    }
                }
                str = DLConstants.CPU_ARMEABI;
            } else {
                str = DLConstants.CPU_X86;
            }
        }
        return str;
    }

    private static synchronized String getDefaultPlatformString() {
        String str;
        synchronized (SoLoadCore.class) {
            str = DLConstants.CPU_ARMEABI;
        }
        return str;
    }

    public static String getApkPath(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 8192);
            if (applicationInfo != null) {
                return applicationInfo.sourceDir;
            }
            return null;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getAppWorkPath(Context context) {
        try {
            return context.getFilesDir().getParent();
        } catch (Exception e) {
            Object baseContext;
            if (context instanceof ContextWrapper) {
                baseContext = ((ContextWrapper) context).getBaseContext();
            } else {
                Field declaredField = ContextWrapper.class.getDeclaredField("mBase");
                declaredField.setAccessible(true);
                baseContext = declaredField.get(context);
            }
            Method declaredMethod = baseContext.getClass().getDeclaredMethod("getDataDirFile", new Class[0]);
            declaredMethod.setAccessible(true);
            return ((File) declaredMethod.invoke(baseContext, new Object[0])).getAbsolutePath();
        } catch (Throwable th) {
            return null;
        }
    }

    public static String getOsClassLoader() {
        try {
            Class.forName("dalvik.system.LexClassLoader");
            return "lex";
        } catch (ClassNotFoundException e) {
            Object obj = 1;
            try {
                Class.forName("dalvik.system.BaseDexClassLoader");
            } catch (ClassNotFoundException e2) {
                obj = null;
            }
            if (obj == null) {
                return "below14";
            }
            return "equalAndAbove14";
        }
    }
}
