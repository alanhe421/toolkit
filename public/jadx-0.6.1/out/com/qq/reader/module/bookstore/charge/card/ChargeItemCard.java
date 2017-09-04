package com.qq.reader.module.bookstore.charge.card;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.charge.a;
import com.qq.reader.module.bookstore.qnative.c.c;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class ChargeItemCard extends ChargeBaseCard {
    private boolean hideDivider = false;
    private a mChargeItem;

    public ChargeItemCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.chargeitem_layout;
    }

    public void setChargeItem(a aVar) {
        this.mChargeItem = aVar;
        this.mDataState = 1001;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        return true;
    }

    public void attachView() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.charge_title);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.charge_info);
        TextView textView3 = (TextView) ap.a(getRootView(), R.id.charge_tag_1);
        TextView textView4 = (TextView) ap.a(getRootView(), R.id.charge_tag_2);
        TextView textView5 = (TextView) ap.a(getRootView(), R.id.charge_price);
        View a = ap.a(getRootView(), R.id.divider);
        textView.setText(this.mChargeItem.c() + "书币");
        if (TextUtils.isEmpty(this.mChargeItem.d())) {
            textView2.setVisibility(8);
        } else {
            textView2.setVisibility(0);
            textView2.setText(this.mChargeItem.d());
        }
        if (TextUtils.isEmpty(this.mChargeItem.e())) {
            textView3.setVisibility(8);
        } else {
            textView3.setVisibility(0);
            textView3.setText(this.mChargeItem.e());
        }
        textView4.setVisibility(8);
        textView5.setText(this.mChargeItem.b());
        if (this.hideDivider) {
            a.setVisibility(8);
        } else {
            a.setVisibility(0);
        }
        getRootView().setOnClickListener(new c(this) {
            final /* synthetic */ ChargeItemCard a;

            {
                this.a = r1;
            }

            public void a(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("charge_action", "charge_action_charge");
                bundle.putInt("chargenum", this.a.mChargeItem.c());
                bundle.putString("productId", this.a.mChargeItem.f());
                this.a.getEvnetListener().doFunction(bundle);
            }
        });
    }

    public boolean isHideDivider() {
        return this.hideDivider;
    }

    public void setHideDivider(boolean z) {
        this.hideDivider = z;
    }
}
