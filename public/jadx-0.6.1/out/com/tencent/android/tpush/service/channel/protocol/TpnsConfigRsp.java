package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

/* compiled from: ProGuard */
public final class TpnsConfigRsp extends JceStruct {
    public String confContent = "";
    public long confVersion = 0;

    public TpnsConfigRsp(long j, String str) {
        this.confVersion = j;
        this.confContent = str;
    }

    public void writeTo(d dVar) {
        dVar.a(this.confVersion, 0);
        dVar.a(this.confContent, 1);
    }

    public void readFrom(c cVar) {
        this.confVersion = cVar.a(this.confVersion, 0, true);
        this.confContent = cVar.a(1, true);
    }
}
