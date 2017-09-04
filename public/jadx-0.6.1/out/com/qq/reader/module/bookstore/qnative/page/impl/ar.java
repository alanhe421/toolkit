package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.appconfig.e;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.card.impl.SingleSubscribeAuthorCard;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentforEmptyRefreshable;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeServerPageOfSubscribedAuthors */
public class ar extends af {
    public ar(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        return e.cG + "?userId=" + bundle.getString("userId");
    }

    public boolean a() {
        return false;
    }

    public void b(JSONObject jSONObject) {
        if (jSONObject != null) {
            JSONArray optJSONArray = jSONObject.optJSONArray("followManitoList");
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                this.k.clear();
                this.l.clear();
                return;
            }
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    a singleSubscribeAuthorCard = new SingleSubscribeAuthorCard(this, "SingleSubscribeAuthorCard");
                    singleSubscribeAuthorCard.setEventListener(l());
                    singleSubscribeAuthorCard.fillData(optJSONObject);
                    this.k.add(singleSubscribeAuthorCard);
                    this.l.put(singleSubscribeAuthorCard.getCardId(), singleSubscribeAuthorCard);
                }
            }
        }
    }

    public Class c() {
        return NativePageFragmentforEmptyRefreshable.class;
    }
}
