package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.card.impl.SearchMoreCard;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeServerSearchMorePage */
public class ay extends af {
    public ay(Bundle bundle) {
        super(bundle);
    }

    public void b(JSONObject jSONObject) {
        try {
            this.o = jSONObject.optLong("pagestamp");
            JSONArray optJSONArray = jSONObject.optJSONArray("conditions");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    a searchMoreCard = new SearchMoreCard(this, "searchmore");
                    searchMoreCard.setEventListener(l());
                    searchMoreCard.fillData(jSONObject2);
                    this.k.add(searchMoreCard);
                    this.l.put(searchMoreCard.getCardId(), searchMoreCard);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean a() {
        return false;
    }

    public String a(Bundle bundle) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("findbook/hotsearchmore?");
        stringBuilder.append("sex=" + d.aS(ReaderApplication.getApplicationImp()));
        return new c(this.f).b(stringBuilder.toString());
    }
}
