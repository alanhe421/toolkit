package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

/* compiled from: ProGuard */
public final class TpnsPushClientReport extends JceStruct {
    public long accessId = 0;
    public byte ackType = (byte) 0;
    public byte apn = (byte) 0;
    public long broadcastId = 0;
    public long confirmMs = 0;
    public byte isp = (byte) 0;
    public long locip = 0;
    public int locport = 0;
    public long msgId = 0;
    public byte pack = (byte) 0;
    public String qua = "";
    public long receiveTime = 0;
    public String serviceHost = "";
    public long timeUs = 0;
    public long timestamp = 0;
    public long type = 0;

    public TpnsPushClientReport(long j, long j2, byte b, byte b2, byte b3, String str, long j3, int i, String str2, long j4, long j5, long j6, long j7, long j8, long j9, byte b4) {
        this.msgId = j;
        this.accessId = j2;
        this.isp = b;
        this.apn = b2;
        this.pack = b3;
        this.qua = str;
        this.locip = j3;
        this.locport = i;
        this.serviceHost = str2;
        this.timeUs = j4;
        this.confirmMs = j5;
        this.broadcastId = j6;
        this.timestamp = j7;
        this.type = j8;
        this.receiveTime = j9;
        this.ackType = b4;
    }

    public void writeTo(d dVar) {
        dVar.a(this.msgId, 0);
        dVar.a(this.accessId, 1);
        dVar.b(this.isp, 2);
        dVar.b(this.apn, 3);
        dVar.b(this.pack, 4);
        if (this.qua != null) {
            dVar.a(this.qua, 5);
        }
        dVar.a(this.locip, 6);
        dVar.a(this.locport, 7);
        if (this.serviceHost != null) {
            dVar.a(this.serviceHost, 8);
        }
        dVar.a(this.timeUs, 9);
        dVar.a(this.confirmMs, 10);
        dVar.a(this.broadcastId, 11);
        dVar.a(this.timestamp, 12);
        dVar.a(this.type, 13);
        dVar.a(this.receiveTime, 14);
        dVar.b(this.ackType, 15);
    }

    public void readFrom(c cVar) {
        this.msgId = cVar.a(this.msgId, 0, true);
        this.accessId = cVar.a(this.accessId, 1, true);
        this.isp = cVar.a(this.isp, 2, false);
        this.apn = cVar.a(this.apn, 3, false);
        this.pack = cVar.a(this.pack, 4, false);
        this.qua = cVar.a(5, false);
        this.locip = cVar.a(this.locip, 6, false);
        this.locport = cVar.a(this.locport, 7, false);
        this.serviceHost = cVar.a(8, false);
        this.timeUs = cVar.a(this.timeUs, 9, false);
        this.confirmMs = cVar.a(this.confirmMs, 10, false);
        this.broadcastId = cVar.a(this.broadcastId, 11, false);
        this.timestamp = cVar.a(this.timestamp, 12, false);
        this.type = cVar.a(this.type, 13, false);
        this.receiveTime = cVar.a(this.receiveTime, 14, false);
        this.ackType = cVar.a(this.ackType, 15, false);
    }
}
