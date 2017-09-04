package com.qq.reader.module.bookstore.qnative.card.impl;

import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONArray;
import org.json.JSONObject;

public class ListCard4BigBook extends ListCardCommon {
    private String mactionTag;

    public ListCard4BigBook(b bVar, String str) {
        super(bVar, str);
        this.mactionTag = new c(bVar.j()).a().getString("KEY_ACTIONTAG");
    }

    public s createListItem() {
        return new com.qq.reader.module.bookstore.qnative.item.c();
    }

    public int getCardItemLayoutId() {
        return R.layout.localstore_card_big_bookinfo;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        JSONArray optJSONArray = jSONObject.optJSONArray("data");
        getItemList().clear();
        if (optJSONArray == null) {
            return false;
        }
        int length = optJSONArray.length();
        if (length <= 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
            jSONObject2.put("actionTag", this.mactionTag);
            com.qq.reader.module.bookstore.qnative.item.c cVar = (com.qq.reader.module.bookstore.qnative.item.c) createListItem();
            cVar.parseData(jSONObject2);
            addItem(cVar);
        }
        return true;
    }
}
