package com.tencent.android.tpush.service;

import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.service.channel.a;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.p;

/* compiled from: ProGuard */
class g implements p {
    final /* synthetic */ long a;
    final /* synthetic */ int b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ a e;

    g(a aVar, long j, int i, String str, String str2) {
        this.e = aVar;
        this.a = j;
        this.b = i;
        this.c = str;
        this.d = str2;
    }

    public void a(JceStruct jceStruct, int i, JceStruct jceStruct2, a aVar) {
        if (i == 0) {
            com.tencent.android.tpush.a.a.e(a.a, "Set tag ack success  [accId = " + this.a + " , tagtype = " + this.b + " , tagName = " + this.c + ", packName = " + this.d + " , rsp = " + aVar.c() + "]");
        } else {
            com.tencent.android.tpush.a.a.i(a.a, "Set tag ack failed with responseCode = " + i + " , tagName = " + this.c);
        }
        a.a(this.e, i, this.c, this.b, this.d);
    }

    public void a(JceStruct jceStruct, ChannelException channelException, a aVar) {
        if (channelException != null) {
            a.a(this.e, channelException.errorCode, this.c, this.b, this.d);
        }
    }

    public void a(JceStruct jceStruct, a aVar) {
        com.tencent.android.tpush.a.a.g(a.a, "Set tag onMessageDiscarded  , tagName = " + this.c);
    }
}
