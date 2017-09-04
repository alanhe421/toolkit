package com.tencent.android.tpush.stat;

import android.content.Context;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.service.channel.protocol.TpnsClickClientReport;
import com.tencent.android.tpush.stat.event.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* compiled from: ProGuard */
final class o implements Runnable {
    final /* synthetic */ ArrayList a;
    final /* synthetic */ Context b;

    o(ArrayList arrayList, Context context) {
        this.a = arrayList;
        this.b = context;
    }

    public void run() {
        try {
            List arrayList = new ArrayList(this.a.size());
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                TpnsClickClientReport tpnsClickClientReport = (TpnsClickClientReport) it.next();
                long j = tpnsClickClientReport.type;
                long j2 = tpnsClickClientReport.timestamp;
                long j3 = tpnsClickClientReport.broadcastId;
                long j4 = tpnsClickClientReport.msgId;
                long j5 = tpnsClickClientReport.accessId;
                long j6 = tpnsClickClientReport.action;
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("type", "" + j);
                jSONObject.put(MessageKey.MSG_BUSI_MSG_ID, "" + j3);
                jSONObject.put(MessageKey.MSG_ID, "" + j4);
                jSONObject.put("action", "" + j6);
                a aVar = new a(this.b, h.b(this.b, j5), "Action", j5, j2);
                aVar.a().c = jSONObject;
                arrayList.add(aVar);
            }
            h.a(arrayList);
        } catch (Throwable th) {
            h.d.b(th);
        }
    }
}
