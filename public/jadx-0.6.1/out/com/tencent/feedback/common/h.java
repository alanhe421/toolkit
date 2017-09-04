package com.tencent.feedback.common;

import android.os.Build;
import com.tencent.feedback.proguard.a;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: RQDSRC */
public final class h {
    private static h b = null;
    private boolean a;

    public static synchronized h a() {
        h hVar;
        synchronized (h.class) {
            if (b == null) {
                b = new h();
            }
            hVar = b;
        }
        return hVar;
    }

    protected h() {
        boolean z = true;
        this.a = false;
        this.a = true;
        String str = Build.TAGS;
        if (str == null || !str.contains("test-keys")) {
            z = false;
        } else {
            e.b("rqdp{  test-keys}", new Object[0]);
        }
        if (!z && !c() && !d()) {
            this.a = false;
        }
    }

    public final synchronized boolean b() {
        return this.a;
    }

    private static boolean c() {
        try {
            if (!new File("/system/app/Superuser.apk").exists()) {
                return false;
            }
            e.b("rqdp{  super_apk}", new Object[0]);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean d() {
        ArrayList a = a.a(new String[]{"/system/bin/sh", "-c", "type su"});
        if (a == null || a.size() <= 0) {
            e.b("rqdp{  no response}", new Object[0]);
            return false;
        }
        Iterator it = a.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            e.b(str, new Object[0]);
            if (str.contains("not found")) {
                return false;
            }
        }
        e.b("rqdp{  sufile}", new Object[0]);
        return true;
    }
}
