package com.qq.reader.module.rookie.presenter;

import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import org.json.JSONObject;

/* compiled from: RookieGiftHelper */
class a$5 implements c {
    final /* synthetic */ a a;

    a$5(a aVar) {
        this.a = aVar;
    }

    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
        try {
            this.a.a("已经从服务器获得新的礼物列表");
            com.qq.reader.common.monitor.debug.c.e("RookieGift", str);
            JSONObject jSONObject = new JSONObject(str);
            a.a(this.a, Boolean.valueOf(true));
            a.a(this.a, jSONObject);
            a.a(this.a, Boolean.valueOf(false));
            this.a.c(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
    }
}
