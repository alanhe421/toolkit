package com.qq.reader.module.feed.card;

import android.app.Activity;
import android.view.View;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.feed.card.view.FeedColumnSingleBookOneFourthView;
import com.qq.reader.module.feed.card.view.FeedColumnSingleBookOneHalfPicGodView;
import com.qq.reader.module.feed.card.view.FeedColumnSingleBookOneHalfPicView;
import com.qq.reader.module.feed.data.impl.a;
import com.qq.reader.module.feed.data.impl.b;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class FeedColumnPersonalTwoCard extends FeedMultiClickBaseCard implements b {
    private ArrayList<a> cardChangeAndRefreshUIListeners = new ArrayList();
    private ArrayList<com.qq.reader.module.feed.b.a> mFeedOperationCommonModels = new ArrayList();
    private ArrayList<View> viewList = new ArrayList();

    public FeedColumnPersonalTwoCard(com.qq.reader.module.bookstore.qnative.page.b bVar, String str) {
        super(bVar, str);
    }

    public void attachView() {
        int aS = d.aS(ReaderApplication.getApplicationImp().getApplicationContext());
        if (aS == 1) {
            i.a("event_F36", null, ReaderApplication.getApplicationImp().getApplicationContext());
        } else if (aS == 2) {
            i.a("event_F35", null, ReaderApplication.getApplicationImp().getApplicationContext());
        } else {
            i.a("event_F37", null, ReaderApplication.getApplicationImp().getApplicationContext());
        }
        if (this.cardChangeAndRefreshUIListeners.size() > 0) {
            this.cardChangeAndRefreshUIListeners.clear();
        }
        FeedColumnSingleBookOneHalfPicGodView feedColumnSingleBookOneHalfPicGodView = (FeedColumnSingleBookOneHalfPicGodView) ap.a(getRootView(), R.id.column_layout_1);
        FeedColumnSingleBookOneFourthView feedColumnSingleBookOneFourthView = (FeedColumnSingleBookOneFourthView) ap.a(getRootView(), R.id.column_layout_2);
        FeedColumnSingleBookOneFourthView feedColumnSingleBookOneFourthView2 = (FeedColumnSingleBookOneFourthView) ap.a(getRootView(), R.id.column_layout_3);
        FeedColumnSingleBookOneHalfPicView feedColumnSingleBookOneHalfPicView = (FeedColumnSingleBookOneHalfPicView) ap.a(getRootView(), R.id.column_layout_4);
        FeedColumnSingleBookOneHalfPicView feedColumnSingleBookOneHalfPicView2 = (FeedColumnSingleBookOneHalfPicView) ap.a(getRootView(), R.id.column_layout_5);
        Activity fromActivity = getEvnetListener().getFromActivity();
        feedColumnSingleBookOneHalfPicGodView.a(fromActivity);
        feedColumnSingleBookOneFourthView.a(fromActivity);
        feedColumnSingleBookOneFourthView2.a(fromActivity);
        feedColumnSingleBookOneHalfPicView.a(fromActivity);
        feedColumnSingleBookOneHalfPicView2.a(fromActivity);
        this.cardChangeAndRefreshUIListeners.add(feedColumnSingleBookOneHalfPicGodView);
        this.cardChangeAndRefreshUIListeners.add(feedColumnSingleBookOneFourthView);
        this.cardChangeAndRefreshUIListeners.add(feedColumnSingleBookOneFourthView2);
        this.cardChangeAndRefreshUIListeners.add(feedColumnSingleBookOneHalfPicView);
        this.cardChangeAndRefreshUIListeners.add(feedColumnSingleBookOneHalfPicView2);
        for (int i = 0; i < this.cardChangeAndRefreshUIListeners.size(); i++) {
            ((a) this.cardChangeAndRefreshUIListeners.get(i)).a((com.qq.reader.module.feed.b.a) this.mFeedOperationCommonModels.get(i));
        }
    }

    public int getResLayoutId() {
        return R.layout.feed_column_personal_two_card_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        if (jSONObject == null || jSONObject.optInt("style") != 6) {
            return false;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray(MessageKey.MSG_CONTENT);
        if (optJSONArray == null) {
            return false;
        }
        if (this.mFeedOperationCommonModels != null && this.mFeedOperationCommonModels.size() > 0) {
            this.mFeedOperationCommonModels.clear();
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            com.qq.reader.module.feed.b.a a = com.qq.reader.module.feed.c.a.a(optJSONArray.optJSONObject(i));
            if (a != null) {
                this.mFeedOperationCommonModels.add(a);
            }
        }
        if (this.mFeedOperationCommonModels.size() >= 5) {
            return true;
        }
        return false;
    }

    protected ArrayList<View> getViews() {
        return this.viewList;
    }

    protected void doClick(final View view) {
        if (view != null) {
            view.setSelected(true);
            view.postDelayed(new Runnable(this) {
                final /* synthetic */ FeedColumnPersonalTwoCard b;

                public void run() {
                    view.setSelected(false);
                }
            }, 100);
        }
    }

    public boolean swipeEnable() {
        return false;
    }

    public void change() {
        for (int i = 0; i < this.mFeedOperationCommonModels.size(); i++) {
            this.mFeedOperationCommonModels.set(i, ((com.qq.reader.module.feed.b.a) this.mFeedOperationCommonModels.get(i)).a((com.qq.reader.module.feed.b.a) this.mFeedOperationCommonModels.get(i)));
        }
    }
}
