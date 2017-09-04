package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.card.impl.StackTabLineCard;
import com.qq.reader.module.bookstore.qnative.card.impl.StackTabRecommendCard;
import com.qq.reader.module.bookstore.qnative.card.impl.StackTabRowCard;
import com.qq.reader.module.bookstore.qnative.card.impl.StackTabTitleCard;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentforOther;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NativeServerPageOfStackTab */
public class aq extends af {
    public aq(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        String str = "queryOperation?";
        return new c(bundle).b("queryOperation?");
    }

    protected void a(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            String string = jSONObject.getString("type");
            String toLowerCase = string.toLowerCase();
            a stackTabTitleCard;
            if (ComicStoreExclusiveItemCard.NET_AD_ATTR_COUNT.equalsIgnoreCase(toLowerCase)) {
                stackTabTitleCard = new StackTabTitleCard(this, string);
                stackTabTitleCard.fillData(jSONObject2.optJSONObject(string));
                stackTabTitleCard.setEventListener(l());
                this.k.add(stackTabTitleCard);
                this.l.put(stackTabTitleCard.getCardId(), stackTabTitleCard);
            } else if ("boyCategoryList".equalsIgnoreCase(toLowerCase) || "girlCategoryList".equalsIgnoreCase(toLowerCase) || "comicCategoryList".equalsIgnoreCase(toLowerCase) || "publishCategoryList".equalsIgnoreCase(toLowerCase) || "audioCategoryList".equalsIgnoreCase(toLowerCase)) {
                JSONArray optJSONArray = jSONObject2.optJSONArray(string);
                if (optJSONArray != null) {
                    int i = 0;
                    while (i < optJSONArray.length()) {
                        a stackTabRowCard = new StackTabRowCard(this, string);
                        JSONObject jSONObject3 = optJSONArray.getJSONObject(i);
                        JSONArray jSONArray = new JSONArray();
                        jSONArray.put(jSONObject3);
                        i++;
                        if (i >= optJSONArray.length()) {
                            stackTabRowCard.fillData(jSONArray);
                            stackTabRowCard.setEventListener(l());
                            this.k.add(stackTabRowCard);
                            this.l.put(stackTabRowCard.getCardId(), stackTabRowCard);
                            return;
                        }
                        jSONArray.put(optJSONArray.getJSONObject(i));
                        i++;
                        stackTabRowCard.fillData(jSONArray);
                        stackTabRowCard.setEventListener(l());
                        this.k.add(stackTabRowCard);
                        this.l.put(stackTabRowCard.getCardId(), stackTabRowCard);
                    }
                }
            } else if ("line".equalsIgnoreCase(toLowerCase)) {
                stackTabTitleCard = new StackTabLineCard(this, string);
                stackTabTitleCard.fillData(jSONObject2.optJSONObject(string));
                stackTabTitleCard.setEventListener(l());
                this.k.add(stackTabTitleCard);
                this.l.put(stackTabTitleCard.getCardId(), stackTabTitleCard);
            } else if ("recmd".equalsIgnoreCase(toLowerCase)) {
                stackTabTitleCard = new StackTabRecommendCard(this, string);
                stackTabTitleCard.fillData(jSONObject2);
                stackTabTitleCard.setEventListener(l());
                this.k.add(stackTabTitleCard);
                this.l.put(stackTabTitleCard.getCardId(), stackTabTitleCard);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean a() {
        return false;
    }

    public Class c() {
        return NativePageFragmentforOther.class;
    }
}
