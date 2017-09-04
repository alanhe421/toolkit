package com.qq.reader.module.question.card;

import android.view.View;
import android.view.View.OnClickListener;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.question.card.view.AudioAuthorStateView;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class AudioQuestionAuthorCard extends a {
    static final String tag = "AudioQuestionAuthorCard";
    AudioAuthorStateView mAuthorState;
    com.qq.reader.module.question.data.a mData;

    public AudioQuestionAuthorCard(b bVar, String str) {
        super(bVar, str);
        c.a(tag, "new AudioQuestionAuthorCard ");
    }

    public int getResLayoutId() {
        c.a(tag, "getReslayoutId ");
        return R.layout.famous_author_page_recommend_card_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        if (jSONObject == null) {
            return false;
        }
        this.mData = new com.qq.reader.module.question.data.a();
        this.mData.a(jSONObject);
        return true;
    }

    public void attachView() {
        c.a(tag, "attachView ");
        ap.a(getRootView(), R.id.localstore_adv_divider).setVisibility(8);
        this.mAuthorState = (AudioAuthorStateView) ap.a(getRootView(), R.id.author_state_layout);
        this.mAuthorState.setType(2);
        this.mAuthorState.a(this.mData);
        OnClickListener anonymousClass1 = new OnClickListener(this) {
            final /* synthetic */ AudioQuestionAuthorCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                i.a("event_D162", null, ReaderApplication.getApplicationImp());
                o.a(this.a.getEvnetListener().getFromActivity(), this.a.mData.d());
            }
        };
        this.mAuthorState.setButttonListener(anonymousClass1);
        ap.a(getRootView(), R.id.answer_card_layout).setOnClickListener(anonymousClass1);
    }
}
