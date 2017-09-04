package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.View;
import android.view.View.OnClickListener;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.card.view.CardMoreView;
import com.qq.reader.module.question.card.view.AudioComItemView;
import com.qq.reader.module.question.data.AudioData;
import com.tencent.feedback.proguard.R;
import org.json.JSONException;
import org.json.JSONObject;

public class UserCenterHisQuestionCard extends a implements com.qq.reader.module.question.card.a {
    static final String JSONKEY_QUESTION_LIST = "hisQuestionList";
    int bookCoinCount = 10;
    int listenCount = 100;
    AudioData mAudioData;
    private int mInterActionCount = 0;
    private int mTotalCommentCount = 0;
    int questionCount = 1000;
    long userId = 0;

    public UserCenterHisQuestionCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.user_center_fenda_enter_guest_item_card_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.mTotalCommentCount = jSONObject.optInt("totalCount");
        this.mInterActionCount = jSONObject.optInt("contentCount");
        this.questionCount = jSONObject.optInt("hisQuestionCount");
        this.listenCount = jSONObject.optInt("qListenCount");
        this.bookCoinCount = jSONObject.optInt("qListenReward");
        this.userId = jSONObject.optLong("userId");
        this.mAudioData = new AudioData();
        this.mAudioData.a(jSONObject.optJSONArray(JSONKEY_QUESTION_LIST).optJSONObject(0));
        return true;
    }

    public void attachView() {
        CardTitle cardTitle = (CardTitle) ap.a(getRootView(), R.id.guest_item_title);
        AudioComItemView audioComItemView = (AudioComItemView) ap.a(getRootView(), R.id.guest_audio_item);
        cardTitle.setCardTitle(37, ReaderApplication.getApplicationImp().getString(R.string.usercenter_fenda_his_questions));
        cardTitle.setCardIntroduction(this.questionCount + "个");
        cardTitle.setCardIntroductionBottom(null);
        if (this.listenCount > 0) {
            cardTitle.setCardIntroductionRightEnd(String.format(ReaderApplication.getApplicationImp().getString(R.string.usercenter_fenda_questions_state_ticket), new Object[]{Integer.valueOf(this.listenCount), Integer.valueOf(this.bookCoinCount)}));
        } else {
            cardTitle.setCardIntroductionRightEnd("");
        }
        audioComItemView.setType(1);
        audioComItemView.a(this.mAudioData);
        audioComItemView.setSupportPlay(false);
        CardMoreView cardMoreView = (CardMoreView) ap.a(getRootView(), R.id.more_v);
        if (this.questionCount > 1) {
            cardMoreView.setText("查看全部提问");
            cardMoreView.setVisibility(0);
            cardMoreView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ UserCenterHisQuestionCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    o.c(this.a.getEvnetListener().getFromActivity(), String.valueOf(this.a.userId), "2", ReaderApplication.getApplicationImp().getString(R.string.usercenter_fenda_his_questions));
                }
            });
        } else {
            cardMoreView.setVisibility(8);
        }
        audioComItemView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UserCenterHisQuestionCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                com.qq.reader.module.question.b.a(this.a.getEvnetListener().getFromActivity(), this.a.mAudioData, false);
            }
        });
        audioComItemView.setOnPlayBtnClickListener(new OnClickListener(this) {
            final /* synthetic */ UserCenterHisQuestionCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                com.qq.reader.module.question.b.a(this.a.getEvnetListener().getFromActivity(), this.a.mAudioData, true);
            }
        });
        View a = ap.a(getRootView(), R.id.localstore_adv_divider);
        if (this.mInterActionCount == 0 && this.mTotalCommentCount == 0) {
            a.setVisibility(8);
        } else {
            a.setVisibility(0);
        }
    }

    public boolean isDataChanged(AudioData audioData) throws Exception {
        if (audioData.a().g().equals(this.mAudioData.a().g())) {
            this.mAudioData.b().b(audioData.b().n());
            this.mAudioData.b().a(audioData.b().l());
            try {
                doReSave();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (getRootView() != null) {
                attachView();
            }
        }
        return false;
    }

    public boolean reSaveDataBuild(JSONObject jSONObject) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONArray(JSONKEY_QUESTION_LIST).optJSONObject(0);
        int n = this.mAudioData.b().n();
        int l = this.mAudioData.b().l();
        optJSONObject = optJSONObject.optJSONObject("answer");
        optJSONObject.putOpt("purchased", Integer.valueOf(n));
        optJSONObject.putOpt("listenCount", Integer.valueOf(l));
        return true;
    }
}
