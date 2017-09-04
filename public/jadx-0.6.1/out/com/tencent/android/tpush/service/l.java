package com.tencent.android.tpush.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.common.g;

/* compiled from: ProGuard */
class l extends BroadcastReceiver {
    final /* synthetic */ a a;

    private l(a aVar) {
        this.a = aVar;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null && context != null) {
            g.a().a(new k(this.a, context, intent));
        }
    }
}
