package com.qq.reader.module.bookstore.qnative.card.impl;

import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class UserCenterEmptyCard extends a {
    public UserCenterEmptyCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.user_center_empty_card_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        return true;
    }

    public void attachView() {
    }
}
