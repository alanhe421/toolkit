package com.qq.reader.module.feed.activity;

import android.view.View;
import com.qq.reader.common.monitor.i;
import com.qq.reader.cservice.adv.a;
import com.qq.reader.module.bookstore.qnative.c.c;

class FeedGoogleCardsFragment$27 extends c {
    final /* synthetic */ FeedGoogleCardsFragment a;

    FeedGoogleCardsFragment$27(FeedGoogleCardsFragment feedGoogleCardsFragment) {
        this.a = feedGoogleCardsFragment;
    }

    public void a(View view) {
        FeedGoogleCardsFragment.access$300(this.a);
        a.a(this.a.getApplicationContext(), "FEED_ADV_OPERATING_ACTIVITY_DATE");
        i.a("event_F99", null, this.a.getApplicationContext());
    }
}
