package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.c.a;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.j;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreConfigDetailActivity;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import com.qq.reader.view.CustomTailIconTextView;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;
import tencent.tls.platform.SigType;

public class SearchResultBookCard extends FeedBaseCard {
    private static final String JSON_KEY_AUTH = "auth";
    private static final String JSON_KEY_AUTHOR = "author";
    public static final String JSON_KEY_BID = "bid";
    private static final String JSON_KEY_CARDICON = "cardicon";
    private static final String JSON_KEY_CATEGORYNAME = "categoryShortName";
    private static final String JSON_KEY_CATEL2NAME = "catel2name";
    private static final String JSON_KEY_CATEL3NAME = "catel3name";
    private static final String JSON_KEY_CATETAG = "tag";
    private static final String JSON_KEY_COVER = "cover";
    private static final String JSON_KEY_DESC = "intro";
    private static final String JSON_KEY_FINISHED = "finished";
    private static final String JSON_KEY_ICONCOLOR = "iconColor";
    private static final String JSON_KEY_ICONDEST = "icondesc";
    private static final String JSON_KEY_LFTAG = "lftag";
    private static final String JSON_KEY_LMENDTIME = "lmendtime";
    private static final String JSON_KEY_LMSTARTTIME = "lmstarttime";
    private static final String JSON_KEY_RANK_VALUE = "rankValue";
    private static final String JSON_KEY_STAT_ALGINFO = "alg_info";
    private static final String JSON_KEY_STAT_PARAMS = "stat_params";
    private static final String JSON_KEY_STAT_TAGS = "tags";
    private static final String JSON_KEY_TITLE = "title";
    private static final String JSON_KEY_WORDCOUNT = "totalWords";
    private String mAuthor;
    private String mBookid;
    private int mCardicon;
    private String mCateL2Name;
    private String mCateL3Name;
    private String mCateTag;
    private String mCategoryName;
    private String mCoverUrl;
    private String mDesc;
    private int mFinished;
    private String mIconColor;
    private String mIconDest;
    private int mLftag;
    private int mMaxAuthorTextLength = 6;
    private long mRankValue;
    private String mStatAlgInfo;
    private JSONObject mStatParams;
    private String mStatTags;
    private long mWordcount;
    private long mlimitEndTime;
    private long mlimitStartTime;

