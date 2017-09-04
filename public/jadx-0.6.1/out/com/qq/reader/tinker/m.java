package com.qq.reader.tinker;

import android.os.Environment;
import android.os.StatFs;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/* compiled from: Utils */
public class m {
    private static boolean a = false;

    public static boolean a() {
        return false;
    }

    public static boolean b() {
        return a;
    }

    public static int a(long j, int i) {
        if (a()) {
            return -5;
        }
        if (i < 45) {
            return -7;
        }
        if (a(j)) {
            return 0;
        }
        return -6;
    }

    public static boolean a(Throwable th) {
        for (StackTraceElement className : th.getStackTrace()) {
            String className2 = className.getClassName();
            if (className2 != null && className2.contains("de.robv.android.xposed.XposedBridge")) {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public static boolean a(long j) {
        long blockSize;
        long availableBlocks;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            availableBlocks = ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
            try {
                blockSize = ((long) statFs.getBlockSize()) * ((long) statFs.getBlockCount());
            } catch (Exception e) {
                blockSize = 0;
                if (blockSize != 0) {
                }
                return false;
            }
        } catch (Exception e2) {
            availableBlocks = 0;
            blockSize = 0;
            if (blockSize != 0) {
            }
            return false;
        }
        if (blockSize != 0 || r0 <= j) {
            return false;
        }
        return true;
    }

    public static String b(Throwable th) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        while (th.getCause() != null) {
            try {
                th = th.getCause();
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        th.printStackTrace(printStream);
        String a = a(byteArrayOutputStream.toString());
        return a;
    }

    private static String a(String str) {
        if (str == null) {
            return null;
        }
        char[] toCharArray = str.toCharArray();
        if (toCharArray == null) {
            return null;
        }
        char c;
        int i = 0;
        while (i < toCharArray.length) {
            if (toCharArray[i] > '') {
                toCharArray[i] = '\u0000';
                c = '\u0001';
                break;
            }
            i++;
        }
        c = '\u0000';
        if (c != '\u0000') {
            return new String(toCharArray, 0, i);
        }
        return str;
    }
}
