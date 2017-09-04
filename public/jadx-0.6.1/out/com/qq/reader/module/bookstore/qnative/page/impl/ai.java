package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import com.qq.reader.appconfig.e;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.impl.VirtualRecommendCard;
import com.qq.reader.module.bookstore.qnative.card.impl.VirtualRecommendFourBookCard;
import com.qq.reader.module.bookstore.qnative.card.impl.VirtualRecommendOneBookCard;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentForEditorRecommend;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NativeServerPageOfEditorRecommend */
public class ai extends af {
    public ai(Bundle bundle) {
        super(bundle);
    }

    public String a(Bundle bundle) {
        c cVar = new c(bundle);
        StringBuilder stringBuilder = new StringBuilder("common/oneeditorrec?");
        stringBuilder.append("id=").append(bundle.getLong("_id", 0));
        return cVar.a(e.a, stringBuilder.toString());
    }

    public void b(JSONObject jSONObject) {
        super.b(jSONObject);
        this.o = (long) jSONObject.optInt("pagestamp");
        if (jSONObject != null) {
            JSONArray optJSONArray = jSONObject.optJSONArray("list");
            int i = 0;
            while (i < optJSONArray.length()) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i).optJSONObject("recommend");
                if (optJSONObject != null) {
                    JSONArray optJSONArray2 = optJSONObject.optJSONArray("bookList");
                    if (optJSONArray2 != null) {
                        int length = optJSONArray2.length();
                        VirtualRecommendCard virtualRecommendCard = null;
                        if (length == 1) {
                            virtualRecommendCard = new VirtualRecommendOneBookCard(this, length, false);
                        } else if (length >= 2) {
                            virtualRecommendCard = new VirtualRecommendFourBookCard(this, length, false);
                        }
                        if (virtualRecommendCard != null) {
                            virtualRecommendCard.fillData(optJSONArray.optJSONObject(i));
                            virtualRecommendCard.setEventListener(l());
                            this.k.add(virtualRecommendCard);
                        }
                        i++;
                    } else {
                        return;
                    }
                }
                return;
            }
        }
    }

    public boolean a() {
        return false;
    }

    public Class c() {
        return NativePageFragmentForEditorRecommend.class;
    }
}
