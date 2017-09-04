package com.tencent.android.tpush.service.channel;

import android.os.Handler;
import android.os.Message;
import com.tencent.android.tpush.a.a;

/* compiled from: ProGuard */
class d extends Handler {
    final /* synthetic */ b a;

    d(b bVar) {
        this.a = bVar;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        switch (message.what) {
            case 1000:
                this.a.e();
                return;
            default:
                a.h("TpnsChannel", "Unexpected: unhandled msg - " + message.what);
                return;
        }
    }
}
