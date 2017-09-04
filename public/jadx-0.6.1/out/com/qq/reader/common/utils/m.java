package com.qq.reader.common.utils;

import android.content.Context;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.c;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.page.impl.f;
import com.qq.reader.module.bookstore.qnative.page.impl.k;
import com.qq.reader.module.bookstore.qnative.page.impl.n;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* compiled from: DowngradeServiceWatcher */
public class m {
    public static long a = 0;
    public static long b = 0;

    static {
        a();
    }

    public static boolean a(Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis < a || currentTimeMillis >= b) {
            return false;
        }
        return true;
    }

    public static void a(Context context, long j, long j2) {
        c.a(context, j);
        c.b(context, j2);
        a();
    }

    public static void a() {
        a = c.c(ReaderApplication.getApplicationImp());
        if (a == 0) {
            try {
                a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2017-1-27 18:30:00").getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        b = c.f(ReaderApplication.getApplicationImp());
        if (b == 0) {
            try {
                b = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2017-1-28 00:00:00").getTime();
            } catch (ParseException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static boolean a(b bVar) {
        if (!a(ReaderApplication.getApplicationImp())) {
            return false;
        }
        if ((bVar instanceof f) || (bVar instanceof com.qq.reader.module.bookstore.qnative.page.impl.b) || (bVar instanceof n) || (bVar instanceof k)) {
            return true;
        }
        return false;
    }
}
