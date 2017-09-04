package com.tencent.android.tpush.stat;

import android.content.Context;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushMsg;
import com.tencent.android.tpush.stat.event.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* compiled from: ProGuard */
final class m implements Runnable {
    final /* synthetic */ ArrayList a;
    final /* synthetic */ Context b;

    m(ArrayList arrayList, Context context) {
        this.a = arrayList;
        this.b = context;
    }

    public void run() {
        try {
            List arrayList = new ArrayList(this.a.size());
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                TpnsPushMsg tpnsPushMsg = (TpnsPushMsg) it.next();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("type", "" + tpnsPushMsg.type);
                jSONObject.put(MessageKey.MSG_BUSI_MSG_ID, "" + tpnsPushMsg.busiMsgId);
                jSONObject.put(MessageKey.MSG_ID, "" + tpnsPushMsg.msgId);
                a aVar = new a(this.b, h.b(this.b, tpnsPushMsg.accessId), "SrvAck", tpnsPushMsg.accessId, tpnsPushMsg.timestamp);
                aVar.a().c = jSONObject;
                arrayList.add(aVar);
            }
            h.a(arrayList);
        } catch (Throwable th) {
            h.d.b(th);
        }
    }
}
