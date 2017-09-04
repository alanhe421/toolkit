package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.impl.MyFavorEmptyCard;
import com.qq.reader.module.feed.card.FeedColumnBSingleBookCard;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeServerColumnListBPage */
public class aa extends af {
    private String a;
    private String b;

    public aa(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        c cVar = new c(bundle);
        StringBuffer stringBuffer = new StringBuffer("listDispatch?");
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
            this.o = jSONObject.optLong("pagestamp");
            if (this.o == 0 && (optJSONArray == null || optJSONArray.length() == 0)) {
                this.k.add(new MyFavorEmptyCard(this, "MyFavourEmpty"));
            } else if (optJSONArray != null && optJSONArray.length() > 0) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    FeedColumnBSingleBookCard feedColumnBSingleBookCard = new FeedColumnBSingleBookCard(this, "");
                    if (feedColumnBSingleBookCard != null) {
                        feedColumnBSingleBookCard.setDataStatus(1001);
                        feedColumnBSingleBookCard.setIndex(i);
                        feedColumnBSingleBookCard.setAlgInfo(this.a);
                        feedColumnBSingleBookCard.setExtInfoId(this.b);
                        feedColumnBSingleBookCard.parseData(optJSONObject);
                        feedColumnBSingleBookCard.setEventListener(l());
                        this.k.add(feedColumnBSingleBookCard);
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
