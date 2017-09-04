package com.qq.reader.module.feed.card;

import android.util.TimingLogger;
import android.view.View;
import android.view.View.OnClickListener;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import com.qq.reader.module.question.card.a;
import com.qq.reader.module.question.card.view.AudioComItemView;
import com.qq.reader.module.question.data.AudioData;
import com.tencent.feedback.proguard.R;
import org.json.JSONException;
import org.json.JSONObject;

public class FeedQuestionCard extends FeedBaseCard implements a {
    protected AudioData data;
    int viewType = 1;

    public FeedQuestionCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.audio_com_card_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        JSONObject optJSONObject = jSONObject.optJSONObject("qanode");
        if (optJSONObject == null) {
            return false;
        }
        this.data = new AudioData();
        this.data.a(optJSONObject);
        setCardId(this.data.a().g());
        return true;
    }

    public void config(int i) {
        this.viewType = i;
    }

    public void attachView() {
        TimingLogger timingLogger;
        if (com.qq.reader.appconfig.b.a) {
            timingLogger = new TimingLogger("timing", "begin");
        } else {
            timingLogger = null;
        }
        AudioComItemView audioComItemView = (AudioComItemView) ap.a(getRootView(), R.id.audio_view);
        audioComItemView.setSupportPlay(false);
        audioComItemView.setType(this.viewType);
        audioComItemView.a(this.data);
        onCardShowUpLog();
        audioComItemView.setOnPlayBtnClickListener(new OnClickListener(this) {
            final /* synthetic */ FeedQuestionCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.doClickAction(true);
                this.a.onToDetailPageUpLog(true);
            }
        });
        if (com.qq.reader.appconfig.b.a && timingLogger != null) {
            timingLogger.addSplit("attachView");
            timingLogger.dumpToLog();
        }
    }

    protected void doClickAction(boolean z) {
        com.qq.reader.module.question.b.a(getEvnetListener().getFromActivity(), this.data, z);
    }

    protected void onToDetailPageUpLog(boolean z) {
    }

    protected void onCardShowUpLog() {
    }

    public boolean isDataChanged(AudioData audioData) {
        if (audioData != null) {
            if (this.data.a().g().equals(audioData.a().g())) {
                this.data.b().b(audioData.b().n());
                this.data.b().a(audioData.b().l());
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
        }
        return false;
    }

    public boolean reSaveDataBuild(JSONObject jSONObject) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject("answer");
        optJSONObject.putOpt("listenCount", Integer.valueOf(this.data.b().l()));
        optJSONObject.putOpt("purchased", Integer.valueOf(this.data.b().n()));
        return true;
    }

    public AudioData getAudioData() {
        return this.data;
    }
}
