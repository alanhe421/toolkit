package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.util.Log;

final class v implements Runnable {
    final /* synthetic */ Context a;

    v(Context context) {
        this.a = context;
    }

    public void run() {
        try {
            PackageInfo packageInfo = this.a.getPackageManager().getPackageInfo(this.a.getPackageName(), 4612);
            i.b(this.a);
            i.a(this.a, packageInfo);
            i.b(this.a, packageInfo);
        } catch (Throwable th) {
            Log.e("ManifestChecker", "", th);
        }
    }
}
