package com.tencent.android.tpush.service.b;

import android.content.Intent;
import android.content.ServiceConnection;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.rpc.a;
import com.tencent.android.tpush.service.m;

/* compiled from: ProGuard */
class g implements Runnable {
    final /* synthetic */ Intent a;
    final /* synthetic */ a b;
    private a c;
    private com.tencent.android.tpush.rpc.g d = new com.tencent.android.tpush.rpc.g();
    private ServiceConnection e = new h(this);

    g(a aVar, Intent intent) {
        this.b = aVar;
        this.a = intent;
    }

    public void run() {
        try {
            this.a.setAction(this.a.getPackage() + Constants.RPC_SUFFIX);
            this.d.a(this.e);
            m.e().bindService(this.a, this.e, 1);
        } catch (Throwable th) {
            com.tencent.android.tpush.a.a.c("MessageManager", "SendBroadcastByRPC -> bindService", th);
            if (th instanceof SecurityException) {
                try {
                    this.a.setAction(Constants.ACTION_INTERNAL_PUSH_MESSAGE);
                    com.tencent.android.tpush.a.a.d("MessageManager", "SendBroadcastByIntent -> PushServiceManager.getContext().sendBroadcast" + this.a);
                    m.e().sendBroadcast(this.a);
                } catch (Throwable th2) {
                    com.tencent.android.tpush.a.a.c("MessageManager", "SendBroadcastByRPC -> sendBroadcast", th2);
                }
            }
        }
    }
}
