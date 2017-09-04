package com.tencent.mid.util;

import java.io.File;

class n {
    private static int a = -1;

    public static boolean a() {
        if (a == 1) {
            return true;
        }
        if (a == 0) {
            return false;
        }
        String[] strArr = new String[]{"/bin", "/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        int i = 0;
        while (i < strArr.length) {
            try {
                File file = new File(strArr[i] + "su");
                if (file == null || !file.exists()) {
                    i++;
                } else {
                    a = 1;
                    return true;
                }
            } catch (Exception e) {
            }
        }
        a = 0;
        return false;
    }
}
