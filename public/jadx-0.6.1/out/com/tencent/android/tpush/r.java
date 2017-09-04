package com.tencent.android.tpush;

import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.service.d.f;

/* compiled from: ProGuard */
final class r implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ boolean b;

    r(Context context, boolean z) {
        this.a = context;
        this.b = z;
    }

    public void run() {
        a.a(2);
        f.a(this.a, "com.tencent.android.tpush.debug," + this.a.getPackageName(), this.b ? 1 : 0);
        Intent intent = new Intent("com.tencent.android.tpush.action.ENABLE_DEBUG");
        intent.putExtra("debugMode", this.b);
        this.a.sendBroadcast(intent);
    }
}
