package com.qq.reader.module.bookstore.search.card;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.j;
import com.qq.reader.common.utils.k;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.qurl.c;
import com.qq.reader.view.CustomTailIconTextView;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchSingleBookCard extends SearchBaseCard {
    private static final String JSON_KEY_AUDIO = "audio";
    private static final String JSON_KEY_AUTH = "auth";
    private static final String JSON_KEY_AUTHOR = "author";
    public static final String JSON_KEY_BID = "bid";
    private static final String JSON_KEY_CARDICON = "cardicon";
    private static final String JSON_KEY_CATEGORYNAME = "categoryShortName";
    private static final String JSON_KEY_CATEL2NAME = "categoryShortName";
    private static final String JSON_KEY_CATEL3NAME = "catel3Name";
    private static final String JSON_KEY_CATETAG = "tag";
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
    public static final int RELATED_PRODUCT_TYPE_AUDIO = 1;
    public static final int RELATED_PRODUCT_TYPE_COMIC = 0;
    public static final int RELATED_PRODUCT_TYPE_GAME = 2;
    private boolean isOutBook;
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
    private List<a> mRelatedProductList;
    private String mRent;
    private String mStatAlgInfo;
    private JSONObject mStatParams;
    private String mStatTags;
    private ArrayList<com.qq.reader.module.bookstore.search.card.SearchBaseCard.a> mTerms = new ArrayList();
    private long mTotalWords;
    private String mUnit;
    private long mUpdatetime;
    private long mWordcount;
    private long mlimitEndTime;
    private long mlimitStartTime;
    private String obtainUrl;

    private class a {
        String a;
        String b;
        int c;
        final /* synthetic */ SearchSingleBookCard d;

        public a(SearchSingleBookCard searchSingleBookCard, String str, String str2, int i) {
            this.d = searchSingleBookCard;
            this.a = str;
            this.b = str2;
            this.c = i;
        }
    }

    public SearchSingleBookCard(b bVar, String str, String str2) {
        super(bVar, str);
        this.mKey = str2;
    }

    public int getResLayoutId() {
        return R.layout.search_singlebook_layout;
    }

    public void attachView() {
        super.attachView();
        if (!(ao.m(this.mBookid) || this.isUploadStat)) {
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, String.valueOf(this.mBookid));
            i.a("event_A203", hashMap, ReaderApplication.getApplicationImp());
            this.isUploadStat = true;
        }
        setImage((ImageView) ap.a(getRootView(), R.id.concept_cover_img), getCoverUrl(), null);
        TextView textView = (TextView) ap.a(getRootView(), R.id.concept_author);
        String author = getAuthor();
        if (author.length() > this.mMaxAuthorTextLength) {
            author = author.substring(0, this.mMaxAuthorTextLength - 1) + "…";
        }
        textView.setText(createSearchHitsString(author, this.mTerms));
        textView = (TextView) ap.a(getRootView(), R.id.concept_title);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.concept_tag_subscript);
        CustomTailIconTextView customTailIconTextView = (CustomTailIconTextView) ap.a(getRootView(), R.id.search_singlecard_title_container);
        TextView textView3 = (TextView) ap.a(getRootView(), R.id.concept_content);
        if (TextUtils.isEmpty(getDesc())) {
            textView3.setVisibility(8);
            textView.setMaxLines(2);
            customTailIconTextView.setMaxlines(2);
        } else {
            textView.setMaxLines(1);
            customTailIconTextView.setMaxlines(1);
            textView3.setVisibility(0);
            textView3.setText(getDesc());
        }
        textView.setText(createSearchHitsString(getTitle(), this.mTerms));
        textView = (TextView) ap.a(getRootView(), R.id.limitprice);
        TextView textView4 = (TextView) ap.a(getRootView(), R.id.concept_tag_1);
        textView3 = (TextView) ap.a(getRootView(), R.id.concept_tag_2);
        if (this.isOutBook) {
            textView4.setVisibility(0);
            textView3.setVisibility(8);
            textView.setVisibility(8);
            textView4.setText("待上架");
        } else if (TextUtils.isEmpty(this.mLimitPrice) && TextUtils.isEmpty(this.mRent)) {
            textView4.setVisibility(0);
            textView3.setVisibility(0);
            textView.setVisibility(8);
            if (TextUtils.isEmpty(getCateL2Name()) || "null".equals(getCateL2Name())) {
                textView4.setVisibility(8);
            } else {
                textView4.setText(getCateL2Name());
            }
            textView3.setBackgroundResource(R.drawable.concept_bookitem_order);
            textView3.setTextColor(textView3.getContext().getResources().getColor(R.color.book_store_card_order_color));
            if (this.mFavoritecount > 0) {
                textView3.setText(j.a(this.mFavoritecount) + this.mUnit);
            } else if (this.mOrdercount > 0) {
                textView3.setText(j.a(this.mOrdercount) + this.mUnit);
            } else if (this.mUpdatetime > 0) {
                textView3.setText(k.a(this.mUpdatetime));
            } else if (this.mTotalWords > 0) {
                textView3.setText(j.a(this.mTotalWords) + this.mUnit);
            } else if (!TextUtils.isEmpty(getCateTag()) && !"null".equals(getCateTag())) {
                textView3.setBackgroundResource(R.drawable.concept_bookitem_tag_level3);
                textView3.setTextColor(textView3.getContext().getResources().getColor(R.color.common_textcolor_secondary));
                textView3.setText(getCateTag());
            } else if ("null".equals(getCateL3Name()) || TextUtils.isEmpty(getCateL3Name())) {
                textView3.setVisibility(8);
            } else {
                textView3.setBackgroundResource(R.drawable.concept_bookitem_tag_level3);
                textView3.setTextColor(textView3.getContext().getResources().getColor(R.color.common_textcolor_secondary));
                textView3.setText(getCateL3Name());
            }
        } else {
            textView4.setVisibility(8);
            textView3.setVisibility(8);
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
        ap.a(getRootView(), R.id.concept_tingbook_tag).setVisibility(8);
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
        } else {
            a.setVisibility(8);
            textView.setVisibility(0);
            textView.setText(this.mReadpercent + "的用户还阅读了这本书");
        }
        textView2.setVisibility(8);
        bindRelatedProduct();
    }

    private void bindRelatedProduct() {
        View a = ap.a(getRootView(), R.id.search_single_book_related_container);
        if (this.mRelatedProductList.size() == 0) {
            a.setVisibility(8);
            return;
        }
        a.setVisibility(0);
        StringBuilder stringBuilder = new StringBuilder();
        View a2;
        View inflate;
        final a aVar;
        if (this.mRelatedProductList.size() == 1) {
            a2 = ap.a(getRootView(), R.id.search_single_book_related_1_container);
            if (a2 == null) {
                inflate = ((ViewStub) a.findViewById(R.id.search_single_book_related_1_stub)).inflate();
            } else {
                a2.setVisibility(0);
                inflate = a2;
            }
            a2 = ap.a(getRootView(), R.id.search_single_book_related_3_container);
            if (a2 != null) {
                a2.setVisibility(8);
            }
            aVar = (a) this.mRelatedProductList.get(0);
            ((TextView) inflate.findViewById(R.id.search_single_book_related_1_title)).setText(aVar.b);
            ((ImageView) inflate.findViewById(R.id.search_single_book_related_1_icon)).setImageResource(getRelatedProductIcon(aVar.c));
            inflate.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ SearchSingleBookCard b;

                public void onClick(View view) {
                    try {
                        c.a(this.b.getEvnetListener().getFromActivity(), aVar.a);
                        this.b.stateRelatedProductClick(aVar.c);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            stringBuilder.append(((a) this.mRelatedProductList.get(0)).c);
        } else {
            a2 = ap.a(getRootView(), R.id.search_single_book_related_3_container);
            if (a2 == null) {
                inflate = ((ViewStub) a.findViewById(R.id.search_single_book_related_3_stub)).inflate();
            } else {
                a2.setVisibility(0);
                inflate = a2;
            }
            a2 = ap.a(getRootView(), R.id.search_single_book_related_1_container);
            if (a2 != null) {
                a2.setVisibility(8);
            }
            aVar = (a) this.mRelatedProductList.get(0);
            ((TextView) inflate.findViewById(R.id.search_single_book_related_3_item1_title)).setText(getRelatedProductTitle(aVar.c));
            ((ImageView) inflate.findViewById(R.id.search_single_book_related_3_item1_icon)).setImageResource(getRelatedProductIcon(aVar.c));
            inflate.findViewById(R.id.search_single_book_related_3_item1).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ SearchSingleBookCard b;

                public void onClick(View view) {
                    try {
                        c.a(this.b.getEvnetListener().getFromActivity(), aVar.a);
                        this.b.stateRelatedProductClick(aVar.c);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            stringBuilder.append(((a) this.mRelatedProductList.get(0)).c);
            aVar = (a) this.mRelatedProductList.get(1);
            ((TextView) inflate.findViewById(R.id.search_single_book_related_3_item2_title)).setText(getRelatedProductTitle(aVar.c));
            ((ImageView) inflate.findViewById(R.id.search_single_book_related_3_item2_icon)).setImageResource(getRelatedProductIcon(aVar.c));
            inflate.findViewById(R.id.search_single_book_related_3_item2).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ SearchSingleBookCard b;

                public void onClick(View view) {
                    try {
                        c.a(this.b.getEvnetListener().getFromActivity(), aVar.a);
                        this.b.stateRelatedProductClick(aVar.c);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            stringBuilder.append(',').append(((a) this.mRelatedProductList.get(1)).c);
            View findViewById = inflate.findViewById(R.id.search_single_book_related_3_item3);
            if (this.mRelatedProductList.size() >= 3) {
                findViewById.setVisibility(0);
                aVar = (a) this.mRelatedProductList.get(2);
                ((TextView) inflate.findViewById(R.id.search_single_book_related_3_item3_title)).setText(getRelatedProductTitle(aVar.c));
                ((ImageView) inflate.findViewById(R.id.search_single_book_related_3_item3_icon)).setImageResource(getRelatedProductIcon(aVar.c));
                findViewById.findViewById(R.id.search_single_book_related_3_item3).setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ SearchSingleBookCard b;

                    public void onClick(View view) {
                        try {
                            c.a(this.b.getEvnetListener().getFromActivity(), aVar.a);
                            this.b.stateRelatedProductClick(aVar.c);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                stringBuilder.append(',').append(((a) this.mRelatedProductList.get(2)).c);
            } else {
                findViewById.setVisibility(8);
            }
        }
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, stringBuilder.toString());
        i.a("event_F296", hashMap, ReaderApplication.getApplicationImp());
    }

    private void stateRelatedProductClick(int i) {
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, String.valueOf(i));
        i.a("event_F297", hashMap, ReaderApplication.getApplicationImp());
    }

    private int getRelatedProductIcon(int i) {
        switch (i) {
            case 0:
                return R.drawable.search_single_book_related_product_icon_comic;
            case 2:
                return R.drawable.search_single_book_related_product_icon_game;
            default:
                return R.drawable.search_single_book_related_product_icon_audio;
        }
    }

    private String getRelatedProductTitle(int i) {
        switch (i) {
            case 0:
                return "热改漫画";
            case 1:
                return "真人听书";
            case 2:
                return "同名手游";
            default:
                return "听书";
        }
    }

    protected void expose() {
        super.expose();
        this.mLogMap.put("bid", String.valueOf(this.mBookid));
        if (this.isReCommendCardNow) {
            addRDMStaticsParam(this.mLogMap);
            i.a("event_B167", this.mLogMap, ReaderApplication.getApplicationImp());
            StatisticsManager.a().a("event_B167", this.mLogMap);
        } else if (TextUtils.isEmpty(this.mOverRating)) {
            addRDMStaticsParam(this.mLogMap);
            i.a("event_B148", this.mLogMap, ReaderApplication.getApplicationImp());
            StatisticsManager.a().a("event_B148", this.mLogMap);
        } else {
            addRDMStaticsParam(this.mLogMap);
            i.a("event_B181", this.mLogMap, ReaderApplication.getApplicationImp());
            StatisticsManager.a().a("event_B181", this.mLogMap);
        }
    }

    private void addRDMStaticsParam(Map<String, String> map) {
        if (map != null) {
            map.put("type", "0");
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
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                try {
                    this.mTerms.add(new com.qq.reader.module.bookstore.search.card.SearchBaseCard.a(this, optJSONArray.getJSONObject(i2)));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
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
        this.isOutBook = jSONObject.optInt("outer") == 1;
        this.mRelatedProductList = new ArrayList();
        JSONArray optJSONArray2 = jSONObject.optJSONArray("relateUrls");
        if (optJSONArray2 != null) {
            while (i < optJSONArray2.length()) {
                JSONObject optJSONObject = optJSONArray2.optJSONObject(i);
                if (optJSONObject != null) {
                    if (optJSONObject.optInt("type") == 1) {
                        this.mRelatedProductList.add(new a(this, optJSONObject.optString("NewUrl"), optJSONObject.optString("name"), optJSONObject.optInt("type")));
                    } else {
                        this.mRelatedProductList.add(new a(this, optJSONObject.optString("Url"), optJSONObject.optString("name"), optJSONObject.optInt("type")));
                    }
                }
                i++;
            }
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
        if (this.isReCommendCardNow) {
            i.a("event_B170", this.mLogMap, ReaderApplication.getApplicationImp());
            StatisticsManager.a().a("event_B170", this.mLogMap);
        } else if (TextUtils.isEmpty(this.mOverRating)) {
            addRDMStaticsParam(this.mLogMap);
            i.a("event_B149", this.mLogMap, ReaderApplication.getApplicationImp());
            StatisticsManager.a().a("event_B149", this.mLogMap);
        } else {
            addRDMStaticsParam(this.mLogMap);
            i.a("event_B182", this.mLogMap, ReaderApplication.getApplicationImp());
            StatisticsManager.a().a("event_B182", this.mLogMap);
        }
    }
}
