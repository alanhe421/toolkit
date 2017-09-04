package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

/* compiled from: ProGuard */
public final class TpnsCheckMsgReq extends JceStruct {
    public String token = "";

    public TpnsCheckMsgReq(String str) {
        this.token = str;
    }

    public void writeTo(d dVar) {
        dVar.a(this.token, 0);
    }

    public void readFrom(c cVar) {
        this.token = cVar.a(0, true);
    }
}
