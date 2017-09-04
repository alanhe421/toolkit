package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

/* compiled from: ProGuard */
public final class TpnsConfigReq extends JceStruct {
    public long confVersion = 0;

    public TpnsConfigReq(long j) {
        this.confVersion = j;
    }

    public void writeTo(d dVar) {
        dVar.a(this.confVersion, 0);
    }

    public void readFrom(c cVar) {
        this.confVersion = cVar.a(this.confVersion, 0, true);
    }
}
