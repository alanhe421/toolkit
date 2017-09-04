package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.question.card.view.AudioComItemView;
import com.qq.reader.module.question.data.AudioData;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import org.json.JSONObject;

public class WriterAnswerCard extends a {
    protected static final String JSON_KEY_ACTIVE = "active";
    protected static final String JSON_KEY_COUNT = "qaCount";
    protected static final String JSON_KEY_INFO = "manitoInfo";
    protected static final String JSON_KEY_QA = "qa";
    protected static final String JSON_KEY_TITLE = "title";
    private String buttonText = "";
    private AudioData mAudioData;
    private int mCommentCount = 0;
    private boolean mIsActive = false;

    public WriterAnswerCard(b bVar, String str) {
        super(bVar, str);
    }

    public void attachView() {
        LinearLayout linearLayout = (LinearLayout) ap.a(getRootView(), R.id.answer_card_layout);
        if (this.mAudioData != null) {
            linearLayout.setVisibility(0);
            CardTitle cardTitle = (CardTitle) ap.a(getRootView(), R.id.answer_title_layout);
            cardTitle.setVisibility(0);
            cardTitle.setCardTitle(37, this.mShowTitle, this.mCommentCount == 0 ? "" : this.mCommentCount + "个", null);
            TextView textView = (TextView) ap.a(getRootView(), R.id.ask_button_bottom);
            textView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ WriterAnswerCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    Object hashMap = new HashMap();
                    if (this.a.buttonText.equals(ReaderApplication.getApplicationImp().getString(R.string.audio_quiz_pay_submit))) {
                        hashMap.put(s.ORIGIN, "1");
                    } else {
                        hashMap.put(s.ORIGIN, "2");
                    }
                    i.a("event_D192", hashMap, ReaderApplication.getApplicationImp());
                    o.a(this.a.getEvnetListener().getFromActivity(), this.a.mAudioData.b().f());
                }
            });
            textView.setText(this.buttonText);
            textView.setVisibility(0);
            if (!this.mIsActive && this.mCommentCount <= 1) {
                textView.setVisibility(8);
            }
            AudioComItemView audioComItemView = (AudioComItemView) ap.a(getRootView(), R.id.answer_body_layout);
            audioComItemView.setType(0);
            audioComItemView.a(this.mAudioData);
            audioComItemView.setSupportPlay(false);
            audioComItemView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ WriterAnswerCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    i.a("event_D194", null, ReaderApplication.getApplicationImp());
                    com.qq.reader.module.question.b.a(this.a.getEvnetListener().getFromActivity(), this.a.mAudioData, false);
                }
            });
            audioComItemView.setOnPlayBtnClickListener(new OnClickListener(this) {
                final /* synthetic */ WriterAnswerCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    i.a("event_D194", null, ReaderApplication.getApplicationImp());
                    com.qq.reader.module.question.b.a(this.a.getEvnetListener().getFromActivity(), this.a.mAudioData, true);
                }
            });
            i.a("event_D193", null, ReaderApplication.getApplicationImp());
        }
    }

    public int getResLayoutId() {
        return R.layout.author_page_writer_answer_layout;
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        if (jSONObject != null) {
            this.mServerTitle = jSONObject.optString("title");
            JSONObject optJSONObject = jSONObject.optJSONObject(JSON_KEY_INFO);
            if (optJSONObject != null) {
                this.mCommentCount = optJSONObject.optInt(JSON_KEY_COUNT);
                this.mIsActive = optJSONObject.optInt(JSON_KEY_ACTIVE) == 1;
                this.buttonText = this.mCommentCount > 1 ? ReaderApplication.getApplicationImp().getString(R.string.audio_quiz_pay_submit_all) : ReaderApplication.getApplicationImp().getString(R.string.audio_quiz_pay_submit);
                JSONObject optJSONObject2 = optJSONObject.optJSONObject(JSON_KEY_QA);
                if (optJSONObject2 != null) {
                    this.mAudioData = new AudioData();
                    this.mAudioData.a(optJSONObject2);
                    return true;
                }
            }
        }
        return false;
    }
}
