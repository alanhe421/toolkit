package com.qq.reader.module.bookstore.qnative.card.impl;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.dynamicload.Lib.DLConstants;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.protocol.H5GameChargeTask;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.c.c;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.g;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.card.view.CardMoreView;
import com.qq.reader.view.CustomTailIconTextView;
import com.tencent.feedback.proguard.R;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class RealTimeRecFreeCard_3ColBooks extends a {
    protected static final String JSON_KEY_TITLE = "title";
    private int[] layoutIds = new int[]{R.id.book_0, R.id.book_1, R.id.book_2};
    private int mMaxAuthorTextLength = 6;

    public RealTimeRecFreeCard_3ColBooks(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.free_card_3_col_books_layout;
    }

    public void attachView() {
        ((CardTitle) ap.a(getRootView(), R.id.card_title)).setCardTitle(0, this.mShowTitle, this.mPromotionName, null);
        List itemList = getItemList();
        for (int a : this.layoutIds) {
            ap.a(getRootView(), a).setVisibility(8);
        }
        int i = 0;
        while (itemList != null && i < itemList.size() && i < this.layoutIds.length) {
            View a2 = ap.a(getRootView(), this.layoutIds[i]);
            a2.setVisibility(0);
            fillSingleItem(a2, (g) itemList.get(i));
            i++;
        }
        CardMoreView cardMoreView = (CardMoreView) ap.a(getRootView(), R.id.more_view);
        if (this.mMoreAction == null || itemList == null || itemList.size() <= this.mDispaly) {
            cardMoreView.setVisibility(8);
        } else {
            cardMoreView.setVisibility(0);
            cardMoreView.setText(this.mMoreAction.e);
            cardMoreView.setOnClickListener(new c(this) {
                final /* synthetic */ RealTimeRecFreeCard_3ColBooks a;

                {
                    this.a = r1;
                }

                public void a(View view) {
                    Bundle a = this.a.mMoreAction.a().a();
                    if (a != null) {
                        a.putString("LOCAL_STORE_IN_TITLE", this.a.mShowTitle);
                        a.putString("key_data", this.a.getOrginCardJsonOjb().toString());
                        a.putString("KEY_JUMP_PAGENAME", "PAGE_NAME_REAL_TIME_REC_FREE_BOOK_LIST");
                    }
                    this.a.mMoreAction.a(this.a.getEvnetListener());
                }
            });
        }
        showStatics();
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.mServerTitle = jSONObject.optString("title");
        String str = "pushName";
        List itemList = getItemList();
        if (itemList != null) {
            itemList.clear();
        } else {
            itemList = new ArrayList();
        }
        this.mPromotionName = jSONObject.optString("pushName");
        JSONArray optJSONArray = jSONObject.optJSONArray("bookList");
        int i = 0;
        while (optJSONArray != null && i < optJSONArray.length()) {
            s gVar = new g();
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                gVar.parseData(optJSONObject);
                addItem(gVar);
            }
            i++;
        }
        if (itemList == null || itemList.size() < this.mDispaly) {
            return false;
        }
        return true;
    }

    private void showStatics() {
        CharSequence feedStatString = getFeedStatString();
        if (!TextUtils.isEmpty(feedStatString)) {
            Map hashMap = new HashMap();
            hashMap.put("event_feed_exposure", feedStatString);
            StatisticsManager.a().a("event_feed_exposure", hashMap);
        }
        i.a("event_shown_" + getCardId(), null, ReaderApplication.getApplicationImp());
    }

    private void clickStatics() {
        i.a("event_clicked_" + getCardId(), null, ReaderApplication.getApplicationImp());
    }

    private String getFeedStatString() {
        if (getItemList() == null || getItemList().size() < this.mDispaly) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.mDispaly; i++) {
            g gVar = (g) getItemList().get(i);
            if (gVar != null) {
                long m = gVar.m();
                String alg = gVar.getAlg();
                if (i == 0) {
                    stringBuilder.append(m).append(":").append(1).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(alg).append(DLConstants.DEPENDENCY_PACKAGE_DIV);
                } else {
                    stringBuilder.append(",").append(m).append(":").append(1).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(alg).append(DLConstants.DEPENDENCY_PACKAGE_DIV);
                }
            }
        }
        return stringBuilder.toString();
    }

    private void fillSingleItem(View view, final g gVar) {
        String k = gVar.k();
        String l = gVar.l();
        setImage((ImageView) ap.a(view, R.id.concept_cover_img), ao.g(gVar.m()), null);
        ap.a(view, R.id.search_count).setVisibility(8);
        TextView textView = (TextView) ap.a(view, R.id.concept_author);
        CharSequence q = gVar.q();
        if (q.length() > this.mMaxAuthorTextLength) {
            q = q.substring(0, this.mMaxAuthorTextLength - 1) + "…";
        }
        textView.setText(q);
        textView = (TextView) ap.a(view, R.id.concept_title);
        textView.setText(gVar.n());
        TextView textView2 = (TextView) ap.a(view, R.id.concept_tag_subscript);
        CustomTailIconTextView customTailIconTextView = (CustomTailIconTextView) ap.a(view, R.id.search_singlecard_title_container);
        TextView textView3 = (TextView) ap.a(view, R.id.concept_content);
        if (TextUtils.isEmpty(gVar.s())) {
            textView3.setVisibility(8);
            textView.setMaxLines(2);
            customTailIconTextView.setMaxlines(2);
        } else {
            textView.setMaxLines(1);
            customTailIconTextView.setMaxlines(1);
            textView3.setVisibility(0);
            textView3.setText(gVar.s());
        }
        textView = (TextView) ap.a(view, R.id.limitprice);
        textView2 = (TextView) ap.a(view, R.id.concept_tag_1);
        textView3 = (TextView) ap.a(view, R.id.concept_tag_2);
        textView2.setVisibility(8);
        textView3.setVisibility(8);
        textView.setVisibility(8);
        if (TextUtils.isEmpty(l)) {
            textView2.setVisibility(8);
            textView3.setVisibility(8);
            textView.setVisibility(8);
        } else {
            String str;
            textView2.setVisibility(8);
            textView3.setVisibility(8);
            textView.setVisibility(0);
            if (k == null) {
                str = "";
            } else {
                str = k;
            }
            if (!TextUtils.isEmpty(l)) {
                CharSequence spannableString = new SpannableString(str + " " + l);
                spannableString.setSpan(new StrikethroughSpan(), 0, str.length(), 33);
                spannableString.setSpan(new ForegroundColorSpan(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c103)), 0, str.length(), 33);
                spannableString.setSpan(new ForegroundColorSpan(ReaderApplication.getApplicationImp().getResources().getColor(R.color.book_store_card_price_color)), str.length() + 1, (str.length() + 1) + l.length(), 33);
                textView.setText(spannableString);
            }
        }
        view.setOnClickListener(new c(this) {
            final /* synthetic */ RealTimeRecFreeCard_3ColBooks b;

            public void a(View view) {
                if (gVar != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("uniteqqreader://nativepage/book/detail?").append("bid=").append(gVar.m()).append("&alg=").append(gVar.getAlg()).append(H5GameChargeTask.ITEMID).append(gVar.m());
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("ext_info_id", gVar.m());
                        jSONObject.put("itemid", gVar.m());
                        jSONObject.put(s.ALG, gVar.getAlg());
                        stringBuilder.append("&statInfo=").append(URLEncoder.encode(jSONObject.toString(), "utf-8"));
                        com.qq.reader.qurl.c.a(this.b.getEvnetListener().getFromActivity(), stringBuilder.toString(), null);
                    } catch (Exception e) {
                    }
                    this.b.clickStatics();
                }
            }
        });
    }
}
