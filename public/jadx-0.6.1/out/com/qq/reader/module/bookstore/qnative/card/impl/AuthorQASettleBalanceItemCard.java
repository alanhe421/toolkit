package com.qq.reader.module.bookstore.qnative.card.impl;

import android.widget.TextView;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class AuthorQASettleBalanceItemCard extends com.qq.reader.module.bookstore.qnative.card.a {
    private a item;

    private class a extends s {
        public String a;
        public String b;
        public double c;
        final /* synthetic */ AuthorQASettleBalanceItemCard d;

        private a(AuthorQASettleBalanceItemCard authorQASettleBalanceItemCard) {
            this.d = authorQASettleBalanceItemCard;
        }

        public void parseData(JSONObject jSONObject) {
            this.a = jSONObject.optString("intro");
            this.b = jSONObject.optString(MessageKey.MSG_DATE);
            this.c = jSONObject.optDouble("amount", 0.0d);
        }
    }

    public AuthorQASettleBalanceItemCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.author_qa_settle_balance_item_layout;
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
        TextView textView = (TextView) ap.a(getRootView(), R.id.tv_description);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.tv_date);
        TextView textView3 = (TextView) ap.a(getRootView(), R.id.tv_amount);
        if (this.item != null) {
            textView.setText(this.item.a);
            textView2.setText(this.item.b);
            try {
                textView3.setText("+" + String.format("%.2f", new Object[]{Double.valueOf(this.item.c)}));
            } catch (Exception e) {
                c.e("Error", e.getMessage());
            }
        }
    }
}
