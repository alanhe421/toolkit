package com.qq.reader.module.qmessage.data.impl;

import com.qq.reader.module.bookstore.qnative.page.b;
import org.json.JSONObject;

public class MessageNewUserHelpCard extends MessageBaseCard {
    public MessageNewUserHelpCard(b bVar) {
        super(bVar);
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        return false;
    }

    public void attachView() {
    }
}
