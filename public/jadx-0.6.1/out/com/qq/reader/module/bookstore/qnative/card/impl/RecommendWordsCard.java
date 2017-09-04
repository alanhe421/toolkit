package com.qq.reader.module.bookstore.qnative.card.impl;

import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.widget.TextView;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class RecommendWordsCard extends a {
    public String mContent = null;

    public RecommendWordsCard(b bVar, String str) {
        super(bVar, str);
    }

    public void attachView() {
        if (this.mContent != null) {
            TextView textView = (TextView) ap.a(getRootView(), R.id.recommend_words_content);
            CharSequence spannableString = new SpannableString("小编寄语：" + this.mContent);
            spannableString.setSpan(new AbsoluteSizeSpan(16, true), 0, 5, 33);
            textView.setText(spannableString);
        }
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_recommend_words;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        if (jSONObject == null) {
            return false;
        }
        this.mContent = jSONObject.optString(MessageKey.MSG_CONTENT);
        return true;
    }
}
