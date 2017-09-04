package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.module.feed.card.UserCenterMoreBookCard;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeUserCenterMoreBookPage */
public class bb extends af {
    public bb(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        return e.bW + "uin=" + d.R(ReaderApplication.getApplicationImp()) + "&userId=" + bundle.getString("userId") + "&platform=" + "android";
    }

    public void b(JSONObject jSONObject) {
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("shelfList");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    UserCenterMoreBookCard userCenterMoreBookCard = new UserCenterMoreBookCard(this, "");
                    if (userCenterMoreBookCard != null) {
                        userCenterMoreBookCard.setDataStatus(1001);
                        userCenterMoreBookCard.setIndex(i);
                        userCenterMoreBookCard.parseData(optJSONObject);
                        userCenterMoreBookCard.setEventListener(l());
                        this.k.add(userCenterMoreBookCard);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean a() {
        return false;
    }
}
