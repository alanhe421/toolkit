package com.qq.reader.module.comic.card;

import com.qq.reader.module.bookstore.qnative.card.impl.ListCardCommon;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.comic.b.a;
import com.tencent.feedback.proguard.R;
import org.json.JSONArray;
import org.json.JSONObject;

public class ComicColumnListCard extends ListCardCommon {
    public ComicColumnListCard(b bVar, String str) {
        super(bVar, str);
    }

    public s createListItem() {
        return new a();
    }

    public int getCardItemLayoutId() {
        return R.layout.comic_column_item;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        JSONArray optJSONArray = jSONObject.optJSONArray("data");
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
