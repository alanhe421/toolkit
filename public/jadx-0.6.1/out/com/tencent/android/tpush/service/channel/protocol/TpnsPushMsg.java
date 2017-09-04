package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

/* compiled from: ProGuard */
public final class TpnsPushMsg extends JceStruct {
    public long accessId = 0;
    public String appPkgName = "";
    public long busiMsgId = 0;
    public String content = "";
    public String date = "";
    public long msgId = 0;
    public long multiPkg = 0;
    public long serverTime = 0;
    public long timestamp = 0;
    public String title = "";
    public int ttl = 0;
    public long type = 0;

    public TpnsPushMsg(long j, long j2, long j3, String str, String str2, long j4, String str3, long j5, long j6, String str4, long j7, int i) {
        this.msgId = j;
        this.accessId = j2;
        this.busiMsgId = j3;
        this.title = str;
        this.content = str2;
        this.type = j4;
        this.appPkgName = str3;
        this.timestamp = j5;
        this.multiPkg = j6;
        this.date = str4;
        this.serverTime = j7;
        this.ttl = i;
    }

    public void writeTo(d dVar) {
        dVar.a(this.msgId, 0);
        dVar.a(this.accessId, 1);
        dVar.a(this.busiMsgId, 2);
        dVar.a(this.title, 3);
        dVar.a(this.content, 4);
        dVar.a(this.type, 5);
        if (this.appPkgName != null) {
            dVar.a(this.appPkgName, 6);
        }
        dVar.a(this.timestamp, 7);
        dVar.a(this.multiPkg, 8);
        if (this.date != null) {
            dVar.a(this.date, 9);
        }
        dVar.a(this.serverTime, 10);
        dVar.a(this.ttl, 11);
    }

    public void readFrom(c cVar) {
        this.msgId = cVar.a(this.msgId, 0, true);
        this.accessId = cVar.a(this.accessId, 1, true);
        this.busiMsgId = cVar.a(this.busiMsgId, 2, true);
        this.title = cVar.a(3, true);
        this.content = cVar.a(4, true);
        this.type = cVar.a(this.type, 5, true);
        this.appPkgName = cVar.a(6, false);
        this.timestamp = cVar.a(this.timestamp, 7, false);
        this.multiPkg = cVar.a(this.multiPkg, 8, false);
        this.date = cVar.a(9, false);
        this.serverTime = cVar.a(this.serverTime, 10, false);
        this.ttl = cVar.a(this.ttl, 11, false);
    }
}
