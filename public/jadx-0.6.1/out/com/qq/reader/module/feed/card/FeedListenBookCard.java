package com.qq.reader.module.feed.card;

import android.graphics.Color;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class FeedListenBookCard extends FeedBaseCard {
    private static final String JSON_KEY_BID = "bid";
    private static final String JSON_KEY_COUNT = "allAudios";
    private static final String JSON_KEY_COVER = "cover";
    private static final String JSON_KEY_DESC = "desc";
    private static final String JSON_KEY_ICONCOLOR = "iconColor";
    private static final String JSON_KEY_ICONDEST = "icondesc";
    private static final String JSON_KEY_LFTAG = "lftag";
    private static final String JSON_KEY_LMENDTIME = "lmendtime";
    private static final String JSON_KEY_LMSTARTTIME = "lmstarttime";
    private static final String JSON_KEY_MEDIAID = "mediaBookId";
    private static final String JSON_KEY_PODCAST = "anchor";
    private static final String JSON_KEY_TITLE = "title";
    private String mBookid;
    private int mCount = 0;
    private String mCoverUrl;
    private String mDesc;
    private String mIconColor;
    private String mIconDest;
    private int mLftag;
    private int mMaxAuthorTextLength = 6;
    private String mMediaId;
    private String mPodcaster;
    private long mlimitEndTime;
    private long mlimitStartTime;

    public FeedListenBookCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.concept_listenbook_layout;
    }

    public void attachView() {
        boolean z;
        CharSequence podcaster;
        setImage((ImageView) ap.a(getRootView(), R.id.concept_cover_img), getCoverUrl(), null);
        TextView textView = (TextView) ap.a(getRootView(), R.id.concept_tag_subscript);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.concept_title);
        if (this.mLftag != 0) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis < this.mlimitStartTime || currentTimeMillis > this.mlimitEndTime) {
                z = true;
                if (!z || TextUtils.isEmpty(this.mIconColor) || TextUtils.isEmpty(getIconDest())) {
                    textView.setVisibility(8);
                } else {
                    textView.setVisibility(0);
                    try {
                        ao.a(textView, Color.parseColor(this.mIconColor));
                        textView.setText(getIconDest());
                    } catch (Exception e) {
                        c.e("FeedListenBookCard", e.getMessage());
                        textView.setVisibility(8);
                    }
                }
                textView2.setText(getTitle());
                if (isClickedStatus()) {
                    textView2.setSelected(false);
                } else {
                    textView2.setSelected(true);
                }
                textView = (TextView) ap.a(getRootView(), R.id.concept_content);
                textView2.setText(getTitle());
                textView.setText(getDesc());
                textView = (TextView) ap.a(getRootView(), R.id.concept_podcaster);
                podcaster = getPodcaster();
                if (podcaster.length() > this.mMaxAuthorTextLength) {
                    podcaster = podcaster.substring(0, this.mMaxAuthorTextLength - 1) + "…";
                }
                textView.setText(podcaster);
                textView = (TextView) ap.a(getRootView(), R.id.concept_count);
                if (getCount() <= 0) {
                    textView.setText(String.format(ReaderApplication.getApplicationImp().getString(R.string.chapter_count), new Object[]{Integer.valueOf(getCount())}));
                    textView.setVisibility(0);
                } else {
                    textView.setVisibility(8);
                }
                checkClickEnable();
            }
        }
        z = false;
        if (z) {
        }
        textView.setVisibility(8);
        textView2.setText(getTitle());
        if (isClickedStatus()) {
            textView2.setSelected(false);
        } else {
            textView2.setSelected(true);
        }
        textView = (TextView) ap.a(getRootView(), R.id.concept_content);
        textView2.setText(getTitle());
        textView.setText(getDesc());
        textView = (TextView) ap.a(getRootView(), R.id.concept_podcaster);
        podcaster = getPodcaster();
        if (podcaster.length() > this.mMaxAuthorTextLength) {
            podcaster = podcaster.substring(0, this.mMaxAuthorTextLength - 1) + "…";
        }
        textView.setText(podcaster);
        textView = (TextView) ap.a(getRootView(), R.id.concept_count);
        if (getCount() <= 0) {
            textView.setVisibility(8);
        } else {
            textView.setText(String.format(ReaderApplication.getApplicationImp().getString(R.string.chapter_count), new Object[]{Integer.valueOf(getCount())}));
            textView.setVisibility(0);
        }
        checkClickEnable();
    }

    public void doClickedCard() {
        super.doClickedCard();
        ap.a(getRootView(), R.id.concept_title).setSelected(true);
    }

    public boolean parseData(JSONObject jSONObject) {
        this.mPodcaster = jSONObject.optString(JSON_KEY_PODCAST);
        this.mTitle = jSONObject.optString("title");
        this.mDesc = jSONObject.optString("desc");
        this.mCoverUrl = jSONObject.optString(JSON_KEY_COVER);
        this.mBookid = jSONObject.optString("bid");
        this.mMediaId = jSONObject.optString(JSON_KEY_MEDIAID);
        this.mlimitStartTime = jSONObject.optLong(JSON_KEY_LMSTARTTIME);
        this.mlimitEndTime = jSONObject.optLong(JSON_KEY_LMENDTIME);
        this.mIconDest = jSONObject.optString(JSON_KEY_ICONDEST);
        this.mLftag = jSONObject.optInt(JSON_KEY_LFTAG);
        this.mIconColor = jSONObject.optString(JSON_KEY_ICONCOLOR);
        this.mCount = jSONObject.optInt(JSON_KEY_COUNT, 0);
        return true;
    }

    public String getCoverUrl() {
        if (this.mCoverUrl == null || this.mCoverUrl.trim().equalsIgnoreCase("") || !this.mCoverUrl.toLowerCase().startsWith("http://")) {
            this.mCoverUrl = ao.g(Long.valueOf(this.mBookid).longValue());
        }
        return this.mCoverUrl;
    }

    public String getDesc() {
        return this.mDesc;
    }

    public String getPodcaster() {
        return this.mPodcaster;
    }

    public String getMediaId() {
        return this.mMediaId;
    }

    public String getIconDest() {
        return this.mIconDest;
    }

    public int getCount() {
        return this.mCount;
    }
}
