package com.tencent.android.tpush.service.channel;

import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.service.c.a;
import com.tencent.android.tpush.service.channel.exception.ChannelException;

/* compiled from: ProGuard */
class e implements p {
    final /* synthetic */ b a;

    e(b bVar) {
        this.a = bVar;
    }

    public void a(JceStruct jceStruct, int i, JceStruct jceStruct2, a aVar) {
        if (i == 0) {
            a.a();
            if (b.a >= 3) {
                b.a++;
            } else {
                b.a++;
            }
        }
        com.tencent.android.tpush.a.a.c("TpnsChannel", "heartbeat success rsp = " + aVar.c() + " heartbeattimes = " + b.a);
    }

    public void a(JceStruct jceStruct, ChannelException channelException, a aVar) {
        com.tencent.android.tpush.a.a.h("TpnsChannel", "heartbeat failed onMessageSendFailed " + channelException.errorCode + "," + channelException.getMessage());
    }

    public void a(JceStruct jceStruct, a aVar) {
        com.tencent.android.tpush.a.a.h("TpnsChannel", "heartbeat failed TpnsMessage.IEventListener.onMessageDiscarded");
    }
}
