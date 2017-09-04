package com.tencent.smtt.sdk;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

class y {
    public boolean a;
    private Map<String, String> b;

    public y() {
        this.b = null;
        this.b = new HashMap();
    }

    public synchronized void a(int i) {
        aq a = aq.a();
        if (a.b()) {
            this.b.put("is_first_init_x5", String.valueOf(this.a));
            a.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "setTbsInitPerformanceData", new Class[]{Integer.TYPE, Map.class}, Integer.valueOf(i), this.b);
        }
    }

    public synchronized void a(String str, byte b) {
        if (!TextUtils.isEmpty(str)) {
            String str2 = "";
            if (b == (byte) 1) {
                str2 = "_begin";
            } else if (b == (byte) 2) {
                str2 = "_end";
            }
            this.b.put(str + str2, String.valueOf(System.currentTimeMillis()));
        }
    }
}
