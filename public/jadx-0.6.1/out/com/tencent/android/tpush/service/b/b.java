package com.tencent.android.tpush.service.b;

import android.content.Context;
import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.g;
import com.tencent.android.tpush.service.channel.a;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.p;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushVerifyReq;
import com.tencent.android.tpush.service.m;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ProGuard */
class b implements p {
    final /* synthetic */ ArrayList a;
    final /* synthetic */ Context b;
    final /* synthetic */ a c;

    b(a aVar, ArrayList arrayList, Context context) {
        this.c = aVar;
        this.a = arrayList;
        this.b = context;
    }

    public void a(JceStruct jceStruct, int i, JceStruct jceStruct2, a aVar) {
        a.a(false);
        if (i == 0) {
            com.tencent.android.tpush.a.a.a(6, this.a);
            List list = ((TpnsPushVerifyReq) jceStruct).msgReportList;
            com.tencent.android.tpush.a.a.a(7, list);
            com.tencent.android.tpush.service.c.a.b(this.a);
            if (list == null || list.size() == 0) {
                com.tencent.android.tpush.a.a.h("MessageManager", "requestAck ack failed with null tReq.msgReportList rsp = " + aVar.c());
            }
            this.c.c(m.e(), list);
            g.a().a(2);
            g.a().a(new i(this.c, this.b, 2), 2, 3000);
            return;
        }
        com.tencent.android.tpush.a.a.h("MessageManager", ">> msg ack onMessageSendFailed  responseCode=" + i);
    }

    public void a(JceStruct jceStruct, ChannelException channelException, a aVar) {
        com.tencent.android.tpush.a.a.h(Constants.ServiceLogTag, "@@ TpnsMessage.IEventListener.onMessageSendFailed " + channelException.errorCode + "," + channelException.getMessage());
        a.a(false);
        com.tencent.android.tpush.a.a.a(8, this.a);
    }

    public void a(JceStruct jceStruct, a aVar) {
        a.a(false);
        com.tencent.android.tpush.a.a.h(Constants.ServiceLogTag, "@@ TpnsMessage.IEventListener.onMessageDiscarded ");
    }
}
