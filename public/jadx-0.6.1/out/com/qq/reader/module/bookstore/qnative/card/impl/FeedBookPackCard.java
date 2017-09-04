package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.View;
import android.view.View.OnClickListener;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.item.t;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.card.view.CardMoreView;
import com.qq.reader.module.feed.card.view.FeedBookPackView;
import com.tencent.feedback.proguard.R;
import org.json.JSONArray;
import org.json.JSONObject;

public class FeedBookPackCard extends a {
    protected static final String JSON_KEY_BOOKPACK = "bags";
    protected static final String JSON_KEY_PROMOTION_NAME = "desc";
    protected static final String JSON_KEY_TITLE = "title";

    public FeedBookPackCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.concept_bookpack_cardlayout;
    }

    public void attachView() {
        int size = getItemList().size();
        if (size > 0) {
            ((CardTitle) ap.a(getRootView(), R.id.card_title)).setCardTitle(0, this.mShowTitle, this.mPromotionName, null);
            FeedBookPackView feedBookPackView = (FeedBookPackView) ap.a(getRootView(), R.id.bookcollectlist_item0);
            final t tVar = (t) getItemList().get(0);
            feedBookPackView.setBookBagItemData(tVar);
            feedBookPackView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ FeedBookPackCard b;

                public void onClick(View view) {
                    if (this.b.getEvnetListener() != null) {
                        tVar.a(this.b.getEvnetListener());
                    }
                }
            });
            FeedBookPackView feedBookPackView2 = (FeedBookPackView) ap.a(getRootView(), R.id.bookcollectlist_item1);
            if (size > 1) {
                feedBookPackView2.setVisibility(0);
                feedBookPackView.setDividerVisible(true);
                final t tVar2 = (t) getItemList().get(1);
                feedBookPackView2.setBookBagItemData(tVar2);
                feedBookPackView2.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ FeedBookPackCard b;

                    public void onClick(View view) {
                        if (this.b.getEvnetListener() != null) {
                            tVar2.a(this.b.getEvnetListener());
                        }
                    }
                });
            } else {
                feedBookPackView2.setVisibility(8);
                feedBookPackView.setDividerVisible(false);
            }
            feedBookPackView = (FeedBookPackView) ap.a(getRootView(), R.id.bookcollectlist_item2);
            if (size > 2) {
                feedBookPackView.setVisibility(0);
                feedBookPackView2.setDividerVisible(true);
                tVar = (t) getItemList().get(2);
                feedBookPackView.setBookBagItemData(tVar);
                feedBookPackView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ FeedBookPackCard b;

                    public void onClick(View view) {
                        if (this.b.getEvnetListener() != null) {
                            tVar.a(this.b.getEvnetListener());
                        }
                    }
                });
            } else {
                feedBookPackView.setVisibility(8);
                feedBookPackView2.setDividerVisible(false);
            }
            feedBookPackView.setDividerVisible(false);
            CardMoreView cardMoreView = (CardMoreView) ap.a(getRootView(), R.id.localstore_moreaction);
            cardMoreView.setText("更多超值包");
            if (this.mMoreAction != null) {
                cardMoreView.setVisibility(0);
                cardMoreView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ FeedBookPackCard a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.mMoreAction.a(this.a.getEvnetListener());
                        i.a("event_C97", null, ReaderApplication.getApplicationImp());
                    }
                });
                return;
            }
            cardMoreView.setVisibility(8);
        }
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        getItemList().clear();
        this.mServerTitle = jSONObject.optString("title");
        this.mPromotionName = jSONObject.optString("desc");
        JSONArray optJSONArray = jSONObject.optJSONArray(JSON_KEY_BOOKPACK);
        if (optJSONArray == null) {
            return false;
        }
        int length = optJSONArray.length();
        if (length <= 0) {
            return false;
        }
        while (i < length) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
            s tVar = new t();
            tVar.parseData(jSONObject2);
            addItem(tVar);
            i++;
        }
        return true;
    }
}
