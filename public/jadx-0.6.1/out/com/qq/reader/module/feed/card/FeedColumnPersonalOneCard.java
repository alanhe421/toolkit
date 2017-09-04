package com.qq.reader.module.feed.card;

import android.app.Activity;
import android.view.View;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.feed.card.view.FeedColumnPersonalityBooksOneHalfView;
import com.qq.reader.module.feed.card.view.FeedColumnSingleBookOneHalfPicView;
import com.qq.reader.module.feed.card.view.FeedColumnThreeBooksOneHalfCoverOneView;
import com.qq.reader.module.feed.data.impl.a;
import com.qq.reader.module.feed.data.impl.b;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class FeedColumnPersonalOneCard extends FeedMultiClickBaseCard implements b {
    private ArrayList<a> cardChangeAndRefreshUIListeners = new ArrayList();
    private ArrayList<com.qq.reader.module.feed.b.a> mFeedOperationCommonModels = new ArrayList();
    private ArrayList<View> viewList = new ArrayList();

    public FeedColumnPersonalOneCard(com.qq.reader.module.bookstore.qnative.page.b bVar, String str) {
        super(bVar, str);
    }

    public void attachView() {
        if (this.cardChangeAndRefreshUIListeners.size() > 0) {
            this.cardChangeAndRefreshUIListeners.clear();
        }
        int aS = d.aS(ReaderApplication.getApplicationImp().getApplicationContext());
        if (aS == 1) {
            i.a("event_F33", null, ReaderApplication.getApplicationImp().getApplicationContext());
        } else if (aS == 2) {
            i.a("event_F32", null, ReaderApplication.getApplicationImp().getApplicationContext());
        } else {
            i.a("event_F34", null, ReaderApplication.getApplicationImp().getApplicationContext());
        }
        Activity fromActivity = getEvnetListener().getFromActivity();
        FeedColumnPersonalityBooksOneHalfView feedColumnPersonalityBooksOneHalfView = (FeedColumnPersonalityBooksOneHalfView) ap.a(getRootView(), R.id.column_layout_1);
        feedColumnPersonalityBooksOneHalfView.a(fromActivity);
        feedColumnPersonalityBooksOneHalfView.a(getEvnetListener());
        this.cardChangeAndRefreshUIListeners.add(feedColumnPersonalityBooksOneHalfView);
        FeedColumnThreeBooksOneHalfCoverOneView feedColumnThreeBooksOneHalfCoverOneView = (FeedColumnThreeBooksOneHalfCoverOneView) ap.a(getRootView(), R.id.column_layout_2);
        feedColumnThreeBooksOneHalfCoverOneView.a(fromActivity);
        this.cardChangeAndRefreshUIListeners.add(feedColumnThreeBooksOneHalfCoverOneView);
        feedColumnThreeBooksOneHalfCoverOneView = (FeedColumnThreeBooksOneHalfCoverOneView) ap.a(getRootView(), R.id.column_layout_3);
        feedColumnThreeBooksOneHalfCoverOneView.a(fromActivity);
        this.cardChangeAndRefreshUIListeners.add(feedColumnThreeBooksOneHalfCoverOneView);
        FeedColumnSingleBookOneHalfPicView feedColumnSingleBookOneHalfPicView = (FeedColumnSingleBookOneHalfPicView) ap.a(getRootView(), R.id.column_layout_4);
        feedColumnSingleBookOneHalfPicView.a(fromActivity);
        this.cardChangeAndRefreshUIListeners.add(feedColumnSingleBookOneHalfPicView);
        for (int i = 0; i < this.cardChangeAndRefreshUIListeners.size(); i++) {
            ((a) this.cardChangeAndRefreshUIListeners.get(i)).a((com.qq.reader.module.feed.b.a) this.mFeedOperationCommonModels.get(i));
        }
    }

    public int getResLayoutId() {
        return R.layout.feed_column_personal_one_card_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        if (jSONObject == null || jSONObject.optInt("style") != 5) {
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
        if (this.mFeedOperationCommonModels.size() >= 4) {
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
                final /* synthetic */ FeedColumnPersonalOneCard b;

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
