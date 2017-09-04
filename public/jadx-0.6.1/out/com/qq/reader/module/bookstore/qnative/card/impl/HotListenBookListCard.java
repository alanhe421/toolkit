package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.View;
import android.view.View.OnClickListener;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.ab;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.card.view.CardMoreView;
import com.qq.reader.module.feed.card.view.ListenBooklistView;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class HotListenBookListCard extends a {
    protected static final String JSON_KEY_ADLIST = "adList";
    protected static final String JSON_KEY_PROMOTION_NAME = "desc";
    protected static final String JSON_KEY_TITLE = "title";

    public HotListenBookListCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.concept_listenbooklist_cardlayout;
    }

    private void showStatics() {
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, getCardId());
        i.a("event_B232", hashMap, ReaderApplication.getApplicationImp());
    }

    private void clickStatics() {
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, getCardId());
        i.a("event_B233", hashMap, ReaderApplication.getApplicationImp());
    }

    public void attachView() {
        int size = getItemList().size();
        if (size > 0) {
            ((CardTitle) ap.a(getRootView(), R.id.title_layout)).setCardTitle(this.mIconIndex, this.mShowTitle, this.mPromotionName, null);
            ListenBooklistView listenBooklistView = (ListenBooklistView) ap.a(getRootView(), R.id.bookcollectlist_item0);
            final ab abVar = (ab) getItemList().get(0);
            listenBooklistView.setBookCollectListItemData(abVar);
            listenBooklistView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ HotListenBookListCard b;

                public void onClick(View view) {
                    if (this.b.getEvnetListener() != null) {
                        this.b.clickStatics();
                        abVar.a(this.b.getEvnetListener());
                    }
                }
            });
            listenBooklistView = (ListenBooklistView) ap.a(getRootView(), R.id.bookcollectlist_item1);
            if (size > 1) {
                listenBooklistView.setVisibility(0);
                listenBooklistView.setDividerVisible(true);
                abVar = (ab) getItemList().get(1);
                listenBooklistView.setBookCollectListItemData(abVar);
                listenBooklistView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ HotListenBookListCard b;

                    public void onClick(View view) {
                        if (this.b.getEvnetListener() != null) {
                            this.b.clickStatics();
                            abVar.a(this.b.getEvnetListener());
                        }
                    }
                });
            } else {
                listenBooklistView.setVisibility(8);
                listenBooklistView.setDividerVisible(false);
            }
            listenBooklistView = (ListenBooklistView) ap.a(getRootView(), R.id.bookcollectlist_item2);
            if (size > 2) {
                listenBooklistView.setVisibility(0);
                abVar = (ab) getItemList().get(2);
                listenBooklistView.setBookCollectListItemData(abVar);
                listenBooklistView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ HotListenBookListCard b;

                    public void onClick(View view) {
                        if (this.b.getEvnetListener() != null) {
                            this.b.clickStatics();
                            abVar.a(this.b.getEvnetListener());
                        }
                    }
                });
            } else {
                listenBooklistView.setVisibility(8);
            }
            listenBooklistView.setDividerVisible(false);
            CardMoreView cardMoreView = (CardMoreView) ap.a(getRootView(), R.id.localstore_moreaction);
            cardMoreView.setText("查看更多");
            if (this.mMoreAction == null || size <= 3) {
                cardMoreView.setVisibility(8);
            } else {
                cardMoreView.setVisibility(0);
                cardMoreView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ HotListenBookListCard a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.mMoreAction.a(this.a.getEvnetListener());
                        i.a("event_C214", null, ReaderApplication.getApplicationImp());
                    }
                });
            }
        }
        showStatics();
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        getItemList().clear();
        this.mServerTitle = jSONObject.optString("title");
        this.mPromotionName = jSONObject.optString("desc");
        JSONArray optJSONArray = jSONObject.optJSONArray("adList");
        if (optJSONArray == null) {
            return false;
        }
        int length = optJSONArray.length();
        if (length <= 0) {
            return false;
        }
        while (i < length) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
            s abVar = new ab();
            abVar.parseData(jSONObject2);
            addItem(abVar);
            i++;
        }
        return true;
    }
}
