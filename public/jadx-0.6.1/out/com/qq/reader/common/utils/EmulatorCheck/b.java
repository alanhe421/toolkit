package com.qq.reader.common.utils.EmulatorCheck;

import android.text.TextUtils;
import com.qq.reader.ReaderApplication;

/* compiled from: EmulatorCheckUtil */
public class b {
    public static boolean a() {
        try {
            if (b() >= 2) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            return false;
        }
    }

    private static int b() {
        String cpuinfo = JniAnti.getCpuinfo();
        String a = c.a(a.a());
        String kernelVersion = JniAnti.getKernelVersion();
        boolean a2 = a.a(ReaderApplication.getApplicationImp());
        CharSequence c = a.c(ReaderApplication.getApplicationImp());
        CharSequence d = a.d(ReaderApplication.getApplicationImp());
        int checkAntiFile = JniAnti.checkAntiFile();
        boolean b = a.b(ReaderApplication.getApplicationImp());
        int i = 0;
        if (cpuinfo.contains("Genuine Intel(R)") || cpuinfo.contains("Intel(R) Core(TM)") || cpuinfo.contains("Intel(R) Pentium(R)") || cpuinfo.contains("Intel(R) Xeon(R)") || cpuinfo.contains("AMD")) {
            i = 1;
        }
        if (kernelVersion.contains("qemu+") || kernelVersion.contains("tencent") || kernelVersion.contains("virtualbox")) {
            i++;
        }
        if (!a2) {
            i++;
        }
        if (TextUtils.isEmpty(c)) {
            i++;
        }
        if (TextUtils.isEmpty(d)) {
            i++;
        }
        if (checkAntiFile > 0) {
            i++;
        }
        if (!b) {
            i++;
        }
        if (a.equals("0M")) {
            return i + 1;
        }
        return i;
    }
}
