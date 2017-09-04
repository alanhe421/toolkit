package com.qq.reader.module.feed.card;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.c.a;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import com.qq.reader.module.feed.widget.FeedSubscriptView;
import com.tencent.feedback.proguard.R;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.json.JSONObject;

public class FeedColumnBSingleBookCard extends FeedBaseCard {
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
    private static final String JSON_KEY_RECTIME = "recTime";
    private static final String JSON_KEY_SCORE = "score";
    private static final String JSON_KEY_STAT_ALGINFO = "alg_info";
    private static final String JSON_KEY_STAT_PARAMS = "stat_params";
    private static final String JSON_KEY_STAT_TAGS = "tags";
    private static final String JSON_KEY_TITLE = "title";
    private static final String JSON_KEY_WORDCOUNT = "wordcount";
    public static final String ONLYTITLE = "onlytitle";
    private boolean isHardCover = false;
    private boolean isShowDayTime = false;
    private String mAlgInfo;
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
    private String mExtInfoId;
    private int mFinished;
    private String mIconColor;
    private String mIconDest;
    private int mLftag;
    private int mMaxAuthorTextLength = 6;
    private String mRatingScore;
    private long mRecTime;
    private String mStatAlgInfo;
    private JSONObject mStatParams;
    private String mStatTags;
    private long mWordcount;
    private long mlimitEndTime;
    private long mlimitStartTime;

    public FeedColumnBSingleBookCard(b bVar, String str) {
        super(bVar, str);
    }

    public String getExtInfoId() {
        return this.mExtInfoId;
    }

    public void setExtInfoId(String str) {
        this.mExtInfoId = str;
    }

    public void setAlgInfo(String str) {
        this.mAlgInfo = str;
    }

    public String getAlgInfo() {
        return this.mAlgInfo;
    }

    public int getResLayoutId() {
        if (com.qq.reader.common.c.b.a == 0) {
            return R.layout.concept_column_bookitem_layout;
        }
        return R.layout.debug_concept_bookitem_layout;
    }

