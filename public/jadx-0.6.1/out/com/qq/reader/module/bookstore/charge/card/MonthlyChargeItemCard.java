package com.qq.reader.module.bookstore.charge.card;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.charge.b;
import com.qq.reader.module.bookstore.qnative.c.c;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class MonthlyChargeItemCard extends ChargeBaseCard {
    private b mChargeItem;

    public MonthlyChargeItemCard(com.qq.reader.module.bookstore.qnative.page.b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.chargeitem_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.mChargeItem = new b();
        this.mChargeItem.a(jSONObject);
        return true;
    }

    public void attachView() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.charge_info);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.charge_tag_1);
        TextView textView3 = (TextView) ap.a(getRootView(), R.id.charge_tag_2);
        TextView textView4 = (TextView) ap.a(getRootView(), R.id.charge_price);
        ((TextView) ap.a(getRootView(), R.id.charge_title)).setText(this.mChargeItem.f());
        if (TextUtils.isEmpty(this.mChargeItem.c())) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
            textView.setText(this.mChargeItem.c());
        }
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
        textView4.setText(String.valueOf(this.mChargeItem.a()));
        getRootView().setOnClickListener(new c(this) {
            final /* synthetic */ MonthlyChargeItemCard a;

            {
                this.a = r1;
            }

            public void a(View view) {
                Map hashMap = new HashMap();
                hashMap.put(s.ORIGIN, String.valueOf(this.a.mChargeItem.b()));
                i.a("event_F189", hashMap, ReaderApplication.getApplicationImp());
                Bundle bundle = new Bundle();
                bundle.putString("charge_action", "charge_action_charge");
                bundle.putInt("chargenum", this.a.mChargeItem.b());
                bundle.putInt("chargebookcoincost", this.a.mChargeItem.g());
                bundle.putString("chargenumintro", this.a.mChargeItem.c());
                bundle.putInt("chargeyuan", this.a.mChargeItem.a());
                bundle.putString("servicecode", this.a.mChargeItem.i());
                bundle.putString("productid", this.a.mChargeItem.j());
                this.a.getEvnetListener().doFunction(bundle);
            }
        });
    }
}
