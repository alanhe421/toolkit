package com.qq.reader.module.feed.data.impl;

import android.view.View;
import android.view.View.OnClickListener;

class FeedBaseCard$3 implements OnClickListener {
    final /* synthetic */ FeedBaseCard a;

    FeedBaseCard$3(FeedBaseCard feedBaseCard) {
        this.a = feedBaseCard;
    }

    public void onClick(View view) {
        this.a.doOnFeedClicked(false);
    }
}
