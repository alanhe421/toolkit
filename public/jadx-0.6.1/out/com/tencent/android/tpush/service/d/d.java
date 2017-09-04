package com.tencent.android.tpush.service.d;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.tencent.android.tpush.data.a;
import com.tencent.android.tpush.service.cache.CacheManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: ProGuard */
public class d {
    private static volatile d a = null;
    private Context b = null;
    private Map c = new HashMap(10);
    private Map d = new HashMap(10);

    private d(Context context) {
        this.b = context.getApplicationContext();
        this.d.put(Long.valueOf(-1), "");
    }

    public static d a(Context context) {
        if (a == null) {
            synchronized (d.class) {
                if (a == null) {
                    a = new d(context);
                }
            }
        }
        return a;
    }

    public String a(long j) {
        if (this.d.containsKey(Long.valueOf(j))) {
            return (String) this.d.get(Long.valueOf(j));
        }
        List<String> registerInfos = CacheManager.getRegisterInfos(this.b);
        if (registerInfos != null) {
            for (String str : registerInfos) {
                a registerInfoByPkgName = CacheManager.getRegisterInfoByPkgName(str);
                if (registerInfoByPkgName != null) {
                    this.d.put(Long.valueOf(registerInfoByPkgName.a), a(str));
                }
            }
        }
        return this.d.get(Long.valueOf(j)) == null ? "" : (String) this.d.get(Long.valueOf(j));
    }

    public String a(String str) {
        if (str == null) {
            return "";
        }
        if (this.c.containsKey(str)) {
            return (String) this.c.get(str);
        }
        List<PackageInfo> installedPackages = this.b.getPackageManager().getInstalledPackages(0);
        if (installedPackages != null) {
            for (PackageInfo packageInfo : installedPackages) {
                if (str.equals(packageInfo.packageName)) {
                    this.c.put(str, packageInfo.versionName);
                    return packageInfo.versionName;
                }
            }
        }
        return "";
    }
}
