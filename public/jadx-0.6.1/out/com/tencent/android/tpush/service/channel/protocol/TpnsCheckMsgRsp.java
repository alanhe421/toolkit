package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

/* compiled from: ProGuard */
public final class TpnsCheckMsgRsp extends JceStruct {
    public short result = (short) 0;

    public TpnsCheckMsgRsp(short s) {
        this.result = s;
    }

    public void writeTo(d dVar) {
        dVar.a(this.result, 0);
    }

    public void readFrom(c cVar) {
        this.result = cVar.a(this.result, 0, true);
    }
}
