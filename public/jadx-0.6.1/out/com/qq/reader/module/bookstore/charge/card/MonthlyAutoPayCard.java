package com.qq.reader.module.bookstore.charge.card;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.view.web.f;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class MonthlyAutoPayCard extends ChargeBaseCard {
    private String mAutoPayStr;
    private String mVipIntroURL;

    public MonthlyAutoPayCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.charge_autopay;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.mVipIntroURL = jSONObject.optString("yearVipIntroUrl");
        JSONObject optJSONObject = jSONObject.optJSONObject("autoOpenConfig");
        Object obj = null;
        if (optJSONObject != null) {
            obj = optJSONObject.optString("intro");
        }
        if (TextUtils.isEmpty(obj)) {
            this.mAutoPayStr = "自动续费1个月";
        } else {
            this.mAutoPayStr = "自动续费" + obj;
        }
        return true;
    }

    public void attachView() {
        ((TextView) ap.a(getRootView(), R.id.charge_help)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MonthlyAutoPayCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                f fVar = new f(this.a.getEvnetListener().getFromActivity());
                fVar.a(this.a.mVipIntroURL);
                fVar.f_();
            }
        });
        CheckBox checkBox = (CheckBox) ap.a(getRootView(), R.id.charge_autopay);
        checkBox.setText(this.mAutoPayStr);
        checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ MonthlyAutoPayCard a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Bundle bundle = new Bundle();
                bundle.putString("charge_action", "charge_action_autopay");
                bundle.putBoolean("chargeautopay", z);
                this.a.getEvnetListener().doFunction(bundle);
            }
        });
    }
}
