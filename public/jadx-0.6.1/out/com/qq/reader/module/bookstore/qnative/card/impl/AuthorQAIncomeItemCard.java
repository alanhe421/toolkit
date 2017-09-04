package com.qq.reader.module.bookstore.qnative.card.impl;

import android.widget.TextView;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class AuthorQAIncomeItemCard extends com.qq.reader.module.bookstore.qnative.card.a {
    private a item;

    private class a extends s {
        public String a;
        public String b;
        final /* synthetic */ AuthorQAIncomeItemCard c;

        private a(AuthorQAIncomeItemCard authorQAIncomeItemCard) {
            this.c = authorQAIncomeItemCard;
        }

        public void parseData(JSONObject jSONObject) {
            this.a = jSONObject.optString(MessageKey.MSG_DATE);
            this.b = jSONObject.optString("amount");
        }
    }

    public AuthorQAIncomeItemCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.author_qa_income_detail_item_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        if (jSONObject == null) {
            return false;
        }
        this.item = new a();
        this.item.parseData(jSONObject);
        return true;
    }

    public void attachView() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.tv_date);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.tv_amount);
        if (this.item != null) {
            textView.setText(this.item.a);
            textView2.setText("+" + this.item.b + "书币");
            return;
        }
        textView.setText("");
        textView2.setText("");
    }
}
