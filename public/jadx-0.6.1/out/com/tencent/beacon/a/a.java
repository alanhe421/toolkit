package com.tencent.beacon.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ProGuard */
public final class a {
    private static a a = null;
    private Boolean b = Boolean.valueOf(false);
    private int c = 0;
    private Context d;

    public static synchronized a a(Context context) {
        a aVar;
        synchronized (a.class) {
            if (a == null) {
                a = new a(context);
            }
            aVar = a;
        }
        return aVar;
    }

    private a(Context context) {
        this.d = context;
        List c = c();
        if (c.size() > 0) {
            com.tencent.beacon.net.a.g(a(c));
        }
    }

    private List<String> c() {
        Throwable th;
        List<String> list = null;
        if (this.d == null) {
            com.tencent.beacon.e.a.c("mContext = null,get result is null..", new Object[0]);
            return null;
        }
        try {
            PackageManager packageManager = this.d.getPackageManager();
            if (packageManager == null) {
                return null;
            }
            List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
            if (installedPackages == null || installedPackages.size() <= 0) {
                return null;
            }
            this.b = Boolean.valueOf(true);
            this.c = installedPackages.size();
            com.tencent.beacon.e.a.b("get successfull,and size=" + installedPackages.size(), new Object[0]);
            List<String> arrayList = new ArrayList();
            try {
                for (PackageInfo packageInfo : installedPackages) {
                    if ((packageInfo.applicationInfo.flags & 1) == 0) {
                        arrayList.add(packageInfo.packageName);
                    }
                }
                return arrayList;
            } catch (Throwable e) {
                Throwable th2 = e;
                list = arrayList;
                th = th2;
                com.tencent.beacon.e.a.a(th);
                com.tencent.beacon.e.a.c("get failed!", new Object[0]);
                return list;
            }
        } catch (Exception e2) {
            th = e2;
            com.tencent.beacon.e.a.a(th);
            com.tencent.beacon.e.a.c("get failed!", new Object[0]);
            return list;
        }
    }

    private static String a(List<String> list) {
        if (list == null || list.size() == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append((String) list.get(i)).append(" ");
        }
        return stringBuilder.toString().trim();
    }

    public final int a() {
        return this.c;
    }

    public final Boolean b() {
        return this.b;
    }
}
