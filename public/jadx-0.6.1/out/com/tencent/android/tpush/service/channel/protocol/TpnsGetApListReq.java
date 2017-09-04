package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

/* compiled from: ProGuard */
public final class TpnsGetApListReq extends JceStruct {
    static NetworkInfo cache_netInfo;
    public NetworkInfo netInfo = null;

    public TpnsGetApListReq(NetworkInfo networkInfo) {
        this.netInfo = networkInfo;
    }

    public void writeTo(d dVar) {
        dVar.a(this.netInfo, 0);
    }

    public void readFrom(c cVar) {
        if (cache_netInfo == null) {
            cache_netInfo = new NetworkInfo();
        }
        this.netInfo = (NetworkInfo) cVar.a(cache_netInfo, 0, true);
    }
}
