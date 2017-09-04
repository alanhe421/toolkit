package com.qq.reader.module.bookstore.qnative.card.impl;

import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class AuthorQAIncomeTotalCard extends a {
    private double amount;

    public AuthorQAIncomeTotalCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.author_qa_income_total_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        if (jSONObject != null) {
            this.amount = jSONObject.optDouble("totalAmount");
            if (this.amount > 0.0d) {
                return true;
            }
        }
        return false;
    }

    public void attachView() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.tv_amount);
        ((TextView) ap.a(getRootView(), R.id.tv_title)).setText(ReaderApplication.getApplicationImp().getResources().getString(R.string.author_qa_income_total_title));
        textView.setText("+" + ((int) this.amount));
    }
}
