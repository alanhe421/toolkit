package com.tencent.android.tpush.service.b;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.rpc.b;

/* compiled from: ProGuard */
class h implements ServiceConnection {
    final /* synthetic */ g a;

    h(g gVar) {
        this.a = gVar;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        try {
            this.a.c = b.a(iBinder);
            if (this.a.c != null) {
                this.a.c.a(this.a.a.toURI(), this.a.d);
            }
        } catch (Throwable th) {
            a.c("MessageManager", "SendBroadcastByRPC", th);
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        this.a.e = null;
        this.a.c = null;
        this.a.d = null;
    }
}
