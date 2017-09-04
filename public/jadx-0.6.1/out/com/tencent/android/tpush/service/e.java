package com.tencent.android.tpush.service;

import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.common.m;
import com.tencent.android.tpush.service.channel.a;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.p;
import com.tencent.android.tpush.service.d.f;
import com.tencent.util.TimeFormatterUtils;

/* compiled from: ProGuard */
class e implements p {
    final /* synthetic */ d a;

    e(d dVar) {
        this.a = dVar;
    }

    public void a(JceStruct jceStruct, int i, JceStruct jceStruct2, a aVar) {
        if (i == 0) {
            long j = this.a.b + TimeFormatterUtils.ONE_DAY;
            try {
                f.b(this.a.a, "com.tencent.android.tpush.action.next.applist.ts", j);
                m.b(this.a.a, "com.tencent.android.tpush.action.next.applist.ts", j);
            } catch (Throwable th) {
            }
        }
    }

    public void a(JceStruct jceStruct, ChannelException channelException, a aVar) {
        com.tencent.android.tpush.a.a.h(a.a, ">>> reportReq onMessageSendFailed(" + jceStruct + ", " + channelException + ", " + aVar + ")");
    }

    public void a(JceStruct jceStruct, a aVar) {
        com.tencent.android.tpush.a.a.h(a.a, ">>> reportReq onMessageDiscarded(" + jceStruct + ", " + aVar + ")");
    }
}
