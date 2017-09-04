package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.qmessage.data.impl.CommunityChosenListCard;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NativeLocalCommunityChosenContentPage */
public class e extends af {
    public e(Bundle bundle) {
        super(bundle);
    }

    public void b(JSONObject jSONObject) {
        super.b(jSONObject);
        JSONArray optJSONArray = jSONObject.optJSONArray("issueList");
        if (optJSONArray != null) {
            int i = 0;
            while (i < optJSONArray.length()) {
                try {
                    JSONObject jSONObject2 = (JSONObject) optJSONArray.get(i);
                    if (jSONObject2 != null) {
                        CommunityChosenListCard communityChosenListCard = new CommunityChosenListCard(this, CommunityChosenListCard.class.getSimpleName());
                        communityChosenListCard.fillData(jSONObject2);
                        this.k.add(communityChosenListCard);
                        this.l.put(i + "", communityChosenListCard);
                        communityChosenListCard.setEventListener(l());
                        communityChosenListCard.setIsShowBottomDivider(true);
                    }
                    i++;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        if (this.o > 0 && this.k.size() > 0) {
            ((CommunityChosenListCard) this.k.get(this.k.size() - 1)).setIsShowBottomDivider(false);
        }
    }

    public String a(Bundle bundle) {
        c cVar = new c(bundle);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("?");
        stringBuilder.append("pushTime=").append(bundle.getString("pushTime", ""));
        return cVar.a(com.qq.reader.appconfig.e.cN, stringBuilder.toString());
    }

    public boolean a() {
        return false;
    }
}
