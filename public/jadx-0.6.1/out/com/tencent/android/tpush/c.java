package com.tencent.android.tpush;

import android.content.Context;
import com.tencent.android.tpush.common.m;
import com.tencent.android.tpush.horse.Tools;
import com.tencent.android.tpush.service.d.f;

/* compiled from: ProGuard */
final class c implements Runnable {
    final /* synthetic */ Context a;

    c(Context context) {
        this.a = context;
    }

    public void run() {
        if (!f.a(m.a(this.a, XGPush4Msdk.a(this.a), null))) {
            m.b(this.a, XGPush4Msdk.a(this.a));
            Tools.clearCacheServerItems(this.a);
            Tools.clearOptStrategyItem(this.a);
        }
    }
}
