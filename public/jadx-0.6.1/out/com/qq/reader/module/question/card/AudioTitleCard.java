package com.qq.reader.module.question.card;

import android.widget.TextView;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class AudioTitleCard extends a {
    private String mContent;
    private String mTitle;

    public AudioTitleCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.audio_question_titlecard;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.mTitle = jSONObject.optString("title");
        this.mContent = jSONObject.optString(MessageKey.MSG_CONTENT);
        return true;
    }

    public void attachView() {
        ((TextView) ap.a(getRootView(), R.id.cardtitle_title)).setText(this.mTitle);
        ((TextView) ap.a(getRootView(), R.id.cardtitle_introduction)).setText(this.mContent);
    }
}
