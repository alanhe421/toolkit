package com.qq.reader.common.a;

import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import java.util.HashMap;

/* compiled from: ABTestHandle */
public class b {
    private static HashMap<String, Integer> a = new HashMap();

    public static int a() {
        int i;
        synchronized (b.class) {
            try {
                if (com.qq.reader.common.c.b.p != -1) {
                    i = com.qq.reader.common.c.b.p;
                } else {
                    if (a != null && a.isEmpty()) {
                        c();
                    }
                    Integer num = (Integer) a.get("rank_635");
                    if (num == null || num.intValue() == -1) {
                        i = b();
                    } else {
                        i = num.intValue();
                    }
                }
            } catch (Exception e) {
                i = 0;
            }
        }
        return i;
    }

    private static int b() {
        try {
            int abs;
            synchronized (b.class) {
                abs = Math.abs(d.i(ReaderApplication.getApplicationImp()).hashCode()) % 2;
            }
            return abs;
        } catch (Exception e) {
            return 0;
        }
    }

    public static void a(String str) {
        try {
            if (!TextUtils.isEmpty(str) && !d.bX(ReaderApplication.getApplicationImp()).equals(str)) {
                if ("null".equals(str)) {
                    d.N(ReaderApplication.getApplicationImp(), "");
                    a.clear();
                    return;
                }
                d.N(ReaderApplication.getApplicationImp(), str);
                b(str);
            }
        } catch (Exception e) {
            d.N(ReaderApplication.getApplicationImp(), "");
        }
    }

    private static void c() {
        b(d.bX(ReaderApplication.getApplicationImp()));
    }

    private static void b(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                for (String split : str.split("-")) {
                    String[] split2 = split.split(":");
                    a.put(split2[0], Integer.valueOf(split2[1]));
                }
            }
        } catch (Exception e) {
        }
    }
}
