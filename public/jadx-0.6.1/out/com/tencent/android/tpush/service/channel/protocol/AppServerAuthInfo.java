package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

/* compiled from: ProGuard */
public final class AppServerAuthInfo extends JceStruct {
    public long accessId = 0;
    public String secretKey = "";

    public AppServerAuthInfo(long j, String str) {
        this.accessId = j;
        this.secretKey = str;
    }

    public void writeTo(d dVar) {
        dVar.a(this.accessId, 0);
        dVar.a(this.secretKey, 1);
    }

    public void readFrom(c cVar) {
        this.accessId = cVar.a(this.accessId, 0, true);
        this.secretKey = cVar.a(1, true);
    }
}
