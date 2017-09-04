package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.page.a;
import java.util.List;
import org.json.JSONObject;

/* compiled from: NativeLocalSearchToolMainPage */
public class l extends a {
    public l(Bundle bundle, String str) {
        this.f = bundle;
        this.i = str;
    }

    public void b(JSONObject jSONObject) {
        for (Object obj : new String[]{"clouds", "conditions"}) {
            com.qq.reader.module.bookstore.qnative.card.a aVar = (com.qq.reader.module.bookstore.qnative.card.a) this.l.get(obj);
            if (aVar != null) {
                aVar.fillData(jSONObject);
            }
        }
    }

    public String a(List<com.qq.reader.module.bookstore.qnative.card.a> list) {
        c cVar = new c(null);
        cVar.a();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("nativepage/findbook/index?");
        stringBuffer.append("sex=" + d.aS(ReaderApplication.getApplicationImp()));
        return cVar.b(stringBuffer.toString());
    }
}
