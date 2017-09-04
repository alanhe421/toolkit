package com.qq.reader.module.bookstore.qnative.card.impl;

import android.widget.ImageView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.item.n;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class SingleCommentCard extends CommentCard_1 {
    protected n item;

    public SingleCommentCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.comment_card_1_item;
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        this.item = new n();
        this.item.parseData(jSONObject);
        setCardId(this.item.g);
        return true;
    }

    public void attachView() {
        i.a("event_D154", null, ReaderApplication.getApplicationImp());
        showComment(getRootView(), this.item);
        ((ImageView) ap.a(getRootView(), R.id.avatar_img_mask)).setImageResource(R.drawable.translucent);
    }

    public n getItem() {
        return this.item;
    }

    public boolean isDataReady() {
        return true;
    }
}
