package com.qq.reader.module.question.card;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.login.c;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.k;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.question.card.view.AudioListTopUserBtmTitleView;
import com.qq.reader.module.question.data.AudioData;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class AudioQuestionWaiting4AnswerCard extends a {
    private String eavesDroppingMsg;
    private AudioData mAudioData;

    public AudioQuestionWaiting4AnswerCard(b bVar) {
        super(bVar, "");
    }

    public int getResLayoutId() {
        return R.layout.audio_question_waiting4answer_card;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.mAudioData = new AudioData();
        this.mAudioData.a(jSONObject);
        if (this.mAudioData.a() == null || this.mAudioData.a().j() == -1 || this.mAudioData.b() == null) {
            return false;
        }
        return true;
    }

    public void attachView() {
        long f = this.mAudioData.b().f();
        long l = c.c().l(ReaderApplication.getApplicationImp());
        ((AudioListTopUserBtmTitleView) ap.a(getRootView(), R.id.audio_question_item)).a(this.mAudioData);
        TextView textView = (TextView) ap.a(getRootView(), R.id.audio_question_report);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.audio_question_refuse);
        TextView textView3 = (TextView) ap.a(getRootView(), R.id.audio_question_pay);
        TextView textView4 = (TextView) ap.a(getRootView(), R.id.audio_tip);
        TextView textView5 = (TextView) ap.a(getRootView(), R.id.audio_exprittime);
        if (this.mAudioData.a().j() == 0 && f == l) {
            textView.setVisibility(0);
            textView2.setVisibility(0);
            textView4.setVisibility(0);
            textView4.setText(this.eavesDroppingMsg);
            textView5.setVisibility(0);
            textView2.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ AudioQuestionWaiting4AnswerCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("audio_action", "audio_detail_refuse");
                    this.a.getEvnetListener().doFunction(bundle);
                }
            });
            textView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ AudioQuestionWaiting4AnswerCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("audio_action", "audio_detail_report");
                    this.a.getEvnetListener().doFunction(bundle);
                }
            });
            textView5.setText(getEvnetListener().getFromActivity().getString(R.string.audio_question_autorefusetime, new Object[]{k.e(this.mAudioData.a().b())}));
        } else {
            textView.setVisibility(8);
            textView2.setVisibility(8);
            textView4.setVisibility(8);
            textView5.setVisibility(8);
        }
        textView3.setText(this.mAudioData.a().i() + "书币");
    }

    public void setEavesDroppingMsg(String str) {
        this.eavesDroppingMsg = str;
    }

    public AudioData getAudioData() {
        return this.mAudioData;
    }
}
