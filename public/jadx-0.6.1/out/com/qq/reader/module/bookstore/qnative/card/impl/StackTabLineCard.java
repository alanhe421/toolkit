package com.qq.reader.module.bookstore.qnative.card.impl;

import android.widget.TextView;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class StackTabLineCard extends a {
    private String mContent = null;

    public StackTabLineCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.localbookstore_stackline_item;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.mContent = jSONObject.optString("title");
        return true;
    }

    public void attachView() {
        ((TextView) ap.a(getRootView(), R.id.linecontent)).setText(this.mContent);
    }
}
