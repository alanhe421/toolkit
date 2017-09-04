package com.qq.reader.common.imageloader.a.a;

import java.io.File;

/* compiled from: DisCacheDispatch */
public class a {
    public static final int[][] a = new int[][]{new int[]{0, 2097152}, new int[]{3, 10485760}};
    public static final String b = (com.qq.reader.common.c.a.l + "default/");
    public static final String c = (com.qq.reader.common.c.a.l + "Adv/");
    public static final String d = (com.qq.reader.common.c.a.l + "cover/");
    public static final String e = (com.qq.reader.common.c.a.l + "news/");
    public static final String f = (com.qq.reader.common.c.a.l + "install/");
    public static final String g = (com.qq.reader.common.c.a.l + "gene/");

    public static File a(int i) {
        return new File(b(i));
    }

    private static String b(int i) {
        switch (i) {
            case 1:
                return d;
            case 3:
                return c;
            default:
                return b;
        }
    }

    public static String a(int i, String str, String str2) {
        return (str == null || str.length() <= 0) ? b(i) + str2 : str;
    }

    public static File b(int i, String str, String str2) {
        if (str != null && str.length() > 0) {
            return d(i, str);
        }
        if (str2 == null || str2.length() <= 0) {
            return null;
        }
        return e(i, str2);
    }

    private static String c(int i, String str) {
        return b(i) + com.qq.reader.common.imageloader.a.a.b.a.a().a(a(i, str, null));
    }

    private static File d(int i, String str) {
        return new File(c(i, str));
    }

    public static String a(int i, String str) {
        return b(i) + com.qq.reader.common.imageloader.a.a.b.a.a().a(a(i, null, str));
    }

    public static String b(int i, String str) {
        return "file://" + b(i) + com.qq.reader.common.imageloader.a.a.b.a.a().a(a(i, null, str));
    }

    private static File e(int i, String str) {
        return new File(a(i, str));
    }
}
