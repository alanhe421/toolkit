package com.qq.reader.module.feed.loader;

import com.qq.reader.common.conn.http.HttpResponseException;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import com.qq.reader.module.feed.data.impl.e;
import com.qq.reader.module.feed.data.impl.g;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: FeedDataStyleBLoader */
class d$2 implements c {
    final /* synthetic */ WeakReference a;
    final /* synthetic */ e b;
    final /* synthetic */ d c;

    d$2(d dVar, WeakReference weakReference, e eVar) {
        this.c = dVar;
        this.a = weakReference;
        this.b = eVar;
    }

    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
        try {
            com.qq.reader.common.monitor.debug.c.e("FeedTimeStyleBUtil", "loadDataWithNet OK...");
            JSONArray optJSONArray = new JSONObject(str).optJSONArray("areas");
            if (optJSONArray == null || optJSONArray.length() != 0) {
                int c = this.b.c();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    String jSONObject = optJSONArray.optJSONObject(i).toString();
                    g gVar = new g();
                    if (this.b.a()) {
                        c++;
                        gVar.a(c);
                    }
                    if (this.b.a() || this.b.b()) {
                        gVar.a(this.b.f());
                    }
                    gVar.b(jSONObject);
                    if (this.b.a()) {
                        this.b.a(0, gVar);
                    } else {
                        this.b.a(gVar);
                    }
                    d.a(this.c, gVar, false);
                }
                d.a(this.c, this.b, "NET");
                Iterator it = this.b.h().iterator();
                while (it.hasNext()) {
                    ((FeedBaseCard) it.next()).mIsFromNet = true;
                }
                d.a(this.c, this.a, this.b, true);
                return;
            }
            d.a(this.c, this.a, this.b, -2);
        } catch (Exception e) {
            e.printStackTrace();
            d.a(this.c, this.a, this.b, -1);
        }
    }

    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
        com.qq.reader.common.monitor.debug.c.e("FeedTimeStyleBUtil", "loadDataWithNet ERROR...");
        if (!(exception instanceof HttpResponseException)) {
            d.a(this.c, this.a, this.b, -1);
        } else if (((HttpResponseException) exception).getStateCode() == 206) {
            d.a(this.c, this.a, this.b, -3);
        } else {
            d.a(this.c, this.a, this.b, -1);
        }
    }
}
