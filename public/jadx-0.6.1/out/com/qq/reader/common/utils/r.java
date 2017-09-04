package com.qq.reader.common.utils;

import android.os.Environment;
import android.os.StatFs;
import com.qq.reader.common.monitor.debug.c;

/* compiled from: MemoryStatus */
public class r {
    public static boolean a() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static long b() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
    }

    public static long c() {
        long j = -1;
        if (!a()) {
            return j;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Exception e) {
            c.e("MemoryStatus", e.getMessage());
            return j;
        }
    }

    public static boolean a(long j) {
        return j <= c();
    }

    public static boolean b(long j) {
        return j <= b();
    }

    public static boolean c(long j) {
        long j2 = 2097152 + j;
        if (a()) {
            return a(j2);
        }
        return b(j2);
    }
}
