package com.qq.reader.common.push;

import android.content.Context;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.readertask.g;
import com.xiaomi.channel.commonutils.b.a;

/* compiled from: XiaoMiPush */
public class b {
    public static String a = null;
    public static String b = null;
    public static String c = null;
    public static String d = null;
    public static XiaoMiShortTaskOfRegId e = new XiaoMiShortTaskOfRegId();
    public static XiaoMiShortTaskOfAlias f = new XiaoMiShortTaskOfAlias();
    public static XiaoMiShortTaskOfAccount g = new XiaoMiShortTaskOfAccount();
    public static XiaoMiShortTaskOfUnAccount h = new XiaoMiShortTaskOfUnAccount();
    public static XiaoMiShortTaskOfTopic i = new XiaoMiShortTaskOfTopic();

    public static void a() {
        g.a().a(e);
        com.xiaomi.mipush.sdk.b.a(ReaderApplication.getApplicationImp(), new a() {
            public void a(String str, Throwable th) {
                c.a("XiaoMiPush", str);
            }

            public void a(String str) {
                c.a("XiaoMiPush", str);
            }
        });
    }

    public static void b() {
        Context applicationImp = ReaderApplication.getApplicationImp();
        com.xiaomi.mipush.sdk.c.b(applicationImp, d.h(applicationImp), null);
    }

    public static void c() {
        Context applicationImp = ReaderApplication.getApplicationImp();
        if (!TextUtils.isEmpty(d.R(applicationImp))) {
            com.xiaomi.mipush.sdk.c.c(applicationImp, d.R(applicationImp), null);
        }
    }

    public static void d() {
        Context applicationImp = ReaderApplication.getApplicationImp();
        String R = d.R(applicationImp);
        if (R != null) {
            for (String str : com.xiaomi.mipush.sdk.c.d(applicationImp)) {
                if (!(R.equals(str) || TextUtils.isEmpty(str))) {
                    com.xiaomi.mipush.sdk.c.d(applicationImp, str, null);
                }
            }
        }
    }

    public static void e() {
        com.xiaomi.mipush.sdk.c.e(ReaderApplication.getApplicationImp(), "qqreader_6.5.3.0888_android", null);
    }
}
