package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

/* compiled from: ProGuard */
public final class TpnsHeartBeatRsp extends JceStruct {
    public byte padding = (byte) 0;

    public TpnsHeartBeatRsp(byte b) {
        this.padding = b;
    }

    public void writeTo(d dVar) {
        dVar.b(this.padding, 0);
    }

    public void readFrom(c cVar) {
        this.padding = cVar.a(this.padding, 0, true);
    }
}
