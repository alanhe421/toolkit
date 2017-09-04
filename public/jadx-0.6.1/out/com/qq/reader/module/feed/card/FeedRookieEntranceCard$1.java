package com.qq.reader.module.feed.card;

import android.view.View;
import android.view.View.OnClickListener;

class FeedRookieEntranceCard$1 implements OnClickListener {
    final /* synthetic */ FeedRookieEntranceCard a;

    FeedRookieEntranceCard$1(FeedRookieEntranceCard feedRookieEntranceCard) {
        this.a = feedRookieEntranceCard;
    }

    public void onClick(View view) {
        this.a.doOnFeedClicked(true);
    }
}
