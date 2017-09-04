package com.tencent.android.tpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.common.g;
import com.tencent.android.tpush.common.p;

/* compiled from: ProGuard */
final class aa extends BroadcastReceiver {
    final /* synthetic */ XGIOperateCallback a;

    aa(XGIOperateCallback xGIOperateCallback) {
        this.a = xGIOperateCallback;
    }

    public void onReceive(Context context, Intent intent) {
        p.a(context, (BroadcastReceiver) this);
        try {
            g.a().a(new ab(this.a, context, intent, 1, 1));
        } catch (Exception e) {
        }
    }
}
