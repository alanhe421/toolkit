package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

/* compiled from: ProGuard */
public final class TpnsRedirectReq extends JceStruct {
    public byte network = (byte) 0;
    public byte op = (byte) 0;

    public TpnsRedirectReq(byte b, byte b2) {
        this.network = b;
        this.op = b2;
    }

    public void writeTo(d dVar) {
        dVar.b(this.network, 0);
        dVar.b(this.op, 1);
    }

    public void readFrom(c cVar) {
        this.network = cVar.a(this.network, 0, false);
        this.op = cVar.a(this.op, 1, false);
    }
}
