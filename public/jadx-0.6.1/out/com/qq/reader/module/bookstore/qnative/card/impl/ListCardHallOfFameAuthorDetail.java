package com.qq.reader.module.bookstore.qnative.card.impl;

import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.item.x;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONArray;
import org.json.JSONObject;

public class ListCardHallOfFameAuthorDetail extends ListCardCommon {
    public ListCardHallOfFameAuthorDetail(b bVar, String str) {
        super(bVar, str);
    }

    public s createListItem() {
        return new x();
    }

    public int getCardItemLayoutId() {
        return R.layout.localstore_card_author_right;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        JSONArray optJSONArray = jSONObject.optJSONArray("flames");
        getItemList().clear();
        if (!"".equalsIgnoreCase(jSONObject.optString("intro"))) {
            s createListItem = createListItem();
            createListItem.parseData(jSONObject);
            addItem(createListItem);
        }
        if (optJSONArray != null) {
            int length = optJSONArray.length();
            if (length > 0) {
                while (i < length) {
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    s createListItem2 = createListItem();
                    createListItem2.parseData(jSONObject);
                    createListItem2.parseData(jSONObject2);
                    addItem(createListItem2);
                    i++;
                }
                return true;
            }
        }
        return false;
    }
}
