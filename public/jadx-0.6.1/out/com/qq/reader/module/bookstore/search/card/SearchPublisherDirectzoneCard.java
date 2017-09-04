package com.qq.reader.module.bookstore.search.card;

import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class SearchPublisherDirectzoneCard extends SearchBaseCard {
    private static final String JSON_KEY_TITLE = "title";
    public String mTitle;

    public SearchPublisherDirectzoneCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.search_publisher_card;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        super.parseData(jSONObject);
        this.mTitle = jSONObject.optString("title");
        this.mQURL = jSONObject.optString("qurl");
        return true;
    }

    public void doClickedCard() {
        super.doClickedCard();
        i.a("event_B166", this.mLogMap, ReaderApplication.getApplicationImp());
        StatisticsManager.a().a("event_B166", this.mLogMap);
    }

    public void attachView() {
        super.attachView();
        ((TextView) ap.a(getRootView(), R.id.publisher)).setText(this.mTitle);
    }

    protected void expose() {
        super.expose();
        i.a("event_B165", this.mLogMap, ReaderApplication.getApplicationImp());
        StatisticsManager.a().a("event_B165", this.mLogMap);
    }
}
