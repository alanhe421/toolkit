package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.appconfig.e;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.card.impl.SingleAuthorNewsCard;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeServerAuthorAllNewsPage */
public class s extends af {
    public s(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        c cVar = new c(this.f);
        StringBuffer stringBuffer = new StringBuffer("");
        stringBuffer.append("manitoNews?authorId=" + this.f.getString("AUTHORPAGE_KEY_AUTHORID") + "&platform=1");
        return cVar.a(e.a, stringBuffer.toString());
    }

    public void b(JSONObject jSONObject) {
        this.p = jSONObject.toString();
        this.o = jSONObject.optLong("pagestamp");
        JSONArray optJSONArray = jSONObject.optJSONArray("newslist");
        int i = 0;
        while (optJSONArray != null && i < optJSONArray.length()) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            a singleAuthorNewsCard = new SingleAuthorNewsCard(this, "newsCard");
            singleAuthorNewsCard.fillData(optJSONObject);
            singleAuthorNewsCard.setEventListener(l());
            this.k.add(singleAuthorNewsCard);
            i++;
        }
    }

    public boolean a() {
        return false;
    }
}
