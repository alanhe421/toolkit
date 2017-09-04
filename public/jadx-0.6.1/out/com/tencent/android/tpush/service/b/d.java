package com.tencent.android.tpush.service.b;

import android.content.Context;
import android.content.Intent;
import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.common.g;
import com.tencent.android.tpush.service.channel.a;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.p;
import com.tencent.android.tpush.service.m;
import com.tencent.upload.log.trace.TracerConfig;
import java.util.ArrayList;

/* compiled from: ProGuard */
class d implements p {
    final /* synthetic */ ArrayList a;
    final /* synthetic */ Context b;
    final /* synthetic */ Intent c;
    final /* synthetic */ a d;

    d(a aVar, ArrayList arrayList, Context context, Intent intent) {
        this.d = aVar;
        this.a = arrayList;
        this.b = context;
        this.c = intent;
    }

    public void a(JceStruct jceStruct, int i, JceStruct jceStruct2, a aVar) {
        com.tencent.android.tpush.service.c.a.c(this.a);
        if (i == 0) {
            this.d.b(m.e(), this.a);
            g.a().a(new e(this), TracerConfig.LOG_FLUSH_DURATION);
        } else {
            com.tencent.android.tpush.a.a.h("MessageManager", ">> msg ckicled ack failed responseCode=" + i);
        }
        a.c(false);
    }

    public void a(JceStruct jceStruct, ChannelException channelException, a aVar) {
        com.tencent.android.tpush.a.a.h("MessageManager", "### msg ack onMessageSendFailed  responseCode=" + channelException.errorCode);
        a.c(false);
    }

    public void a(JceStruct jceStruct, a aVar) {
        a.c(false);
    }
}
