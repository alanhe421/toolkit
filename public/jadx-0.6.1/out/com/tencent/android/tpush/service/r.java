package com.tencent.android.tpush.service;

import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.service.channel.a;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.p;
import com.tencent.android.tpush.service.channel.protocol.TpnsConfigRsp;

/* compiled from: ProGuard */
class r implements p {
    final /* synthetic */ p a;

    r(p pVar) {
        this.a = pVar;
    }

    public void a(JceStruct jceStruct, int i, JceStruct jceStruct2, a aVar) {
        if (i == 0) {
            com.tencent.android.tpush.service.a.a.a(m.e()).a(((TpnsConfigRsp) jceStruct2).confContent);
            return;
        }
        com.tencent.android.tpush.a.a.h("PushServiceNetworkHandler", ">> loadConfig fail responseCode=" + i);
        this.a.a(i, "", aVar);
    }

    public void a(JceStruct jceStruct, ChannelException channelException, a aVar) {
        com.tencent.android.tpush.a.a.h("PushServiceNetworkHandler", "@@ loadConfiguration.onMessageSendFailed " + channelException.errorCode + "," + channelException.getMessage());
        this.a.a(channelException.errorCode, channelException.getMessage(), aVar);
    }

    public void a(JceStruct jceStruct, a aVar) {
    }
}
