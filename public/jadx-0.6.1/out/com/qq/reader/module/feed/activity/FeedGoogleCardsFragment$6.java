package com.qq.reader.module.feed.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.qq.reader.module.feed.card.Feed3HorBooksGroupCard;
import com.qq.reader.module.feed.card.Feed3VerBooksGroupCard;
import com.qq.reader.module.feed.card.Feed4HorBooksGroupCard;
import com.qq.reader.module.feed.card.FeedRecommendACard;
import com.qq.reader.module.feed.card.FeedRecommendBCard;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;

class FeedGoogleCardsFragment$6 implements OnItemClickListener {
    final /* synthetic */ FeedGoogleCardsFragment a;

    FeedGoogleCardsFragment$6(FeedGoogleCardsFragment feedGoogleCardsFragment) {
        this.a = feedGoogleCardsFragment;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerViewsCount = i - ((ListView) adapterView).getHeaderViewsCount();
        if (headerViewsCount < this.a.mFeedAdapter.getCount() && headerViewsCount >= 0) {
            FeedBaseCard b = this.a.mFeedAdapter.b(headerViewsCount);
            if (!((b instanceof Feed3VerBooksGroupCard) || (b instanceof Feed3HorBooksGroupCard) || (b instanceof FeedRecommendACard) || (b instanceof FeedRecommendBCard) || (b instanceof Feed4HorBooksGroupCard))) {
                this.a.mFeedAdapter.b(headerViewsCount).doOnFeedClicked(true);
            }
            FeedGoogleCardsFragment.access$1102(this.a, 0);
        }
    }
}
