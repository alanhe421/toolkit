package com.tencent.android.tpush.stat;

import android.content.Context;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushClientReport;
import com.tencent.android.tpush.stat.event.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* compiled from: ProGuard */
final class n implements Runnable {
    final /* synthetic */ ArrayList a;
    final /* synthetic */ Context b;

    n(ArrayList arrayList, Context context) {
        this.a = arrayList;
        this.b = context;
    }

    public void run() {
        try {
            List arrayList = new ArrayList(this.a.size());
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                TpnsPushClientReport tpnsPushClientReport = (TpnsPushClientReport) it.next();
                long j = tpnsPushClientReport.type;
                long j2 = tpnsPushClientReport.timestamp;
                long j3 = tpnsPushClientReport.broadcastId;
                long j4 = tpnsPushClientReport.msgId;
                long j5 = tpnsPushClientReport.accessId;
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("type", "" + j);
                jSONObject.put(MessageKey.MSG_BUSI_MSG_ID, "" + j3);
                jSONObject.put(MessageKey.MSG_ID, "" + j4);
                a aVar = new a(this.b, h.b(this.b, j5), "Ack", j5, j2);
                aVar.a().c = jSONObject;
                arrayList.add(aVar);
            }
            h.a(arrayList);
        } catch (Throwable th) {
            h.d.b(th);
        }
    }
}
