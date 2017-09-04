package com.qq.reader.module.feed.card;

import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class FeedInteractiveTopicCard extends FeedBaseCard {
    private static final String JSON_KEY_COVER = "pic";
    private static final String JSON_KEY_DESC = "content";
    private static final String JSON_KEY_TITLE = "title";
    private static final String JSON_KEY_TOPIC_COUNT = "data";
    private String mCoverUrl;
    private String mDesc;
    private String mTopicCount;

    public FeedInteractiveTopicCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.concept_inactivetopic_layout;
    }

    public void attachView() {
        setImage((ImageView) ap.a(getRootView(), R.id.concept_cover_img), this.mCoverUrl, null);
        TextView textView = (TextView) ap.a(getRootView(), R.id.concept_title);
        textView.setText(getTitle());
        if (isClickedStatus()) {
            textView.setSelected(true);
        } else {
            textView.setSelected(false);
        }
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.concept_content);
        textView.setText(getTitle());
        textView2.setText(getDesc());
        textView = (TextView) ap.a(getRootView(), R.id.concept_topiccount);
        if (ao.s(this.mTopicCount)) {
            textView.setVisibility(4);
        } else {
            textView.setVisibility(0);
            textView.setText(this.mTopicCount);
        }
        checkClickEnable();
    }

    public void doClickedCard() {
        super.doClickedCard();
        ap.a(getRootView(), R.id.concept_title).setSelected(true);
    }

    public boolean parseData(JSONObject jSONObject) {
        this.mTitle = jSONObject.optString("title");
        this.mDesc = jSONObject.optString("content");
        this.mCoverUrl = jSONObject.optString(JSON_KEY_COVER);
        this.mTopicCount = jSONObject.optString(JSON_KEY_TOPIC_COUNT);
        return true;
    }

    public String getDesc() {
        return this.mDesc;
    }

    public String getTopicCount() {
        return this.mTopicCount;
    }
}
