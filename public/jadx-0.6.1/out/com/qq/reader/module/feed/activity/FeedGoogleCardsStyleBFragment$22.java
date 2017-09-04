package com.qq.reader.module.feed.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

class FeedGoogleCardsStyleBFragment$22 implements OnItemLongClickListener {
    final /* synthetic */ FeedGoogleCardsStyleBFragment a;

    FeedGoogleCardsStyleBFragment$22(FeedGoogleCardsStyleBFragment feedGoogleCardsStyleBFragment) {
        this.a = feedGoogleCardsStyleBFragment;
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerViewsCount = i - ((ListView) adapterView).getHeaderViewsCount();
        if (headerViewsCount < this.a.mFeedAdapter.getCount() && headerViewsCount >= 0) {
            this.a.mFeedAdapter.b(headerViewsCount).doFeedLongClicked();
        }
        return true;
    }
}
