package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;
import java.util.ArrayList;

/* compiled from: ProGuard */
public final class TpnsPushClientReq extends JceStruct {
    static ArrayList cache_msgList;
    public ArrayList msgList = null;
    public long timeUs = 0;

    public TpnsPushClientReq(ArrayList arrayList, long j) {
        this.msgList = arrayList;
        this.timeUs = j;
    }

    public void writeTo(d dVar) {
        dVar.a(this.msgList, 0);
        dVar.a(this.timeUs, 1);
    }

    public void readFrom(c cVar) {
        if (cache_msgList == null) {
            cache_msgList = new ArrayList();
            cache_msgList.add(new TpnsPushMsg());
        }
        this.msgList = (ArrayList) cVar.a(cache_msgList, 0, true);
        this.timeUs = cVar.a(this.timeUs, 1, true);
    }
}
