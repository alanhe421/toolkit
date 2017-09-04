package com.qq.reader.module.bookstore.qnative.card.impl;

import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class DefaultCard extends a {
    public DefaultCard(b bVar, String str) {
        super(bVar, str);
    }

    public void attachView() {
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_adv_0;
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        return true;
    }
}
