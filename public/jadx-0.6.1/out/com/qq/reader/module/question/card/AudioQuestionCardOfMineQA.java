package com.qq.reader.module.question.card;

import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.question.data.AudioData;
import com.tencent.feedback.proguard.R;
import org.json.JSONException;
import org.json.JSONObject;

public class AudioQuestionCardOfMineQA extends a implements a {
    AudioData data;

    public AudioQuestionCardOfMineQA(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.audio_question_card_of_main;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.data = new AudioData();
        this.data.a(jSONObject);
        return true;
    }

    public void attachView() {
        ((TextView) ap.a(getRootView(), R.id.tv_title)).setText(combineTitle());
        TextView textView = (TextView) ap.a(getRootView(), R.id.tv_question_states);
        com.qq.reader.module.question.b.a a = com.qq.reader.module.question.b.a(this.data.a().j());
        textView.setText(a.a);
        textView.setBackgroundResource(a.b);
        ((TextView) ap.a(getRootView(), R.id.question_cost)).setText(String.format(ReaderApplication.getApplicationImp().getResources().getString(R.string.common_qa_cost), new Object[]{Integer.valueOf(this.data.a().i())}));
        c.a(getEvnetListener().getFromActivity()).a(this.data.b().g(), (ImageView) ap.a(getRootView(), R.id.iv_asker), com.qq.reader.common.imageloader.a.a().f());
        getRootView().setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AudioQuestionCardOfMineQA a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                o.e(this.a.getEvnetListener().getFromActivity(), this.a.data.a().g());
            }
        });
    }

    private SpannableStringBuilder combineTitle() {
        Object format = String.format(ReaderApplication.getApplicationImp().getResources().getString(R.string.qa_asker_pre_str), new Object[]{this.data.b().a()});
        CharSequence charSequence = " " + this.data.a().h();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(format);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c201)), 0, spannableStringBuilder.length(), 33);
        spannableStringBuilder.append(charSequence);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c102)), format.length(), spannableStringBuilder.length(), 33);
        return spannableStringBuilder;
    }

    public boolean isDataChanged(AudioData audioData) {
        if (!this.data.a().g().equals(audioData.a().g())) {
            return false;
        }
        this.data.a().a(audioData.a().j());
        try {
            doReSave();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (getRootView() != null) {
            attachView();
        }
        return true;
    }

    public boolean reSaveDataBuild(JSONObject jSONObject) throws JSONException {
        jSONObject.optJSONObject("question").putOpt("status", Integer.valueOf(this.data.a().j()));
        return true;
    }
}
