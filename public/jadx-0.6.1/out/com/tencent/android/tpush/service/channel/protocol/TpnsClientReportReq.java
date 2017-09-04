package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;
import java.util.ArrayList;

/* compiled from: ProGuard */
public final class TpnsClientReportReq extends JceStruct {
    static ArrayList cache_reportMsgs;
    public ArrayList reportMsgs = null;

    public TpnsClientReportReq(ArrayList arrayList) {
        this.reportMsgs = arrayList;
    }

    public void writeTo(d dVar) {
        if (this.reportMsgs != null) {
            dVar.a(this.reportMsgs, 1);
        }
    }

    public void readFrom(c cVar) {
        if (cache_reportMsgs == null) {
            cache_reportMsgs = new ArrayList();
            cache_reportMsgs.add(new TpnsClientReport());
        }
        this.reportMsgs = (ArrayList) cVar.a(cache_reportMsgs, 1, false);
    }
}
