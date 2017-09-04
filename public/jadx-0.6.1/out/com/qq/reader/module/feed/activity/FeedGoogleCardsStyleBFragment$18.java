package com.qq.reader.module.feed.activity;

import com.qq.reader.appconfig.a;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.widget.SwipeRefreshLayout.b;
import com.qq.reader.module.feed.loader.d;
import java.util.ArrayList;

class FeedGoogleCardsStyleBFragment$18 implements b {
    final /* synthetic */ FeedGoogleCardsStyleBFragment a;

    FeedGoogleCardsStyleBFragment$18(FeedGoogleCardsStyleBFragment feedGoogleCardsStyleBFragment) {
        this.a = feedGoogleCardsStyleBFragment;
    }

    public void a() {
        ArrayList e = d.b().e();
        if (e == null || e.size() != 3) {
            FeedGoogleCardsStyleBFragment.access$200(this.a);
        } else {
            this.a.mFeedAdapter.l();
        }
        FeedGoogleCardsStyleBFragment.access$300(this.a).a("");
        i.a("event_C55", null, this.a.getApplicationContext());
        StatisticsManager.a().a("event_C55", null);
        if (!FeedGoogleCardsStyleBFragment.access$400(this.a)) {
            FeedGoogleCardsStyleBFragment.access$402(this.a, true);
            a.d.bt(this.a.getApplicationContext());
        }
        FeedGoogleCardsStyleBFragment.access$500(this.a);
    }
}
