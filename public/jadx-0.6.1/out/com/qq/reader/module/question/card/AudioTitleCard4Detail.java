package com.qq.reader.module.question.card;

import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.card.impl.CardTitle;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

@Deprecated
public class AudioTitleCard4Detail extends a {
    private String mContent;
    private String mTitle;

    public AudioTitleCard4Detail(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.audio_question_titlecard4detail;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.mTitle = jSONObject.optString("title");
        this.mContent = jSONObject.optString(MessageKey.MSG_CONTENT);
        return true;
    }

    public void attachView() {
        ((CardTitle) getRootView()).setCardTitle(37, this.mTitle, this.mContent, "");
    }
}
