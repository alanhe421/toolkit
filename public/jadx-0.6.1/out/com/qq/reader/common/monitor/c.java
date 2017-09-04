package com.qq.reader.common.monitor;

import com.qq.reader.ReaderApplication;
import com.qq.reader.common.utils.r;
import java.util.HashMap;
import java.util.Map;

/* compiled from: DatabaseErrorLog */
public class c {
    private static c a = null;

    private c() {
    }

    public static synchronized c a() {
        c cVar;
        synchronized (c.class) {
            if (a == null) {
                a = new c();
            }
            cVar = a;
        }
        return cVar;
    }

    public void a(Exception exception) {
        boolean z;
        Map hashMap = new HashMap();
        hashMap.put("Exception", exception.toString() + " || " + exception.getMessage());
        boolean z2 = false;
        if (r.a()) {
            if (r.c(0)) {
                z2 = true;
            } else {
                hashMap.put("Status", "noMemory");
            }
            z = z2;
        } else {
            hashMap.put("Status", "unAble");
            z = false;
        }
        i.a("get_writable_error", z, 0, 0, hashMap, ReaderApplication.getApplicationImp().getApplicationContext());
    }
}
