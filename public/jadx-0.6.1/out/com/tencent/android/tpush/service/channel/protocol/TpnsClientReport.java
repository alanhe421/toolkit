package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

/* compiled from: ProGuard */
public final class TpnsClientReport extends JceStruct {
    static int cache_commandId;
    public long acceptTime = 0;
    public long accip = 0;
    public byte apn = (byte) 0;
    public byte available = (byte) 0;
    public int commandId = 0;
    public long connectTime = 0;
    public String domain = "";
    public long downstreamTime = 0;
    public byte isp = (byte) 0;
    public String lbs = "";
    public byte pack = (byte) 0;
    public int port = 0;
    public String qua = "";
    public byte result = (byte) 0;
    public long resultCode = 0;
    public String signal = "";
    public long tmcost = 0;
    public long upstreamTime = 0;

    public TpnsClientReport(int i, byte b, int i2, long j, byte b2, byte b3, byte b4, long j2, byte b5, long j3, String str, String str2, long j4, long j5, long j6, long j7, String str3, String str4) {
        this.commandId = i;
        this.isp = b;
        this.port = i2;
        this.accip = j;
        this.apn = b2;
        this.pack = b3;
        this.available = b4;
        this.tmcost = j2;
        this.result = b5;
        this.resultCode = j3;
        this.domain = str;
        this.qua = str2;
        this.connectTime = j4;
        this.upstreamTime = j5;
        this.downstreamTime = j6;
        this.acceptTime = j7;
        this.signal = str3;
        this.lbs = str4;
    }

    public void writeTo(d dVar) {
        dVar.a(this.commandId, 0);
        dVar.b(this.isp, 1);
        dVar.a(this.port, 2);
        dVar.a(this.accip, 3);
        dVar.b(this.apn, 4);
        dVar.b(this.pack, 5);
        dVar.b(this.available, 6);
        dVar.a(this.tmcost, 7);
        dVar.b(this.result, 8);
        dVar.a(this.resultCode, 9);
        if (this.domain != null) {
            dVar.a(this.domain, 10);
        }
        if (this.qua != null) {
            dVar.a(this.qua, 11);
        }
        dVar.a(this.connectTime, 12);
        dVar.a(this.upstreamTime, 13);
        dVar.a(this.downstreamTime, 14);
        dVar.a(this.acceptTime, 15);
        if (this.signal != null) {
            dVar.a(this.signal, 16);
        }
        if (this.lbs != null) {
            dVar.a(this.lbs, 17);
        }
    }

    public void readFrom(c cVar) {
        this.commandId = cVar.a(this.commandId, 0, false);
        this.isp = cVar.a(this.isp, 1, false);
        this.port = cVar.a(this.port, 2, false);
        this.accip = cVar.a(this.accip, 3, false);
        this.apn = cVar.a(this.apn, 4, false);
        this.pack = cVar.a(this.pack, 5, false);
        this.available = cVar.a(this.available, 6, false);
        this.tmcost = cVar.a(this.tmcost, 7, false);
        this.result = cVar.a(this.result, 8, false);
        this.resultCode = cVar.a(this.resultCode, 9, false);
        this.domain = cVar.a(10, false);
        this.qua = cVar.a(11, false);
        this.connectTime = cVar.a(this.connectTime, 12, false);
        this.upstreamTime = cVar.a(this.upstreamTime, 13, false);
        this.downstreamTime = cVar.a(this.downstreamTime, 14, false);
        this.acceptTime = cVar.a(this.acceptTime, 15, false);
        this.signal = cVar.a(16, false);
        this.lbs = cVar.a(17, false);
    }
}
