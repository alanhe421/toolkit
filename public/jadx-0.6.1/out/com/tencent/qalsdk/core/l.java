package com.tencent.qalsdk.core;

import android.content.Context;
import com.tencent.qalsdk.config.NativeConfigStore;
import com.tencent.qalsdk.util.QLog;

/* compiled from: MsfStore */
public class l {
    static NativeConfigStore a = null;
    private static String b = "MSF.C.MsfStore";

    public synchronized boolean a(Context context) {
        boolean z = false;
        synchronized (this) {
            a = new NativeConfigStore(context);
            String a = b.a(context);
            if (a == null) {
                if (QLog.isColorLevel()) {
                    QLog.w(b, 2, "can not load data");
                }
                a.loadSaveRootSucc.set(false);
            } else {
                try {
                    a.setSaveRootPath(a);
                    a.loadConfig(context, false);
                    z = true;
                } catch (Throwable th) {
                    QLog.d(b, 1, "setSaveRootPath or loadConfig failed." + th, th);
                }
            }
        }
        return z;
    }

    public void a(String str, String str2) {
    }

    public String a(String str) {
        return null;
    }

    public String[] b(String str) {
        return null;
    }

    public static NativeConfigStore a() {
        return a;
    }
}
