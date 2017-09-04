package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.b.c;

final class aa implements Runnable {
    final /* synthetic */ String[] a;
    final /* synthetic */ Context b;

    aa(String[] strArr, Context context) {
        this.a = strArr;
        this.b = context;
    }

    public void run() {
        try {
            for (Object obj : this.a) {
                if (!TextUtils.isEmpty(obj)) {
                    PackageInfo packageInfo = this.b.getPackageManager().getPackageInfo(obj, 4);
                    if (packageInfo != null) {
                        c.b(this.b, packageInfo);
                    }
                }
            }
        } catch (Throwable th) {
            c.a(th);
        }
    }
}
