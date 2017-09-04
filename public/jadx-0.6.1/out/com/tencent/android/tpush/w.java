package com.tencent.android.tpush;

import android.app.Activity;
import android.content.Intent;

/* compiled from: ProGuard */
final class w implements Runnable {
    final /* synthetic */ Activity a;
    final /* synthetic */ Intent b;

    w(Activity activity, Intent intent) {
        this.a = activity;
        this.b = intent;
    }

    public void run() {
        XGPushManager.c(this.a, this.b);
        XGPushManager.d(this.a, this.b);
    }
}
