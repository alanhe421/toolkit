package com.qq.reader.module.bookstore.qnative.card.impl;

import android.text.TextUtils;
import android.widget.TextView;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class AudioBookDetailIntroCard extends a {
    private String intro;

    public AudioBookDetailIntroCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.audio_book_detail_intro_card_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        JSONObject optJSONObject = jSONObject.optJSONObject("audio");
        if (optJSONObject != null) {
            if (optJSONObject.optLong("cpid", 0) == 2000000804) {
                return false;
            }
            this.intro = optJSONObject.optString("intro");
        }
        if (TextUtils.isEmpty(this.intro)) {
            return false;
        }
        return true;
    }

    public void attachView() {
        ((TextView) ap.a(getRootView(), R.id.tv_intro)).setText(this.intro);
    }
}
