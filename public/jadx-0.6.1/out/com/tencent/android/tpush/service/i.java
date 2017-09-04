package com.tencent.android.tpush.service;

import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.service.channel.a;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.p;

/* compiled from: ProGuard */
class i implements p {
    final /* synthetic */ String a;
    final /* synthetic */ a b;

    i(a aVar, String str) {
        this.b = aVar;
        this.a = str;
    }

    public void a(JceStruct jceStruct, int i, JceStruct jceStruct2, a aVar) {
        if (i == 0) {
            com.tencent.android.tpush.a.a.e(a.a, ">> updateOtherPushToken ack with [accId = " + this.a + "  , rsp = " + i + "]");
        } else {
            com.tencent.android.tpush.a.a.i(a.a, ">> updateOtherPushToken ack failed responseCode=" + i);
        }
    }

    public void a(JceStruct jceStruct, ChannelException channelException, a aVar) {
        com.tencent.android.tpush.a.a.i(a.a, "@@ updateOtherPushToken onMessageSendFailed " + channelException.errorCode + "," + channelException.getMessage());
    }

    public void a(JceStruct jceStruct, a aVar) {
    }
}
