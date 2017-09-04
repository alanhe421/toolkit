package com.tencent.upload.b.a;

import com.tencent.upload.b.a;
import java.util.Date;

public final class b {
    private static volatile boolean a = false;
    private static Object b = new Object();
    private static c c = null;

    private static void a() {
        if (!a) {
            synchronized (b) {
                if (a) {
                    return;
                }
                c cVar = new c("ReportThread");
                c = cVar;
                cVar.a();
                a = true;
            }
        }
    }

    public static void a(a aVar) {
        if (!a) {
            a();
        }
        c.a(aVar);
    }

    public static boolean a(String str, Date date) {
        if (!a) {
            a();
        }
        return c.a(str, date);
    }
}
