package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

/* compiled from: ProGuard */
public final class TpnsUpdateTokenReq extends JceStruct {
    public long accessId = 0;
    public String externalToken = "";
    public String token = "";
    public String type = "";

    public TpnsUpdateTokenReq(long j, String str, String str2, String str3) {
        this.accessId = j;
        this.token = str;
        this.type = str2;
        this.externalToken = str3;
    }

    public void writeTo(d dVar) {
        dVar.a(this.accessId, 0);
        dVar.a(this.token, 1);
        dVar.a(this.type, 2);
        dVar.a(this.externalToken, 3);
    }

    public void readFrom(c cVar) {
        this.accessId = cVar.a(this.accessId, 0, true);
        this.token = cVar.a(1, true);
        this.type = cVar.a(2, true);
        this.externalToken = cVar.a(3, true);
    }
}
