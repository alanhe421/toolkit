package com.qq.reader.module.feed.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.qq.reader.module.feed.card.Feed3HorBooksGroupCard;
import com.qq.reader.module.feed.card.Feed3VerBooksGroupCard;
import com.qq.reader.module.feed.card.FeedRecommendACard;
import com.qq.reader.module.feed.card.FeedRecommendBCard;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;

class FeedGoogleCardsStyleBFragment$21 implements OnItemClickListener {
    final /* synthetic */ FeedGoogleCardsStyleBFragment a;

    FeedGoogleCardsStyleBFragment$21(FeedGoogleCardsStyleBFragment feedGoogleCardsStyleBFragment) {
        this.a = feedGoogleCardsStyleBFragment;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerViewsCount = i - ((ListView) adapterView).getHeaderViewsCount();
        if (headerViewsCount < this.a.mFeedAdapter.getCount() && headerViewsCount >= 0) {
            FeedBaseCard b = this.a.mFeedAdapter.b(headerViewsCount);
            if (!((b instanceof Feed3VerBooksGroupCard) || (b instanceof Feed3HorBooksGroupCard) || (b instanceof FeedRecommendACard) || (b instanceof FeedRecommendBCard))) {
                this.a.mFeedAdapter.b(headerViewsCount).doOnFeedClicked(true);
            }
            FeedGoogleCardsStyleBFragment.access$1002(this.a, 0);
        }
    }
}
