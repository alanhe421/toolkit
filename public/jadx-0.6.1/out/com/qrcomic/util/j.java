package com.qrcomic.util;

import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;

/* compiled from: Utils */
public class j {
    public static final String[] a = new String[]{"水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座"};
    private static final char[] b = new char[]{'。', '？', '！', '!', '?', '，', '；', ',', ' '};
    private static StringBuilder c = new StringBuilder();
    private static int d = 0;

    public static String a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        return "comic-" + str + "section-" + str2;
    }

    public static boolean a() {
        if (Environment.getExternalStorageState().equals("mounted") && Environment.getExternalStorageDirectory().exists()) {
            return true;
        }
        return false;
    }

    public static long b() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        int blockSize = statFs.getBlockSize();
        return ((long) statFs.getAvailableBlocks()) * ((long) blockSize);
    }

    public static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length);
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() < 2) {
                stringBuffer.append(0);
            }
            stringBuffer.append(toHexString.toUpperCase());
        }
        return stringBuffer.toString();
    }

    public static <T> T a(T t) {
        if (t != null) {
            return t;
        }
        throw new RuntimeException("初始化参数不能为空");
    }
}
