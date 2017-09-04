package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

/* compiled from: ProGuard */
public final class TpnsUnregisterRsp extends JceStruct {
    public byte unregResult = (byte) 0;

    public TpnsUnregisterRsp(byte b) {
        this.unregResult = b;
    }

    public void writeTo(d dVar) {
        dVar.b(this.unregResult, 0);
    }

    public void readFrom(c cVar) {
        this.unregResult = cVar.a(this.unregResult, 0, true);
    }
}