    public SearchResultBookCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.concept_bookitem_layout;
    }

    public void attachView() {
        getRootView().setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SearchResultBookCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                long longValue;
                try {
                    longValue = Long.valueOf(this.a.mBookid).longValue();
                } catch (Exception e) {
                    longValue = 0;
                }
                if (longValue > 0) {
                    Bundle bundle = new Bundle();
                    bundle.putString("KEY_JUMP_PAGENAME", "DetailPage");
                    bundle.putLong("URL_BUILD_PERE_BOOK_ID", longValue);
                    Intent intent = new Intent();
                    intent.setClass(ReaderApplication.getApplicationImp(), NativeBookStoreConfigDetailActivity.class);
                    intent.putExtras(bundle);
                    intent.setFlags(SigType.TLS);
                    ReaderApplication.getApplicationImp().startActivity(intent);
                }
            }
        });
        setImage((ImageView) ap.a(getRootView(), R.id.concept_cover_img), getCoverUrl(), null);
        TextView textView = (TextView) ap.a(getRootView(), R.id.concept_author);
        CharSequence author = getAuthor();
        if (author.length() > this.mMaxAuthorTextLength) {
            author = author.substring(0, this.mMaxAuthorTextLength - 1) + "…";
        }
        textView.setText(author);
        ((TextView) ap.a(getRootView(), R.id.concept_tag_subscript)).setVisibility(8);
        textView = (TextView) ap.a(getRootView(), R.id.concept_title);
        textView.setText(getTitle());
        if (isClickedStatus()) {
            textView.setSelected(true);
        } else {
            textView.setSelected(false);
        }
        CustomTailIconTextView customTailIconTextView = (CustomTailIconTextView) ap.a(getRootView(), R.id.feed_title_container);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.concept_content);
        textView.setText(getTitle());
        if (TextUtils.isEmpty(getDesc())) {
            textView2.setVisibility(8);
            textView.setMaxLines(2);
            customTailIconTextView.setMaxlines(2);
        } else {
            textView.setMaxLines(1);
            customTailIconTextView.setMaxlines(1);
            textView2.setVisibility(0);
            textView2.setText(getDesc());
        }
        textView = (TextView) ap.a(getRootView(), R.id.concept_tag_1);
        if (TextUtils.isEmpty(getCateL2Name())) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
            textView.setText(getCateL2Name());
        }
        textView = (TextView) ap.a(getRootView(), R.id.concept_tag_2);
        if (("null".equals(getCateTag()) || TextUtils.isEmpty(getCateTag())) && ("null".equals(getCateL3Name()) || TextUtils.isEmpty(getCateL3Name()))) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
            if (!TextUtils.isEmpty(getCateTag()) && !"null".equals(getCateTag())) {
                textView.setText(getCateTag());
            } else if (!(TextUtils.isEmpty(getCateL3Name()) || "null".equals(getCateL3Name()))) {
                textView.setText(getCateL3Name());
            }
        }
        ((TextView) ap.a(getRootView(), R.id.concept_tag_3)).setText(getSortColumn(this.mRankValue));
        ap.a(getRootView(), R.id.concept_cover_tag).setVisibility(8);
    }

    private String getSortColumn(long j) {
        switch (d.aR(getRootView().getContext())) {
            case 1:
                return j.a(j) + "人气";
            case 3:
                return j.b(1000 * j);
            case 5:
                return j.a(j) + "收藏";
            case 7:
                return j.a(j) + "热销";
            case 9:
                return j.a(j) + "字";
            default:
                return "";
        }
    }

    public void doClickedCard() {
        super.doClickedCard();
        ap.a(getRootView(), R.id.concept_title).setSelected(true);
    }

    public boolean parseData(JSONObject jSONObject) {
        this.mFinished = jSONObject.optInt(JSON_KEY_FINISHED);
        this.mWordcount = jSONObject.optLong(JSON_KEY_WORDCOUNT);
        this.mIconColor = jSONObject.optString(JSON_KEY_ICONCOLOR);
        this.mTitle = jSONObject.optString("title");
        this.mDesc = jSONObject.optString(JSON_KEY_DESC);
        if (this.mDesc != null) {
            this.mDesc = this.mDesc.replaceAll("\\s", "");
        }
        this.mCateTag = jSONObject.optString("tag");
        this.mCategoryName = jSONObject.optString(JSON_KEY_CATEGORYNAME);
        this.mAuthor = jSONObject.optString(JSON_KEY_AUTHOR);
        this.mCardicon = jSONObject.optInt(JSON_KEY_CARDICON);
        this.mCoverUrl = jSONObject.optString(JSON_KEY_COVER);
        this.mBookid = jSONObject.optString("bid");
        this.mCateL2Name = jSONObject.optString(JSON_KEY_CATEL2NAME);
        this.mCateL3Name = jSONObject.optString(JSON_KEY_CATEL3NAME);
        this.mlimitStartTime = jSONObject.optLong(JSON_KEY_LMSTARTTIME);
        this.mlimitEndTime = jSONObject.optLong(JSON_KEY_LMENDTIME);
        this.mIconDest = jSONObject.optString(JSON_KEY_ICONDEST);
        this.mStatParams = jSONObject.optJSONObject("stat_params");
        this.mLftag = jSONObject.optInt(JSON_KEY_LFTAG);
        this.mRankValue = jSONObject.optLong(JSON_KEY_RANK_VALUE);
        if (this.mStatParams != null) {
            this.mStatTags = this.mStatParams.optString(JSON_KEY_STAT_TAGS);
            this.mStatAlgInfo = this.mStatParams.optString(JSON_KEY_STAT_ALGINFO);
        }
        if (a.bZ < 2.0f) {
            this.mMaxAuthorTextLength = 6;
        } else {
            this.mMaxAuthorTextLength = 8;
        }
        return true;
    }

    public String getDesc() {
        return this.mDesc;
    }

    public String getCateTag() {
        if ("null".equals(this.mCateTag)) {
            return "";
        }
        return this.mCateTag;
    }

    public String getCategoryName() {
        return this.mCategoryName;
    }

    public String getAuthor() {
        return this.mAuthor;
    }

    public int getCardicon() {
        return this.mCardicon;
    }

    public String getBookid() {
        return this.mBookid;
    }

    public String getCoverUrl() {
        if ("null".equals(this.mCoverUrl) || this.mCoverUrl == null || this.mCoverUrl.trim().equalsIgnoreCase("")) {
            this.mCoverUrl = ao.g(Long.valueOf(this.mBookid).longValue());
        }
        return this.mCoverUrl;
    }

    public String getCateL2Name() {
        return this.mCateL2Name;
    }

    public String getCateL3Name() {
        if ("null".equals(this.mCateL3Name)) {
            return "";
        }
        return this.mCateL3Name;
    }

    public String getIconDest() {
        return this.mIconDest;
    }
}
