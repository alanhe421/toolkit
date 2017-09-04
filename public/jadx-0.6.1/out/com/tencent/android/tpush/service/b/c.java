package com.tencent.android.tpush.service.b;

import android.content.Context;
import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.common.g;
import com.tencent.android.tpush.data.MessageId;
import com.tencent.android.tpush.service.channel.a;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.p;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushVerifyReq;

/* compiled from: ProGuard */
class c implements p {
    final /* synthetic */ MessageId a;
    final /* synthetic */ Context b;
    final /* synthetic */ a c;

    c(a aVar, MessageId messageId, Context context) {
        this.c = aVar;
        this.a = messageId;
        this.b = context;
    }

    public void a(JceStruct jceStruct, int i, JceStruct jceStruct2, a aVar) {
        Object obj = null;
        if (i == 0) {
            try {
                if (jceStruct instanceof TpnsPushVerifyReq) {
                    TpnsPushVerifyReq tpnsPushVerifyReq = (TpnsPushVerifyReq) jceStruct;
                    com.tencent.android.tpush.a.a.a(1, tpnsPushVerifyReq.msgReportList);
                    if (tpnsPushVerifyReq.msgReportList == null || tpnsPushVerifyReq.msgReportList.size() == 0) {
                        String str = "MessageManager";
                        StringBuilder append = new StringBuilder().append("ServiceAcking ack failed with null tReq.msgReportList rsp = ").append(aVar.c()).append(" msgId ");
                        if (this.a != null) {
                            obj = Long.valueOf(this.a.id);
                        }
                        com.tencent.android.tpush.a.a.h(str, append.append(obj).toString());
                    }
                    this.c.a(this.b, tpnsPushVerifyReq.msgReportList);
                } else {
                    com.tencent.android.tpush.a.a.h("MessageManager", "requestServiceAck -> Invalid ack callback");
                }
                g.a().a(1);
                g.a().a(new i(this.c, this.b, 1), 1, 3000);
            } catch (Throwable th) {
                com.tencent.android.tpush.a.a.h("MessageManager", "requestServiceAck -> Invalid ack callback");
            } finally {
                a.b(false);
            }
            return;
        }
        a.b(false);
        str = "MessageManager";
        append = new StringBuilder().append(">> ServiceAcking ack onMessageSendFailed responseCode= ").append(i).append(" msgId = ");
        if (this.a != null) {
            obj = Long.valueOf(this.a.id);
        }
        com.tencent.android.tpush.a.a.h(str, append.append(obj).toString());
    }

    public void a(JceStruct jceStruct, ChannelException channelException, a aVar) {
        com.tencent.android.tpush.a.a.h("MessageManager", "ServiceAcking ack onMessageSendFailed  responseCode= " + channelException.errorCode + "," + channelException.getMessage());
        a.b(false);
    }

    public void a(JceStruct jceStruct, a aVar) {
        com.tencent.android.tpush.a.a.h("MessageManager", "ServiceAcking ack onMessageDiscarded msgId = " + (this.a == null ? null : Long.valueOf(this.a.id)));
        a.b(false);
    }
}
