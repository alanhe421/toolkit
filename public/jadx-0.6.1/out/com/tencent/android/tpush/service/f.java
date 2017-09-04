package com.tencent.android.tpush.service;

import android.content.Context;
import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.service.channel.a;
import com.tencent.android.tpush.service.channel.b;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.p;
import com.tencent.android.tpush.service.channel.protocol.TpnsRegisterReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsRegisterRsp;

/* compiled from: ProGuard */
class f implements p {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ boolean c;
    final /* synthetic */ Context d;
    final /* synthetic */ a e;

    f(a aVar, String str, String str2, boolean z, Context context) {
        this.e = aVar;
        this.a = str;
        this.b = str2;
        this.c = z;
        this.d = context;
    }

    public void a(JceStruct jceStruct, int i, JceStruct jceStruct2, a aVar) {
        b.a().b(true);
        if (i == 0) {
            if (XGPushConfig.enableDebug) {
                com.tencent.android.tpush.a.a.c(a.a, ">> Register [accId = " + this.a + " , packName = " + this.b + " , rsp = " + aVar.c() + "]");
            }
            a.a(this.e, i, (TpnsRegisterRsp) jceStruct2, (TpnsRegisterReq) jceStruct, aVar, this.b, this.c);
            try {
                a.a(this.e, this.d);
                return;
            } catch (Throwable th) {
                com.tencent.android.tpush.a.a.c(a.a, "handler app info failed", th);
                return;
            }
        }
        com.tencent.android.tpush.a.a.h(a.a, ">> Register ack fail responseCode = " + i);
        a.a(this.e, i, "服务器处理失败，返回错误", (TpnsRegisterReq) jceStruct, aVar, this.b);
    }

    public void a(JceStruct jceStruct, ChannelException channelException, a aVar) {
        com.tencent.android.tpush.a.a.i(a.a, "@@ TpnsMessage.IEventListener.onMessageSendFailed " + channelException.errorCode + "," + channelException.getMessage());
        a.a(this.e, channelException.errorCode, channelException.getMessage(), (TpnsRegisterReq) jceStruct, aVar, this.b);
    }

    public void a(JceStruct jceStruct, a aVar) {
    }
}
