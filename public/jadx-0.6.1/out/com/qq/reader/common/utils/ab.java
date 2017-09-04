package com.qq.reader.common.utils;

import com.qq.reader.ReaderApplication;
import com.qq.reader.common.c.a;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.f;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.WeakHashMap;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.util.EncodingUtils;

/* compiled from: ReaderFileUtils */
public class ab {
    private static final char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final Map<String, Object> b = new WeakHashMap();
    private static final byte[] c = new byte[0];

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] a(java.lang.String r6) {
        /*
        r1 = 0;
        r2 = new java.io.BufferedInputStream;	 Catch:{ FileNotFoundException -> 0x0066, IOException -> 0x004a, all -> 0x0054 }
        r0 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x0066, IOException -> 0x004a, all -> 0x0054 }
        r0.<init>(r6);	 Catch:{ FileNotFoundException -> 0x0066, IOException -> 0x004a, all -> 0x0054 }
        r2.<init>(r0);	 Catch:{ FileNotFoundException -> 0x0066, IOException -> 0x004a, all -> 0x0054 }
        r0 = 10240; // 0x2800 float:1.4349E-41 double:5.059E-320;
        r3 = new byte[r0];	 Catch:{ FileNotFoundException -> 0x0021, IOException -> 0x0064, all -> 0x0062 }
        r0 = "SHA-1";
        r0 = java.security.MessageDigest.getInstance(r0);	 Catch:{ NoSuchAlgorithmException -> 0x0029 }
    L_0x0016:
        r4 = r2.read(r3);	 Catch:{ FileNotFoundException -> 0x0021, IOException -> 0x0064, all -> 0x0062 }
        if (r4 <= 0) goto L_0x002f;
    L_0x001c:
        r5 = 0;
        r0.update(r3, r5, r4);	 Catch:{ FileNotFoundException -> 0x0021, IOException -> 0x0064, all -> 0x0062 }
        goto L_0x0016;
    L_0x0021:
        r0 = move-exception;
        r0 = r2;
    L_0x0023:
        if (r0 == 0) goto L_0x0028;
    L_0x0025:
        r0.close();	 Catch:{ IOException -> 0x005e }
    L_0x0028:
        return r1;
    L_0x0029:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ FileNotFoundException -> 0x0021, IOException -> 0x0064, all -> 0x0062 }
        r0 = r1;
        goto L_0x0016;
    L_0x002f:
        if (r2 == 0) goto L_0x0034;
    L_0x0031:
        r2.close();	 Catch:{ IOException -> 0x005c, FileNotFoundException -> 0x0021, all -> 0x0062 }
    L_0x0034:
        if (r0 == 0) goto L_0x0042;
    L_0x0036:
        r1 = r0.digest();	 Catch:{ FileNotFoundException -> 0x0021, IOException -> 0x0064, all -> 0x0062 }
        if (r2 == 0) goto L_0x0028;
    L_0x003c:
        r2.close();	 Catch:{ IOException -> 0x0040 }
        goto L_0x0028;
    L_0x0040:
        r0 = move-exception;
        goto L_0x0028;
    L_0x0042:
        if (r2 == 0) goto L_0x0028;
    L_0x0044:
        r2.close();	 Catch:{ IOException -> 0x0048 }
        goto L_0x0028;
    L_0x0048:
        r0 = move-exception;
        goto L_0x0028;
    L_0x004a:
        r0 = move-exception;
        r2 = r1;
    L_0x004c:
        if (r2 == 0) goto L_0x0028;
    L_0x004e:
        r2.close();	 Catch:{ IOException -> 0x0052 }
        goto L_0x0028;
    L_0x0052:
        r0 = move-exception;
        goto L_0x0028;
    L_0x0054:
        r0 = move-exception;
        r2 = r1;
    L_0x0056:
        if (r2 == 0) goto L_0x005b;
    L_0x0058:
        r2.close();	 Catch:{ IOException -> 0x0060 }
    L_0x005b:
        throw r0;
    L_0x005c:
        r3 = move-exception;
        goto L_0x0034;
    L_0x005e:
        r0 = move-exception;
        goto L_0x0028;
    L_0x0060:
        r1 = move-exception;
        goto L_0x005b;
    L_0x0062:
        r0 = move-exception;
        goto L_0x0056;
    L_0x0064:
        r0 = move-exception;
        goto L_0x004c;
    L_0x0066:
        r0 = move-exception;
        r0 = r1;
        goto L_0x0023;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.utils.ab.a(java.lang.String):byte[]");
    }

    public static long b(String str) {
        FileInputStream fileInputStream;
        Throwable th;
        long j = 0;
        String str2 = "";
        FileInputStream fileInputStream2 = null;
        try {
            File file = new File(str);
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                try {
                    j = (long) fileInputStream.available();
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e) {
                        }
                    }
                } catch (Exception e2) {
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e3) {
                        }
                    }
                    return j;
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream2 = fileInputStream;
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (IOException e4) {
                        }
                    }
                    throw th;
                }
            } else if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (IOException e5) {
                }
            }
        } catch (Exception e6) {
            fileInputStream = fileInputStream2;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return j;
        } catch (Throwable th3) {
            th = th3;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            throw th;
        }
        return j;
    }

    public static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            cArr[(i * 2) + 1] = a[b & 15];
            cArr[(i * 2) + 0] = a[((byte) (b >>> 4)) & 15];
        }
        return new String(cArr);
    }

    public static byte[] c(String str) {
        int i = 0;
        if (str == null || str.equals("")) {
            return new byte[0];
        }
        byte[] bArr = new byte[(str.length() / 2)];
        while (i < bArr.length) {
            bArr[i] = (byte) ((a(str.charAt(i * 2)) * 16) + a(str.charAt((i * 2) + 1)));
            i++;
        }
        return bArr;
    }

    public static byte a(char c) {
        if (c >= '0' && c <= '9') {
            return (byte) (c - 48);
        }
        if (c >= 'a' && c <= 'f') {
            return (byte) ((c - 97) + 10);
        }
        if (c < 'A' || c > 'F') {
            return (byte) 0;
        }
        return (byte) ((c - 65) + 10);
    }

    public static void a() {
        try {
            File file = new File(a.o);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            c.e("nomedia", "Can't create \".nomedia\" file in application external directory");
        }
    }

    public static boolean a(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return true;
        }
        boolean mkdirs;
        synchronized (ab.class) {
            mkdirs = file.mkdirs();
        }
        return mkdirs;
    }

    public static boolean b(File file) {
        boolean mkdirs;
        synchronized (ab.class) {
            mkdirs = file.mkdirs();
        }
        return mkdirs;
    }

    public static boolean c(File file) {
        boolean z = false;
        if (!a(file.getParentFile())) {
            return z;
        }
        if (file.exists()) {
            return true;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return z;
        }
    }

    public static void a(String str, Serializable serializable) {
        ObjectOutputStream objectOutputStream;
        FileNotFoundException e;
        Throwable th;
        File file = new File(str);
        ObjectOutputStream objectOutputStream2 = null;
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e2) {
                IOException e22;
                e22.printStackTrace();
            }
        }
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(str));
            try {
                objectOutputStream.writeObject(serializable);
                objectOutputStream.flush();
                objectOutputStream.close();
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (IOException e222) {
                        e222.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException e2222) {
                            e2222.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    objectOutputStream2 = objectOutputStream;
                    if (objectOutputStream2 != null) {
                        try {
                            objectOutputStream2.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e5) {
                e2222 = e5;
                objectOutputStream2 = objectOutputStream;
                try {
                    e2222.printStackTrace();
                    if (objectOutputStream2 != null) {
                        try {
                            objectOutputStream2.close();
                        } catch (IOException e22222) {
                            e22222.printStackTrace();
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (objectOutputStream2 != null) {
                        objectOutputStream2.close();
                    }
                    throw th;
                }
            }
        } catch (FileNotFoundException e6) {
            e = e6;
            objectOutputStream = null;
            e.printStackTrace();
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
        } catch (IOException e7) {
            e22222 = e7;
            e22222.printStackTrace();
            if (objectOutputStream2 != null) {
                objectOutputStream2.close();
            }
        }
    }

    public static Object d(String str) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(str));
            Object readObject = objectInputStream.readObject();
            objectInputStream.close();
            f.a("SignupManager", "now reading disk cache data ok");
            return readObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static long d(File file) {
        long j = 0;
        if (!file.exists()) {
            return 0;
        }
        if (!file.isDirectory()) {
            return file.length();
        }
        File[] listFiles = file.listFiles();
        int i = 0;
        while (i < listFiles.length) {
            long d = d(listFiles[i]) + j;
            i++;
            j = d;
        }
        return j;
    }

    public static long a(File file, boolean z) {
        long j = 0;
        if (!file.exists()) {
            return 0;
        }
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            int i = 0;
            while (i < listFiles.length) {
                long a = a(listFiles[i], true) + j;
                i++;
                j = a;
            }
            if (!z) {
                return j;
            }
            file.delete();
            return j;
        }
        long length = file.length();
        if (file.delete()) {
            return 0 + length;
        }
        return 0;
    }

    public static String a(long j) {
        StringBuilder stringBuilder = new StringBuilder();
        if (j > 0 && j < 1024) {
            j = 1024;
        }
        long j2 = j / 1024;
        if (j2 > 1024) {
            stringBuilder.append(j2 / 1024);
            long j3 = (long) (((((double) j2) + 51.2d) % 1024.0d) / 102.4d);
            if (j3 != 0) {
                stringBuilder.append(".");
                stringBuilder.append(j3);
            }
            stringBuilder.append("MB");
        } else {
            stringBuilder.append(j2).append("KB");
        }
        return stringBuilder.toString();
    }

    public static String e(File file) throws IOException {
        Exception e;
        if (file == null || !file.exists()) {
            return null;
        }
        String str = "";
        String string;
        try {
            FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            string = EncodingUtils.getString(bArr, "UTF-8");
            try {
                fileInputStream.close();
                return string;
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return string;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            string = str;
            e = exception;
            e.printStackTrace();
            return string;
        }
    }

    public static Object e(String str) throws Exception {
        ByteArrayInputStream byteArrayInputStream;
        ObjectInputStream objectInputStream;
        Throwable th;
        Object obj = null;
        byte[] decodeBase64 = Base64.decodeBase64(str.getBytes());
        if (!(decodeBase64 == null || decodeBase64.length == 0)) {
            try {
                byteArrayInputStream = new ByteArrayInputStream(decodeBase64);
                try {
                    objectInputStream = new ObjectInputStream(byteArrayInputStream);
                    try {
                        obj = objectInputStream.readObject();
                        if (objectInputStream != null) {
                            objectInputStream.close();
                        }
                        if (byteArrayInputStream != null) {
                            byteArrayInputStream.close();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (objectInputStream != null) {
                            objectInputStream.close();
                        }
                        if (byteArrayInputStream != null) {
                            byteArrayInputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    objectInputStream = null;
                    th = th4;
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    if (byteArrayInputStream != null) {
                        byteArrayInputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th32) {
                byteArrayInputStream = null;
                th = th32;
                objectInputStream = null;
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (byteArrayInputStream != null) {
                    byteArrayInputStream.close();
                }
                throw th;
            }
        }
        return obj;
    }

    public static String f(String str) {
        InputStream inputStream = null;
        if (str == null || str.equals("")) {
            return "";
        }
        String str2 = "";
        try {
            inputStream = ReaderApplication.getApplicationImp().getAssets().open(str);
            str2 = a(inputStream, null);
            if (inputStream == null) {
                return str2;
            }
            try {
                inputStream.close();
                return str2;
            } catch (IOException e) {
                e.printStackTrace();
                return str2;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            if (inputStream == null) {
                return str2;
            }
            try {
                inputStream.close();
                return str2;
            } catch (IOException e3) {
                e3.printStackTrace();
                return str2;
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

    public static byte[] f(File file) {
        Exception e;
        byte[] bArr;
        FileInputStream fileInputStream;
        Throwable th;
        OutOfMemoryError e2;
        OutOfMemoryError outOfMemoryError;
        byte[] bArr2 = null;
        if (file != null && file.exists()) {
            synchronized (g(file.getAbsolutePath())) {
                byte[] bArr3;
                FileInputStream fileInputStream2;
                try {
                    fileInputStream2 = new FileInputStream(file);
                    try {
                        bArr3 = new byte[((int) file.length())];
                        try {
                            fileInputStream2.read(bArr3);
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                    bArr2 = bArr3;
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                    bArr2 = bArr3;
                                }
                            }
                        } catch (Exception e4) {
                            e = e4;
                            FileInputStream fileInputStream3 = fileInputStream2;
                            bArr = bArr3;
                            fileInputStream = fileInputStream3;
                            try {
                                e.printStackTrace();
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                        bArr2 = bArr;
                                    } catch (IOException e32) {
                                        e32.printStackTrace();
                                        bArr2 = bArr;
                                    }
                                } else {
                                    bArr2 = bArr;
                                }
                                return bArr2;
                            } catch (Throwable th2) {
                                th = th2;
                                fileInputStream2 = fileInputStream;
                                if (fileInputStream2 != null) {
                                    try {
                                        fileInputStream2.close();
                                    } catch (IOException e5) {
                                        e5.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        } catch (OutOfMemoryError e6) {
                            e2 = e6;
                            try {
                                e2.printStackTrace();
                                if (fileInputStream2 != null) {
                                    try {
                                        fileInputStream2.close();
                                        bArr2 = bArr3;
                                    } catch (IOException e322) {
                                        e322.printStackTrace();
                                        bArr2 = bArr3;
                                    }
                                    return bArr2;
                                }
                                bArr2 = bArr3;
                                return bArr2;
                            } catch (Throwable th3) {
                                th = th3;
                                if (fileInputStream2 != null) {
                                    fileInputStream2.close();
                                }
                                throw th;
                            }
                        }
                    } catch (Exception e7) {
                        Exception exception = e7;
                        fileInputStream = fileInputStream2;
                        bArr = null;
                        e = exception;
                        e.printStackTrace();
                        if (fileInputStream != null) {
                            bArr2 = bArr;
                        } else {
                            fileInputStream.close();
                            bArr2 = bArr;
                        }
                        return bArr2;
                    } catch (OutOfMemoryError e8) {
                        outOfMemoryError = e8;
                        bArr3 = null;
                        e2 = outOfMemoryError;
                        e2.printStackTrace();
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                            bArr2 = bArr3;
                            return bArr2;
                        }
                        bArr2 = bArr3;
                        return bArr2;
                    }
                } catch (Exception e72) {
                    bArr = null;
                    e = e72;
                    fileInputStream = null;
                    e.printStackTrace();
                    if (fileInputStream != null) {
                        fileInputStream.close();
                        bArr2 = bArr;
                    } else {
                        bArr2 = bArr;
                    }
                    return bArr2;
                } catch (OutOfMemoryError e82) {
                    fileInputStream2 = null;
                    outOfMemoryError = e82;
                    bArr3 = null;
                    e2 = outOfMemoryError;
                    e2.printStackTrace();
                    if (fileInputStream2 != null) {
                        fileInputStream2.close();
                        bArr2 = bArr3;
                        return bArr2;
                    }
                    bArr2 = bArr3;
                    return bArr2;
                } catch (Throwable th4) {
                    fileInputStream2 = null;
                    th = th4;
                    if (fileInputStream2 != null) {
                        fileInputStream2.close();
                    }
                    throw th;
                }
                bArr2 = bArr3;
            }
        }
        return bArr2;
    }

    public static Object g(String str) {
        Object obj;
        synchronized (c) {
            if (str == null) {
                str = "";
            }
            obj = b.get(str);
            if (obj == null) {
                obj = new Object();
                b.put(str, obj);
            }
        }
        return obj;
    }

    public static String a(InputStream inputStream, String str) {
        ByteArrayOutputStream byteArrayOutputStream;
        Exception e;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        String str2 = "";
        BufferedInputStream bufferedInputStream;
        try {
            bufferedInputStream = new BufferedInputStream(inputStream);
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byte[] bArr = new byte[256];
                    while (true) {
                        int read = bufferedInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    if (str != null) {
                        if (str.length() > 0) {
                            str2 = new String(byteArrayOutputStream.toByteArray(), str);
                            if (byteArrayOutputStream != null) {
                                try {
                                    byteArrayOutputStream.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (IOException e22) {
                                    e22.printStackTrace();
                                }
                            }
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e222) {
                                    e222.printStackTrace();
                                }
                            }
                            return str2;
                        }
                    }
                    str2 = new String(byteArrayOutputStream.toByteArray());
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (Exception e3) {
                    e = e3;
                    byteArrayOutputStream2 = byteArrayOutputStream;
                    try {
                        e.printStackTrace();
                        if (byteArrayOutputStream2 != null) {
                            try {
                                byteArrayOutputStream2.close();
                            } catch (IOException e2222) {
                                e2222.printStackTrace();
                            }
                        }
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e22222) {
                                e22222.printStackTrace();
                            }
                        }
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e222222) {
                                e222222.printStackTrace();
                            }
                        }
                        return str2;
                    } catch (Throwable th2) {
                        th = th2;
                        byteArrayOutputStream = byteArrayOutputStream2;
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (IOException e2222222) {
                                e2222222.printStackTrace();
                            }
                        }
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e22222222) {
                                e22222222.printStackTrace();
                            }
                        }
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e222222222) {
                                e222222222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    throw th;
                }
            } catch (Exception e4) {
                e = e4;
                e.printStackTrace();
                if (byteArrayOutputStream2 != null) {
                    byteArrayOutputStream2.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                return str2;
            } catch (Throwable th4) {
                th = th4;
                byteArrayOutputStream = null;
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        } catch (Exception e5) {
            e = e5;
            bufferedInputStream = null;
            e.printStackTrace();
            if (byteArrayOutputStream2 != null) {
                byteArrayOutputStream2.close();
            }
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            return str2;
        } catch (Throwable th5) {
            th = th5;
            bufferedInputStream = null;
            byteArrayOutputStream = null;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
        return str2;
    }

    public static String a(Object obj) {
        ObjectOutputStream objectOutputStream;
        String str;
        IOException e;
        Throwable th;
        OutOfMemoryError e2;
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            try {
                objectOutputStream.writeObject(obj);
                str = new String(Base64.encodeBase64(byteArrayOutputStream.toByteArray()));
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (IOException e3) {
                        c.e("FileUtils", e3.toString());
                    }
                }
            } catch (IOException e4) {
                e = e4;
                try {
                    c.e("FileUtils", e.toString());
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                            str = null;
                        } catch (IOException e5) {
                            c.e("FileUtils", e5.toString());
                            str = null;
                        }
                        if (str == null) {
                            return str;
                        }
                        return "";
                    }
                    str = null;
                    if (str == null) {
                        return "";
                    }
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException e32) {
                            c.e("FileUtils", e32.toString());
                        }
                    }
                    throw th;
                }
            } catch (OutOfMemoryError e6) {
                e2 = e6;
                c.e("FileUtils", e2.toString());
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                        str = null;
                    } catch (IOException e52) {
                        c.e("FileUtils", e52.toString());
                        str = null;
                    }
                    if (str == null) {
                        return str;
                    }
                    return "";
                }
                str = null;
                if (str == null) {
                    return "";
                }
                return str;
            }
        } catch (IOException e7) {
            e52 = e7;
            objectOutputStream = null;
            c.e("FileUtils", e52.toString());
            if (objectOutputStream != null) {
                objectOutputStream.close();
                str = null;
                if (str == null) {
                    return str;
                }
                return "";
            }
            str = null;
            if (str == null) {
                return "";
            }
            return str;
        } catch (OutOfMemoryError e8) {
            e2 = e8;
            objectOutputStream = null;
            c.e("FileUtils", e2.toString());
            if (objectOutputStream != null) {
                objectOutputStream.close();
                str = null;
                if (str == null) {
                    return str;
                }
                return "";
            }
            str = null;
            if (str == null) {
                return "";
            }
            return str;
        } catch (Throwable th3) {
            th = th3;
            objectOutputStream = null;
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            throw th;
        }
        if (str == null) {
            return "";
        }
        return str;
    }
}
