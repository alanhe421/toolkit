package com.qq.reader.module.bookstore.qnative.card.impl;

import android.text.TextUtils;
import android.widget.TextView;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class AuthorQASettleBalacnUserInfoCard extends a {
    private String wxNickName;

    public AuthorQASettleBalacnUserInfoCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.author_qa_settle_balance_user_info_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        if (jSONObject != null) {
            this.wxNickName = jSONObject.optString("wxNickName");
            if (!TextUtils.isEmpty(this.wxNickName)) {
                return true;
            }
        }
        return false;
    }

    public void attachView() {
        ((TextView) ap.a(getRootView(), R.id.tv_name)).setText("已绑定微信：" + this.wxNickName);
    }
}
