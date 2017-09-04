package com.qq.reader.module.question.card;

import android.view.View;
import android.view.View.OnClickListener;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.question.card.view.AudioAuthorStateView;
import com.qq.reader.module.question.data.a;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class FamousAuthorSayRecommendCard extends FamousAuthorSayBaseCard {
    static final String tag = "FamousAuthorSayRecommendCard";
    long adEndTime;
    AudioAuthorStateView mAuthorState;
    a mData;

    public FamousAuthorSayRecommendCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.famous_author_page_recommend_card_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.mData = new a();
        this.mData.a(jSONObject);
        this.mOrginCardJsonOjb = jSONObject;
        this.mDisplayTime = jSONObject.optLong("displayTime");
        setId(jSONObject);
        this.adEndTime = jSONObject.optLong("adEndTime");
        if (this.adEndTime <= 0 || this.adEndTime >= System.currentTimeMillis()) {
            return true;
        }
        return false;
    }

    public void attachView() {
        this.mAuthorState = (AudioAuthorStateView) ap.a(getRootView(), R.id.author_state_layout);
        this.mAuthorState.setType(0);
        this.mAuthorState.a(this.mData);
        onCardShowUpLog();
        OnClickListener anonymousClass1 = new OnClickListener(this) {
            final /* synthetic */ FamousAuthorSayRecommendCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.onToDetailPageUpLog(false);
                o.a(this.a.getEvnetListener().getFromActivity(), this.a.mData.d());
            }
        };
        this.mAuthorState.setButttonListener(anonymousClass1);
        this.mAuthorState.setIconListener(new OnClickListener(this) {
            final /* synthetic */ FamousAuthorSayRecommendCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                o.c(this.a.getEvnetListener().getFromActivity(), String.valueOf(this.a.mData.d()), this.a.mData.a(), this.a.mData.b(), null);
            }
        });
        ap.a(getRootView(), R.id.answer_card_layout).setOnClickListener(anonymousClass1);
    }

    protected void onToDetailPageUpLog(boolean z) {
        super.onToDetailPageUpLog(z);
        i.a("event_D169", null, ReaderApplication.getApplicationImp());
    }

    protected void onCardShowUpLog() {
        i.a("event_D168", null, ReaderApplication.getApplicationImp());
    }
}
