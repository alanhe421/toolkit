package com.qq.reader.module.bookstore.charge.card;

import android.text.TextUtils;
import android.widget.TextView;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class ChargeAdvTextCard extends ChargeBaseCard {
    private final String TAG = "ChargeAdvTextCard";
    private String mAdContent;

    public ChargeAdvTextCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.charge_adv_txt;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.mAdContent = jSONObject.optString("adContent");
        if (!TextUtils.isEmpty(this.mAdContent) && this.mAdContent.length() > 18) {
            this.mAdContent = this.mAdContent.substring(0, 18);
            this.mAdContent += "…";
        }
        if (TextUtils.isEmpty(this.mAdContent)) {
            return false;
        }
        return true;
    }

    public void attachView() {
        ((TextView) ap.a(getRootView(), R.id.charge_adv_txt)).setText(this.mAdContent);
    }
}
