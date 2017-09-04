package com.qq.reader.module.feed.card;

import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import org.json.JSONObject;

public class FeedResidentColumnCard extends FeedBaseCard {
    public FeedResidentColumnCard(b bVar, String str) {
        super(bVar, str);
    }

    public void attachView() {
    }

    public int getResLayoutId() {
        return -1;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        return super.parseData(jSONObject);
    }
}
