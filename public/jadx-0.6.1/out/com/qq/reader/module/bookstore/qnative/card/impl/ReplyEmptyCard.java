package com.qq.reader.module.bookstore.qnative.card.impl;

import com.qq.reader.module.bookstore.qnative.card.BaseEmptyCard;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class ReplyEmptyCard extends BaseEmptyCard {
    public ReplyEmptyCard(b bVar, String str) {
        super(bVar, str);
        setCardId(str);
        this.mDataState = 1001;
    }

    public int getResLayoutId() {
        return R.layout.reply_empty_card_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        return false;
    }

    public void attachView() {
    }
}
