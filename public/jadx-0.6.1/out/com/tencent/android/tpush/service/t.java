package com.tencent.android.tpush.service;

import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.horse.DefaultServer;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.a;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.p;
import com.tencent.android.tpush.service.channel.protocol.TpnsGetApListRsp;

/* compiled from: ProGuard */
class t implements p {
    final /* synthetic */ p a;

    t(p pVar) {
        this.a = pVar;
    }

    public void a(JceStruct jceStruct, int i, JceStruct jceStruct2, a aVar) {
        if (i == 0) {
            DefaultServer.a(((TpnsGetApListRsp) jceStruct2).apList);
            CacheManager.saveLoadIpTime(m.e(), System.currentTimeMillis());
            return;
        }
        com.tencent.android.tpush.a.a.h("PushServiceNetworkHandler", ">> loadIPList fail responseCode=" + i);
    }

    public void a(JceStruct jceStruct, ChannelException channelException, a aVar) {
        com.tencent.android.tpush.a.a.h("PushServiceNetworkHandler", "@@ loadIPList.onMessageSendFailed " + channelException.errorCode + "," + channelException.getMessage());
    }

    public void a(JceStruct jceStruct, a aVar) {
    }
}
