package com.qq.reader.module.qmessage.data.impl;

import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class MessageDividerCard extends MessageBaseCard {
    public MessageDividerCard(b bVar) {
        super(bVar);
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        return false;
    }

    public int getResLayoutId() {
        return R.layout.message_divider_layout;
    }

    public void attachView() {
        getRootView().setClickable(false);
    }
}
