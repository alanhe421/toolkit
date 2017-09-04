package com.qq.reader.module.feed.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

class FeedGoogleCardsFragment$7 implements OnItemLongClickListener {
    final /* synthetic */ FeedGoogleCardsFragment a;

    FeedGoogleCardsFragment$7(FeedGoogleCardsFragment feedGoogleCardsFragment) {
        this.a = feedGoogleCardsFragment;
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerViewsCount = i - ((ListView) adapterView).getHeaderViewsCount();
        if (headerViewsCount < this.a.mFeedAdapter.getCount() && headerViewsCount >= 0) {
            this.a.mFeedAdapter.b(headerViewsCount).doFeedLongClicked();
        }
        return true;
    }
}
