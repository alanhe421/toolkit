package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;
import java.util.ArrayList;

/* compiled from: ProGuard */
public final class TpnsPushClickReq extends JceStruct {
    static ArrayList cache_msgClickList;
    public ArrayList msgClickList = null;

    public TpnsPushClickReq(ArrayList arrayList) {
        this.msgClickList = arrayList;
    }

    public void writeTo(d dVar) {
        dVar.a(this.msgClickList, 1);
    }

    public void readFrom(c cVar) {
        if (cache_msgClickList == null) {
            cache_msgClickList = new ArrayList();
            cache_msgClickList.add(new TpnsClickClientReport());
        }
        this.msgClickList = (ArrayList) cVar.a(cache_msgClickList, 1, true);
    }
}
