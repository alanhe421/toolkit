package com.tencent.upload.a.a;

import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.text.TextUtils;
import com.tencent.upload.a.b;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class a {
    private static final char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static class a extends RuntimeException {
        public a(String str, Throwable th) {
            super(str, th);
        }
    }

    private static File a() {
        File e = b.e();
        if (e == null) {
            e = b.f();
        }
        return new File((e != null ? e.getParent() : "/data/data/" + b.d()) + File.separator + "qzlib");
    }

    private static String a(InputStream inputStream, String str) {
        String str2;
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                instance.update(bArr, 0, read);
            }
            byte[] digest = instance.digest();
            if (digest == null || digest.length == 0) {
                str2 = null;
            } else {
                char[] cArr = new char[(digest.length * 2)];
                for (int i = 0; i < digest.length; i++) {
                    byte b = digest[i];
                    cArr[(i * 2) + 1] = a[b & 15];
                    cArr[i * 2] = a[((byte) (b >>> 4)) & 15];
                }
                str2 = new String(cArr);
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    return str2;
                }
            }
            return str2;
        } catch (IOException e2) {
            if (inputStream == null) {
                return null;
            }
            try {
                inputStream.close();
                return null;
            } catch (Exception e3) {
                return null;
            }
        } catch (NoSuchAlgorithmException e4) {
            if (inputStream == null) {
                return null;
            }
            try {
                inputStream.close();
                return null;
            } catch (Exception e5) {
                return null;
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e6) {
                }
            }
        }
    }

    private static String a(String str, String str2) {
        return "check_" + str + "_" + str2;
    }

    private static void a(File file) {
        a(file, false);
    }

    private static void a(File file, boolean z) {
        if (file != null && file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File a : listFiles) {
                    a(a, z);
                }
                if (!z) {
                    file.delete();
                }
            }
        }
    }

    private static void a(String str, String... strArr) {
        if (strArr != null) {
            int length = strArr.length;
            int i = 0;
            while (i < length) {
                String str2 = strArr[i];
                try {
                    a(str2, b(str2), a().getAbsolutePath());
                    SharedPreferences a = b.a("guarder", 0);
                    a.edit().putBoolean(a(str, str2), true).commit();
                    i++;
                } catch (Throwable th) {
                    a aVar = new a("copy file:" + str2 + " failed!", th);
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.lang.String r7) {
        /*
        r0 = 1;
        r1 = 0;
        r2 = new java.lang.StringBuilder;
        r3 = "lib";
        r2.<init>(r3);
        r2 = r2.append(r7);
        r3 = ".so";
        r2 = r2.append(r3);
        r3 = r2.toString();
        r4 = new java.io.File;
        r2 = a();
        r4.<init>(r2, r3);
        java.lang.System.loadLibrary(r7);	 Catch:{ UnsatisfiedLinkError -> 0x0026, Exception -> 0x00a8, Error -> 0x00ab }
    L_0x0025:
        return r0;
    L_0x0026:
        r2 = move-exception;
    L_0x0027:
        r2 = r4.exists();
        if (r2 == 0) goto L_0x0042;
    L_0x002d:
        r2 = b();
        r5 = "guarder";
        r5 = com.tencent.upload.a.b.a(r5, r1);
        r2 = a(r2, r3);
        r2 = r5.getBoolean(r2, r1);
        if (r2 != 0) goto L_0x0057;
    L_0x0042:
        r2 = c(r3);
        if (r2 != 0) goto L_0x004a;
    L_0x0048:
        r0 = r1;
        goto L_0x0025;
    L_0x004a:
        r2 = b();	 Catch:{ a -> 0x00bc, Exception -> 0x00ba }
        r5 = 1;
        r5 = new java.lang.String[r5];	 Catch:{ a -> 0x00bc, Exception -> 0x00ba }
        r6 = 0;
        r5[r6] = r3;	 Catch:{ a -> 0x00bc, Exception -> 0x00ba }
        a(r2, r5);	 Catch:{ a -> 0x00bc, Exception -> 0x00ba }
    L_0x0057:
        r2 = r4.exists();
        if (r2 == 0) goto L_0x0066;
    L_0x005d:
        r2 = r4.getAbsolutePath();	 Catch:{ UnsatisfiedLinkError -> 0x0065, Exception -> 0x00b8, Error -> 0x00b6 }
        java.lang.System.load(r2);	 Catch:{ UnsatisfiedLinkError -> 0x0065, Exception -> 0x00b8, Error -> 0x00b6 }
        goto L_0x0025;
    L_0x0065:
        r2 = move-exception;
    L_0x0066:
        r2 = c(r3);
        if (r2 == 0) goto L_0x00be;
    L_0x006c:
        r2 = r4.exists();
        if (r2 != 0) goto L_0x0077;
    L_0x0072:
        r2 = r0;
    L_0x0073:
        if (r2 != 0) goto L_0x0087;
    L_0x0075:
        r0 = r1;
        goto L_0x0025;
    L_0x0077:
        r2 = e(r3);
        if (r2 != 0) goto L_0x007f;
    L_0x007d:
        r2 = r0;
        goto L_0x0073;
    L_0x007f:
        r2 = d(r3);
        if (r2 != 0) goto L_0x00be;
    L_0x0085:
        r2 = r0;
        goto L_0x0073;
    L_0x0087:
        r2 = b();	 Catch:{ a -> 0x00b4, Exception -> 0x00b2 }
        r5 = 1;
        r5 = new java.lang.String[r5];	 Catch:{ a -> 0x00b4, Exception -> 0x00b2 }
        r6 = 0;
        r5[r6] = r3;	 Catch:{ a -> 0x00b4, Exception -> 0x00b2 }
        a(r2, r5);	 Catch:{ a -> 0x00b4, Exception -> 0x00b2 }
    L_0x0094:
        r2 = r4.exists();
        if (r2 != 0) goto L_0x009c;
    L_0x009a:
        r0 = r1;
        goto L_0x0025;
    L_0x009c:
        r2 = r4.getAbsolutePath();	 Catch:{ UnsatisfiedLinkError -> 0x00a4, Exception -> 0x00b0, Error -> 0x00ae }
        java.lang.System.load(r2);	 Catch:{ UnsatisfiedLinkError -> 0x00a4, Exception -> 0x00b0, Error -> 0x00ae }
        goto L_0x0025;
    L_0x00a4:
        r0 = move-exception;
    L_0x00a5:
        r0 = r1;
        goto L_0x0025;
    L_0x00a8:
        r2 = move-exception;
        goto L_0x0027;
    L_0x00ab:
        r2 = move-exception;
        goto L_0x0027;
    L_0x00ae:
        r0 = move-exception;
        goto L_0x00a5;
    L_0x00b0:
        r0 = move-exception;
        goto L_0x00a5;
    L_0x00b2:
        r2 = move-exception;
        goto L_0x0094;
    L_0x00b4:
        r2 = move-exception;
        goto L_0x0094;
    L_0x00b6:
        r2 = move-exception;
        goto L_0x0066;
    L_0x00b8:
        r2 = move-exception;
        goto L_0x0066;
    L_0x00ba:
        r2 = move-exception;
        goto L_0x0057;
    L_0x00bc:
        r2 = move-exception;
        goto L_0x0057;
    L_0x00be:
        r2 = r1;
        goto L_0x0073;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.upload.a.a.a.a(java.lang.String):boolean");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized boolean a(java.lang.String r7, java.lang.String r8, java.lang.String r9) {
        /*
        r0 = 0;
        r1 = 0;
        r5 = com.tencent.upload.a.a.a.class;
        monitor-enter(r5);
        r2 = com.tencent.upload.a.b.a();	 Catch:{ all -> 0x0070 }
        if (r2 == 0) goto L_0x000d;
    L_0x000b:
        if (r8 != 0) goto L_0x000f;
    L_0x000d:
        monitor-exit(r5);
        return r0;
    L_0x000f:
        if (r9 == 0) goto L_0x001b;
    L_0x0011:
        r0 = r9.trim();	 Catch:{ all -> 0x0070 }
        r0 = r0.length();	 Catch:{ all -> 0x0070 }
        if (r0 != 0) goto L_0x0023;
    L_0x001b:
        r0 = r2.getFilesDir();	 Catch:{ all -> 0x0070 }
        r9 = r0.getAbsolutePath();	 Catch:{ all -> 0x0070 }
    L_0x0023:
        r0 = new java.io.File;	 Catch:{ all -> 0x0070 }
        r0.<init>(r9);	 Catch:{ all -> 0x0070 }
        r0.mkdirs();	 Catch:{ all -> 0x0070 }
        r0 = r2.getAssets();	 Catch:{ Throwable -> 0x007f }
        r4 = r0.open(r8);	 Catch:{ Throwable -> 0x007f }
        r3 = new java.io.File;	 Catch:{ Throwable -> 0x0083 }
        r3.<init>(r9, r7);	 Catch:{ Throwable -> 0x0083 }
        r0 = r3.exists();	 Catch:{ Throwable -> 0x0087 }
        if (r0 == 0) goto L_0x0041;
    L_0x003e:
        a(r3);	 Catch:{ Throwable -> 0x0087 }
    L_0x0041:
        r3.createNewFile();	 Catch:{ Throwable -> 0x0087 }
        r2 = new java.io.FileOutputStream;	 Catch:{ Throwable -> 0x0087 }
        r2.<init>(r3);	 Catch:{ Throwable -> 0x0087 }
        r0 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r0 = new byte[r0];	 Catch:{ Throwable -> 0x005e }
    L_0x004d:
        r1 = r4.available();	 Catch:{ Throwable -> 0x005e }
        if (r1 <= 0) goto L_0x0073;
    L_0x0053:
        r1 = r4.read(r0);	 Catch:{ Throwable -> 0x005e }
        if (r1 <= 0) goto L_0x0073;
    L_0x0059:
        r6 = 0;
        r2.write(r0, r6, r1);	 Catch:{ Throwable -> 0x005e }
        goto L_0x004d;
    L_0x005e:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
        r3 = r4;
    L_0x0062:
        if (r1 == 0) goto L_0x0067;
    L_0x0064:
        r1.close();	 Catch:{ Exception -> 0x007b }
    L_0x0067:
        if (r3 == 0) goto L_0x006c;
    L_0x0069:
        r3.close();	 Catch:{ Exception -> 0x007d }
    L_0x006c:
        a(r2);	 Catch:{ all -> 0x0070 }
        throw r0;	 Catch:{ all -> 0x0070 }
    L_0x0070:
        r0 = move-exception;
        monitor-exit(r5);
        throw r0;
    L_0x0073:
        r2.close();	 Catch:{ Throwable -> 0x005e }
        r4.close();	 Catch:{ Throwable -> 0x005e }
        r0 = 1;
        goto L_0x000d;
    L_0x007b:
        r1 = move-exception;
        goto L_0x0067;
    L_0x007d:
        r1 = move-exception;
        goto L_0x006c;
    L_0x007f:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
        goto L_0x0062;
    L_0x0083:
        r0 = move-exception;
        r2 = r1;
        r3 = r4;
        goto L_0x0062;
    L_0x0087:
        r0 = move-exception;
        r2 = r3;
        r3 = r4;
        goto L_0x0062;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.upload.a.a.a.a(java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    private static String b() {
        try {
            return b.c().getPackageInfo(b.d(), 0).versionName;
        } catch (Exception e) {
            return String.valueOf(System.currentTimeMillis());
        }
    }

    private static String b(String str) {
        return "lib/armeabi" + File.separator + str;
    }

    private static boolean c(String str) {
        try {
            String[] list = b.a().getAssets().list("lib/armeabi");
            if (list == null) {
                return false;
            }
            for (String equalsIgnoreCase : list) {
                if (str.equalsIgnoreCase(equalsIgnoreCase)) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean d(String str) {
        boolean z = false;
        try {
            Object a = a(b.b().open(b(str)), "MD5");
            if (!TextUtils.isEmpty(a)) {
                z = a.equals(a(new FileInputStream(new File(a(), str)), "MD5"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return z;
    }

    private static boolean e(String str) {
        InputStream open;
        boolean z = false;
        String absolutePath = new File(a(), str).getAbsolutePath();
        File file = new File(absolutePath);
        AssetManager b = b.b();
        String b2 = b(str);
        try {
            AssetFileDescriptor openFd = b.openFd(b2);
            if (openFd == null) {
                return true;
            }
            absolutePath = file.length() == openFd.getLength() ? 1 : null;
            if (z) {
                try {
                    open = b.open(b2);
                    if (file.length() == ((long) open.available())) {
                        absolutePath = 1;
                    }
                    open.close();
                } catch (IOException e) {
                    open.close();
                } catch (Exception e2) {
                } catch (Throwable th) {
                    open.close();
                }
            }
            return absolutePath;
        } catch (FileNotFoundException e3) {
            return true;
        } catch (IOException e4) {
            absolutePath = null;
            z = true;
        } catch (Exception e5) {
            absolutePath = null;
        }
    }
}
