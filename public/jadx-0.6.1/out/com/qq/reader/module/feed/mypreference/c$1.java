package com.qq.reader.module.feed.mypreference;

import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import org.json.JSONObject;

/* compiled from: ReadingGeneCache */
class c$1 implements c {
    final /* synthetic */ c a;

    c$1(c cVar) {
        this.a = cVar;
    }

    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
        com.qq.reader.common.monitor.debug.c.a("ccc", "str " + str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt("code", -1) != 0) {
                c.a(this.a);
                return;
            }
            int optInt = jSONObject.optInt("gtype");
            if (optInt != -1) {
                d.H(ReaderApplication.getApplicationImp(), optInt);
            }
            d.F(ReaderApplication.getApplicationImp(), jSONObject.optInt("sex"));
            c.a(this.a, new d(jSONObject));
            if (c.b(this.a) != null) {
                c.b(this.a).a(c.c(this.a));
                c.a(this.a, null);
            }
            c.a(this.a, str);
        } catch (Exception e) {
            e.printStackTrace();
            c.a(this.a);
        }
    }

    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
        exception.printStackTrace();
        c.a(this.a);
    }
}
