package com.qq.reader.module.bookstore.search.card;

import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class SearchTopicCard extends SearchBaseCard {
    private static final String JSON_KEY_DESC = "desc";
    private static final String JSON_KEY_PICS = "pics";
    private static final String JSON_KEY_TITLE = "title";
    private static final String JSON_KEY_URL = "url";
    private String mDesc;
    private ArrayList<String> mImgUrlList = new ArrayList();
    private boolean mIsListen = false;
    private JSONArray mPics;
    private int mPicsNeed2ShowNum;

    public SearchTopicCard(b bVar, String str, int i) {
        super(bVar, str);
        this.mPicsNeed2ShowNum = i;
    }

    public int getResLayoutId() {
        return R.layout.search_topic_card;
    }

    public void attachView() {
        super.attachView();
        ((TextView) ap.a(getRootView(), R.id.concept_title)).setText(getTitle());
        ImageView imageView = (ImageView) ap.a(getRootView(), R.id.concept_listen_icon);
        int i = (getListen() && this.mPicsNeed2ShowNum == 1) ? 0 : 8;
        imageView.setVisibility(i);
        ((TextView) ap.a(getRootView(), R.id.concept_content)).setText(getDesc());
        imageView = (ImageView) ap.a(getRootView(), R.id.concept_img_0);
        ImageView imageView2 = (ImageView) ap.a(getRootView(), R.id.concept_img_1);
        ImageView imageView3 = (ImageView) ap.a(getRootView(), R.id.concept_img_2);
        int min = Math.min(this.mPicsNeed2ShowNum, this.mImgUrlList.size());
        if (min > 0) {
            imageView.setVisibility(0);
            setImage(imageView, (String) this.mImgUrlList.get(0), null);
        } else {
            imageView.setVisibility(8);
        }
        if (min > 1) {
            imageView2.setVisibility(0);
            setImage(imageView2, (String) this.mImgUrlList.get(1), null);
        } else {
            imageView2.setVisibility(8);
        }
        if (min > 2) {
            imageView3.setVisibility(0);
            setImage(imageView3, (String) this.mImgUrlList.get(2), null);
            return;
        }
        imageView3.setVisibility(8);
    }

    protected void expose() {
        super.expose();
        i.a("event_B171", this.mLogMap, ReaderApplication.getApplicationImp());
        StatisticsManager.a().a("event_B171", this.mLogMap);
    }

    public void doClickedCard() {
        super.doClickedCard();
        i.a("event_B172", this.mLogMap, ReaderApplication.getApplicationImp());
        StatisticsManager.a().a("event_B172", this.mLogMap);
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        super.parseData(jSONObject);
        this.mQURL = jSONObject.optString("qurl");
        this.mDesc = jSONObject.optString("desc");
        this.mTitle = jSONObject.optString("title");
        this.mPics = jSONObject.optJSONArray("pics");
        for (int i = 0; i < this.mPics.length(); i++) {
            this.mImgUrlList.add(this.mPics.optJSONObject(i).optString("url"));
        }
        return true;
    }

    public ArrayList<String> getImg0Url() {
        return this.mImgUrlList;
    }

    public String getDesc() {
        return this.mDesc;
    }

    public boolean getListen() {
        return this.mIsListen;
    }

    public void setListen(boolean z) {
        this.mIsListen = z;
    }
}
