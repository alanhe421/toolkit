package com.tencent.android.tpush.service;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.service.b.j;
import com.tencent.android.tpush.service.cache.CacheManager;

/* compiled from: ProGuard */
class c implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ a c;

    c(a aVar, Context context, String str) {
        this.c = aVar;
        this.a = context;
        this.b = str;
    }

    public void run() {
        try {
            this.a.getPackageManager().getApplicationInfo(this.b, 8192);
        } catch (NameNotFoundException e) {
            a.c(a.a, "appRemoveHandler check app:" + this.b + " has been removed.");
            j.a().a(this.a, this.b);
            CacheManager.removeRegisterInfos(this.b);
            p.a().a(this.b);
        } catch (Throwable th) {
        }
    }
}