    public void attachView() {
        boolean z;
        TextView textView;
        TextView textView2;
        TextView textView3;
        RelativeLayout relativeLayout = (RelativeLayout) ap.a(getRootView(), R.id.feed_column_time_relativelayout);
        if (this.isShowDayTime) {
            relativeLayout.setVisibility(0);
            String dayTime = getDayTime(this.mRecTime);
            ((TextView) ap.a(getRootView(), R.id.feed_column_time_tip)).setText(dayTime.substring(4, 6) + "月" + dayTime.substring(6, 8) + "日");
            ap.a(getRootView(), R.id.divider_top).setVisibility(8);
        } else {
            relativeLayout.setVisibility(8);
            ap.a(getRootView(), R.id.divider_top).setVisibility(0);
        }
        setImage((ImageView) ap.a(getRootView(), R.id.concept_cover_img), getCoverUrl(), null);
        TextView textView4 = (TextView) ap.a(getRootView(), R.id.concept_author);
        CharSequence author = getAuthor();
        if (!TextUtils.isEmpty(author) && author.length() > this.mMaxAuthorTextLength) {
            author = author.substring(0, this.mMaxAuthorTextLength - 1) + "…";
        }
        textView4.setText(author);
        FeedSubscriptView feedSubscriptView = (FeedSubscriptView) ap.a(getRootView(), R.id.concept_tag_subscript);
        if (this.mLftag != 0) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis < this.mlimitStartTime || currentTimeMillis > this.mlimitEndTime) {
                z = true;
                if (!z || TextUtils.isEmpty(this.mIconColor) || TextUtils.isEmpty(getIconDest())) {
                    feedSubscriptView.setVisibility(8);
                } else {
                    feedSubscriptView.setVisibility(0);
                    try {
                        feedSubscriptView.setConceptBgColor(Color.parseColor(this.mIconColor));
                        feedSubscriptView.setText(getIconDest());
                    } catch (Exception e) {
                        c.e("FeedSingleBookCard", e.getMessage());
                        feedSubscriptView.setVisibility(8);
                    }
                }
                textView4 = (TextView) ap.a(getRootView(), R.id.concept_title);
                textView4.setText(getTitle());
                if (isClickedStatus()) {
                    textView4.setSelected(false);
                } else {
                    textView4.setSelected(true);
                }
                textView = (TextView) ap.a(getRootView(), R.id.concept_content);
                if (com.qq.reader.common.c.b.a != 2 || com.qq.reader.common.c.b.a == 3) {
                    textView4.setText(getDesc());
                    textView4.setSingleLine(false);
                    textView4.setMaxLines(2);
                    textView.setVisibility(8);
                } else {
                    textView4.setText(getTitle());
                    if (TextUtils.isEmpty(getDesc())) {
                        textView.setVisibility(8);
                        textView4.setMaxLines(2);
                    } else {
                        textView4.setMaxLines(1);
                        textView.setVisibility(0);
                        textView.setText(getDesc());
                    }
                }
                textView4 = (TextView) ap.a(getRootView(), R.id.concept_tag_1);
                if (TextUtils.isEmpty(getCateL2Name())) {
                    textView4.setVisibility(0);
                    textView4.setText(getCateL2Name());
                } else {
                    textView4.setVisibility(8);
                }
                textView = (TextView) ap.a(getRootView(), R.id.concept_tag_2);
                if (TextUtils.isEmpty(getCateTag()) || !TextUtils.isEmpty(getCateL3Name())) {
                    textView.setVisibility(0);
                    if (TextUtils.isEmpty(getCateTag())) {
                        textView.setText(getCateTag());
                    } else {
                        textView.setText(getCateL3Name());
                    }
                } else {
                    textView.setVisibility(8);
                }
                textView2 = (TextView) ap.a(getRootView(), R.id.concept_tag_3);
                if (this.mAuth != 5 || this.mWordcount <= 0) {
                    textView2.setVisibility(8);
                } else {
                    textView2.setVisibility(0);
                    textView2.setText(s.countTransform(this.mWordcount) + "字");
                }
                textView3 = (TextView) ap.a(getRootView(), R.id.concept_tag_4);
                if (TextUtils.isEmpty(this.mRatingScore)) {
                    textView3.setVisibility(0);
                    textView3.setText(this.mRatingScore + "分");
                } else {
                    textView3.setVisibility(8);
                }
                ((TextView) ap.a(getRootView(), R.id.concept_cover_tag)).setVisibility(8);
                if (com.qq.reader.common.c.b.a != 2) {
                    textView4.setVisibility(8);
                    textView.setVisibility(8);
                    textView2.setVisibility(8);
                }
                getRootView().setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ FeedColumnBSingleBookCard a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putString("LOCAL_STORE_IN_TITLE", this.a.getTitle());
                        bundle.putString("KEY_JUMP_PAGENAME", "DetailPage");
                        bundle.putLong("URL_BUILD_PERE_BOOK_ID", Long.parseLong(this.a.getBookid()));
                        try {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put(s.ALG, this.a.mAlgInfo);
                            jSONObject.put("ext_info_id", this.a.mExtInfoId);
                            bundle.putString("stat_params", jSONObject.toString());
                        } catch (Exception e) {
                        }
                        new com.qq.reader.module.bookstore.qnative.c(bundle).a(this.a.getEvnetListener());
                    }
                });
                return;
            }
        }
        z = false;
        if (z) {
        }
        feedSubscriptView.setVisibility(8);
        textView4 = (TextView) ap.a(getRootView(), R.id.concept_title);
        textView4.setText(getTitle());
        if (isClickedStatus()) {
            textView4.setSelected(false);
        } else {
            textView4.setSelected(true);
        }
        textView = (TextView) ap.a(getRootView(), R.id.concept_content);
        if (com.qq.reader.common.c.b.a != 2) {
        }
        textView4.setText(getDesc());
        textView4.setSingleLine(false);
        textView4.setMaxLines(2);
        textView.setVisibility(8);
        textView4 = (TextView) ap.a(getRootView(), R.id.concept_tag_1);
        if (TextUtils.isEmpty(getCateL2Name())) {
            textView4.setVisibility(0);
            textView4.setText(getCateL2Name());
        } else {
            textView4.setVisibility(8);
        }
        textView = (TextView) ap.a(getRootView(), R.id.concept_tag_2);
        if (TextUtils.isEmpty(getCateTag())) {
        }
        textView.setVisibility(0);
        if (TextUtils.isEmpty(getCateTag())) {
            textView.setText(getCateL3Name());
        } else {
            textView.setText(getCateTag());
        }
        textView2 = (TextView) ap.a(getRootView(), R.id.concept_tag_3);
        if (this.mAuth != 5) {
        }
        textView2.setVisibility(8);
        textView3 = (TextView) ap.a(getRootView(), R.id.concept_tag_4);
        if (TextUtils.isEmpty(this.mRatingScore)) {
            textView3.setVisibility(0);
            textView3.setText(this.mRatingScore + "分");
        } else {
            textView3.setVisibility(8);
        }
        ((TextView) ap.a(getRootView(), R.id.concept_cover_tag)).setVisibility(8);
        if (com.qq.reader.common.c.b.a != 2) {
            getRootView().setOnClickListener(/* anonymous class already generated */);
            return;
        }
        textView4.setVisibility(8);
        textView.setVisibility(8);
        textView2.setVisibility(8);
    }

    public void doClickedCard() {
        super.doClickedCard();
        ap.a(getRootView(), R.id.concept_title).setSelected(true);
    }

    public boolean parseData(JSONObject jSONObject) {
        this.mAuth = jSONObject.optInt(JSON_KEY_AUTH);
        this.mFinished = jSONObject.optInt(JSON_KEY_FINISHED);
        this.mWordcount = jSONObject.optLong(JSON_KEY_WORDCOUNT);
        this.mIconColor = jSONObject.optString(JSON_KEY_ICONCOLOR);
        this.mTitle = jSONObject.optString("title");
        if (!"onlytitle".equals(this.mType)) {
            this.mDesc = jSONObject.optString("desc");
        }
        if (TextUtils.isEmpty(this.mDesc)) {
            this.mDesc = jSONObject.optString("intro");
        }
        this.mCateTag = jSONObject.optString(JSON_KEY_CATETAG);
        this.mCategoryName = jSONObject.optString(JSON_KEY_CATEGORYNAME);
        this.mAuthor = jSONObject.optString(JSON_KEY_AUTHOR);
        this.mCardicon = jSONObject.optInt(JSON_KEY_CARDICON);
        this.mCoverUrl = jSONObject.optString(JSON_KEY_COVER);
        this.mBookid = jSONObject.optString("bid", "");
        if (TextUtils.isEmpty(this.mBookid)) {
            this.mBookid = jSONObject.optString("id");
        }
        this.mCateL2Name = jSONObject.optString(JSON_KEY_CATEL2NAME);
        this.mCateL3Name = jSONObject.optString(JSON_KEY_CATEL3NAME);
        this.mlimitStartTime = jSONObject.optLong(JSON_KEY_LMSTARTTIME);
        this.mlimitEndTime = jSONObject.optLong(JSON_KEY_LMENDTIME);
        this.mIconDest = jSONObject.optString(JSON_KEY_ICONDEST);
        this.mStatParams = jSONObject.optJSONObject("stat_params");
        this.mLftag = jSONObject.optInt(JSON_KEY_LFTAG);
        this.mRecTime = jSONObject.optLong(JSON_KEY_RECTIME, -1);
        if (this.mRecTime != -1) {
            String dayTime = getDayTime(this.mRecTime);
            CharSequence bx = d.bx(ReaderApplication.getApplicationImp());
            if (TextUtils.isEmpty(bx) || !dayTime.equals(bx)) {
                d.u(ReaderApplication.getApplicationImp(), dayTime);
                this.isShowDayTime = true;
            } else {
                this.isShowDayTime = false;
            }
        } else {
            this.isShowDayTime = false;
        }
        if (this.mStatParams != null) {
            this.mStatTags = this.mStatParams.optString(JSON_KEY_STAT_TAGS);
            this.mStatAlgInfo = this.mStatParams.optString(JSON_KEY_STAT_ALGINFO);
        }
        this.mRatingScore = jSONObject.optString(JSON_KEY_SCORE);
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
        JSONObject optJSONObject = jSONObject.optJSONObject(JSON_KEY_DLFILE);
        if (!(optJSONObject == null || TextUtils.isEmpty(optJSONObject.optString(JSON_KEY_QTEB)))) {
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

    private String getDayTime(long j) {
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return simpleDateFormat.format(instance.getTime());
    }
}
