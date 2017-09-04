package com.qq.reader.module.question.card;

import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.qurl.c;
import org.json.JSONObject;

public class FamousAuthorSayAdvCard extends FamousAuthorSayBaseCard {
    static final String tag = "FamousAuthorSayAdvCard";
    long adEndTime;
    int adType;
    String url = "";

    public FamousAuthorSayAdvCard(b bVar, String str) {
        super(bVar, str);
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        boolean parseData = super.parseData(jSONObject);
        this.adType = jSONObject.optInt("adType");
        this.url = jSONObject.optString("qurl");
        this.adEndTime = jSONObject.optLong("adEndTime");
        if (this.adEndTime <= 0 || this.adEndTime >= System.currentTimeMillis()) {
            return parseData;
        }
        return false;
    }

    public void attachView() {
        config(1);
        super.attachView();
    }

    protected void doClickAction(boolean z) {
        if (this.adType == 2) {
            try {
                c.a(getEvnetListener().getFromActivity(), this.url);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        super.doClickAction(z);
    }

    protected void onToDetailPageUpLog(boolean z) {
        super.onToDetailPageUpLog(z);
        if (this.mType.equals("ad")) {
            i.a("event_D165", null, ReaderApplication.getApplicationImp());
        } else if (this.mType.equals("recommend")) {
            i.a("event_D171", null, ReaderApplication.getApplicationImp());
        }
    }

    protected void onCardShowUpLog() {
        if (this.mType.equals("ad")) {
            i.a("event_D164", null, ReaderApplication.getApplicationImp());
        } else if (this.mType.equals("recommend")) {
            i.a("event_D170", null, ReaderApplication.getApplicationImp());
        }
    }
}
