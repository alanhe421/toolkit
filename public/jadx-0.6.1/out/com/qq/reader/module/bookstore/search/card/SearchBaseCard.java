package com.qq.reader.module.bookstore.search.card;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderShortTask;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.search.b;
import com.qq.reader.qurl.JumpActivityParameter;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

public abstract class SearchBaseCard extends com.qq.reader.module.bookstore.qnative.card.a implements b {
    private a exchangeContoller = null;
    private boolean isExposed;
    public int mHittype;
    private JSONObject mJsondataObj = null;
    protected Map<String, String> mLogMap = new HashMap();
    protected String mMsgId;
    private int mPageNo;
    protected String mQURL;
    protected String mRecommendUrl;
    protected String mSearchKey;
    protected ArrayList<a> mTermList = null;
    protected String mTitle;
    ReaderShortTask shortTask;

    public class a {
        public int a = 0;
        public String b;
        public String c;
        final /* synthetic */ SearchBaseCard d;

        public a(SearchBaseCard searchBaseCard, JSONObject jSONObject) {
            this.d = searchBaseCard;
            this.a = jSONObject.optInt("matched");
            this.b = jSONObject.optString("term");
            this.c = jSONObject.optString("type");
        }
    }

    public SearchBaseCard(com.qq.reader.module.bookstore.qnative.page.b bVar, String str) {
        super(bVar, str);
        if (enableExchange()) {
            this.exchangeContoller = new a();
        }
    }

    public int getPageNo() {
        return this.mPageNo;
    }

    public void setPageNo(int i) {
        this.mPageNo = i;
    }

    public boolean fillData(JSONObject jSONObject) {
        if (jSONObject == null) {
            return false;
        }
        this.mJsondataObj = jSONObject;
        this.mMsgId = jSONObject.optString("id");
        setCardId(this.mMsgId);
        try {
            if (parseData(jSONObject.optJSONObject("info"))) {
                this.mDataState = 1001;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject(s.STATPARAM_KEY);
            if (optJSONObject != null) {
                this.mHittype = optJSONObject.optInt("exact", -1);
                Iterator keys = optJSONObject.keys();
                while (keys.hasNext()) {
                    String obj = keys.next().toString();
                    this.mLogMap.put(obj, optJSONObject.optString(obj));
                }
                this.mLogMap.put(s.STATPARAM_KEY, optJSONObject.toString());
            }
            return true;
        } catch (Exception e) {
            c.a("SearchBaseCard", e.getMessage());
            return false;
        }
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.isExposed = false;
        return true;
    }

    public void attachView() {
        if (!this.isExposed) {
            expose();
            this.isExposed = true;
        }
    }

    protected void expose() {
        invokeRDMSearchCommonParams(this.mLogMap);
    }

    protected SpannableStringBuilder createSearchHitsString(String str, ArrayList<a> arrayList) {
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
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(getRootView().getResources().getColor(R.color.text_color_c401)), str.indexOf(str2), str2.length() + str.indexOf(str2), 33);
                }
            }
        }
        return spannableStringBuilder;
    }

    public void doAnimation(View view) {
        this.exchangeContoller.a(view, new AnimatorListenerAdapter(this) {
            final /* synthetic */ SearchBaseCard a;

            {
                this.a = r1;
            }

            public void onAnimationEnd(Animator animator) {
                this.a.onHideOld();
            }
        }, new AnimatorListenerAdapter(this) {
            final /* synthetic */ SearchBaseCard a;

            {
                this.a = r1;
            }

            public void onAnimationEnd(Animator animator) {
                this.a.onShowNew();
            }
        });
    }

    public void parserExchangeData() {
        if (this.shortTask != null) {
            g.a().b(this.shortTask);
        }
        this.shortTask = new ReaderShortTask() {
            public void run() {
                try {
                    super.run();
                    if (SearchBaseCard.this.fillData(SearchBaseCard.this.exchangeContoller.a())) {
                        SearchBaseCard.this.exchangeContoller.b();
                        Bundle bundle = new Bundle();
                        bundle.putString("function_type", "PARA_TYPE_REFRESH");
                        SearchBaseCard.this.getEvnetListener().doFunction(bundle);
                        return;
                    }
                    SearchBaseCard.this.exchangeContoller.d();
                    c.e("ExchangeContoller", "RESET by parse false");
                } catch (Exception e) {
                    SearchBaseCard.this.exchangeContoller.d();
                    c.e("ExchangeContoller", "RESET by Exception : " + e.toString());
                }
            }
        };
        g.a().a(this.shortTask);
    }

    public void attachView(View view) {
        super.attachView(view);
    }

    public void doClickedCard() {
        if (com.qq.reader.qurl.c.a(this.mQURL)) {
            try {
                com.qq.reader.qurl.c.a(getEvnetListener().getFromActivity(), this.mQURL, new JumpActivityParameter().a(this.mLogMap));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.exchangeContoller != null) {
            this.exchangeContoller.a(getExchangeUrl());
        }
    }

    public void onHideOld() {
        attachView(getRootView());
    }

    public void onShowNew() {
        c.e("animation", "show - new - data");
    }

    public String getExchangeUrl() {
        return "";
    }

    public boolean enableExchange() {
        return false;
    }

    public boolean isNeedExchange() {
        boolean z = this.exchangeContoller != null && this.exchangeContoller.a(System.currentTimeMillis());
        c.e("ExchangeContoller", "RESET by isNeedExchange");
        return z;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public boolean isAnimationReady() {
        return this.exchangeContoller != null && this.exchangeContoller.c();
    }

    public String getSearchKey() {
        return this.mSearchKey;
    }

    public void setSearchKey(String str) {
        this.mSearchKey = str;
    }

    public int getHittype() {
        return this.mHittype;
    }

    public void setHittype(int i) {
        this.mHittype = i;
    }

    protected void invokeRDMSearchCommonParams(Map<String, String> map) {
        if (map == null) {
            map = new HashMap();
        }
        map.put("id", this.mMsgId);
        map.put("key", this.mSearchKey);
        map.put("page", String.valueOf(getPageNo()));
        if (getPageNo() == 0) {
            map.put("index", String.valueOf(this.mShowIndexOnPage));
        }
    }
}
