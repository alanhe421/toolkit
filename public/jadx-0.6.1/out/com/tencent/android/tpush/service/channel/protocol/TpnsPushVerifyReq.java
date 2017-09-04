package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;
import java.util.ArrayList;

/* compiled from: ProGuard */
public final class TpnsPushVerifyReq extends JceStruct {
    static ArrayList cache_msgReportList;
    public ArrayList msgReportList = null;

    public TpnsPushVerifyReq(ArrayList arrayList) {
        this.msgReportList = arrayList;
    }

    public void writeTo(d dVar) {
        dVar.a(this.msgReportList, 1);
    }

    public void readFrom(c cVar) {
        if (cache_msgReportList == null) {
            cache_msgReportList = new ArrayList();
            cache_msgReportList.add(new TpnsPushClientReport());
        }
        this.msgReportList = (ArrayList) cVar.a(cache_msgReportList, 1, true);
    }
}
