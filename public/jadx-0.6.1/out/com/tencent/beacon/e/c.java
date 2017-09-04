package com.tencent.beacon.e;

import android.os.Build;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: ProGuard */
public final class c {
    private static c b = null;
    private boolean a;

    public static synchronized c a() {
        c cVar;
        synchronized (c.class) {
            if (b == null) {
                b = new c();
            }
            cVar = b;
        }
        return cVar;
    }

    protected c() {
        boolean z = true;
        this.a = false;
        this.a = true;
        String str = Build.TAGS;
        if (str == null || !str.contains("test-keys")) {
            z = false;
        } else {
            a.b(" test-keys}", new Object[0]);
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
            a.b(" super_apk}", new Object[0]);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean d() {
        String[] strArr = new String[]{"/system/xbin/type", "su"};
        ArrayList e = e();
        if (e == null || e.size() <= 0) {
            a.b(" no response}", new Object[0]);
            return false;
        }
        Iterator it = e.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            a.b(str, new Object[0]);
            if (str.contains("not found")) {
                return false;
            }
        }
        a.b(" sufile}", new Object[0]);
        return true;
    }

    private static ArrayList<String> e() {
        ArrayList<String> arrayList = new ArrayList();
        try {
            Process exec = Runtime.getRuntime().exec(new String[]{"/system/bin/sh", "-c", "type su"});
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                arrayList.add(readLine);
            }
            bufferedReader = new BufferedReader(new InputStreamReader(exec.getErrorStream()));
            while (true) {
                String readLine2 = bufferedReader.readLine();
                if (readLine2 == null) {
                    return arrayList;
                }
                arrayList.add(readLine2);
            }
        } catch (Throwable th) {
            a.a(th);
            return null;
        }
    }
}
