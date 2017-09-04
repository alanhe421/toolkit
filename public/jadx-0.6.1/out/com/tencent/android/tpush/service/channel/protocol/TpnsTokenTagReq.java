package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

/* compiled from: ProGuard */
public final class TpnsTokenTagReq extends JceStruct {
    public long accessId = 0;
    public int flag = 0;
    public String tag = "";

    public TpnsTokenTagReq(long j, String str, int i) {
        this.accessId = j;
        this.tag = str;
        this.flag = i;
    }

    public void writeTo(d dVar) {
        dVar.a(this.accessId, 0);
        dVar.a(this.tag, 1);
        dVar.a(this.flag, 2);
    }

    public void readFrom(c cVar) {
        this.accessId = cVar.a(this.accessId, 0, true);
        this.tag = cVar.a(1, true);
        this.flag = cVar.a(this.flag, 2, true);
    }
}
