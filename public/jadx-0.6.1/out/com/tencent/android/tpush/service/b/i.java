package com.tencent.android.tpush.service.b;

import android.content.Context;
import com.tencent.android.tpush.a.a;

/* compiled from: ProGuard */
class i implements Runnable {
    int a;
    Context b;
    final /* synthetic */ a c;

    public i(a aVar, Context context, int i) {
        this.c = aVar;
        this.b = context;
        this.a = i;
    }

    public void run() {
        switch (this.a) {
            case 1:
                a.a(this.c, this.b, null);
                return;
            case 2:
                a.a(this.c, this.b, Long.valueOf(-1));
                return;
            default:
                a.h("MessageManager", "unknown report type");
                return;
        }
    }
}
