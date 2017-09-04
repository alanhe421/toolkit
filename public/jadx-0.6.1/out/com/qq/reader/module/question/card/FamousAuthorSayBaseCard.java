package com.qq.reader.module.question.card;

import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.question.data.AudioData;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class FamousAuthorSayBaseCard extends AudioComBaseCardDisablePlay {
    protected String mDataId = "";
    protected long mDisplayTime = 0;

    public FamousAuthorSayBaseCard(b bVar, String str) {
        super(bVar, str);
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        try {
            this.mOrginCardJsonOjb = jSONObject;
            this.mDisplayTime = jSONObject.optLong("displayTime");
            if (super.parseData(jSONObject)) {
                setId(jSONObject);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getId() {
        return this.mDataId;
    }

    public long getDisplayTime() {
        return this.mDisplayTime;
    }

    public void setId(JSONObject jSONObject) {
        this.mDataId = String.valueOf(jSONObject.optLong("adId"));
    }

    public boolean isDataChanged(AudioData audioData) {
        if (!this.data.a().g().equals(audioData.a().g())) {
            return false;
        }
        this.data.b().b(audioData.b().n());
        this.data.b().a(audioData.b().l());
        try {
            if (this.mOrginCardJsonOjb instanceof JSONObject) {
                reSaveDataBuild((JSONObject) this.mOrginCardJsonOjb);
                g.a().a(new ReaderDBTask() {
                    public void run() {
                        com.qq.reader.module.question.data.b.b().a(FamousAuthorSayBaseCard.this.getId(), FamousAuthorSayBaseCard.this.getOrginCardJsonOjb().toString());
                    }
                });
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (getRootView() != null) {
            attachView();
        }
        return true;
    }

    protected void onToDetailPageUpLog(boolean z) {
        i.a("event_D175", null, ReaderApplication.getApplicationImp());
    }
}
