package com.qq.reader.module.feed.activity;

import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.utils.StatisticsManager;

class FeedGoogleCardsFragment$15 implements Runnable {
    final /* synthetic */ FeedGoogleCardsFragment a;

    FeedGoogleCardsFragment$15(FeedGoogleCardsFragment feedGoogleCardsFragment) {
        this.a = feedGoogleCardsFragment;
    }

    public void run() {
        FeedGoogleCardsFragment.access$2400(this.a).clear();
        FeedGoogleCardsFragment.access$2400(this.a).put("prefer", d.aS(this.a.getApplicationContext()) + "");
        i.a("event_C63", FeedGoogleCardsFragment.access$2400(this.a), this.a.getApplicationContext());
        j.a(62, 2);
        StatisticsManager.a().a("event_C63", FeedGoogleCardsFragment.access$2400(this.a));
    }
}
