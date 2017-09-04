package com.qq.reader.module.feed.activity;

import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.view.pullupdownlist.XListView$a;

class FeedGoogleCardsStyleBFragment$19 implements XListView$a {
    final /* synthetic */ FeedGoogleCardsStyleBFragment a;

    FeedGoogleCardsStyleBFragment$19(FeedGoogleCardsStyleBFragment feedGoogleCardsStyleBFragment) {
        this.a = feedGoogleCardsStyleBFragment;
    }

    public void a() {
        FeedGoogleCardsStyleBFragment.access$600(this.a, this.a.mFeedAdapter.h(), 0);
        i.a("event_C112", null, ReaderApplication.getApplicationImp());
    }
}
