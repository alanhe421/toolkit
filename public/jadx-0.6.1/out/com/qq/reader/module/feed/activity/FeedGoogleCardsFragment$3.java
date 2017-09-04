package com.qq.reader.module.feed.activity;

import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.m;
import com.qq.reader.common.widget.SwipeRefreshLayout.b;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import java.util.Iterator;

class FeedGoogleCardsFragment$3 implements b {
    final /* synthetic */ FeedGoogleCardsFragment a;

    FeedGoogleCardsFragment$3(FeedGoogleCardsFragment feedGoogleCardsFragment) {
        this.a = feedGoogleCardsFragment;
    }

    public void a() {
        if (!m.a(this.a.getActivity())) {
            if (!ao.s(this.a.getApplicationContext()) || FeedGoogleCardsFragment.access$500(this.a).hasMessages(8000006)) {
                Iterator it = this.a.mFeedAdapter.c.iterator();
                while (it.hasNext()) {
                    FeedBaseCard feedBaseCard = (FeedBaseCard) it.next();
                    if (feedBaseCard instanceof com.qq.reader.module.feed.data.impl.b) {
                        ((com.qq.reader.module.feed.data.impl.b) feedBaseCard).change();
                    }
                }
                if (FeedGoogleCardsFragment.access$700(this.a).size() > 1) {
                    FeedGoogleCardsFragment.access$802(this.a, FeedGoogleCardsFragment.access$800(this.a) + 1);
                    if (FeedGoogleCardsFragment.access$800(this.a) == FeedGoogleCardsFragment.access$700(this.a).size()) {
                        FeedGoogleCardsFragment.access$802(this.a, 0);
                    }
                    i.a("event_F5", null, this.a.getContext());
                    FeedGoogleCardsFragment.access$900(this.a, (String) FeedGoogleCardsFragment.access$700(this.a).get(FeedGoogleCardsFragment.access$800(this.a)));
                }
                FeedGoogleCardsFragment.access$1000(this.a).setRefreshing(false);
            } else {
                FeedGoogleCardsFragment.access$600(this.a, false);
            }
        }
        i.a("event_C55", null, this.a.getApplicationContext());
        StatisticsManager.a().a("event_C55", null);
        FeedGoogleCardsFragment.access$1108(this.a);
        if (!FeedGoogleCardsFragment.access$1200(this.a)) {
            FeedGoogleCardsFragment.access$1202(this.a, true);
            d.bt(this.a.getApplicationContext());
        }
    }
}
