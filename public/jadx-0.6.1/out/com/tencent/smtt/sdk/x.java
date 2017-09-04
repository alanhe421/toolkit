package com.tencent.smtt.sdk;

import android.os.HandlerThread;

class x extends HandlerThread {
    private static x a;

    public x(String str) {
        super(str);
    }

    public static synchronized x a() {
        x xVar;
        synchronized (x.class) {
            if (a == null) {
                a = new x("TbsHandlerThread");
                a.start();
            }
            xVar = a;
        }
        return xVar;
    }
}
