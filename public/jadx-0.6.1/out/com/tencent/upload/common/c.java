package com.tencent.upload.common;

import com.tencent.upload.log.b;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;

public class c {
    static {
        c.class.getSimpleName();
    }

    public static String a(File file) {
        FileInputStream fileInputStream;
        Throwable e;
        Throwable th;
        String str = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                MessageDigest instance = MessageDigest.getInstance("SHA-1");
                byte[] bArr = new byte[65536];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    instance.update(bArr, 0, read);
                }
                str = i.a(instance.digest());
                try {
                    fileInputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    b.c("FileUtils", "getFileSha1->NoSuchAlgorithmException###", e);
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    return str;
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
            } catch (OutOfMemoryError e4) {
                e = e4;
                b.c("FileUtils", "getFileSha1->OutOfMemoryError###", e);
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e2222) {
                        e2222.printStackTrace();
                    }
                }
                return str;
            }
        } catch (Exception e5) {
            e = e5;
            Object obj = str;
            b.c("FileUtils", "getFileSha1->NoSuchAlgorithmException###", e);
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return str;
        } catch (OutOfMemoryError e6) {
            e = e6;
            fileInputStream = str;
            b.c("FileUtils", "getFileSha1->OutOfMemoryError###", e);
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return str;
        } catch (Throwable e7) {
            fileInputStream = str;
            th = e7;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
        return str;
    }

    public static String a(byte[] bArr) {
        String str = null;
        if (bArr != null && bArr.length > 0) {
            try {
                MessageDigest instance = MessageDigest.getInstance("SHA-1");
                instance.update(bArr, 0, bArr.length);
                str = i.a(instance.digest());
            } catch (Throwable e) {
                b.c("FileUtils", "getFileSha1->NoSuchAlgorithmException###", e);
            } catch (Throwable e2) {
                b.c("FileUtils", "getFileSha1->OutOfMemoryError###", e2);
            }
        }
        return str;
    }
}
