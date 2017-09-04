package com.qq.reader.module.bookstore.qnative.page.impl;

import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.card.impl.RankHorizontalCard;
import com.qq.reader.module.bookstore.qnative.page.a;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NativeLocalRankBoardPage */
public class k extends a {
    public void b(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("head");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            com.qq.reader.module.bookstore.qnative.card.a aVar = (com.qq.reader.module.bookstore.qnative.card.a) this.l.get("rankhead");
            if (aVar != null) {
                aVar.fillData(optJSONArray);
            }
        }
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("rank");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = (JSONObject) jSONArray.get(i);
                if (jSONObject2 != null) {
                    if (i == 0) {
                        ((com.qq.reader.module.bookstore.qnative.card.a) this.l.get("rank")).fillData(jSONObject2);
                    } else if (i == 1) {
                        com.qq.reader.module.bookstore.qnative.card.a aVar2 = (com.qq.reader.module.bookstore.qnative.card.a) this.l.get("ranksecond");
                        ((RankHorizontalCard) aVar2).setShowDivider(true);
                        aVar2.fillData(jSONObject2);
                    } else {
                        ((com.qq.reader.module.bookstore.qnative.card.a) this.l.get("rankH")).fillData(jSONObject2);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void a(String str) {
        super.a(str);
    }

    public String a(List<com.qq.reader.module.bookstore.qnative.card.a> list) {
        return new c(this.f).b(new StringBuffer("queryOperation?").toString());
    }
}
