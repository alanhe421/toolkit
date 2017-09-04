package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

/* compiled from: ProGuard */
public final class AppInfo extends JceStruct {
    public long accessId = 0;
    public String accessKey = "";
    public String appCert = "";
    public byte keyEncrypted = (byte) 0;

    public AppInfo(long j, String str, String str2, byte b) {
        this.accessId = j;
        this.accessKey = str;
        this.appCert = str2;
        this.keyEncrypted = b;
    }

    public void writeTo(d dVar) {
        dVar.a(this.accessId, 0);
        dVar.a(this.accessKey, 1);
        dVar.a(this.appCert, 2);
        dVar.b(this.keyEncrypted, 3);
    }

    public void readFrom(c cVar) {
        this.accessId = cVar.a(this.accessId, 0, true);
        this.accessKey = cVar.a(1, true);
        this.appCert = cVar.a(2, true);
        this.keyEncrypted = cVar.a(this.keyEncrypted, 3, false);
    }
}
