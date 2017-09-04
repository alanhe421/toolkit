package com.qq.reader.module.feed.card;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class FeedTopicCard extends FeedBaseCard {
    private static final String JSON_KEY_DESC = "desc";
    private static final String JSON_KEY_PICS = "pics";
    private static final String JSON_KEY_TITLE = "title";
    private static final String JSON_KEY_URL = "url";
    private String mDesc;
    private ArrayList<String> mImgUrlList = new ArrayList();
    private boolean mIsListen = false;
    private int mPicsNeed2ShowNum;

    public FeedTopicCard(b bVar, String str, int i) {
        super(bVar, str);
        this.mPicsNeed2ShowNum = i;
    }

    public int getResLayoutId() {
        return R.layout.concept_topic_layout;
    }

    public void attachView() {
        int i;
        TextView textView = (TextView) ap.a(getRootView(), R.id.concept_title);
        textView.setText(getTitle());
        if (isClickedStatus()) {
            textView.setSelected(true);
        } else {
            textView.setSelected(false);
        }
        View a = ap.a(getRootView(), R.id.imgcontainer);
        ImageView imageView = (ImageView) ap.a(getRootView(), R.id.concept_listen_icon);
        if (getListen() && this.mPicsNeed2ShowNum == 1) {
            i = 0;
        } else {
            i = 8;
        }
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
        } else {
            imageView3.setVisibility(8);
        }
        if (this.mImgUrlList.size() < 1 || this.mPicsNeed2ShowNum == 0) {
            a.setVisibility(8);
        }
        checkClickEnable();
    }

    public void doClickedCard() {
        super.doClickedCard();
        ap.a(getRootView(), R.id.concept_title).setSelected(true);
    }

    public boolean parseData(JSONObject jSONObject) {
        this.mDesc = jSONObject.optString("desc");
        this.mTitle = jSONObject.optString("title");
        JSONArray optJSONArray = jSONObject.optJSONArray("pics");
        for (int i = 0; i < optJSONArray.length(); i++) {
            this.mImgUrlList.add(optJSONArray.optJSONObject(i).optString("url"));
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

    public boolean isLongClickEnable() {
        return false;
    }
}
