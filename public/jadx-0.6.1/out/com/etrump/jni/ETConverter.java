package com.etrump.jni;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

public class ETConverter {
    public static final int ET_CONVERTER_CHECK_OUTLINE_FLAG = 256;
    public static final int ET_CONVERTER_GLYPH_CACHE_SIZE_MASK = 240;
    public static final int ET_CONVERTER_LOAD_FTF_FROM_MEMORY_FLAG = 2;
    public static final int ET_CONVERTER_LOAD_TTF_FROM_MEMORY_FLAG = 4;
    public static final int ET_CONVERTER_REUSE_TTF_GLYPH_FLAG = 1;
    private static ETConverter sInstance = null;

    public native boolean native_check_ttf(String str, String str2, String str3, int i);

    public native boolean native_check_ttf_ex(byte[] bArr, String str, String str2, int i);

    public native boolean native_ftf2ttf(String str, String str2, String str3, int i);

    public native boolean native_ftf2ttf_ex(byte[] bArr, String str, String str2, int i);

    public native boolean native_is_ftf(String str);

    public native boolean native_is_ftf_ex(byte[] bArr);

    public native boolean native_quick_check(String str, String str2);

    public native boolean native_quick_check_ex(byte[] bArr, String str);

    static {
        System.loadLibrary("etconverter");
    }

    public static ETConverter getInstance() {
        if (sInstance == null) {
            sInstance = new ETConverter();
        }
        return sInstance;
    }

    public static String getMd5ByFile(File file) {
        FileInputStream fileInputStream;
        Exception e;
        Throwable th;
        String str = "";
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            fileInputStream = new FileInputStream(file);
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    instance.update(bArr, 0, read);
                }
                str = new BigInteger(1, instance.digest()).toString(16);
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
                    str = "";
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
            }
        } catch (Exception e4) {
            e = e4;
            fileInputStream = null;
            e.printStackTrace();
            str = "";
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return str;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
        return str;
    }
}
