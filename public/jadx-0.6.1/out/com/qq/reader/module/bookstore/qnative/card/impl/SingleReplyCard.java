package com.qq.reader.module.bookstore.qnative.card.impl;

import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.bookstore.qnative.item.n;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class SingleReplyCard extends CommentCard_1 {
    protected n item;

    public SingleReplyCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.message_interaction_layout;
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        this.item = new n();
        this.item.parseData(jSONObject);
        return true;
    }

    public void attachView() {
        i.a("event_D154", null, ReaderApplication.getApplicationImp());
        showReply(getRootView(), this.item);
    }

    public boolean isDataReady() {
        return true;
    }
}
