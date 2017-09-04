package com.tencent.android.tpush.service;

import android.content.Context;
import com.tencent.android.tpush.common.p;

/* compiled from: ProGuard */
class b implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ a b;

    b(a aVar, Context context) {
        this.b = aVar;
        this.a = context;
    }

    public void run() {
        p.e(this.a);
    }
}
