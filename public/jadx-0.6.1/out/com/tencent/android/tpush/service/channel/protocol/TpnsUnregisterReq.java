package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

/* compiled from: ProGuard */
public final class TpnsUnregisterReq extends JceStruct {
    static UnregInfo cache_unregInfo;
    public short deviceType = (short) 0;
    public UnregInfo unregInfo = null;

    public TpnsUnregisterReq(UnregInfo unregInfo, short s) {
        this.unregInfo = unregInfo;
        this.deviceType = s;
    }

    public void writeTo(d dVar) {
        dVar.a(this.unregInfo, 0);
        dVar.a(this.deviceType, 1);
    }

    public void readFrom(c cVar) {
        if (cache_unregInfo == null) {
            cache_unregInfo = new UnregInfo();
        }
        this.unregInfo = (UnregInfo) cVar.a(cache_unregInfo, 0, true);
        this.deviceType = cVar.a(this.deviceType, 1, false);
    }
}
