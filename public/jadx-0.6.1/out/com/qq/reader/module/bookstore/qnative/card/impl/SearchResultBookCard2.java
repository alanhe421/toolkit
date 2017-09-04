package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.activity.NativeBookStoreConfigDetailActivity;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import com.qq.reader.view.CustomTailIconTextView;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tencent.tls.platform.SigType;

public class SearchResultBookCard2 extends FeedBaseCard {
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
    private static final String JSON_KEY_STAT_ALGINFO = "alg_info";
    private static final String JSON_KEY_STAT_PARAMS = "stat_params";
    private static final String JSON_KEY_STAT_TAGS = "tags";
    private static final String JSON_KEY_TERMS = "terms";
    private static final String JSON_KEY_TITLE = "title";
    private static final String JSON_KEY_WORDCOUNT = "totalWords";
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
    private int mFinished;
    private String mIconColor;
    private String mIconDest;
    private String mKey;
    private int mLftag;
    private int mMaxAuthorTextLength = 6;
    private String mOrigin;
    private String mStatAlgInfo;
    private JSONObject mStatParams;
    private String mStatTags;
    private ArrayList<a> mTerms = new ArrayList();
    private long mWordcount;
    private long mlimitEndTime;
    private long mlimitStartTime;

    public class a {
        public int a = 0;
        public String b;
        public String c;
        final /* synthetic */ SearchResultBookCard2 d;

        public a(SearchResultBookCard2 searchResultBookCard2, JSONObject jSONObject) {
            this.d = searchResultBookCard2;
            this.a = jSONObject.optInt("matched");
            this.b = jSONObject.optString("term");
            this.c = jSONObject.optString("type");
        }
    }

    public SearchResultBookCard2(b bVar, String str, String str2) {
        super(bVar, str);
        this.mKey = str2;
    }

    public int getResLayoutId() {
        return R.layout.concept_bookitem_layout;
    }

    public void attachView() {
        if (!(ao.m(this.mBookid) || this.isUploadStat)) {
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, String.valueOf(this.mBookid));
            i.a("event_A203", hashMap, ReaderApplication.getApplicationImp());
            this.isUploadStat = true;
        }
        getRootView().setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SearchResultBookCard2 a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                long access$000 = this.a.mBookid;
                if (access$000 > 0) {
                    if (!ao.m(access$000)) {
                        Map hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, String.valueOf(this.a.mBookid));
                        i.a("event_A204", hashMap, ReaderApplication.getApplicationImp());
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("KEY_JUMP_PAGENAME", "DetailPage");
                    bundle.putLong("URL_BUILD_PERE_BOOK_ID", access$000);
                    bundle.putString("searchkey", this.a.mKey);
                    if (this.a.mStatParams != null) {
                        bundle.putString("stat_params", this.a.mStatParams.toString());
                    }
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
        String author = getAuthor();
        if (author.length() > this.mMaxAuthorTextLength) {
            author = author.substring(0, this.mMaxAuthorTextLength - 1) + "…";
        }
        textView.setText(createSpannableString(author, this.mTerms));
        ((TextView) ap.a(getRootView(), R.id.concept_tag_subscript)).setVisibility(8);
        textView = (TextView) ap.a(getRootView(), R.id.concept_title);
        if (isClickedStatus()) {
            textView.setSelected(true);
        } else {
            textView.setSelected(false);
        }
        CustomTailIconTextView customTailIconTextView = (CustomTailIconTextView) ap.a(getRootView(), R.id.feed_title_container);
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
        textView.setText(createSpannableString(getTitle(), this.mTerms));
        textView = (TextView) ap.a(getRootView(), R.id.concept_tag_1);
        if (TextUtils.isEmpty(getCateL2Name()) || "null".equals(getCateL2Name())) {
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
            if (TextUtils.isEmpty(getCateTag()) || "null".equals(getCateTag())) {
                textView.setText(getCateL3Name());
            } else {
                textView.setText(getCateTag());
            }
        }
        textView = (TextView) ap.a(getRootView(), R.id.concept_tag_3);
        if (this.mWordcount <= 0) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
            textView.setText(s.countTransform(this.mWordcount) + "字");
        }
        ap.a(getRootView(), R.id.concept_cover_tag).setVisibility(8);
        View a = ap.a(getRootView(), R.id.concept_tingbook_tag);
        if (this.mAudio == 1) {
            a.setVisibility(0);
        } else {
            a.setVisibility(8);
        }
    }

    public void doClickedCard() {
        super.doClickedCard();
        ap.a(getRootView(), R.id.concept_title).setSelected(true);
    }

    public boolean parseData(JSONObject jSONObject) {
        int i = 0;
        this.mFinished = jSONObject.optInt(JSON_KEY_FINISHED);
        this.mAudio = jSONObject.optInt(JSON_KEY_AUDIO, 0);
        this.mWordcount = jSONObject.optLong(JSON_KEY_WORDCOUNT);
        this.mIconColor = jSONObject.optString(JSON_KEY_ICONCOLOR);
        this.mTitle = jSONObject.optString("title");
        this.mDesc = jSONObject.optString(JSON_KEY_DESC);
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
            while (i < optJSONArray.length()) {
                try {
                    this.mTerms.add(new a(this, optJSONArray.getJSONObject(i)));
                } catch (JSONException e2) {
                    e2.printStackTrace();
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

    private SpannableStringBuilder createSpannableString(String str, ArrayList<a> arrayList) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(str);
        if (arrayList == null || arrayList.size() == 0) {
            return spannableStringBuilder;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            a aVar = (a) arrayList.get(i);
            if (aVar.a == 1) {
                String str2 = aVar.b;
                if (str.contains(str2)) {
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(getRootView().getResources().getColor(R.color.search_tool_item_textcolor)), str.indexOf(str2), str2.length() + str.indexOf(str2), 33);
                }
            }
        }
        return spannableStringBuilder;
    }
}
