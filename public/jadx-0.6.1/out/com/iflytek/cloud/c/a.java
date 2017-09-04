package com.iflytek.cloud.c;

import java.util.Locale;

public class a {
    private static Locale a = Locale.CHINA;

    public static String a(int i) {
        String[] strArr = b.c;
        if (a.equals(Locale.US)) {
            strArr = c.c;
        } else if (a.equals(Locale.TRADITIONAL_CHINESE)) {
            strArr = d.c;
        }
        return (i <= 0 || i >= strArr.length) ? b(1) : strArr[i];
    }

    public static String b(int i) {
        String[] strArr = b.d;
        if (a.equals(Locale.US)) {
            strArr = c.d;
        } else if (a.equals(Locale.TRADITIONAL_CHINESE)) {
            strArr = d.d;
        }
        return (i < 0 || i >= strArr.length) ? "" : strArr[i];
    }
}
