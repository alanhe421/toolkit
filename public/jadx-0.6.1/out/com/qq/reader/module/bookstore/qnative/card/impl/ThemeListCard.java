package com.qq.reader.module.bookstore.qnative.card.impl;

import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class ThemeListCard extends a {
    public ThemeListCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.theme_list_ui;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        return true;
    }

    public void attachView() {
    }
}
