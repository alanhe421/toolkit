package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.BaseAdvCard;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class AdvCard_Line_4 extends BaseAdvCard {
    public AdvCard_Line_4(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_adv_line_4;
    }

    public void attachView() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.localstore_card_adv_btn_0);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.localstore_card_adv_btn_1);
        TextView textView3 = (TextView) ap.a(getRootView(), R.id.localstore_card_adv_btn_2);
        TextView textView4 = (TextView) ap.a(getRootView(), R.id.localstore_card_adv_btn_3);
        if (getItemList().size() > 0) {
            setAdvData((com.qq.reader.module.bookstore.qnative.item.b) getItemList().get(0), textView);
        } else {
            textView.setVisibility(8);
        }
        if (getItemList().size() > 1) {
            setAdvData((com.qq.reader.module.bookstore.qnative.item.b) getItemList().get(1), textView2);
        } else {
            textView2.setVisibility(8);
        }
        if (getItemList().size() > 2) {
            setAdvData((com.qq.reader.module.bookstore.qnative.item.b) getItemList().get(2), textView3);
        } else {
            textView3.setVisibility(8);
        }
        if (getItemList().size() > 3) {
            setAdvData((com.qq.reader.module.bookstore.qnative.item.b) getItemList().get(3), textView4);
        } else {
            textView4.setVisibility(8);
        }
    }

    private void setAdvData(final com.qq.reader.module.bookstore.qnative.item.b bVar, TextView textView) {
        textView.setVisibility(0);
        textView.setText(bVar.c());
        textView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AdvCard_Line_4 b;

            public void onClick(View view) {
                bVar.a(this.b.getEvnetListener());
            }
        });
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        getItemList().clear();
        return super.parseData(jSONObject);
    }
}
