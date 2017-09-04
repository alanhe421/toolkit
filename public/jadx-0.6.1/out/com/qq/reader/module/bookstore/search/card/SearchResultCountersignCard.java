package com.qq.reader.module.bookstore.search.card;

import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import java.util.Iterator;
import org.json.JSONObject;

public class SearchResultCountersignCard extends SearchBaseCard {
    private static final String JSON_KEY_DESC = "desc";
    public String mDesc;
    public String mLinkUrl;
    public int mType;

    public SearchResultCountersignCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.concept_search_countersign_card_layout;
    }

    public void attachView() {
        super.attachView();
        ((TextView) ap.a(getRootView(), R.id.countersign_content)).setText(this.mDesc);
    }

    protected void expose() {
        super.expose();
        i.a("event_C167", this.mLogMap, ReaderApplication.getApplicationImp());
        StatisticsManager.a().a("event_C167", this.mLogMap);
    }

    public void doClickedCard() {
        super.doClickedCard();
        i.a("event_C168", this.mLogMap, ReaderApplication.getApplicationImp());
        StatisticsManager.a().a("event_C168", this.mLogMap);
    }

    public boolean fillData(JSONObject jSONObject) {
        this.mMsgId = jSONObject.optString("id");
        setCardId(this.mMsgId);
        this.mQURL = jSONObject.optString("qurl");
        try {
            if (parseData(jSONObject)) {
                this.mDataState = 1001;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject(s.STATPARAM_KEY);
            if (optJSONObject != null) {
                Iterator keys = optJSONObject.keys();
                while (keys.hasNext()) {
                    String obj = keys.next().toString();
                    this.mLogMap.put(obj, optJSONObject.optString(obj));
                }
            }
            return true;
        } catch (Exception e) {
            c.a("SearchResultCountersignCard", e.getMessage());
            return false;
        }
    }

    public boolean parseData(JSONObject jSONObject) {
        this.mDesc = jSONObject.optString("desc");
        return true;
    }
}
