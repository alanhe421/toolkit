package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.module.feed.card.UserCenterMoreInterActionCard;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeUserCenterMoreInterActionPage */
public class bd extends af {
    public bd(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        return e.bX + "uin=" + d.R(ReaderApplication.getApplicationImp()) + "&userId=" + bundle.getString("userId");
    }

    public void b(JSONObject jSONObject) {
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("interList");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    UserCenterMoreInterActionCard userCenterMoreInterActionCard = new UserCenterMoreInterActionCard(this, "");
                    if (userCenterMoreInterActionCard != null) {
                        userCenterMoreInterActionCard.setDataStatus(1001);
                        userCenterMoreInterActionCard.setIndex(i);
                        userCenterMoreInterActionCard.parseData(optJSONObject);
                        userCenterMoreInterActionCard.setEventListener(l());
                        this.k.add(userCenterMoreInterActionCard);
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
