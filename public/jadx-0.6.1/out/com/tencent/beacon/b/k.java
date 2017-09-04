package com.tencent.beacon.b;

import android.content.Context;
import android.content.IntentFilter;

/* compiled from: ProGuard */
public final class k {
    protected Runnable a = new 1();
    private Context b;
    private a c;

    public k(Context context) {
        this.b = context;
        this.c = new a(this, (byte) 0);
    }

    public final void a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        this.b.registerReceiver(this.c, intentFilter);
    }
}
