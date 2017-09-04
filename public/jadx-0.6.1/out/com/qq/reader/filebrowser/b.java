package com.qq.reader.filebrowser;

import com.qq.reader.common.monitor.f;

/* compiled from: PingyinOfChinese */
public final class b {
    private static final int[] a = new int[]{1601, 1637, 1833, 2078, 2274, 2302, 2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730, 3858, 4027, 4086, 4390, 4558, 4684, 4925, 5249, 5590};
    private static final String[] b = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "w", "x", "y", "z"};

    public static String a(String str) {
        if (str == null || str.trim().length() == 0) {
            return "";
        }
        String str2 = "";
        for (int i = 0; i < str.length(); i++) {
            str2 = str2 + b(str.substring(i, i + 1));
        }
        return str2;
    }

    public static String b(String str) {
        int i = 0;
        if (str == null || str.trim().length() == 0) {
            return "";
        }
        String a = a(str, "GBK", "ISO8859-1");
        if (a.length() <= 1) {
            return a;
        }
        int charAt = ((a.charAt(0) - 160) * 100) + (a.charAt(1) - 160);
        if (charAt <= 1600 || charAt >= 5590) {
            return a(a, "ISO8859-1", "GBK").substring(0, 1);
        }
        while (i < 23) {
            if (charAt >= a[i] && charAt < a[i + 1]) {
                return b[i];
            }
            i++;
        }
        return a;
    }

    private static String a(String str, String str2, String str3) {
        try {
            return new String(str.getBytes(str2), str3);
        } catch (Exception e) {
            f.a("conversionStr", "encoding string error", e);
            return str;
        }
    }
}
