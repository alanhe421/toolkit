package com.qq.reader.module.bookstore.search.card;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.search.card.SearchBaseCard.a;
import com.qq.reader.view.CustomTailIconTextView;
import com.tencent.feedback.proguard.R;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchAudioSingleBookCard extends SearchBaseCard {
    private static final String JSON_KEY_AUDIO = "audio";
    private static final String JSON_KEY_AUTH = "auth";
    private static final String JSON_KEY_AUTHOR = "author";
    public static final String JSON_KEY_BID = "bid";
    private static final String JSON_KEY_CARDICON = "cardicon";
    private static final String JSON_KEY_CATEGORYNAME = "categoryShortName";
    private static final String JSON_KEY_CATEL2NAME = "categoryShortName";
    private static final String JSON_KEY_CATEL3NAME = "catel3Name";
    private static final String JSON_KEY_CATETAG = "tag";
    private static final String JSON_KEY_COLLECTION_COUNT = "update";
    private static final String JSON_KEY_COVER = "cover";
    private static final String JSON_KEY_DESC = "intro";
    private static final String JSON_KEY_FINISHED = "finished";
    private static final String JSON_KEY_ICONCOLOR = "iconColor";
    private static final String JSON_KEY_ICONDEST = "icondesc";
    private static final String JSON_KEY_LFTAG = "lftag";
    private static final String JSON_KEY_LMENDTIME = "lmendtime";
    private static final String JSON_KEY_LMSTARTTIME = "lmstarttime";
    private static final String JSON_KEY_RECOMMEND = "recommendqurl";
    private static final String JSON_KEY_STAT_ALGINFO = "alg_info";
    private static final String JSON_KEY_STAT_PARAMS = "stat_params";
    private static final String JSON_KEY_STAT_TAGS = "tags";
    private static final String JSON_KEY_TERMS = "terms";
    private static final String JSON_KEY_TITLE = "title";
    private static final String JSON_KEY_WORDCOUNT = "totalWords";
    private String[] completeMatchTypeStrings = new String[]{"TSTF", "TSPT", "TSAF", "TSPA"};
    boolean isReCommendCardNow = false;
    private boolean isUploadStat = false;
    private int mAudio;
    private String mAuthor;
    private long mBookid;
    private int mCardicon;
    private String mCateL2Name;
    private String mCateL3Name;
    private String mCateTag;
    private String mCategoryName;
    private String mCollectionCount;
    private String mCoverUrl;
    private String mDesc;
    private long mFavoritecount;
    private int mFinished;
    private String mIconColor;
    private String mIconDest;
    private String mKey;
    private int mLftag;
    private String mLimitPrice;
    private int mMaxAuthorTextLength = 6;
    private long mOrdercount;
    private String mOrigin;
    private String mOverRating;
    private String mPrice;
    private String mReadpercent;
    private String mRent;
    private String mStatAlgInfo;
    private JSONObject mStatParams;
    private String mStatTags;
    private ArrayList<a> mTerms = new ArrayList();
    private long mTotalWords;
    private String mUnit;
    private long mUpdatetime;
    private long mWordcount;
    private long mlimitEndTime;
    private long mlimitStartTime;
    private String obtainUrl;

    public SearchAudioSingleBookCard(b bVar, String str, String str2) {
        super(bVar, str);
        this.mKey = str2;
    }

    public int getResLayoutId() {
        return R.layout.search_singlebook_layout;
    }

    public void attachView() {
        super.attachView();
        setImage((ImageView) ap.a(getRootView(), R.id.concept_cover_img), getCoverUrl(), null);
        TextView textView = (TextView) ap.a(getRootView(), R.id.concept_author);
        String author = getAuthor();
        if (author.length() > this.mMaxAuthorTextLength) {
            author = author.substring(0, this.mMaxAuthorTextLength - 1) + "…";
        }
        textView.setText(createSearchHitsString(author, this.mTerms));
        textView = (TextView) ap.a(getRootView(), R.id.concept_title);
        CustomTailIconTextView customTailIconTextView = (CustomTailIconTextView) ap.a(getRootView(), R.id.search_singlecard_title_container);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.concept_content);
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
        textView.setText(createSearchHitsString(getTitle(), this.mTerms));
        textView = (TextView) ap.a(getRootView(), R.id.limitprice);
        TextView textView3 = (TextView) ap.a(getRootView(), R.id.concept_tag_1);
        textView2 = (TextView) ap.a(getRootView(), R.id.concept_tag_2);
        if (TextUtils.isEmpty(this.mLimitPrice) && TextUtils.isEmpty(this.mRent)) {
            textView3.setVisibility(0);
            textView2.setVisibility(0);
            textView.setVisibility(8);
            if (TextUtils.isEmpty(getCateL2Name()) || "null".equals(getCateL2Name())) {
                textView3.setVisibility(8);
            } else {
                textView3.setText(getCateL2Name());
            }
            textView2.setBackgroundResource(R.drawable.concept_bookitem_order);
            textView2.setTextColor(textView2.getContext().getResources().getColor(R.color.book_store_card_order_color));
            if (TextUtils.isEmpty(this.mCollectionCount)) {
                textView2.setText("");
                textView2.setVisibility(8);
            } else {
                textView2.setText(this.mCollectionCount);
                textView2.setVisibility(0);
            }
        } else {
            textView3.setVisibility(8);
            textView2.setVisibility(8);
            textView.setVisibility(0);
            if (this.mPrice == null) {
                this.mPrice = "";
            }
            CharSequence spannableString;
            if (TextUtils.isEmpty(this.mLimitPrice)) {
                spannableString = new SpannableString(this.mRent);
                spannableString.setSpan(new ForegroundColorSpan(ReaderApplication.getApplicationImp().getResources().getColor(R.color.book_store_card_rent_color)), 0, this.mRent.length(), 33);
                textView.setText(spannableString);
            } else {
                spannableString = new SpannableString(this.mPrice + this.mLimitPrice);
                spannableString.setSpan(new StrikethroughSpan(), 0, this.mPrice.length(), 33);
                spannableString.setSpan(new ForegroundColorSpan(ReaderApplication.getApplicationImp().getResources().getColor(R.color.search_icon_color_gray)), 0, this.mPrice.length(), 33);
                spannableString.setSpan(new ForegroundColorSpan(ReaderApplication.getApplicationImp().getResources().getColor(R.color.book_store_card_price_color)), this.mPrice.length(), this.mPrice.length() + this.mLimitPrice.length(), 33);
                textView.setText(spannableString);
            }
        }
        ap.a(getRootView(), R.id.concept_tingbook_tag).setVisibility(0);
        textView = (TextView) ap.a(getRootView(), R.id.search_count);
        if (TextUtils.isEmpty(this.mOverRating)) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
            textView.setText(this.mOverRating + "的用户搜索该词后阅读本书");
        }
        textView = (TextView) ap.a(getRootView(), R.id.search_bottom_recommon);
        View a = ap.a(getRootView(), R.id.search_bottom_nor);
        if (TextUtils.isEmpty(this.mReadpercent)) {
            textView.setVisibility(8);
            a.setVisibility(0);
            return;
        }
        a.setVisibility(8);
        textView.setVisibility(0);
        textView.setText(this.mReadpercent + "的用户还阅读了这本书");
    }

    private int getResultCompleteMatchTypeIndex() {
        if (this.mTerms != null) {
            Iterator it = this.mTerms.iterator();
            while (it.hasNext()) {
                String str = ((a) it.next()).c;
                for (int i = 0; i < this.completeMatchTypeStrings.length; i++) {
                    if (this.completeMatchTypeStrings[i].equals(str)) {
                        return i + 1;
                    }
                }
            }
        }
        return -1;
    }

    protected void expose() {
        super.expose();
        int resultCompleteMatchTypeIndex = getResultCompleteMatchTypeIndex();
        if (resultCompleteMatchTypeIndex > 0) {
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, String.valueOf(resultCompleteMatchTypeIndex));
            i.a("event_B238", hashMap, ReaderApplication.getApplicationImp());
            StatisticsManager.a().a("event_B238", hashMap);
        }
    }

    private void addRDMStaticsParam(Map<String, String> map) {
        if (map != null) {
            map.put("type", "1");
        }
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        super.parseData(jSONObject);
        this.mQURL = jSONObject.optString("qurl");
        this.mFinished = jSONObject.optInt(JSON_KEY_FINISHED);
        this.mAudio = jSONObject.optInt(JSON_KEY_AUDIO, 0);
        this.mWordcount = jSONObject.optLong(JSON_KEY_WORDCOUNT);
        this.mIconColor = jSONObject.optString(JSON_KEY_ICONCOLOR);
        this.mTitle = jSONObject.optString("title");
        this.mDesc = jSONObject.optString(JSON_KEY_DESC);
        this.mRecommendUrl = jSONObject.optString(JSON_KEY_RECOMMEND);
        if (this.mDesc != null) {
            this.mDesc = this.mDesc.replaceAll("\\s", "");
        }
        this.mCateTag = jSONObject.optString("tag");
        this.mCategoryName = jSONObject.optString("categoryShortName");
        this.mAuthor = jSONObject.optString(JSON_KEY_AUTHOR);
        this.mCardicon = jSONObject.optInt(JSON_KEY_CARDICON);
        this.mCoverUrl = jSONObject.optString(JSON_KEY_COVER);
        try {
            this.mBookid = Long.valueOf(jSONObject.optString("bid")).longValue();
        } catch (Exception e) {
            this.mBookid = 0;
        }
        this.mCateL2Name = jSONObject.optString("categoryShortName");
        this.mCateL3Name = jSONObject.optString(JSON_KEY_CATEL3NAME);
        this.mlimitStartTime = jSONObject.optLong(JSON_KEY_LMSTARTTIME);
        this.mlimitEndTime = jSONObject.optLong(JSON_KEY_LMENDTIME);
        this.mIconDest = jSONObject.optString(JSON_KEY_ICONDEST);
        this.mStatParams = jSONObject.optJSONObject("stat_params");
        this.mLftag = jSONObject.optInt(JSON_KEY_LFTAG);
        this.mCollectionCount = jSONObject.optString(JSON_KEY_COLLECTION_COUNT);
        if (this.mStatParams != null) {
            this.mStatTags = this.mStatParams.optString(JSON_KEY_STAT_TAGS);
            this.mStatAlgInfo = this.mStatParams.optString(JSON_KEY_STAT_ALGINFO);
            this.mOrigin = this.mStatParams.optString(s.ORIGIN);
        }
        if (com.qq.reader.common.c.a.bZ < 2.0f) {
            this.mMaxAuthorTextLength = 6;
        } else {
            this.mMaxAuthorTextLength = 8;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray(JSON_KEY_TERMS);
        if (optJSONArray != null) {
            this.mTerms.clear();
            while (i < optJSONArray.length()) {
                try {
                    this.mTerms.add(new a(this, optJSONArray.getJSONObject(i)));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                i++;
            }
        }
        this.mOverRating = jSONObject.optString("overrating");
        this.mPrice = jSONObject.optString("price");
        this.mLimitPrice = jSONObject.optString("discount");
        this.mRent = jSONObject.optString("rent");
        this.mUpdatetime = jSONObject.optLong("updatetime", 0);
        this.mFavoritecount = jSONObject.optLong("favoritecount", 0);
        this.mTotalWords = jSONObject.optLong(JSON_KEY_WORDCOUNT, 0);
        this.mOrdercount = jSONObject.optLong("ordercount", 0);
        this.mUnit = jSONObject.optString("unit");
        this.mReadpercent = jSONObject.optString("readpercent");
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

    public String getCoverUrl() {
        if ("null".equals(this.mCoverUrl) || this.mCoverUrl == null || this.mCoverUrl.trim().equalsIgnoreCase("")) {
            this.mCoverUrl = ao.a(Long.valueOf(this.mBookid).longValue(), false, (int) Opcodes.OR_INT);
        }
        return this.mCoverUrl;
    }

    public String getCateL2Name() {
        if (TextUtils.isEmpty(this.mCateL2Name)) {
            return this.mCateL3Name;
        }
        return this.mCateL2Name;
    }

    public String getCateL3Name() {
        return this.mCateL3Name;
    }

    public String getIconDest() {
        return this.mIconDest;
    }

    public void onHideOld() {
        this.isReCommendCardNow = true;
        super.onHideOld();
    }

    public void onShowNew() {
        super.onShowNew();
    }

    public boolean enableExchange() {
        return true;
    }

    public String getExchangeUrl() {
        return this.mRecommendUrl;
    }

    public void doClickedCard() {
        super.doClickedCard();
        int resultCompleteMatchTypeIndex = getResultCompleteMatchTypeIndex();
        if (resultCompleteMatchTypeIndex > 0) {
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, String.valueOf(resultCompleteMatchTypeIndex));
            i.a("event_B239", hashMap, ReaderApplication.getApplicationImp());
            StatisticsManager.a().a("event_B239", hashMap);
        }
    }
}
