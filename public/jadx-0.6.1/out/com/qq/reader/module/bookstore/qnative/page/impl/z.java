package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.feed.card.FeedColumnASingleBookCard;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeServerColumnListAPage */
public class z extends af {
    private String a;
    private String b;

    public z(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        c cVar = new c(bundle);
        StringBuffer stringBuffer = new StringBuffer("listDispatch?");
        stringBuffer.append("action=itembooks");
        if (bundle.containsKey("algInfo")) {
            this.a = bundle.getString("algInfo");
        }
        if (bundle.containsKey("extInfoId")) {
            this.b = bundle.getString("extInfoId");
        }
        return cVar.b(stringBuffer.toString());
    }

    public void b(JSONObject jSONObject) {
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("bookList");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    FeedColumnASingleBookCard feedColumnASingleBookCard = new FeedColumnASingleBookCard(this, "");
                    if (feedColumnASingleBookCard != null) {
                        feedColumnASingleBookCard.setDataStatus(1001);
                        feedColumnASingleBookCard.setIndex(i);
                        feedColumnASingleBookCard.setAlgInfo(this.a);
                        feedColumnASingleBookCard.setExtInfoId(this.b);
                        feedColumnASingleBookCard.parseData(optJSONObject);
                        feedColumnASingleBookCard.setEventListener(l());
                        this.k.add(feedColumnASingleBookCard);
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
