package com.qq.reader.module.game.presenter;

import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.module.game.data.b;
import com.qq.reader.module.game.presenter.a.a;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: GameCouponHelper */
class a$2 implements c {
    final /* synthetic */ a a;
    final /* synthetic */ a b;

    a$2(a aVar, a aVar2) {
        this.b = aVar;
        this.a = aVar2;
    }

    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
        try {
            com.qq.reader.common.monitor.debug.c.e("GameCoupon", str);
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt("code") == 0) {
                JSONArray optJSONArray = jSONObject.optJSONArray("tickets");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    a.a(this.b).clear();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        jSONObject = (JSONObject) optJSONArray.get(i);
                        b bVar = new b();
                        bVar.a(jSONObject);
                        if (bVar.h()) {
                            a.a(this.b).add(bVar);
                        } else {
                            a.a(this.b, bVar.a());
                        }
                    }
                }
                a.b(this.b).post(new Runnable(this) {
                    final /* synthetic */ a$2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.a(a.a(this.a.b));
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
    }
}
