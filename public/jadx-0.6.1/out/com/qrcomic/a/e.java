package com.qrcomic.a;

import android.text.TextUtils;

/* compiled from: QRComicConst */
public class e {
    public static String a;
    public static String b;

    public static synchronized String a() {
        String b;
        synchronized (e.class) {
            b = b();
        }
        return b;
    }

    public static synchronized String a(String str) {
        String str2;
        synchronized (e.class) {
            if (TextUtils.isEmpty(b) || !b.contains(str)) {
                b = "vip_comic_file_" + str;
            }
            str2 = b;
        }
        return str2;
    }

    public static synchronized String b() {
        String str;
        synchronized (e.class) {
            if (TextUtils.isEmpty(a)) {
                a = "vip_comic_file_GLOBAL";
            }
            str = a;
        }
        return str;
    }
}
