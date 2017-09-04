package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;

/* compiled from: ProGuard */
public final class TpnsGetApListRsp extends JceStruct {
    static ApList cache_apList;
    public ApList apList = null;

    public TpnsGetApListRsp(ApList apList) {
        this.apList = apList;
    }

    public void writeTo(d dVar) {
        dVar.a(this.apList, 0);
    }

    public void readFrom(c cVar) {
        if (cache_apList == null) {
            cache_apList = new ApList();
        }
        this.apList = (ApList) cVar.a(cache_apList, 0, true);
    }
}
