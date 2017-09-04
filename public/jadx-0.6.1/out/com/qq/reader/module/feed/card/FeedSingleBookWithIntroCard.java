package com.qq.reader.module.feed.card;

import android.graphics.Color;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.common.c.a;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import com.qq.reader.view.CustomTailIconTextView;
import com.tencent.feedback.proguard.R;
import org.json.JSONArray;
import org.json.JSONObject;

public class FeedSingleBookWithIntroCard extends FeedBaseCard {
    private static final String JSON_KEY_AUTH = "auth";
    private static final String JSON_KEY_AUTHOR = "author";
    public static final String JSON_KEY_BID = "bid";
    private static final String JSON_KEY_CARDICON = "cardicon";
    private static final String JSON_KEY_CATEGORYNAME = "categoryname ";
    private static final String JSON_KEY_CATEL2NAME = "catel2name";
    private static final String JSON_KEY_CATEL3NAME = "catel3name";
    private static final String JSON_KEY_CATETAG = "catetag";
    private static final String JSON_KEY_COVER = "cover";
    private static final String JSON_KEY_DESC = "desc";
    private static final String JSON_KEY_DLFILE = "dlfile";
    private static final String JSON_KEY_FINISHED = "finished";
    private static final String JSON_KEY_ICONCOLOR = "iconColor";
    private static final String JSON_KEY_ICONDEST = "icondesc";
    private static final String JSON_KEY_LFTAG = "lftag";
    private static final String JSON_KEY_LMENDTIME = "lmendtime";
    private static final String JSON_KEY_LMSTARTTIME = "lmstarttime";
    private static final String JSON_KEY_QTEB = "qteb";
    private static final String JSON_KEY_SCORE = "score";
    private static final String JSON_KEY_STAT_ALGINFO = "alg_info";
    private static final String JSON_KEY_STAT_PARAMS = "stat_params";
    private static final String JSON_KEY_STAT_TAGS = "tags";
    private static final String JSON_KEY_TITLE = "showTitle";
    private static final String JSON_KEY_WORDCOUNT = "wordcount";
    public static final String ONLYTITLE = "onlytitle";
    private boolean isHardCover = false;
    private int mAuth;
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
    private String mIntro;
    private int mLftag;
    private int mMaxAuthorTextLength = 6;
    private String mRatingScore;
    private long mWordcount;
    private long mlimitEndTime;
    private long mlimitStartTime;

    public FeedSingleBookWithIntroCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        if (com.qq.reader.common.c.b.a == 0) {
            return R.layout.concept_bookitem_with_intro_layout;
        }
        return R.layout.debug_concept_bookitem_layout;
    }

    public void attachView() {
        boolean z;
        CustomTailIconTextView customTailIconTextView;
        TextView textView;
        float parseFloat;
        boolean z2;
        ImageView imageView;
        setImage((ImageView) ap.a(getRootView(), R.id.concept_cover_img), getCoverUrl(), null);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.concept_author);
        CharSequence author = getAuthor();
        if (!TextUtils.isEmpty(author) && author.length() > this.mMaxAuthorTextLength) {
            author = author.substring(0, this.mMaxAuthorTextLength - 1) + "…";
        }
        textView2.setText(author);
        textView2 = (TextView) ap.a(getRootView(), R.id.concept_tag_subscript);
        TextView textView3 = (TextView) ap.a(getRootView(), R.id.concept_title);
        if (this.mLftag != 0) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis < this.mlimitStartTime || currentTimeMillis > this.mlimitEndTime) {
                z = true;
                if (!z || TextUtils.isEmpty(this.mIconColor) || TextUtils.isEmpty(getIconDest())) {
                    textView2.setVisibility(8);
                } else {
                    textView2.setVisibility(0);
                    try {
                        ao.a(textView2, Color.parseColor(this.mIconColor));
                        textView2.setText(getIconDest());
                    } catch (Exception e) {
                        c.e("FeedSingleBookCard", e.getMessage());
                        textView2.setVisibility(8);
                    }
                }
                textView3.setText(getTitle());
                if (isClickedStatus()) {
                    textView3.setSelected(false);
                } else {
                    textView3.setSelected(true);
                }
                customTailIconTextView = (CustomTailIconTextView) ap.a(getRootView(), R.id.feed_title_container);
                textView = (TextView) ap.a(getRootView(), R.id.concept_content);
                if (com.qq.reader.common.c.b.a != 2 || com.qq.reader.common.c.b.a == 3) {
                    textView3.setText(getDesc());
                    textView3.setSingleLine(false);
                    textView3.setMaxLines(2);
                    textView.setVisibility(8);
                } else {
                    textView3.setText(getTitle());
                    if (TextUtils.isEmpty(getDesc())) {
                        textView.setVisibility(8);
                        textView3.setMaxLines(2);
                        customTailIconTextView.setMaxlines(2);
                    } else {
                        textView3.setMaxLines(1);
                        customTailIconTextView.setMaxlines(1);
                        textView.setVisibility(0);
                        textView.setText(getDesc());
                    }
                }
                textView2 = (TextView) ap.a(getRootView(), R.id.concept_tag_1);
                if (TextUtils.isEmpty(getCateL2Name())) {
                    textView2.setVisibility(0);
                    textView2.setText(getCateL2Name());
                } else {
                    textView2.setVisibility(8);
                }
                textView3 = (TextView) ap.a(getRootView(), R.id.concept_tag_2);
                if (TextUtils.isEmpty(getCateTag()) || !TextUtils.isEmpty(getCateL3Name())) {
                    textView3.setVisibility(0);
                    if (TextUtils.isEmpty(getCateTag())) {
                        textView3.setText(getCateTag());
                    } else {
                        textView3.setText(getCateL3Name());
                    }
                } else {
                    textView3.setVisibility(8);
                }
                textView = (TextView) ap.a(getRootView(), R.id.concept_tag_4);
                if (TextUtils.isEmpty(this.mRatingScore)) {
                    try {
                        parseFloat = Float.parseFloat(this.mRatingScore);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        parseFloat = 0.0f;
                    }
                    if (parseFloat <= 5.0f) {
                        textView.setVisibility(0);
                        textView.setText(this.mRatingScore + "分");
                        z2 = true;
                    } else {
                        textView.setVisibility(8);
                        z2 = false;
                    }
                } else {
                    textView.setVisibility(8);
                    z2 = false;
                }
                textView = (TextView) ap.a(getRootView(), R.id.concept_tag_3);
                if (this.mAuth != 5 || this.mWordcount <= 0 || r3) {
                    textView.setVisibility(8);
                } else {
                    textView.setVisibility(0);
                    textView.setText(s.countTransform(this.mWordcount) + "字");
                }
                imageView = (ImageView) ap.a(getRootView(), R.id.concept_cover_tag);
                if (this.isHardCover) {
                    imageView.setVisibility(0);
                    imageView.setImageResource(R.drawable.feed_corner_mark_hard_cover);
                } else if (this.mAuth == 5 && this.mFinished == 1) {
                    imageView.setVisibility(0);
                    imageView.setImageResource(R.drawable.feed_corner_mark_finish);
                } else {
                    imageView.setVisibility(8);
                }
                checkClickEnable();
                if (com.qq.reader.common.c.b.a != 2) {
                    textView2.setVisibility(8);
                    textView3.setVisibility(8);
                    textView.setVisibility(8);
                }
                ((TextView) ap.a(getRootView(), R.id.concept_intro)).setText(this.mIntro);
                return;
            }
        }
        z = false;
        if (z) {
        }
        textView2.setVisibility(8);
        textView3.setText(getTitle());
        if (isClickedStatus()) {
            textView3.setSelected(false);
        } else {
            textView3.setSelected(true);
        }
        customTailIconTextView = (CustomTailIconTextView) ap.a(getRootView(), R.id.feed_title_container);
        textView = (TextView) ap.a(getRootView(), R.id.concept_content);
        if (com.qq.reader.common.c.b.a != 2) {
        }
        textView3.setText(getDesc());
        textView3.setSingleLine(false);
        textView3.setMaxLines(2);
        textView.setVisibility(8);
        textView2 = (TextView) ap.a(getRootView(), R.id.concept_tag_1);
        if (TextUtils.isEmpty(getCateL2Name())) {
            textView2.setVisibility(0);
            textView2.setText(getCateL2Name());
        } else {
            textView2.setVisibility(8);
        }
        textView3 = (TextView) ap.a(getRootView(), R.id.concept_tag_2);
        if (TextUtils.isEmpty(getCateTag())) {
        }
        textView3.setVisibility(0);
        if (TextUtils.isEmpty(getCateTag())) {
            textView3.setText(getCateL3Name());
        } else {
            textView3.setText(getCateTag());
        }
        textView = (TextView) ap.a(getRootView(), R.id.concept_tag_4);
        if (TextUtils.isEmpty(this.mRatingScore)) {
            parseFloat = Float.parseFloat(this.mRatingScore);
            if (parseFloat <= 5.0f) {
                textView.setVisibility(8);
                z2 = false;
            } else {
                textView.setVisibility(0);
                textView.setText(this.mRatingScore + "分");
                z2 = true;
            }
        } else {
            textView.setVisibility(8);
            z2 = false;
        }
        textView = (TextView) ap.a(getRootView(), R.id.concept_tag_3);
        if (this.mAuth != 5) {
        }
        textView.setVisibility(8);
        imageView = (ImageView) ap.a(getRootView(), R.id.concept_cover_tag);
        if (this.isHardCover) {
            imageView.setVisibility(0);
            imageView.setImageResource(R.drawable.feed_corner_mark_hard_cover);
        } else {
            if (this.mAuth == 5) {
            }
            imageView.setVisibility(8);
        }
        checkClickEnable();
        if (com.qq.reader.common.c.b.a != 2) {
            ((TextView) ap.a(getRootView(), R.id.concept_intro)).setText(this.mIntro);
            return;
        }
        textView2.setVisibility(8);
        textView3.setVisibility(8);
        textView.setVisibility(8);
    }

    public void doClickedCard() {
        super.doClickedCard();
        ap.a(getRootView(), R.id.concept_title).setSelected(true);
    }

    public boolean parseData(JSONObject jSONObject) {
        this.mIntro = jSONObject.optString("title");
        JSONArray optJSONArray = jSONObject.optJSONArray("data");
        if (optJSONArray == null || optJSONArray.length() < 1) {
            return false;
        }
        JSONObject optJSONObject = optJSONArray.optJSONObject(0).optJSONObject("ext_info");
        if (optJSONObject == null) {
            return false;
        }
        this.mAuth = optJSONObject.optInt(JSON_KEY_AUTH);
        this.mFinished = optJSONObject.optInt(JSON_KEY_FINISHED);
        this.mWordcount = optJSONObject.optLong(JSON_KEY_WORDCOUNT);
        this.mIconColor = optJSONObject.optString(JSON_KEY_ICONCOLOR);
        this.mTitle = optJSONObject.optString(JSON_KEY_TITLE);
        if (!"onlytitle".equals(this.mType)) {
            this.mDesc = optJSONObject.optString("desc");
        }
        this.mCateTag = optJSONObject.optString(JSON_KEY_CATETAG);
        this.mCategoryName = optJSONObject.optString(JSON_KEY_CATEGORYNAME);
        this.mAuthor = optJSONObject.optString(JSON_KEY_AUTHOR);
        this.mCardicon = optJSONObject.optInt(JSON_KEY_CARDICON);
        this.mCoverUrl = optJSONObject.optString(JSON_KEY_COVER);
        this.mBookid = optJSONObject.optString("bid");
        this.mCateL2Name = optJSONObject.optString(JSON_KEY_CATEL2NAME);
        this.mCateL3Name = optJSONObject.optString(JSON_KEY_CATEL3NAME);
        this.mlimitStartTime = optJSONObject.optLong(JSON_KEY_LMSTARTTIME);
        this.mlimitEndTime = optJSONObject.optLong(JSON_KEY_LMENDTIME);
        this.mIconDest = optJSONObject.optString(JSON_KEY_ICONDEST);
        JSONObject optJSONObject2 = jSONObject.optJSONObject("stat_params");
        this.mLftag = optJSONObject.optInt(JSON_KEY_LFTAG);
        if (optJSONObject2 != null) {
            optJSONObject2.optString(JSON_KEY_STAT_TAGS);
            optJSONObject2.optString(JSON_KEY_STAT_ALGINFO);
        }
        this.mRatingScore = optJSONObject.optString(JSON_KEY_SCORE);
        if (this.mAuth == 5) {
            if (a.bZ < 2.0f) {
                this.mMaxAuthorTextLength = 10;
            } else {
                this.mMaxAuthorTextLength = 14;
            }
        } else if (a.bZ < 2.0f) {
            this.mMaxAuthorTextLength = 6;
        } else {
            this.mMaxAuthorTextLength = 8;
        }
        if (!TextUtils.isEmpty(this.mRatingScore)) {
            this.mMaxAuthorTextLength -= 3;
        }
        optJSONObject2 = optJSONObject.optJSONObject(JSON_KEY_DLFILE);
        if (!(optJSONObject2 == null || TextUtils.isEmpty(optJSONObject2.optString(JSON_KEY_QTEB)))) {
            this.isHardCover = true;
        }
        return true;
    }

    public String getDesc() {
        return this.mDesc;
    }

    public String getCateTag() {
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
        if ((this.mCoverUrl == null || this.mCoverUrl.trim().equalsIgnoreCase("")) && !TextUtils.isEmpty(this.mBookid)) {
            this.mCoverUrl = ao.g(Long.valueOf(this.mBookid).longValue());
        }
        return this.mCoverUrl;
    }

    public String getCateL2Name() {
        return this.mCateL2Name;
    }

    public String getCateL3Name() {
        return this.mCateL3Name;
    }

    public String getIconDest() {
        return this.mIconDest;
    }
}
