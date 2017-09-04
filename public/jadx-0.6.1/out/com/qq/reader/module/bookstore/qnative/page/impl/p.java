package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.module.bookstore.qnative.card.impl.ListCard4ListenTopicList;
import org.json.JSONObject;

/* compiled from: NativeServerAdvListPage */
public class p extends af {
    public p(Bundle bundle) {
        super(bundle);
    }

    protected void a(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("ads");
        if (optJSONObject != null) {
            for (String str : this.f.getString("URL_BUILD_PERE_ADVS").split(",")) {
                if (str != null && str.length() > 0) {
                    JSONObject optJSONObject2 = optJSONObject.optJSONObject(str);
                    if (optJSONObject2 != null) {
                        ListCard4ListenTopicList listCard4ListenTopicList = new ListCard4ListenTopicList(this, str);
                        listCard4ListenTopicList.setEventListener(l());
                        listCard4ListenTopicList.fillData(optJSONObject2);
                        this.k.add(listCard4ListenTopicList);
                        this.l.put(listCard4ListenTopicList.getCardId(), listCard4ListenTopicList);
                    }
                }
            }
        }
    }
}
