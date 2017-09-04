package com.qq.reader.module.bookstore.search.card;

import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class SearchZoneDirectzoneCard extends SearchBaseCard {
    private String mTitle;

    public SearchZoneDirectzoneCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.search_zone_card;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        super.parseData(jSONObject);
        this.mTitle = jSONObject.optString("title");
        this.mQURL = jSONObject.optString("qurl");
        return true;
    }

    public void doClickedCard() {
        super.doClickedCard();
        i.a("event_B174", this.mLogMap, ReaderApplication.getApplicationImp());
        StatisticsManager.a().a("event_B174", this.mLogMap);
    }

    public void attachView() {
        super.attachView();
        TextView textView = (TextView) ap.a(getRootView(), R.id.search_zone_title);
        ImageView imageView = (ImageView) ap.a(getRootView(), R.id.search_zone_icon);
        if ("免费".equals(this.mTitle)) {
            textView.setText(R.string.directzone_free);
            imageView.setImageResource(R.drawable.search_directzone_free);
        } else if ("包月".equals(this.mTitle)) {
            textView.setText(R.string.directzone_vip);
            imageView.setImageResource(R.drawable.search_directzone_vip);
        } else {
            textView.setText(R.string.directzone_audio);
            imageView.setImageResource(R.drawable.search_directzone_audio);
        }
    }

    protected void expose() {
        super.expose();
        i.a("event_B173", this.mLogMap, ReaderApplication.getApplicationImp());
        StatisticsManager.a().a("event_B173", this.mLogMap);
    }
}
