package com.qq.reader.module.bookstore.qnative.card.impl;

import com.qq.reader.module.bookstore.qnative.card.impl.AuthorNewsCard.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class SingleAuthorNewsCard extends AuthorNewsCard {
    a item;

    public SingleAuthorNewsCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.author_news_item_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.item = new a();
        this.item.parseData(jSONObject);
        return true;
    }

    public void attachView() {
        fillItemUI(getRootView(), this.item);
    }
}
