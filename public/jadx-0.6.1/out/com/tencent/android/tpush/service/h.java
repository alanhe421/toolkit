package com.tencent.android.tpush.service;

import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.service.channel.a;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.p;
import com.tencent.android.tpush.service.channel.protocol.TpnsUnregisterReq;

/* compiled from: ProGuard */
class h implements p {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ a c;

    h(a aVar, String str, String str2) {
        this.c = aVar;
        this.a = str;
        this.b = str2;
    }

    public void a(JceStruct jceStruct, int i, JceStruct jceStruct2, a aVar) {
        if (i == 0) {
            com.tencent.android.tpush.a.a.e(a.a, ">> UnRegister ack with [accId = " + this.a + " , packName = " + this.b + " , rsp = " + aVar.c() + "]");
            a.a(this.c, i, (TpnsUnregisterReq) jceStruct, aVar, this.b);
            return;
        }
        com.tencent.android.tpush.a.a.h(a.a, ">> unregeister ack failed responseCode=" + i);
        a.a(this.c, i, "服务器处理失败，返回错误", (TpnsUnregisterReq) jceStruct, aVar, this.b);
    }

    public void a(JceStruct jceStruct, ChannelException channelException, a aVar) {
        com.tencent.android.tpush.a.a.h(a.a, "@@ unregister onMessageSendFailed " + channelException.errorCode + "," + channelException.getMessage());
        a.a(this.c, channelException.errorCode, channelException.getMessage(), (TpnsUnregisterReq) jceStruct, aVar, this.b);
    }

    public void a(JceStruct jceStruct, a aVar) {
    }
}
