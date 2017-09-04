package com.qq.reader.module.bookstore.charge.card;

import android.text.TextUtils;
import android.widget.TextView;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class ChargeOpenVipItemCard extends ChargeBaseCard {
    private String mChargeInfo;
    private int mChargePrice;
    private String mChargeTitle;
    private String mTag1;
    private String mTag2;

    public ChargeOpenVipItemCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.chargeitem_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.mChargeInfo = jSONObject.optString("chargeTitle");
        this.mChargeInfo = jSONObject.optString("mChargeInfo");
        this.mTag1 = jSONObject.optString("mTag1");
        this.mTag2 = jSONObject.optString("mTag2");
        return true;
    }

    public void attachView() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.charge_info);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.charge_tag_1);
        TextView textView3 = (TextView) ap.a(getRootView(), R.id.charge_tag_2);
        TextView textView4 = (TextView) ap.a(getRootView(), R.id.charge_price);
        ((TextView) ap.a(getRootView(), R.id.charge_title)).setText(this.mChargeInfo);
        textView.setText(this.mChargeInfo);
        if (TextUtils.isEmpty(this.mTag1)) {
            textView2.setVisibility(8);
        } else {
            textView2.setVisibility(0);
            textView2.setText(this.mTag1);
        }
        if (TextUtils.isEmpty(this.mTag2)) {
            textView3.setVisibility(8);
        } else {
            textView3.setVisibility(0);
            textView3.setText(this.mTag2);
        }
        textView4.setText(String.valueOf(this.mChargePrice));
    }
}
