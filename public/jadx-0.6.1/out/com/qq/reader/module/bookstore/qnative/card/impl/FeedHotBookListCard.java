package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.View;
import android.view.View.OnClickListener;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.item.u;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.card.view.CardMoreView;
import com.qq.reader.module.feed.card.view.FeedHotBooklistView;
import com.tencent.feedback.proguard.R;
import org.json.JSONArray;
import org.json.JSONObject;

public class FeedHotBookListCard extends a {
    protected static final String JSON_KEY_PROMOTION_NAME = "desc";
    protected static final String JSON_KEY_TITLE = "title";
    protected static final String JSON_KEY_TOPICS = "topicList";

    public FeedHotBookListCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.concept_hotbooklist_cardlayout;
    }

    public void attachView() {
        int size = getItemList().size();
        if (size > 0) {
            ((CardTitle) ap.a(getRootView(), R.id.title_layout)).setCardTitle(37, this.mShowTitle, this.mPromotionName, null);
            FeedHotBooklistView feedHotBooklistView = (FeedHotBooklistView) ap.a(getRootView(), R.id.bookcollectlist_item0);
            final u uVar = (u) getItemList().get(0);
            feedHotBooklistView.setBookCollectListItemData(uVar);
            feedHotBooklistView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ FeedHotBookListCard b;

                public void onClick(View view) {
                    if (this.b.getEvnetListener() != null) {
                        uVar.a(this.b.getEvnetListener());
                    }
                }
            });
            feedHotBooklistView = (FeedHotBooklistView) ap.a(getRootView(), R.id.bookcollectlist_item1);
            if (size > 1) {
                feedHotBooklistView.setVisibility(0);
                feedHotBooklistView.setDividerVisible(true);
                uVar = (u) getItemList().get(1);
                feedHotBooklistView.setBookCollectListItemData(uVar);
                feedHotBooklistView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ FeedHotBookListCard b;

                    public void onClick(View view) {
                        if (this.b.getEvnetListener() != null) {
                            uVar.a(this.b.getEvnetListener());
                        }
                    }
                });
            } else {
                feedHotBooklistView.setVisibility(8);
                feedHotBooklistView.setDividerVisible(false);
            }
            feedHotBooklistView = (FeedHotBooklistView) ap.a(getRootView(), R.id.bookcollectlist_item2);
            if (size > 2) {
                feedHotBooklistView.setVisibility(0);
                uVar = (u) getItemList().get(2);
                feedHotBooklistView.setBookCollectListItemData(uVar);
                feedHotBooklistView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ FeedHotBookListCard b;

                    public void onClick(View view) {
                        if (this.b.getEvnetListener() != null) {
                            uVar.a(this.b.getEvnetListener());
                        }
                    }
                });
            } else {
                feedHotBooklistView.setVisibility(8);
            }
            feedHotBooklistView.setDividerVisible(false);
            CardMoreView cardMoreView = (CardMoreView) ap.a(getRootView(), R.id.localstore_moreaction);
            cardMoreView.setText("更多精彩书单");
            if (this.mMoreAction != null) {
                cardMoreView.setVisibility(0);
                cardMoreView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ FeedHotBookListCard a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.mMoreAction.a(this.a.getEvnetListener());
                        i.a("event_C95", null, ReaderApplication.getApplicationImp());
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
        JSONArray optJSONArray = jSONObject.optJSONArray(JSON_KEY_TOPICS);
        if (optJSONArray == null) {
            return false;
        }
        int length = optJSONArray.length();
        if (length <= 0) {
            return false;
        }
        while (i < length) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
            s uVar = new u();
            uVar.parseData(jSONObject2);
            addItem(uVar);
            i++;
        }
        return true;
    }
}
