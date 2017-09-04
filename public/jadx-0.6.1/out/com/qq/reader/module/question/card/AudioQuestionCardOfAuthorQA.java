package com.qq.reader.module.question.card;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.question.card.view.AudioListTopUserBtmTitleView;
import com.qq.reader.module.question.data.AudioData;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class AudioQuestionCardOfAuthorQA extends a implements a {
    AudioData data;

    public AudioQuestionCardOfAuthorQA(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.audio_question_card_author_qa;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.data = new AudioData();
        this.data.a(jSONObject);
        return true;
    }

    public void attachView() {
        ((AudioListTopUserBtmTitleView) ap.a(getRootView(), R.id.atb_question)).b(this.data);
        TextView textView = (TextView) ap.a(getRootView(), R.id.tv_question_states);
        com.qq.reader.module.question.b.a a = com.qq.reader.module.question.b.a(this.data.a().j());
        textView.setText(a.a);
        textView.setBackgroundResource(a.b);
        textView = (TextView) ap.a(getRootView(), R.id.overdue);
        CharSequence c = this.data.a().b() == 0 ? "" : com.qq.reader.module.question.b.c(this.data.a().b());
        if (TextUtils.isEmpty(c) || !(this.data.a().j() == 0 || this.data.a().j() == 4)) {
            textView.setVisibility(8);
        } else {
            textView.setText(c);
            textView.setVisibility(0);
        }
        ((TextView) ap.a(getRootView(), R.id.answer_price)).setText(String.format(ReaderApplication.getApplicationImp().getResources().getString(R.string.common_qa_cost), new Object[]{Integer.valueOf(this.data.a().i())}));
        getRootView().setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AudioQuestionCardOfAuthorQA a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                o.e(this.a.getEvnetListener().getFromActivity(), this.a.data.a().g());
            }
        });
        View a2 = ap.a(getRootView(), R.id.localstore_adv_divider);
        if (this.mShowIndexOnPage == 1) {
            a2.setVisibility(8);
        } else {
            a2.setVisibility(0);
        }
    }

    public boolean isDataChanged(AudioData audioData) {
        if (!this.data.a().g().equals(audioData.a().g())) {
            return false;
        }
        this.data.a().a(audioData.a().j());
        if (getRootView() != null) {
            attachView();
        }
        return true;
    }
}
