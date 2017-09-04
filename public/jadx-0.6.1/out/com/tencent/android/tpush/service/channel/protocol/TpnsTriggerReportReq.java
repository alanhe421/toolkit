package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

/* compiled from: ProGuard */
public final class TpnsTriggerReportReq extends JceStruct {
    public long timeEnd = 0;
    public long timeStart = 0;

    public TpnsTriggerReportReq(long j, long j2) {
        this.timeStart = j;
        this.timeEnd = j2;
    }

    public void writeTo(d dVar) {
        dVar.a(this.timeStart, 0);
        dVar.a(this.timeEnd, 1);
    }

    public void readFrom(c cVar) {
        this.timeStart = cVar.a(this.timeStart, 0, true);
        this.timeEnd = cVar.a(this.timeEnd, 1, true);
    }
}
