package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

/* compiled from: ProGuard */
public final class TpnsRedirectRsp extends JceStruct {
    public long ip = 0;
    public int port = 0;

    public TpnsRedirectRsp(long j, int i) {
        this.ip = j;
        this.port = i;
    }

    public void writeTo(d dVar) {
        dVar.a(this.ip, 0);
        dVar.a(this.port, 1);
    }

    public void readFrom(c cVar) {
        this.ip = cVar.a(this.ip, 0, false);
        this.port = cVar.a(this.port, 1, false);
    }
}
