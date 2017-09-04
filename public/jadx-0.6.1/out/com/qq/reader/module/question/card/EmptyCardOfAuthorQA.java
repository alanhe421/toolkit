package com.qq.reader.module.question.card;

import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.BaseEmptyCard;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.view.EmptyView;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class EmptyCardOfAuthorQA extends BaseEmptyCard {
    private final int icon;
    private final String title;

    public EmptyCardOfAuthorQA(b bVar, String str, String str2, int i) {
        super(bVar, str);
        this.title = str2;
        this.icon = i;
        this.mDataState = 1001;
    }

    public int getResLayoutId() {
        return R.layout.audio_answer_clubempyt_card_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        return true;
    }

    public void attachView() {
        EmptyView emptyView = (EmptyView) ap.a(getRootView(), R.id.empty_questions);
        emptyView.a(this.title);
        emptyView.b(this.icon);
        emptyView.a(0);
    }
}
