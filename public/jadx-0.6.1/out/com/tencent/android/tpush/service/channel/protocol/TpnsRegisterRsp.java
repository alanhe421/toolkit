package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

/* compiled from: ProGuard */
public final class TpnsRegisterRsp extends JceStruct {
    public long confVersion = 0;
    public String token = "";

    public TpnsRegisterRsp(long j, String str) {
        this.confVersion = j;
        this.token = str;
    }

    public void writeTo(d dVar) {
        dVar.a(this.confVersion, 0);
        dVar.a(this.token, 1);
    }

    public void readFrom(c cVar) {
        this.confVersion = cVar.a(this.confVersion, 0, true);
        this.token = cVar.a(1, true);
    }
}
