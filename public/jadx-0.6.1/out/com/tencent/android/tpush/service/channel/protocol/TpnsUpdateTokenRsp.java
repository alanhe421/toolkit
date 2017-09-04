package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

/* compiled from: ProGuard */
public final class TpnsUpdateTokenRsp extends JceStruct {
    public byte result = (byte) 0;
    public String token = "";

    public TpnsUpdateTokenRsp(String str, byte b) {
        this.token = str;
        this.result = b;
    }

    public void writeTo(d dVar) {
        dVar.a(this.token, 0);
        dVar.b(this.result, 1);
    }

    public void readFrom(c cVar) {
        this.token = cVar.a(0, true);
        this.result = cVar.a(this.result, 1, true);
    }
}
