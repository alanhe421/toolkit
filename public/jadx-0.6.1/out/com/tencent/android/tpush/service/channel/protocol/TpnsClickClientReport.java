package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

/* compiled from: ProGuard */
public final class TpnsClickClientReport extends JceStruct {
    public long accessId = 0;
    public long action = 0;
    public long broadcastId = 0;
    public long clickTime = 0;
    public long msgId = 0;
    public long timestamp = 0;
    public long type = 0;

    public TpnsClickClientReport(long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        this.msgId = j;
        this.accessId = j2;
        this.broadcastId = j3;
        this.timestamp = j4;
        this.type = j5;
        this.clickTime = j6;
        this.action = j7;
    }

    public void writeTo(d dVar) {
        dVar.a(this.msgId, 0);
        dVar.a(this.accessId, 1);
        dVar.a(this.broadcastId, 2);
        dVar.a(this.timestamp, 3);
        dVar.a(this.type, 4);
        dVar.a(this.clickTime, 5);
        dVar.a(this.action, 6);
    }

    public void readFrom(c cVar) {
        this.msgId = cVar.a(this.msgId, 0, true);
        this.accessId = cVar.a(this.accessId, 1, true);
        this.broadcastId = cVar.a(this.broadcastId, 2, false);
        this.timestamp = cVar.a(this.timestamp, 3, false);
        this.type = cVar.a(this.type, 4, false);
        this.clickTime = cVar.a(this.clickTime, 5, false);
        this.action = cVar.a(this.action, 6, false);
    }
}
