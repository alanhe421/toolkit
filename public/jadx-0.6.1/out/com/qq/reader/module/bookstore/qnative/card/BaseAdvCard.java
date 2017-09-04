package com.qq.reader.module.bookstore.qnative.card;

import com.qq.reader.common.utils.h;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class BaseAdvCard extends a {
    public BaseAdvCard(b bVar, String str) {
        super(bVar, str);
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        JSONArray optJSONArray = jSONObject.optJSONArray(ComicStoreExclusiveItemCard.NET_AD_ATTR_ADVS);
        if (optJSONArray == null) {
            return false;
        }
        int length = optJSONArray.length();
        if (length <= 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
            s bVar = new com.qq.reader.module.bookstore.qnative.item.b();
            bVar.parseData(jSONObject2);
            addItem(bVar);
            h.b(getClass().getSimpleName(), getPageCacheKey(), this.mFromBid, ((com.qq.reader.module.bookstore.qnative.item.b) bVar).b(), ((com.qq.reader.module.bookstore.qnative.item.b) bVar).a());
        }
        return true;
    }
}
