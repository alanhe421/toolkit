package com.tencent.android.tpush.service.channel.protocol;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;
import java.util.ArrayList;

/* compiled from: ProGuard */
public final class TpnsReconnectRsp extends JceStruct {
    static ArrayList cache_appOfflinePushMsgList;
    public ArrayList appOfflinePushMsgList = null;
    public long confVersion = 0;
    public long timeUs = 0;

    public TpnsReconnectRsp(long j, ArrayList arrayList, long j2) {
        this.confVersion = j;
        this.appOfflinePushMsgList = arrayList;
        this.timeUs = j2;
    }

    public void writeTo(d dVar) {
        dVar.a(this.confVersion, 0);
        if (this.appOfflinePushMsgList != null) {
            dVar.a(this.appOfflinePushMsgList, 1);
        }
        dVar.a(this.timeUs, 2);
    }

    public void readFrom(c cVar) {
        this.confVersion = cVar.a(this.confVersion, 0, true);
        if (cache_appOfflinePushMsgList == null) {
            cache_appOfflinePushMsgList = new ArrayList();
            cache_appOfflinePushMsgList.add(new TpnsPushMsg());
        }
        this.appOfflinePushMsgList = (ArrayList) cVar.a(cache_appOfflinePushMsgList, 1, false);
        this.timeUs = cVar.a(this.timeUs, 2, false);
    }
}
