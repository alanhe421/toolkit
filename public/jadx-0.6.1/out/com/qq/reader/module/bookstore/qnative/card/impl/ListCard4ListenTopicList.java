package com.qq.reader.module.bookstore.qnative.card.impl;

import com.qq.reader.module.bookstore.qnative.item.ab;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.tencent.feedback.proguard.R;
import org.json.JSONArray;
import org.json.JSONObject;

public class ListCard4ListenTopicList extends ListCardCommon {
    public ListCard4ListenTopicList(b bVar, String str) {
        super(bVar, str);
    }

    public s createListItem() {
        return new ab();
    }

    public int getCardItemLayoutId() {
        return R.layout.nativestore_listentopiclist_item;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        JSONArray optJSONArray = jSONObject.optJSONArray(ComicStoreExclusiveItemCard.NET_AD_ATTR_ADVS);
        if (optJSONArray == null) {
            return false;
        }
        int length = optJSONArray.length();
        getItemList().clear();
        if (length <= 0) {
            return false;
        }
        while (i < length) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
            s createListItem = createListItem();
            createListItem.parseData(jSONObject2);
            addItem(createListItem);
            i++;
        }
        return true;
    }
}
