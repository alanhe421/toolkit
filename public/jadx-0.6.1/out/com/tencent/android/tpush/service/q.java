package com.tencent.android.tpush.service;

import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.a;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.p;
import com.tencent.android.tpush.service.channel.protocol.TpnsReconnectReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsReconnectRsp;

/* compiled from: ProGuard */
class q implements p {
    final /* synthetic */ p a;

    q(p pVar) {
        this.a = pVar;
    }

    public void a(JceStruct jceStruct, int i, JceStruct jceStruct2, a aVar) {
        if (i == 0) {
            if (jceStruct != null) {
                com.tencent.android.tpush.a.a.a(7, ((TpnsReconnectReq) jceStruct).recvMsgList);
                CacheManager.updateUnregUninList(m.e(), ((TpnsReconnectReq) jceStruct).unregInfoList);
                com.tencent.android.tpush.service.b.a.a().c(m.e(), ((TpnsReconnectReq) jceStruct).recvMsgList);
                com.tencent.android.tpush.service.b.a.a().b(m.e(), ((TpnsReconnectReq) jceStruct).msgClickList);
            }
            TpnsReconnectRsp tpnsReconnectRsp = (TpnsReconnectRsp) jceStruct2;
            if (!(tpnsReconnectRsp == null || tpnsReconnectRsp.appOfflinePushMsgList == null || tpnsReconnectRsp.appOfflinePushMsgList.size() <= 0)) {
                com.tencent.android.tpush.service.b.a.a().a(tpnsReconnectRsp.appOfflinePushMsgList, tpnsReconnectRsp.timeUs, aVar);
            }
            if (tpnsReconnectRsp != null) {
                this.a.a(aVar.b(), tpnsReconnectRsp.confVersion);
                return;
            }
            return;
        }
        com.tencent.android.tpush.a.a.h("PushServiceNetworkHandler", ">> reconn failed responseCode=" + i);
    }

    public void a(JceStruct jceStruct, ChannelException channelException, a aVar) {
    }

    public void a(JceStruct jceStruct, a aVar) {
    }
}
