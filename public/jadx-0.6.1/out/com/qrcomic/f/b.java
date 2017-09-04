package com.qrcomic.f;

import android.content.Context;
import com.qrcomic.a.f;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: FileUtils */
public class b {
    public static boolean a = true;
    public static Runnable b = new Runnable() {
        public void run() {
            b.a(com.qrcomic.manager.b.a().b().b(), true);
        }
    };
    public static String c = "unknown_";

    public static void a(File file, File file2) {
        FileOutputStream fileOutputStream;
        Throwable th;
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream2;
        FileInputStream fileInputStream2;
        try {
            if (file2.exists()) {
                file2.delete();
            }
            fileOutputStream2 = new FileOutputStream(file2);
            try {
                fileInputStream2 = new FileInputStream(file);
            } catch (IOException e) {
                fileOutputStream = fileOutputStream2;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e2) {
                    }
                }
                if (fileInputStream == null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                        return;
                    }
                }
            } catch (OutOfMemoryError e4) {
                fileInputStream2 = null;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (IOException e5) {
                    }
                }
                if (fileInputStream2 == null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e6) {
                        return;
                    }
                }
            } catch (Throwable th2) {
                Throwable th3 = th2;
                fileInputStream2 = null;
                th = th3;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (IOException e7) {
                    }
                }
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e8) {
                    }
                }
                throw th;
            }
            try {
                byte[] a = a.a().a(4096);
                while (true) {
                    int read = fileInputStream2.read(a);
                    if (read == -1) {
                        break;
                    }
                    fileOutputStream2.write(a, 0, read);
                    fileOutputStream2.flush();
                }
                a.a().a(a);
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (IOException e9) {
                    }
                }
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e10) {
                    }
                }
            } catch (IOException e11) {
                fileInputStream = fileInputStream2;
                fileOutputStream = fileOutputStream2;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (fileInputStream == null) {
                    fileInputStream.close();
                }
            } catch (OutOfMemoryError e12) {
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                if (fileInputStream2 == null) {
                    fileInputStream2.close();
                }
            } catch (Throwable th4) {
                th = th4;
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                throw th;
            }
        } catch (IOException e13) {
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (fileInputStream == null) {
                fileInputStream.close();
            }
        } catch (OutOfMemoryError e14) {
            fileInputStream2 = null;
            fileOutputStream2 = null;
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            if (fileInputStream2 == null) {
                fileInputStream2.close();
            }
        } catch (Throwable th22) {
            fileOutputStream2 = null;
            th = th22;
            fileInputStream2 = null;
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            throw th;
        }
    }

    public static boolean a(String str) {
        if (str == null) {
            return false;
        }
        File file = new File(str);
        if (file == null || !file.exists()) {
            return false;
        }
        return true;
    }

    public static boolean a(Context context) {
        return a(f.a(context));
    }

    public static boolean b(Context context) {
        return a(f.c(context));
    }

    public static boolean c(Context context) {
        return a(context, true);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(android.content.Context r5, boolean r6) {
        /*
        r0 = 1;
        r1 = 0;
        r2 = new java.io.File;
        r3 = com.qrcomic.a.f.c(r5);
        r2.<init>(r3);
        r3 = r2.exists();
        if (r3 == 0) goto L_0x0012;
    L_0x0011:
        return r0;
    L_0x0012:
        r0 = r2.mkdirs();	 Catch:{ Exception -> 0x0039, all -> 0x0051 }
        r2 = a;	 Catch:{ Exception -> 0x0039, all -> 0x006f }
        if (r2 != 0) goto L_0x001d;
    L_0x001a:
        r2 = 1;
        a = r2;	 Catch:{ Exception -> 0x0039, all -> 0x006f }
    L_0x001d:
        if (r0 != 0) goto L_0x0025;
    L_0x001f:
        r2 = a(r5);
        if (r2 != 0) goto L_0x0032;
    L_0x0025:
        if (r6 == 0) goto L_0x0011;
    L_0x0027:
        if (r0 != 0) goto L_0x002d;
    L_0x0029:
        r2 = a;
        if (r2 == 0) goto L_0x0011;
    L_0x002d:
        if (r0 != 0) goto L_0x0011;
    L_0x002f:
        a = r1;
        goto L_0x0011;
    L_0x0032:
        r2 = b(r5);
        if (r2 != 0) goto L_0x0025;
    L_0x0038:
        goto L_0x0025;
    L_0x0039:
        r0 = move-exception;
        r0 = a(r5);
        if (r0 != 0) goto L_0x004a;
    L_0x0040:
        if (r6 == 0) goto L_0x0074;
    L_0x0042:
        r0 = a;
        if (r0 == 0) goto L_0x0074;
    L_0x0046:
        a = r1;
        r0 = r1;
        goto L_0x0011;
    L_0x004a:
        r0 = b(r5);
        if (r0 != 0) goto L_0x0040;
    L_0x0050:
        goto L_0x0040;
    L_0x0051:
        r0 = move-exception;
        r2 = r1;
    L_0x0053:
        if (r2 != 0) goto L_0x005b;
    L_0x0055:
        r3 = a(r5);
        if (r3 != 0) goto L_0x0068;
    L_0x005b:
        if (r6 == 0) goto L_0x0067;
    L_0x005d:
        if (r2 != 0) goto L_0x0063;
    L_0x005f:
        r3 = a;
        if (r3 == 0) goto L_0x0067;
    L_0x0063:
        if (r2 != 0) goto L_0x0067;
    L_0x0065:
        a = r1;
    L_0x0067:
        throw r0;
    L_0x0068:
        r3 = b(r5);
        if (r3 != 0) goto L_0x005b;
    L_0x006e:
        goto L_0x005b;
    L_0x006f:
        r2 = move-exception;
        r4 = r2;
        r2 = r0;
        r0 = r4;
        goto L_0x0053;
    L_0x0074:
        r0 = r1;
        goto L_0x0011;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qrcomic.f.b.a(android.content.Context, boolean):boolean");
    }

    public static File b(String str) throws IOException {
        File file = new File(str);
        if (!file.exists()) {
            if (file.getParentFile() == null || file.getParentFile().exists()) {
                file.createNewFile();
            } else if (file.getParentFile().mkdirs()) {
                file.createNewFile();
            }
        }
        return file;
    }

    public static boolean a(String str, String str2) {
        File file = new File(str);
        if (!file.exists()) {
            return false;
        }
        try {
            a(file, b(str2));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void c(String str) {
        if (str != null && str.trim().length() != 0) {
            File file = new File(str);
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (int i = 0; i < listFiles.length; i++) {
                    if (listFiles[i].isDirectory()) {
                        c(listFiles[i].getAbsolutePath());
                    } else {
                        listFiles[i].delete();
                    }
                }
            }
            file.delete();
        }
    }
}
