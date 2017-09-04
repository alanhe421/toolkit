package com.qq.reader.common.web.js;

import com.qq.reader.common.monitor.f;
import com.qq.reader.common.web.js.a.a.b;
import com.qq.reader.module.bookstore.qweb.channel.d;

public class JsSubscribe extends b {
    private a a;

    public JsSubscribe(a aVar) {
        this.a = aVar;
    }

    public void doSubscribe(String str) {
        try {
            d.a().a(Integer.valueOf(str).intValue());
            if (this.a != null) {
                this.a.doSuccess();
            }
        } catch (Exception e) {
            f.d("JsSubscribe", "getSubscribedInDb " + e.toString());
        }
    }

    public int getSubscribedInDb(String str) {
        try {
            return d.a().b(Integer.valueOf(str).intValue());
        } catch (Exception e) {
            f.d("JsSubscribe", "getSubscribedInDb " + e.toString());
            return -1;
        }
    }
}
