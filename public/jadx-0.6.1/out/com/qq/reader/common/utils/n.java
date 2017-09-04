package com.qq.reader.common.utils;

import android.content.Context;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.c;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.c.a;
import com.qq.reader.common.readertask.g;
import java.io.File;
import java.util.List;

/* compiled from: FixedHistoryVersions */
public class n {
    public static void a() {
        Context applicationContext = ReaderApplication.getApplicationImp().getApplicationContext();
        int a = c.a(applicationContext);
        if (a != ao.v(applicationContext)) {
            if (!ReaderApplication.isFirstInstall && a < 60) {
                a(applicationContext);
            }
            if (a <= 61) {
                a.M = true;
                a.P = true;
            }
            if (a <= 68) {
                g.a().a(new FixedHistoryVersions$1());
            }
            if (a <= 91) {
                com.qq.reader.common.login.c.a(applicationContext);
            }
            if (a <= 98) {
                d.l(ReaderApplication.getApplicationImp(), true);
            }
            b();
            d.h(applicationContext.getApplicationContext(), true);
            c.d(applicationContext, 0);
            if (a > 0) {
                a.U = true;
                a.T = false;
            } else {
                d.P(applicationContext, false);
                a.U = false;
                a.T = true;
            }
            a.N = true;
            a.K = true;
            c.h(applicationContext);
            c.b(applicationContext);
            return;
        }
        a.U = false;
    }

    private static void a(Context context) {
        try {
            if (Float.valueOf(c.g(context).substring(9, 12)).floatValue() == 4.7f) {
                return;
            }
        } catch (Exception e) {
        }
        if (d.l(context, "READING_STYLE")) {
            int L = d.L(context.getApplicationContext());
            switch (L) {
                case 0:
                    L = 2;
                    break;
                case 1:
                    L = 4;
                    break;
                case 2:
                    L = 1;
                    break;
                case 3:
                    L = 3;
                    break;
                case 4:
                    L = 6;
                    break;
                case 5:
                    L = 0;
                    break;
                case 6:
                    L = 5;
                    break;
            }
            d.j(context, L);
        }
    }

    public static void b() {
        if (c.a(ReaderApplication.getApplicationImp()) <= 91 && !d.ck(ReaderApplication.getApplicationImp())) {
            List<File> b = ao.b();
            if (d.ck(ReaderApplication.getApplicationImp())) {
                com.qq.reader.appconfig.a.a.a().b();
                com.qq.reader.appconfig.a.a.a().d();
                for (File file : b) {
                    if (file != null && file.exists()) {
                        com.qq.reader.common.monitor.debug.c.a("ACCOUNT", "delete file " + file.getAbsolutePath() + "  " + file.delete());
                    }
                }
            }
        }
    }
}
